package org.apache.http;

@Deprecated
public abstract interface HttpClientConnection extends HttpConnection
{
  public abstract void flush();

  public abstract boolean isResponseAvailable(int paramInt);

  public abstract void receiveResponseEntity(HttpResponse paramHttpResponse);

  public abstract HttpResponse receiveResponseHeader();

  public abstract void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest);

  public abstract void sendRequestHeader(HttpRequest paramHttpRequest);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpClientConnection
 * JD-Core Version:    0.6.1
 */