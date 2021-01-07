package org.apache.commons.httpclient.auth;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class AuthChallengeProcessor
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
  private HttpParams params = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$AuthChallengeProcessor == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.AuthChallengeProcessor");
      class$org$apache$commons$httpclient$auth$AuthChallengeProcessor = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
    }
  }

  public AuthChallengeProcessor(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameter collection may not be null");
    this.params = paramHttpParams;
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

  public AuthScheme processChallenge(AuthState paramAuthState, Map paramMap)
  {
    if (paramAuthState == null)
      throw new IllegalArgumentException("Authentication state may not be null");
    if (paramMap == null)
      throw new IllegalArgumentException("Challenge map may not be null");
    if ((paramAuthState.isPreemptive()) || (paramAuthState.getAuthScheme() == null))
      paramAuthState.setAuthScheme(selectAuthScheme(paramMap));
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    String str1 = localAuthScheme.getSchemeName();
    if (LOG.isDebugEnabled())
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Using authentication scheme: ");
      localStringBuffer2.append(str1);
      localLog.debug(localStringBuffer2.toString());
    }
    String str2 = (String)paramMap.get(str1.toLowerCase());
    if (str2 == null)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(str1);
      localStringBuffer1.append(" authorization challenge expected, but not found");
      throw new AuthenticationException(localStringBuffer1.toString());
    }
    localAuthScheme.processChallenge(str2);
    LOG.debug("Authorization challenge processed");
    return localAuthScheme;
  }

  public AuthScheme selectAuthScheme(Map paramMap)
  {
    if (paramMap == null)
      throw new IllegalArgumentException("Challenge map may not be null");
    Object localObject = (Collection)this.params.getParameter("http.auth.scheme-priority");
    if ((localObject == null) || (((Collection)localObject).isEmpty()))
      localObject = AuthPolicy.getDefaultAuthPrefs();
    if (LOG.isDebugEnabled())
    {
      Log localLog3 = LOG;
      StringBuffer localStringBuffer4 = new StringBuffer();
      localStringBuffer4.append("Supported authentication schemes in the order of preference: ");
      localStringBuffer4.append(localObject);
      localLog3.debug(localStringBuffer4.toString());
    }
    Iterator localIterator = ((Collection)localObject).iterator();
    AuthScheme localAuthScheme;
    while (true)
    {
      boolean bool = localIterator.hasNext();
      localAuthScheme = null;
      if (!bool)
        break;
      String str = (String)localIterator.next();
      if ((String)paramMap.get(str.toLowerCase()) != null)
      {
        if (LOG.isInfoEnabled())
        {
          Log localLog2 = LOG;
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append(str);
          localStringBuffer3.append(" authentication scheme selected");
          localLog2.info(localStringBuffer3.toString());
        }
        try
        {
          localAuthScheme = AuthPolicy.getAuthScheme(str);
        }
        catch (IllegalStateException localIllegalStateException)
        {
          throw new AuthChallengeException(localIllegalStateException.getMessage());
        }
      }
      if (LOG.isDebugEnabled())
      {
        Log localLog1 = LOG;
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Challenge for ");
        localStringBuffer2.append(str);
        localStringBuffer2.append(" authentication scheme not available");
        localLog1.debug(localStringBuffer2.toString());
      }
    }
    if (localAuthScheme == null)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Unable to respond to any of these challenges: ");
      localStringBuffer1.append(paramMap);
      throw new AuthChallengeException(localStringBuffer1.toString());
    }
    return localAuthScheme;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthChallengeProcessor
 * JD-Core Version:    0.6.1
 */