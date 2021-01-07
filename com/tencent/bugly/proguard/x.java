package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

public final class x
{
  public static String a = "CrashReport";
  public static boolean b = false;
  private static String c = "CrashReportInfo";

  private static boolean a(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    if (!b)
      return false;
    if (paramString == null)
      paramString = "null";
    else if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
      paramString = String.format(Locale.US, paramString, paramArrayOfObject);
    if (paramInt != 5)
    {
      switch (paramInt)
      {
      default:
        return false;
      case 3:
        Log.e(a, paramString);
        return true;
      case 2:
        Log.w(a, paramString);
        return true;
      case 1:
        Log.d(a, paramString);
        return true;
      case 0:
      }
      Log.i(a, paramString);
      return true;
    }
    Log.i(c, paramString);
    return true;
  }

  public static boolean a(Class paramClass, String paramString, Object[] paramArrayOfObject)
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramClass.getSimpleName();
    arrayOfObject[1] = paramString;
    return a(0, String.format(localLocale, "[%s] %s", arrayOfObject), paramArrayOfObject);
  }

  public static boolean a(String paramString, Object[] paramArrayOfObject)
  {
    return a(0, paramString, paramArrayOfObject);
  }

  public static boolean a(Throwable paramThrowable)
  {
    if (!b)
      return false;
    return a(2, z.a(paramThrowable), new Object[0]);
  }

  public static boolean b(Class paramClass, String paramString, Object[] paramArrayOfObject)
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramClass.getSimpleName();
    arrayOfObject[1] = paramString;
    return a(1, String.format(localLocale, "[%s] %s", arrayOfObject), paramArrayOfObject);
  }

  public static boolean b(String paramString, Object[] paramArrayOfObject)
  {
    return a(5, paramString, paramArrayOfObject);
  }

  public static boolean b(Throwable paramThrowable)
  {
    if (!b)
      return false;
    return a(3, z.a(paramThrowable), new Object[0]);
  }

  public static boolean c(String paramString, Object[] paramArrayOfObject)
  {
    return a(1, paramString, paramArrayOfObject);
  }

  public static boolean d(String paramString, Object[] paramArrayOfObject)
  {
    return a(2, paramString, paramArrayOfObject);
  }

  public static boolean e(String paramString, Object[] paramArrayOfObject)
  {
    return a(3, paramString, paramArrayOfObject);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.x
 * JD-Core Version:    0.6.1
 */