package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;

public abstract interface CredentialsProvider
{
  public static final String PROVIDER = "http.authentication.credential-provider";

  public abstract Credentials getCredentials(AuthScheme paramAuthScheme, String paramString, int paramInt, boolean paramBoolean);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.CredentialsProvider
 * JD-Core Version:    0.6.1
 */