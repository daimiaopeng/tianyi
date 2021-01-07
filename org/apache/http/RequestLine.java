package org.apache.http;

@Deprecated
public abstract interface RequestLine
{
  public abstract String getMethod();

  public abstract ProtocolVersion getProtocolVersion();

  public abstract String getUri();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.RequestLine
 * JD-Core Version:    0.6.1
 */