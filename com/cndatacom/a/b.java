package com.cndatacom.a;

import android.content.Context;
import android.text.TextUtils;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class b
{
  // ERROR //
  public static com.cndatacom.c.a a(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: new 20	com/cndatacom/c/a
    //   3: dup
    //   4: invokespecial 24	com/cndatacom/c/a:<init>	()V
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 4
    //   11: new 26	org/apache/http/params/BasicHttpParams
    //   14: dup
    //   15: invokespecial 27	org/apache/http/params/BasicHttpParams:<init>	()V
    //   18: astore 5
    //   20: aload 5
    //   22: sipush 10000
    //   25: invokestatic 33	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   28: aload 5
    //   30: sipush 10000
    //   33: invokestatic 36	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   36: aload 5
    //   38: iconst_1
    //   39: invokestatic 42	org/apache/http/client/params/HttpClientParams:setRedirecting	(Lorg/apache/http/params/HttpParams;Z)V
    //   42: aconst_null
    //   43: astore 4
    //   45: aload_1
    //   46: ifnull +25 -> 71
    //   49: aload_1
    //   50: ldc 44
    //   52: invokevirtual 50	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   55: istore 26
    //   57: aconst_null
    //   58: astore 4
    //   60: iload 26
    //   62: ifne +9 -> 71
    //   65: aload 5
    //   67: aload_1
    //   68: invokestatic 56	org/apache/http/params/HttpProtocolParams:setUserAgent	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
    //   71: new 58	org/apache/http/impl/client/DefaultHttpClient
    //   74: dup
    //   75: aload 5
    //   77: invokespecial 61	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   80: astore 9
    //   82: new 63	org/apache/http/client/methods/HttpGet
    //   85: dup
    //   86: aload_0
    //   87: invokespecial 66	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   90: astore 13
    //   92: aload_2
    //   93: ifnull +86 -> 179
    //   96: aload_2
    //   97: arraylength
    //   98: ifle +81 -> 179
    //   101: aload_2
    //   102: arraylength
    //   103: istore 21
    //   105: iconst_2
    //   106: anewarray 46	java/lang/String
    //   109: pop
    //   110: iconst_0
    //   111: istore 23
    //   113: iload 23
    //   115: iload 21
    //   117: if_icmpge +62 -> 179
    //   120: aload_2
    //   121: iload 23
    //   123: aaload
    //   124: astore 24
    //   126: aload 24
    //   128: ifnull +376 -> 504
    //   131: aload 24
    //   133: ldc 44
    //   135: invokevirtual 50	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   138: ifne +366 -> 504
    //   141: aload 24
    //   143: ldc 68
    //   145: invokevirtual 72	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   148: astore 25
    //   150: aload 25
    //   152: arraylength
    //   153: iconst_2
    //   154: if_icmpne +350 -> 504
    //   157: aload 13
    //   159: aload 25
    //   161: iconst_0
    //   162: aaload
    //   163: invokevirtual 76	java/lang/String:trim	()Ljava/lang/String;
    //   166: aload 25
    //   168: iconst_1
    //   169: aaload
    //   170: invokevirtual 76	java/lang/String:trim	()Ljava/lang/String;
    //   173: invokevirtual 80	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   176: goto +328 -> 504
    //   179: aload 9
    //   181: aload 13
    //   183: invokeinterface 86 2 0
    //   188: astore 14
    //   190: aload 14
    //   192: ifnull +114 -> 306
    //   195: aload 14
    //   197: invokeinterface 92 1 0
    //   202: astore 15
    //   204: aload 15
    //   206: ifnull +100 -> 306
    //   209: aload_3
    //   210: aload 15
    //   212: invokeinterface 98 1 0
    //   217: invokevirtual 101	com/cndatacom/c/a:a	(I)V
    //   220: aload 14
    //   222: invokeinterface 105 1 0
    //   227: astore 16
    //   229: aload 16
    //   231: ifnull +75 -> 306
    //   234: aload 16
    //   236: invokeinterface 111 1 0
    //   241: astore 17
    //   243: new 113	java/io/ByteArrayOutputStream
    //   246: dup
    //   247: invokespecial 114	java/io/ByteArrayOutputStream:<init>	()V
    //   250: astore 18
    //   252: sipush 1024
    //   255: newarray byte
    //   257: astore 19
    //   259: aload 17
    //   261: aload 19
    //   263: invokevirtual 120	java/io/InputStream:read	([B)I
    //   266: istore 20
    //   268: iload 20
    //   270: iconst_m1
    //   271: if_icmple +16 -> 287
    //   274: aload 18
    //   276: aload 19
    //   278: iconst_0
    //   279: iload 20
    //   281: invokevirtual 124	java/io/ByteArrayOutputStream:write	([BII)V
    //   284: goto -25 -> 259
    //   287: aload_3
    //   288: aload 18
    //   290: invokevirtual 127	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   293: invokevirtual 129	com/cndatacom/c/a:a	(Ljava/lang/String;)V
    //   296: aload 18
    //   298: invokevirtual 132	java/io/ByteArrayOutputStream:close	()V
    //   301: aload 17
    //   303: invokevirtual 133	java/io/InputStream:close	()V
    //   306: aload 9
    //   308: ifnull +174 -> 482
    //   311: aload 9
    //   313: invokeinterface 137 1 0
    //   318: astore 7
    //   320: goto +155 -> 475
    //   323: astore 8
    //   325: goto +159 -> 484
    //   328: astore 12
    //   330: aload 9
    //   332: astore 4
    //   334: goto +41 -> 375
    //   337: astore 11
    //   339: aload 9
    //   341: astore 4
    //   343: goto +55 -> 398
    //   346: astore 10
    //   348: aload 9
    //   350: astore 4
    //   352: goto +69 -> 421
    //   355: astore 6
    //   357: aload 9
    //   359: astore 4
    //   361: goto +84 -> 445
    //   364: astore 8
    //   366: aload 4
    //   368: astore 9
    //   370: goto +114 -> 484
    //   373: astore 12
    //   375: ldc 139
    //   377: aload 12
    //   379: ldc 141
    //   381: invokestatic 146	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   384: aload_3
    //   385: bipush 102
    //   387: invokevirtual 101	com/cndatacom/c/a:a	(I)V
    //   390: aload 4
    //   392: ifnull +90 -> 482
    //   395: goto +71 -> 466
    //   398: ldc 139
    //   400: aload 11
    //   402: ldc 148
    //   404: invokestatic 146	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   407: aload_3
    //   408: bipush 102
    //   410: invokevirtual 101	com/cndatacom/c/a:a	(I)V
    //   413: aload 4
    //   415: ifnull +67 -> 482
    //   418: goto +48 -> 466
    //   421: ldc 139
    //   423: aload 10
    //   425: ldc 150
    //   427: invokestatic 146	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   430: aload_3
    //   431: sipush 303
    //   434: invokevirtual 101	com/cndatacom/c/a:a	(I)V
    //   437: aload 4
    //   439: ifnull +43 -> 482
    //   442: goto +24 -> 466
    //   445: ldc 139
    //   447: aload 6
    //   449: ldc 152
    //   451: invokestatic 146	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   454: aload_3
    //   455: sipush 303
    //   458: invokevirtual 101	com/cndatacom/c/a:a	(I)V
    //   461: aload 4
    //   463: ifnull +19 -> 482
    //   466: aload 4
    //   468: invokeinterface 137 1 0
    //   473: astore 7
    //   475: aload 7
    //   477: invokeinterface 157 1 0
    //   482: aload_3
    //   483: areturn
    //   484: aload 9
    //   486: ifnull +15 -> 501
    //   489: aload 9
    //   491: invokeinterface 137 1 0
    //   496: invokeinterface 157 1 0
    //   501: aload 8
    //   503: athrow
    //   504: iinc 23 1
    //   507: goto -394 -> 113
    //   510: astore 11
    //   512: aconst_null
    //   513: astore 4
    //   515: goto -117 -> 398
    //   518: astore 10
    //   520: aconst_null
    //   521: astore 4
    //   523: goto -102 -> 421
    //   526: astore 6
    //   528: aconst_null
    //   529: astore 4
    //   531: goto -86 -> 445
    //
    // Exception table:
    //   from	to	target	type
    //   82	306	323	finally
    //   82	306	328	java/lang/Exception
    //   82	306	337	java/io/IOException
    //   82	306	346	org/apache/http/conn/ConnectTimeoutException
    //   82	306	355	java/net/SocketTimeoutException
    //   11	82	364	finally
    //   375	461	364	finally
    //   11	82	373	java/lang/Exception
    //   11	82	510	java/io/IOException
    //   11	82	518	org/apache/http/conn/ConnectTimeoutException
    //   11	82	526	java/net/SocketTimeoutException
  }

  public static void a(final Context paramContext, final String paramString1, final String paramString2)
  {
    String str1 = m.a(paramContext, "GetPhoneMarketingDataURL");
    if (TextUtils.isEmpty(str1))
    {
      j.b("TrineaAndroidCommon", "sendPhoneMarketingData url == null");
      str1 = "http://218.77.121.111/EsurfingClient/Other/GetPhoneMarketingData.ashx";
    }
    j.b("TrineaAndroidCommon", "sendPhoneMarketingData do");
    Map localMap = new a().a(paramContext, paramString1, paramString2);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(str1);
    localStringBuffer.append("?");
    try
    {
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        String str3 = (String)localMap.get(str2);
        localStringBuffer.append(str2);
        localStringBuffer.append("=");
        localStringBuffer.append(URLEncoder.encode(str3, "UTF-8"));
        localStringBuffer.append("&");
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      j.a("TrineaAndroidCommon", localUnsupportedEncodingException, "Http sendPhoneMarketingData UnsupportedEncodingException");
      new Thread()
      {
        public void run()
        {
          try
          {
            String str = b.a(this.a);
            if (str == null)
              return;
            int i = b.a(paramContext, paramString1, paramString2, str);
            if (i == -1)
              return;
            return;
          }
          catch (Exception localException)
          {
            j.a("TrineaAndroidCommon", localException, "Http sendPhoneMarketingData Exception");
          }
        }
      }
      .start();
    }
  }

  private static int b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramString3.contains("<value>-1</value>"))
      return -1;
    try
    {
      if (!paramString3.contains("SendPhoneMessageInterval"))
      {
        j.b("TrineaAndroidCommon", " no SendPhoneMessageInterval ");
        return -1;
      }
      String str = paramString3.substring(paramString3.indexOf("<Item>") + "<Item>".length(), paramString3.indexOf("</Item>"));
      if (!str.contains("SendPhoneMessageInterval"))
        str = paramString3.substring(paramString3.lastIndexOf("<Item>") + "<Item>".length(), paramString3.lastIndexOf("</Item>"));
      int i = Integer.parseInt(str.replaceAll("\\D", ""));
      return i;
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "Http sendPhoneMarketingData parseXML Exception");
    }
    return -1;
  }

  private static String b(String paramString)
  {
    try
    {
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 10000);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(new HttpGet(paramString));
      if (localHttpResponse.getStatusLine().getStatusCode() != 200)
        return null;
      String str = EntityUtils.toString(localHttpResponse.getEntity(), "UTF-8");
      localDefaultHttpClient.getConnectionManager().shutdown();
      return str;
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "Http sendPhoneMarketingData send Exception");
      return null;
    }
    catch (IOException localIOException)
    {
      j.a("TrineaAndroidCommon", localIOException, "Http sendPhoneMarketingData send IOException");
      return null;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      j.a("TrineaAndroidCommon", localUnsupportedEncodingException, "Http sendPhoneMarketingData send UnsupportedEncodingException");
      return null;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "Http sendPhoneMarketingData send ConnectTimeoutException");
    }
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.a.b
 * JD-Core Version:    0.6.1
 */