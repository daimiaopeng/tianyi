package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;

@Deprecated
public class BasicPoolEntry extends AbstractPoolEntry
{
  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute, ReferenceQueue<Object> paramReferenceQueue)
  {
    super((ClientConnectionOperator)null, (HttpRoute)null);
    throw new RuntimeException("Stub!");
  }

  protected final OperatedClientConnection getConnection()
  {
    throw new RuntimeException("Stub!");
  }

  protected final HttpRoute getPlannedRoute()
  {
    throw new RuntimeException("Stub!");
  }

  protected final BasicPoolEntryRef getWeakRef()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.BasicPoolEntry
 * JD-Core Version:    0.6.1
 */