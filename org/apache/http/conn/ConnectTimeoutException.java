package org.apache.http.conn;

import java.io.InterruptedIOException;

@Deprecated
public class ConnectTimeoutException extends InterruptedIOException
{
  public ConnectTimeoutException()
  {
    throw new RuntimeException("Stub!");
  }

  public ConnectTimeoutException(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ConnectTimeoutException
 * JD-Core Version:    0.6.1
 */