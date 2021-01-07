package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.TokenIterator;
import org.apache.http.protocol.HttpContext;

@Deprecated
public class DefaultConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  public DefaultConnectionReuseStrategy()
  {
    throw new RuntimeException("Stub!");
  }

  protected TokenIterator createTokenIterator(HeaderIterator paramHeaderIterator)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.DefaultConnectionReuseStrategy
 * JD-Core Version:    0.6.1
 */