package com.qihoo.jiagutracker;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Field;

public class ViewInfo
{
  byte[] background;
  int buttom;
  String classname;
  boolean isFocus;
  int left;
  int right;
  String text;
  int top;

  public ViewInfo(View paramView)
  {
    this.classname = paramView.getClass().getCanonicalName();
    this.text = getText(paramView);
    this.background = getBackground(paramView);
    this.left = paramView.getLeft();
    this.top = paramView.getTop();
    this.right = paramView.getRight();
    this.buttom = paramView.getBottom();
    this.isFocus = paramView.isFocused();
  }

  private Bitmap toGrayscale(Bitmap paramBitmap)
  {
    int i = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }

  public Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    Bitmap localBitmap;
    if ((paramDrawable.getIntrinsicWidth() <= 0) || (paramDrawable.getIntrinsicHeight() <= 0))
      localBitmap = null;
    while (true)
    {
      return localBitmap;
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1);
      Canvas localCanvas;
      for (Bitmap.Config localConfig = Bitmap.Config.ARGB_8888; ; localConfig = Bitmap.Config.RGB_565)
      {
        localBitmap = Bitmap.createBitmap(i, j, localConfig);
        localCanvas = new Canvas(localBitmap);
        if (paramDrawable.getBounds() != null)
          break label81;
        localBitmap = null;
        break;
      }
      label81: paramDrawable.draw(localCanvas);
    }
  }

  public byte[] getBackground(View paramView)
  {
    return getBytes(paramView);
  }

  public Bitmap getBitmapFromView(View paramView)
    throws Throwable
  {
    if ((paramView instanceof ImageView));
    Object localObject;
    for (Drawable localDrawable = ((ImageView)paramView).getDrawable(); ; localDrawable = paramView.getBackground())
    {
      localObject = null;
      if (localDrawable != null)
        break;
      return localObject;
    }
    NinePatchDrawable localNinePatchDrawable;
    if ((localDrawable instanceof NinePatchDrawable))
      localNinePatchDrawable = (NinePatchDrawable)localDrawable;
    while (true)
    {
      Bitmap localBitmap1;
      try
      {
        Field localField = NinePatchDrawable.class.getDeclaredField("mNinePatch");
        localField.setAccessible(true);
        localNinePatch = (NinePatch)localField.get(localNinePatchDrawable);
        int i = Build.VERSION.SDK_INT;
        Bitmap localBitmap2 = null;
        if (i >= 19)
        {
          localBitmap2 = null;
          if (localNinePatch != null)
          {
            Bitmap localBitmap3 = localNinePatch.getBitmap();
            localBitmap2 = null;
            if (localBitmap3 == null)
              localBitmap2 = localNinePatch.getBitmap();
          }
        }
        localBitmap1 = localBitmap2;
        if (localBitmap1 == null)
          localBitmap1 = drawableToBitmap(localDrawable);
        localObject = localBitmap1;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        NinePatch localNinePatch = null;
        continue;
      }
      if ((localDrawable instanceof BitmapDrawable))
        localBitmap1 = ((BitmapDrawable)localDrawable).getBitmap();
      else
        localBitmap1 = null;
    }
  }

  // ERROR //
  public byte[] getBytes(Bitmap paramBitmap)
    throws Throwable
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: bipush 32
    //   4: bipush 32
    //   6: iconst_1
    //   7: invokestatic 203	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   10: invokespecial 205	com/qihoo/jiagutracker/ViewInfo:toGrayscale	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   13: astore 7
    //   15: new 207	java/io/ByteArrayOutputStream
    //   18: dup
    //   19: invokespecial 208	java/io/ByteArrayOutputStream:<init>	()V
    //   22: astore_3
    //   23: aload 7
    //   25: getstatic 214	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   28: bipush 100
    //   30: aload_3
    //   31: invokevirtual 218	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   34: pop
    //   35: aload_3
    //   36: invokevirtual 222	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   39: astore 9
    //   41: aload 9
    //   43: astore 6
    //   45: aload_3
    //   46: ifnull +7 -> 53
    //   49: aload_3
    //   50: invokevirtual 225	java/io/ByteArrayOutputStream:close	()V
    //   53: aload 6
    //   55: areturn
    //   56: astore 4
    //   58: aconst_null
    //   59: astore_3
    //   60: aload 4
    //   62: invokevirtual 195	java/lang/Throwable:printStackTrace	()V
    //   65: aconst_null
    //   66: astore 6
    //   68: aload_3
    //   69: ifnull -16 -> 53
    //   72: aload_3
    //   73: invokevirtual 225	java/io/ByteArrayOutputStream:close	()V
    //   76: aconst_null
    //   77: astore 6
    //   79: goto -26 -> 53
    //   82: astore_2
    //   83: aconst_null
    //   84: astore_3
    //   85: aload_3
    //   86: ifnull +7 -> 93
    //   89: aload_3
    //   90: invokevirtual 225	java/io/ByteArrayOutputStream:close	()V
    //   93: aload_2
    //   94: athrow
    //   95: astore 5
    //   97: aload 5
    //   99: astore_2
    //   100: goto -15 -> 85
    //   103: astore 4
    //   105: goto -45 -> 60
    //
    // Exception table:
    //   from	to	target	type
    //   0	23	56	java/lang/Throwable
    //   0	23	82	finally
    //   23	41	95	finally
    //   60	65	95	finally
    //   23	41	103	java/lang/Throwable
  }

  public byte[] getBytes(View paramView)
  {
    Object localObject = null;
    if (paramView == null);
    while (true)
    {
      return localObject;
      try
      {
        Bitmap localBitmap = getBitmapFromView(paramView);
        localObject = null;
        if (localBitmap != null)
        {
          byte[] arrayOfByte = getBytes(localBitmap);
          localObject = arrayOfByte;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        localObject = null;
      }
    }
  }

  public String getText(View paramView)
  {
    String str;
    if ((paramView instanceof TextView))
      str = ((TextView)paramView).getText().toString();
    while (true)
    {
      return str;
      if ((paramView instanceof EditText))
        str = ((EditText)paramView).getHint().toString();
      else
        str = null;
    }
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[8];
    String str1;
    String str2;
    if (this.classname == null)
    {
      str1 = "";
      arrayOfObject[0] = str1;
      if (this.text != null)
        break label118;
      str2 = "";
      label30: arrayOfObject[1] = str2;
      arrayOfObject[2] = Integer.valueOf(this.left);
      arrayOfObject[3] = Integer.valueOf(this.top);
      arrayOfObject[4] = Integer.valueOf(this.right);
      arrayOfObject[5] = Integer.valueOf(this.buttom);
      arrayOfObject[6] = Boolean.valueOf(this.isFocus);
      if (this.background != null)
        break label126;
    }
    label118: label126: for (String str3 = ""; ; str3 = new String(this.background))
    {
      arrayOfObject[7] = str3;
      return String.format("classname:%s text:%s left:%d top:%d right:%d buttom:%d isFocus:%b background:%s \n", arrayOfObject);
      str1 = this.classname;
      break;
      str2 = this.text;
      break label30;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.ViewInfo
 * JD-Core Version:    0.6.1
 */