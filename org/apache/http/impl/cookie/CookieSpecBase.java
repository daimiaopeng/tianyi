package org.apache.http.impl.cookie;

import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;

@Deprecated
public abstract class CookieSpecBase extends AbstractCookieSpec
{
  public CookieSpecBase()
  {
    throw new RuntimeException("Stub!");
  }

  protected static String getDefaultDomain(CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  protected static String getDefaultPath(CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  protected List<Cookie> parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.CookieSpecBase
 * JD-Core Version:    0.6.1
 */