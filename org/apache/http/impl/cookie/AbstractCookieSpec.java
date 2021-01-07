package org.apache.http.impl.cookie;

import java.util.Collection;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;

@Deprecated
public abstract class AbstractCookieSpec
  implements CookieSpec
{
  public AbstractCookieSpec()
  {
    throw new RuntimeException("Stub!");
  }

  protected CookieAttributeHandler findAttribHandler(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  protected CookieAttributeHandler getAttribHandler(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  protected Collection<CookieAttributeHandler> getAttribHandlers()
  {
    throw new RuntimeException("Stub!");
  }

  public void registerAttribHandler(String paramString, CookieAttributeHandler paramCookieAttributeHandler)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.AbstractCookieSpec
 * JD-Core Version:    0.6.1
 */