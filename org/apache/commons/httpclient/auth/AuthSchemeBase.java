package org.apache.commons.httpclient.auth;

public abstract class AuthSchemeBase
  implements AuthScheme
{
  private String challenge = null;

  public AuthSchemeBase(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Challenge may not be null");
    this.challenge = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof AuthSchemeBase))
      return this.challenge.equals(((AuthSchemeBase)paramObject).challenge);
    return super.equals(paramObject);
  }

  public int hashCode()
  {
    return this.challenge.hashCode();
  }

  public String toString()
  {
    return this.challenge;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthSchemeBase
 * JD-Core Version:    0.6.1
 */