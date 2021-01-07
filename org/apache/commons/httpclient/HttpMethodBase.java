package org.apache.commons.httpclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.cookie.CookieVersionSupport;
import org.apache.commons.httpclient.cookie.MalformedCookieException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class HttpMethodBase
  implements HttpMethod
{
  private static final int DEFAULT_INITIAL_BUFFER_SIZE = 4096;
  private static final Log LOG = LogFactory.getLog(localClass);
  private static final int RESPONSE_WAIT_TIME_MS = 3000;
  static Class class$org$apache$commons$httpclient$HttpMethodBase;
  private volatile boolean aborted = false;
  private boolean connectionCloseForced = false;
  private CookieSpec cookiespec = null;
  private boolean doAuthentication = true;
  protected HttpVersion effectiveVersion = null;
  private boolean followRedirects = false;
  private AuthState hostAuthState = new AuthState();
  private HttpHost httphost = null;
  private MethodRetryHandler methodRetryHandler;
  private HttpMethodParams params = new HttpMethodParams();
  private String path = null;
  private AuthState proxyAuthState = new AuthState();
  private String queryString = null;
  private int recoverableExceptionCount = 0;
  private HeaderGroup requestHeaders = new HeaderGroup();
  private boolean requestSent = false;
  private byte[] responseBody = null;
  private HttpConnection responseConnection = null;
  private HeaderGroup responseHeaders = new HeaderGroup();
  private InputStream responseStream = null;
  private HeaderGroup responseTrailerHeaders = new HeaderGroup();
  protected StatusLine statusLine = null;
  private boolean used = false;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpMethodBase == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpMethodBase");
      class$org$apache$commons$httpclient$HttpMethodBase = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$HttpMethodBase;
    }
  }

  public HttpMethodBase()
  {
  }

  public HttpMethodBase(String paramString)
  {
    if (paramString != null);
    while (true)
    {
      try
      {
        if (!paramString.equals(""))
        {
          setURI(new URI(paramString, true, getParams().getUriCharset()));
          return;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append("Invalid uri '");
          localStringBuffer.append(paramString);
          localStringBuffer.append("': ");
          Object localObject;
          localStringBuffer.append(localObject.getMessage());
          throw new IllegalArgumentException(localStringBuffer.toString());
        }
      }
      catch (URIException localURIException)
      {
        continue;
      }
      paramString = "/";
    }
  }

  private static boolean canResponseHaveBody(int paramInt)
  {
    LOG.trace("enter HttpMethodBase.canResponseHaveBody(int)");
    boolean bool;
    if (((paramInt < 100) || (paramInt > 199)) && (paramInt != 204) && (paramInt != 304))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private void checkExecuteConditions(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    if (paramHttpState == null)
      throw new IllegalArgumentException("HttpState parameter may not be null");
    if (paramHttpConnection == null)
      throw new IllegalArgumentException("HttpConnection parameter may not be null");
    if (this.aborted)
      throw new IllegalStateException("Method has been aborted");
    if (!validate())
      throw new ProtocolException("HttpMethodBase object not valid");
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

  private void ensureConnectionRelease()
  {
    if (this.responseConnection != null)
    {
      this.responseConnection.releaseConnection();
      this.responseConnection = null;
    }
  }

  protected static String generateRequestLine(HttpConnection paramHttpConnection, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    LOG.trace("enter HttpMethodBase.generateRequestLine(HttpConnection, String, String, String, String)");
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString1);
    localStringBuffer.append(" ");
    if (!paramHttpConnection.isTransparent())
    {
      Protocol localProtocol = paramHttpConnection.getProtocol();
      localStringBuffer.append(localProtocol.getScheme().toLowerCase());
      localStringBuffer.append("://");
      localStringBuffer.append(paramHttpConnection.getHost());
      if ((paramHttpConnection.getPort() != -1) && (paramHttpConnection.getPort() != localProtocol.getDefaultPort()))
      {
        localStringBuffer.append(":");
        localStringBuffer.append(paramHttpConnection.getPort());
      }
    }
    if (paramString2 == null)
    {
      localStringBuffer.append("/");
    }
    else
    {
      if ((!paramHttpConnection.isTransparent()) && (!paramString2.startsWith("/")))
        localStringBuffer.append("/");
      localStringBuffer.append(paramString2);
    }
    if (paramString3 != null)
    {
      if (paramString3.indexOf("?") != 0)
        localStringBuffer.append("?");
      localStringBuffer.append(paramString3);
    }
    localStringBuffer.append(" ");
    localStringBuffer.append(paramString4);
    localStringBuffer.append("\r\n");
    return localStringBuffer.toString();
  }

  private CookieSpec getCookieSpec(HttpState paramHttpState)
  {
    if (this.cookiespec == null)
    {
      int i = paramHttpState.getCookiePolicy();
      if (i == -1)
        this.cookiespec = CookiePolicy.getCookieSpec(this.params.getCookiePolicy());
      else
        this.cookiespec = CookiePolicy.getSpecByPolicy(i);
      this.cookiespec.setValidDateFormats((Collection)this.params.getParameter("http.dateparser.patterns"));
    }
    return this.cookiespec;
  }

  private String getRequestLine(HttpConnection paramHttpConnection)
  {
    return generateRequestLine(paramHttpConnection, getName(), getPath(), getQueryString(), this.effectiveVersion.toString());
  }

  private InputStream readResponseBody(HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.readResponseBody(HttpConnection)");
    this.responseBody = null;
    Object localObject1 = paramHttpConnection.getResponseInputStream();
    if (Wire.CONTENT_WIRE.enabled())
      localObject1 = new WireLogInputStream((InputStream)localObject1, Wire.CONTENT_WIRE);
    boolean bool = canResponseHaveBody(this.statusLine.getStatusCode());
    Header localHeader1 = this.responseHeaders.getFirstHeader("Transfer-Encoding");
    long l;
    if (localHeader1 != null)
    {
      String str2 = localHeader1.getValue();
      if ((!"chunked".equalsIgnoreCase(str2)) && (!"identity".equalsIgnoreCase(str2)) && (LOG.isWarnEnabled()))
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Unsupported transfer encoding: ");
        localStringBuffer.append(str2);
        localLog.warn(localStringBuffer.toString());
      }
      HeaderElement[] arrayOfHeaderElement = localHeader1.getElements();
      int i = arrayOfHeaderElement.length;
      if ((i > 0) && ("chunked".equalsIgnoreCase(arrayOfHeaderElement[(i - 1)].getName())))
      {
        if (paramHttpConnection.isResponseAvailable(paramHttpConnection.getParams().getSoTimeout()))
        {
          localObject2 = new ChunkedInputStream((InputStream)localObject1, this);
          break label384;
        }
        if (getParams().isParameterTrue("http.protocol.strict-transfer-encoding"))
          throw new ProtocolException("Chunk-encoded body declared but not sent");
        LOG.warn("Chunk-encoded body missing");
        localObject2 = null;
        break label384;
      }
      LOG.info("Response content is not chunk-encoded");
      setConnectionCloseForced(true);
    }
    else
    {
      l = getResponseContentLength();
      if (l != -1L)
        break label372;
      if ((bool) && (this.effectiveVersion.greaterEquals(HttpVersion.HTTP_1_1)))
      {
        Header localHeader2 = this.responseHeaders.getFirstHeader("Connection");
        String str1;
        if (localHeader2 != null)
          str1 = localHeader2.getValue();
        else
          str1 = null;
        if (!"close".equalsIgnoreCase(str1))
        {
          LOG.info("Response content length is not known");
          setConnectionCloseForced(true);
        }
      }
    }
    Object localObject2 = localObject1;
    break label384;
    label372: localObject2 = new ContentLengthInputStream((InputStream)localObject1, l);
    label384: if (!bool)
      localObject2 = null;
    if (localObject2 != null)
      localObject2 = new AutoCloseInputStream((InputStream)localObject2, new HttpMethodBase.1(this));
    return localObject2;
  }

  private boolean responseAvailable()
  {
    boolean bool;
    if ((this.responseBody == null) && (this.responseStream == null))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public void abort()
  {
    if (this.aborted)
      return;
    this.aborted = true;
    HttpConnection localHttpConnection = this.responseConnection;
    if (localHttpConnection != null)
      localHttpConnection.close();
  }

  protected void addCookieRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.addCookieRequestHeader(HttpState, HttpConnection)");
    Header[] arrayOfHeader = getRequestHeaderGroup().getHeaders("Cookie");
    int i = 0;
    for (int j = 0; j < arrayOfHeader.length; j++)
    {
      Header localHeader = arrayOfHeader[j];
      if (localHeader.isAutogenerated())
        getRequestHeaderGroup().removeHeader(localHeader);
    }
    CookieSpec localCookieSpec = getCookieSpec(paramHttpState);
    String str1 = this.params.getVirtualHost();
    if (str1 == null)
      str1 = paramHttpConnection.getHost();
    Cookie[] arrayOfCookie = localCookieSpec.match(str1, paramHttpConnection.getPort(), getPath(), paramHttpConnection.isSecure(), paramHttpState.getCookies());
    if ((arrayOfCookie != null) && (arrayOfCookie.length > 0))
    {
      if (getParams().isParameterTrue("http.protocol.single-cookie-header"))
      {
        String str3 = localCookieSpec.formatCookies(arrayOfCookie);
        getRequestHeaderGroup().addHeader(new Header("Cookie", str3, true));
      }
      else
      {
        for (int k = 0; k < arrayOfCookie.length; k++)
        {
          String str2 = localCookieSpec.formatCookie(arrayOfCookie[k]);
          getRequestHeaderGroup().addHeader(new Header("Cookie", str2, true));
        }
      }
      if ((localCookieSpec instanceof CookieVersionSupport))
      {
        CookieVersionSupport localCookieVersionSupport = (CookieVersionSupport)localCookieSpec;
        int m = localCookieVersionSupport.getVersion();
        int n = 0;
        while (i < arrayOfCookie.length)
        {
          if (m != arrayOfCookie[i].getVersion())
            n = 1;
          i++;
        }
        if (n != 0)
          getRequestHeaderGroup().addHeader(localCookieVersionSupport.getVersionHeader());
      }
    }
  }

  protected void addHostRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.addHostRequestHeader(HttpState, HttpConnection)");
    String str = this.params.getVirtualHost();
    if (str != null)
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Using virtual host name: ");
      localStringBuffer2.append(str);
      localLog.debug(localStringBuffer2.toString());
    }
    else
    {
      str = paramHttpConnection.getHost();
    }
    int i = paramHttpConnection.getPort();
    if (LOG.isDebugEnabled())
      LOG.debug("Adding Host request header");
    if (paramHttpConnection.getProtocol().getDefaultPort() != i)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(str);
      localStringBuffer1.append(":");
      localStringBuffer1.append(i);
      str = localStringBuffer1.toString();
    }
    setRequestHeader("Host", str);
  }

  protected void addProxyConnectionHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.addProxyConnectionHeader(HttpState, HttpConnection)");
    if ((!paramHttpConnection.isTransparent()) && (getRequestHeader("Proxy-Connection") == null))
      addRequestHeader("Proxy-Connection", "Keep-Alive");
  }

  public void addRequestHeader(String paramString1, String paramString2)
  {
    addRequestHeader(new Header(paramString1, paramString2));
  }

  public void addRequestHeader(Header paramHeader)
  {
    LOG.trace("HttpMethodBase.addRequestHeader(Header)");
    if (paramHeader == null)
      LOG.debug("null header value ignored");
    else
      getRequestHeaderGroup().addHeader(paramHeader);
  }

  protected void addRequestHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.addRequestHeaders(HttpState, HttpConnection)");
    addUserAgentRequestHeader(paramHttpState, paramHttpConnection);
    addHostRequestHeader(paramHttpState, paramHttpConnection);
    addCookieRequestHeader(paramHttpState, paramHttpConnection);
    addProxyConnectionHeader(paramHttpState, paramHttpConnection);
  }

  public void addResponseFooter(Header paramHeader)
  {
    getResponseTrailerHeaderGroup().addHeader(paramHeader);
  }

  protected void addUserAgentRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.addUserAgentRequestHeaders(HttpState, HttpConnection)");
    if (getRequestHeader("User-Agent") == null)
    {
      String str = (String)getParams().getParameter("http.useragent");
      if (str == null)
        str = "Jakarta Commons-HttpClient";
      setRequestHeader("User-Agent", str);
    }
  }

  protected void checkNotUsed()
  {
    if (this.used)
      throw new IllegalStateException("Already used.");
  }

  protected void checkUsed()
  {
    if (!this.used)
      throw new IllegalStateException("Not Used.");
  }

  public int execute(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.execute(HttpState, HttpConnection)");
    this.responseConnection = paramHttpConnection;
    checkExecuteConditions(paramHttpState, paramHttpConnection);
    this.statusLine = null;
    this.connectionCloseForced = false;
    paramHttpConnection.setLastResponseInputStream(null);
    if (this.effectiveVersion == null)
      this.effectiveVersion = this.params.getVersion();
    writeRequest(paramHttpState, paramHttpConnection);
    this.requestSent = true;
    readResponse(paramHttpState, paramHttpConnection);
    this.used = true;
    return this.statusLine.getStatusCode();
  }

  void fakeResponse(StatusLine paramStatusLine, HeaderGroup paramHeaderGroup, InputStream paramInputStream)
  {
    this.used = true;
    this.statusLine = paramStatusLine;
    this.responseHeaders = paramHeaderGroup;
    this.responseBody = null;
    this.responseStream = paramInputStream;
  }

  public String getAuthenticationRealm()
  {
    return this.hostAuthState.getRealm();
  }

  protected String getContentCharSet(Header paramHeader)
  {
    LOG.trace("enter getContentCharSet( Header contentheader )");
    if (paramHeader != null)
    {
      HeaderElement[] arrayOfHeaderElement = paramHeader.getElements();
      if (arrayOfHeaderElement.length == 1)
      {
        NameValuePair localNameValuePair = arrayOfHeaderElement[0].getParameterByName("charset");
        if (localNameValuePair != null)
        {
          str = localNameValuePair.getValue();
          break label56;
        }
      }
    }
    String str = null;
    label56: if (str == null)
    {
      str = getParams().getContentCharset();
      if (LOG.isDebugEnabled())
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Default charset used: ");
        localStringBuffer.append(str);
        localLog.debug(localStringBuffer.toString());
      }
    }
    return str;
  }

  public boolean getDoAuthentication()
  {
    return this.doAuthentication;
  }

  public HttpVersion getEffectiveVersion()
  {
    return this.effectiveVersion;
  }

  public boolean getFollowRedirects()
  {
    return this.followRedirects;
  }

  public AuthState getHostAuthState()
  {
    return this.hostAuthState;
  }

  public HostConfiguration getHostConfiguration()
  {
    HostConfiguration localHostConfiguration = new HostConfiguration();
    localHostConfiguration.setHost(this.httphost);
    return localHostConfiguration;
  }

  public MethodRetryHandler getMethodRetryHandler()
  {
    return this.methodRetryHandler;
  }

  public abstract String getName();

  public HttpMethodParams getParams()
  {
    return this.params;
  }

  public String getPath()
  {
    String str;
    if ((this.path != null) && (!this.path.equals("")))
      str = this.path;
    else
      str = "/";
    return str;
  }

  public AuthState getProxyAuthState()
  {
    return this.proxyAuthState;
  }

  public String getProxyAuthenticationRealm()
  {
    return this.proxyAuthState.getRealm();
  }

  public String getQueryString()
  {
    return this.queryString;
  }

  public int getRecoverableExceptionCount()
  {
    return this.recoverableExceptionCount;
  }

  public String getRequestCharSet()
  {
    return getContentCharSet(getRequestHeader("Content-Type"));
  }

  public Header getRequestHeader(String paramString)
  {
    if (paramString == null)
      return null;
    return getRequestHeaderGroup().getCondensedHeader(paramString);
  }

  protected HeaderGroup getRequestHeaderGroup()
  {
    return this.requestHeaders;
  }

  public Header[] getRequestHeaders()
  {
    return getRequestHeaderGroup().getAllHeaders();
  }

  public Header[] getRequestHeaders(String paramString)
  {
    return getRequestHeaderGroup().getHeaders(paramString);
  }

  public byte[] getResponseBody()
  {
    if (this.responseBody == null)
    {
      InputStream localInputStream = getResponseBodyAsStream();
      if (localInputStream != null)
      {
        long l = getResponseContentLength();
        if (l > 2147483647L)
        {
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append("Content too large to be buffered: ");
          localStringBuffer.append(l);
          localStringBuffer.append(" bytes");
          throw new IOException(localStringBuffer.toString());
        }
        int i = getParams().getIntParameter("http.method.response.buffer.warnlimit", 1048576);
        if ((l == -1L) || (l > i))
          LOG.warn("Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.");
        LOG.debug("Buffering response body");
        int j;
        if (l > 0L)
          j = (int)l;
        else
          j = 4096;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(j);
        byte[] arrayOfByte = new byte[4096];
        while (true)
        {
          int k = localInputStream.read(arrayOfByte);
          if (k <= 0)
            break;
          localByteArrayOutputStream.write(arrayOfByte, 0, k);
        }
        localByteArrayOutputStream.close();
        setResponseStream(null);
        this.responseBody = localByteArrayOutputStream.toByteArray();
      }
    }
    return this.responseBody;
  }

  public byte[] getResponseBody(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("maxlen must be positive");
    if (this.responseBody == null)
    {
      InputStream localInputStream = getResponseBodyAsStream();
      if (localInputStream != null)
      {
        long l = getResponseContentLength();
        if ((l != -1L) && (l > paramInt))
        {
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.append("Content-Length is ");
          localStringBuffer2.append(l);
          throw new HttpContentTooLargeException(localStringBuffer2.toString(), paramInt);
        }
        LOG.debug("Buffering response body");
        int i;
        if (l > 0L)
          i = (int)l;
        else
          i = 4096;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
        byte[] arrayOfByte = new byte[2048];
        int j = 0;
        do
        {
          int k = localInputStream.read(arrayOfByte, 0, Math.min(arrayOfByte.length, paramInt - j));
          if (k == -1)
            break;
          localByteArrayOutputStream.write(arrayOfByte, 0, k);
          j += k;
        }
        while (j < paramInt);
        setResponseStream(null);
        if ((j == paramInt) && (localInputStream.read() != -1))
        {
          StringBuffer localStringBuffer1 = new StringBuffer();
          localStringBuffer1.append("Content-Length not known but larger than ");
          localStringBuffer1.append(paramInt);
          throw new HttpContentTooLargeException(localStringBuffer1.toString(), paramInt);
        }
        this.responseBody = localByteArrayOutputStream.toByteArray();
      }
    }
    return this.responseBody;
  }

  public InputStream getResponseBodyAsStream()
  {
    if (this.responseStream != null)
      return this.responseStream;
    if (this.responseBody != null)
    {
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.responseBody);
      LOG.debug("re-creating response stream from byte array");
      return localByteArrayInputStream;
    }
    return null;
  }

  public String getResponseBodyAsString()
  {
    byte[] arrayOfByte;
    if (responseAvailable())
      arrayOfByte = getResponseBody();
    else
      arrayOfByte = null;
    if (arrayOfByte != null)
      return EncodingUtil.getString(arrayOfByte, getResponseCharSet());
    return null;
  }

  public String getResponseBodyAsString(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("maxlen must be positive");
    byte[] arrayOfByte;
    if (responseAvailable())
      arrayOfByte = getResponseBody(paramInt);
    else
      arrayOfByte = null;
    if (arrayOfByte != null)
      return EncodingUtil.getString(arrayOfByte, getResponseCharSet());
    return null;
  }

  public String getResponseCharSet()
  {
    return getContentCharSet(getResponseHeader("Content-Type"));
  }

  public long getResponseContentLength()
  {
    Header[] arrayOfHeader = getResponseHeaderGroup().getHeaders("Content-Length");
    if (arrayOfHeader.length == 0)
      return -1L;
    if (arrayOfHeader.length > 1)
      LOG.warn("Multiple content-length headers detected");
    int i = arrayOfHeader.length - 1;
    while (i >= 0)
    {
      Header localHeader = arrayOfHeader[i];
      try
      {
        long l = Long.parseLong(localHeader.getValue());
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        if (LOG.isWarnEnabled())
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append("Invalid content-length value: ");
          localStringBuffer.append(localNumberFormatException.getMessage());
          localLog.warn(localStringBuffer.toString());
        }
        i--;
      }
    }
    return -1L;
  }

  public Header getResponseFooter(String paramString)
  {
    if (paramString == null)
      return null;
    return getResponseTrailerHeaderGroup().getCondensedHeader(paramString);
  }

  public Header[] getResponseFooters()
  {
    return getResponseTrailerHeaderGroup().getAllHeaders();
  }

  public Header getResponseHeader(String paramString)
  {
    if (paramString == null)
      return null;
    return getResponseHeaderGroup().getCondensedHeader(paramString);
  }

  protected HeaderGroup getResponseHeaderGroup()
  {
    return this.responseHeaders;
  }

  public Header[] getResponseHeaders()
  {
    return getResponseHeaderGroup().getAllHeaders();
  }

  public Header[] getResponseHeaders(String paramString)
  {
    return getResponseHeaderGroup().getHeaders(paramString);
  }

  protected InputStream getResponseStream()
  {
    return this.responseStream;
  }

  protected HeaderGroup getResponseTrailerHeaderGroup()
  {
    return this.responseTrailerHeaders;
  }

  public int getStatusCode()
  {
    return this.statusLine.getStatusCode();
  }

  public StatusLine getStatusLine()
  {
    return this.statusLine;
  }

  public String getStatusText()
  {
    return this.statusLine.getReasonPhrase();
  }

  public URI getURI()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.httphost != null)
    {
      localStringBuffer.append(this.httphost.getProtocol().getScheme());
      localStringBuffer.append("://");
      localStringBuffer.append(this.httphost.getHostName());
      int i = this.httphost.getPort();
      if ((i != -1) && (i != this.httphost.getProtocol().getDefaultPort()))
      {
        localStringBuffer.append(":");
        localStringBuffer.append(i);
      }
    }
    localStringBuffer.append(this.path);
    if (this.queryString != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(this.queryString);
    }
    String str = getParams().getUriCharset();
    return new URI(localStringBuffer.toString(), true, str);
  }

  public boolean hasBeenUsed()
  {
    return this.used;
  }

  public boolean isAborted()
  {
    return this.aborted;
  }

  protected boolean isConnectionCloseForced()
  {
    return this.connectionCloseForced;
  }

  public boolean isHttp11()
  {
    return this.params.getVersion().equals(HttpVersion.HTTP_1_1);
  }

  public boolean isRequestSent()
  {
    return this.requestSent;
  }

  public boolean isStrictMode()
  {
    return false;
  }

  protected void processCookieHeaders(CookieSpec paramCookieSpec, Header[] paramArrayOfHeader, HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.processCookieHeaders(Header[], HttpState, HttpConnection)");
    String str1 = this.params.getVirtualHost();
    if (str1 == null)
      str1 = paramHttpConnection.getHost();
    String str2 = str1;
    for (int i = 0; i < paramArrayOfHeader.length; i++)
    {
      Header localHeader = paramArrayOfHeader[i];
      Cookie[] arrayOfCookie1;
      try
      {
        Cookie[] arrayOfCookie2 = paramCookieSpec.parse(str2, paramHttpConnection.getPort(), getPath(), paramHttpConnection.isSecure(), localHeader);
        arrayOfCookie1 = arrayOfCookie2;
      }
      catch (MalformedCookieException localMalformedCookieException1)
      {
        boolean bool = LOG.isWarnEnabled();
        arrayOfCookie1 = null;
        if (bool)
        {
          Log localLog3 = LOG;
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append("Invalid cookie header: \"");
          localStringBuffer3.append(localHeader.getValue());
          localStringBuffer3.append("\". ");
          localStringBuffer3.append(localMalformedCookieException1.getMessage());
          localLog3.warn(localStringBuffer3.toString());
        }
      }
      if (arrayOfCookie1 != null)
        for (int j = 0; j < arrayOfCookie1.length; j++)
        {
          Cookie localCookie = arrayOfCookie1[j];
          try
          {
            paramCookieSpec.validate(str2, paramHttpConnection.getPort(), getPath(), paramHttpConnection.isSecure(), localCookie);
            try
            {
              paramHttpState.addCookie(localCookie);
              if (!LOG.isDebugEnabled())
                continue;
              Log localLog2 = LOG;
              StringBuffer localStringBuffer2 = new StringBuffer();
              localStringBuffer2.append("Cookie accepted: \"");
              localStringBuffer2.append(paramCookieSpec.formatCookie(localCookie));
              localStringBuffer2.append("\"");
              localLog2.debug(localStringBuffer2.toString());
            }
            catch (MalformedCookieException localMalformedCookieException2)
            {
            }
          }
          catch (MalformedCookieException localMalformedCookieException3)
          {
          }
          MalformedCookieException localMalformedCookieException4 = localMalformedCookieException3;
          if (LOG.isWarnEnabled())
          {
            Log localLog1 = LOG;
            StringBuffer localStringBuffer1 = new StringBuffer();
            localStringBuffer1.append("Cookie rejected: \"");
            localStringBuffer1.append(paramCookieSpec.formatCookie(localCookie));
            localStringBuffer1.append("\". ");
            localStringBuffer1.append(localMalformedCookieException4.getMessage());
            localLog1.warn(localStringBuffer1.toString());
          }
        }
    }
  }

  protected void processResponseBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
  }

  protected void processResponseHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.processResponseHeaders(HttpState, HttpConnection)");
    CookieSpec localCookieSpec = getCookieSpec(paramHttpState);
    processCookieHeaders(localCookieSpec, getResponseHeaderGroup().getHeaders("set-cookie"), paramHttpState, paramHttpConnection);
    if (((localCookieSpec instanceof CookieVersionSupport)) && (((CookieVersionSupport)localCookieSpec).getVersion() > 0))
      processCookieHeaders(localCookieSpec, getResponseHeaderGroup().getHeaders("set-cookie2"), paramHttpState, paramHttpConnection);
  }

  protected void processStatusLine(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
  }

  protected void readResponse(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.readResponse(HttpState, HttpConnection)");
    while (this.statusLine == null)
    {
      readStatusLine(paramHttpState, paramHttpConnection);
      processStatusLine(paramHttpState, paramHttpConnection);
      readResponseHeaders(paramHttpState, paramHttpConnection);
      processResponseHeaders(paramHttpState, paramHttpConnection);
      int i = this.statusLine.getStatusCode();
      if ((i >= 100) && (i < 200))
      {
        if (LOG.isInfoEnabled())
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append("Discarding unexpected response: ");
          localStringBuffer.append(this.statusLine.toString());
          localLog.info(localStringBuffer.toString());
        }
        this.statusLine = null;
      }
    }
    readResponseBody(paramHttpState, paramHttpConnection);
    processResponseBody(paramHttpState, paramHttpConnection);
  }

  protected void readResponseBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.readResponseBody(HttpState, HttpConnection)");
    InputStream localInputStream = readResponseBody(paramHttpConnection);
    if (localInputStream == null)
    {
      responseBodyConsumed();
    }
    else
    {
      paramHttpConnection.setLastResponseInputStream(localInputStream);
      setResponseStream(localInputStream);
    }
  }

  protected void readResponseHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.readResponseHeaders(HttpState,HttpConnection)");
    getResponseHeaderGroup().clear();
    Header[] arrayOfHeader = HttpParser.parseHeaders(paramHttpConnection.getResponseInputStream(), getParams().getHttpElementCharset());
    getResponseHeaderGroup().setHeaders(arrayOfHeader);
  }

  protected void readStatusLine(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.readStatusLine(HttpState, HttpConnection)");
    int i = getParams().getIntParameter("http.protocol.status-line-garbage-limit", 2147483647);
    for (int j = 0; ; j++)
    {
      String str1 = paramHttpConnection.readLine(getParams().getHttpElementCharset());
      if ((str1 == null) && (j == 0))
      {
        StringBuffer localStringBuffer4 = new StringBuffer();
        localStringBuffer4.append("The server ");
        localStringBuffer4.append(paramHttpConnection.getHost());
        localStringBuffer4.append(" failed to respond");
        throw new NoHttpResponseException(localStringBuffer4.toString());
      }
      if (Wire.HEADER_WIRE.enabled())
      {
        Wire localWire = Wire.HEADER_WIRE;
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append(str1);
        localStringBuffer3.append("\r\n");
        localWire.input(localStringBuffer3.toString());
      }
      if ((str1 != null) && (StatusLine.startsWithHTTP(str1)))
      {
        this.statusLine = new StatusLine(str1);
        String str2 = this.statusLine.getHttpVersion();
        if ((getParams().isParameterFalse("http.protocol.unambiguous-statusline")) && (str2.equals("HTTP")))
        {
          getParams().setVersion(HttpVersion.HTTP_1_0);
          if (LOG.isWarnEnabled())
          {
            Log localLog = LOG;
            StringBuffer localStringBuffer2 = new StringBuffer();
            localStringBuffer2.append("Ambiguous status line (HTTP protocol version missing):");
            localStringBuffer2.append(this.statusLine.toString());
            localLog.warn(localStringBuffer2.toString());
          }
        }
        else
        {
          this.effectiveVersion = HttpVersion.parse(str2);
        }
        return;
      }
      if ((str1 == null) || (j >= i))
        break;
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("The server ");
    localStringBuffer1.append(paramHttpConnection.getHost());
    localStringBuffer1.append(" failed to respond with a valid HTTP response");
    throw new ProtocolException(localStringBuffer1.toString());
  }

  public void recycle()
  {
    LOG.trace("enter HttpMethodBase.recycle()");
    releaseConnection();
    this.path = null;
    this.followRedirects = false;
    this.doAuthentication = true;
    this.queryString = null;
    getRequestHeaderGroup().clear();
    getResponseHeaderGroup().clear();
    getResponseTrailerHeaderGroup().clear();
    this.statusLine = null;
    this.effectiveVersion = null;
    this.aborted = false;
    this.used = false;
    this.params = new HttpMethodParams();
    this.responseBody = null;
    this.recoverableExceptionCount = 0;
    this.connectionCloseForced = false;
    this.hostAuthState.invalidate();
    this.proxyAuthState.invalidate();
    this.cookiespec = null;
    this.requestSent = false;
  }

  public void releaseConnection()
  {
    try
    {
      InputStream localInputStream = this.responseStream;
      if (localInputStream != null);
      try
      {
        this.responseStream.close();
      }
      catch (IOException localIOException)
      {
      }
      return;
    }
    finally
    {
      ensureConnectionRelease();
    }
  }

  public void removeRequestHeader(String paramString)
  {
    Header[] arrayOfHeader = getRequestHeaderGroup().getHeaders(paramString);
    for (int i = 0; i < arrayOfHeader.length; i++)
      getRequestHeaderGroup().removeHeader(arrayOfHeader[i]);
  }

  public void removeRequestHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    getRequestHeaderGroup().removeHeader(paramHeader);
  }

  protected void responseBodyConsumed()
  {
    this.responseStream = null;
    if (this.responseConnection != null)
    {
      this.responseConnection.setLastResponseInputStream(null);
      if (shouldCloseConnection(this.responseConnection))
        this.responseConnection.close();
      else
        try
        {
          if (this.responseConnection.isResponseAvailable())
          {
            if (getParams().isParameterTrue("http.protocol.warn-extra-input"))
              LOG.warn("Extra response data detected - closing connection");
            this.responseConnection.close();
          }
        }
        catch (IOException localIOException)
        {
          LOG.warn(localIOException.getMessage());
          this.responseConnection.close();
        }
    }
    this.connectionCloseForced = false;
    ensureConnectionRelease();
  }

  protected void setConnectionCloseForced(boolean paramBoolean)
  {
    if (LOG.isDebugEnabled())
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Force-close connection: ");
      localStringBuffer.append(paramBoolean);
      localLog.debug(localStringBuffer.toString());
    }
    this.connectionCloseForced = paramBoolean;
  }

  public void setDoAuthentication(boolean paramBoolean)
  {
    this.doAuthentication = paramBoolean;
  }

  public void setFollowRedirects(boolean paramBoolean)
  {
    this.followRedirects = paramBoolean;
  }

  public void setHostConfiguration(HostConfiguration paramHostConfiguration)
  {
    if (paramHostConfiguration != null)
      this.httphost = new HttpHost(paramHostConfiguration.getHost(), paramHostConfiguration.getPort(), paramHostConfiguration.getProtocol());
    else
      this.httphost = null;
  }

  public void setHttp11(boolean paramBoolean)
  {
    if (paramBoolean)
      this.params.setVersion(HttpVersion.HTTP_1_1);
    else
      this.params.setVersion(HttpVersion.HTTP_1_0);
  }

  public void setMethodRetryHandler(MethodRetryHandler paramMethodRetryHandler)
  {
    this.methodRetryHandler = paramMethodRetryHandler;
  }

  public void setParams(HttpMethodParams paramHttpMethodParams)
  {
    if (paramHttpMethodParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpMethodParams;
  }

  public void setPath(String paramString)
  {
    this.path = paramString;
  }

  public void setQueryString(String paramString)
  {
    this.queryString = paramString;
  }

  public void setQueryString(NameValuePair[] paramArrayOfNameValuePair)
  {
    LOG.trace("enter HttpMethodBase.setQueryString(NameValuePair[])");
    this.queryString = EncodingUtil.formUrlEncode(paramArrayOfNameValuePair, "UTF-8");
  }

  public void setRequestHeader(String paramString1, String paramString2)
  {
    setRequestHeader(new Header(paramString1, paramString2));
  }

  public void setRequestHeader(Header paramHeader)
  {
    Header[] arrayOfHeader = getRequestHeaderGroup().getHeaders(paramHeader.getName());
    for (int i = 0; i < arrayOfHeader.length; i++)
      getRequestHeaderGroup().removeHeader(arrayOfHeader[i]);
    getRequestHeaderGroup().addHeader(paramHeader);
  }

  protected void setResponseStream(InputStream paramInputStream)
  {
    this.responseStream = paramInputStream;
  }

  public void setStrictMode(boolean paramBoolean)
  {
    if (paramBoolean)
      this.params.makeStrict();
    else
      this.params.makeLenient();
  }

  public void setURI(URI paramURI)
  {
    if (paramURI.isAbsoluteURI())
      this.httphost = new HttpHost(paramURI);
    String str;
    if (paramURI.getPath() == null)
      str = "/";
    else
      str = paramURI.getEscapedPath();
    setPath(str);
    setQueryString(paramURI.getEscapedQuery());
  }

  protected boolean shouldCloseConnection(HttpConnection paramHttpConnection)
  {
    if (isConnectionCloseForced())
    {
      LOG.debug("Should force-close connection.");
      return true;
    }
    boolean bool = paramHttpConnection.isTransparent();
    Header localHeader = null;
    if (!bool)
      localHeader = this.responseHeaders.getFirstHeader("proxy-connection");
    if (localHeader == null)
      localHeader = this.responseHeaders.getFirstHeader("connection");
    if (localHeader == null)
      localHeader = this.requestHeaders.getFirstHeader("connection");
    if (localHeader != null)
    {
      if (localHeader.getValue().equalsIgnoreCase("close"))
      {
        if (LOG.isDebugEnabled())
        {
          Log localLog5 = LOG;
          StringBuffer localStringBuffer5 = new StringBuffer();
          localStringBuffer5.append("Should close connection in response to directive: ");
          localStringBuffer5.append(localHeader.getValue());
          localLog5.debug(localStringBuffer5.toString());
        }
        return true;
      }
      if (localHeader.getValue().equalsIgnoreCase("keep-alive"))
      {
        if (LOG.isDebugEnabled())
        {
          Log localLog4 = LOG;
          StringBuffer localStringBuffer4 = new StringBuffer();
          localStringBuffer4.append("Should NOT close connection in response to directive: ");
          localStringBuffer4.append(localHeader.getValue());
          localLog4.debug(localStringBuffer4.toString());
        }
        return false;
      }
      if (LOG.isDebugEnabled())
      {
        Log localLog3 = LOG;
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append("Unknown directive: ");
        localStringBuffer3.append(localHeader.toExternalForm());
        localLog3.debug(localStringBuffer3.toString());
      }
    }
    LOG.debug("Resorting to protocol version default close connection policy");
    if (this.effectiveVersion.greaterEquals(HttpVersion.HTTP_1_1))
    {
      if (LOG.isDebugEnabled())
      {
        Log localLog2 = LOG;
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Should NOT close connection, using ");
        localStringBuffer2.append(this.effectiveVersion.toString());
        localLog2.debug(localStringBuffer2.toString());
      }
    }
    else if (LOG.isDebugEnabled())
    {
      Log localLog1 = LOG;
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Should close connection, using ");
      localStringBuffer1.append(this.effectiveVersion.toString());
      localLog1.debug(localStringBuffer1.toString());
    }
    return this.effectiveVersion.lessEquals(HttpVersion.HTTP_1_0);
  }

  public boolean validate()
  {
    return true;
  }

  // ERROR //
  protected void writeRequest(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    // Byte code:
    //   0: getstatic 69	org/apache/commons/httpclient/HttpMethodBase:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc_w 1063
    //   6: invokeinterface 186 2 0
    //   11: aload_0
    //   12: aload_1
    //   13: aload_2
    //   14: invokevirtual 1066	org/apache/commons/httpclient/HttpMethodBase:writeRequestLine	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)V
    //   17: aload_0
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 1069	org/apache/commons/httpclient/HttpMethodBase:writeRequestHeaders	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)V
    //   23: aload_2
    //   24: invokevirtual 1072	org/apache/commons/httpclient/HttpConnection:writeLine	()V
    //   27: getstatic 919	org/apache/commons/httpclient/Wire:HEADER_WIRE	Lorg/apache/commons/httpclient/Wire;
    //   30: invokevirtual 338	org/apache/commons/httpclient/Wire:enabled	()Z
    //   33: ifeq +12 -> 45
    //   36: getstatic 919	org/apache/commons/httpclient/Wire:HEADER_WIRE	Lorg/apache/commons/httpclient/Wire;
    //   39: ldc_w 273
    //   42: invokevirtual 1075	org/apache/commons/httpclient/Wire:output	(Ljava/lang/String;)V
    //   45: aload_0
    //   46: invokevirtual 142	org/apache/commons/httpclient/HttpMethodBase:getParams	()Lorg/apache/commons/httpclient/params/HttpMethodParams;
    //   49: invokevirtual 602	org/apache/commons/httpclient/params/HttpMethodParams:getVersion	()Lorg/apache/commons/httpclient/HttpVersion;
    //   52: astore_3
    //   53: aload_0
    //   54: ldc_w 1077
    //   57: invokevirtual 546	org/apache/commons/httpclient/HttpMethodBase:getRequestHeader	(Ljava/lang/String;)Lorg/apache/commons/httpclient/Header;
    //   60: astore 4
    //   62: aload 4
    //   64: ifnull +13 -> 77
    //   67: aload 4
    //   69: invokevirtual 361	org/apache/commons/httpclient/Header:getValue	()Ljava/lang/String;
    //   72: astore 5
    //   74: goto +6 -> 80
    //   77: aconst_null
    //   78: astore 5
    //   80: aload 5
    //   82: ifnull +178 -> 260
    //   85: aload 5
    //   87: ldc_w 1079
    //   90: invokevirtual 1082	java/lang/String:compareToIgnoreCase	(Ljava/lang/String;)I
    //   93: ifne +167 -> 260
    //   96: aload_3
    //   97: getstatic 426	org/apache/commons/httpclient/HttpVersion:HTTP_1_1	Lorg/apache/commons/httpclient/HttpVersion;
    //   100: invokevirtual 430	org/apache/commons/httpclient/HttpVersion:greaterEquals	(Lorg/apache/commons/httpclient/HttpVersion;)Z
    //   103: ifeq +139 -> 242
    //   106: aload_2
    //   107: invokevirtual 1085	org/apache/commons/httpclient/HttpConnection:flushRequestOutputStream	()V
    //   110: aload_2
    //   111: invokevirtual 386	org/apache/commons/httpclient/HttpConnection:getParams	()Lorg/apache/commons/httpclient/params/HttpConnectionParams;
    //   114: invokevirtual 391	org/apache/commons/httpclient/params/HttpConnectionParams:getSoTimeout	()I
    //   117: istore 7
    //   119: aload_2
    //   120: sipush 3000
    //   123: invokevirtual 1088	org/apache/commons/httpclient/HttpConnection:setSocketTimeout	(I)V
    //   126: aload_0
    //   127: aload_1
    //   128: aload_2
    //   129: invokevirtual 858	org/apache/commons/httpclient/HttpMethodBase:readStatusLine	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)V
    //   132: aload_0
    //   133: aload_1
    //   134: aload_2
    //   135: invokevirtual 860	org/apache/commons/httpclient/HttpMethodBase:processStatusLine	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)V
    //   138: aload_0
    //   139: aload_1
    //   140: aload_2
    //   141: invokevirtual 863	org/apache/commons/httpclient/HttpMethodBase:readResponseHeaders	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)V
    //   144: aload_0
    //   145: aload_1
    //   146: aload_2
    //   147: invokevirtual 865	org/apache/commons/httpclient/HttpMethodBase:processResponseHeaders	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)V
    //   150: aload_0
    //   151: getfield 79	org/apache/commons/httpclient/HttpMethodBase:statusLine	Lorg/apache/commons/httpclient/StatusLine;
    //   154: invokevirtual 348	org/apache/commons/httpclient/StatusLine:getStatusCode	()I
    //   157: bipush 100
    //   159: if_icmpne +22 -> 181
    //   162: aload_0
    //   163: aconst_null
    //   164: putfield 79	org/apache/commons/httpclient/HttpMethodBase:statusLine	Lorg/apache/commons/httpclient/StatusLine;
    //   167: getstatic 69	org/apache/commons/httpclient/HttpMethodBase:LOG	Lorg/apache/commons/logging/Log;
    //   170: ldc_w 1090
    //   173: invokeinterface 527 2 0
    //   178: goto +46 -> 224
    //   181: aload_2
    //   182: iload 7
    //   184: invokevirtual 1088	org/apache/commons/httpclient/HttpConnection:setSocketTimeout	(I)V
    //   187: return
    //   188: astore 9
    //   190: goto +43 -> 233
    //   193: astore 8
    //   195: aload 8
    //   197: invokestatic 1096	org/apache/commons/httpclient/util/ExceptionUtil:isSocketTimeoutException	(Ljava/io/InterruptedIOException;)Z
    //   200: ifne +6 -> 206
    //   203: aload 8
    //   205: athrow
    //   206: aload_0
    //   207: ldc_w 1077
    //   210: invokevirtual 1098	org/apache/commons/httpclient/HttpMethodBase:removeRequestHeader	(Ljava/lang/String;)V
    //   213: getstatic 69	org/apache/commons/httpclient/HttpMethodBase:LOG	Lorg/apache/commons/logging/Log;
    //   216: ldc_w 1100
    //   219: invokeinterface 413 2 0
    //   224: aload_2
    //   225: iload 7
    //   227: invokevirtual 1088	org/apache/commons/httpclient/HttpConnection:setSocketTimeout	(I)V
    //   230: goto +30 -> 260
    //   233: aload_2
    //   234: iload 7
    //   236: invokevirtual 1088	org/apache/commons/httpclient/HttpConnection:setSocketTimeout	(I)V
    //   239: aload 9
    //   241: athrow
    //   242: aload_0
    //   243: ldc_w 1077
    //   246: invokevirtual 1098	org/apache/commons/httpclient/HttpMethodBase:removeRequestHeader	(Ljava/lang/String;)V
    //   249: getstatic 69	org/apache/commons/httpclient/HttpMethodBase:LOG	Lorg/apache/commons/logging/Log;
    //   252: ldc_w 1102
    //   255: invokeinterface 413 2 0
    //   260: aload_0
    //   261: aload_1
    //   262: aload_2
    //   263: invokevirtual 1106	org/apache/commons/httpclient/HttpMethodBase:writeRequestBody	(Lorg/apache/commons/httpclient/HttpState;Lorg/apache/commons/httpclient/HttpConnection;)Z
    //   266: pop
    //   267: aload_2
    //   268: invokevirtual 1085	org/apache/commons/httpclient/HttpConnection:flushRequestOutputStream	()V
    //   271: return
    //
    // Exception table:
    //   from	to	target	type
    //   119	178	188	finally
    //   195	224	188	finally
    //   119	178	193	java/io/InterruptedIOException
  }

  protected boolean writeRequestBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    return true;
  }

  protected void writeRequestHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.writeRequestHeaders(HttpState,HttpConnection)");
    addRequestHeaders(paramHttpState, paramHttpConnection);
    String str1 = getParams().getHttpElementCharset();
    Header[] arrayOfHeader = getRequestHeaders();
    for (int i = 0; i < arrayOfHeader.length; i++)
    {
      String str2 = arrayOfHeader[i].toExternalForm();
      if (Wire.HEADER_WIRE.enabled())
        Wire.HEADER_WIRE.output(str2);
      paramHttpConnection.print(str2, str1);
    }
  }

  protected void writeRequestLine(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpMethodBase.writeRequestLine(HttpState, HttpConnection)");
    String str = getRequestLine(paramHttpConnection);
    if (Wire.HEADER_WIRE.enabled())
      Wire.HEADER_WIRE.output(str);
    paramHttpConnection.print(str, getParams().getHttpElementCharset());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodBase
 * JD-Core Version:    0.6.1
 */