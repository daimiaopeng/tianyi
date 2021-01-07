package com.cndatacom.e;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class j
{
  private static String a = "CDCLogsDefault";
  private static boolean b;
  private static boolean c;
  private static boolean d;

  // ERROR //
  private static String a(Throwable paramThrowable)
  {
    // Byte code:
    //   0: new 19	java/io/StringWriter
    //   3: dup
    //   4: invokespecial 22	java/io/StringWriter:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: new 24	java/io/PrintWriter
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 27	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   17: invokevirtual 33	java/lang/Throwable:printStackTrace	(Ljava/io/PrintWriter;)V
    //   20: aload_1
    //   21: invokevirtual 37	java/io/StringWriter:toString	()Ljava/lang/String;
    //   24: astore_2
    //   25: aload_2
    //   26: areturn
    //   27: ldc 39
    //   29: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	25	27	java/lang/Exception
  }

  public static void a(String paramString1, Exception paramException, String paramString2)
  {
    b(paramString1, paramString2);
    b(paramString1, a(paramException));
  }

  public static void a(String paramString1, String paramString2)
  {
  }

  public static void a(String paramString1, Throwable paramThrowable, String paramString2)
  {
    b(paramString1, paramString2);
    b(paramString1, a(paramThrowable));
  }

  public static void a(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    a = paramString;
    b = paramBoolean1;
    c = paramBoolean2;
    d = paramBoolean3;
  }

  public static void b(String paramString1, String paramString2)
  {
    if (d)
      Log.i(paramString1, paramString2);
    if (a.equals(paramString1))
    {
      if (b)
        c(paramString1, paramString2);
    }
    else if (c)
      c(paramString1, paramString2);
  }

  private static void c(String paramString1, String paramString2)
  {
    String str1;
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(Environment.getExternalStorageDirectory().getPath());
      localStringBuilder1.append("/");
      localStringBuilder1.append(paramString1);
      localStringBuilder1.append("/");
      str1 = localStringBuilder1.toString();
    }
    try
    {
      Date localDate = new Date();
      SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
      SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
      String str2 = localSimpleDateFormat1.format(localDate);
      String str3 = localSimpleDateFormat2.format(localDate);
      File localFile1 = new File(str1);
      if (!localFile1.exists())
        localFile1.mkdirs();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(str1);
      localStringBuilder2.append(str3);
      localStringBuilder2.append(".txt");
      File localFile2 = new File(localStringBuilder2.toString());
      if (!localFile2.exists())
        localFile2.createNewFile();
      FileWriter localFileWriter = new FileWriter(localFile2, true);
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("\r\n=============");
      localStringBuilder3.append(str2);
      localStringBuilder3.append("=====================\r\n");
      localFileWriter.append(localStringBuilder3.toString());
      localFileWriter.append(paramString2);
      localFileWriter.flush();
      localFileWriter.close();
    }
    catch (IOException localIOException)
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.j
 * JD-Core Version:    0.6.1
 */