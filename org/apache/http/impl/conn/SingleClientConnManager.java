package org.apache.http.impl.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.params.HttpParams;

@Deprecated
public class SingleClientConnManager
  implements ClientConnectionManager
{
  public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
  protected boolean alwaysShutDown;
  protected ClientConnectionOperator connOperator;
  protected long connectionExpiresTime;
  protected volatile boolean isShutDown;
  protected long lastReleaseTime;
  protected ConnAdapter managedConn;
  protected SchemeRegistry schemeRegistry;
  protected PoolEntry uniquePoolEntry;

  public SingleClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    throw new RuntimeException("Stub!");
  }

  protected final void assertStillUp()
  {
    throw new RuntimeException("Stub!");
  }

  public void closeExpiredConnections()
  {
    throw new RuntimeException("Stub!");
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    throw new RuntimeException("Stub!");
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    throw new RuntimeException("Stub!");
  }

  protected void finalize()
  {
    throw new RuntimeException("Stub!");
  }

  public ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    throw new RuntimeException("Stub!");
  }

  public SchemeRegistry getSchemeRegistry()
  {
    throw new RuntimeException("Stub!");
  }

  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    throw new RuntimeException("Stub!");
  }

  public final ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    throw new RuntimeException("Stub!");
  }

  protected void revokeConnection()
  {
    throw new RuntimeException("Stub!");
  }

  public void shutdown()
  {
    throw new RuntimeException("Stub!");
  }

  protected class ConnAdapter extends AbstractPooledConnAdapter
  {
    protected ConnAdapter(SingleClientConnManager.PoolEntry paramHttpRoute, HttpRoute arg3)
    {
      super((AbstractPoolEntry)null);
      throw new RuntimeException("Stub!");
    }
  }

  protected class PoolEntry extends AbstractPoolEntry
  {
    protected PoolEntry()
    {
      super((HttpRoute)null);
      throw new RuntimeException("Stub!");
    }

    protected void close()
    {
      throw new RuntimeException("Stub!");
    }

    protected void shutdown()
    {
      throw new RuntimeException("Stub!");
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.SingleClientConnManager
 * JD-Core Version:    0.6.1
 */