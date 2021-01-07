package com.cndatacom.campus.jscportal;

import com.cndatacom.e.j;

public class a
  implements Thread.UncaughtExceptionHandler
{
  private static a a;

  public static a a()
  {
    try
    {
      if (a == null)
        a = new a();
      a locala = a;
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    j.a("TrineaAndroidCommon", paramThrowable, "AppExceptionHandler uncaughtException");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.campus.jscportal.a
 * JD-Core Version:    0.6.1
 */