package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.Map;

public class Bugly
{
  public static final String SDK_IS_DEV = "false";
  private static boolean a = false;
  public static Context applicationContext;
  private static String[] b = { "BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule" };
  private static String[] c = { "BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule" };
  public static boolean enable = true;
  public static Boolean isDev;

  public static String getAppChannel()
  {
    try
    {
      a locala = a.b();
      if (locala == null)
        return null;
      if (TextUtils.isEmpty(locala.l))
      {
        p localp = p.a();
        if (localp == null)
        {
          String str3 = locala.l;
          return str3;
        }
        Map localMap = localp.a(556, null, true);
        if (localMap != null)
        {
          byte[] arrayOfByte = (byte[])localMap.get("app_channel");
          if (arrayOfByte != null)
          {
            String str2 = new String(arrayOfByte);
            return str2;
          }
        }
      }
      String str1 = locala.l;
      return str1;
    }
    finally
    {
    }
  }

  public static void init(Context paramContext, String paramString, boolean paramBoolean)
  {
    init(paramContext, paramString, paramBoolean, null);
  }

  public static void init(Context paramContext, String paramString, boolean paramBoolean, BuglyStrategy paramBuglyStrategy)
  {
    while (true)
    {
      int j;
      try
      {
        boolean bool = a;
        if (bool)
          return;
        a = true;
        Context localContext = z.a(paramContext);
        applicationContext = localContext;
        if (localContext == null)
        {
          Log.e(x.a, "init arg 'context' should not be null!");
          return;
        }
        if (isDev())
          b = c;
        String[] arrayOfString = b;
        int i = arrayOfString.length;
        j = 0;
        if (j < i)
        {
          String str = arrayOfString[j];
          try
          {
            if (str.equals("BuglyCrashModule"))
              b.a(CrashModule.getInstance());
            else if ((!str.equals("BuglyBetaModule")) && (!str.equals("BuglyRqdModule")))
              str.equals("BuglyFeedbackModule");
          }
          catch (Throwable localThrowable)
          {
            x.b(localThrowable);
          }
        }
        else
        {
          b.a = enable;
          b.a(applicationContext, paramString, paramBoolean, paramBuglyStrategy);
          return;
        }
      }
      finally
      {
      }
      j++;
    }
  }

  public static boolean isDev()
  {
    if (isDev == null)
      isDev = Boolean.valueOf(Boolean.parseBoolean("false".replace("@", "")));
    return isDev.booleanValue();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.Bugly
 * JD-Core Version:    0.6.1
 */