package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.Map;

public final class e
  implements Thread.UncaughtExceptionHandler
{
  private static String h;
  private static final Object i = new Object();
  private Context a;
  private b b;
  private com.tencent.bugly.crashreport.common.strategy.a c;
  private com.tencent.bugly.crashreport.common.info.a d;
  private Thread.UncaughtExceptionHandler e;
  private Thread.UncaughtExceptionHandler f;
  private boolean g = false;
  private int j;

  public e(Context paramContext, b paramb, com.tencent.bugly.crashreport.common.strategy.a parama, com.tencent.bugly.crashreport.common.info.a parama1)
  {
    this.a = paramContext;
    this.b = paramb;
    this.c = parama;
    this.d = parama1;
  }

  private static String a(Throwable paramThrowable, int paramInt)
  {
    if (paramThrowable == null)
      return null;
    StringBuilder localStringBuilder1 = new StringBuilder();
    try
    {
      if (paramThrowable.getStackTrace() != null)
        for (StackTraceElement localStackTraceElement : paramThrowable.getStackTrace())
        {
          if ((paramInt > 0) && (localStringBuilder1.length() >= paramInt))
          {
            StringBuilder localStringBuilder2 = new StringBuilder("\n[Stack over limit size :");
            localStringBuilder2.append(paramInt);
            localStringBuilder2.append(" , has been cutted !]");
            localStringBuilder1.append(localStringBuilder2.toString());
            return localStringBuilder1.toString();
          }
          localStringBuilder1.append(localStackTraceElement.toString());
          localStringBuilder1.append("\n");
        }
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      x.e("gen stack error %s", arrayOfObject);
    }
    return localStringBuilder1.toString();
  }

  private static boolean a(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    if (paramUncaughtExceptionHandler == null)
      return true;
    String str1 = paramUncaughtExceptionHandler.getClass().getName();
    for (StackTraceElement localStackTraceElement : Thread.currentThread().getStackTrace())
    {
      String str2 = localStackTraceElement.getClassName();
      String str3 = localStackTraceElement.getMethodName();
      if ((str1.equals(str2)) && ("uncaughtException".equals(str3)))
        return false;
    }
    return true;
  }

  private static boolean a(Thread paramThread)
  {
    synchronized (i)
    {
      if (h != null)
      {
        boolean bool = paramThread.getName().equals(h);
        if (bool)
          return true;
      }
      h = paramThread.getName();
      return false;
    }
  }

  private CrashDetailBean b(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, String paramString, byte[] paramArrayOfByte)
  {
    if (paramThrowable == null)
    {
      x.d("We can do nothing with a null throwable.", new Object[0]);
      return null;
    }
    boolean bool1 = c.a().l();
    String str1;
    if ((bool1) && (paramBoolean))
      str1 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
    else
      str1 = "";
    if ((bool1) && (paramBoolean))
      x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
    CrashDetailBean localCrashDetailBean = new CrashDetailBean();
    localCrashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.h();
    localCrashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.f();
    localCrashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.j();
    localCrashDetailBean.E = this.d.p();
    localCrashDetailBean.F = this.d.o();
    localCrashDetailBean.G = this.d.q();
    localCrashDetailBean.w = z.a(this.a, c.e, null);
    localCrashDetailBean.x = y.a();
    Object[] arrayOfObject1 = new Object[1];
    int k;
    if (localCrashDetailBean.x == null)
      k = 0;
    else
      k = localCrashDetailBean.x.length;
    arrayOfObject1[0] = Integer.valueOf(k);
    x.a("user log size:%d", arrayOfObject1);
    int m;
    if (paramBoolean)
      m = 0;
    else
      m = 2;
    localCrashDetailBean.b = m;
    localCrashDetailBean.e = this.d.h();
    localCrashDetailBean.f = this.d.j;
    localCrashDetailBean.g = this.d.w();
    localCrashDetailBean.m = this.d.g();
    String str2 = paramThrowable.getClass().getName();
    String str3 = b(paramThrowable, 1000);
    if (str3 == null)
      str3 = "";
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = Integer.valueOf(paramThrowable.getStackTrace().length);
    boolean bool2;
    if (paramThrowable.getCause() != null)
      bool2 = true;
    else
      bool2 = false;
    arrayOfObject2[1] = Boolean.valueOf(bool2);
    x.e("stack frame :%d, has cause %b", arrayOfObject2);
    String str4 = "";
    if (paramThrowable.getStackTrace().length > 0)
      str4 = paramThrowable.getStackTrace()[0].toString();
    for (Throwable localThrowable1 = paramThrowable; (localThrowable1 != null) && (localThrowable1.getCause() != null); localThrowable1 = localThrowable1.getCause());
    String str5;
    if ((localThrowable1 != null) && (localThrowable1 != paramThrowable))
    {
      localCrashDetailBean.n = localThrowable1.getClass().getName();
      localCrashDetailBean.o = b(localThrowable1, 1000);
      if (localCrashDetailBean.o == null)
        localCrashDetailBean.o = "";
      if (localThrowable1.getStackTrace().length > 0)
        localCrashDetailBean.p = localThrowable1.getStackTrace()[0].toString();
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(str2);
      localStringBuilder3.append(":");
      localStringBuilder3.append(str3);
      localStringBuilder3.append("\n");
      localStringBuilder3.append(str4);
      localStringBuilder3.append("\n......");
      localStringBuilder3.append("\nCaused by:\n");
      localStringBuilder3.append(localCrashDetailBean.n);
      localStringBuilder3.append(":");
      localStringBuilder3.append(localCrashDetailBean.o);
      localStringBuilder3.append("\n");
      str5 = a(localThrowable1, c.f);
      localStringBuilder3.append(str5);
      localCrashDetailBean.q = localStringBuilder3.toString();
    }
    else
    {
      localCrashDetailBean.n = str2;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(str3);
      localStringBuilder1.append(str1);
      localCrashDetailBean.o = localStringBuilder1.toString();
      if (localCrashDetailBean.o == null)
        localCrashDetailBean.o = "";
      localCrashDetailBean.p = str4;
      str5 = a(paramThrowable, c.f);
      localCrashDetailBean.q = str5;
    }
    localCrashDetailBean.r = System.currentTimeMillis();
    localCrashDetailBean.u = z.b(localCrashDetailBean.q.getBytes());
    try
    {
      localCrashDetailBean.y = z.a(c.f, false);
      localCrashDetailBean.z = this.d.d;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramThread.getName());
      localStringBuilder2.append("(");
      localStringBuilder2.append(paramThread.getId());
      localStringBuilder2.append(")");
      localCrashDetailBean.A = localStringBuilder2.toString();
      localCrashDetailBean.y.put(localCrashDetailBean.A, str5);
      localCrashDetailBean.H = this.d.y();
      localCrashDetailBean.h = this.d.v();
      localCrashDetailBean.i = this.d.K();
      localCrashDetailBean.L = this.d.a;
      localCrashDetailBean.M = this.d.a();
      localCrashDetailBean.O = this.d.H();
      localCrashDetailBean.P = this.d.I();
      localCrashDetailBean.Q = this.d.B();
      localCrashDetailBean.R = this.d.G();
    }
    catch (Throwable localThrowable2)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = localThrowable2.toString();
      x.e("handle crash error %s", arrayOfObject3);
    }
    if (paramBoolean)
    {
      this.b.c(localCrashDetailBean);
    }
    else
    {
      int n;
      if ((paramString != null) && (paramString.length() > 0))
        n = 1;
      else
        n = 0;
      int i1 = 0;
      if (paramArrayOfByte != null)
      {
        int i2 = paramArrayOfByte.length;
        i1 = 0;
        if (i2 > 0)
          i1 = 1;
      }
      if (n != 0)
      {
        localCrashDetailBean.N = new HashMap(1);
        localCrashDetailBean.N.put("UserData", paramString);
      }
      if (i1 != 0)
        localCrashDetailBean.S = paramArrayOfByte;
    }
    return localCrashDetailBean;
  }

  private static String b(Throwable paramThrowable, int paramInt)
  {
    if (paramThrowable.getMessage() == null)
      return "";
    if (paramThrowable.getMessage().length() <= 1000)
      return paramThrowable.getMessage();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramThrowable.getMessage().substring(0, 1000));
    localStringBuilder.append("\n[Message over limit size:1000");
    localStringBuilder.append(", has been cutted!]");
    return localStringBuilder.toString();
  }

  public final void a()
  {
    try
    {
      if (this.j >= 10)
      {
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = Integer.valueOf(10);
        x.a("java crash handler over %d, no need set.", arrayOfObject4);
        return;
      }
      this.g = true;
      Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
      if (localUncaughtExceptionHandler != null)
      {
        boolean bool = getClass().getName().equals(localUncaughtExceptionHandler.getClass().getName());
        if (bool)
          return;
        if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(localUncaughtExceptionHandler.getClass().getName()))
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = localUncaughtExceptionHandler.toString();
          x.a("backup system java handler: %s", arrayOfObject3);
          this.f = localUncaughtExceptionHandler;
          this.e = localUncaughtExceptionHandler;
        }
        else
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = localUncaughtExceptionHandler.toString();
          x.a("backup java handler: %s", arrayOfObject2);
          this.e = localUncaughtExceptionHandler;
        }
      }
      Thread.setDefaultUncaughtExceptionHandler(this);
      this.j = (1 + this.j);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = toString();
      x.a("registered java monitor: %s", arrayOfObject1);
      return;
    }
    finally
    {
    }
  }

  public final void a(StrategyBean paramStrategyBean)
  {
    if (paramStrategyBean != null)
      try
      {
        if (paramStrategyBean.g != this.g)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Boolean.valueOf(paramStrategyBean.g);
          x.a("java changed to %b", arrayOfObject);
          if (paramStrategyBean.g)
          {
            a();
            return;
          }
          b();
        }
      }
      finally
      {
      }
  }

  // ERROR //
  public final void a(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iload_3
    //   1: ifeq +111 -> 112
    //   4: iconst_2
    //   5: anewarray 4	java/lang/Object
    //   8: astore 41
    //   10: aload 41
    //   12: iconst_0
    //   13: aload_1
    //   14: invokevirtual 123	java/lang/Thread:getName	()Ljava/lang/String;
    //   17: aastore
    //   18: aload 41
    //   20: iconst_1
    //   21: aload_1
    //   22: invokevirtual 299	java/lang/Thread:getId	()J
    //   25: invokestatic 432	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   28: aastore
    //   29: ldc_w 434
    //   32: aload 41
    //   34: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   37: pop
    //   38: aload_1
    //   39: invokestatic 436	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread;)Z
    //   42: ifeq +81 -> 123
    //   45: ldc_w 438
    //   48: iconst_0
    //   49: anewarray 4	java/lang/Object
    //   52: invokestatic 214	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   55: pop
    //   56: aload_0
    //   57: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   60: ifnull +28 -> 88
    //   63: ldc_w 440
    //   66: iconst_0
    //   67: anewarray 4	java/lang/Object
    //   70: invokestatic 214	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   73: pop
    //   74: aload_0
    //   75: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   78: aload_1
    //   79: aload_2
    //   80: invokeinterface 443 3 0
    //   85: goto +38 -> 123
    //   88: ldc_w 445
    //   91: iconst_0
    //   92: anewarray 4	java/lang/Object
    //   95: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   98: pop
    //   99: invokestatic 450	android/os/Process:myPid	()I
    //   102: invokestatic 453	android/os/Process:killProcess	(I)V
    //   105: iconst_1
    //   106: invokestatic 456	java/lang/System:exit	(I)V
    //   109: goto +14 -> 123
    //   112: ldc_w 458
    //   115: iconst_0
    //   116: anewarray 4	java/lang/Object
    //   119: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   122: pop
    //   123: aload_0
    //   124: getfield 35	com/tencent/bugly/crashreport/crash/e:g	Z
    //   127: ifne +154 -> 281
    //   130: ldc_w 460
    //   133: iconst_0
    //   134: anewarray 4	java/lang/Object
    //   137: invokestatic 462	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   140: pop
    //   141: iload_3
    //   142: ifeq +138 -> 280
    //   145: aload_0
    //   146: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   149: ifnull +47 -> 196
    //   152: aload_0
    //   153: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   156: invokestatic 464	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread$UncaughtExceptionHandler;)Z
    //   159: ifeq +37 -> 196
    //   162: ldc_w 466
    //   165: iconst_0
    //   166: anewarray 4	java/lang/Object
    //   169: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   172: pop
    //   173: aload_0
    //   174: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   177: aload_1
    //   178: aload_2
    //   179: invokeinterface 443 3 0
    //   184: ldc_w 468
    //   187: iconst_0
    //   188: anewarray 4	java/lang/Object
    //   191: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   194: pop
    //   195: return
    //   196: aload_0
    //   197: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   200: ifnull +37 -> 237
    //   203: ldc_w 470
    //   206: iconst_0
    //   207: anewarray 4	java/lang/Object
    //   210: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   213: pop
    //   214: aload_0
    //   215: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   218: aload_1
    //   219: aload_2
    //   220: invokeinterface 443 3 0
    //   225: ldc_w 472
    //   228: iconst_0
    //   229: anewarray 4	java/lang/Object
    //   232: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   235: pop
    //   236: return
    //   237: ldc_w 474
    //   240: iconst_0
    //   241: anewarray 4	java/lang/Object
    //   244: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   247: pop
    //   248: ldc_w 445
    //   251: iconst_0
    //   252: anewarray 4	java/lang/Object
    //   255: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   258: pop
    //   259: invokestatic 450	android/os/Process:myPid	()I
    //   262: invokestatic 453	android/os/Process:killProcess	(I)V
    //   265: iconst_1
    //   266: invokestatic 456	java/lang/System:exit	(I)V
    //   269: ldc_w 476
    //   272: iconst_0
    //   273: anewarray 4	java/lang/Object
    //   276: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   279: pop
    //   280: return
    //   281: aload_0
    //   282: getfield 41	com/tencent/bugly/crashreport/crash/e:c	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   285: invokevirtual 480	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   288: ifne +47 -> 335
    //   291: ldc_w 482
    //   294: iconst_0
    //   295: anewarray 4	java/lang/Object
    //   298: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   301: pop
    //   302: iconst_0
    //   303: istore 36
    //   305: aload_0
    //   306: getfield 41	com/tencent/bugly/crashreport/crash/e:c	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   309: invokevirtual 480	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   312: ifne +23 -> 335
    //   315: ldc2_w 483
    //   318: invokestatic 487	com/tencent/bugly/proguard/z:b	(J)V
    //   321: wide
    //   327: iload 36
    //   329: sipush 3000
    //   332: if_icmplt -27 -> 305
    //   335: aload_0
    //   336: getfield 41	com/tencent/bugly/crashreport/crash/e:c	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   339: invokevirtual 480	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   342: ifne +14 -> 356
    //   345: ldc_w 489
    //   348: iconst_0
    //   349: anewarray 4	java/lang/Object
    //   352: invokestatic 128	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   355: pop
    //   356: aload_0
    //   357: getfield 41	com/tencent/bugly/crashreport/crash/e:c	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   360: invokevirtual 492	com/tencent/bugly/crashreport/common/strategy/a:c	()Lcom/tencent/bugly/crashreport/common/strategy/StrategyBean;
    //   363: getfield 420	com/tencent/bugly/crashreport/common/strategy/StrategyBean:g	Z
    //   366: ifne +135 -> 501
    //   369: aload_0
    //   370: getfield 41	com/tencent/bugly/crashreport/crash/e:c	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   373: invokevirtual 480	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   376: ifeq +125 -> 501
    //   379: ldc_w 494
    //   382: iconst_0
    //   383: anewarray 4	java/lang/Object
    //   386: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   389: pop
    //   390: iload_3
    //   391: ifeq +564 -> 955
    //   394: ldc_w 496
    //   397: astore 30
    //   399: goto +3 -> 402
    //   402: aload 30
    //   404: invokestatic 498	com/tencent/bugly/proguard/z:a	()Ljava/lang/String;
    //   407: aload_0
    //   408: getfield 43	com/tencent/bugly/crashreport/crash/e:d	Lcom/tencent/bugly/crashreport/common/info/a;
    //   411: getfield 291	com/tencent/bugly/crashreport/common/info/a:d	Ljava/lang/String;
    //   414: aload_1
    //   415: aload_2
    //   416: invokestatic 501	com/tencent/bugly/proguard/z:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   419: aconst_null
    //   420: invokestatic 504	com/tencent/bugly/crashreport/crash/b:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Ljava/lang/String;Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   423: iload_3
    //   424: ifeq +76 -> 500
    //   427: aload_0
    //   428: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   431: ifnull +16 -> 447
    //   434: aload_0
    //   435: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   438: invokestatic 464	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread$UncaughtExceptionHandler;)Z
    //   441: ifeq +6 -> 447
    //   444: goto -282 -> 162
    //   447: aload_0
    //   448: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   451: ifnull +6 -> 457
    //   454: goto -251 -> 203
    //   457: ldc_w 474
    //   460: iconst_0
    //   461: anewarray 4	java/lang/Object
    //   464: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   467: pop
    //   468: ldc_w 445
    //   471: iconst_0
    //   472: anewarray 4	java/lang/Object
    //   475: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   478: pop
    //   479: invokestatic 450	android/os/Process:myPid	()I
    //   482: invokestatic 453	android/os/Process:killProcess	(I)V
    //   485: iconst_1
    //   486: invokestatic 456	java/lang/System:exit	(I)V
    //   489: ldc_w 476
    //   492: iconst_0
    //   493: anewarray 4	java/lang/Object
    //   496: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   499: pop
    //   500: return
    //   501: aload_0
    //   502: aload_1
    //   503: aload_2
    //   504: iload_3
    //   505: aload 4
    //   507: aload 5
    //   509: invokespecial 506	com/tencent/bugly/crashreport/crash/e:b	(Ljava/lang/Thread;Ljava/lang/Throwable;ZLjava/lang/String;[B)Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;
    //   512: astore 23
    //   514: aload 23
    //   516: ifnonnull +92 -> 608
    //   519: ldc_w 508
    //   522: iconst_0
    //   523: anewarray 4	java/lang/Object
    //   526: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   529: pop
    //   530: iload_3
    //   531: ifeq +76 -> 607
    //   534: aload_0
    //   535: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   538: ifnull +16 -> 554
    //   541: aload_0
    //   542: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   545: invokestatic 464	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread$UncaughtExceptionHandler;)Z
    //   548: ifeq +6 -> 554
    //   551: goto -389 -> 162
    //   554: aload_0
    //   555: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   558: ifnull +6 -> 564
    //   561: goto -358 -> 203
    //   564: ldc_w 474
    //   567: iconst_0
    //   568: anewarray 4	java/lang/Object
    //   571: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   574: pop
    //   575: ldc_w 445
    //   578: iconst_0
    //   579: anewarray 4	java/lang/Object
    //   582: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   585: pop
    //   586: invokestatic 450	android/os/Process:myPid	()I
    //   589: invokestatic 453	android/os/Process:killProcess	(I)V
    //   592: iconst_1
    //   593: invokestatic 456	java/lang/System:exit	(I)V
    //   596: ldc_w 476
    //   599: iconst_0
    //   600: anewarray 4	java/lang/Object
    //   603: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   606: pop
    //   607: return
    //   608: iload_3
    //   609: ifeq +354 -> 963
    //   612: ldc_w 496
    //   615: astore 24
    //   617: goto +3 -> 620
    //   620: aload 24
    //   622: invokestatic 498	com/tencent/bugly/proguard/z:a	()Ljava/lang/String;
    //   625: aload_0
    //   626: getfield 43	com/tencent/bugly/crashreport/crash/e:d	Lcom/tencent/bugly/crashreport/common/info/a;
    //   629: getfield 291	com/tencent/bugly/crashreport/common/info/a:d	Ljava/lang/String;
    //   632: aload_1
    //   633: aload_2
    //   634: invokestatic 501	com/tencent/bugly/proguard/z:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   637: aload 23
    //   639: invokestatic 504	com/tencent/bugly/crashreport/crash/b:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Ljava/lang/String;Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   642: aload_0
    //   643: getfield 39	com/tencent/bugly/crashreport/crash/e:b	Lcom/tencent/bugly/crashreport/crash/b;
    //   646: aload 23
    //   648: invokevirtual 511	com/tencent/bugly/crashreport/crash/b:a	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)Z
    //   651: ifne +16 -> 667
    //   654: aload_0
    //   655: getfield 39	com/tencent/bugly/crashreport/crash/e:b	Lcom/tencent/bugly/crashreport/crash/b;
    //   658: aload 23
    //   660: ldc2_w 512
    //   663: iload_3
    //   664: invokevirtual 516	com/tencent/bugly/crashreport/crash/b:a	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;JZ)V
    //   667: aload_0
    //   668: getfield 39	com/tencent/bugly/crashreport/crash/e:b	Lcom/tencent/bugly/crashreport/crash/b;
    //   671: aload 23
    //   673: invokevirtual 518	com/tencent/bugly/crashreport/crash/b:b	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   676: iload_3
    //   677: ifeq +131 -> 808
    //   680: aload_0
    //   681: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   684: ifnull +16 -> 700
    //   687: aload_0
    //   688: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   691: invokestatic 464	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread$UncaughtExceptionHandler;)Z
    //   694: ifeq +6 -> 700
    //   697: goto -535 -> 162
    //   700: aload_0
    //   701: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   704: ifnull +6 -> 710
    //   707: goto -504 -> 203
    //   710: ldc_w 474
    //   713: iconst_0
    //   714: anewarray 4	java/lang/Object
    //   717: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   720: pop
    //   721: ldc_w 445
    //   724: iconst_0
    //   725: anewarray 4	java/lang/Object
    //   728: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   731: pop
    //   732: invokestatic 450	android/os/Process:myPid	()I
    //   735: invokestatic 453	android/os/Process:killProcess	(I)V
    //   738: iconst_1
    //   739: invokestatic 456	java/lang/System:exit	(I)V
    //   742: ldc_w 476
    //   745: iconst_0
    //   746: anewarray 4	java/lang/Object
    //   749: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   752: pop
    //   753: return
    //   754: astore 15
    //   756: goto +53 -> 809
    //   759: astore 7
    //   761: aload 7
    //   763: invokestatic 521	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   766: ifne +8 -> 774
    //   769: aload 7
    //   771: invokevirtual 524	java/lang/Throwable:printStackTrace	()V
    //   774: iload_3
    //   775: ifeq +33 -> 808
    //   778: aload_0
    //   779: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   782: ifnull +16 -> 798
    //   785: aload_0
    //   786: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   789: invokestatic 464	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread$UncaughtExceptionHandler;)Z
    //   792: ifeq +6 -> 798
    //   795: goto -633 -> 162
    //   798: aload_0
    //   799: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   802: ifnull -92 -> 710
    //   805: goto -602 -> 203
    //   808: return
    //   809: iload_3
    //   810: ifeq +142 -> 952
    //   813: aload_0
    //   814: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   817: ifnull +49 -> 866
    //   820: aload_0
    //   821: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   824: invokestatic 464	com/tencent/bugly/crashreport/crash/e:a	(Ljava/lang/Thread$UncaughtExceptionHandler;)Z
    //   827: ifeq +39 -> 866
    //   830: ldc_w 466
    //   833: iconst_0
    //   834: anewarray 4	java/lang/Object
    //   837: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   840: pop
    //   841: aload_0
    //   842: getfield 408	com/tencent/bugly/crashreport/crash/e:e	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   845: aload_1
    //   846: aload_2
    //   847: invokeinterface 443 3 0
    //   852: ldc_w 468
    //   855: iconst_0
    //   856: anewarray 4	java/lang/Object
    //   859: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   862: pop
    //   863: goto +89 -> 952
    //   866: aload_0
    //   867: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   870: ifnull +39 -> 909
    //   873: ldc_w 470
    //   876: iconst_0
    //   877: anewarray 4	java/lang/Object
    //   880: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   883: pop
    //   884: aload_0
    //   885: getfield 406	com/tencent/bugly/crashreport/crash/e:f	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   888: aload_1
    //   889: aload_2
    //   890: invokeinterface 443 3 0
    //   895: ldc_w 472
    //   898: iconst_0
    //   899: anewarray 4	java/lang/Object
    //   902: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   905: pop
    //   906: goto +46 -> 952
    //   909: ldc_w 474
    //   912: iconst_0
    //   913: anewarray 4	java/lang/Object
    //   916: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   919: pop
    //   920: ldc_w 445
    //   923: iconst_0
    //   924: anewarray 4	java/lang/Object
    //   927: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   930: pop
    //   931: invokestatic 450	android/os/Process:myPid	()I
    //   934: invokestatic 453	android/os/Process:killProcess	(I)V
    //   937: iconst_1
    //   938: invokestatic 456	java/lang/System:exit	(I)V
    //   941: ldc_w 476
    //   944: iconst_0
    //   945: anewarray 4	java/lang/Object
    //   948: invokestatic 88	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   951: pop
    //   952: aload 15
    //   954: athrow
    //   955: ldc_w 526
    //   958: astore 30
    //   960: goto -558 -> 402
    //   963: ldc_w 526
    //   966: astore 24
    //   968: goto -348 -> 620
    //
    // Exception table:
    //   from	to	target	type
    //   123	141	754	finally
    //   281	423	754	finally
    //   501	530	754	finally
    //   612	676	754	finally
    //   761	774	754	finally
    //   123	141	759	java/lang/Throwable
    //   281	423	759	java/lang/Throwable
    //   501	530	759	java/lang/Throwable
    //   612	676	759	java/lang/Throwable
  }

  public final void b()
  {
    try
    {
      this.g = false;
      x.a("close java monitor!", new Object[0]);
      if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly"))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = toString();
        x.a("Java monitor to unregister: %s", arrayOfObject);
        Thread.setDefaultUncaughtExceptionHandler(this.e);
        this.j -= 1;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    synchronized (i)
    {
      a(paramThread, paramThrowable, true, null, null);
      return;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.e
 * JD-Core Version:    0.6.1
 */