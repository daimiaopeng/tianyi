package org.apache.commons.httpclient;

public class InvalidRedirectLocationException extends RedirectException
{
  private final String location;

  public InvalidRedirectLocationException(String paramString1, String paramString2)
  {
    super(paramString1);
    this.location = paramString2;
  }

  public InvalidRedirectLocationException(String paramString1, String paramString2, Throwable paramThrowable)
  {
    super(paramString1, paramThrowable);
    this.location = paramString2;
  }

  public String getLocation()
  {
    return this.location;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.InvalidRedirectLocationException
 * JD-Core Version:    0.6.1
 */