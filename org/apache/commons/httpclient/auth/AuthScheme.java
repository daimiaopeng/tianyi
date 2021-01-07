package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

public abstract interface AuthScheme
{
  public abstract String authenticate(Credentials paramCredentials, String paramString1, String paramString2);

  public abstract String authenticate(Credentials paramCredentials, HttpMethod paramHttpMethod);

  public abstract String getID();

  public abstract String getParameter(String paramString);

  public abstract String getRealm();

  public abstract String getSchemeName();

  public abstract boolean isComplete();

  public abstract boolean isConnectionBased();

  public abstract void processChallenge(String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthScheme
 * JD-Core Version:    0.6.1
 */