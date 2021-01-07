package org.apache.commons.httpclient.protocol;

import java.net.Socket;

public abstract interface SecureProtocolSocketFactory extends ProtocolSocketFactory
{
  public abstract Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory
 * JD-Core Version:    0.6.1
 */