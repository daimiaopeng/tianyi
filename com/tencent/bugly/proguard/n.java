package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.HashMap;
import java.util.Map;

public final class n
{
  public static final long a = System.currentTimeMillis();
  private static n b;
  private Context c;
  private String d;
  private Map<Integer, Map<String, m>> e;
  private SharedPreferences f;

  private n(Context paramContext)
  {
    this.c = paramContext;
    this.e = new HashMap();
    this.d = a.b().d;
    this.f = paramContext.getSharedPreferences("crashrecord", 0);
  }

  public static n a()
  {
    try
    {
      n localn = b;
      return localn;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static n a(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new n(paramContext);
      n localn = b;
      return localn;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  private <T extends java.util.List<?>> void a(int paramInt, T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: ifnonnull +6 -> 9
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: aload_0
    //   10: getfield 33	com/tencent/bugly/proguard/n:c	Landroid/content/Context;
    //   13: ldc 48
    //   15: iconst_0
    //   16: invokevirtual 76	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   19: astore 5
    //   21: new 78	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   28: astore 6
    //   30: aload 6
    //   32: iload_1
    //   33: invokevirtual 83	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: new 85	java/io/File
    //   40: dup
    //   41: aload 5
    //   43: aload 6
    //   45: invokevirtual 89	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokespecial 92	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   51: astore 8
    //   53: aconst_null
    //   54: astore 9
    //   56: new 94	java/io/ObjectOutputStream
    //   59: dup
    //   60: new 96	java/io/FileOutputStream
    //   63: dup
    //   64: aload 8
    //   66: invokespecial 99	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   69: invokespecial 102	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   72: astore 10
    //   74: aload 10
    //   76: aload_2
    //   77: invokevirtual 106	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   80: aload 10
    //   82: invokevirtual 109	java/io/ObjectOutputStream:close	()V
    //   85: goto +95 -> 180
    //   88: astore 15
    //   90: aload 15
    //   92: astore 14
    //   94: aload 10
    //   96: astore 9
    //   98: goto +54 -> 152
    //   101: astore 11
    //   103: aload 11
    //   105: astore 12
    //   107: aload 10
    //   109: astore 9
    //   111: goto +10 -> 121
    //   114: astore 14
    //   116: goto +36 -> 152
    //   119: astore 12
    //   121: aload 12
    //   123: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   126: ldc 114
    //   128: iconst_0
    //   129: anewarray 4	java/lang/Object
    //   132: invokestatic 119	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   135: pop
    //   136: aload 9
    //   138: ifnull +11 -> 149
    //   141: aload 9
    //   143: invokevirtual 109	java/io/ObjectOutputStream:close	()V
    //   146: goto +34 -> 180
    //   149: aload_0
    //   150: monitorexit
    //   151: return
    //   152: aload 9
    //   154: ifnull +8 -> 162
    //   157: aload 9
    //   159: invokevirtual 109	java/io/ObjectOutputStream:close	()V
    //   162: aload 14
    //   164: athrow
    //   165: astore 4
    //   167: goto +16 -> 183
    //   170: ldc 121
    //   172: iconst_0
    //   173: anewarray 4	java/lang/Object
    //   176: invokestatic 123	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   179: pop
    //   180: aload_0
    //   181: monitorexit
    //   182: return
    //   183: aload_0
    //   184: monitorexit
    //   185: aload 4
    //   187: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   74	80	88	finally
    //   74	80	101	java/io/IOException
    //   56	74	114	finally
    //   121	136	114	finally
    //   56	74	119	java/io/IOException
    //   9	53	165	finally
    //   80	85	165	finally
    //   141	146	165	finally
    //   157	165	165	finally
    //   170	180	165	finally
    //   9	53	170	java/lang/Exception
    //   80	85	170	java/lang/Exception
    //   141	146	170	java/lang/Exception
    //   157	165	170	java/lang/Exception
  }

  // ERROR //
  private boolean b(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: invokespecial 67	com/tencent/bugly/proguard/n:c	(I)Ljava/util/List;
    //   7: astore 4
    //   9: aload 4
    //   11: ifnonnull +7 -> 18
    //   14: aload_0
    //   15: monitorexit
    //   16: iconst_0
    //   17: ireturn
    //   18: invokestatic 25	java/lang/System:currentTimeMillis	()J
    //   21: lstore 5
    //   23: new 130	java/util/ArrayList
    //   26: dup
    //   27: invokespecial 131	java/util/ArrayList:<init>	()V
    //   30: astore 7
    //   32: new 130	java/util/ArrayList
    //   35: dup
    //   36: invokespecial 131	java/util/ArrayList:<init>	()V
    //   39: astore 8
    //   41: aload 4
    //   43: invokeinterface 137 1 0
    //   48: astore 9
    //   50: aload 9
    //   52: invokeinterface 143 1 0
    //   57: ifeq +84 -> 141
    //   60: aload 9
    //   62: invokeinterface 147 1 0
    //   67: checkcast 149	com/tencent/bugly/proguard/m
    //   70: astore 11
    //   72: aload 11
    //   74: getfield 151	com/tencent/bugly/proguard/m:b	Ljava/lang/String;
    //   77: ifnull +36 -> 113
    //   80: aload 11
    //   82: getfield 151	com/tencent/bugly/proguard/m:b	Ljava/lang/String;
    //   85: aload_0
    //   86: getfield 46	com/tencent/bugly/proguard/n:d	Ljava/lang/String;
    //   89: invokevirtual 157	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   92: ifeq +21 -> 113
    //   95: aload 11
    //   97: getfield 160	com/tencent/bugly/proguard/m:d	I
    //   100: ifle +13 -> 113
    //   103: aload 7
    //   105: aload 11
    //   107: invokeinterface 164 2 0
    //   112: pop
    //   113: ldc2_w 165
    //   116: aload 11
    //   118: getfield 168	com/tencent/bugly/proguard/m:c	J
    //   121: ladd
    //   122: lload 5
    //   124: lcmp
    //   125: ifge -75 -> 50
    //   128: aload 8
    //   130: aload 11
    //   132: invokeinterface 164 2 0
    //   137: pop
    //   138: goto -88 -> 50
    //   141: aload 7
    //   143: invokestatic 174	java/util/Collections:sort	(Ljava/util/List;)V
    //   146: aload 7
    //   148: invokeinterface 178 1 0
    //   153: iconst_2
    //   154: if_icmplt +67 -> 221
    //   157: aload 7
    //   159: invokeinterface 178 1 0
    //   164: ifle +53 -> 217
    //   167: ldc2_w 165
    //   170: aload 7
    //   172: aload 7
    //   174: invokeinterface 178 1 0
    //   179: iconst_1
    //   180: isub
    //   181: invokeinterface 182 2 0
    //   186: checkcast 149	com/tencent/bugly/proguard/m
    //   189: getfield 168	com/tencent/bugly/proguard/m:c	J
    //   192: ladd
    //   193: lload 5
    //   195: lcmp
    //   196: ifge +21 -> 217
    //   199: aload 4
    //   201: invokeinterface 185 1 0
    //   206: aload_0
    //   207: iload_1
    //   208: aload 4
    //   210: invokespecial 126	com/tencent/bugly/proguard/n:a	(ILjava/util/List;)V
    //   213: aload_0
    //   214: monitorexit
    //   215: iconst_0
    //   216: ireturn
    //   217: aload_0
    //   218: monitorexit
    //   219: iconst_1
    //   220: ireturn
    //   221: aload 4
    //   223: aload 8
    //   225: invokeinterface 189 2 0
    //   230: pop
    //   231: aload_0
    //   232: iload_1
    //   233: aload 4
    //   235: invokespecial 126	com/tencent/bugly/proguard/n:a	(ILjava/util/List;)V
    //   238: aload_0
    //   239: monitorexit
    //   240: iconst_0
    //   241: ireturn
    //   242: astore_3
    //   243: goto +17 -> 260
    //   246: ldc 191
    //   248: iconst_0
    //   249: anewarray 4	java/lang/Object
    //   252: invokestatic 123	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   255: pop
    //   256: aload_0
    //   257: monitorexit
    //   258: iconst_0
    //   259: ireturn
    //   260: aload_0
    //   261: monitorexit
    //   262: aload_3
    //   263: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	9	242	finally
    //   18	213	242	finally
    //   221	238	242	finally
    //   246	256	242	finally
    //   2	9	246	java/lang/Exception
    //   18	213	246	java/lang/Exception
    //   221	238	246	java/lang/Exception
  }

  // ERROR //
  private <T extends java.util.List<?>> T c(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 33	com/tencent/bugly/proguard/n:c	Landroid/content/Context;
    //   6: ldc 48
    //   8: iconst_0
    //   9: invokevirtual 76	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   12: astore 4
    //   14: new 78	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   21: astore 5
    //   23: aload 5
    //   25: iload_1
    //   26: invokevirtual 83	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: new 85	java/io/File
    //   33: dup
    //   34: aload 4
    //   36: aload 5
    //   38: invokevirtual 89	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokespecial 92	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   44: astore 7
    //   46: aload 7
    //   48: invokevirtual 200	java/io/File:exists	()Z
    //   51: istore 8
    //   53: iload 8
    //   55: ifne +7 -> 62
    //   58: aload_0
    //   59: monitorexit
    //   60: aconst_null
    //   61: areturn
    //   62: new 202	java/io/ObjectInputStream
    //   65: dup
    //   66: new 204	java/io/FileInputStream
    //   69: dup
    //   70: aload 7
    //   72: invokespecial 205	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   75: invokespecial 208	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   78: astore 9
    //   80: aload 9
    //   82: invokevirtual 211	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   85: checkcast 133	java/util/List
    //   88: astore 15
    //   90: aload 9
    //   92: invokevirtual 212	java/io/ObjectInputStream:close	()V
    //   95: aload_0
    //   96: monitorexit
    //   97: aload 15
    //   99: areturn
    //   100: astore 10
    //   102: aconst_null
    //   103: astore 9
    //   105: goto +60 -> 165
    //   108: aconst_null
    //   109: astore 9
    //   111: goto +4 -> 115
    //   114: pop
    //   115: ldc 214
    //   117: iconst_0
    //   118: anewarray 4	java/lang/Object
    //   121: invokestatic 119	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   124: pop
    //   125: aload 9
    //   127: ifnull +65 -> 192
    //   130: aload 9
    //   132: invokevirtual 212	java/io/ObjectInputStream:close	()V
    //   135: goto +57 -> 192
    //   138: aconst_null
    //   139: astore 9
    //   141: goto +4 -> 145
    //   144: pop
    //   145: ldc 114
    //   147: iconst_0
    //   148: anewarray 4	java/lang/Object
    //   151: invokestatic 119	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   154: pop
    //   155: aload 9
    //   157: ifnull +35 -> 192
    //   160: goto -30 -> 130
    //   163: astore 10
    //   165: aload 9
    //   167: ifnull +8 -> 175
    //   170: aload 9
    //   172: invokevirtual 212	java/io/ObjectInputStream:close	()V
    //   175: aload 10
    //   177: athrow
    //   178: astore_3
    //   179: goto +17 -> 196
    //   182: ldc 216
    //   184: iconst_0
    //   185: anewarray 4	java/lang/Object
    //   188: invokestatic 123	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   191: pop
    //   192: aload_0
    //   193: monitorexit
    //   194: aconst_null
    //   195: areturn
    //   196: aload_0
    //   197: monitorexit
    //   198: aload_3
    //   199: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   62	80	100	finally
    //   62	80	108	java/lang/ClassNotFoundException
    //   80	90	114	java/lang/ClassNotFoundException
    //   62	80	138	java/io/IOException
    //   80	90	144	java/io/IOException
    //   80	90	163	finally
    //   114	125	163	finally
    //   144	155	163	finally
    //   2	53	178	finally
    //   90	95	178	finally
    //   130	135	178	finally
    //   170	178	178	finally
    //   182	192	178	finally
    //   2	53	182	java/lang/Exception
    //   90	95	182	java/lang/Exception
    //   130	135	182	java/lang/Exception
    //   170	178	182	java/lang/Exception
  }

  public final void a(int paramInt1, final int paramInt2)
  {
    try
    {
      w.a().a(new Runnable()
      {
        // ERROR //
        public final void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   4: invokestatic 32	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;)Ljava/lang/String;
          //   7: invokestatic 38	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
          //   10: ifeq +4 -> 14
          //   13: return
          //   14: aload_0
          //   15: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   18: aload_0
          //   19: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   22: invokestatic 41	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;I)Ljava/util/List;
          //   25: astore_2
          //   26: aload_2
          //   27: ifnonnull +11 -> 38
          //   30: new 43	java/util/ArrayList
          //   33: dup
          //   34: invokespecial 44	java/util/ArrayList:<init>	()V
          //   37: astore_2
          //   38: aload_0
          //   39: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   42: invokestatic 47	com/tencent/bugly/proguard/n:b	(Lcom/tencent/bugly/proguard/n;)Ljava/util/Map;
          //   45: aload_0
          //   46: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   49: invokestatic 53	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   52: invokeinterface 59 2 0
          //   57: ifnonnull +32 -> 89
          //   60: new 61	java/util/HashMap
          //   63: dup
          //   64: invokespecial 62	java/util/HashMap:<init>	()V
          //   67: astore_3
          //   68: aload_0
          //   69: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   72: invokestatic 47	com/tencent/bugly/proguard/n:b	(Lcom/tencent/bugly/proguard/n;)Ljava/util/Map;
          //   75: aload_0
          //   76: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   79: invokestatic 53	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   82: aload_3
          //   83: invokeinterface 66 3 0
          //   88: pop
          //   89: aload_0
          //   90: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   93: invokestatic 47	com/tencent/bugly/proguard/n:b	(Lcom/tencent/bugly/proguard/n;)Ljava/util/Map;
          //   96: aload_0
          //   97: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   100: invokestatic 53	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   103: invokeinterface 59 2 0
          //   108: checkcast 55	java/util/Map
          //   111: aload_0
          //   112: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   115: invokestatic 32	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;)Ljava/lang/String;
          //   118: invokeinterface 59 2 0
          //   123: ifnonnull +124 -> 247
          //   126: new 68	com/tencent/bugly/proguard/m
          //   129: dup
          //   130: invokespecial 69	com/tencent/bugly/proguard/m:<init>	()V
          //   133: astore 5
          //   135: aload 5
          //   137: aload_0
          //   138: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   141: i2l
          //   142: putfield 72	com/tencent/bugly/proguard/m:a	J
          //   145: aload 5
          //   147: getstatic 73	com/tencent/bugly/proguard/n:a	J
          //   150: putfield 76	com/tencent/bugly/proguard/m:g	J
          //   153: aload 5
          //   155: aload_0
          //   156: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   159: invokestatic 32	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;)Ljava/lang/String;
          //   162: putfield 79	com/tencent/bugly/proguard/m:b	Ljava/lang/String;
          //   165: aload 5
          //   167: invokestatic 84	com/tencent/bugly/crashreport/common/info/a:b	()Lcom/tencent/bugly/crashreport/common/info/a;
          //   170: getfield 87	com/tencent/bugly/crashreport/common/info/a:j	Ljava/lang/String;
          //   173: putfield 90	com/tencent/bugly/proguard/m:f	Ljava/lang/String;
          //   176: invokestatic 84	com/tencent/bugly/crashreport/common/info/a:b	()Lcom/tencent/bugly/crashreport/common/info/a;
          //   179: invokevirtual 94	java/lang/Object:getClass	()Ljava/lang/Class;
          //   182: pop
          //   183: aload 5
          //   185: ldc 96
          //   187: putfield 99	com/tencent/bugly/proguard/m:e	Ljava/lang/String;
          //   190: aload 5
          //   192: invokestatic 105	java/lang/System:currentTimeMillis	()J
          //   195: putfield 107	com/tencent/bugly/proguard/m:c	J
          //   198: aload 5
          //   200: aload_0
          //   201: getfield 23	com/tencent/bugly/proguard/n$1:b	I
          //   204: putfield 110	com/tencent/bugly/proguard/m:d	I
          //   207: aload_0
          //   208: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   211: invokestatic 47	com/tencent/bugly/proguard/n:b	(Lcom/tencent/bugly/proguard/n;)Ljava/util/Map;
          //   214: aload_0
          //   215: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   218: invokestatic 53	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   221: invokeinterface 59 2 0
          //   226: checkcast 55	java/util/Map
          //   229: aload_0
          //   230: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   233: invokestatic 32	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;)Ljava/lang/String;
          //   236: aload 5
          //   238: invokeinterface 66 3 0
          //   243: pop
          //   244: goto +51 -> 295
          //   247: aload_0
          //   248: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   251: invokestatic 47	com/tencent/bugly/proguard/n:b	(Lcom/tencent/bugly/proguard/n;)Ljava/util/Map;
          //   254: aload_0
          //   255: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   258: invokestatic 53	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   261: invokeinterface 59 2 0
          //   266: checkcast 55	java/util/Map
          //   269: aload_0
          //   270: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   273: invokestatic 32	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;)Ljava/lang/String;
          //   276: invokeinterface 59 2 0
          //   281: checkcast 68	com/tencent/bugly/proguard/m
          //   284: astore 5
          //   286: aload 5
          //   288: aload_0
          //   289: getfield 23	com/tencent/bugly/proguard/n$1:b	I
          //   292: putfield 110	com/tencent/bugly/proguard/m:d	I
          //   295: new 43	java/util/ArrayList
          //   298: dup
          //   299: invokespecial 44	java/util/ArrayList:<init>	()V
          //   302: astore 8
          //   304: aload_2
          //   305: invokeinterface 116 1 0
          //   310: astore 9
          //   312: iconst_0
          //   313: istore 10
          //   315: aload 9
          //   317: invokeinterface 122 1 0
          //   322: ifeq +135 -> 457
          //   325: aload 9
          //   327: invokeinterface 126 1 0
          //   332: checkcast 68	com/tencent/bugly/proguard/m
          //   335: astore 13
          //   337: aload 13
          //   339: getfield 76	com/tencent/bugly/proguard/m:g	J
          //   342: aload 5
          //   344: getfield 76	com/tencent/bugly/proguard/m:g	J
          //   347: lcmp
          //   348: ifne +40 -> 388
          //   351: aload 13
          //   353: getfield 79	com/tencent/bugly/proguard/m:b	Ljava/lang/String;
          //   356: ifnull +32 -> 388
          //   359: aload 13
          //   361: getfield 79	com/tencent/bugly/proguard/m:b	Ljava/lang/String;
          //   364: aload 5
          //   366: getfield 79	com/tencent/bugly/proguard/m:b	Ljava/lang/String;
          //   369: invokevirtual 132	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   372: ifeq +16 -> 388
          //   375: iconst_1
          //   376: istore 10
          //   378: aload 13
          //   380: aload 5
          //   382: getfield 110	com/tencent/bugly/proguard/m:d	I
          //   385: putfield 110	com/tencent/bugly/proguard/m:d	I
          //   388: aload 13
          //   390: getfield 99	com/tencent/bugly/proguard/m:e	Ljava/lang/String;
          //   393: ifnull +19 -> 412
          //   396: aload 13
          //   398: getfield 99	com/tencent/bugly/proguard/m:e	Ljava/lang/String;
          //   401: aload 5
          //   403: getfield 99	com/tencent/bugly/proguard/m:e	Ljava/lang/String;
          //   406: invokevirtual 132	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   409: ifeq +35 -> 444
          //   412: aload 13
          //   414: getfield 90	com/tencent/bugly/proguard/m:f	Ljava/lang/String;
          //   417: ifnull +19 -> 436
          //   420: aload 13
          //   422: getfield 90	com/tencent/bugly/proguard/m:f	Ljava/lang/String;
          //   425: aload 5
          //   427: getfield 90	com/tencent/bugly/proguard/m:f	Ljava/lang/String;
          //   430: invokevirtual 132	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   433: ifeq +11 -> 444
          //   436: aload 13
          //   438: getfield 110	com/tencent/bugly/proguard/m:d	I
          //   441: ifgt -126 -> 315
          //   444: aload 8
          //   446: aload 13
          //   448: invokeinterface 136 2 0
          //   453: pop
          //   454: goto -139 -> 315
          //   457: aload_2
          //   458: aload 8
          //   460: invokeinterface 140 2 0
          //   465: pop
          //   466: iload 10
          //   468: ifne +12 -> 480
          //   471: aload_2
          //   472: aload 5
          //   474: invokeinterface 136 2 0
          //   479: pop
          //   480: aload_0
          //   481: getfield 19	com/tencent/bugly/proguard/n$1:c	Lcom/tencent/bugly/proguard/n;
          //   484: aload_0
          //   485: getfield 21	com/tencent/bugly/proguard/n$1:a	I
          //   488: aload_2
          //   489: invokestatic 143	com/tencent/bugly/proguard/n:a	(Lcom/tencent/bugly/proguard/n;ILjava/util/List;)V
          //   492: return
          //   493: ldc 145
          //   495: iconst_0
          //   496: anewarray 4	java/lang/Object
          //   499: invokestatic 150	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
          //   502: pop
          //   503: return
          //
          // Exception table:
          //   from	to	target	type
          //   0	492	493	java/lang/Exception
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public final boolean a(final int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_1
    //   3: istore_2
    //   4: aload_0
    //   5: getfield 56	com/tencent/bugly/proguard/n:f	Landroid/content/SharedPreferences;
    //   8: astore 5
    //   10: new 78	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   17: astore 6
    //   19: aload 6
    //   21: iload_1
    //   22: invokevirtual 83	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload 6
    //   28: ldc 232
    //   30: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: aload 6
    //   36: aload_0
    //   37: getfield 46	com/tencent/bugly/proguard/n:d	Ljava/lang/String;
    //   40: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload 5
    //   46: aload 6
    //   48: invokevirtual 89	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: iload_2
    //   52: invokeinterface 241 3 0
    //   57: istore 10
    //   59: invokestatic 222	com/tencent/bugly/proguard/w:a	()Lcom/tencent/bugly/proguard/w;
    //   62: new 243	com/tencent/bugly/proguard/n$2
    //   65: dup
    //   66: aload_0
    //   67: iload_1
    //   68: invokespecial 246	com/tencent/bugly/proguard/n$2:<init>	(Lcom/tencent/bugly/proguard/n;I)V
    //   71: invokevirtual 230	com/tencent/bugly/proguard/w:a	(Ljava/lang/Runnable;)Z
    //   74: pop
    //   75: aload_0
    //   76: monitorexit
    //   77: iload 10
    //   79: ireturn
    //   80: iload 10
    //   82: istore_2
    //   83: goto +8 -> 91
    //   86: astore 4
    //   88: goto +17 -> 105
    //   91: ldc 248
    //   93: iconst_0
    //   94: anewarray 4	java/lang/Object
    //   97: invokestatic 123	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   100: pop
    //   101: aload_0
    //   102: monitorexit
    //   103: iload_2
    //   104: ireturn
    //   105: aload_0
    //   106: monitorexit
    //   107: aload 4
    //   109: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   59	75	80	java/lang/Exception
    //   4	59	86	finally
    //   59	75	86	finally
    //   91	101	86	finally
    //   4	59	91	java/lang/Exception
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.n
 * JD-Core Version:    0.6.1
 */