package org.apache.commons.httpclient;

import java.net.Socket;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.httpclient.protocol.Protocol;

public class ProxyClient
{
  private HostConfiguration hostConfiguration = new HostConfiguration();
  private HttpClientParams params = null;
  private HttpState state = new HttpState();

  public ProxyClient()
  {
    this(new HttpClientParams());
  }

  public ProxyClient(HttpClientParams paramHttpClientParams)
  {
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Params may not be null");
    this.params = paramHttpClientParams;
  }

  public ConnectResponse connect()
  {
    HostConfiguration localHostConfiguration = getHostConfiguration();
    if (localHostConfiguration.getProxyHost() == null)
      throw new IllegalStateException("proxy host must be configured");
    if (localHostConfiguration.getHost() == null)
      throw new IllegalStateException("destination host must be configured");
    if (localHostConfiguration.getProtocol().isSecure())
      throw new IllegalStateException("secure protocol socket factory may not be used");
    ConnectMethod localConnectMethod = new ConnectMethod(getHostConfiguration());
    localConnectMethod.getParams().setDefaults(getParams());
    DummyConnectionManager localDummyConnectionManager = new DummyConnectionManager();
    localDummyConnectionManager.setConnectionParams(getParams());
    new HttpMethodDirector(localDummyConnectionManager, localHostConfiguration, getParams(), getState()).executeMethod(localConnectMethod);
    ConnectResponse localConnectResponse = new ConnectResponse(null);
    localConnectResponse.setConnectMethod(localConnectMethod);
    if (localConnectMethod.getStatusCode() == 200)
      localConnectResponse.setSocket(localDummyConnectionManager.getConnection().getSocket());
    else
      localDummyConnectionManager.getConnection().close();
    return localConnectResponse;
  }

  public HostConfiguration getHostConfiguration()
  {
    try
    {
      HostConfiguration localHostConfiguration = this.hostConfiguration;
      return localHostConfiguration;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpClientParams getParams()
  {
    try
    {
      HttpClientParams localHttpClientParams = this.params;
      return localHttpClientParams;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpState getState()
  {
    try
    {
      HttpState localHttpState = this.state;
      return localHttpState;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHostConfiguration(HostConfiguration paramHostConfiguration)
  {
    try
    {
      this.hostConfiguration = paramHostConfiguration;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setParams(HttpClientParams paramHttpClientParams)
  {
    if (paramHttpClientParams == null);
    try
    {
      throw new IllegalArgumentException("Parameters may not be null");
      this.params = paramHttpClientParams;
      return;
      Object localObject1;
      throw localObject1;
    }
    finally
    {
    }
  }

  public void setState(HttpState paramHttpState)
  {
    try
    {
      this.state = paramHttpState;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static class ConnectResponse
  {
    private ConnectMethod connectMethod;
    private Socket socket;

    private ConnectResponse()
    {
    }

    ConnectResponse(ProxyClient.1 param1)
    {
      this();
    }

    private void setConnectMethod(ConnectMethod paramConnectMethod)
    {
      this.connectMethod = paramConnectMethod;
    }

    private void setSocket(Socket paramSocket)
    {
      this.socket = paramSocket;
    }

    public ConnectMethod getConnectMethod()
    {
      return this.connectMethod;
    }

    public Socket getSocket()
    {
      return this.socket;
    }
  }

  static class DummyConnectionManager
    implements HttpConnectionManager
  {
    private HttpParams connectionParams;
    private HttpConnection httpConnection;

    public void closeIdleConnections(long paramLong)
    {
    }

    public HttpConnection getConnection()
    {
      return this.httpConnection;
    }

    public HttpConnection getConnection(HostConfiguration paramHostConfiguration)
    {
      return getConnectionWithTimeout(paramHostConfiguration, -1L);
    }

    public HttpConnection getConnection(HostConfiguration paramHostConfiguration, long paramLong)
    {
      return getConnectionWithTimeout(paramHostConfiguration, paramLong);
    }

    public HttpConnection getConnectionWithTimeout(HostConfiguration paramHostConfiguration, long paramLong)
    {
      this.httpConnection = new HttpConnection(paramHostConfiguration);
      this.httpConnection.setHttpConnectionManager(this);
      this.httpConnection.getParams().setDefaults(this.connectionParams);
      return this.httpConnection;
    }

    public HttpConnectionManagerParams getParams()
    {
      return null;
    }

    public void releaseConnection(HttpConnection paramHttpConnection)
    {
    }

    public void setConnectionParams(HttpParams paramHttpParams)
    {
      this.connectionParams = paramHttpParams;
    }

    public void setParams(HttpConnectionManagerParams paramHttpConnectionManagerParams)
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ProxyClient
 * JD-Core Version:    0.6.1
 */