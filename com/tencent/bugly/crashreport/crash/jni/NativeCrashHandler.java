package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;

public class NativeCrashHandler
  implements com.tencent.bugly.crashreport.a
{
  private static NativeCrashHandler a;
  private static boolean l;
  private static boolean m;
  private final Context b;
  private final com.tencent.bugly.crashreport.common.info.a c;
  private final w d;
  private NativeExceptionHandler e;
  private String f;
  private final boolean g;
  private boolean h;
  private boolean i;
  private boolean j;
  private boolean k;
  private com.tencent.bugly.crashreport.crash.b n;

  // ERROR //
  @android.annotation.SuppressLint({"SdCardPath"})
  private NativeCrashHandler(Context paramContext, com.tencent.bugly.crashreport.common.info.a parama, com.tencent.bugly.crashreport.crash.b paramb, w paramw, boolean paramBoolean, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 39	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield 41	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:h	Z
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 47	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:k	Z
    //   24: aload_0
    //   25: aload_1
    //   26: invokestatic 52	com/tencent/bugly/proguard/z:a	(Landroid/content/Context;)Landroid/content/Context;
    //   29: putfield 54	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:b	Landroid/content/Context;
    //   32: aload 6
    //   34: invokestatic 57	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;)Z
    //   37: ifne +6 -> 43
    //   40: goto +61 -> 101
    //   43: aload_1
    //   44: ldc 59
    //   46: iconst_0
    //   47: invokevirtual 65	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   50: invokevirtual 71	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   53: astore 6
    //   55: goto +46 -> 101
    //   58: aload_1
    //   59: invokestatic 76	com/tencent/bugly/crashreport/common/info/a:a	(Landroid/content/Context;)Lcom/tencent/bugly/crashreport/common/info/a;
    //   62: getfield 78	com/tencent/bugly/crashreport/common/info/a:c	Ljava/lang/String;
    //   65: astore 7
    //   67: new 80	java/lang/StringBuilder
    //   70: dup
    //   71: ldc 82
    //   73: invokespecial 85	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   76: astore 8
    //   78: aload 8
    //   80: aload 7
    //   82: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload 8
    //   88: ldc 91
    //   90: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload 8
    //   96: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: astore 6
    //   101: aload_0
    //   102: aload_3
    //   103: putfield 96	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:n	Lcom/tencent/bugly/crashreport/crash/b;
    //   106: aload_0
    //   107: aload 6
    //   109: putfield 98	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:f	Ljava/lang/String;
    //   112: aload_0
    //   113: aload_2
    //   114: putfield 100	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:c	Lcom/tencent/bugly/crashreport/common/info/a;
    //   117: aload_0
    //   118: aload 4
    //   120: putfield 102	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:d	Lcom/tencent/bugly/proguard/w;
    //   123: aload_0
    //   124: iload 5
    //   126: putfield 104	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:g	Z
    //   129: aload_0
    //   130: new 106	com/tencent/bugly/crashreport/crash/jni/a
    //   133: dup
    //   134: aload_1
    //   135: aload_2
    //   136: aload_3
    //   137: invokestatic 111	com/tencent/bugly/crashreport/common/strategy/a:a	()Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   140: invokespecial 114	com/tencent/bugly/crashreport/crash/jni/a:<init>	(Landroid/content/Context;Lcom/tencent/bugly/crashreport/common/info/a;Lcom/tencent/bugly/crashreport/crash/b;Lcom/tencent/bugly/crashreport/common/strategy/a;)V
    //   143: putfield 116	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:e	Lcom/tencent/bugly/crashreport/crash/jni/NativeExceptionHandler;
    //   146: return
    //
    // Exception table:
    //   from	to	target	type
    //   32	55	58	java/lang/Throwable
  }

  // ERROR //
  private void a(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   6: ifeq +16 -> 22
    //   9: ldc 120
    //   11: iconst_0
    //   12: anewarray 4	java/lang/Object
    //   15: invokestatic 125	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   18: pop
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   26: istore_3
    //   27: iload_3
    //   28: ifeq +285 -> 313
    //   31: aload_0
    //   32: aload_0
    //   33: getfield 98	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:f	Ljava/lang/String;
    //   36: iload_1
    //   37: iconst_1
    //   38: invokevirtual 129	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:regist	(Ljava/lang/String;ZI)Ljava/lang/String;
    //   41: astore 21
    //   43: aload 21
    //   45: ifnull +616 -> 661
    //   48: ldc 131
    //   50: iconst_0
    //   51: anewarray 4	java/lang/Object
    //   54: invokestatic 133	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   57: pop
    //   58: ldc 135
    //   60: iconst_1
    //   61: anewarray 4	java/lang/Object
    //   64: dup
    //   65: iconst_0
    //   66: aload 21
    //   68: aastore
    //   69: invokestatic 137	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   72: pop
    //   73: ldc 139
    //   75: ldc 141
    //   77: ldc 143
    //   79: invokevirtual 149	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   82: astore 24
    //   84: ldc 151
    //   86: ldc 141
    //   88: ldc 143
    //   90: invokevirtual 149	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   93: astore 25
    //   95: aload 21
    //   97: ldc 141
    //   99: ldc 143
    //   101: invokevirtual 149	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   104: astore 26
    //   106: aload 26
    //   108: invokevirtual 155	java/lang/String:length	()I
    //   111: iconst_2
    //   112: if_icmpne +38 -> 150
    //   115: new 80	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   122: astore 27
    //   124: aload 27
    //   126: aload 26
    //   128: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: pop
    //   132: aload 27
    //   134: ldc 158
    //   136: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload 27
    //   142: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: astore 26
    //   147: goto +40 -> 187
    //   150: aload 26
    //   152: invokevirtual 155	java/lang/String:length	()I
    //   155: iconst_1
    //   156: if_icmpne +31 -> 187
    //   159: new 80	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   166: astore 27
    //   168: aload 27
    //   170: aload 26
    //   172: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload 27
    //   178: ldc 160
    //   180: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: goto -44 -> 140
    //   187: aload 26
    //   189: invokestatic 166	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   192: aload 24
    //   194: invokestatic 166	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   197: if_icmplt +7 -> 204
    //   200: iconst_1
    //   201: putstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   204: aload 26
    //   206: invokestatic 166	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   209: aload 25
    //   211: invokestatic 166	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   214: if_icmplt +10 -> 224
    //   217: iconst_1
    //   218: putstatic 170	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:m	Z
    //   221: goto +4 -> 225
    //   224: pop
    //   225: getstatic 170	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:m	Z
    //   228: ifeq +16 -> 244
    //   231: ldc 172
    //   233: iconst_0
    //   234: anewarray 4	java/lang/Object
    //   237: invokestatic 133	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   240: pop
    //   241: goto +13 -> 254
    //   244: ldc 174
    //   246: iconst_0
    //   247: anewarray 4	java/lang/Object
    //   250: invokestatic 125	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   253: pop
    //   254: getstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   257: ifeq +16 -> 273
    //   260: ldc 176
    //   262: iconst_0
    //   263: anewarray 4	java/lang/Object
    //   266: invokestatic 133	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   269: pop
    //   270: goto +13 -> 283
    //   273: ldc 178
    //   275: iconst_0
    //   276: anewarray 4	java/lang/Object
    //   279: invokestatic 125	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   282: pop
    //   283: aload_0
    //   284: getfield 100	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:c	Lcom/tencent/bugly/crashreport/common/info/a;
    //   287: aload 21
    //   289: putfield 180	com/tencent/bugly/crashreport/common/info/a:n	Ljava/lang/String;
    //   292: aload_0
    //   293: iconst_1
    //   294: putfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   297: aload_0
    //   298: monitorexit
    //   299: return
    //   300: ldc 182
    //   302: iconst_0
    //   303: anewarray 4	java/lang/Object
    //   306: invokestatic 137	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   309: pop
    //   310: goto +351 -> 661
    //   313: aload_0
    //   314: getfield 41	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:h	Z
    //   317: istore 4
    //   319: iload 4
    //   321: ifeq +340 -> 661
    //   324: iconst_4
    //   325: anewarray 184	java/lang/Class
    //   328: astore 5
    //   330: aload 5
    //   332: iconst_0
    //   333: ldc 145
    //   335: aastore
    //   336: aload 5
    //   338: iconst_1
    //   339: ldc 145
    //   341: aastore
    //   342: aload 5
    //   344: iconst_2
    //   345: getstatic 188	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   348: aastore
    //   349: aload 5
    //   351: iconst_3
    //   352: getstatic 188	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   355: aastore
    //   356: iconst_4
    //   357: anewarray 4	java/lang/Object
    //   360: astore 6
    //   362: aload 6
    //   364: iconst_0
    //   365: aload_0
    //   366: getfield 98	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:f	Ljava/lang/String;
    //   369: aastore
    //   370: aload 6
    //   372: iconst_1
    //   373: iconst_0
    //   374: invokestatic 193	com/tencent/bugly/crashreport/common/info/b:a	(Z)Ljava/lang/String;
    //   377: aastore
    //   378: iconst_5
    //   379: istore 7
    //   381: iload_1
    //   382: ifeq +297 -> 679
    //   385: iconst_1
    //   386: istore 8
    //   388: goto +3 -> 391
    //   391: aload 6
    //   393: iconst_2
    //   394: iload 8
    //   396: invokestatic 197	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   399: aastore
    //   400: aload 6
    //   402: iconst_3
    //   403: iconst_1
    //   404: invokestatic 197	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   407: aastore
    //   408: ldc 199
    //   410: ldc 201
    //   412: aconst_null
    //   413: aload 5
    //   415: aload 6
    //   417: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   420: checkcast 145	java/lang/String
    //   423: astore 9
    //   425: aload 9
    //   427: ifnonnull +81 -> 508
    //   430: iconst_3
    //   431: anewarray 184	java/lang/Class
    //   434: astore 17
    //   436: aload 17
    //   438: iconst_0
    //   439: ldc 145
    //   441: aastore
    //   442: aload 17
    //   444: iconst_1
    //   445: ldc 145
    //   447: aastore
    //   448: aload 17
    //   450: iconst_2
    //   451: getstatic 188	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   454: aastore
    //   455: iconst_3
    //   456: anewarray 4	java/lang/Object
    //   459: astore 18
    //   461: aload 18
    //   463: iconst_0
    //   464: aload_0
    //   465: getfield 98	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:f	Ljava/lang/String;
    //   468: aastore
    //   469: aload 18
    //   471: iconst_1
    //   472: iconst_0
    //   473: invokestatic 193	com/tencent/bugly/crashreport/common/info/b:a	(Z)Ljava/lang/String;
    //   476: aastore
    //   477: invokestatic 207	com/tencent/bugly/crashreport/common/info/a:b	()Lcom/tencent/bugly/crashreport/common/info/a;
    //   480: pop
    //   481: aload 18
    //   483: iconst_2
    //   484: invokestatic 210	com/tencent/bugly/crashreport/common/info/a:L	()I
    //   487: invokestatic 197	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   490: aastore
    //   491: ldc 199
    //   493: ldc 212
    //   495: aconst_null
    //   496: aload 17
    //   498: aload 18
    //   500: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   503: checkcast 145	java/lang/String
    //   506: astore 9
    //   508: aload 9
    //   510: ifnull +151 -> 661
    //   513: aload_0
    //   514: iconst_1
    //   515: putfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   518: invokestatic 207	com/tencent/bugly/crashreport/common/info/a:b	()Lcom/tencent/bugly/crashreport/common/info/a;
    //   521: aload 9
    //   523: putfield 180	com/tencent/bugly/crashreport/common/info/a:n	Ljava/lang/String;
    //   526: ldc 199
    //   528: ldc 214
    //   530: aconst_null
    //   531: iconst_1
    //   532: anewarray 184	java/lang/Class
    //   535: dup
    //   536: iconst_0
    //   537: ldc 145
    //   539: aastore
    //   540: iconst_1
    //   541: anewarray 4	java/lang/Object
    //   544: dup
    //   545: iconst_0
    //   546: aload 9
    //   548: aastore
    //   549: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   552: checkcast 216	java/lang/Boolean
    //   555: astore 10
    //   557: aload 10
    //   559: ifnull +11 -> 570
    //   562: aload 10
    //   564: invokevirtual 220	java/lang/Boolean:booleanValue	()Z
    //   567: putstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   570: iconst_1
    //   571: anewarray 184	java/lang/Class
    //   574: astore 11
    //   576: aload 11
    //   578: iconst_0
    //   579: getstatic 221	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   582: aastore
    //   583: iconst_1
    //   584: anewarray 4	java/lang/Object
    //   587: astore 12
    //   589: aload 12
    //   591: iconst_0
    //   592: iconst_1
    //   593: invokestatic 224	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   596: aastore
    //   597: ldc 199
    //   599: ldc 226
    //   601: aconst_null
    //   602: aload 11
    //   604: aload 12
    //   606: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   609: pop
    //   610: iload_1
    //   611: ifeq +6 -> 617
    //   614: iconst_1
    //   615: istore 7
    //   617: iconst_1
    //   618: anewarray 184	java/lang/Class
    //   621: astore 14
    //   623: aload 14
    //   625: iconst_0
    //   626: getstatic 188	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   629: aastore
    //   630: iconst_1
    //   631: anewarray 4	java/lang/Object
    //   634: astore 15
    //   636: aload 15
    //   638: iconst_0
    //   639: iload 7
    //   641: invokestatic 197	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   644: aastore
    //   645: ldc 199
    //   647: ldc 228
    //   649: aconst_null
    //   650: aload 14
    //   652: aload 15
    //   654: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   657: pop
    //   658: aload_0
    //   659: monitorexit
    //   660: return
    //   661: aload_0
    //   662: iconst_0
    //   663: putfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   666: aload_0
    //   667: iconst_0
    //   668: putfield 41	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:h	Z
    //   671: aload_0
    //   672: monitorexit
    //   673: return
    //   674: astore_2
    //   675: aload_0
    //   676: monitorexit
    //   677: aload_2
    //   678: athrow
    //   679: iconst_5
    //   680: istore 8
    //   682: goto -291 -> 391
    //
    // Exception table:
    //   from	to	target	type
    //   187	224	224	java/lang/Throwable
    //   31	184	300	java/lang/Throwable
    //   224	297	300	java/lang/Throwable
    //   324	658	661	java/lang/Throwable
    //   2	19	674	finally
    //   22	27	674	finally
    //   31	184	674	finally
    //   187	224	674	finally
    //   224	297	674	finally
    //   300	319	674	finally
    //   324	658	674	finally
    //   661	671	674	finally
  }

  // ERROR //
  private boolean a(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   4: ifeq +40 -> 44
    //   7: getstatic 170	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:m	Z
    //   10: ifne +6 -> 16
    //   13: goto +31 -> 44
    //   16: aload_0
    //   17: iload_1
    //   18: aload_2
    //   19: invokevirtual 235	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:setNativeInfo	(ILjava/lang/String;)V
    //   22: iconst_1
    //   23: ireturn
    //   24: astore_3
    //   25: aload_3
    //   26: invokestatic 238	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   29: ifne +7 -> 36
    //   32: aload_3
    //   33: invokevirtual 241	java/lang/Throwable:printStackTrace	()V
    //   36: iconst_0
    //   37: ireturn
    //   38: iconst_0
    //   39: putstatic 170	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:m	Z
    //   42: iconst_0
    //   43: ireturn
    //   44: iconst_0
    //   45: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   16	22	24	java/lang/Throwable
    //   16	22	38	java/lang/UnsatisfiedLinkError
  }

  private static boolean a(String paramString, boolean paramBoolean)
  {
    boolean bool1 = true;
    boolean bool2;
    try
    {
      Object[] arrayOfObject2 = new Object[bool1];
      arrayOfObject2[0] = paramString;
      x.a("[Native] Trying to load so: %s", arrayOfObject2);
      if (paramBoolean)
        System.load(paramString);
      else
        System.loadLibrary(paramString);
      try
      {
        Object[] arrayOfObject3 = new Object[bool1];
        arrayOfObject3[0] = paramString;
        x.a("[Native] Successfully loaded SO: %s", arrayOfObject3);
      }
      catch (Throwable localThrowable1)
      {
        bool2 = true;
      }
    }
    catch (Throwable localThrowable2)
    {
      bool2 = false;
    }
    x.d(localThrowable2.getMessage(), new Object[0]);
    Object[] arrayOfObject1 = new Object[bool1];
    arrayOfObject1[0] = paramString;
    x.d("[Native] Failed to load so: %s", arrayOfObject1);
    bool1 = bool2;
    return bool1;
  }

  // ERROR //
  private void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   6: ifne +17 -> 23
    //   9: ldc_w 262
    //   12: iconst_0
    //   13: anewarray 4	java/lang/Object
    //   16: invokestatic 125	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   19: pop
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: aload_0
    //   24: invokevirtual 265	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:unregist	()Ljava/lang/String;
    //   27: ifnull +33 -> 60
    //   30: ldc_w 267
    //   33: iconst_0
    //   34: anewarray 4	java/lang/Object
    //   37: invokestatic 133	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   40: pop
    //   41: aload_0
    //   42: iconst_0
    //   43: putfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: ldc_w 269
    //   52: iconst_0
    //   53: anewarray 4	java/lang/Object
    //   56: invokestatic 137	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   59: pop
    //   60: iconst_1
    //   61: anewarray 184	java/lang/Class
    //   64: astore 4
    //   66: aload 4
    //   68: iconst_0
    //   69: getstatic 221	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   72: aastore
    //   73: iconst_1
    //   74: anewarray 4	java/lang/Object
    //   77: astore 5
    //   79: aload 5
    //   81: iconst_0
    //   82: iconst_0
    //   83: invokestatic 224	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   86: aastore
    //   87: ldc 199
    //   89: ldc 226
    //   91: aconst_null
    //   92: aload 4
    //   94: aload 5
    //   96: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: aload_0
    //   101: iconst_0
    //   102: putfield 45	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:j	Z
    //   105: ldc_w 267
    //   108: iconst_0
    //   109: anewarray 4	java/lang/Object
    //   112: invokestatic 133	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   115: pop
    //   116: aload_0
    //   117: monitorexit
    //   118: return
    //   119: ldc_w 269
    //   122: iconst_0
    //   123: anewarray 4	java/lang/Object
    //   126: invokestatic 137	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   129: pop
    //   130: aload_0
    //   131: iconst_0
    //   132: putfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   135: aload_0
    //   136: iconst_0
    //   137: putfield 41	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:h	Z
    //   140: aload_0
    //   141: monitorexit
    //   142: return
    //   143: astore_1
    //   144: aload_0
    //   145: monitorexit
    //   146: aload_1
    //   147: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   23	46	49	java/lang/Throwable
    //   60	116	119	java/lang/Throwable
    //   2	20	143	finally
    //   23	46	143	finally
    //   49	60	143	finally
    //   60	116	143	finally
    //   119	140	143	finally
  }

  private void b(boolean paramBoolean)
  {
    if (paramBoolean)
      try
      {
        startNativeMonitor();
        return;
      }
      finally
      {
        break label24;
      }
    b();
    return;
    label24: throw localObject;
  }

  private void c(boolean paramBoolean)
  {
    try
    {
      if (this.k != paramBoolean)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(paramBoolean);
        x.a("user change native %b", arrayOfObject);
        this.k = paramBoolean;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static NativeCrashHandler getInstance()
  {
    try
    {
      NativeCrashHandler localNativeCrashHandler = a;
      return localNativeCrashHandler;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static NativeCrashHandler getInstance(Context paramContext, com.tencent.bugly.crashreport.common.info.a parama, com.tencent.bugly.crashreport.crash.b paramb, com.tencent.bugly.crashreport.common.strategy.a parama1, w paramw, boolean paramBoolean, String paramString)
  {
    try
    {
      if (a == null)
      {
        NativeCrashHandler localNativeCrashHandler1 = new NativeCrashHandler(paramContext, parama, paramb, paramw, paramBoolean, paramString);
        a = localNativeCrashHandler1;
      }
      NativeCrashHandler localNativeCrashHandler2 = a;
      return localNativeCrashHandler2;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  protected final void a()
  {
    // Byte code:
    //   0: invokestatic 289	com/tencent/bugly/proguard/z:b	()J
    //   3: getstatic 294	com/tencent/bugly/crashreport/crash/c:g	J
    //   6: lsub
    //   7: lstore_1
    //   8: new 67	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: getfield 98	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:f	Ljava/lang/String;
    //   16: invokespecial 295	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore_3
    //   20: aload_3
    //   21: invokevirtual 298	java/io/File:exists	()Z
    //   24: ifeq +181 -> 205
    //   27: aload_3
    //   28: invokevirtual 301	java/io/File:isDirectory	()Z
    //   31: ifeq +174 -> 205
    //   34: aload_3
    //   35: invokevirtual 305	java/io/File:listFiles	()[Ljava/io/File;
    //   38: astore 4
    //   40: aload 4
    //   42: ifnull +162 -> 204
    //   45: aload 4
    //   47: arraylength
    //   48: ifne +6 -> 54
    //   51: goto +153 -> 204
    //   54: ldc_w 307
    //   57: invokevirtual 155	java/lang/String:length	()I
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
    //   89: invokevirtual 310	java/io/File:getName	()Ljava/lang/String;
    //   92: astore 12
    //   94: aload 12
    //   96: ldc_w 307
    //   99: invokevirtual 313	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   102: ifeq +69 -> 171
    //   105: aload 12
    //   107: ldc_w 315
    //   110: invokevirtual 318	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   113: istore 14
    //   115: iload 14
    //   117: ifle +43 -> 160
    //   120: aload 12
    //   122: iload 5
    //   124: iload 14
    //   126: invokevirtual 322	java/lang/String:substring	(II)Ljava/lang/String;
    //   129: invokestatic 328	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   132: lstore 15
    //   134: lload 15
    //   136: lload_1
    //   137: lcmp
    //   138: iflt +22 -> 160
    //   141: goto +30 -> 171
    //   144: ldc_w 330
    //   147: iconst_1
    //   148: anewarray 4	java/lang/Object
    //   151: dup
    //   152: iconst_0
    //   153: aload 12
    //   155: aastore
    //   156: invokestatic 332	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   159: pop
    //   160: aload 11
    //   162: invokevirtual 335	java/io/File:delete	()Z
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
    //   188: invokestatic 197	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   191: aastore
    //   192: ldc_w 337
    //   195: aload 9
    //   197: invokestatic 137	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   200: pop
    //   201: goto +4 -> 205
    //   204: return
    //   205: return
    //
    // Exception table:
    //   from	to	target	type
    //   105	134	144	java/lang/Throwable
  }

  // ERROR //
  public boolean appendLogToNative(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:h	Z
    //   4: ifne +12 -> 16
    //   7: aload_0
    //   8: getfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   11: ifne +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: getstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   19: ifne +5 -> 24
    //   22: iconst_0
    //   23: ireturn
    //   24: aload_1
    //   25: ifnull +118 -> 143
    //   28: aload_2
    //   29: ifnull +114 -> 143
    //   32: aload_3
    //   33: ifnonnull +6 -> 39
    //   36: goto +107 -> 143
    //   39: aload_0
    //   40: getfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   43: ifeq +11 -> 54
    //   46: aload_0
    //   47: aload_1
    //   48: aload_2
    //   49: aload_3
    //   50: invokevirtual 342	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:appendNativeLog	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   53: ireturn
    //   54: ldc 199
    //   56: ldc_w 343
    //   59: aconst_null
    //   60: iconst_3
    //   61: anewarray 184	java/lang/Class
    //   64: dup
    //   65: iconst_0
    //   66: ldc 145
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: ldc 145
    //   73: aastore
    //   74: dup
    //   75: iconst_2
    //   76: ldc 145
    //   78: aastore
    //   79: iconst_3
    //   80: anewarray 4	java/lang/Object
    //   83: dup
    //   84: iconst_0
    //   85: aload_1
    //   86: aastore
    //   87: dup
    //   88: iconst_1
    //   89: aload_2
    //   90: aastore
    //   91: dup
    //   92: iconst_2
    //   93: aload_3
    //   94: aastore
    //   95: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   98: checkcast 216	java/lang/Boolean
    //   101: astore 5
    //   103: aload 5
    //   105: ifnull +13 -> 118
    //   108: aload 5
    //   110: invokevirtual 220	java/lang/Boolean:booleanValue	()Z
    //   113: istore 6
    //   115: iload 6
    //   117: ireturn
    //   118: iconst_0
    //   119: ireturn
    //   120: astore 4
    //   122: aload 4
    //   124: invokestatic 238	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   127: ifne +8 -> 135
    //   130: aload 4
    //   132: invokevirtual 241	java/lang/Throwable:printStackTrace	()V
    //   135: iconst_0
    //   136: ireturn
    //   137: iconst_0
    //   138: putstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   141: iconst_0
    //   142: ireturn
    //   143: iconst_0
    //   144: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   39	115	120	java/lang/Throwable
    //   39	115	137	java/lang/UnsatisfiedLinkError
  }

  protected native boolean appendNativeLog(String paramString1, String paramString2, String paramString3);

  protected native boolean appendWholeNativeLog(String paramString);

  public boolean filterSigabrtSysLog()
  {
    return a(998, "true");
  }

  public String getDumpFilePath()
  {
    try
    {
      String str = this.f;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public NativeExceptionHandler getNativeExceptionHandler()
  {
    return this.e;
  }

  protected native String getNativeKeyValueList();

  protected native String getNativeLog();

  public boolean isUserOpened()
  {
    try
    {
      boolean bool = this.k;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onStrategyChanged(StrategyBean paramStrategyBean)
  {
    if (paramStrategyBean != null);
    try
    {
      if (paramStrategyBean.g != this.j)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Boolean.valueOf(paramStrategyBean.g);
        x.d("server native changed to %b", arrayOfObject2);
      }
      if ((com.tencent.bugly.crashreport.common.strategy.a.a().c().g) && (this.k))
      {
        bool = true;
        if (bool != this.j)
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Boolean.valueOf(bool);
          x.a("native changed to %b", arrayOfObject1);
          b(bool);
        }
        return;
        label106: Object localObject1;
        throw localObject1;
      }
    }
    finally
    {
      break label106;
      boolean bool = false;
    }
  }

  // ERROR //
  public boolean putKeyValueToNative(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:h	Z
    //   4: ifne +12 -> 16
    //   7: aload_0
    //   8: getfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   11: ifne +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: getstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   19: ifne +5 -> 24
    //   22: iconst_0
    //   23: ireturn
    //   24: aload_1
    //   25: ifnull +101 -> 126
    //   28: aload_2
    //   29: ifnonnull +6 -> 35
    //   32: goto +94 -> 126
    //   35: aload_0
    //   36: getfield 43	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:i	Z
    //   39: ifeq +10 -> 49
    //   42: aload_0
    //   43: aload_1
    //   44: aload_2
    //   45: invokevirtual 374	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:putNativeKeyValue	(Ljava/lang/String;Ljava/lang/String;)Z
    //   48: ireturn
    //   49: ldc 199
    //   51: ldc_w 375
    //   54: aconst_null
    //   55: iconst_2
    //   56: anewarray 184	java/lang/Class
    //   59: dup
    //   60: iconst_0
    //   61: ldc 145
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: ldc 145
    //   68: aastore
    //   69: iconst_2
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: aload_1
    //   76: aastore
    //   77: dup
    //   78: iconst_1
    //   79: aload_2
    //   80: aastore
    //   81: invokestatic 204	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   84: checkcast 216	java/lang/Boolean
    //   87: astore 4
    //   89: aload 4
    //   91: ifnull +13 -> 104
    //   94: aload 4
    //   96: invokevirtual 220	java/lang/Boolean:booleanValue	()Z
    //   99: istore 5
    //   101: iload 5
    //   103: ireturn
    //   104: iconst_0
    //   105: ireturn
    //   106: astore_3
    //   107: aload_3
    //   108: invokestatic 238	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   111: ifne +7 -> 118
    //   114: aload_3
    //   115: invokevirtual 241	java/lang/Throwable:printStackTrace	()V
    //   118: iconst_0
    //   119: ireturn
    //   120: iconst_0
    //   121: putstatic 168	com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler:l	Z
    //   124: iconst_0
    //   125: ireturn
    //   126: iconst_0
    //   127: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   35	101	106	java/lang/Throwable
    //   35	101	120	java/lang/UnsatisfiedLinkError
  }

  protected native boolean putNativeKeyValue(String paramString1, String paramString2);

  protected native String regist(String paramString, boolean paramBoolean, int paramInt);

  protected native String removeNativeKeyValue(String paramString);

  public void setDumpFilePath(String paramString)
  {
    try
    {
      this.f = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean setNativeAppChannel(String paramString)
  {
    return a(12, paramString);
  }

  public boolean setNativeAppPackage(String paramString)
  {
    return a(13, paramString);
  }

  public boolean setNativeAppVersion(String paramString)
  {
    return a(10, paramString);
  }

  protected native void setNativeInfo(int paramInt, String paramString);

  public boolean setNativeIsAppForeground(boolean paramBoolean)
  {
    String str;
    if (paramBoolean)
      str = "true";
    else
      str = "false";
    return a(14, str);
  }

  public boolean setNativeLaunchTime(long paramLong)
  {
    try
    {
      boolean bool = a(15, String.valueOf(paramLong));
      return bool;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      if (!x.a(localNumberFormatException))
        localNumberFormatException.printStackTrace();
    }
    return false;
  }

  public boolean setNativeUserId(String paramString)
  {
    return a(11, paramString);
  }

  public void setUserOpened(boolean paramBoolean)
  {
    while (true)
    {
      try
      {
        c(paramBoolean);
        bool = isUserOpened();
        com.tencent.bugly.crashreport.common.strategy.a locala = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (locala != null)
        {
          if ((!bool) || (!locala.c().g))
            break label86;
          bool = true;
        }
        if (bool != this.j)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Boolean.valueOf(bool);
          x.a("native changed to %b", arrayOfObject);
          b(bool);
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
      label86: boolean bool = false;
    }
  }

  public void startNativeMonitor()
  {
    while (true)
    {
      String str;
      try
      {
        if ((!this.i) && (!this.h))
        {
          localObject2 = "Bugly";
          boolean bool1 = true ^ z.a(this.c.m);
          str = this.c.m;
          if (!bool1)
          {
            this.c.getClass();
            this.i = a((String)localObject2, bool1);
            if (!this.i)
            {
              boolean bool2 = this.h;
              if (!bool2)
                return;
            }
            a(this.g);
            this.d.a(new Runnable()
            {
              public final void run()
              {
                if (!z.a(NativeCrashHandler.a(NativeCrashHandler.this), "native_record_lock", 10000L))
                {
                  x.a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
                  return;
                }
                try
                {
                  NativeCrashHandler.this.setNativeAppVersion(NativeCrashHandler.b(NativeCrashHandler.this).j);
                  NativeCrashHandler.this.setNativeAppChannel(NativeCrashHandler.b(NativeCrashHandler.this).l);
                  NativeCrashHandler.this.setNativeAppPackage(NativeCrashHandler.b(NativeCrashHandler.this).c);
                  NativeCrashHandler.this.setNativeUserId(NativeCrashHandler.b(NativeCrashHandler.this).g());
                  NativeCrashHandler.this.setNativeIsAppForeground(NativeCrashHandler.b(NativeCrashHandler.this).a());
                  NativeCrashHandler.this.setNativeLaunchTime(NativeCrashHandler.b(NativeCrashHandler.this).a);
                }
                catch (Throwable localThrowable)
                {
                  if (!x.a(localThrowable))
                    localThrowable.printStackTrace();
                }
                CrashDetailBean localCrashDetailBean = b.a(NativeCrashHandler.a(NativeCrashHandler.this), NativeCrashHandler.c(NativeCrashHandler.this), NativeCrashHandler.d(NativeCrashHandler.this));
                if (localCrashDetailBean != null)
                {
                  x.a("[Native] Get crash from native record.", new Object[0]);
                  if (!NativeCrashHandler.e(NativeCrashHandler.this).a(localCrashDetailBean))
                    NativeCrashHandler.e(NativeCrashHandler.this).a(localCrashDetailBean, 3000L, false);
                  b.a(false, NativeCrashHandler.c(NativeCrashHandler.this));
                }
                NativeCrashHandler.this.a();
                z.b(NativeCrashHandler.a(NativeCrashHandler.this), "native_record_lock");
              }
            });
          }
        }
        else
        {
          a(this.g);
          return;
        }
      }
      finally
      {
      }
      Object localObject2 = str;
    }
  }

  protected native void testCrash();

  public void testNativeCrash()
  {
    if (!this.i)
    {
      x.d("[Native] Bugly SO file has not been load.", new Object[0]);
      return;
    }
    testCrash();
  }

  protected native String unregist();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler
 * JD-Core Version:    0.6.1
 */