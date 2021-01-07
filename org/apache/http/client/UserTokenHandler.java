package org.apache.http.client;

import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface UserTokenHandler
{
  public abstract Object getUserToken(HttpContext paramHttpContext);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.UserTokenHandler
 * JD-Core Version:    0.6.1
 */