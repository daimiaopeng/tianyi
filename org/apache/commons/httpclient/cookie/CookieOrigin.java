package org.apache.commons.httpclient.cookie;

public final class CookieOrigin
{
  private final String host;
  private final String path;
  private final int port;
  private final boolean secure;

  public CookieOrigin(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid port: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    this.host = paramString1;
    this.port = paramInt;
    this.path = paramString2;
    this.secure = paramBoolean;
  }

  public String getHost()
  {
    return this.host;
  }

  public String getPath()
  {
    return this.path;
  }

  public int getPort()
  {
    return this.port;
  }

  public boolean isSecure()
  {
    return this.secure;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookieOrigin
 * JD-Core Version:    0.6.1
 */