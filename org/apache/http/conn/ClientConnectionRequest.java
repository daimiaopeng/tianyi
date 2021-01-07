package org.apache.http.conn;

import java.util.concurrent.TimeUnit;

@Deprecated
public abstract interface ClientConnectionRequest
{
  public abstract void abortRequest();

  public abstract ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ClientConnectionRequest
 * JD-Core Version:    0.6.1
 */