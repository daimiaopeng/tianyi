package com.tencent.bugly.proguard;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class s
{
  private static s b;
  public Map<String, String> a = null;
  private Context c;

  private s(Context paramContext)
  {
    this.c = paramContext;
  }

  public static s a(Context paramContext)
  {
    if (b == null)
      b = new s(paramContext);
    return b;
  }

  private static HttpURLConnection a(String paramString1, String paramString2)
  {
    try
    {
      URL localURL = new URL(paramString2);
      HttpURLConnection localHttpURLConnection;
      if ((paramString1 != null) && (paramString1.toLowerCase(Locale.US).contains("wap")))
      {
        InetSocketAddress localInetSocketAddress = new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")));
        localHttpURLConnection = (HttpURLConnection)localURL.openConnection(new Proxy(Proxy.Type.HTTP, localInetSocketAddress));
      }
      else
      {
        localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
      }
      localHttpURLConnection.setConnectTimeout(30000);
      localHttpURLConnection.setReadTimeout(10000);
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setUseCaches(false);
      localHttpURLConnection.setInstanceFollowRedirects(false);
      return localHttpURLConnection;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  private HttpURLConnection a(String paramString1, byte[] paramArrayOfByte, String paramString2, Map<String, String> paramMap)
  {
    if (paramString1 == null)
    {
      x.e("destUrl is null.", new Object[0]);
      return null;
    }
    HttpURLConnection localHttpURLConnection = a(paramString2, paramString1);
    if (localHttpURLConnection == null)
    {
      x.e("Failed to get HttpURLConnection object.", new Object[0]);
      return null;
    }
    try
    {
      localHttpURLConnection.setRequestProperty("wup_version", "3.0");
      if ((paramMap != null) && (paramMap.size() > 0))
      {
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localHttpURLConnection.setRequestProperty((String)localEntry.getKey(), URLEncoder.encode((String)localEntry.getValue(), "utf-8"));
        }
      }
      localHttpURLConnection.setRequestProperty("A37", URLEncoder.encode(paramString2, "utf-8"));
      localHttpURLConnection.setRequestProperty("A38", URLEncoder.encode(paramString2, "utf-8"));
      OutputStream localOutputStream = localHttpURLConnection.getOutputStream();
      if (paramArrayOfByte == null)
        localOutputStream.write(0);
      else
        localOutputStream.write(paramArrayOfByte);
      return localHttpURLConnection;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
      x.e("Failed to upload, please check your network.", new Object[0]);
    }
    return null;
  }

  private static Map<String, String> a(HttpURLConnection paramHttpURLConnection)
  {
    HashMap localHashMap = new HashMap();
    Map localMap = paramHttpURLConnection.getHeaderFields();
    if ((localMap != null) && (localMap.size() != 0))
    {
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        List localList = (List)localMap.get(str);
        if (localList.size() > 0)
          localHashMap.put(str, localList.get(0));
      }
      return localHashMap;
    }
    return null;
  }

  // ERROR //
  private static byte[] b(HttpURLConnection paramHttpURLConnection)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 232	java/io/BufferedInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokevirtual 236	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   14: invokespecial 239	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_1
    //   18: new 241	java/io/ByteArrayOutputStream
    //   21: dup
    //   22: invokespecial 242	java/io/ByteArrayOutputStream:<init>	()V
    //   25: astore_2
    //   26: sipush 1024
    //   29: newarray byte
    //   31: astore 7
    //   33: aload_1
    //   34: aload 7
    //   36: invokevirtual 246	java/io/BufferedInputStream:read	([B)I
    //   39: istore 8
    //   41: iload 8
    //   43: ifle +15 -> 58
    //   46: aload_2
    //   47: aload 7
    //   49: iconst_0
    //   50: iload 8
    //   52: invokevirtual 249	java/io/ByteArrayOutputStream:write	([BII)V
    //   55: goto -22 -> 33
    //   58: aload_2
    //   59: invokevirtual 252	java/io/ByteArrayOutputStream:flush	()V
    //   62: aload_2
    //   63: invokevirtual 256	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   66: astore 9
    //   68: aload_1
    //   69: invokevirtual 259	java/io/BufferedInputStream:close	()V
    //   72: goto +10 -> 82
    //   75: astore 10
    //   77: aload 10
    //   79: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   82: aload 9
    //   84: areturn
    //   85: astore 5
    //   87: goto +13 -> 100
    //   90: astore_3
    //   91: aconst_null
    //   92: astore_1
    //   93: goto +41 -> 134
    //   96: astore 5
    //   98: aconst_null
    //   99: astore_1
    //   100: aload 5
    //   102: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   105: ifne +8 -> 113
    //   108: aload 5
    //   110: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   113: aload_1
    //   114: ifnull +17 -> 131
    //   117: aload_1
    //   118: invokevirtual 259	java/io/BufferedInputStream:close	()V
    //   121: goto +10 -> 131
    //   124: astore 6
    //   126: aload 6
    //   128: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   131: aconst_null
    //   132: areturn
    //   133: astore_3
    //   134: aload_1
    //   135: ifnull +17 -> 152
    //   138: aload_1
    //   139: invokevirtual 259	java/io/BufferedInputStream:close	()V
    //   142: goto +10 -> 152
    //   145: astore 4
    //   147: aload 4
    //   149: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   152: aload_3
    //   153: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   68	72	75	java/lang/Throwable
    //   18	68	85	java/lang/Throwable
    //   6	18	90	finally
    //   6	18	96	java/lang/Throwable
    //   117	121	124	java/lang/Throwable
    //   18	68	133	finally
    //   100	113	133	finally
    //   138	142	145	java/lang/Throwable
  }

  // ERROR //
  public final byte[] a(String paramString, byte[] paramArrayOfByte, v paramv, Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +16 -> 17
    //   4: ldc_w 264
    //   7: iconst_0
    //   8: anewarray 4	java/lang/Object
    //   11: invokestatic 132	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   14: pop
    //   15: aconst_null
    //   16: areturn
    //   17: aload_2
    //   18: ifnonnull +9 -> 27
    //   21: lconst_0
    //   22: lstore 5
    //   24: goto +8 -> 32
    //   27: aload_2
    //   28: arraylength
    //   29: i2l
    //   30: lstore 5
    //   32: iconst_4
    //   33: anewarray 4	java/lang/Object
    //   36: astore 7
    //   38: aload 7
    //   40: iconst_0
    //   41: aload_1
    //   42: aastore
    //   43: lload 5
    //   45: invokestatic 270	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   48: astore 8
    //   50: iconst_1
    //   51: istore 9
    //   53: aload 7
    //   55: iload 9
    //   57: aload 8
    //   59: aastore
    //   60: aload 7
    //   62: iconst_2
    //   63: invokestatic 275	android/os/Process:myPid	()I
    //   66: invokestatic 278	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: aastore
    //   70: aload 7
    //   72: iconst_3
    //   73: invokestatic 281	android/os/Process:myTid	()I
    //   76: invokestatic 278	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   79: aastore
    //   80: ldc_w 283
    //   83: aload 7
    //   85: invokestatic 285	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   88: pop
    //   89: aload_1
    //   90: astore 11
    //   92: iconst_0
    //   93: istore 12
    //   95: iconst_0
    //   96: istore 13
    //   98: iconst_0
    //   99: istore 14
    //   101: iload 12
    //   103: ifgt +682 -> 785
    //   106: iload 13
    //   108: ifgt +677 -> 785
    //   111: iload 14
    //   113: ifeq +9 -> 122
    //   116: iconst_0
    //   117: istore 14
    //   119: goto +70 -> 189
    //   122: iinc 12 1
    //   125: iload 12
    //   127: iload 9
    //   129: if_icmple +60 -> 189
    //   132: new 287	java/lang/StringBuilder
    //   135: dup
    //   136: ldc_w 289
    //   139: invokespecial 290	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   142: astore 47
    //   144: aload 47
    //   146: iload 12
    //   148: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload 47
    //   154: invokevirtual 298	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: iconst_0
    //   158: anewarray 4	java/lang/Object
    //   161: invokestatic 285	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   164: pop
    //   165: ldc2_w 299
    //   168: new 302	java/util/Random
    //   171: dup
    //   172: invokestatic 306	java/lang/System:currentTimeMillis	()J
    //   175: invokespecial 309	java/util/Random:<init>	(J)V
    //   178: sipush 10000
    //   181: invokevirtual 313	java/util/Random:nextInt	(I)I
    //   184: i2l
    //   185: ladd
    //   186: invokestatic 318	android/os/SystemClock:sleep	(J)V
    //   189: aload_0
    //   190: getfield 20	com/tencent/bugly/proguard/s:c	Landroid/content/Context;
    //   193: invokestatic 324	com/tencent/bugly/crashreport/common/info/b:f	(Landroid/content/Context;)Ljava/lang/String;
    //   196: astore 15
    //   198: aload 15
    //   200: ifnonnull +20 -> 220
    //   203: ldc_w 326
    //   206: iconst_0
    //   207: anewarray 4	java/lang/Object
    //   210: invokestatic 329	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   213: pop
    //   214: iconst_1
    //   215: istore 9
    //   217: goto -116 -> 101
    //   220: aload_3
    //   221: lload 5
    //   223: invokevirtual 333	com/tencent/bugly/proguard/v:a	(J)V
    //   226: aload_0
    //   227: aload 11
    //   229: aload_2
    //   230: aload 15
    //   232: aload 4
    //   234: invokespecial 335	com/tencent/bugly/proguard/s:a	(Ljava/lang/String;[BLjava/lang/String;Ljava/util/Map;)Ljava/net/HttpURLConnection;
    //   237: astore 16
    //   239: aload 16
    //   241: ifnull +522 -> 763
    //   244: aload 16
    //   246: invokevirtual 338	java/net/HttpURLConnection:getResponseCode	()I
    //   249: istore 26
    //   251: iload 26
    //   253: sipush 200
    //   256: if_icmpne +80 -> 336
    //   259: aload_0
    //   260: aload 16
    //   262: invokestatic 340	com/tencent/bugly/proguard/s:a	(Ljava/net/HttpURLConnection;)Ljava/util/Map;
    //   265: putfield 18	com/tencent/bugly/proguard/s:a	Ljava/util/Map;
    //   268: aload 16
    //   270: invokestatic 342	com/tencent/bugly/proguard/s:b	(Ljava/net/HttpURLConnection;)[B
    //   273: astore 42
    //   275: aload 42
    //   277: ifnonnull +9 -> 286
    //   280: lconst_0
    //   281: lstore 43
    //   283: goto +9 -> 292
    //   286: aload 42
    //   288: arraylength
    //   289: i2l
    //   290: lstore 43
    //   292: aload_3
    //   293: lload 43
    //   295: invokevirtual 344	com/tencent/bugly/proguard/v:b	(J)V
    //   298: aload 16
    //   300: invokevirtual 347	java/net/HttpURLConnection:disconnect	()V
    //   303: goto +18 -> 321
    //   306: astore 45
    //   308: aload 45
    //   310: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   313: ifne +8 -> 321
    //   316: aload 45
    //   318: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   321: aload 42
    //   323: areturn
    //   324: astore 18
    //   326: iload 14
    //   328: istore 19
    //   330: iconst_1
    //   331: istore 9
    //   333: goto +341 -> 674
    //   336: iload 26
    //   338: sipush 301
    //   341: if_icmpeq +36 -> 377
    //   344: iload 26
    //   346: sipush 302
    //   349: if_icmpeq +28 -> 377
    //   352: iload 26
    //   354: sipush 303
    //   357: if_icmpeq +20 -> 377
    //   360: iload 26
    //   362: sipush 307
    //   365: if_icmpne +6 -> 371
    //   368: goto +9 -> 377
    //   371: iconst_0
    //   372: istore 27
    //   374: goto +6 -> 380
    //   377: iconst_1
    //   378: istore 27
    //   380: iload 27
    //   382: ifeq +187 -> 569
    //   385: aload 16
    //   387: ldc_w 349
    //   390: invokevirtual 352	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   393: astore 34
    //   395: aload 34
    //   397: ifnonnull +76 -> 473
    //   400: new 287	java/lang/StringBuilder
    //   403: dup
    //   404: ldc_w 354
    //   407: invokespecial 290	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   410: astore 38
    //   412: aload 38
    //   414: iload 26
    //   416: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   419: pop
    //   420: aload 38
    //   422: invokevirtual 298	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   425: iconst_0
    //   426: anewarray 4	java/lang/Object
    //   429: invokestatic 132	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   432: pop
    //   433: aload 16
    //   435: invokevirtual 347	java/net/HttpURLConnection:disconnect	()V
    //   438: goto +21 -> 459
    //   441: astore 41
    //   443: aload 41
    //   445: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   448: ifne -10 -> 438
    //   451: aload 41
    //   453: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   456: goto -18 -> 438
    //   459: aconst_null
    //   460: areturn
    //   461: astore 18
    //   463: iload 13
    //   465: istore 20
    //   467: iconst_1
    //   468: istore 9
    //   470: goto +93 -> 563
    //   473: iinc 13 1
    //   476: iconst_2
    //   477: anewarray 4	java/lang/Object
    //   480: astore 36
    //   482: aload 36
    //   484: iconst_0
    //   485: iload 26
    //   487: invokestatic 278	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   490: aastore
    //   491: iconst_1
    //   492: istore 9
    //   494: aload 36
    //   496: iload 9
    //   498: aload 34
    //   500: aastore
    //   501: ldc_w 356
    //   504: aload 36
    //   506: invokestatic 285	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   509: pop
    //   510: aload 34
    //   512: astore 11
    //   514: iconst_1
    //   515: istore 14
    //   517: iconst_0
    //   518: istore 12
    //   520: goto +52 -> 572
    //   523: astore 35
    //   525: goto +8 -> 533
    //   528: astore 35
    //   530: iconst_1
    //   531: istore 9
    //   533: aload 35
    //   535: astore 22
    //   537: aload 34
    //   539: astore 11
    //   541: iconst_1
    //   542: istore 19
    //   544: iload 13
    //   546: istore 20
    //   548: iconst_0
    //   549: istore 21
    //   551: goto +135 -> 686
    //   554: astore 18
    //   556: iconst_1
    //   557: istore 9
    //   559: iload 13
    //   561: istore 20
    //   563: iconst_1
    //   564: istore 19
    //   566: goto +112 -> 678
    //   569: iconst_1
    //   570: istore 9
    //   572: new 287	java/lang/StringBuilder
    //   575: dup
    //   576: ldc_w 358
    //   579: invokespecial 290	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   582: astore 28
    //   584: aload 28
    //   586: iload 26
    //   588: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   591: pop
    //   592: aload 28
    //   594: invokevirtual 298	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   597: iconst_0
    //   598: anewarray 4	java/lang/Object
    //   601: invokestatic 329	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   604: pop
    //   605: aload 16
    //   607: invokevirtual 361	java/net/HttpURLConnection:getContentLength	()I
    //   610: i2l
    //   611: lstore 31
    //   613: lload 31
    //   615: lconst_0
    //   616: lcmp
    //   617: ifge +6 -> 623
    //   620: lconst_0
    //   621: lstore 31
    //   623: aload_3
    //   624: lload 31
    //   626: invokevirtual 344	com/tencent/bugly/proguard/v:b	(J)V
    //   629: aload 16
    //   631: invokevirtual 347	java/net/HttpURLConnection:disconnect	()V
    //   634: goto +100 -> 734
    //   637: astore 33
    //   639: aload 33
    //   641: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   644: ifne +90 -> 734
    //   647: aload 33
    //   649: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   652: goto +82 -> 734
    //   655: astore 18
    //   657: goto +13 -> 670
    //   660: astore 24
    //   662: goto +75 -> 737
    //   665: astore 18
    //   667: iconst_1
    //   668: istore 9
    //   670: iload 14
    //   672: istore 19
    //   674: iload 13
    //   676: istore 20
    //   678: iload 12
    //   680: istore 21
    //   682: aload 18
    //   684: astore 22
    //   686: aload 22
    //   688: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   691: ifne +8 -> 699
    //   694: aload 22
    //   696: invokevirtual 362	java/io/IOException:printStackTrace	()V
    //   699: aload 16
    //   701: invokevirtual 347	java/net/HttpURLConnection:disconnect	()V
    //   704: goto +18 -> 722
    //   707: astore 23
    //   709: aload 23
    //   711: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   714: ifne +8 -> 722
    //   717: aload 23
    //   719: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   722: iload 21
    //   724: istore 12
    //   726: iload 20
    //   728: istore 13
    //   730: iload 19
    //   732: istore 14
    //   734: goto +48 -> 782
    //   737: aload 16
    //   739: invokevirtual 347	java/net/HttpURLConnection:disconnect	()V
    //   742: goto +18 -> 760
    //   745: astore 25
    //   747: aload 25
    //   749: invokestatic 122	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   752: ifne +8 -> 760
    //   755: aload 25
    //   757: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   760: aload 24
    //   762: athrow
    //   763: iconst_1
    //   764: istore 9
    //   766: ldc_w 364
    //   769: iconst_0
    //   770: anewarray 4	java/lang/Object
    //   773: invokestatic 285	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   776: pop
    //   777: aload_3
    //   778: lconst_0
    //   779: invokevirtual 344	com/tencent/bugly/proguard/v:b	(J)V
    //   782: goto -565 -> 217
    //   785: aconst_null
    //   786: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   298	303	306	java/lang/Throwable
    //   259	298	324	java/io/IOException
    //   433	438	441	java/lang/Throwable
    //   400	433	461	java/io/IOException
    //   494	510	523	java/io/IOException
    //   476	491	528	java/io/IOException
    //   385	395	554	java/io/IOException
    //   629	634	637	java/lang/Throwable
    //   572	629	655	java/io/IOException
    //   244	251	660	finally
    //   259	298	660	finally
    //   385	395	660	finally
    //   400	433	660	finally
    //   476	491	660	finally
    //   494	510	660	finally
    //   572	629	660	finally
    //   686	699	660	finally
    //   244	251	665	java/io/IOException
    //   699	704	707	java/lang/Throwable
    //   737	742	745	java/lang/Throwable
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.s
 * JD-Core Version:    0.6.1
 */