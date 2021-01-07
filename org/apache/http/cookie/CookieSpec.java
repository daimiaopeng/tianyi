package org.apache.http.cookie;

import java.util.List;
import org.apache.http.Header;

@Deprecated
public abstract interface CookieSpec
{
  public abstract List<Header> formatCookies(List<Cookie> paramList);

  public abstract int getVersion();

  public abstract Header getVersionHeader();

  public abstract boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin);

  public abstract List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin);

  public abstract void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.cookie.CookieSpec
 * JD-Core Version:    0.6.1
 */