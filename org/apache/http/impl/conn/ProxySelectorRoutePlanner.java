package org.apache.http.impl.conn;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.protocol.HttpContext;

@Deprecated
public class ProxySelectorRoutePlanner
  implements HttpRoutePlanner
{
  protected ProxySelector proxySelector;
  protected SchemeRegistry schemeRegistry;

  public ProxySelectorRoutePlanner(SchemeRegistry paramSchemeRegistry, ProxySelector paramProxySelector)
  {
    throw new RuntimeException("Stub!");
  }

  protected Proxy chooseProxy(List<Proxy> paramList, HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  protected HttpHost determineProxy(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  protected String getHost(InetSocketAddress paramInetSocketAddress)
  {
    throw new RuntimeException("Stub!");
  }

  public ProxySelector getProxySelector()
  {
    throw new RuntimeException("Stub!");
  }

  public void setProxySelector(ProxySelector paramProxySelector)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.ProxySelectorRoutePlanner
 * JD-Core Version:    0.6.1
 */