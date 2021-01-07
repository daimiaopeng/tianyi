package com.tencent.bugly.proguard;

import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class a
{
  protected HashMap<String, HashMap<String, byte[]>> a = new HashMap();
  protected String b;
  i c;
  private HashMap<String, Object> d;

  a()
  {
    new HashMap();
    this.d = new HashMap();
    this.b = "GBK";
    this.c = new i();
  }

  public static ag a(int paramInt)
  {
    if (paramInt == 1)
      return new af();
    if (paramInt == 3)
      return new ae();
    return null;
  }

  // ERROR //
  public static am a(android.content.Context paramContext, int paramInt, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: invokestatic 48	com/tencent/bugly/crashreport/common/info/a:b	()Lcom/tencent/bugly/crashreport/common/info/a;
    //   3: astore_3
    //   4: invokestatic 53	com/tencent/bugly/crashreport/common/strategy/a:a	()Lcom/tencent/bugly/crashreport/common/strategy/a;
    //   7: invokevirtual 56	com/tencent/bugly/crashreport/common/strategy/a:c	()Lcom/tencent/bugly/crashreport/common/strategy/StrategyBean;
    //   10: astore 4
    //   12: aload_3
    //   13: ifnull +1138 -> 1151
    //   16: aload 4
    //   18: ifnonnull +6 -> 24
    //   21: goto +1130 -> 1151
    //   24: new 58	com/tencent/bugly/proguard/am
    //   27: dup
    //   28: invokespecial 59	com/tencent/bugly/proguard/am:<init>	()V
    //   31: astore 6
    //   33: aload_3
    //   34: monitorenter
    //   35: aload 6
    //   37: iconst_1
    //   38: putfield 62	com/tencent/bugly/proguard/am:a	I
    //   41: aload 6
    //   43: aload_3
    //   44: invokevirtual 66	com/tencent/bugly/crashreport/common/info/a:f	()Ljava/lang/String;
    //   47: putfield 67	com/tencent/bugly/proguard/am:b	Ljava/lang/String;
    //   50: aload 6
    //   52: aload_3
    //   53: getfield 69	com/tencent/bugly/crashreport/common/info/a:c	Ljava/lang/String;
    //   56: putfield 70	com/tencent/bugly/proguard/am:c	Ljava/lang/String;
    //   59: aload 6
    //   61: aload_3
    //   62: getfield 73	com/tencent/bugly/crashreport/common/info/a:j	Ljava/lang/String;
    //   65: putfield 75	com/tencent/bugly/proguard/am:d	Ljava/lang/String;
    //   68: aload 6
    //   70: aload_3
    //   71: getfield 78	com/tencent/bugly/crashreport/common/info/a:l	Ljava/lang/String;
    //   74: putfield 81	com/tencent/bugly/proguard/am:e	Ljava/lang/String;
    //   77: aload_3
    //   78: invokevirtual 85	java/lang/Object:getClass	()Ljava/lang/Class;
    //   81: pop
    //   82: aload 6
    //   84: ldc 87
    //   86: putfield 89	com/tencent/bugly/proguard/am:f	Ljava/lang/String;
    //   89: aload 6
    //   91: iload_1
    //   92: putfield 92	com/tencent/bugly/proguard/am:g	I
    //   95: aload_2
    //   96: ifnonnull +1068 -> 1164
    //   99: ldc 94
    //   101: invokevirtual 100	java/lang/String:getBytes	()[B
    //   104: astore 10
    //   106: goto +3 -> 109
    //   109: aload 6
    //   111: aload 10
    //   113: putfield 104	com/tencent/bugly/proguard/am:h	[B
    //   116: aload 6
    //   118: aload_3
    //   119: getfield 106	com/tencent/bugly/crashreport/common/info/a:g	Ljava/lang/String;
    //   122: putfield 109	com/tencent/bugly/proguard/am:i	Ljava/lang/String;
    //   125: aload 6
    //   127: aload_3
    //   128: getfield 111	com/tencent/bugly/crashreport/common/info/a:h	Ljava/lang/String;
    //   131: putfield 112	com/tencent/bugly/proguard/am:j	Ljava/lang/String;
    //   134: aload 6
    //   136: new 19	java/util/HashMap
    //   139: dup
    //   140: invokespecial 20	java/util/HashMap:<init>	()V
    //   143: putfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   146: aload 6
    //   148: aload_3
    //   149: invokevirtual 118	com/tencent/bugly/crashreport/common/info/a:e	()Ljava/lang/String;
    //   152: putfield 119	com/tencent/bugly/proguard/am:l	Ljava/lang/String;
    //   155: aload 6
    //   157: aload 4
    //   159: getfield 125	com/tencent/bugly/crashreport/common/strategy/StrategyBean:p	J
    //   162: putfield 128	com/tencent/bugly/proguard/am:m	J
    //   165: aload 6
    //   167: aload_3
    //   168: invokevirtual 130	com/tencent/bugly/crashreport/common/info/a:h	()Ljava/lang/String;
    //   171: putfield 133	com/tencent/bugly/proguard/am:o	Ljava/lang/String;
    //   174: aload 6
    //   176: aload_0
    //   177: invokestatic 138	com/tencent/bugly/crashreport/common/info/b:f	(Landroid/content/Context;)Ljava/lang/String;
    //   180: putfield 140	com/tencent/bugly/proguard/am:p	Ljava/lang/String;
    //   183: aload 6
    //   185: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   188: putfield 149	com/tencent/bugly/proguard/am:q	J
    //   191: new 151	java/lang/StringBuilder
    //   194: dup
    //   195: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   198: astore 11
    //   200: aload 11
    //   202: aload_3
    //   203: invokevirtual 154	com/tencent/bugly/crashreport/common/info/a:k	()Ljava/lang/String;
    //   206: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: aload 6
    //   212: aload 11
    //   214: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: putfield 164	com/tencent/bugly/proguard/am:r	Ljava/lang/String;
    //   220: aload 6
    //   222: aload_3
    //   223: invokevirtual 166	com/tencent/bugly/crashreport/common/info/a:j	()Ljava/lang/String;
    //   226: putfield 169	com/tencent/bugly/proguard/am:s	Ljava/lang/String;
    //   229: new 151	java/lang/StringBuilder
    //   232: dup
    //   233: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   236: astore 13
    //   238: aload 13
    //   240: aload_3
    //   241: invokevirtual 171	com/tencent/bugly/crashreport/common/info/a:m	()Ljava/lang/String;
    //   244: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: pop
    //   248: aload 6
    //   250: aload 13
    //   252: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: putfield 174	com/tencent/bugly/proguard/am:t	Ljava/lang/String;
    //   258: aload 6
    //   260: aload_3
    //   261: invokevirtual 176	com/tencent/bugly/crashreport/common/info/a:l	()Ljava/lang/String;
    //   264: putfield 179	com/tencent/bugly/proguard/am:u	Ljava/lang/String;
    //   267: new 151	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   274: astore 15
    //   276: aload 15
    //   278: aload_3
    //   279: invokevirtual 182	com/tencent/bugly/crashreport/common/info/a:n	()Ljava/lang/String;
    //   282: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: aload 6
    //   288: aload 15
    //   290: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   293: putfield 185	com/tencent/bugly/proguard/am:v	Ljava/lang/String;
    //   296: aload 6
    //   298: aload 6
    //   300: getfield 140	com/tencent/bugly/proguard/am:p	Ljava/lang/String;
    //   303: putfield 188	com/tencent/bugly/proguard/am:w	Ljava/lang/String;
    //   306: aload_3
    //   307: invokevirtual 85	java/lang/Object:getClass	()Ljava/lang/Class;
    //   310: pop
    //   311: aload 6
    //   313: ldc 190
    //   315: putfield 192	com/tencent/bugly/proguard/am:n	Ljava/lang/String;
    //   318: aload 6
    //   320: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   323: astore 18
    //   325: new 151	java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   332: astore 19
    //   334: aload 19
    //   336: aload_3
    //   337: invokevirtual 195	com/tencent/bugly/crashreport/common/info/a:y	()Ljava/lang/String;
    //   340: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: pop
    //   344: aload 18
    //   346: ldc 197
    //   348: aload 19
    //   350: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   353: invokeinterface 203 3 0
    //   358: pop
    //   359: aload 6
    //   361: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   364: astore 22
    //   366: new 151	java/lang/StringBuilder
    //   369: dup
    //   370: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   373: astore 23
    //   375: aload 23
    //   377: aload_3
    //   378: invokevirtual 206	com/tencent/bugly/crashreport/common/info/a:z	()Ljava/lang/String;
    //   381: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: pop
    //   385: aload 22
    //   387: ldc 208
    //   389: aload 23
    //   391: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   394: invokeinterface 203 3 0
    //   399: pop
    //   400: aload 6
    //   402: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   405: astore 26
    //   407: new 151	java/lang/StringBuilder
    //   410: dup
    //   411: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   414: astore 27
    //   416: aload 27
    //   418: aload_3
    //   419: invokevirtual 211	com/tencent/bugly/crashreport/common/info/a:A	()Ljava/lang/String;
    //   422: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   425: pop
    //   426: aload 26
    //   428: ldc 213
    //   430: aload 27
    //   432: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   435: invokeinterface 203 3 0
    //   440: pop
    //   441: aload 6
    //   443: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   446: astore 30
    //   448: new 151	java/lang/StringBuilder
    //   451: dup
    //   452: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   455: astore 31
    //   457: aload 31
    //   459: aload_3
    //   460: getfield 216	com/tencent/bugly/crashreport/common/info/a:z	Z
    //   463: invokevirtual 219	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   466: pop
    //   467: aload 30
    //   469: ldc 221
    //   471: aload 31
    //   473: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   476: invokeinterface 203 3 0
    //   481: pop
    //   482: aload 6
    //   484: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   487: astore 34
    //   489: new 151	java/lang/StringBuilder
    //   492: dup
    //   493: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   496: astore 35
    //   498: aload 35
    //   500: aload_3
    //   501: getfield 223	com/tencent/bugly/crashreport/common/info/a:y	Z
    //   504: invokevirtual 219	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: aload 34
    //   510: ldc 225
    //   512: aload 35
    //   514: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   517: invokeinterface 203 3 0
    //   522: pop
    //   523: aload 6
    //   525: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   528: astore 38
    //   530: new 151	java/lang/StringBuilder
    //   533: dup
    //   534: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   537: astore 39
    //   539: aload 39
    //   541: aload_3
    //   542: invokevirtual 227	com/tencent/bugly/crashreport/common/info/a:u	()Ljava/lang/String;
    //   545: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: pop
    //   549: aload 38
    //   551: ldc 229
    //   553: aload 39
    //   555: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   558: invokeinterface 203 3 0
    //   563: pop
    //   564: aload_3
    //   565: getfield 232	com/tencent/bugly/crashreport/common/info/a:B	Z
    //   568: ifeq +254 -> 822
    //   571: aload 6
    //   573: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   576: astore 57
    //   578: new 151	java/lang/StringBuilder
    //   581: dup
    //   582: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   585: astore 58
    //   587: aload 58
    //   589: aload_3
    //   590: invokevirtual 235	com/tencent/bugly/crashreport/common/info/a:M	()Ljava/lang/String;
    //   593: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: pop
    //   597: aload 57
    //   599: ldc 237
    //   601: aload 58
    //   603: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   606: invokeinterface 203 3 0
    //   611: pop
    //   612: aload 6
    //   614: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   617: astore 61
    //   619: new 151	java/lang/StringBuilder
    //   622: dup
    //   623: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   626: astore 62
    //   628: aload 62
    //   630: aload_3
    //   631: invokevirtual 240	com/tencent/bugly/crashreport/common/info/a:N	()Ljava/lang/String;
    //   634: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: pop
    //   638: aload 61
    //   640: ldc 242
    //   642: aload 62
    //   644: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   647: invokeinterface 203 3 0
    //   652: pop
    //   653: aload 6
    //   655: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   658: astore 65
    //   660: new 151	java/lang/StringBuilder
    //   663: dup
    //   664: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   667: astore 66
    //   669: aload 66
    //   671: aload_3
    //   672: invokevirtual 245	com/tencent/bugly/crashreport/common/info/a:O	()Ljava/lang/String;
    //   675: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   678: pop
    //   679: aload 65
    //   681: ldc 247
    //   683: aload 66
    //   685: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   688: invokeinterface 203 3 0
    //   693: pop
    //   694: aload 6
    //   696: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   699: astore 69
    //   701: new 151	java/lang/StringBuilder
    //   704: dup
    //   705: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   708: astore 70
    //   710: aload 70
    //   712: aload_3
    //   713: invokevirtual 250	com/tencent/bugly/crashreport/common/info/a:P	()Ljava/lang/String;
    //   716: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   719: pop
    //   720: aload 69
    //   722: ldc 252
    //   724: aload 70
    //   726: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   729: invokeinterface 203 3 0
    //   734: pop
    //   735: aload 6
    //   737: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   740: astore 73
    //   742: new 151	java/lang/StringBuilder
    //   745: dup
    //   746: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   749: astore 74
    //   751: aload 74
    //   753: aload_3
    //   754: invokevirtual 255	com/tencent/bugly/crashreport/common/info/a:Q	()Ljava/lang/String;
    //   757: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: pop
    //   761: aload 73
    //   763: ldc_w 257
    //   766: aload 74
    //   768: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   771: invokeinterface 203 3 0
    //   776: pop
    //   777: aload 6
    //   779: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   782: astore 77
    //   784: new 151	java/lang/StringBuilder
    //   787: dup
    //   788: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   791: astore 78
    //   793: aload 78
    //   795: aload_3
    //   796: invokevirtual 260	com/tencent/bugly/crashreport/common/info/a:R	()J
    //   799: invokestatic 265	java/lang/Long:toString	(J)Ljava/lang/String;
    //   802: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: pop
    //   806: aload 77
    //   808: ldc_w 267
    //   811: aload 78
    //   813: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   816: invokeinterface 203 3 0
    //   821: pop
    //   822: aload 6
    //   824: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   827: astore 42
    //   829: new 151	java/lang/StringBuilder
    //   832: dup
    //   833: invokespecial 152	java/lang/StringBuilder:<init>	()V
    //   836: astore 43
    //   838: aload 43
    //   840: aload_3
    //   841: getfield 269	com/tencent/bugly/crashreport/common/info/a:k	Ljava/lang/String;
    //   844: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: pop
    //   848: aload 42
    //   850: ldc_w 271
    //   853: aload 43
    //   855: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   858: invokeinterface 203 3 0
    //   863: pop
    //   864: getstatic 276	com/tencent/bugly/b:b	Ljava/util/List;
    //   867: ifnull +75 -> 942
    //   870: getstatic 276	com/tencent/bugly/b:b	Ljava/util/List;
    //   873: invokeinterface 282 1 0
    //   878: astore 54
    //   880: aload 54
    //   882: invokeinterface 288 1 0
    //   887: ifeq +55 -> 942
    //   890: aload 54
    //   892: invokeinterface 292 1 0
    //   897: checkcast 294	com/tencent/bugly/a
    //   900: astore 55
    //   902: aload 55
    //   904: getfield 297	com/tencent/bugly/a:versionKey	Ljava/lang/String;
    //   907: ifnull -27 -> 880
    //   910: aload 55
    //   912: getfield 300	com/tencent/bugly/a:version	Ljava/lang/String;
    //   915: ifnull -35 -> 880
    //   918: aload 6
    //   920: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   923: aload 55
    //   925: getfield 297	com/tencent/bugly/a:versionKey	Ljava/lang/String;
    //   928: aload 55
    //   930: getfield 300	com/tencent/bugly/a:version	Ljava/lang/String;
    //   933: invokeinterface 203 3 0
    //   938: pop
    //   939: goto -59 -> 880
    //   942: aload 6
    //   944: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   947: ldc_w 302
    //   950: ldc_w 302
    //   953: ldc 94
    //   955: invokestatic 307	com/tencent/bugly/proguard/z:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   958: invokeinterface 203 3 0
    //   963: pop
    //   964: aload 6
    //   966: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   969: ldc_w 309
    //   972: ldc_w 309
    //   975: ldc_w 311
    //   978: invokestatic 307	com/tencent/bugly/proguard/z:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   981: invokeinterface 203 3 0
    //   986: pop
    //   987: aload_3
    //   988: monitorexit
    //   989: invokestatic 316	com/tencent/bugly/proguard/u:a	()Lcom/tencent/bugly/proguard/u;
    //   992: astore 48
    //   994: aload 48
    //   996: ifnull +56 -> 1052
    //   999: aload 48
    //   1001: getfield 318	com/tencent/bugly/proguard/u:a	Z
    //   1004: ifne +48 -> 1052
    //   1007: aload_2
    //   1008: ifnull +44 -> 1052
    //   1011: aload 6
    //   1013: aload 6
    //   1015: getfield 104	com/tencent/bugly/proguard/am:h	[B
    //   1018: iconst_2
    //   1019: iconst_1
    //   1020: aload 4
    //   1022: getfield 319	com/tencent/bugly/crashreport/common/strategy/StrategyBean:u	Ljava/lang/String;
    //   1025: invokestatic 322	com/tencent/bugly/proguard/z:a	([BIILjava/lang/String;)[B
    //   1028: putfield 104	com/tencent/bugly/proguard/am:h	[B
    //   1031: aload 6
    //   1033: getfield 104	com/tencent/bugly/proguard/am:h	[B
    //   1036: ifnonnull +16 -> 1052
    //   1039: ldc_w 324
    //   1042: iconst_0
    //   1043: anewarray 4	java/lang/Object
    //   1046: invokestatic 329	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1049: pop
    //   1050: aconst_null
    //   1051: areturn
    //   1052: aload_3
    //   1053: invokevirtual 333	com/tencent/bugly/crashreport/common/info/a:F	()Ljava/util/Map;
    //   1056: astore 49
    //   1058: aload 49
    //   1060: ifnull +110 -> 1170
    //   1063: aload 49
    //   1065: invokeinterface 337 1 0
    //   1070: invokeinterface 340 1 0
    //   1075: astore 50
    //   1077: aload 50
    //   1079: invokeinterface 288 1 0
    //   1084: ifeq +86 -> 1170
    //   1087: aload 50
    //   1089: invokeinterface 292 1 0
    //   1094: checkcast 342	java/util/Map$Entry
    //   1097: astore 51
    //   1099: aload 6
    //   1101: getfield 116	com/tencent/bugly/proguard/am:k	Ljava/util/Map;
    //   1104: aload 51
    //   1106: invokeinterface 345 1 0
    //   1111: aload 51
    //   1113: invokeinterface 348 1 0
    //   1118: invokeinterface 203 3 0
    //   1123: pop
    //   1124: goto -47 -> 1077
    //   1127: astore 8
    //   1129: aload_3
    //   1130: monitorexit
    //   1131: aload 8
    //   1133: athrow
    //   1134: astore 7
    //   1136: aload 7
    //   1138: invokestatic 351	com/tencent/bugly/proguard/x:b	(Ljava/lang/Throwable;)Z
    //   1141: ifne +8 -> 1149
    //   1144: aload 7
    //   1146: invokevirtual 354	java/lang/Throwable:printStackTrace	()V
    //   1149: aconst_null
    //   1150: areturn
    //   1151: ldc_w 356
    //   1154: iconst_0
    //   1155: anewarray 4	java/lang/Object
    //   1158: invokestatic 329	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   1161: pop
    //   1162: aconst_null
    //   1163: areturn
    //   1164: aload_2
    //   1165: astore 10
    //   1167: goto -1058 -> 109
    //   1170: aload 6
    //   1172: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   35	989	1127	finally
    //   24	35	1134	java/lang/Throwable
    //   989	1134	1134	java/lang/Throwable
  }

  public static an a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramArrayOfByte != null);
    while (true)
    {
      try
      {
        d locald = new d();
        locald.b();
        locald.a("utf-8");
        locald.a(paramArrayOfByte);
        Object localObject = locald.b("detail", new an());
        if (!an.class.isInstance(localObject))
          break label181;
        localan = (an)an.class.cast(localObject);
        if ((!paramBoolean) && (localan != null) && (localan.c != null) && (localan.c.length > 0))
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(localan.c.length);
          x.c("resp buf %d", arrayOfObject);
          localan.c = z.b(localan.c, 2, 1, StrategyBean.d);
          if (localan.c == null)
          {
            x.e("resp sbuffer error!", new Object[0]);
            return null;
          }
        }
        return localan;
      }
      catch (Throwable localThrowable)
      {
        if (!x.b(localThrowable))
          localThrowable.printStackTrace();
      }
      return null;
      label181: an localan = null;
    }
  }

  public static aq a(UserInfoBean paramUserInfoBean)
  {
    if (paramUserInfoBean == null)
      return null;
    aq localaq = new aq();
    localaq.a = paramUserInfoBean.e;
    localaq.e = paramUserInfoBean.j;
    localaq.d = paramUserInfoBean.c;
    localaq.c = paramUserInfoBean.d;
    localaq.g = com.tencent.bugly.crashreport.common.info.a.b().i();
    boolean bool;
    if (paramUserInfoBean.o == 1)
      bool = true;
    else
      bool = false;
    localaq.h = bool;
    switch (paramUserInfoBean.b)
    {
    default:
      if ((paramUserInfoBean.b < 10) || (paramUserInfoBean.b >= 20))
        break label837;
      localaq.b = (byte)paramUserInfoBean.b;
      break;
    case 4:
      localaq.b = 3;
      break;
    case 3:
      localaq.b = 2;
      break;
    case 2:
      localaq.b = 4;
      break;
    case 1:
      localaq.b = 1;
    }
    localaq.f = new HashMap();
    if (paramUserInfoBean.p >= 0)
    {
      Map localMap11 = localaq.f;
      StringBuilder localStringBuilder11 = new StringBuilder();
      localStringBuilder11.append(paramUserInfoBean.p);
      localMap11.put("C01", localStringBuilder11.toString());
    }
    if (paramUserInfoBean.q >= 0)
    {
      Map localMap10 = localaq.f;
      StringBuilder localStringBuilder10 = new StringBuilder();
      localStringBuilder10.append(paramUserInfoBean.q);
      localMap10.put("C02", localStringBuilder10.toString());
    }
    if ((paramUserInfoBean.r != null) && (paramUserInfoBean.r.size() > 0))
    {
      Iterator localIterator2 = paramUserInfoBean.r.entrySet().iterator();
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
        Map localMap9 = localaq.f;
        StringBuilder localStringBuilder9 = new StringBuilder("C03_");
        localStringBuilder9.append((String)localEntry2.getKey());
        localMap9.put(localStringBuilder9.toString(), localEntry2.getValue());
      }
    }
    if ((paramUserInfoBean.s != null) && (paramUserInfoBean.s.size() > 0))
    {
      Iterator localIterator1 = paramUserInfoBean.s.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
        Map localMap8 = localaq.f;
        StringBuilder localStringBuilder8 = new StringBuilder("C04_");
        localStringBuilder8.append((String)localEntry1.getKey());
        localMap8.put(localStringBuilder8.toString(), localEntry1.getValue());
      }
    }
    Map localMap1 = localaq.f;
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(true ^ paramUserInfoBean.l);
    localMap1.put("A36", localStringBuilder1.toString());
    Map localMap2 = localaq.f;
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(paramUserInfoBean.g);
    localMap2.put("F02", localStringBuilder2.toString());
    Map localMap3 = localaq.f;
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append(paramUserInfoBean.h);
    localMap3.put("F03", localStringBuilder3.toString());
    Map localMap4 = localaq.f;
    StringBuilder localStringBuilder4 = new StringBuilder();
    localStringBuilder4.append(paramUserInfoBean.j);
    localMap4.put("F04", localStringBuilder4.toString());
    Map localMap5 = localaq.f;
    StringBuilder localStringBuilder5 = new StringBuilder();
    localStringBuilder5.append(paramUserInfoBean.i);
    localMap5.put("F05", localStringBuilder5.toString());
    Map localMap6 = localaq.f;
    StringBuilder localStringBuilder6 = new StringBuilder();
    localStringBuilder6.append(paramUserInfoBean.m);
    localMap6.put("F06", localStringBuilder6.toString());
    Map localMap7 = localaq.f;
    StringBuilder localStringBuilder7 = new StringBuilder();
    localStringBuilder7.append(paramUserInfoBean.k);
    localMap7.put("F10", localStringBuilder7.toString());
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Byte.valueOf(localaq.b);
    arrayOfObject1[1] = Integer.valueOf(localaq.f.size());
    x.c("summary type %d vm:%d", arrayOfObject1);
    return localaq;
    label837: Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(paramUserInfoBean.b);
    x.e("unknown uinfo type %d ", arrayOfObject2);
    return null;
  }

  public static ar a(List<UserInfoBean> paramList, int paramInt)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
      if (locala == null)
        return null;
      locala.t();
      ar localar = new ar();
      localar.b = locala.d;
      localar.c = locala.h();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        aq localaq = a((UserInfoBean)localIterator1.next());
        if (localaq != null)
          localArrayList.add(localaq);
      }
      localar.d = localArrayList;
      localar.e = new HashMap();
      Map localMap1 = localar.e;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(locala.f);
      localMap1.put("A7", localStringBuilder1.toString());
      Map localMap2 = localar.e;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(locala.s());
      localMap2.put("A6", localStringBuilder2.toString());
      Map localMap3 = localar.e;
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(locala.r());
      localMap3.put("A5", localStringBuilder3.toString());
      Map localMap4 = localar.e;
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append(locala.p());
      localMap4.put("A2", localStringBuilder4.toString());
      Map localMap5 = localar.e;
      StringBuilder localStringBuilder5 = new StringBuilder();
      localStringBuilder5.append(locala.p());
      localMap5.put("A1", localStringBuilder5.toString());
      Map localMap6 = localar.e;
      StringBuilder localStringBuilder6 = new StringBuilder();
      localStringBuilder6.append(locala.h);
      localMap6.put("A24", localStringBuilder6.toString());
      Map localMap7 = localar.e;
      StringBuilder localStringBuilder7 = new StringBuilder();
      localStringBuilder7.append(locala.q());
      localMap7.put("A17", localStringBuilder7.toString());
      Map localMap8 = localar.e;
      StringBuilder localStringBuilder8 = new StringBuilder();
      localStringBuilder8.append(locala.w());
      localMap8.put("A15", localStringBuilder8.toString());
      Map localMap9 = localar.e;
      StringBuilder localStringBuilder9 = new StringBuilder();
      localStringBuilder9.append(locala.x());
      localMap9.put("A13", localStringBuilder9.toString());
      Map localMap10 = localar.e;
      StringBuilder localStringBuilder10 = new StringBuilder();
      localStringBuilder10.append(locala.v);
      localMap10.put("F08", localStringBuilder10.toString());
      Map localMap11 = localar.e;
      StringBuilder localStringBuilder11 = new StringBuilder();
      localStringBuilder11.append(locala.w);
      localMap11.put("F09", localStringBuilder11.toString());
      Map localMap12 = locala.G();
      if ((localMap12 != null) && (localMap12.size() > 0))
      {
        Iterator localIterator2 = localMap12.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator2.next();
          Map localMap13 = localar.e;
          StringBuilder localStringBuilder12 = new StringBuilder("C04_");
          localStringBuilder12.append((String)localEntry.getKey());
          localMap13.put(localStringBuilder12.toString(), localEntry.getValue());
        }
      }
      switch (paramInt)
      {
      default:
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        x.e("unknown up type %d ", arrayOfObject);
        return null;
      case 2:
        localar.a = 2;
        break;
      case 1:
        localar.a = 1;
      }
      return localar;
    }
    return null;
  }

  public static <T extends k> T a(byte[] paramArrayOfByte, Class<T> paramClass)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
      try
      {
        k localk = (k)paramClass.newInstance();
        i locali = new i(paramArrayOfByte);
        locali.a("utf-8");
        localk.a(locali);
        return localk;
      }
      catch (Throwable localThrowable)
      {
        if (!x.b(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    return null;
  }

  public static String a(ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      String str2 = (String)paramArrayList.get(i);
      if ((!str2.equals("java.lang.Integer")) && (!str2.equals("int")))
      {
        if ((!str2.equals("java.lang.Boolean")) && (!str2.equals("boolean")))
        {
          if ((!str2.equals("java.lang.Byte")) && (!str2.equals("byte")))
          {
            if ((!str2.equals("java.lang.Double")) && (!str2.equals("double")))
            {
              if ((!str2.equals("java.lang.Float")) && (!str2.equals("float")))
              {
                if ((!str2.equals("java.lang.Long")) && (!str2.equals("long")))
                {
                  if ((!str2.equals("java.lang.Short")) && (!str2.equals("short")))
                  {
                    if (str2.equals("java.lang.Character"))
                      throw new IllegalArgumentException("can not support java.lang.Character");
                    if (str2.equals("java.lang.String"))
                      str2 = "string";
                    else if (str2.equals("java.util.List"))
                      str2 = "list";
                    else if (str2.equals("java.util.Map"))
                      str2 = "map";
                  }
                  else
                  {
                    str2 = "short";
                  }
                }
                else
                  str2 = "int64";
              }
              else
                str2 = "float";
            }
            else
              str2 = "double";
          }
          else
            str2 = "char";
        }
        else
          str2 = "bool";
      }
      else
        str2 = "int32";
      paramArrayList.set(i, str2);
    }
    Collections.reverse(paramArrayList);
    for (int j = 0; j < paramArrayList.size(); j++)
    {
      String str1 = (String)paramArrayList.get(j);
      if (str1.equals("list"))
      {
        int n = j - 1;
        StringBuilder localStringBuilder5 = new StringBuilder("<");
        localStringBuilder5.append((String)paramArrayList.get(n));
        paramArrayList.set(n, localStringBuilder5.toString());
        StringBuilder localStringBuilder6 = new StringBuilder();
        localStringBuilder6.append((String)paramArrayList.get(0));
        localStringBuilder6.append(">");
        paramArrayList.set(0, localStringBuilder6.toString());
      }
      else if (str1.equals("map"))
      {
        int m = j - 1;
        StringBuilder localStringBuilder3 = new StringBuilder("<");
        localStringBuilder3.append((String)paramArrayList.get(m));
        localStringBuilder3.append(",");
        paramArrayList.set(m, localStringBuilder3.toString());
        StringBuilder localStringBuilder4 = new StringBuilder();
        localStringBuilder4.append((String)paramArrayList.get(0));
        localStringBuilder4.append(">");
        paramArrayList.set(0, localStringBuilder4.toString());
      }
      else if (str1.equals("Array"))
      {
        int k = j - 1;
        StringBuilder localStringBuilder1 = new StringBuilder("<");
        localStringBuilder1.append((String)paramArrayList.get(k));
        paramArrayList.set(k, localStringBuilder1.toString());
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append((String)paramArrayList.get(0));
        localStringBuilder2.append(">");
        paramArrayList.set(0, localStringBuilder2.toString());
      }
    }
    Collections.reverse(paramArrayList);
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
      localStringBuffer.append((String)localIterator.next());
    return localStringBuffer.toString();
  }

  private void a(ArrayList<String> paramArrayList, Object paramObject)
  {
    if (paramObject.getClass().isArray())
    {
      if (!paramObject.getClass().getComponentType().toString().equals("byte"))
        throw new IllegalArgumentException("only byte[] is supported");
      if (Array.getLength(paramObject) > 0)
      {
        paramArrayList.add("java.util.List");
        a(paramArrayList, Array.get(paramObject, 0));
        return;
      }
      paramArrayList.add("Array");
      paramArrayList.add("?");
      return;
    }
    if ((paramObject instanceof Array))
      throw new IllegalArgumentException("can not support Array, please use List");
    if ((paramObject instanceof List))
    {
      paramArrayList.add("java.util.List");
      List localList = (List)paramObject;
      if (localList.size() > 0)
        a(paramArrayList, localList.get(0));
      else
        paramArrayList.add("?");
    }
    else if ((paramObject instanceof Map))
    {
      paramArrayList.add("java.util.Map");
      Map localMap = (Map)paramObject;
      if (localMap.size() > 0)
      {
        Object localObject1 = localMap.keySet().iterator().next();
        Object localObject2 = localMap.get(localObject1);
        paramArrayList.add(localObject1.getClass().getName());
        a(paramArrayList, localObject2);
      }
      else
      {
        paramArrayList.add("?");
        paramArrayList.add("?");
      }
    }
    else
    {
      paramArrayList.add(paramObject.getClass().getName());
    }
  }

  public static byte[] a(k paramk)
  {
    try
    {
      j localj = new j();
      localj.a("utf-8");
      paramk.a(localj);
      byte[] arrayOfByte = localj.b();
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      if (!x.b(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  public static byte[] a(Object paramObject)
  {
    try
    {
      d locald = new d();
      locald.b();
      locald.a("utf-8");
      locald.b(1);
      locald.b("RqdServer");
      locald.c("sync");
      locald.a("detail", paramObject);
      byte[] arrayOfByte = locald.a();
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      if (!x.b(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  public void a(String paramString)
  {
    this.b = paramString;
  }

  public <T> void a(String paramString, T paramT)
  {
    if (paramString == null)
      throw new IllegalArgumentException("put key can not is null");
    if (paramT == null)
      throw new IllegalArgumentException("put value can not is null");
    if ((paramT instanceof Set))
      throw new IllegalArgumentException("can not support Set");
    j localj = new j();
    localj.a(this.b);
    localj.a(paramT, 0);
    byte[] arrayOfByte = l.a(localj.a());
    HashMap localHashMap = new HashMap(1);
    ArrayList localArrayList = new ArrayList(1);
    a(localArrayList, paramT);
    localHashMap.put(a(localArrayList), arrayOfByte);
    this.d.remove(paramString);
    this.a.put(paramString, localHashMap);
  }

  public void a(byte[] paramArrayOfByte)
  {
    this.c.a(paramArrayOfByte);
    this.c.a(this.b);
    HashMap localHashMap1 = new HashMap(1);
    HashMap localHashMap2 = new HashMap(1);
    localHashMap2.put("", new byte[0]);
    localHashMap1.put("", localHashMap2);
    this.a = this.c.a(localHashMap1, 0, false);
  }

  public byte[] a()
  {
    j localj = new j(0);
    localj.a(this.b);
    localj.a(this.a, 0);
    return l.a(localj.a());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.a
 * JD-Core Version:    0.6.1
 */