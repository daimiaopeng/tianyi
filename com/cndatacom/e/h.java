package com.cndatacom.e;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class h extends AsyncTask<String, Integer, String>
{
  final int a = 2130972713;
  final int b = 2133069865;
  private final String c = "HttpDownloadAsyncTask";
  private int d;
  private final int e = 2130968593;
  private final int f = 2130968594;
  private final int g = 2130968595;
  private final int h = 2130968596;
  private final int i = 2130968597;
  private final int j = 2130968601;
  private final int k = 2130968665;
  private final int l = 2130968617;
  private a m = null;
  private String n = null;
  private final int o = 150000;
  private final int p = 150000;
  private Context q = null;
  private String r;
  private int s;
  private Handler t = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Log.i("HttpDownloadAsyncTask", " handleMessage ");
      int i = paramAnonymousMessage.what;
      if (i != 2130968601)
      {
        if (i != 2130968617)
        {
          if (i != 2130968665);
          switch (i)
          {
          default:
            break;
          case 2130968597:
            h.a(h.this).a(h.b(h.this));
            break;
          case 2130968596:
            h.a(h.this).b("存储空间不足.");
            break;
          case 2130968595:
            h.a(h.this).b("文件操作异常.");
            break;
          case 2130968594:
            h.a(h.this).b("服务响应失败.");
            break;
            h.a(h.this).b("10010");
          case 2130968593:
            h.a(h.this).b("连接超时.");
            break;
          }
        }
        else
        {
          h.a(h.this).b("下载失败.");
        }
      }
      else
        h.a(h.this).b("下载地址异常.");
    }
  };

  public h(a parama, Context paramContext, String paramString)
  {
    this.m = parama;
    this.q = paramContext;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("HttpDownloadAsyncTask time:");
    localStringBuilder.append(System.currentTimeMillis());
    Log.i("HttpDownloadAsyncTask", localStringBuilder.toString());
  }

  public static void a(a parama, String paramString1, Context paramContext, String paramString2)
  {
    new h(parama, paramContext, paramString2).execute(new String[] { paramString1, paramString2 });
  }

  private boolean a()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  private boolean a(int paramInt)
  {
    ActivityManager localActivityManager = (ActivityManager)this.q.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" 磁盘可以空间  ");
    localStringBuilder.append(localMemoryInfo.availMem);
    Log.i("HttpDownloadAsyncTask", localStringBuilder.toString());
    boolean bool;
    if (paramInt >= localMemoryInfo.availMem)
      bool = true;
    else
      bool = false;
    return bool;
  }

  // ERROR //
  protected String a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: new 89	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: ldc 174
    //   11: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: pop
    //   15: aload_2
    //   16: invokestatic 102	java/lang/System:currentTimeMillis	()J
    //   19: invokevirtual 105	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   22: pop
    //   23: ldc 37
    //   25: aload_2
    //   26: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokestatic 114	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   32: pop
    //   33: aload_0
    //   34: invokespecial 176	com/cndatacom/e/h:a	()Z
    //   37: ifeq +44 -> 81
    //   40: new 89	java/lang/StringBuilder
    //   43: dup
    //   44: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   47: astore 6
    //   49: aload 6
    //   51: invokestatic 180	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   54: invokevirtual 185	java/io/File:getPath	()Ljava/lang/String;
    //   57: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload 6
    //   63: ldc 187
    //   65: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload_0
    //   70: aload 6
    //   72: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: putfield 189	com/cndatacom/e/h:r	Ljava/lang/String;
    //   78: goto +41 -> 119
    //   81: new 89	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   88: astore 60
    //   90: aload 60
    //   92: invokestatic 192	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   95: invokevirtual 185	java/io/File:getPath	()Ljava/lang/String;
    //   98: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload 60
    //   104: ldc 187
    //   106: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: aload_0
    //   111: aload 60
    //   113: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: putfield 189	com/cndatacom/e/h:r	Ljava/lang/String;
    //   119: new 89	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   126: astore 9
    //   128: aload 9
    //   130: aload_0
    //   131: getfield 189	com/cndatacom/e/h:r	Ljava/lang/String;
    //   134: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: aload 9
    //   140: aload_1
    //   141: iconst_1
    //   142: aaload
    //   143: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: pop
    //   147: new 182	java/io/File
    //   150: dup
    //   151: aload 9
    //   153: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: invokespecial 195	java/io/File:<init>	(Ljava/lang/String;)V
    //   159: astore 21
    //   161: aload 21
    //   163: invokevirtual 198	java/io/File:getParentFile	()Ljava/io/File;
    //   166: astore 22
    //   168: aload 22
    //   170: invokevirtual 201	java/io/File:exists	()Z
    //   173: ifne +9 -> 182
    //   176: aload 22
    //   178: invokevirtual 204	java/io/File:mkdirs	()Z
    //   181: pop
    //   182: aload 21
    //   184: invokevirtual 201	java/io/File:exists	()Z
    //   187: ifeq +9 -> 196
    //   190: aload 21
    //   192: invokevirtual 207	java/io/File:delete	()Z
    //   195: pop
    //   196: new 209	java/net/URL
    //   199: dup
    //   200: aload_1
    //   201: iconst_0
    //   202: aaload
    //   203: invokespecial 210	java/net/URL:<init>	(Ljava/lang/String;)V
    //   206: invokevirtual 214	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   209: checkcast 216	java/net/HttpURLConnection
    //   212: astore 23
    //   214: aload 23
    //   216: ldc 68
    //   218: invokevirtual 220	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   221: aload 23
    //   223: ldc 68
    //   225: invokevirtual 223	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   228: aload 23
    //   230: ldc 225
    //   232: ldc 227
    //   234: invokevirtual 231	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: aload 23
    //   239: ldc 233
    //   241: ldc 235
    //   243: invokevirtual 231	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   246: aload 23
    //   248: invokevirtual 238	java/net/HttpURLConnection:connect	()V
    //   251: aload 23
    //   253: invokevirtual 242	java/net/HttpURLConnection:getResponseCode	()I
    //   256: sipush 200
    //   259: if_icmpne +451 -> 710
    //   262: aload 23
    //   264: invokevirtual 246	java/net/HttpURLConnection:getURL	()Ljava/net/URL;
    //   267: astore 25
    //   269: new 89	java/lang/StringBuilder
    //   272: dup
    //   273: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   276: astore 26
    //   278: aload 26
    //   280: ldc 248
    //   282: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: aload 26
    //   288: aload 25
    //   290: invokevirtual 249	java/net/URL:toString	()Ljava/lang/String;
    //   293: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: pop
    //   297: ldc 37
    //   299: aload 26
    //   301: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: invokestatic 114	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   307: pop
    //   308: aload_0
    //   309: aload 23
    //   311: invokevirtual 252	java/net/HttpURLConnection:getContentLength	()I
    //   314: putfield 254	com/cndatacom/e/h:s	I
    //   317: aload 23
    //   319: ldc_w 256
    //   322: invokevirtual 260	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   325: astore 30
    //   327: aload 30
    //   329: ifnull +12 -> 341
    //   332: aload 30
    //   334: invokevirtual 263	java/lang/String:length	()I
    //   337: iconst_1
    //   338: if_icmpge +10 -> 348
    //   341: aload 25
    //   343: invokevirtual 266	java/net/URL:getFile	()Ljava/lang/String;
    //   346: astore 30
    //   348: aload 23
    //   350: invokevirtual 269	java/net/HttpURLConnection:getContentType	()Ljava/lang/String;
    //   353: astore 31
    //   355: new 89	java/lang/StringBuilder
    //   358: dup
    //   359: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   362: astore 32
    //   364: aload 32
    //   366: ldc_w 271
    //   369: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: pop
    //   373: aload 32
    //   375: aload 31
    //   377: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: pop
    //   381: ldc 37
    //   383: aload 32
    //   385: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   388: invokestatic 114	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   391: pop
    //   392: new 89	java/lang/StringBuilder
    //   395: dup
    //   396: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   399: astore 36
    //   401: aload 36
    //   403: ldc_w 273
    //   406: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: pop
    //   410: aload 36
    //   412: aload 30
    //   414: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: pop
    //   418: ldc 37
    //   420: aload 36
    //   422: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   425: invokestatic 114	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   428: pop
    //   429: aload_0
    //   430: aload_0
    //   431: getfield 254	com/cndatacom/e/h:s	I
    //   434: invokespecial 275	com/cndatacom/e/h:a	(I)Z
    //   437: ifeq +15 -> 452
    //   440: aload_0
    //   441: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   444: ldc 49
    //   446: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   449: pop
    //   450: aconst_null
    //   451: areturn
    //   452: new 89	java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   459: astore 40
    //   461: aload 40
    //   463: ldc_w 282
    //   466: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: pop
    //   470: aload 40
    //   472: aload_0
    //   473: getfield 254	com/cndatacom/e/h:s	I
    //   476: invokevirtual 285	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   479: pop
    //   480: ldc 37
    //   482: aload 40
    //   484: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   487: invokestatic 114	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   490: pop
    //   491: aload_0
    //   492: getfield 254	com/cndatacom/e/h:s	I
    //   495: ifgt +15 -> 510
    //   498: aload_0
    //   499: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   502: ldc 61
    //   504: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   507: pop
    //   508: aconst_null
    //   509: areturn
    //   510: new 287	java/io/FileOutputStream
    //   513: dup
    //   514: aload 21
    //   516: invokespecial 290	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   519: astore 11
    //   521: aload 23
    //   523: invokevirtual 294	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   526: astore 10
    //   528: sipush 1024
    //   531: newarray byte
    //   533: astore 49
    //   535: aload 10
    //   537: aload 49
    //   539: invokevirtual 300	java/io/InputStream:read	([B)I
    //   542: istore 50
    //   544: aload 11
    //   546: aload 49
    //   548: iconst_0
    //   549: iload 50
    //   551: invokevirtual 304	java/io/FileOutputStream:write	([BII)V
    //   554: aload_0
    //   555: iload 50
    //   557: aload_0
    //   558: getfield 306	com/cndatacom/e/h:d	I
    //   561: iadd
    //   562: putfield 306	com/cndatacom/e/h:d	I
    //   565: iconst_1
    //   566: anewarray 308	java/lang/Integer
    //   569: astore 51
    //   571: aload 51
    //   573: iconst_0
    //   574: aload_0
    //   575: getfield 306	com/cndatacom/e/h:d	I
    //   578: invokestatic 312	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   581: aastore
    //   582: aload_0
    //   583: aload 51
    //   585: invokevirtual 316	com/cndatacom/e/h:publishProgress	([Ljava/lang/Object;)V
    //   588: aload_0
    //   589: getfield 306	com/cndatacom/e/h:d	I
    //   592: aload_0
    //   593: getfield 254	com/cndatacom/e/h:s	I
    //   596: if_icmplt -61 -> 535
    //   599: new 89	java/lang/StringBuilder
    //   602: dup
    //   603: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   606: astore 52
    //   608: aload 52
    //   610: aload_0
    //   611: getfield 189	com/cndatacom/e/h:r	Ljava/lang/String;
    //   614: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   617: pop
    //   618: aload 52
    //   620: aload_1
    //   621: iconst_1
    //   622: aaload
    //   623: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   626: pop
    //   627: aload 52
    //   629: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   632: astore 55
    //   634: aload 10
    //   636: ifnull +8 -> 644
    //   639: aload 10
    //   641: invokevirtual 319	java/io/InputStream:close	()V
    //   644: aload 11
    //   646: ifnull +23 -> 669
    //   649: aload 11
    //   651: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   654: goto +15 -> 669
    //   657: aload_0
    //   658: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   661: ldc 46
    //   663: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   666: pop
    //   667: aconst_null
    //   668: areturn
    //   669: aload 55
    //   671: areturn
    //   672: astore 12
    //   674: aconst_null
    //   675: astore 10
    //   677: goto +267 -> 944
    //   680: aconst_null
    //   681: astore 10
    //   683: goto +59 -> 742
    //   686: aconst_null
    //   687: astore 10
    //   689: goto +95 -> 784
    //   692: aconst_null
    //   693: astore 10
    //   695: goto +131 -> 826
    //   698: aconst_null
    //   699: astore 10
    //   701: goto +167 -> 868
    //   704: aconst_null
    //   705: astore 10
    //   707: goto +203 -> 910
    //   710: aload_0
    //   711: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   714: ldc 43
    //   716: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   719: pop
    //   720: aconst_null
    //   721: areturn
    //   722: astore 12
    //   724: aconst_null
    //   725: astore 10
    //   727: aconst_null
    //   728: astore 11
    //   730: goto +214 -> 944
    //   733: aconst_null
    //   734: astore 10
    //   736: aconst_null
    //   737: astore 11
    //   739: goto +4 -> 743
    //   742: pop
    //   743: aload_0
    //   744: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   747: ldc 46
    //   749: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   752: pop
    //   753: aload 10
    //   755: ifnull +8 -> 763
    //   758: aload 10
    //   760: invokevirtual 319	java/io/InputStream:close	()V
    //   763: aload 11
    //   765: ifnull +8 -> 773
    //   768: aload 11
    //   770: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   773: aconst_null
    //   774: areturn
    //   775: aconst_null
    //   776: astore 10
    //   778: aconst_null
    //   779: astore 11
    //   781: goto +4 -> 785
    //   784: pop
    //   785: aload_0
    //   786: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   789: ldc 46
    //   791: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   794: pop
    //   795: aload 10
    //   797: ifnull +8 -> 805
    //   800: aload 10
    //   802: invokevirtual 319	java/io/InputStream:close	()V
    //   805: aload 11
    //   807: ifnull +8 -> 815
    //   810: aload 11
    //   812: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   815: aconst_null
    //   816: areturn
    //   817: aconst_null
    //   818: astore 10
    //   820: aconst_null
    //   821: astore 11
    //   823: goto +4 -> 827
    //   826: pop
    //   827: aload_0
    //   828: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   831: ldc 40
    //   833: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   836: pop
    //   837: aload 10
    //   839: ifnull +8 -> 847
    //   842: aload 10
    //   844: invokevirtual 319	java/io/InputStream:close	()V
    //   847: aload 11
    //   849: ifnull +8 -> 857
    //   852: aload 11
    //   854: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   857: aconst_null
    //   858: areturn
    //   859: aconst_null
    //   860: astore 10
    //   862: aconst_null
    //   863: astore 11
    //   865: goto +4 -> 869
    //   868: pop
    //   869: aload_0
    //   870: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   873: ldc 40
    //   875: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   878: pop
    //   879: aload 10
    //   881: ifnull +8 -> 889
    //   884: aload 10
    //   886: invokevirtual 319	java/io/InputStream:close	()V
    //   889: aload 11
    //   891: ifnull +8 -> 899
    //   894: aload 11
    //   896: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   899: aconst_null
    //   900: areturn
    //   901: aconst_null
    //   902: astore 10
    //   904: aconst_null
    //   905: astore 11
    //   907: goto +4 -> 911
    //   910: pop
    //   911: aload_0
    //   912: getfield 87	com/cndatacom/e/h:t	Landroid/os/Handler;
    //   915: ldc 55
    //   917: invokevirtual 280	android/os/Handler:sendEmptyMessage	(I)Z
    //   920: pop
    //   921: aload 10
    //   923: ifnull +8 -> 931
    //   926: aload 10
    //   928: invokevirtual 319	java/io/InputStream:close	()V
    //   931: aload 11
    //   933: ifnull +34 -> 967
    //   936: aload 11
    //   938: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   941: goto +26 -> 967
    //   944: aload 10
    //   946: ifnull +8 -> 954
    //   949: aload 10
    //   951: invokevirtual 319	java/io/InputStream:close	()V
    //   954: aload 11
    //   956: ifnull +8 -> 964
    //   959: aload 11
    //   961: invokevirtual 320	java/io/FileOutputStream:close	()V
    //   964: aload 12
    //   966: athrow
    //   967: aconst_null
    //   968: areturn
    //   969: astore 12
    //   971: goto -27 -> 944
    //
    // Exception table:
    //   from	to	target	type
    //   639	654	657	java/io/IOException
    //   758	773	657	java/io/IOException
    //   800	815	657	java/io/IOException
    //   842	857	657	java/io/IOException
    //   884	899	657	java/io/IOException
    //   926	964	657	java/io/IOException
    //   521	528	672	finally
    //   521	528	680	java/io/IOException
    //   521	528	686	java/lang/ArrayIndexOutOfBoundsException
    //   521	528	692	org/apache/http/conn/ConnectTimeoutException
    //   521	528	698	java/net/SocketTimeoutException
    //   521	528	704	java/net/MalformedURLException
    //   119	521	722	finally
    //   710	720	722	finally
    //   119	521	733	java/io/IOException
    //   710	720	733	java/io/IOException
    //   528	634	742	java/io/IOException
    //   119	521	775	java/lang/ArrayIndexOutOfBoundsException
    //   710	720	775	java/lang/ArrayIndexOutOfBoundsException
    //   528	634	784	java/lang/ArrayIndexOutOfBoundsException
    //   119	521	817	org/apache/http/conn/ConnectTimeoutException
    //   710	720	817	org/apache/http/conn/ConnectTimeoutException
    //   528	634	826	org/apache/http/conn/ConnectTimeoutException
    //   119	521	859	java/net/SocketTimeoutException
    //   710	720	859	java/net/SocketTimeoutException
    //   528	634	868	java/net/SocketTimeoutException
    //   119	521	901	java/net/MalformedURLException
    //   710	720	901	java/net/MalformedURLException
    //   528	634	910	java/net/MalformedURLException
    //   528	634	969	finally
    //   742	753	969	finally
    //   784	795	969	finally
    //   826	837	969	finally
    //   868	879	969	finally
    //   910	921	969	finally
  }

  protected void a(String paramString)
  {
    super.onPostExecute(paramString);
    Log.i("HttpDownloadAsyncTask", "----执行完成----");
    if (paramString == null)
      return;
    this.n = paramString;
    this.t.sendEmptyMessage(2130968597);
  }

  protected void a(Integer[] paramArrayOfInteger)
  {
    super.onProgressUpdate(paramArrayOfInteger);
    int i1 = (int)(100.0D * (paramArrayOfInteger[0].intValue() / this.s));
    this.m.a(i1);
  }

  protected void onPreExecute()
  {
    super.onPreExecute();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("onPreExecute time:");
    localStringBuilder.append(System.currentTimeMillis());
    Log.i("HttpDownloadAsyncTask", localStringBuilder.toString());
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt);

    public abstract void a(String paramString);

    public abstract void b(String paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.h
 * JD-Core Version:    0.6.1
 */