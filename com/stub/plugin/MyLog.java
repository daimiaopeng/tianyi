package com.stub.plugin;

import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;

public class MyLog
{
  private static final String TAG = "STUB";
  private static boolean isDebug = false;

  private static void handleException(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, int paramInt)
  {
    try
    {
      printException(paramThrowable);
      Class localClass1 = Class.forName("com.qihoo.bugreport.CrashReport");
      Class localClass2 = Class.forName("com.qihoo.bugreport.javacrash.ExceptionHandleReporter");
      Object localObject = localClass1.getDeclaredMethod("getExceptionHandlerInstance", new Class[0]).invoke(null, new Object[0]);
      Class[] arrayOfClass = new Class[4];
      arrayOfClass[0] = Thread.class;
      arrayOfClass[1] = Throwable.class;
      arrayOfClass[2] = Boolean.TYPE;
      arrayOfClass[3] = Integer.TYPE;
      Method localMethod = localClass2.getDeclaredMethod("uncaughtException", arrayOfClass);
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = paramThread;
      arrayOfObject[1] = paramThrowable;
      arrayOfObject[2] = Boolean.valueOf(paramBoolean);
      arrayOfObject[3] = Integer.valueOf(paramInt);
      localMethod.invoke(localObject, arrayOfObject);
      label124: return;
    }
    catch (Throwable localThrowable)
    {
      break label124;
    }
  }

  public static void handleException(Throwable paramThrowable)
  {
    handleException(Thread.currentThread(), paramThrowable, true, 19);
  }

  public static void log(String[] paramArrayOfString)
  {
    if (!isDebug);
    while (true)
    {
      return;
      StringBuilder localStringBuilder = new StringBuilder();
      int i = paramArrayOfString.length;
      int j = 0;
      if (j < i)
      {
        String str = paramArrayOfString[j];
        if (TextUtils.isEmpty(str));
        while (true)
        {
          j++;
          break;
          localStringBuilder.append(str + ", ");
        }
      }
      Log.e("STUB", localStringBuilder.toString());
    }
  }

  public static void printException(Throwable paramThrowable)
  {
    if ((paramThrowable != null) && (!isDebug))
      Log.e("STUB", Log.getStackTraceString(paramThrowable));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.MyLog
 * JD-Core Version:    0.6.1
 */