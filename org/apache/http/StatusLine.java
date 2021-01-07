package org.apache.http;

@Deprecated
public abstract interface StatusLine
{
  public abstract ProtocolVersion getProtocolVersion();

  public abstract String getReasonPhrase();

  public abstract int getStatusCode();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.StatusLine
 * JD-Core Version:    0.6.1
 */