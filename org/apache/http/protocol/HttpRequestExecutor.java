package org.apache.http.protocol;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

@Deprecated
public class HttpRequestExecutor
{
  public HttpRequestExecutor()
  {
    throw new RuntimeException("Stub!");
  }

  protected boolean canResponseHaveBody(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    throw new RuntimeException("Stub!");
  }

  protected HttpResponse doReceiveResponse(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  protected HttpResponse doSendRequest(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  public HttpResponse execute(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  public void postProcess(HttpResponse paramHttpResponse, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  public void preProcess(HttpRequest paramHttpRequest, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpRequestExecutor
 * JD-Core Version:    0.6.1
 */