package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;

@Deprecated
public class DigestScheme extends RFC2617Scheme
{
  public DigestScheme()
  {
    throw new RuntimeException("Stub!");
  }

  public static String createCnonce()
  {
    throw new RuntimeException("Stub!");
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
  {
    throw new RuntimeException("Stub!");
  }

  public String getSchemeName()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isComplete()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isConnectionBased()
  {
    throw new RuntimeException("Stub!");
  }

  public void overrideParamter(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }

  public void processChallenge(Header paramHeader)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.DigestScheme
 * JD-Core Version:    0.6.1
 */