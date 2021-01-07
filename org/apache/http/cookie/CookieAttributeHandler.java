package org.apache.http.cookie;

@Deprecated
public abstract interface CookieAttributeHandler
{
  public abstract boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin);

  public abstract void parse(SetCookie paramSetCookie, String paramString);

  public abstract void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.cookie.CookieAttributeHandler
 * JD-Core Version:    0.6.1
 */