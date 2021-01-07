package org.apache.http.impl.cookie;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;

@Deprecated
public class NetscapeDraftSpec extends CookieSpecBase
{
  protected static final String EXPIRES_PATTERN = "EEE, dd-MMM-yyyy HH:mm:ss z";

  public NetscapeDraftSpec()
  {
    throw new RuntimeException("Stub!");
  }

  public NetscapeDraftSpec(String[] paramArrayOfString)
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
 * Qualified Name:     org.apache.http.impl.cookie.NetscapeDraftSpec
 * JD-Core Version:    0.6.1
 */