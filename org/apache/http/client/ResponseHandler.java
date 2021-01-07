package org.apache.http.client;

import org.apache.http.HttpResponse;

@Deprecated
public abstract interface ResponseHandler<T>
{
  public abstract T handleResponse(HttpResponse paramHttpResponse);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.ResponseHandler
 * JD-Core Version:    0.6.1
 */