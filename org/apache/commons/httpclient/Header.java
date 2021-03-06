package org.apache.commons.httpclient;

public class Header extends NameValuePair
{
  private boolean isAutogenerated = false;

  public Header()
  {
    this(null, null);
  }

  public Header(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public Header(String paramString1, String paramString2, boolean paramBoolean)
  {
    super(paramString1, paramString2);
    this.isAutogenerated = paramBoolean;
  }

  public HeaderElement[] getElements()
  {
    return HeaderElement.parseElements(getValue());
  }

  public HeaderElement[] getValues()
  {
    return HeaderElement.parse(getValue());
  }

  public boolean isAutogenerated()
  {
    return this.isAutogenerated;
  }

  public String toExternalForm()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str1;
    if (getName() == null)
      str1 = "";
    else
      str1 = getName();
    localStringBuffer.append(str1);
    localStringBuffer.append(": ");
    String str2;
    if (getValue() == null)
      str2 = "";
    else
      str2 = getValue();
    localStringBuffer.append(str2);
    localStringBuffer.append("\r\n");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return toExternalForm();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.Header
 * JD-Core Version:    0.6.1
 */