package org.jsoup;

import java.io.IOException;

public class HttpStatusException extends IOException
{
  private int statusCode;
  private String url;

  public HttpStatusException(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1);
    this.statusCode = paramInt;
    this.url = paramString2;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public String getUrl()
  {
    return this.url;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.toString());
    localStringBuilder.append(". Status=");
    localStringBuilder.append(this.statusCode);
    localStringBuilder.append(", URL=");
    localStringBuilder.append(this.url);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.HttpStatusException
 * JD-Core Version:    0.6.1
 */