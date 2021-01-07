package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile
{
  private final File mBackupName;
  private final File mBaseName;

  public AtomicFile(@NonNull File paramFile)
  {
    this.mBaseName = paramFile;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramFile.getPath());
    localStringBuilder.append(".bak");
    this.mBackupName = new File(localStringBuilder.toString());
  }

  // ERROR //
  private static boolean sync(@NonNull FileOutputStream paramFileOutputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 48	java/io/FileOutputStream:getFD	()Ljava/io/FileDescriptor;
    //   4: invokevirtual 52	java/io/FileDescriptor:sync	()V
    //   7: iconst_1
    //   8: ireturn
    //   9: iconst_0
    //   10: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	9	java/io/IOException
  }

  public void delete()
  {
    this.mBaseName.delete();
    this.mBackupName.delete();
  }

  public void failWrite(@Nullable FileOutputStream paramFileOutputStream)
  {
    if (paramFileOutputStream != null)
    {
      sync(paramFileOutputStream);
      try
      {
        paramFileOutputStream.close();
        this.mBaseName.delete();
        this.mBackupName.renameTo(this.mBaseName);
      }
      catch (IOException localIOException)
      {
        Log.w("AtomicFile", "failWrite: Got exception:", localIOException);
      }
    }
  }

  public void finishWrite(@Nullable FileOutputStream paramFileOutputStream)
  {
    if (paramFileOutputStream != null)
    {
      sync(paramFileOutputStream);
      try
      {
        paramFileOutputStream.close();
        this.mBackupName.delete();
      }
      catch (IOException localIOException)
      {
        Log.w("AtomicFile", "finishWrite: Got exception:", localIOException);
      }
    }
  }

  @NonNull
  public File getBaseFile()
  {
    return this.mBaseName;
  }

  @NonNull
  public FileInputStream openRead()
  {
    if (this.mBackupName.exists())
    {
      this.mBaseName.delete();
      this.mBackupName.renameTo(this.mBaseName);
    }
    return new FileInputStream(this.mBaseName);
  }

  @NonNull
  public byte[] readFully()
  {
    FileInputStream localFileInputStream = openRead();
    try
    {
      Object localObject2 = new byte[localFileInputStream.available()];
      int i = 0;
      while (true)
      {
        int j = localFileInputStream.read((byte[])localObject2, i, localObject2.length - i);
        if (j <= 0)
          return localObject2;
        i += j;
        int k = localFileInputStream.available();
        if (k > localObject2.length - i)
        {
          byte[] arrayOfByte = new byte[k + i];
          System.arraycopy(localObject2, 0, arrayOfByte, 0, i);
          localObject2 = arrayOfByte;
        }
      }
    }
    finally
    {
      localFileInputStream.close();
    }
  }

  // ERROR //
  @NonNull
  public FileOutputStream startWrite()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   4: invokevirtual 88	java/io/File:exists	()Z
    //   7: ifeq +94 -> 101
    //   10: aload_0
    //   11: getfield 38	android/support/v4/util/AtomicFile:mBackupName	Ljava/io/File;
    //   14: invokevirtual 88	java/io/File:exists	()Z
    //   17: ifne +76 -> 93
    //   20: aload_0
    //   21: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   24: aload_0
    //   25: getfield 38	android/support/v4/util/AtomicFile:mBackupName	Ljava/io/File;
    //   28: invokevirtual 68	java/io/File:renameTo	(Ljava/io/File;)Z
    //   31: ifne +70 -> 101
    //   34: new 17	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 18	java/lang/StringBuilder:<init>	()V
    //   41: astore 9
    //   43: aload 9
    //   45: ldc 117
    //   47: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: aload 9
    //   53: aload_0
    //   54: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   57: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload 9
    //   63: ldc 122
    //   65: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload 9
    //   71: aload_0
    //   72: getfield 38	android/support/v4/util/AtomicFile:mBackupName	Ljava/io/File;
    //   75: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: ldc 70
    //   81: aload 9
    //   83: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 125	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: goto +11 -> 101
    //   93: aload_0
    //   94: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   97: invokevirtual 56	java/io/File:delete	()Z
    //   100: pop
    //   101: new 44	java/io/FileOutputStream
    //   104: dup
    //   105: aload_0
    //   106: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   109: invokespecial 126	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   112: astore_1
    //   113: goto +64 -> 177
    //   116: aload_0
    //   117: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   120: invokevirtual 129	java/io/File:getParentFile	()Ljava/io/File;
    //   123: invokevirtual 132	java/io/File:mkdirs	()Z
    //   126: ifne +39 -> 165
    //   129: new 17	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 18	java/lang/StringBuilder:<init>	()V
    //   136: astore_2
    //   137: aload_2
    //   138: ldc 134
    //   140: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload_2
    //   145: aload_0
    //   146: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   149: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: new 42	java/io/IOException
    //   156: dup
    //   157: aload_2
    //   158: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokespecial 135	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   164: athrow
    //   165: new 44	java/io/FileOutputStream
    //   168: dup
    //   169: aload_0
    //   170: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   173: invokespecial 126	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   176: astore_1
    //   177: aload_1
    //   178: areturn
    //   179: new 17	java/lang/StringBuilder
    //   182: dup
    //   183: invokespecial 18	java/lang/StringBuilder:<init>	()V
    //   186: astore 5
    //   188: aload 5
    //   190: ldc 137
    //   192: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload 5
    //   198: aload_0
    //   199: getfield 15	android/support/v4/util/AtomicFile:mBaseName	Ljava/io/File;
    //   202: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: new 42	java/io/IOException
    //   209: dup
    //   210: aload 5
    //   212: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: invokespecial 135	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   218: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   101	113	116	java/io/FileNotFoundException
    //   165	177	179	java/io/FileNotFoundException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.AtomicFile
 * JD-Core Version:    0.6.1
 */