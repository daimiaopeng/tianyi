package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;

public class BuglyBroadcastRecevier extends BroadcastReceiver
{
  private static BuglyBroadcastRecevier d;
  private IntentFilter a = new IntentFilter();
  private Context b;
  private String c;
  private boolean e = true;

  private boolean a(Context paramContext, Intent paramIntent)
  {
    if ((paramContext != null) && (paramIntent != null))
      try
      {
        if (paramIntent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
        {
          if (this.e)
          {
            this.e = false;
            return true;
          }
          String str1 = com.tencent.bugly.crashreport.common.info.b.f(this.b);
          StringBuilder localStringBuilder1 = new StringBuilder("is Connect BC ");
          localStringBuilder1.append(str1);
          x.c(localStringBuilder1.toString(), new Object[0]);
          Object[] arrayOfObject = new Object[2];
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append(this.c);
          arrayOfObject[0] = localStringBuilder2.toString();
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append(str1);
          arrayOfObject[1] = localStringBuilder3.toString();
          x.a("network %s changed to %s", arrayOfObject);
          if (str1 == null)
          {
            this.c = null;
            return true;
          }
          String str2 = this.c;
          this.c = str1;
          long l = System.currentTimeMillis();
          com.tencent.bugly.crashreport.common.strategy.a locala = com.tencent.bugly.crashreport.common.strategy.a.a();
          u localu = u.a();
          com.tencent.bugly.crashreport.common.info.a locala1 = com.tencent.bugly.crashreport.common.info.a.a(paramContext);
          if ((locala != null) && (localu != null) && (locala1 != null))
          {
            if (!str1.equals(str2))
            {
              if (l - localu.a(c.a) > 30000L)
              {
                x.a("try to upload crash on network changed.", new Object[0]);
                c localc = c.a();
                if (localc != null)
                  localc.a(0L);
              }
              if (l - localu.a(1001) > 30000L)
              {
                x.a("try to upload userinfo on network changed.", new Object[0]);
                com.tencent.bugly.crashreport.biz.b.a.b();
              }
            }
            return true;
          }
          x.d("not inited BC not work", new Object[0]);
          return true;
        }
      }
      finally
      {
      }
    return false;
  }

  public static BuglyBroadcastRecevier getInstance()
  {
    try
    {
      if (d == null)
        d = new BuglyBroadcastRecevier();
      BuglyBroadcastRecevier localBuglyBroadcastRecevier = d;
      return localBuglyBroadcastRecevier;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addFilter(String paramString)
  {
    try
    {
      if (!this.a.hasAction(paramString))
        this.a.addAction(paramString);
      x.c("add action %s", new Object[] { paramString });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      a(paramContext, paramIntent);
      return;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
  }

  public void register(Context paramContext)
  {
    try
    {
      this.b = paramContext;
      z.a(new Runnable()
      {
        public final void run()
        {
          try
          {
            x.a(BuglyBroadcastRecevier.a().getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
            synchronized (jdField_this)
            {
              BuglyBroadcastRecevier.b(BuglyBroadcastRecevier.this).registerReceiver(BuglyBroadcastRecevier.a(), BuglyBroadcastRecevier.a(BuglyBroadcastRecevier.this));
            }
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
          }
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void unregister(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 177	java/lang/Object:getClass	()Ljava/lang/Class;
    //   6: ldc 179
    //   8: iconst_0
    //   9: anewarray 69	java/lang/Object
    //   12: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)Z
    //   15: pop
    //   16: aload_1
    //   17: aload_0
    //   18: invokevirtual 188	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   21: aload_0
    //   22: aload_1
    //   23: putfield 47	com/tencent/bugly/crashreport/crash/BuglyBroadcastRecevier:b	Landroid/content/Context;
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: astore_3
    //   30: goto +18 -> 48
    //   33: astore_2
    //   34: aload_2
    //   35: invokestatic 157	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   38: ifne +7 -> 45
    //   41: aload_2
    //   42: invokevirtual 160	java/lang/Throwable:printStackTrace	()V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_3
    //   51: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	26	29	finally
    //   34	45	29	finally
    //   2	26	33	java/lang/Throwable
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier
 * JD-Core Version:    0.6.1
 */