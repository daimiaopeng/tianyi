package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public final class b
{
  private static String a;
  private static String b;

  public static String a()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return "fail";
  }

  // ERROR //
  public static String a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: ldc 30
    //   11: invokestatic 35	com/tencent/bugly/crashreport/common/info/AppInfo:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   14: ifne +15 -> 29
    //   17: ldc 37
    //   19: iconst_0
    //   20: anewarray 4	java/lang/Object
    //   23: invokestatic 41	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   26: pop
    //   27: aconst_null
    //   28: areturn
    //   29: aload_0
    //   30: ldc 43
    //   32: invokevirtual 49	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   35: checkcast 51	android/telephony/TelephonyManager
    //   38: astore_3
    //   39: aconst_null
    //   40: astore_1
    //   41: aload_3
    //   42: ifnull +45 -> 87
    //   45: aload_3
    //   46: invokevirtual 54	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   49: astore 4
    //   51: aload 4
    //   53: ifnull +18 -> 71
    //   56: aload 4
    //   58: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   61: astore_1
    //   62: goto +25 -> 87
    //   65: aload 4
    //   67: astore_1
    //   68: goto +9 -> 77
    //   71: aload 4
    //   73: astore_1
    //   74: goto +13 -> 87
    //   77: ldc 61
    //   79: iconst_0
    //   80: anewarray 4	java/lang/Object
    //   83: invokestatic 63	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   86: pop
    //   87: aload_1
    //   88: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   56	62	65	java/lang/Throwable
    //   29	51	77	java/lang/Throwable
  }

  public static String a(boolean paramBoolean)
  {
    String str1 = null;
    if (paramBoolean);
    try
    {
      str1 = q();
      if (str1 == null)
        str1 = System.getProperty("os.arch");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str1);
      String str2 = localStringBuilder.toString();
      return str2;
      label46: Throwable localThrowable1;
      if (!x.a(localThrowable1))
        localThrowable1.printStackTrace();
      return "fail";
    }
    catch (Throwable localThrowable2)
    {
      break label46;
    }
  }

  public static String b()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return "fail";
  }

  // ERROR //
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: ldc 30
    //   11: invokestatic 35	com/tencent/bugly/crashreport/common/info/AppInfo:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   14: ifne +15 -> 29
    //   17: ldc 94
    //   19: iconst_0
    //   20: anewarray 4	java/lang/Object
    //   23: invokestatic 41	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   26: pop
    //   27: aconst_null
    //   28: areturn
    //   29: aload_0
    //   30: ldc 43
    //   32: invokevirtual 49	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   35: checkcast 51	android/telephony/TelephonyManager
    //   38: astore_3
    //   39: aconst_null
    //   40: astore_1
    //   41: aload_3
    //   42: ifnull +45 -> 87
    //   45: aload_3
    //   46: invokevirtual 97	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   49: astore 4
    //   51: aload 4
    //   53: ifnull +18 -> 71
    //   56: aload 4
    //   58: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   61: astore_1
    //   62: goto +25 -> 87
    //   65: aload 4
    //   67: astore_1
    //   68: goto +9 -> 77
    //   71: aload 4
    //   73: astore_1
    //   74: goto +13 -> 87
    //   77: ldc 99
    //   79: iconst_0
    //   80: anewarray 4	java/lang/Object
    //   83: invokestatic 63	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   86: pop
    //   87: aload_1
    //   88: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   56	62	65	java/lang/Throwable
    //   29	51	77	java/lang/Throwable
  }

  public static int c()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return -1;
  }

  // ERROR //
  public static String c(Context paramContext)
  {
    // Byte code:
    //   0: ldc 27
    //   2: astore_1
    //   3: aload_0
    //   4: ifnonnull +5 -> 9
    //   7: aload_1
    //   8: areturn
    //   9: aload_0
    //   10: invokevirtual 109	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc 111
    //   15: invokestatic 117	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   18: astore 4
    //   20: aload 4
    //   22: ifnonnull +9 -> 31
    //   25: ldc 119
    //   27: astore_1
    //   28: goto +30 -> 58
    //   31: aload 4
    //   33: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   36: astore_1
    //   37: goto +21 -> 58
    //   40: astore_2
    //   41: aload_2
    //   42: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   45: ifne +13 -> 58
    //   48: ldc 121
    //   50: iconst_0
    //   51: anewarray 4	java/lang/Object
    //   54: invokestatic 63	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   57: pop
    //   58: aload_1
    //   59: areturn
    //   60: astore 5
    //   62: aload 4
    //   64: astore_1
    //   65: aload 5
    //   67: astore_2
    //   68: goto -27 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   9	20	40	java/lang/Throwable
    //   25	37	60	java/lang/Throwable
  }

  // ERROR //
  public static String d()
  {
    // Byte code:
    //   0: getstatic 124	android/os/Build:SERIAL	Ljava/lang/String;
    //   3: astore_1
    //   4: aload_1
    //   5: areturn
    //   6: ldc 126
    //   8: iconst_0
    //   9: anewarray 4	java/lang/Object
    //   12: invokestatic 63	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   15: pop
    //   16: ldc 27
    //   18: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	4	6	java/lang/Throwable
  }

  // ERROR //
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: ldc 27
    //   2: astore_1
    //   3: aload_0
    //   4: ifnonnull +5 -> 9
    //   7: aload_1
    //   8: areturn
    //   9: aload_0
    //   10: ldc 128
    //   12: invokevirtual 49	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   15: checkcast 130	android/net/wifi/WifiManager
    //   18: astore 4
    //   20: aload 4
    //   22: ifnull +129 -> 151
    //   25: aload 4
    //   27: invokevirtual 134	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   30: astore 5
    //   32: aload 5
    //   34: ifnull +117 -> 151
    //   37: aload 5
    //   39: invokevirtual 139	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   42: astore 6
    //   44: aload 6
    //   46: ifnull +16 -> 62
    //   49: aload 6
    //   51: ldc 141
    //   53: invokevirtual 145	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq +77 -> 133
    //   59: goto +3 -> 62
    //   62: aload_0
    //   63: ldc 147
    //   65: invokestatic 152	com/tencent/bugly/proguard/z:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   68: astore 7
    //   70: ldc 154
    //   72: iconst_1
    //   73: anewarray 4	java/lang/Object
    //   76: dup
    //   77: iconst_0
    //   78: aload 7
    //   80: aastore
    //   81: invokestatic 156	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   84: pop
    //   85: aload 7
    //   87: invokestatic 162	java/net/NetworkInterface:getByName	(Ljava/lang/String;)Ljava/net/NetworkInterface;
    //   90: astore 9
    //   92: aload 9
    //   94: ifnonnull +10 -> 104
    //   97: ldc 164
    //   99: invokestatic 162	java/net/NetworkInterface:getByName	(Ljava/lang/String;)Ljava/net/NetworkInterface;
    //   102: astore 9
    //   104: aload 9
    //   106: ifnonnull +10 -> 116
    //   109: ldc 166
    //   111: invokestatic 162	java/net/NetworkInterface:getByName	(Ljava/lang/String;)Ljava/net/NetworkInterface;
    //   114: astore 9
    //   116: aload 9
    //   118: ifnull +15 -> 133
    //   121: aload 9
    //   123: invokevirtual 170	java/net/NetworkInterface:getHardwareAddress	()[B
    //   126: invokestatic 173	com/tencent/bugly/proguard/z:d	([B)Ljava/lang/String;
    //   129: astore_1
    //   130: goto +21 -> 151
    //   133: aload 6
    //   135: astore_1
    //   136: goto +15 -> 151
    //   139: astore_2
    //   140: aload_2
    //   141: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   144: ifne +7 -> 151
    //   147: aload_2
    //   148: invokevirtual 25	java/lang/Throwable:printStackTrace	()V
    //   151: aload_1
    //   152: ifnonnull +6 -> 158
    //   155: ldc 119
    //   157: astore_1
    //   158: ldc 175
    //   160: iconst_1
    //   161: anewarray 4	java/lang/Object
    //   164: dup
    //   165: iconst_0
    //   166: aload_1
    //   167: aastore
    //   168: invokestatic 156	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   171: pop
    //   172: aload_1
    //   173: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   176: areturn
    //   177: astore_2
    //   178: aload 6
    //   180: astore_1
    //   181: goto -41 -> 140
    //
    // Exception table:
    //   from	to	target	type
    //   9	44	139	java/lang/Throwable
    //   49	130	177	java/lang/Throwable
  }

  public static long e()
  {
    long l1;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2 = localStatFs.getBlockSize();
      int i = localStatFs.getBlockCount();
      l1 = l2 * i;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
      l1 = -1L;
    }
    return l1;
  }

  // ERROR //
  public static String e(Context paramContext)
  {
    // Byte code:
    //   0: ldc 27
    //   2: astore_1
    //   3: aload_0
    //   4: ifnonnull +5 -> 9
    //   7: aload_1
    //   8: areturn
    //   9: aload_0
    //   10: ldc 43
    //   12: invokevirtual 49	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   15: checkcast 51	android/telephony/TelephonyManager
    //   18: astore_3
    //   19: aload_3
    //   20: ifnull +36 -> 56
    //   23: aload_3
    //   24: invokevirtual 204	android/telephony/TelephonyManager:getSimSerialNumber	()Ljava/lang/String;
    //   27: astore 4
    //   29: aload 4
    //   31: ifnonnull +9 -> 40
    //   34: ldc 119
    //   36: astore_1
    //   37: goto +19 -> 56
    //   40: aload 4
    //   42: astore_1
    //   43: goto +13 -> 56
    //   46: ldc 206
    //   48: iconst_0
    //   49: anewarray 4	java/lang/Object
    //   52: invokestatic 63	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   55: pop
    //   56: aload_1
    //   57: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   9	29	46	java/lang/Throwable
  }

  public static long f()
  {
    long l1;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2 = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      l1 = l2 * i;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
      l1 = -1L;
    }
    return l1;
  }

  public static String f(Context paramContext)
  {
    Object localObject1 = "unknown";
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return null;
      if (localNetworkInfo.getType() == 1)
      {
        localObject2 = "WIFI";
        break label205;
      }
      if (localNetworkInfo.getType() == 0)
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager != null)
        {
          int i = localTelephonyManager.getNetworkType();
          switch (i)
          {
          default:
            StringBuilder localStringBuilder = new StringBuilder("MOBILE(");
            localStringBuilder.append(i);
            localStringBuilder.append(")");
            String str = localStringBuilder.toString();
            localObject1 = str;
          case 15:
          case 14:
          case 13:
          case 12:
          case 11:
          case 10:
          case 9:
          case 8:
          case 7:
          case 6:
          case 5:
          case 4:
          case 3:
          case 2:
          case 1:
          }
        }
      }
    }
    catch (Exception localException)
    {
      if (!x.a(localException))
        localException.printStackTrace();
    }
    Object localObject2 = localObject1;
    while (true)
    {
      label205: return localObject2;
      localObject2 = "HSPA+";
      continue;
      localObject2 = "eHRPD";
      continue;
      localObject2 = "LTE";
      continue;
      localObject2 = "EVDO_B";
      continue;
      localObject2 = "iDen";
      continue;
      localObject2 = "HSPA";
      continue;
      localObject2 = "HSUPA";
      continue;
      localObject2 = "HSDPA";
      continue;
      localObject2 = "1xRTT";
      continue;
      localObject2 = "EVDO_A";
      continue;
      localObject2 = "EVDO_0";
      continue;
      localObject2 = "CDMA";
      continue;
      localObject2 = "UMTS";
      continue;
      localObject2 = "EDGE";
      continue;
      localObject2 = "GPRS";
    }
  }

  // ERROR //
  public static long g()
  {
    // Byte code:
    //   0: new 276	java/io/FileReader
    //   3: dup
    //   4: ldc_w 278
    //   7: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 281	java/io/BufferedReader
    //   14: dup
    //   15: aload_0
    //   16: sipush 2048
    //   19: invokespecial 284	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore 8
    //   29: aload 8
    //   31: ifnonnull +51 -> 82
    //   34: aload_1
    //   35: invokevirtual 290	java/io/BufferedReader:close	()V
    //   38: goto +18 -> 56
    //   41: astore 15
    //   43: aload 15
    //   45: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   48: ifne +8 -> 56
    //   51: aload 15
    //   53: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   56: aload_0
    //   57: invokevirtual 292	java/io/FileReader:close	()V
    //   60: goto +18 -> 78
    //   63: astore 16
    //   65: aload 16
    //   67: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   70: ifne +8 -> 78
    //   73: aload 16
    //   75: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   78: ldc2_w 200
    //   81: lreturn
    //   82: aload 8
    //   84: ldc_w 294
    //   87: iconst_2
    //   88: invokevirtual 298	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   91: iconst_1
    //   92: aaload
    //   93: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   96: ldc_w 300
    //   99: ldc_w 302
    //   102: invokevirtual 306	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   105: invokevirtual 309	java/lang/String:trim	()Ljava/lang/String;
    //   108: invokestatic 315	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   111: lstore 9
    //   113: lload 9
    //   115: bipush 10
    //   117: lshl
    //   118: lstore 11
    //   120: aload_1
    //   121: invokevirtual 290	java/io/BufferedReader:close	()V
    //   124: goto +18 -> 142
    //   127: astore 13
    //   129: aload 13
    //   131: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   134: ifne +8 -> 142
    //   137: aload 13
    //   139: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   142: aload_0
    //   143: invokevirtual 292	java/io/FileReader:close	()V
    //   146: goto +18 -> 164
    //   149: astore 14
    //   151: aload 14
    //   153: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   156: ifne +8 -> 164
    //   159: aload 14
    //   161: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   164: lload 11
    //   166: lreturn
    //   167: astore 5
    //   169: goto +46 -> 215
    //   172: astore 18
    //   174: aload 18
    //   176: astore_2
    //   177: aconst_null
    //   178: astore_1
    //   179: goto +106 -> 285
    //   182: astore 17
    //   184: aload 17
    //   186: astore 5
    //   188: aconst_null
    //   189: astore_1
    //   190: goto +25 -> 215
    //   193: astore 20
    //   195: aload 20
    //   197: astore_2
    //   198: aconst_null
    //   199: astore_1
    //   200: aconst_null
    //   201: astore_0
    //   202: goto +83 -> 285
    //   205: astore 19
    //   207: aconst_null
    //   208: astore_0
    //   209: aload 19
    //   211: astore 5
    //   213: aconst_null
    //   214: astore_1
    //   215: aload 5
    //   217: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   220: ifne +8 -> 228
    //   223: aload 5
    //   225: invokevirtual 25	java/lang/Throwable:printStackTrace	()V
    //   228: aload_1
    //   229: ifnull +25 -> 254
    //   232: aload_1
    //   233: invokevirtual 290	java/io/BufferedReader:close	()V
    //   236: goto +18 -> 254
    //   239: astore 7
    //   241: aload 7
    //   243: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   246: ifne +8 -> 254
    //   249: aload 7
    //   251: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   254: aload_0
    //   255: ifnull +25 -> 280
    //   258: aload_0
    //   259: invokevirtual 292	java/io/FileReader:close	()V
    //   262: goto +18 -> 280
    //   265: astore 6
    //   267: aload 6
    //   269: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   272: ifne +8 -> 280
    //   275: aload 6
    //   277: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   280: ldc2_w 316
    //   283: lreturn
    //   284: astore_2
    //   285: aload_1
    //   286: ifnull +25 -> 311
    //   289: aload_1
    //   290: invokevirtual 290	java/io/BufferedReader:close	()V
    //   293: goto +18 -> 311
    //   296: astore 4
    //   298: aload 4
    //   300: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   303: ifne +8 -> 311
    //   306: aload 4
    //   308: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   311: aload_0
    //   312: ifnull +22 -> 334
    //   315: aload_0
    //   316: invokevirtual 292	java/io/FileReader:close	()V
    //   319: goto +15 -> 334
    //   322: astore_3
    //   323: aload_3
    //   324: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   327: ifne +7 -> 334
    //   330: aload_3
    //   331: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   334: aload_2
    //   335: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   34	38	41	java/io/IOException
    //   56	60	63	java/io/IOException
    //   120	124	127	java/io/IOException
    //   142	146	149	java/io/IOException
    //   23	29	167	java/lang/Throwable
    //   82	113	167	java/lang/Throwable
    //   11	23	172	finally
    //   11	23	182	java/lang/Throwable
    //   0	11	193	finally
    //   0	11	205	java/lang/Throwable
    //   232	236	239	java/io/IOException
    //   258	262	265	java/io/IOException
    //   23	29	284	finally
    //   82	113	284	finally
    //   215	228	284	finally
    //   289	293	296	java/io/IOException
    //   315	319	322	java/io/IOException
  }

  public static String g(Context paramContext)
  {
    String str1 = z.a(paramContext, "ro.miui.ui.version.name");
    if ((!z.a(str1)) && (!str1.equals("fail")))
    {
      StringBuilder localStringBuilder12 = new StringBuilder("XiaoMi/MIUI/");
      localStringBuilder12.append(str1);
      return localStringBuilder12.toString();
    }
    String str2 = z.a(paramContext, "ro.build.version.emui");
    if ((!z.a(str2)) && (!str2.equals("fail")))
    {
      StringBuilder localStringBuilder11 = new StringBuilder("HuaWei/EMOTION/");
      localStringBuilder11.append(str2);
      return localStringBuilder11.toString();
    }
    String str3 = z.a(paramContext, "ro.lenovo.series");
    if ((!z.a(str3)) && (!str3.equals("fail")))
    {
      String str12 = z.a(paramContext, "ro.build.version.incremental");
      StringBuilder localStringBuilder10 = new StringBuilder("Lenovo/VIBE/");
      localStringBuilder10.append(str12);
      return localStringBuilder10.toString();
    }
    String str4 = z.a(paramContext, "ro.build.nubia.rom.name");
    if ((!z.a(str4)) && (!str4.equals("fail")))
    {
      StringBuilder localStringBuilder9 = new StringBuilder("Zte/NUBIA/");
      localStringBuilder9.append(str4);
      localStringBuilder9.append("_");
      localStringBuilder9.append(z.a(paramContext, "ro.build.nubia.rom.code"));
      return localStringBuilder9.toString();
    }
    String str5 = z.a(paramContext, "ro.meizu.product.model");
    if ((!z.a(str5)) && (!str5.equals("fail")))
    {
      StringBuilder localStringBuilder8 = new StringBuilder("Meizu/FLYME/");
      localStringBuilder8.append(z.a(paramContext, "ro.build.display.id"));
      return localStringBuilder8.toString();
    }
    String str6 = z.a(paramContext, "ro.build.version.opporom");
    if ((!z.a(str6)) && (!str6.equals("fail")))
    {
      StringBuilder localStringBuilder7 = new StringBuilder("Oppo/COLOROS/");
      localStringBuilder7.append(str6);
      return localStringBuilder7.toString();
    }
    String str7 = z.a(paramContext, "ro.vivo.os.build.display.id");
    if ((!z.a(str7)) && (!str7.equals("fail")))
    {
      StringBuilder localStringBuilder6 = new StringBuilder("vivo/FUNTOUCH/");
      localStringBuilder6.append(str7);
      return localStringBuilder6.toString();
    }
    String str8 = z.a(paramContext, "ro.aa.romver");
    if ((!z.a(str8)) && (!str8.equals("fail")))
    {
      StringBuilder localStringBuilder5 = new StringBuilder("htc/");
      localStringBuilder5.append(str8);
      localStringBuilder5.append("/");
      localStringBuilder5.append(z.a(paramContext, "ro.build.description"));
      return localStringBuilder5.toString();
    }
    String str9 = z.a(paramContext, "ro.lewa.version");
    if ((!z.a(str9)) && (!str9.equals("fail")))
    {
      StringBuilder localStringBuilder4 = new StringBuilder("tcl/");
      localStringBuilder4.append(str9);
      localStringBuilder4.append("/");
      localStringBuilder4.append(z.a(paramContext, "ro.build.display.id"));
      return localStringBuilder4.toString();
    }
    String str10 = z.a(paramContext, "ro.gn.gnromvernumber");
    if ((!z.a(str10)) && (!str10.equals("fail")))
    {
      StringBuilder localStringBuilder3 = new StringBuilder("amigo/");
      localStringBuilder3.append(str10);
      localStringBuilder3.append("/");
      localStringBuilder3.append(z.a(paramContext, "ro.build.display.id"));
      return localStringBuilder3.toString();
    }
    String str11 = z.a(paramContext, "ro.build.tyd.kbstyle_version");
    if ((!z.a(str11)) && (!str11.equals("fail")))
    {
      StringBuilder localStringBuilder2 = new StringBuilder("dido/");
      localStringBuilder2.append(str11);
      return localStringBuilder2.toString();
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(z.a(paramContext, "ro.build.fingerprint"));
    localStringBuilder1.append("/");
    localStringBuilder1.append(z.a(paramContext, "ro.build.rom.id"));
    return localStringBuilder1.toString();
  }

  // ERROR //
  public static long h()
  {
    // Byte code:
    //   0: new 276	java/io/FileReader
    //   3: dup
    //   4: ldc_w 278
    //   7: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 281	java/io/BufferedReader
    //   14: dup
    //   15: aload_0
    //   16: sipush 2048
    //   19: invokespecial 284	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: pop
    //   28: aload_1
    //   29: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore 9
    //   34: aload 9
    //   36: ifnonnull +51 -> 87
    //   39: aload_1
    //   40: invokevirtual 290	java/io/BufferedReader:close	()V
    //   43: goto +18 -> 61
    //   46: astore 26
    //   48: aload 26
    //   50: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   53: ifne +8 -> 61
    //   56: aload 26
    //   58: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   61: aload_0
    //   62: invokevirtual 292	java/io/FileReader:close	()V
    //   65: goto +18 -> 83
    //   68: astore 27
    //   70: aload 27
    //   72: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   75: ifne +8 -> 83
    //   78: aload 27
    //   80: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   83: ldc2_w 200
    //   86: lreturn
    //   87: lconst_0
    //   88: aload 9
    //   90: ldc_w 294
    //   93: iconst_2
    //   94: invokevirtual 298	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   97: iconst_1
    //   98: aaload
    //   99: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   102: ldc_w 300
    //   105: ldc_w 302
    //   108: invokevirtual 306	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   111: invokevirtual 309	java/lang/String:trim	()Ljava/lang/String;
    //   114: invokestatic 315	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   117: bipush 10
    //   119: lshl
    //   120: ladd
    //   121: lstore 10
    //   123: aload_1
    //   124: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   127: astore 12
    //   129: aload 12
    //   131: ifnonnull +51 -> 182
    //   134: aload_1
    //   135: invokevirtual 290	java/io/BufferedReader:close	()V
    //   138: goto +18 -> 156
    //   141: astore 24
    //   143: aload 24
    //   145: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   148: ifne +8 -> 156
    //   151: aload 24
    //   153: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   156: aload_0
    //   157: invokevirtual 292	java/io/FileReader:close	()V
    //   160: goto +18 -> 178
    //   163: astore 25
    //   165: aload 25
    //   167: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   170: ifne +8 -> 178
    //   173: aload 25
    //   175: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   178: ldc2_w 200
    //   181: lreturn
    //   182: lload 10
    //   184: aload 12
    //   186: ldc_w 294
    //   189: iconst_2
    //   190: invokevirtual 298	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   193: iconst_1
    //   194: aaload
    //   195: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   198: ldc_w 300
    //   201: ldc_w 302
    //   204: invokevirtual 306	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   207: invokevirtual 309	java/lang/String:trim	()Ljava/lang/String;
    //   210: invokestatic 315	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   213: bipush 10
    //   215: lshl
    //   216: ladd
    //   217: lstore 13
    //   219: aload_1
    //   220: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   223: astore 15
    //   225: aload 15
    //   227: ifnonnull +51 -> 278
    //   230: aload_1
    //   231: invokevirtual 290	java/io/BufferedReader:close	()V
    //   234: goto +18 -> 252
    //   237: astore 22
    //   239: aload 22
    //   241: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   244: ifne +8 -> 252
    //   247: aload 22
    //   249: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   252: aload_0
    //   253: invokevirtual 292	java/io/FileReader:close	()V
    //   256: goto +18 -> 274
    //   259: astore 23
    //   261: aload 23
    //   263: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   266: ifne +8 -> 274
    //   269: aload 23
    //   271: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   274: ldc2_w 200
    //   277: lreturn
    //   278: aload 15
    //   280: ldc_w 294
    //   283: iconst_2
    //   284: invokevirtual 298	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   287: iconst_1
    //   288: aaload
    //   289: invokevirtual 59	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   292: ldc_w 300
    //   295: ldc_w 302
    //   298: invokevirtual 306	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   301: invokevirtual 309	java/lang/String:trim	()Ljava/lang/String;
    //   304: invokestatic 315	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   307: lstore 16
    //   309: lload 13
    //   311: lload 16
    //   313: bipush 10
    //   315: lshl
    //   316: ladd
    //   317: lstore 18
    //   319: aload_1
    //   320: invokevirtual 290	java/io/BufferedReader:close	()V
    //   323: goto +18 -> 341
    //   326: astore 20
    //   328: aload 20
    //   330: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   333: ifne +8 -> 341
    //   336: aload 20
    //   338: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   341: aload_0
    //   342: invokevirtual 292	java/io/FileReader:close	()V
    //   345: goto +18 -> 363
    //   348: astore 21
    //   350: aload 21
    //   352: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   355: ifne +8 -> 363
    //   358: aload 21
    //   360: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   363: lload 18
    //   365: lreturn
    //   366: astore 5
    //   368: goto +46 -> 414
    //   371: astore 29
    //   373: aload 29
    //   375: astore_2
    //   376: aconst_null
    //   377: astore_1
    //   378: goto +106 -> 484
    //   381: astore 28
    //   383: aload 28
    //   385: astore 5
    //   387: aconst_null
    //   388: astore_1
    //   389: goto +25 -> 414
    //   392: astore 31
    //   394: aload 31
    //   396: astore_2
    //   397: aconst_null
    //   398: astore_1
    //   399: aconst_null
    //   400: astore_0
    //   401: goto +83 -> 484
    //   404: astore 30
    //   406: aconst_null
    //   407: astore_0
    //   408: aload 30
    //   410: astore 5
    //   412: aconst_null
    //   413: astore_1
    //   414: aload 5
    //   416: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   419: ifne +8 -> 427
    //   422: aload 5
    //   424: invokevirtual 25	java/lang/Throwable:printStackTrace	()V
    //   427: aload_1
    //   428: ifnull +25 -> 453
    //   431: aload_1
    //   432: invokevirtual 290	java/io/BufferedReader:close	()V
    //   435: goto +18 -> 453
    //   438: astore 7
    //   440: aload 7
    //   442: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   445: ifne +8 -> 453
    //   448: aload 7
    //   450: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   453: aload_0
    //   454: ifnull +25 -> 479
    //   457: aload_0
    //   458: invokevirtual 292	java/io/FileReader:close	()V
    //   461: goto +18 -> 479
    //   464: astore 6
    //   466: aload 6
    //   468: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   471: ifne +8 -> 479
    //   474: aload 6
    //   476: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   479: ldc2_w 316
    //   482: lreturn
    //   483: astore_2
    //   484: aload_1
    //   485: ifnull +25 -> 510
    //   488: aload_1
    //   489: invokevirtual 290	java/io/BufferedReader:close	()V
    //   492: goto +18 -> 510
    //   495: astore 4
    //   497: aload 4
    //   499: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   502: ifne +8 -> 510
    //   505: aload 4
    //   507: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   510: aload_0
    //   511: ifnull +22 -> 533
    //   514: aload_0
    //   515: invokevirtual 292	java/io/FileReader:close	()V
    //   518: goto +15 -> 533
    //   521: astore_3
    //   522: aload_3
    //   523: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   526: ifne +7 -> 533
    //   529: aload_3
    //   530: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   533: aload_2
    //   534: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   39	43	46	java/io/IOException
    //   61	65	68	java/io/IOException
    //   134	138	141	java/io/IOException
    //   156	160	163	java/io/IOException
    //   230	234	237	java/io/IOException
    //   252	256	259	java/io/IOException
    //   319	323	326	java/io/IOException
    //   341	345	348	java/io/IOException
    //   23	34	366	java/lang/Throwable
    //   87	129	366	java/lang/Throwable
    //   182	225	366	java/lang/Throwable
    //   278	309	366	java/lang/Throwable
    //   11	23	371	finally
    //   11	23	381	java/lang/Throwable
    //   0	11	392	finally
    //   0	11	404	java/lang/Throwable
    //   431	435	438	java/io/IOException
    //   457	461	464	java/io/IOException
    //   23	34	483	finally
    //   87	129	483	finally
    //   182	225	483	finally
    //   278	309	483	finally
    //   414	427	483	finally
    //   488	492	495	java/io/IOException
    //   514	518	521	java/io/IOException
  }

  public static String h(Context paramContext)
  {
    return z.a(paramContext, "ro.board.platform");
  }

  public static long i()
  {
    if (!p())
      return 0L;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      int i = localStatFs.getBlockSize();
      int j = localStatFs.getBlockCount();
      return j * i;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return -2L;
  }

  public static boolean i(Context paramContext)
  {
    boolean bool1;
    try
    {
      bool1 = new File("/system/app/Superuser.apk").exists();
    }
    catch (Throwable localThrowable)
    {
      if (!x.b(localThrowable))
        localThrowable.printStackTrace();
      bool1 = false;
    }
    ArrayList localArrayList = z.a(paramContext, new String[] { "/system/bin/sh", "-c", "type su" });
    Boolean localBoolean1 = null;
    if (localArrayList != null)
    {
      int j = localArrayList.size();
      localBoolean1 = null;
      if (j > 0)
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          x.c(str, new Object[0]);
          if (str.contains("not found"))
            localBoolean1 = Boolean.valueOf(false);
        }
        if (localBoolean1 == null)
          localBoolean1 = Boolean.valueOf(true);
      }
    }
    boolean bool2;
    if (localBoolean1 == null)
      bool2 = false;
    else
      bool2 = localBoolean1.booleanValue();
    Boolean localBoolean2 = Boolean.valueOf(bool2);
    int i;
    if ((Build.TAGS != null) && (Build.TAGS.contains("test-keys")))
      i = 1;
    else
      i = 0;
    boolean bool3;
    if ((i == 0) && (!bool1))
    {
      boolean bool4 = localBoolean2.booleanValue();
      bool3 = false;
      if (!bool4);
    }
    else
    {
      bool3 = true;
    }
    return bool3;
  }

  public static long j()
  {
    if (!p())
      return 0L;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      int i = localStatFs.getBlockSize();
      int j = localStatFs.getAvailableBlocks();
      return j * i;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return -2L;
  }

  public static String j(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = z.a(paramContext, "ro.genymotion.version");
    if (str1 != null)
    {
      localStringBuilder.append("ro.genymotion.version");
      localStringBuilder.append("|");
      localStringBuilder.append(str1);
      localStringBuilder.append("\n");
    }
    String str2 = z.a(paramContext, "androVM.vbox_dpi");
    if (str2 != null)
    {
      localStringBuilder.append("androVM.vbox_dpi");
      localStringBuilder.append("|");
      localStringBuilder.append(str2);
      localStringBuilder.append("\n");
    }
    String str3 = z.a(paramContext, "qemu.sf.fake_camera");
    if (str3 != null)
    {
      localStringBuilder.append("qemu.sf.fake_camera");
      localStringBuilder.append("|");
      localStringBuilder.append(str3);
    }
    return localStringBuilder.toString();
  }

  public static String k()
  {
    Object localObject = "fail";
    try
    {
      String str = Locale.getDefault().getCountry();
      localObject = str;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return localObject;
  }

  // ERROR //
  public static String k(Context paramContext)
  {
    // Byte code:
    //   0: new 77	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   7: astore_1
    //   8: getstatic 470	com/tencent/bugly/crashreport/common/info/b:a	Ljava/lang/String;
    //   11: ifnonnull +13 -> 24
    //   14: aload_0
    //   15: ldc_w 472
    //   18: invokestatic 152	com/tencent/bugly/proguard/z:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   21: putstatic 470	com/tencent/bugly/crashreport/common/info/b:a	Ljava/lang/String;
    //   24: getstatic 470	com/tencent/bugly/crashreport/common/info/b:a	Ljava/lang/String;
    //   27: ifnull +35 -> 62
    //   30: aload_1
    //   31: ldc_w 472
    //   34: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: pop
    //   38: aload_1
    //   39: ldc_w 452
    //   42: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: aload_1
    //   47: getstatic 470	com/tencent/bugly/crashreport/common/info/b:a	Ljava/lang/String;
    //   50: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_1
    //   55: ldc_w 454
    //   58: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: getstatic 474	com/tencent/bugly/crashreport/common/info/b:b	Ljava/lang/String;
    //   65: ifnonnull +13 -> 78
    //   68: aload_0
    //   69: ldc_w 476
    //   72: invokestatic 152	com/tencent/bugly/proguard/z:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   75: putstatic 474	com/tencent/bugly/crashreport/common/info/b:b	Ljava/lang/String;
    //   78: getstatic 474	com/tencent/bugly/crashreport/common/info/b:b	Ljava/lang/String;
    //   81: ifnull +35 -> 116
    //   84: aload_1
    //   85: ldc_w 476
    //   88: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload_1
    //   93: ldc_w 452
    //   96: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload_1
    //   101: getstatic 474	com/tencent/bugly/crashreport/common/info/b:b	Ljava/lang/String;
    //   104: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload_1
    //   109: ldc_w 454
    //   112: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: new 281	java/io/BufferedReader
    //   119: dup
    //   120: new 276	java/io/FileReader
    //   123: dup
    //   124: ldc_w 478
    //   127: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   130: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   133: astore_2
    //   134: aload_2
    //   135: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   138: astore 10
    //   140: aload 10
    //   142: ifnull +14 -> 156
    //   145: aload 10
    //   147: ldc_w 483
    //   150: invokevirtual 486	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   153: ifeq -19 -> 134
    //   156: aload 10
    //   158: ifnull +38 -> 196
    //   161: aload 10
    //   163: bipush 10
    //   165: invokevirtual 490	java/lang/String:substring	(I)Ljava/lang/String;
    //   168: invokevirtual 309	java/lang/String:trim	()Ljava/lang/String;
    //   171: astore 14
    //   173: aload_1
    //   174: ldc_w 492
    //   177: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload_1
    //   182: ldc_w 452
    //   185: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: aload_1
    //   190: aload 14
    //   192: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload_1
    //   197: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: astore 11
    //   202: aload_2
    //   203: invokevirtual 290	java/io/BufferedReader:close	()V
    //   206: goto +11 -> 217
    //   209: astore 12
    //   211: aload 12
    //   213: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   216: pop
    //   217: aload 11
    //   219: areturn
    //   220: astore 6
    //   222: goto +21 -> 243
    //   225: astore 19
    //   227: aload 19
    //   229: astore_3
    //   230: aconst_null
    //   231: astore_2
    //   232: goto +42 -> 274
    //   235: astore 18
    //   237: aconst_null
    //   238: astore_2
    //   239: aload 18
    //   241: astore 6
    //   243: aload 6
    //   245: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   248: pop
    //   249: aload_2
    //   250: ifnull +18 -> 268
    //   253: aload_2
    //   254: invokevirtual 290	java/io/BufferedReader:close	()V
    //   257: goto +11 -> 268
    //   260: astore 8
    //   262: aload 8
    //   264: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   267: pop
    //   268: aload_1
    //   269: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: areturn
    //   273: astore_3
    //   274: aload_2
    //   275: ifnull +18 -> 293
    //   278: aload_2
    //   279: invokevirtual 290	java/io/BufferedReader:close	()V
    //   282: goto +11 -> 293
    //   285: astore 4
    //   287: aload 4
    //   289: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   292: pop
    //   293: aload_3
    //   294: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   202	206	209	java/io/IOException
    //   134	202	220	java/lang/Throwable
    //   116	134	225	finally
    //   116	134	235	java/lang/Throwable
    //   253	257	260	java/io/IOException
    //   134	202	273	finally
    //   243	249	273	finally
    //   278	282	285	java/io/IOException
  }

  public static String l()
  {
    Object localObject = "fail";
    try
    {
      String str = Build.BRAND;
      localObject = str;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return localObject;
  }

  public static String l(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = z.a(paramContext, "gsm.sim.state");
    if (str1 != null)
    {
      localStringBuilder.append("gsm.sim.state");
      localStringBuilder.append("|");
      localStringBuilder.append(str1);
    }
    localStringBuilder.append("\n");
    String str2 = z.a(paramContext, "gsm.sim.state2");
    if (str2 != null)
    {
      localStringBuilder.append("gsm.sim.state2");
      localStringBuilder.append("|");
      localStringBuilder.append(str2);
    }
    return localStringBuilder.toString();
  }

  // ERROR //
  public static String m()
  {
    // Byte code:
    //   0: new 77	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   7: astore_0
    //   8: new 187	java/io/File
    //   11: dup
    //   12: ldc_w 503
    //   15: invokespecial 395	java/io/File:<init>	(Ljava/lang/String;)V
    //   18: invokevirtual 398	java/io/File:exists	()Z
    //   21: ifeq +286 -> 307
    //   24: new 281	java/io/BufferedReader
    //   27: dup
    //   28: new 276	java/io/FileReader
    //   31: dup
    //   32: ldc_w 503
    //   35: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   38: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_1
    //   42: aload_1
    //   43: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: astore 9
    //   48: aload 9
    //   50: ifnull +10 -> 60
    //   53: aload_0
    //   54: aload 9
    //   56: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload_1
    //   61: invokevirtual 290	java/io/BufferedReader:close	()V
    //   64: goto +3 -> 67
    //   67: aload_0
    //   68: ldc_w 505
    //   71: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: new 187	java/io/File
    //   78: dup
    //   79: ldc_w 507
    //   82: invokespecial 395	java/io/File:<init>	(Ljava/lang/String;)V
    //   85: invokevirtual 398	java/io/File:exists	()Z
    //   88: ifeq +66 -> 154
    //   91: new 281	java/io/BufferedReader
    //   94: dup
    //   95: new 276	java/io/FileReader
    //   98: dup
    //   99: ldc_w 507
    //   102: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   105: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   108: astore 11
    //   110: aload 11
    //   112: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   115: astore 12
    //   117: aload 12
    //   119: ifnull +10 -> 129
    //   122: aload_0
    //   123: aload 12
    //   125: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload 11
    //   131: invokevirtual 290	java/io/BufferedReader:close	()V
    //   134: aload 11
    //   136: astore_1
    //   137: goto +17 -> 154
    //   140: astore 5
    //   142: aload 11
    //   144: astore_1
    //   145: goto +115 -> 260
    //   148: aload 11
    //   150: astore_1
    //   151: goto +136 -> 287
    //   154: aload_0
    //   155: ldc_w 505
    //   158: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: new 187	java/io/File
    //   165: dup
    //   166: ldc_w 509
    //   169: invokespecial 395	java/io/File:<init>	(Ljava/lang/String;)V
    //   172: invokevirtual 398	java/io/File:exists	()Z
    //   175: ifeq +44 -> 219
    //   178: new 281	java/io/BufferedReader
    //   181: dup
    //   182: new 276	java/io/FileReader
    //   185: dup
    //   186: ldc_w 509
    //   189: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   192: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   195: astore 11
    //   197: aload 11
    //   199: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   202: astore 14
    //   204: aload 14
    //   206: ifnull +10 -> 216
    //   209: aload_0
    //   210: aload 14
    //   212: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload 11
    //   218: astore_1
    //   219: aload_0
    //   220: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: astore 15
    //   225: aload_1
    //   226: ifnull +18 -> 244
    //   229: aload_1
    //   230: invokevirtual 290	java/io/BufferedReader:close	()V
    //   233: goto +11 -> 244
    //   236: astore 16
    //   238: aload 16
    //   240: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   243: pop
    //   244: aload 15
    //   246: areturn
    //   247: astore 5
    //   249: goto +11 -> 260
    //   252: astore 4
    //   254: aconst_null
    //   255: astore_1
    //   256: aload 4
    //   258: astore 5
    //   260: aload_1
    //   261: ifnull +18 -> 279
    //   264: aload_1
    //   265: invokevirtual 290	java/io/BufferedReader:close	()V
    //   268: goto +11 -> 279
    //   271: astore 6
    //   273: aload 6
    //   275: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   278: pop
    //   279: aload 5
    //   281: athrow
    //   282: aconst_null
    //   283: astore_1
    //   284: goto +4 -> 288
    //   287: pop
    //   288: aload_1
    //   289: ifnull +16 -> 305
    //   292: aload_1
    //   293: invokevirtual 290	java/io/BufferedReader:close	()V
    //   296: goto +9 -> 305
    //   299: astore_2
    //   300: aload_2
    //   301: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   304: pop
    //   305: aconst_null
    //   306: areturn
    //   307: aconst_null
    //   308: astore_1
    //   309: goto -242 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   110	134	140	finally
    //   197	216	140	finally
    //   110	134	148	java/lang/Throwable
    //   197	216	148	java/lang/Throwable
    //   229	233	236	java/io/IOException
    //   42	110	247	finally
    //   154	197	247	finally
    //   219	225	247	finally
    //   0	42	252	finally
    //   264	268	271	java/io/IOException
    //   0	42	282	java/lang/Throwable
    //   42	110	287	java/lang/Throwable
    //   154	197	287	java/lang/Throwable
    //   219	225	287	java/lang/Throwable
    //   292	296	299	java/io/IOException
  }

  // ERROR //
  public static String n()
  {
    // Byte code:
    //   0: new 77	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   7: astore_0
    //   8: aconst_null
    //   9: astore_1
    //   10: new 187	java/io/File
    //   13: dup
    //   14: ldc_w 512
    //   17: invokespecial 395	java/io/File:<init>	(Ljava/lang/String;)V
    //   20: invokevirtual 398	java/io/File:exists	()Z
    //   23: istore 7
    //   25: aconst_null
    //   26: astore_1
    //   27: iload 7
    //   29: ifeq +82 -> 111
    //   32: new 281	java/io/BufferedReader
    //   35: dup
    //   36: new 276	java/io/FileReader
    //   39: dup
    //   40: ldc_w 512
    //   43: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   46: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   49: astore 8
    //   51: aload 8
    //   53: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   56: astore 9
    //   58: aload 9
    //   60: ifnull +26 -> 86
    //   63: aload_0
    //   64: ldc_w 514
    //   67: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_0
    //   72: ldc_w 452
    //   75: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload_0
    //   80: aload 9
    //   82: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload 8
    //   88: invokevirtual 290	java/io/BufferedReader:close	()V
    //   91: aload 8
    //   93: astore_1
    //   94: goto +17 -> 111
    //   97: astore 4
    //   99: aload 8
    //   101: astore_1
    //   102: goto +194 -> 296
    //   105: aload 8
    //   107: astore_1
    //   108: goto +210 -> 318
    //   111: aload_0
    //   112: ldc_w 454
    //   115: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: new 187	java/io/File
    //   122: dup
    //   123: ldc_w 516
    //   126: invokespecial 395	java/io/File:<init>	(Ljava/lang/String;)V
    //   129: invokevirtual 398	java/io/File:exists	()Z
    //   132: ifeq +65 -> 197
    //   135: new 281	java/io/BufferedReader
    //   138: dup
    //   139: new 276	java/io/FileReader
    //   142: dup
    //   143: ldc_w 516
    //   146: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   149: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   152: astore 8
    //   154: aload 8
    //   156: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   159: astore 11
    //   161: aload 11
    //   163: ifnull +26 -> 189
    //   166: aload_0
    //   167: ldc_w 518
    //   170: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_0
    //   175: ldc_w 452
    //   178: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: aload_0
    //   183: aload 11
    //   185: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: aload 8
    //   191: invokevirtual 290	java/io/BufferedReader:close	()V
    //   194: aload 8
    //   196: astore_1
    //   197: aload_0
    //   198: ldc_w 454
    //   201: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: new 187	java/io/File
    //   208: dup
    //   209: ldc_w 520
    //   212: invokespecial 395	java/io/File:<init>	(Ljava/lang/String;)V
    //   215: invokevirtual 398	java/io/File:exists	()Z
    //   218: ifeq +65 -> 283
    //   221: new 281	java/io/BufferedReader
    //   224: dup
    //   225: new 276	java/io/FileReader
    //   228: dup
    //   229: ldc_w 520
    //   232: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   235: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   238: astore 8
    //   240: aload 8
    //   242: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   245: astore 13
    //   247: aload 13
    //   249: ifnull +26 -> 275
    //   252: aload_0
    //   253: ldc_w 522
    //   256: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload_0
    //   261: ldc_w 452
    //   264: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload_0
    //   269: aload 13
    //   271: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: pop
    //   275: aload 8
    //   277: invokevirtual 290	java/io/BufferedReader:close	()V
    //   280: aload 8
    //   282: astore_1
    //   283: aload_1
    //   284: ifnull +51 -> 335
    //   287: aload_1
    //   288: invokevirtual 290	java/io/BufferedReader:close	()V
    //   291: goto +44 -> 335
    //   294: astore 4
    //   296: aload_1
    //   297: ifnull +18 -> 315
    //   300: aload_1
    //   301: invokevirtual 290	java/io/BufferedReader:close	()V
    //   304: goto +11 -> 315
    //   307: astore 5
    //   309: aload 5
    //   311: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   314: pop
    //   315: aload 4
    //   317: athrow
    //   318: aload_1
    //   319: ifnull +16 -> 335
    //   322: aload_1
    //   323: invokevirtual 290	java/io/BufferedReader:close	()V
    //   326: goto +9 -> 335
    //   329: astore_2
    //   330: aload_2
    //   331: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   334: pop
    //   335: aload_0
    //   336: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   339: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   51	91	97	finally
    //   154	194	97	finally
    //   240	280	97	finally
    //   51	91	105	java/lang/Throwable
    //   154	194	105	java/lang/Throwable
    //   240	280	105	java/lang/Throwable
    //   10	51	294	finally
    //   111	154	294	finally
    //   197	240	294	finally
    //   300	304	307	java/io/IOException
    //   10	51	318	java/lang/Throwable
    //   111	154	318	java/lang/Throwable
    //   197	240	318	java/lang/Throwable
    //   287	291	329	java/io/IOException
    //   322	326	329	java/io/IOException
  }

  // ERROR //
  public static long o()
  {
    // Byte code:
    //   0: new 281	java/io/BufferedReader
    //   3: dup
    //   4: new 276	java/io/FileReader
    //   7: dup
    //   8: ldc_w 525
    //   11: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: invokespecial 481	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   22: astore 9
    //   24: fconst_0
    //   25: fstore 6
    //   27: aload 9
    //   29: ifnull +39 -> 68
    //   32: aload 9
    //   34: ldc_w 527
    //   37: invokevirtual 530	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   40: iconst_0
    //   41: aaload
    //   42: astore 10
    //   44: invokestatic 533	java/lang/System:currentTimeMillis	()J
    //   47: ldc2_w 534
    //   50: ldiv
    //   51: l2f
    //   52: fstore 11
    //   54: aload 10
    //   56: invokestatic 541	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   59: fstore 12
    //   61: fload 11
    //   63: fload 12
    //   65: fsub
    //   66: fstore 6
    //   68: aload_0
    //   69: invokevirtual 290	java/io/BufferedReader:close	()V
    //   72: goto +50 -> 122
    //   75: astore 7
    //   77: aload 7
    //   79: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   82: pop
    //   83: goto +39 -> 122
    //   86: astore 4
    //   88: goto +17 -> 105
    //   91: astore_1
    //   92: aconst_null
    //   93: astore_0
    //   94: goto +33 -> 127
    //   97: astore 13
    //   99: aconst_null
    //   100: astore_0
    //   101: aload 13
    //   103: astore 4
    //   105: aload 4
    //   107: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   110: pop
    //   111: fconst_0
    //   112: fstore 6
    //   114: aload_0
    //   115: ifnull +7 -> 122
    //   118: aload_0
    //   119: invokevirtual 290	java/io/BufferedReader:close	()V
    //   122: fload 6
    //   124: f2l
    //   125: lreturn
    //   126: astore_1
    //   127: aload_0
    //   128: ifnull +16 -> 144
    //   131: aload_0
    //   132: invokevirtual 290	java/io/BufferedReader:close	()V
    //   135: goto +9 -> 144
    //   138: astore_2
    //   139: aload_2
    //   140: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   143: pop
    //   144: aload_1
    //   145: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   68	72	75	java/io/IOException
    //   118	122	75	java/io/IOException
    //   18	61	86	java/lang/Throwable
    //   0	18	91	finally
    //   0	18	97	java/lang/Throwable
    //   18	61	126	finally
    //   105	111	126	finally
    //   131	135	138	java/io/IOException
  }

  private static boolean p()
  {
    try
    {
      boolean bool = Environment.getExternalStorageState().equals("mounted");
      if (bool)
        return true;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return false;
  }

  // ERROR //
  private static String q()
  {
    // Byte code:
    //   0: new 276	java/io/FileReader
    //   3: dup
    //   4: ldc_w 548
    //   7: invokespecial 279	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 281	java/io/BufferedReader
    //   14: dup
    //   15: aload_0
    //   16: sipush 2048
    //   19: invokespecial 284	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore 8
    //   29: aload 8
    //   31: ifnull +288 -> 319
    //   34: aload 8
    //   36: ldc_w 550
    //   39: iconst_2
    //   40: invokevirtual 298	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   43: astore 12
    //   45: aload 12
    //   47: arraylength
    //   48: iconst_2
    //   49: if_icmpne -26 -> 23
    //   52: aload 12
    //   54: iconst_0
    //   55: aaload
    //   56: ldc_w 552
    //   59: invokevirtual 145	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   62: ifeq +12 -> 74
    //   65: aload 12
    //   67: iconst_1
    //   68: aaload
    //   69: astore 9
    //   71: goto +25 -> 96
    //   74: aload 12
    //   76: iconst_0
    //   77: aaload
    //   78: ldc_w 554
    //   81: invokevirtual 145	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   84: ifeq -61 -> 23
    //   87: aload 12
    //   89: iconst_1
    //   90: aaload
    //   91: astore 9
    //   93: goto +3 -> 96
    //   96: aload 9
    //   98: ifnull +15 -> 113
    //   101: aload 9
    //   103: ldc_w 505
    //   106: invokevirtual 530	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   109: iconst_0
    //   110: aaload
    //   111: astore 9
    //   113: aload_1
    //   114: invokevirtual 290	java/io/BufferedReader:close	()V
    //   117: goto +18 -> 135
    //   120: astore 10
    //   122: aload 10
    //   124: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   127: ifne +8 -> 135
    //   130: aload 10
    //   132: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   135: aload_0
    //   136: invokevirtual 292	java/io/FileReader:close	()V
    //   139: goto +18 -> 157
    //   142: astore 11
    //   144: aload 11
    //   146: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   149: ifne +8 -> 157
    //   152: aload 11
    //   154: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   157: aload 9
    //   159: areturn
    //   160: astore 5
    //   162: goto +38 -> 200
    //   165: astore 13
    //   167: aload 13
    //   169: astore_2
    //   170: aconst_null
    //   171: astore_1
    //   172: goto +96 -> 268
    //   175: astore 5
    //   177: aconst_null
    //   178: astore_1
    //   179: goto +21 -> 200
    //   182: astore 14
    //   184: aload 14
    //   186: astore_2
    //   187: aconst_null
    //   188: astore_1
    //   189: aconst_null
    //   190: astore_0
    //   191: goto +77 -> 268
    //   194: astore 5
    //   196: aconst_null
    //   197: astore_1
    //   198: aconst_null
    //   199: astore_0
    //   200: aload 5
    //   202: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   205: ifne +8 -> 213
    //   208: aload 5
    //   210: invokevirtual 25	java/lang/Throwable:printStackTrace	()V
    //   213: aload_1
    //   214: ifnull +25 -> 239
    //   217: aload_1
    //   218: invokevirtual 290	java/io/BufferedReader:close	()V
    //   221: goto +18 -> 239
    //   224: astore 7
    //   226: aload 7
    //   228: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   231: ifne +8 -> 239
    //   234: aload 7
    //   236: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   239: aload_0
    //   240: ifnull +25 -> 265
    //   243: aload_0
    //   244: invokevirtual 292	java/io/FileReader:close	()V
    //   247: goto +18 -> 265
    //   250: astore 6
    //   252: aload 6
    //   254: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   257: ifne +8 -> 265
    //   260: aload 6
    //   262: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   265: aconst_null
    //   266: areturn
    //   267: astore_2
    //   268: aload_1
    //   269: ifnull +25 -> 294
    //   272: aload_1
    //   273: invokevirtual 290	java/io/BufferedReader:close	()V
    //   276: goto +18 -> 294
    //   279: astore 4
    //   281: aload 4
    //   283: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   286: ifne +8 -> 294
    //   289: aload 4
    //   291: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   294: aload_0
    //   295: ifnull +22 -> 317
    //   298: aload_0
    //   299: invokevirtual 292	java/io/FileReader:close	()V
    //   302: goto +15 -> 317
    //   305: astore_3
    //   306: aload_3
    //   307: invokestatic 22	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   310: ifne +7 -> 317
    //   313: aload_3
    //   314: invokevirtual 291	java/io/IOException:printStackTrace	()V
    //   317: aload_2
    //   318: athrow
    //   319: aconst_null
    //   320: astore 9
    //   322: goto -226 -> 96
    //
    // Exception table:
    //   from	to	target	type
    //   113	117	120	java/io/IOException
    //   135	139	142	java/io/IOException
    //   23	113	160	java/lang/Throwable
    //   11	23	165	finally
    //   11	23	175	java/lang/Throwable
    //   0	11	182	finally
    //   0	11	194	java/lang/Throwable
    //   217	221	224	java/io/IOException
    //   243	247	250	java/io/IOException
    //   23	113	267	finally
    //   200	213	267	finally
    //   272	276	279	java/io/IOException
    //   298	302	305	java/io/IOException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.common.info.b
 * JD-Core Version:    0.6.1
 */