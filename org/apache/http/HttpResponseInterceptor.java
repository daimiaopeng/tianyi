package org.apache.http;

import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface HttpResponseInterceptor
{
  public abstract void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpResponseInterceptor
 * JD-Core Version:    0.6.1
 */