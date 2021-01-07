package org.apache.commons.httpclient.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AuthPolicy
{
  public static final String AUTH_SCHEME_PRIORITY = "http.auth.scheme-priority";
  public static final String BASIC = "Basic";
  public static final String DIGEST = "Digest";
  protected static final Log LOG = LogFactory.getLog(localClass4);
  public static final String NTLM = "NTLM";
  private static final HashMap SCHEMES = new HashMap();
  private static final ArrayList SCHEME_LIST = new ArrayList();
  static Class class$org$apache$commons$httpclient$auth$AuthPolicy;
  static Class class$org$apache$commons$httpclient$auth$BasicScheme;
  static Class class$org$apache$commons$httpclient$auth$DigestScheme;
  static Class class$org$apache$commons$httpclient$auth$NTLMScheme;

  static
  {
    Class localClass1;
    if (class$org$apache$commons$httpclient$auth$NTLMScheme == null)
    {
      localClass1 = class$("org.apache.commons.httpclient.auth.NTLMScheme");
      class$org$apache$commons$httpclient$auth$NTLMScheme = localClass1;
    }
    else
    {
      localClass1 = class$org$apache$commons$httpclient$auth$NTLMScheme;
    }
    registerAuthScheme("NTLM", localClass1);
    Class localClass2;
    if (class$org$apache$commons$httpclient$auth$DigestScheme == null)
    {
      localClass2 = class$("org.apache.commons.httpclient.auth.DigestScheme");
      class$org$apache$commons$httpclient$auth$DigestScheme = localClass2;
    }
    else
    {
      localClass2 = class$org$apache$commons$httpclient$auth$DigestScheme;
    }
    registerAuthScheme("Digest", localClass2);
    Class localClass3;
    if (class$org$apache$commons$httpclient$auth$BasicScheme == null)
    {
      localClass3 = class$("org.apache.commons.httpclient.auth.BasicScheme");
      class$org$apache$commons$httpclient$auth$BasicScheme = localClass3;
    }
    else
    {
      localClass3 = class$org$apache$commons$httpclient$auth$BasicScheme;
    }
    registerAuthScheme("Basic", localClass3);
    Class localClass4;
    if (class$org$apache$commons$httpclient$auth$AuthPolicy == null)
    {
      localClass4 = class$("org.apache.commons.httpclient.auth.AuthPolicy");
      class$org$apache$commons$httpclient$auth$AuthPolicy = localClass4;
    }
    else
    {
      localClass4 = class$org$apache$commons$httpclient$auth$AuthPolicy;
    }
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

  public static AuthScheme getAuthScheme(String paramString)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("Id may not be null");
      Class localClass = (Class)SCHEMES.get(paramString.toLowerCase());
      if (localClass != null)
        try
        {
          AuthScheme localAuthScheme = (AuthScheme)localClass.newInstance();
          return localAuthScheme;
        }
        catch (Exception localException)
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.append("Error initializing authentication scheme: ");
          localStringBuffer2.append(paramString);
          localLog.error(localStringBuffer2.toString(), localException);
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append(paramString);
          localStringBuffer3.append(" authentication scheme implemented by ");
          localStringBuffer3.append(localClass.getName());
          localStringBuffer3.append(" could not be initialized");
          throw new IllegalStateException(localStringBuffer3.toString());
        }
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Unsupported authentication scheme ");
      localStringBuffer1.append(paramString);
      throw new IllegalStateException(localStringBuffer1.toString());
      label183: Object localObject1;
      throw localObject1;
    }
    finally
    {
      break label183;
    }
  }

  public static List getDefaultAuthPrefs()
  {
    try
    {
      List localList = (List)SCHEME_LIST.clone();
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static void registerAuthScheme(String paramString, Class paramClass)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("Id may not be null");
      if (paramClass == null)
        throw new IllegalArgumentException("Authentication scheme class may not be null");
      SCHEMES.put(paramString.toLowerCase(), paramClass);
      SCHEME_LIST.add(paramString.toLowerCase());
      return;
      Object localObject1;
      throw localObject1;
    }
    finally
    {
    }
  }

  public static void unregisterAuthScheme(String paramString)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("Id may not be null");
      SCHEMES.remove(paramString.toLowerCase());
      SCHEME_LIST.remove(paramString.toLowerCase());
      return;
      Object localObject1;
      throw localObject1;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthPolicy
 * JD-Core Version:    0.6.1
 */