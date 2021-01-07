package org.apache.commons.httpclient;

public class HttpContentTooLargeException extends HttpException
{
  private int maxlen;

  public HttpContentTooLargeException(String paramString, int paramInt)
  {
    super(paramString);
    this.maxlen = paramInt;
  }

  public int getMaxLength()
  {
    return this.maxlen;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpContentTooLargeException
 * JD-Core Version:    0.6.1
 */