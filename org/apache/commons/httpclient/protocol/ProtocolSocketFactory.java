package org.apache.commons.httpclient.protocol;

import java.net.InetAddress;
import java.net.Socket;
import org.apache.commons.httpclient.params.HttpConnectionParams;

public abstract interface ProtocolSocketFactory
{
  public abstract Socket createSocket(String paramString, int paramInt);

  public abstract Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2);

  public abstract Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpConnectionParams paramHttpConnectionParams);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.ProtocolSocketFactory
 * JD-Core Version:    0.6.1
 */