package org.apache.http.impl.conn;

import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter
{
  protected volatile AbstractPoolEntry poolEntry;

  protected AbstractPooledConnAdapter(ClientConnectionManager paramClientConnectionManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super((ClientConnectionManager)null, (OperatedClientConnection)null);
    throw new RuntimeException("Stub!");
  }

  protected final void assertAttached()
  {
    throw new RuntimeException("Stub!");
  }

  public void close()
  {
    throw new RuntimeException("Stub!");
  }

  protected void detach()
  {
    throw new RuntimeException("Stub!");
  }

  public HttpRoute getRoute()
  {
    throw new RuntimeException("Stub!");
  }

  public Object getState()
  {
    throw new RuntimeException("Stub!");
  }

  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public void setState(Object paramObject)
  {
    throw new RuntimeException("Stub!");
  }

  public void shutdown()
  {
    throw new RuntimeException("Stub!");
  }

  public void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.AbstractPooledConnAdapter
 * JD-Core Version:    0.6.1
 */