package com.cndatacom.xjhui.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.cndatacom.e.i;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class b
{
  public static boolean a(Context paramContext)
  {
    return true ^ "".equals(m.b(paramContext, "ticket", ""));
  }

  public static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    try
    {
      while (true)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      paramInputStream.close();
      localByteArrayOutputStream.flush();
    }
    catch (IOException localIOException)
    {
      j.a("TrineaAndroidCommon", localIOException, "LoginUtils portalReadInputStream IOException");
    }
    return localByteArrayOutputStream.toByteArray();
  }

  public static String b(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder;
    for (String str1 = ""; ; str1 = localStringBuilder.toString())
    {
      String str2 = localBufferedReader.readLine();
      if (str2 == null)
        break;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str1);
      localStringBuilder.append(str2);
    }
    paramInputStream.close();
    localBufferedReader.close();
    return str1;
  }

  public static boolean b(Context paramContext)
  {
    String str1 = m.b(paramContext, "keep-url", "");
    String str2 = m.b(paramContext, "term-url", "");
    boolean bool;
    if ((!"".equals(str1)) && (!"".equals(str2)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static boolean c(Context paramContext)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL("http://www.qq.com").openConnection();
      localHttpURLConnection.setConnectTimeout(8000);
      localHttpURLConnection.setReadTimeout(8000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.addRequestProperty("User-Agent", d.b(paramContext));
      localHttpURLConnection.getResponseCode();
      String str = localHttpURLConnection.getURL().toString();
      boolean bool1 = str.contains("userip");
      boolean bool2 = false;
      if (!bool1)
      {
        boolean bool3 = str.contains("wlanuserip");
        bool2 = false;
        if (!bool3)
          bool2 = true;
      }
      return bool2;
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "EveryThingsUtils isHaveNet Exception");
    }
    return false;
  }

  public static void d(Context paramContext)
  {
    WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if (localWifiInfo != null)
      m.a(paramContext, "WifiInfo", localWifiInfo.getSSID());
  }

  public static boolean e(Context paramContext)
  {
    String str1 = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).getString("wlanuserip", "");
    String str2 = i.a();
    String str3 = i.a(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("wlanuserip : ");
    localStringBuilder.append(str1);
    localStringBuilder.append(" ip1 : ");
    localStringBuilder.append(str2);
    localStringBuilder.append(" ip2 : ");
    localStringBuilder.append(str3);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    if (!"".equals(str1))
    {
      boolean bool1;
      if (!str2.contains(str1))
      {
        boolean bool2 = str3.contains(str1);
        bool1 = false;
        if (!bool2);
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }

  public static boolean f(Context paramContext)
  {
    if (b(paramContext))
      return g(paramContext);
    return false;
  }

  public static boolean g(Context paramContext)
  {
    WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    String str1 = "";
    String str2 = "";
    if (localWifiInfo != null)
    {
      str1 = localWifiInfo.getSSID();
      str2 = m.b(paramContext, "WifiInfo", "");
    }
    if ((!"".equals(str2)) && (!"".equals(str1)))
      return str1.equals(str2);
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.a.b
 * JD-Core Version:    0.6.1
 */