package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public abstract interface CookieSpec
{
  public static final String PATH_DELIM = "/";
  public static final char PATH_DELIM_CHAR = "/".charAt(0);

  public abstract boolean domainMatch(String paramString1, String paramString2);

  public abstract String formatCookie(Cookie paramCookie);

  public abstract Header formatCookieHeader(Cookie paramCookie);

  public abstract Header formatCookieHeader(Cookie[] paramArrayOfCookie);

  public abstract String formatCookies(Cookie[] paramArrayOfCookie);

  public abstract Collection getValidDateFormats();

  public abstract boolean match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie);

  public abstract Cookie[] match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie[] paramArrayOfCookie);

  public abstract Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3);

  public abstract Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Header paramHeader);

  public abstract void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie);

  public abstract boolean pathMatch(String paramString1, String paramString2);

  public abstract void setValidDateFormats(Collection paramCollection);

  public abstract void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookieSpec
 * JD-Core Version:    0.6.1
 */