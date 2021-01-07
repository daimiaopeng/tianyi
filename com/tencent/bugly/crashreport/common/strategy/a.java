package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import com.tencent.bugly.crashreport.biz.b;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class a
{
  public static int a = 1000;
  private static a b;
  private static String h;
  private final List<com.tencent.bugly.a> c;
  private final w d;
  private final StrategyBean e;
  private StrategyBean f = null;
  private Context g;

  private a(Context paramContext, List<com.tencent.bugly.a> paramList)
  {
    this.g = paramContext;
    this.e = new StrategyBean();
    this.c = paramList;
    this.d = w.a();
  }

  public static a a()
  {
    try
    {
      a locala = b;
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static a a(Context paramContext, List<com.tencent.bugly.a> paramList)
  {
    try
    {
      if (b == null)
        b = new a(paramContext, paramList);
      a locala = b;
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static StrategyBean d()
  {
    List localList = p.a().a(2);
    if ((localList != null) && (localList.size() > 0))
    {
      r localr = (r)localList.get(0);
      if (localr.g != null)
        return (StrategyBean)z.a(localr.g, StrategyBean.CREATOR);
    }
    return null;
  }

  public final void a(long paramLong)
  {
    this.d.a(new Thread()
    {
      public final void run()
      {
        try
        {
          Map localMap = p.a().a(a.a, null, true);
          if (localMap != null)
          {
            byte[] arrayOfByte1 = (byte[])localMap.get("key_imei");
            byte[] arrayOfByte2 = (byte[])localMap.get("key_ip");
            if (arrayOfByte1 != null)
              com.tencent.bugly.crashreport.common.info.a.a(a.a(a.this)).e(new String(arrayOfByte1));
            if (arrayOfByte2 != null)
              com.tencent.bugly.crashreport.common.info.a.a(a.a(a.this)).d(new String(arrayOfByte2));
          }
          a locala = a.this;
          a.a(locala, a.d());
          if ((a.b(a.this) != null) && (!z.a(a.e())) && (z.c(a.e())))
          {
            a.b(a.this).r = a.e();
            a.b(a.this).s = a.e();
          }
        }
        catch (Throwable localThrowable)
        {
          if (!x.a(localThrowable))
            localThrowable.printStackTrace();
        }
        a.this.a(a.b(a.this), false);
      }
    }
    , paramLong);
  }

  protected final void a(StrategyBean paramStrategyBean, boolean paramBoolean)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = b.class.getName();
    x.c("[Strategy] Notify %s", arrayOfObject1);
    b.a(paramStrategyBean, paramBoolean);
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
    {
      com.tencent.bugly.a locala = (com.tencent.bugly.a)localIterator.next();
      try
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = locala.getClass().getName();
        x.c("[Strategy] Notify %s", arrayOfObject2);
        locala.onServerStrategyChanged(paramStrategyBean);
      }
      catch (Throwable localThrowable)
      {
      }
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
  }

  public final void a(ap paramap)
  {
    if (paramap == null)
      return;
    if ((this.f != null) && (paramap.h == this.f.p))
      return;
    StrategyBean localStrategyBean = new StrategyBean();
    localStrategyBean.g = paramap.a;
    localStrategyBean.i = paramap.c;
    localStrategyBean.h = paramap.b;
    if ((z.a(null)) || (!z.c(null)))
    {
      if (z.c(paramap.d))
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = paramap.d;
        x.c("[Strategy] Upload url changes to %s", arrayOfObject3);
        localStrategyBean.r = paramap.d;
      }
      if (z.c(paramap.e))
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramap.e;
        x.c("[Strategy] Exception upload url changes to %s", arrayOfObject2);
        localStrategyBean.s = paramap.e;
      }
    }
    if ((paramap.f != null) && (!z.a(paramap.f.a)))
      localStrategyBean.u = paramap.f.a;
    if (paramap.h != 0L)
      localStrategyBean.p = paramap.h;
    if ((paramap.g != null) && (paramap.g.size() > 0))
    {
      localStrategyBean.v = paramap.g;
      String str1 = (String)paramap.g.get("B11");
      if ((str1 != null) && (str1.equals("1")))
        localStrategyBean.j = true;
      else
        localStrategyBean.j = false;
      String str2 = (String)paramap.g.get("B3");
      if (str2 != null)
        localStrategyBean.y = Long.valueOf(str2).longValue();
      localStrategyBean.q = paramap.i;
      localStrategyBean.x = paramap.i;
      String str3 = (String)paramap.g.get("B27");
      if ((str3 != null) && (str3.length() > 0))
        try
        {
          int i = Integer.parseInt(str3);
          if (i > 0)
            localStrategyBean.w = i;
        }
        catch (Exception localException)
        {
          if (!x.a(localException))
            localException.printStackTrace();
        }
      String str4 = (String)paramap.g.get("B25");
      if ((str4 != null) && (str4.equals("1")))
        localStrategyBean.l = true;
      else
        localStrategyBean.l = false;
    }
    Object[] arrayOfObject1 = new Object[10];
    arrayOfObject1[0] = Boolean.valueOf(localStrategyBean.g);
    arrayOfObject1[1] = Boolean.valueOf(localStrategyBean.i);
    arrayOfObject1[2] = Boolean.valueOf(localStrategyBean.h);
    arrayOfObject1[3] = Boolean.valueOf(localStrategyBean.j);
    arrayOfObject1[4] = Boolean.valueOf(localStrategyBean.k);
    arrayOfObject1[5] = Boolean.valueOf(localStrategyBean.n);
    arrayOfObject1[6] = Boolean.valueOf(localStrategyBean.o);
    arrayOfObject1[7] = Long.valueOf(localStrategyBean.q);
    arrayOfObject1[8] = Boolean.valueOf(localStrategyBean.l);
    arrayOfObject1[9] = Long.valueOf(localStrategyBean.p);
    x.a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", arrayOfObject1);
    this.f = localStrategyBean;
    p.a().b(2);
    r localr = new r();
    localr.b = 2;
    localr.a = localStrategyBean.e;
    localr.e = localStrategyBean.f;
    localr.g = z.a(localStrategyBean);
    p.a().a(localr);
    a(localStrategyBean, true);
  }

  public final boolean b()
  {
    try
    {
      StrategyBean localStrategyBean = this.f;
      if (localStrategyBean != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public final StrategyBean c()
  {
    if (this.f != null)
      return this.f;
    return this.e;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.common.strategy.a
 * JD-Core Version:    0.6.1
 */