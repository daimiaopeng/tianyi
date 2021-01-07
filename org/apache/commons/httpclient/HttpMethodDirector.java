package org.apache.commons.httpclient;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.auth.AuthChallengeException;
import org.apache.commons.httpclient.auth.AuthChallengeParser;
import org.apache.commons.httpclient.auth.AuthChallengeProcessor;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class HttpMethodDirector
{
  private static final Log LOG = LogFactory.getLog(localClass);
  public static final String PROXY_AUTH_CHALLENGE = "Proxy-Authenticate";
  public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
  public static final String WWW_AUTH_CHALLENGE = "WWW-Authenticate";
  public static final String WWW_AUTH_RESP = "Authorization";
  static Class class$org$apache$commons$httpclient$HttpMethodDirector;
  private AuthChallengeProcessor authProcessor = null;
  private HttpConnection conn;
  private ConnectMethod connectMethod;
  private HttpConnectionManager connectionManager;
  private HostConfiguration hostConfiguration;
  private HttpClientParams params;
  private Set redirectLocations = null;
  private boolean releaseConnection = false;
  private HttpState state;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpMethodDirector == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpMethodDirector");
      class$org$apache$commons$httpclient$HttpMethodDirector = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$HttpMethodDirector;
    }
  }

  public HttpMethodDirector(HttpConnectionManager paramHttpConnectionManager, HostConfiguration paramHostConfiguration, HttpClientParams paramHttpClientParams, HttpState paramHttpState)
  {
    this.connectionManager = paramHttpConnectionManager;
    this.hostConfiguration = paramHostConfiguration;
    this.params = paramHttpClientParams;
    this.state = paramHttpState;
    this.authProcessor = new AuthChallengeProcessor(this.params);
  }

  private void applyConnectionParams(HttpMethod paramHttpMethod)
  {
    Object localObject = paramHttpMethod.getParams().getParameter("http.socket.timeout");
    if (localObject == null)
      localObject = this.conn.getParams().getParameter("http.socket.timeout");
    int i;
    if (localObject != null)
      i = ((Integer)localObject).intValue();
    else
      i = 0;
    this.conn.setSocketTimeout(i);
  }

  private void authenticate(HttpMethod paramHttpMethod)
  {
    try
    {
      if ((this.conn.isProxied()) && (!this.conn.isSecure()))
        authenticateProxy(paramHttpMethod);
      authenticateHost(paramHttpMethod);
    }
    catch (AuthenticationException localAuthenticationException)
    {
      LOG.error(localAuthenticationException.getMessage(), localAuthenticationException);
    }
  }

  private void authenticateHost(HttpMethod paramHttpMethod)
  {
    if (!cleanAuthHeaders(paramHttpMethod, "Authorization"))
      return;
    AuthState localAuthState = paramHttpMethod.getHostAuthState();
    AuthScheme localAuthScheme = localAuthState.getAuthScheme();
    if (localAuthScheme == null)
      return;
    if ((localAuthState.isAuthRequested()) || (!localAuthScheme.isConnectionBased()))
    {
      String str1 = paramHttpMethod.getParams().getVirtualHost();
      if (str1 == null)
        str1 = this.conn.getHost();
      AuthScope localAuthScope = new AuthScope(str1, this.conn.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
      if (LOG.isDebugEnabled())
      {
        Log localLog2 = LOG;
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Authenticating with ");
        localStringBuffer2.append(localAuthScope);
        localLog2.debug(localStringBuffer2.toString());
      }
      Credentials localCredentials = this.state.getCredentials(localAuthScope);
      if (localCredentials != null)
      {
        String str2 = localAuthScheme.authenticate(localCredentials, paramHttpMethod);
        if (str2 != null)
          paramHttpMethod.addRequestHeader(new Header("Authorization", str2, true));
      }
      else if (LOG.isWarnEnabled())
      {
        Log localLog1 = LOG;
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("Required credentials not available for ");
        localStringBuffer1.append(localAuthScope);
        localLog1.warn(localStringBuffer1.toString());
        if (paramHttpMethod.getHostAuthState().isPreemptive())
          LOG.warn("Preemptive authentication requested but no default credentials available");
      }
    }
  }

  private void authenticateProxy(HttpMethod paramHttpMethod)
  {
    if (!cleanAuthHeaders(paramHttpMethod, "Proxy-Authorization"))
      return;
    AuthState localAuthState = paramHttpMethod.getProxyAuthState();
    AuthScheme localAuthScheme = localAuthState.getAuthScheme();
    if (localAuthScheme == null)
      return;
    if ((localAuthState.isAuthRequested()) || (!localAuthScheme.isConnectionBased()))
    {
      AuthScope localAuthScope = new AuthScope(this.conn.getProxyHost(), this.conn.getProxyPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
      if (LOG.isDebugEnabled())
      {
        Log localLog2 = LOG;
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Authenticating with ");
        localStringBuffer2.append(localAuthScope);
        localLog2.debug(localStringBuffer2.toString());
      }
      Credentials localCredentials = this.state.getProxyCredentials(localAuthScope);
      if (localCredentials != null)
      {
        String str = localAuthScheme.authenticate(localCredentials, paramHttpMethod);
        if (str != null)
          paramHttpMethod.addRequestHeader(new Header("Proxy-Authorization", str, true));
      }
      else if (LOG.isWarnEnabled())
      {
        Log localLog1 = LOG;
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("Required proxy credentials not available for ");
        localStringBuffer1.append(localAuthScope);
        localLog1.warn(localStringBuffer1.toString());
        if (paramHttpMethod.getProxyAuthState().isPreemptive())
          LOG.warn("Preemptive authentication requested but no default proxy credentials available");
      }
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

  private boolean cleanAuthHeaders(HttpMethod paramHttpMethod, String paramString)
  {
    Header[] arrayOfHeader = paramHttpMethod.getRequestHeaders(paramString);
    int i = 0;
    boolean bool = true;
    while (i < arrayOfHeader.length)
    {
      Header localHeader = arrayOfHeader[i];
      if (localHeader.isAutogenerated())
        paramHttpMethod.removeRequestHeader(localHeader);
      else
        bool = false;
      i++;
    }
    return bool;
  }

  private boolean executeConnect()
  {
    this.connectMethod = new ConnectMethod(this.hostConfiguration);
    this.connectMethod.getParams().setDefaults(this.hostConfiguration.getParams());
    while (true)
    {
      if (!this.conn.isOpen())
        this.conn.open();
      if ((this.params.isAuthenticationPreemptive()) || (this.state.isAuthenticationPreemptive()))
      {
        LOG.debug("Preemptively sending default basic credentials");
        this.connectMethod.getProxyAuthState().setPreemptive();
        this.connectMethod.getProxyAuthState().setAuthAttempted(true);
      }
      try
      {
        authenticateProxy(this.connectMethod);
      }
      catch (AuthenticationException localAuthenticationException)
      {
        LOG.error(localAuthenticationException.getMessage(), localAuthenticationException);
      }
      applyConnectionParams(this.connectMethod);
      this.connectMethod.execute(this.state, this.conn);
      int i = this.connectMethod.getStatusCode();
      AuthState localAuthState = this.connectMethod.getProxyAuthState();
      boolean bool;
      if (i == 407)
        bool = true;
      else
        bool = false;
      localAuthState.setAuthRequested(bool);
      int j;
      if ((localAuthState.isAuthRequested()) && (processAuthenticationResponse(this.connectMethod)))
        j = 1;
      else
        j = 0;
      if (j == 0)
      {
        if ((i >= 200) && (i < 300))
        {
          this.conn.tunnelCreated();
          this.connectMethod = null;
          return true;
        }
        this.conn.close();
        return false;
      }
      if (this.connectMethod.getResponseBodyAsStream() != null)
        this.connectMethod.getResponseBodyAsStream().close();
    }
  }

  // ERROR //
  private void executeWithRetry(HttpMethod paramHttpMethod)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iinc 2 1
    //   5: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   8: invokeinterface 354 1 0
    //   13: ifeq +54 -> 67
    //   16: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   19: astore 20
    //   21: new 189	java/lang/StringBuffer
    //   24: dup
    //   25: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   28: astore 21
    //   30: aload 21
    //   32: ldc_w 356
    //   35: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   38: pop
    //   39: aload 21
    //   41: iload_2
    //   42: invokevirtual 359	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   45: pop
    //   46: aload 21
    //   48: ldc_w 361
    //   51: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   54: pop
    //   55: aload 20
    //   57: aload 21
    //   59: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   62: invokeinterface 364 2 0
    //   67: aload_0
    //   68: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   71: invokevirtual 103	org/apache/commons/httpclient/HttpConnection:getParams	()Lorg/apache/commons/httpclient/params/HttpConnectionParams;
    //   74: invokevirtual 367	org/apache/commons/httpclient/params/HttpConnectionParams:isStaleCheckingEnabled	()Z
    //   77: ifeq +11 -> 88
    //   80: aload_0
    //   81: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   84: invokevirtual 370	org/apache/commons/httpclient/HttpConnection:closeIfStale	()Z
    //   87: pop
    //   88: aload_0
    //   89: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   92: invokevirtual 296	org/apache/commons/httpclient/HttpConnection:isOpen	()Z
    //   95: ifne +45 -> 140
    //   98: aload_0
    //   99: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   102: invokevirtual 299	org/apache/commons/httpclient/HttpConnection:open	()V
    //   105: aload_0
    //   106: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   109: invokevirtual 123	org/apache/commons/httpclient/HttpConnection:isProxied	()Z
    //   112: ifeq +28 -> 140
    //   115: aload_0
    //   116: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   119: invokevirtual 126	org/apache/commons/httpclient/HttpConnection:isSecure	()Z
    //   122: ifeq +18 -> 140
    //   125: aload_1
    //   126: instanceof 279
    //   129: ifne +11 -> 140
    //   132: aload_0
    //   133: invokespecial 372	org/apache/commons/httpclient/HttpMethodDirector:executeConnect	()Z
    //   136: ifne +4 -> 140
    //   139: return
    //   140: aload_0
    //   141: aload_1
    //   142: invokespecial 317	org/apache/commons/httpclient/HttpMethodDirector:applyConnectionParams	(Lorg/apache/commons/httpclient/HttpMethod;)V
    //   145: aload_1
    //   146: aload_0
    //   147: getfield 75	org/apache/commons/httpclient/HttpMethodDirector:state	Lorg/apache/commons/httpclient/HttpState;
    //   150: aload_0
    //   151: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   154: invokeinterface 373 3 0
    //   159: pop
    //   160: return
    //   161: astore 5
    //   163: goto +286 -> 449
    //   166: astore 6
    //   168: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   171: ldc_w 375
    //   174: invokeinterface 206 2 0
    //   179: aload_0
    //   180: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   183: invokevirtual 337	org/apache/commons/httpclient/HttpConnection:close	()V
    //   186: aload_1
    //   187: instanceof 377
    //   190: ifeq +77 -> 267
    //   193: aload_1
    //   194: checkcast 377	org/apache/commons/httpclient/HttpMethodBase
    //   197: invokevirtual 381	org/apache/commons/httpclient/HttpMethodBase:getMethodRetryHandler	()Lorg/apache/commons/httpclient/MethodRetryHandler;
    //   200: astore 14
    //   202: aload 14
    //   204: ifnull +63 -> 267
    //   207: aload_0
    //   208: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   211: astore 15
    //   213: new 383	org/apache/commons/httpclient/HttpRecoverableException
    //   216: dup
    //   217: aload 6
    //   219: invokevirtual 384	java/io/IOException:getMessage	()Ljava/lang/String;
    //   222: invokespecial 385	org/apache/commons/httpclient/HttpRecoverableException:<init>	(Ljava/lang/String;)V
    //   225: astore 16
    //   227: aload_1
    //   228: invokeinterface 388 1 0
    //   233: istore 17
    //   235: aload 14
    //   237: aload_1
    //   238: aload 15
    //   240: aload 16
    //   242: iload_2
    //   243: iload 17
    //   245: invokeinterface 394 6 0
    //   250: ifne +17 -> 267
    //   253: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   256: ldc_w 396
    //   259: invokeinterface 206 2 0
    //   264: aload 6
    //   266: athrow
    //   267: aload_1
    //   268: invokeinterface 88 1 0
    //   273: ldc_w 398
    //   276: invokevirtual 96	org/apache/commons/httpclient/params/HttpMethodParams:getParameter	(Ljava/lang/String;)Ljava/lang/Object;
    //   279: checkcast 400	org/apache/commons/httpclient/HttpMethodRetryHandler
    //   282: astore 7
    //   284: aload 7
    //   286: ifnonnull +12 -> 298
    //   289: new 402	org/apache/commons/httpclient/DefaultHttpMethodRetryHandler
    //   292: dup
    //   293: invokespecial 403	org/apache/commons/httpclient/DefaultHttpMethodRetryHandler:<init>	()V
    //   296: astore 7
    //   298: aload 7
    //   300: aload_1
    //   301: aload 6
    //   303: iload_2
    //   304: invokeinterface 406 4 0
    //   309: ifne +17 -> 326
    //   312: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   315: ldc_w 396
    //   318: invokeinterface 206 2 0
    //   323: aload 6
    //   325: athrow
    //   326: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   329: invokeinterface 409 1 0
    //   334: ifeq +72 -> 406
    //   337: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   340: astore 8
    //   342: new 189	java/lang/StringBuffer
    //   345: dup
    //   346: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   349: astore 9
    //   351: aload 9
    //   353: ldc_w 411
    //   356: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   359: pop
    //   360: aload 9
    //   362: aload 6
    //   364: invokevirtual 415	java/lang/Object:getClass	()Ljava/lang/Class;
    //   367: invokevirtual 418	java/lang/Class:getName	()Ljava/lang/String;
    //   370: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   373: pop
    //   374: aload 9
    //   376: ldc_w 420
    //   379: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   382: pop
    //   383: aload 9
    //   385: aload 6
    //   387: invokevirtual 384	java/io/IOException:getMessage	()Ljava/lang/String;
    //   390: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   393: pop
    //   394: aload 8
    //   396: aload 9
    //   398: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   401: invokeinterface 423 2 0
    //   406: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   409: invokeinterface 187 1 0
    //   414: ifeq +18 -> 432
    //   417: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   420: aload 6
    //   422: invokevirtual 384	java/io/IOException:getMessage	()Ljava/lang/String;
    //   425: aload 6
    //   427: invokeinterface 425 3 0
    //   432: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   435: ldc_w 427
    //   438: invokeinterface 423 2 0
    //   443: goto -441 -> 2
    //   446: astore_3
    //   447: aload_3
    //   448: athrow
    //   449: aload_0
    //   450: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   453: invokevirtual 296	org/apache/commons/httpclient/HttpConnection:isOpen	()Z
    //   456: ifeq +21 -> 477
    //   459: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   462: ldc_w 375
    //   465: invokeinterface 206 2 0
    //   470: aload_0
    //   471: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   474: invokevirtual 337	org/apache/commons/httpclient/HttpConnection:close	()V
    //   477: aload_0
    //   478: iconst_1
    //   479: putfield 63	org/apache/commons/httpclient/HttpMethodDirector:releaseConnection	Z
    //   482: aload 5
    //   484: athrow
    //   485: astore 4
    //   487: aload_0
    //   488: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   491: invokevirtual 296	org/apache/commons/httpclient/HttpConnection:isOpen	()Z
    //   494: ifeq +21 -> 515
    //   497: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   500: ldc_w 375
    //   503: invokeinterface 206 2 0
    //   508: aload_0
    //   509: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   512: invokevirtual 337	org/apache/commons/httpclient/HttpConnection:close	()V
    //   515: aload_0
    //   516: iconst_1
    //   517: putfield 63	org/apache/commons/httpclient/HttpMethodDirector:releaseConnection	Z
    //   520: aload 4
    //   522: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	160	161	java/lang/RuntimeException
    //   168	449	161	java/lang/RuntimeException
    //   5	160	166	java/io/IOException
    //   5	160	446	org/apache/commons/httpclient/HttpException
    //   168	449	485	java/io/IOException
  }

  private void fakeResponse(HttpMethod paramHttpMethod)
  {
    LOG.debug("CONNECT failed, fake the response for the original method");
    if ((paramHttpMethod instanceof HttpMethodBase))
    {
      ((HttpMethodBase)paramHttpMethod).fakeResponse(this.connectMethod.getStatusLine(), this.connectMethod.getResponseHeaderGroup(), this.connectMethod.getResponseBodyAsStream());
      paramHttpMethod.getProxyAuthState().setAuthScheme(this.connectMethod.getProxyAuthState().getAuthScheme());
      this.connectMethod = null;
    }
    else
    {
      this.releaseConnection = true;
      LOG.warn("Unable to fake response on method as it is not derived from HttpMethodBase.");
    }
  }

  private boolean isAuthenticationNeeded(HttpMethod paramHttpMethod)
  {
    AuthState localAuthState1 = paramHttpMethod.getHostAuthState();
    boolean bool1;
    if (paramHttpMethod.getStatusCode() == 401)
      bool1 = true;
    else
      bool1 = false;
    localAuthState1.setAuthRequested(bool1);
    AuthState localAuthState2 = paramHttpMethod.getProxyAuthState();
    boolean bool2;
    if (paramHttpMethod.getStatusCode() == 407)
      bool2 = true;
    else
      bool2 = false;
    localAuthState2.setAuthRequested(bool2);
    if ((!paramHttpMethod.getHostAuthState().isAuthRequested()) && (!paramHttpMethod.getProxyAuthState().isAuthRequested()))
      return false;
    LOG.debug("Authorization required");
    if (paramHttpMethod.getDoAuthentication())
      return true;
    LOG.info("Authentication requested but doAuthentication is disabled");
    return false;
  }

  private boolean isRedirectNeeded(HttpMethod paramHttpMethod)
  {
    int i = paramHttpMethod.getStatusCode();
    if (i != 307)
      switch (i)
      {
      default:
        return false;
      case 301:
      case 302:
      case 303:
      }
    LOG.debug("Redirect required");
    return paramHttpMethod.getFollowRedirects();
  }

  private boolean processAuthenticationResponse(HttpMethod paramHttpMethod)
  {
    LOG.trace("enter HttpMethodBase.processAuthenticationResponse(HttpState, HttpConnection)");
    try
    {
      int i = paramHttpMethod.getStatusCode();
      if (i != 401)
      {
        if (i != 407)
          return false;
        return processProxyAuthChallenge(paramHttpMethod);
      }
      boolean bool = processWWWAuthChallenge(paramHttpMethod);
      return bool;
    }
    catch (Exception localException)
    {
      if (LOG.isErrorEnabled())
        LOG.error(localException.getMessage(), localException);
    }
    return false;
  }

  private boolean processProxyAuthChallenge(HttpMethod paramHttpMethod)
  {
    AuthState localAuthState = paramHttpMethod.getProxyAuthState();
    Map localMap = AuthChallengeParser.parseChallenges(paramHttpMethod.getResponseHeaders("Proxy-Authenticate"));
    if (localMap.isEmpty())
    {
      LOG.debug("Proxy authentication challenge(s) not found");
      return false;
    }
    AuthScheme localAuthScheme;
    try
    {
      localAuthScheme = this.authProcessor.processChallenge(localAuthState, localMap);
    }
    catch (AuthChallengeException localAuthChallengeException)
    {
      if (LOG.isWarnEnabled())
        LOG.warn(localAuthChallengeException.getMessage());
      localAuthScheme = null;
    }
    if (localAuthScheme == null)
      return false;
    AuthScope localAuthScope = new AuthScope(this.conn.getProxyHost(), this.conn.getProxyPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
    if (LOG.isDebugEnabled())
    {
      Log localLog3 = LOG;
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Proxy authentication scope: ");
      localStringBuffer3.append(localAuthScope);
      localLog3.debug(localStringBuffer3.toString());
    }
    if ((localAuthState.isAuthAttempted()) && (localAuthScheme.isComplete()))
    {
      if (promptForProxyCredentials(localAuthScheme, paramHttpMethod.getParams(), localAuthScope) == null)
      {
        if (LOG.isInfoEnabled())
        {
          Log localLog2 = LOG;
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.append("Failure authenticating with ");
          localStringBuffer2.append(localAuthScope);
          localLog2.info(localStringBuffer2.toString());
        }
        return false;
      }
      return true;
    }
    localAuthState.setAuthAttempted(true);
    Credentials localCredentials = this.state.getProxyCredentials(localAuthScope);
    if (localCredentials == null)
      localCredentials = promptForProxyCredentials(localAuthScheme, paramHttpMethod.getParams(), localAuthScope);
    if (localCredentials == null)
    {
      if (LOG.isInfoEnabled())
      {
        Log localLog1 = LOG;
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("No credentials available for ");
        localStringBuffer1.append(localAuthScope);
        localLog1.info(localStringBuffer1.toString());
      }
      return false;
    }
    return true;
  }

  // ERROR //
  private boolean processRedirectResponse(HttpMethod paramHttpMethod)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 520
    //   4: invokeinterface 524 2 0
    //   9: astore_2
    //   10: aload_2
    //   11: ifnonnull +61 -> 72
    //   14: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   17: astore 32
    //   19: new 189	java/lang/StringBuffer
    //   22: dup
    //   23: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   26: astore 33
    //   28: aload 33
    //   30: ldc_w 526
    //   33: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   36: pop
    //   37: aload 33
    //   39: aload_1
    //   40: invokeinterface 449 1 0
    //   45: invokevirtual 359	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   48: pop
    //   49: aload 33
    //   51: ldc_w 528
    //   54: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   57: pop
    //   58: aload 32
    //   60: aload 33
    //   62: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   65: invokeinterface 530 2 0
    //   70: iconst_0
    //   71: ireturn
    //   72: aload_2
    //   73: invokevirtual 533	org/apache/commons/httpclient/Header:getValue	()Ljava/lang/String;
    //   76: astore_3
    //   77: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   80: invokeinterface 187 1 0
    //   85: ifeq +54 -> 139
    //   88: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   91: astore 27
    //   93: new 189	java/lang/StringBuffer
    //   96: dup
    //   97: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   100: astore 28
    //   102: aload 28
    //   104: ldc_w 535
    //   107: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   110: pop
    //   111: aload 28
    //   113: aload_3
    //   114: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   117: pop
    //   118: aload 28
    //   120: ldc_w 537
    //   123: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   126: pop
    //   127: aload 27
    //   129: aload 28
    //   131: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   134: invokeinterface 206 2 0
    //   139: new 539	org/apache/commons/httpclient/URI
    //   142: dup
    //   143: aload_0
    //   144: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   147: invokevirtual 543	org/apache/commons/httpclient/HttpConnection:getProtocol	()Lorg/apache/commons/httpclient/protocol/Protocol;
    //   150: invokevirtual 548	org/apache/commons/httpclient/protocol/Protocol:getScheme	()Ljava/lang/String;
    //   153: aconst_null
    //   154: aload_0
    //   155: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   158: invokevirtual 170	org/apache/commons/httpclient/HttpConnection:getHost	()Ljava/lang/String;
    //   161: aload_0
    //   162: getfield 98	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   165: invokevirtual 175	org/apache/commons/httpclient/HttpConnection:getPort	()I
    //   168: aload_1
    //   169: invokeinterface 551 1 0
    //   174: invokespecial 554	org/apache/commons/httpclient/URI:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   177: astore 4
    //   179: new 539	org/apache/commons/httpclient/URI
    //   182: dup
    //   183: aload_3
    //   184: iconst_1
    //   185: aload_1
    //   186: invokeinterface 88 1 0
    //   191: invokevirtual 557	org/apache/commons/httpclient/params/HttpMethodParams:getUriCharset	()Ljava/lang/String;
    //   194: invokespecial 560	org/apache/commons/httpclient/URI:<init>	(Ljava/lang/String;ZLjava/lang/String;)V
    //   197: astore 5
    //   199: aload 5
    //   201: invokevirtual 563	org/apache/commons/httpclient/URI:isRelativeURI	()Z
    //   204: ifeq +96 -> 300
    //   207: aload_0
    //   208: getfield 73	org/apache/commons/httpclient/HttpMethodDirector:params	Lorg/apache/commons/httpclient/params/HttpClientParams;
    //   211: ldc_w 565
    //   214: invokevirtual 569	org/apache/commons/httpclient/params/HttpClientParams:isParameterTrue	(Ljava/lang/String;)Z
    //   217: ifeq +56 -> 273
    //   220: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   223: astore 22
    //   225: new 189	java/lang/StringBuffer
    //   228: dup
    //   229: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   232: astore 23
    //   234: aload 23
    //   236: ldc_w 571
    //   239: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   242: pop
    //   243: aload 23
    //   245: aload_3
    //   246: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   249: pop
    //   250: aload 23
    //   252: ldc_w 573
    //   255: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   258: pop
    //   259: aload 22
    //   261: aload 23
    //   263: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   266: invokeinterface 232 2 0
    //   271: iconst_0
    //   272: ireturn
    //   273: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   276: ldc_w 575
    //   279: invokeinterface 206 2 0
    //   284: new 539	org/apache/commons/httpclient/URI
    //   287: dup
    //   288: aload 4
    //   290: aload 5
    //   292: invokespecial 578	org/apache/commons/httpclient/URI:<init>	(Lorg/apache/commons/httpclient/URI;Lorg/apache/commons/httpclient/URI;)V
    //   295: astore 10
    //   297: goto +20 -> 317
    //   300: aload_1
    //   301: invokeinterface 88 1 0
    //   306: aload_0
    //   307: getfield 73	org/apache/commons/httpclient/HttpMethodDirector:params	Lorg/apache/commons/httpclient/params/HttpClientParams;
    //   310: invokevirtual 293	org/apache/commons/httpclient/params/HttpMethodParams:setDefaults	(Lorg/apache/commons/httpclient/params/HttpParams;)V
    //   313: aload 5
    //   315: astore 10
    //   317: aload_1
    //   318: aload 10
    //   320: invokeinterface 582 2 0
    //   325: aload_0
    //   326: getfield 71	org/apache/commons/httpclient/HttpMethodDirector:hostConfiguration	Lorg/apache/commons/httpclient/HostConfiguration;
    //   329: aload 10
    //   331: invokevirtual 585	org/apache/commons/httpclient/HostConfiguration:setHost	(Lorg/apache/commons/httpclient/URI;)V
    //   334: aload_0
    //   335: getfield 73	org/apache/commons/httpclient/HttpMethodDirector:params	Lorg/apache/commons/httpclient/params/HttpClientParams;
    //   338: ldc_w 587
    //   341: invokevirtual 590	org/apache/commons/httpclient/params/HttpClientParams:isParameterFalse	(Ljava/lang/String;)Z
    //   344: ifeq +111 -> 455
    //   347: aload_0
    //   348: getfield 67	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   351: ifnonnull +14 -> 365
    //   354: aload_0
    //   355: new 592	java/util/HashSet
    //   358: dup
    //   359: invokespecial 593	java/util/HashSet:<init>	()V
    //   362: putfield 67	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   365: aload_0
    //   366: getfield 67	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   369: aload 4
    //   371: invokeinterface 599 2 0
    //   376: pop
    //   377: aload 10
    //   379: invokevirtual 602	org/apache/commons/httpclient/URI:hasQuery	()Z
    //   382: ifeq +9 -> 391
    //   385: aload 10
    //   387: aconst_null
    //   388: invokevirtual 605	org/apache/commons/httpclient/URI:setQuery	(Ljava/lang/String;)V
    //   391: aload_0
    //   392: getfield 67	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   395: aload 10
    //   397: invokeinterface 608 2 0
    //   402: ifeq +53 -> 455
    //   405: new 189	java/lang/StringBuffer
    //   408: dup
    //   409: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   412: astore 18
    //   414: aload 18
    //   416: ldc_w 610
    //   419: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   422: pop
    //   423: aload 18
    //   425: aload 10
    //   427: invokevirtual 199	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   430: pop
    //   431: aload 18
    //   433: ldc_w 537
    //   436: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   439: pop
    //   440: new 612	org/apache/commons/httpclient/CircularRedirectException
    //   443: dup
    //   444: aload 18
    //   446: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   449: invokespecial 613	org/apache/commons/httpclient/CircularRedirectException:<init>	(Ljava/lang/String;)V
    //   452: athrow
    //   453: iconst_0
    //   454: ireturn
    //   455: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   458: invokeinterface 187 1 0
    //   463: ifeq +69 -> 532
    //   466: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   469: astore 11
    //   471: new 189	java/lang/StringBuffer
    //   474: dup
    //   475: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   478: astore 12
    //   480: aload 12
    //   482: ldc_w 615
    //   485: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   488: pop
    //   489: aload 12
    //   491: aload 4
    //   493: invokevirtual 618	org/apache/commons/httpclient/URI:getEscapedURI	()Ljava/lang/String;
    //   496: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   499: pop
    //   500: aload 12
    //   502: ldc_w 620
    //   505: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   508: pop
    //   509: aload 12
    //   511: aload 10
    //   513: invokevirtual 618	org/apache/commons/httpclient/URI:getEscapedURI	()Ljava/lang/String;
    //   516: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   519: pop
    //   520: aload 11
    //   522: aload 12
    //   524: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   527: invokeinterface 206 2 0
    //   532: aload_1
    //   533: invokeinterface 150 1 0
    //   538: invokevirtual 623	org/apache/commons/httpclient/auth/AuthState:invalidate	()V
    //   541: iconst_1
    //   542: ireturn
    //   543: astore 6
    //   545: new 189	java/lang/StringBuffer
    //   548: dup
    //   549: invokespecial 190	java/lang/StringBuffer:<init>	()V
    //   552: astore 7
    //   554: aload 7
    //   556: ldc_w 625
    //   559: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   562: pop
    //   563: aload 7
    //   565: aload_3
    //   566: invokevirtual 196	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   569: pop
    //   570: new 627	org/apache/commons/httpclient/InvalidRedirectLocationException
    //   573: dup
    //   574: aload 7
    //   576: invokevirtual 202	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   579: aload_3
    //   580: aload 6
    //   582: invokespecial 630	org/apache/commons/httpclient/InvalidRedirectLocationException:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   585: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   377	391	453	org/apache/commons/httpclient/URIException
    //   139	334	543	org/apache/commons/httpclient/URIException
  }

  private boolean processWWWAuthChallenge(HttpMethod paramHttpMethod)
  {
    AuthState localAuthState = paramHttpMethod.getHostAuthState();
    Map localMap = AuthChallengeParser.parseChallenges(paramHttpMethod.getResponseHeaders("WWW-Authenticate"));
    if (localMap.isEmpty())
    {
      LOG.debug("Authentication challenge(s) not found");
      return false;
    }
    AuthScheme localAuthScheme;
    try
    {
      localAuthScheme = this.authProcessor.processChallenge(localAuthState, localMap);
    }
    catch (AuthChallengeException localAuthChallengeException)
    {
      if (LOG.isWarnEnabled())
        LOG.warn(localAuthChallengeException.getMessage());
      localAuthScheme = null;
    }
    if (localAuthScheme == null)
      return false;
    String str = paramHttpMethod.getParams().getVirtualHost();
    if (str == null)
      str = this.conn.getHost();
    AuthScope localAuthScope = new AuthScope(str, this.conn.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
    if (LOG.isDebugEnabled())
    {
      Log localLog3 = LOG;
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Authentication scope: ");
      localStringBuffer3.append(localAuthScope);
      localLog3.debug(localStringBuffer3.toString());
    }
    if ((localAuthState.isAuthAttempted()) && (localAuthScheme.isComplete()))
    {
      if (promptForCredentials(localAuthScheme, paramHttpMethod.getParams(), localAuthScope) == null)
      {
        if (LOG.isInfoEnabled())
        {
          Log localLog2 = LOG;
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.append("Failure authenticating with ");
          localStringBuffer2.append(localAuthScope);
          localLog2.info(localStringBuffer2.toString());
        }
        return false;
      }
      return true;
    }
    localAuthState.setAuthAttempted(true);
    Credentials localCredentials = this.state.getCredentials(localAuthScope);
    if (localCredentials == null)
      localCredentials = promptForCredentials(localAuthScheme, paramHttpMethod.getParams(), localAuthScope);
    if (localCredentials == null)
    {
      if (LOG.isInfoEnabled())
      {
        Log localLog1 = LOG;
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("No credentials available for ");
        localStringBuffer1.append(localAuthScope);
        localLog1.info(localStringBuffer1.toString());
      }
      return false;
    }
    return true;
  }

  private Credentials promptForCredentials(AuthScheme paramAuthScheme, HttpParams paramHttpParams, AuthScope paramAuthScope)
  {
    LOG.debug("Credentials required");
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpParams.getParameter("http.authentication.credential-provider");
    Object localObject = null;
    if (localCredentialsProvider != null)
    {
      try
      {
        Credentials localCredentials = localCredentialsProvider.getCredentials(paramAuthScheme, paramAuthScope.getHost(), paramAuthScope.getPort(), false);
        localObject = localCredentials;
      }
      catch (CredentialsNotAvailableException localCredentialsNotAvailableException)
      {
        LOG.warn(localCredentialsNotAvailableException.getMessage());
      }
      if (localObject != null)
      {
        this.state.setCredentials(paramAuthScope, localObject);
        if (LOG.isDebugEnabled())
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append(paramAuthScope);
          localStringBuffer.append(" new credentials given");
          localLog.debug(localStringBuffer.toString());
        }
      }
    }
    else
    {
      LOG.debug("Credentials provider not available");
    }
    return localObject;
  }

  private Credentials promptForProxyCredentials(AuthScheme paramAuthScheme, HttpParams paramHttpParams, AuthScope paramAuthScope)
  {
    LOG.debug("Proxy credentials required");
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpParams.getParameter("http.authentication.credential-provider");
    Object localObject = null;
    if (localCredentialsProvider != null)
    {
      try
      {
        Credentials localCredentials = localCredentialsProvider.getCredentials(paramAuthScheme, paramAuthScope.getHost(), paramAuthScope.getPort(), true);
        localObject = localCredentials;
      }
      catch (CredentialsNotAvailableException localCredentialsNotAvailableException)
      {
        LOG.warn(localCredentialsNotAvailableException.getMessage());
      }
      if (localObject != null)
      {
        this.state.setProxyCredentials(paramAuthScope, localObject);
        if (LOG.isDebugEnabled())
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append(paramAuthScope);
          localStringBuffer.append(" new credentials given");
          localLog.debug(localStringBuffer.toString());
        }
      }
    }
    else
    {
      LOG.debug("Proxy credentials provider not available");
    }
    return localObject;
  }

  public void executeMethod(HttpMethod paramHttpMethod)
  {
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("Method may not be null");
    this.hostConfiguration.getParams().setDefaults(this.params);
    paramHttpMethod.getParams().setDefaults(this.hostConfiguration.getParams());
    Collection localCollection = (Collection)this.hostConfiguration.getParams().getParameter("http.default-headers");
    if (localCollection != null)
    {
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
        paramHttpMethod.addRequestHeader((Header)localIterator.next());
    }
    while (true)
    {
      int j;
      int n;
      try
      {
        int i = this.params.getIntParameter("http.protocol.max-redirects", 100);
        j = 0;
        if ((this.conn != null) && (!this.hostConfiguration.hostEquals(this.conn)))
        {
          this.conn.setLocked(false);
          this.conn.releaseConnection();
          this.conn = null;
        }
        if (this.conn == null)
        {
          this.conn = this.connectionManager.getConnectionWithTimeout(this.hostConfiguration, this.params.getConnectionManagerTimeout());
          this.conn.setLocked(true);
          if ((this.params.isAuthenticationPreemptive()) || (this.state.isAuthenticationPreemptive()))
          {
            LOG.debug("Preemptively sending default basic credentials");
            paramHttpMethod.getHostAuthState().setPreemptive();
            paramHttpMethod.getHostAuthState().setAuthAttempted(true);
            if ((this.conn.isProxied()) && (!this.conn.isSecure()))
            {
              paramHttpMethod.getProxyAuthState().setPreemptive();
              paramHttpMethod.getProxyAuthState().setAuthAttempted(true);
            }
          }
        }
        authenticate(paramHttpMethod);
        executeWithRetry(paramHttpMethod);
        if (this.connectMethod != null)
        {
          fakeResponse(paramHttpMethod);
        }
        else
        {
          if ((!isRedirectNeeded(paramHttpMethod)) || (!processRedirectResponse(paramHttpMethod)))
            break label640;
          n = j + 1;
          if (n >= i)
          {
            LOG.error("Narrowly avoided an infinite loop in execute");
            StringBuffer localStringBuffer2 = new StringBuffer();
            localStringBuffer2.append("Maximum redirects (");
            localStringBuffer2.append(i);
            localStringBuffer2.append(") exceeded");
            throw new RedirectException(localStringBuffer2.toString());
          }
          if (!LOG.isDebugEnabled())
            break label630;
          Log localLog = LOG;
          StringBuffer localStringBuffer1 = new StringBuffer();
          localStringBuffer1.append("Execute redirect ");
          localStringBuffer1.append(n);
          localStringBuffer1.append(" of ");
          localStringBuffer1.append(i);
          localLog.debug(localStringBuffer1.toString());
          break label630;
          if ((isAuthenticationNeeded(paramHttpMethod)) && (processAuthenticationResponse(paramHttpMethod)))
          {
            LOG.debug("Retry authentication");
            m = 1;
          }
          if (m != 0)
            continue;
        }
        return;
        if (paramHttpMethod.getResponseBodyAsStream() != null)
          paramHttpMethod.getResponseBodyAsStream().close();
        j = k;
        continue;
      }
      finally
      {
        if (this.conn != null)
          this.conn.setLocked(false);
        if (((this.releaseConnection) || (paramHttpMethod.getResponseBodyAsStream() == null)) && (this.conn != null))
          this.conn.releaseConnection();
      }
      label630: int k = n;
      int m = 1;
      continue;
      label640: k = j;
      m = 0;
    }
  }

  public HttpConnectionManager getConnectionManager()
  {
    return this.connectionManager;
  }

  public HostConfiguration getHostConfiguration()
  {
    return this.hostConfiguration;
  }

  public HttpParams getParams()
  {
    return this.params;
  }

  public HttpState getState()
  {
    return this.state;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodDirector
 * JD-Core Version:    0.6.1
 */