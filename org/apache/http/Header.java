package org.apache.http;

@Deprecated
public abstract interface Header
{
  public abstract HeaderElement[] getElements();

  public abstract String getName();

  public abstract String getValue();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.Header
 * JD-Core Version:    0.6.1
 */