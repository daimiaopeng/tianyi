package org.apache.http.impl.cookie;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;

@Deprecated
public class BestMatchSpec
  implements CookieSpec
{
  public BestMatchSpec()
  {
    throw new RuntimeException("Stub!");
  }

  public BestMatchSpec(String[] paramArrayOfString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    throw new RuntimeException("Stub!");
  }

  public int getVersion()
  {
    throw new RuntimeException("Stub!");
  }

  public Header getVersionHeader()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.BestMatchSpec
 * JD-Core Version:    0.6.1
 */