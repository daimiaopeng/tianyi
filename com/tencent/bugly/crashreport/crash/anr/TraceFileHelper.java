package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.x;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraceFileHelper
{
  private static String a(BufferedReader paramBufferedReader)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < 3; i++)
    {
      String str = paramBufferedReader.readLine();
      if (str == null)
        return null;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("\n");
      localStringBuffer.append(localStringBuilder.toString());
    }
    return localStringBuffer.toString();
  }

  private static Object[] a(BufferedReader paramBufferedReader, Pattern[] paramArrayOfPattern)
  {
    if ((paramBufferedReader != null) && (paramArrayOfPattern != null))
    {
      while (true)
      {
        String str = paramBufferedReader.readLine();
        if (str == null)
          break;
        int i = paramArrayOfPattern.length;
        for (int j = 0; j < i; j++)
        {
          Pattern localPattern = paramArrayOfPattern[j];
          if (localPattern.matcher(str).matches())
            return new Object[] { localPattern, str };
        }
      }
      return null;
    }
    return null;
  }

  private static String b(BufferedReader paramBufferedReader)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      String str = paramBufferedReader.readLine();
      if ((str == null) || (str.trim().length() <= 0))
        break;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("\n");
      localStringBuffer.append(localStringBuilder.toString());
    }
    return localStringBuffer.toString();
  }

  public static a readFirstDumpInfo(String paramString, final boolean paramBoolean)
  {
    if (paramString == null)
    {
      x.e("path:%s", new Object[] { paramString });
      return null;
    }
    a locala = new a();
    readTraceFile(paramString, new b()
    {
      public final boolean a(long paramAnonymousLong)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Long.valueOf(paramAnonymousLong);
        x.c("process end %d", arrayOfObject);
        return false;
      }

      public final boolean a(long paramAnonymousLong1, long paramAnonymousLong2, String paramAnonymousString)
      {
        x.c("new process %s", new Object[] { paramAnonymousString });
        this.a.a = paramAnonymousLong1;
        this.a.b = paramAnonymousString;
        this.a.c = paramAnonymousLong2;
        return paramBoolean;
      }

      public final boolean a(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2, String paramAnonymousString3)
      {
        x.c("new thread %s", new Object[] { paramAnonymousString1 });
        if (this.a.d == null)
          this.a.d = new HashMap();
        Map localMap = this.a.d;
        String[] arrayOfString = new String[3];
        arrayOfString[0] = paramAnonymousString2;
        arrayOfString[1] = paramAnonymousString3;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramAnonymousInt);
        arrayOfString[2] = localStringBuilder.toString();
        localMap.put(paramAnonymousString1, arrayOfString);
        return true;
      }
    });
    if ((locala.a > 0L) && (locala.c > 0L) && (locala.b != null))
      return locala;
    Object[] arrayOfObject = new Object[1];
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(locala.a);
    localStringBuilder.append(" ");
    localStringBuilder.append(locala.c);
    localStringBuilder.append(" ");
    localStringBuilder.append(locala.b);
    arrayOfObject[0] = localStringBuilder.toString();
    x.e("first dump error %s", arrayOfObject);
    return null;
  }

  public static a readTargetDumpInfo(String paramString1, String paramString2, final boolean paramBoolean)
  {
    if ((paramString1 != null) && (paramString2 != null))
    {
      a locala = new a();
      readTraceFile(paramString2, new b()
      {
        public final boolean a(long paramAnonymousLong)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Long.valueOf(paramAnonymousLong);
          x.c("process end %d", arrayOfObject);
          return (this.a.a <= 0L) || (this.a.c <= 0L) || (this.a.b == null);
        }

        public final boolean a(long paramAnonymousLong1, long paramAnonymousLong2, String paramAnonymousString)
        {
          x.c("new process %s", new Object[] { paramAnonymousString });
          if (!paramAnonymousString.equals(paramAnonymousString))
            return true;
          this.a.a = paramAnonymousLong1;
          this.a.b = paramAnonymousString;
          this.a.c = paramAnonymousLong2;
          return paramBoolean;
        }

        public final boolean a(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2, String paramAnonymousString3)
        {
          x.c("new thread %s", new Object[] { paramAnonymousString1 });
          if ((this.a.a > 0L) && (this.a.c > 0L) && (this.a.b != null))
          {
            if (this.a.d == null)
              this.a.d = new HashMap();
            Map localMap = this.a.d;
            String[] arrayOfString = new String[3];
            arrayOfString[0] = paramAnonymousString2;
            arrayOfString[1] = paramAnonymousString3;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(paramAnonymousInt);
            arrayOfString[2] = localStringBuilder.toString();
            localMap.put(paramAnonymousString1, arrayOfString);
            return true;
          }
          return true;
        }
      });
      if ((locala.a > 0L) && (locala.c > 0L) && (locala.b != null))
        return locala;
      return null;
    }
    return null;
  }

  // ERROR //
  public static void readTraceFile(String paramString, b paramb)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +710 -> 711
    //   4: aload_1
    //   5: ifnonnull +6 -> 11
    //   8: goto +703 -> 711
    //   11: new 107	java/io/File
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 110	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore_2
    //   20: aload_2
    //   21: invokevirtual 113	java/io/File:exists	()Z
    //   24: ifne +4 -> 28
    //   27: return
    //   28: aload_2
    //   29: invokevirtual 117	java/io/File:lastModified	()J
    //   32: pop2
    //   33: aload_2
    //   34: invokevirtual 119	java/io/File:length	()J
    //   37: pop2
    //   38: new 15	java/io/BufferedReader
    //   41: dup
    //   42: new 121	java/io/FileReader
    //   45: dup
    //   46: aload_2
    //   47: invokespecial 124	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   50: invokespecial 127	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   53: astore 7
    //   55: ldc 129
    //   57: invokestatic 133	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   60: astore 18
    //   62: ldc 135
    //   64: invokestatic 133	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   67: astore 19
    //   69: ldc 137
    //   71: invokestatic 133	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   74: astore 20
    //   76: ldc 139
    //   78: invokestatic 133	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   81: astore 21
    //   83: new 141	java/text/SimpleDateFormat
    //   86: dup
    //   87: ldc 143
    //   89: getstatic 149	java/util/Locale:US	Ljava/util/Locale;
    //   92: invokespecial 152	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   95: astore 22
    //   97: aload 7
    //   99: iconst_1
    //   100: anewarray 38	java/util/regex/Pattern
    //   103: dup
    //   104: iconst_0
    //   105: aload 18
    //   107: aastore
    //   108: invokestatic 154	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:a	(Ljava/io/BufferedReader;[Ljava/util/regex/Pattern;)[Ljava/lang/Object;
    //   111: astore 23
    //   113: aload 23
    //   115: ifnull +416 -> 531
    //   118: aload 23
    //   120: iconst_1
    //   121: aaload
    //   122: invokevirtual 155	java/lang/Object:toString	()Ljava/lang/String;
    //   125: ldc 157
    //   127: invokevirtual 161	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   130: astore 25
    //   132: aload 25
    //   134: iconst_2
    //   135: aaload
    //   136: invokestatic 167	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   139: lstore 26
    //   141: new 21	java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial 22	java/lang/StringBuilder:<init>	()V
    //   148: astore 28
    //   150: aload 28
    //   152: aload 25
    //   154: iconst_4
    //   155: aaload
    //   156: invokevirtual 26	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload 28
    //   162: ldc 94
    //   164: invokevirtual 26	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload 28
    //   170: aload 25
    //   172: iconst_5
    //   173: aaload
    //   174: invokevirtual 26	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload 22
    //   180: aload 28
    //   182: invokevirtual 31	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokevirtual 171	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   188: invokevirtual 176	java/util/Date:getTime	()J
    //   191: lstore 32
    //   193: aload 7
    //   195: iconst_1
    //   196: anewarray 38	java/util/regex/Pattern
    //   199: dup
    //   200: iconst_0
    //   201: aload 20
    //   203: aastore
    //   204: invokestatic 154	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:a	(Ljava/io/BufferedReader;[Ljava/util/regex/Pattern;)[Ljava/lang/Object;
    //   207: astore 34
    //   209: aload 34
    //   211: ifnonnull +25 -> 236
    //   214: aload 7
    //   216: invokevirtual 179	java/io/BufferedReader:close	()V
    //   219: return
    //   220: astore 55
    //   222: aload 55
    //   224: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   227: ifne +8 -> 235
    //   230: aload 55
    //   232: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   235: return
    //   236: aload 20
    //   238: aload 34
    //   240: iconst_1
    //   241: aaload
    //   242: invokevirtual 155	java/lang/Object:toString	()Ljava/lang/String;
    //   245: invokevirtual 42	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   248: astore 35
    //   250: aload 35
    //   252: invokevirtual 188	java/util/regex/Matcher:find	()Z
    //   255: pop
    //   256: aload 35
    //   258: iconst_1
    //   259: invokevirtual 192	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   262: pop
    //   263: aload 35
    //   265: iconst_1
    //   266: invokevirtual 192	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   269: astore 38
    //   271: aload 22
    //   273: astore 39
    //   275: aload_1
    //   276: lload 26
    //   278: lload 32
    //   280: aload 38
    //   282: invokeinterface 197 6 0
    //   287: istore 40
    //   289: iload 40
    //   291: ifne +25 -> 316
    //   294: aload 7
    //   296: invokevirtual 179	java/io/BufferedReader:close	()V
    //   299: return
    //   300: astore 54
    //   302: aload 54
    //   304: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   307: ifne +8 -> 315
    //   310: aload 54
    //   312: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   315: return
    //   316: aload 7
    //   318: iconst_2
    //   319: anewarray 38	java/util/regex/Pattern
    //   322: dup
    //   323: iconst_0
    //   324: aload 21
    //   326: aastore
    //   327: dup
    //   328: iconst_1
    //   329: aload 19
    //   331: aastore
    //   332: invokestatic 154	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:a	(Ljava/io/BufferedReader;[Ljava/util/regex/Pattern;)[Ljava/lang/Object;
    //   335: astore 41
    //   337: aload 41
    //   339: ifnull +185 -> 524
    //   342: aload 41
    //   344: iconst_0
    //   345: aaload
    //   346: aload 21
    //   348: if_acmpne +124 -> 472
    //   351: aload 41
    //   353: iconst_1
    //   354: aaload
    //   355: invokevirtual 155	java/lang/Object:toString	()Ljava/lang/String;
    //   358: astore 44
    //   360: ldc 199
    //   362: invokestatic 133	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   365: aload 44
    //   367: invokevirtual 42	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   370: astore 45
    //   372: aload 45
    //   374: invokevirtual 188	java/util/regex/Matcher:find	()Z
    //   377: pop
    //   378: aload 45
    //   380: invokevirtual 201	java/util/regex/Matcher:group	()Ljava/lang/String;
    //   383: astore 47
    //   385: aload 47
    //   387: iconst_1
    //   388: aload 47
    //   390: invokevirtual 58	java/lang/String:length	()I
    //   393: iconst_1
    //   394: isub
    //   395: invokevirtual 205	java/lang/String:substring	(II)Ljava/lang/String;
    //   398: astore 48
    //   400: aload 44
    //   402: ldc 207
    //   404: invokevirtual 211	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   407: pop
    //   408: ldc 213
    //   410: invokestatic 133	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   413: aload 44
    //   415: invokevirtual 42	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   418: astore 50
    //   420: aload 50
    //   422: invokevirtual 188	java/util/regex/Matcher:find	()Z
    //   425: pop
    //   426: aload 50
    //   428: invokevirtual 201	java/util/regex/Matcher:group	()Ljava/lang/String;
    //   431: astore 52
    //   433: aload_1
    //   434: aload 48
    //   436: aload 52
    //   438: iconst_1
    //   439: aload 52
    //   441: ldc 215
    //   443: invokevirtual 219	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   446: iadd
    //   447: invokevirtual 221	java/lang/String:substring	(I)Ljava/lang/String;
    //   450: invokestatic 226	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   453: aload 7
    //   455: invokestatic 228	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:a	(Ljava/io/BufferedReader;)Ljava/lang/String;
    //   458: aload 7
    //   460: invokestatic 230	com/tencent/bugly/crashreport/crash/anr/TraceFileHelper:b	(Ljava/io/BufferedReader;)Ljava/lang/String;
    //   463: invokeinterface 233 5 0
    //   468: pop
    //   469: goto -153 -> 316
    //   472: aload_1
    //   473: aload 41
    //   475: iconst_1
    //   476: aaload
    //   477: invokevirtual 155	java/lang/Object:toString	()Ljava/lang/String;
    //   480: ldc 157
    //   482: invokevirtual 161	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   485: iconst_2
    //   486: aaload
    //   487: invokestatic 167	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   490: invokeinterface 236 3 0
    //   495: istore 42
    //   497: iload 42
    //   499: ifne +25 -> 524
    //   502: aload 7
    //   504: invokevirtual 179	java/io/BufferedReader:close	()V
    //   507: return
    //   508: astore 43
    //   510: aload 43
    //   512: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   515: ifne +8 -> 523
    //   518: aload 43
    //   520: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   523: return
    //   524: aload 39
    //   526: astore 22
    //   528: goto -431 -> 97
    //   531: aload 7
    //   533: invokevirtual 179	java/io/BufferedReader:close	()V
    //   536: return
    //   537: astore 24
    //   539: aload 24
    //   541: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   544: ifne +8 -> 552
    //   547: aload 24
    //   549: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   552: return
    //   553: astore 11
    //   555: goto +20 -> 575
    //   558: astore 8
    //   560: aconst_null
    //   561: astore 7
    //   563: aload 8
    //   565: astore 9
    //   567: goto +113 -> 680
    //   570: astore 11
    //   572: aconst_null
    //   573: astore 7
    //   575: aload 11
    //   577: astore 12
    //   579: aload 12
    //   581: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   584: ifne +8 -> 592
    //   587: aload 12
    //   589: invokevirtual 237	java/lang/Exception:printStackTrace	()V
    //   592: iconst_2
    //   593: anewarray 4	java/lang/Object
    //   596: astore 13
    //   598: aload 13
    //   600: iconst_0
    //   601: aload 12
    //   603: invokevirtual 241	java/lang/Object:getClass	()Ljava/lang/Class;
    //   606: invokevirtual 246	java/lang/Class:getName	()Ljava/lang/String;
    //   609: aastore
    //   610: new 21	java/lang/StringBuilder
    //   613: dup
    //   614: invokespecial 22	java/lang/StringBuilder:<init>	()V
    //   617: astore 14
    //   619: aload 14
    //   621: aload 12
    //   623: invokevirtual 249	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   626: invokevirtual 26	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   629: pop
    //   630: aload 13
    //   632: iconst_1
    //   633: aload 14
    //   635: invokevirtual 31	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   638: aastore
    //   639: ldc 251
    //   641: aload 13
    //   643: invokestatic 254	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   646: pop
    //   647: aload 7
    //   649: ifnull +25 -> 674
    //   652: aload 7
    //   654: invokevirtual 179	java/io/BufferedReader:close	()V
    //   657: return
    //   658: astore 17
    //   660: aload 17
    //   662: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   665: ifne +8 -> 673
    //   668: aload 17
    //   670: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   673: return
    //   674: return
    //   675: astore 8
    //   677: goto -114 -> 563
    //   680: aload 7
    //   682: ifnull +26 -> 708
    //   685: aload 7
    //   687: invokevirtual 179	java/io/BufferedReader:close	()V
    //   690: goto +18 -> 708
    //   693: astore 10
    //   695: aload 10
    //   697: invokestatic 182	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   700: ifne +8 -> 708
    //   703: aload 10
    //   705: invokevirtual 185	java/io/IOException:printStackTrace	()V
    //   708: aload 9
    //   710: athrow
    //   711: return
    //
    // Exception table:
    //   from	to	target	type
    //   214	219	220	java/io/IOException
    //   294	299	300	java/io/IOException
    //   502	507	508	java/io/IOException
    //   531	536	537	java/io/IOException
    //   55	209	553	java/lang/Exception
    //   236	289	553	java/lang/Exception
    //   316	497	553	java/lang/Exception
    //   38	55	558	finally
    //   38	55	570	java/lang/Exception
    //   652	657	658	java/io/IOException
    //   55	209	675	finally
    //   236	289	675	finally
    //   316	497	675	finally
    //   579	647	675	finally
    //   685	690	693	java/io/IOException
  }

  public static final class a
  {
    public long a;
    public String b;
    public long c;
    public Map<String, String[]> d;
  }

  public static abstract interface b
  {
    public abstract boolean a(long paramLong);

    public abstract boolean a(long paramLong1, long paramLong2, String paramString);

    public abstract boolean a(String paramString1, int paramInt, String paramString2, String paramString3);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.anr.TraceFileHelper
 * JD-Core Version:    0.6.1
 */