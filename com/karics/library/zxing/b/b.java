package com.karics.library.zxing.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.karics.library.zxing.android.CaptureActivity;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public final class b extends Handler
{
  private static final String a = "b";
  private final CaptureActivity b;
  private final MultiFormatReader c = new MultiFormatReader();
  private boolean d = true;

  b(CaptureActivity paramCaptureActivity, Map<DecodeHintType, Object> paramMap)
  {
    this.c.setHints(paramMap);
    this.b = paramCaptureActivity;
  }

  private static void a(PlanarYUVLuminanceSource paramPlanarYUVLuminanceSource, Bundle paramBundle)
  {
    int[] arrayOfInt = paramPlanarYUVLuminanceSource.renderThumbnail();
    int i = paramPlanarYUVLuminanceSource.getThumbnailWidth();
    Bitmap localBitmap = Bitmap.createBitmap(arrayOfInt, 0, i, i, paramPlanarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localBitmap.compress(Bitmap.CompressFormat.JPEG, 50, localByteArrayOutputStream);
    paramBundle.putByteArray("barcode_bitmap", localByteArrayOutputStream.toByteArray());
    paramBundle.putFloat("barcode_scaled_factor", i / paramPlanarYUVLuminanceSource.getWidth());
  }

  // ERROR //
  private void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: invokestatic 101	java/lang/System:currentTimeMillis	()J
    //   3: lstore 4
    //   5: aload_1
    //   6: arraylength
    //   7: newarray byte
    //   9: astore 6
    //   11: iconst_0
    //   12: istore 7
    //   14: iload 7
    //   16: iload_3
    //   17: if_icmpge +47 -> 64
    //   20: iconst_0
    //   21: istore 23
    //   23: iload 23
    //   25: iload_2
    //   26: if_icmpge +32 -> 58
    //   29: aload 6
    //   31: iconst_m1
    //   32: iload_3
    //   33: iload 23
    //   35: iload_3
    //   36: imul
    //   37: iadd
    //   38: iload 7
    //   40: isub
    //   41: iadd
    //   42: aload_1
    //   43: iload 23
    //   45: iload 7
    //   47: iload_2
    //   48: imul
    //   49: iadd
    //   50: baload
    //   51: bastore
    //   52: iinc 23 1
    //   55: goto -32 -> 23
    //   58: iinc 7 1
    //   61: goto -47 -> 14
    //   64: aload_0
    //   65: getfield 32	com/karics/library/zxing/b/b:b	Lcom/karics/library/zxing/android/CaptureActivity;
    //   68: invokevirtual 106	com/karics/library/zxing/android/CaptureActivity:c	()Lcom/karics/library/zxing/a/d;
    //   71: aload 6
    //   73: iload_3
    //   74: iload_2
    //   75: invokevirtual 111	com/karics/library/zxing/a/d:a	([BII)Lcom/google/zxing/PlanarYUVLuminanceSource;
    //   78: astore 8
    //   80: aload 8
    //   82: ifnull +61 -> 143
    //   85: new 113	com/google/zxing/BinaryBitmap
    //   88: dup
    //   89: new 115	com/google/zxing/common/HybridBinarizer
    //   92: dup
    //   93: aload 8
    //   95: invokespecial 118	com/google/zxing/common/HybridBinarizer:<init>	(Lcom/google/zxing/LuminanceSource;)V
    //   98: invokespecial 121	com/google/zxing/BinaryBitmap:<init>	(Lcom/google/zxing/Binarizer;)V
    //   101: astore 9
    //   103: aload_0
    //   104: getfield 26	com/karics/library/zxing/b/b:c	Lcom/google/zxing/MultiFormatReader;
    //   107: aload 9
    //   109: invokevirtual 125	com/google/zxing/MultiFormatReader:decodeWithState	(Lcom/google/zxing/BinaryBitmap;)Lcom/google/zxing/Result;
    //   112: astore 10
    //   114: aload_0
    //   115: getfield 26	com/karics/library/zxing/b/b:c	Lcom/google/zxing/MultiFormatReader;
    //   118: invokevirtual 128	com/google/zxing/MultiFormatReader:reset	()V
    //   121: goto +25 -> 146
    //   124: astore 22
    //   126: aload_0
    //   127: getfield 26	com/karics/library/zxing/b/b:c	Lcom/google/zxing/MultiFormatReader;
    //   130: invokevirtual 128	com/google/zxing/MultiFormatReader:reset	()V
    //   133: aload 22
    //   135: athrow
    //   136: aload_0
    //   137: getfield 26	com/karics/library/zxing/b/b:c	Lcom/google/zxing/MultiFormatReader;
    //   140: invokevirtual 128	com/google/zxing/MultiFormatReader:reset	()V
    //   143: aconst_null
    //   144: astore 10
    //   146: aload_0
    //   147: getfield 32	com/karics/library/zxing/b/b:b	Lcom/karics/library/zxing/android/CaptureActivity;
    //   150: invokevirtual 131	com/karics/library/zxing/android/CaptureActivity:b	()Landroid/os/Handler;
    //   153: astore 11
    //   155: aload 10
    //   157: ifnull +107 -> 264
    //   160: invokestatic 101	java/lang/System:currentTimeMillis	()J
    //   163: lstore 12
    //   165: getstatic 133	com/karics/library/zxing/b/b:a	Ljava/lang/String;
    //   168: astore 14
    //   170: new 135	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   177: astore 15
    //   179: aload 15
    //   181: ldc 138
    //   183: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload 15
    //   189: lload 12
    //   191: lload 4
    //   193: lsub
    //   194: invokevirtual 145	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: aload 15
    //   200: ldc 147
    //   202: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: aload 14
    //   208: aload 15
    //   210: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokestatic 156	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   216: pop
    //   217: aload 11
    //   219: ifnull +60 -> 279
    //   222: aload 11
    //   224: ldc 157
    //   226: aload 10
    //   228: invokestatic 163	android/os/Message:obtain	(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
    //   231: astore 20
    //   233: new 79	android/os/Bundle
    //   236: dup
    //   237: invokespecial 164	android/os/Bundle:<init>	()V
    //   240: astore 21
    //   242: aload 8
    //   244: aload 21
    //   246: invokestatic 166	com/karics/library/zxing/b/b:a	(Lcom/google/zxing/PlanarYUVLuminanceSource;Landroid/os/Bundle;)V
    //   249: aload 20
    //   251: aload 21
    //   253: invokevirtual 170	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   256: aload 20
    //   258: invokevirtual 173	android/os/Message:sendToTarget	()V
    //   261: goto +18 -> 279
    //   264: aload 11
    //   266: ifnull +13 -> 279
    //   269: aload 11
    //   271: ldc 174
    //   273: invokestatic 177	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
    //   276: invokevirtual 173	android/os/Message:sendToTarget	()V
    //   279: return
    //
    // Exception table:
    //   from	to	target	type
    //   103	114	124	finally
    //   103	114	136	com/google/zxing/ReaderException
  }

  public void handleMessage(Message paramMessage)
  {
    if (!this.d)
      return;
    int i = paramMessage.what;
    if (i != 2131034156)
    {
      if (i == 2131034211)
      {
        this.d = false;
        Looper.myLooper().quit();
      }
    }
    else
      a((byte[])paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.b.b
 * JD-Core Version:    0.6.1
 */