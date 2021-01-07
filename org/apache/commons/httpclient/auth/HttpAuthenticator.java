package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class HttpAuthenticator
{
  private static final Log LOG = LogFactory.getLog(localClass);
  public static final String PROXY_AUTH = "Proxy-Authenticate";
  public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
  public static final String WWW_AUTH = "WWW-Authenticate";
  public static final String WWW_AUTH_RESP = "Authorization";
  static Class class$org$apache$commons$httpclient$auth$HttpAuthenticator;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$HttpAuthenticator == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.HttpAuthenticator");
      class$org$apache$commons$httpclient$auth$HttpAuthenticator = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$auth$HttpAuthenticator;
    }
  }

  public static boolean authenticate(AuthScheme paramAuthScheme, HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
  {
    LOG.trace("enter HttpAuthenticator.authenticate(AuthScheme, HttpMethod, HttpConnection, HttpState)");
    return doAuthenticate(paramAuthScheme, paramHttpMethod, paramHttpConnection, paramHttpState, false);
  }

  public static boolean authenticateDefault(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
  {
    LOG.trace("enter HttpAuthenticator.authenticateDefault(HttpMethod, HttpConnection, HttpState)");
    return doAuthenticateDefault(paramHttpMethod, paramHttpConnection, paramHttpState, false);
  }

  public static boolean authenticateProxy(AuthScheme paramAuthScheme, HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
  {
    LOG.trace("enter HttpAuthenticator.authenticateProxy(AuthScheme, HttpMethod, HttpState)");
    return doAuthenticate(paramAuthScheme, paramHttpMethod, paramHttpConnection, paramHttpState, true);
  }

  public static boolean authenticateProxyDefault(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
  {
    LOG.trace("enter HttpAuthenticator.authenticateProxyDefault(HttpMethod, HttpState)");
    return doAuthenticateDefault(paramHttpMethod, paramHttpConnection, paramHttpState, true);
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  private static boolean doAuthenticate(AuthScheme paramAuthScheme, HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState, boolean paramBoolean)
  {
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Authentication scheme may not be null");
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("HTTP method may not be null");
    if (paramHttpState == null)
      throw new IllegalArgumentException("HTTP state may not be null");
    String str1 = null;
    if (paramHttpConnection != null)
      if (paramBoolean)
      {
        str1 = paramHttpConnection.getProxyHost();
      }
      else
      {
        str1 = paramHttpMethod.getParams().getVirtualHost();
        if (str1 == null)
          str1 = paramHttpConnection.getHost();
      }
    String str2 = paramAuthScheme.getRealm();
    if (LOG.isDebugEnabled())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Using credentials for ");
      if (str2 == null)
      {
        localStringBuffer1.append("default");
      }
      else
      {
        localStringBuffer1.append('\'');
        localStringBuffer1.append(str2);
        localStringBuffer1.append('\'');
      }
      localStringBuffer1.append(" authentication realm at ");
      localStringBuffer1.append(str1);
      LOG.debug(localStringBuffer1.toString());
    }
    Credentials localCredentials;
    if (paramBoolean)
      localCredentials = paramHttpState.getProxyCredentials(str2, str1);
    else
      localCredentials = paramHttpState.getCredentials(str2, str1);
    if (localCredentials == null)
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("No credentials available for the ");
      if (str2 == null)
      {
        localStringBuffer2.append("default");
      }
      else
      {
        localStringBuffer2.append('\'');
        localStringBuffer2.append(str2);
        localStringBuffer2.append('\'');
      }
      localStringBuffer2.append(" authentication realm at ");
      localStringBuffer2.append(str1);
      throw new CredentialsNotAvailableException(localStringBuffer2.toString());
    }
    String str3 = paramAuthScheme.authenticate(localCredentials, paramHttpMethod);
    if (str3 != null)
    {
      String str4;
      if (paramBoolean)
        str4 = "Proxy-Authorization";
      else
        str4 = "Authorization";
      paramHttpMethod.addRequestHeader(new Header(str4, str3, true));
      return true;
    }
    return false;
  }

  private static boolean doAuthenticateDefault(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState, boolean paramBoolean)
  {
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("HTTP method may not be null");
    if (paramHttpState == null)
      throw new IllegalArgumentException("HTTP state may not be null");
    String str1;
    if (paramHttpConnection != null)
    {
      if (paramBoolean)
        str1 = paramHttpConnection.getProxyHost();
      else
        str1 = paramHttpConnection.getHost();
    }
    else
      str1 = null;
    Credentials localCredentials;
    if (paramBoolean)
      localCredentials = paramHttpState.getProxyCredentials(null, str1);
    else
      localCredentials = paramHttpState.getCredentials(null, str1);
    if (localCredentials == null)
      return false;
    if (!(localCredentials instanceof UsernamePasswordCredentials))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Credentials cannot be used for basic authentication: ");
      localStringBuffer.append(localCredentials.toString());
      throw new InvalidCredentialsException(localStringBuffer.toString());
    }
    String str2 = BasicScheme.authenticate((UsernamePasswordCredentials)localCredentials, paramHttpMethod.getParams().getCredentialCharset());
    if (str2 != null)
    {
      String str3;
      if (paramBoolean)
        str3 = "Proxy-Authorization";
      else
        str3 = "Authorization";
      paramHttpMethod.addRequestHeader(new Header(str3, str2, true));
      return true;
    }
    return false;
  }

  public static AuthScheme selectAuthScheme(Header[] paramArrayOfHeader)
  {
    LOG.trace("enter HttpAuthenticator.selectAuthScheme(Header[])");
    if (paramArrayOfHeader == null)
      throw new IllegalArgumentException("Array of challenges may not be null");
    if (paramArrayOfHeader.length == 0)
      throw new IllegalArgumentException("Array of challenges may not be empty");
    HashMap localHashMap = new HashMap(paramArrayOfHeader.length);
    for (int i = 0; i < paramArrayOfHeader.length; i++)
    {
      String str4 = paramArrayOfHeader[i].getValue();
      localHashMap.put(AuthChallengeParser.extractScheme(str4), str4);
    }
    String str1 = (String)localHashMap.get("ntlm");
    if (str1 != null)
      return new NTLMScheme(str1);
    String str2 = (String)localHashMap.get("digest");
    if (str2 != null)
      return new DigestScheme(str2);
    String str3 = (String)localHashMap.get("basic");
    if (str3 != null)
      return new BasicScheme(str3);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Authentication scheme(s) not supported: ");
    localStringBuffer.append(localHashMap.toString());
    throw new UnsupportedOperationException(localStringBuffer.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.HttpAuthenticator
 * JD-Core Version:    0.6.1
 */