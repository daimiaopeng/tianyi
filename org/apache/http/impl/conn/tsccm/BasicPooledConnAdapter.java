package org.apache.http.impl.conn.tsccm;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.AbstractPooledConnAdapter;

@Deprecated
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter
{
  protected BasicPooledConnAdapter(ThreadSafeClientConnManager paramThreadSafeClientConnManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super((ClientConnectionManager)null, (AbstractPoolEntry)null);
    throw new RuntimeException("Stub!");
  }

  protected void detach()
  {
    throw new RuntimeException("Stub!");
  }

  protected ClientConnectionManager getManager()
  {
    throw new RuntimeException("Stub!");
  }

  protected AbstractPoolEntry getPoolEntry()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.BasicPooledConnAdapter
 * JD-Core Version:    0.6.1
 */