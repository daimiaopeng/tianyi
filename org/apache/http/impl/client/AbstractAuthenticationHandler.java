package org.apache.http.impl.client;

import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract class AbstractAuthenticationHandler
  implements AuthenticationHandler
{
  public AbstractAuthenticationHandler()
  {
    throw new RuntimeException("Stub!");
  }

  protected List<String> getAuthPreferences()
  {
    throw new RuntimeException("Stub!");
  }

  protected Map<String, Header> parseChallenges(Header[] paramArrayOfHeader)
  {
    throw new RuntimeException("Stub!");
  }

  public AuthScheme selectScheme(Map<String, Header> paramMap, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.AbstractAuthenticationHandler
 * JD-Core Version:    0.6.1
 */