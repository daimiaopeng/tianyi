package org.apache.commons.httpclient.protocol;

import java.net.InetAddress;
import java.net.Socket;
import org.apache.commons.httpclient.params.HttpConnectionParams;

public class DefaultProtocolSocketFactory
  implements ProtocolSocketFactory
{
  private static final DefaultProtocolSocketFactory factory = new DefaultProtocolSocketFactory();

  static DefaultProtocolSocketFactory getSocketFactory()
  {
    return factory;
  }

  public Socket createSocket(String paramString, int paramInt)
  {
    return new Socket(paramString, paramInt);
  }

  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
  {
    return new Socket(paramString, paramInt1, paramInetAddress, paramInt2);
  }

  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpConnectionParams paramHttpConnectionParams)
  {
    if (paramHttpConnectionParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    int i = paramHttpConnectionParams.getConnectionTimeout();
    if (i == 0)
      return createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
    Socket localSocket = ReflectionSocketFactory.createSocket("javax.net.SocketFactory", paramString, paramInt1, paramInetAddress, paramInt2, i);
    if (localSocket == null)
      localSocket = ControllerThreadSocketFactory.createSocket(this, paramString, paramInt1, paramInetAddress, paramInt2, i);
    return localSocket;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if ((paramObject != null) && (paramObject.getClass().equals(getClass())))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public int hashCode()
  {
    return getClass().hashCode();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory
 * JD-Core Version:    0.6.1
 */