package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public class IgnoreCookiesSpec
  implements CookieSpec
{
  public boolean domainMatch(String paramString1, String paramString2)
  {
    return false;
  }

  public String formatCookie(Cookie paramCookie)
  {
    return null;
  }

  public Header formatCookieHeader(Cookie paramCookie)
  {
    return null;
  }

  public Header formatCookieHeader(Cookie[] paramArrayOfCookie)
  {
    return null;
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
  {
    return null;
  }

  public Collection getValidDateFormats()
  {
    return null;
  }

  public boolean match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    return false;
  }

  public Cookie[] match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie[] paramArrayOfCookie)
  {
    return new Cookie[0];
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
  {
    return new Cookie[0];
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Header paramHeader)
  {
    return new Cookie[0];
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
  {
  }

  public boolean pathMatch(String paramString1, String paramString2)
  {
    return false;
  }

  public void setValidDateFormats(Collection paramCollection)
  {
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.IgnoreCookiesSpec
 * JD-Core Version:    0.6.1
 */