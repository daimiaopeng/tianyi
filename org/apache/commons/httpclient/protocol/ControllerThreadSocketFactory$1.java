package org.apache.commons.httpclient.protocol;

import java.net.InetAddress;

class ControllerThreadSocketFactory$1 extends ControllerThreadSocketFactory.SocketTask
{
  private final String val$host;
  private final InetAddress val$localAddress;
  private final int val$localPort;
  private final int val$port;
  private final ProtocolSocketFactory val$socketfactory;

  ControllerThreadSocketFactory$1(ProtocolSocketFactory paramProtocolSocketFactory, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
  {
    this.val$socketfactory = paramProtocolSocketFactory;
    this.val$host = paramString;
    this.val$port = paramInt1;
    this.val$localAddress = paramInetAddress;
    this.val$localPort = paramInt2;
  }

  public void doit()
  {
    setSocket(this.val$socketfactory.createSocket(this.val$host, this.val$port, this.val$localAddress, this.val$localPort));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory.1
 * JD-Core Version:    0.6.1
 */