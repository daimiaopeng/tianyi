package org.apache.commons.httpclient.util;

import java.io.InterruptedIOException;
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExceptionUtil
{
  private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();
  private static final Log LOG;
  private static final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
  static Class class$java$lang$Throwable;
  static Class class$org$apache$commons$httpclient$util$ExceptionUtil;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$util$ExceptionUtil == null)
    {
      localClass = class$("org.apache.commons.httpclient.util.ExceptionUtil");
      class$org$apache$commons$httpclient$util$ExceptionUtil = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$util$ExceptionUtil;
    }
    LOG = LogFactory.getLog(localClass);
  }

  // ERROR //
  private static Class SocketTimeoutExceptionClass()
  {
    // Byte code:
    //   0: ldc 49
    //   2: invokestatic 54	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: astore_0
    //   6: aload_0
    //   7: areturn
    //   8: aconst_null
    //   9: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	8	java/lang/ClassNotFoundException
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  // ERROR //
  private static Method getInitCauseMethod()
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 51	java/lang/Class
    //   4: astore_0
    //   5: getstatic 67	org/apache/commons/httpclient/util/ExceptionUtil:class$java$lang$Throwable	Ljava/lang/Class;
    //   8: ifnonnull +16 -> 24
    //   11: ldc 69
    //   13: invokestatic 22	org/apache/commons/httpclient/util/ExceptionUtil:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   16: astore_1
    //   17: aload_1
    //   18: putstatic 67	org/apache/commons/httpclient/util/ExceptionUtil:class$java$lang$Throwable	Ljava/lang/Class;
    //   21: goto +7 -> 28
    //   24: getstatic 67	org/apache/commons/httpclient/util/ExceptionUtil:class$java$lang$Throwable	Ljava/lang/Class;
    //   27: astore_1
    //   28: aload_0
    //   29: iconst_0
    //   30: aload_1
    //   31: aastore
    //   32: getstatic 67	org/apache/commons/httpclient/util/ExceptionUtil:class$java$lang$Throwable	Ljava/lang/Class;
    //   35: ifnonnull +16 -> 51
    //   38: ldc 69
    //   40: invokestatic 22	org/apache/commons/httpclient/util/ExceptionUtil:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   43: astore_2
    //   44: aload_2
    //   45: putstatic 67	org/apache/commons/httpclient/util/ExceptionUtil:class$java$lang$Throwable	Ljava/lang/Class;
    //   48: goto +7 -> 55
    //   51: getstatic 67	org/apache/commons/httpclient/util/ExceptionUtil:class$java$lang$Throwable	Ljava/lang/Class;
    //   54: astore_2
    //   55: aload_2
    //   56: ldc 71
    //   58: aload_0
    //   59: invokevirtual 75	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   62: astore_3
    //   63: aload_3
    //   64: areturn
    //   65: aconst_null
    //   66: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	63	65	java/lang/NoSuchMethodException
  }

  public static void initCause(Throwable paramThrowable1, Throwable paramThrowable2)
  {
    if (INIT_CAUSE_METHOD != null)
      try
      {
        INIT_CAUSE_METHOD.invoke(paramThrowable1, new Object[] { paramThrowable2 });
      }
      catch (Exception localException)
      {
        LOG.warn("Exception invoking Throwable.initCause", localException);
      }
  }

  public static boolean isSocketTimeoutException(InterruptedIOException paramInterruptedIOException)
  {
    if (SOCKET_TIMEOUT_CLASS != null)
      return SOCKET_TIMEOUT_CLASS.isInstance(paramInterruptedIOException);
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.ExceptionUtil
 * JD-Core Version:    0.6.1
 */