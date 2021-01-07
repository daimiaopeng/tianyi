package org.apache.commons.io;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class FileSystemUtils
{
  private static final int INIT_PROBLEM = -1;
  private static final FileSystemUtils INSTANCE = new FileSystemUtils();
  private static final int OS = 0;
  private static final int OTHER = 0;
  private static final int POSIX_UNIX = 3;
  private static final int UNIX = 2;
  private static final int WINDOWS = 1;

  static
  {
    int i = -1;
    label197: 
    try
    {
      String str1 = System.getProperty("os.name");
      if (str1 == null)
        throw new IOException("os.name not found");
      String str2 = str1.toLowerCase();
      if (str2.indexOf("windows") != i)
      {
        i = 1;
      }
      else if ((str2.indexOf("linux") == i) && (str2.indexOf("sun os") == i) && (str2.indexOf("sunos") == i) && (str2.indexOf("solaris") == i) && (str2.indexOf("mpe/ix") == i) && (str2.indexOf("freebsd") == i) && (str2.indexOf("irix") == i) && (str2.indexOf("digital unix") == i) && (str2.indexOf("unix") == i) && (str2.indexOf("mac os x") == i))
      {
        if (str2.indexOf("hp-ux") == i)
        {
          int j = str2.indexOf("aix");
          if (j == i)
          {
            i = 0;
            break label197;
          }
        }
        i = 3;
      }
      else
      {
        i = 2;
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static long freeSpace(String paramString)
  {
    return INSTANCE.freeSpaceOS(paramString, OS, false);
  }

  public static long freeSpaceKb(String paramString)
  {
    return INSTANCE.freeSpaceOS(paramString, OS, true);
  }

  long freeSpaceOS(String paramString, int paramInt, boolean paramBoolean)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Path must not be empty");
    switch (paramInt)
    {
    default:
      throw new IllegalStateException("Exception caught when determining operating system");
    case 3:
      return freeSpaceUnix(paramString, paramBoolean, true);
    case 2:
      return freeSpaceUnix(paramString, paramBoolean, false);
    case 1:
      long l;
      if (paramBoolean)
        l = freeSpaceWindows(paramString) / 1024L;
      else
        l = freeSpaceWindows(paramString);
      return l;
    case 0:
    }
    throw new IllegalStateException("Unsupported operating system");
  }

  long freeSpaceUnix(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString.length() == 0)
      throw new IllegalArgumentException("Path must not be empty");
    String str1 = FilenameUtils.normalize(paramString);
    String str2 = "-";
    if (paramBoolean1)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(str2);
      localStringBuffer1.append("k");
      str2 = localStringBuffer1.toString();
    }
    if (paramBoolean2)
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(str2);
      localStringBuffer2.append("P");
      str2 = localStringBuffer2.toString();
    }
    String[] arrayOfString;
    if (str2.length() > 1)
      arrayOfString = new String[] { "df", str2, str1 };
    else
      arrayOfString = new String[] { "df", str1 };
    List localList = performCommand(arrayOfString, 3);
    if (localList.size() < 2)
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Command line 'df' did not return info as expected for path '");
      localStringBuffer3.append(str1);
      localStringBuffer3.append("'- response was ");
      localStringBuffer3.append(localList);
      throw new IOException(localStringBuffer3.toString());
    }
    StringTokenizer localStringTokenizer = new StringTokenizer((String)localList.get(1), " ");
    if (localStringTokenizer.countTokens() < 4)
    {
      if ((localStringTokenizer.countTokens() == 1) && (localList.size() >= 3))
      {
        localStringTokenizer = new StringTokenizer((String)localList.get(2), " ");
      }
      else
      {
        StringBuffer localStringBuffer4 = new StringBuffer();
        localStringBuffer4.append("Command line 'df' did not return data as expected for path '");
        localStringBuffer4.append(str1);
        localStringBuffer4.append("'- check path is valid");
        throw new IOException(localStringBuffer4.toString());
      }
    }
    else
      localStringTokenizer.nextToken();
    localStringTokenizer.nextToken();
    localStringTokenizer.nextToken();
    return parseBytes(localStringTokenizer.nextToken(), str1);
  }

  long freeSpaceWindows(String paramString)
  {
    String str1 = FilenameUtils.normalize(paramString);
    if ((str1.length() > 2) && (str1.charAt(1) == ':'))
      str1 = str1.substring(0, 2);
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "cmd.exe";
    arrayOfString[1] = "/C";
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("dir /-c ");
    localStringBuffer1.append(str1);
    arrayOfString[2] = localStringBuffer1.toString();
    List localList = performCommand(arrayOfString, 2147483647);
    for (int i = localList.size() - 1; i >= 0; i--)
    {
      String str2 = (String)localList.get(i);
      if (str2.length() > 0)
        return parseDir(str2, str1);
    }
    StringBuffer localStringBuffer2 = new StringBuffer();
    localStringBuffer2.append("Command line 'dir /-c' did not return any info for path '");
    localStringBuffer2.append(str1);
    localStringBuffer2.append("'");
    throw new IOException(localStringBuffer2.toString());
  }

  Process openProcess(String[] paramArrayOfString)
  {
    return Runtime.getRuntime().exec(paramArrayOfString);
  }

  // ERROR //
  long parseBytes(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 217	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   4: lstore 7
    //   6: lload 7
    //   8: lconst_0
    //   9: lcmp
    //   10: ifge +48 -> 58
    //   13: new 122	java/lang/StringBuffer
    //   16: dup
    //   17: invokespecial 123	java/lang/StringBuffer:<init>	()V
    //   20: astore 9
    //   22: aload 9
    //   24: ldc 219
    //   26: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   29: pop
    //   30: aload 9
    //   32: aload_2
    //   33: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   36: pop
    //   37: aload 9
    //   39: ldc 170
    //   41: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   44: pop
    //   45: new 37	java/io/IOException
    //   48: dup
    //   49: aload 9
    //   51: invokevirtual 132	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   54: invokespecial 42	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   57: athrow
    //   58: lload 7
    //   60: lreturn
    //   61: new 122	java/lang/StringBuffer
    //   64: dup
    //   65: invokespecial 123	java/lang/StringBuffer:<init>	()V
    //   68: astore_3
    //   69: aload_3
    //   70: ldc 221
    //   72: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   75: pop
    //   76: aload_3
    //   77: aload_2
    //   78: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   81: pop
    //   82: aload_3
    //   83: ldc 170
    //   85: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   88: pop
    //   89: new 37	java/io/IOException
    //   92: dup
    //   93: aload_3
    //   94: invokevirtual 132	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   97: invokespecial 42	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   100: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	58	61	java/lang/NumberFormatException
  }

  long parseDir(String paramString1, String paramString2)
  {
    int j;
    for (int i = -1 + paramString1.length(); ; i--)
    {
      j = 0;
      if (i < 0)
        break;
      if (Character.isDigit(paramString1.charAt(i)))
      {
        k = i + 1;
        break label42;
      }
    }
    int k = 0;
    label42: 
    while (i >= 0)
    {
      char c = paramString1.charAt(i);
      if ((!Character.isDigit(c)) && (c != ',') && (c != '.'))
      {
        m = i + 1;
        break label92;
      }
      i--;
    }
    int m = 0;
    label92: if (i < 0)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Command line 'dir /-c' did not return valid info for path '");
      localStringBuffer1.append(paramString2);
      localStringBuffer1.append("'");
      throw new IOException(localStringBuffer1.toString());
    }
    StringBuffer localStringBuffer2 = new StringBuffer(paramString1.substring(m, k));
    while (j < localStringBuffer2.length())
    {
      int n;
      if ((localStringBuffer2.charAt(j) != ',') && (localStringBuffer2.charAt(j) != '.'))
      {
        n = j;
      }
      else
      {
        n = j - 1;
        localStringBuffer2.deleteCharAt(j);
      }
      j = n + 1;
    }
    return parseBytes(localStringBuffer2.toString(), paramString2);
  }

  // ERROR //
  List performCommand(String[] paramArrayOfString, int paramInt)
  {
    // Byte code:
    //   0: new 240	java/util/ArrayList
    //   3: dup
    //   4: bipush 20
    //   6: invokespecial 243	java/util/ArrayList:<init>	(I)V
    //   9: astore_3
    //   10: aconst_null
    //   11: astore 4
    //   13: aload_0
    //   14: aload_1
    //   15: invokevirtual 245	org/apache/commons/io/FileSystemUtils:openProcess	([Ljava/lang/String;)Ljava/lang/Process;
    //   18: astore 12
    //   20: aload 12
    //   22: invokevirtual 251	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   25: astore 6
    //   27: aload 12
    //   29: invokevirtual 255	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   32: astore 7
    //   34: aload 12
    //   36: invokevirtual 258	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   39: astore 8
    //   41: new 260	java/io/BufferedReader
    //   44: dup
    //   45: new 262	java/io/InputStreamReader
    //   48: dup
    //   49: aload 6
    //   51: invokespecial 265	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: invokespecial 268	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   57: astore 9
    //   59: aload 9
    //   61: invokevirtual 271	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 17
    //   66: aload 17
    //   68: ifnull +38 -> 106
    //   71: aload_3
    //   72: invokeinterface 145 1 0
    //   77: iload_2
    //   78: if_icmpge +28 -> 106
    //   81: aload_3
    //   82: aload 17
    //   84: invokevirtual 48	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   87: invokevirtual 274	java/lang/String:trim	()Ljava/lang/String;
    //   90: invokeinterface 278 2 0
    //   95: pop
    //   96: aload 9
    //   98: invokevirtual 271	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   101: astore 17
    //   103: goto -37 -> 66
    //   106: aload 12
    //   108: invokevirtual 281	java/lang/Process:waitFor	()I
    //   111: pop
    //   112: aload 12
    //   114: invokevirtual 284	java/lang/Process:exitValue	()I
    //   117: ifeq +64 -> 181
    //   120: new 122	java/lang/StringBuffer
    //   123: dup
    //   124: invokespecial 123	java/lang/StringBuffer:<init>	()V
    //   127: astore 19
    //   129: aload 19
    //   131: ldc_w 286
    //   134: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   137: pop
    //   138: aload 19
    //   140: aload 12
    //   142: invokevirtual 284	java/lang/Process:exitValue	()I
    //   145: invokevirtual 288	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   148: pop
    //   149: aload 19
    //   151: ldc_w 290
    //   154: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   157: pop
    //   158: aload 19
    //   160: aload_1
    //   161: invokestatic 296	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   164: invokevirtual 152	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   167: pop
    //   168: new 37	java/io/IOException
    //   171: dup
    //   172: aload 19
    //   174: invokevirtual 132	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   177: invokespecial 42	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   180: athrow
    //   181: aload_3
    //   182: invokeinterface 145 1 0
    //   187: ifne +44 -> 231
    //   190: new 122	java/lang/StringBuffer
    //   193: dup
    //   194: invokespecial 123	java/lang/StringBuffer:<init>	()V
    //   197: astore 24
    //   199: aload 24
    //   201: ldc_w 298
    //   204: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   207: pop
    //   208: aload 24
    //   210: aload_1
    //   211: invokestatic 296	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   214: invokevirtual 152	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   217: pop
    //   218: new 37	java/io/IOException
    //   221: dup
    //   222: aload 24
    //   224: invokevirtual 132	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   227: invokespecial 42	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   230: athrow
    //   231: aload 6
    //   233: invokestatic 303	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   236: aload 7
    //   238: invokestatic 306	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   241: aload 8
    //   243: invokestatic 303	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   246: aload 9
    //   248: invokestatic 308	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   251: aload 12
    //   253: ifnull +8 -> 261
    //   256: aload 12
    //   258: invokevirtual 311	java/lang/Process:destroy	()V
    //   261: aload_3
    //   262: areturn
    //   263: astore 11
    //   265: goto +183 -> 448
    //   268: astore 5
    //   270: goto +70 -> 340
    //   273: astore 11
    //   275: aconst_null
    //   276: astore 9
    //   278: goto +170 -> 448
    //   281: astore 5
    //   283: aconst_null
    //   284: astore 9
    //   286: goto +54 -> 340
    //   289: astore 11
    //   291: goto +67 -> 358
    //   294: astore 5
    //   296: aconst_null
    //   297: astore 8
    //   299: goto +38 -> 337
    //   302: astore 11
    //   304: aconst_null
    //   305: astore 7
    //   307: goto +51 -> 358
    //   310: astore 5
    //   312: aconst_null
    //   313: astore 7
    //   315: goto +19 -> 334
    //   318: astore 11
    //   320: aconst_null
    //   321: astore 6
    //   323: goto +32 -> 355
    //   326: astore 5
    //   328: aconst_null
    //   329: astore 6
    //   331: aconst_null
    //   332: astore 7
    //   334: aconst_null
    //   335: astore 8
    //   337: aconst_null
    //   338: astore 9
    //   340: aload 12
    //   342: astore 4
    //   344: goto +37 -> 381
    //   347: astore 11
    //   349: aconst_null
    //   350: astore 12
    //   352: aconst_null
    //   353: astore 6
    //   355: aconst_null
    //   356: astore 7
    //   358: aconst_null
    //   359: astore 8
    //   361: aconst_null
    //   362: astore 9
    //   364: goto +84 -> 448
    //   367: astore 5
    //   369: aconst_null
    //   370: astore 6
    //   372: aconst_null
    //   373: astore 7
    //   375: aconst_null
    //   376: astore 8
    //   378: aconst_null
    //   379: astore 9
    //   381: new 122	java/lang/StringBuffer
    //   384: dup
    //   385: invokespecial 123	java/lang/StringBuffer:<init>	()V
    //   388: astore 10
    //   390: aload 10
    //   392: ldc_w 313
    //   395: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   398: pop
    //   399: aload 10
    //   401: aload 5
    //   403: invokevirtual 316	java/lang/InterruptedException:getMessage	()Ljava/lang/String;
    //   406: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   409: pop
    //   410: aload 10
    //   412: ldc_w 290
    //   415: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   418: pop
    //   419: aload 10
    //   421: aload_1
    //   422: invokestatic 296	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   425: invokevirtual 152	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   428: pop
    //   429: new 37	java/io/IOException
    //   432: dup
    //   433: aload 10
    //   435: invokevirtual 132	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   438: invokespecial 42	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   441: athrow
    //   442: astore 11
    //   444: aload 4
    //   446: astore 12
    //   448: aload 6
    //   450: invokestatic 303	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   453: aload 7
    //   455: invokestatic 306	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   458: aload 8
    //   460: invokestatic 303	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   463: aload 9
    //   465: invokestatic 308	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   468: aload 12
    //   470: ifnull +8 -> 478
    //   473: aload 12
    //   475: invokevirtual 311	java/lang/Process:destroy	()V
    //   478: aload 11
    //   480: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   59	231	263	finally
    //   59	231	268	java/lang/InterruptedException
    //   41	59	273	finally
    //   41	59	281	java/lang/InterruptedException
    //   34	41	289	finally
    //   34	41	294	java/lang/InterruptedException
    //   27	34	302	finally
    //   27	34	310	java/lang/InterruptedException
    //   20	27	318	finally
    //   20	27	326	java/lang/InterruptedException
    //   13	20	347	finally
    //   13	20	367	java/lang/InterruptedException
    //   381	442	442	finally
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileSystemUtils
 * JD-Core Version:    0.6.1
 */