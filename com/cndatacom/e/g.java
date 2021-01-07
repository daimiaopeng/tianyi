package com.cndatacom.e;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class g extends AsyncTask<String, Integer, String>
{
  public boolean a = false;
  private int b = 3;
  private final int c = 15000;
  private final String d = "HttpAsyncTask";
  private Map<String, String> e;
  private final int f = 2131165184;
  private final int g = 2131165186;
  private final int h = 2131165187;
  private final int i = 2131165188;
  private String j = null;
  private a k;
  private Handler l = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 2131165185:
      default:
        break;
      case 2131165188:
        g.b(g.this).b("服务响应错误！请稍后重试.");
        break;
      case 2131165187:
        g.b(g.this).b("网络连接超时！请稍后重试.");
        break;
      case 2131165186:
        g.b(g.this).b("服务无响应！请稍后重试.");
        break;
      case 2131165184:
        g.b(g.this).a(g.a(g.this));
      }
    }
  };

  private g(Map<String, String> paramMap, a parama)
  {
    this.e = paramMap;
    this.k = parama;
  }

  private List<NameValuePair> a(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new BasicNameValuePair(str, (String)paramMap.get(str)));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("=");
      localStringBuilder.append((String)paramMap.get(str));
      Log.i("HttpAsyncTask", localStringBuilder.toString());
    }
    return localArrayList;
  }

  public static void a(String paramString, Map<String, String> paramMap, a parama)
  {
    new g(paramMap, parama).execute(new String[] { paramString, null });
  }

  // ERROR //
  protected String a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: ldc 35
    //   2: ldc 147
    //   4: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   7: pop
    //   8: aload_0
    //   9: iconst_1
    //   10: aload_0
    //   11: getfield 31	com/cndatacom/e/g:b	I
    //   14: iadd
    //   15: putfield 31	com/cndatacom/e/g:b	I
    //   18: new 110	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   25: astore_3
    //   26: aload_3
    //   27: ldc 149
    //   29: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload_3
    //   34: aload_0
    //   35: getfield 31	com/cndatacom/e/g:b	I
    //   38: invokevirtual 152	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: ldc 35
    //   44: aload_3
    //   45: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: aload_1
    //   53: iconst_0
    //   54: aaload
    //   55: astore 7
    //   57: new 110	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   64: astore 8
    //   66: aload 8
    //   68: ldc 154
    //   70: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload 8
    //   76: aload 7
    //   78: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: ldc 35
    //   84: aload 8
    //   86: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   92: pop
    //   93: aload_1
    //   94: iconst_1
    //   95: aaload
    //   96: astore 12
    //   98: new 110	java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   105: astore 13
    //   107: aload 13
    //   109: ldc 156
    //   111: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: aload 13
    //   117: aload 12
    //   119: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: ldc 35
    //   125: aload 13
    //   127: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   133: pop
    //   134: new 158	org/apache/http/params/BasicHttpParams
    //   137: dup
    //   138: invokespecial 159	org/apache/http/params/BasicHttpParams:<init>	()V
    //   141: astore 17
    //   143: aload 17
    //   145: sipush 15000
    //   148: invokestatic 165	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   151: aload 17
    //   153: sipush 15000
    //   156: invokestatic 168	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   159: new 170	org/apache/http/impl/client/DefaultHttpClient
    //   162: dup
    //   163: aload 17
    //   165: invokespecial 173	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   168: astore 18
    //   170: new 175	org/apache/http/client/methods/HttpPost
    //   173: dup
    //   174: aload 7
    //   176: invokespecial 178	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   179: astore 19
    //   181: aload 12
    //   183: ifnull +81 -> 264
    //   186: aload 12
    //   188: ldc 180
    //   190: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   193: ifne +71 -> 264
    //   196: aload_0
    //   197: getfield 53	com/cndatacom/e/g:a	Z
    //   200: ifeq +34 -> 234
    //   203: new 185	org/apache/http/entity/StringEntity
    //   206: dup
    //   207: new 93	java/lang/String
    //   210: dup
    //   211: aload 12
    //   213: ldc 187
    //   215: invokevirtual 191	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   218: invokestatic 196	com/cndatacom/e/c:a	([B)[B
    //   221: invokespecial 199	java/lang/String:<init>	([B)V
    //   224: ldc 187
    //   226: invokespecial 200	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   229: astore 56
    //   231: goto +16 -> 247
    //   234: new 185	org/apache/http/entity/StringEntity
    //   237: dup
    //   238: aload 12
    //   240: ldc 187
    //   242: invokespecial 200	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   245: astore 56
    //   247: aload 56
    //   249: ldc 202
    //   251: invokevirtual 205	org/apache/http/entity/StringEntity:setContentType	(Ljava/lang/String;)V
    //   254: aload 19
    //   256: aload 56
    //   258: invokevirtual 209	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   261: goto +3 -> 264
    //   264: aload_0
    //   265: getfield 62	com/cndatacom/e/g:e	Ljava/util/Map;
    //   268: ifnull +37 -> 305
    //   271: aload_0
    //   272: getfield 62	com/cndatacom/e/g:e	Ljava/util/Map;
    //   275: invokeinterface 213 1 0
    //   280: ifle +25 -> 305
    //   283: aload 19
    //   285: new 215	org/apache/http/client/entity/UrlEncodedFormEntity
    //   288: dup
    //   289: aload_0
    //   290: aload_0
    //   291: getfield 62	com/cndatacom/e/g:e	Ljava/util/Map;
    //   294: invokespecial 217	com/cndatacom/e/g:a	(Ljava/util/Map;)Ljava/util/List;
    //   297: ldc 187
    //   299: invokespecial 220	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   302: invokevirtual 209	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   305: aload 18
    //   307: aload 19
    //   309: invokevirtual 223	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   312: astore 29
    //   314: aload 29
    //   316: invokeinterface 229 1 0
    //   321: invokeinterface 234 1 0
    //   326: istore 30
    //   328: new 110	java/lang/StringBuilder
    //   331: dup
    //   332: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   335: astore 31
    //   337: aload 31
    //   339: ldc 236
    //   341: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: pop
    //   345: aload 31
    //   347: iload 30
    //   349: invokevirtual 152	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   352: pop
    //   353: ldc 35
    //   355: aload 31
    //   357: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   363: pop
    //   364: aload 29
    //   366: invokeinterface 240 1 0
    //   371: ldc 187
    //   373: invokestatic 245	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   376: astore 35
    //   378: iload 30
    //   380: sipush 200
    //   383: if_icmpne +91 -> 474
    //   386: ldc 35
    //   388: ldc 247
    //   390: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   393: pop
    //   394: aload_0
    //   395: getfield 53	com/cndatacom/e/g:a	Z
    //   398: ifeq +22 -> 420
    //   401: new 93	java/lang/String
    //   404: dup
    //   405: aload 35
    //   407: ldc 187
    //   409: invokevirtual 191	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   412: invokestatic 249	com/cndatacom/e/c:b	([B)[B
    //   415: invokespecial 199	java/lang/String:<init>	([B)V
    //   418: astore 35
    //   420: new 110	java/lang/StringBuilder
    //   423: dup
    //   424: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   427: astore 52
    //   429: aload 52
    //   431: ldc 156
    //   433: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: pop
    //   437: aload 52
    //   439: aload 35
    //   441: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: pop
    //   445: ldc 35
    //   447: aload 52
    //   449: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   452: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   455: pop
    //   456: aload 18
    //   458: ifnull +13 -> 471
    //   461: aload 18
    //   463: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   466: invokeinterface 258 1 0
    //   471: aload 35
    //   473: areturn
    //   474: ldc 35
    //   476: ldc_w 260
    //   479: invokestatic 126	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   482: pop
    //   483: aload_0
    //   484: getfield 31	com/cndatacom/e/g:b	I
    //   487: iconst_2
    //   488: if_icmple +30 -> 518
    //   491: aload_0
    //   492: getfield 60	com/cndatacom/e/g:l	Landroid/os/Handler;
    //   495: ldc 41
    //   497: invokevirtual 266	android/os/Handler:sendEmptyMessage	(I)Z
    //   500: pop
    //   501: aload 18
    //   503: ifnull +13 -> 516
    //   506: aload 18
    //   508: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   511: invokeinterface 258 1 0
    //   516: aconst_null
    //   517: areturn
    //   518: aload_0
    //   519: aload_1
    //   520: invokevirtual 268	com/cndatacom/e/g:a	([Ljava/lang/String;)Ljava/lang/String;
    //   523: astore 37
    //   525: aload 18
    //   527: ifnull +13 -> 540
    //   530: aload 18
    //   532: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   535: invokeinterface 258 1 0
    //   540: aload 37
    //   542: areturn
    //   543: new 110	java/lang/StringBuilder
    //   546: dup
    //   547: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   550: astore 45
    //   552: aload 45
    //   554: ldc_w 270
    //   557: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: aload 45
    //   563: aload 44
    //   565: invokevirtual 271	java/lang/Exception:toString	()Ljava/lang/String;
    //   568: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: ldc 35
    //   574: aload 45
    //   576: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   579: invokestatic 273	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   582: pop
    //   583: aload_0
    //   584: getfield 60	com/cndatacom/e/g:l	Landroid/os/Handler;
    //   587: ldc 47
    //   589: invokevirtual 266	android/os/Handler:sendEmptyMessage	(I)Z
    //   592: pop
    //   593: aload 18
    //   595: ifnull +13 -> 608
    //   598: aload 18
    //   600: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   603: invokeinterface 258 1 0
    //   608: aconst_null
    //   609: areturn
    //   610: new 110	java/lang/StringBuilder
    //   613: dup
    //   614: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   617: astore 39
    //   619: aload 39
    //   621: ldc_w 270
    //   624: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: pop
    //   628: aload 39
    //   630: aload 38
    //   632: invokevirtual 274	java/io/IOException:toString	()Ljava/lang/String;
    //   635: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: pop
    //   639: ldc 35
    //   641: aload 39
    //   643: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   646: invokestatic 273	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   649: pop
    //   650: aload_0
    //   651: getfield 60	com/cndatacom/e/g:l	Landroid/os/Handler;
    //   654: ldc 47
    //   656: invokevirtual 266	android/os/Handler:sendEmptyMessage	(I)Z
    //   659: pop
    //   660: aload 18
    //   662: ifnull +13 -> 675
    //   665: aload 18
    //   667: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   670: invokeinterface 258 1 0
    //   675: aconst_null
    //   676: areturn
    //   677: ldc 35
    //   679: ldc_w 276
    //   682: invokestatic 273	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   685: pop
    //   686: aload_0
    //   687: getfield 60	com/cndatacom/e/g:l	Landroid/os/Handler;
    //   690: ldc 47
    //   692: invokevirtual 266	android/os/Handler:sendEmptyMessage	(I)Z
    //   695: pop
    //   696: aload 18
    //   698: ifnull +13 -> 711
    //   701: aload 18
    //   703: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   706: invokeinterface 258 1 0
    //   711: aconst_null
    //   712: areturn
    //   713: ldc 35
    //   715: ldc_w 278
    //   718: invokestatic 273	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   721: pop
    //   722: aload_0
    //   723: getfield 31	com/cndatacom/e/g:b	I
    //   726: iconst_2
    //   727: if_icmple +30 -> 757
    //   730: aload_0
    //   731: getfield 60	com/cndatacom/e/g:l	Landroid/os/Handler;
    //   734: ldc 44
    //   736: invokevirtual 266	android/os/Handler:sendEmptyMessage	(I)Z
    //   739: pop
    //   740: aload 18
    //   742: ifnull +13 -> 755
    //   745: aload 18
    //   747: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   750: invokeinterface 258 1 0
    //   755: aconst_null
    //   756: areturn
    //   757: aload_0
    //   758: aload_1
    //   759: invokevirtual 268	com/cndatacom/e/g:a	([Ljava/lang/String;)Ljava/lang/String;
    //   762: astore 25
    //   764: aload 18
    //   766: ifnull +13 -> 779
    //   769: aload 18
    //   771: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   774: invokeinterface 258 1 0
    //   779: aload 25
    //   781: areturn
    //   782: ldc 35
    //   784: ldc_w 280
    //   787: invokestatic 273	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   790: pop
    //   791: aload_0
    //   792: getfield 31	com/cndatacom/e/g:b	I
    //   795: iconst_2
    //   796: if_icmple +30 -> 826
    //   799: aload_0
    //   800: getfield 60	com/cndatacom/e/g:l	Landroid/os/Handler;
    //   803: ldc 44
    //   805: invokevirtual 266	android/os/Handler:sendEmptyMessage	(I)Z
    //   808: pop
    //   809: aload 18
    //   811: ifnull +13 -> 824
    //   814: aload 18
    //   816: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   819: invokeinterface 258 1 0
    //   824: aconst_null
    //   825: areturn
    //   826: aload_0
    //   827: aload_1
    //   828: invokevirtual 268	com/cndatacom/e/g:a	([Ljava/lang/String;)Ljava/lang/String;
    //   831: astore 21
    //   833: aload 18
    //   835: ifnull +13 -> 848
    //   838: aload 18
    //   840: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   843: invokeinterface 258 1 0
    //   848: aload 21
    //   850: areturn
    //   851: aload 18
    //   853: ifnull +13 -> 866
    //   856: aload 18
    //   858: invokevirtual 253	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   861: invokeinterface 258 1 0
    //   866: aload 22
    //   868: athrow
    //   869: astore 22
    //   871: goto -20 -> 851
    //   874: astore 44
    //   876: goto -333 -> 543
    //   879: astore 38
    //   881: goto -271 -> 610
    //
    // Exception table:
    //   from	to	target	type
    //   186	456	677	java/io/UnsupportedEncodingException
    //   474	501	677	java/io/UnsupportedEncodingException
    //   518	525	677	java/io/UnsupportedEncodingException
    //   186	456	713	org/apache/http/conn/ConnectTimeoutException
    //   474	501	713	org/apache/http/conn/ConnectTimeoutException
    //   518	525	713	org/apache/http/conn/ConnectTimeoutException
    //   186	456	782	java/net/SocketTimeoutException
    //   474	501	782	java/net/SocketTimeoutException
    //   518	525	782	java/net/SocketTimeoutException
    //   186	456	869	finally
    //   474	501	869	finally
    //   518	525	869	finally
    //   543	593	869	finally
    //   610	660	869	finally
    //   677	696	869	finally
    //   713	740	869	finally
    //   757	764	869	finally
    //   782	809	869	finally
    //   826	833	869	finally
    //   186	456	874	java/lang/Exception
    //   474	501	874	java/lang/Exception
    //   518	525	874	java/lang/Exception
    //   186	456	879	java/io/IOException
    //   474	501	879	java/io/IOException
    //   518	525	879	java/io/IOException
  }

  protected void a(String paramString)
  {
    super.onPostExecute(this.j);
    if ((paramString != null) && (!paramString.equals("")))
    {
      this.j = paramString;
      this.l.sendEmptyMessage(2131165184);
    }
    else
    {
      this.j = paramString;
      this.l.sendEmptyMessage(2131165186);
    }
  }

  protected void a(Integer[] paramArrayOfInteger)
  {
    super.onProgressUpdate(paramArrayOfInteger);
  }

  protected void onPreExecute()
  {
    super.onPreExecute();
  }

  public static abstract interface a
  {
    public abstract void a(String paramString);

    public abstract void b(String paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.g
 * JD-Core Version:    0.6.1
 */