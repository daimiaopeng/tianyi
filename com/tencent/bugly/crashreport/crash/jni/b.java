package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class b
{
  private static String a;

  // ERROR //
  public static CrashDetailBean a(Context paramContext, String paramString, NativeExceptionHandler paramNativeExceptionHandler)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +307 -> 308
    //   4: aload_1
    //   5: ifnull +303 -> 308
    //   8: aload_2
    //   9: ifnonnull +6 -> 15
    //   12: goto +296 -> 308
    //   15: new 13	java/io/File
    //   18: dup
    //   19: aload_1
    //   20: ldc 15
    //   22: invokespecial 19	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   25: astore 4
    //   27: aload 4
    //   29: invokevirtual 23	java/io/File:exists	()Z
    //   32: ifeq +274 -> 306
    //   35: aload 4
    //   37: invokevirtual 26	java/io/File:canRead	()Z
    //   40: ifne +6 -> 46
    //   43: goto +263 -> 306
    //   46: new 28	java/io/BufferedInputStream
    //   49: dup
    //   50: new 30	java/io/FileInputStream
    //   53: dup
    //   54: aload 4
    //   56: invokespecial 33	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   59: invokespecial 36	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   62: astore 5
    //   64: aload 5
    //   66: invokestatic 39	com/tencent/bugly/crashreport/crash/jni/b:a	(Ljava/io/BufferedInputStream;)Ljava/lang/String;
    //   69: astore 10
    //   71: aload 10
    //   73: ifnull +131 -> 204
    //   76: aload 10
    //   78: ldc 41
    //   80: invokevirtual 47	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   83: ifne +6 -> 89
    //   86: goto +118 -> 204
    //   89: new 49	java/util/HashMap
    //   92: dup
    //   93: invokespecial 51	java/util/HashMap:<init>	()V
    //   96: astore 13
    //   98: goto +222 -> 320
    //   101: aload 5
    //   103: invokestatic 39	com/tencent/bugly/crashreport/crash/jni/b:a	(Ljava/io/BufferedInputStream;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 15
    //   110: ifnull +30 -> 140
    //   113: aload 14
    //   115: ifnonnull +10 -> 125
    //   118: aload 15
    //   120: astore 14
    //   122: goto -21 -> 101
    //   125: aload 13
    //   127: aload 14
    //   129: aload 15
    //   131: invokeinterface 57 3 0
    //   136: pop
    //   137: goto +183 -> 320
    //   140: aload 14
    //   142: ifnull +35 -> 177
    //   145: ldc 59
    //   147: iconst_1
    //   148: anewarray 4	java/lang/Object
    //   151: dup
    //   152: iconst_0
    //   153: aload 14
    //   155: aastore
    //   156: invokestatic 65	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   159: pop
    //   160: aload 5
    //   162: invokevirtual 68	java/io/BufferedInputStream:close	()V
    //   165: goto +10 -> 175
    //   168: astore 19
    //   170: aload 19
    //   172: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   175: aconst_null
    //   176: areturn
    //   177: aload_0
    //   178: aload 13
    //   180: aload_2
    //   181: invokestatic 74	com/tencent/bugly/crashreport/crash/jni/b:a	(Landroid/content/Context;Ljava/util/Map;Lcom/tencent/bugly/crashreport/crash/jni/NativeExceptionHandler;)Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;
    //   184: astore 16
    //   186: aload 5
    //   188: invokevirtual 68	java/io/BufferedInputStream:close	()V
    //   191: goto +10 -> 201
    //   194: astore 17
    //   196: aload 17
    //   198: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   201: aload 16
    //   203: areturn
    //   204: ldc 76
    //   206: iconst_1
    //   207: anewarray 4	java/lang/Object
    //   210: dup
    //   211: iconst_0
    //   212: aload 10
    //   214: aastore
    //   215: invokestatic 65	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   218: pop
    //   219: aload 5
    //   221: invokevirtual 68	java/io/BufferedInputStream:close	()V
    //   224: goto +10 -> 234
    //   227: astore 12
    //   229: aload 12
    //   231: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   234: aconst_null
    //   235: areturn
    //   236: astore 8
    //   238: goto +16 -> 254
    //   241: astore 6
    //   243: aconst_null
    //   244: astore 5
    //   246: goto +37 -> 283
    //   249: astore 8
    //   251: aconst_null
    //   252: astore 5
    //   254: aload 8
    //   256: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   259: aload 5
    //   261: ifnull +18 -> 279
    //   264: aload 5
    //   266: invokevirtual 68	java/io/BufferedInputStream:close	()V
    //   269: goto +10 -> 279
    //   272: astore 9
    //   274: aload 9
    //   276: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   279: aconst_null
    //   280: areturn
    //   281: astore 6
    //   283: aload 5
    //   285: ifnull +18 -> 303
    //   288: aload 5
    //   290: invokevirtual 68	java/io/BufferedInputStream:close	()V
    //   293: goto +10 -> 303
    //   296: astore 7
    //   298: aload 7
    //   300: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   303: aload 6
    //   305: athrow
    //   306: aconst_null
    //   307: areturn
    //   308: ldc 78
    //   310: iconst_0
    //   311: anewarray 4	java/lang/Object
    //   314: invokestatic 65	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   317: pop
    //   318: aconst_null
    //   319: areturn
    //   320: aconst_null
    //   321: astore 14
    //   323: goto -222 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   160	165	168	java/io/IOException
    //   186	191	194	java/io/IOException
    //   219	224	227	java/io/IOException
    //   64	160	236	java/io/IOException
    //   177	186	236	java/io/IOException
    //   204	219	236	java/io/IOException
    //   46	64	241	finally
    //   46	64	249	java/io/IOException
    //   264	269	272	java/io/IOException
    //   64	160	281	finally
    //   177	186	281	finally
    //   204	219	281	finally
    //   254	259	281	finally
    //   288	293	296	java/io/IOException
  }

  private static CrashDetailBean a(Context paramContext, Map<String, String> paramMap, NativeExceptionHandler paramNativeExceptionHandler)
  {
    if (paramMap == null)
      return null;
    if (a.a(paramContext) == null)
    {
      x.e("abnormal com info not created", new Object[0]);
      return null;
    }
    String str1 = (String)paramMap.get("intStateStr");
    Map localMap;
    if ((str1 != null) && (str1.trim().length() > 0))
    {
      localMap = c(str1);
      if (localMap == null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramMap.size());
        x.e("parse intSateMap fail", arrayOfObject);
        return null;
      }
    }
    while (true)
    {
      String str5;
      String str8;
      HashMap localHashMap;
      int j;
      Object localObject3;
      try
      {
        ((Integer)localMap.get("sino")).intValue();
        ((Integer)localMap.get("sud")).intValue();
        String str2 = (String)paramMap.get("soVersion");
        if (str2 == null)
        {
          x.e("error format at version", new Object[0]);
          return null;
        }
        String str3 = (String)paramMap.get("errorAddr");
        if (str3 == null)
          str3 = "unknown";
        String str4 = str3;
        str5 = (String)paramMap.get("codeMsg");
        if (str5 == null)
          str5 = "unknown";
        String str6 = (String)paramMap.get("tombPath");
        if (str6 == null)
          str6 = "unknown";
        String str7 = str6;
        str8 = (String)paramMap.get("signalName");
        if (str8 == null)
          str8 = "unknown";
        paramMap.get("errnoMsg");
        String str9 = (String)paramMap.get("stack");
        if (str9 == null)
          str9 = "unknown";
        String str10 = (String)paramMap.get("jstack");
        if (str10 != null)
        {
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append(str9);
          localStringBuilder1.append("java:\n");
          localStringBuilder1.append(str10);
          str9 = localStringBuilder1.toString();
        }
        Integer localInteger1 = (Integer)localMap.get("sico");
        if ((localInteger1 == null) || (localInteger1.intValue() <= 0))
          break label1206;
        StringBuilder localStringBuilder5 = new StringBuilder();
        localStringBuilder5.append(str8);
        localStringBuilder5.append("(");
        localStringBuilder5.append(str5);
        localStringBuilder5.append(")");
        str12 = localStringBuilder5.toString();
        str11 = "KERNEL";
        String str13 = (String)paramMap.get("nativeLog");
        if ((str13 == null) || (str13.isEmpty()))
          break label1217;
        arrayOfByte = z.a(null, str13, "BuglyNativeLog.txt");
        String str14 = (String)paramMap.get("sendingProcess");
        if (str14 == null)
          str14 = "unknown";
        Integer localInteger2 = (Integer)localMap.get("spd");
        if (localInteger2 != null)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append(str14);
          localStringBuilder2.append("(");
          localStringBuilder2.append(localInteger2);
          localStringBuilder2.append(")");
          str14 = localStringBuilder2.toString();
        }
        String str15 = str14;
        String str16 = (String)paramMap.get("threadName");
        if (str16 == null)
          str16 = "unknown";
        Integer localInteger3 = (Integer)localMap.get("et");
        if (localInteger3 != null)
        {
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append(str16);
          localStringBuilder3.append("(");
          localStringBuilder3.append(localInteger3);
          localStringBuilder3.append(")");
          str16 = localStringBuilder3.toString();
        }
        String str17 = str16;
        String str18 = (String)paramMap.get("processName");
        if (str18 == null)
          str18 = "unknown";
        Integer localInteger4 = (Integer)localMap.get("ep");
        if (localInteger4 != null)
        {
          StringBuilder localStringBuilder4 = new StringBuilder();
          localStringBuilder4.append(str18);
          localStringBuilder4.append("(");
          localStringBuilder4.append(localInteger4);
          localStringBuilder4.append(")");
          str18 = localStringBuilder4.toString();
        }
        String str19 = (String)paramMap.get("key-value");
        if (str19 == null)
          break label1240;
        localHashMap = new HashMap();
        localObject1 = str19.split("\n");
        int i = localObject1.length;
        j = 0;
        if (j >= i)
          break label1233;
        String[] arrayOfString = localObject1[j].split("=");
        int k = arrayOfString.length;
        localObject3 = localObject1;
        if (k != 2)
          break label1223;
        localHashMap.put(arrayOfString[0], arrayOfString[1]);
        break label1223;
        long l1 = ((Integer)localMap.get("ets")).intValue();
        long l2 = ((Integer)localMap.get("etms")).intValue();
        long l3 = l1 * 1000L + l2 / 1000L;
        String str20 = a(str9);
        String str21 = (String)paramMap.get("sysLogPath");
        CrashDetailBean localCrashDetailBean = paramNativeExceptionHandler.packageCrashDatas(str18, str17, l3, str12, str4, str20, str11, str15, str7, str21, str2, arrayOfByte, (Map)localObject2, false);
        if (localCrashDetailBean != null)
        {
          String str22 = (String)paramMap.get("userId");
          if (str22 != null)
          {
            x.c("[Native record info] userId: %s", new Object[] { str22 });
            localCrashDetailBean.m = str22;
          }
          String str23 = (String)paramMap.get("sysLog");
          if (str23 != null)
            localCrashDetailBean.w = str23;
          String str24 = (String)paramMap.get("appVersion");
          if (str24 != null)
          {
            x.c("[Native record info] appVersion: %s", new Object[] { str24 });
            localCrashDetailBean.f = str24;
          }
          String str25 = (String)paramMap.get("isAppForeground");
          if (str25 != null)
          {
            x.c("[Native record info] isAppForeground: %s", new Object[] { str25 });
            localCrashDetailBean.M = str25.equalsIgnoreCase("true");
          }
          String str26 = (String)paramMap.get("launchTime");
          if (str26 != null)
          {
            x.c("[Native record info] launchTime: %s", new Object[] { str26 });
            try
            {
              localCrashDetailBean.L = Long.parseLong(str26);
            }
            catch (NumberFormatException localNumberFormatException)
            {
              if (!x.a(localNumberFormatException))
                localNumberFormatException.printStackTrace();
            }
          }
          localCrashDetailBean.y = null;
          localCrashDetailBean.k = true;
        }
        return localCrashDetailBean;
      }
      catch (Throwable localThrowable)
      {
        x.e("error format", new Object[0]);
        localThrowable.printStackTrace();
        return null;
      }
      x.e("no intStateStr", new Object[0]);
      return null;
      label1206: String str11 = str5;
      String str12 = str8;
      continue;
      label1217: byte[] arrayOfByte = null;
      continue;
      label1223: j++;
      Object localObject1 = localObject3;
      continue;
      label1233: Object localObject2 = localHashMap;
      continue;
      label1240: localObject2 = null;
    }
  }

  private static String a(BufferedInputStream paramBufferedInputStream)
  {
    if (paramBufferedInputStream == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      int i = paramBufferedInputStream.read();
      if (i == -1)
        break;
      if (i == 0)
        return localStringBuilder.toString();
      localStringBuilder.append((char)i);
    }
    return null;
  }

  protected static String a(String paramString)
  {
    if (paramString == null)
      return "";
    String[] arrayOfString = paramString.split("\n");
    if ((arrayOfString != null) && (arrayOfString.length != 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str = arrayOfString[j];
        if (!str.contains("java.lang.Thread.getStackTrace("))
        {
          localStringBuilder.append(str);
          localStringBuilder.append("\n");
        }
      }
      return localStringBuilder.toString();
    }
    return paramString;
  }

  // ERROR //
  public static String a(String paramString1, int paramInt, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +334 -> 335
    //   4: iload_1
    //   5: ifgt +6 -> 11
    //   8: goto +327 -> 335
    //   11: new 13	java/io/File
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 306	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore_3
    //   20: aload_3
    //   21: invokevirtual 23	java/io/File:exists	()Z
    //   24: ifeq +309 -> 333
    //   27: aload_3
    //   28: invokevirtual 26	java/io/File:canRead	()Z
    //   31: ifne +6 -> 37
    //   34: goto +299 -> 333
    //   37: aload_0
    //   38: putstatic 308	com/tencent/bugly/crashreport/crash/jni/b:a	Ljava/lang/String;
    //   41: iconst_2
    //   42: anewarray 4	java/lang/Object
    //   45: astore 4
    //   47: aload 4
    //   49: iconst_0
    //   50: aload_3
    //   51: invokevirtual 311	java/io/File:length	()J
    //   54: invokestatic 314	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   57: aastore
    //   58: aload 4
    //   60: iconst_1
    //   61: aload_3
    //   62: invokevirtual 317	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   65: aastore
    //   66: ldc_w 319
    //   69: aload 4
    //   71: invokestatic 321	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   74: pop
    //   75: aload_2
    //   76: ifnonnull +19 -> 95
    //   79: new 13	java/io/File
    //   82: dup
    //   83: aload_0
    //   84: invokespecial 306	java/io/File:<init>	(Ljava/lang/String;)V
    //   87: invokestatic 324	com/tencent/bugly/proguard/z:a	(Ljava/io/File;)Ljava/lang/String;
    //   90: astore 21
    //   92: goto +137 -> 229
    //   95: new 147	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   102: astore 6
    //   104: new 326	java/io/BufferedReader
    //   107: dup
    //   108: new 328	java/io/InputStreamReader
    //   111: dup
    //   112: new 30	java/io/FileInputStream
    //   115: dup
    //   116: aload_3
    //   117: invokespecial 33	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   120: ldc_w 330
    //   123: invokespecial 333	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   126: invokespecial 336	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   129: astore 7
    //   131: aload 7
    //   133: invokevirtual 339	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   136: astore 15
    //   138: aload 15
    //   140: ifnull +66 -> 206
    //   143: new 147	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   150: astore 16
    //   152: aload 16
    //   154: aload_2
    //   155: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload 16
    //   161: ldc_w 341
    //   164: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload 16
    //   170: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokestatic 347	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   176: aload 15
    //   178: invokevirtual 351	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   181: invokevirtual 356	java/util/regex/Matcher:find	()Z
    //   184: ifeq -53 -> 131
    //   187: aload 6
    //   189: aload 15
    //   191: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: pop
    //   195: aload 6
    //   197: ldc 196
    //   199: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: pop
    //   203: goto -72 -> 131
    //   206: aload 6
    //   208: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: astore 21
    //   213: aload 7
    //   215: invokevirtual 357	java/io/BufferedReader:close	()V
    //   218: goto +11 -> 229
    //   221: astore 22
    //   223: aload 22
    //   225: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   228: pop
    //   229: aload 21
    //   231: ifnull +26 -> 257
    //   234: aload 21
    //   236: invokevirtual 103	java/lang/String:length	()I
    //   239: iload_1
    //   240: if_icmple +17 -> 257
    //   243: aload 21
    //   245: aload 21
    //   247: invokevirtual 103	java/lang/String:length	()I
    //   250: iload_1
    //   251: isub
    //   252: invokevirtual 361	java/lang/String:substring	(I)Ljava/lang/String;
    //   255: astore 21
    //   257: aload 21
    //   259: areturn
    //   260: astore 11
    //   262: goto +16 -> 278
    //   265: astore 8
    //   267: aconst_null
    //   268: astore 7
    //   270: goto +39 -> 309
    //   273: astore 11
    //   275: aconst_null
    //   276: astore 7
    //   278: aload 11
    //   280: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   283: pop
    //   284: aload 7
    //   286: ifnull +19 -> 305
    //   289: aload 7
    //   291: invokevirtual 357	java/io/BufferedReader:close	()V
    //   294: goto +11 -> 305
    //   297: astore 13
    //   299: aload 13
    //   301: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   304: pop
    //   305: aconst_null
    //   306: areturn
    //   307: astore 8
    //   309: aload 7
    //   311: ifnull +19 -> 330
    //   314: aload 7
    //   316: invokevirtual 357	java/io/BufferedReader:close	()V
    //   319: goto +11 -> 330
    //   322: astore 9
    //   324: aload 9
    //   326: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   329: pop
    //   330: aload 8
    //   332: athrow
    //   333: aconst_null
    //   334: areturn
    //   335: aconst_null
    //   336: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   213	218	221	java/lang/Exception
    //   131	213	260	java/lang/Throwable
    //   95	131	265	finally
    //   95	131	273	java/lang/Throwable
    //   289	294	297	java/lang/Exception
    //   131	213	307	finally
    //   278	284	307	finally
    //   314	319	322	java/lang/Exception
  }

  public static String a(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      String str1 = b(paramString1, paramString2);
      if ((str1 != null) && (!str1.isEmpty()))
      {
        localStringBuilder.append("Register infos:\n");
        localStringBuilder.append(str1);
      }
      String str2 = c(paramString1, paramString2);
      if ((str2 != null) && (!str2.isEmpty()))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append("\n");
        localStringBuilder.append("System SO infos:\n");
        localStringBuilder.append(str2);
      }
      return localStringBuilder.toString();
    }
    return null;
  }

  public static void a(boolean paramBoolean, String paramString)
  {
    if (paramString != null)
    {
      File localFile1 = new File(paramString, "rqd_record.eup");
      if ((localFile1.exists()) && (localFile1.canWrite()))
      {
        localFile1.delete();
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[0] = localFile1.getAbsoluteFile();
        x.c("delete record file %s", arrayOfObject6);
      }
      File localFile2 = new File(paramString, "reg_record.txt");
      if ((localFile2.exists()) && (localFile2.canWrite()))
      {
        localFile2.delete();
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = localFile2.getAbsoluteFile();
        x.c("delete record file %s", arrayOfObject5);
      }
      File localFile3 = new File(paramString, "map_record.txt");
      if ((localFile3.exists()) && (localFile3.canWrite()))
      {
        localFile3.delete();
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = localFile3.getAbsoluteFile();
        x.c("delete record file %s", arrayOfObject4);
      }
      File localFile4 = new File(paramString, "backup_record.txt");
      if ((localFile4.exists()) && (localFile4.canWrite()))
      {
        localFile4.delete();
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = localFile4.getAbsoluteFile();
        x.c("delete record file %s", arrayOfObject3);
      }
      if (a != null)
      {
        File localFile5 = new File(a);
        if ((localFile5.exists()) && (localFile5.canWrite()))
        {
          localFile5.delete();
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = localFile5.getAbsoluteFile();
          x.c("delete record file %s", arrayOfObject2);
        }
      }
      if (paramBoolean)
      {
        File localFile6 = new File(paramString);
        if ((localFile6.canRead()) && (localFile6.isDirectory()))
        {
          File[] arrayOfFile = localFile6.listFiles();
          if (arrayOfFile != null)
          {
            int i = arrayOfFile.length;
            for (int j = 0; j < i; j++)
            {
              File localFile7 = arrayOfFile[j];
              if ((localFile7.canRead()) && (localFile7.canWrite()) && (localFile7.length() == 0L))
              {
                localFile7.delete();
                Object[] arrayOfObject1 = new Object[1];
                arrayOfObject1[0] = localFile7.getAbsoluteFile();
                x.c("delete invalid record file %s", arrayOfObject1);
              }
            }
          }
        }
      }
    }
  }

  public static String b(String paramString)
  {
    if (paramString == null)
      return null;
    File localFile = new File(paramString, "backup_record.txt");
    if (localFile.exists())
      return localFile.getAbsolutePath();
    return null;
  }

  // ERROR //
  private static String b(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 387
    //   4: invokestatic 403	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/BufferedReader;
    //   7: astore_2
    //   8: aload_2
    //   9: ifnonnull +5 -> 14
    //   12: aconst_null
    //   13: areturn
    //   14: new 147	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   21: astore_3
    //   22: aload_2
    //   23: invokevirtual 339	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   26: astore 11
    //   28: aload 11
    //   30: ifnull +142 -> 172
    //   33: aload 11
    //   35: aload_1
    //   36: invokevirtual 406	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   39: ifne +210 -> 249
    //   42: goto +130 -> 172
    //   45: aload_2
    //   46: invokevirtual 339	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   49: astore 17
    //   51: aload 17
    //   53: ifnull +84 -> 137
    //   56: iload 15
    //   58: iconst_4
    //   59: irem
    //   60: ifne +26 -> 86
    //   63: iload 15
    //   65: ifle +10 -> 75
    //   68: aload_3
    //   69: ldc 196
    //   71: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload_3
    //   76: ldc_w 408
    //   79: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: goto +34 -> 117
    //   86: aload 17
    //   88: invokevirtual 103	java/lang/String:length	()I
    //   91: bipush 16
    //   93: if_icmple +7 -> 100
    //   96: bipush 28
    //   98: istore 14
    //   100: aload_3
    //   101: ldc_w 410
    //   104: iconst_0
    //   105: iload 14
    //   107: iload 16
    //   109: isub
    //   110: invokevirtual 413	java/lang/String:substring	(II)Ljava/lang/String;
    //   113: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload 17
    //   119: invokevirtual 103	java/lang/String:length	()I
    //   122: istore 16
    //   124: aload_3
    //   125: aload 17
    //   127: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: iinc 15 1
    //   134: goto -89 -> 45
    //   137: aload_3
    //   138: ldc 196
    //   140: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload_3
    //   145: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: astore 19
    //   150: aload_2
    //   151: ifnull +18 -> 169
    //   154: aload_2
    //   155: invokevirtual 357	java/io/BufferedReader:close	()V
    //   158: goto +11 -> 169
    //   161: astore 20
    //   163: aload 20
    //   165: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   168: pop
    //   169: aload 19
    //   171: areturn
    //   172: aload_2
    //   173: ifnull +18 -> 191
    //   176: aload_2
    //   177: invokevirtual 357	java/io/BufferedReader:close	()V
    //   180: goto +11 -> 191
    //   183: astore 12
    //   185: aload 12
    //   187: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   190: pop
    //   191: aconst_null
    //   192: areturn
    //   193: astore 8
    //   195: goto +32 -> 227
    //   198: astore 4
    //   200: aload 4
    //   202: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   205: pop
    //   206: aload_2
    //   207: ifnull +18 -> 225
    //   210: aload_2
    //   211: invokevirtual 357	java/io/BufferedReader:close	()V
    //   214: goto +11 -> 225
    //   217: astore 6
    //   219: aload 6
    //   221: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   224: pop
    //   225: aconst_null
    //   226: areturn
    //   227: aload_2
    //   228: ifnull +18 -> 246
    //   231: aload_2
    //   232: invokevirtual 357	java/io/BufferedReader:close	()V
    //   235: goto +11 -> 246
    //   238: astore 9
    //   240: aload 9
    //   242: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   245: pop
    //   246: aload 8
    //   248: athrow
    //   249: bipush 18
    //   251: istore 14
    //   253: iconst_0
    //   254: istore 15
    //   256: iconst_0
    //   257: istore 16
    //   259: goto -214 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   154	158	161	java/lang/Exception
    //   176	180	183	java/lang/Exception
    //   14	150	193	finally
    //   200	206	193	finally
    //   14	150	198	java/lang/Throwable
    //   210	214	217	java/lang/Exception
    //   231	235	238	java/lang/Exception
  }

  // ERROR //
  private static String c(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 389
    //   4: invokestatic 403	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/BufferedReader;
    //   7: astore_2
    //   8: aload_2
    //   9: ifnonnull +5 -> 14
    //   12: aconst_null
    //   13: areturn
    //   14: new 147	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   21: astore_3
    //   22: aload_2
    //   23: invokevirtual 339	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   26: astore 11
    //   28: aload 11
    //   30: ifnull +79 -> 109
    //   33: aload 11
    //   35: aload_1
    //   36: invokevirtual 406	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   39: ifne +6 -> 45
    //   42: goto +67 -> 109
    //   45: aload_2
    //   46: invokevirtual 339	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   49: astore 14
    //   51: aload 14
    //   53: ifnull +28 -> 81
    //   56: aload_3
    //   57: ldc_w 408
    //   60: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload_3
    //   65: aload 14
    //   67: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_3
    //   72: ldc 196
    //   74: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: goto -33 -> 45
    //   81: aload_3
    //   82: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: astore 15
    //   87: aload_2
    //   88: ifnull +18 -> 106
    //   91: aload_2
    //   92: invokevirtual 357	java/io/BufferedReader:close	()V
    //   95: goto +11 -> 106
    //   98: astore 16
    //   100: aload 16
    //   102: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   105: pop
    //   106: aload 15
    //   108: areturn
    //   109: aload_2
    //   110: ifnull +18 -> 128
    //   113: aload_2
    //   114: invokevirtual 357	java/io/BufferedReader:close	()V
    //   117: goto +11 -> 128
    //   120: astore 12
    //   122: aload 12
    //   124: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   127: pop
    //   128: aconst_null
    //   129: areturn
    //   130: astore 8
    //   132: goto +32 -> 164
    //   135: astore 4
    //   137: aload 4
    //   139: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   142: pop
    //   143: aload_2
    //   144: ifnull +18 -> 162
    //   147: aload_2
    //   148: invokevirtual 357	java/io/BufferedReader:close	()V
    //   151: goto +11 -> 162
    //   154: astore 6
    //   156: aload 6
    //   158: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   161: pop
    //   162: aconst_null
    //   163: areturn
    //   164: aload_2
    //   165: ifnull +18 -> 183
    //   168: aload_2
    //   169: invokevirtual 357	java/io/BufferedReader:close	()V
    //   172: goto +11 -> 183
    //   175: astore 9
    //   177: aload 9
    //   179: invokestatic 273	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   182: pop
    //   183: aload 8
    //   185: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   91	95	98	java/lang/Exception
    //   113	117	120	java/lang/Exception
    //   14	87	130	finally
    //   137	143	130	finally
    //   14	87	135	java/lang/Throwable
    //   147	151	154	java/lang/Exception
    //   168	172	175	java/lang/Exception
  }

  private static Map<String, Integer> c(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      HashMap localHashMap = new HashMap();
      for (String str : paramString.split(","))
      {
        String[] arrayOfString2 = str.split(":");
        if (arrayOfString2.length != 2)
        {
          x.e("error format at %s", new Object[] { str });
          return null;
        }
        int k = Integer.parseInt(arrayOfString2[1]);
        localHashMap.put(arrayOfString2[0], Integer.valueOf(k));
      }
      return localHashMap;
    }
    catch (Exception localException)
    {
      x.e("error format intStateStr %s", new Object[] { paramString });
      localException.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.jni.b
 * JD-Core Version:    0.6.1
 */