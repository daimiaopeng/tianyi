package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpConnectionManager;

public class IdleConnectionTimeoutThread extends Thread
{
  private List connectionManagers = new ArrayList();
  private long connectionTimeout = 3000L;
  private boolean shutdown = false;
  private long timeoutInterval = 1000L;

  public IdleConnectionTimeoutThread()
  {
    setDaemon(true);
  }

  public void addConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
      this.connectionManagers.add(paramHttpConnectionManager);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void handleCloseIdleConnections(HttpConnectionManager paramHttpConnectionManager)
  {
    paramHttpConnectionManager.closeIdleConnections(this.connectionTimeout);
  }

  public void removeConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
      this.connectionManagers.remove(paramHttpConnectionManager);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: goto +4 -> 6
    //   5: pop
    //   6: aload_0
    //   7: getfield 22	org/apache/commons/httpclient/util/IdleConnectionTimeoutThread:shutdown	Z
    //   10: ifne +49 -> 59
    //   13: aload_0
    //   14: getfield 20	org/apache/commons/httpclient/util/IdleConnectionTimeoutThread:connectionManagers	Ljava/util/List;
    //   17: invokeinterface 67 1 0
    //   22: astore_2
    //   23: aload_2
    //   24: invokeinterface 73 1 0
    //   29: ifeq +19 -> 48
    //   32: aload_0
    //   33: aload_2
    //   34: invokeinterface 77 1 0
    //   39: checkcast 52	org/apache/commons/httpclient/HttpConnectionManager
    //   42: invokevirtual 79	org/apache/commons/httpclient/util/IdleConnectionTimeoutThread:handleCloseIdleConnections	(Lorg/apache/commons/httpclient/HttpConnectionManager;)V
    //   45: goto -22 -> 23
    //   48: aload_0
    //   49: aload_0
    //   50: getfield 26	org/apache/commons/httpclient/util/IdleConnectionTimeoutThread:timeoutInterval	J
    //   53: invokevirtual 84	java/lang/Object:wait	(J)V
    //   56: goto -51 -> 5
    //   59: aload_0
    //   60: getfield 20	org/apache/commons/httpclient/util/IdleConnectionTimeoutThread:connectionManagers	Ljava/util/List;
    //   63: invokeinterface 87 1 0
    //   68: aload_0
    //   69: monitorexit
    //   70: return
    //   71: astore_1
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   48	56	5	java/lang/InterruptedException
    //   5	45	71	finally
    //   48	56	71	finally
    //   59	68	71	finally
  }

  public void setConnectionTimeout(long paramLong)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
      this.connectionTimeout = paramLong;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setTimeoutInterval(long paramLong)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
      this.timeoutInterval = paramLong;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void shutdown()
  {
    try
    {
      this.shutdown = true;
      notifyAll();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.IdleConnectionTimeoutThread
 * JD-Core Version:    0.6.1
 */