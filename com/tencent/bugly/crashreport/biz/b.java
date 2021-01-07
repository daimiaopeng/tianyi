package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.stub.StubApp;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.List;

public class b
{
  public static a a;
  private static boolean b = false;
  private static int c = 10;
  private static long d = 300000L;
  private static long e = 30000L;
  private static long f = 0L;
  private static int g = 0;
  private static long h = 0L;
  private static long i = 0L;
  private static long j = 0L;
  private static Application.ActivityLifecycleCallbacks k;
  private static Class<?> l;
  private static boolean m = true;

  public static void a()
  {
    if (a != null)
      a.a(2, false, 0L);
  }

  public static void a(long paramLong)
  {
    if (paramLong < 0L)
      paramLong = com.tencent.bugly.crashreport.common.strategy.a.a().c().q;
    f = paramLong;
  }

  public static void a(Context paramContext)
  {
    if ((b) && (paramContext != null))
    {
      if (Build.VERSION.SDK_INT >= 14)
      {
        boolean bool = StubApp.getOrigApplicationContext(paramContext.getApplicationContext()) instanceof Application;
        Application localApplication = null;
        if (bool)
          localApplication = (Application)StubApp.getOrigApplicationContext(paramContext.getApplicationContext());
        if (localApplication != null)
          try
          {
            if (k != null)
              localApplication.unregisterActivityLifecycleCallbacks(k);
          }
          catch (Exception localException)
          {
            if (!x.a(localException))
              localException.printStackTrace();
          }
      }
      b = false;
      return;
    }
  }

  public static void a(Context paramContext, final BuglyStrategy paramBuglyStrategy)
  {
    if (b)
      return;
    m = com.tencent.bugly.crashreport.common.info.a.a(paramContext).e;
    a = new a(paramContext, m);
    b = true;
    long l1;
    if (paramBuglyStrategy != null)
    {
      l = paramBuglyStrategy.getUserInfoActivity();
      l1 = paramBuglyStrategy.getAppReportDelay();
    }
    else
    {
      l1 = 0L;
    }
    if (l1 <= 0L)
    {
      c(paramContext, paramBuglyStrategy);
      return;
    }
    w.a().a(new Runnable()
    {
      public final void run()
      {
        b.b(this.a, paramBuglyStrategy);
      }
    }
    , l1);
  }

  public static void a(StrategyBean paramStrategyBean, boolean paramBoolean)
  {
    if ((a != null) && (!paramBoolean))
    {
      a locala = a;
      w localw = w.a();
      if (localw != null)
        localw.a(new a.2(locala));
    }
    if (paramStrategyBean == null)
      return;
    if (paramStrategyBean.q > 0L)
      e = paramStrategyBean.q;
    if (paramStrategyBean.w > 0)
      c = paramStrategyBean.w;
    if (paramStrategyBean.x > 0L)
      d = paramStrategyBean.x;
  }

