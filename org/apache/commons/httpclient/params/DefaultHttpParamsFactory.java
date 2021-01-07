package org.apache.commons.httpclient.params;

public class DefaultHttpParamsFactory
  implements HttpParamsFactory
{
  static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
  private HttpParams httpParams;

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
  protected HttpParams createParams()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 38	org/apache/commons/httpclient/params/HttpClientParams
    //   5: dup
    //   6: aconst_null
    //   7: invokespecial 41	org/apache/commons/httpclient/params/HttpClientParams:<init>	(Lorg/apache/commons/httpclient/params/HttpParams;)V
    //   10: astore_2
    //   11: aload_2
    //   12: ldc 43
    //   14: ldc 45
    //   16: invokevirtual 49	org/apache/commons/httpclient/params/HttpClientParams:setParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   19: aload_2
    //   20: getstatic 55	org/apache/commons/httpclient/HttpVersion:HTTP_1_1	Lorg/apache/commons/httpclient/HttpVersion;
    //   23: invokevirtual 59	org/apache/commons/httpclient/params/HttpClientParams:setVersion	(Lorg/apache/commons/httpclient/HttpVersion;)V
    //   26: getstatic 61	org/apache/commons/httpclient/params/DefaultHttpParamsFactory:class$org$apache$commons$httpclient$SimpleHttpConnectionManager	Ljava/lang/Class;
    //   29: ifnonnull +16 -> 45
    //   32: ldc 63
    //   34: invokestatic 65	org/apache/commons/httpclient/params/DefaultHttpParamsFactory:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   37: astore_3
    //   38: aload_3
    //   39: putstatic 61	org/apache/commons/httpclient/params/DefaultHttpParamsFactory:class$org$apache$commons$httpclient$SimpleHttpConnectionManager	Ljava/lang/Class;
    //   42: goto +7 -> 49
    //   45: getstatic 61	org/apache/commons/httpclient/params/DefaultHttpParamsFactory:class$org$apache$commons$httpclient$SimpleHttpConnectionManager	Ljava/lang/Class;
    //   48: astore_3
    //   49: aload_2
    //   50: aload_3
    //   51: invokevirtual 69	org/apache/commons/httpclient/params/HttpClientParams:setConnectionManagerClass	(Ljava/lang/Class;)V
    //   54: aload_2
    //   55: ldc 71
    //   57: invokevirtual 74	org/apache/commons/httpclient/params/HttpClientParams:setCookiePolicy	(Ljava/lang/String;)V
    //   60: aload_2
    //   61: ldc 76
    //   63: invokevirtual 79	org/apache/commons/httpclient/params/HttpClientParams:setHttpElementCharset	(Ljava/lang/String;)V
    //   66: aload_2
    //   67: ldc 81
    //   69: invokevirtual 84	org/apache/commons/httpclient/params/HttpClientParams:setContentCharset	(Ljava/lang/String;)V
    //   72: aload_2
    //   73: ldc 86
    //   75: new 88	org/apache/commons/httpclient/DefaultHttpMethodRetryHandler
    //   78: dup
    //   79: invokespecial 89	org/apache/commons/httpclient/DefaultHttpMethodRetryHandler:<init>	()V
    //   82: invokevirtual 49	org/apache/commons/httpclient/params/HttpClientParams:setParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   85: new 91	java/util/ArrayList
    //   88: dup
    //   89: invokespecial 92	java/util/ArrayList:<init>	()V
    //   92: astore 4
    //   94: aload 4
    //   96: bipush 14
    //   98: anewarray 94	java/lang/String
    //   101: dup
    //   102: iconst_0
    //   103: ldc 96
    //   105: aastore
    //   106: dup
    //   107: iconst_1
    //   108: ldc 98
    //   110: aastore
    //   111: dup
    //   112: iconst_2
    //   113: ldc 100
    //   115: aastore
    //   116: dup
    //   117: iconst_3
    //   118: ldc 102
    //   120: aastore
    //   121: dup
    //   122: iconst_4
    //   123: ldc 104
    //   125: aastore
    //   126: dup
    //   127: iconst_5
    //   128: ldc 106
    //   130: aastore
    //   131: dup
    //   132: bipush 6
    //   134: ldc 108
    //   136: aastore
    //   137: dup
    //   138: bipush 7
    //   140: ldc 110
    //   142: aastore
    //   143: dup
    //   144: bipush 8
    //   146: ldc 112
    //   148: aastore
    //   149: dup
    //   150: bipush 9
    //   152: ldc 114
    //   154: aastore
    //   155: dup
    //   156: bipush 10
    //   158: ldc 116
    //   160: aastore
    //   161: dup
    //   162: bipush 11
    //   164: ldc 118
    //   166: aastore
    //   167: dup
    //   168: bipush 12
    //   170: ldc 120
    //   172: aastore
    //   173: dup
    //   174: bipush 13
    //   176: ldc 122
    //   178: aastore
    //   179: invokestatic 128	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   182: invokevirtual 132	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   185: pop
    //   186: aload_2
    //   187: ldc 134
    //   189: aload 4
    //   191: invokevirtual 49	org/apache/commons/httpclient/params/HttpClientParams:setParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   194: ldc 136
    //   196: invokestatic 142	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   199: astore 6
    //   201: goto +6 -> 207
    //   204: aconst_null
    //   205: astore 6
    //   207: aload 6
    //   209: ifnull +11 -> 220
    //   212: aload_2
    //   213: ldc 43
    //   215: aload 6
    //   217: invokevirtual 49	org/apache/commons/httpclient/params/HttpClientParams:setParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   220: ldc 144
    //   222: invokestatic 142	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   225: astore 7
    //   227: goto +6 -> 233
    //   230: aconst_null
    //   231: astore 7
    //   233: aload 7
    //   235: ifnull +54 -> 289
    //   238: aload 7
    //   240: invokevirtual 147	java/lang/String:trim	()Ljava/lang/String;
    //   243: invokevirtual 150	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   246: astore 10
    //   248: aload 10
    //   250: ldc 152
    //   252: invokevirtual 156	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   255: ifeq +15 -> 270
    //   258: aload_2
    //   259: ldc 158
    //   261: getstatic 164	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   264: invokevirtual 49	org/apache/commons/httpclient/params/HttpClientParams:setParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   267: goto +22 -> 289
    //   270: aload 10
    //   272: ldc 166
    //   274: invokevirtual 156	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   277: ifeq +12 -> 289
    //   280: aload_2
    //   281: ldc 158
    //   283: getstatic 169	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   286: invokevirtual 49	org/apache/commons/httpclient/params/HttpClientParams:setParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   289: ldc 171
    //   291: invokestatic 142	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   294: astore 9
    //   296: aload 9
    //   298: astore_1
    //   299: goto +4 -> 303
    //   302: pop
    //   303: aload_1
    //   304: ifnull +54 -> 358
    //   307: ldc 173
    //   309: aload_1
    //   310: invokevirtual 177	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   313: ifeq +12 -> 325
    //   316: aload_2
    //   317: ldc 179
    //   319: invokevirtual 74	org/apache/commons/httpclient/params/HttpClientParams:setCookiePolicy	(Ljava/lang/String;)V
    //   322: goto +36 -> 358
    //   325: ldc 181
    //   327: aload_1
    //   328: invokevirtual 177	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   331: ifeq +12 -> 343
    //   334: aload_2
    //   335: ldc 183
    //   337: invokevirtual 74	org/apache/commons/httpclient/params/HttpClientParams:setCookiePolicy	(Ljava/lang/String;)V
    //   340: goto +18 -> 358
    //   343: ldc 185
    //   345: aload_1
    //   346: invokevirtual 177	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   349: ifeq +9 -> 358
    //   352: aload_2
    //   353: ldc 187
    //   355: invokevirtual 74	org/apache/commons/httpclient/params/HttpClientParams:setCookiePolicy	(Ljava/lang/String;)V
    //   358: aload_2
    //   359: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   194	201	204	java/lang/SecurityException
    //   220	227	230	java/lang/SecurityException
    //   289	296	302	java/lang/SecurityException
  }

  public HttpParams getDefaultParams()
  {
    try
    {
      if (this.httpParams == null)
        this.httpParams = createParams();
      HttpParams localHttpParams = this.httpParams;
      return localHttpParams;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.DefaultHttpParamsFactory
 * JD-Core Version:    0.6.1
 */