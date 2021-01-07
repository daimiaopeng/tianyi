package org.jsoup.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Connection.Base;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Request;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

public class HttpConnection
  implements Connection
{
  private static final int HTTP_TEMP_REDIR = 307;
  private Connection.Request req = new Request(null);
  private Connection.Response res = new Response();

  public static Connection connect(String paramString)
  {
    HttpConnection localHttpConnection = new HttpConnection();
    localHttpConnection.url(paramString);
    return localHttpConnection;
  }

  public static Connection connect(URL paramURL)
  {
    HttpConnection localHttpConnection = new HttpConnection();
    localHttpConnection.url(paramURL);
    return localHttpConnection;
  }

  private static String encodeUrl(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.replaceAll(" ", "%20");
  }

  public Connection cookie(String paramString1, String paramString2)
  {
    this.req.cookie(paramString1, paramString2);
    return this;
  }

  public Connection cookies(Map<String, String> paramMap)
  {
    Validate.notNull(paramMap, "Cookie map must not be null");
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.req.cookie((String)localEntry.getKey(), (String)localEntry.getValue());
    }
    return this;
  }

  public Connection data(String paramString1, String paramString2)
  {
    this.req.data(KeyVal.create(paramString1, paramString2));
    return this;
  }

  public Connection data(Collection<Connection.KeyVal> paramCollection)
  {
    Validate.notNull(paramCollection, "Data collection must not be null");
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Connection.KeyVal localKeyVal = (Connection.KeyVal)localIterator.next();
      this.req.data(localKeyVal);
    }
    return this;
  }

  public Connection data(Map<String, String> paramMap)
  {
    Validate.notNull(paramMap, "Data map must not be null");
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.req.data(KeyVal.create((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    return this;
  }

  public Connection data(String[] paramArrayOfString)
  {
    Validate.notNull(paramArrayOfString, "Data key value pairs must not be null");
    int i = paramArrayOfString.length % 2;
    int j = 0;
    boolean bool;
    if (i == 0)
      bool = true;
    else
      bool = false;
    Validate.isTrue(bool, "Must supply an even number of key value pairs");
    while (j < paramArrayOfString.length)
    {
      String str1 = paramArrayOfString[j];
      String str2 = paramArrayOfString[(j + 1)];
      Validate.notEmpty(str1, "Data key must not be empty");
      Validate.notNull(str2, "Data value must not be null");
      this.req.data(KeyVal.create(str1, str2));
      j += 2;
    }
    return this;
  }

  public Connection.Response execute()
  {
    this.res = Response.execute(this.req);
    return this.res;
  }

  public Connection followRedirects(boolean paramBoolean)
  {
    this.req.followRedirects(paramBoolean);
    return this;
  }

  public Document get()
  {
    this.req.method(Connection.Method.GET);
    execute();
    return this.res.parse();
  }

  public Connection header(String paramString1, String paramString2)
  {
    this.req.header(paramString1, paramString2);
    return this;
  }

  public Connection ignoreContentType(boolean paramBoolean)
  {
    this.req.ignoreContentType(paramBoolean);
    return this;
  }

  public Connection ignoreHttpErrors(boolean paramBoolean)
  {
    this.req.ignoreHttpErrors(paramBoolean);
    return this;
  }

  public Connection maxBodySize(int paramInt)
  {
    this.req.maxBodySize(paramInt);
    return this;
  }

  public Connection method(Connection.Method paramMethod)
  {
    this.req.method(paramMethod);
    return this;
  }

  public Connection parser(Parser paramParser)
  {
    this.req.parser(paramParser);
    return this;
  }

  public Document post()
  {
    this.req.method(Connection.Method.POST);
    execute();
    return this.res.parse();
  }

  public Connection referrer(String paramString)
  {
    Validate.notNull(paramString, "Referrer must not be null");
    this.req.header("Referer", paramString);
    return this;
  }

  public Connection.Request request()
  {
    return this.req;
  }

  public Connection request(Connection.Request paramRequest)
  {
    this.req = paramRequest;
    return this;
  }

  public Connection.Response response()
  {
    return this.res;
  }

  public Connection response(Connection.Response paramResponse)
  {
    this.res = paramResponse;
    return this;
  }

  public Connection timeout(int paramInt)
  {
    this.req.timeout(paramInt);
    return this;
  }

  public Connection url(String paramString)
  {
    Validate.notEmpty(paramString, "Must supply a valid URL");
    try
    {
      this.req.url(new URL(encodeUrl(paramString)));
      return this;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Malformed URL: ");
      localStringBuilder.append(paramString);
      throw new IllegalArgumentException(localStringBuilder.toString(), localMalformedURLException);
    }
  }

  public Connection url(URL paramURL)
  {
    this.req.url(paramURL);
    return this;
  }

  public Connection userAgent(String paramString)
  {
    Validate.notNull(paramString, "User agent must not be null");
    this.req.header("User-Agent", paramString);
    return this;
  }

  private static abstract class Base<T extends Connection.Base>
    implements Connection.Base<T>
  {
    Map<String, String> cookies = new LinkedHashMap();
    Map<String, String> headers = new LinkedHashMap();
    Connection.Method method;
    URL url;

    private String getHeaderCaseInsensitive(String paramString)
    {
      Validate.notNull(paramString, "Header name must not be null");
      String str = (String)this.headers.get(paramString);
      if (str == null)
        str = (String)this.headers.get(paramString.toLowerCase());
      if (str == null)
      {
        Map.Entry localEntry = scanHeaders(paramString);
        if (localEntry != null)
          str = (String)localEntry.getValue();
      }
      return str;
    }

    private Map.Entry<String, String> scanHeaders(String paramString)
    {
      String str = paramString.toLowerCase();
      Iterator localIterator = this.headers.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (((String)localEntry.getKey()).toLowerCase().equals(str))
          return localEntry;
      }
      return null;
    }

    public String cookie(String paramString)
    {
      Validate.notNull(paramString, "Cookie name must not be null");
      return (String)this.cookies.get(paramString);
    }

    public T cookie(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1, "Cookie name must not be empty");
      Validate.notNull(paramString2, "Cookie value must not be null");
      this.cookies.put(paramString1, paramString2);
      return this;
    }

    public Map<String, String> cookies()
    {
      return this.cookies;
    }

    public boolean hasCookie(String paramString)
    {
      Validate.notEmpty("Cookie name must not be empty");
      return this.cookies.containsKey(paramString);
    }

    public boolean hasHeader(String paramString)
    {
      Validate.notEmpty(paramString, "Header name must not be empty");
      boolean bool;
      if (getHeaderCaseInsensitive(paramString) != null)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String header(String paramString)
    {
      Validate.notNull(paramString, "Header name must not be null");
      return getHeaderCaseInsensitive(paramString);
    }

    public T header(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1, "Header name must not be empty");
      Validate.notNull(paramString2, "Header value must not be null");
      removeHeader(paramString1);
      this.headers.put(paramString1, paramString2);
      return this;
    }

    public Map<String, String> headers()
    {
      return this.headers;
    }

    public T method(Connection.Method paramMethod)
    {
      Validate.notNull(paramMethod, "Method must not be null");
      this.method = paramMethod;
      return this;
    }

    public Connection.Method method()
    {
      return this.method;
    }

    public T removeCookie(String paramString)
    {
      Validate.notEmpty("Cookie name must not be empty");
      this.cookies.remove(paramString);
      return this;
    }

    public T removeHeader(String paramString)
    {
      Validate.notEmpty(paramString, "Header name must not be empty");
      Map.Entry localEntry = scanHeaders(paramString);
      if (localEntry != null)
        this.headers.remove(localEntry.getKey());
      return this;
    }

    public URL url()
    {
      return this.url;
    }

    public T url(URL paramURL)
    {
      Validate.notNull(paramURL, "URL must not be null");
      this.url = paramURL;
      return this;
    }
  }

  public static class KeyVal
    implements Connection.KeyVal
  {
    private String key;
    private String value;

    private KeyVal(String paramString1, String paramString2)
    {
      this.key = paramString1;
      this.value = paramString2;
    }

    public static KeyVal create(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1, "Data key must not be empty");
      Validate.notNull(paramString2, "Data value must not be null");
      return new KeyVal(paramString1, paramString2);
    }

    public String key()
    {
      return this.key;
    }

    public KeyVal key(String paramString)
    {
      Validate.notEmpty(paramString, "Data key must not be empty");
      this.key = paramString;
      return this;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.key);
      localStringBuilder.append("=");
      localStringBuilder.append(this.value);
      return localStringBuilder.toString();
    }

    public String value()
    {
      return this.value;
    }

    public KeyVal value(String paramString)
    {
      Validate.notNull(paramString, "Data value must not be null");
      this.value = paramString;
      return this;
    }
  }

  public static class Request extends HttpConnection.Base<Connection.Request>
    implements Connection.Request
  {
    private Collection<Connection.KeyVal> data = new ArrayList();
    private boolean followRedirects = true;
    private boolean ignoreContentType = false;
    private boolean ignoreHttpErrors = false;
    private int maxBodySizeBytes = 1048576;
    private Parser parser;
    private int timeoutMilliseconds = 3000;

    private Request()
    {
      super();
      this.method = Connection.Method.GET;
      this.headers.put("Accept-Encoding", "gzip");
      this.parser = Parser.htmlParser();
    }

    public Collection<Connection.KeyVal> data()
    {
      return this.data;
    }

    public Request data(Connection.KeyVal paramKeyVal)
    {
      Validate.notNull(paramKeyVal, "Key val must not be null");
      this.data.add(paramKeyVal);
      return this;
    }

    public Connection.Request followRedirects(boolean paramBoolean)
    {
      this.followRedirects = paramBoolean;
      return this;
    }

    public boolean followRedirects()
    {
      return this.followRedirects;
    }

    public Connection.Request ignoreContentType(boolean paramBoolean)
    {
      this.ignoreContentType = paramBoolean;
      return this;
    }

    public boolean ignoreContentType()
    {
      return this.ignoreContentType;
    }

    public Connection.Request ignoreHttpErrors(boolean paramBoolean)
    {
      this.ignoreHttpErrors = paramBoolean;
      return this;
    }

    public boolean ignoreHttpErrors()
    {
      return this.ignoreHttpErrors;
    }

    public int maxBodySize()
    {
      return this.maxBodySizeBytes;
    }

    public Connection.Request maxBodySize(int paramInt)
    {
      boolean bool;
      if (paramInt >= 0)
        bool = true;
      else
        bool = false;
      Validate.isTrue(bool, "maxSize must be 0 (unlimited) or larger");
      this.maxBodySizeBytes = paramInt;
      return this;
    }

    public Request parser(Parser paramParser)
    {
      this.parser = paramParser;
      return this;
    }

    public Parser parser()
    {
      return this.parser;
    }

    public int timeout()
    {
      return this.timeoutMilliseconds;
    }

    public Request timeout(int paramInt)
    {
      boolean bool;
      if (paramInt >= 0)
        bool = true;
      else
        bool = false;
      Validate.isTrue(bool, "Timeout milliseconds must be 0 (infinite) or greater");
      this.timeoutMilliseconds = paramInt;
      return this;
    }
  }

  public static class Response extends HttpConnection.Base<Connection.Response>
    implements Connection.Response
  {
    private static final int MAX_REDIRECTS = 20;
    private static final Pattern xmlContentTypeRxp = Pattern.compile("application/\\w+\\+xml.*");
    private ByteBuffer byteData;
    private String charset;
    private String contentType;
    private boolean executed = false;
    private int numRedirects = 0;
    private Connection.Request req;
    private int statusCode;
    private String statusMessage;

    Response()
    {
      super();
    }

    private Response(Response paramResponse)
    {
      super();
      if (paramResponse != null)
      {
        this.numRedirects = (1 + paramResponse.numRedirects);
        if (this.numRedirects >= 20)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = paramResponse.url();
          throw new IOException(String.format("Too many redirects occurred trying to load URL %s", arrayOfObject));
        }
      }
    }

    private static HttpURLConnection createConnection(Connection.Request paramRequest)
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramRequest.url().openConnection();
      localHttpURLConnection.setRequestMethod(paramRequest.method().name());
      localHttpURLConnection.setInstanceFollowRedirects(false);
      localHttpURLConnection.setConnectTimeout(paramRequest.timeout());
      localHttpURLConnection.setReadTimeout(paramRequest.timeout());
      if (paramRequest.method() == Connection.Method.POST)
        localHttpURLConnection.setDoOutput(true);
      if (paramRequest.cookies().size() > 0)
        localHttpURLConnection.addRequestProperty("Cookie", getRequestCookieString(paramRequest));
      Iterator localIterator = paramRequest.headers().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHttpURLConnection.addRequestProperty((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      return localHttpURLConnection;
    }

    static Response execute(Connection.Request paramRequest)
    {
      return execute(paramRequest, null);
    }

    // ERROR //
    static Response execute(Connection.Request paramRequest, Response paramResponse)
    {
      // Byte code:
      //   0: aload_0
      //   1: ldc 169
      //   3: invokestatic 175	org/jsoup/helper/Validate:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
      //   6: aload_0
      //   7: invokeinterface 69 1 0
      //   12: invokevirtual 178	java/net/URL:getProtocol	()Ljava/lang/String;
      //   15: astore_2
      //   16: aload_2
      //   17: ldc 180
      //   19: invokevirtual 184	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   22: ifne +22 -> 44
      //   25: aload_2
      //   26: ldc 186
      //   28: invokevirtual 184	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   31: ifne +13 -> 44
      //   34: new 188	java/net/MalformedURLException
      //   37: dup
      //   38: ldc 190
      //   40: invokespecial 191	java/net/MalformedURLException:<init>	(Ljava/lang/String;)V
      //   43: athrow
      //   44: aload_0
      //   45: invokeinterface 81 1 0
      //   50: getstatic 194	org/jsoup/Connection$Method:GET	Lorg/jsoup/Connection$Method;
      //   53: if_acmpne +21 -> 74
      //   56: aload_0
      //   57: invokeinterface 198 1 0
      //   62: invokeinterface 201 1 0
      //   67: ifle +7 -> 74
      //   70: aload_0
      //   71: invokestatic 205	org/jsoup/helper/HttpConnection$Response:serialiseRequestUrl	(Lorg/jsoup/Connection$Request;)V
      //   74: aload_0
      //   75: invokestatic 207	org/jsoup/helper/HttpConnection$Response:createConnection	(Lorg/jsoup/Connection$Request;)Ljava/net/HttpURLConnection;
      //   78: astore_3
      //   79: aload_3
      //   80: invokevirtual 210	java/net/HttpURLConnection:connect	()V
      //   83: aload_0
      //   84: invokeinterface 81 1 0
      //   89: getstatic 109	org/jsoup/Connection$Method:POST	Lorg/jsoup/Connection$Method;
      //   92: if_acmpne +16 -> 108
      //   95: aload_0
      //   96: invokeinterface 198 1 0
      //   101: aload_3
      //   102: invokevirtual 214	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   105: invokestatic 218	org/jsoup/helper/HttpConnection$Response:writePost	(Ljava/util/Collection;Ljava/io/OutputStream;)V
      //   108: aload_3
      //   109: invokevirtual 221	java/net/HttpURLConnection:getResponseCode	()I
      //   112: istore 5
      //   114: iconst_0
      //   115: istore 6
      //   117: iload 5
      //   119: sipush 200
      //   122: if_icmpeq +75 -> 197
      //   125: iload 5
      //   127: sipush 302
      //   130: if_icmpeq +566 -> 696
      //   133: iload 5
      //   135: sipush 301
      //   138: if_icmpeq +558 -> 696
      //   141: iload 5
      //   143: sipush 303
      //   146: if_icmpeq +550 -> 696
      //   149: iload 5
      //   151: sipush 307
      //   154: if_icmpne +6 -> 160
      //   157: goto +539 -> 696
      //   160: aload_0
      //   161: invokeinterface 224 1 0
      //   166: istore 22
      //   168: iconst_0
      //   169: istore 6
      //   171: iload 22
      //   173: ifne +24 -> 197
      //   176: new 226	org/jsoup/HttpStatusException
      //   179: dup
      //   180: ldc 228
      //   182: iload 5
      //   184: aload_0
      //   185: invokeinterface 69 1 0
      //   190: invokevirtual 231	java/net/URL:toString	()Ljava/lang/String;
      //   193: invokespecial 234	org/jsoup/HttpStatusException:<init>	(Ljava/lang/String;ILjava/lang/String;)V
      //   196: athrow
      //   197: new 2	org/jsoup/helper/HttpConnection$Response
      //   200: dup
      //   201: aload_1
      //   202: invokespecial 236	org/jsoup/helper/HttpConnection$Response:<init>	(Lorg/jsoup/helper/HttpConnection$Response;)V
      //   205: astore 7
      //   207: aload 7
      //   209: aload_3
      //   210: aload_1
      //   211: invokespecial 240	org/jsoup/helper/HttpConnection$Response:setupFromConnection	(Ljava/net/HttpURLConnection;Lorg/jsoup/Connection$Response;)V
      //   214: iload 6
      //   216: ifeq +188 -> 404
      //   219: aload_0
      //   220: invokeinterface 243 1 0
      //   225: ifeq +179 -> 404
      //   228: aload_0
      //   229: getstatic 194	org/jsoup/Connection$Method:GET	Lorg/jsoup/Connection$Method;
      //   232: invokeinterface 246 2 0
      //   237: pop
      //   238: aload_0
      //   239: invokeinterface 198 1 0
      //   244: invokeinterface 249 1 0
      //   249: aload 7
      //   251: ldc 251
      //   253: invokevirtual 255	org/jsoup/helper/HttpConnection$Response:header	(Ljava/lang/String;)Ljava/lang/String;
      //   256: astore 16
      //   258: aload 16
      //   260: ifnull +35 -> 295
      //   263: aload 16
      //   265: ldc_w 257
      //   268: invokevirtual 261	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   271: ifeq +24 -> 295
      //   274: aload 16
      //   276: bipush 6
      //   278: invokevirtual 265	java/lang/String:charAt	(I)C
      //   281: bipush 47
      //   283: if_icmpeq +12 -> 295
      //   286: aload 16
      //   288: bipush 6
      //   290: invokevirtual 269	java/lang/String:substring	(I)Ljava/lang/String;
      //   293: astore 16
      //   295: aload_0
      //   296: new 71	java/net/URL
      //   299: dup
      //   300: aload_0
      //   301: invokeinterface 69 1 0
      //   306: aload 16
      //   308: invokestatic 274	org/jsoup/helper/HttpConnection:access$200	(Ljava/lang/String;)Ljava/lang/String;
      //   311: invokespecial 277	java/net/URL:<init>	(Ljava/net/URL;Ljava/lang/String;)V
      //   314: invokeinterface 280 2 0
      //   319: pop
      //   320: aload 7
      //   322: getfield 283	org/jsoup/helper/HttpConnection$Response:cookies	Ljava/util/Map;
      //   325: invokeinterface 138 1 0
      //   330: invokeinterface 144 1 0
      //   335: astore 18
      //   337: aload 18
      //   339: invokeinterface 150 1 0
      //   344: ifeq +45 -> 389
      //   347: aload 18
      //   349: invokeinterface 154 1 0
      //   354: checkcast 156	java/util/Map$Entry
      //   357: astore 20
      //   359: aload_0
      //   360: aload 20
      //   362: invokeinterface 159 1 0
      //   367: checkcast 57	java/lang/String
      //   370: aload 20
      //   372: invokeinterface 162 1 0
      //   377: checkcast 57	java/lang/String
      //   380: invokeinterface 287 3 0
      //   385: pop
      //   386: goto -49 -> 337
      //   389: aload_0
      //   390: aload 7
      //   392: invokestatic 167	org/jsoup/helper/HttpConnection$Response:execute	(Lorg/jsoup/Connection$Request;Lorg/jsoup/helper/HttpConnection$Response;)Lorg/jsoup/helper/HttpConnection$Response;
      //   395: astore 19
      //   397: aload_3
      //   398: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
      //   401: aload 19
      //   403: areturn
      //   404: aload 7
      //   406: aload_0
      //   407: putfield 292	org/jsoup/helper/HttpConnection$Response:req	Lorg/jsoup/Connection$Request;
      //   410: aload 7
      //   412: invokevirtual 294	org/jsoup/helper/HttpConnection$Response:contentType	()Ljava/lang/String;
      //   415: astore 8
      //   417: aload 8
      //   419: ifnull +70 -> 489
      //   422: aload_0
      //   423: invokeinterface 297 1 0
      //   428: ifne +61 -> 489
      //   431: aload 8
      //   433: ldc_w 299
      //   436: invokevirtual 261	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   439: ifne +50 -> 489
      //   442: aload 8
      //   444: ldc_w 301
      //   447: invokevirtual 261	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   450: ifne +39 -> 489
      //   453: getstatic 36	org/jsoup/helper/HttpConnection$Response:xmlContentTypeRxp	Ljava/util/regex/Pattern;
      //   456: aload 8
      //   458: invokevirtual 305	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   461: invokevirtual 310	java/util/regex/Matcher:matches	()Z
      //   464: ifne +25 -> 489
      //   467: new 312	org/jsoup/UnsupportedMimeTypeException
      //   470: dup
      //   471: ldc_w 314
      //   474: aload 8
      //   476: aload_0
      //   477: invokeinterface 69 1 0
      //   482: invokevirtual 231	java/net/URL:toString	()Ljava/lang/String;
      //   485: invokespecial 317	org/jsoup/UnsupportedMimeTypeException:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   488: athrow
      //   489: aconst_null
      //   490: astore 9
      //   492: aload_3
      //   493: invokevirtual 321	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
      //   496: ifnull +12 -> 508
      //   499: aload_3
      //   500: invokevirtual 321	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
      //   503: astore 11
      //   505: goto +9 -> 514
      //   508: aload_3
      //   509: invokevirtual 324	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   512: astore 11
      //   514: aload 7
      //   516: ldc_w 326
      //   519: invokevirtual 329	org/jsoup/helper/HttpConnection$Response:hasHeader	(Ljava/lang/String;)Z
      //   522: istore 12
      //   524: aconst_null
      //   525: astore 9
      //   527: iload 12
      //   529: ifeq +48 -> 577
      //   532: aload 7
      //   534: ldc_w 326
      //   537: invokevirtual 255	org/jsoup/helper/HttpConnection$Response:header	(Ljava/lang/String;)Ljava/lang/String;
      //   540: ldc_w 331
      //   543: invokevirtual 334	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   546: istore 14
      //   548: aconst_null
      //   549: astore 9
      //   551: iload 14
      //   553: ifeq +24 -> 577
      //   556: new 336	java/io/BufferedInputStream
      //   559: dup
      //   560: new 338	java/util/zip/GZIPInputStream
      //   563: dup
      //   564: aload 11
      //   566: invokespecial 341	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
      //   569: invokespecial 342	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   572: astore 13
      //   574: goto +14 -> 588
      //   577: new 336	java/io/BufferedInputStream
      //   580: dup
      //   581: aload 11
      //   583: invokespecial 342	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   586: astore 13
      //   588: aload 13
      //   590: astore 9
      //   592: aload 7
      //   594: aload 9
      //   596: aload_0
      //   597: invokeinterface 345 1 0
      //   602: invokestatic 351	org/jsoup/helper/DataUtil:readToByteBuffer	(Ljava/io/InputStream;I)Ljava/nio/ByteBuffer;
      //   605: putfield 353	org/jsoup/helper/HttpConnection$Response:byteData	Ljava/nio/ByteBuffer;
      //   608: aload 7
      //   610: aload 7
      //   612: getfield 355	org/jsoup/helper/HttpConnection$Response:contentType	Ljava/lang/String;
      //   615: invokestatic 358	org/jsoup/helper/DataUtil:getCharsetFromContentType	(Ljava/lang/String;)Ljava/lang/String;
      //   618: putfield 360	org/jsoup/helper/HttpConnection$Response:charset	Ljava/lang/String;
      //   621: aload 9
      //   623: ifnull +8 -> 631
      //   626: aload 9
      //   628: invokevirtual 365	java/io/InputStream:close	()V
      //   631: aload 11
      //   633: ifnull +8 -> 641
      //   636: aload 11
      //   638: invokevirtual 365	java/io/InputStream:close	()V
      //   641: aload_3
      //   642: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
      //   645: aload 7
      //   647: iconst_1
      //   648: putfield 42	org/jsoup/helper/HttpConnection$Response:executed	Z
      //   651: aload 7
      //   653: areturn
      //   654: astore 10
      //   656: goto +8 -> 664
      //   659: astore 10
      //   661: aconst_null
      //   662: astore 11
      //   664: aload 9
      //   666: ifnull +8 -> 674
      //   669: aload 9
      //   671: invokevirtual 365	java/io/InputStream:close	()V
      //   674: aload 11
      //   676: ifnull +8 -> 684
      //   679: aload 11
      //   681: invokevirtual 365	java/io/InputStream:close	()V
      //   684: aload 10
      //   686: athrow
      //   687: astore 4
      //   689: aload_3
      //   690: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
      //   693: aload 4
      //   695: athrow
      //   696: iconst_1
      //   697: istore 6
      //   699: goto -502 -> 197
      //
      // Exception table:
      //   from	to	target	type
      //   514	621	654	finally
      //   492	514	659	finally
      //   79	397	687	finally
      //   404	489	687	finally
      //   626	641	687	finally
      //   669	687	687	finally
    }

    private static String getRequestCookieString(Connection.Request paramRequest)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = paramRequest.cookies().entrySet().iterator();
      int i = 1;
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (i == 0)
          localStringBuilder.append("; ");
        else
          i = 0;
        localStringBuilder.append((String)localEntry.getKey());
        localStringBuilder.append('=');
        localStringBuilder.append((String)localEntry.getValue());
      }
      return localStringBuilder.toString();
    }

    private static void serialiseRequestUrl(Connection.Request paramRequest)
    {
      URL localURL = paramRequest.url();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localURL.getProtocol());
      localStringBuilder.append("://");
      localStringBuilder.append(localURL.getAuthority());
      localStringBuilder.append(localURL.getPath());
      localStringBuilder.append("?");
      int i;
      if (localURL.getQuery() != null)
      {
        localStringBuilder.append(localURL.getQuery());
        i = 0;
      }
      else
      {
        i = 1;
      }
      Iterator localIterator = paramRequest.data().iterator();
      while (localIterator.hasNext())
      {
        Connection.KeyVal localKeyVal = (Connection.KeyVal)localIterator.next();
        if (i == 0)
          localStringBuilder.append('&');
        else
          i = 0;
        localStringBuilder.append(URLEncoder.encode(localKeyVal.key(), "UTF-8"));
        localStringBuilder.append('=');
        localStringBuilder.append(URLEncoder.encode(localKeyVal.value(), "UTF-8"));
      }
      paramRequest.url(new URL(localStringBuilder.toString()));
      paramRequest.data().clear();
    }

    private void setupFromConnection(HttpURLConnection paramHttpURLConnection, Connection.Response paramResponse)
    {
      this.method = Connection.Method.valueOf(paramHttpURLConnection.getRequestMethod());
      this.url = paramHttpURLConnection.getURL();
      this.statusCode = paramHttpURLConnection.getResponseCode();
      this.statusMessage = paramHttpURLConnection.getResponseMessage();
      this.contentType = paramHttpURLConnection.getContentType();
      processResponseHeaders(paramHttpURLConnection.getHeaderFields());
      if (paramResponse != null)
      {
        Iterator localIterator = paramResponse.cookies().entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if (!hasCookie((String)localEntry.getKey()))
            cookie((String)localEntry.getKey(), (String)localEntry.getValue());
        }
      }
    }

    private static void writePost(Collection<Connection.KeyVal> paramCollection, OutputStream paramOutputStream)
    {
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, "UTF-8");
      Iterator localIterator = paramCollection.iterator();
      int i = 1;
      while (localIterator.hasNext())
      {
        Connection.KeyVal localKeyVal = (Connection.KeyVal)localIterator.next();
        if (i == 0)
          localOutputStreamWriter.append('&');
        else
          i = 0;
        localOutputStreamWriter.write(URLEncoder.encode(localKeyVal.key(), "UTF-8"));
        localOutputStreamWriter.write(61);
        localOutputStreamWriter.write(URLEncoder.encode(localKeyVal.value(), "UTF-8"));
      }
      localOutputStreamWriter.close();
    }

    public String body()
    {
      Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
      String str;
      if (this.charset == null)
        str = Charset.forName("UTF-8").decode(this.byteData).toString();
      else
        str = Charset.forName(this.charset).decode(this.byteData).toString();
      this.byteData.rewind();
      return str;
    }

    public byte[] bodyAsBytes()
    {
      Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
      return this.byteData.array();
    }

    public String charset()
    {
      return this.charset;
    }

    public String contentType()
    {
      return this.contentType;
    }

    public Document parse()
    {
      Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
      Document localDocument = DataUtil.parseByteData(this.byteData, this.charset, this.url.toExternalForm(), this.req.parser());
      this.byteData.rewind();
      this.charset = localDocument.outputSettings().charset().name();
      return localDocument;
    }

    void processResponseHeaders(Map<String, List<String>> paramMap)
    {
      Iterator localIterator1 = paramMap.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        String str1 = (String)localEntry.getKey();
        if (str1 != null)
        {
          List localList = (List)localEntry.getValue();
          if (str1.equalsIgnoreCase("Set-Cookie"))
          {
            Iterator localIterator2 = localList.iterator();
            while (localIterator2.hasNext())
            {
              String str2 = (String)localIterator2.next();
              if (str2 != null)
              {
                TokenQueue localTokenQueue = new TokenQueue(str2);
                String str3 = localTokenQueue.chompTo("=").trim();
                String str4 = localTokenQueue.consumeTo(";").trim();
                if (str4 == null)
                  str4 = "";
                if ((str3 != null) && (str3.length() > 0))
                  cookie(str3, str4);
              }
            }
          }
          else if (!localList.isEmpty())
          {
            header(str1, (String)localList.get(0));
          }
        }
      }
    }

    public int statusCode()
    {
      return this.statusCode;
    }

    public String statusMessage()
    {
      return this.statusMessage;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.helper.HttpConnection
 * JD-Core Version:    0.6.1
 */