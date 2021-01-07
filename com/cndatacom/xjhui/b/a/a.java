package com.cndatacom.xjhui.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
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

public class a
{
  private static int a;

  private static String a(HttpURLConnection paramHttpURLConnection)
  {
    String str1;
    String str2;
    String str5;
    try
    {
      str1 = paramHttpURLConnection.getURL().toString();
      if ((TextUtils.isEmpty(str1)) || ((!str1.contains("wlanuserip")) && (!str1.contains("userip"))))
      {
        str2 = paramHttpURLConnection.getHeaderField("Location");
        if ((!TextUtils.isEmpty(str2)) && ((str2.contains("wlanuserip")) || (str2.contains("userip"))))
          break label290;
        InputStream localInputStream = paramHttpURLConnection.getInputStream();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream));
        StringBuffer localStringBuffer = new StringBuffer();
        while (true)
        {
          String str3 = localBufferedReader.readLine();
          if (str3 == null)
            break;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str3);
          localStringBuilder.append("\n");
          localStringBuffer.append(localStringBuilder.toString());
        }
        localInputStream.close();
        String str4 = localStringBuffer.toString();
        Elements localElements = Jsoup.parse(str4).select("a");
        str5 = "";
        Iterator localIterator = localElements.iterator();
        while (localIterator.hasNext())
          str5 = ((Element)localIterator.next()).attr("href");
        if ((!TextUtils.isEmpty(str5)) && ((str5.contains("wlanuserip")) || (str5.contains("userip"))))
          break label292;
        String str6 = d.a(str4);
        boolean bool = TextUtils.isEmpty(str6);
        if (!bool)
          return str6;
        return "";
      }
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "ConfigUtils getRedirectUrl Exception");
      return "";
    }
    return str1;
    label290: return str2;
    label292: return str5;
  }

  public static void a(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
    localEditor.putString("redirectParameters", "");
    localEditor.putString("wlanuserip", "");
    localEditor.putString("wlanacip", "");
    localEditor.putString("schoolid", "");
    localEditor.putString("domain", "");
    localEditor.putString("area", "");
    localEditor.putString("ticket-url", "");
    localEditor.putString("query-url", "");
    localEditor.putString("authType", "");
    localEditor.putString("authDefault", "");
    localEditor.putString("auth-url", "");
    localEditor.putString("state-url", "");
    localEditor.putString("postfix", "");
    localEditor.putString("notifyRegister", "");
    localEditor.putString("PackageQueryURL", "");
    localEditor.putString("QRcodeEnable", "");
    localEditor.putString("QRcodeWhite", "");
    localEditor.putString("QRcodeBlack", "");
    localEditor.putString("ManagerInternetEnable", "");
    localEditor.putString("ManagerInternetWhite", "");
    localEditor.putString("ManagerInternetBlack", "");
    localEditor.commit();
  }

  private static void a(Context paramContext, String paramString)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      localHttpURLConnection.setConnectTimeout(8000);
      localHttpURLConnection.setReadTimeout(8000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.addRequestProperty("User-Agent", d.c(paramContext));
      a = 1 + a;
      localHttpURLConnection.setInstanceFollowRedirects(false);
      int i = localHttpURLConnection.getResponseCode();
      b(paramContext, paramString);
      if ((i != 302) && (i != 301))
      {
        if (i == 200)
          if (a == 1)
          {
            String str = a(localHttpURLConnection);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("ConfigUtils getConfig redirectUrl : ");
            localStringBuilder.append(str);
            j.b("TrineaAndroidCommon", localStringBuilder.toString());
            if (TextUtils.isEmpty(str))
              a = 0;
            else
              a(paramContext, str);
          }
          else
          {
            a = 0;
            b(paramContext, localHttpURLConnection);
          }
      }
      else
      {
        a(paramContext, localHttpURLConnection);
        a(paramContext, localHttpURLConnection.getHeaderField("Location"));
      }
    }
    catch (Exception localException)
    {
      a = 0;
      j.a("TrineaAndroidCommon", localException, "ConfigUtils getConfig Exception");
    }
  }

  private static void a(Context paramContext, HttpURLConnection paramHttpURLConnection)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
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
          b(paramContext, str5);
      }
    }
    String str1 = paramHttpURLConnection.getHeaderField("area");
    if (!TextUtils.isEmpty(str1))
      localEditor.putString("area", str1);
    String str2 = paramHttpURLConnection.getHeaderField("schoolid");
    if (!TextUtils.isEmpty(str2))
      localEditor.putString("schoolid", str2);
    String str3 = paramHttpURLConnection.getHeaderField("domain");
    if (!TextUtils.isEmpty(str3))
      localEditor.putString("domain", str3);
    localEditor.commit();
  }

  private static void b(Context paramContext, String paramString)
  {
    if ((paramString.contains("wlanuserip")) || (paramString.contains("userip")))
    {
      int i = 0;
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
      String str1 = paramString.substring(1 + paramString.lastIndexOf("?"));
      String[] arrayOfString = str1.split("&");
      localEditor.putString("redirectParameters", str1);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ConfigUtils redirect parameters : ");
      localStringBuilder.append(str1);
      j.b("TrineaAndroidCommon", localStringBuilder.toString());
      while (i < arrayOfString.length)
      {
        String str2 = arrayOfString[i];
        if (str2.contains("wlanuserip"))
        {
          String str5 = str2.split("=")[1];
          if (!TextUtils.isEmpty(str5))
            localEditor.putString("wlanuserip", str5);
        }
        if (str2.contains("userip"))
        {
          String str4 = str2.split("=")[1];
          if (!TextUtils.isEmpty(str4))
            localEditor.putString("wlanuserip", str4);
        }
        if (str2.contains("wlanacip"))
        {
          String str3 = str2.split("=")[1];
          if (!TextUtils.isEmpty(str3))
            localEditor.putString("wlanacip", str3);
        }
        i++;
      }
      localEditor.commit();
    }
  }

  private static void b(Context paramContext, HttpURLConnection paramHttpURLConnection)
  {
    try
    {
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
          String str3 = localStringBuilder2.toString();
          c(paramContext, str3);
          d(paramContext, str3);
          e(paramContext, str3);
        }
      }
    }
    catch (IOException localIOException)
    {
      j.a("TrineaAndroidCommon", localIOException, "ConfigUtils getConfigFromHtml Exception");
    }
  }

  public static boolean b(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("TrineaAndroidCommon", 0);
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

  public static void c(Context paramContext)
  {
    a(paramContext, d(paramContext));
    if (b(paramContext))
    {
      m.a(paramContext, "decideurl", d(paramContext));
    }
    else
    {
      a(paramContext, "http://www.qq.com");
      if (b(paramContext))
      {
        m.a(paramContext, "decideurl", "http://www.qq.com");
      }
      else
      {
        a(paramContext, "http://www.sina.com.cn");
        if (b(paramContext))
        {
          m.a(paramContext, "decideurl", "http://www.sina.com.cn");
        }
        else
        {
          a(paramContext, "http://www.baidu.com");
          if (b(paramContext))
            m.a(paramContext, "decideurl", "http://www.baidu.com");
        }
      }
    }
  }

  private static void c(Context paramContext, String paramString)
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
            String str;
            if ("config".equals(localXmlPullParser.getName()))
            {
              localEditor.commit();
              continue;
              str = localXmlPullParser.getName();
            }
            switch (str.hashCode())
            {
            case 2088386350:
              if (!str.equals("ticket-url"))
                break;
              j = 0;
              break;
            case 1544803905:
              if (!str.equals("default"))
                break;
              j = 5;
              break;
            case 1431110602:
              if (!str.equals("auth-url"))
                break;
              j = 3;
              break;
            case 111972721:
              if (!str.equals("value"))
                break;
              j = 6;
              break;
            case 3575610:
              if (!str.equals("type"))
                break;
              j = 2;
              break;
            case -690213213:
              if (!str.equals("register"))
                break;
              j = 7;
              break;
            case -1808027926:
              if (!str.equals("query-url"))
                break;
              j = 1;
              break;
            case -2087039437:
              if (!str.equals("state-url"))
                break;
              j = 4;
              break label543;
              localEditor.putString("notifyRegister", localXmlPullParser.nextText());
              continue;
              localEditor.putString("postfix", localXmlPullParser.nextText());
              continue;
              localEditor.putString("authDefault", localXmlPullParser.nextText());
              continue;
              localEditor.putString("state-url", localXmlPullParser.nextText());
              continue;
              localEditor.putString("auth-url", localXmlPullParser.nextText());
              continue;
              localEditor.putString("authType", localXmlPullParser.nextText());
              continue;
              localEditor.putString("query-url", localXmlPullParser.nextText());
              continue;
              localEditor.putString("ticket-url", localXmlPullParser.nextText());
              i = localXmlPullParser.next();
            }
          }
          catch (IOException localIOException)
          {
            j.a("TrineaAndroidCommon", localIOException, "ConfigUtils getConfigInfo IOException");
          }
          catch (NumberFormatException localNumberFormatException)
          {
            j.a("TrineaAndroidCommon", localNumberFormatException, "ConfigUtils getConfigInfo NumberFormatException");
          }
        }
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        j.a("TrineaAndroidCommon", localXmlPullParserException, "ConfigUtils getConfigInfo XmlPullParserException");
      }
      return;
      int j = -1;
      label543: switch (j)
      {
      case 7:
      case 6:
      case 5:
      case 4:
      case 3:
      case 2:
      case 1:
      case 0:
      }
    }
  }

  private static String d(Context paramContext)
  {
    String str = m.b(paramContext, "decideurl", "");
    if ("".equals(str))
      return "http://www.baidu.com";
    return str;
  }

  private static void d(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
    Elements localElements = Jsoup.parse(paramString).select("weburl").select("url");
    int i = localElements.size();
    for (int j = 0; j < i; j++)
    {
      Element localElement = localElements.get(j);
      String str = localElement.attr("name");
      switch (str.hashCode())
      {
      default:
        break;
      case 2048508749:
        if (str.equals("PhoneAdvertURL"))
          k = 3;
        break;
      case 1405056848:
        if (str.equals("UpdateCheckURL"))
          k = 2;
        break;
      case 424056957:
        if (str.equals("SubErrorDataURL"))
          k = 1;
        break;
      case 252364237:
        if (str.equals("PackageQueryURL"))
          k = 5;
        break;
      case -1235725225:
        if (str.equals("GetPhoneMarketingDataURL"))
          k = 0;
        break;
      case -1755071529:
        if (str.equals("ManualSubErrorDataURL"))
          k = 4;
        break;
      }
      int k = -1;
      switch (k)
      {
      default:
        break;
      case 5:
        localEditor.putString("PackageQueryURL", localElement.attr("value"));
        break;
      case 4:
        localEditor.putString("ManualSubErrorDataURL", localElement.attr("value"));
        break;
      case 3:
        localEditor.putString("phoneAdvertURL", localElement.attr("value"));
        break;
      case 2:
        localEditor.putString("UpdateCheckURL", localElement.attr("value"));
        break;
      case 1:
        localEditor.putString("SubErrorDataURL", localElement.attr("value"));
        break;
      case 0:
        localEditor.putString("GetPhoneMarketingDataURL", localElement.attr("value"));
      }
    }
    localEditor.commit();
  }

  private static void e(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
    Elements localElements = Jsoup.parse(paramString).select("moblie").select("funcfg").select("cfg");
    int i = localElements.size();
    for (int j = 0; j < i; j++)
    {
      Element localElement = localElements.get(j);
      String str = localElement.attr("name");
      int k = str.hashCode();
      if (k != -1897218162)
      {
        if ((k == 2012607982) && (str.equals("ManagerInternet")))
        {
          m = 1;
          break label133;
        }
      }
      else if (str.equals("QRcode"))
      {
        m = 0;
        break label133;
      }
      int m = -1;
      switch (m)
      {
      default:
        break;
      case 1:
        localEditor.putString("ManagerInternetEnable", localElement.attr("enable"));
        if (localElement.hasAttr("whiteSch"))
          localEditor.putString("ManagerInternetWhite", localElement.attr("whiteSch"));
        else
          localEditor.putString("ManagerInternetWhite", "");
        if (localElement.hasAttr("blackSch"))
          localEditor.putString("ManagerInternetBlack", localElement.attr("blackSch"));
        else
          localEditor.putString("ManagerInternetBlack", "");
        break;
      case 0:
        label133: localEditor.putString("QRcodeEnable", localElement.attr("enable"));
        if (localElement.hasAttr("whiteSch"))
          localEditor.putString("QRcodeWhite", localElement.attr("whiteSch"));
        else
          localEditor.putString("QRcodeWhite", "");
        if (localElement.hasAttr("blackSch"))
          localEditor.putString("QRcodeBlack", localElement.attr("blackSch"));
        else
          localEditor.putString("QRcodeBlack", "");
        break;
      }
    }
    localEditor.commit();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.a.a
 * JD-Core Version:    0.6.1
 */