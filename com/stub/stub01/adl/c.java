package com.stub.stub01.adl;

import android.os.AsyncTask;
import com.stub.stub01.a.d;
import com.stub.stub01.b;
import java.net.HttpURLConnection;
import java.net.URL;

public final class c extends AsyncTask<String, String, String>
{
  private DownloadManager a;
  private d b;
  private volatile boolean c = false;

  public c(DownloadManager paramDownloadManager, d paramd)
  {
    this.a = paramDownloadManager;
    this.b = paramd;
  }

  // ERROR //
  private String a()
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 27	java/lang/String
    //   4: astore_1
    //   5: aload_1
    //   6: iconst_0
    //   7: ldc 29
    //   9: aastore
    //   10: aload_1
    //   11: iconst_1
    //   12: new 31	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   19: aload_0
    //   20: invokevirtual 38	java/lang/Object:getClass	()Ljava/lang/Class;
    //   23: invokevirtual 43	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   26: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: ldc 49
    //   31: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokestatic 55	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   37: invokevirtual 59	java/lang/Thread:getId	()J
    //   40: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   43: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: aastore
    //   47: aload_1
    //   48: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   51: aload_0
    //   52: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   55: invokevirtual 75	com/stub/stub01/a/d:d	()J
    //   58: lconst_0
    //   59: lcmp
    //   60: ifne +41 -> 101
    //   63: aload_0
    //   64: invokespecial 77	com/stub/stub01/adl/c:b	()J
    //   67: lstore_2
    //   68: lload_2
    //   69: lconst_0
    //   70: lcmp
    //   71: ifgt +41 -> 112
    //   74: new 31	java/lang/StringBuilder
    //   77: dup
    //   78: ldc 79
    //   80: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   83: lload_2
    //   84: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_0
    //   89: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   92: aload_0
    //   93: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   96: invokevirtual 88	com/stub/stub01/adl/DownloadManager:onFail$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   99: aconst_null
    //   100: areturn
    //   101: aload_0
    //   102: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   105: invokevirtual 75	com/stub/stub01/a/d:d	()J
    //   108: lstore_2
    //   109: goto -41 -> 68
    //   112: new 90	java/io/File
    //   115: dup
    //   116: aload_0
    //   117: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   120: invokevirtual 92	com/stub/stub01/a/d:c	()Ljava/lang/String;
    //   123: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   126: astore 4
    //   128: aload 4
    //   130: invokevirtual 97	java/io/File:getParentFile	()Ljava/io/File;
    //   133: invokevirtual 101	java/io/File:exists	()Z
    //   136: ifne +12 -> 148
    //   139: aload 4
    //   141: invokevirtual 97	java/io/File:getParentFile	()Ljava/io/File;
    //   144: invokevirtual 104	java/io/File:mkdirs	()Z
    //   147: pop
    //   148: new 90	java/io/File
    //   151: dup
    //   152: aload_0
    //   153: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   156: invokevirtual 107	com/stub/stub01/a/d:h	()Ljava/lang/String;
    //   159: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   162: astore 5
    //   164: aload 5
    //   166: invokevirtual 101	java/io/File:exists	()Z
    //   169: ifne +9 -> 178
    //   172: aload 5
    //   174: invokevirtual 110	java/io/File:createNewFile	()Z
    //   177: pop
    //   178: new 112	java/io/RandomAccessFile
    //   181: dup
    //   182: aload 5
    //   184: ldc 114
    //   186: invokespecial 117	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   189: astore 33
    //   191: lload_2
    //   192: lconst_0
    //   193: lcmp
    //   194: ifgt +390 -> 584
    //   197: aload 33
    //   199: invokevirtual 120	java/io/RandomAccessFile:close	()V
    //   202: aload_0
    //   203: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   206: lload_2
    //   207: invokevirtual 123	com/stub/stub01/a/d:a	(J)V
    //   210: aload_0
    //   211: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   214: aload_0
    //   215: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   218: invokevirtual 126	com/stub/stub01/adl/DownloadManager:onStart$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   221: aload_0
    //   222: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   225: invokevirtual 129	com/stub/stub01/a/d:e	()J
    //   228: lstore 18
    //   230: new 112	java/io/RandomAccessFile
    //   233: dup
    //   234: aload_0
    //   235: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   238: invokevirtual 107	com/stub/stub01/a/d:h	()Ljava/lang/String;
    //   241: ldc 114
    //   243: invokespecial 132	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   246: astore 10
    //   248: aload 10
    //   250: lload 18
    //   252: invokevirtual 135	java/io/RandomAccessFile:seek	(J)V
    //   255: iconst_2
    //   256: anewarray 27	java/lang/String
    //   259: astore 22
    //   261: aload 22
    //   263: iconst_0
    //   264: ldc 29
    //   266: aastore
    //   267: aload 22
    //   269: iconst_1
    //   270: new 31	java/lang/StringBuilder
    //   273: dup
    //   274: invokespecial 32	java/lang/StringBuilder:<init>	()V
    //   277: aload_0
    //   278: invokevirtual 38	java/lang/Object:getClass	()Ljava/lang/Class;
    //   281: invokevirtual 43	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   284: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: ldc 137
    //   289: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: lload 18
    //   294: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   297: ldc 139
    //   299: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: lload_2
    //   303: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   306: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: aastore
    //   310: aload 22
    //   312: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   315: new 141	java/net/URL
    //   318: dup
    //   319: aload_0
    //   320: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   323: invokevirtual 143	com/stub/stub01/a/d:a	()Ljava/lang/String;
    //   326: invokespecial 144	java/net/URL:<init>	(Ljava/lang/String;)V
    //   329: invokevirtual 148	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   332: checkcast 150	java/net/HttpURLConnection
    //   335: astore 23
    //   337: aload 23
    //   339: sipush 5000
    //   342: invokevirtual 154	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   345: aload 23
    //   347: ldc 156
    //   349: ldc 158
    //   351: invokevirtual 161	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 23
    //   356: ldc 163
    //   358: invokevirtual 166	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   361: aload 23
    //   363: ldc 168
    //   365: new 31	java/lang/StringBuilder
    //   368: dup
    //   369: ldc 170
    //   371: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   374: lload 18
    //   376: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   379: ldc 172
    //   381: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: lload_2
    //   385: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   388: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   391: invokevirtual 161	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   394: aload 23
    //   396: ldc 174
    //   398: ldc 176
    //   400: invokevirtual 161	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   403: aload 23
    //   405: ldc 178
    //   407: ldc 180
    //   409: invokevirtual 161	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   412: aload 23
    //   414: invokevirtual 184	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   417: astore 24
    //   419: aload 24
    //   421: astore 9
    //   423: iconst_2
    //   424: anewarray 27	java/lang/String
    //   427: astore 25
    //   429: aload 25
    //   431: iconst_0
    //   432: ldc 29
    //   434: aastore
    //   435: aload 25
    //   437: iconst_1
    //   438: new 31	java/lang/StringBuilder
    //   441: dup
    //   442: ldc 186
    //   444: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   447: aload_0
    //   448: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   451: invokevirtual 107	com/stub/stub01/a/d:h	()Ljava/lang/String;
    //   454: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   457: ldc 188
    //   459: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   462: lload 18
    //   464: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   467: ldc 139
    //   469: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: lload_2
    //   473: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   476: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   479: aastore
    //   480: aload 25
    //   482: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   485: sipush 4096
    //   488: newarray byte
    //   490: astore 26
    //   492: aload 9
    //   494: aload 26
    //   496: invokevirtual 194	java/io/InputStream:read	([B)I
    //   499: istore 27
    //   501: iload 27
    //   503: iconst_m1
    //   504: if_icmpeq +241 -> 745
    //   507: aload_0
    //   508: getfield 18	com/stub/stub01/adl/c:c	Z
    //   511: ifeq +97 -> 608
    //   514: iconst_2
    //   515: anewarray 27	java/lang/String
    //   518: dup
    //   519: iconst_0
    //   520: ldc 29
    //   522: aastore
    //   523: dup
    //   524: iconst_1
    //   525: ldc 196
    //   527: aastore
    //   528: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   531: aload_0
    //   532: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   535: lload 18
    //   537: invokevirtual 198	com/stub/stub01/a/d:b	(J)V
    //   540: aload_0
    //   541: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   544: aload_0
    //   545: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   548: invokevirtual 201	com/stub/stub01/adl/DownloadManager:onPause$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   551: aload 9
    //   553: ifnull +8 -> 561
    //   556: aload 9
    //   558: invokevirtual 202	java/io/InputStream:close	()V
    //   561: aload 10
    //   563: invokevirtual 120	java/io/RandomAccessFile:close	()V
    //   566: aload 23
    //   568: ifnull -469 -> 99
    //   571: aload 23
    //   573: invokevirtual 205	java/net/HttpURLConnection:disconnect	()V
    //   576: goto -477 -> 99
    //   579: astore 32
    //   581: goto -482 -> 99
    //   584: aload 33
    //   586: lload_2
    //   587: invokevirtual 208	java/io/RandomAccessFile:setLength	(J)V
    //   590: aload 33
    //   592: invokevirtual 120	java/io/RandomAccessFile:close	()V
    //   595: goto -393 -> 202
    //   598: astore 6
    //   600: aload 6
    //   602: invokevirtual 211	java/lang/Throwable:printStackTrace	()V
    //   605: goto -403 -> 202
    //   608: aload 10
    //   610: aload 26
    //   612: iconst_0
    //   613: iload 27
    //   615: invokevirtual 215	java/io/RandomAccessFile:write	([BII)V
    //   618: lload 18
    //   620: iload 27
    //   622: i2l
    //   623: ladd
    //   624: lstore 18
    //   626: aload_0
    //   627: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   630: lload 18
    //   632: invokevirtual 198	com/stub/stub01/a/d:b	(J)V
    //   635: aload_0
    //   636: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   639: aload_0
    //   640: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   643: invokevirtual 218	com/stub/stub01/adl/DownloadManager:onDownload$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   646: goto -154 -> 492
    //   649: astore 8
    //   651: aload 23
    //   653: astore 11
    //   655: iconst_2
    //   656: anewarray 27	java/lang/String
    //   659: astore 15
    //   661: aload 15
    //   663: iconst_0
    //   664: ldc 29
    //   666: aastore
    //   667: aload 15
    //   669: iconst_1
    //   670: new 31	java/lang/StringBuilder
    //   673: dup
    //   674: ldc 220
    //   676: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   679: aload 8
    //   681: invokevirtual 223	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   684: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   690: aastore
    //   691: aload 15
    //   693: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   696: aload_0
    //   697: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   700: aload_0
    //   701: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   704: invokevirtual 201	com/stub/stub01/adl/DownloadManager:onPause$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   707: aload 9
    //   709: ifnull +8 -> 717
    //   712: aload 9
    //   714: invokevirtual 202	java/io/InputStream:close	()V
    //   717: aload 10
    //   719: ifnull +8 -> 727
    //   722: aload 10
    //   724: invokevirtual 120	java/io/RandomAccessFile:close	()V
    //   727: aload 11
    //   729: ifnull -630 -> 99
    //   732: aload 11
    //   734: invokevirtual 205	java/net/HttpURLConnection:disconnect	()V
    //   737: goto -638 -> 99
    //   740: astore 16
    //   742: goto -643 -> 99
    //   745: iconst_2
    //   746: anewarray 27	java/lang/String
    //   749: astore 28
    //   751: aload 28
    //   753: iconst_0
    //   754: ldc 29
    //   756: aastore
    //   757: aload 28
    //   759: iconst_1
    //   760: new 31	java/lang/StringBuilder
    //   763: dup
    //   764: ldc 225
    //   766: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   769: iload 27
    //   771: invokevirtual 228	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   774: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   777: aastore
    //   778: aload 28
    //   780: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   783: aload 23
    //   785: invokevirtual 205	java/net/HttpURLConnection:disconnect	()V
    //   788: lload 18
    //   790: lload_2
    //   791: lcmp
    //   792: ifne +13 -> 805
    //   795: aload 10
    //   797: invokevirtual 231	java/io/RandomAccessFile:getFilePointer	()J
    //   800: lload_2
    //   801: lcmp
    //   802: ifeq +100 -> 902
    //   805: iconst_2
    //   806: anewarray 27	java/lang/String
    //   809: astore 29
    //   811: aload 29
    //   813: iconst_0
    //   814: ldc 29
    //   816: aastore
    //   817: aload 29
    //   819: iconst_1
    //   820: new 31	java/lang/StringBuilder
    //   823: dup
    //   824: ldc 233
    //   826: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   829: lload 18
    //   831: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   834: ldc 235
    //   836: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: aload_0
    //   840: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   843: invokevirtual 75	com/stub/stub01/a/d:d	()J
    //   846: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   849: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   852: aastore
    //   853: aload 29
    //   855: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   858: aload_0
    //   859: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   862: aload_0
    //   863: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   866: invokevirtual 88	com/stub/stub01/adl/DownloadManager:onFail$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   869: aload 9
    //   871: ifnull +8 -> 879
    //   874: aload 9
    //   876: invokevirtual 202	java/io/InputStream:close	()V
    //   879: aload 10
    //   881: invokevirtual 120	java/io/RandomAccessFile:close	()V
    //   884: aload 23
    //   886: ifnull -787 -> 99
    //   889: aload 23
    //   891: invokevirtual 205	java/net/HttpURLConnection:disconnect	()V
    //   894: goto -795 -> 99
    //   897: astore 30
    //   899: goto -800 -> 99
    //   902: iconst_2
    //   903: anewarray 27	java/lang/String
    //   906: astore 31
    //   908: aload 31
    //   910: iconst_0
    //   911: ldc 29
    //   913: aastore
    //   914: aload 31
    //   916: iconst_1
    //   917: new 31	java/lang/StringBuilder
    //   920: dup
    //   921: ldc 237
    //   923: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   926: aload_0
    //   927: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   930: invokevirtual 143	com/stub/stub01/a/d:a	()Ljava/lang/String;
    //   933: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   936: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   939: aastore
    //   940: aload 31
    //   942: invokestatic 70	com/stub/stub01/b:a	([Ljava/lang/String;)V
    //   945: aload_0
    //   946: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   949: lload 18
    //   951: invokevirtual 198	com/stub/stub01/a/d:b	(J)V
    //   954: aload_0
    //   955: getfield 20	com/stub/stub01/adl/c:a	Lcom/stub/stub01/adl/DownloadManager;
    //   958: aload_0
    //   959: getfield 22	com/stub/stub01/adl/c:b	Lcom/stub/stub01/a/d;
    //   962: invokevirtual 240	com/stub/stub01/adl/DownloadManager:onDone$24b7851c	(Lcom/stub/stub01/a/d;)V
    //   965: goto -96 -> 869
    //   968: astore 13
    //   970: aload 23
    //   972: astore 11
    //   974: aload 9
    //   976: ifnull +8 -> 984
    //   979: aload 9
    //   981: invokevirtual 202	java/io/InputStream:close	()V
    //   984: aload 10
    //   986: ifnull +8 -> 994
    //   989: aload 10
    //   991: invokevirtual 120	java/io/RandomAccessFile:close	()V
    //   994: aload 11
    //   996: ifnull +8 -> 1004
    //   999: aload 11
    //   1001: invokevirtual 205	java/net/HttpURLConnection:disconnect	()V
    //   1004: aload 13
    //   1006: athrow
    //   1007: astore 14
    //   1009: goto -5 -> 1004
    //   1012: astore 17
    //   1014: aload 17
    //   1016: astore 13
    //   1018: aconst_null
    //   1019: astore 9
    //   1021: aconst_null
    //   1022: astore 10
    //   1024: aconst_null
    //   1025: astore 11
    //   1027: goto -53 -> 974
    //   1030: astore 21
    //   1032: aload 21
    //   1034: astore 13
    //   1036: aconst_null
    //   1037: astore 9
    //   1039: aconst_null
    //   1040: astore 11
    //   1042: goto -68 -> 974
    //   1045: astore 13
    //   1047: aload 23
    //   1049: astore 11
    //   1051: aconst_null
    //   1052: astore 9
    //   1054: goto -80 -> 974
    //   1057: astore 12
    //   1059: aload 12
    //   1061: astore 13
    //   1063: goto -89 -> 974
    //   1066: astore 7
    //   1068: aload 7
    //   1070: astore 8
    //   1072: aconst_null
    //   1073: astore 9
    //   1075: aconst_null
    //   1076: astore 10
    //   1078: aconst_null
    //   1079: astore 11
    //   1081: goto -426 -> 655
    //   1084: astore 20
    //   1086: aload 20
    //   1088: astore 8
    //   1090: aconst_null
    //   1091: astore 9
    //   1093: aconst_null
    //   1094: astore 11
    //   1096: goto -441 -> 655
    //   1099: astore 8
    //   1101: aload 23
    //   1103: astore 11
    //   1105: aconst_null
    //   1106: astore 9
    //   1108: goto -453 -> 655
    //
    // Exception table:
    //   from	to	target	type
    //   556	576	579	java/lang/Throwable
    //   148	202	598	java/lang/Throwable
    //   584	595	598	java/lang/Throwable
    //   423	551	649	java/lang/Throwable
    //   608	646	649	java/lang/Throwable
    //   745	869	649	java/lang/Throwable
    //   902	965	649	java/lang/Throwable
    //   712	737	740	java/lang/Throwable
    //   874	894	897	java/lang/Throwable
    //   423	551	968	finally
    //   608	646	968	finally
    //   745	869	968	finally
    //   902	965	968	finally
    //   979	1004	1007	java/lang/Throwable
    //   221	248	1012	finally
    //   248	337	1030	finally
    //   337	419	1045	finally
    //   655	707	1057	finally
    //   221	248	1066	java/lang/Throwable
    //   248	337	1084	java/lang/Throwable
    //   337	419	1099	java/lang/Throwable
  }

  private long b()
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(this.b.a()).openConnection();
      localHttpURLConnection.setConnectTimeout(5000);
      localHttpURLConnection.setRequestMethod("HEAD");
      long l2 = localHttpURLConnection.getContentLength();
      String[] arrayOfString1 = new String[2];
      arrayOfString1[0] = "appdownload";
      arrayOfString1[1] = (this.b.a() + " " + l2);
      b.a(arrayOfString1);
      localHttpURLConnection.disconnect();
      String[] arrayOfString2 = new String[2];
      arrayOfString2[0] = "appdownload";
      arrayOfString2[1] = ("getSize " + l2);
      b.a(arrayOfString2);
      l1 = l2;
      return l1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        long l1 = 0L;
      }
    }
  }

  final void a(boolean paramBoolean)
  {
    this.c = true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.stub01.adl.c
 * JD-Core Version:    0.6.1
 */