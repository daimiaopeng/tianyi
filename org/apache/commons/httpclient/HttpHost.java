package org.apache.commons.httpclient;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

public class HttpHost
  implements Cloneable
{
  private String hostname = null;
  private int port = -1;
  private Protocol protocol = null;

  public HttpHost(String paramString)
  {
    this(paramString, -1, Protocol.getProtocol("http"));
  }

  public HttpHost(String paramString, int paramInt)
  {
    this(paramString, paramInt, Protocol.getProtocol("http"));
  }

  public HttpHost(String paramString, int paramInt, Protocol paramProtocol)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Host name may not be null");
    if (paramProtocol == null)
      throw new IllegalArgumentException("Protocol may not be null");
    this.hostname = paramString;
    this.protocol = paramProtocol;
    if (paramInt >= 0)
      this.port = paramInt;
    else
      this.port = this.protocol.getDefaultPort();
  }

  public HttpHost(HttpHost paramHttpHost)
  {
    init(paramHttpHost);
  }

  public HttpHost(URI paramURI)
  {
    this(paramURI.getHost(), paramURI.getPort(), Protocol.getProtocol(paramURI.getScheme()));
  }

  private void init(HttpHost paramHttpHost)
  {
    this.hostname = paramHttpHost.hostname;
    this.port = paramHttpHost.port;
    this.protocol = paramHttpHost.protocol;
  }

  public Object clone()
  {
    HttpHost localHttpHost = (HttpHost)super.clone();
    localHttpHost.init(this);
    return localHttpHost;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof HttpHost))
    {
      if (paramObject == this)
        return true;
      HttpHost localHttpHost = (HttpHost)paramObject;
      if (!this.hostname.equalsIgnoreCase(localHttpHost.hostname))
        return false;
      if (this.port != localHttpHost.port)
        return false;
      return this.protocol.equals(localHttpHost.protocol);
    }
    return false;
  }

  public String getHostName()
  {
    return this.hostname;
  }

  public int getPort()
  {
    return this.port;
  }

  public Protocol getProtocol()
  {
    return this.protocol;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.hostname), this.port), this.protocol);
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(50);
    localStringBuffer.append(toURI());
    return localStringBuffer.toString();
  }

  public String toURI()
  {
    StringBuffer localStringBuffer = new StringBuffer(50);
    localStringBuffer.append(this.protocol.getScheme());
    localStringBuffer.append("://");
    localStringBuffer.append(this.hostname);
    if (this.port != this.protocol.getDefaultPort())
    {
      localStringBuffer.append(':');
      localStringBuffer.append(this.port);
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpHost
 * JD-Core Version:    0.6.1
 */