package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import java.util.Map;

public final class d
{
  private static d a;
  private com.tencent.bugly.crashreport.common.strategy.a b;
  private com.tencent.bugly.crashreport.common.info.a c;
  private b d;
  private Context e;

  private d(Context paramContext)
  {
    c localc = c.a();
    if (localc == null)
      return;
    this.b = com.tencent.bugly.crashreport.common.strategy.a.a();
    this.c = com.tencent.bugly.crashreport.common.info.a.a(paramContext);
    this.d = localc.o;
    this.e = paramContext;
    w.a().a(new Runnable()
    {
      public final void run()
      {
        d.a(d.this);
      }
    });
  }

  public static d a(Context paramContext)
  {
    if (a == null)
      a = new d(paramContext);
    return a;
  }

  public static void a(Thread paramThread, final int paramInt, final String paramString1, final String paramString2, final String paramString3, final Map<String, String> paramMap)
  {
    w localw = w.a();
    Runnable local2 = new Runnable()
    {
      public final void run()
      {
        try
        {
          if (d.a() == null)
          {
            x.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
            return;
          }
          d.a(d.a(), this.a, paramInt, paramString1, paramString2, paramString3, paramMap);
          return;
        }
        catch (Throwable localThrowable)
        {
          if (!x.b(localThrowable))
            localThrowable.printStackTrace();
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = paramString1;
          arrayOfObject[1] = paramString2;
          arrayOfObject[2] = paramString3;
          x.e("[ExtraCrashManager] Crash error %s %s %s", arrayOfObject);
        }
      }
    };
    localw.a(local2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.d
 * JD-Core Version:    0.6.1
 */