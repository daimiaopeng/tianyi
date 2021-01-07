package org.apache.http.conn;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

@Deprecated
public class BasicManagedEntity extends HttpEntityWrapper
  implements ConnectionReleaseTrigger, EofSensorWatcher
{
  protected final boolean attemptReuse;
  protected ManagedClientConnection managedConn;

  public BasicManagedEntity(HttpEntity paramHttpEntity, ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    super((HttpEntity)null);
    throw new RuntimeException("Stub!");
  }

  public void abortConnection()
  {
    throw new RuntimeException("Stub!");
  }

  public void consumeContent()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean eofDetected(InputStream paramInputStream)
  {
    throw new RuntimeException("Stub!");
  }

  public InputStream getContent()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isRepeatable()
  {
    throw new RuntimeException("Stub!");
  }

  public void releaseConnection()
  {
    throw new RuntimeException("Stub!");
  }

  protected void releaseManagedConnection()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean streamAbort(InputStream paramInputStream)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean streamClosed(InputStream paramInputStream)
  {
    throw new RuntimeException("Stub!");
  }

  public void writeTo(OutputStream paramOutputStream)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.BasicManagedEntity
 * JD-Core Version:    0.6.1
 */