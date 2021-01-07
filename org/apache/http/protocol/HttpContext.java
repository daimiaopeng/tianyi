package org.apache.http.protocol;

@Deprecated
public abstract interface HttpContext
{
  public static final String RESERVED_PREFIX = "http.";

  public abstract Object getAttribute(String paramString);

  public abstract Object removeAttribute(String paramString);

  public abstract void setAttribute(String paramString, Object paramObject);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpContext
 * JD-Core Version:    0.6.1
 */