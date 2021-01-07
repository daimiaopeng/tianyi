package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppInfo
{
  private static ActivityManager a;

  static
  {
    "@buglyAllChannel@".split(",");
    "@buglyAllChannelPriority@".split(",");
  }

  // ERROR //
  public static String a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 28	java/lang/StringBuilder
    //   5: dup
    //   6: ldc 30
    //   8: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   11: astore_2
    //   12: aload_2
    //   13: iload_0
    //   14: invokevirtual 37	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload_2
    //   19: ldc 39
    //   21: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: new 44	java/io/FileReader
    //   28: dup
    //   29: aload_2
    //   30: invokevirtual 48	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokespecial 49	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   36: astore 10
    //   38: sipush 512
    //   41: newarray char
    //   43: astore 12
    //   45: aload 10
    //   47: aload 12
    //   49: invokevirtual 53	java/io/FileReader:read	([C)I
    //   52: pop
    //   53: iconst_0
    //   54: istore 14
    //   56: iload 14
    //   58: aload 12
    //   60: arraylength
    //   61: if_icmpge +17 -> 78
    //   64: aload 12
    //   66: iload 14
    //   68: caload
    //   69: ifeq +9 -> 78
    //   72: iinc 14 1
    //   75: goto -19 -> 56
    //   78: new 14	java/lang/String
    //   81: dup
    //   82: aload 12
    //   84: invokespecial 56	java/lang/String:<init>	([C)V
    //   87: iconst_0
    //   88: iload 14
    //   90: invokevirtual 60	java/lang/String:substring	(II)Ljava/lang/String;
    //   93: astore 15
    //   95: aload 10
    //   97: invokevirtual 63	java/io/FileReader:close	()V
    //   100: goto +4 -> 104
    //   103: pop
    //   104: aload 15
    //   106: areturn
    //   107: astore 6
    //   109: aload 10
    //   111: astore_1
    //   112: goto +52 -> 164
    //   115: astore 11
    //   117: aload 11
    //   119: astore_3
    //   120: aload 10
    //   122: astore_1
    //   123: goto +9 -> 132
    //   126: astore 6
    //   128: goto +36 -> 164
    //   131: astore_3
    //   132: aload_3
    //   133: invokestatic 68	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   136: ifne +7 -> 143
    //   139: aload_3
    //   140: invokevirtual 71	java/lang/Throwable:printStackTrace	()V
    //   143: iload_0
    //   144: invokestatic 74	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   147: astore 4
    //   149: aload_1
    //   150: ifnull +10 -> 160
    //   153: aload_1
    //   154: invokevirtual 63	java/io/FileReader:close	()V
    //   157: goto +4 -> 161
    //   160: pop
    //   161: aload 4
    //   163: areturn
    //   164: aload_1
    //   165: ifnull +10 -> 175
    //   168: aload_1
    //   169: invokevirtual 63	java/io/FileReader:close	()V
    //   172: goto +4 -> 176
    //   175: pop
    //   176: aload 6
    //   178: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   95	103	103	java/lang/Throwable
    //   38	95	107	finally
    //   38	95	115	java/lang/Throwable
    //   2	38	126	finally
    //   132	149	126	finally
    //   2	38	131	java/lang/Throwable
    //   153	160	160	java/lang/Throwable
    //   168	175	175	java/lang/Throwable
  }

  public static String a(Context paramContext)
  {
    if (paramContext == null)
      return null;
    try
    {
      String str = paramContext.getPackageName();
      return str;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return "fail";
  }

  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
      try
      {
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        if (localCertificateFactory == null)
          return null;
        X509Certificate localX509Certificate = (X509Certificate)localCertificateFactory.generateCertificate(new ByteArrayInputStream(paramArrayOfByte));
        if (localX509Certificate == null)
          return null;
        localStringBuilder.append("Issuer|");
        Principal localPrincipal = localX509Certificate.getIssuerDN();
        if (localPrincipal != null)
          localStringBuilder.append(localPrincipal.toString());
        else
          localStringBuilder.append("unknown");
        localStringBuilder.append("\n");
        localStringBuilder.append("SerialNumber|");
        BigInteger localBigInteger = localX509Certificate.getSerialNumber();
        if (localPrincipal != null)
          localStringBuilder.append(localBigInteger.toString(16));
        else
          localStringBuilder.append("unknown");
        localStringBuilder.append("\n");
        localStringBuilder.append("NotBefore|");
        Date localDate1 = localX509Certificate.getNotBefore();
        if (localPrincipal != null)
          localStringBuilder.append(localDate1.toString());
        else
          localStringBuilder.append("unknown");
        localStringBuilder.append("\n");
        localStringBuilder.append("NotAfter|");
        Date localDate2 = localX509Certificate.getNotAfter();
        if (localPrincipal != null)
          localStringBuilder.append(localDate2.toString());
        else
          localStringBuilder.append("unknown");
        localStringBuilder.append("\n");
        localStringBuilder.append("SHA1|");
        String str1 = z.a(MessageDigest.getInstance("SHA1").digest(localX509Certificate.getEncoded()));
        if ((str1 != null) && (str1.length() > 0))
          localStringBuilder.append(str1.toString());
        else
          localStringBuilder.append("unknown");
        localStringBuilder.append("\n");
        localStringBuilder.append("MD5|");
        String str2 = z.a(MessageDigest.getInstance("MD5").digest(localX509Certificate.getEncoded()));
        if ((str2 != null) && (str2.length() > 0))
          localStringBuilder.append(str2.toString());
        else
          localStringBuilder.append("unknown");
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
      }
      catch (CertificateException localCertificateException)
      {
        if (!x.a(localCertificateException))
          localCertificateException.printStackTrace();
      }
    if (localStringBuilder.length() == 0)
      return "unknown";
    return localStringBuilder.toString();
  }

  public static List<String> a(Map<String, String> paramMap)
  {
    if (paramMap == null)
      return null;
    try
    {
      String str = (String)paramMap.get("BUGLY_DISABLE");
      if ((str != null) && (str.length() != 0))
      {
        String[] arrayOfString = str.split(",");
        for (int i = 0; i < arrayOfString.length; i++)
          arrayOfString[i] = arrayOfString[i].trim();
        List localList = Arrays.asList(arrayOfString);
        return localList;
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null) && (paramString.trim().length() > 0))
    {
      try
      {
        String[] arrayOfString = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
        if (arrayOfString != null)
        {
          int i = arrayOfString.length;
          for (int j = 0; j < i; j++)
          {
            boolean bool = paramString.equals(arrayOfString[j]);
            if (bool)
              return true;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
      }
      return false;
    }
    return false;
  }

  public static PackageInfo b(Context paramContext)
  {
    try
    {
      String str = a(paramContext);
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(str, 0);
      return localPackageInfo;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  public static String c(Context paramContext)
  {
    if (paramContext == null)
      return null;
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
      if ((localPackageManager != null) && (localApplicationInfo != null))
      {
        CharSequence localCharSequence = localPackageManager.getApplicationLabel(localApplicationInfo);
        if (localCharSequence != null)
        {
          String str = localCharSequence.toString();
          return str;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  public static Map<String, String> d(Context paramContext)
  {
    if (paramContext == null)
      return null;
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      Bundle localBundle = localApplicationInfo.metaData;
      Object localObject1 = null;
      if (localBundle != null)
      {
        HashMap localHashMap = new HashMap();
        Object localObject2 = localApplicationInfo.metaData.get("BUGLY_DISABLE");
        if (localObject2 != null)
          localHashMap.put("BUGLY_DISABLE", localObject2.toString());
        Object localObject3 = localApplicationInfo.metaData.get("BUGLY_APPID");
        if (localObject3 != null)
          localHashMap.put("BUGLY_APPID", localObject3.toString());
        Object localObject4 = localApplicationInfo.metaData.get("BUGLY_APP_CHANNEL");
        if (localObject4 != null)
          localHashMap.put("BUGLY_APP_CHANNEL", localObject4.toString());
        Object localObject5 = localApplicationInfo.metaData.get("BUGLY_APP_VERSION");
        if (localObject5 != null)
          localHashMap.put("BUGLY_APP_VERSION", localObject5.toString());
        Object localObject6 = localApplicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
        if (localObject6 != null)
          localHashMap.put("BUGLY_ENABLE_DEBUG", localObject6.toString());
        Object localObject7 = localApplicationInfo.metaData.get("com.tencent.rdm.uuid");
        if (localObject7 != null)
          localHashMap.put("com.tencent.rdm.uuid", localObject7.toString());
        localObject1 = localHashMap;
      }
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public static String e(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 217	com/tencent/bugly/crashreport/common/info/AppInfo:a	(Landroid/content/Context;)Ljava/lang/String;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: aload_0
    //   12: invokevirtual 197	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   15: aload_1
    //   16: bipush 64
    //   18: invokevirtual 203	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   21: astore_2
    //   22: aload_2
    //   23: ifnonnull +5 -> 28
    //   26: aconst_null
    //   27: areturn
    //   28: aload_2
    //   29: getfield 270	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   32: astore_3
    //   33: aload_3
    //   34: ifnull +24 -> 58
    //   37: aload_3
    //   38: arraylength
    //   39: ifne +6 -> 45
    //   42: goto +16 -> 58
    //   45: aload_2
    //   46: getfield 270	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   49: iconst_0
    //   50: aaload
    //   51: invokevirtual 275	android/content/pm/Signature:toByteArray	()[B
    //   54: invokestatic 276	com/tencent/bugly/crashreport/common/info/AppInfo:a	([B)Ljava/lang/String;
    //   57: areturn
    //   58: aconst_null
    //   59: areturn
    //   60: aconst_null
    //   61: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   11	22	60	android/content/pm/PackageManager$NameNotFoundException
  }

  public static boolean f(Context paramContext)
  {
    if (paramContext == null)
      return false;
    if (a == null)
      a = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      a.getMemoryInfo(localMemoryInfo);
      if (localMemoryInfo.lowMemory)
      {
        x.c("Memory is low.", new Object[0]);
        return true;
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.common.info.AppInfo
 * JD-Core Version:    0.6.1
 */