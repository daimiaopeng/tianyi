package com.tencent.bugly.proguard;

import android.content.Context;
import java.util.Map;
import java.util.UUID;

public final class v
  implements Runnable
{
  private int a = 2;
  private int b = 30000;
  private final Context c;
  private final int d;
  private final byte[] e;
  private final com.tencent.bugly.crashreport.common.info.a f;
  private final com.tencent.bugly.crashreport.common.strategy.a g;
  private final s h;
  private final u i;
  private final int j;
  private final t k;
  private final t l;
  private String m = null;
  private final String n;
  private final Map<String, String> o;
  private int p = 0;
  private long q = 0L;
  private long r = 0L;
  private boolean s = true;
  private boolean t = false;

  public v(Context paramContext, int paramInt1, int paramInt2, byte[] paramArrayOfByte, String paramString1, String paramString2, t paramt, boolean paramBoolean1, int paramInt3, int paramInt4, boolean paramBoolean2, Map<String, String> paramMap)
  {
    this.c = paramContext;
    this.f = com.tencent.bugly.crashreport.common.info.a.a(paramContext);
    this.e = paramArrayOfByte;
    this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
    this.h = s.a(paramContext);
    this.i = u.a();
    this.j = paramInt1;
    this.m = paramString1;
    this.n = paramString2;
    this.k = paramt;
    this.l = null;
    this.s = paramBoolean1;
    this.d = paramInt2;
    if (paramInt3 > 0)
      this.a = paramInt3;
    if (paramInt4 > 0)
      this.b = paramInt4;
    this.t = paramBoolean2;
    this.o = paramMap;
  }

  public v(Context paramContext, int paramInt1, int paramInt2, byte[] paramArrayOfByte, String paramString1, String paramString2, t paramt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext, paramInt1, paramInt2, paramArrayOfByte, paramString1, paramString2, paramt, paramBoolean1, 2, 30000, paramBoolean2, null);
  }

  private static String a(String paramString)
  {
    if (z.a(paramString))
      return paramString;
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = UUID.randomUUID().toString();
      String str = String.format("%s?aid=%s", arrayOfObject);
      return str;
    }
    catch (Throwable localThrowable)
    {
      x.a(localThrowable);
    }
    return paramString;
  }

  private void a(an paraman, boolean paramBoolean, int paramInt1, String paramString, int paramInt2)
  {
    int i1 = this.d;
    if (i1 != 630)
    {
      if (i1 != 640)
      {
        if (i1 == 830)
          break label57;
        if (i1 != 840)
        {
          str = String.valueOf(this.d);
          break label61;
        }
      }
      str = "userinfo";
      break label61;
    }
    label57: String str = "crash";
    label61: if (paramBoolean)
    {
      x.a("[Upload] Success: %s", new Object[] { str });
    }
    else
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = str;
      arrayOfObject[2] = paramString;
      x.e("[Upload] Failed to upload(%d) %s: %s", arrayOfObject);
      if (this.s)
        this.i.a(paramInt2, null);
    }
    if (this.q + this.r > 0L)
    {
      long l1 = this.i.a(this.t) + this.q + this.r;
      this.i.a(l1, this.t);
    }
    if (this.k != null)
    {
      t localt2 = this.k;
      localt2.a(paramBoolean);
    }
    if (this.l != null)
    {
      t localt1 = this.l;
      localt1.a(paramBoolean);
    }
  }

  private static boolean a(an paraman, com.tencent.bugly.crashreport.common.info.a parama, com.tencent.bugly.crashreport.common.strategy.a parama1)
  {
    if (paraman == null)
    {
      x.d("resp == null!", new Object[0]);
      return false;
    }
    if (paraman.a != 0)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Byte.valueOf(paraman.a);
      x.e("resp result error %d", arrayOfObject3);
      return false;
    }
    try
    {
      if ((!z.a(paraman.d)) && (!com.tencent.bugly.crashreport.common.info.a.b().i().equals(paraman.d)))
      {
        p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_ip", paraman.d.getBytes("UTF-8"), null, true);
        parama.d(paraman.d);
      }
      if ((!z.a(paraman.f)) && (!com.tencent.bugly.crashreport.common.info.a.b().j().equals(paraman.f)))
      {
        p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_imei", paraman.f.getBytes("UTF-8"), null, true);
        parama.e(paraman.f);
      }
    }
    catch (Throwable localThrowable)
    {
      x.a(localThrowable);
    }
    parama.i = paraman.e;
    if (paraman.b == 510)
    {
      if (paraman.c == null)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paraman.b);
        x.e("[Upload] Strategy data is null. Response cmd: %d", arrayOfObject2);
        return false;
      }
      ap localap = (ap)a.a(paraman.c, ap.class);
      if (localap == null)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(paraman.b);
        x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", arrayOfObject1);
        return false;
      }
      parama1.a(localap);
    }
    return true;
  }

  public final void a(long paramLong)
  {
    this.p = (1 + this.p);
    this.q = (paramLong + this.q);
  }

  public final void b(long paramLong)
  {
    this.r = (paramLong + this.r);
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield 52	com/tencent/bugly/proguard/v:p	I
    //   5: aload_0
    //   6: lconst_0
    //   7: putfield 54	com/tencent/bugly/proguard/v:q	J
    //   10: aload_0
    //   11: lconst_0
    //   12: putfield 56	com/tencent/bugly/proguard/v:r	J
    //   15: aload_0
    //   16: getfield 71	com/tencent/bugly/proguard/v:e	[B
    //   19: astore_2
    //   20: aload_0
    //   21: getfield 62	com/tencent/bugly/proguard/v:c	Landroid/content/Context;
    //   24: invokestatic 259	com/tencent/bugly/crashreport/common/info/b:f	(Landroid/content/Context;)Ljava/lang/String;
    //   27: ifnonnull +15 -> 42
    //   30: aload_0
    //   31: aconst_null
    //   32: iconst_0
    //   33: iconst_0
    //   34: ldc_w 261
    //   37: iconst_0
    //   38: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   41: return
    //   42: aload_2
    //   43: ifnull +1753 -> 1796
    //   46: aload_2
    //   47: arraylength
    //   48: ifne +6 -> 54
    //   51: goto +1745 -> 1796
    //   54: aload_0
    //   55: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   58: aload_0
    //   59: getfield 60	com/tencent/bugly/proguard/v:t	Z
    //   62: invokevirtual 167	com/tencent/bugly/proguard/u:a	(Z)J
    //   65: lstore_3
    //   66: lload_3
    //   67: aload_2
    //   68: arraylength
    //   69: i2l
    //   70: ladd
    //   71: ldc2_w 264
    //   74: lcmp
    //   75: iflt +80 -> 155
    //   78: iconst_2
    //   79: anewarray 4	java/lang/Object
    //   82: astore 81
    //   84: aload 81
    //   86: iconst_0
    //   87: lload_3
    //   88: invokestatic 270	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   91: aastore
    //   92: aload 81
    //   94: iconst_1
    //   95: ldc2_w 264
    //   98: invokestatic 270	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   101: aastore
    //   102: ldc_w 272
    //   105: aload 81
    //   107: invokestatic 161	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   110: pop
    //   111: new 274	java/lang/StringBuilder
    //   114: dup
    //   115: ldc_w 276
    //   118: invokespecial 278	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   121: astore 83
    //   123: aload 83
    //   125: ldc2_w 279
    //   128: invokevirtual 284	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   131: pop
    //   132: aload 83
    //   134: ldc_w 286
    //   137: invokevirtual 289	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload_0
    //   142: aconst_null
    //   143: iconst_0
    //   144: iconst_0
    //   145: aload 83
    //   147: invokevirtual 290	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: iconst_0
    //   151: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   154: return
    //   155: iconst_1
    //   156: anewarray 4	java/lang/Object
    //   159: astore 5
    //   161: aload 5
    //   163: iconst_0
    //   164: aload_0
    //   165: getfield 102	com/tencent/bugly/proguard/v:d	I
    //   168: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   171: aastore
    //   172: ldc_w 292
    //   175: aload 5
    //   177: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   180: pop
    //   181: aload_0
    //   182: getfield 62	com/tencent/bugly/proguard/v:c	Landroid/content/Context;
    //   185: ifnull +1599 -> 1784
    //   188: aload_0
    //   189: getfield 69	com/tencent/bugly/proguard/v:f	Lcom/tencent/bugly/crashreport/common/info/a;
    //   192: ifnull +1592 -> 1784
    //   195: aload_0
    //   196: getfield 78	com/tencent/bugly/proguard/v:g	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   199: ifnull +1585 -> 1784
    //   202: aload_0
    //   203: getfield 85	com/tencent/bugly/proguard/v:h	Lcom/tencent/bugly/proguard/s;
    //   206: ifnonnull +6 -> 212
    //   209: goto +1575 -> 1784
    //   212: aload_0
    //   213: getfield 78	com/tencent/bugly/proguard/v:g	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   216: invokevirtual 297	com/tencent/bugly/crashreport/common/strategy/a:c	()Lcom/tencent/bugly/crashreport/common/strategy/StrategyBean;
    //   219: astore 7
    //   221: aload 7
    //   223: ifnonnull +15 -> 238
    //   226: aload_0
    //   227: aconst_null
    //   228: iconst_0
    //   229: iconst_0
    //   230: ldc_w 299
    //   233: iconst_0
    //   234: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   237: return
    //   238: new 301	java/util/HashMap
    //   241: dup
    //   242: invokespecial 302	java/util/HashMap:<init>	()V
    //   245: astore 8
    //   247: aload 8
    //   249: ldc_w 304
    //   252: aload_0
    //   253: getfield 69	com/tencent/bugly/proguard/v:f	Lcom/tencent/bugly/crashreport/common/info/a;
    //   256: invokevirtual 306	com/tencent/bugly/crashreport/common/info/a:f	()Ljava/lang/String;
    //   259: invokeinterface 312 3 0
    //   264: pop
    //   265: aload 8
    //   267: ldc_w 314
    //   270: aload_0
    //   271: getfield 69	com/tencent/bugly/proguard/v:f	Lcom/tencent/bugly/crashreport/common/info/a;
    //   274: getfield 316	com/tencent/bugly/crashreport/common/info/a:c	Ljava/lang/String;
    //   277: invokeinterface 312 3 0
    //   282: pop
    //   283: aload 8
    //   285: ldc_w 318
    //   288: aload_0
    //   289: getfield 69	com/tencent/bugly/proguard/v:f	Lcom/tencent/bugly/crashreport/common/info/a;
    //   292: getfield 320	com/tencent/bugly/crashreport/common/info/a:j	Ljava/lang/String;
    //   295: invokeinterface 312 3 0
    //   300: pop
    //   301: aload_0
    //   302: getfield 104	com/tencent/bugly/proguard/v:o	Ljava/util/Map;
    //   305: ifnull +14 -> 319
    //   308: aload 8
    //   310: aload_0
    //   311: getfield 104	com/tencent/bugly/proguard/v:o	Ljava/util/Map;
    //   314: invokeinterface 324 2 0
    //   319: aload_0
    //   320: getfield 58	com/tencent/bugly/proguard/v:s	Z
    //   323: ifeq +151 -> 474
    //   326: aload 8
    //   328: ldc_w 326
    //   331: aload_0
    //   332: getfield 102	com/tencent/bugly/proguard/v:d	I
    //   335: invokestatic 328	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   338: invokeinterface 312 3 0
    //   343: pop
    //   344: aload 8
    //   346: ldc_w 330
    //   349: iconst_1
    //   350: invokestatic 333	java/lang/Byte:toString	(B)Ljava/lang/String;
    //   353: invokeinterface 312 3 0
    //   358: pop
    //   359: aload_0
    //   360: getfield 69	com/tencent/bugly/proguard/v:f	Lcom/tencent/bugly/crashreport/common/info/a;
    //   363: invokevirtual 337	java/lang/Object:getClass	()Ljava/lang/Class;
    //   366: pop
    //   367: aload 8
    //   369: ldc_w 339
    //   372: ldc_w 341
    //   375: invokeinterface 312 3 0
    //   380: pop
    //   381: aload 8
    //   383: ldc_w 343
    //   386: aload 7
    //   388: getfield 347	com/tencent/bugly/crashreport/common/strategy/StrategyBean:p	J
    //   391: invokestatic 350	java/lang/Long:toString	(J)Ljava/lang/String;
    //   394: invokeinterface 312 3 0
    //   399: pop
    //   400: aload_0
    //   401: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   404: aload 8
    //   406: invokevirtual 353	com/tencent/bugly/proguard/u:a	(Ljava/util/Map;)Z
    //   409: ifne +15 -> 424
    //   412: aload_0
    //   413: aconst_null
    //   414: iconst_0
    //   415: iconst_0
    //   416: ldc_w 355
    //   419: iconst_0
    //   420: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   423: return
    //   424: aload_2
    //   425: iconst_2
    //   426: invokestatic 358	com/tencent/bugly/proguard/z:a	([BI)[B
    //   429: astore 80
    //   431: aload 80
    //   433: ifnonnull +15 -> 448
    //   436: aload_0
    //   437: aconst_null
    //   438: iconst_0
    //   439: iconst_0
    //   440: ldc_w 360
    //   443: iconst_0
    //   444: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   447: return
    //   448: aload_0
    //   449: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   452: aload 80
    //   454: invokevirtual 363	com/tencent/bugly/proguard/u:a	([B)[B
    //   457: astore_2
    //   458: aload_2
    //   459: ifnonnull +15 -> 474
    //   462: aload_0
    //   463: aconst_null
    //   464: iconst_0
    //   465: iconst_0
    //   466: ldc_w 365
    //   469: iconst_0
    //   470: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   473: return
    //   474: aload_0
    //   475: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   478: aload_0
    //   479: getfield 94	com/tencent/bugly/proguard/v:j	I
    //   482: invokestatic 371	java/lang/System:currentTimeMillis	()J
    //   485: invokevirtual 374	com/tencent/bugly/proguard/u:a	(IJ)V
    //   488: aload_0
    //   489: getfield 98	com/tencent/bugly/proguard/v:k	Lcom/tencent/bugly/proguard/t;
    //   492: ifnull +13 -> 505
    //   495: aload_0
    //   496: getfield 98	com/tencent/bugly/proguard/v:k	Lcom/tencent/bugly/proguard/t;
    //   499: pop
    //   500: aload_0
    //   501: getfield 102	com/tencent/bugly/proguard/v:d	I
    //   504: pop
    //   505: aload_0
    //   506: getfield 100	com/tencent/bugly/proguard/v:l	Lcom/tencent/bugly/proguard/t;
    //   509: ifnull +13 -> 522
    //   512: aload_0
    //   513: getfield 100	com/tencent/bugly/proguard/v:l	Lcom/tencent/bugly/proguard/t;
    //   516: pop
    //   517: aload_0
    //   518: getfield 102	com/tencent/bugly/proguard/v:d	I
    //   521: pop
    //   522: aload_0
    //   523: getfield 50	com/tencent/bugly/proguard/v:m	Ljava/lang/String;
    //   526: astore 12
    //   528: iconst_0
    //   529: istore 13
    //   531: iconst_0
    //   532: istore 14
    //   534: iconst_m1
    //   535: istore 15
    //   537: iload 13
    //   539: iconst_1
    //   540: iadd
    //   541: istore 16
    //   543: iload 13
    //   545: aload_0
    //   546: getfield 46	com/tencent/bugly/proguard/v:a	I
    //   549: if_icmpge +1222 -> 1771
    //   552: iload 16
    //   554: iconst_1
    //   555: if_icmple +73 -> 628
    //   558: iconst_1
    //   559: anewarray 4	java/lang/Object
    //   562: astore 67
    //   564: aload 67
    //   566: iconst_0
    //   567: iload 16
    //   569: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   572: aastore
    //   573: ldc_w 376
    //   576: aload 67
    //   578: invokestatic 180	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   581: pop
    //   582: aload_0
    //   583: getfield 48	com/tencent/bugly/proguard/v:b	I
    //   586: i2l
    //   587: invokestatic 378	com/tencent/bugly/proguard/z:b	(J)V
    //   590: iload 16
    //   592: aload_0
    //   593: getfield 46	com/tencent/bugly/proguard/v:a	I
    //   596: if_icmpne +32 -> 628
    //   599: iconst_1
    //   600: anewarray 4	java/lang/Object
    //   603: astore 69
    //   605: aload 69
    //   607: iconst_0
    //   608: aload_0
    //   609: getfield 96	com/tencent/bugly/proguard/v:n	Ljava/lang/String;
    //   612: aastore
    //   613: ldc_w 380
    //   616: aload 69
    //   618: invokestatic 180	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   621: pop
    //   622: aload_0
    //   623: getfield 96	com/tencent/bugly/proguard/v:n	Ljava/lang/String;
    //   626: astore 12
    //   628: iconst_1
    //   629: anewarray 4	java/lang/Object
    //   632: astore 17
    //   634: aload 17
    //   636: iconst_0
    //   637: aload_2
    //   638: arraylength
    //   639: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   642: aastore
    //   643: ldc_w 382
    //   646: aload 17
    //   648: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   651: pop
    //   652: aload_0
    //   653: getfield 58	com/tencent/bugly/proguard/v:s	Z
    //   656: ifeq +10 -> 666
    //   659: aload 12
    //   661: invokestatic 384	com/tencent/bugly/proguard/v:a	(Ljava/lang/String;)Ljava/lang/String;
    //   664: astore 12
    //   666: iconst_4
    //   667: anewarray 4	java/lang/Object
    //   670: astore 19
    //   672: aload 19
    //   674: iconst_0
    //   675: aload 12
    //   677: aastore
    //   678: aload 19
    //   680: iconst_1
    //   681: aload_0
    //   682: getfield 102	com/tencent/bugly/proguard/v:d	I
    //   685: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   688: aastore
    //   689: aload 19
    //   691: iconst_2
    //   692: invokestatic 390	android/os/Process:myPid	()I
    //   695: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   698: aastore
    //   699: aload 19
    //   701: iconst_3
    //   702: invokestatic 393	android/os/Process:myTid	()I
    //   705: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   708: aastore
    //   709: ldc_w 395
    //   712: aload 19
    //   714: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   717: pop
    //   718: aload_0
    //   719: getfield 85	com/tencent/bugly/proguard/v:h	Lcom/tencent/bugly/proguard/s;
    //   722: aload 12
    //   724: aload_2
    //   725: aload_0
    //   726: aload 8
    //   728: invokevirtual 398	com/tencent/bugly/proguard/s:a	(Ljava/lang/String;[BLcom/tencent/bugly/proguard/v;Ljava/util/Map;)[B
    //   731: astore 21
    //   733: aload 21
    //   735: ifnonnull +36 -> 771
    //   738: iconst_2
    //   739: anewarray 4	java/lang/Object
    //   742: astore 65
    //   744: aload 65
    //   746: iconst_0
    //   747: iconst_1
    //   748: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   751: aastore
    //   752: aload 65
    //   754: iconst_1
    //   755: ldc_w 400
    //   758: aastore
    //   759: ldc_w 402
    //   762: aload 65
    //   764: invokestatic 161	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   767: pop
    //   768: goto +1053 -> 1821
    //   771: aload_0
    //   772: getfield 85	com/tencent/bugly/proguard/v:h	Lcom/tencent/bugly/proguard/s;
    //   775: getfield 404	com/tencent/bugly/proguard/s:a	Ljava/util/Map;
    //   778: astore 22
    //   780: aload_0
    //   781: getfield 58	com/tencent/bugly/proguard/v:s	Z
    //   784: ifeq +1063 -> 1847
    //   787: aload 22
    //   789: ifnull +149 -> 938
    //   792: aload 22
    //   794: invokeinterface 407 1 0
    //   799: ifne +6 -> 805
    //   802: goto +136 -> 938
    //   805: aload 22
    //   807: ldc_w 409
    //   810: invokeinterface 412 2 0
    //   815: ifne +23 -> 838
    //   818: ldc_w 414
    //   821: iconst_1
    //   822: anewarray 4	java/lang/Object
    //   825: dup
    //   826: iconst_0
    //   827: ldc_w 409
    //   830: aastore
    //   831: invokestatic 180	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   834: pop
    //   835: goto +996 -> 1831
    //   838: aload 22
    //   840: ldc_w 416
    //   843: invokeinterface 412 2 0
    //   848: ifne +23 -> 871
    //   851: ldc_w 414
    //   854: astore 35
    //   856: iconst_1
    //   857: anewarray 4	java/lang/Object
    //   860: dup
    //   861: iconst_0
    //   862: ldc_w 416
    //   865: aastore
    //   866: astore 36
    //   868: goto +81 -> 949
    //   871: aload 22
    //   873: ldc_w 416
    //   876: invokeinterface 420 2 0
    //   881: checkcast 129	java/lang/String
    //   884: astore 61
    //   886: aload 61
    //   888: ldc_w 422
    //   891: invokevirtual 426	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   894: ifne +22 -> 916
    //   897: ldc_w 428
    //   900: iconst_1
    //   901: anewarray 4	java/lang/Object
    //   904: dup
    //   905: iconst_0
    //   906: aload 61
    //   908: aastore
    //   909: invokestatic 180	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   912: pop
    //   913: goto +918 -> 1831
    //   916: ldc_w 430
    //   919: iconst_1
    //   920: anewarray 4	java/lang/Object
    //   923: dup
    //   924: iconst_0
    //   925: aload 61
    //   927: aastore
    //   928: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   931: pop
    //   932: iconst_1
    //   933: istore 38
    //   935: goto +25 -> 960
    //   938: ldc_w 432
    //   941: astore 35
    //   943: iconst_0
    //   944: anewarray 4	java/lang/Object
    //   947: astore 36
    //   949: aload 35
    //   951: aload 36
    //   953: invokestatic 180	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   956: pop
    //   957: goto +874 -> 1831
    //   960: iload 38
    //   962: ifne +174 -> 1136
    //   965: iconst_2
    //   966: anewarray 4	java/lang/Object
    //   969: astore 52
    //   971: aload 52
    //   973: iconst_0
    //   974: invokestatic 390	android/os/Process:myPid	()I
    //   977: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   980: aastore
    //   981: aload 52
    //   983: iconst_1
    //   984: invokestatic 393	android/os/Process:myTid	()I
    //   987: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   990: aastore
    //   991: ldc_w 434
    //   994: aload 52
    //   996: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   999: pop
    //   1000: iconst_2
    //   1001: anewarray 4	java/lang/Object
    //   1004: astore 54
    //   1006: aload 54
    //   1008: iconst_0
    //   1009: iconst_1
    //   1010: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1013: aastore
    //   1014: aload 54
    //   1016: iconst_1
    //   1017: ldc_w 436
    //   1020: aastore
    //   1021: ldc_w 402
    //   1024: aload 54
    //   1026: invokestatic 161	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1029: pop
    //   1030: aload 22
    //   1032: ifnull +86 -> 1118
    //   1035: aload 22
    //   1037: invokeinterface 440 1 0
    //   1042: invokeinterface 446 1 0
    //   1047: astore 57
    //   1049: aload 57
    //   1051: invokeinterface 452 1 0
    //   1056: ifeq +62 -> 1118
    //   1059: aload 57
    //   1061: invokeinterface 456 1 0
    //   1066: checkcast 458	java/util/Map$Entry
    //   1069: astore 58
    //   1071: iconst_2
    //   1072: anewarray 4	java/lang/Object
    //   1075: astore 59
    //   1077: aload 59
    //   1079: iconst_0
    //   1080: aload 58
    //   1082: invokeinterface 461 1 0
    //   1087: aastore
    //   1088: aload 59
    //   1090: iconst_1
    //   1091: aload 58
    //   1093: invokeinterface 464 1 0
    //   1098: aastore
    //   1099: ldc_w 466
    //   1102: aload 59
    //   1104: invokestatic 133	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1107: iconst_0
    //   1108: anewarray 4	java/lang/Object
    //   1111: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1114: pop
    //   1115: goto -66 -> 1049
    //   1118: ldc_w 436
    //   1121: iconst_0
    //   1122: anewarray 4	java/lang/Object
    //   1125: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1128: pop
    //   1129: iload 16
    //   1131: istore 13
    //   1133: goto +692 -> 1825
    //   1136: aload 22
    //   1138: ldc_w 409
    //   1141: invokeinterface 420 2 0
    //   1146: checkcast 129	java/lang/String
    //   1149: invokestatic 470	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1152: istore 23
    //   1154: iconst_3
    //   1155: anewarray 4	java/lang/Object
    //   1158: astore 44
    //   1160: aload 44
    //   1162: iconst_0
    //   1163: iload 23
    //   1165: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1168: aastore
    //   1169: aload 44
    //   1171: iconst_1
    //   1172: invokestatic 390	android/os/Process:myPid	()I
    //   1175: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1178: aastore
    //   1179: aload 44
    //   1181: iconst_2
    //   1182: invokestatic 393	android/os/Process:myTid	()I
    //   1185: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1188: aastore
    //   1189: ldc_w 472
    //   1192: aload 44
    //   1194: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1197: pop
    //   1198: iload 23
    //   1200: ifeq +246 -> 1446
    //   1203: iload 23
    //   1205: iconst_2
    //   1206: if_icmpne +143 -> 1349
    //   1209: aload_0
    //   1210: getfield 54	com/tencent/bugly/proguard/v:q	J
    //   1213: aload_0
    //   1214: getfield 56	com/tencent/bugly/proguard/v:r	J
    //   1217: ladd
    //   1218: lconst_0
    //   1219: lcmp
    //   1220: ifle +39 -> 1259
    //   1223: aload_0
    //   1224: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   1227: aload_0
    //   1228: getfield 60	com/tencent/bugly/proguard/v:t	Z
    //   1231: invokevirtual 167	com/tencent/bugly/proguard/u:a	(Z)J
    //   1234: aload_0
    //   1235: getfield 54	com/tencent/bugly/proguard/v:q	J
    //   1238: ladd
    //   1239: aload_0
    //   1240: getfield 56	com/tencent/bugly/proguard/v:r	J
    //   1243: ladd
    //   1244: lstore 50
    //   1246: aload_0
    //   1247: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   1250: lload 50
    //   1252: aload_0
    //   1253: getfield 60	com/tencent/bugly/proguard/v:t	Z
    //   1256: invokevirtual 170	com/tencent/bugly/proguard/u:a	(JZ)V
    //   1259: aload_0
    //   1260: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   1263: iload 23
    //   1265: aconst_null
    //   1266: invokevirtual 164	com/tencent/bugly/proguard/u:a	(ILcom/tencent/bugly/proguard/an;)V
    //   1269: iconst_2
    //   1270: anewarray 4	java/lang/Object
    //   1273: astore 48
    //   1275: aload 48
    //   1277: iconst_0
    //   1278: invokestatic 390	android/os/Process:myPid	()I
    //   1281: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1284: aastore
    //   1285: aload 48
    //   1287: iconst_1
    //   1288: invokestatic 393	android/os/Process:myTid	()I
    //   1291: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1294: aastore
    //   1295: ldc_w 474
    //   1298: aload 48
    //   1300: invokestatic 152	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1303: pop
    //   1304: aload_0
    //   1305: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   1308: aload_0
    //   1309: getfield 94	com/tencent/bugly/proguard/v:j	I
    //   1312: aload_0
    //   1313: getfield 102	com/tencent/bugly/proguard/v:d	I
    //   1316: aload_0
    //   1317: getfield 71	com/tencent/bugly/proguard/v:e	[B
    //   1320: aload_0
    //   1321: getfield 50	com/tencent/bugly/proguard/v:m	Ljava/lang/String;
    //   1324: aload_0
    //   1325: getfield 96	com/tencent/bugly/proguard/v:n	Ljava/lang/String;
    //   1328: aload_0
    //   1329: getfield 98	com/tencent/bugly/proguard/v:k	Lcom/tencent/bugly/proguard/t;
    //   1332: aload_0
    //   1333: getfield 46	com/tencent/bugly/proguard/v:a	I
    //   1336: aload_0
    //   1337: getfield 48	com/tencent/bugly/proguard/v:b	I
    //   1340: iconst_1
    //   1341: aload_0
    //   1342: getfield 104	com/tencent/bugly/proguard/v:o	Ljava/util/Map;
    //   1345: invokevirtual 477	com/tencent/bugly/proguard/u:a	(II[BLjava/lang/String;Ljava/lang/String;Lcom/tencent/bugly/proguard/t;IIZLjava/util/Map;)V
    //   1348: return
    //   1349: new 274	java/lang/StringBuilder
    //   1352: dup
    //   1353: ldc_w 479
    //   1356: invokespecial 278	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1359: astore 46
    //   1361: aload 46
    //   1363: iload 23
    //   1365: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1368: pop
    //   1369: aload_0
    //   1370: aconst_null
    //   1371: iconst_0
    //   1372: iconst_1
    //   1373: aload 46
    //   1375: invokevirtual 290	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1378: iload 23
    //   1380: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1383: return
    //   1384: new 274	java/lang/StringBuilder
    //   1387: dup
    //   1388: ldc_w 484
    //   1391: invokespecial 278	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1394: astore 39
    //   1396: aload 39
    //   1398: iload 15
    //   1400: invokestatic 328	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   1403: invokevirtual 289	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1406: pop
    //   1407: aload 39
    //   1409: invokevirtual 290	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1412: astore 41
    //   1414: iconst_2
    //   1415: anewarray 4	java/lang/Object
    //   1418: astore 42
    //   1420: aload 42
    //   1422: iconst_0
    //   1423: iconst_1
    //   1424: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1427: aastore
    //   1428: aload 42
    //   1430: iconst_1
    //   1431: aload 41
    //   1433: aastore
    //   1434: ldc_w 402
    //   1437: aload 42
    //   1439: invokestatic 161	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1442: pop
    //   1443: goto +378 -> 1821
    //   1446: iconst_1
    //   1447: anewarray 4	java/lang/Object
    //   1450: astore 24
    //   1452: aload 24
    //   1454: iconst_0
    //   1455: aload 21
    //   1457: arraylength
    //   1458: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1461: aastore
    //   1462: ldc_w 486
    //   1465: aload 24
    //   1467: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1470: pop
    //   1471: aload_0
    //   1472: getfield 58	com/tencent/bugly/proguard/v:s	Z
    //   1475: ifeq +150 -> 1625
    //   1478: aload 21
    //   1480: arraylength
    //   1481: ifne +91 -> 1572
    //   1484: aload 22
    //   1486: invokeinterface 440 1 0
    //   1491: invokeinterface 446 1 0
    //   1496: astore 31
    //   1498: aload 31
    //   1500: invokeinterface 452 1 0
    //   1505: ifeq +55 -> 1560
    //   1508: aload 31
    //   1510: invokeinterface 456 1 0
    //   1515: checkcast 458	java/util/Map$Entry
    //   1518: astore 32
    //   1520: iconst_2
    //   1521: anewarray 4	java/lang/Object
    //   1524: astore 33
    //   1526: aload 33
    //   1528: iconst_0
    //   1529: aload 32
    //   1531: invokeinterface 461 1 0
    //   1536: aastore
    //   1537: aload 33
    //   1539: iconst_1
    //   1540: aload 32
    //   1542: invokeinterface 464 1 0
    //   1547: aastore
    //   1548: ldc_w 488
    //   1551: aload 33
    //   1553: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1556: pop
    //   1557: goto -59 -> 1498
    //   1560: aload_0
    //   1561: aconst_null
    //   1562: iconst_0
    //   1563: iconst_1
    //   1564: ldc_w 490
    //   1567: iconst_0
    //   1568: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1571: return
    //   1572: aload_0
    //   1573: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   1576: aload 21
    //   1578: invokevirtual 492	com/tencent/bugly/proguard/u:b	([B)[B
    //   1581: astore 30
    //   1583: aload 30
    //   1585: ifnonnull +15 -> 1600
    //   1588: aload_0
    //   1589: aconst_null
    //   1590: iconst_0
    //   1591: iconst_1
    //   1592: ldc_w 494
    //   1595: iconst_0
    //   1596: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1599: return
    //   1600: aload 30
    //   1602: iconst_2
    //   1603: invokestatic 496	com/tencent/bugly/proguard/z:b	([BI)[B
    //   1606: astore 21
    //   1608: aload 21
    //   1610: ifnonnull +15 -> 1625
    //   1613: aload_0
    //   1614: aconst_null
    //   1615: iconst_0
    //   1616: iconst_1
    //   1617: ldc_w 498
    //   1620: iconst_0
    //   1621: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1624: return
    //   1625: aload 21
    //   1627: aload_0
    //   1628: getfield 58	com/tencent/bugly/proguard/v:s	Z
    //   1631: invokestatic 501	com/tencent/bugly/proguard/a:a	([BZ)Lcom/tencent/bugly/proguard/an;
    //   1634: astore 26
    //   1636: aload 26
    //   1638: ifnonnull +15 -> 1653
    //   1641: aload_0
    //   1642: aconst_null
    //   1643: iconst_0
    //   1644: iconst_1
    //   1645: ldc_w 503
    //   1648: iconst_0
    //   1649: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1652: return
    //   1653: aload_0
    //   1654: getfield 58	com/tencent/bugly/proguard/v:s	Z
    //   1657: ifeq +14 -> 1671
    //   1660: aload_0
    //   1661: getfield 92	com/tencent/bugly/proguard/v:i	Lcom/tencent/bugly/proguard/u;
    //   1664: iload 23
    //   1666: aload 26
    //   1668: invokevirtual 164	com/tencent/bugly/proguard/u:a	(ILcom/tencent/bugly/proguard/an;)V
    //   1671: iconst_2
    //   1672: anewarray 4	java/lang/Object
    //   1675: astore 27
    //   1677: aload 27
    //   1679: iconst_0
    //   1680: aload 26
    //   1682: getfield 236	com/tencent/bugly/proguard/an:b	I
    //   1685: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1688: aastore
    //   1689: aload 26
    //   1691: getfield 238	com/tencent/bugly/proguard/an:c	[B
    //   1694: ifnonnull +9 -> 1703
    //   1697: iconst_0
    //   1698: istore 28
    //   1700: goto +11 -> 1711
    //   1703: aload 26
    //   1705: getfield 238	com/tencent/bugly/proguard/an:c	[B
    //   1708: arraylength
    //   1709: istore 28
    //   1711: aload 27
    //   1713: iconst_1
    //   1714: iload 28
    //   1716: invokestatic 157	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1719: aastore
    //   1720: ldc_w 505
    //   1723: aload 27
    //   1725: invokestatic 294	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1728: pop
    //   1729: aload 26
    //   1731: aload_0
    //   1732: getfield 69	com/tencent/bugly/proguard/v:f	Lcom/tencent/bugly/crashreport/common/info/a;
    //   1735: aload_0
    //   1736: getfield 78	com/tencent/bugly/proguard/v:g	Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   1739: invokestatic 507	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;Lcom/tencent/bugly/crashreport/common/info/a;Lcom/tencent/bugly/crashreport/common/strategy/a;)Z
    //   1742: ifne +16 -> 1758
    //   1745: aload_0
    //   1746: aload 26
    //   1748: iconst_0
    //   1749: iconst_2
    //   1750: ldc_w 509
    //   1753: iconst_0
    //   1754: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1757: return
    //   1758: aload_0
    //   1759: aload 26
    //   1761: iconst_1
    //   1762: iconst_2
    //   1763: ldc_w 511
    //   1766: iconst_0
    //   1767: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1770: return
    //   1771: aload_0
    //   1772: aconst_null
    //   1773: iconst_0
    //   1774: iload 14
    //   1776: ldc_w 513
    //   1779: iconst_0
    //   1780: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1783: return
    //   1784: aload_0
    //   1785: aconst_null
    //   1786: iconst_0
    //   1787: iconst_0
    //   1788: ldc_w 515
    //   1791: iconst_0
    //   1792: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1795: return
    //   1796: aload_0
    //   1797: aconst_null
    //   1798: iconst_0
    //   1799: iconst_0
    //   1800: ldc_w 517
    //   1803: iconst_0
    //   1804: invokespecial 263	com/tencent/bugly/proguard/v:a	(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;I)V
    //   1807: return
    //   1808: astore_1
    //   1809: aload_1
    //   1810: invokestatic 138	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   1813: ifne +7 -> 1820
    //   1816: aload_1
    //   1817: invokevirtual 520	java/lang/Throwable:printStackTrace	()V
    //   1820: return
    //   1821: iload 16
    //   1823: istore 13
    //   1825: iconst_1
    //   1826: istore 14
    //   1828: goto -1291 -> 537
    //   1831: iconst_0
    //   1832: istore 38
    //   1834: goto -874 -> 960
    //   1837: iload 23
    //   1839: istore 15
    //   1841: goto -457 -> 1384
    //   1844: goto -460 -> 1384
    //   1847: iload 15
    //   1849: istore 23
    //   1851: goto -405 -> 1446
    //
    // Exception table:
    //   from	to	target	type
    //   0	1129	1808	java/lang/Throwable
    //   1209	1807	1808	java/lang/Throwable
    //   1154	1198	1837	java/lang/Throwable
    //   1136	1154	1844	java/lang/Throwable
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.v
 * JD-Core Version:    0.6.1
 */