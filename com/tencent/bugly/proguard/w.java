package com.tencent.bugly.proguard;

import com.tencent.bugly.b;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class w
{
  private static w a;
  private ScheduledExecutorService b = null;

  protected w()
  {
    if ((this.b == null) || (this.b.isShutdown()))
      x.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
  }

  public static w a()
  {
    try
    {
      if (a == null)
        a = new w();
      w localw = a;
      return localw;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final boolean a(Runnable paramRunnable)
  {
    try
    {
      if (!c())
      {
        x.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
        return false;
      }
      if (paramRunnable == null)
      {
        x.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
        return false;
      }
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramRunnable.getClass().getName();
      x.c("[AsyncTaskHandler] Post a normal task: %s", arrayOfObject);
      try
      {
        this.b.execute(paramRunnable);
        return true;
      }
      catch (Throwable localThrowable)
      {
        if (b.c)
          localThrowable.printStackTrace();
        return false;
      }
    }
    finally
    {
    }
  }

  public final boolean a(Runnable paramRunnable, long paramLong)
  {
    try
    {
      if (!c())
      {
        x.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
        return false;
      }
      if (paramRunnable == null)
      {
        x.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
        return false;
      }
      if (paramLong <= 0L)
        paramLong = 0L;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(paramLong);
      arrayOfObject[1] = paramRunnable.getClass().getName();
      x.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", arrayOfObject);
      try
      {
        this.b.schedule(paramRunnable, paramLong, TimeUnit.MILLISECONDS);
        return true;
      }
      catch (Throwable localThrowable)
      {
        if (b.c)
          localThrowable.printStackTrace();
        return false;
      }
    }
    finally
    {
    }
  }

  public final void b()
  {
    try
    {
      if ((this.b != null) && (!this.b.isShutdown()))
      {
        x.c("[AsyncTaskHandler] Close async handler.", new Object[0]);
        this.b.shutdownNow();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final boolean c()
  {
    try
    {
      if (this.b != null)
      {
        boolean bool2 = this.b.isShutdown();
        if (!bool2)
        {
          bool1 = true;
          return bool1;
        }
      }
      boolean bool1 = false;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.w
 * JD-Core Version:    0.6.1
 */