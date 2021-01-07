package com.karics.library.zxing.a;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

final class f
  implements Camera.PreviewCallback
{
  private static final String a = "f";
  private final b b;
  private Handler c;
  private int d;

  f(b paramb)
  {
    this.b = paramb;
  }

  void a(Handler paramHandler, int paramInt)
  {
    this.c = paramHandler;
    this.d = paramInt;
  }

  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    Point localPoint = this.b.a();
    Handler localHandler = this.c;
    if ((localPoint != null) && (localHandler != null))
    {
      localHandler.obtainMessage(this.d, localPoint.x, localPoint.y, paramArrayOfByte).sendToTarget();
      this.c = null;
    }
    else
    {
      Log.d(a, "Got preview callback, but no handler or resolution available");
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.a.f
 * JD-Core Version:    0.6.1
 */