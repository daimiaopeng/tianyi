package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.LangUtils;

public class UsernamePasswordCredentials
  implements Credentials
{
  private String password;
  private String userName;

  public UsernamePasswordCredentials()
  {
  }

  public UsernamePasswordCredentials(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username:password string may not be null");
    int i = paramString.indexOf(':');
    if (i >= 0)
    {
      this.userName = paramString.substring(0, i);
      this.password = paramString.substring(i + 1);
    }
    else
    {
      this.userName = paramString;
    }
  }

  public UsernamePasswordCredentials(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Username may not be null");
    this.userName = paramString1;
    this.password = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this == paramObject)
      return true;
    if (getClass().equals(paramObject.getClass()))
    {
      UsernamePasswordCredentials localUsernamePasswordCredentials = (UsernamePasswordCredentials)paramObject;
      if ((LangUtils.equals(this.userName, localUsernamePasswordCredentials.userName)) && (LangUtils.equals(this.password, localUsernamePasswordCredentials.password)))
        return true;
    }
    return false;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.userName), this.password);
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setUserName(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username may not be null");
    this.userName = paramString;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.userName);
    localStringBuffer.append(":");
    String str;
    if (this.password == null)
      str = "null";
    else
      str = this.password;
    localStringBuffer.append(str);
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.UsernamePasswordCredentials
 * JD-Core Version:    0.6.1
 */