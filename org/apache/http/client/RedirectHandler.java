package org.apache.http.client;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface RedirectHandler
{
  public abstract URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext);

  public abstract boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.RedirectHandler
 * JD-Core Version:    0.6.1
 */