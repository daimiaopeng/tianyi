package org.apache.http.impl.cookie;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;

@Deprecated
public class BrowserCompatSpec extends CookieSpecBase
{
  protected static final String[] DATE_PATTERNS;

  public BrowserCompatSpec()
  {
    throw new RuntimeException("Stub!");
  }

  public BrowserCompatSpec(String[] paramArrayOfString)
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

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.BrowserCompatSpec
 * JD-Core Version:    0.6.1
 */