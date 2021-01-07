package org.apache.commons.httpclient;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.util.LangUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cookie extends NameValuePair
  implements Serializable, Comparator
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$Cookie;
  private String cookieComment;
  private String cookieDomain;
  private Date cookieExpiryDate;
  private String cookiePath;
  private int cookieVersion = 0;
  private boolean hasDomainAttribute = false;
  private boolean hasPathAttribute = false;
  private boolean isSecure;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$Cookie == null)
    {
      localClass = class$("org.apache.commons.httpclient.Cookie");
      class$org$apache$commons$httpclient$Cookie = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$Cookie;
    }
  }

  public Cookie()
  {
    this(null, "noname", null, null, null, false);
  }

  public Cookie(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, null, null, false);
  }

  public Cookie(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
  {
    this(paramString1, paramString2, paramString3, paramString4, null, paramBoolean);
    if (paramInt < -1)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid max age:  ");
      localStringBuffer.append(Integer.toString(paramInt));
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    if (paramInt >= 0)
      setExpiryDate(new Date(System.currentTimeMillis() + 1000L * paramInt));
  }

  public Cookie(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate, boolean paramBoolean)
  {
    super(paramString2, paramString3);
    LOG.trace("enter Cookie(String, String, String, String, Date, boolean)");
    if (paramString2 == null)
      throw new IllegalArgumentException("Cookie name may not be null");
    if (paramString2.trim().equals(""))
      throw new IllegalArgumentException("Cookie name may not be blank");
    setPath(paramString4);
    setDomain(paramString1);
    setExpiryDate(paramDate);
    setSecure(paramBoolean);
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    LOG.trace("enter Cookie.compare(Object, Object)");
    if (!(paramObject1 instanceof Cookie))
      throw new ClassCastException(paramObject1.getClass().getName());
    if (!(paramObject2 instanceof Cookie))
      throw new ClassCastException(paramObject2.getClass().getName());
    Cookie localCookie1 = (Cookie)paramObject1;
    Cookie localCookie2 = (Cookie)paramObject2;
    if ((localCookie1.getPath() == null) && (localCookie2.getPath() == null))
      return 0;
    if (localCookie1.getPath() == null)
    {
      if (localCookie2.getPath().equals("/"))
        return 0;
      return -1;
    }
    if (localCookie2.getPath() == null)
    {
      if (localCookie1.getPath().equals("/"))
        return 0;
      return 1;
    }
    return localCookie1.getPath().compareTo(localCookie2.getPath());
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this == paramObject)
      return true;
    if ((paramObject instanceof Cookie))
    {
      Cookie localCookie = (Cookie)paramObject;
      boolean bool1 = LangUtils.equals(getName(), localCookie.getName());
      boolean bool2 = false;
      if (bool1)
      {
        boolean bool3 = LangUtils.equals(this.cookieDomain, localCookie.cookieDomain);
        bool2 = false;
        if (bool3)
        {
          boolean bool4 = LangUtils.equals(this.cookiePath, localCookie.cookiePath);
          bool2 = false;
          if (bool4)
            bool2 = true;
        }
      }
      return bool2;
    }
    return false;
  }

  public String getComment()
  {
    return this.cookieComment;
  }

  public String getDomain()
  {
    return this.cookieDomain;
  }

  public Date getExpiryDate()
  {
    return this.cookieExpiryDate;
  }

  public String getPath()
  {
    return this.cookiePath;
  }

  public boolean getSecure()
  {
    return this.isSecure;
  }

  public int getVersion()
  {
    return this.cookieVersion;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, getName()), this.cookieDomain), this.cookiePath);
  }

  public boolean isDomainAttributeSpecified()
  {
    return this.hasDomainAttribute;
  }

  public boolean isExpired()
  {
    boolean bool;
    if ((this.cookieExpiryDate != null) && (this.cookieExpiryDate.getTime() <= System.currentTimeMillis()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isExpired(Date paramDate)
  {
    boolean bool;
    if ((this.cookieExpiryDate != null) && (this.cookieExpiryDate.getTime() <= paramDate.getTime()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isPathAttributeSpecified()
  {
    return this.hasPathAttribute;
  }

  public boolean isPersistent()
  {
    boolean bool;
    if (this.cookieExpiryDate != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void setComment(String paramString)
  {
    this.cookieComment = paramString;
  }

  public void setDomain(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.indexOf(":");
      if (i != -1)
        paramString = paramString.substring(0, i);
      this.cookieDomain = paramString.toLowerCase();
    }
  }

  public void setDomainAttributeSpecified(boolean paramBoolean)
  {
    this.hasDomainAttribute = paramBoolean;
  }

  public void setExpiryDate(Date paramDate)
  {
    this.cookieExpiryDate = paramDate;
  }

  public void setPath(String paramString)
  {
    this.cookiePath = paramString;
  }

  public void setPathAttributeSpecified(boolean paramBoolean)
  {
    this.hasPathAttribute = paramBoolean;
  }

  public void setSecure(boolean paramBoolean)
  {
    this.isSecure = paramBoolean;
  }

  public void setVersion(int paramInt)
  {
    this.cookieVersion = paramInt;
  }

  public String toExternalForm()
  {
    CookieSpec localCookieSpec;
    if (getVersion() > 0)
      localCookieSpec = CookiePolicy.getDefaultSpec();
    else
      localCookieSpec = CookiePolicy.getCookieSpec("netscape");
    return localCookieSpec.formatCookie(this);
  }

  public String toString()
  {
    return toExternalForm();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.Cookie
 * JD-Core Version:    0.6.1
 */