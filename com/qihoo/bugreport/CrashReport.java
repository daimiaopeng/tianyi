package com.qihoo.bugreport;

import android.content.Context;
import com.qihoo.bugreport.javacrash.ExceptionHandleReporter;

public class CrashReport
{
  private static Thread.UncaughtExceptionHandler a;
  private static ExceptionHandleReporter b;
  private static Context c;

  public static Context a()
  {
    return c;
  }

  public static ExceptionHandleReporter getExceptionHandlerInstance()
  {
    return b;
  }

  public static void init(Context paramContext)
  {
    if (paramContext == null);
    while (true)
    {
      return;
      try
      {
        if ((a != null) && ((c == null) || (b == null)))
        {
          c = paramContext;
          b = ExceptionHandleReporter.a(paramContext, a);
        }
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }

  public static void prepareInit()
  {
    if (a == null)
      a = Thread.getDefaultUncaughtExceptionHandler();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.bugreport.CrashReport
 * JD-Core Version:    0.6.1
 */