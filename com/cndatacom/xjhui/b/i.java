package com.cndatacom.xjhui.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
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

public class i
{
  public static int a(Context paramContext, String paramString, boolean paramBoolean)
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
    localStringBuilder1.append("doTicket account : ");
    localStringBuilder1.append(paramString);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    localPortalCipher.setUser(paramString);
    localPortalCipher.getZsmInfo();
    String str1 = a(paramContext);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("ticket packageBody : ");
    localStringBuilder2.append(str1);
    j.b("TrineaAndroidCommon", localStringBuilder2.toString());
    byte[] arrayOfByte = localPortalCipher.zsmEncrypt(str1.getBytes());
    if (arrayOfByte == null)
      return 995;
    String str2 = a.a(arrayOfByte);
    String str3 = m.b(paramContext, "ticket-url", "");
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append("ticket url : ");
    localStringBuilder3.append(str3);
    j.b("TrineaAndroidCommon", localStringBuilder3.toString());
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
      localHttpPost.addHeader("User-Agent", d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str2);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append("ticket return : ");
      localStringBuilder4.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder4.toString());
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        if (paramBoolean)
        {
          m.a(paramContext, "isCanTicket", true);
          return 0;
        }
      }
      else if (paramBoolean)
      {
        m.a(paramContext, "isCanTicket", false);
        return 0;
      }
      int i = a(paramContext, localHttpResponse, localPortalCipher);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "TicketUtils doTicket refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "TicketUtils doTicket Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "TicketUtils doTicket ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "TicketUtils doTicket SocketTimeoutException");
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
            localStringBuilder1.append("ticket headers ");
            localStringBuilder1.append(arrayOfHeader1[i]);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            i++;
            continue;
          }
          j = Integer.parseInt(paramHttpResponse.getFirstHeader("Error-Code").getValue());
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("ticket Error-Code : ");
          localStringBuilder2.append(j);
          j.b("TrineaAndroidCommon", localStringBuilder2.toString());
          Header[] arrayOfHeader2 = paramHttpResponse.getHeaders("Sub-Error");
          if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
          {
            String str2 = arrayOfHeader2[0].getValue();
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append(j);
            localStringBuilder4.append("");
            localStringBuilder4.append(str2);
            j = Integer.parseInt(localStringBuilder4.toString());
            StringBuilder localStringBuilder5 = new StringBuilder();
            localStringBuilder5.append("ticket Sub-Error: ");
            localStringBuilder5.append(j);
            j.b("TrineaAndroidCommon", localStringBuilder5.toString());
          }
          InputStream localInputStream = paramHttpResponse.getEntity().getContent();
          if (localInputStream == null)
          {
            return 993;
            byte[] arrayOfByte = b.b(localInputStream).getBytes();
            continue;
            arrayOfByte = b.a(localInputStream);
            if (j != 0)
              break label455;
            String str1 = new String(paramPortalCipher.zsmDecrypt(arrayOfByte));
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append("ticket retData : ");
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
          j.a("TrineaAndroidCommon", localException, "TicketUtils dealTicketResponseCode refuse Exception");
          return 992;
        }
        j.a("TrineaAndroidCommon", localException, "TicketUtils dealTicketResponseCode Exception");
        return 993;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        j.a("TrineaAndroidCommon", localConnectTimeoutException, "TicketUtils dealTicketResponseCode ConnectTimeoutException");
        return 303;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        j.a("TrineaAndroidCommon", localSocketTimeoutException, "TicketUtils dealTicketResponseCode SocketTimeoutException");
        return 303;
      }
      if (j != 13)
        if (j == 200)
        {
          continue;
          label455: if (j != 13)
            if (j != 200);
        }
    }
  }

  private static String a(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><host-name>%s</host-name><user-agent>%s</user-agent><client-id>%s</client-id><ipv4>%s</ipv4><ipv6>%s</ipv6><mac>%s</mac><ostag>");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append(" android ");
    localStringBuilder.append(Build.VERSION.RELEASE);
    localStringBuilder.append("</ostag><local-time>%s</local-time></request>");
    String str = localStringBuilder.toString();
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = d.b();
    arrayOfObject[1] = d.c(paramContext);
    arrayOfObject[2] = d.e(paramContext);
    arrayOfObject[3] = d.f(paramContext);
    arrayOfObject[4] = d.c();
    arrayOfObject[5] = d.a(paramContext);
    arrayOfObject[6] = d.a();
    return String.format(str, arrayOfObject);
  }

  private static void a(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
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
            case 3:
              localEditor.commit();
              break;
            case 2:
              if ("ticket".equals(str))
                localEditor.putString("ticket", localXmlPullParser.nextText());
              else if ("expire".equals(str))
                localEditor.putString("expire", localXmlPullParser.nextText());
              i = localXmlPullParser.next();
            }
          }
          catch (IOException localIOException)
          {
            j.a("TrineaAndroidCommon", localIOException, "TicketUtils getTicketInfo IOException");
          }
          catch (NumberFormatException localNumberFormatException)
          {
            j.a("TrineaAndroidCommon", localNumberFormatException, "TicketUtils getTicketInfo NumberFormatException");
          }
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        j.a("TrineaAndroidCommon", localXmlPullParserException, "TicketUtils getTicketInfo XmlPullParserException");
      }
      return;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.i
 * JD-Core Version:    0.6.1
 */