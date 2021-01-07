package org.apache.commons.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class LockableFileWriter extends Writer
{
  private static final String LCK = ".lck";
  static Class class$org$apache$commons$io$output$LockableFileWriter;
  private final File lockFile;
  private final Writer out;

  public LockableFileWriter(File paramFile)
  {
    this(paramFile, false, null);
  }

  public LockableFileWriter(File paramFile, String paramString)
  {
    this(paramFile, paramString, false, null);
  }

  public LockableFileWriter(File paramFile, String paramString1, boolean paramBoolean, String paramString2)
  {
    File localFile1 = paramFile.getAbsoluteFile();
    if (localFile1.getParentFile() != null)
      FileUtils.forceMkdir(localFile1.getParentFile());
    if (localFile1.isDirectory())
      throw new IOException("File specified is a directory");
    if (paramString2 == null)
      paramString2 = System.getProperty("java.io.tmpdir");
    File localFile2 = new File(paramString2);
    FileUtils.forceMkdir(localFile2);
    testLockDir(localFile2);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(localFile1.getName());
    localStringBuffer.append(".lck");
    this.lockFile = new File(localFile2, localStringBuffer.toString());
    createLock();
    this.out = initWriter(localFile1, paramString1, paramBoolean);
  }

  public LockableFileWriter(File paramFile, boolean paramBoolean)
  {
    this(paramFile, paramBoolean, null);
  }

  public LockableFileWriter(File paramFile, boolean paramBoolean, String paramString)
  {
    this(paramFile, null, paramBoolean, paramString);
  }

  public LockableFileWriter(String paramString)
  {
    this(paramString, false, null);
  }

  public LockableFileWriter(String paramString, boolean paramBoolean)
  {
    this(paramString, paramBoolean, null);
  }

  public LockableFileWriter(String paramString1, boolean paramBoolean, String paramString2)
  {
    this(new File(paramString1), paramBoolean, paramString2);
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  private void createLock()
  {
    if (class$org$apache$commons$io$output$LockableFileWriter == null)
    {
      ??? = class$("org.apache.commons.io.output.LockableFileWriter");
      class$org$apache$commons$io$output$LockableFileWriter = ???;
    }
    synchronized (class$org$apache$commons$io$output$LockableFileWriter)
    {
      if (!this.lockFile.createNewFile())
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Can't write file, lock ");
        localStringBuffer.append(this.lockFile.getAbsolutePath());
        localStringBuffer.append(" exists");
        throw new IOException(localStringBuffer.toString());
      }
      this.lockFile.deleteOnExit();
      return;
    }
  }

  private Writer initWriter(File paramFile, String paramString, boolean paramBoolean)
  {
    boolean bool = paramFile.exists();
    if (paramString == null);
    try
    {
      Object localObject = new FileWriter(paramFile.getAbsolutePath(), paramBoolean);
      break label57;
      localFileOutputStream = new FileOutputStream(paramFile.getAbsolutePath(), paramBoolean);
      try
      {
        OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(localFileOutputStream, paramString);
        localObject = localOutputStreamWriter;
        label57: return localObject;
      }
      catch (RuntimeException localRuntimeException1)
      {
      }
      catch (IOException localIOException1)
      {
        break label100;
      }
      IOUtils.closeQuietly(null);
      IOUtils.closeQuietly(localFileOutputStream);
      this.lockFile.delete();
      if (!bool)
        paramFile.delete();
      throw localRuntimeException1;
      label100: IOUtils.closeQuietly(null);
      IOUtils.closeQuietly(localFileOutputStream);
      this.lockFile.delete();
      if (!bool)
        paramFile.delete();
      throw localIOException1;
    }
    catch (RuntimeException localRuntimeException2)
    {
      while (true)
        localFileOutputStream = null;
    }
    catch (IOException localIOException2)
    {
      while (true)
        FileOutputStream localFileOutputStream = null;
    }
  }

  private void testLockDir(File paramFile)
  {
    if (!paramFile.exists())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Could not find lockDir: ");
      localStringBuffer1.append(paramFile.getAbsolutePath());
      throw new IOException(localStringBuffer1.toString());
    }
    if (!paramFile.canWrite())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Could not write to lockDir: ");
      localStringBuffer2.append(paramFile.getAbsolutePath());
      throw new IOException(localStringBuffer2.toString());
    }
  }

  public void close()
  {
    try
    {
      this.out.close();
      return;
    }
    finally
    {
      this.lockFile.delete();
    }
  }

  public void flush()
  {
    this.out.flush();
  }

  public void write(int paramInt)
  {
    this.out.write(paramInt);
  }

  public void write(String paramString)
  {
    this.out.write(paramString);
  }

  public void write(String paramString, int paramInt1, int paramInt2)
  {
    this.out.write(paramString, paramInt1, paramInt2);
  }

  public void write(char[] paramArrayOfChar)
  {
    this.out.write(paramArrayOfChar);
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.out.write(paramArrayOfChar, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.LockableFileWriter
 * JD-Core Version:    0.6.1
 */