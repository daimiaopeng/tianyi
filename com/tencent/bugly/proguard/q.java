package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.a;
import com.tencent.bugly.crashreport.common.info.b;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class q extends SQLiteOpenHelper
{
  public static String a = "bugly_db";
  private static int b = 13;
  private Context c;
  private List<a> d;

  public q(Context paramContext, List<a> paramList)
  {
    super(paramContext, localStringBuilder.toString(), null, b);
    this.c = paramContext;
    this.d = paramList;
  }

  // ERROR //
  private boolean a(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_3
    //   3: anewarray 61	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc 63
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: ldc 65
    //   15: aastore
    //   16: dup
    //   17: iconst_2
    //   18: ldc 67
    //   20: aastore
    //   21: astore_2
    //   22: aload_2
    //   23: arraylength
    //   24: istore 5
    //   26: iconst_0
    //   27: istore 6
    //   29: iload 6
    //   31: iload 5
    //   33: if_icmpge +47 -> 80
    //   36: aload_2
    //   37: iload 6
    //   39: aaload
    //   40: astore 7
    //   42: new 22	java/lang/StringBuilder
    //   45: dup
    //   46: ldc 69
    //   48: invokespecial 72	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   51: astore 8
    //   53: aload 8
    //   55: aload 7
    //   57: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload_1
    //   62: aload 8
    //   64: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: iconst_0
    //   68: anewarray 61	java/lang/String
    //   71: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   74: iinc 6 1
    //   77: goto -48 -> 29
    //   80: aload_0
    //   81: monitorexit
    //   82: iconst_1
    //   83: ireturn
    //   84: astore 4
    //   86: goto +19 -> 105
    //   89: astore_3
    //   90: aload_3
    //   91: invokestatic 83	com/tencent/bugly/proguard/x:b	(Ljava/lang/Throwable;)Z
    //   94: ifne +7 -> 101
    //   97: aload_3
    //   98: invokevirtual 86	java/lang/Throwable:printStackTrace	()V
    //   101: aload_0
    //   102: monitorexit
    //   103: iconst_0
    //   104: ireturn
    //   105: aload_0
    //   106: monitorexit
    //   107: aload 4
    //   109: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	74	84	finally
    //   90	101	84	finally
    //   2	74	89	java/lang/Throwable
  }

  // ERROR //
  public final SQLiteDatabase getReadableDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_1
    //   4: iconst_0
    //   5: istore_2
    //   6: aload_1
    //   7: ifnonnull +86 -> 93
    //   10: iload_2
    //   11: iconst_5
    //   12: if_icmpge +81 -> 93
    //   15: iinc 2 1
    //   18: aload_0
    //   19: invokespecial 92	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore 8
    //   24: aload 8
    //   26: astore_1
    //   27: goto -21 -> 6
    //   30: astore 6
    //   32: goto +56 -> 88
    //   35: iconst_1
    //   36: anewarray 39	java/lang/Object
    //   39: astore_3
    //   40: aload_3
    //   41: iconst_0
    //   42: iload_2
    //   43: invokestatic 98	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   46: aastore
    //   47: ldc 100
    //   49: aload_3
    //   50: invokestatic 103	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   53: pop
    //   54: iload_2
    //   55: iconst_5
    //   56: if_icmpne +13 -> 69
    //   59: ldc 105
    //   61: iconst_0
    //   62: anewarray 39	java/lang/Object
    //   65: invokestatic 108	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   68: pop
    //   69: ldc2_w 109
    //   72: invokestatic 116	java/lang/Thread:sleep	(J)V
    //   75: goto -69 -> 6
    //   78: astore 5
    //   80: aload 5
    //   82: invokevirtual 117	java/lang/InterruptedException:printStackTrace	()V
    //   85: goto -79 -> 6
    //   88: aload_0
    //   89: monitorexit
    //   90: aload 6
    //   92: athrow
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_1
    //   96: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   18	24	30	finally
    //   35	69	30	finally
    //   69	75	30	finally
    //   80	85	30	finally
    //   18	24	35	java/lang/Throwable
    //   69	75	78	java/lang/InterruptedException
  }

  public final SQLiteDatabase getWritableDatabase()
  {
    Object localObject1 = null;
    int i = 0;
    while (true)
    {
      if ((localObject1 == null) && (i < 5))
        i++;
      try
      {
        SQLiteDatabase localSQLiteDatabase = super.getWritableDatabase();
        localObject1 = localSQLiteDatabase;
      }
      catch (Throwable localThrowable)
      {
        arrayOfObject[0] = Integer.valueOf(i);
        x.d("[Database] Try to get db(count: %d).", arrayOfObject);
        if (i == 5)
          x.e("[Database] Failed to get db.", new Object[0]);
        Thread.sleep(200L);
        tmpTernaryOp = localThrowable;
        try
        {
        }
        catch (InterruptedException localInterruptedException)
        {
          Object[] arrayOfObject;
          localInterruptedException.printStackTrace();
        }
        continue;
        if (localObject1 == null)
          x.d("[Database] db error delay error record 1min.", new Object[0]);
      }
      finally
      {
      }
    }
    return localObject1;
  }

  // ERROR //
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 22	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: iconst_0
    //   12: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   15: aload_2
    //   16: ldc 130
    //   18: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: pop
    //   22: aload_2
    //   23: ldc 132
    //   25: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: pop
    //   29: aload_2
    //   30: ldc 134
    //   32: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload_2
    //   37: ldc 136
    //   39: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload_2
    //   44: ldc 138
    //   46: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload_2
    //   51: ldc 140
    //   53: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload_2
    //   58: ldc 138
    //   60: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload_2
    //   65: ldc 142
    //   67: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_2
    //   72: ldc 138
    //   74: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload_2
    //   79: ldc 144
    //   81: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload_2
    //   86: ldc 146
    //   88: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload_2
    //   93: ldc 148
    //   95: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload_2
    //   100: ldc 150
    //   102: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: pop
    //   106: aload_2
    //   107: ldc 152
    //   109: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload_2
    //   114: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: iconst_0
    //   118: anewarray 39	java/lang/Object
    //   121: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   124: pop
    //   125: aload_1
    //   126: aload_2
    //   127: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: iconst_0
    //   131: anewarray 61	java/lang/String
    //   134: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   137: aload_2
    //   138: iconst_0
    //   139: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   142: aload_2
    //   143: ldc 156
    //   145: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: aload_2
    //   150: ldc 132
    //   152: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: pop
    //   156: aload_2
    //   157: ldc 134
    //   159: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload_2
    //   164: ldc 142
    //   166: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload_2
    //   171: ldc 138
    //   173: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: aload_2
    //   178: ldc 136
    //   180: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: aload_2
    //   185: ldc 138
    //   187: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: pop
    //   191: aload_2
    //   192: ldc 148
    //   194: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: aload_2
    //   199: ldc 150
    //   201: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload_2
    //   206: ldc 158
    //   208: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: pop
    //   212: aload_2
    //   213: ldc 150
    //   215: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: pop
    //   219: aload_2
    //   220: ldc 144
    //   222: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload_2
    //   227: ldc 146
    //   229: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: pop
    //   233: aload_2
    //   234: ldc 152
    //   236: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: pop
    //   240: aload_2
    //   241: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: iconst_0
    //   245: anewarray 39	java/lang/Object
    //   248: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   251: pop
    //   252: aload_1
    //   253: aload_2
    //   254: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: iconst_0
    //   258: anewarray 61	java/lang/String
    //   261: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   264: aload_2
    //   265: iconst_0
    //   266: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   269: aload_2
    //   270: ldc 160
    //   272: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: aload_2
    //   277: ldc 132
    //   279: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload_2
    //   284: ldc 162
    //   286: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: pop
    //   290: aload_2
    //   291: ldc 142
    //   293: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: pop
    //   297: aload_2
    //   298: ldc 150
    //   300: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: pop
    //   304: aload_2
    //   305: ldc 136
    //   307: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: pop
    //   311: aload_2
    //   312: ldc 138
    //   314: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: pop
    //   318: aload_2
    //   319: ldc 144
    //   321: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: aload_2
    //   326: ldc 146
    //   328: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: pop
    //   332: aload_2
    //   333: ldc 164
    //   335: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: pop
    //   339: aload_2
    //   340: ldc 166
    //   342: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: pop
    //   346: aload_2
    //   347: ldc 168
    //   349: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: pop
    //   353: aload_2
    //   354: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   357: iconst_0
    //   358: anewarray 39	java/lang/Object
    //   361: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   364: pop
    //   365: aload_1
    //   366: aload_2
    //   367: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   370: iconst_0
    //   371: anewarray 61	java/lang/String
    //   374: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   377: aload_2
    //   378: iconst_0
    //   379: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   382: aload_2
    //   383: ldc 170
    //   385: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: pop
    //   389: aload_2
    //   390: ldc 132
    //   392: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: pop
    //   396: aload_2
    //   397: ldc 134
    //   399: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: pop
    //   403: aload_2
    //   404: ldc 136
    //   406: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: pop
    //   410: aload_2
    //   411: ldc 138
    //   413: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: pop
    //   417: aload_2
    //   418: ldc 172
    //   420: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: pop
    //   424: aload_2
    //   425: ldc 150
    //   427: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: pop
    //   431: aload_2
    //   432: ldc 174
    //   434: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: pop
    //   438: aload_2
    //   439: ldc 138
    //   441: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: pop
    //   445: aload_2
    //   446: ldc 176
    //   448: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: pop
    //   452: aload_2
    //   453: ldc 138
    //   455: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: pop
    //   459: aload_2
    //   460: ldc 178
    //   462: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: pop
    //   466: aload_2
    //   467: ldc 138
    //   469: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: pop
    //   473: aload_2
    //   474: ldc 144
    //   476: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: pop
    //   480: aload_2
    //   481: ldc 146
    //   483: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload_2
    //   488: ldc 152
    //   490: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: pop
    //   494: aload_2
    //   495: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   498: iconst_0
    //   499: anewarray 39	java/lang/Object
    //   502: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   505: pop
    //   506: aload_1
    //   507: aload_2
    //   508: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: iconst_0
    //   512: anewarray 61	java/lang/String
    //   515: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   518: aload_2
    //   519: iconst_0
    //   520: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   523: aload_2
    //   524: ldc 180
    //   526: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: pop
    //   530: aload_2
    //   531: ldc 182
    //   533: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: pop
    //   537: aload_2
    //   538: ldc 184
    //   540: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: pop
    //   544: aload_2
    //   545: ldc 186
    //   547: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: pop
    //   551: aload_2
    //   552: ldc 188
    //   554: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: pop
    //   558: aload_2
    //   559: ldc 190
    //   561: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   564: pop
    //   565: aload_2
    //   566: ldc 192
    //   568: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: aload_2
    //   573: ldc 194
    //   575: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: pop
    //   579: aload_2
    //   580: ldc 196
    //   582: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   585: pop
    //   586: aload_2
    //   587: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   590: iconst_0
    //   591: anewarray 39	java/lang/Object
    //   594: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   597: pop
    //   598: aload_1
    //   599: aload_2
    //   600: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   603: iconst_0
    //   604: anewarray 61	java/lang/String
    //   607: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   610: aload_2
    //   611: iconst_0
    //   612: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   615: aload_2
    //   616: ldc 198
    //   618: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: pop
    //   622: aload_2
    //   623: ldc 182
    //   625: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: pop
    //   629: aload_2
    //   630: ldc 200
    //   632: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: pop
    //   636: aload_2
    //   637: ldc 202
    //   639: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   642: pop
    //   643: aload_2
    //   644: ldc 204
    //   646: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: pop
    //   650: aload_2
    //   651: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   654: iconst_0
    //   655: anewarray 39	java/lang/Object
    //   658: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   661: pop
    //   662: aload_1
    //   663: aload_2
    //   664: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   667: iconst_0
    //   668: anewarray 61	java/lang/String
    //   671: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   674: aload_2
    //   675: iconst_0
    //   676: invokevirtual 128	java/lang/StringBuilder:setLength	(I)V
    //   679: aload_2
    //   680: ldc 206
    //   682: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: pop
    //   686: aload_2
    //   687: ldc 132
    //   689: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   692: pop
    //   693: aload_2
    //   694: ldc 162
    //   696: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   699: pop
    //   700: aload_2
    //   701: ldc 142
    //   703: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: pop
    //   707: aload_2
    //   708: ldc 150
    //   710: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   713: pop
    //   714: aload_2
    //   715: ldc 136
    //   717: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   720: pop
    //   721: aload_2
    //   722: ldc 138
    //   724: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   727: pop
    //   728: aload_2
    //   729: ldc 144
    //   731: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   734: pop
    //   735: aload_2
    //   736: ldc 146
    //   738: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   741: pop
    //   742: aload_2
    //   743: ldc 164
    //   745: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   748: pop
    //   749: aload_2
    //   750: ldc 166
    //   752: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   755: pop
    //   756: aload_2
    //   757: ldc 168
    //   759: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: pop
    //   763: aload_2
    //   764: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   767: iconst_0
    //   768: anewarray 39	java/lang/Object
    //   771: invokestatic 154	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   774: pop
    //   775: aload_1
    //   776: aload_2
    //   777: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   780: iconst_0
    //   781: anewarray 61	java/lang/String
    //   784: invokevirtual 78	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   787: goto +20 -> 807
    //   790: astore 8
    //   792: goto +92 -> 884
    //   795: astore_3
    //   796: aload_3
    //   797: invokestatic 83	com/tencent/bugly/proguard/x:b	(Ljava/lang/Throwable;)Z
    //   800: ifne +7 -> 807
    //   803: aload_3
    //   804: invokevirtual 86	java/lang/Throwable:printStackTrace	()V
    //   807: aload_0
    //   808: getfield 56	com/tencent/bugly/proguard/q:d	Ljava/util/List;
    //   811: astore 4
    //   813: aload 4
    //   815: ifnonnull +6 -> 821
    //   818: aload_0
    //   819: monitorexit
    //   820: return
    //   821: aload_0
    //   822: getfield 56	com/tencent/bugly/proguard/q:d	Ljava/util/List;
    //   825: invokeinterface 212 1 0
    //   830: astore 5
    //   832: aload 5
    //   834: invokeinterface 218 1 0
    //   839: ifeq +42 -> 881
    //   842: aload 5
    //   844: invokeinterface 222 1 0
    //   849: checkcast 224	com/tencent/bugly/a
    //   852: astore 6
    //   854: aload 6
    //   856: aload_1
    //   857: invokevirtual 227	com/tencent/bugly/a:onDbCreate	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   860: goto -28 -> 832
    //   863: astore 7
    //   865: aload 7
    //   867: invokestatic 83	com/tencent/bugly/proguard/x:b	(Ljava/lang/Throwable;)Z
    //   870: ifne -38 -> 832
    //   873: aload 7
    //   875: invokevirtual 86	java/lang/Throwable:printStackTrace	()V
    //   878: goto -46 -> 832
    //   881: aload_0
    //   882: monitorexit
    //   883: return
    //   884: aload_0
    //   885: monitorexit
    //   886: aload 8
    //   888: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	787	790	finally
    //   796	813	790	finally
    //   821	854	790	finally
    //   854	860	790	finally
    //   865	878	790	finally
    //   2	787	795	java/lang/Throwable
    //   854	860	863	java/lang/Throwable
  }

  @TargetApi(11)
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      if (b.c() >= 11)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt1);
        arrayOfObject[1] = Integer.valueOf(paramInt2);
        x.d("[Database] Downgrade %d to %d drop tables.", arrayOfObject);
        if (this.d != null)
        {
          Iterator localIterator = this.d.iterator();
          while (localIterator.hasNext())
          {
            a locala = (a)localIterator.next();
            try
            {
              locala.onDbDowngrade(paramSQLiteDatabase, paramInt1, paramInt2);
            }
            catch (Throwable localThrowable)
            {
            }
            if (!x.b(localThrowable))
              localThrowable.printStackTrace();
          }
        }
        if (a(paramSQLiteDatabase))
        {
          onCreate(paramSQLiteDatabase);
          return;
        }
        x.d("[Database] Failed to drop, delete db.", new Object[0]);
        File localFile = this.c.getDatabasePath(a);
        if ((localFile != null) && (localFile.canWrite()))
          localFile.delete();
      }
      return;
    }
    finally
    {
    }
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      x.d("[Database] Upgrade %d to %d , drop tables!", arrayOfObject);
      if (this.d != null)
      {
        Iterator localIterator = this.d.iterator();
        while (localIterator.hasNext())
        {
          a locala = (a)localIterator.next();
          try
          {
            locala.onDbUpgrade(paramSQLiteDatabase, paramInt1, paramInt2);
          }
          catch (Throwable localThrowable)
          {
          }
          if (!x.b(localThrowable))
            localThrowable.printStackTrace();
        }
      }
      if (a(paramSQLiteDatabase))
      {
        onCreate(paramSQLiteDatabase);
        return;
      }
      x.d("[Database] Failed to drop, delete db.", new Object[0]);
      File localFile = this.c.getDatabasePath(a);
      if ((localFile != null) && (localFile.canWrite()))
        localFile.delete();
      return;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.q
 * JD-Core Version:    0.6.1
 */