package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.tencent.bugly.a;
import java.util.List;
import java.util.Map;

public final class p
{
  private static p a;
  private static q b;
  private static boolean c;

  private p(Context paramContext, List<a> paramList)
  {
    b = new q(paramContext, paramList);
  }

  // ERROR //
  private int a(String paramString1, String paramString2, String[] paramArrayOfString, o paramo)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   5: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore 11
    //   10: iconst_0
    //   11: istore 6
    //   13: aload 11
    //   15: ifnull +17 -> 32
    //   18: aload 11
    //   20: aload_1
    //   21: aload_2
    //   22: aload_3
    //   23: invokevirtual 38	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   26: istore 12
    //   28: iload 12
    //   30: istore 6
    //   32: aload 4
    //   34: ifnull +46 -> 80
    //   37: iload 6
    //   39: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   42: pop
    //   43: goto +37 -> 80
    //   46: astore 9
    //   48: goto +37 -> 85
    //   51: astore 5
    //   53: aload 5
    //   55: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   58: ifne +8 -> 66
    //   61: aload 5
    //   63: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   66: iconst_0
    //   67: istore 6
    //   69: aload 4
    //   71: ifnull +9 -> 80
    //   74: iconst_0
    //   75: istore 6
    //   77: goto -40 -> 37
    //   80: aload_0
    //   81: monitorexit
    //   82: iload 6
    //   84: ireturn
    //   85: aload 4
    //   87: ifnull +11 -> 98
    //   90: iconst_0
    //   91: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   94: pop
    //   95: goto +3 -> 98
    //   98: aload 9
    //   100: athrow
    //   101: aload_0
    //   102: monitorexit
    //   103: aload 7
    //   105: athrow
    //   106: astore 7
    //   108: goto -7 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   2	28	46	finally
    //   53	66	46	finally
    //   2	28	51	java/lang/Throwable
    //   37	43	106	finally
    //   90	101	106	finally
  }

  // ERROR //
  private long a(String paramString, ContentValues paramContentValues, o paramo)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lconst_0
    //   3: lstore 4
    //   5: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   8: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   11: astore 11
    //   13: aload 11
    //   15: ifnull +61 -> 76
    //   18: aload_2
    //   19: ifnull +57 -> 76
    //   22: aload 11
    //   24: aload_1
    //   25: ldc 58
    //   27: aload_2
    //   28: invokevirtual 62	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   31: lstore 12
    //   33: lload 12
    //   35: lload 4
    //   37: lcmp
    //   38: iflt +20 -> 58
    //   41: ldc 64
    //   43: iconst_1
    //   44: anewarray 4	java/lang/Object
    //   47: dup
    //   48: iconst_0
    //   49: aload_1
    //   50: aastore
    //   51: invokestatic 67	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   54: pop
    //   55: goto +17 -> 72
    //   58: ldc 69
    //   60: iconst_1
    //   61: anewarray 4	java/lang/Object
    //   64: dup
    //   65: iconst_0
    //   66: aload_1
    //   67: aastore
    //   68: invokestatic 72	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   71: pop
    //   72: lload 12
    //   74: lstore 4
    //   76: aload_3
    //   77: ifnull +39 -> 116
    //   80: lload 4
    //   82: invokestatic 77	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   85: pop
    //   86: goto +30 -> 116
    //   89: astore 9
    //   91: goto +30 -> 121
    //   94: astore 6
    //   96: aload 6
    //   98: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   101: ifne +8 -> 109
    //   104: aload 6
    //   106: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   109: aload_3
    //   110: ifnull +6 -> 116
    //   113: goto -33 -> 80
    //   116: aload_0
    //   117: monitorexit
    //   118: lload 4
    //   120: lreturn
    //   121: aload_3
    //   122: ifnull +12 -> 134
    //   125: lload 4
    //   127: invokestatic 77	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   130: pop
    //   131: goto +3 -> 134
    //   134: aload 9
    //   136: athrow
    //   137: aload_0
    //   138: monitorexit
    //   139: aload 7
    //   141: athrow
    //   142: astore 7
    //   144: goto -7 -> 137
    //
    // Exception table:
    //   from	to	target	type
    //   5	72	89	finally
    //   96	109	89	finally
    //   5	72	94	java/lang/Throwable
    //   80	86	142	finally
    //   125	137	142	finally
  }

  // ERROR //
  private Cursor a(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6, o paramo)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   5: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore 16
    //   10: aconst_null
    //   11: astore 13
    //   13: aload 16
    //   15: ifnull +59 -> 74
    //   18: aload 16
    //   20: iload_1
    //   21: aload_2
    //   22: aload_3
    //   23: aload 4
    //   25: aload 5
    //   27: aload 6
    //   29: aload 7
    //   31: aload 8
    //   33: aload 9
    //   35: invokevirtual 85	android/database/sqlite/SQLiteDatabase:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: astore 17
    //   40: aload 17
    //   42: astore 13
    //   44: goto +30 -> 74
    //   47: astore 14
    //   49: goto +30 -> 79
    //   52: astore 11
    //   54: aload 11
    //   56: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   59: istore 12
    //   61: aconst_null
    //   62: astore 13
    //   64: iload 12
    //   66: ifne +8 -> 74
    //   69: aload 11
    //   71: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   74: aload_0
    //   75: monitorexit
    //   76: aload 13
    //   78: areturn
    //   79: aload 14
    //   81: athrow
    //   82: astore 15
    //   84: aload_0
    //   85: monitorexit
    //   86: aload 15
    //   88: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	40	47	finally
    //   54	74	47	finally
    //   2	40	52	java/lang/Throwable
    //   79	82	82	finally
  }

  public static p a()
  {
    try
    {
      p localp = a;
      return localp;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static p a(Context paramContext, List<a> paramList)
  {
    try
    {
      if (a == null)
        a = new p(paramContext, paramList);
      p localp = a;
      return localp;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static r a(Cursor paramCursor)
  {
    if (paramCursor == null)
      return null;
    try
    {
      r localr = new r();
      localr.a = paramCursor.getLong(paramCursor.getColumnIndex("_id"));
      localr.b = paramCursor.getInt(paramCursor.getColumnIndex("_tp"));
      localr.c = paramCursor.getString(paramCursor.getColumnIndex("_pc"));
      localr.d = paramCursor.getString(paramCursor.getColumnIndex("_th"));
      localr.e = paramCursor.getLong(paramCursor.getColumnIndex("_tm"));
      localr.g = paramCursor.getBlob(paramCursor.getColumnIndex("_dt"));
      return localr;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  private Map<String, byte[]> a(int paramInt, o paramo)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: iload_1
    //   4: invokespecial 148	com/tencent/bugly/proguard/p:c	(I)Ljava/util/List;
    //   7: astore 6
    //   9: aconst_null
    //   10: astore_3
    //   11: aload 6
    //   13: ifnull +107 -> 120
    //   16: new 150	java/util/HashMap
    //   19: dup
    //   20: invokespecial 151	java/util/HashMap:<init>	()V
    //   23: astore 7
    //   25: aload 6
    //   27: invokeinterface 157 1 0
    //   32: astore 8
    //   34: aload 8
    //   36: invokeinterface 163 1 0
    //   41: ifeq +45 -> 86
    //   44: aload 8
    //   46: invokeinterface 167 1 0
    //   51: checkcast 93	com/tencent/bugly/proguard/r
    //   54: astore 9
    //   56: aload 9
    //   58: getfield 144	com/tencent/bugly/proguard/r:g	[B
    //   61: astore 10
    //   63: aload 10
    //   65: ifnull -31 -> 34
    //   68: aload 7
    //   70: aload 9
    //   72: getfield 170	com/tencent/bugly/proguard/r:f	Ljava/lang/String;
    //   75: aload 10
    //   77: invokeinterface 176 3 0
    //   82: pop
    //   83: goto -49 -> 34
    //   86: aload 7
    //   88: astore_3
    //   89: goto +31 -> 120
    //   92: astore 4
    //   94: aload 7
    //   96: astore_3
    //   97: goto +10 -> 107
    //   100: astore 5
    //   102: goto +20 -> 122
    //   105: astore 4
    //   107: aload 4
    //   109: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   112: ifne +8 -> 120
    //   115: aload 4
    //   117: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   120: aload_3
    //   121: areturn
    //   122: aload 5
    //   124: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   25	83	92	java/lang/Throwable
    //   2	25	100	finally
    //   25	83	100	finally
    //   107	120	100	finally
    //   2	25	105	java/lang/Throwable
  }

  // ERROR //
  private boolean a(int paramInt, String paramString, o paramo)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   5: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore 10
    //   10: iconst_0
    //   11: istore 5
    //   13: aload 10
    //   15: ifnull +146 -> 161
    //   18: aload_2
    //   19: invokestatic 185	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;)Z
    //   22: ifeq +31 -> 53
    //   25: new 187	java/lang/StringBuilder
    //   28: dup
    //   29: ldc 189
    //   31: invokespecial 192	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   34: astore 11
    //   36: aload 11
    //   38: iload_1
    //   39: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload 11
    //   45: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: astore 13
    //   50: goto +59 -> 109
    //   53: new 187	java/lang/StringBuilder
    //   56: dup
    //   57: ldc 189
    //   59: invokespecial 192	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   62: astore 17
    //   64: aload 17
    //   66: iload_1
    //   67: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload 17
    //   73: ldc 202
    //   75: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload 17
    //   81: ldc 207
    //   83: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload 17
    //   89: aload_2
    //   90: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload 17
    //   96: ldc 209
    //   98: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload 17
    //   104: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: astore 13
    //   109: aload 10
    //   111: ldc 211
    //   113: aload 13
    //   115: aconst_null
    //   116: invokevirtual 38	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   119: istore 14
    //   121: iconst_2
    //   122: anewarray 4	java/lang/Object
    //   125: astore 15
    //   127: aload 15
    //   129: iconst_0
    //   130: ldc 211
    //   132: aastore
    //   133: aload 15
    //   135: iconst_1
    //   136: iload 14
    //   138: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   141: aastore
    //   142: ldc 213
    //   144: aload 15
    //   146: invokestatic 67	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   149: pop
    //   150: iconst_0
    //   151: istore 5
    //   153: iload 14
    //   155: ifle +6 -> 161
    //   158: iconst_1
    //   159: istore 5
    //   161: aload_3
    //   162: ifnull +45 -> 207
    //   165: iload 5
    //   167: invokestatic 218	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   170: pop
    //   171: goto +36 -> 207
    //   174: astore 8
    //   176: goto +36 -> 212
    //   179: astore 4
    //   181: aload 4
    //   183: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   186: ifne +8 -> 194
    //   189: aload 4
    //   191: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   194: iconst_0
    //   195: istore 5
    //   197: aload_3
    //   198: ifnull +9 -> 207
    //   201: iconst_0
    //   202: istore 5
    //   204: goto -39 -> 165
    //   207: aload_0
    //   208: monitorexit
    //   209: iload 5
    //   211: ireturn
    //   212: aload_3
    //   213: ifnull +11 -> 224
    //   216: iconst_0
    //   217: invokestatic 218	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   220: pop
    //   221: goto +3 -> 224
    //   224: aload 8
    //   226: athrow
    //   227: aload_0
    //   228: monitorexit
    //   229: aload 6
    //   231: athrow
    //   232: astore 6
    //   234: goto -7 -> 227
    //
    // Exception table:
    //   from	to	target	type
    //   2	150	174	finally
    //   181	194	174	finally
    //   2	150	179	java/lang/Throwable
    //   165	171	232	finally
    //   216	227	232	finally
  }

  // ERROR //
  private boolean a(int paramInt, String paramString, byte[] paramArrayOfByte, o paramo)
  {
    // Byte code:
    //   0: new 93	com/tencent/bugly/proguard/r
    //   3: dup
    //   4: invokespecial 94	com/tencent/bugly/proguard/r:<init>	()V
    //   7: astore 5
    //   9: aload 5
    //   11: iload_1
    //   12: i2l
    //   13: putfield 107	com/tencent/bugly/proguard/r:a	J
    //   16: aload 5
    //   18: aload_2
    //   19: putfield 170	com/tencent/bugly/proguard/r:f	Ljava/lang/String;
    //   22: aload 5
    //   24: invokestatic 225	java/lang/System:currentTimeMillis	()J
    //   27: putfield 134	com/tencent/bugly/proguard/r:e	J
    //   30: aload 5
    //   32: aload_3
    //   33: putfield 144	com/tencent/bugly/proguard/r:g	[B
    //   36: aload_0
    //   37: aload 5
    //   39: invokespecial 228	com/tencent/bugly/proguard/p:b	(Lcom/tencent/bugly/proguard/r;)Z
    //   42: istore 7
    //   44: aload 4
    //   46: ifnull +45 -> 91
    //   49: iload 7
    //   51: invokestatic 218	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   54: pop
    //   55: goto +36 -> 91
    //   58: astore 9
    //   60: goto +34 -> 94
    //   63: astore 6
    //   65: aload 6
    //   67: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   70: ifne +8 -> 78
    //   73: aload 6
    //   75: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   78: aload 4
    //   80: ifnull +8 -> 88
    //   83: iconst_0
    //   84: invokestatic 218	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   87: pop
    //   88: iconst_0
    //   89: istore 7
    //   91: iload 7
    //   93: ireturn
    //   94: aload 4
    //   96: ifnull +8 -> 104
    //   99: iconst_0
    //   100: invokestatic 218	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   103: pop
    //   104: aload 9
    //   106: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	44	58	finally
    //   65	78	58	finally
    //   0	44	63	java/lang/Throwable
  }

  private static r b(Cursor paramCursor)
  {
    if (paramCursor == null)
      return null;
    try
    {
      r localr = new r();
      localr.a = paramCursor.getLong(paramCursor.getColumnIndex("_id"));
      localr.e = paramCursor.getLong(paramCursor.getColumnIndex("_tm"));
      localr.f = paramCursor.getString(paramCursor.getColumnIndex("_tp"));
      localr.g = paramCursor.getBlob(paramCursor.getColumnIndex("_dt"));
      return localr;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  private boolean b(r paramr)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull +7 -> 10
    //   6: aload_0
    //   7: monitorexit
    //   8: iconst_0
    //   9: ireturn
    //   10: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   13: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 5
    //   18: aload 5
    //   20: ifnull +63 -> 83
    //   23: aload_1
    //   24: invokestatic 237	com/tencent/bugly/proguard/p:d	(Lcom/tencent/bugly/proguard/r;)Landroid/content/ContentValues;
    //   27: astore 6
    //   29: aload 6
    //   31: ifnull +52 -> 83
    //   34: aload 5
    //   36: ldc 211
    //   38: ldc 58
    //   40: aload 6
    //   42: invokevirtual 62	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   45: lstore 7
    //   47: lload 7
    //   49: lconst_0
    //   50: lcmp
    //   51: iflt +28 -> 79
    //   54: ldc 64
    //   56: iconst_1
    //   57: anewarray 4	java/lang/Object
    //   60: dup
    //   61: iconst_0
    //   62: ldc 211
    //   64: aastore
    //   65: invokestatic 67	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   68: pop
    //   69: aload_1
    //   70: lload 7
    //   72: putfield 107	com/tencent/bugly/proguard/r:a	J
    //   75: aload_0
    //   76: monitorexit
    //   77: iconst_1
    //   78: ireturn
    //   79: aload_0
    //   80: monitorexit
    //   81: iconst_0
    //   82: ireturn
    //   83: aload_0
    //   84: monitorexit
    //   85: iconst_0
    //   86: ireturn
    //   87: astore_3
    //   88: goto +19 -> 107
    //   91: astore_2
    //   92: aload_2
    //   93: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   96: ifne +7 -> 103
    //   99: aload_2
    //   100: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   103: aload_0
    //   104: monitorexit
    //   105: iconst_0
    //   106: ireturn
    //   107: aload_3
    //   108: athrow
    //   109: astore 4
    //   111: aload_0
    //   112: monitorexit
    //   113: aload 4
    //   115: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	75	87	finally
    //   92	103	87	finally
    //   10	75	91	java/lang/Throwable
    //   107	109	109	finally
  }

  private static ContentValues c(r paramr)
  {
    if (paramr == null)
      return null;
    try
    {
      ContentValues localContentValues = new ContentValues();
      if (paramr.a > 0L)
        localContentValues.put("_id", Long.valueOf(paramr.a));
      localContentValues.put("_tp", Integer.valueOf(paramr.b));
      localContentValues.put("_pc", paramr.c);
      localContentValues.put("_th", paramr.d);
      localContentValues.put("_tm", Long.valueOf(paramr.e));
      if (paramr.g != null)
        localContentValues.put("_dt", paramr.g);
      return localContentValues;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  // ERROR //
  private List<r> c(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   5: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore 6
    //   10: aload 6
    //   12: ifnull +300 -> 312
    //   15: new 187	java/lang/StringBuilder
    //   18: dup
    //   19: ldc 189
    //   21: invokespecial 192	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   24: astore 7
    //   26: aload 7
    //   28: iload_1
    //   29: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload 7
    //   35: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: astore 9
    //   40: aload 6
    //   42: ldc 211
    //   44: aconst_null
    //   45: aload 9
    //   47: aconst_null
    //   48: aconst_null
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual 255	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_3
    //   55: aload_3
    //   56: ifnonnull +17 -> 73
    //   59: aload_3
    //   60: ifnull +9 -> 69
    //   63: aload_3
    //   64: invokeinterface 258 1 0
    //   69: aload_0
    //   70: monitorexit
    //   71: aconst_null
    //   72: areturn
    //   73: new 187	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 259	java/lang/StringBuilder:<init>	()V
    //   80: astore 10
    //   82: new 261	java/util/ArrayList
    //   85: dup
    //   86: invokespecial 262	java/util/ArrayList:<init>	()V
    //   89: astore 11
    //   91: aload_3
    //   92: invokeinterface 265 1 0
    //   97: ifeq +86 -> 183
    //   100: aload_3
    //   101: invokestatic 267	com/tencent/bugly/proguard/p:b	(Landroid/database/Cursor;)Lcom/tencent/bugly/proguard/r;
    //   104: astore 18
    //   106: aload 18
    //   108: ifnull +16 -> 124
    //   111: aload 11
    //   113: aload 18
    //   115: invokeinterface 271 2 0
    //   120: pop
    //   121: goto -30 -> 91
    //   124: aload_3
    //   125: aload_3
    //   126: ldc 109
    //   128: invokeinterface 100 2 0
    //   133: invokeinterface 122 2 0
    //   138: astore 20
    //   140: aload 10
    //   142: ldc_w 273
    //   145: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: aload 10
    //   151: ldc_w 275
    //   154: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: aload 10
    //   160: aload 20
    //   162: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: goto -75 -> 91
    //   169: ldc_w 277
    //   172: iconst_0
    //   173: anewarray 4	java/lang/Object
    //   176: invokestatic 72	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   179: pop
    //   180: goto -89 -> 91
    //   183: aload 10
    //   185: invokevirtual 281	java/lang/StringBuilder:length	()I
    //   188: ifle +74 -> 262
    //   191: aload 10
    //   193: ldc_w 283
    //   196: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: pop
    //   200: aload 10
    //   202: ldc_w 275
    //   205: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload 10
    //   211: iload_1
    //   212: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload 6
    //   218: ldc 211
    //   220: aload 9
    //   222: iconst_4
    //   223: invokevirtual 288	java/lang/String:substring	(I)Ljava/lang/String;
    //   226: aconst_null
    //   227: invokevirtual 38	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   230: istore 15
    //   232: iconst_2
    //   233: anewarray 4	java/lang/Object
    //   236: astore 16
    //   238: aload 16
    //   240: iconst_0
    //   241: ldc 211
    //   243: aastore
    //   244: aload 16
    //   246: iconst_1
    //   247: iload 15
    //   249: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   252: aastore
    //   253: ldc_w 290
    //   256: aload 16
    //   258: invokestatic 72	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   261: pop
    //   262: aload_3
    //   263: ifnull +9 -> 272
    //   266: aload_3
    //   267: invokeinterface 258 1 0
    //   272: aload_0
    //   273: monitorexit
    //   274: aload 11
    //   276: areturn
    //   277: astore_2
    //   278: goto +13 -> 291
    //   281: astore 4
    //   283: aconst_null
    //   284: astore_3
    //   285: goto +33 -> 318
    //   288: astore_2
    //   289: aconst_null
    //   290: astore_3
    //   291: aload_2
    //   292: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   295: ifne +7 -> 302
    //   298: aload_2
    //   299: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   302: aload_3
    //   303: ifnull +9 -> 312
    //   306: aload_3
    //   307: invokeinterface 258 1 0
    //   312: aload_0
    //   313: monitorexit
    //   314: aconst_null
    //   315: areturn
    //   316: astore 4
    //   318: aload_3
    //   319: ifnull +12 -> 331
    //   322: aload_3
    //   323: invokeinterface 258 1 0
    //   328: goto +3 -> 331
    //   331: aload 4
    //   333: athrow
    //   334: aload_0
    //   335: monitorexit
    //   336: aload 5
    //   338: athrow
    //   339: astore 5
    //   341: goto -7 -> 334
    //
    // Exception table:
    //   from	to	target	type
    //   124	166	169	java/lang/Throwable
    //   73	121	277	java/lang/Throwable
    //   169	262	277	java/lang/Throwable
    //   2	55	281	finally
    //   2	55	288	java/lang/Throwable
    //   73	121	316	finally
    //   124	166	316	finally
    //   169	262	316	finally
    //   291	302	316	finally
    //   63	69	339	finally
    //   266	272	339	finally
    //   306	312	339	finally
    //   322	334	339	finally
  }

  private static ContentValues d(r paramr)
  {
    if ((paramr != null) && (!z.a(paramr.f)))
      try
      {
        ContentValues localContentValues = new ContentValues();
        if (paramr.a > 0L)
          localContentValues.put("_id", Long.valueOf(paramr.a));
        localContentValues.put("_tp", paramr.f);
        localContentValues.put("_tm", Long.valueOf(paramr.e));
        if (paramr.g != null)
          localContentValues.put("_dt", paramr.g);
        return localContentValues;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    return null;
  }

  public final int a(String paramString1, String paramString2, String[] paramArrayOfString, o paramo, boolean paramBoolean)
  {
    return a(paramString1, paramString2, null, null);
  }

  public final long a(String paramString, ContentValues paramContentValues, o paramo, boolean paramBoolean)
  {
    return a(paramString, paramContentValues, null);
  }

  public final Cursor a(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, o paramo, boolean paramBoolean)
  {
    return a(false, paramString1, paramArrayOfString1, paramString2, null, null, null, null, null, null);
  }

  // ERROR //
  public final List<r> a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   5: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore_3
    //   9: aload_3
    //   10: ifnull +311 -> 321
    //   13: iload_1
    //   14: iflt +340 -> 354
    //   17: new 187	java/lang/StringBuilder
    //   20: dup
    //   21: ldc_w 295
    //   24: invokespecial 192	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   27: astore 24
    //   29: aload 24
    //   31: iload_1
    //   32: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload 24
    //   38: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: astore 4
    //   43: goto +3 -> 46
    //   46: aload_3
    //   47: ldc_w 297
    //   50: aconst_null
    //   51: aload 4
    //   53: aconst_null
    //   54: aconst_null
    //   55: aconst_null
    //   56: aconst_null
    //   57: invokevirtual 255	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   60: astore 5
    //   62: aload 5
    //   64: ifnonnull +19 -> 83
    //   67: aload 5
    //   69: ifnull +10 -> 79
    //   72: aload 5
    //   74: invokeinterface 258 1 0
    //   79: aload_0
    //   80: monitorexit
    //   81: aconst_null
    //   82: areturn
    //   83: new 187	java/lang/StringBuilder
    //   86: dup
    //   87: invokespecial 259	java/lang/StringBuilder:<init>	()V
    //   90: astore 6
    //   92: new 261	java/util/ArrayList
    //   95: dup
    //   96: invokespecial 262	java/util/ArrayList:<init>	()V
    //   99: astore 7
    //   101: aload 5
    //   103: invokeinterface 265 1 0
    //   108: ifeq +89 -> 197
    //   111: aload 5
    //   113: invokestatic 299	com/tencent/bugly/proguard/p:a	(Landroid/database/Cursor;)Lcom/tencent/bugly/proguard/r;
    //   116: astore 14
    //   118: aload 14
    //   120: ifnull +16 -> 136
    //   123: aload 7
    //   125: aload 14
    //   127: invokeinterface 271 2 0
    //   132: pop
    //   133: goto -32 -> 101
    //   136: aload 5
    //   138: aload 5
    //   140: ldc 58
    //   142: invokeinterface 100 2 0
    //   147: invokeinterface 104 2 0
    //   152: lstore 16
    //   154: aload 6
    //   156: ldc_w 301
    //   159: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload 6
    //   165: ldc_w 275
    //   168: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload 6
    //   174: lload 16
    //   176: invokevirtual 304	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: goto -79 -> 101
    //   183: ldc_w 277
    //   186: iconst_0
    //   187: anewarray 4	java/lang/Object
    //   190: invokestatic 72	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   193: pop
    //   194: goto -93 -> 101
    //   197: aload 6
    //   199: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   202: astore 10
    //   204: aload 10
    //   206: invokevirtual 305	java/lang/String:length	()I
    //   209: ifle +50 -> 259
    //   212: aload_3
    //   213: ldc_w 297
    //   216: aload 10
    //   218: iconst_4
    //   219: invokevirtual 288	java/lang/String:substring	(I)Ljava/lang/String;
    //   222: aconst_null
    //   223: invokevirtual 38	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   226: istore 11
    //   228: iconst_2
    //   229: anewarray 4	java/lang/Object
    //   232: astore 12
    //   234: aload 12
    //   236: iconst_0
    //   237: ldc_w 297
    //   240: aastore
    //   241: aload 12
    //   243: iconst_1
    //   244: iload 11
    //   246: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   249: aastore
    //   250: ldc_w 307
    //   253: aload 12
    //   255: invokestatic 72	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   258: pop
    //   259: aload 5
    //   261: ifnull +10 -> 271
    //   264: aload 5
    //   266: invokeinterface 258 1 0
    //   271: aload_0
    //   272: monitorexit
    //   273: aload 7
    //   275: areturn
    //   276: astore 9
    //   278: aload 9
    //   280: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   283: ifne +8 -> 291
    //   286: aload 9
    //   288: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   291: aload 5
    //   293: ifnull +28 -> 321
    //   296: aload 5
    //   298: invokeinterface 258 1 0
    //   303: goto +18 -> 321
    //   306: aload 5
    //   308: ifnull +10 -> 318
    //   311: aload 5
    //   313: invokeinterface 258 1 0
    //   318: aload 8
    //   320: athrow
    //   321: aload_0
    //   322: monitorexit
    //   323: aconst_null
    //   324: areturn
    //   325: astore_2
    //   326: aload_0
    //   327: monitorexit
    //   328: aload_2
    //   329: athrow
    //   330: astore 23
    //   332: aload 23
    //   334: astore 8
    //   336: aconst_null
    //   337: astore 5
    //   339: goto -33 -> 306
    //   342: astore 22
    //   344: aload 22
    //   346: astore 9
    //   348: aconst_null
    //   349: astore 5
    //   351: goto -73 -> 278
    //   354: aconst_null
    //   355: astore 4
    //   357: goto -311 -> 46
    //   360: astore 8
    //   362: goto -56 -> 306
    //
    // Exception table:
    //   from	to	target	type
    //   136	180	183	java/lang/Throwable
    //   83	133	276	java/lang/Throwable
    //   183	259	276	java/lang/Throwable
    //   2	9	325	finally
    //   72	79	325	finally
    //   264	271	325	finally
    //   296	321	325	finally
    //   17	62	330	finally
    //   17	62	342	java/lang/Throwable
    //   83	133	360	finally
    //   136	180	360	finally
    //   183	259	360	finally
    //   278	291	360	finally
  }

  public final Map<String, byte[]> a(int paramInt, o paramo, boolean paramBoolean)
  {
    return a(paramInt, null);
  }

  // ERROR //
  public final void a(List<r> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +205 -> 208
    //   6: aload_1
    //   7: invokeinterface 312 1 0
    //   12: ifne +6 -> 18
    //   15: goto +193 -> 208
    //   18: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   21: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   24: astore_3
    //   25: aload_3
    //   26: ifnull +174 -> 200
    //   29: new 187	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 259	java/lang/StringBuilder:<init>	()V
    //   36: astore 4
    //   38: aload_1
    //   39: invokeinterface 157 1 0
    //   44: astore 5
    //   46: aload 5
    //   48: invokeinterface 163 1 0
    //   53: ifeq +47 -> 100
    //   56: aload 5
    //   58: invokeinterface 167 1 0
    //   63: checkcast 93	com/tencent/bugly/proguard/r
    //   66: astore 12
    //   68: aload 4
    //   70: ldc_w 301
    //   73: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload 4
    //   79: ldc_w 275
    //   82: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload 4
    //   88: aload 12
    //   90: getfield 107	com/tencent/bugly/proguard/r:a	J
    //   93: invokevirtual 304	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: goto -51 -> 46
    //   100: aload 4
    //   102: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: astore 6
    //   107: aload 6
    //   109: invokevirtual 305	java/lang/String:length	()I
    //   112: ifle +11 -> 123
    //   115: aload 6
    //   117: iconst_4
    //   118: invokevirtual 288	java/lang/String:substring	(I)Ljava/lang/String;
    //   121: astore 6
    //   123: aload 4
    //   125: iconst_0
    //   126: invokevirtual 316	java/lang/StringBuilder:setLength	(I)V
    //   129: aload_3
    //   130: ldc_w 297
    //   133: aload 6
    //   135: aconst_null
    //   136: invokevirtual 38	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   139: istore 9
    //   141: iconst_2
    //   142: anewarray 4	java/lang/Object
    //   145: astore 10
    //   147: aload 10
    //   149: iconst_0
    //   150: ldc_w 297
    //   153: aastore
    //   154: aload 10
    //   156: iconst_1
    //   157: iload 9
    //   159: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   162: aastore
    //   163: ldc 213
    //   165: aload 10
    //   167: invokestatic 67	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   170: pop
    //   171: aload_0
    //   172: monitorexit
    //   173: return
    //   174: astore 8
    //   176: goto +21 -> 197
    //   179: astore 7
    //   181: aload 7
    //   183: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   186: ifne +8 -> 194
    //   189: aload 7
    //   191: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   194: aload_0
    //   195: monitorexit
    //   196: return
    //   197: aload 8
    //   199: athrow
    //   200: aload_0
    //   201: monitorexit
    //   202: return
    //   203: astore_2
    //   204: aload_0
    //   205: monitorexit
    //   206: aload_2
    //   207: athrow
    //   208: aload_0
    //   209: monitorexit
    //   210: return
    //
    // Exception table:
    //   from	to	target	type
    //   129	171	174	finally
    //   181	194	174	finally
    //   129	171	179	java/lang/Throwable
    //   6	129	203	finally
    //   197	200	203	finally
  }

  public final boolean a(int paramInt, String paramString, o paramo, boolean paramBoolean)
  {
    return a(555, paramString, null);
  }

  public final boolean a(int paramInt, String paramString, byte[] paramArrayOfByte, o paramo, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      a locala = new a(4, null);
      locala.a(paramInt, paramString, paramArrayOfByte);
      w.a().a(locala);
      return true;
    }
    return a(paramInt, paramString, paramArrayOfByte, null);
  }

  // ERROR //
  public final boolean a(r paramr)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull +7 -> 10
    //   6: aload_0
    //   7: monitorexit
    //   8: iconst_0
    //   9: ireturn
    //   10: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   13: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 5
    //   18: aload 5
    //   20: ifnull +65 -> 85
    //   23: aload_1
    //   24: invokestatic 336	com/tencent/bugly/proguard/p:c	(Lcom/tencent/bugly/proguard/r;)Landroid/content/ContentValues;
    //   27: astore 6
    //   29: aload 6
    //   31: ifnull +54 -> 85
    //   34: aload 5
    //   36: ldc_w 297
    //   39: ldc 58
    //   41: aload 6
    //   43: invokevirtual 62	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   46: lstore 7
    //   48: lload 7
    //   50: lconst_0
    //   51: lcmp
    //   52: iflt +29 -> 81
    //   55: ldc 64
    //   57: iconst_1
    //   58: anewarray 4	java/lang/Object
    //   61: dup
    //   62: iconst_0
    //   63: ldc_w 297
    //   66: aastore
    //   67: invokestatic 67	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   70: pop
    //   71: aload_1
    //   72: lload 7
    //   74: putfield 107	com/tencent/bugly/proguard/r:a	J
    //   77: aload_0
    //   78: monitorexit
    //   79: iconst_1
    //   80: ireturn
    //   81: aload_0
    //   82: monitorexit
    //   83: iconst_0
    //   84: ireturn
    //   85: aload_0
    //   86: monitorexit
    //   87: iconst_0
    //   88: ireturn
    //   89: astore_3
    //   90: goto +19 -> 109
    //   93: astore_2
    //   94: aload_2
    //   95: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   98: ifne +7 -> 105
    //   101: aload_2
    //   102: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   105: aload_0
    //   106: monitorexit
    //   107: iconst_0
    //   108: ireturn
    //   109: aload_3
    //   110: athrow
    //   111: astore 4
    //   113: aload_0
    //   114: monitorexit
    //   115: aload 4
    //   117: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	77	89	finally
    //   94	105	89	finally
    //   10	77	93	java/lang/Throwable
    //   109	111	111	finally
  }

  // ERROR //
  public final void b(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 22	com/tencent/bugly/proguard/p:b	Lcom/tencent/bugly/proguard/q;
    //   5: invokevirtual 32	com/tencent/bugly/proguard/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore_3
    //   9: aload_3
    //   10: ifnull +100 -> 110
    //   13: iload_1
    //   14: iflt +114 -> 128
    //   17: new 187	java/lang/StringBuilder
    //   20: dup
    //   21: ldc_w 295
    //   24: invokespecial 192	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   27: astore 10
    //   29: aload 10
    //   31: iload_1
    //   32: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload 10
    //   38: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: astore 4
    //   43: goto +3 -> 46
    //   46: aload_3
    //   47: ldc_w 297
    //   50: aload 4
    //   52: aconst_null
    //   53: invokevirtual 38	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   56: istore 5
    //   58: iconst_2
    //   59: anewarray 4	java/lang/Object
    //   62: astore 6
    //   64: aload 6
    //   66: iconst_0
    //   67: ldc_w 297
    //   70: aastore
    //   71: aload 6
    //   73: iconst_1
    //   74: iload 5
    //   76: invokestatic 44	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   79: aastore
    //   80: ldc 213
    //   82: aload 6
    //   84: invokestatic 67	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   87: pop
    //   88: aload_0
    //   89: monitorexit
    //   90: return
    //   91: aload 8
    //   93: invokestatic 49	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   96: ifne +8 -> 104
    //   99: aload 8
    //   101: invokevirtual 52	java/lang/Throwable:printStackTrace	()V
    //   104: aload_0
    //   105: monitorexit
    //   106: return
    //   107: aload 9
    //   109: athrow
    //   110: aload_0
    //   111: monitorexit
    //   112: return
    //   113: astore_2
    //   114: aload_0
    //   115: monitorexit
    //   116: aload_2
    //   117: athrow
    //   118: astore 9
    //   120: goto -13 -> 107
    //   123: astore 8
    //   125: goto -34 -> 91
    //   128: aconst_null
    //   129: astore 4
    //   131: goto -85 -> 46
    //
    // Exception table:
    //   from	to	target	type
    //   2	9	113	finally
    //   107	110	113	finally
    //   17	88	118	finally
    //   91	104	118	finally
    //   17	88	123	java/lang/Throwable
  }

  final class a extends Thread
  {
    private int a;
    private o b;
    private String c;
    private ContentValues d;
    private boolean e;
    private String[] f;
    private String g;
    private String[] h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String[] n;
    private int o;
    private String p;
    private byte[] q;

    public a(int paramo, o arg3)
    {
      this.a = paramo;
      Object localObject;
      this.b = localObject;
    }

    public final void a(int paramInt, String paramString, byte[] paramArrayOfByte)
    {
      this.o = paramInt;
      this.p = paramString;
      this.q = paramArrayOfByte;
    }

    public final void a(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
    {
      this.e = paramBoolean;
      this.c = paramString1;
      this.f = paramArrayOfString1;
      this.g = paramString2;
      this.h = paramArrayOfString2;
      this.i = paramString3;
      this.j = paramString4;
      this.k = paramString5;
      this.l = paramString6;
    }

    public final void run()
    {
      switch (this.a)
      {
      default:
        break;
      case 6:
        p.a(p.this, this.o, this.p, this.b);
        break;
      case 5:
        p.a(p.this, this.o, this.b);
        return;
      case 4:
        p.a(p.this, this.o, this.p, this.q, this.b);
        return;
      case 3:
        p.a(p.this, this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
        return;
      case 2:
        p.a(p.this, this.c, this.m, this.n, this.b);
        return;
      case 1:
        p.a(p.this, this.c, this.d, this.b);
        return;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.p
 * JD-Core Version:    0.6.1
 */