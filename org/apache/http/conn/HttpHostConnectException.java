package org.apache.http.conn;

import java.net.ConnectException;
import org.apache.http.HttpHost;

@Deprecated
public class HttpHostConnectException extends ConnectException
{
  public HttpHostConnectException(HttpHost paramHttpHost, ConnectException paramConnectException)
  {
    throw new RuntimeException("Stub!");
  }

  public HttpHost getHost()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.HttpHostConnectException
 * JD-Core Version:    0.6.1
 */