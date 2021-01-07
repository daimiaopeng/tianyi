package org.apache.http.auth;

import java.security.Principal;

@Deprecated
public abstract interface Credentials
{
  public abstract String getPassword();

  public abstract Principal getUserPrincipal();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.Credentials
 * JD-Core Version:    0.6.1
 */