package com.common.busi;

import android.content.Context;
import com.stub.StubApp;
import java.io.File;
import java.net.Proxy;

@QVMProtect
public final class d
{
  private static final char[] a = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  static
  {
    StubApp.interface11(2709);
  }

  private static native String a(int paramInt);

  public static native String a(Context paramContext, String paramString);

  private static native String a(File paramFile);

  public static native String a(String paramString);

  public static native String a(String paramString1, String paramString2);

  public static native String a(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3);

  private static native String a(byte[] paramArrayOfByte);

  private static native Proxy a();

  public static native void a(Context paramContext, String paramString1, String paramString2);

  private static native byte[] a(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, int paramInt3);

  private static native byte[] a(String paramString, byte[] paramArrayOfByte);

  private static native byte[] a(byte[] paramArrayOfByte, String paramString);

  private static native String b(Context paramContext, String paramString);

  private static native String b(String paramString);

  private static native String b(String paramString1, String paramString2);

  private static native String b(byte[] paramArrayOfByte);

  private static native String c(String paramString);

  private static native byte[] c(String paramString1, String paramString2);

  private static native StringBuilder[] c(Context paramContext, String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.common.busi.d
 * JD-Core Version:    0.6.1
 */