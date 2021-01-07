package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class y
{
  public static boolean a = true;
  private static SimpleDateFormat b;
  private static int c = 5120;
  private static StringBuilder d;
  private static StringBuilder e;
  private static boolean f;
  private static a g;
  private static String h;
  private static String i;
  private static Context j;
  private static String k;
  private static boolean l;
  private static int m;
  private static final Object n = new Object();

  // ERROR //
  static
  {
    // Byte code:
    //   0: new 4	java/lang/Object
    //   3: dup
    //   4: invokespecial 35	java/lang/Object:<init>	()V
    //   7: putstatic 37	com/tencent/bugly/proguard/y:n	Ljava/lang/Object;
    //   10: new 39	java/text/SimpleDateFormat
    //   13: dup
    //   14: ldc 41
    //   16: invokespecial 44	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   19: putstatic 46	com/tencent/bugly/proguard/y:b	Ljava/text/SimpleDateFormat;
    //   22: return
    //   23: return
    //
    // Exception table:
    //   from	to	target	type
    //   10	22	23	java/lang/Throwable
  }

  public static void a(int paramInt)
  {
    synchronized (n)
    {
      c = paramInt;
      if (paramInt < 0)
        c = 0;
      else if (paramInt > 10240)
        c = 10240;
      return;
    }
  }

  public static void a(Context paramContext)
  {
    try
    {
      if ((!l) && (paramContext != null))
      {
        boolean bool = a;
        if (bool)
        {
          try
          {
            e = new StringBuilder(0);
            d = new StringBuilder(0);
            j = paramContext;
            com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.a(paramContext);
            h = locala.d;
            locala.getClass();
            i = "";
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(j.getFilesDir().getPath());
            localStringBuilder.append("/buglylog_");
            localStringBuilder.append(h);
            localStringBuilder.append("_");
            localStringBuilder.append(i);
            localStringBuilder.append(".txt");
            k = localStringBuilder.toString();
            m = Process.myPid();
          }
          catch (Throwable localThrowable)
          {
          }
          l = true;
          return;
        }
      }
      return;
    }
    finally
    {
    }
  }

  public static void a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if ((l) && (a))
      {
        b(paramString1, paramString2, paramString3);
        long l1 = Process.myTid();
        d.setLength(0);
        if (paramString3.length() > 30720)
          paramString3 = paramString3.substring(paramString3.length() - 30720, paramString3.length() - 1);
        Date localDate = new Date();
        String str1;
        if (b != null)
          str1 = b.format(localDate);
        else
          str1 = localDate.toString();
        StringBuilder localStringBuilder = d;
        localStringBuilder.append(str1);
        localStringBuilder.append(" ");
        localStringBuilder.append(m);
        localStringBuilder.append(" ");
        localStringBuilder.append(l1);
        localStringBuilder.append(" ");
        localStringBuilder.append(paramString1);
        localStringBuilder.append(" ");
        localStringBuilder.append(paramString2);
        localStringBuilder.append(": ");
        localStringBuilder.append(paramString3);
        localStringBuilder.append("\001\r\n");
        String str2 = d.toString();
        synchronized (n)
        {
          e.append(str2);
          if (e.length() <= c)
            return;
          if (f)
            return;
          f = true;
          w.a().a(new Runnable()
          {
            // ERROR //
            public final void run()
            {
              // Byte code:
              //   0: invokestatic 26	com/tencent/bugly/proguard/y:b	()Ljava/lang/Object;
              //   3: astore_1
              //   4: aload_1
              //   5: monitorenter
              //   6: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   9: ifnonnull +20 -> 29
              //   12: new 32	com/tencent/bugly/proguard/y$a
              //   15: dup
              //   16: invokestatic 36	com/tencent/bugly/proguard/y:d	()Ljava/lang/String;
              //   19: invokespecial 38	com/tencent/bugly/proguard/y$a:<init>	(Ljava/lang/String;)V
              //   22: invokestatic 41	com/tencent/bugly/proguard/y:a	(Lcom/tencent/bugly/proguard/y$a;)Lcom/tencent/bugly/proguard/y$a;
              //   25: pop
              //   26: goto +46 -> 72
              //   29: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   32: invokestatic 44	com/tencent/bugly/proguard/y$a:a	(Lcom/tencent/bugly/proguard/y$a;)Ljava/io/File;
              //   35: ifnull +30 -> 65
              //   38: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   41: invokestatic 44	com/tencent/bugly/proguard/y$a:a	(Lcom/tencent/bugly/proguard/y$a;)Ljava/io/File;
              //   44: invokevirtual 50	java/io/File:length	()J
              //   47: invokestatic 54	com/tencent/bugly/proguard/y:e	()Ljava/lang/StringBuilder;
              //   50: invokevirtual 59	java/lang/StringBuilder:length	()I
              //   53: i2l
              //   54: ladd
              //   55: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   58: invokestatic 62	com/tencent/bugly/proguard/y$a:b	(Lcom/tencent/bugly/proguard/y$a;)J
              //   61: lcmp
              //   62: ifle +10 -> 72
              //   65: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   68: invokestatic 65	com/tencent/bugly/proguard/y$a:c	(Lcom/tencent/bugly/proguard/y$a;)Z
              //   71: pop
              //   72: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   75: invokestatic 67	com/tencent/bugly/proguard/y$a:d	(Lcom/tencent/bugly/proguard/y$a;)Z
              //   78: ifeq +26 -> 104
              //   81: invokestatic 30	com/tencent/bugly/proguard/y:c	()Lcom/tencent/bugly/proguard/y$a;
              //   84: invokestatic 54	com/tencent/bugly/proguard/y:e	()Ljava/lang/StringBuilder;
              //   87: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
              //   90: invokevirtual 73	com/tencent/bugly/proguard/y$a:a	(Ljava/lang/String;)Z
              //   93: pop
              //   94: invokestatic 54	com/tencent/bugly/proguard/y:e	()Ljava/lang/StringBuilder;
              //   97: iconst_0
              //   98: invokevirtual 77	java/lang/StringBuilder:setLength	(I)V
              //   101: goto +21 -> 122
              //   104: invokestatic 54	com/tencent/bugly/proguard/y:e	()Ljava/lang/StringBuilder;
              //   107: iconst_0
              //   108: invokevirtual 77	java/lang/StringBuilder:setLength	(I)V
              //   111: invokestatic 54	com/tencent/bugly/proguard/y:e	()Ljava/lang/StringBuilder;
              //   114: aload_0
              //   115: getfield 16	com/tencent/bugly/proguard/y$1:a	Ljava/lang/String;
              //   118: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
              //   121: pop
              //   122: iconst_0
              //   123: invokestatic 84	com/tencent/bugly/proguard/y:a	(Z)Z
              //   126: pop
              //   127: goto +7 -> 134
              //   130: astore_2
              //   131: goto +6 -> 137
              //   134: aload_1
              //   135: monitorexit
              //   136: return
              //   137: aload_1
              //   138: monitorexit
              //   139: aload_2
              //   140: athrow
              //
              // Exception table:
              //   from	to	target	type
              //   6	127	130	finally
              //   134	136	130	finally
              //   6	127	134	java/lang/Throwable
            }
          });
          return;
        }
      }
      return;
    }
    finally
    {
    }
  }

  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return;
    String str = paramThrowable.getMessage();
    if (str == null)
      str = "";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append('\n');
    localStringBuilder.append(z.b(paramThrowable));
    a(paramString1, paramString2, localStringBuilder.toString());
  }

  // ERROR //
  public static byte[] a()
  {
    // Byte code:
    //   0: getstatic 57	com/tencent/bugly/proguard/y:a	Z
    //   3: ifne +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: getstatic 37	com/tencent/bugly/proguard/y:n	Ljava/lang/Object;
    //   11: astore_0
    //   12: aload_0
    //   13: monitorenter
    //   14: getstatic 49	com/tencent/bugly/proguard/y:g	Lcom/tencent/bugly/proguard/y$a;
    //   17: ifnull +69 -> 86
    //   20: getstatic 49	com/tencent/bugly/proguard/y:g	Lcom/tencent/bugly/proguard/y$a;
    //   23: invokestatic 194	com/tencent/bugly/proguard/y$a:d	(Lcom/tencent/bugly/proguard/y$a;)Z
    //   26: ifeq +60 -> 86
    //   29: getstatic 49	com/tencent/bugly/proguard/y:g	Lcom/tencent/bugly/proguard/y$a;
    //   32: invokestatic 197	com/tencent/bugly/proguard/y$a:a	(Lcom/tencent/bugly/proguard/y$a;)Ljava/io/File;
    //   35: astore_2
    //   36: goto +3 -> 39
    //   39: getstatic 63	com/tencent/bugly/proguard/y:e	Ljava/lang/StringBuilder;
    //   42: invokevirtual 160	java/lang/StringBuilder:length	()I
    //   45: istore_3
    //   46: iload_3
    //   47: ifne +11 -> 58
    //   50: aload_2
    //   51: ifnonnull +7 -> 58
    //   54: aload_0
    //   55: monitorexit
    //   56: aconst_null
    //   57: areturn
    //   58: aload_2
    //   59: getstatic 63	com/tencent/bugly/proguard/y:e	Ljava/lang/StringBuilder;
    //   62: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: ldc 199
    //   67: invokestatic 202	com/tencent/bugly/proguard/z:a	(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)[B
    //   70: astore 4
    //   72: aload_0
    //   73: monitorexit
    //   74: aload 4
    //   76: areturn
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    //   82: aload_0
    //   83: monitorexit
    //   84: aconst_null
    //   85: areturn
    //   86: aconst_null
    //   87: astore_2
    //   88: goto -49 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   14	46	77	finally
    //   54	56	77	finally
    //   58	72	77	finally
    //   72	74	77	finally
    //   14	46	82	java/lang/Throwable
    //   58	72	82	java/lang/Throwable
  }

  private static boolean b(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
      if ((locala != null) && (locala.D != null))
      {
        boolean bool = locala.D.appendLogToNative(paramString1, paramString2, paramString3);
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return false;
  }

  public static final class a
  {
    private boolean a;
    private File b;
    private String c;
    private long d;
    private long e = 30720L;

    public a(String paramString)
    {
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.c = paramString;
        this.a = a();
        return;
      }
    }

    // ERROR //
    private boolean a()
    {
      // Byte code:
      //   0: aload_0
      //   1: new 44	java/io/File
      //   4: dup
      //   5: aload_0
      //   6: getfield 32	com/tencent/bugly/proguard/y$a:c	Ljava/lang/String;
      //   9: invokespecial 46	java/io/File:<init>	(Ljava/lang/String;)V
      //   12: putfield 40	com/tencent/bugly/proguard/y$a:b	Ljava/io/File;
      //   15: aload_0
      //   16: getfield 40	com/tencent/bugly/proguard/y$a:b	Ljava/io/File;
      //   19: invokevirtual 49	java/io/File:exists	()Z
      //   22: ifeq +20 -> 42
      //   25: aload_0
      //   26: getfield 40	com/tencent/bugly/proguard/y$a:b	Ljava/io/File;
      //   29: invokevirtual 52	java/io/File:delete	()Z
      //   32: ifne +10 -> 42
      //   35: aload_0
      //   36: iconst_0
      //   37: putfield 37	com/tencent/bugly/proguard/y$a:a	Z
      //   40: iconst_0
      //   41: ireturn
      //   42: aload_0
      //   43: getfield 40	com/tencent/bugly/proguard/y$a:b	Ljava/io/File;
      //   46: invokevirtual 55	java/io/File:createNewFile	()Z
      //   49: ifne +15 -> 64
      //   52: aload_0
      //   53: iconst_0
      //   54: putfield 37	com/tencent/bugly/proguard/y$a:a	Z
      //   57: iconst_0
      //   58: ireturn
      //   59: aload_0
      //   60: iconst_0
      //   61: putfield 37	com/tencent/bugly/proguard/y$a:a	Z
      //   64: iconst_1
      //   65: ireturn
      //
      // Exception table:
      //   from	to	target	type
      //   0	57	59	java/lang/Throwable
    }

    // ERROR //
    public final boolean a(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 37	com/tencent/bugly/proguard/y$a:a	Z
      //   4: ifne +5 -> 9
      //   7: iconst_0
      //   8: ireturn
      //   9: aconst_null
      //   10: astore_2
      //   11: new 62	java/io/FileOutputStream
      //   14: dup
      //   15: aload_0
      //   16: getfield 40	com/tencent/bugly/proguard/y$a:b	Ljava/io/File;
      //   19: iconst_1
      //   20: invokespecial 65	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
      //   23: astore_3
      //   24: aload_1
      //   25: ldc 67
      //   27: invokevirtual 71	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   30: astore 7
      //   32: aload_3
      //   33: aload 7
      //   35: invokevirtual 75	java/io/FileOutputStream:write	([B)V
      //   38: aload_3
      //   39: invokevirtual 78	java/io/FileOutputStream:flush	()V
      //   42: aload_3
      //   43: invokevirtual 81	java/io/FileOutputStream:close	()V
      //   46: aload_0
      //   47: aload_0
      //   48: getfield 83	com/tencent/bugly/proguard/y$a:d	J
      //   51: aload 7
      //   53: arraylength
      //   54: i2l
      //   55: ladd
      //   56: putfield 83	com/tencent/bugly/proguard/y$a:d	J
      //   59: aload_3
      //   60: invokevirtual 81	java/io/FileOutputStream:close	()V
      //   63: goto +4 -> 67
      //   66: pop
      //   67: iconst_1
      //   68: ireturn
      //   69: astore 5
      //   71: goto +34 -> 105
      //   74: aload_3
      //   75: astore_2
      //   76: goto +10 -> 86
      //   79: astore 5
      //   81: aload_2
      //   82: astore_3
      //   83: goto +22 -> 105
      //   86: aload_0
      //   87: iconst_0
      //   88: putfield 37	com/tencent/bugly/proguard/y$a:a	Z
      //   91: aload_2
      //   92: ifnull +10 -> 102
      //   95: aload_2
      //   96: invokevirtual 81	java/io/FileOutputStream:close	()V
      //   99: goto +4 -> 103
      //   102: pop
      //   103: iconst_0
      //   104: ireturn
      //   105: aload_3
      //   106: ifnull +10 -> 116
      //   109: aload_3
      //   110: invokevirtual 81	java/io/FileOutputStream:close	()V
      //   113: goto +4 -> 117
      //   116: pop
      //   117: aload 5
      //   119: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   59	66	66	java/io/IOException
      //   24	59	69	finally
      //   24	59	74	java/lang/Throwable
      //   11	24	79	finally
      //   86	91	79	finally
      //   11	24	86	java/lang/Throwable
      //   95	102	102	java/io/IOException
      //   109	116	116	java/io/IOException
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.y
 * JD-Core Version:    0.6.1
 */