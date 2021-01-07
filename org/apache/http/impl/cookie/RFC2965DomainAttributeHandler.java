package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.SetCookie;

@Deprecated
public class RFC2965DomainAttributeHandler
  implements CookieAttributeHandler
{
  public RFC2965DomainAttributeHandler()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean domainMatch(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  public void parse(SetCookie paramSetCookie, String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.RFC2965DomainAttributeHandler
 * JD-Core Version:    0.6.1
 */