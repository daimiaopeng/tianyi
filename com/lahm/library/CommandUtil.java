package com.lahm.library;

import java.io.BufferedInputStream;

public class CommandUtil
{
  public static final CommandUtil getSingleInstance()
  {
    return SingletonHolder.INSTANCE;
  }

  private static String getStrFromBufferInputSteam(BufferedInputStream paramBufferedInputStream)
  {
    if (paramBufferedInputStream == null)
      return "";
    byte[] arrayOfByte = new byte[512];
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      int i;
      do
      {
        i = paramBufferedInputStream.read(arrayOfByte);
        if (i > 0)
          localStringBuilder.append(new String(arrayOfByte, 0, i));
      }
      while (i >= 512);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localStringBuilder.toString();
  }

  // ERROR //
  public String exec(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: invokestatic 58	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   5: ldc 60
    //   7: invokevirtual 63	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   10: astore_3
    //   11: new 65	java/io/BufferedOutputStream
    //   14: dup
    //   15: aload_3
    //   16: invokevirtual 71	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   19: invokespecial 74	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   22: astore 4
    //   24: new 28	java/io/BufferedInputStream
    //   27: dup
    //   28: aload_3
    //   29: invokevirtual 78	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   32: invokespecial 81	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   35: astore 5
    //   37: aload 4
    //   39: aload_1
    //   40: invokevirtual 85	java/lang/String:getBytes	()[B
    //   43: invokevirtual 89	java/io/BufferedOutputStream:write	([B)V
    //   46: aload 4
    //   48: bipush 10
    //   50: invokevirtual 92	java/io/BufferedOutputStream:write	(I)V
    //   53: aload 4
    //   55: invokevirtual 95	java/io/BufferedOutputStream:flush	()V
    //   58: aload 4
    //   60: invokevirtual 98	java/io/BufferedOutputStream:close	()V
    //   63: aload_3
    //   64: invokevirtual 102	java/lang/Process:waitFor	()I
    //   67: pop
    //   68: aload 5
    //   70: invokestatic 104	com/lahm/library/CommandUtil:getStrFromBufferInputSteam	(Ljava/io/BufferedInputStream;)Ljava/lang/String;
    //   73: astore 13
    //   75: aload 4
    //   77: ifnull +18 -> 95
    //   80: aload 4
    //   82: invokevirtual 98	java/io/BufferedOutputStream:close	()V
    //   85: goto +10 -> 95
    //   88: astore 15
    //   90: aload 15
    //   92: invokevirtual 105	java/io/IOException:printStackTrace	()V
    //   95: aload 5
    //   97: ifnull +18 -> 115
    //   100: aload 5
    //   102: invokevirtual 106	java/io/BufferedInputStream:close	()V
    //   105: goto +10 -> 115
    //   108: astore 14
    //   110: aload 14
    //   112: invokevirtual 105	java/io/IOException:printStackTrace	()V
    //   115: aload_3
    //   116: ifnull +7 -> 123
    //   119: aload_3
    //   120: invokevirtual 109	java/lang/Process:destroy	()V
    //   123: aload 13
    //   125: areturn
    //   126: astore 8
    //   128: aload 5
    //   130: astore_2
    //   131: goto +36 -> 167
    //   134: astore 8
    //   136: aconst_null
    //   137: astore_2
    //   138: goto +29 -> 167
    //   141: aconst_null
    //   142: astore 5
    //   144: goto +83 -> 227
    //   147: astore 8
    //   149: aconst_null
    //   150: astore_2
    //   151: aconst_null
    //   152: astore 4
    //   154: goto +13 -> 167
    //   157: goto +61 -> 218
    //   160: astore 8
    //   162: aconst_null
    //   163: astore_3
    //   164: aconst_null
    //   165: astore 4
    //   167: aload 4
    //   169: ifnull +18 -> 187
    //   172: aload 4
    //   174: invokevirtual 98	java/io/BufferedOutputStream:close	()V
    //   177: goto +10 -> 187
    //   180: astore 10
    //   182: aload 10
    //   184: invokevirtual 105	java/io/IOException:printStackTrace	()V
    //   187: aload_2
    //   188: ifnull +17 -> 205
    //   191: aload_2
    //   192: invokevirtual 106	java/io/BufferedInputStream:close	()V
    //   195: goto +10 -> 205
    //   198: astore 9
    //   200: aload 9
    //   202: invokevirtual 105	java/io/IOException:printStackTrace	()V
    //   205: aload_3
    //   206: ifnull +7 -> 213
    //   209: aload_3
    //   210: invokevirtual 109	java/lang/Process:destroy	()V
    //   213: aload 8
    //   215: athrow
    //   216: aconst_null
    //   217: astore_3
    //   218: aconst_null
    //   219: astore 4
    //   221: aconst_null
    //   222: astore 5
    //   224: goto +4 -> 228
    //   227: pop
    //   228: aload 4
    //   230: ifnull +18 -> 248
    //   233: aload 4
    //   235: invokevirtual 98	java/io/BufferedOutputStream:close	()V
    //   238: goto +10 -> 248
    //   241: astore 7
    //   243: aload 7
    //   245: invokevirtual 105	java/io/IOException:printStackTrace	()V
    //   248: aload 5
    //   250: ifnull +18 -> 268
    //   253: aload 5
    //   255: invokevirtual 106	java/io/BufferedInputStream:close	()V
    //   258: goto +10 -> 268
    //   261: astore 6
    //   263: aload 6
    //   265: invokevirtual 105	java/io/IOException:printStackTrace	()V
    //   268: aload_3
    //   269: ifnull +7 -> 276
    //   272: aload_3
    //   273: invokevirtual 109	java/lang/Process:destroy	()V
    //   276: aconst_null
    //   277: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   80	85	88	java/io/IOException
    //   100	105	108	java/io/IOException
    //   37	75	126	finally
    //   24	37	134	finally
    //   24	37	141	java/lang/Exception
    //   11	24	147	finally
    //   11	24	157	java/lang/Exception
    //   2	11	160	finally
    //   172	177	180	java/io/IOException
    //   191	195	198	java/io/IOException
    //   2	11	216	java/lang/Exception
    //   37	75	227	java/lang/Exception
    //   233	238	241	java/io/IOException
    //   253	258	261	java/io/IOException
  }

  // ERROR //
  public String getProperty(String paramString)
  {
    // Byte code:
    //   0: ldc 112
    //   2: invokestatic 118	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: ldc 120
    //   7: iconst_1
    //   8: anewarray 114	java/lang/Class
    //   11: dup
    //   12: iconst_0
    //   13: ldc 34
    //   15: aastore
    //   16: invokevirtual 124	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   19: aconst_null
    //   20: iconst_1
    //   21: anewarray 4	java/lang/Object
    //   24: dup
    //   25: iconst_0
    //   26: aload_1
    //   27: aastore
    //   28: invokevirtual 130	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   31: astore_2
    //   32: aload_2
    //   33: ifnull +11 -> 44
    //   36: aload_2
    //   37: checkcast 34	java/lang/String
    //   40: astore_3
    //   41: goto +5 -> 46
    //   44: aconst_null
    //   45: astore_3
    //   46: aload_3
    //   47: areturn
    //   48: aconst_null
    //   49: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	41	48	java/lang/Exception
    //   0	41	48	finally
  }

  private static class SingletonHolder
  {
    private static final CommandUtil INSTANCE = new CommandUtil(null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.lahm.library.CommandUtil
 * JD-Core Version:    0.6.1
 */