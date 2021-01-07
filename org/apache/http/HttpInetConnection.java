package org.apache.http;

import java.net.InetAddress;

@Deprecated
public abstract interface HttpInetConnection extends HttpConnection
{
  public abstract InetAddress getLocalAddress();

  public abstract int getLocalPort();

  public abstract InetAddress getRemoteAddress();

  public abstract int getRemotePort();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpInetConnection
 * JD-Core Version:    0.6.1
 */