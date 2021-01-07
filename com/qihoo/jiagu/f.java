package com.qihoo.jiagu;

import java.lang.reflect.Method;

public final class f
{
  private static int a = 2;

  public static void a(String paramString1, String paramString2)
  {
    try
    {
      Class.forName("android.util.Log").getDeclaredMethod("e", new Class[] { String.class, String.class }).invoke(null, new Object[] { paramString1, paramString2 });
      label41: return;
    }
    catch (Throwable localThrowable)
    {
      break label41;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagu.f
 * JD-Core Version:    0.6.1
 */