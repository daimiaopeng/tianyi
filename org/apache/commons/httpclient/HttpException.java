package org.apache.commons.httpclient;

import java.io.IOException;
import java.lang.reflect.Method;

public class HttpException extends IOException
{
  static Class class$java$lang$Throwable;
  private final Throwable cause;
  private String reason;
  private int reasonCode = 200;

  public HttpException()
  {
    this.cause = null;
  }

  public HttpException(String paramString)
  {
    super(paramString);
    this.cause = null;
  }

  public HttpException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    this.cause = paramThrowable;
    try
    {
      Class[] arrayOfClass = new Class[1];
      Class localClass1;
      if (class$java$lang$Throwable == null)
      {
        localClass1 = class$("java.lang.Throwable");
        class$java$lang$Throwable = localClass1;
      }
      else
      {
        localClass1 = class$java$lang$Throwable;
      }
      arrayOfClass[0] = localClass1;
      Class localClass2;
      if (class$java$lang$Throwable == null)
      {
        localClass2 = class$("java.lang.Throwable");
        class$java$lang$Throwable = localClass2;
      }
      else
      {
        localClass2 = class$java$lang$Throwable;
      }
      localClass2.getMethod("initCause", arrayOfClass).invoke(this, new Object[] { paramThrowable });
    }
    catch (Exception localException)
    {
    }
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

  public Throwable getCause()
  {
    return this.cause;
  }

  public String getReason()
  {
    return this.reason;
  }

  public int getReasonCode()
  {
    return this.reasonCode;
  }

  public void printStackTrace()
  {
    printStackTrace(System.err);
  }

  // ERROR //
  public void printStackTrace(java.io.PrintStream paramPrintStream)
  {
    // Byte code:
    //   0: iconst_0
    //   1: anewarray 28	java/lang/Class
    //   4: astore_2
    //   5: aload_0
    //   6: invokevirtual 83	java/lang/Object:getClass	()Ljava/lang/Class;
    //   9: ldc 85
    //   11: aload_2
    //   12: invokevirtual 42	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   15: pop
    //   16: aload_0
    //   17: aload_1
    //   18: invokespecial 86	java/io/IOException:printStackTrace	(Ljava/io/PrintStream;)V
    //   21: goto +29 -> 50
    //   24: aload_0
    //   25: aload_1
    //   26: invokespecial 86	java/io/IOException:printStackTrace	(Ljava/io/PrintStream;)V
    //   29: aload_0
    //   30: getfield 20	org/apache/commons/httpclient/HttpException:cause	Ljava/lang/Throwable;
    //   33: ifnull +17 -> 50
    //   36: aload_1
    //   37: ldc 88
    //   39: invokevirtual 93	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   42: aload_0
    //   43: getfield 20	org/apache/commons/httpclient/HttpException:cause	Ljava/lang/Throwable;
    //   46: aload_1
    //   47: invokevirtual 96	java/lang/Throwable:printStackTrace	(Ljava/io/PrintStream;)V
    //   50: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	21	24	java/lang/Exception
  }

  // ERROR //
  public void printStackTrace(java.io.PrintWriter paramPrintWriter)
  {
    // Byte code:
    //   0: iconst_0
    //   1: anewarray 28	java/lang/Class
    //   4: astore_2
    //   5: aload_0
    //   6: invokevirtual 83	java/lang/Object:getClass	()Ljava/lang/Class;
    //   9: ldc 85
    //   11: aload_2
    //   12: invokevirtual 42	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   15: pop
    //   16: aload_0
    //   17: aload_1
    //   18: invokespecial 99	java/io/IOException:printStackTrace	(Ljava/io/PrintWriter;)V
    //   21: goto +29 -> 50
    //   24: aload_0
    //   25: aload_1
    //   26: invokespecial 99	java/io/IOException:printStackTrace	(Ljava/io/PrintWriter;)V
    //   29: aload_0
    //   30: getfield 20	org/apache/commons/httpclient/HttpException:cause	Ljava/lang/Throwable;
    //   33: ifnull +17 -> 50
    //   36: aload_1
    //   37: ldc 88
    //   39: invokevirtual 102	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   42: aload_0
    //   43: getfield 20	org/apache/commons/httpclient/HttpException:cause	Ljava/lang/Throwable;
    //   46: aload_1
    //   47: invokevirtual 103	java/lang/Throwable:printStackTrace	(Ljava/io/PrintWriter;)V
    //   50: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	21	24	java/lang/Exception
  }

  public void setReason(String paramString)
  {
    this.reason = paramString;
  }

  public void setReasonCode(int paramInt)
  {
    this.reasonCode = paramInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpException
 * JD-Core Version:    0.6.1
 */