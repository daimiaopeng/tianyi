package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.util.LangUtils;

public class AuthScope
{
  public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
  public static final String ANY_HOST;
  public static final int ANY_PORT = -1;
  public static final String ANY_REALM;
  public static final String ANY_SCHEME;
  private String host = null;
  private int port = -1;
  private String realm = null;
  private String scheme = null;

  public AuthScope(String paramString, int paramInt)
  {
    this(paramString, paramInt, ANY_REALM, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2)
  {
    this(paramString1, paramInt, paramString2, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    String str1;
    if (paramString1 == null)
      str1 = ANY_HOST;
    else
      str1 = paramString1.toLowerCase();
    this.host = str1;
    if (paramInt < 0)
      paramInt = -1;
    this.port = paramInt;
    if (paramString2 == null)
      paramString2 = ANY_REALM;
    this.realm = paramString2;
    String str2;
    if (paramString3 == null)
      str2 = ANY_SCHEME;
    else
      str2 = paramString3.toUpperCase();
    this.scheme = str2;
  }

  public AuthScope(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Scope may not be null");
    this.host = paramAuthScope.getHost();
    this.port = paramAuthScope.getPort();
    this.realm = paramAuthScope.getRealm();
    this.scheme = paramAuthScope.getScheme();
  }

  private static boolean paramsEqual(int paramInt1, int paramInt2)
  {
    boolean bool;
    if (paramInt1 == paramInt2)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static boolean paramsEqual(String paramString1, String paramString2)
  {
    if (paramString1 == null)
    {
      boolean bool;
      if (paramString1 == paramString2)
        bool = true;
      else
        bool = false;
      return bool;
    }
    return paramString1.equals(paramString2);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof AuthScope))
      return super.equals(paramObject);
    AuthScope localAuthScope = (AuthScope)paramObject;
    boolean bool1 = paramsEqual(this.host, localAuthScope.host);
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = paramsEqual(this.port, localAuthScope.port);
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = paramsEqual(this.realm, localAuthScope.realm);
        bool2 = false;
        if (bool4)
        {
          boolean bool5 = paramsEqual(this.scheme, localAuthScope.scheme);
          bool2 = false;
          if (bool5)
            bool2 = true;
        }
      }
    }
    return bool2;
  }

  public String getHost()
  {
    return this.host;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getRealm()
  {
    return this.realm;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.port), this.realm), this.scheme);
  }

  public int match(AuthScope paramAuthScope)
  {
    int i;
    if (paramsEqual(this.scheme, paramAuthScope.scheme))
    {
      i = 1;
    }
    else
    {
      if ((this.scheme != ANY_SCHEME) && (paramAuthScope.scheme != ANY_SCHEME))
        return -1;
      i = 0;
    }
    if (paramsEqual(this.realm, paramAuthScope.realm))
      i += 2;
    else if ((this.realm != ANY_REALM) && (paramAuthScope.realm != ANY_REALM))
      return -1;
    if (paramsEqual(this.port, paramAuthScope.port))
      i += 4;
    else if ((this.port != -1) && (paramAuthScope.port != -1))
      return -1;
    if (paramsEqual(this.host, paramAuthScope.host))
      i += 8;
    else if ((this.host != ANY_HOST) && (paramAuthScope.host != ANY_HOST))
      return -1;
    return i;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.scheme != null)
    {
      localStringBuffer.append(this.scheme.toUpperCase());
      localStringBuffer.append(' ');
    }
    if (this.realm != null)
    {
      localStringBuffer.append('\'');
      localStringBuffer.append(this.realm);
      localStringBuffer.append('\'');
    }
    else
    {
      localStringBuffer.append("<any realm>");
    }
    if (this.host != null)
    {
      localStringBuffer.append('@');
      localStringBuffer.append(this.host);
      if (this.port >= 0)
      {
        localStringBuffer.append(':');
        localStringBuffer.append(this.port);
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthScope
 * JD-Core Version:    0.6.1
 */