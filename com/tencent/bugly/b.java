package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class b
{
  public static boolean a = true;
  public static List<a> b = new ArrayList();
  public static boolean c;
  private static p d;
  private static boolean e;

  public static void a(Context paramContext)
  {
    try
    {
      a(paramContext, null);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static void a(Context paramContext, BuglyStrategy paramBuglyStrategy)
  {
    try
    {
      if (e)
      {
        x.d("[init] initial Multi-times, ignore this.", new Object[0]);
        return;
      }
      if (paramContext == null)
      {
        Log.w(x.a, "[init] context of init() is null, check it.");
        return;
      }
      com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.a(paramContext);
      if (a(locala))
      {
        a = false;
        return;
      }
      String str = locala.f();
      if (str == null)
      {
        Log.e(x.a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
        return;
      }
      a(paramContext, str, locala.u, paramBuglyStrategy);
      return;
    }
    finally
    {
    }
  }

  public static void a(Context paramContext, String paramString, boolean paramBoolean, BuglyStrategy paramBuglyStrategy)
  {
    while (true)
    {
      String str1;
      String str7;
      String str2;
      String str3;
      int i;
      try
      {
        if (e)
        {
          x.d("[init] initial Multi-times, ignore this.", new Object[0]);
          return;
        }
        if (paramContext == null)
        {
          Log.w(x.a, "[init] context is null, check it.");
          return;
        }
        if (paramString == null)
        {
          Log.e(x.a, "init arg 'crashReportAppID' should not be null!");
          return;
        }
        e = true;
        if (paramBoolean)
        {
          c = true;
          x.b = true;
          x.d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
          x.e("--------------------------------------------------------------------------------------------", new Object[0]);
          x.d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
          x.d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
          x.d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
          x.d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
          x.e("--------------------------------------------------------------------------------------------", new Object[0]);
          x.b("[init] Open debug mode of Bugly.", new Object[0]);
        }
        x.a("[init] Bugly version: v%s", new Object[] { "2.6.6" });
        x.a(" crash report start initializing...", new Object[0]);
        x.b("[init] Bugly start initializing...", new Object[0]);
        x.a("[init] Bugly complete version: v%s", new Object[] { "2.6.6" });
        Context localContext = z.a(paramContext);
        com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.a(localContext);
        locala.t();
        y.a(localContext);
        d = p.a(localContext, b);
        u.a(localContext);
        com.tencent.bugly.crashreport.common.strategy.a locala1 = com.tencent.bugly.crashreport.common.strategy.a.a(localContext, b);
        n localn = n.a(localContext);
        if (a(locala))
        {
          a = false;
          return;
        }
        locala.a(paramString);
        x.a("[param] Set APP ID:%s", new Object[] { paramString });
        if (paramBuglyStrategy != null)
        {
          str1 = paramBuglyStrategy.getAppVersion();
          if (!TextUtils.isEmpty(str1))
          {
            if (str1.length() > 100)
            {
              str8 = str1.substring(0, 100);
              Object[] arrayOfObject7 = new Object[3];
              arrayOfObject7[0] = str1;
              arrayOfObject7[1] = Integer.valueOf(100);
              arrayOfObject7[2] = str8;
              x.d("appVersion %s length is over limit %d substring to %s", arrayOfObject7);
              locala.j = str8;
              Object[] arrayOfObject6 = new Object[1];
              arrayOfObject6[0] = paramBuglyStrategy.getAppVersion();
              x.a("[param] Set App version: %s", arrayOfObject6);
            }
          }
          else
          {
            try
            {
              if (paramBuglyStrategy.isReplaceOldChannel())
              {
                str7 = paramBuglyStrategy.getAppChannel();
                if (!TextUtils.isEmpty(str7))
                {
                  if (str7.length() <= 100)
                    break label949;
                  str6 = str7.substring(0, 100);
                  Object[] arrayOfObject5 = new Object[3];
                  arrayOfObject5[0] = str7;
                  arrayOfObject5[1] = Integer.valueOf(100);
                  arrayOfObject5[2] = str6;
                  x.d("appChannel %s length is over limit %d substring to %s", arrayOfObject5);
                  d.a(556, "app_channel", str6.getBytes(), null, false);
                  locala.l = str6;
                }
              }
              else
              {
                Map localMap = d.a(556, null, true);
                if (localMap != null)
                {
                  byte[] arrayOfByte = (byte[])localMap.get("app_channel");
                  if (arrayOfByte != null)
                  {
                    str6 = new String(arrayOfByte);
                    continue;
                  }
                }
              }
              Object[] arrayOfObject4 = new Object[1];
              arrayOfObject4[0] = locala.l;
              x.a("[param] Set App channel: %s", arrayOfObject4);
            }
            catch (Exception localException)
            {
              if (c)
                localException.printStackTrace();
            }
            str2 = paramBuglyStrategy.getAppPackageName();
            if (!TextUtils.isEmpty(str2))
            {
              if (str2.length() <= 100)
                break label956;
              str5 = str2.substring(0, 100);
              Object[] arrayOfObject3 = new Object[3];
              arrayOfObject3[0] = str2;
              arrayOfObject3[1] = Integer.valueOf(100);
              arrayOfObject3[2] = str5;
              x.d("appPackageName %s length is over limit %d substring to %s", arrayOfObject3);
              locala.c = str5;
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = paramBuglyStrategy.getAppPackageName();
              x.a("[param] Set App package: %s", arrayOfObject2);
            }
            str3 = paramBuglyStrategy.getDeviceID();
            if (str3 != null)
            {
              if (str3.length() <= 100)
                break label963;
              str4 = str3.substring(0, 100);
              Object[] arrayOfObject1 = new Object[3];
              arrayOfObject1[0] = str3;
              arrayOfObject1[1] = Integer.valueOf(100);
              arrayOfObject1[2] = str4;
              x.d("deviceId %s length is over limit %d substring to %s", arrayOfObject1);
              locala.c(str4);
              x.a("s[param] Set device ID: %s", new Object[] { str4 });
            }
            locala.e = paramBuglyStrategy.isUploadProcess();
            y.a = paramBuglyStrategy.isBuglyLogUpload();
          }
        }
        else
        {
          com.tencent.bugly.crashreport.biz.b.a(localContext, paramBuglyStrategy);
          i = 0;
          int j = b.size();
          if (i < j)
          {
            try
            {
              if (!localn.a(((a)b.get(i)).id))
                break label970;
              ((a)b.get(i)).init(localContext, paramBoolean, paramBuglyStrategy);
            }
            catch (Throwable localThrowable)
            {
              if (x.a(localThrowable))
                break label970;
            }
            localThrowable.printStackTrace();
            break label970;
          }
          if (paramBuglyStrategy == null)
            break label976;
          l = paramBuglyStrategy.getAppReportDelay();
          locala1.a(l);
          x.b("[init] Bugly initialization finished.", new Object[0]);
          return;
        }
      }
      finally
      {
      }
      String str8 = str1;
      continue;
      label949: String str6 = str7;
      continue;
      label956: String str5 = str2;
      continue;
      label963: String str4 = str3;
      continue;
      label970: i++;
      continue;
      label976: long l = 0L;
    }
  }

  public static void a(a parama)
  {
    try
    {
      if (!b.contains(parama))
        b.add(parama);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static boolean a(com.tencent.bugly.crashreport.common.info.a parama)
  {
    List localList = parama.o;
    parama.getClass();
    return (localList != null) && (localList.contains("bugly"));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.b
 * JD-Core Version:    0.6.1
 */