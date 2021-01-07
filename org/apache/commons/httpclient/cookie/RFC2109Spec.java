package org.apache.commons.httpclient.cookie;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;

public class RFC2109Spec extends CookieSpecBase
{
  public static final String SET_COOKIE_KEY = "set-cookie";
  private final ParameterFormatter formatter = new ParameterFormatter();

  public RFC2109Spec()
  {
    this.formatter.setAlwaysUseQuotes(true);
  }

  private void formatCookieAsVer(StringBuffer paramStringBuffer, Cookie paramCookie, int paramInt)
  {
    String str = paramCookie.getValue();
    if (str == null)
      str = "";
    formatParam(paramStringBuffer, new NameValuePair(paramCookie.getName(), str), paramInt);
    if ((paramCookie.getPath() != null) && (paramCookie.isPathAttributeSpecified()))
    {
      paramStringBuffer.append("; ");
      formatParam(paramStringBuffer, new NameValuePair("$Path", paramCookie.getPath()), paramInt);
    }
    if ((paramCookie.getDomain() != null) && (paramCookie.isDomainAttributeSpecified()))
    {
      paramStringBuffer.append("; ");
      formatParam(paramStringBuffer, new NameValuePair("$Domain", paramCookie.getDomain()), paramInt);
    }
  }

  private void formatParam(StringBuffer paramStringBuffer, NameValuePair paramNameValuePair, int paramInt)
  {
    if (paramInt < 1)
    {
      paramStringBuffer.append(paramNameValuePair.getName());
      paramStringBuffer.append("=");
      if (paramNameValuePair.getValue() != null)
        paramStringBuffer.append(paramNameValuePair.getValue());
    }
    else
    {
      this.formatter.format(paramStringBuffer, paramNameValuePair);
    }
  }

  public boolean domainMatch(String paramString1, String paramString2)
  {
    boolean bool;
    if ((!paramString1.equals(paramString2)) && ((!paramString2.startsWith(".")) || (!paramString1.endsWith(paramString2))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public String formatCookie(Cookie paramCookie)
  {
    LOG.trace("enter RFC2109Spec.formatCookie(Cookie)");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    int i = paramCookie.getVersion();
    StringBuffer localStringBuffer = new StringBuffer();
    formatParam(localStringBuffer, new NameValuePair("$Version", Integer.toString(i)), i);
    localStringBuffer.append("; ");
    formatCookieAsVer(localStringBuffer, paramCookie, i);
    return localStringBuffer.toString();
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter RFC2109Spec.formatCookieHeader(Cookie[])");
    int i = 0;
    int j = 0;
    int k = 2147483647;
    while (j < paramArrayOfCookie.length)
    {
      Cookie localCookie = paramArrayOfCookie[j];
      if (localCookie.getVersion() < k)
        k = localCookie.getVersion();
      j++;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    formatParam(localStringBuffer, new NameValuePair("$Version", Integer.toString(k)), k);
    while (i < paramArrayOfCookie.length)
    {
      localStringBuffer.append("; ");
      formatCookieAsVer(localStringBuffer, paramArrayOfCookie[i], k);
      i++;
    }
    return localStringBuffer.toString();
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("Attribute may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null.");
    String str1 = paramNameValuePair.getName().toLowerCase();
    String str2 = paramNameValuePair.getValue();
    if (str1.equals("path"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for path attribute");
      if (str2.trim().equals(""))
        throw new MalformedCookieException("Blank value for path attribute");
      paramCookie.setPath(str2);
      paramCookie.setPathAttributeSpecified(true);
    }
    else if (str1.equals("version"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for version attribute");
      try
      {
        paramCookie.setVersion(Integer.parseInt(str2));
      }
      catch (NumberFormatException localNumberFormatException)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Invalid version: ");
        localStringBuffer.append(localNumberFormatException.getMessage());
        throw new MalformedCookieException(localStringBuffer.toString());
      }
    }
    else
    {
      super.parseAttribute(paramNameValuePair, paramCookie);
    }
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    LOG.trace("enter RFC2109Spec.validate(String, int, String, boolean, Cookie)");
    super.validate(paramString1, paramInt, paramString2, paramBoolean, paramCookie);
    if (paramCookie.getName().indexOf(' ') != -1)
      throw new MalformedCookieException("Cookie name may not contain blanks");
    if (paramCookie.getName().startsWith("$"))
      throw new MalformedCookieException("Cookie name may not start with $");
    if ((paramCookie.isDomainAttributeSpecified()) && (!paramCookie.getDomain().equals(paramString1)))
    {
      if (!paramCookie.getDomain().startsWith("."))
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("Domain attribute \"");
        localStringBuffer1.append(paramCookie.getDomain());
        localStringBuffer1.append("\" violates RFC 2109: domain must start with a dot");
        throw new MalformedCookieException(localStringBuffer1.toString());
      }
      int i = paramCookie.getDomain().indexOf('.', 1);
      if ((i >= 0) && (i != paramCookie.getDomain().length() - 1))
      {
        String str = paramString1.toLowerCase();
        if (!str.endsWith(paramCookie.getDomain()))
        {
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append("Illegal domain attribute \"");
          localStringBuffer3.append(paramCookie.getDomain());
          localStringBuffer3.append("\". Domain of origin: \"");
          localStringBuffer3.append(str);
          localStringBuffer3.append("\"");
          throw new MalformedCookieException(localStringBuffer3.toString());
        }
        if (str.substring(0, str.length() - paramCookie.getDomain().length()).indexOf('.') != -1)
        {
          StringBuffer localStringBuffer4 = new StringBuffer();
          localStringBuffer4.append("Domain attribute \"");
          localStringBuffer4.append(paramCookie.getDomain());
          localStringBuffer4.append("\" violates RFC 2109: host minus domain may not contain any dots");
          throw new MalformedCookieException(localStringBuffer4.toString());
        }
      }
      else
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Domain attribute \"");
        localStringBuffer2.append(paramCookie.getDomain());
        localStringBuffer2.append("\" violates RFC 2109: domain must contain an embedded dot");
        throw new MalformedCookieException(localStringBuffer2.toString());
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.RFC2109Spec
 * JD-Core Version:    0.6.1
 */