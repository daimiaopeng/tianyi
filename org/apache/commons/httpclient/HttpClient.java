package org.apache.commons.httpclient;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

public class HttpClient
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$HttpClient;
  private HostConfiguration hostConfiguration = new HostConfiguration();
  private HttpConnectionManager httpConnectionManager;
  private HttpClientParams params = null;
  private HttpState state = new HttpState();

  // ERROR //
  static
  {
    // Byte code:
    //   0: getstatic 22	org/apache/commons/httpclient/HttpClient:class$org$apache$commons$httpclient$HttpClient	Ljava/lang/Class;
    //   3: ifnonnull +16 -> 19
    //   6: ldc 24
    //   8: invokestatic 28	org/apache/commons/httpclient/HttpClient:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   11: astore_0
    //   12: aload_0
    //   13: putstatic 22	org/apache/commons/httpclient/HttpClient:class$org$apache$commons$httpclient$HttpClient	Ljava/lang/Class;
    //   16: goto +7 -> 23
    //   19: getstatic 22	org/apache/commons/httpclient/HttpClient:class$org$apache$commons$httpclient$HttpClient	Ljava/lang/Class;
    //   22: astore_0
    //   23: aload_0
    //   24: invokestatic 34	org/apache/commons/logging/LogFactory:getLog	(Ljava/lang/Class;)Lorg/apache/commons/logging/Log;
    //   27: putstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   30: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   33: invokeinterface 42 1 0
    //   38: ifeq +371 -> 409
    //   41: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   44: astore_1
    //   45: new 44	java/lang/StringBuffer
    //   48: dup
    //   49: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   52: astore_2
    //   53: aload_2
    //   54: ldc 49
    //   56: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   59: pop
    //   60: aload_2
    //   61: ldc 55
    //   63: invokestatic 61	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   66: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   69: pop
    //   70: aload_1
    //   71: aload_2
    //   72: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   75: invokeinterface 69 2 0
    //   80: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   83: astore 5
    //   85: new 44	java/lang/StringBuffer
    //   88: dup
    //   89: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   92: astore 6
    //   94: aload 6
    //   96: ldc 71
    //   98: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   101: pop
    //   102: aload 6
    //   104: ldc 73
    //   106: invokestatic 61	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   109: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   112: pop
    //   113: aload 5
    //   115: aload 6
    //   117: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   120: invokeinterface 69 2 0
    //   125: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   128: astore 9
    //   130: new 44	java/lang/StringBuffer
    //   133: dup
    //   134: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   137: astore 10
    //   139: aload 10
    //   141: ldc 75
    //   143: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   146: pop
    //   147: aload 10
    //   149: ldc 77
    //   151: invokestatic 61	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   154: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   157: pop
    //   158: aload 9
    //   160: aload 10
    //   162: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   165: invokeinterface 69 2 0
    //   170: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   173: astore 13
    //   175: new 44	java/lang/StringBuffer
    //   178: dup
    //   179: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   182: astore 14
    //   184: aload 14
    //   186: ldc 79
    //   188: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   191: pop
    //   192: aload 14
    //   194: ldc 81
    //   196: invokestatic 61	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   199: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   202: pop
    //   203: aload 13
    //   205: aload 14
    //   207: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   210: invokeinterface 69 2 0
    //   215: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   218: astore 17
    //   220: new 44	java/lang/StringBuffer
    //   223: dup
    //   224: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   227: astore 18
    //   229: aload 18
    //   231: ldc 83
    //   233: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   236: pop
    //   237: aload 18
    //   239: ldc 85
    //   241: invokestatic 61	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   244: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   247: pop
    //   248: aload 17
    //   250: aload 18
    //   252: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   255: invokeinterface 69 2 0
    //   260: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   263: astore 21
    //   265: new 44	java/lang/StringBuffer
    //   268: dup
    //   269: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   272: astore 22
    //   274: aload 22
    //   276: ldc 87
    //   278: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   281: pop
    //   282: aload 22
    //   284: ldc 89
    //   286: invokestatic 61	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   289: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   292: pop
    //   293: aload 21
    //   295: aload 22
    //   297: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   300: invokeinterface 69 2 0
    //   305: invokestatic 95	java/security/Security:getProviders	()[Ljava/security/Provider;
    //   308: astore 25
    //   310: iconst_0
    //   311: istore 26
    //   313: iload 26
    //   315: aload 25
    //   317: arraylength
    //   318: if_icmpge +91 -> 409
    //   321: aload 25
    //   323: iload 26
    //   325: aaload
    //   326: astore 27
    //   328: getstatic 36	org/apache/commons/httpclient/HttpClient:LOG	Lorg/apache/commons/logging/Log;
    //   331: astore 28
    //   333: new 44	java/lang/StringBuffer
    //   336: dup
    //   337: invokespecial 47	java/lang/StringBuffer:<init>	()V
    //   340: astore 29
    //   342: aload 29
    //   344: aload 27
    //   346: invokevirtual 100	java/security/Provider:getName	()Ljava/lang/String;
    //   349: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   352: pop
    //   353: aload 29
    //   355: ldc 102
    //   357: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   360: pop
    //   361: aload 29
    //   363: aload 27
    //   365: invokevirtual 106	java/security/Provider:getVersion	()D
    //   368: invokevirtual 109	java/lang/StringBuffer:append	(D)Ljava/lang/StringBuffer;
    //   371: pop
    //   372: aload 29
    //   374: ldc 111
    //   376: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   379: pop
    //   380: aload 29
    //   382: aload 27
    //   384: invokevirtual 114	java/security/Provider:getInfo	()Ljava/lang/String;
    //   387: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   390: pop
    //   391: aload 28
    //   393: aload 29
    //   395: invokevirtual 65	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   398: invokeinterface 69 2 0
    //   403: iinc 26 1
    //   406: goto -93 -> 313
    //   409: return
    //
    // Exception table:
    //   from	to	target	type
    //   41	403	409	java/lang/SecurityException
  }

  public HttpClient()
  {
    this(new HttpClientParams());
  }

  public HttpClient(HttpConnectionManager paramHttpConnectionManager)
  {
    this(new HttpClientParams(), paramHttpConnectionManager);
  }

  public HttpClient(HttpClientParams paramHttpClientParams)
  {
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Params may not be null");
    this.params = paramHttpClientParams;
    this.httpConnectionManager = null;
    Class localClass = paramHttpClientParams.getConnectionManagerClass();
    if (localClass != null)
      try
      {
        this.httpConnectionManager = ((HttpConnectionManager)localClass.newInstance());
      }
      catch (Exception localException)
      {
        LOG.warn("Error instantiating connection manager class, defaulting to SimpleHttpConnectionManager", localException);
      }
    if (this.httpConnectionManager == null)
      this.httpConnectionManager = new SimpleHttpConnectionManager();
    if (this.httpConnectionManager != null)
      this.httpConnectionManager.getParams().setDefaults(this.params);
  }

  public HttpClient(HttpClientParams paramHttpClientParams, HttpConnectionManager paramHttpConnectionManager)
  {
    if (paramHttpConnectionManager == null)
      throw new IllegalArgumentException("httpConnectionManager cannot be null");
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Params may not be null");
    this.params = paramHttpClientParams;
    this.httpConnectionManager = paramHttpConnectionManager;
    this.httpConnectionManager.getParams().setDefaults(this.params);
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

  public int executeMethod(HostConfiguration paramHostConfiguration, HttpMethod paramHttpMethod)
  {
    LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod)");
    return executeMethod(paramHostConfiguration, paramHttpMethod, null);
  }

  public int executeMethod(HostConfiguration paramHostConfiguration, HttpMethod paramHttpMethod, HttpState paramHttpState)
  {
    LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod,HttpState)");
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("HttpMethod parameter may not be null");
    HostConfiguration localHostConfiguration = getHostConfiguration();
    if (paramHostConfiguration == null)
      paramHostConfiguration = localHostConfiguration;
    URI localURI = paramHttpMethod.getURI();
    if ((paramHostConfiguration == localHostConfiguration) || (localURI.isAbsoluteURI()))
    {
      paramHostConfiguration = (HostConfiguration)paramHostConfiguration.clone();
      if (localURI.isAbsoluteURI())
        paramHostConfiguration.setHost(localURI);
    }
    HttpConnectionManager localHttpConnectionManager = getHttpConnectionManager();
    HttpClientParams localHttpClientParams = this.params;
    if (paramHttpState == null)
      paramHttpState = getState();
    new HttpMethodDirector(localHttpConnectionManager, paramHostConfiguration, localHttpClientParams, paramHttpState).executeMethod(paramHttpMethod);
    return paramHttpMethod.getStatusCode();
  }

  public int executeMethod(HttpMethod paramHttpMethod)
  {
    LOG.trace("enter HttpClient.executeMethod(HttpMethod)");
    return executeMethod(null, paramHttpMethod, null);
  }

  public String getHost()
  {
    return this.hostConfiguration.getHost();
  }

  public HostConfiguration getHostConfiguration()
  {
    try
    {
      HostConfiguration localHostConfiguration = this.hostConfiguration;
      return localHostConfiguration;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpConnectionManager getHttpConnectionManager()
  {
    try
    {
      HttpConnectionManager localHttpConnectionManager = this.httpConnectionManager;
      return localHttpConnectionManager;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpClientParams getParams()
  {
    return this.params;
  }

  public int getPort()
  {
    return this.hostConfiguration.getPort();
  }

  public HttpState getState()
  {
    try
    {
      HttpState localHttpState = this.state;
      return localHttpState;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isStrictMode()
  {
    return false;
  }

  public void setConnectionTimeout(int paramInt)
  {
    try
    {
      this.httpConnectionManager.getParams().setConnectionTimeout(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHostConfiguration(HostConfiguration paramHostConfiguration)
  {
    try
    {
      this.hostConfiguration = paramHostConfiguration;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHttpConnectionFactoryTimeout(long paramLong)
  {
    try
    {
      this.params.setConnectionManagerTimeout(paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHttpConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    try
    {
      this.httpConnectionManager = paramHttpConnectionManager;
      if (this.httpConnectionManager != null)
        this.httpConnectionManager.getParams().setDefaults(this.params);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setParams(HttpClientParams paramHttpClientParams)
  {
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpClientParams;
  }

  public void setState(HttpState paramHttpState)
  {
    try
    {
      this.state = paramHttpState;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setStrictMode(boolean paramBoolean)
  {
    if (paramBoolean);
    try
    {
      this.params.makeStrict();
      break label23;
      this.params.makeLenient();
      label23: return;
      Object localObject1;
      throw localObject1;
    }
    finally
    {
    }
  }

  public void setTimeout(int paramInt)
  {
    try
    {
      this.params.setSoTimeout(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpClient
 * JD-Core Version:    0.6.1
 */