package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.b;
import com.tencent.bugly.proguard.y;

public class BuglyLog
{
  public static void d(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "null";
    if (b.c)
      Log.d(paramString1, paramString2);
    y.a("D", paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "null";
    if (b.c)
      Log.e(paramString1, paramString2);
    y.a("E", paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "null";
    if (b.c)
      Log.e(paramString1, paramString2, paramThrowable);
    y.a("E", paramString1, paramThrowable);
  }

  public static void i(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "null";
    if (b.c)
      Log.i(paramString1, paramString2);
    y.a("I", paramString1, paramString2);
  }

  public static void setCache(int paramInt)
  {
    y.a(paramInt);
  }

  public static void v(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "null";
    if (b.c)
      Log.v(paramString1, paramString2);
    y.a("V", paramString1, paramString2);
  }

  public static void w(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "null";
    if (b.c)
      Log.w(paramString1, paramString2);
    y.a("W", paramString1, paramString2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.BuglyLog
 * JD-Core Version:    0.6.1
 */