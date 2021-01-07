package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.x;

public class CrashModule extends a
{
  public static final int MODULE_ID = 1004;
  private static int c;
  private static boolean d;
  private static CrashModule e = new CrashModule();
  private long a;
  private BuglyStrategy.a b;

  private void a(Context paramContext, BuglyStrategy paramBuglyStrategy)
  {
    if (paramBuglyStrategy == null)
      return;
    try
    {
      String str = paramBuglyStrategy.getLibBuglySOFilePath();
      if (!TextUtils.isEmpty(str))
      {
        com.tencent.bugly.crashreport.common.info.a.a(paramContext).m = str;
        x.a("setted libBugly.so file path :%s", new Object[] { str });
      }
      if (paramBuglyStrategy.getCrashHandleCallback() != null)
      {
        this.b = paramBuglyStrategy.getCrashHandleCallback();
        x.a("setted CrashHanldeCallback", new Object[0]);
      }
      if (paramBuglyStrategy.getAppReportDelay() > 0L)
      {
        this.a = paramBuglyStrategy.getAppReportDelay();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Long.valueOf(this.a);
        x.a("setted delay: %d", arrayOfObject);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static CrashModule getInstance()
  {
    e.id = 1004;
    return e;
  }

  public static boolean hasInitialized()
  {
    return d;
  }

  public String[] getTables()
  {
    return new String[] { "t_cr" };
  }

  public void init(Context paramContext, boolean paramBoolean, BuglyStrategy paramBuglyStrategy)
  {
    if (paramContext != null)
      try
      {
        if (!d)
        {
          x.a("Initializing crash module.", new Object[0]);
          n localn1 = n.a();
          int i = 1 + c;
          c = i;
          localn1.a(1004, i);
          d = true;
          CrashReport.setContext(paramContext);
          a(paramContext, paramBuglyStrategy);
          c.a(1004, paramContext, paramBoolean, this.b, null, null);
          c localc = c.a();
          localc.e();
          if ((paramBuglyStrategy != null) && (!paramBuglyStrategy.isEnableNativeCrashMonitor()))
          {
            x.a("[crash] Closed native crash monitor!", new Object[0]);
            localc.f();
          }
          else
          {
            localc.g();
          }
          if ((paramBuglyStrategy != null) && (!paramBuglyStrategy.isEnableANRCrashMonitor()))
          {
            x.a("[crash] Closed ANR monitor!", new Object[0]);
            localc.i();
          }
          else
          {
            localc.h();
          }
          d.a(paramContext);
          BuglyBroadcastRecevier localBuglyBroadcastRecevier = BuglyBroadcastRecevier.getInstance();
          localBuglyBroadcastRecevier.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
          localBuglyBroadcastRecevier.register(paramContext);
          n localn2 = n.a();
          int j = c - 1;
          c = j;
          localn2.a(1004, j);
          return;
        }
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
  }

  public void onServerStrategyChanged(StrategyBean paramStrategyBean)
  {
    if (paramStrategyBean == null)
      return;
    c localc = c.a();
    if (localc != null)
      localc.a(paramStrategyBean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.CrashModule
 * JD-Core Version:    0.6.1
 */