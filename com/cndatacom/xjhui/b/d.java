package com.cndatacom.xjhui.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.cndatacom.dykeylib.PortalCipher;
import com.cndatacom.e.a.a;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.httppap.PortalShared;
import com.cndatacom.xjhui.b.a.b;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class d
{
  public static int a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    PortalShared localPortalShared = PortalShared.getInstance();
    PortalCipher localPortalCipher = localPortalShared.getPortalCipher();
    if (localPortalCipher == null)
    {
      localPortalCipher = new PortalCipher();
      localPortalShared.setPortalCipher(localPortalCipher);
    }
    localPortalCipher.setContext(paramContext);
    if (!localPortalCipher.isInitial())
      localPortalCipher.zsmInitial();
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("doAuth account : ");
    localStringBuilder1.append(paramString1);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    localPortalCipher.setUser(paramString1);
    localPortalCipher.getZsmInfo();
    String str1 = b(paramContext, paramString1, paramString2, paramString3);
    if (str1 == null)
      return 995;
    byte[] arrayOfByte = localPortalCipher.zsmEncrypt(str1.getBytes());
    if (arrayOfByte == null)
      return 995;
    String str2 = a.a(arrayOfByte);
    String str3 = m.b(paramContext, "auth-url", "");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("auth url : ");
    localStringBuilder2.append(str3);
    j.b("TrineaAndroidCommon", localStringBuilder2.toString());
    if ("".equals(str3))
      return 201;
    try
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpParams localHttpParams = localDefaultHttpClient.getParams();
      ConnManagerParams.setTimeout(localHttpParams, 30000L);
      localHttpParams.setIntParameter("http.connection.timeout", 30000);
      localHttpParams.setIntParameter("http.socket.timeout", 30000);
      HttpPost localHttpPost = new HttpPost(str3);
      localHttpPost.setHeader("Accept", "*/*");
      localHttpPost.setHeader("Content-type", "text/html");
      localHttpPost.addHeader("User-Agent", com.cndatacom.xjhui.b.a.d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", com.cndatacom.xjhui.b.a.d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str2);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("auth return : ");
      localStringBuilder3.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder3.toString());
      int i = a(paramContext, localHttpResponse, localPortalCipher);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "AuthUtils doAuth refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "AuthUtils doAuth Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "AuthUtils doAuth ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "AuthUtils doAuth SocketTimeoutException");
    }
    return 303;
  }

  public static int a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PortalShared localPortalShared = PortalShared.getInstance();
    PortalCipher localPortalCipher = localPortalShared.getPortalCipher();
    if (localPortalCipher == null)
    {
      localPortalCipher = new PortalCipher();
      localPortalShared.setPortalCipher(localPortalCipher);
    }
    localPortalCipher.setContext(paramContext);
    if (!localPortalCipher.isInitial())
      localPortalCipher.zsmInitial();
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("doAuthForPC old account : ");
    localStringBuilder1.append(paramString1);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    localPortalCipher.setUser(paramString1);
    localPortalCipher.getZsmInfo();
    String str1 = b(paramContext, paramString1, paramString2, paramString3, paramString4);
    if (str1 == null)
      return 995;
    byte[] arrayOfByte = localPortalCipher.zsmEncrypt(str1.getBytes());
    if (arrayOfByte == null)
      return 995;
    String str2 = a.a(arrayOfByte);
    String str3 = m.b(paramContext, "auth-url", "");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("authForPC url : ");
    localStringBuilder2.append(str3);
    j.b("TrineaAndroidCommon", localStringBuilder2.toString());
    if (TextUtils.isEmpty(str3))
      return 201;
    try
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpParams localHttpParams = localDefaultHttpClient.getParams();
      ConnManagerParams.setTimeout(localHttpParams, 30000L);
      localHttpParams.setIntParameter("http.connection.timeout", 30000);
      localHttpParams.setIntParameter("http.socket.timeout", 30000);
      HttpPost localHttpPost = new HttpPost(str3);
      localHttpPost.setHeader("Accept", "*/*");
      localHttpPost.setHeader("Content-type", "text/html");
      localHttpPost.addHeader("User-Agent", com.cndatacom.xjhui.b.a.d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", com.cndatacom.xjhui.b.a.d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str2);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("authForPC return : ");
      localStringBuilder3.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder3.toString());
      int i = b(paramContext, localHttpResponse, localPortalCipher);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "AuthUtils doAuthForPC refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "AuthUtils doAuthForPC Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "AuthUtils doAuthForPC ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "AuthUtils doAuthForPC SocketTimeoutException");
    }
    return 303;
  }

  public static int a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    try
    {
      if (TextUtils.isEmpty(paramString4))
        return 201;
      String str1 = new String(Base64.decode(paramString4, 0)).trim();
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("authForPC url : ");
      localStringBuilder1.append(str1);
      j.b("TrineaAndroidCommon", localStringBuilder1.toString());
      if (TextUtils.isEmpty(str1))
        return 201;
      PortalShared localPortalShared = PortalShared.getInstance();
      PortalCipher localPortalCipher = localPortalShared.getPortalCipher();
      if (localPortalCipher == null)
      {
        localPortalCipher = new PortalCipher();
        localPortalShared.setPortalCipher(localPortalCipher);
      }
      localPortalCipher.setContext(paramContext);
      if (!localPortalCipher.isInitial())
        localPortalCipher.zsmInitial();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("doAuthForPC account : ");
      localStringBuilder2.append(paramString1);
      j.b("TrineaAndroidCommon", localStringBuilder2.toString());
      localPortalCipher.setUser(paramString1);
      localPortalCipher.getZsmInfo();
      String str2 = b(paramContext, paramString1, paramString2, paramString3, paramString5);
      if (str2 == null)
        return 995;
      byte[] arrayOfByte = localPortalCipher.zsmEncrypt(str2.getBytes());
      if (arrayOfByte == null)
        return 995;
      String str3 = a.a(arrayOfByte);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpParams localHttpParams = localDefaultHttpClient.getParams();
      ConnManagerParams.setTimeout(localHttpParams, 30000L);
      localHttpParams.setIntParameter("http.connection.timeout", 30000);
      localHttpParams.setIntParameter("http.socket.timeout", 30000);
      HttpPost localHttpPost = new HttpPost(str1);
      localHttpPost.setHeader("Accept", "*/*");
      localHttpPost.setHeader("Content-type", "text/html");
      localHttpPost.addHeader("User-Agent", com.cndatacom.xjhui.b.a.d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", com.cndatacom.xjhui.b.a.d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str3);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("authForPC return : ");
      localStringBuilder3.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder3.toString());
      int i = b(paramContext, localHttpResponse, localPortalCipher);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "AuthUtils doAuthForPC refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "AuthUtils doAuthForPC Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "AuthUtils doAuthForPC ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "AuthUtils doAuthForPC SocketTimeoutException");
    }
    return 303;
  }

  private static int a(Context paramContext, HttpResponse paramHttpResponse, PortalCipher paramPortalCipher)
  {
    while (true)
    {
      int j;
      try
      {
        if (paramHttpResponse.getStatusLine().getStatusCode() == 200)
        {
          Header[] arrayOfHeader1 = paramHttpResponse.getAllHeaders();
          int i = 0;
          if (i < arrayOfHeader1.length)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("auth headers ");
            localStringBuilder1.append(arrayOfHeader1[i]);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            i++;
            continue;
          }
          j = Integer.parseInt(paramHttpResponse.getFirstHeader("Error-Code").getValue());
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("auth Error-Code : ");
          localStringBuilder2.append(j);
          j.b("TrineaAndroidCommon", localStringBuilder2.toString());
          Header[] arrayOfHeader2 = paramHttpResponse.getHeaders("Sub-Error");
          if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
          {
            String str2 = arrayOfHeader2[0].getValue();
            StringBuilder localStringBuilder5 = new StringBuilder();
            localStringBuilder5.append(j);
            localStringBuilder5.append("");
            localStringBuilder5.append(str2);
            j = Integer.parseInt(localStringBuilder5.toString());
            StringBuilder localStringBuilder6 = new StringBuilder();
            localStringBuilder6.append("auth Sub_Error : ");
            localStringBuilder6.append(j);
            j.b("TrineaAndroidCommon", localStringBuilder6.toString());
          }
          if (j == 105)
          {
            Header[] arrayOfHeader3 = paramHttpResponse.getHeaders("Last-Login");
            if ((arrayOfHeader3 == null) || (arrayOfHeader3.length <= 0))
            {
              j = 203;
              StringBuilder localStringBuilder4 = new StringBuilder();
              localStringBuilder4.append("auth Last-Login srvErrCode : ");
              localStringBuilder4.append(j);
              j.b("TrineaAndroidCommon", localStringBuilder4.toString());
            }
          }
          InputStream localInputStream = paramHttpResponse.getEntity().getContent();
          if (localInputStream == null)
          {
            return 993;
            byte[] arrayOfByte = b.b(localInputStream).getBytes();
            continue;
            arrayOfByte = b.a(localInputStream);
            if (j != 0)
              break label529;
            String str1 = new String(paramPortalCipher.zsmDecrypt(arrayOfByte));
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append("auth retData : ");
            localStringBuilder3.append(str1);
            j.b("TrineaAndroidCommon", localStringBuilder3.toString());
            a(paramContext, str1);
            return 0;
            paramPortalCipher.zsmUpdate(arrayOfByte);
            return j;
          }
        }
        else
        {
          return 994;
        }
      }
      catch (Exception localException)
      {
        if (localException.toString().contains("refuse"))
        {
          j.a("TrineaAndroidCommon", localException, "AuthUtils dealAuthResponseCode refuse Exception");
          return 992;
        }
        j.a("TrineaAndroidCommon", localException, "AuthUtils dealAuthResponseCode Exception");
        return 993;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        j.a("TrineaAndroidCommon", localConnectTimeoutException, "AuthUtils dealAuthResponseCode ConnectTimeoutException");
        return 303;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        j.a("TrineaAndroidCommon", localSocketTimeoutException, "AuthUtils dealAuthResponseCode SocketTimeoutException");
        return 303;
      }
      if (j != 13)
        if (j == 200)
        {
          continue;
          label529: if (j != 13)
            if (j != 200);
        }
    }
  }

  private static void a(Context paramContext, String paramString)
  {
    while (true)
    {
      try
      {
        XmlPullParser localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
        localXmlPullParser.setInput(new ByteArrayInputStream(paramString.getBytes()), "UTF-8");
        int i = localXmlPullParser.getEventType();
        if (i != 1)
          try
          {
            String str = localXmlPullParser.getName();
            if (i != 0);
            switch (i)
            {
            case 2:
              if ("userid".equals(str))
                m.a(paramContext, "userid", localXmlPullParser.nextText());
              else if ("keep-retry".equals(str))
                m.a(paramContext, "keep-retry", localXmlPullParser.nextText());
              else if ("keep-url".equals(str))
                m.a(paramContext, "keep-url", localXmlPullParser.nextText());
              else if ("term-url".equals(str))
                m.a(paramContext, "term-url", localXmlPullParser.nextText());
            case 3:
              i = localXmlPullParser.next();
            }
          }
          catch (IOException localIOException)
          {
            j.a("TrineaAndroidCommon", localIOException, "AuthUtils getAuthInfo IOException");
          }
          catch (NumberFormatException localNumberFormatException)
          {
            j.a("TrineaAndroidCommon", localNumberFormatException, "AuthUtils getAuthInfo NumberFormatException");
          }
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        j.a("TrineaAndroidCommon", localXmlPullParserException, "AuthUtils getAuthInfo XmlPullParserException");
      }
      return;
    }
  }

  private static int b(Context paramContext, HttpResponse paramHttpResponse, PortalCipher paramPortalCipher)
  {
    while (true)
    {
      int j;
      try
      {
        if (paramHttpResponse.getStatusLine().getStatusCode() == 200)
        {
          Header[] arrayOfHeader1 = paramHttpResponse.getAllHeaders();
          int i = 0;
          if (i < arrayOfHeader1.length)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("authForPC headers ");
            localStringBuilder1.append(arrayOfHeader1[i]);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            i++;
            continue;
          }
          j = Integer.parseInt(paramHttpResponse.getFirstHeader("Error-Code").getValue());
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("authForPC Error-Code : ");
          localStringBuilder2.append(j);
          j.b("TrineaAndroidCommon", localStringBuilder2.toString());
          Header[] arrayOfHeader2 = paramHttpResponse.getHeaders("Sub-Error");
          if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
          {
            String str = arrayOfHeader2[0].getValue();
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append(j);
            localStringBuilder4.append("");
            localStringBuilder4.append(str);
            j = Integer.parseInt(localStringBuilder4.toString());
            StringBuilder localStringBuilder5 = new StringBuilder();
            localStringBuilder5.append("authForPC Sub-Code : ");
            localStringBuilder5.append(j);
            j.b("TrineaAndroidCommon", localStringBuilder5.toString());
          }
          if (j == 105)
          {
            Header[] arrayOfHeader3 = paramHttpResponse.getHeaders("Last-Login");
            if ((arrayOfHeader3 == null) || (arrayOfHeader3.length <= 0))
            {
              j = 203;
              StringBuilder localStringBuilder3 = new StringBuilder();
              localStringBuilder3.append("authForPC Last-Login srvErrCode : ");
              localStringBuilder3.append(j);
              j.b("TrineaAndroidCommon", localStringBuilder3.toString());
            }
          }
          InputStream localInputStream = paramHttpResponse.getEntity().getContent();
          if (localInputStream == null)
          {
            return 993;
            byte[] arrayOfByte = b.b(localInputStream).getBytes();
            break label468;
            arrayOfByte = b.a(localInputStream);
            break label468;
            paramPortalCipher.zsmUpdate(arrayOfByte);
            return j;
          }
        }
        else
        {
          return 994;
        }
      }
      catch (Exception localException)
      {
        if (localException.toString().contains("refuse"))
        {
          j.a("TrineaAndroidCommon", localException, "AuthUtils dealAuthResponseCodeForPC refuse Exception");
          return 992;
        }
        j.a("TrineaAndroidCommon", localException, "AuthUtils dealAuthResponseCodeForPC Exception");
        return 993;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        j.a("TrineaAndroidCommon", localConnectTimeoutException, "AuthUtils dealAuthResponseCodeForPC ConnectTimeoutException");
        return 303;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        j.a("TrineaAndroidCommon", localSocketTimeoutException, "AuthUtils dealAuthResponseCodeForPC SocketTimeoutException");
        return 303;
      }
      if (j != 13)
        if (j == 200)
        {
          continue;
          label468: if (j == 0)
            return 0;
          if (j != 13)
            if (j != 200);
        }
    }
  }

  private static String b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str1 = m.a(paramContext, "ticket");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("auth ticket : ");
    localStringBuilder.append(str1);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    if (TextUtils.isEmpty(str1))
      return null;
    String str2;
    if (!TextUtils.isEmpty(paramString3))
      str2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><userid>%s</userid><passwd>%s</passwd><verify>%s</verify><ticket>%s</ticket><local-time>%s</local-time></request>";
    else
      str2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><userid>%s</userid><passwd>%s</passwd><ticket>%s</ticket><local-time>%s</local-time></request>";
    String str3;
    if (!TextUtils.isEmpty(paramString3))
    {
      Object[] arrayOfObject2 = new Object[7];
      arrayOfObject2[0] = com.cndatacom.xjhui.b.a.d.c(paramContext);
      arrayOfObject2[1] = com.cndatacom.xjhui.b.a.d.e(paramContext);
      arrayOfObject2[2] = com.cndatacom.xjhui.b.a.d.a(paramContext, paramString1);
      arrayOfObject2[3] = paramString2;
      arrayOfObject2[4] = paramString3;
      arrayOfObject2[5] = str1;
      arrayOfObject2[6] = com.cndatacom.xjhui.b.a.d.a();
      str3 = String.format(str2, arrayOfObject2);
    }
    else
    {
      Object[] arrayOfObject1 = new Object[6];
      arrayOfObject1[0] = com.cndatacom.xjhui.b.a.d.c(paramContext);
      arrayOfObject1[1] = com.cndatacom.xjhui.b.a.d.e(paramContext);
      arrayOfObject1[2] = com.cndatacom.xjhui.b.a.d.a(paramContext, paramString1);
      arrayOfObject1[3] = paramString2;
      arrayOfObject1[4] = str1;
      arrayOfObject1[5] = com.cndatacom.xjhui.b.a.d.a();
      str3 = String.format(str2, arrayOfObject1);
    }
    return str3;
  }

  private static String b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("authForPC ticket :");
    localStringBuilder.append(paramString3);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    if (TextUtils.isEmpty(paramString3))
      return null;
    String str1;
    if (!TextUtils.isEmpty(paramString4))
      str1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><userid>%s</userid><passwd>%s</passwd><verify>%s</verify><ticket>%s</ticket><local-time>%s</local-time></request>";
    else
      str1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><userid>%s</userid><passwd>%s</passwd><ticket>%s</ticket><local-time>%s</local-time></request>";
    String str2;
    if (!"".equals(paramString4))
    {
      Object[] arrayOfObject2 = new Object[7];
      arrayOfObject2[0] = com.cndatacom.xjhui.b.a.d.c(paramContext);
      arrayOfObject2[1] = com.cndatacom.xjhui.b.a.d.e(paramContext);
      arrayOfObject2[2] = com.cndatacom.xjhui.b.a.d.a(paramContext, paramString1);
      arrayOfObject2[3] = paramString2;
      arrayOfObject2[4] = paramString4;
      arrayOfObject2[5] = paramString3;
      arrayOfObject2[6] = com.cndatacom.xjhui.b.a.d.a();
      str2 = String.format(str1, arrayOfObject2);
    }
    else
    {
      Object[] arrayOfObject1 = new Object[6];
      arrayOfObject1[0] = com.cndatacom.xjhui.b.a.d.c(paramContext);
      arrayOfObject1[1] = com.cndatacom.xjhui.b.a.d.e(paramContext);
      arrayOfObject1[2] = com.cndatacom.xjhui.b.a.d.a(paramContext, paramString1);
      arrayOfObject1[3] = paramString2;
      arrayOfObject1[4] = paramString3;
      arrayOfObject1[5] = com.cndatacom.xjhui.b.a.d.a();
      str2 = String.format(str1, arrayOfObject1);
    }
    return str2;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.d
 * JD-Core Version:    0.6.1
 */