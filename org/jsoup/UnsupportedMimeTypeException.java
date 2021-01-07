package org.jsoup;

import java.io.IOException;

public class UnsupportedMimeTypeException extends IOException
{
  private String mimeType;
  private String url;

  public UnsupportedMimeTypeException(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1);
    this.mimeType = paramString2;
    this.url = paramString3;
  }

  public String getMimeType()
  {
    return this.mimeType;
  }

  public String getUrl()
  {
    return this.url;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.toString());
    localStringBuilder.append(". Mimetype=");
    localStringBuilder.append(this.mimeType);
    localStringBuilder.append(", URL=");
    localStringBuilder.append(this.url);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.UnsupportedMimeTypeException
 * JD-Core Version:    0.6.1
 */