package org.apache.http.cookie;

import org.apache.http.params.HttpParams;

@Deprecated
public abstract interface CookieSpecFactory
{
  public abstract CookieSpec newInstance(HttpParams paramHttpParams);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.cookie.CookieSpecFactory
 * JD-Core Version:    0.6.1
 */