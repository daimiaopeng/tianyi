package android.support.v4.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;

public final class PrintHelper
{

  @SuppressLint({"InlinedApi"})
  public static final int COLOR_MODE_COLOR = 2;

  @SuppressLint({"InlinedApi"})
  public static final int COLOR_MODE_MONOCHROME = 1;
  static final boolean IS_MIN_MARGINS_HANDLING_CORRECT = false;
  private static final String LOG_TAG = "PrintHelper";
  private static final int MAX_PRINT_SIZE = 3500;
  public static final int ORIENTATION_LANDSCAPE = 1;
  public static final int ORIENTATION_PORTRAIT = 2;
  static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = false;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  int mColorMode = 2;
  final Context mContext;
  BitmapFactory.Options mDecodeOptions = null;
  final Object mLock = new Object();
  int mOrientation = 1;
  int mScaleMode = 2;

  static
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool1 = true;
    boolean bool2;
    if ((i >= 20) && (Build.VERSION.SDK_INT <= 23))
      bool2 = false;
    else
      bool2 = true;
    PRINT_ACTIVITY_RESPECTS_ORIENTATION = bool2;
    if (Build.VERSION.SDK_INT == 23)
      bool1 = false;
  }

  public PrintHelper(@NonNull Context paramContext)
  {
    this.mContext = paramContext;
  }

  static Bitmap convertBitmapForColorMode(Bitmap paramBitmap, int paramInt)
  {
    if (paramInt != 1)
      return paramBitmap;
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    localCanvas.setBitmap(null);
    return localBitmap;
  }

  @RequiresApi(19)
  private static PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes)
  {
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder().setMediaSize(paramPrintAttributes.getMediaSize()).setResolution(paramPrintAttributes.getResolution()).setMinMargins(paramPrintAttributes.getMinMargins());
    if (paramPrintAttributes.getColorMode() != 0)
      localBuilder.setColorMode(paramPrintAttributes.getColorMode());
    if ((Build.VERSION.SDK_INT >= 23) && (paramPrintAttributes.getDuplexMode() != 0))
      localBuilder.setDuplexMode(paramPrintAttributes.getDuplexMode());
    return localBuilder;
  }

  static Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3)
  {
    Matrix localMatrix = new Matrix();
    float f1 = paramRectF.width();
    float f2 = paramInt1;
    float f3 = f1 / f2;
    float f4;
    if (paramInt3 == 2)
      f4 = Math.max(f3, paramRectF.height() / paramInt2);
    else
      f4 = Math.min(f3, paramRectF.height() / paramInt2);
    localMatrix.postScale(f4, f4);
    localMatrix.postTranslate((paramRectF.width() - f2 * f4) / 2.0F, (paramRectF.height() - f4 * paramInt2) / 2.0F);
    return localMatrix;
  }

  static boolean isPortrait(Bitmap paramBitmap)
  {
    boolean bool;
    if (paramBitmap.getWidth() <= paramBitmap.getHeight())
      bool = true;
    else
      bool = false;
    return bool;
  }

  // ERROR //
  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +101 -> 102
    //   4: aload_0
    //   5: getfield 63	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
    //   8: ifnonnull +6 -> 14
    //   11: goto +91 -> 102
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_0
    //   17: getfield 63	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
    //   20: invokevirtual 203	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: aload_1
    //   24: invokevirtual 209	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   27: astore 7
    //   29: aload 7
    //   31: aconst_null
    //   32: aload_2
    //   33: invokestatic 215	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   36: astore 8
    //   38: aload 7
    //   40: ifnull +23 -> 63
    //   43: aload 7
    //   45: invokevirtual 220	java/io/InputStream:close	()V
    //   48: goto +15 -> 63
    //   51: astore 9
    //   53: ldc 19
    //   55: ldc 222
    //   57: aload 9
    //   59: invokestatic 228	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   62: pop
    //   63: aload 8
    //   65: areturn
    //   66: astore 4
    //   68: aload 7
    //   70: astore_3
    //   71: goto +5 -> 76
    //   74: astore 4
    //   76: aload_3
    //   77: ifnull +22 -> 99
    //   80: aload_3
    //   81: invokevirtual 220	java/io/InputStream:close	()V
    //   84: goto +15 -> 99
    //   87: astore 5
    //   89: ldc 19
    //   91: ldc 222
    //   93: aload 5
    //   95: invokestatic 228	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   98: pop
    //   99: aload 4
    //   101: athrow
    //   102: new 230	java/lang/IllegalArgumentException
    //   105: dup
    //   106: ldc 232
    //   108: invokespecial 235	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   111: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   43	48	51	java/io/IOException
    //   29	38	66	finally
    //   16	29	74	finally
    //   80	84	87	java/io/IOException
  }

  public static boolean systemSupportsPrint()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public int getColorMode()
  {
    return this.mColorMode;
  }

  public int getOrientation()
  {
    if ((Build.VERSION.SDK_INT >= 19) && (this.mOrientation == 0))
      return 1;
    return this.mOrientation;
  }

  public int getScaleMode()
  {
    return this.mScaleMode;
  }

  // ERROR //
  Bitmap loadConstrainedBitmap(Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +239 -> 240
    //   4: aload_0
    //   5: getfield 63	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
    //   8: ifnonnull +6 -> 14
    //   11: goto +229 -> 240
    //   14: new 243	android/graphics/BitmapFactory$Options
    //   17: dup
    //   18: invokespecial 244	android/graphics/BitmapFactory$Options:<init>	()V
    //   21: astore_2
    //   22: aload_2
    //   23: iconst_1
    //   24: putfield 247	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   27: aload_0
    //   28: aload_1
    //   29: aload_2
    //   30: invokespecial 249	android/support/v4/print/PrintHelper:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   33: pop
    //   34: aload_2
    //   35: getfield 252	android/graphics/BitmapFactory$Options:outWidth	I
    //   38: istore 4
    //   40: aload_2
    //   41: getfield 255	android/graphics/BitmapFactory$Options:outHeight	I
    //   44: istore 5
    //   46: iload 4
    //   48: ifle +190 -> 238
    //   51: iload 5
    //   53: ifgt +6 -> 59
    //   56: goto +182 -> 238
    //   59: iload 4
    //   61: iload 5
    //   63: invokestatic 258	java/lang/Math:max	(II)I
    //   66: istore 6
    //   68: iconst_1
    //   69: istore 7
    //   71: iload 6
    //   73: sipush 3500
    //   76: if_icmple +18 -> 94
    //   79: iload 6
    //   81: iconst_1
    //   82: iushr
    //   83: istore 6
    //   85: iload 7
    //   87: iconst_1
    //   88: ishl
    //   89: istore 7
    //   91: goto -20 -> 71
    //   94: iload 7
    //   96: ifle +140 -> 236
    //   99: iload 4
    //   101: iload 5
    //   103: invokestatic 260	java/lang/Math:min	(II)I
    //   106: iload 7
    //   108: idiv
    //   109: ifgt +6 -> 115
    //   112: goto +124 -> 236
    //   115: aload_0
    //   116: getfield 55	android/support/v4/print/PrintHelper:mLock	Ljava/lang/Object;
    //   119: astore 8
    //   121: aload 8
    //   123: monitorenter
    //   124: aload_0
    //   125: new 243	android/graphics/BitmapFactory$Options
    //   128: dup
    //   129: invokespecial 244	android/graphics/BitmapFactory$Options:<init>	()V
    //   132: putfield 53	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   135: aload_0
    //   136: getfield 53	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   139: iconst_1
    //   140: putfield 263	android/graphics/BitmapFactory$Options:inMutable	Z
    //   143: aload_0
    //   144: getfield 53	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   147: iload 7
    //   149: putfield 266	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   152: aload_0
    //   153: getfield 53	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   156: astore 10
    //   158: aload 8
    //   160: monitorexit
    //   161: aload_0
    //   162: aload_1
    //   163: aload 10
    //   165: invokespecial 249	android/support/v4/print/PrintHelper:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   168: astore 14
    //   170: aload_0
    //   171: getfield 55	android/support/v4/print/PrintHelper:mLock	Ljava/lang/Object;
    //   174: astore 15
    //   176: aload 15
    //   178: monitorenter
    //   179: aload_0
    //   180: aconst_null
    //   181: putfield 53	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   184: aload 15
    //   186: monitorexit
    //   187: aload 14
    //   189: areturn
    //   190: astore 16
    //   192: aload 15
    //   194: monitorexit
    //   195: aload 16
    //   197: athrow
    //   198: astore 11
    //   200: aload_0
    //   201: getfield 55	android/support/v4/print/PrintHelper:mLock	Ljava/lang/Object;
    //   204: astore 12
    //   206: aload 12
    //   208: monitorenter
    //   209: aload_0
    //   210: aconst_null
    //   211: putfield 53	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   214: aload 12
    //   216: monitorexit
    //   217: aload 11
    //   219: athrow
    //   220: astore 13
    //   222: aload 12
    //   224: monitorexit
    //   225: aload 13
    //   227: athrow
    //   228: astore 9
    //   230: aload 8
    //   232: monitorexit
    //   233: aload 9
    //   235: athrow
    //   236: aconst_null
    //   237: areturn
    //   238: aconst_null
    //   239: areturn
    //   240: new 230	java/lang/IllegalArgumentException
    //   243: dup
    //   244: ldc_w 268
    //   247: invokespecial 235	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   250: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   179	195	190	finally
    //   161	170	198	finally
    //   209	217	220	finally
    //   222	225	220	finally
    //   124	161	228	finally
    //   230	233	228	finally
  }

  public void printBitmap(@NonNull String paramString, @NonNull Bitmap paramBitmap)
  {
    printBitmap(paramString, paramBitmap, null);
  }

  public void printBitmap(@NonNull String paramString, @NonNull Bitmap paramBitmap, @Nullable OnPrintFinishCallback paramOnPrintFinishCallback)
  {
    if ((Build.VERSION.SDK_INT >= 19) && (paramBitmap != null))
    {
      PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
      PrintAttributes.MediaSize localMediaSize;
      if (isPortrait(paramBitmap))
        localMediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
      else
        localMediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
      PrintAttributes localPrintAttributes = new PrintAttributes.Builder().setMediaSize(localMediaSize).setColorMode(this.mColorMode).build();
      PrintBitmapAdapter localPrintBitmapAdapter = new PrintBitmapAdapter(paramString, this.mScaleMode, paramBitmap, paramOnPrintFinishCallback);
      localPrintManager.print(paramString, localPrintBitmapAdapter, localPrintAttributes);
      return;
    }
  }

  public void printBitmap(@NonNull String paramString, @NonNull Uri paramUri)
  {
    printBitmap(paramString, paramUri, null);
  }

  public void printBitmap(@NonNull String paramString, @NonNull Uri paramUri, @Nullable OnPrintFinishCallback paramOnPrintFinishCallback)
  {
    if (Build.VERSION.SDK_INT < 19)
      return;
    PrintUriAdapter localPrintUriAdapter = new PrintUriAdapter(paramString, paramUri, paramOnPrintFinishCallback, this.mScaleMode);
    PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder();
    localBuilder.setColorMode(this.mColorMode);
    if ((this.mOrientation != 1) && (this.mOrientation != 0))
    {
      if (this.mOrientation == 2)
        localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
    }
    else
      localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
    localPrintManager.print(paramString, localPrintUriAdapter, localBuilder.build());
  }

  public void setColorMode(int paramInt)
  {
    this.mColorMode = paramInt;
  }

  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }

  public void setScaleMode(int paramInt)
  {
    this.mScaleMode = paramInt;
  }

  @RequiresApi(19)
  void writeBitmap(final PrintAttributes paramPrintAttributes, final int paramInt, final Bitmap paramBitmap, final ParcelFileDescriptor paramParcelFileDescriptor, final CancellationSignal paramCancellationSignal, final PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
  {
    final PrintAttributes localPrintAttributes;
    if (IS_MIN_MARGINS_HANDLING_CORRECT)
      localPrintAttributes = paramPrintAttributes;
    else
      localPrintAttributes = copyAttributes(paramPrintAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
    AsyncTask local1 = new AsyncTask()
    {
      protected Throwable doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        try
        {
          if (paramCancellationSignal.isCanceled())
            return null;
          PrintedPdfDocument localPrintedPdfDocument1 = new PrintedPdfDocument(PrintHelper.this.mContext, localPrintAttributes);
          Bitmap localBitmap = PrintHelper.convertBitmapForColorMode(paramBitmap, localPrintAttributes.getColorMode());
          boolean bool1 = paramCancellationSignal.isCanceled();
          if (bool1)
            return null;
          try
          {
            PdfDocument.Page localPage1 = localPrintedPdfDocument1.startPage(1);
            Object localObject2;
            if (PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT)
            {
              localObject2 = new RectF(localPage1.getInfo().getContentRect());
            }
            else
            {
              PrintedPdfDocument localPrintedPdfDocument2 = new PrintedPdfDocument(PrintHelper.this.mContext, paramPrintAttributes);
              PdfDocument.Page localPage2 = localPrintedPdfDocument2.startPage(1);
              RectF localRectF = new RectF(localPage2.getInfo().getContentRect());
              localPrintedPdfDocument2.finishPage(localPage2);
              localPrintedPdfDocument2.close();
              localObject2 = localRectF;
            }
            Matrix localMatrix = PrintHelper.getMatrix(localBitmap.getWidth(), localBitmap.getHeight(), (RectF)localObject2, paramInt);
            if (!PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT)
            {
              localMatrix.postTranslate(((RectF)localObject2).left, ((RectF)localObject2).top);
              localPage1.getCanvas().clipRect((RectF)localObject2);
            }
            localPage1.getCanvas().drawBitmap(localBitmap, localMatrix, null);
            localPrintedPdfDocument1.finishPage(localPage1);
            boolean bool2 = paramCancellationSignal.isCanceled();
            if (bool2)
            {
              ParcelFileDescriptor localParcelFileDescriptor3;
              return null;
            }
            localPrintedPdfDocument1.writeTo(new FileOutputStream(paramParcelFileDescriptor.getFileDescriptor()));
            localPrintedPdfDocument1.close();
            ParcelFileDescriptor localParcelFileDescriptor2 = paramParcelFileDescriptor;
            if (localParcelFileDescriptor2 != null);
            try
            {
              paramParcelFileDescriptor.close();
            }
            catch (IOException localIOException2)
            {
            }
            if (localBitmap != paramBitmap)
              localBitmap.recycle();
          }
          finally
          {
            localPrintedPdfDocument1.close();
            ParcelFileDescriptor localParcelFileDescriptor1 = paramParcelFileDescriptor;
            if (localParcelFileDescriptor1 != null);
            try
            {
              paramParcelFileDescriptor.close();
            }
            catch (IOException localIOException3)
            {
            }
            if (localBitmap != paramBitmap)
              localBitmap.recycle();
          }
        }
        catch (Throwable localThrowable)
        {
          return localThrowable;
        }
        return null;
      }

      protected void onPostExecute(Throwable paramAnonymousThrowable)
      {
        if (paramCancellationSignal.isCanceled())
        {
          paramWriteResultCallback.onWriteCancelled();
        }
        else if (paramAnonymousThrowable == null)
        {
          PrintDocumentAdapter.WriteResultCallback localWriteResultCallback = paramWriteResultCallback;
          PageRange[] arrayOfPageRange = new PageRange[1];
          arrayOfPageRange[0] = PageRange.ALL_PAGES;
          localWriteResultCallback.onWriteFinished(arrayOfPageRange);
        }
        else
        {
          Log.e("PrintHelper", "Error writing printed content", paramAnonymousThrowable);
          paramWriteResultCallback.onWriteFailed(null);
        }
      }
    };
    local1.execute(new Void[0]);
  }

  public static abstract interface OnPrintFinishCallback
  {
    public abstract void onFinish();
  }

  @RequiresApi(19)
  private class PrintBitmapAdapter extends PrintDocumentAdapter
  {
    private PrintAttributes mAttributes;
    private final Bitmap mBitmap;
    private final PrintHelper.OnPrintFinishCallback mCallback;
    private final int mFittingMode;
    private final String mJobName;

    PrintBitmapAdapter(String paramInt, int paramBitmap, Bitmap paramOnPrintFinishCallback, PrintHelper.OnPrintFinishCallback arg5)
    {
      this.mJobName = paramInt;
      this.mFittingMode = paramBitmap;
      this.mBitmap = paramOnPrintFinishCallback;
      Object localObject;
      this.mCallback = localObject;
    }

    public void onFinish()
    {
      if (this.mCallback != null)
        this.mCallback.onFinish();
    }

    public void onLayout(PrintAttributes paramPrintAttributes1, PrintAttributes paramPrintAttributes2, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
    {
      this.mAttributes = paramPrintAttributes2;
      paramLayoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), true ^ paramPrintAttributes2.equals(paramPrintAttributes1));
    }

    public void onWrite(PageRange[] paramArrayOfPageRange, ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
    {
      PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, paramParcelFileDescriptor, paramCancellationSignal, paramWriteResultCallback);
    }
  }

  @RequiresApi(19)
  private class PrintUriAdapter extends PrintDocumentAdapter
  {
    PrintAttributes mAttributes;
    Bitmap mBitmap;
    final PrintHelper.OnPrintFinishCallback mCallback;
    final int mFittingMode;
    final Uri mImageFile;
    final String mJobName;
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

    PrintUriAdapter(String paramUri, Uri paramOnPrintFinishCallback, PrintHelper.OnPrintFinishCallback paramInt, int arg5)
    {
      this.mJobName = paramUri;
      this.mImageFile = paramOnPrintFinishCallback;
      this.mCallback = paramInt;
      int i;
      this.mFittingMode = i;
      this.mBitmap = null;
    }

    void cancelLoad()
    {
      synchronized (PrintHelper.this.mLock)
      {
        if (PrintHelper.this.mDecodeOptions != null)
        {
          if (Build.VERSION.SDK_INT < 24)
            PrintHelper.this.mDecodeOptions.requestCancelDecode();
          PrintHelper.this.mDecodeOptions = null;
        }
        return;
      }
    }

    public void onFinish()
    {
      super.onFinish();
      cancelLoad();
      if (this.mLoadBitmap != null)
        this.mLoadBitmap.cancel(true);
      if (this.mCallback != null)
        this.mCallback.onFinish();
      if (this.mBitmap != null)
      {
        this.mBitmap.recycle();
        this.mBitmap = null;
      }
    }

    public void onLayout(final PrintAttributes paramPrintAttributes1, final PrintAttributes paramPrintAttributes2, final CancellationSignal paramCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
    {
      try
      {
        this.mAttributes = paramPrintAttributes2;
        if (paramCancellationSignal.isCanceled())
        {
          paramLayoutResultCallback.onLayoutCancelled();
          return;
        }
        if (this.mBitmap != null)
        {
          paramLayoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), true ^ paramPrintAttributes2.equals(paramPrintAttributes1));
          return;
        }
        AsyncTask local1 = new AsyncTask()
        {
          // ERROR //
          protected Bitmap doInBackground(Uri[] paramAnonymousArrayOfUri)
          {
            // Byte code:
            //   0: aload_0
            //   1: getfield 23	android/support/v4/print/PrintHelper$PrintUriAdapter$1:this$1	Landroid/support/v4/print/PrintHelper$PrintUriAdapter;
            //   4: getfield 42	android/support/v4/print/PrintHelper$PrintUriAdapter:this$0	Landroid/support/v4/print/PrintHelper;
            //   7: aload_0
            //   8: getfield 23	android/support/v4/print/PrintHelper$PrintUriAdapter$1:this$1	Landroid/support/v4/print/PrintHelper$PrintUriAdapter;
            //   11: getfield 46	android/support/v4/print/PrintHelper$PrintUriAdapter:mImageFile	Landroid/net/Uri;
            //   14: invokevirtual 52	android/support/v4/print/PrintHelper:loadConstrainedBitmap	(Landroid/net/Uri;)Landroid/graphics/Bitmap;
            //   17: astore_2
            //   18: aload_2
            //   19: areturn
            //   20: aconst_null
            //   21: areturn
            //
            // Exception table:
            //   from	to	target	type
            //   0	18	20	java/io/FileNotFoundException
          }

          protected void onCancelled(Bitmap paramAnonymousBitmap)
          {
            paramLayoutResultCallback.onLayoutCancelled();
            PrintHelper.PrintUriAdapter.this.mLoadBitmap = null;
          }

          protected void onPostExecute(Bitmap paramAnonymousBitmap)
          {
            super.onPostExecute(paramAnonymousBitmap);
            if ((paramAnonymousBitmap != null) && ((!PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION) || (PrintHelper.this.mOrientation == 0)))
              try
              {
                PrintAttributes.MediaSize localMediaSize = PrintHelper.PrintUriAdapter.this.mAttributes.getMediaSize();
                if ((localMediaSize != null) && (localMediaSize.isPortrait() != PrintHelper.isPortrait(paramAnonymousBitmap)))
                {
                  Matrix localMatrix = new Matrix();
                  localMatrix.postRotate(90.0F);
                  int i = paramAnonymousBitmap.getWidth();
                  int j = paramAnonymousBitmap.getHeight();
                  paramAnonymousBitmap = Bitmap.createBitmap(paramAnonymousBitmap, 0, 0, i, j, localMatrix, true);
                }
              }
              finally
              {
              }
            PrintHelper.PrintUriAdapter.this.mBitmap = paramAnonymousBitmap;
            if (paramAnonymousBitmap != null)
            {
              PrintDocumentInfo localPrintDocumentInfo = new PrintDocumentInfo.Builder(PrintHelper.PrintUriAdapter.this.mJobName).setContentType(1).setPageCount(1).build();
              boolean bool = true ^ paramPrintAttributes2.equals(paramPrintAttributes1);
              paramLayoutResultCallback.onLayoutFinished(localPrintDocumentInfo, bool);
            }
            else
            {
              paramLayoutResultCallback.onLayoutFailed(null);
            }
            PrintHelper.PrintUriAdapter.this.mLoadBitmap = null;
          }

          protected void onPreExecute()
          {
            paramCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
            {
              public void onCancel()
              {
                PrintHelper.PrintUriAdapter.this.cancelLoad();
                PrintHelper.PrintUriAdapter.1.this.cancel(false);
              }
            });
          }
        };
        this.mLoadBitmap = local1.execute(new Uri[0]);
        return;
      }
      finally
      {
      }
    }

    public void onWrite(PageRange[] paramArrayOfPageRange, ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
    {
      PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, paramParcelFileDescriptor, paramCancellationSignal, paramWriteResultCallback);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.print.PrintHelper
 * JD-Core Version:    0.6.1
 */