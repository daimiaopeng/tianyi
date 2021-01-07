package org.apache.commons.httpclient.util;

public final class TimeoutController
{
  public static void execute(Runnable paramRunnable, long paramLong)
  {
    Thread localThread = new Thread(paramRunnable, "Timeout guard");
    localThread.setDaemon(true);
    execute(localThread, paramLong);
  }

  public static void execute(Thread paramThread, long paramLong)
  {
    paramThread.start();
    try
    {
      paramThread.join(paramLong);
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    if (paramThread.isAlive())
    {
      paramThread.interrupt();
      throw new TimeoutException();
    }
  }

  public static class TimeoutException extends Exception
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.TimeoutController
 * JD-Core Version:    0.6.1
 */