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
import com.cndatacom.xjhui.model.Device;
import com.google.a.e;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
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
import org.xmlpull.v1.XmlPullParserFactory;

public class f
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
    byte[] arrayOfByte = localPortalCipher.zsmEncrypt(a(paramContext).getBytes());
    if (arrayOfByte == null)
      return 995;
    String str1 = a.a(arrayOfByte);
    String str2 = m.a(paramContext, "query-url");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("query url : ");
    localStringBuilder1.append(str2);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    if (TextUtils.isEmpty(str2))
      return 201;
    try
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpParams localHttpParams = localDefaultHttpClient.getParams();
      ConnManagerParams.setTimeout(localHttpParams, 30000L);
      localHttpParams.setIntParameter("http.connection.timeout", 30000);
      localHttpParams.setIntParameter("http.socket.timeout", 30000);
      HttpPost localHttpPost = new HttpPost(str2);
      localHttpPost.setHeader("Accept", "*/*");
      localHttpPost.setHeader("Content-type", "text/html");
      localHttpPost.addHeader("User-Agent", d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str1);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("query return : ");
      localStringBuilder2.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder2.toString());
      int i = a(paramContext, localHttpResponse, localPortalCipher);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "QueryUtils doQuery refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "QueryUtils doQuery Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "QueryUtils doQuery ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "QueryUtils doQuery SocketTimeoutException");
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
            localStringBuilder1.append("query headers ");
            localStringBuilder1.append(arrayOfHeader1[i]);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            i++;
            continue;
          }
          j = Integer.parseInt(paramHttpResponse.getFirstHeader("Error-Code").getValue());
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("query Error-Code : ");
          localStringBuilder2.append(j);
          j.b("TrineaAndroidCommon", localStringBuilder2.toString());
          Header[] arrayOfHeader2 = paramHttpResponse.getHeaders("Sub-Error");
          if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
          {
            String str3 = arrayOfHeader2[0].getValue();
            StringBuilder localStringBuilder5 = new StringBuilder();
            localStringBuilder5.append(j);
            localStringBuilder5.append("");
            localStringBuilder5.append(str3);
            j = Integer.parseInt(localStringBuilder5.toString());
            StringBuilder localStringBuilder6 = new StringBuilder();
            localStringBuilder6.append("query Sub-Error : ");
            localStringBuilder6.append(j);
            j.b("TrineaAndroidCommon", localStringBuilder6.toString());
          }
          InputStream localInputStream = paramHttpResponse.getEntity().getContent();
          if (localInputStream == null)
          {
            return 993;
            byte[] arrayOfByte = b.b(localInputStream).getBytes();
            continue;
            arrayOfByte = b.a(localInputStream);
            if (j != 0)
              break label533;
            j.b("TrineaAndroidCommon", "query successfully");
            String str1 = new String(paramPortalCipher.zsmDecrypt(arrayOfByte));
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append("query retData : ");
            localStringBuilder3.append(str1);
            j.b("TrineaAndroidCommon", localStringBuilder3.toString());
            ArrayList localArrayList = new ArrayList();
            a(paramContext, str1, localArrayList);
            String str2 = new e().a(localArrayList);
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append("query jsonString : ");
            localStringBuilder4.append(str2);
            j.b("TrineaAndroidCommon", localStringBuilder4.toString());
            m.a(paramContext, "terminalManagerList", str2);
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
          j.a("TrineaAndroidCommon", localException, "QueryUtils dealQueryResponseCode refuse Exception");
          return 992;
        }
        j.a("TrineaAndroidCommon", localException, "QueryUtils dealQueryResponseCode Exception");
        return 993;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        j.a("TrineaAndroidCommon", localConnectTimeoutException, "QueryUtils dealQueryResponseCode ConnectTimeoutException");
        return 303;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        j.a("TrineaAndroidCommon", localSocketTimeoutException, "QueryUtils dealQueryResponseCode SocketTimeoutException");
        return 303;
      }
      if (j != 13)
        if (j == 200)
        {
          continue;
          label533: if (j != 13)
            if (j != 200);
        }
    }
  }

  private static String a(Context paramContext)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = d.c(paramContext);
    arrayOfObject[1] = d.e(paramContext);
    arrayOfObject[2] = d.a();
    return String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><local-time>%s</local-time></request>", arrayOfObject);
  }

  private static void a(Context paramContext, String paramString, List<Device> paramList)
  {
    while (true)
    {
      int i;
      int k;
      try
      {
        XmlPullParser localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
        localXmlPullParser.setInput(new ByteArrayInputStream(paramString.getBytes()), "UTF-8");
        i = localXmlPullParser.getEventType();
        Device localDevice = new Device();
        break label326;
        String str1 = localXmlPullParser.getName();
        int j = str1.hashCode();
        if (j != -1793802575)
        {
          if (j != -1007351010)
          {
            if (j != -873960692)
            {
              if ((j != -299803597) || (!str1.equals("hostname")))
                break label363;
              k = 1;
              break label366;
            }
            if (!str1.equals("ticket"))
              break label363;
            k = 0;
            break label366;
          }
          if (!str1.equals("ostype"))
            break label363;
          k = 3;
          break label366;
        }
        if (!str1.equals("login-time"))
          break label363;
        k = 2;
        break label366;
        if (localXmlPullParser.nextText().equals("phone"))
          localDevice.setType(2);
        else
          localDevice.setType(1);
        paramList.add(localDevice);
        localDevice = new Device();
        continue;
        localDevice.setLoginTime(localXmlPullParser.nextText());
        continue;
        localDevice.setHostName(localXmlPullParser.nextText());
        continue;
        String str2 = localXmlPullParser.nextText();
        String str3 = m.b(paramContext, "ticket", "");
        localDevice.setTicket(str2);
        if (str3.equals(str2))
          localDevice.setLocaltype(1);
        else
          localDevice.setLocaltype(2);
        i = localXmlPullParser.next();
      }
      catch (Exception localException)
      {
        j.a("TrineaAndroidCommon", localException, "QueryUtils getQueryInfo Exception");
      }
      label326: 
      while (i == 1)
        return;
      if (i != 0)
      {
        switch (i)
        {
        case 2:
        case 3:
        }
        continue;
        label363: k = -1;
        label366: switch (k)
        {
        case 3:
        case 2:
        case 1:
        case 0:
        }
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.f
 * JD-Core Version:    0.6.1
 */