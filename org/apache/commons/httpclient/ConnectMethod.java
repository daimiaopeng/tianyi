package org.apache.commons.httpclient;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConnectMethod extends HttpMethodBase
{
  private static final Log LOG = LogFactory.getLog(localClass);
  public static final String NAME = "CONNECT";
  static Class class$org$apache$commons$httpclient$ConnectMethod;
  private final HostConfiguration targethost;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$ConnectMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.ConnectMethod");
      class$org$apache$commons$httpclient$ConnectMethod = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$ConnectMethod;
    }
  }

  public ConnectMethod()
  {
    this.targethost = null;
  }

  public ConnectMethod(HostConfiguration paramHostConfiguration)
  {
    if (paramHostConfiguration == null)
      throw new IllegalArgumentException("Target host may not be null");
    this.targethost = paramHostConfiguration;
  }

  public ConnectMethod(HttpMethod paramHttpMethod)
  {
    this.targethost = null;
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  protected void addCookieRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
  }

  protected void addRequestHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter ConnectMethod.addRequestHeaders(HttpState, HttpConnection)");
    addUserAgentRequestHeader(paramHttpState, paramHttpConnection);
    addHostRequestHeader(paramHttpState, paramHttpConnection);
    addProxyConnectionHeader(paramHttpState, paramHttpConnection);
  }

  public int execute(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter ConnectMethod.execute(HttpState, HttpConnection)");
    int i = super.execute(paramHttpState, paramHttpConnection);
    if (LOG.isDebugEnabled())
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("CONNECT status code ");
      localStringBuffer.append(i);
      localLog.debug(localStringBuffer.toString());
    }
    return i;
  }

  public String getName()
  {
    return "CONNECT";
  }

  public String getPath()
  {
    if (this.targethost != null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(this.targethost.getHost());
      int i = this.targethost.getPort();
      if (i == -1)
        i = this.targethost.getProtocol().getDefaultPort();
      localStringBuffer.append(':');
      localStringBuffer.append(i);
      return localStringBuffer.toString();
    }
    return "/";
  }

  public URI getURI()
  {
    String str = getParams().getUriCharset();
    return new URI(getPath(), true, str);
  }

  protected boolean shouldCloseConnection(HttpConnection paramHttpConnection)
  {
    if (getStatusCode() == 200)
    {
      boolean bool = paramHttpConnection.isTransparent();
      Header localHeader = null;
      if (!bool)
        localHeader = getResponseHeader("proxy-connection");
      if (localHeader == null)
        localHeader = getResponseHeader("connection");
      if ((localHeader != null) && (localHeader.getValue().equalsIgnoreCase("close")) && (LOG.isWarnEnabled()))
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Invalid header encountered '");
        localStringBuffer.append(localHeader.toExternalForm());
        localStringBuffer.append("' in response ");
        localStringBuffer.append(getStatusLine().toString());
        localLog.warn(localStringBuffer.toString());
      }
      return false;
    }
    return super.shouldCloseConnection(paramHttpConnection);
  }

  protected void writeRequestLine(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(getName());
    localStringBuffer.append(' ');
    if (this.targethost != null)
    {
      localStringBuffer.append(getPath());
    }
    else
    {
      int i = paramHttpConnection.getPort();
      if (i == -1)
        i = paramHttpConnection.getProtocol().getDefaultPort();
      localStringBuffer.append(paramHttpConnection.getHost());
      localStringBuffer.append(':');
      localStringBuffer.append(i);
    }
    localStringBuffer.append(" ");
    localStringBuffer.append(getEffectiveVersion());
    String str = localStringBuffer.toString();
    paramHttpConnection.printLine(str, getParams().getHttpElementCharset());
    if (Wire.HEADER_WIRE.enabled())
      Wire.HEADER_WIRE.output(str);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ConnectMethod
 * JD-Core Version:    0.6.1
 */