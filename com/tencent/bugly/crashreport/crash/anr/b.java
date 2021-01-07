package com.tencent.bugly.crashreport.crash.anr;

import android.content.Context;
import android.os.FileObserver;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class b
{
  private AtomicInteger a = new AtomicInteger(0);
  private long b = -1L;
  private final Context c;
  private final com.tencent.bugly.crashreport.common.info.a d;
  private final w e;
  private final com.tencent.bugly.crashreport.common.strategy.a f;
  private final String g;
  private final com.tencent.bugly.crashreport.crash.b h;
  private FileObserver i;
  private boolean j = true;

  public b(Context paramContext, com.tencent.bugly.crashreport.common.strategy.a parama, com.tencent.bugly.crashreport.common.info.a parama1, w paramw, com.tencent.bugly.crashreport.crash.b paramb)
  {
    this.c = z.a(paramContext);
    this.g = paramContext.getDir("bugly", 0).getAbsolutePath();
    this.d = parama1;
    this.e = paramw;
    this.f = parama;
    this.h = paramb;
  }

  private CrashDetailBean a(a parama)
  {
    CrashDetailBean localCrashDetailBean = new CrashDetailBean();
    while (true)
    {
      try
      {
        localCrashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.h();
        localCrashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.f();
        localCrashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.j();
        localCrashDetailBean.E = this.d.p();
        localCrashDetailBean.F = this.d.o();
        localCrashDetailBean.G = this.d.q();
        localCrashDetailBean.w = z.a(this.c, c.e, null);
        localCrashDetailBean.b = 3;
        localCrashDetailBean.e = this.d.h();
        localCrashDetailBean.f = this.d.j;
        localCrashDetailBean.g = this.d.w();
        localCrashDetailBean.m = this.d.g();
        localCrashDetailBean.n = "ANR_EXCEPTION";
        localCrashDetailBean.o = parama.f;
        localCrashDetailBean.q = parama.g;
        localCrashDetailBean.N = new HashMap();
        localCrashDetailBean.N.put("BUGLY_CR_01", parama.e);
        int k = -1;
        if (localCrashDetailBean.q != null)
          k = localCrashDetailBean.q.indexOf("\n");
        if (k <= 0)
          break label426;
        str = localCrashDetailBean.q.substring(0, k);
        localCrashDetailBean.p = str;
        localCrashDetailBean.r = parama.c;
        if (localCrashDetailBean.q != null)
          localCrashDetailBean.u = z.b(localCrashDetailBean.q.getBytes());
        localCrashDetailBean.y = parama.b;
        localCrashDetailBean.z = this.d.d;
        localCrashDetailBean.A = "main(1)";
        localCrashDetailBean.H = this.d.y();
        localCrashDetailBean.h = this.d.v();
        localCrashDetailBean.i = this.d.K();
        localCrashDetailBean.v = parama.d;
        localCrashDetailBean.K = this.d.n;
        localCrashDetailBean.L = this.d.a;
        localCrashDetailBean.M = this.d.a();
        localCrashDetailBean.O = this.d.H();
        localCrashDetailBean.P = this.d.I();
        localCrashDetailBean.Q = this.d.B();
        localCrashDetailBean.R = this.d.G();
        this.h.c(localCrashDetailBean);
        localCrashDetailBean.x = y.a();
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
      }
      return localCrashDetailBean;
      label426: String str = "GET_FAIL";
    }
  }

  // ERROR //
  private static boolean a(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_2
    //   1: aload_0
    //   2: iconst_1
    //   3: invokestatic 307	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:readTargetDumpInfo	(Ljava/lang/String;Ljava/lang/String;Z)Lcom/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull +760 -> 768
    //   11: aload_3
    //   12: getfield 311	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a:d	Ljava/util/Map;
    //   15: ifnull +753 -> 768
    //   18: aload_3
    //   19: getfield 311	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a:d	Ljava/util/Map;
    //   22: invokeinterface 314 1 0
    //   27: ifgt +6 -> 33
    //   30: goto +738 -> 768
    //   33: new 59	java/io/File
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 317	java/io/File:<init>	(Ljava/lang/String;)V
    //   41: astore 5
    //   43: aload 5
    //   45: invokevirtual 320	java/io/File:exists	()Z
    //   48: ifne +29 -> 77
    //   51: aload 5
    //   53: invokevirtual 324	java/io/File:getParentFile	()Ljava/io/File;
    //   56: invokevirtual 320	java/io/File:exists	()Z
    //   59: ifne +12 -> 71
    //   62: aload 5
    //   64: invokevirtual 324	java/io/File:getParentFile	()Ljava/io/File;
    //   67: invokevirtual 327	java/io/File:mkdirs	()Z
    //   70: pop
    //   71: aload 5
    //   73: invokevirtual 330	java/io/File:createNewFile	()Z
    //   76: pop
    //   77: aload 5
    //   79: invokevirtual 320	java/io/File:exists	()Z
    //   82: ifeq +580 -> 662
    //   85: aload 5
    //   87: invokevirtual 333	java/io/File:canWrite	()Z
    //   90: ifne +6 -> 96
    //   93: goto +569 -> 662
    //   96: aconst_null
    //   97: astore 14
    //   99: new 335	java/io/BufferedWriter
    //   102: dup
    //   103: new 337	java/io/FileWriter
    //   106: dup
    //   107: aload 5
    //   109: iconst_0
    //   110: invokespecial 340	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   113: invokespecial 343	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   116: astore 15
    //   118: aload_3
    //   119: getfield 311	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a:d	Ljava/util/Map;
    //   122: ldc_w 345
    //   125: invokeinterface 349 2 0
    //   130: checkcast 351	[Ljava/lang/String;
    //   133: astore 26
    //   135: aload 26
    //   137: ifnull +105 -> 242
    //   140: aload 26
    //   142: arraylength
    //   143: iconst_3
    //   144: if_icmplt +98 -> 242
    //   147: aload 26
    //   149: iconst_0
    //   150: aaload
    //   151: astore 42
    //   153: aload 26
    //   155: iconst_1
    //   156: aaload
    //   157: astore 43
    //   159: aload 26
    //   161: iconst_2
    //   162: aaload
    //   163: astore 44
    //   165: new 353	java/lang/StringBuilder
    //   168: dup
    //   169: ldc_w 355
    //   172: invokespecial 356	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   175: astore 45
    //   177: aload 45
    //   179: aload 44
    //   181: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: pop
    //   185: aload 45
    //   187: ldc_w 362
    //   190: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: aload 45
    //   196: aload 42
    //   198: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload 45
    //   204: ldc 177
    //   206: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: aload 45
    //   212: aload 43
    //   214: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload 45
    //   220: ldc_w 364
    //   223: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload 15
    //   229: aload 45
    //   231: invokevirtual 367	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokevirtual 370	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   237: aload 15
    //   239: invokevirtual 373	java/io/BufferedWriter:flush	()V
    //   242: aload_3
    //   243: getfield 311	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a:d	Ljava/util/Map;
    //   246: invokeinterface 377 1 0
    //   251: invokeinterface 383 1 0
    //   256: astore 27
    //   258: aload 27
    //   260: invokeinterface 388 1 0
    //   265: ifeq +206 -> 471
    //   268: aload 27
    //   270: invokeinterface 392 1 0
    //   275: checkcast 394	java/util/Map$Entry
    //   278: astore 29
    //   280: aload 29
    //   282: invokeinterface 397 1 0
    //   287: checkcast 179	java/lang/String
    //   290: ldc_w 345
    //   293: invokevirtual 401	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   296: ifne -38 -> 258
    //   299: aload 29
    //   301: invokeinterface 404 1 0
    //   306: ifnull -48 -> 258
    //   309: aload 29
    //   311: invokeinterface 404 1 0
    //   316: checkcast 351	[Ljava/lang/String;
    //   319: arraylength
    //   320: iconst_3
    //   321: if_icmplt -63 -> 258
    //   324: aload 29
    //   326: invokeinterface 404 1 0
    //   331: checkcast 351	[Ljava/lang/String;
    //   334: iconst_0
    //   335: aaload
    //   336: astore 30
    //   338: aload 29
    //   340: invokeinterface 404 1 0
    //   345: checkcast 351	[Ljava/lang/String;
    //   348: iconst_1
    //   349: aaload
    //   350: astore 31
    //   352: aload 29
    //   354: invokeinterface 404 1 0
    //   359: checkcast 351	[Ljava/lang/String;
    //   362: iconst_2
    //   363: aaload
    //   364: astore 32
    //   366: new 353	java/lang/StringBuilder
    //   369: dup
    //   370: ldc_w 406
    //   373: invokespecial 356	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   376: astore 33
    //   378: aload 33
    //   380: aload 29
    //   382: invokeinterface 397 1 0
    //   387: checkcast 179	java/lang/String
    //   390: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   393: pop
    //   394: aload 33
    //   396: ldc_w 408
    //   399: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: pop
    //   403: aload 33
    //   405: aload 32
    //   407: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: pop
    //   411: aload 33
    //   413: ldc_w 362
    //   416: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: pop
    //   420: aload 33
    //   422: aload 30
    //   424: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: pop
    //   428: aload 33
    //   430: ldc 177
    //   432: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: pop
    //   436: aload 33
    //   438: aload 31
    //   440: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: pop
    //   444: aload 33
    //   446: ldc_w 364
    //   449: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: pop
    //   453: aload 15
    //   455: aload 33
    //   457: invokevirtual 367	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   460: invokevirtual 370	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   463: aload 15
    //   465: invokevirtual 373	java/io/BufferedWriter:flush	()V
    //   468: goto -210 -> 258
    //   471: aload 15
    //   473: invokevirtual 411	java/io/BufferedWriter:close	()V
    //   476: goto +18 -> 494
    //   479: astore 28
    //   481: aload 28
    //   483: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   486: ifne +8 -> 494
    //   489: aload 28
    //   491: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   494: iconst_1
    //   495: ireturn
    //   496: astore 24
    //   498: goto +133 -> 631
    //   501: astore 16
    //   503: aload 15
    //   505: astore 14
    //   507: goto +14 -> 521
    //   510: astore 24
    //   512: aload 14
    //   514: astore 15
    //   516: goto +115 -> 631
    //   519: astore 16
    //   521: aload 16
    //   523: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   526: ifne +8 -> 534
    //   529: aload 16
    //   531: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   534: iconst_1
    //   535: anewarray 4	java/lang/Object
    //   538: astore 17
    //   540: new 353	java/lang/StringBuilder
    //   543: dup
    //   544: invokespecial 413	java/lang/StringBuilder:<init>	()V
    //   547: astore 18
    //   549: aload 18
    //   551: aload 16
    //   553: invokevirtual 417	java/lang/Object:getClass	()Ljava/lang/Class;
    //   556: invokevirtual 422	java/lang/Class:getName	()Ljava/lang/String;
    //   559: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: pop
    //   563: aload 18
    //   565: ldc_w 424
    //   568: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: aload 18
    //   574: aload 16
    //   576: invokevirtual 427	java/io/IOException:getMessage	()Ljava/lang/String;
    //   579: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   582: pop
    //   583: aload 17
    //   585: iconst_0
    //   586: aload 18
    //   588: invokevirtual 367	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   591: aastore
    //   592: ldc_w 429
    //   595: aload 17
    //   597: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   600: pop
    //   601: aload 14
    //   603: ifnull +26 -> 629
    //   606: aload 14
    //   608: invokevirtual 411	java/io/BufferedWriter:close	()V
    //   611: goto +18 -> 629
    //   614: astore 23
    //   616: aload 23
    //   618: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   621: ifne +8 -> 629
    //   624: aload 23
    //   626: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   629: iconst_0
    //   630: ireturn
    //   631: aload 15
    //   633: ifnull +26 -> 659
    //   636: aload 15
    //   638: invokevirtual 411	java/io/BufferedWriter:close	()V
    //   641: goto +18 -> 659
    //   644: astore 25
    //   646: aload 25
    //   648: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   651: ifne +8 -> 659
    //   654: aload 25
    //   656: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   659: aload 24
    //   661: athrow
    //   662: ldc_w 434
    //   665: iconst_1
    //   666: anewarray 4	java/lang/Object
    //   669: dup
    //   670: iconst_0
    //   671: aload_1
    //   672: aastore
    //   673: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   676: pop
    //   677: iconst_0
    //   678: ireturn
    //   679: astore 6
    //   681: aload 6
    //   683: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   686: ifne +8 -> 694
    //   689: aload 6
    //   691: invokevirtual 435	java/lang/Exception:printStackTrace	()V
    //   694: iconst_2
    //   695: anewarray 4	java/lang/Object
    //   698: astore 7
    //   700: new 353	java/lang/StringBuilder
    //   703: dup
    //   704: invokespecial 413	java/lang/StringBuilder:<init>	()V
    //   707: astore 8
    //   709: aload 8
    //   711: aload 6
    //   713: invokevirtual 417	java/lang/Object:getClass	()Ljava/lang/Class;
    //   716: invokevirtual 422	java/lang/Class:getName	()Ljava/lang/String;
    //   719: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   722: pop
    //   723: aload 8
    //   725: ldc_w 424
    //   728: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: pop
    //   732: aload 8
    //   734: aload 6
    //   736: invokevirtual 436	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   739: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: pop
    //   743: aload 7
    //   745: iconst_0
    //   746: aload 8
    //   748: invokevirtual 367	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   751: aastore
    //   752: aload 7
    //   754: iconst_1
    //   755: aload_1
    //   756: aastore
    //   757: ldc_w 438
    //   760: aload 7
    //   762: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   765: pop
    //   766: iconst_0
    //   767: ireturn
    //   768: ldc_w 440
    //   771: iconst_1
    //   772: anewarray 4	java/lang/Object
    //   775: dup
    //   776: iconst_0
    //   777: aload_2
    //   778: aastore
    //   779: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   782: pop
    //   783: iconst_0
    //   784: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   471	476	479	java/io/IOException
    //   118	468	496	finally
    //   118	468	501	java/io/IOException
    //   99	118	510	finally
    //   521	601	510	finally
    //   99	118	519	java/io/IOException
    //   606	611	614	java/io/IOException
    //   636	641	644	java/io/IOException
    //   43	77	679	java/lang/Exception
  }

  private void b(boolean paramBoolean)
  {
    if (paramBoolean)
      try
      {
        c();
        return;
      }
      finally
      {
        break label24;
      }
    d();
    return;
    label24: throw localObject;
  }

  private void c()
  {
    try
    {
      if (e())
      {
        x.d("start when started!", new Object[0]);
        return;
      }
      this.i = new FileObserver("/data/anr/", 8)
      {
        public final void onEvent(int paramAnonymousInt, String paramAnonymousString)
        {
          if (paramAnonymousString == null)
            return;
          StringBuilder localStringBuilder = new StringBuilder("/data/anr/");
          localStringBuilder.append(paramAnonymousString);
          String str = localStringBuilder.toString();
          if (!str.contains("trace"))
          {
            x.d("not anr file %s", new Object[] { str });
            return;
          }
          b.this.a(str);
        }
      };
      try
      {
        this.i.startWatching();
        x.a("start anr monitor!", new Object[0]);
        this.e.a(new Runnable()
        {
          public final void run()
          {
            b.this.b();
          }
        });
        return;
      }
      catch (Throwable localThrowable)
      {
        this.i = null;
        x.d("start anr monitor failed!", new Object[0]);
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return;
      }
    }
    finally
    {
    }
  }

  private void c(boolean paramBoolean)
  {
    try
    {
      if (this.j != paramBoolean)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(paramBoolean);
        x.a("user change anr %b", arrayOfObject);
        this.j = paramBoolean;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void d()
  {
    try
    {
      if (!e())
      {
        x.d("close when closed!", new Object[0]);
        return;
      }
      try
      {
        this.i.stopWatching();
        this.i = null;
        x.d("close anr monitor!", new Object[0]);
        return;
      }
      catch (Throwable localThrowable)
      {
        x.d("stop anr monitor failed!", new Object[0]);
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return;
      }
    }
    finally
    {
    }
  }

  private boolean e()
  {
    try
    {
      FileObserver localFileObserver = this.i;
      if (localFileObserver != null)
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

  private boolean f()
  {
    try
    {
      boolean bool = this.j;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void a(StrategyBean paramStrategyBean)
  {
    if (paramStrategyBean == null)
      return;
    while (true)
    {
      try
      {
        if (paramStrategyBean.j != e())
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Boolean.valueOf(paramStrategyBean.j);
          x.d("server anr changed to %b", arrayOfObject2);
        }
        if ((paramStrategyBean.j) && (f()))
        {
          bool = true;
          if (bool != e())
          {
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = Boolean.valueOf(bool);
            x.a("anr changed to %b", arrayOfObject1);
            b(bool);
          }
          return;
        }
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
      boolean bool = false;
    }
  }

  // ERROR //
  public final void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   6: invokevirtual 512	java/util/concurrent/atomic/AtomicInteger:get	()I
    //   9: ifeq +17 -> 26
    //   12: ldc_w 514
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   22: pop
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: aload_0
    //   27: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   30: iconst_1
    //   31: invokevirtual 519	java/util/concurrent/atomic/AtomicInteger:set	(I)V
    //   34: aload_0
    //   35: monitorexit
    //   36: ldc_w 521
    //   39: iconst_0
    //   40: anewarray 4	java/lang/Object
    //   43: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   46: pop
    //   47: aload_1
    //   48: iconst_0
    //   49: invokestatic 525	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:readFirstDumpInfo	(Ljava/lang/String;Z)Lcom/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a;
    //   52: astore 8
    //   54: aload 8
    //   56: ifnull +1075 -> 1131
    //   59: aload 8
    //   61: getfield 526	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper$a:c	J
    //   64: lstore 9
    //   66: goto +3 -> 69
    //   69: lload 9
    //   71: ldc2_w 37
    //   74: lcmp
    //   75: ifne +19 -> 94
    //   78: ldc_w 528
    //   81: iconst_0
    //   82: anewarray 4	java/lang/Object
    //   85: invokestatic 451	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   88: pop
    //   89: invokestatic 533	java/lang/System:currentTimeMillis	()J
    //   92: lstore 9
    //   94: lload 9
    //   96: aload_0
    //   97: getfield 40	com/tencent/bugly/crashreport/crash/anr/b:b	J
    //   100: lsub
    //   101: invokestatic 539	java/lang/Math:abs	(J)J
    //   104: ldc2_w 540
    //   107: lcmp
    //   108: ifge +37 -> 145
    //   111: iconst_1
    //   112: anewarray 4	java/lang/Object
    //   115: astore 54
    //   117: aload 54
    //   119: iconst_0
    //   120: sipush 10000
    //   123: invokestatic 546	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   126: aastore
    //   127: ldc_w 548
    //   130: aload 54
    //   132: invokestatic 451	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   135: pop
    //   136: aload_0
    //   137: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   140: iconst_0
    //   141: invokevirtual 519	java/util/concurrent/atomic/AtomicInteger:set	(I)V
    //   144: return
    //   145: aload_0
    //   146: lload 9
    //   148: putfield 40	com/tencent/bugly/crashreport/crash/anr/b:b	J
    //   151: aload_0
    //   152: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   155: iconst_1
    //   156: invokevirtual 519	java/util/concurrent/atomic/AtomicInteger:set	(I)V
    //   159: getstatic 550	com/tencent/bugly/crashreport/crash/c:f	I
    //   162: iconst_0
    //   163: invokestatic 553	com/tencent/bugly/proguard/z:a	(IZ)Ljava/util/Map;
    //   166: astore 14
    //   168: aload 14
    //   170: ifnull +863 -> 1033
    //   173: aload 14
    //   175: invokeinterface 314 1 0
    //   180: ifgt +6 -> 186
    //   183: goto +850 -> 1033
    //   186: aload_0
    //   187: getfield 49	com/tencent/bugly/crashreport/crash/anr/b:c	Landroid/content/Context;
    //   190: astore 16
    //   192: ldc_w 555
    //   195: iconst_0
    //   196: anewarray 4	java/lang/Object
    //   199: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   202: pop
    //   203: aload 16
    //   205: ldc_w 557
    //   208: invokevirtual 561	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   211: checkcast 563	android/app/ActivityManager
    //   214: astore 18
    //   216: iconst_0
    //   217: istore 19
    //   219: ldc_w 565
    //   222: iconst_0
    //   223: anewarray 4	java/lang/Object
    //   226: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   229: pop
    //   230: aload 18
    //   232: invokevirtual 569	android/app/ActivityManager:getProcessesInErrorState	()Ljava/util/List;
    //   235: astore 21
    //   237: aload 21
    //   239: ifnull +57 -> 296
    //   242: aload 21
    //   244: invokeinterface 572 1 0
    //   249: astore 52
    //   251: aload 52
    //   253: invokeinterface 388 1 0
    //   258: ifeq +38 -> 296
    //   261: aload 52
    //   263: invokeinterface 392 1 0
    //   268: checkcast 574	android/app/ActivityManager$ProcessErrorStateInfo
    //   271: astore 24
    //   273: aload 24
    //   275: getfield 577	android/app/ActivityManager$ProcessErrorStateInfo:condition	I
    //   278: iconst_2
    //   279: if_icmpne +860 -> 1139
    //   282: ldc_w 579
    //   285: iconst_0
    //   286: anewarray 4	java/lang/Object
    //   289: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   292: pop
    //   293: goto +39 -> 332
    //   296: ldc2_w 580
    //   299: invokestatic 584	com/tencent/bugly/proguard/z:b	(J)V
    //   302: iload 19
    //   304: iconst_1
    //   305: iadd
    //   306: istore 22
    //   308: iload 19
    //   310: i2l
    //   311: ldc2_w 585
    //   314: lcmp
    //   315: iflt +711 -> 1026
    //   318: ldc_w 588
    //   321: iconst_0
    //   322: anewarray 4	java/lang/Object
    //   325: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   328: pop
    //   329: aconst_null
    //   330: astore 24
    //   332: aload 24
    //   334: ifnonnull +17 -> 351
    //   337: ldc_w 590
    //   340: iconst_0
    //   341: anewarray 4	java/lang/Object
    //   344: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   347: pop
    //   348: goto -212 -> 136
    //   351: aload 24
    //   353: getfield 593	android/app/ActivityManager$ProcessErrorStateInfo:pid	I
    //   356: invokestatic 598	android/os/Process:myPid	()I
    //   359: if_icmpeq +30 -> 389
    //   362: iconst_1
    //   363: anewarray 4	java/lang/Object
    //   366: astore 49
    //   368: aload 49
    //   370: iconst_0
    //   371: aload 24
    //   373: getfield 601	android/app/ActivityManager$ProcessErrorStateInfo:processName	Ljava/lang/String;
    //   376: aastore
    //   377: ldc_w 603
    //   380: aload 49
    //   382: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   385: pop
    //   386: goto -250 -> 136
    //   389: ldc_w 605
    //   392: iconst_0
    //   393: anewarray 4	java/lang/Object
    //   396: invokestatic 469	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   399: pop
    //   400: aload_0
    //   401: getfield 49	com/tencent/bugly/crashreport/crash/anr/b:c	Landroid/content/Context;
    //   404: astore 26
    //   406: aload_0
    //   407: getfield 71	com/tencent/bugly/crashreport/crash/anr/b:f	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   410: invokevirtual 610	com/tencent/bugly/crashreport/common/strategy/a:c	()Lcom/tencent/bugly/crashreport/common/strategy/StrategyBean;
    //   413: pop
    //   414: aload_0
    //   415: getfield 71	com/tencent/bugly/crashreport/crash/anr/b:f	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   418: invokevirtual 612	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   421: ifne +47 -> 468
    //   424: ldc_w 614
    //   427: iconst_0
    //   428: anewarray 4	java/lang/Object
    //   431: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   434: pop
    //   435: iconst_0
    //   436: istore 48
    //   438: aload_0
    //   439: getfield 71	com/tencent/bugly/crashreport/crash/anr/b:f	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   442: invokevirtual 612	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   445: ifne +23 -> 468
    //   448: ldc2_w 580
    //   451: invokestatic 584	com/tencent/bugly/proguard/z:b	(J)V
    //   454: wide
    //   460: iload 48
    //   462: sipush 3000
    //   465: if_icmplt -27 -> 438
    //   468: aload 26
    //   470: invokevirtual 617	android/content/Context:getFilesDir	()Ljava/io/File;
    //   473: astore 28
    //   475: new 353	java/lang/StringBuilder
    //   478: dup
    //   479: ldc_w 619
    //   482: invokespecial 356	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   485: astore 29
    //   487: aload 29
    //   489: lload 9
    //   491: invokevirtual 622	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   494: pop
    //   495: aload 29
    //   497: ldc_w 624
    //   500: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: pop
    //   504: new 59	java/io/File
    //   507: dup
    //   508: aload 28
    //   510: aload 29
    //   512: invokevirtual 367	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   515: invokespecial 627	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   518: astore 32
    //   520: new 153	com/tencent/bugly/crashreport/crash/anr/a
    //   523: dup
    //   524: invokespecial 628	com/tencent/bugly/crashreport/crash/anr/a:<init>	()V
    //   527: astore 33
    //   529: aload 33
    //   531: lload 9
    //   533: putfield 191	com/tencent/bugly/crashreport/crash/anr/a:c	J
    //   536: aload 33
    //   538: aload 32
    //   540: invokevirtual 63	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   543: putfield 236	com/tencent/bugly/crashreport/crash/anr/a:d	Ljava/lang/String;
    //   546: aload 33
    //   548: aload 24
    //   550: getfield 601	android/app/ActivityManager$ProcessErrorStateInfo:processName	Ljava/lang/String;
    //   553: putfield 630	com/tencent/bugly/crashreport/crash/anr/a:a	Ljava/lang/String;
    //   556: aload 33
    //   558: aload 24
    //   560: getfield 633	android/app/ActivityManager$ProcessErrorStateInfo:shortMsg	Ljava/lang/String;
    //   563: putfield 154	com/tencent/bugly/crashreport/crash/anr/a:f	Ljava/lang/String;
    //   566: aload 33
    //   568: aload 24
    //   570: getfield 636	android/app/ActivityManager$ProcessErrorStateInfo:longMsg	Ljava/lang/String;
    //   573: putfield 169	com/tencent/bugly/crashreport/crash/anr/a:e	Ljava/lang/String;
    //   576: aload 33
    //   578: aload 14
    //   580: putfield 206	com/tencent/bugly/crashreport/crash/anr/a:b	Ljava/util/Map;
    //   583: aload 14
    //   585: ifnull +70 -> 655
    //   588: aload 14
    //   590: invokeinterface 639 1 0
    //   595: invokeinterface 383 1 0
    //   600: astore 45
    //   602: aload 45
    //   604: invokeinterface 388 1 0
    //   609: ifeq +46 -> 655
    //   612: aload 45
    //   614: invokeinterface 392 1 0
    //   619: checkcast 179	java/lang/String
    //   622: astore 46
    //   624: aload 46
    //   626: ldc_w 641
    //   629: invokevirtual 645	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   632: ifeq -30 -> 602
    //   635: aload 33
    //   637: aload 14
    //   639: aload 46
    //   641: invokeinterface 349 2 0
    //   646: checkcast 179	java/lang/String
    //   649: putfield 157	com/tencent/bugly/crashreport/crash/anr/a:g	Ljava/lang/String;
    //   652: goto -50 -> 602
    //   655: bipush 6
    //   657: anewarray 4	java/lang/Object
    //   660: astore 34
    //   662: aload 34
    //   664: iconst_0
    //   665: aload 33
    //   667: getfield 191	com/tencent/bugly/crashreport/crash/anr/a:c	J
    //   670: invokestatic 650	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   673: aastore
    //   674: aload 34
    //   676: iconst_1
    //   677: aload 33
    //   679: getfield 236	com/tencent/bugly/crashreport/crash/anr/a:d	Ljava/lang/String;
    //   682: aastore
    //   683: aload 34
    //   685: iconst_2
    //   686: aload 33
    //   688: getfield 630	com/tencent/bugly/crashreport/crash/anr/a:a	Ljava/lang/String;
    //   691: aastore
    //   692: aload 34
    //   694: iconst_3
    //   695: aload 33
    //   697: getfield 154	com/tencent/bugly/crashreport/crash/anr/a:f	Ljava/lang/String;
    //   700: aastore
    //   701: aload 34
    //   703: iconst_4
    //   704: aload 33
    //   706: getfield 169	com/tencent/bugly/crashreport/crash/anr/a:e	Ljava/lang/String;
    //   709: aastore
    //   710: aload 33
    //   712: getfield 206	com/tencent/bugly/crashreport/crash/anr/a:b	Ljava/util/Map;
    //   715: ifnonnull +9 -> 724
    //   718: iconst_0
    //   719: istore 35
    //   721: goto +15 -> 736
    //   724: aload 33
    //   726: getfield 206	com/tencent/bugly/crashreport/crash/anr/a:b	Ljava/util/Map;
    //   729: invokeinterface 314 1 0
    //   734: istore 35
    //   736: aload 34
    //   738: iconst_5
    //   739: iload 35
    //   741: invokestatic 546	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   744: aastore
    //   745: ldc_w 652
    //   748: aload 34
    //   750: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   753: pop
    //   754: aload_0
    //   755: getfield 71	com/tencent/bugly/crashreport/crash/anr/b:f	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   758: invokevirtual 612	com/tencent/bugly/crashreport/common/strategy/a:b	()Z
    //   761: ifne +38 -> 799
    //   764: ldc_w 654
    //   767: iconst_0
    //   768: anewarray 4	java/lang/Object
    //   771: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   774: pop
    //   775: ldc_w 656
    //   778: invokestatic 658	com/tencent/bugly/proguard/z:a	()Ljava/lang/String;
    //   781: aload 33
    //   783: getfield 630	com/tencent/bugly/crashreport/crash/anr/a:a	Ljava/lang/String;
    //   786: aconst_null
    //   787: aload 33
    //   789: getfield 169	com/tencent/bugly/crashreport/crash/anr/a:e	Ljava/lang/String;
    //   792: aconst_null
    //   793: invokestatic 661	com/tencent/bugly/crashreport/crash/b:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Ljava/lang/String;Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   796: goto +221 -> 1017
    //   799: aload_0
    //   800: getfield 71	com/tencent/bugly/crashreport/crash/anr/b:f	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   803: invokevirtual 610	com/tencent/bugly/crashreport/common/strategy/a:c	()Lcom/tencent/bugly/crashreport/common/strategy/StrategyBean;
    //   806: getfield 502	com/tencent/bugly/crashreport/common/strategy/StrategyBean:j	Z
    //   809: ifne +17 -> 826
    //   812: ldc_w 663
    //   815: iconst_0
    //   816: anewarray 4	java/lang/Object
    //   819: invokestatic 451	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   822: pop
    //   823: goto +194 -> 1017
    //   826: ldc_w 665
    //   829: iconst_0
    //   830: anewarray 4	java/lang/Object
    //   833: invokestatic 469	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   836: pop
    //   837: aload_0
    //   838: aload 33
    //   840: invokespecial 667	com/tencent/bugly/crashreport/crash/anr/b:a	(Lcom/tencent/bugly/crashreport/crash/anr/a;)Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;
    //   843: astore 38
    //   845: aload 38
    //   847: ifnonnull +17 -> 864
    //   850: ldc_w 669
    //   853: iconst_0
    //   854: anewarray 4	java/lang/Object
    //   857: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   860: pop
    //   861: goto +156 -> 1017
    //   864: invokestatic 672	com/tencent/bugly/crashreport/crash/c:a	()Lcom/tencent/bugly/crashreport/crash/c;
    //   867: aload 38
    //   869: invokevirtual 674	com/tencent/bugly/crashreport/crash/c:a	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   872: aload 38
    //   874: getfield 675	com/tencent/bugly/crashreport/crash/CrashDetailBean:a	J
    //   877: lconst_0
    //   878: lcmp
    //   879: iflt +17 -> 896
    //   882: ldc_w 677
    //   885: iconst_0
    //   886: anewarray 4	java/lang/Object
    //   889: invokestatic 469	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   892: pop
    //   893: goto +14 -> 907
    //   896: ldc_w 679
    //   899: iconst_0
    //   900: anewarray 4	java/lang/Object
    //   903: invokestatic 451	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   906: pop
    //   907: aload_1
    //   908: ifnull +53 -> 961
    //   911: new 59	java/io/File
    //   914: dup
    //   915: aload_1
    //   916: invokespecial 317	java/io/File:<init>	(Ljava/lang/String;)V
    //   919: invokevirtual 320	java/io/File:exists	()Z
    //   922: ifeq +39 -> 961
    //   925: aload_0
    //   926: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   929: iconst_3
    //   930: invokevirtual 519	java/util/concurrent/atomic/AtomicInteger:set	(I)V
    //   933: aload_1
    //   934: aload 33
    //   936: getfield 236	com/tencent/bugly/crashreport/crash/anr/a:d	Ljava/lang/String;
    //   939: aload 33
    //   941: getfield 630	com/tencent/bugly/crashreport/crash/anr/a:a	Ljava/lang/String;
    //   944: invokestatic 681	com/tencent/bugly/crashreport/crash/anr/b:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   947: ifeq +14 -> 961
    //   950: ldc_w 683
    //   953: iconst_0
    //   954: anewarray 4	java/lang/Object
    //   957: invokestatic 469	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   960: pop
    //   961: ldc_w 656
    //   964: invokestatic 658	com/tencent/bugly/proguard/z:a	()Ljava/lang/String;
    //   967: aload 33
    //   969: getfield 630	com/tencent/bugly/crashreport/crash/anr/a:a	Ljava/lang/String;
    //   972: aconst_null
    //   973: aload 33
    //   975: getfield 169	com/tencent/bugly/crashreport/crash/anr/a:e	Ljava/lang/String;
    //   978: aload 38
    //   980: invokestatic 661	com/tencent/bugly/crashreport/crash/b:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Thread;Ljava/lang/String;Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   983: aload_0
    //   984: getfield 73	com/tencent/bugly/crashreport/crash/anr/b:h	Lcom/tencent/bugly/crashreport/crash/b;
    //   987: aload 38
    //   989: invokevirtual 686	com/tencent/bugly/crashreport/crash/b:a	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)Z
    //   992: ifne +16 -> 1008
    //   995: aload_0
    //   996: getfield 73	com/tencent/bugly/crashreport/crash/anr/b:h	Lcom/tencent/bugly/crashreport/crash/b;
    //   999: aload 38
    //   1001: ldc2_w 687
    //   1004: iconst_1
    //   1005: invokevirtual 691	com/tencent/bugly/crashreport/crash/b:a	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;JZ)V
    //   1008: aload_0
    //   1009: getfield 73	com/tencent/bugly/crashreport/crash/anr/b:h	Lcom/tencent/bugly/crashreport/crash/b;
    //   1012: aload 38
    //   1014: invokevirtual 693	com/tencent/bugly/crashreport/crash/b:b	(Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;)V
    //   1017: aload_0
    //   1018: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   1021: iconst_0
    //   1022: invokevirtual 519	java/util/concurrent/atomic/AtomicInteger:set	(I)V
    //   1025: return
    //   1026: iload 22
    //   1028: istore 19
    //   1030: goto -811 -> 219
    //   1033: ldc_w 695
    //   1036: iconst_0
    //   1037: anewarray 4	java/lang/Object
    //   1040: invokestatic 451	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1043: pop
    //   1044: goto -908 -> 136
    //   1047: astore 11
    //   1049: aload 11
    //   1051: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   1054: pop
    //   1055: ldc_w 697
    //   1058: iconst_0
    //   1059: anewarray 4	java/lang/Object
    //   1062: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1065: pop
    //   1066: goto -930 -> 136
    //   1069: astore 6
    //   1071: goto +44 -> 1115
    //   1074: astore_3
    //   1075: aload_3
    //   1076: invokestatic 291	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   1079: ifne +7 -> 1086
    //   1082: aload_3
    //   1083: invokevirtual 294	java/lang/Throwable:printStackTrace	()V
    //   1086: iconst_1
    //   1087: anewarray 4	java/lang/Object
    //   1090: astore 4
    //   1092: aload 4
    //   1094: iconst_0
    //   1095: aload_3
    //   1096: invokevirtual 417	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1099: invokevirtual 698	java/lang/Class:toString	()Ljava/lang/String;
    //   1102: aastore
    //   1103: ldc_w 700
    //   1106: aload 4
    //   1108: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1111: pop
    //   1112: goto -95 -> 1017
    //   1115: aload_0
    //   1116: getfield 36	com/tencent/bugly/crashreport/crash/anr/b:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   1119: iconst_0
    //   1120: invokevirtual 519	java/util/concurrent/atomic/AtomicInteger:set	(I)V
    //   1123: aload 6
    //   1125: athrow
    //   1126: astore_2
    //   1127: aload_0
    //   1128: monitorexit
    //   1129: aload_2
    //   1130: athrow
    //   1131: ldc2_w 37
    //   1134: lstore 9
    //   1136: goto -1067 -> 69
    //   1139: goto -888 -> 251
    //
    // Exception table:
    //   from	to	target	type
    //   159	168	1047	java/lang/Throwable
    //   36	136	1069	finally
    //   145	159	1069	finally
    //   159	168	1069	finally
    //   173	1017	1069	finally
    //   1033	1066	1069	finally
    //   1075	1112	1069	finally
    //   36	136	1074	java/lang/Throwable
    //   145	159	1074	java/lang/Throwable
    //   173	1017	1074	java/lang/Throwable
    //   1033	1066	1074	java/lang/Throwable
    //   2	36	1126	finally
  }

  public final void a(boolean paramBoolean)
  {
    c(paramBoolean);
    boolean bool = f();
    com.tencent.bugly.crashreport.common.strategy.a locala = com.tencent.bugly.crashreport.common.strategy.a.a();
    if (locala != null)
      if ((bool) && (locala.c().g))
        bool = true;
      else
        bool = false;
    if (bool != e())
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(bool);
      x.a("anr changed to %b", arrayOfObject);
      b(bool);
    }
  }

  public final boolean a()
  {
    return this.a.get() != 0;
  }

  // ERROR //
  protected final void b()
  {
    // Byte code:
    //   0: invokestatic 709	com/tencent/bugly/proguard/z:b	()J
    //   3: getstatic 711	com/tencent/bugly/crashreport/crash/c:g	J
    //   6: lsub
    //   7: lstore_1
    //   8: new 59	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: getfield 65	com/tencent/bugly/crashreport/crash/anr/b:g	Ljava/lang/String;
    //   16: invokespecial 317	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore_3
    //   20: aload_3
    //   21: invokevirtual 320	java/io/File:exists	()Z
    //   24: ifeq +181 -> 205
    //   27: aload_3
    //   28: invokevirtual 714	java/io/File:isDirectory	()Z
    //   31: ifeq +174 -> 205
    //   34: aload_3
    //   35: invokevirtual 718	java/io/File:listFiles	()[Ljava/io/File;
    //   38: astore 4
    //   40: aload 4
    //   42: ifnull +162 -> 204
    //   45: aload 4
    //   47: arraylength
    //   48: ifne +6 -> 54
    //   51: goto +153 -> 204
    //   54: ldc_w 720
    //   57: invokevirtual 723	java/lang/String:length	()I
    //   60: istore 5
    //   62: aload 4
    //   64: arraylength
    //   65: istore 6
    //   67: iconst_0
    //   68: istore 7
    //   70: iconst_0
    //   71: istore 8
    //   73: iload 7
    //   75: iload 6
    //   77: if_icmpge +100 -> 177
    //   80: aload 4
    //   82: iload 7
    //   84: aaload
    //   85: astore 11
    //   87: aload 11
    //   89: invokevirtual 724	java/io/File:getName	()Ljava/lang/String;
    //   92: astore 12
    //   94: aload 12
    //   96: ldc_w 720
    //   99: invokevirtual 645	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   102: ifeq +69 -> 171
    //   105: aload 12
    //   107: ldc_w 624
    //   110: invokevirtual 183	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   113: istore 14
    //   115: iload 14
    //   117: ifle +43 -> 160
    //   120: aload 12
    //   122: iload 5
    //   124: iload 14
    //   126: invokevirtual 187	java/lang/String:substring	(II)Ljava/lang/String;
    //   129: invokestatic 728	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   132: lstore 15
    //   134: lload 15
    //   136: lload_1
    //   137: lcmp
    //   138: iflt +22 -> 160
    //   141: goto +30 -> 171
    //   144: ldc_w 730
    //   147: iconst_1
    //   148: anewarray 4	java/lang/Object
    //   151: dup
    //   152: iconst_0
    //   153: aload 12
    //   155: aastore
    //   156: invokestatic 432	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   159: pop
    //   160: aload 11
    //   162: invokevirtual 733	java/io/File:delete	()Z
    //   165: ifeq +6 -> 171
    //   168: iinc 8 1
    //   171: iinc 7 1
    //   174: goto -101 -> 73
    //   177: iconst_1
    //   178: anewarray 4	java/lang/Object
    //   181: astore 9
    //   183: aload 9
    //   185: iconst_0
    //   186: iload 8
    //   188: invokestatic 546	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   191: aastore
    //   192: ldc_w 735
    //   195: aload 9
    //   197: invokestatic 516	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   200: pop
    //   201: goto +4 -> 205
    //   204: return
    //   205: return
    //
    // Exception table:
    //   from	to	target	type
    //   105	134	144	java/lang/Throwable
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.anr.b
 * JD-Core Version:    0.6.1
 */