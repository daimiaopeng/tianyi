package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

@Deprecated
public class NoConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  public NoConnectionReuseStrategy()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.NoConnectionReuseStrategy
 * JD-Core Version:    0.6.1
 */