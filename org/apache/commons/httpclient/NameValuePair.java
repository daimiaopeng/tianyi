package org.apache.commons.httpclient;

import java.io.Serializable;
import org.apache.commons.httpclient.util.LangUtils;

public class NameValuePair
  implements Serializable
{
  private String name = null;
  private String value = null;

  public NameValuePair()
  {
    this(null, null);
  }

  public NameValuePair(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NameValuePair))
    {
      NameValuePair localNameValuePair = (NameValuePair)paramObject;
      boolean bool1 = LangUtils.equals(this.name, localNameValuePair.name);
      boolean bool2 = false;
      if (bool1)
      {
        boolean bool3 = LangUtils.equals(this.value, localNameValuePair.value);
        bool2 = false;
        if (bool3)
          bool2 = true;
      }
      return bool2;
    }
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setValue(String paramString)
  {
    this.value = paramString;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("name=");
    localStringBuffer.append(this.name);
    localStringBuffer.append(", ");
    localStringBuffer.append("value=");
    localStringBuffer.append(this.value);
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.NameValuePair
 * JD-Core Version:    0.6.1
 */