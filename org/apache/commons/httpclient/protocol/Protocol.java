package org.apache.commons.httpclient.protocol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.util.LangUtils;

public class Protocol
{
  private static final Map PROTOCOLS = Collections.synchronizedMap(new HashMap());
  private int defaultPort;
  private String scheme;
  private boolean secure;
  private ProtocolSocketFactory socketFactory;

  public Protocol(String paramString, ProtocolSocketFactory paramProtocolSocketFactory, int paramInt)
  {
    if (paramString == null)
      throw new IllegalArgumentException("scheme is null");
    if (paramProtocolSocketFactory == null)
      throw new IllegalArgumentException("socketFactory is null");
    if (paramInt <= 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("port is invalid: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    this.scheme = paramString;
    this.socketFactory = paramProtocolSocketFactory;
    this.defaultPort = paramInt;
    this.secure = (paramProtocolSocketFactory instanceof SecureProtocolSocketFactory);
  }

  public Protocol(String paramString, SecureProtocolSocketFactory paramSecureProtocolSocketFactory, int paramInt)
  {
    this(paramString, paramSecureProtocolSocketFactory, paramInt);
  }

  public static Protocol getProtocol(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("id is null");
    Protocol localProtocol = (Protocol)PROTOCOLS.get(paramString);
    if (localProtocol == null)
      localProtocol = lazyRegisterProtocol(paramString);
    return localProtocol;
  }

  private static Protocol lazyRegisterProtocol(String paramString)
  {
    if ("http".equals(paramString))
    {
      Protocol localProtocol1 = new Protocol("http", DefaultProtocolSocketFactory.getSocketFactory(), 80);
      registerProtocol("http", localProtocol1);
      return localProtocol1;
    }
    if ("https".equals(paramString))
    {
      Protocol localProtocol2 = new Protocol("https", SSLProtocolSocketFactory.getSocketFactory(), 443);
      registerProtocol("https", localProtocol2);
      return localProtocol2;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("unsupported protocol: '");
    localStringBuffer.append(paramString);
    localStringBuffer.append("'");
    throw new IllegalStateException(localStringBuffer.toString());
  }

  public static void registerProtocol(String paramString, Protocol paramProtocol)
  {
    if (paramString == null)
      throw new IllegalArgumentException("id is null");
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol is null");
    PROTOCOLS.put(paramString, paramProtocol);
  }

  public static void unregisterProtocol(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("id is null");
    PROTOCOLS.remove(paramString);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Protocol))
    {
      Protocol localProtocol = (Protocol)paramObject;
      int i = this.defaultPort;
      int j = localProtocol.getDefaultPort();
      boolean bool1 = false;
      if (i == j)
      {
        boolean bool2 = this.scheme.equalsIgnoreCase(localProtocol.getScheme());
        bool1 = false;
        if (bool2)
        {
          boolean bool3 = this.secure;
          boolean bool4 = localProtocol.isSecure();
          bool1 = false;
          if (bool3 == bool4)
          {
            boolean bool5 = this.socketFactory.equals(localProtocol.getSocketFactory());
            bool1 = false;
            if (bool5)
              bool1 = true;
          }
        }
      }
      return bool1;
    }
    return false;
  }

  public int getDefaultPort()
  {
    return this.defaultPort;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public ProtocolSocketFactory getSocketFactory()
  {
    return this.socketFactory;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), this.scheme.toLowerCase()), this.secure), this.socketFactory);
  }

  public boolean isSecure()
  {
    return this.secure;
  }

  public int resolvePort(int paramInt)
  {
    if (paramInt <= 0)
      paramInt = getDefaultPort();
    return paramInt;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.scheme);
    localStringBuffer.append(":");
    localStringBuffer.append(this.defaultPort);
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.Protocol
 * JD-Core Version:    0.6.1
 */