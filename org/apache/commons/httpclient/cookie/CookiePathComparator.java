package org.apache.commons.httpclient.cookie;

import java.util.Comparator;
import org.apache.commons.httpclient.Cookie;

public class CookiePathComparator
  implements Comparator
{
  private String normalizePath(Cookie paramCookie)
  {
    String str = paramCookie.getPath();
    if (str == null)
      str = "/";
    if (!str.endsWith("/"))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(str);
      localStringBuffer.append("/");
      str = localStringBuffer.toString();
    }
    return str;
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    Cookie localCookie1 = (Cookie)paramObject1;
    Cookie localCookie2 = (Cookie)paramObject2;
    String str1 = normalizePath(localCookie1);
    String str2 = normalizePath(localCookie2);
    if (str1.equals(str2))
      return 0;
    if (str1.startsWith(str2))
      return -1;
    if (str2.startsWith(str1))
      return 1;
    return 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookiePathComparator
 * JD-Core Version:    0.6.1
 */