package org.apache.http.conn.scheme;

import java.net.Socket;

@Deprecated
public abstract interface LayeredSocketFactory extends SocketFactory
{
  public abstract Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.scheme.LayeredSocketFactory
 * JD-Core Version:    0.6.1
 */