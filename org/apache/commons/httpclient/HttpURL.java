package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.URIUtil;

public class HttpURL extends URI
{
  public static final int DEFAULT_PORT = 80;
  public static final char[] DEFAULT_SCHEME = { 104, 116, 116, 112 };
  public static final int _default_port = 80;
  public static final char[] _default_scheme = DEFAULT_SCHEME;
  static final long serialVersionUID = -7158031098595039459L;

  protected HttpURL()
  {
  }

  public HttpURL(String paramString)
  {
    parseUriReference(paramString, false);
    checkValid();
  }

  public HttpURL(String paramString1, int paramInt, String paramString2)
  {
    this(null, null, paramString1, paramInt, paramString2, null, null);
  }

  public HttpURL(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this(null, null, paramString1, paramInt, paramString2, paramString3, null);
  }

  public HttpURL(String paramString1, String paramString2)
  {
    this.protocolCharset = paramString2;
    parseUriReference(paramString1, false);
    checkValid();
  }

  public HttpURL(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    this(paramString1, paramString2, paramInt, paramString3, null, null);
  }

  public HttpURL(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
  {
    this(paramString1, paramString2, paramInt, paramString3, paramString4, null);
  }

  public HttpURL(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramString1 != null) || (paramString2 != null) || (paramInt != -1))
    {
      this._scheme = DEFAULT_SCHEME;
      localStringBuffer.append(_default_scheme);
      localStringBuffer.append("://");
      if (paramString1 != null)
      {
        localStringBuffer.append(paramString1);
        localStringBuffer.append('@');
      }
      if (paramString2 != null)
      {
        localStringBuffer.append(URIUtil.encode(paramString2, URI.allowed_host));
        if ((paramInt != -1) || (paramInt != 80))
        {
          localStringBuffer.append(':');
          localStringBuffer.append(paramInt);
        }
      }
    }
    if (paramString3 != null)
    {
      if ((scheme != null) && (!paramString3.startsWith("/")))
        throw new URIException(1, "abs_path requested");
      localStringBuffer.append(URIUtil.encode(paramString3, URI.allowed_abs_path));
    }
    if (paramString4 != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(URIUtil.encode(paramString4, URI.allowed_query));
    }
    if (paramString5 != null)
    {
      localStringBuffer.append('#');
      localStringBuffer.append(URIUtil.encode(paramString5, URI.allowed_fragment));
    }
    parseUriReference(localStringBuffer.toString(), true);
    checkValid();
  }

  public HttpURL(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, -1, null, null, null);
  }

  public HttpURL(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this(paramString1, paramString2, paramString3, paramInt, null, null, null);
  }

  public HttpURL(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    this(paramString1, paramString2, paramString3, paramInt, paramString4, null, null);
  }

  public HttpURL(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
  {
    this(paramString1, paramString2, paramString3, paramInt, paramString4, paramString5, null);
  }

  public HttpURL(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, String paramString6)
  {
    this(toUserinfo(paramString1, paramString2), paramString3, paramInt, paramString4, paramString5, paramString6);
  }

  public HttpURL(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this(null, null, paramString1, -1, paramString2, paramString3, paramString4);
  }

  public HttpURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this(paramString1, paramString2, -1, paramString3, paramString4, paramString5);
  }

  public HttpURL(HttpURL paramHttpURL, String paramString)
  {
    this(paramHttpURL, new HttpURL(paramString));
  }

  public HttpURL(HttpURL paramHttpURL1, HttpURL paramHttpURL2)
  {
    super(paramHttpURL1, paramHttpURL2);
    checkValid();
  }

  public HttpURL(char[] paramArrayOfChar)
  {
    parseUriReference(new String(paramArrayOfChar), true);
    checkValid();
  }

  public HttpURL(char[] paramArrayOfChar, String paramString)
  {
    this.protocolCharset = paramString;
    parseUriReference(new String(paramArrayOfChar), true);
    checkValid();
  }

  protected static String toUserinfo(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    StringBuffer localStringBuffer = new StringBuffer(20);
    localStringBuffer.append(URIUtil.encode(paramString1, URI.allowed_within_userinfo));
    if (paramString2 == null)
      return localStringBuffer.toString();
    localStringBuffer.append(':');
    localStringBuffer.append(URIUtil.encode(paramString2, URI.allowed_within_userinfo));
    return localStringBuffer.toString();
  }

  protected void checkValid()
  {
    if ((!equals(this._scheme, DEFAULT_SCHEME)) && (this._scheme != null))
      throw new URIException(1, "wrong class use");
  }

  public String getEscapedPassword()
  {
    char[] arrayOfChar = getRawPassword();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedUser()
  {
    char[] arrayOfChar = getRawUser();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getPassword()
  {
    char[] arrayOfChar = getRawPassword();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public int getPort()
  {
    int i;
    if (this._port == -1)
      i = 80;
    else
      i = this._port;
    return i;
  }

  public char[] getRawAboveHierPath()
  {
    char[] arrayOfChar1 = getRawCurrentHierPath();
    char[] arrayOfChar2;
    if ((arrayOfChar1 != null) && (arrayOfChar1.length != 0))
      arrayOfChar2 = getRawCurrentHierPath(arrayOfChar1);
    else
      arrayOfChar2 = rootPath;
    return arrayOfChar2;
  }

  public char[] getRawCurrentHierPath()
  {
    char[] arrayOfChar;
    if ((this._path != null) && (this._path.length != 0))
      arrayOfChar = super.getRawCurrentHierPath(this._path);
    else
      arrayOfChar = rootPath;
    return arrayOfChar;
  }

  public char[] getRawPassword()
  {
    int i = indexFirstOf(this._userinfo, ':');
    if (i == -1)
      return null;
    int j = -1 + (this._userinfo.length - i);
    char[] arrayOfChar = new char[j];
    System.arraycopy(this._userinfo, i + 1, arrayOfChar, 0, j);
    return arrayOfChar;
  }

  public char[] getRawPath()
  {
    char[] arrayOfChar = super.getRawPath();
    if ((arrayOfChar == null) || (arrayOfChar.length == 0))
      arrayOfChar = rootPath;
    return arrayOfChar;
  }

  public char[] getRawScheme()
  {
    char[] arrayOfChar;
    if (this._scheme == null)
      arrayOfChar = null;
    else
      arrayOfChar = DEFAULT_SCHEME;
    return arrayOfChar;
  }

  public char[] getRawUser()
  {
    if ((this._userinfo != null) && (this._userinfo.length != 0))
    {
      int i = indexFirstOf(this._userinfo, ':');
      if (i == -1)
        return this._userinfo;
      char[] arrayOfChar = new char[i];
      System.arraycopy(this._userinfo, 0, arrayOfChar, 0, i);
      return arrayOfChar;
    }
    return null;
  }

  public String getScheme()
  {
    String str;
    if (this._scheme == null)
      str = null;
    else
      str = new String(DEFAULT_SCHEME);
    return str;
  }

  public String getUser()
  {
    char[] arrayOfChar = getRawUser();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public void setEscapedPassword(String paramString)
  {
    char[] arrayOfChar;
    if (paramString == null)
      arrayOfChar = null;
    else
      arrayOfChar = paramString.toCharArray();
    setRawPassword(arrayOfChar);
  }

  public void setEscapedUser(String paramString)
  {
    setRawUser(paramString.toCharArray());
  }

  public void setEscapedUserinfo(String paramString1, String paramString2)
  {
    char[] arrayOfChar1 = paramString1.toCharArray();
    char[] arrayOfChar2;
    if (paramString2 == null)
      arrayOfChar2 = null;
    else
      arrayOfChar2 = paramString2.toCharArray();
    setRawUserinfo(arrayOfChar1, arrayOfChar2);
  }

  public void setPassword(String paramString)
  {
    char[] arrayOfChar;
    if (paramString == null)
      arrayOfChar = null;
    else
      arrayOfChar = encode(paramString, allowed_within_userinfo, getProtocolCharset());
    setRawPassword(arrayOfChar);
  }

  public void setQuery(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str = getProtocolCharset();
    localStringBuffer.append(encode(paramString1, allowed_within_query, str));
    localStringBuffer.append('=');
    localStringBuffer.append(encode(paramString2, allowed_within_query, str));
    this._query = localStringBuffer.toString().toCharArray();
    setURI();
  }

  public void setQuery(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    int i = paramArrayOfString1.length;
    if (i != paramArrayOfString2.length)
      throw new URIException("wrong array size of query");
    StringBuffer localStringBuffer = new StringBuffer();
    String str = getProtocolCharset();
    int j = 0;
    while (j < i)
    {
      localStringBuffer.append(encode(paramArrayOfString1[j], allowed_within_query, str));
      localStringBuffer.append('=');
      localStringBuffer.append(encode(paramArrayOfString2[j], allowed_within_query, str));
      j++;
      if (j < i)
        localStringBuffer.append('&');
    }
    this._query = localStringBuffer.toString().toCharArray();
    setURI();
  }

  public void setRawPassword(char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar != null) && (!validate(paramArrayOfChar, within_userinfo)))
      throw new URIException(3, "escaped password not valid");
    if ((getRawUser() != null) && (getRawUser().length != 0))
    {
      String str1 = new String(getRawUser());
      String str2;
      if (paramArrayOfChar == null)
        str2 = null;
      else
        str2 = new String(paramArrayOfChar);
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(str1);
      String str3;
      if (str2 == null)
      {
        str3 = "";
      }
      else
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append(":");
        localStringBuffer2.append(str2);
        str3 = localStringBuffer2.toString();
      }
      localStringBuffer1.append(str3);
      String str4 = localStringBuffer1.toString();
      String str5 = new String(getRawHost());
      if (this._port != -1)
      {
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append(str5);
        localStringBuffer3.append(":");
        localStringBuffer3.append(this._port);
        str5 = localStringBuffer3.toString();
      }
      StringBuffer localStringBuffer4 = new StringBuffer();
      localStringBuffer4.append(str4);
      localStringBuffer4.append("@");
      localStringBuffer4.append(str5);
      String str6 = localStringBuffer4.toString();
      this._userinfo = str4.toCharArray();
      this._authority = str6.toCharArray();
      setURI();
      return;
    }
    throw new URIException(1, "username required");
  }

  public void setRawUser(char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar != null) && (paramArrayOfChar.length != 0))
    {
      if (!validate(paramArrayOfChar, within_userinfo))
        throw new URIException(3, "escaped user not valid");
      String str1 = new String(paramArrayOfChar);
      char[] arrayOfChar = getRawPassword();
      String str2;
      if (arrayOfChar == null)
        str2 = null;
      else
        str2 = new String(arrayOfChar);
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(str1);
      String str3;
      if (str2 == null)
      {
        str3 = "";
      }
      else
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append(":");
        localStringBuffer2.append(str2);
        str3 = localStringBuffer2.toString();
      }
      localStringBuffer1.append(str3);
      String str4 = localStringBuffer1.toString();
      String str5 = new String(getRawHost());
      if (this._port != -1)
      {
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append(str5);
        localStringBuffer3.append(":");
        localStringBuffer3.append(this._port);
        str5 = localStringBuffer3.toString();
      }
      StringBuffer localStringBuffer4 = new StringBuffer();
      localStringBuffer4.append(str4);
      localStringBuffer4.append("@");
      localStringBuffer4.append(str5);
      String str6 = localStringBuffer4.toString();
      this._userinfo = str4.toCharArray();
      this._authority = str6.toCharArray();
      setURI();
      return;
    }
    throw new URIException(1, "user required");
  }

  public void setRawUserinfo(char[] paramArrayOfChar1, char[] paramArrayOfChar2)
  {
    if ((paramArrayOfChar1 != null) && (paramArrayOfChar1.length != 0))
    {
      if ((validate(paramArrayOfChar1, within_userinfo)) && ((paramArrayOfChar2 == null) || (validate(paramArrayOfChar2, within_userinfo))))
      {
        String str1 = new String(paramArrayOfChar1);
        String str2;
        if (paramArrayOfChar2 == null)
          str2 = null;
        else
          str2 = new String(paramArrayOfChar2);
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append(str1);
        String str3;
        if (str2 == null)
        {
          str3 = "";
        }
        else
        {
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.append(":");
          localStringBuffer2.append(str2);
          str3 = localStringBuffer2.toString();
        }
        localStringBuffer1.append(str3);
        String str4 = localStringBuffer1.toString();
        String str5 = new String(getRawHost());
        if (this._port != -1)
        {
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append(str5);
          localStringBuffer3.append(":");
          localStringBuffer3.append(this._port);
          str5 = localStringBuffer3.toString();
        }
        StringBuffer localStringBuffer4 = new StringBuffer();
        localStringBuffer4.append(str4);
        localStringBuffer4.append("@");
        localStringBuffer4.append(str5);
        String str6 = localStringBuffer4.toString();
        this._userinfo = str4.toCharArray();
        this._authority = str6.toCharArray();
        setURI();
        return;
      }
      throw new URIException(3, "escaped userinfo not valid");
    }
    throw new URIException(1, "user required");
  }

  protected void setURI()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this._scheme != null)
    {
      localStringBuffer.append(this._scheme);
      localStringBuffer.append(':');
    }
    if (this._is_net_path)
    {
      localStringBuffer.append("//");
      if (this._authority != null)
        if (this._userinfo != null)
        {
          if (this._host != null)
          {
            localStringBuffer.append(this._host);
            if (this._port != -1)
            {
              localStringBuffer.append(':');
              localStringBuffer.append(this._port);
            }
          }
        }
        else
          localStringBuffer.append(this._authority);
    }
    if ((this._opaque != null) && (this._is_opaque_part))
      localStringBuffer.append(this._opaque);
    else if ((this._path != null) && (this._path.length != 0))
      localStringBuffer.append(this._path);
    if (this._query != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(this._query);
    }
    this._uri = localStringBuffer.toString().toCharArray();
    this.hash = 0;
  }

  public void setUser(String paramString)
  {
    setRawUser(encode(paramString, allowed_within_userinfo, getProtocolCharset()));
  }

  public void setUserinfo(String paramString1, String paramString2)
  {
    String str = getProtocolCharset();
    char[] arrayOfChar1 = encode(paramString1, within_userinfo, str);
    char[] arrayOfChar2;
    if (paramString2 == null)
      arrayOfChar2 = null;
    else
      arrayOfChar2 = encode(paramString2, within_userinfo, str);
    setRawUserinfo(arrayOfChar1, arrayOfChar2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpURL
 * JD-Core Version:    0.6.1
 */