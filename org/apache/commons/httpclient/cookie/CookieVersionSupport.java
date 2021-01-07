package org.apache.commons.httpclient.cookie;

import org.apache.commons.httpclient.Header;

public abstract interface CookieVersionSupport
{
  public abstract int getVersion();

  public abstract Header getVersionHeader();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookieVersionSupport
 * JD-Core Version:    0.6.1
 */