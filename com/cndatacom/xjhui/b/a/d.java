package com.cndatacom.xjhui.b.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.cndatacom.e.a.a;
import com.cndatacom.e.i;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class d
{
  public static String a()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
  }

  static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(0xFF & paramInt >> 8);
    localStringBuilder.append(".");
    localStringBuilder.append(0xFF & paramInt >> 16);
    localStringBuilder.append(".");
    localStringBuilder.append(0xFF & paramInt >> 24);
    return localStringBuilder.toString();
  }

  public static String a(Context paramContext)
  {
    String str = m.b(paramContext, "macAddress", "");
    try
    {
      if (TextUtils.isEmpty(str))
      {
        str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if ((Build.VERSION.SDK_INT >= 23) && (str.equals("02:00:00:00:00:00")))
          str = d();
        if (TextUtils.isEmpty(str))
          str = e();
        if ((!TextUtils.isEmpty(str)) && (str.contains("-")))
          str = str.replaceAll("-", ":");
        if ((!TextUtils.isEmpty(str)) && (!str.equals("02:00:00:00:00:00")))
          m.a(paramContext, "macAddress", str);
        else
          str = "00:00:00:00:00:00";
      }
    }
    catch (Exception localException)
    {
      str = "00:00:00:00:00:00";
      j.a("TrineaAndroidCommon", localException, "PortalParams getMacAddr Exception");
    }
    return str;
  }

  public static String a(Context paramContext, String paramString)
  {
    String str1 = m.b(paramContext, "postfix", "");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("setPostfix postfix : ");
    localStringBuilder1.append(str1);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    String str2;
    if (paramString.contains("@"))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString.split("@")[0]);
      localStringBuilder2.append(str1);
      str2 = localStringBuilder2.toString();
    }
    else
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(paramString);
      localStringBuilder3.append(str1);
      str2 = localStringBuilder3.toString();
    }
    return str2;
  }

  public static String a(String paramString)
  {
    Elements localElements = Jsoup.parse(paramString.toLowerCase(Locale.US)).getAllElements();
    for (int i = 0; i < localElements.size(); i++)
    {
      Element localElement = localElements.get(i);
      if ((localElement != null) && (localElement.hasAttr("content")) && (localElement.nodeName().equalsIgnoreCase("meta")))
      {
        localObject = localElement.attr("content");
        if ((localObject != null) && ((((String)localObject).contains("wlanuserip")) || (((String)localObject).contains("userip"))))
          break label348;
      }
      if ((localElement != null) && (localElement.hasAttr("url")))
      {
        localObject = localElement.attr("url");
        if ((localObject != null) && ((((String)localObject).contains("wlanuserip")) || (((String)localObject).contains("userip"))))
          break label348;
      }
      if ((localElement != null) && (localElement.hasAttr("href")))
      {
        localObject = localElement.attr("href");
        if ((localObject != null) && ((((String)localObject).contains("wlanuserip")) || (((String)localObject).contains("userip"))))
          break label348;
      }
      if ((localElement != null) && (localElement.hasAttr("src")))
      {
        localObject = localElement.attr("src");
        if ((localObject != null) && ((((String)localObject).contains("wlanuserip")) || (((String)localObject).contains("userip"))))
          break label348;
      }
      if ((localElement != null) && (localElement.hasAttr("action")))
      {
        localObject = localElement.attr("action");
        if ((localObject != null) && ((((String)localObject).contains("wlanuserip")) || (((String)localObject).contains("userip"))))
          break label348;
      }
      if ((localElement != null) && (localElement.hasAttr("location")))
      {
        String str = localElement.attr("location");
        if ((str != null) && ((str.contains("wlanuserip")) || (str.contains("userip"))))
        {
          localObject = str;
          break label348;
        }
      }
    }
    Object localObject = "";
    label348: return localObject;
  }

  public static String b()
  {
    return Build.MODEL;
  }

  public static String b(Context paramContext)
  {
    try
    {
      String str = "CCTP/ANDROID/@".replace("@", String.valueOf(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode));
      return str;
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "PortalParams getUserAgent Exception");
    }
    return "CCTP/ANDROID/1";
  }

  public static String c()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration2;
        while (!localEnumeration2.hasMoreElements())
        {
          if (!localEnumeration1.hasMoreElements())
            break;
          localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration2.nextElement();
      }
      while ((localInetAddress.isLoopbackAddress()) || (localInetAddress.isAnyLocalAddress()));
      String str = localInetAddress.getHostAddress().toString();
      return str;
    }
    catch (SocketException localSocketException)
    {
      j.a("TrineaAndroidCommon", localSocketException, "PortalParams getIpv6 SocketException");
    }
    return "fe80::d59c:43f8:b941:28d3%11";
  }

  public static String c(Context paramContext)
  {
    try
    {
      String str = "CCTP/AndroidPhone/@".replace("@", String.valueOf(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode));
      return str;
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "PortalParams getTicketUserAgent Exception");
    }
    return "CCTP/AndroidPhone/1";
  }

  public static int d(Context paramContext)
  {
    if (paramContext != null)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
          if (localPackageInfo != null)
          {
            int i = localPackageInfo.versionCode;
            return i;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          j.a("TrineaAndroidCommon", localNameNotFoundException, "PortalParams getAppVersionCode NameNotFoundException");
        }
    }
    return -1;
  }

  @SuppressLint({"NewApi"})
  private static String d()
  {
    Enumeration localEnumeration = NetworkInterface.getNetworkInterfaces();
    while (localEnumeration.hasMoreElements())
    {
      NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration.nextElement();
      byte[] arrayOfByte = localNetworkInterface.getHardwareAddress();
      if ((arrayOfByte != null) && (arrayOfByte.length != 0))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        int i = arrayOfByte.length;
        for (int j = 0; j < i; j++)
        {
          byte b = arrayOfByte[j];
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Byte.valueOf(b);
          localStringBuilder.append(String.format("%02X:", arrayOfObject));
        }
        if (localStringBuilder.length() > 0)
          localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        String str = localStringBuilder.toString();
        if ("wlan0".equals(localNetworkInterface.getName()))
          return str;
      }
    }
    return "";
  }

  // ERROR //
  private static String e()
  {
    // Byte code:
    //   0: ldc 60
    //   2: astore_0
    //   3: new 331	java/io/LineNumberReader
    //   6: dup
    //   7: new 333	java/io/InputStreamReader
    //   10: dup
    //   11: invokestatic 339	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   14: ldc_w 341
    //   17: invokevirtual 345	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   20: invokevirtual 351	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   23: invokespecial 354	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   26: invokespecial 357	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   29: astore_1
    //   30: aload_1
    //   31: invokevirtual 360	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   34: astore 18
    //   36: aload 18
    //   38: ifnull +43 -> 81
    //   41: new 38	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 41	java/lang/StringBuilder:<init>	()V
    //   48: astore 19
    //   50: aload 19
    //   52: aload_0
    //   53: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload 19
    //   59: aload 18
    //   61: invokevirtual 363	java/lang/String:trim	()Ljava/lang/String;
    //   64: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload 19
    //   70: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 22
    //   75: aload 22
    //   77: astore_0
    //   78: goto -48 -> 30
    //   81: aload_1
    //   82: ifnull +58 -> 140
    //   85: aload_1
    //   86: invokevirtual 366	java/io/LineNumberReader:close	()V
    //   89: goto +51 -> 140
    //   92: astore 17
    //   94: ldc 129
    //   96: aload 17
    //   98: ldc_w 368
    //   101: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   104: goto +36 -> 140
    //   107: astore 4
    //   109: goto +13 -> 122
    //   112: astore_2
    //   113: aconst_null
    //   114: astore_1
    //   115: goto +240 -> 355
    //   118: astore 4
    //   120: aconst_null
    //   121: astore_1
    //   122: ldc 129
    //   124: aload 4
    //   126: ldc_w 370
    //   129: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   132: aload_1
    //   133: ifnull +7 -> 140
    //   136: aload_1
    //   137: invokevirtual 366	java/io/LineNumberReader:close	()V
    //   140: aload_0
    //   141: invokestatic 72	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   144: ifeq +208 -> 352
    //   147: new 372	java/io/FileReader
    //   150: dup
    //   151: ldc_w 374
    //   154: invokespecial 377	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   157: astore 5
    //   159: new 38	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 41	java/lang/StringBuilder:<init>	()V
    //   166: astore 6
    //   168: sipush 4096
    //   171: newarray char
    //   173: astore 11
    //   175: aload 5
    //   177: aload 11
    //   179: invokevirtual 381	java/io/FileReader:read	([C)I
    //   182: istore 12
    //   184: iload 12
    //   186: iflt +26 -> 212
    //   189: aload 6
    //   191: aload 11
    //   193: iconst_0
    //   194: iload 12
    //   196: invokevirtual 384	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   199: pop
    //   200: aload 5
    //   202: aload 11
    //   204: invokevirtual 381	java/io/FileReader:read	([C)I
    //   207: istore 12
    //   209: goto -25 -> 184
    //   212: aload 6
    //   214: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: getstatic 16	java/util/Locale:CHINA	Ljava/util/Locale;
    //   220: invokevirtual 387	java/lang/String:toUpperCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   223: iconst_0
    //   224: bipush 17
    //   226: invokevirtual 391	java/lang/String:substring	(II)Ljava/lang/String;
    //   229: astore 13
    //   231: aload 5
    //   233: ifnull +23 -> 256
    //   236: aload 5
    //   238: invokevirtual 392	java/io/FileReader:close	()V
    //   241: goto +15 -> 256
    //   244: astore 14
    //   246: ldc 129
    //   248: aload 14
    //   250: ldc_w 394
    //   253: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   256: aload 13
    //   258: astore_0
    //   259: goto +93 -> 352
    //   262: astore 9
    //   264: goto +20 -> 284
    //   267: astore 7
    //   269: aconst_null
    //   270: astore 5
    //   272: goto +52 -> 324
    //   275: astore 16
    //   277: aconst_null
    //   278: astore 5
    //   280: aload 16
    //   282: astore 9
    //   284: ldc 129
    //   286: aload 9
    //   288: ldc_w 396
    //   291: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   294: aload 5
    //   296: ifnull +56 -> 352
    //   299: aload 5
    //   301: invokevirtual 392	java/io/FileReader:close	()V
    //   304: goto +48 -> 352
    //   307: astore 10
    //   309: ldc 129
    //   311: aload 10
    //   313: ldc_w 394
    //   316: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   319: goto +33 -> 352
    //   322: astore 7
    //   324: aload 5
    //   326: ifnull +23 -> 349
    //   329: aload 5
    //   331: invokevirtual 392	java/io/FileReader:close	()V
    //   334: goto +15 -> 349
    //   337: astore 8
    //   339: ldc 129
    //   341: aload 8
    //   343: ldc_w 394
    //   346: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   349: aload 7
    //   351: athrow
    //   352: aload_0
    //   353: areturn
    //   354: astore_2
    //   355: aload_1
    //   356: ifnull +20 -> 376
    //   359: aload_1
    //   360: invokevirtual 366	java/io/LineNumberReader:close	()V
    //   363: goto +13 -> 376
    //   366: astore_3
    //   367: ldc 129
    //   369: aload_3
    //   370: ldc_w 368
    //   373: invokestatic 136	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
    //   376: aload_2
    //   377: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   85	89	92	java/io/IOException
    //   136	140	92	java/io/IOException
    //   30	75	107	java/lang/Exception
    //   3	30	112	finally
    //   3	30	118	java/lang/Exception
    //   236	241	244	java/io/IOException
    //   159	231	262	java/lang/Exception
    //   147	159	267	finally
    //   147	159	275	java/lang/Exception
    //   299	304	307	java/io/IOException
    //   159	231	322	finally
    //   284	294	322	finally
    //   329	334	337	java/io/IOException
    //   30	75	354	finally
    //   122	132	354	finally
    //   359	363	366	java/io/IOException
  }

  public static String e(Context paramContext)
  {
    return g(paramContext);
  }

  private static String f()
  {
    String str;
    try
    {
      Class localClass = Class.forName("android.os.SystemProperties");
      str = (String)localClass.getMethod("get", new Class[] { String.class }).invoke(localClass, new Object[] { "ro.serialno" });
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "PortalParams getSerialNumber Exception");
      str = null;
    }
    return str;
  }

  public static String f(Context paramContext)
  {
    WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
    if (localWifiManager.isWifiEnabled())
    {
      String str3 = a(localWifiManager.getConnectionInfo().getIpAddress());
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("getIpv4 : ");
      localStringBuilder3.append(str3);
      j.b("TrineaAndroidCommon", localStringBuilder3.toString());
      if (!"0.0.0.0".equals(str3))
        return str3;
    }
    String str1 = i.a();
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("getIpv4 ip1 : ");
    localStringBuilder1.append(str1);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    if ((str1 != null) && (!"".equals(str1)) && (!"0.0.0.0".equals(str1.trim())))
      return str1.trim();
    String str2 = i.a(paramContext);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("getIpv4 ip3 : ");
    localStringBuilder2.append(str2);
    j.b("TrineaAndroidCommon", localStringBuilder2.toString());
    if ((str2 != null) && (!"".equals(str2)) && (!"0.0.0.0".equals(str2.trim())))
      return str2.trim();
    return "0.0.0.0";
  }

  private static String g(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("");
    localStringBuilder1.append(localTelephonyManager.getDeviceId());
    String str1 = localStringBuilder1.toString();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("");
    localStringBuilder2.append(localTelephonyManager.getSimSerialNumber());
    String str2 = localStringBuilder2.toString();
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append("");
    localStringBuilder3.append(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
    String str3 = localStringBuilder3.toString();
    StringBuilder localStringBuilder4 = new StringBuilder();
    localStringBuilder4.append("getClientId 1 getIMEI : ");
    localStringBuilder4.append(str1);
    j.b("TrineaAndroidCommon", localStringBuilder4.toString());
    StringBuilder localStringBuilder5 = new StringBuilder();
    localStringBuilder5.append("getClientId 1 getIMEI : ");
    localStringBuilder5.append(str2);
    j.b("TrineaAndroidCommon", localStringBuilder5.toString());
    StringBuilder localStringBuilder6 = new StringBuilder();
    localStringBuilder6.append("getClientId 1 getIMEI : ");
    localStringBuilder6.append(str3);
    j.b("TrineaAndroidCommon", localStringBuilder6.toString());
    UUID localUUID = new UUID(str3.hashCode(), str1.hashCode() << 32 | str2.hashCode());
    if (!TextUtils.isEmpty(localUUID.toString()))
    {
      StringBuilder localStringBuilder7 = new StringBuilder();
      localStringBuilder7.append("getClientId 1 getIMEI finally : ");
      localStringBuilder7.append(localUUID.toString());
      j.b("TrineaAndroidCommon", localStringBuilder7.toString());
      return localUUID.toString();
    }
    String str4 = h(paramContext);
    StringBuilder localStringBuilder8 = new StringBuilder();
    localStringBuilder8.append("getClientId 2 getIMEI finally : ");
    localStringBuilder8.append(str4);
    j.b("TrineaAndroidCommon", localStringBuilder8.toString());
    return str4;
  }

  private static String h(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    if (TextUtils.isEmpty(str))
    {
      str = f();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("getClientId 2 getIMEI getSerialNumber : ");
      localStringBuilder2.append(str);
      j.b("TrineaAndroidCommon", localStringBuilder2.toString());
    }
    if (TextUtils.isEmpty(str))
    {
      str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("getClientId 2 getIMEI android : ");
      localStringBuilder1.append(str);
      j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    }
    return a.a(str.getBytes());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.a.d
 * JD-Core Version:    0.6.1
 */