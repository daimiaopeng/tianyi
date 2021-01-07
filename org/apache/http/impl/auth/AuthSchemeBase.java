package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.auth.AuthScheme;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract class AuthSchemeBase
  implements AuthScheme
{
  public AuthSchemeBase()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isProxy()
  {
    throw new RuntimeException("Stub!");
  }

  protected abstract void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2);

  public void processChallenge(Header paramHeader)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.AuthSchemeBase
 * JD-Core Version:    0.6.1
 */