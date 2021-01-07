package org.apache.commons.httpclient.auth;

import java.util.Map;

public abstract class RFC2617Scheme
  implements AuthScheme
{
  private Map params = null;

  public RFC2617Scheme()
  {
  }

  public RFC2617Scheme(String paramString)
  {
    processChallenge(paramString);
  }

  public String getID()
  {
    return getRealm();
  }

  public String getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name may not be null");
    if (this.params == null)
      return null;
    return (String)this.params.get(paramString.toLowerCase());
  }

  protected Map getParameters()
  {
    return this.params;
  }

  public String getRealm()
  {
    return getParameter("realm");
  }

  public void processChallenge(String paramString)
  {
    if (!AuthChallengeParser.extractScheme(paramString).equalsIgnoreCase(getSchemeName()))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid ");
      localStringBuffer.append(getSchemeName());
      localStringBuffer.append(" challenge: ");
      localStringBuffer.append(paramString);
      throw new MalformedChallengeException(localStringBuffer.toString());
    }
    this.params = AuthChallengeParser.extractParams(paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.RFC2617Scheme
 * JD-Core Version:    0.6.1
 */