package org.apache.commons.httpclient.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DigestScheme extends RFC2617Scheme
{
  private static final char[] HEXADECIMAL = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final Log LOG;
  private static final String NC = "00000001";
  private static final int QOP_AUTH = 2;
  private static final int QOP_AUTH_INT = 1;
  private static final int QOP_MISSING;
  static Class class$org$apache$commons$httpclient$auth$DigestScheme;
  private String cnonce;
  private boolean complete = false;
  private final ParameterFormatter formatter = new ParameterFormatter();
  private int qopVariant = 0;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$DigestScheme == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.DigestScheme");
      class$org$apache$commons$httpclient$auth$DigestScheme = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$auth$DigestScheme;
    }
    LOG = LogFactory.getLog(localClass);
  }

  public DigestScheme()
  {
  }

  public DigestScheme(String paramString)
  {
    this();
    processChallenge(paramString);
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

  // ERROR //
  public static String createCnonce()
  {
    // Byte code:
    //   0: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 99
    //   5: invokeinterface 105 2 0
    //   10: ldc 107
    //   12: invokestatic 113	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   15: astore_0
    //   16: aload_0
    //   17: invokestatic 119	java/lang/System:currentTimeMillis	()J
    //   20: invokestatic 125	java/lang/Long:toString	(J)Ljava/lang/String;
    //   23: invokestatic 131	org/apache/commons/httpclient/util/EncodingUtil:getAsciiBytes	(Ljava/lang/String;)[B
    //   26: invokevirtual 135	java/security/MessageDigest:digest	([B)[B
    //   29: invokestatic 139	org/apache/commons/httpclient/auth/DigestScheme:encode	([B)Ljava/lang/String;
    //   32: areturn
    //   33: new 141	org/apache/commons/httpclient/HttpClientError
    //   36: dup
    //   37: ldc 143
    //   39: invokespecial 144	org/apache/commons/httpclient/HttpClientError:<init>	(Ljava/lang/String;)V
    //   42: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	16	33	java/security/NoSuchAlgorithmException
  }

  // ERROR //
  private String createDigest(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 150
    //   5: invokeinterface 105 2 0
    //   10: aload_0
    //   11: ldc 152
    //   13: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore_3
    //   17: aload_0
    //   18: ldc 158
    //   20: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   23: astore 4
    //   25: aload_0
    //   26: ldc 160
    //   28: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 5
    //   33: aload_0
    //   34: ldc 162
    //   36: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   39: astore 6
    //   41: aload_0
    //   42: ldc 164
    //   44: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   47: astore 7
    //   49: aload_0
    //   50: ldc 166
    //   52: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   55: astore 8
    //   57: aload 8
    //   59: ifnonnull +7 -> 66
    //   62: ldc 107
    //   64: astore 8
    //   66: aload_0
    //   67: ldc 168
    //   69: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   72: astore 9
    //   74: aload 9
    //   76: ifnonnull +7 -> 83
    //   79: ldc 170
    //   81: astore 9
    //   83: aload_0
    //   84: getfield 67	org/apache/commons/httpclient/auth/DigestScheme:qopVariant	I
    //   87: iconst_1
    //   88: if_icmpne +23 -> 111
    //   91: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   94: ldc 172
    //   96: invokeinterface 175 2 0
    //   101: new 177	org/apache/commons/httpclient/auth/AuthenticationException
    //   104: dup
    //   105: ldc 179
    //   107: invokespecial 180	org/apache/commons/httpclient/auth/AuthenticationException:<init>	(Ljava/lang/String;)V
    //   110: athrow
    //   111: ldc 107
    //   113: invokestatic 113	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   116: astore 10
    //   118: new 182	java/lang/StringBuffer
    //   121: dup
    //   122: iconst_2
    //   123: aload_1
    //   124: invokevirtual 188	java/lang/String:length	()I
    //   127: aload 4
    //   129: invokevirtual 188	java/lang/String:length	()I
    //   132: iadd
    //   133: aload_2
    //   134: invokevirtual 188	java/lang/String:length	()I
    //   137: iadd
    //   138: iadd
    //   139: invokespecial 191	java/lang/StringBuffer:<init>	(I)V
    //   142: astore 11
    //   144: aload 11
    //   146: aload_1
    //   147: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   150: pop
    //   151: aload 11
    //   153: bipush 58
    //   155: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   158: pop
    //   159: aload 11
    //   161: aload 4
    //   163: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   166: pop
    //   167: aload 11
    //   169: bipush 58
    //   171: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   174: pop
    //   175: aload 11
    //   177: aload_2
    //   178: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   181: pop
    //   182: aload 11
    //   184: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   187: astore 17
    //   189: aload 8
    //   191: ldc 202
    //   193: invokevirtual 206	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   196: ifeq +102 -> 298
    //   199: aload 10
    //   201: aload 17
    //   203: aload 9
    //   205: invokestatic 210	org/apache/commons/httpclient/util/EncodingUtil:getBytes	(Ljava/lang/String;Ljava/lang/String;)[B
    //   208: invokevirtual 135	java/security/MessageDigest:digest	([B)[B
    //   211: invokestatic 139	org/apache/commons/httpclient/auth/DigestScheme:encode	([B)Ljava/lang/String;
    //   214: astore 54
    //   216: new 182	java/lang/StringBuffer
    //   219: dup
    //   220: iconst_2
    //   221: aload 54
    //   223: invokevirtual 188	java/lang/String:length	()I
    //   226: aload 5
    //   228: invokevirtual 188	java/lang/String:length	()I
    //   231: iadd
    //   232: aload_0
    //   233: getfield 212	org/apache/commons/httpclient/auth/DigestScheme:cnonce	Ljava/lang/String;
    //   236: invokevirtual 188	java/lang/String:length	()I
    //   239: iadd
    //   240: iadd
    //   241: invokespecial 191	java/lang/StringBuffer:<init>	(I)V
    //   244: astore 55
    //   246: aload 55
    //   248: aload 54
    //   250: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   253: pop
    //   254: aload 55
    //   256: bipush 58
    //   258: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   261: pop
    //   262: aload 55
    //   264: aload 5
    //   266: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   269: pop
    //   270: aload 55
    //   272: bipush 58
    //   274: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   277: pop
    //   278: aload 55
    //   280: aload_0
    //   281: getfield 212	org/apache/commons/httpclient/auth/DigestScheme:cnonce	Ljava/lang/String;
    //   284: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   287: pop
    //   288: aload 55
    //   290: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   293: astore 17
    //   295: goto +63 -> 358
    //   298: aload 8
    //   300: ldc 107
    //   302: invokevirtual 206	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   305: ifne +53 -> 358
    //   308: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   311: astore 49
    //   313: new 182	java/lang/StringBuffer
    //   316: dup
    //   317: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   320: astore 50
    //   322: aload 50
    //   324: ldc 215
    //   326: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   329: pop
    //   330: aload 50
    //   332: aload 8
    //   334: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   337: pop
    //   338: aload 50
    //   340: ldc 217
    //   342: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   345: pop
    //   346: aload 49
    //   348: aload 50
    //   350: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   353: invokeinterface 175 2 0
    //   358: aload 10
    //   360: aload 17
    //   362: aload 9
    //   364: invokestatic 210	org/apache/commons/httpclient/util/EncodingUtil:getBytes	(Ljava/lang/String;Ljava/lang/String;)[B
    //   367: invokevirtual 135	java/security/MessageDigest:digest	([B)[B
    //   370: invokestatic 139	org/apache/commons/httpclient/auth/DigestScheme:encode	([B)Ljava/lang/String;
    //   373: astore 18
    //   375: aload_0
    //   376: getfield 67	org/apache/commons/httpclient/auth/DigestScheme:qopVariant	I
    //   379: iconst_1
    //   380: if_icmpne +19 -> 399
    //   383: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   386: ldc 219
    //   388: invokeinterface 222 2 0
    //   393: aconst_null
    //   394: astore 23
    //   396: goto +42 -> 438
    //   399: new 182	java/lang/StringBuffer
    //   402: dup
    //   403: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   406: astore 19
    //   408: aload 19
    //   410: aload 7
    //   412: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   415: pop
    //   416: aload 19
    //   418: ldc 224
    //   420: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   423: pop
    //   424: aload 19
    //   426: aload_3
    //   427: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   430: pop
    //   431: aload 19
    //   433: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   436: astore 23
    //   438: aload 10
    //   440: aload 23
    //   442: invokestatic 131	org/apache/commons/httpclient/util/EncodingUtil:getAsciiBytes	(Ljava/lang/String;)[B
    //   445: invokevirtual 135	java/security/MessageDigest:digest	([B)[B
    //   448: invokestatic 139	org/apache/commons/httpclient/auth/DigestScheme:encode	([B)Ljava/lang/String;
    //   451: astore 24
    //   453: aload_0
    //   454: getfield 67	org/apache/commons/httpclient/auth/DigestScheme:qopVariant	I
    //   457: ifne +89 -> 546
    //   460: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   463: ldc 226
    //   465: invokeinterface 229 2 0
    //   470: new 182	java/lang/StringBuffer
    //   473: dup
    //   474: aload 18
    //   476: invokevirtual 188	java/lang/String:length	()I
    //   479: aload 5
    //   481: invokevirtual 188	java/lang/String:length	()I
    //   484: iadd
    //   485: aload 24
    //   487: invokevirtual 188	java/lang/String:length	()I
    //   490: iadd
    //   491: invokespecial 191	java/lang/StringBuffer:<init>	(I)V
    //   494: astore 43
    //   496: aload 43
    //   498: aload 18
    //   500: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   503: pop
    //   504: aload 43
    //   506: bipush 58
    //   508: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   511: pop
    //   512: aload 43
    //   514: aload 5
    //   516: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   519: pop
    //   520: aload 43
    //   522: bipush 58
    //   524: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   527: pop
    //   528: aload 43
    //   530: aload 24
    //   532: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   535: pop
    //   536: aload 43
    //   538: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   541: astore 38
    //   543: goto +207 -> 750
    //   546: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   549: invokeinterface 233 1 0
    //   554: ifeq +45 -> 599
    //   557: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   560: astore 39
    //   562: new 182	java/lang/StringBuffer
    //   565: dup
    //   566: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   569: astore 40
    //   571: aload 40
    //   573: ldc 235
    //   575: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   578: pop
    //   579: aload 40
    //   581: aload 6
    //   583: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   586: pop
    //   587: aload 39
    //   589: aload 40
    //   591: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   594: invokeinterface 229 2 0
    //   599: aload_0
    //   600: invokespecial 238	org/apache/commons/httpclient/auth/DigestScheme:getQopVariantString	()Ljava/lang/String;
    //   603: astore 25
    //   605: new 182	java/lang/StringBuffer
    //   608: dup
    //   609: iconst_5
    //   610: aload 18
    //   612: invokevirtual 188	java/lang/String:length	()I
    //   615: aload 5
    //   617: invokevirtual 188	java/lang/String:length	()I
    //   620: iadd
    //   621: ldc 12
    //   623: invokevirtual 188	java/lang/String:length	()I
    //   626: iadd
    //   627: aload_0
    //   628: getfield 212	org/apache/commons/httpclient/auth/DigestScheme:cnonce	Ljava/lang/String;
    //   631: invokevirtual 188	java/lang/String:length	()I
    //   634: iadd
    //   635: aload 25
    //   637: invokevirtual 188	java/lang/String:length	()I
    //   640: iadd
    //   641: aload 24
    //   643: invokevirtual 188	java/lang/String:length	()I
    //   646: iadd
    //   647: iadd
    //   648: invokespecial 191	java/lang/StringBuffer:<init>	(I)V
    //   651: astore 26
    //   653: aload 26
    //   655: aload 18
    //   657: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   660: pop
    //   661: aload 26
    //   663: bipush 58
    //   665: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   668: pop
    //   669: aload 26
    //   671: aload 5
    //   673: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   676: pop
    //   677: aload 26
    //   679: bipush 58
    //   681: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   684: pop
    //   685: aload 26
    //   687: ldc 12
    //   689: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   692: pop
    //   693: aload 26
    //   695: bipush 58
    //   697: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   700: pop
    //   701: aload 26
    //   703: aload_0
    //   704: getfield 212	org/apache/commons/httpclient/auth/DigestScheme:cnonce	Ljava/lang/String;
    //   707: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   710: pop
    //   711: aload 26
    //   713: bipush 58
    //   715: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   718: pop
    //   719: aload 26
    //   721: aload 25
    //   723: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   726: pop
    //   727: aload 26
    //   729: bipush 58
    //   731: invokevirtual 198	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   734: pop
    //   735: aload 26
    //   737: aload 24
    //   739: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   742: pop
    //   743: aload 26
    //   745: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   748: astore 38
    //   750: aload 10
    //   752: aload 38
    //   754: invokestatic 131	org/apache/commons/httpclient/util/EncodingUtil:getAsciiBytes	(Ljava/lang/String;)[B
    //   757: invokevirtual 135	java/security/MessageDigest:digest	([B)[B
    //   760: invokestatic 139	org/apache/commons/httpclient/auth/DigestScheme:encode	([B)Ljava/lang/String;
    //   763: areturn
    //   764: new 177	org/apache/commons/httpclient/auth/AuthenticationException
    //   767: dup
    //   768: ldc 143
    //   770: invokespecial 180	org/apache/commons/httpclient/auth/AuthenticationException:<init>	(Ljava/lang/String;)V
    //   773: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   111	118	764	java/lang/Exception
  }

  private String createDigestHeader(String paramString1, String paramString2)
  {
    LOG.trace("enter DigestScheme.createDigestHeader(String, Map, String)");
    String str1 = getParameter("uri");
    String str2 = getParameter("realm");
    String str3 = getParameter("nonce");
    String str4 = getParameter("opaque");
    String str5 = getParameter("algorithm");
    ArrayList localArrayList = new ArrayList(20);
    localArrayList.add(new NameValuePair("username", paramString1));
    localArrayList.add(new NameValuePair("realm", str2));
    localArrayList.add(new NameValuePair("nonce", str3));
    localArrayList.add(new NameValuePair("uri", str1));
    localArrayList.add(new NameValuePair("response", paramString2));
    if (this.qopVariant != 0)
    {
      localArrayList.add(new NameValuePair("qop", getQopVariantString()));
      localArrayList.add(new NameValuePair("nc", "00000001"));
      localArrayList.add(new NameValuePair("cnonce", this.cnonce));
    }
    if (str5 != null)
      localArrayList.add(new NameValuePair("algorithm", str5));
    if (str4 != null)
      localArrayList.add(new NameValuePair("opaque", str4));
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < localArrayList.size(); i++)
    {
      NameValuePair localNameValuePair = (NameValuePair)localArrayList.get(i);
      if (i > 0)
        localStringBuffer.append(", ");
      int j;
      if ((!"nc".equals(localNameValuePair.getName())) && (!"qop".equals(localNameValuePair.getName())))
        j = 0;
      else
        j = 1;
      this.formatter.setAlwaysUseQuotes(j ^ 0x1);
      this.formatter.format(localStringBuffer, localNameValuePair);
    }
    return localStringBuffer.toString();
  }

  private static String encode(byte[] paramArrayOfByte)
  {
    LOG.trace("enter DigestScheme.encode(byte[])");
    if (paramArrayOfByte.length != 16)
      return null;
    char[] arrayOfChar = new char[32];
    for (int i = 0; i < 16; i++)
    {
      int j = 0xF & paramArrayOfByte[i];
      int k = (0xF0 & paramArrayOfByte[i]) >> 4;
      int m = i * 2;
      arrayOfChar[m] = HEXADECIMAL[k];
      arrayOfChar[(m + 1)] = HEXADECIMAL[j];
    }
    return new String(arrayOfChar);
  }

  private String getQopVariantString()
  {
    String str;
    if (this.qopVariant == 1)
      str = "auth-int";
    else
      str = "auth";
    return str;
  }

  // ERROR //
  public String authenticate(org.apache.commons.httpclient.Credentials paramCredentials, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc_w 298
    //   6: invokeinterface 105 2 0
    //   11: aload_1
    //   12: checkcast 300	org/apache/commons/httpclient/UsernamePasswordCredentials
    //   15: astore 7
    //   17: aload_0
    //   18: invokevirtual 304	org/apache/commons/httpclient/auth/DigestScheme:getParameters	()Ljava/util/Map;
    //   21: ldc 164
    //   23: aload_2
    //   24: invokeinterface 310 3 0
    //   29: pop
    //   30: aload_0
    //   31: invokevirtual 304	org/apache/commons/httpclient/auth/DigestScheme:getParameters	()Ljava/util/Map;
    //   34: ldc 152
    //   36: aload_3
    //   37: invokeinterface 310 3 0
    //   42: pop
    //   43: aload_0
    //   44: aload 7
    //   46: invokevirtual 313	org/apache/commons/httpclient/UsernamePasswordCredentials:getUserName	()Ljava/lang/String;
    //   49: aload 7
    //   51: invokevirtual 316	org/apache/commons/httpclient/UsernamePasswordCredentials:getPassword	()Ljava/lang/String;
    //   54: invokespecial 318	org/apache/commons/httpclient/auth/DigestScheme:createDigest	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   57: astore 10
    //   59: new 182	java/lang/StringBuffer
    //   62: dup
    //   63: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   66: astore 11
    //   68: aload 11
    //   70: ldc_w 320
    //   73: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   76: pop
    //   77: aload 11
    //   79: aload_0
    //   80: aload 7
    //   82: invokevirtual 313	org/apache/commons/httpclient/UsernamePasswordCredentials:getUserName	()Ljava/lang/String;
    //   85: aload 10
    //   87: invokespecial 322	org/apache/commons/httpclient/auth/DigestScheme:createDigestHeader	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   93: pop
    //   94: aload 11
    //   96: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   99: areturn
    //   100: new 182	java/lang/StringBuffer
    //   103: dup
    //   104: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   107: astore 4
    //   109: aload 4
    //   111: ldc_w 324
    //   114: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   117: pop
    //   118: aload 4
    //   120: aload_1
    //   121: invokevirtual 330	java/lang/Object:getClass	()Ljava/lang/Class;
    //   124: invokevirtual 331	java/lang/Class:getName	()Ljava/lang/String;
    //   127: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   130: pop
    //   131: new 333	org/apache/commons/httpclient/auth/InvalidCredentialsException
    //   134: dup
    //   135: aload 4
    //   137: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   140: invokespecial 334	org/apache/commons/httpclient/auth/InvalidCredentialsException:<init>	(Ljava/lang/String;)V
    //   143: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   11	17	100	java/lang/ClassCastException
  }

  // ERROR //
  public String authenticate(org.apache.commons.httpclient.Credentials paramCredentials, org.apache.commons.httpclient.HttpMethod paramHttpMethod)
  {
    // Byte code:
    //   0: getstatic 44	org/apache/commons/httpclient/auth/DigestScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc_w 337
    //   6: invokeinterface 105 2 0
    //   11: aload_1
    //   12: checkcast 300	org/apache/commons/httpclient/UsernamePasswordCredentials
    //   15: astore 6
    //   17: aload_0
    //   18: invokevirtual 304	org/apache/commons/httpclient/auth/DigestScheme:getParameters	()Ljava/util/Map;
    //   21: ldc 164
    //   23: aload_2
    //   24: invokeinterface 340 1 0
    //   29: invokeinterface 310 3 0
    //   34: pop
    //   35: new 182	java/lang/StringBuffer
    //   38: dup
    //   39: aload_2
    //   40: invokeinterface 343 1 0
    //   45: invokespecial 344	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   48: astore 8
    //   50: aload_2
    //   51: invokeinterface 347 1 0
    //   56: astore 9
    //   58: aload 9
    //   60: ifnull +35 -> 95
    //   63: aload 9
    //   65: ldc_w 349
    //   68: invokevirtual 353	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   71: ifeq +12 -> 83
    //   74: aload 8
    //   76: ldc_w 349
    //   79: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   82: pop
    //   83: aload 8
    //   85: aload_2
    //   86: invokeinterface 347 1 0
    //   91: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   94: pop
    //   95: aload_0
    //   96: invokevirtual 304	org/apache/commons/httpclient/auth/DigestScheme:getParameters	()Ljava/util/Map;
    //   99: ldc 152
    //   101: aload 8
    //   103: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   106: invokeinterface 310 3 0
    //   111: pop
    //   112: aload_0
    //   113: ldc 168
    //   115: invokevirtual 156	org/apache/commons/httpclient/auth/DigestScheme:getParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   118: ifnonnull +24 -> 142
    //   121: aload_0
    //   122: invokevirtual 304	org/apache/commons/httpclient/auth/DigestScheme:getParameters	()Ljava/util/Map;
    //   125: ldc 168
    //   127: aload_2
    //   128: invokeinterface 357 1 0
    //   133: invokevirtual 362	org/apache/commons/httpclient/params/HttpMethodParams:getCredentialCharset	()Ljava/lang/String;
    //   136: invokeinterface 310 3 0
    //   141: pop
    //   142: aload_0
    //   143: aload 6
    //   145: invokevirtual 313	org/apache/commons/httpclient/UsernamePasswordCredentials:getUserName	()Ljava/lang/String;
    //   148: aload 6
    //   150: invokevirtual 316	org/apache/commons/httpclient/UsernamePasswordCredentials:getPassword	()Ljava/lang/String;
    //   153: invokespecial 318	org/apache/commons/httpclient/auth/DigestScheme:createDigest	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   156: astore 11
    //   158: new 182	java/lang/StringBuffer
    //   161: dup
    //   162: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   165: astore 12
    //   167: aload 12
    //   169: ldc_w 320
    //   172: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   175: pop
    //   176: aload 12
    //   178: aload_0
    //   179: aload 6
    //   181: invokevirtual 313	org/apache/commons/httpclient/UsernamePasswordCredentials:getUserName	()Ljava/lang/String;
    //   184: aload 11
    //   186: invokespecial 322	org/apache/commons/httpclient/auth/DigestScheme:createDigestHeader	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   189: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   192: pop
    //   193: aload 12
    //   195: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   198: areturn
    //   199: new 182	java/lang/StringBuffer
    //   202: dup
    //   203: invokespecial 213	java/lang/StringBuffer:<init>	()V
    //   206: astore_3
    //   207: aload_3
    //   208: ldc_w 324
    //   211: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   214: pop
    //   215: aload_3
    //   216: aload_1
    //   217: invokevirtual 330	java/lang/Object:getClass	()Ljava/lang/Class;
    //   220: invokevirtual 331	java/lang/Class:getName	()Ljava/lang/String;
    //   223: invokevirtual 195	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   226: pop
    //   227: new 333	org/apache/commons/httpclient/auth/InvalidCredentialsException
    //   230: dup
    //   231: aload_3
    //   232: invokevirtual 200	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   235: invokespecial 334	org/apache/commons/httpclient/auth/InvalidCredentialsException:<init>	(Ljava/lang/String;)V
    //   238: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   11	17	199	java/lang/ClassCastException
  }

  public String getID()
  {
    String str1 = getRealm();
    String str2 = getParameter("nonce");
    if (str2 != null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(str1);
      localStringBuffer.append("-");
      localStringBuffer.append(str2);
      str1 = localStringBuffer.toString();
    }
    return str1;
  }

  public String getSchemeName()
  {
    return "digest";
  }

  public boolean isComplete()
  {
    if ("true".equalsIgnoreCase(getParameter("stale")))
      return false;
    return this.complete;
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  public void processChallenge(String paramString)
  {
    super.processChallenge(paramString);
    if (getParameter("realm") == null)
      throw new MalformedChallengeException("missing realm in challange");
    if (getParameter("nonce") == null)
      throw new MalformedChallengeException("missing nonce in challange");
    String str1 = getParameter("qop");
    int i = 0;
    if (str1 != null)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(str1, ",");
      while (localStringTokenizer.hasMoreTokens())
      {
        String str2 = localStringTokenizer.nextToken().trim();
        if (str2.equals("auth"))
        {
          this.qopVariant = 2;
          break;
        }
        if (str2.equals("auth-int"))
        {
          this.qopVariant = 1;
        }
        else
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append("Unsupported qop detected: ");
          localStringBuffer.append(str2);
          localLog.warn(localStringBuffer.toString());
          i = 1;
        }
      }
    }
    if ((i != 0) && (this.qopVariant == 0))
      throw new MalformedChallengeException("None of the qop methods is supported");
    this.cnonce = createCnonce();
    this.complete = true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.DigestScheme
 * JD-Core Version:    0.6.1
 */