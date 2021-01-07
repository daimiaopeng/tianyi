package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil
{
  private static final String CACHE_FILE_PREFIX = ".font";
  private static final String TAG = "TypefaceCompatUtil";

  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  @Nullable
  @RequiresApi(19)
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt)
  {
    File localFile = getTempFile(paramContext);
    if (localFile == null)
      return null;
    try
    {
      boolean bool = copyToFile(localFile, paramResources, paramInt);
      if (!bool)
        return null;
      ByteBuffer localByteBuffer = mmap(localFile);
      return localByteBuffer;
    }
    finally
    {
      localFile.delete();
    }
  }

  // ERROR //
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: iload_2
    //   2: invokevirtual 57	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   5: astore 4
    //   7: aload_0
    //   8: aload 4
    //   10: invokestatic 60	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Ljava/io/InputStream;)Z
    //   13: istore 5
    //   15: aload 4
    //   17: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   20: iload 5
    //   22: ireturn
    //   23: astore_3
    //   24: goto +7 -> 31
    //   27: astore_3
    //   28: aconst_null
    //   29: astore 4
    //   31: aload 4
    //   33: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   36: aload_3
    //   37: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	15	23	finally
    //   0	7	27	finally
  }

  // ERROR //
  public static boolean copyToFile(File paramFile, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: invokestatic 68	android/os/StrictMode:allowThreadDiskWrites	()Landroid/os/StrictMode$ThreadPolicy;
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: new 70	java/io/FileOutputStream
    //   9: dup
    //   10: aload_0
    //   11: iconst_0
    //   12: invokespecial 73	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   15: astore 4
    //   17: sipush 1024
    //   20: newarray byte
    //   22: astore 11
    //   24: aload_1
    //   25: aload 11
    //   27: invokevirtual 79	java/io/InputStream:read	([B)I
    //   30: istore 12
    //   32: iload 12
    //   34: iconst_m1
    //   35: if_icmpeq +16 -> 51
    //   38: aload 4
    //   40: aload 11
    //   42: iconst_0
    //   43: iload 12
    //   45: invokevirtual 83	java/io/FileOutputStream:write	([BII)V
    //   48: goto -24 -> 24
    //   51: aload 4
    //   53: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   56: aload_2
    //   57: invokestatic 87	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   60: iconst_1
    //   61: ireturn
    //   62: astore 10
    //   64: aload 4
    //   66: astore_3
    //   67: goto +67 -> 134
    //   70: astore 5
    //   72: aload 4
    //   74: astore_3
    //   75: goto +10 -> 85
    //   78: astore 10
    //   80: goto +54 -> 134
    //   83: astore 5
    //   85: new 89	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   92: astore 6
    //   94: aload 6
    //   96: ldc 92
    //   98: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload 6
    //   104: aload 5
    //   106: invokevirtual 100	java/io/IOException:getMessage	()Ljava/lang/String;
    //   109: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: ldc 15
    //   115: aload 6
    //   117: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokestatic 109	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: aload_3
    //   125: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   128: aload_2
    //   129: invokestatic 87	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   132: iconst_0
    //   133: ireturn
    //   134: aload_3
    //   135: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   138: aload_2
    //   139: invokestatic 87	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   142: aload 10
    //   144: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   17	48	62	finally
    //   17	48	70	java/io/IOException
    //   6	17	78	finally
    //   85	124	78	finally
    //   6	17	83	java/io/IOException
  }

  @Nullable
  public static File getTempFile(Context paramContext)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(".font");
    localStringBuilder1.append(Process.myPid());
    localStringBuilder1.append("-");
    localStringBuilder1.append(Process.myTid());
    localStringBuilder1.append("-");
    String str = localStringBuilder1.toString();
    int i = 0;
    while (i < 100)
    {
      File localFile1 = paramContext.getCacheDir();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(str);
      localStringBuilder2.append(i);
      File localFile2 = new File(localFile1, localStringBuilder2.toString());
      try
      {
        boolean bool = localFile2.createNewFile();
        if (bool)
          return localFile2;
      }
      catch (IOException localIOException)
      {
        localIOException++;
        tmpTernaryOp = localIOException;
      }
    }
    return null;
  }

  // ERROR //
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer mmap(Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 142	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_3
    //   5: aload_3
    //   6: aload_2
    //   7: ldc 144
    //   9: aload_1
    //   10: invokevirtual 150	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore 4
    //   15: aload 4
    //   17: ifnonnull +15 -> 32
    //   20: aload 4
    //   22: ifnull +8 -> 30
    //   25: aload 4
    //   27: invokevirtual 153	android/os/ParcelFileDescriptor:close	()V
    //   30: aconst_null
    //   31: areturn
    //   32: new 155	java/io/FileInputStream
    //   35: dup
    //   36: aload 4
    //   38: invokevirtual 159	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   41: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   44: astore 5
    //   46: aload 5
    //   48: invokevirtual 166	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   51: astore 16
    //   53: aload 16
    //   55: invokevirtual 172	java/nio/channels/FileChannel:size	()J
    //   58: lstore 17
    //   60: aload 16
    //   62: getstatic 178	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   65: lconst_0
    //   66: lload 17
    //   68: invokevirtual 182	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   71: astore 19
    //   73: aload 5
    //   75: ifnull +8 -> 83
    //   78: aload 5
    //   80: invokevirtual 183	java/io/FileInputStream:close	()V
    //   83: aload 4
    //   85: ifnull +8 -> 93
    //   88: aload 4
    //   90: invokevirtual 153	android/os/ParcelFileDescriptor:close	()V
    //   93: aload 19
    //   95: areturn
    //   96: astore 9
    //   98: aconst_null
    //   99: astore 8
    //   101: goto +18 -> 119
    //   104: astore 6
    //   106: aload 6
    //   108: athrow
    //   109: astore 7
    //   111: aload 6
    //   113: astore 8
    //   115: aload 7
    //   117: astore 9
    //   119: aload 5
    //   121: ifnull +33 -> 154
    //   124: aload 8
    //   126: ifnull +23 -> 149
    //   129: aload 5
    //   131: invokevirtual 183	java/io/FileInputStream:close	()V
    //   134: goto +20 -> 154
    //   137: astore 15
    //   139: aload 8
    //   141: aload 15
    //   143: invokevirtual 187	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   146: goto +8 -> 154
    //   149: aload 5
    //   151: invokevirtual 183	java/io/FileInputStream:close	()V
    //   154: aload 9
    //   156: athrow
    //   157: astore 13
    //   159: aconst_null
    //   160: astore 12
    //   162: goto +18 -> 180
    //   165: astore 10
    //   167: aload 10
    //   169: athrow
    //   170: astore 11
    //   172: aload 10
    //   174: astore 12
    //   176: aload 11
    //   178: astore 13
    //   180: aload 4
    //   182: ifnull +33 -> 215
    //   185: aload 12
    //   187: ifnull +23 -> 210
    //   190: aload 4
    //   192: invokevirtual 153	android/os/ParcelFileDescriptor:close	()V
    //   195: goto +20 -> 215
    //   198: astore 14
    //   200: aload 12
    //   202: aload 14
    //   204: invokevirtual 187	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   207: goto +8 -> 215
    //   210: aload 4
    //   212: invokevirtual 153	android/os/ParcelFileDescriptor:close	()V
    //   215: aload 13
    //   217: athrow
    //   218: aconst_null
    //   219: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   46	73	96	finally
    //   46	73	104	java/lang/Throwable
    //   106	109	109	finally
    //   129	134	137	java/lang/Throwable
    //   32	46	157	finally
    //   78	83	157	finally
    //   129	134	157	finally
    //   139	157	157	finally
    //   32	46	165	java/lang/Throwable
    //   78	83	165	java/lang/Throwable
    //   139	157	165	java/lang/Throwable
    //   167	170	170	finally
    //   190	195	198	java/lang/Throwable
    //   5	30	218	java/io/IOException
    //   88	93	218	java/io/IOException
    //   190	195	218	java/io/IOException
    //   200	218	218	java/io/IOException
  }

  // ERROR //
  @Nullable
  @RequiresApi(19)
  private static ByteBuffer mmap(File paramFile)
  {
    // Byte code:
    //   0: new 155	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 190	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 166	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   13: astore 7
    //   15: aload 7
    //   17: invokevirtual 172	java/nio/channels/FileChannel:size	()J
    //   20: lstore 8
    //   22: aload 7
    //   24: getstatic 178	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   27: lconst_0
    //   28: lload 8
    //   30: invokevirtual 182	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   33: astore 10
    //   35: aload_1
    //   36: ifnull +7 -> 43
    //   39: aload_1
    //   40: invokevirtual 183	java/io/FileInputStream:close	()V
    //   43: aload 10
    //   45: areturn
    //   46: astore 5
    //   48: aconst_null
    //   49: astore 4
    //   51: goto +13 -> 64
    //   54: astore_2
    //   55: aload_2
    //   56: athrow
    //   57: astore_3
    //   58: aload_2
    //   59: astore 4
    //   61: aload_3
    //   62: astore 5
    //   64: aload_1
    //   65: ifnull +31 -> 96
    //   68: aload 4
    //   70: ifnull +22 -> 92
    //   73: aload_1
    //   74: invokevirtual 183	java/io/FileInputStream:close	()V
    //   77: goto +19 -> 96
    //   80: astore 6
    //   82: aload 4
    //   84: aload 6
    //   86: invokevirtual 187	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   89: goto +7 -> 96
    //   92: aload_1
    //   93: invokevirtual 183	java/io/FileInputStream:close	()V
    //   96: aload 5
    //   98: athrow
    //   99: aconst_null
    //   100: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   9	35	46	finally
    //   9	35	54	java/lang/Throwable
    //   55	57	57	finally
    //   73	77	80	java/lang/Throwable
    //   0	9	99	java/io/IOException
    //   39	43	99	java/io/IOException
    //   73	77	99	java/io/IOException
    //   82	99	99	java/io/IOException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.TypefaceCompatUtil
 * JD-Core Version:    0.6.1
 */