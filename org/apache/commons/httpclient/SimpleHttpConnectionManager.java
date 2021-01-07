package org.apache.commons.httpclient;

import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleHttpConnectionManager
  implements HttpConnectionManager
{
  private static final Log LOG = LogFactory.getLog(localClass);
  private static final String MISUSE_MESSAGE = "SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.";
  static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
  private boolean alwaysClose = false;
  protected HttpConnection httpConnection;
  private long idleStartTime = 9223372036854775807L;
  private volatile boolean inUse = false;
  private HttpConnectionManagerParams params = new HttpConnectionManagerParams();

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null)
    {
      localClass = class$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
      class$org$apache$commons$httpclient$SimpleHttpConnectionManager = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
    }
  }

  public SimpleHttpConnectionManager()
  {
  }

  public SimpleHttpConnectionManager(boolean paramBoolean)
  {
    this.alwaysClose = paramBoolean;
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
  static void finishLastResponse(HttpConnection paramHttpConnection)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 84	org/apache/commons/httpclient/HttpConnection:getLastResponseInputStream	()Ljava/io/InputStream;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnull +19 -> 25
    //   9: aload_0
    //   10: aconst_null
    //   11: invokevirtual 88	org/apache/commons/httpclient/HttpConnection:setLastResponseInputStream	(Ljava/io/InputStream;)V
    //   14: aload_1
    //   15: invokevirtual 93	java/io/InputStream:close	()V
    //   18: goto +7 -> 25
    //   21: aload_0
    //   22: invokevirtual 94	org/apache/commons/httpclient/HttpConnection:close	()V
    //   25: return
    //
    // Exception table:
    //   from	to	target	type
    //   14	18	21	java/io/IOException
  }

  public void closeIdleConnections(long paramLong)
  {
    long l = System.currentTimeMillis() - paramLong;
    if (this.idleStartTime <= l)
      this.httpConnection.close();
  }

  public HttpConnection getConnection(HostConfiguration paramHostConfiguration)
  {
    return getConnection(paramHostConfiguration, 0L);
  }

  public HttpConnection getConnection(HostConfiguration paramHostConfiguration, long paramLong)
  {
    return getConnectionWithTimeout(paramHostConfiguration, paramLong);
  }

  public HttpConnection getConnectionWithTimeout(HostConfiguration paramHostConfiguration, long paramLong)
  {
    if (this.httpConnection == null)
    {
      this.httpConnection = new HttpConnection(paramHostConfiguration);
      this.httpConnection.setHttpConnectionManager(this);
      this.httpConnection.getParams().setDefaults(this.params);
    }
    else if ((paramHostConfiguration.hostEquals(this.httpConnection)) && (paramHostConfiguration.proxyEquals(this.httpConnection)))
    {
      finishLastResponse(this.httpConnection);
    }
    else
    {
      if (this.httpConnection.isOpen())
        this.httpConnection.close();
      this.httpConnection.setHost(paramHostConfiguration.getHost());
      this.httpConnection.setPort(paramHostConfiguration.getPort());
      this.httpConnection.setProtocol(paramHostConfiguration.getProtocol());
      this.httpConnection.setLocalAddress(paramHostConfiguration.getLocalAddress());
      this.httpConnection.setProxyHost(paramHostConfiguration.getProxyHost());
      this.httpConnection.setProxyPort(paramHostConfiguration.getProxyPort());
    }
    this.idleStartTime = 9223372036854775807L;
    if (this.inUse)
      LOG.warn("SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.");
    this.inUse = true;
    return this.httpConnection;
  }

  public HttpConnectionManagerParams getParams()
  {
    return this.params;
  }

  public boolean isConnectionStaleCheckingEnabled()
  {
    return this.params.isStaleCheckingEnabled();
  }

  public void releaseConnection(HttpConnection paramHttpConnection)
  {
    if (paramHttpConnection != this.httpConnection)
      throw new IllegalStateException("Unexpected release of an unknown connection.");
    if (this.alwaysClose)
      this.httpConnection.close();
    else
      finishLastResponse(this.httpConnection);
    this.inUse = false;
    this.idleStartTime = System.currentTimeMillis();
  }

  public void setConnectionStaleCheckingEnabled(boolean paramBoolean)
  {
    this.params.setStaleCheckingEnabled(paramBoolean);
  }

  public void setParams(HttpConnectionManagerParams paramHttpConnectionManagerParams)
  {
    if (paramHttpConnectionManagerParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpConnectionManagerParams;
  }

  public void shutdown()
  {
    this.httpConnection.close();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.SimpleHttpConnectionManager
 * JD-Core Version:    0.6.1
 */