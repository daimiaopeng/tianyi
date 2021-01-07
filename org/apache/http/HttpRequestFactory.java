package org.apache.http;

@Deprecated
public abstract interface HttpRequestFactory
{
  public abstract HttpRequest newHttpRequest(String paramString1, String paramString2);

  public abstract HttpRequest newHttpRequest(RequestLine paramRequestLine);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpRequestFactory
 * JD-Core Version:    0.6.1
 */