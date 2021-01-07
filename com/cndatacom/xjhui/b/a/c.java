package com.cndatacom.xjhui.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.cndatacom.e.j;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class c
{
  private static int a;

  private static String a(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      String str1 = paramHttpURLConnection.getURL().toString();
      if ((!TextUtils.isEmpty(str1)) && ((str1.contains("wlanuserip")) || (str1.contains("userip"))))
      {
        StringBuilder localStringBuilder5 = new StringBuilder();
        localStringBuilder5.append("PortalNetworkHelper getRedirectUrl url1 : ");
        localStringBuilder5.append(str1);
        j.b("TrineaAndroidCommonConfig", localStringBuilder5.toString());
        return str1;
      }
      String str2 = paramHttpURLConnection.getHeaderField("Location");
      if ((!TextUtils.isEmpty(str2)) && ((str2.contains("wlanuserip")) || (str2.contains("userip"))))
      {
        StringBuilder localStringBuilder4 = new StringBuilder();
        localStringBuilder4.append("PortalNetworkHelper getRedirectUrl url2 : ");
        localStringBuilder4.append(str2);
        j.b("TrineaAndroidCommonConfig", localStringBuilder4.toString());
        return str2;
      }
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream));
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        String str3 = localBufferedReader.readLine();
        if (str3 == null)
          break;
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(str3);
        localStringBuilder1.append("\n");
        localStringBuffer.append(localStringBuilder1.toString());
      }
      localInputStream.close();
      String str4 = localStringBuffer.toString();
      String str5 = "";
      Iterator localIterator = Jsoup.parse(str4).select("a").iterator();
      while (localIterator.hasNext())
        str5 = ((Element)localIterator.next()).attr("href");
      if ((!TextUtils.isEmpty(str5)) && ((str5.contains("wlanuserip")) || (str5.contains("userip"))))
      {
        StringBuilder localStringBuilder3 = new StringBuilder();
        localStringBuilder3.append("PortalNetworkHelper getRedirectUrl url3 : ");
        localStringBuilder3.append(str5);
        j.b("TrineaAndroidCommonConfig", localStringBuilder3.toString());
        return str5;
      }
      String str6 = d.a(str4);
      if (!TextUtils.isEmpty(str6))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("PortalNetworkHelper getRedirectUrl url4 : ");
        localStringBuilder2.append(str6);
        j.b("TrineaAndroidCommonConfig", localStringBuilder2.toString());
        return str6;
      }
      return "";
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommonConfig", localException, "PortalNetworkHelper getRedirectUrl Exception");
    }
    return "";
  }

  public static void a(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString, 0).edit();
    localEditor.putString("ticket-url", "");
    localEditor.putString("auth-url", "");
    localEditor.commit();
  }

  private static void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString2).openConnection();
      localHttpURLConnection.setConnectTimeout(8000);
      localHttpURLConnection.setReadTimeout(8000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.addRequestProperty("User-Agent", d.c(paramContext));
      a = 1 + a;
      localHttpURLConnection.setInstanceFollowRedirects(false);
      int i = localHttpURLConnection.getResponseCode();
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("PortalNetworkHelper getConfig url : ");
      localStringBuilder1.append(paramString2);
      j.b("TrineaAndroidCommonConfig", localStringBuilder1.toString());
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("PortalNetworkHelper getConfig code : ");
      localStringBuilder2.append(i);
      j.b("TrineaAndroidCommonConfig", localStringBuilder2.toString());
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("PortalNetworkHelper getConfig count : ");
      localStringBuilder3.append(a);
      j.b("TrineaAndroidCommonConfig", localStringBuilder3.toString());
      if ((i != 302) && (i != 301))
      {
        if (i == 200)
          if (a == 1)
          {
            String str = a(localHttpURLConnection);
            if (TextUtils.isEmpty(str))
            {
              a = 0;
              j.b("TrineaAndroidCommonConfig", "PortalNetworkHelper getConfig redirectUrl == null");
            }
            else
            {
              a(paramContext, paramString1, str);
            }
          }
          else
          {
            a = 0;
            a(paramContext, paramString1, localHttpURLConnection);
          }
      }
      else
      {
        a(paramContext, localHttpURLConnection);
        a(paramContext, paramString1, localHttpURLConnection.getHeaderField("Location"));
      }
    }
    catch (Exception localException)
    {
      a = 0;
      paramContext.getSharedPreferences(paramString1, 0).edit().putBoolean("getConfigException", true).commit();
      j.a("TrineaAndroidCommonConfig", localException, "PortalNetworkHelper getConfig Exception");
    }
  }

  private static void a(Context paramContext, String paramString, HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString, 0);
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream));
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        String str1 = localBufferedReader.readLine();
        if (str1 == null)
          break;
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(str1);
        localStringBuilder1.append("\n");
        localStringBuffer.append(localStringBuilder1.toString());
      }
      localInputStream.close();
      String str2 = new String(localStringBuffer.toString());
      int i = str2.indexOf("<!--//config.campus.js.chinatelecom.com");
      if (i >= 0)
      {
        int j = str2.indexOf("//config.campus.js.chinatelecom.com-->", i + "<!--//config.campus.js.chinatelecom.com".length());
        if (j > 0)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
          localStringBuilder2.append(str2.substring(i + "<!--//config.campus.js.chinatelecom.com".length(), j));
          b(paramContext, paramString, localStringBuilder2.toString());
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("PortalNetworkHelper ticket-url : ");
          localStringBuilder3.append(localSharedPreferences.getString("ticket-url", null));
          j.b("TrineaAndroidCommonConfig", localStringBuilder3.toString());
          StringBuilder localStringBuilder4 = new StringBuilder();
          localStringBuilder4.append("PortalNetworkHelper auth-url : ");
          localStringBuilder4.append(localSharedPreferences.getString("auth-url", null));
          j.b("TrineaAndroidCommonConfig", localStringBuilder4.toString());
        }
      }
    }
    catch (IOException localIOException)
    {
      j.a("TrineaAndroidCommonConfig", localIOException, "PortalNetworkHelper gettotaldata2 Exception");
    }
  }

  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString, 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (paramBoolean)
    {
      localEditor.putBoolean("getConfigException", false);
      localEditor.commit();
    }
    a(paramContext, paramString, c(paramContext, paramString));
    if (b(paramContext, paramString))
    {
      localEditor.putString("decideurl", c(paramContext, paramString));
    }
    else
    {
      if ((paramBoolean) && (localSharedPreferences.getBoolean("getConfigException", false)))
        return;
      a(paramContext, paramString, "http://www.qq.com");
      if (b(paramContext, paramString))
      {
        localEditor.putString("decideurl", "http://www.qq.com");
      }
      else
      {
        a(paramContext, paramString, "http://www.sina.com.cn");
        if (b(paramContext, paramString))
        {
          localEditor.putString("decideurl", "http://www.sina.com.cn");
        }
        else
        {
          a(paramContext, paramString, "http://www.baidu.com");
          if (b(paramContext, paramString))
            localEditor.putString("decideurl", "http://www.baidu.com");
        }
      }
    }
    localEditor.commit();
  }

  private static void a(Context paramContext, HttpURLConnection paramHttpURLConnection)
  {
    Map localMap = paramHttpURLConnection.getHeaderFields();
    Iterator localIterator1 = localMap.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str4 = (String)localIterator1.next();
      Iterator localIterator2 = ((List)localMap.get(str4)).iterator();
      while (localIterator2.hasNext())
      {
        String str5 = (String)localIterator2.next();
        if ((str4 != null) && (str4.equals("Location")))
        {
          StringBuilder localStringBuilder4 = new StringBuilder();
          localStringBuilder4.append("PortalNetworkHelper Location : ");
          localStringBuilder4.append(str5);
          j.b("TrineaAndroidCommonConfig", localStringBuilder4.toString());
        }
      }
    }
    String str1 = paramHttpURLConnection.getHeaderField("area");
    String str2 = paramHttpURLConnection.getHeaderField("domain");
    String str3 = paramHttpURLConnection.getHeaderField("schoolid");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("PortalNetworkHelper area : ");
    localStringBuilder1.append(str1);
    j.b("TrineaAndroidCommonConfig", localStringBuilder1.toString());
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("PortalNetworkHelper domain : ");
    localStringBuilder2.append(str2);
    j.b("TrineaAndroidCommonConfig", localStringBuilder2.toString());
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append("PortalNetworkHelper schoolid : ");
    localStringBuilder3.append(str3);
    j.b("TrineaAndroidCommonConfig", localStringBuilder3.toString());
  }

  private static void b(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(paramString1, 0).edit();
    while (true)
    {
      try
      {
        XmlPullParser localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
        localXmlPullParser.setInput(new ByteArrayInputStream(paramString2.getBytes()), "UTF-8");
        int i = localXmlPullParser.getEventType();
        j = 1;
        if (i != j)
        {
          if (i != 0)
            switch (i)
            {
            default:
              break;
            case 3:
            case 2:
            }
          try
          {
            if ("config".equals(localXmlPullParser.getName()))
            {
              localEditor.commit();
              continue;
              String str = localXmlPullParser.getName();
              int k = str.hashCode();
              if (k != 1431110602)
              {
                if ((k != 2088386350) || (!str.equals("ticket-url")))
                  break label271;
                j = 0;
                break label274;
              }
              if (!str.equals("auth-url"))
                break label271;
              break label274;
              localEditor.putString("auth-url", localXmlPullParser.nextText());
              continue;
              localEditor.putString("ticket-url", localXmlPullParser.nextText());
            }
            i = localXmlPullParser.next();
          }
          catch (IOException localIOException)
          {
            j.a("TrineaAndroidCommonConfig", localIOException, "PortalNetworkHelper getConfigInfo IOException");
          }
          catch (NumberFormatException localNumberFormatException)
          {
            j.a("TrineaAndroidCommonConfig", localNumberFormatException, "PortalNetworkHelper getConfigInfo NumberFormatException");
          }
        }
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        j.a("TrineaAndroidCommonConfig", localXmlPullParserException, "PortalNetworkHelper getConfigInfo XmlPullParserException");
      }
      return;
      label271: int j = -1;
      label274: switch (j)
      {
      case 1:
      case 0:
      }
    }
  }

  public static boolean b(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString, 0);
    String str1 = localSharedPreferences.getString("ticket-url", "");
    String str2 = localSharedPreferences.getString("auth-url", "");
    boolean bool1 = "".equals(str1);
    boolean bool2 = false;
    if (!bool1)
    {
      boolean bool3 = "".equals(str2);
      bool2 = false;
      if (!bool3)
        bool2 = true;
    }
    return bool2;
  }

  private static String c(Context paramContext, String paramString)
  {
    String str = paramContext.getSharedPreferences(paramString, 0).getString("decideurl", "");
    if ("".equals(str))
      return "http://www.baidu.com";
    return str;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.a.c
 * JD-Core Version:    0.6.1
 */