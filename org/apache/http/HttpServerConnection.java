package org.apache.http;

@Deprecated
public abstract interface HttpServerConnection extends HttpConnection
{
  public abstract void flush();

  public abstract void receiveRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest);

  public abstract HttpRequest receiveRequestHeader();

  public abstract void sendResponseEntity(HttpResponse paramHttpResponse);

  public abstract void sendResponseHeader(HttpResponse paramHttpResponse);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpServerConnection
 * JD-Core Version:    0.6.1
 */