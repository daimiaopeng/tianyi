package org.apache.commons.httpclient.cookie;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CookiePolicy
{
  public static final String BROWSER_COMPATIBILITY = "compatibility";
  public static final int COMPATIBILITY = 0;
  public static final String DEFAULT = "default";
  public static final String IGNORE_COOKIES = "ignoreCookies";
  protected static final Log LOG = LogFactory.getLog(localClass7);
  public static final String NETSCAPE = "netscape";
  public static final int NETSCAPE_DRAFT = 1;
  public static final int RFC2109 = 2;
  public static final int RFC2965 = 3;
  public static final String RFC_2109 = "rfc2109";
  public static final String RFC_2965 = "rfc2965";
  private static Map SPECS = Collections.synchronizedMap(new HashMap());
  static Class class$org$apache$commons$httpclient$cookie$CookiePolicy;
  static Class class$org$apache$commons$httpclient$cookie$CookieSpecBase;
  static Class class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec;
  static Class class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec;
  static Class class$org$apache$commons$httpclient$cookie$RFC2109Spec;
  static Class class$org$apache$commons$httpclient$cookie$RFC2965Spec;
  private static int defaultPolicy = 2;

  static
  {
    Class localClass1;
    if (class$org$apache$commons$httpclient$cookie$RFC2109Spec == null)
    {
      localClass1 = class$("org.apache.commons.httpclient.cookie.RFC2109Spec");
      class$org$apache$commons$httpclient$cookie$RFC2109Spec = localClass1;
    }
    else
    {
      localClass1 = class$org$apache$commons$httpclient$cookie$RFC2109Spec;
    }
    registerCookieSpec("default", localClass1);
    Class localClass2;
    if (class$org$apache$commons$httpclient$cookie$RFC2109Spec == null)
    {
      localClass2 = class$("org.apache.commons.httpclient.cookie.RFC2109Spec");
      class$org$apache$commons$httpclient$cookie$RFC2109Spec = localClass2;
    }
    else
    {
      localClass2 = class$org$apache$commons$httpclient$cookie$RFC2109Spec;
    }
    registerCookieSpec("rfc2109", localClass2);
    Class localClass3;
    if (class$org$apache$commons$httpclient$cookie$RFC2965Spec == null)
    {
      localClass3 = class$("org.apache.commons.httpclient.cookie.RFC2965Spec");
      class$org$apache$commons$httpclient$cookie$RFC2965Spec = localClass3;
    }
    else
    {
      localClass3 = class$org$apache$commons$httpclient$cookie$RFC2965Spec;
    }
    registerCookieSpec("rfc2965", localClass3);
    Class localClass4;
    if (class$org$apache$commons$httpclient$cookie$CookieSpecBase == null)
    {
      localClass4 = class$("org.apache.commons.httpclient.cookie.CookieSpecBase");
      class$org$apache$commons$httpclient$cookie$CookieSpecBase = localClass4;
    }
    else
    {
      localClass4 = class$org$apache$commons$httpclient$cookie$CookieSpecBase;
    }
    registerCookieSpec("compatibility", localClass4);
    Class localClass5;
    if (class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec == null)
    {
      localClass5 = class$("org.apache.commons.httpclient.cookie.NetscapeDraftSpec");
      class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec = localClass5;
    }
    else
    {
      localClass5 = class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec;
    }
    registerCookieSpec("netscape", localClass5);
    Class localClass6;
    if (class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec == null)
    {
      localClass6 = class$("org.apache.commons.httpclient.cookie.IgnoreCookiesSpec");
      class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec = localClass6;
    }
    else
    {
      localClass6 = class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec;
    }
    registerCookieSpec("ignoreCookies", localClass6);
    Class localClass7;
    if (class$org$apache$commons$httpclient$cookie$CookiePolicy == null)
    {
      localClass7 = class$("org.apache.commons.httpclient.cookie.CookiePolicy");
      class$org$apache$commons$httpclient$cookie$CookiePolicy = localClass7;
    }
    else
    {
      localClass7 = class$org$apache$commons$httpclient$cookie$CookiePolicy;
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

  public static CookieSpec getCompatibilitySpec()
  {
    return getSpecByPolicy(0);
  }

  public static CookieSpec getCookieSpec(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    Class localClass = (Class)SPECS.get(paramString.toLowerCase());
    if (localClass != null)
      try
      {
        CookieSpec localCookieSpec = (CookieSpec)localClass.newInstance();
        return localCookieSpec;
      }
      catch (Exception localException)
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Error initializing cookie spec: ");
        localStringBuffer2.append(paramString);
        localLog.error(localStringBuffer2.toString(), localException);
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append(paramString);
        localStringBuffer3.append(" cookie spec implemented by ");
        localStringBuffer3.append(localClass.getName());
        localStringBuffer3.append(" could not be initialized");
        throw new IllegalStateException(localStringBuffer3.toString());
      }
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("Unsupported cookie spec ");
    localStringBuffer1.append(paramString);
    throw new IllegalStateException(localStringBuffer1.toString());
  }

  public static int getDefaultPolicy()
  {
    return defaultPolicy;
  }

  // ERROR //
  public static CookieSpec getDefaultSpec()
  {
    // Byte code:
    //   0: ldc 14
    //   2: invokestatic 185	org/apache/commons/httpclient/cookie/CookiePolicy:getCookieSpec	(Ljava/lang/String;)Lorg/apache/commons/httpclient/cookie/CookieSpec;
    //   5: astore_0
    //   6: aload_0
    //   7: areturn
    //   8: getstatic 99	org/apache/commons/httpclient/cookie/CookiePolicy:LOG	Lorg/apache/commons/logging/Log;
    //   11: ldc 187
    //   13: invokeinterface 191 2 0
    //   18: new 193	org/apache/commons/httpclient/cookie/RFC2109Spec
    //   21: dup
    //   22: invokespecial 194	org/apache/commons/httpclient/cookie/RFC2109Spec:<init>	()V
    //   25: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	8	java/lang/IllegalStateException
  }

  public static String[] getRegisteredCookieSpecs()
  {
    return (String[])SPECS.keySet().toArray(new String[SPECS.size()]);
  }

  public static CookieSpec getSpecByPolicy(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return getDefaultSpec();
    case 3:
      return new RFC2965Spec();
    case 2:
      return new RFC2109Spec();
    case 1:
      return new NetscapeDraftSpec();
    case 0:
    }
    return new CookieSpecBase();
  }

  public static CookieSpec getSpecByVersion(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return getDefaultSpec();
    case 1:
      return new RFC2109Spec();
    case 0:
    }
    return new NetscapeDraftSpec();
  }

  public static void registerCookieSpec(String paramString, Class paramClass)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    if (paramClass == null)
      throw new IllegalArgumentException("Cookie spec class may not be null");
    SPECS.put(paramString.toLowerCase(), paramClass);
  }

  public static void setDefaultPolicy(int paramInt)
  {
    defaultPolicy = paramInt;
  }

  public static void unregisterCookieSpec(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    SPECS.remove(paramString.toLowerCase());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookiePolicy
 * JD-Core Version:    0.6.1
 */