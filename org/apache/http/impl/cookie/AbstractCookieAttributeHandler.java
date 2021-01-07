package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;

@Deprecated
public abstract class AbstractCookieAttributeHandler
  implements CookieAttributeHandler
{
  public AbstractCookieAttributeHandler()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.AbstractCookieAttributeHandler
 * JD-Core Version:    0.6.1
 */