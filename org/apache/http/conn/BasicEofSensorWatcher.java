package org.apache.http.conn;

import java.io.InputStream;

@Deprecated
public class BasicEofSensorWatcher
  implements EofSensorWatcher
{
  protected boolean attemptReuse;
  protected ManagedClientConnection managedConn;

  public BasicEofSensorWatcher(ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean eofDetected(InputStream paramInputStream)
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
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.BasicEofSensorWatcher
 * JD-Core Version:    0.6.1
 */