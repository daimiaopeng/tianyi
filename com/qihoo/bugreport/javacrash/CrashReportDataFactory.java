package com.qihoo.bugreport.javacrash;

import android.content.Context;
import com.qihoo.jiagu.a;
import com.qihoo.jiagu.b;
import com.qihoo.jiagu.c;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CrashReportDataFactory
{
  final Calendar a;

  public CrashReportDataFactory(Context paramContext, Calendar paramCalendar)
  {
    this.a = paramCalendar;
  }

  static String a(String paramString)
  {
    if (paramString == null);
    StringBuffer localStringBuffer;
    for (String str = null; ; str = localStringBuffer.toString())
    {
      return str;
      localStringBuffer = new StringBuffer();
      String[] arrayOfString = paramString.split("\n");
      for (int i = 0; i < arrayOfString.length; i++)
        if (!arrayOfString[i].contains(b.b))
          localStringBuffer.append(arrayOfString[i]).append("\n");
    }
  }

  static String a(String paramString, ArrayList<String> paramArrayList)
  {
    String str;
    if ((paramString == null) || (paramArrayList == null))
    {
      str = null;
      return str;
    }
    String[] arrayOfString = paramString.split("\n");
    int i = 0;
    int j = 0;
    label25: if (i < -1 + arrayOfString.length)
      if (!arrayOfString[i].replaceAll("\t", "").trim().startsWith("Caused by:"))
        break label135;
    label130: label135: for (int m = i; ; m = j)
    {
      i++;
      j = m;
      break label25;
      for (int k = j + 1; ; k++)
      {
        if (k >= arrayOfString.length)
          break label130;
        str = arrayOfString[k].replaceAll("\t", "").trim();
        if (str.startsWith("at"))
          str = str.substring(2).trim();
        if (paramArrayList.contains(str))
          break;
      }
      str = null;
      break;
    }
  }

  static String a(Calendar paramCalendar)
  {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH).format(Long.valueOf(paramCalendar.getTimeInMillis()));
  }

  static ArrayList<String> a(Throwable paramThrowable)
  {
    ArrayList localArrayList = new ArrayList();
    while (paramThrowable != null)
    {
      StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
      int i = arrayOfStackTraceElement.length;
      for (int j = 0; j < i; j++)
        localArrayList.add(arrayOfStackTraceElement[j].toString());
      paramThrowable = paramThrowable.getCause();
    }
    return localArrayList;
  }

  // ERROR //
  static java.util.HashMap<String, String> a()
  {
    // Byte code:
    //   0: new 127	java/util/HashMap
    //   3: dup
    //   4: invokespecial 128	java/util/HashMap:<init>	()V
    //   7: astore_0
    //   8: invokestatic 134	android/os/Process:myPid	()I
    //   11: istore_1
    //   12: aload_0
    //   13: ldc 136
    //   15: iload_1
    //   16: invokestatic 140	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   19: invokevirtual 144	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   22: pop
    //   23: new 146	java/io/File
    //   26: dup
    //   27: new 148	java/lang/StringBuilder
    //   30: dup
    //   31: ldc 150
    //   33: invokespecial 153	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   36: iload_1
    //   37: invokevirtual 156	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   40: ldc 158
    //   42: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokespecial 163	java/io/File:<init>	(Ljava/lang/String;)V
    //   51: astore_3
    //   52: new 165	java/io/FileInputStream
    //   55: dup
    //   56: aload_3
    //   57: invokespecial 168	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   60: astore 4
    //   62: new 170	java/io/BufferedReader
    //   65: dup
    //   66: new 172	java/io/InputStreamReader
    //   69: dup
    //   70: aload 4
    //   72: invokespecial 175	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   75: invokespecial 178	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   78: astore 5
    //   80: aload 5
    //   82: invokevirtual 181	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   85: astore 10
    //   87: aload 10
    //   89: ifnull +41 -> 130
    //   92: aload 10
    //   94: ldc 183
    //   96: invokevirtual 61	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   99: ifeq -19 -> 80
    //   102: aload_0
    //   103: ldc 185
    //   105: aload 10
    //   107: iconst_1
    //   108: aload 10
    //   110: ldc 187
    //   112: invokevirtual 191	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   115: iadd
    //   116: invokevirtual 67	java/lang/String:substring	(I)Ljava/lang/String;
    //   119: ldc 193
    //   121: ldc 48
    //   123: invokevirtual 196	java/lang/String:replaceFirst	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   126: invokevirtual 144	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: aload 5
    //   132: invokevirtual 199	java/io/BufferedReader:close	()V
    //   135: aload_0
    //   136: areturn
    //   137: astore 8
    //   139: aload 5
    //   141: invokevirtual 199	java/io/BufferedReader:close	()V
    //   144: goto -9 -> 135
    //   147: astore 9
    //   149: goto -14 -> 135
    //   152: astore 6
    //   154: aload 5
    //   156: invokevirtual 199	java/io/BufferedReader:close	()V
    //   159: aload 6
    //   161: athrow
    //   162: astore 11
    //   164: goto -29 -> 135
    //   167: astore 7
    //   169: goto -10 -> 159
    //   172: astore 13
    //   174: goto -39 -> 135
    //
    // Exception table:
    //   from	to	target	type
    //   80	130	137	java/io/IOException
    //   139	144	147	java/io/IOException
    //   80	130	152	finally
    //   130	135	162	java/io/IOException
    //   154	159	167	java/io/IOException
    //   52	62	172	java/io/FileNotFoundException
  }

  private static boolean a(List<String> paramList, String paramString)
  {
    for (int i = 0; ; i++)
    {
      int j = paramList.size();
      boolean bool = false;
      if (i < j)
      {
        if (paramString.startsWith((String)paramList.get(i)))
          bool = true;
      }
      else
        return bool;
    }
  }

  // ERROR //
  static String c()
  {
    // Byte code:
    //   0: invokestatic 134	android/os/Process:myPid	()I
    //   3: istore_0
    //   4: new 146	java/io/File
    //   7: dup
    //   8: new 148	java/lang/StringBuilder
    //   11: dup
    //   12: ldc 150
    //   14: invokespecial 153	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   17: iload_0
    //   18: invokevirtual 156	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   21: ldc 212
    //   23: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokespecial 163	java/io/File:<init>	(Ljava/lang/String;)V
    //   32: astore_1
    //   33: new 165	java/io/FileInputStream
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 168	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   41: astore_2
    //   42: new 170	java/io/BufferedReader
    //   45: dup
    //   46: new 172	java/io/InputStreamReader
    //   49: dup
    //   50: aload_2
    //   51: invokespecial 175	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: invokespecial 178	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   57: astore_3
    //   58: aload_3
    //   59: invokevirtual 181	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   62: astore 9
    //   64: aload_3
    //   65: invokevirtual 199	java/io/BufferedReader:close	()V
    //   68: aconst_null
    //   69: astore 8
    //   71: aload 9
    //   73: ifnonnull +35 -> 108
    //   76: aload 8
    //   78: areturn
    //   79: astore 6
    //   81: aload_3
    //   82: invokevirtual 199	java/io/BufferedReader:close	()V
    //   85: aconst_null
    //   86: astore 8
    //   88: goto -12 -> 76
    //   91: astore 7
    //   93: aconst_null
    //   94: astore 8
    //   96: goto -20 -> 76
    //   99: astore 4
    //   101: aload_3
    //   102: invokevirtual 199	java/io/BufferedReader:close	()V
    //   105: aload 4
    //   107: athrow
    //   108: aload 9
    //   110: ldc 214
    //   112: invokevirtual 191	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   115: istore 11
    //   117: aconst_null
    //   118: astore 8
    //   120: iload 11
    //   122: iflt -46 -> 76
    //   125: aload 9
    //   127: iconst_0
    //   128: iload 11
    //   130: invokevirtual 217	java/lang/String:substring	(II)Ljava/lang/String;
    //   133: astore 8
    //   135: goto -59 -> 76
    //   138: astore 10
    //   140: goto -72 -> 68
    //   143: astore 5
    //   145: goto -40 -> 105
    //   148: astore 12
    //   150: aconst_null
    //   151: astore 8
    //   153: goto -77 -> 76
    //
    // Exception table:
    //   from	to	target	type
    //   58	64	79	java/io/IOException
    //   81	85	91	java/io/IOException
    //   58	64	99	finally
    //   64	68	138	java/io/IOException
    //   101	105	143	java/io/IOException
    //   33	42	148	java/io/FileNotFoundException
  }

  String a(String paramString1, String paramString2, ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramString1 != null)
      localStringBuffer.append(paramString1);
    if (paramString2 != null)
      localStringBuffer.append(paramString2);
    List localList = Arrays.asList(a.a);
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      String str = (String)paramArrayList.get(i);
      if (!a(localList, str))
        localStringBuffer.append(str);
    }
    return c.a(localStringBuffer.toString().getBytes());
  }

  String b()
  {
    try
    {
      String str2 = interface9();
      str1 = str2;
      return str1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        String str1 = null;
    }
  }

  public native String interface9();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.bugreport.javacrash.CrashReportDataFactory
 * JD-Core Version:    0.6.1
 */