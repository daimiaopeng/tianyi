package org.apache.http;

@Deprecated
public abstract interface HeaderElement
{
  public abstract String getName();

  public abstract NameValuePair getParameter(int paramInt);

  public abstract NameValuePair getParameterByName(String paramString);

  public abstract int getParameterCount();

  public abstract NameValuePair[] getParameters();

  public abstract String getValue();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HeaderElement
 * JD-Core Version:    0.6.1
 */