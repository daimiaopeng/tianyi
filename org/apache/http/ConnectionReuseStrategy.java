package org.apache.http;

import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface ConnectionReuseStrategy
{
  public abstract boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.ConnectionReuseStrategy
 * JD-Core Version:    0.6.1
 */