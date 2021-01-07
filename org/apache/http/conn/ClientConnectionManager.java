package org.apache.http.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

@Deprecated
public abstract interface ClientConnectionManager
{
  public abstract void closeExpiredConnections();

  public abstract void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit);

  public abstract SchemeRegistry getSchemeRegistry();

  public abstract void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit);

  public abstract ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject);

  public abstract void shutdown();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ClientConnectionManager
 * JD-Core Version:    0.6.1
 */