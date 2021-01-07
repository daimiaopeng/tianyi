package com.common.busi;

import android.content.Context;
import android.content.pm.PackageManager;
import com.stub.StubApp;

@QVMProtect
public final class c
{
  private static PackageManager a = null;

  static
  {
    StubApp.interface11(2708);
  }

  static native String a(Context paramContext);

  public static native String a(Context paramContext, String paramString);

  public static native void a(Context paramContext, int paramInt);

  public static native void a(Context paramContext, long paramLong);

  public static native void a(Thread paramThread);

  public static native void a(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, int paramInt);

  public static native boolean a(Context paramContext, String paramString1, String paramString2);

  public static native boolean a(String paramString);

  static native Boolean b(Context paramContext, String paramString);

  public static native String b(Context paramContext);

  private static native boolean b(String paramString);

  public static native int c(Context paramContext);

  public static native String c(Context paramContext, String paramString);

  public static native int d(Context paramContext, String paramString);

  public static native long d(Context paramContext);

  public static native boolean e(Context paramContext, String paramString);

  public static native void f(Context paramContext, String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.common.busi.c
 * JD-Core Version:    0.6.1
 */