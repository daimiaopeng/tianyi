package org.apache.commons.httpclient.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IdleConnectionHandler
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$util$IdleConnectionHandler;
  private Map connectionToAdded = new HashMap();

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$util$IdleConnectionHandler == null)
    {
      localClass = class$("org.apache.commons.httpclient.util.IdleConnectionHandler");
      class$org$apache$commons$httpclient$util$IdleConnectionHandler = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$util$IdleConnectionHandler;
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

  public void add(HttpConnection paramHttpConnection)
  {
    Long localLong = new Long(System.currentTimeMillis());
    if (LOG.isDebugEnabled())
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Adding connection at: ");
      localStringBuffer.append(localLong);
      localLog.debug(localStringBuffer.toString());
    }
    this.connectionToAdded.put(paramHttpConnection, localLong);
  }

  public void closeIdleConnections(long paramLong)
  {
    long l = System.currentTimeMillis() - paramLong;
    if (LOG.isDebugEnabled())
    {
      Log localLog2 = LOG;
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Checking for connections, idleTimeout: ");
      localStringBuffer2.append(l);
      localLog2.debug(localStringBuffer2.toString());
    }
    Iterator localIterator = this.connectionToAdded.keySet().iterator();
    while (localIterator.hasNext())
    {
      HttpConnection localHttpConnection = (HttpConnection)localIterator.next();
      Long localLong = (Long)this.connectionToAdded.get(localHttpConnection);
      if (localLong.longValue() <= l)
      {
        if (LOG.isDebugEnabled())
        {
          Log localLog1 = LOG;
          StringBuffer localStringBuffer1 = new StringBuffer();
          localStringBuffer1.append("Closing connection, connection time: ");
          localStringBuffer1.append(localLong);
          localLog1.debug(localStringBuffer1.toString());
        }
        localIterator.remove();
        localHttpConnection.close();
      }
    }
  }

  public void remove(HttpConnection paramHttpConnection)
  {
    this.connectionToAdded.remove(paramHttpConnection);
  }

  public void removeAll()
  {
    this.connectionToAdded.clear();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.IdleConnectionHandler
 * JD-Core Version:    0.6.1
 */