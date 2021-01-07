package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.stub.StubApp;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class z
{
  private static Map<String, String> a;
  private static boolean b;

  public static Context a(Context paramContext)
  {
    if (paramContext == null)
      return paramContext;
    Context localContext = StubApp.getOrigApplicationContext(paramContext.getApplicationContext());
    if (localContext == null)
      return paramContext;
    return localContext;
  }

  public static SharedPreferences a(String paramString, Context paramContext)
  {
    if (paramContext != null)
      return paramContext.getSharedPreferences(paramString, 0);
    return null;
  }

  public static BufferedReader a(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    try
    {
      File localFile = new File(paramString1, paramString2);
      if ((localFile.exists()) && (localFile.canRead()))
      {
        BufferedReader localBufferedReader = b(localFile);
        return localBufferedReader;
      }
      return null;
    }
    catch (NullPointerException localNullPointerException)
    {
      x.a(localNullPointerException);
    }
    return null;
  }

  // ERROR //
  public static Object a(String paramString1, String paramString2, Object paramObject, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 63	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   4: aload_1
    //   5: aload_3
    //   6: invokevirtual 67	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   9: astore 5
    //   11: aload 5
    //   13: iconst_1
    //   14: invokevirtual 73	java/lang/reflect/Method:setAccessible	(Z)V
    //   17: aload 5
    //   19: aconst_null
    //   20: aload 4
    //   22: invokevirtual 77	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   25: astore 6
    //   27: aload 6
    //   29: areturn
    //   30: aconst_null
    //   31: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	27	30	java/lang/Exception
  }

  // ERROR //
  public static <T> T a(byte[] paramArrayOfByte, android.os.Parcelable.Creator<T> paramCreator)
  {
    // Byte code:
    //   0: invokestatic 86	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_2
    //   4: aload_2
    //   5: aload_0
    //   6: iconst_0
    //   7: aload_0
    //   8: arraylength
    //   9: invokevirtual 90	android/os/Parcel:unmarshall	([BII)V
    //   12: aload_2
    //   13: iconst_0
    //   14: invokevirtual 94	android/os/Parcel:setDataPosition	(I)V
    //   17: aload_1
    //   18: aload_2
    //   19: invokeinterface 100 2 0
    //   24: astore 5
    //   26: aload_2
    //   27: ifnull +7 -> 34
    //   30: aload_2
    //   31: invokevirtual 103	android/os/Parcel:recycle	()V
    //   34: aload 5
    //   36: areturn
    //   37: astore 4
    //   39: goto +18 -> 57
    //   42: astore_3
    //   43: aload_3
    //   44: invokevirtual 106	java/lang/Throwable:printStackTrace	()V
    //   47: aload_2
    //   48: ifnull +7 -> 55
    //   51: aload_2
    //   52: invokevirtual 103	android/os/Parcel:recycle	()V
    //   55: aconst_null
    //   56: areturn
    //   57: aload_2
    //   58: ifnull +7 -> 65
    //   61: aload_2
    //   62: invokevirtual 103	android/os/Parcel:recycle	()V
    //   65: aload 4
    //   67: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   17	26	37	finally
    //   43	47	37	finally
    //   17	26	42	java/lang/Throwable
  }

  public static String a()
  {
    return a(System.currentTimeMillis());
  }

  // ERROR //
  public static String a(long paramLong)
  {
    // Byte code:
    //   0: new 118	java/text/SimpleDateFormat
    //   3: dup
    //   4: ldc 120
    //   6: getstatic 126	java/util/Locale:US	Ljava/util/Locale;
    //   9: invokespecial 129	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   12: new 131	java/util/Date
    //   15: dup
    //   16: lload_0
    //   17: invokespecial 134	java/util/Date:<init>	(J)V
    //   20: invokevirtual 138	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   23: astore_2
    //   24: aload_2
    //   25: areturn
    //   26: new 131	java/util/Date
    //   29: dup
    //   30: invokespecial 139	java/util/Date:<init>	()V
    //   33: invokevirtual 142	java/util/Date:toString	()Ljava/lang/String;
    //   36: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	24	26	java/lang/Exception
  }

  // ERROR //
  public static String a(Context paramContext, int paramInt, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 147
    //   3: invokestatic 152	com/tencent/bugly/crashreport/common/info/AppInfo:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   6: istore_3
    //   7: aconst_null
    //   8: astore 4
    //   10: iload_3
    //   11: ifne +15 -> 26
    //   14: ldc 154
    //   16: iconst_0
    //   17: anewarray 4	java/lang/Object
    //   20: invokestatic 158	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   23: pop
    //   24: aconst_null
    //   25: areturn
    //   26: aload_2
    //   27: ifnonnull +32 -> 59
    //   30: iconst_4
    //   31: anewarray 160	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: ldc 162
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: ldc 164
    //   43: aastore
    //   44: dup
    //   45: iconst_2
    //   46: ldc 166
    //   48: aastore
    //   49: dup
    //   50: iconst_3
    //   51: ldc 168
    //   53: aastore
    //   54: astore 5
    //   56: goto +39 -> 95
    //   59: bipush 6
    //   61: anewarray 160	java/lang/String
    //   64: dup
    //   65: iconst_0
    //   66: ldc 162
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: ldc 164
    //   73: aastore
    //   74: dup
    //   75: iconst_2
    //   76: ldc 166
    //   78: aastore
    //   79: dup
    //   80: iconst_3
    //   81: ldc 168
    //   83: aastore
    //   84: dup
    //   85: iconst_4
    //   86: ldc 170
    //   88: aastore
    //   89: dup
    //   90: iconst_5
    //   91: aload_2
    //   92: aastore
    //   93: astore 5
    //   95: new 172	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   102: astore 6
    //   104: invokestatic 179	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   107: aload 5
    //   109: invokevirtual 183	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   112: astore 20
    //   114: new 185	java/io/BufferedReader
    //   117: dup
    //   118: new 187	java/io/InputStreamReader
    //   121: dup
    //   122: aload 20
    //   124: invokevirtual 193	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   127: invokespecial 196	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   130: invokespecial 199	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   133: astore 21
    //   135: aload 21
    //   137: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   140: astore 22
    //   142: aload 22
    //   144: ifnull +49 -> 193
    //   147: aload 6
    //   149: aload 22
    //   151: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: pop
    //   155: aload 6
    //   157: ldc 208
    //   159: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: iload_1
    //   164: ifle -29 -> 135
    //   167: aload 6
    //   169: invokevirtual 212	java/lang/StringBuilder:length	()I
    //   172: iload_1
    //   173: if_icmple -38 -> 135
    //   176: aload 6
    //   178: iconst_0
    //   179: aload 6
    //   181: invokevirtual 212	java/lang/StringBuilder:length	()I
    //   184: iload_1
    //   185: isub
    //   186: invokevirtual 216	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   189: pop
    //   190: goto -55 -> 135
    //   193: aload 6
    //   195: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: astore 23
    //   200: aload 20
    //   202: ifnull +57 -> 259
    //   205: aload 20
    //   207: invokevirtual 221	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   210: invokevirtual 226	java/io/OutputStream:close	()V
    //   213: goto +10 -> 223
    //   216: astore 24
    //   218: aload 24
    //   220: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   223: aload 20
    //   225: invokevirtual 193	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   228: invokevirtual 230	java/io/InputStream:close	()V
    //   231: goto +10 -> 241
    //   234: astore 25
    //   236: aload 25
    //   238: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   241: aload 20
    //   243: invokevirtual 233	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   246: invokevirtual 230	java/io/InputStream:close	()V
    //   249: goto +10 -> 259
    //   252: astore 26
    //   254: aload 26
    //   256: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   259: aload 23
    //   261: areturn
    //   262: astore 16
    //   264: aload 20
    //   266: astore 4
    //   268: goto +142 -> 410
    //   271: astore 7
    //   273: aload 20
    //   275: astore 4
    //   277: goto +10 -> 287
    //   280: astore 16
    //   282: goto +128 -> 410
    //   285: astore 7
    //   287: aload 7
    //   289: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   292: ifne +8 -> 300
    //   295: aload 7
    //   297: invokevirtual 106	java/lang/Throwable:printStackTrace	()V
    //   300: new 172	java/lang/StringBuilder
    //   303: dup
    //   304: ldc 235
    //   306: invokespecial 238	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   309: astore 8
    //   311: aload 8
    //   313: aload 7
    //   315: invokevirtual 239	java/lang/Throwable:toString	()Ljava/lang/String;
    //   318: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: pop
    //   322: aload 8
    //   324: ldc 241
    //   326: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   329: pop
    //   330: aload 6
    //   332: aload 8
    //   334: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   337: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: pop
    //   341: aload 6
    //   343: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: astore 12
    //   348: aload 4
    //   350: ifnull +57 -> 407
    //   353: aload 4
    //   355: invokevirtual 221	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   358: invokevirtual 226	java/io/OutputStream:close	()V
    //   361: goto +10 -> 371
    //   364: astore 13
    //   366: aload 13
    //   368: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   371: aload 4
    //   373: invokevirtual 193	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   376: invokevirtual 230	java/io/InputStream:close	()V
    //   379: goto +10 -> 389
    //   382: astore 14
    //   384: aload 14
    //   386: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   389: aload 4
    //   391: invokevirtual 233	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   394: invokevirtual 230	java/io/InputStream:close	()V
    //   397: goto +10 -> 407
    //   400: astore 15
    //   402: aload 15
    //   404: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   407: aload 12
    //   409: areturn
    //   410: aload 4
    //   412: ifnull +57 -> 469
    //   415: aload 4
    //   417: invokevirtual 221	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   420: invokevirtual 226	java/io/OutputStream:close	()V
    //   423: goto +10 -> 433
    //   426: astore 17
    //   428: aload 17
    //   430: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   433: aload 4
    //   435: invokevirtual 193	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   438: invokevirtual 230	java/io/InputStream:close	()V
    //   441: goto +10 -> 451
    //   444: astore 18
    //   446: aload 18
    //   448: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   451: aload 4
    //   453: invokevirtual 233	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   456: invokevirtual 230	java/io/InputStream:close	()V
    //   459: goto +10 -> 469
    //   462: astore 19
    //   464: aload 19
    //   466: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   469: aload 16
    //   471: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   205	213	216	java/io/IOException
    //   223	231	234	java/io/IOException
    //   241	249	252	java/io/IOException
    //   114	200	262	finally
    //   114	200	271	java/lang/Throwable
    //   104	114	280	finally
    //   287	348	280	finally
    //   104	114	285	java/lang/Throwable
    //   353	361	364	java/io/IOException
    //   371	379	382	java/io/IOException
    //   389	397	400	java/io/IOException
    //   415	423	426	java/io/IOException
    //   433	441	444	java/io/IOException
    //   451	459	462	java/io/IOException
  }

  public static String a(Context paramContext, String paramString)
  {
    if ((paramString != null) && (!paramString.trim().equals("")))
    {
      if (a == null)
      {
        a = new HashMap();
        ArrayList localArrayList = a(paramContext, new String[] { "/system/bin/sh", "-c", "getprop" });
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          x.b(z.class, "Successfully get 'getprop' list.", new Object[0]);
          Pattern localPattern = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
          Iterator localIterator = localArrayList.iterator();
          while (localIterator.hasNext())
          {
            Matcher localMatcher = localPattern.matcher((String)localIterator.next());
            if (localMatcher.find())
              a.put(localMatcher.group(1), localMatcher.group(2));
          }
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(a.size());
          x.b(z.class, "System properties number: %d.", arrayOfObject);
        }
      }
      if (a.containsKey(paramString))
        return (String)a.get(paramString);
      return "fail";
    }
    return "";
  }

  // ERROR //
  public static String a(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +176 -> 177
    //   4: aload_0
    //   5: invokevirtual 43	java/io/File:exists	()Z
    //   8: ifeq +169 -> 177
    //   11: aload_0
    //   12: invokevirtual 46	java/io/File:canRead	()Z
    //   15: ifne +6 -> 21
    //   18: goto +159 -> 177
    //   21: new 172	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   28: astore_1
    //   29: new 185	java/io/BufferedReader
    //   32: dup
    //   33: new 187	java/io/InputStreamReader
    //   36: dup
    //   37: new 336	java/io/FileInputStream
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 339	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   45: ldc_w 341
    //   48: invokespecial 344	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   51: invokespecial 199	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   54: astore_2
    //   55: aload_2
    //   56: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   59: astore 11
    //   61: aload 11
    //   63: ifnull +20 -> 83
    //   66: aload_1
    //   67: aload 11
    //   69: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_1
    //   74: ldc 208
    //   76: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: goto -25 -> 55
    //   83: aload_1
    //   84: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: astore 12
    //   89: aload_2
    //   90: invokevirtual 345	java/io/BufferedReader:close	()V
    //   93: goto +11 -> 104
    //   96: astore 13
    //   98: aload 13
    //   100: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   103: pop
    //   104: aload 12
    //   106: areturn
    //   107: astore 7
    //   109: goto +14 -> 123
    //   112: astore_3
    //   113: aconst_null
    //   114: astore 4
    //   116: goto +38 -> 154
    //   119: astore 7
    //   121: aconst_null
    //   122: astore_2
    //   123: aload 7
    //   125: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   128: pop
    //   129: aload_2
    //   130: ifnull +18 -> 148
    //   133: aload_2
    //   134: invokevirtual 345	java/io/BufferedReader:close	()V
    //   137: goto +11 -> 148
    //   140: astore 9
    //   142: aload 9
    //   144: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   147: pop
    //   148: aconst_null
    //   149: areturn
    //   150: astore_3
    //   151: aload_2
    //   152: astore 4
    //   154: aload 4
    //   156: ifnull +19 -> 175
    //   159: aload 4
    //   161: invokevirtual 345	java/io/BufferedReader:close	()V
    //   164: goto +11 -> 175
    //   167: astore 5
    //   169: aload 5
    //   171: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   174: pop
    //   175: aload_3
    //   176: athrow
    //   177: aconst_null
    //   178: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   89	93	96	java/lang/Exception
    //   55	89	107	java/lang/Throwable
    //   21	55	112	finally
    //   21	55	119	java/lang/Throwable
    //   133	137	140	java/lang/Exception
    //   55	89	150	finally
    //   123	129	150	finally
    //   159	164	167	java/lang/Exception
  }

  public static String a(Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return "";
    try
    {
      StringWriter localStringWriter = new StringWriter();
      paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
      String str = localStringWriter.getBuffer().toString();
      return str;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return "fail";
  }

  // ERROR //
  public static String a(java.util.Date paramDate)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 118	java/text/SimpleDateFormat
    //   9: dup
    //   10: ldc 120
    //   12: getstatic 126	java/util/Locale:US	Ljava/util/Locale;
    //   15: invokespecial 129	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   18: aload_0
    //   19: invokevirtual 138	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   22: astore_1
    //   23: aload_1
    //   24: areturn
    //   25: new 131	java/util/Date
    //   28: dup
    //   29: invokespecial 139	java/util/Date:<init>	()V
    //   32: invokevirtual 142	java/util/Date:toString	()Ljava/lang/String;
    //   35: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   6	23	25	java/lang/Exception
  }

  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return "";
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() == 1)
        localStringBuffer.append("0");
      localStringBuffer.append(str);
    }
    return localStringBuffer.toString().toUpperCase();
  }

  public static Thread a(Runnable paramRunnable, String paramString)
  {
    try
    {
      Thread localThread = new Thread(paramRunnable);
      localThread.setName(paramString);
      localThread.start();
      return localThread;
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.getMessage();
      x.e("[Util] Failed to start a thread to execute task with message: %s", arrayOfObject);
    }
    return null;
  }

  // ERROR //
  public static ArrayList<String> a(Context paramContext, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: new 400	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 401	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: invokestatic 406	com/tencent/bugly/crashreport/common/info/a:a	(Landroid/content/Context;)Lcom/tencent/bugly/crashreport/common/info/a;
    //   12: invokevirtual 409	com/tencent/bugly/crashreport/common/info/a:J	()Z
    //   15: ifeq +28 -> 43
    //   18: new 400	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 401	java/util/ArrayList:<init>	()V
    //   25: astore_3
    //   26: aload_3
    //   27: new 160	java/lang/String
    //   30: dup
    //   31: ldc_w 411
    //   34: invokespecial 412	java/lang/String:<init>	(Ljava/lang/String;)V
    //   37: invokevirtual 415	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   40: pop
    //   41: aload_3
    //   42: areturn
    //   43: invokestatic 179	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   46: aload_1
    //   47: invokevirtual 183	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   50: astore 14
    //   52: new 185	java/io/BufferedReader
    //   55: dup
    //   56: new 187	java/io/InputStreamReader
    //   59: dup
    //   60: aload 14
    //   62: invokevirtual 193	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   65: invokespecial 196	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   68: invokespecial 199	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   71: astore 6
    //   73: aload 6
    //   75: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   78: astore 15
    //   80: aload 15
    //   82: ifnull +13 -> 95
    //   85: aload_2
    //   86: aload 15
    //   88: invokevirtual 415	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   91: pop
    //   92: goto -19 -> 73
    //   95: new 185	java/io/BufferedReader
    //   98: dup
    //   99: new 187	java/io/InputStreamReader
    //   102: dup
    //   103: aload 14
    //   105: invokevirtual 233	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   108: invokespecial 196	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   111: invokespecial 199	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   114: astore 7
    //   116: aload 7
    //   118: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   121: astore 16
    //   123: aload 16
    //   125: ifnull +13 -> 138
    //   128: aload_2
    //   129: aload 16
    //   131: invokevirtual 415	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   134: pop
    //   135: goto -19 -> 116
    //   138: aload 6
    //   140: invokevirtual 345	java/io/BufferedReader:close	()V
    //   143: goto +10 -> 153
    //   146: astore 17
    //   148: aload 17
    //   150: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   153: aload 7
    //   155: invokevirtual 345	java/io/BufferedReader:close	()V
    //   158: goto +10 -> 168
    //   161: astore 18
    //   163: aload 18
    //   165: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   168: aload_2
    //   169: areturn
    //   170: astore 5
    //   172: goto +38 -> 210
    //   175: astore 8
    //   177: aconst_null
    //   178: astore 9
    //   180: goto +91 -> 271
    //   183: astore 5
    //   185: aconst_null
    //   186: astore 7
    //   188: goto +22 -> 210
    //   191: astore 8
    //   193: aconst_null
    //   194: astore 6
    //   196: aconst_null
    //   197: astore 9
    //   199: goto +72 -> 271
    //   202: astore 5
    //   204: aconst_null
    //   205: astore 6
    //   207: aconst_null
    //   208: astore 7
    //   210: aload 5
    //   212: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   215: ifne +8 -> 223
    //   218: aload 5
    //   220: invokevirtual 106	java/lang/Throwable:printStackTrace	()V
    //   223: aload 6
    //   225: ifnull +18 -> 243
    //   228: aload 6
    //   230: invokevirtual 345	java/io/BufferedReader:close	()V
    //   233: goto +10 -> 243
    //   236: astore 13
    //   238: aload 13
    //   240: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   243: aload 7
    //   245: ifnull +18 -> 263
    //   248: aload 7
    //   250: invokevirtual 345	java/io/BufferedReader:close	()V
    //   253: goto +10 -> 263
    //   256: astore 12
    //   258: aload 12
    //   260: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   263: aconst_null
    //   264: areturn
    //   265: astore 8
    //   267: aload 7
    //   269: astore 9
    //   271: aload 6
    //   273: ifnull +18 -> 291
    //   276: aload 6
    //   278: invokevirtual 345	java/io/BufferedReader:close	()V
    //   281: goto +10 -> 291
    //   284: astore 11
    //   286: aload 11
    //   288: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   291: aload 9
    //   293: ifnull +18 -> 311
    //   296: aload 9
    //   298: invokevirtual 345	java/io/BufferedReader:close	()V
    //   301: goto +10 -> 311
    //   304: astore 10
    //   306: aload 10
    //   308: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   311: aload 8
    //   313: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   138	143	146	java/io/IOException
    //   153	158	161	java/io/IOException
    //   116	135	170	java/lang/Throwable
    //   73	116	175	finally
    //   73	116	183	java/lang/Throwable
    //   43	73	191	finally
    //   43	73	202	java/lang/Throwable
    //   228	233	236	java/io/IOException
    //   248	253	256	java/io/IOException
    //   116	135	265	finally
    //   210	223	265	finally
    //   276	281	284	java/io/IOException
    //   296	301	304	java/io/IOException
  }

  public static Map<String, String> a(int paramInt, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap(12);
    Map localMap = Thread.getAllStackTraces();
    if (localMap == null)
      return null;
    Thread.currentThread().getId();
    StringBuilder localStringBuilder1 = new StringBuilder();
    Iterator localIterator = localMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      int i = 0;
      localStringBuilder1.setLength(0);
      if ((localEntry.getValue() != null) && (((StackTraceElement[])localEntry.getValue()).length != 0))
      {
        StackTraceElement[] arrayOfStackTraceElement = (StackTraceElement[])localEntry.getValue();
        int j = arrayOfStackTraceElement.length;
        while (i < j)
        {
          StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
          if ((paramInt > 0) && (localStringBuilder1.length() >= paramInt))
          {
            StringBuilder localStringBuilder3 = new StringBuilder("\n[Stack over limit size :");
            localStringBuilder3.append(paramInt);
            localStringBuilder3.append(" , has been cutted !]");
            localStringBuilder1.append(localStringBuilder3.toString());
            break;
          }
          localStringBuilder1.append(localStackTraceElement.toString());
          localStringBuilder1.append("\n");
          i++;
        }
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(((Thread)localEntry.getKey()).getName());
        localStringBuilder2.append("(");
        localStringBuilder2.append(((Thread)localEntry.getKey()).getId());
        localStringBuilder2.append(")");
        localHashMap.put(localStringBuilder2.toString(), localStringBuilder1.toString());
      }
    }
    return localHashMap;
  }

  public static Map<String, PlugInBean> a(Parcel paramParcel)
  {
    Bundle localBundle = paramParcel.readBundle();
    HashMap localHashMap = null;
    if (localBundle == null)
      return null;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = ((Integer)localBundle.get("pluginNum")).intValue();
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      StringBuilder localStringBuilder1 = new StringBuilder("pluginKey");
      localStringBuilder1.append(k);
      localArrayList1.add(localBundle.getString(localStringBuilder1.toString()));
    }
    for (int m = 0; m < i; m++)
    {
      StringBuilder localStringBuilder2 = new StringBuilder("pluginVal");
      localStringBuilder2.append(m);
      localStringBuilder2.append("plugInId");
      String str1 = localBundle.getString(localStringBuilder2.toString());
      StringBuilder localStringBuilder3 = new StringBuilder("pluginVal");
      localStringBuilder3.append(m);
      localStringBuilder3.append("plugInUUID");
      String str2 = localBundle.getString(localStringBuilder3.toString());
      StringBuilder localStringBuilder4 = new StringBuilder("pluginVal");
      localStringBuilder4.append(m);
      localStringBuilder4.append("plugInVersion");
      localArrayList2.add(new PlugInBean(str1, localBundle.getString(localStringBuilder4.toString()), str2));
    }
    if (localArrayList1.size() == localArrayList2.size())
    {
      localHashMap = new HashMap(localArrayList1.size());
      while (j < localArrayList1.size())
      {
        localHashMap.put(localArrayList1.get(j), PlugInBean.class.cast(localArrayList2.get(j)));
        j++;
      }
    }
    x.e("map plugin parcel error!", new Object[0]);
    return localHashMap;
  }

  public static void a(Parcel paramParcel, Map<String, PlugInBean> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      int i = paramMap.size();
      ArrayList localArrayList1 = new ArrayList(i);
      ArrayList localArrayList2 = new ArrayList(i);
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localArrayList1.add(localEntry.getKey());
        localArrayList2.add(localEntry.getValue());
      }
      Bundle localBundle = new Bundle();
      localBundle.putInt("pluginNum", localArrayList1.size());
      int m;
      for (int j = 0; ; j++)
      {
        int k = localArrayList1.size();
        m = 0;
        if (j >= k)
          break;
        StringBuilder localStringBuilder1 = new StringBuilder("pluginKey");
        localStringBuilder1.append(j);
        localBundle.putString(localStringBuilder1.toString(), (String)localArrayList1.get(j));
      }
      while (m < localArrayList1.size())
      {
        StringBuilder localStringBuilder2 = new StringBuilder("pluginVal");
        localStringBuilder2.append(m);
        localStringBuilder2.append("plugInId");
        localBundle.putString(localStringBuilder2.toString(), ((PlugInBean)localArrayList2.get(m)).a);
        StringBuilder localStringBuilder3 = new StringBuilder("pluginVal");
        localStringBuilder3.append(m);
        localStringBuilder3.append("plugInUUID");
        localBundle.putString(localStringBuilder3.toString(), ((PlugInBean)localArrayList2.get(m)).c);
        StringBuilder localStringBuilder4 = new StringBuilder("pluginVal");
        localStringBuilder4.append(m);
        localStringBuilder4.append("plugInVersion");
        localBundle.putString(localStringBuilder4.toString(), ((PlugInBean)localArrayList2.get(m)).b);
        m++;
      }
      paramParcel.writeBundle(localBundle);
      return;
    }
    paramParcel.writeBundle(null);
  }

  // ERROR //
  public static void a(Class<?> paramClass, String paramString, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 542	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   5: astore 4
    //   7: aload 4
    //   9: iconst_1
    //   10: invokevirtual 545	java/lang/reflect/Field:setAccessible	(Z)V
    //   13: aload 4
    //   15: aconst_null
    //   16: aload_2
    //   17: invokevirtual 549	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   20: return
    //   21: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	20	21	java/lang/Exception
  }

  public static boolean a(Context paramContext, String paramString, long paramLong)
  {
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = paramString;
    arrayOfObject1[1] = Integer.valueOf(Process.myPid());
    arrayOfObject1[2] = Integer.valueOf(Process.myTid());
    x.c("[Util] try to lock file:%s (pid=%d | tid=%d)", arrayOfObject1);
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getFilesDir());
      localStringBuilder.append(File.separator);
      localStringBuilder.append(paramString);
      File localFile = new File(localStringBuilder.toString());
      if (localFile.exists())
      {
        if (System.currentTimeMillis() - localFile.lastModified() < paramLong)
          return false;
        x.c("[Util] lock file(%s) is expired, unlock it", new Object[] { paramString });
        b(paramContext, paramString);
      }
      if (localFile.createNewFile())
      {
        Object[] arrayOfObject3 = new Object[3];
        arrayOfObject3[0] = paramString;
        arrayOfObject3[1] = Integer.valueOf(Process.myPid());
        arrayOfObject3[2] = Integer.valueOf(Process.myTid());
        x.c("[Util] successfully locked file:%s (pid=%d | tid=%d)", arrayOfObject3);
        return true;
      }
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Integer.valueOf(Process.myPid());
      arrayOfObject2[2] = Integer.valueOf(Process.myTid());
      x.c("[Util] Failed to locked file:%s (pid=%d | tid=%d)", arrayOfObject2);
      return false;
    }
    catch (Throwable localThrowable)
    {
      x.a(localThrowable);
    }
    return false;
  }

  // ERROR //
  public static boolean a(File paramFile1, File paramFile2, int paramInt)
  {
    // Byte code:
    //   0: ldc_w 590
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   10: pop
    //   11: aload_0
    //   12: ifnull +441 -> 453
    //   15: aload_1
    //   16: ifnull +437 -> 453
    //   19: aload_0
    //   20: aload_1
    //   21: invokevirtual 591	java/io/File:equals	(Ljava/lang/Object;)Z
    //   24: ifeq +6 -> 30
    //   27: goto +426 -> 453
    //   30: aload_0
    //   31: invokevirtual 43	java/io/File:exists	()Z
    //   34: ifeq +406 -> 440
    //   37: aload_0
    //   38: invokevirtual 46	java/io/File:canRead	()Z
    //   41: ifne +6 -> 47
    //   44: goto +396 -> 440
    //   47: aload_1
    //   48: invokevirtual 594	java/io/File:getParentFile	()Ljava/io/File;
    //   51: ifnull +21 -> 72
    //   54: aload_1
    //   55: invokevirtual 594	java/io/File:getParentFile	()Ljava/io/File;
    //   58: invokevirtual 43	java/io/File:exists	()Z
    //   61: ifne +11 -> 72
    //   64: aload_1
    //   65: invokevirtual 594	java/io/File:getParentFile	()Ljava/io/File;
    //   68: invokevirtual 597	java/io/File:mkdirs	()Z
    //   71: pop
    //   72: aload_1
    //   73: invokevirtual 43	java/io/File:exists	()Z
    //   76: ifne +26 -> 102
    //   79: aload_1
    //   80: invokevirtual 583	java/io/File:createNewFile	()Z
    //   83: pop
    //   84: goto +18 -> 102
    //   87: astore 6
    //   89: aload 6
    //   91: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   94: ifne +8 -> 102
    //   97: aload 6
    //   99: invokevirtual 106	java/lang/Throwable:printStackTrace	()V
    //   102: aload_1
    //   103: invokevirtual 43	java/io/File:exists	()Z
    //   106: ifeq +332 -> 438
    //   109: aload_1
    //   110: invokevirtual 46	java/io/File:canRead	()Z
    //   113: ifne +6 -> 119
    //   116: goto +322 -> 438
    //   119: aconst_null
    //   120: astore 7
    //   122: new 336	java/io/FileInputStream
    //   125: dup
    //   126: aload_0
    //   127: invokespecial 339	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   130: astore 8
    //   132: new 599	java/util/zip/ZipOutputStream
    //   135: dup
    //   136: new 601	java/io/BufferedOutputStream
    //   139: dup
    //   140: new 603	java/io/FileOutputStream
    //   143: dup
    //   144: aload_1
    //   145: invokespecial 604	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   148: invokespecial 607	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   151: invokespecial 608	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   154: astore 9
    //   156: aload 9
    //   158: bipush 8
    //   160: invokevirtual 611	java/util/zip/ZipOutputStream:setMethod	(I)V
    //   163: aload 9
    //   165: new 613	java/util/zip/ZipEntry
    //   168: dup
    //   169: aload_0
    //   170: invokevirtual 614	java/io/File:getName	()Ljava/lang/String;
    //   173: invokespecial 615	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   176: invokevirtual 619	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   179: sipush 5000
    //   182: newarray byte
    //   184: astore 18
    //   186: aload 8
    //   188: aload 18
    //   190: invokevirtual 623	java/io/FileInputStream:read	([B)I
    //   193: istore 19
    //   195: iload 19
    //   197: ifle +16 -> 213
    //   200: aload 9
    //   202: aload 18
    //   204: iconst_0
    //   205: iload 19
    //   207: invokevirtual 626	java/util/zip/ZipOutputStream:write	([BII)V
    //   210: goto -24 -> 186
    //   213: aload 9
    //   215: invokevirtual 629	java/util/zip/ZipOutputStream:flush	()V
    //   218: aload 9
    //   220: invokevirtual 632	java/util/zip/ZipOutputStream:closeEntry	()V
    //   223: aload 8
    //   225: invokevirtual 633	java/io/FileInputStream:close	()V
    //   228: goto +10 -> 238
    //   231: astore 20
    //   233: aload 20
    //   235: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   238: aload 9
    //   240: invokevirtual 634	java/util/zip/ZipOutputStream:close	()V
    //   243: goto +10 -> 253
    //   246: astore 21
    //   248: aload 21
    //   250: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   253: ldc_w 636
    //   256: iconst_0
    //   257: anewarray 4	java/lang/Object
    //   260: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   263: pop
    //   264: iconst_1
    //   265: ireturn
    //   266: astore 11
    //   268: goto +116 -> 384
    //   271: astore 10
    //   273: goto +16 -> 289
    //   276: astore 11
    //   278: aconst_null
    //   279: astore 9
    //   281: goto +103 -> 384
    //   284: astore 10
    //   286: aconst_null
    //   287: astore 9
    //   289: aload 8
    //   291: astore 7
    //   293: goto +19 -> 312
    //   296: astore 11
    //   298: aconst_null
    //   299: astore 8
    //   301: aconst_null
    //   302: astore 9
    //   304: goto +80 -> 384
    //   307: astore 10
    //   309: aconst_null
    //   310: astore 9
    //   312: aload 10
    //   314: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   317: ifne +8 -> 325
    //   320: aload 10
    //   322: invokevirtual 106	java/lang/Throwable:printStackTrace	()V
    //   325: aload 7
    //   327: ifnull +18 -> 345
    //   330: aload 7
    //   332: invokevirtual 633	java/io/FileInputStream:close	()V
    //   335: goto +10 -> 345
    //   338: astore 17
    //   340: aload 17
    //   342: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   345: aload 9
    //   347: ifnull +18 -> 365
    //   350: aload 9
    //   352: invokevirtual 634	java/util/zip/ZipOutputStream:close	()V
    //   355: goto +10 -> 365
    //   358: astore 16
    //   360: aload 16
    //   362: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   365: ldc_w 636
    //   368: iconst_0
    //   369: anewarray 4	java/lang/Object
    //   372: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   375: pop
    //   376: iconst_0
    //   377: ireturn
    //   378: astore 11
    //   380: aload 7
    //   382: astore 8
    //   384: aload 8
    //   386: ifnull +18 -> 404
    //   389: aload 8
    //   391: invokevirtual 633	java/io/FileInputStream:close	()V
    //   394: goto +10 -> 404
    //   397: astore 14
    //   399: aload 14
    //   401: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   404: aload 9
    //   406: ifnull +18 -> 424
    //   409: aload 9
    //   411: invokevirtual 634	java/util/zip/ZipOutputStream:close	()V
    //   414: goto +10 -> 424
    //   417: astore 13
    //   419: aload 13
    //   421: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   424: ldc_w 636
    //   427: iconst_0
    //   428: anewarray 4	java/lang/Object
    //   431: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   434: pop
    //   435: aload 11
    //   437: athrow
    //   438: iconst_0
    //   439: ireturn
    //   440: ldc_w 638
    //   443: iconst_0
    //   444: anewarray 4	java/lang/Object
    //   447: invokestatic 158	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   450: pop
    //   451: iconst_0
    //   452: ireturn
    //   453: ldc_w 640
    //   456: iconst_0
    //   457: anewarray 4	java/lang/Object
    //   460: invokestatic 158	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   463: pop
    //   464: iconst_0
    //   465: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   47	84	87	java/lang/Throwable
    //   223	228	231	java/io/IOException
    //   238	243	246	java/io/IOException
    //   156	223	266	finally
    //   156	223	271	java/lang/Throwable
    //   132	156	276	finally
    //   132	156	284	java/lang/Throwable
    //   122	132	296	finally
    //   122	132	307	java/lang/Throwable
    //   330	335	338	java/io/IOException
    //   350	355	358	java/io/IOException
    //   312	325	378	finally
    //   389	394	397	java/io/IOException
    //   409	414	417	java/io/IOException
  }

  public static boolean a(Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      w localw = w.a();
      if (localw != null)
        return localw.a(paramRunnable);
      String[] arrayOfString = paramRunnable.getClass().getName().split("\\.");
      if (a(paramRunnable, arrayOfString[(arrayOfString.length - 1)]) != null)
        return true;
    }
    return false;
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() <= 0);
  }

  // ERROR //
  public static byte[] a(int paramInt)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: bipush 16
    //   5: newarray byte
    //   7: astore 9
    //   9: new 665	java/io/DataInputStream
    //   12: dup
    //   13: new 336	java/io/FileInputStream
    //   16: dup
    //   17: new 36	java/io/File
    //   20: dup
    //   21: ldc_w 667
    //   24: invokespecial 573	java/io/File:<init>	(Ljava/lang/String;)V
    //   27: invokespecial 339	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   30: invokespecial 668	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   33: astore_2
    //   34: aload_2
    //   35: aload 9
    //   37: invokevirtual 672	java/io/DataInputStream:readFully	([B)V
    //   40: aload_2
    //   41: invokevirtual 673	java/io/DataInputStream:close	()V
    //   44: ldc 2
    //   46: monitorexit
    //   47: aload 9
    //   49: areturn
    //   50: astore_1
    //   51: goto +12 -> 63
    //   54: astore_3
    //   55: aconst_null
    //   56: astore_2
    //   57: goto +71 -> 128
    //   60: astore_1
    //   61: aconst_null
    //   62: astore_2
    //   63: ldc_w 675
    //   66: iconst_1
    //   67: anewarray 4	java/lang/Object
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: invokestatic 398	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   77: pop
    //   78: aload_2
    //   79: ifnull +7 -> 86
    //   82: aload_2
    //   83: invokevirtual 673	java/io/DataInputStream:close	()V
    //   86: ldc_w 677
    //   89: invokestatic 683	javax/crypto/KeyGenerator:getInstance	(Ljava/lang/String;)Ljavax/crypto/KeyGenerator;
    //   92: astore 7
    //   94: aload 7
    //   96: sipush 128
    //   99: new 685	java/security/SecureRandom
    //   102: dup
    //   103: invokespecial 686	java/security/SecureRandom:<init>	()V
    //   106: invokevirtual 690	javax/crypto/KeyGenerator:init	(ILjava/security/SecureRandom;)V
    //   109: aload 7
    //   111: invokevirtual 694	javax/crypto/KeyGenerator:generateKey	()Ljavax/crypto/SecretKey;
    //   114: invokeinterface 700 1 0
    //   119: astore 8
    //   121: ldc 2
    //   123: monitorexit
    //   124: aload 8
    //   126: areturn
    //   127: astore_3
    //   128: aload_2
    //   129: ifnull +10 -> 139
    //   132: aload_2
    //   133: invokevirtual 673	java/io/DataInputStream:close	()V
    //   136: goto +3 -> 139
    //   139: aload_3
    //   140: athrow
    //   141: aload 4
    //   143: invokestatic 702	com/tencent/bugly/proguard/x:b	(Ljava/lang/Throwable;)Z
    //   146: ifne +8 -> 154
    //   149: aload 4
    //   151: invokevirtual 703	java/lang/Exception:printStackTrace	()V
    //   154: ldc 2
    //   156: monitorexit
    //   157: aconst_null
    //   158: areturn
    //   159: ldc 2
    //   161: monitorexit
    //   162: aload 5
    //   164: athrow
    //   165: astore 5
    //   167: goto -8 -> 159
    //   170: astore 4
    //   172: goto -31 -> 141
    //
    // Exception table:
    //   from	to	target	type
    //   34	40	50	java/lang/Exception
    //   3	34	54	finally
    //   3	34	60	java/lang/Exception
    //   34	40	127	finally
    //   63	78	127	finally
    //   40	44	165	finally
    //   82	121	165	finally
    //   132	141	165	finally
    //   141	154	165	finally
    //   40	44	170	java/lang/Exception
    //   82	121	170	java/lang/Exception
    //   132	141	170	java/lang/Exception
  }

  @TargetApi(19)
  public static byte[] a(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte2, "AES");
      Cipher localCipher = Cipher.getInstance("AES/GCM/NoPadding");
      if ((Build.VERSION.SDK_INT >= 21) && (!b))
      {
        GCMParameterSpec localGCMParameterSpec = new GCMParameterSpec(localCipher.getBlockSize() << 3, paramArrayOfByte2);
        try
        {
          localCipher.init(paramInt, localSecretKeySpec, localGCMParameterSpec);
        }
        catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
        {
          b = true;
          throw localInvalidAlgorithmParameterException;
        }
      }
      else
      {
        localCipher.init(paramInt, localSecretKeySpec, new IvParameterSpec(paramArrayOfByte2));
      }
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte1);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      if (!x.b(localException))
        localException.printStackTrace();
    }
    return null;
  }

  public static byte[] a(Parcelable paramParcelable)
  {
    Parcel localParcel = Parcel.obtain();
    paramParcelable.writeToParcel(localParcel, 0);
    byte[] arrayOfByte = localParcel.marshall();
    localParcel.recycle();
    return arrayOfByte;
  }

  // ERROR //
  public static byte[] a(File paramFile, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc_w 590
    //   3: iconst_0
    //   4: anewarray 4	java/lang/Object
    //   7: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   10: pop
    //   11: aload_0
    //   12: ifnull +392 -> 404
    //   15: aload_0
    //   16: invokevirtual 43	java/io/File:exists	()Z
    //   19: ifeq +385 -> 404
    //   22: aload_0
    //   23: invokevirtual 46	java/io/File:canRead	()Z
    //   26: ifeq +378 -> 404
    //   29: new 336	java/io/FileInputStream
    //   32: dup
    //   33: aload_0
    //   34: invokespecial 339	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   37: astore 5
    //   39: aload_0
    //   40: invokevirtual 614	java/io/File:getName	()Ljava/lang/String;
    //   43: astore 4
    //   45: goto +3 -> 48
    //   48: new 761	java/io/ByteArrayInputStream
    //   51: dup
    //   52: aload_1
    //   53: ldc_w 763
    //   56: invokevirtual 767	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   59: invokespecial 768	java/io/ByteArrayInputStream:<init>	([B)V
    //   62: astore 6
    //   64: new 770	java/io/ByteArrayOutputStream
    //   67: dup
    //   68: invokespecial 771	java/io/ByteArrayOutputStream:<init>	()V
    //   71: astore 7
    //   73: new 599	java/util/zip/ZipOutputStream
    //   76: dup
    //   77: aload 7
    //   79: invokespecial 608	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   82: astore 8
    //   84: aload 8
    //   86: bipush 8
    //   88: invokevirtual 611	java/util/zip/ZipOutputStream:setMethod	(I)V
    //   91: aload 8
    //   93: new 613	java/util/zip/ZipEntry
    //   96: dup
    //   97: aload 4
    //   99: invokespecial 615	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   102: invokevirtual 619	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   105: sipush 1024
    //   108: newarray byte
    //   110: astore 17
    //   112: aload 5
    //   114: ifnull +30 -> 144
    //   117: aload 5
    //   119: aload 17
    //   121: invokevirtual 623	java/io/FileInputStream:read	([B)I
    //   124: istore 23
    //   126: iload 23
    //   128: ifle +16 -> 144
    //   131: aload 8
    //   133: aload 17
    //   135: iconst_0
    //   136: iload 23
    //   138: invokevirtual 626	java/util/zip/ZipOutputStream:write	([BII)V
    //   141: goto -24 -> 117
    //   144: aload 6
    //   146: aload 17
    //   148: invokevirtual 772	java/io/ByteArrayInputStream:read	([B)I
    //   151: istore 18
    //   153: iload 18
    //   155: ifle +16 -> 171
    //   158: aload 8
    //   160: aload 17
    //   162: iconst_0
    //   163: iload 18
    //   165: invokevirtual 626	java/util/zip/ZipOutputStream:write	([BII)V
    //   168: goto -24 -> 144
    //   171: aload 8
    //   173: invokevirtual 632	java/util/zip/ZipOutputStream:closeEntry	()V
    //   176: aload 8
    //   178: invokevirtual 629	java/util/zip/ZipOutputStream:flush	()V
    //   181: aload 8
    //   183: invokevirtual 775	java/util/zip/ZipOutputStream:finish	()V
    //   186: aload 7
    //   188: invokevirtual 778	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   191: astore 19
    //   193: aload 5
    //   195: ifnull +18 -> 213
    //   198: aload 5
    //   200: invokevirtual 633	java/io/FileInputStream:close	()V
    //   203: goto +10 -> 213
    //   206: astore 22
    //   208: aload 22
    //   210: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   213: aload 8
    //   215: invokevirtual 634	java/util/zip/ZipOutputStream:close	()V
    //   218: goto +10 -> 228
    //   221: astore 20
    //   223: aload 20
    //   225: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   228: ldc_w 636
    //   231: iconst_0
    //   232: anewarray 4	java/lang/Object
    //   235: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   238: pop
    //   239: aload 19
    //   241: areturn
    //   242: astore 13
    //   244: goto +16 -> 260
    //   247: astore 9
    //   249: aconst_null
    //   250: astore 8
    //   252: goto +76 -> 328
    //   255: astore 13
    //   257: aconst_null
    //   258: astore 8
    //   260: aload 13
    //   262: invokestatic 54	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   265: ifne +8 -> 273
    //   268: aload 13
    //   270: invokevirtual 106	java/lang/Throwable:printStackTrace	()V
    //   273: aload 5
    //   275: ifnull +18 -> 293
    //   278: aload 5
    //   280: invokevirtual 633	java/io/FileInputStream:close	()V
    //   283: goto +10 -> 293
    //   286: astore 16
    //   288: aload 16
    //   290: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   293: aload 8
    //   295: ifnull +18 -> 313
    //   298: aload 8
    //   300: invokevirtual 634	java/util/zip/ZipOutputStream:close	()V
    //   303: goto +10 -> 313
    //   306: astore 15
    //   308: aload 15
    //   310: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   313: ldc_w 636
    //   316: iconst_0
    //   317: anewarray 4	java/lang/Object
    //   320: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   323: pop
    //   324: aconst_null
    //   325: areturn
    //   326: astore 9
    //   328: aload 5
    //   330: ifnull +18 -> 348
    //   333: aload 5
    //   335: invokevirtual 633	java/io/FileInputStream:close	()V
    //   338: goto +10 -> 348
    //   341: astore 12
    //   343: aload 12
    //   345: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   348: aload 8
    //   350: ifnull +18 -> 368
    //   353: aload 8
    //   355: invokevirtual 634	java/util/zip/ZipOutputStream:close	()V
    //   358: goto +10 -> 368
    //   361: astore 11
    //   363: aload 11
    //   365: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   368: ldc_w 636
    //   371: iconst_0
    //   372: anewarray 4	java/lang/Object
    //   375: invokestatic 562	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   378: pop
    //   379: aload 9
    //   381: athrow
    //   382: astore 9
    //   384: aconst_null
    //   385: astore 8
    //   387: aconst_null
    //   388: astore 5
    //   390: goto -62 -> 328
    //   393: astore 13
    //   395: aconst_null
    //   396: astore 8
    //   398: aconst_null
    //   399: astore 5
    //   401: goto -141 -> 260
    //   404: aload_2
    //   405: astore 4
    //   407: aconst_null
    //   408: astore 5
    //   410: goto -362 -> 48
    //
    // Exception table:
    //   from	to	target	type
    //   198	203	206	java/io/IOException
    //   213	218	221	java/io/IOException
    //   84	193	242	java/lang/Throwable
    //   39	84	247	finally
    //   39	84	255	java/lang/Throwable
    //   278	283	286	java/io/IOException
    //   298	303	306	java/io/IOException
    //   84	193	326	finally
    //   260	273	326	finally
    //   333	338	341	java/io/IOException
    //   353	358	361	java/io/IOException
    //   15	39	382	finally
    //   15	39	393	java/lang/Throwable
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte != null) && (paramInt != -1))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramArrayOfByte.length);
      String str;
      if (paramInt == 2)
        str = "Gzip";
      else
        str = "zip";
      arrayOfObject[1] = str;
      x.c("[Util] Zip %d bytes data with type %s", arrayOfObject);
      try
      {
        ab localab = aa.a(paramInt);
        if (localab == null)
          return null;
        byte[] arrayOfByte = localab.a(paramArrayOfByte);
        return arrayOfByte;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    }
    return paramArrayOfByte;
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      byte[] arrayOfByte = a(a(paramArrayOfByte, 2), 1, paramString);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  private static byte[] a(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
    if ((paramArrayOfByte != null) && (paramInt != -1))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramArrayOfByte.length);
      arrayOfObject[1] = Integer.valueOf(paramInt);
      x.c("rqdp{  enD:} %d %d", arrayOfObject);
      try
      {
        ag localag = a.a(paramInt);
        if (localag == null)
          return null;
        localag.a(paramString);
        byte[] arrayOfByte = localag.b(paramArrayOfByte);
        return arrayOfByte;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    }
    return paramArrayOfByte;
  }

  public static long b()
  {
    try
    {
      long l = 86400000L * ((System.currentTimeMillis() + TimeZone.getDefault().getRawOffset()) / 86400000L);
      int i = TimeZone.getDefault().getRawOffset();
      return l - i;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return -1L;
  }

  private static BufferedReader b(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()) && (paramFile.canRead()))
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(paramFile), "utf-8"));
        return localBufferedReader;
      }
      catch (Throwable localThrowable)
      {
        x.a(localThrowable);
        return null;
      }
    return null;
  }

  public static String b(String paramString1, String paramString2)
  {
    if ((com.tencent.bugly.crashreport.common.info.a.b() != null) && (com.tencent.bugly.crashreport.common.info.a.b().E != null))
      return com.tencent.bugly.crashreport.common.info.a.b().E.getString(paramString1, paramString2);
    return "";
  }

  public static String b(Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return "";
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    localPrintWriter.flush();
    return localStringWriter.toString();
  }

  public static String b(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0))
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
        localMessageDigest.update(paramArrayOfByte);
        String str = a(localMessageDigest.digest());
        return str;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    return "NULL";
  }

  public static Map<String, String> b(Parcel paramParcel)
  {
    Bundle localBundle = paramParcel.readBundle();
    HashMap localHashMap = null;
    if (localBundle == null)
      return null;
    ArrayList localArrayList1 = localBundle.getStringArrayList("keys");
    ArrayList localArrayList2 = localBundle.getStringArrayList("values");
    int i = 0;
    if ((localArrayList1 != null) && (localArrayList2 != null) && (localArrayList1.size() == localArrayList2.size()))
      localHashMap = new HashMap(localArrayList1.size());
    while (i < localArrayList1.size())
    {
      localHashMap.put(localArrayList1.get(i), localArrayList2.get(i));
      i++;
      continue;
      x.e("map parcel error!", new Object[0]);
    }
    return localHashMap;
  }

  public static void b(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }

  public static void b(Parcel paramParcel, Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      int i = paramMap.size();
      ArrayList localArrayList1 = new ArrayList(i);
      ArrayList localArrayList2 = new ArrayList(i);
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localArrayList1.add(localEntry.getKey());
        localArrayList2.add(localEntry.getValue());
      }
      Bundle localBundle = new Bundle();
      localBundle.putStringArrayList("keys", localArrayList1);
      localBundle.putStringArrayList("values", localArrayList2);
      paramParcel.writeBundle(localBundle);
      return;
    }
    paramParcel.writeBundle(null);
  }

  public static void b(String paramString)
  {
    if (paramString == null)
      return;
    File localFile = new File(paramString);
    if ((localFile.isFile()) && (localFile.exists()) && (localFile.canWrite()))
      localFile.delete();
  }

  public static boolean b(Context paramContext, String paramString)
  {
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = paramString;
    arrayOfObject1[1] = Integer.valueOf(Process.myPid());
    arrayOfObject1[2] = Integer.valueOf(Process.myTid());
    x.c("[Util] try to unlock file:%s (pid=%d | tid=%d)", arrayOfObject1);
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getFilesDir());
      localStringBuilder.append(File.separator);
      localStringBuilder.append(paramString);
      File localFile = new File(localStringBuilder.toString());
      if (localFile.exists())
      {
        if (localFile.delete())
        {
          Object[] arrayOfObject2 = new Object[3];
          arrayOfObject2[0] = paramString;
          arrayOfObject2[1] = Integer.valueOf(Process.myPid());
          arrayOfObject2[2] = Integer.valueOf(Process.myTid());
          x.c("[Util] successfully unlocked file:%s (pid=%d | tid=%d)", arrayOfObject2);
          return true;
        }
        return false;
      }
      return true;
    }
    catch (Throwable localThrowable)
    {
      x.a(localThrowable);
    }
    return false;
  }

  public static byte[] b(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(paramArrayOfByte2));
      Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      localCipher.init(1, localPublicKey);
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte1);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      if (!x.b(localException))
        localException.printStackTrace();
    }
    return null;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte != null) && (paramInt != -1))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramArrayOfByte.length);
      String str;
      if (paramInt == 2)
        str = "Gzip";
      else
        str = "zip";
      arrayOfObject[1] = str;
      x.c("[Util] Unzip %d bytes data with type %s", arrayOfObject);
      try
      {
        ab localab = aa.a(paramInt);
        if (localab == null)
          return null;
        byte[] arrayOfByte = localab.b(paramArrayOfByte);
        return arrayOfByte;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    }
    return paramArrayOfByte;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    try
    {
      byte[] arrayOfByte = b(b(paramArrayOfByte, 1, paramString), 2);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      if (!x.a(localException))
        localException.printStackTrace();
    }
    return null;
  }

  private static byte[] b(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
    if ((paramArrayOfByte != null) && (paramInt != -1))
      try
      {
        ag localag = a.a(paramInt);
        if (localag == null)
          return null;
        localag.a(paramString);
        byte[] arrayOfByte = localag.a(paramArrayOfByte);
        return arrayOfByte;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        arrayOfObject[1] = paramString;
        x.d("encrytype %d %s", arrayOfObject);
        return null;
      }
    return paramArrayOfByte;
  }

  public static long c(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return -1L;
    try
    {
      long l = Long.parseLong(new String(paramArrayOfByte, "utf-8"));
      return l;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return -1L;
  }

  public static boolean c(String paramString)
  {
    int i;
    if ((paramString != null) && (paramString.trim().length() > 0))
      i = 0;
    else
      i = 1;
    if (i != 0)
      return false;
    if (paramString.length() > 255)
    {
      x.a("URL(%s)'s length is larger than 255.", new Object[] { paramString });
      return false;
    }
    if (!paramString.toLowerCase().startsWith("http"))
    {
      x.a("URL(%s) is not start with \"http\".", new Object[] { paramString });
      return false;
    }
    if (!paramString.toLowerCase().contains("qq.com"))
    {
      x.a("URL(%s) does not contain \"qq.com\".", new Object[] { paramString });
      return false;
    }
    return true;
  }

  public static byte[] c(long paramLong)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramLong);
      byte[] arrayOfByte = localStringBuilder.toString().getBytes("utf-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }

  public static String d(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return "null";
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      if (i != 0)
        localStringBuffer.append(':');
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() == 1)
      {
        StringBuilder localStringBuilder = new StringBuilder("0");
        localStringBuilder.append(str);
        str = localStringBuilder.toString();
      }
      localStringBuffer.append(str);
    }
    return localStringBuffer.toString().toUpperCase();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.z
 * JD-Core Version:    0.6.1
 */