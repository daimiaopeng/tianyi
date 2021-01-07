package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class c
{
  public static int a = 0;
  public static boolean b = false;
  public static int c = 2;
  public static boolean d = true;
  public static int e = 20000;
  public static int f = 20000;
  public static long g = 604800000L;
  public static String h;
  public static boolean i = false;
  public static String j;
  public static int k = 5000;
  public static boolean l = true;
  public static String m;
  public static String n;
  private static c q;
  public final b o;
  private final Context p;
  private final e r;
  private final NativeCrashHandler s;
  private com.tencent.bugly.crashreport.common.strategy.a t;
  private w u;
  private final com.tencent.bugly.crashreport.crash.anr.b v;
  private Boolean w;

  private c(int paramInt, Context paramContext, w paramw, boolean paramBoolean, BuglyStrategy.a parama, o paramo, String paramString)
  {
    a = paramInt;
    Context localContext = z.a(paramContext);
    this.p = localContext;
    this.t = com.tencent.bugly.crashreport.common.strategy.a.a();
    this.u = paramw;
    u localu = u.a();
    p localp = p.a();
    b localb = new b(paramInt, localContext, localu, localp, this.t, parama, paramo);
    this.o = localb;
    com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.a(localContext);
    this.r = new e(localContext, this.o, this.t, locala);
    this.s = NativeCrashHandler.getInstance(localContext, locala, this.o, this.t, paramw, paramBoolean, paramString);
    locala.D = this.s;
    com.tencent.bugly.crashreport.crash.anr.b localb1 = new com.tencent.bugly.crashreport.crash.anr.b(localContext, this.t, locala, paramw, this.o);
    this.v = localb1;
  }

  public static c a()
  {
    try
    {
      c localc = q;
      return localc;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static void a(int paramInt, Context paramContext, boolean paramBoolean, BuglyStrategy.a parama, o paramo, String paramString)
  {
    try
    {
      if (q == null)
      {
        c localc = new c(1004, paramContext, w.a(), paramBoolean, parama, null, null);
        q = localc;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void a(long paramLong)
  {
    w.a().a(new Thread()
    {
      public final void run()
      {
        if (!z.a(c.b(c.this), "local_crash_lock", 10000L))
          return;
        List localList = c.this.o.a();
        if ((localList != null) && (localList.size() > 0))
        {
          int i = localList.size();
          Object localObject;
          if (i > 100L)
          {
            ArrayList localArrayList = new ArrayList();
            Collections.sort(localList);
            for (int j = 0; j < 100L; j++)
              localArrayList.add(localList.get(i - 1 - j));
            localObject = localArrayList;
          }
          else
          {
            localObject = localList;
          }
          c.this.o.a((List)localObject, 0L, false, false, false);
        }
        z.b(c.b(c.this), "local_crash_lock");
      }
    }
    , 0L);
  }

  public final void a(StrategyBean paramStrategyBean)
  {
    this.r.a(paramStrategyBean);
    this.s.onStrategyChanged(paramStrategyBean);
    this.v.a(paramStrategyBean);
    w.a().a(new Thread()
    {
      public final void run()
      {
        if (!z.a(c.b(c.this), "local_crash_lock", 10000L))
          return;
        List localList = c.this.o.a();
        if ((localList != null) && (localList.size() > 0))
        {
          int i = localList.size();
          Object localObject;
          if (i > 100L)
          {
            ArrayList localArrayList = new ArrayList();
            Collections.sort(localList);
            for (int j = 0; j < 100L; j++)
              localArrayList.add(localList.get(i - 1 - j));
            localObject = localArrayList;
          }
          else
          {
            localObject = localList;
          }
          c.this.o.a((List)localObject, 0L, false, false, false);
        }
        z.b(c.b(c.this), "local_crash_lock");
      }
    }
    , 0L);
  }

  public final void a(CrashDetailBean paramCrashDetailBean)
  {
    this.o.d(paramCrashDetailBean);
  }

  public final void a(final Thread paramThread, final Throwable paramThrowable, boolean paramBoolean1, String paramString, byte[] paramArrayOfByte, final boolean paramBoolean2)
  {
    w localw = this.u;
    Runnable local1 = new Runnable()
    {
      public final void run()
      {
        try
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Boolean.valueOf(this.a);
          x.c("post a throwable %b", arrayOfObject2);
          c.a(c.this).a(paramThread, paramThrowable, false, this.d, this.e);
          if (paramBoolean2)
          {
            x.a("clear user datas", new Object[0]);
            com.tencent.bugly.crashreport.common.info.a.a(c.b(c.this)).C();
          }
          return;
        }
        catch (Throwable localThrowable)
        {
          if (x.b(localThrowable) != true)
            localThrowable.printStackTrace();
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = paramThrowable.toString();
          x.e("java catch error: %s", arrayOfObject1);
        }
      }
    };
    localw.a(local1);
  }

  public final boolean b()
  {
    Boolean localBoolean = this.w;
    if (localBoolean != null)
      return localBoolean.booleanValue();
    String str = com.tencent.bugly.crashreport.common.info.a.b().d;
    List localList = p.a().a(1);
    ArrayList localArrayList = new ArrayList();
    if ((localList != null) && (localList.size() > 0))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        r localr = (r)localIterator.next();
        if (str.equals(localr.c))
        {
          this.w = Boolean.valueOf(true);
          localArrayList.add(localr);
        }
      }
      if (localArrayList.size() > 0)
        p.a().a(localArrayList);
      return true;
    }
    this.w = Boolean.valueOf(false);
    return false;
  }

  public final void c()
  {
    try
    {
      this.r.a();
      this.s.setUserOpened(true);
      this.v.a(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void d()
  {
    try
    {
      this.r.b();
      this.s.setUserOpened(false);
      this.v.a(false);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void e()
  {
    this.r.a();
  }

  public final void f()
  {
    this.s.setUserOpened(false);
  }

  public final void g()
  {
    this.s.setUserOpened(true);
  }

  public final void h()
  {
    this.v.a(true);
  }

  public final void i()
  {
    this.v.a(false);
  }

  public final void j()
  {
    try
    {
      this.s.testNativeCrash();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void k()
  {
    try
    {
      int i1 = 0;
      while (true)
      {
        int i2 = i1 + 1;
        if (i1 < 30)
          try
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(i2);
            x.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", arrayOfObject);
            z.b(5000L);
            i1 = i2;
          }
          catch (Throwable localThrowable)
          {
            if (!x.a(localThrowable))
              localThrowable.printStackTrace();
            return;
          }
      }
      return;
    }
    finally
    {
    }
  }

  public final boolean l()
  {
    return this.v.a();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.c
 * JD-Core Version:    0.6.1
 */