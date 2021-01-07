package org.apache.http.client.protocol;

import java.util.List;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.protocol.HttpContext;

@Deprecated
public class ClientContextConfigurer
  implements ClientContext
{
  public ClientContextConfigurer(HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }

  public void setAuthSchemePref(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }

  public void setAuthSchemeRegistry(AuthSchemeRegistry paramAuthSchemeRegistry)
  {
    throw new RuntimeException("Stub!");
  }

  public void setCookieSpecRegistry(CookieSpecRegistry paramCookieSpecRegistry)
  {
    throw new RuntimeException("Stub!");
  }

  public void setCookieStore(CookieStore paramCookieStore)
  {
    throw new RuntimeException("Stub!");
  }

  public void setCredentialsProvider(CredentialsProvider paramCredentialsProvider)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.protocol.ClientContextConfigurer
 * JD-Core Version:    0.6.1
 */