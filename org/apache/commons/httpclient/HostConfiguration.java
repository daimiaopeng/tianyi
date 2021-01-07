package org.apache.commons.httpclient;

import java.net.InetAddress;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

public class HostConfiguration
  implements Cloneable
{
  public static final HostConfiguration ANY_HOST_CONFIGURATION = new HostConfiguration();
  private HttpHost host = null;
  private InetAddress localAddress = null;
  private HostParams params = new HostParams();
  private ProxyHost proxyHost = null;

  public HostConfiguration()
  {
  }

  public HostConfiguration(HostConfiguration paramHostConfiguration)
  {
    init(paramHostConfiguration);
  }

  private void init(HostConfiguration paramHostConfiguration)
  {
    try
    {
      if (paramHostConfiguration.host != null)
        this.host = ((HttpHost)paramHostConfiguration.host.clone());
      else
        this.host = null;
      if (paramHostConfiguration.proxyHost != null)
        this.proxyHost = ((ProxyHost)paramHostConfiguration.proxyHost.clone());
      else
        this.proxyHost = null;
      this.localAddress = paramHostConfiguration.getLocalAddress();
      this.params = ((HostParams)paramHostConfiguration.getParams().clone());
      return;
    }
    catch (CloneNotSupportedException localObject2)
    {
      label95: throw localObject1;
      localObject2 = finally;
      tmpTernaryOp = localCloneNotSupportedException;
    }
    finally
    {
      Object localObject1;
      break label95;
    }
  }

  // ERROR //
  public Object clone()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 67	java/lang/Object:clone	()Ljava/lang/Object;
    //   4: checkcast 2	org/apache/commons/httpclient/HostConfiguration
    //   7: astore_1
    //   8: aload_1
    //   9: aload_0
    //   10: invokespecial 39	org/apache/commons/httpclient/HostConfiguration:init	(Lorg/apache/commons/httpclient/HostConfiguration;)V
    //   13: aload_1
    //   14: areturn
    //   15: new 61	java/lang/IllegalArgumentException
    //   18: dup
    //   19: ldc 63
    //   21: invokespecial 66	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   24: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	15	java/lang/CloneNotSupportedException
  }

  public boolean equals(Object paramObject)
  {
    try
    {
      boolean bool1 = paramObject instanceof HostConfiguration;
      if (bool1)
      {
        boolean bool2 = true;
        if (paramObject == this)
          return bool2;
        HostConfiguration localHostConfiguration = (HostConfiguration)paramObject;
        if ((LangUtils.equals(this.host, localHostConfiguration.host)) && (LangUtils.equals(this.proxyHost, localHostConfiguration.proxyHost)))
        {
          boolean bool3 = LangUtils.equals(this.localAddress, localHostConfiguration.localAddress);
          if (bool3);
        }
        else
        {
          bool2 = false;
        }
        return bool2;
      }
      return false;
    }
    finally
    {
    }
  }

  public String getHost()
  {
    try
    {
      if (this.host != null)
      {
        String str = this.host.getHostName();
        return str;
      }
      return null;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getHostURL()
  {
    try
    {
      if (this.host == null)
        throw new IllegalStateException("Host must be set to create a host URL");
      String str = this.host.toURI();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public InetAddress getLocalAddress()
  {
    try
    {
      InetAddress localInetAddress = this.localAddress;
      return localInetAddress;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HostParams getParams()
  {
    return this.params;
  }

  public int getPort()
  {
    try
    {
      if (this.host != null)
      {
        int i = this.host.getPort();
        return i;
      }
      return -1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Protocol getProtocol()
  {
    try
    {
      if (this.host != null)
      {
        Protocol localProtocol = this.host.getProtocol();
        return localProtocol;
      }
      return null;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getProxyHost()
  {
    try
    {
      if (this.proxyHost != null)
      {
        String str = this.proxyHost.getHostName();
        return str;
      }
      return null;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getProxyPort()
  {
    try
    {
      if (this.proxyHost != null)
      {
        int i = this.proxyHost.getPort();
        return i;
      }
      return -1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getVirtualHost()
  {
    try
    {
      String str = this.params.getVirtualHost();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int hashCode()
  {
    try
    {
      int i = LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.proxyHost), this.localAddress);
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean hostEquals(HttpConnection paramHttpConnection)
  {
    if (paramHttpConnection == null);
    try
    {
      throw new IllegalArgumentException("Connection may not be null");
      if (this.host != null)
      {
        boolean bool1 = this.host.getHostName().equalsIgnoreCase(paramHttpConnection.getHost());
        if (!bool1)
          return false;
        int i = this.host.getPort();
        int j = paramHttpConnection.getPort();
        if (i != j)
          return false;
        boolean bool2 = this.host.getProtocol().equals(paramHttpConnection.getProtocol());
        if (!bool2)
          return false;
        if (this.localAddress != null)
        {
          boolean bool3 = this.localAddress.equals(paramHttpConnection.getLocalAddress());
          if (!bool3)
            return false;
        }
        else
        {
          InetAddress localInetAddress = paramHttpConnection.getLocalAddress();
          if (localInetAddress != null)
            return false;
        }
        return true;
      }
      return false;
      label147: Object localObject1;
      throw localObject1;
    }
    finally
    {
      break label147;
    }
  }

  public boolean isHostSet()
  {
    try
    {
      HttpHost localHttpHost = this.host;
      boolean bool;
      if (localHttpHost != null)
        bool = true;
      else
        bool = false;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isProxySet()
  {
    try
    {
      ProxyHost localProxyHost = this.proxyHost;
      boolean bool;
      if (localProxyHost != null)
        bool = true;
      else
        bool = false;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean proxyEquals(HttpConnection paramHttpConnection)
  {
    if (paramHttpConnection == null);
    try
    {
      throw new IllegalArgumentException("Connection may not be null");
      if (this.proxyHost != null)
      {
        boolean bool2 = this.proxyHost.getHostName().equalsIgnoreCase(paramHttpConnection.getProxyHost());
        boolean bool3 = false;
        if (bool2)
        {
          int i = this.proxyHost.getPort();
          int j = paramHttpConnection.getProxyPort();
          bool3 = false;
          if (i == j)
            bool3 = true;
        }
        return bool3;
      }
      String str = paramHttpConnection.getProxyHost();
      boolean bool1 = false;
      if (str == null)
        bool1 = true;
      return bool1;
      label97: Object localObject1;
      throw localObject1;
    }
    finally
    {
      break label97;
    }
  }

  public void setHost(String paramString)
  {
    try
    {
      Protocol localProtocol = Protocol.getProtocol("http");
      setHost(paramString, localProtocol.getDefaultPort(), localProtocol);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHost(String paramString, int paramInt)
  {
    try
    {
      setHost(paramString, paramInt, Protocol.getProtocol("http"));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHost(String paramString1, int paramInt, String paramString2)
  {
    try
    {
      this.host = new HttpHost(paramString1, paramInt, Protocol.getProtocol(paramString2));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHost(String paramString, int paramInt, Protocol paramProtocol)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("host must not be null");
      if (paramProtocol == null)
        throw new IllegalArgumentException("protocol must not be null");
      this.host = new HttpHost(paramString, paramInt, paramProtocol);
      return;
      Object localObject1;
      throw localObject1;
    }
    finally
    {
    }
  }

  public void setHost(String paramString1, String paramString2, int paramInt, Protocol paramProtocol)
  {
    try
    {
      setHost(paramString1, paramInt, paramProtocol);
      this.params.setVirtualHost(paramString2);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHost(HttpHost paramHttpHost)
  {
    try
    {
      this.host = paramHttpHost;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void setHost(URI paramURI)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 170	org/apache/commons/httpclient/URI:getHost	()Ljava/lang/String;
    //   7: aload_1
    //   8: invokevirtual 171	org/apache/commons/httpclient/URI:getPort	()I
    //   11: aload_1
    //   12: invokevirtual 174	org/apache/commons/httpclient/URI:getScheme	()Ljava/lang/String;
    //   15: invokevirtual 176	org/apache/commons/httpclient/HostConfiguration:setHost	(Ljava/lang/String;ILjava/lang/String;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_3
    //   22: goto +16 -> 38
    //   25: astore_2
    //   26: new 61	java/lang/IllegalArgumentException
    //   29: dup
    //   30: aload_2
    //   31: invokevirtual 179	org/apache/commons/httpclient/URIException:toString	()Ljava/lang/String;
    //   34: invokespecial 66	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   37: athrow
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_3
    //   41: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
    //   26	38	21	finally
    //   2	18	25	org/apache/commons/httpclient/URIException
  }

  public void setLocalAddress(InetAddress paramInetAddress)
  {
    try
    {
      this.localAddress = paramInetAddress;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setParams(HostParams paramHostParams)
  {
    if (paramHostParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHostParams;
  }

  public void setProxy(String paramString, int paramInt)
  {
    try
    {
      this.proxyHost = new ProxyHost(paramString, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setProxyHost(ProxyHost paramProxyHost)
  {
    try
    {
      this.proxyHost = paramProxyHost;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String toString()
  {
    while (true)
    {
      try
      {
        StringBuffer localStringBuffer = new StringBuffer(50);
        localStringBuffer.append("HostConfiguration[");
        HttpHost localHttpHost = this.host;
        i = 0;
        if (localHttpHost != null)
        {
          localStringBuffer.append("host=");
          localStringBuffer.append(this.host);
          i = 1;
        }
        if (this.proxyHost != null)
        {
          if (i != 0)
          {
            localStringBuffer.append(", ");
            localStringBuffer.append("proxyHost=");
            localStringBuffer.append(this.proxyHost);
          }
        }
        else
        {
          if (this.localAddress != null)
          {
            if (i == 0)
              break label185;
            localStringBuffer.append(", ");
            localStringBuffer.append("localAddress=");
            localStringBuffer.append(this.localAddress);
            if (i != 0)
              localStringBuffer.append(", ");
            localStringBuffer.append("params=");
            localStringBuffer.append(this.params);
          }
          localStringBuffer.append("]");
          String str = localStringBuffer.toString();
          return str;
        }
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
      int i = 1;
      continue;
      label185: i = 1;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HostConfiguration
 * JD-Core Version:    0.6.1
 */