  private static void c(Context paramContext, BuglyStrategy paramBuglyStrategy)
  {
    boolean bool2;
    boolean bool1;
    if (paramBuglyStrategy != null)
    {
      bool2 = paramBuglyStrategy.recordUserInfoOnceADay();
      bool1 = paramBuglyStrategy.isEnableUserInfo();
    }
    else
    {
      bool1 = true;
      bool2 = false;
    }
    if (bool2)
    {
      com.tencent.bugly.crashreport.common.info.a locala3 = com.tencent.bugly.crashreport.common.info.a.a(paramContext);
      String str2 = locala3.d;
      List localList = a.a(str2);
      if (localList != null)
        for (int i4 = 0; i4 < localList.size(); i4++)
        {
          UserInfoBean localUserInfoBean = (UserInfoBean)localList.get(i4);
          if ((localUserInfoBean.n.equals(locala3.j)) && (localUserInfoBean.b == 1))
          {
            long l1 = z.b();
            if (l1 <= 0L)
              break;
            if (localUserInfoBean.e >= l1)
            {
              if (localUserInfoBean.f <= 0L)
              {
                a locala4 = a;
                w localw = w.a();
                if (localw != null)
                  localw.a(new a.2(locala4));
              }
              i3 = 0;
              break label185;
            }
          }
        }
      int i3 = 1;
      label185: if (i3 == 0)
        return;
      bool1 = false;
    }
    com.tencent.bugly.crashreport.common.info.a locala1 = com.tencent.bugly.crashreport.common.info.a.b();
    if (locala1 != null)
    {
      StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
      int n = arrayOfStackTraceElement.length;
      String str1 = null;
      int i1 = 0;
      int i2 = 0;
      while (i1 < n)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i1];
        if (localStackTraceElement.getMethodName().equals("onCreate"))
          str1 = localStackTraceElement.getClassName();
        if (localStackTraceElement.getClassName().equals("android.app.Activity"))
          i2 = 1;
        i1++;
      }
      if (str1 != null)
      {
        if (i2 != 0)
          locala1.a(true);
        else
          str1 = "background";
      }
      else
        str1 = "unknown";
      locala1.p = str1;
    }
    if ((bool1) && (Build.VERSION.SDK_INT >= 14))
    {
      boolean bool3 = StubApp.getOrigApplicationContext(paramContext.getApplicationContext()) instanceof Application;
      Application localApplication = null;
      if (bool3)
        localApplication = (Application)StubApp.getOrigApplicationContext(paramContext.getApplicationContext());
      if (localApplication != null)
        try
        {
          if (k == null)
            k = new Application.ActivityLifecycleCallbacks()
            {
              public final void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
              {
                String str = "unknown";
                if (paramAnonymousActivity != null)
                  str = paramAnonymousActivity.getClass().getName();
                if ((b.b() != null) && (!b.b().getName().equals(str)))
                  return;
                x.c(">>> %s onCreated <<<", new Object[] { str });
                com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
                if (locala != null)
                  locala.C.add(b.a(str, "onCreated"));
              }

              public final void onActivityDestroyed(Activity paramAnonymousActivity)
              {
                String str = "unknown";
                if (paramAnonymousActivity != null)
                  str = paramAnonymousActivity.getClass().getName();
                if ((b.b() != null) && (!b.b().getName().equals(str)))
                  return;
                x.c(">>> %s onDestroyed <<<", new Object[] { str });
                com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
                if (locala != null)
                  locala.C.add(b.a(str, "onDestroyed"));
              }

              public final void onActivityPaused(Activity paramAnonymousActivity)
              {
                String str = "unknown";
                if (paramAnonymousActivity != null)
                  str = paramAnonymousActivity.getClass().getName();
                if ((b.b() != null) && (!b.b().getName().equals(str)))
                  return;
                x.c(">>> %s onPaused <<<", new Object[] { str });
                com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
                if (locala == null)
                  return;
                locala.C.add(b.a(str, "onPaused"));
                locala.a(false);
                locala.r = System.currentTimeMillis();
                locala.s = (locala.r - locala.q);
                b.c(locala.r);
                if (locala.s < 0L)
                  locala.s = 0L;
                if (paramAnonymousActivity != null)
                {
                  locala.p = "background";
                  return;
                }
                locala.p = "unknown";
              }

              public final void onActivityResumed(Activity paramAnonymousActivity)
              {
                String str = "unknown";
                if (paramAnonymousActivity != null)
                  str = paramAnonymousActivity.getClass().getName();
                if ((b.b() != null) && (!b.b().getName().equals(str)))
                  return;
                x.c(">>> %s onResumed <<<", new Object[] { str });
                com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
                if (locala == null)
                  return;
                locala.C.add(b.a(str, "onResumed"));
                locala.a(true);
                locala.p = str;
                locala.q = System.currentTimeMillis();
                locala.t = (locala.q - b.c());
                long l1 = locala.q - b.d();
                long l2;
                if (b.e() > 0L)
                  l2 = b.e();
                else
                  l2 = b.f();
                if (l1 > l2)
                {
                  locala.d();
                  b.g();
                  Object[] arrayOfObject = new Object[2];
                  arrayOfObject[0] = Long.valueOf(l1 / 1000L);
                  arrayOfObject[1] = Long.valueOf(b.f() / 1000L);
                  x.a("[session] launch app one times (app in background %d seconds and over %d seconds)", arrayOfObject);
                  if (b.h() % b.i() == 0)
                  {
                    b.a.a(4, b.j(), 0L);
                    return;
                  }
                  b.a.a(4, false, 0L);
                  long l3 = System.currentTimeMillis();
                  if (l3 - b.k() > b.l())
                  {
                    b.b(l3);
                    x.a("add a timer to upload hot start user info", new Object[0]);
                    if (b.j())
                    {
                      a locala1 = b.a;
                      long l4 = b.l();
                      w.a().a(new a.a(locala1, null, true), l4);
                    }
                  }
                }
              }

              public final void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
              {
              }

              public final void onActivityStarted(Activity paramAnonymousActivity)
              {
              }

              public final void onActivityStopped(Activity paramAnonymousActivity)
              {
              }
            };
          localApplication.registerActivityLifecycleCallbacks(k);
        }
        catch (Exception localException)
        {
          if (!x.a(localException))
            localException.printStackTrace();
        }
    }
    if (m)
    {
      i = System.currentTimeMillis();
      a.a(1, false, 0L);
      x.a("[session] launch app, new start", new Object[0]);
      a.a();
      a locala2 = a;
      w.a().a(new a.c(locala2, 21600000L), 21600000L);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.biz.b
 * JD-Core Version:    0.6.1
 */