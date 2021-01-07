package com.qihoo.jiagutracker.utils;

import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class StubTrace
{
  public static final String LOG_TAG = "StubTrace";
  public static final boolean QIHOO_LOG = true;
  public static int logLevel = 2;

  static
  {
    StubApp.interface11(2749);
  }

  public static native void d(String paramString);

  public static native void d(String paramString1, String paramString2);

  public static native void e(String paramString);

  public static native void e(String paramString1, String paramString2);

  private static native void handleException(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, int paramInt);

  public static native void handleException(Throwable paramThrowable);

  public static native void i(String paramString);

  public static native void i(String paramString1, String paramString2);

  public static native void v(String paramString);

  public static native void v(String paramString1, String paramString2);

  public static native void w(String paramString);

  public static native void w(String paramString1, String paramString2);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.utils.StubTrace
 * JD-Core Version:    0.6.1
 */