package org.apache.http.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;

@Deprecated
public abstract interface AuthScheme
{
  public abstract Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest);

  public abstract String getParameter(String paramString);

  public abstract String getRealm();

  public abstract String getSchemeName();

  public abstract boolean isComplete();

  public abstract boolean isConnectionBased();

  public abstract void processChallenge(Header paramHeader);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.AuthScheme
 * JD-Core Version:    0.6.1
 */