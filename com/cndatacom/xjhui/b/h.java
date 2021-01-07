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

public class h
{
  public static int a(Context paramContext, int paramInt, String paramString1, String paramString2)
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
    localStringBuilder1.append("doTerm account : ");
    localStringBuilder1.append(paramString1);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    localPortalCipher.setUser(paramString1);
    localPortalCipher.getZsmInfo();
    String str1 = a(paramContext, paramString2, paramInt);
    if (str1 == null)
      return 995;
    byte[] arrayOfByte = localPortalCipher.zsmEncrypt(str1.getBytes());
    if (arrayOfByte == null)
      return 995;
    String str2 = a.a(arrayOfByte);
    String str3 = m.b(paramContext, "term-url", "");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("term url : ");
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
      localHttpPost.addHeader("User-Agent", d.c(paramContext));
      localHttpPost.addHeader("Algo-ID", localPortalCipher.getAlgoId());
      localHttpPost.addHeader("Client-ID", d.e(paramContext));
      localHttpPost.addHeader("CDC-Checksum", str2);
      localHttpPost.setEntity(new StringEntity(new String(arrayOfByte)));
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("term return : ");
      localStringBuilder3.append(localHttpResponse.getStatusLine().getStatusCode());
      j.b("TrineaAndroidCommon", localStringBuilder3.toString());
      int i = a(paramContext, localHttpResponse, localPortalCipher, paramInt);
      return i;
    }
    catch (Exception localException)
    {
      if (localException.toString().contains("refuse"))
      {
        j.a("TrineaAndroidCommon", localException, "TermUtils doTerm refuse Exception");
        return 992;
      }
      j.a("TrineaAndroidCommon", localException, "TermUtils doTerm Exception");
      return 993;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "TermUtils doTerm ConnectTimeoutException");
      return 303;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      j.a("TrineaAndroidCommon", localSocketTimeoutException, "TermUtils doTerm SocketTimeoutException");
    }
    return 303;
  }

  private static int a(Context paramContext, HttpResponse paramHttpResponse, PortalCipher paramPortalCipher, int paramInt)
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
            localStringBuilder1.append("term headers ");
            localStringBuilder1.append(arrayOfHeader1[i]);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            i++;
            continue;
          }
          j = Integer.parseInt(paramHttpResponse.getFirstHeader("Error-Code").getValue());
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("term Error-Code : ");
          localStringBuilder2.append(j);
          j.b("TrineaAndroidCommon", localStringBuilder2.toString());
          Header[] arrayOfHeader2 = paramHttpResponse.getHeaders("Sub-Error");
          if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
          {
            String str = arrayOfHeader2[0].getValue();
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append(j);
            localStringBuilder3.append("");
            localStringBuilder3.append(str);
            j = Integer.parseInt(localStringBuilder3.toString());
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append("term Sub-Code : ");
            localStringBuilder4.append(j);
            j.b("TrineaAndroidCommon", localStringBuilder4.toString());
          }
          InputStream localInputStream = paramHttpResponse.getEntity().getContent();
          if (localInputStream == null)
          {
            return 993;
            byte[] arrayOfByte = b.b(localInputStream).getBytes();
            continue;
            arrayOfByte = b.a(localInputStream);
            if (j != 0)
              break label421;
            j.b("TrineaAndroidCommon", "term successfully");
            if (paramInt == 8)
              break label418;
            g.c(paramContext);
            break label418;
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
          j.a("TrineaAndroidCommon", localException, "TermUtils dealTermResponseCode refuse Exception");
          return 992;
        }
        j.a("TrineaAndroidCommon", localException, "TermUtils dealTermResponseCode Exception");
        return 993;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        j.a("TrineaAndroidCommon", localConnectTimeoutException, "TermUtils dealTermResponseCode ConnectTimeoutException");
        return 303;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        j.a("TrineaAndroidCommon", localSocketTimeoutException, "TermUtils dealTermResponseCode SocketTimeoutException");
        return 303;
      }
      if (j != 13)
        if (j == 200)
        {
          continue;
          label418: return j;
          label421: if (j != 13)
            if (j != 200);
        }
    }
  }

  private static String a(Context paramContext, String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("term ticket : ");
    localStringBuilder.append(paramString);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    if (TextUtils.isEmpty(paramString))
      return null;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = d.c(paramContext);
    arrayOfObject[1] = d.e(paramContext);
    arrayOfObject[2] = paramString;
    arrayOfObject[3] = Integer.valueOf(paramInt);
    arrayOfObject[4] = d.a();
    return String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><user-agent>%s</user-agent><client-id>%s</client-id><ticket>%s</ticket><reason>%s</reason><local-time>%s</local-time></request>", arrayOfObject);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.h
 * JD-Core Version:    0.6.1
 */