package com.cndatacom.xjhui.b;

import android.content.Context;
import android.text.TextUtils;
import com.cndatacom.dykeylib.PortalCipher;
import com.cndatacom.e.a.a;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.httppap.PortalShared;
import com.cndatacom.xjhui.b.a.b;
import com.cndatacom.xjhui.b.a.d;
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

public class e
{
  public static int a(Context paramContext, String paramString)
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
    localPortalCipher.setUser(paramString);
    localPortalCipher.getZsmInfo();
    String str1 = a(paramContext);
    if (str1 == null)
      return 995;
    byte[] arrayOfByte = localPortalCipher.zsmEncrypt(str1.getBytes());
    if (arrayOfByte == null)
      return 995;
    String str2 = a.a(arrayOfByte);
    String str3 = m.b(paramContext, "keep-url", "");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("keep url : ");
    localStringBuilder1.append(str3);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
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
      localHttpPost.setHeader("Connection", "Close");
      localHttpPost.addHeader("User-Agent", d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str2);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("keep return : ");
      localStringBuilder2.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder2.toString());
      int i = a(paramContext, localHttpResponse, localPortalCipher);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "KeepUtils doKeep refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "KeepUtils doKeep Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "KeepUtils doKeep ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "KeepUtils doKeep SocketTimeoutException");
    }
    return 303;
  }

  private static int a(Context paramContext, HttpResponse paramHttpResponse, PortalCipher paramPortalCipher)
  {
    while (true)
    {
      int i;
      try
      {
        if (paramHttpResponse.getStatusLine().getStatusCode() == 302)
          return 302;
        if (paramHttpResponse.getStatusLine().getStatusCode() == 200)
        {
          i = 333;
          Header[] arrayOfHeader1 = paramHttpResponse.getAllHeaders();
          int j = 0;
          if (j < arrayOfHeader1.length)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("keep headers ");
            localStringBuilder1.append(arrayOfHeader1[j]);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            j++;
            continue;
          }
          int k;
          if (paramHttpResponse.getFirstHeader("Error-Code") == null)
          {
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("keep Error-Code == null : ");
            localStringBuilder2.append(i);
            j.b("TrineaAndroidCommon", localStringBuilder2.toString());
            k = 1;
          }
          else
          {
            i = Integer.parseInt(paramHttpResponse.getFirstHeader("Error-Code").getValue());
            StringBuilder localStringBuilder6 = new StringBuilder();
            localStringBuilder6.append("keep Error-Code : ");
            localStringBuilder6.append(i);
            j.b("TrineaAndroidCommon", localStringBuilder6.toString());
            k = 0;
          }
          Header[] arrayOfHeader2 = paramHttpResponse.getHeaders("Sub-Error");
          if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
          {
            String str2 = arrayOfHeader2[0].getValue();
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append(i);
            localStringBuilder4.append("");
            localStringBuilder4.append(str2);
            i = Integer.parseInt(localStringBuilder4.toString());
            StringBuilder localStringBuilder5 = new StringBuilder();
            localStringBuilder5.append("keep Sub-Code : ");
            localStringBuilder5.append(i);
            j.b("TrineaAndroidCommon", localStringBuilder5.toString());
          }
          InputStream localInputStream = paramHttpResponse.getEntity().getContent();
          if (localInputStream == null)
          {
            return 993;
            byte[] arrayOfByte = b.b(localInputStream).getBytes();
            continue;
            arrayOfByte = b.a(localInputStream);
            if (k == 1)
            {
              j.b("TrineaAndroidCommon", "keep return 200, but Error-Code == null");
              return 123;
            }
            if (i != 0)
              break label567;
            String str1 = new String(paramPortalCipher.zsmDecrypt(arrayOfByte));
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append("keep retData : ");
            localStringBuilder3.append(str1);
            j.b("TrineaAndroidCommon", localStringBuilder3.toString());
            if (b(paramContext, str1))
              return i;
            j.b("TrineaAndroidCommon", "keep return 200, getKeepInfo error");
            return 123;
            paramPortalCipher.zsmUpdate(arrayOfByte);
            return i;
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
          j.a("TrineaAndroidCommon", localException, "KeepUtils dealKeepResponseCode refuse Exception");
          return 992;
        }
        j.a("TrineaAndroidCommon", localException, "KeepUtils dealKeepResponseCode Exception");
        return 993;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        j.a("TrineaAndroidCommon", localConnectTimeoutException, "KeepUtils dealKeepResponseCode ConnectTimeoutException");
        return 303;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        j.a("TrineaAndroidCommon", localSocketTimeoutException, "KeepUtils dealKeepResponseCode SocketTimeoutException");
        return 303;
      }
      if (i != 13)
        if (i == 200)
        {
          continue;
          label567: if (i != 13)
            if (i != 200);
        }
    }
  }

  private static String a(Context paramContext)
  {
    String str = m.a(paramContext, "ticket");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("keep ticket : ");
    localStringBuilder.append(str);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    if (TextUtils.isEmpty(str))
      return null;
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = d.c(paramContext);
    arrayOfObject[1] = d.e(paramContext);
    arrayOfObject[2] = str;
    arrayOfObject[3] = d.f(paramContext);
    arrayOfObject[4] = d.c();
    arrayOfObject[5] = d.a(paramContext);
    arrayOfObject[6] = d.a();
    return String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><ticket>%s</ticket><ipv4>%s</ipv4><ipv6>%s</ipv6><mac>%s</mac><local-time>%s</local-time></request>", arrayOfObject);
  }

  private static boolean b(Context paramContext, String paramString)
  {
    while (true)
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
              if ("interval".equals(str))
                m.a(paramContext, "interval", localXmlPullParser.nextText());
              else if ("level".equals(str))
                m.a(paramContext, "level", localXmlPullParser.nextText());
            case 3:
              i = localXmlPullParser.next();
            }
          }
          catch (IOException localIOException)
          {
            j.a("TrineaAndroidCommon", localIOException, "KeepUtils getKeepInfo IOException");
            return false;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            j.a("TrineaAndroidCommon", localNumberFormatException, "KeepUtils getKeepInfo NumberFormatException");
            return false;
          }
        else
          return true;
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        j.a("TrineaAndroidCommon", localXmlPullParserException, "KeepUtils getKeepInfo XmlPullParserException");
        return false;
      }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.e
 * JD-Core Version:    0.6.1
 */