package org.apache.commons.io.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

public class DeferredFileOutputStream extends ThresholdingOutputStream
{
  private boolean closed = false;
  private OutputStream currentOutputStream;
  private ByteArrayOutputStream memoryOutputStream;
  private File outputFile;

  public DeferredFileOutputStream(int paramInt, File paramFile)
  {
    super(paramInt);
    this.outputFile = paramFile;
    this.memoryOutputStream = new ByteArrayOutputStream();
    this.currentOutputStream = this.memoryOutputStream;
  }

  public void close()
  {
    super.close();
    this.closed = true;
  }

  public byte[] getData()
  {
    if (this.memoryOutputStream != null)
      return this.memoryOutputStream.toByteArray();
    return null;
  }

  public File getFile()
  {
    return this.outputFile;
  }

  protected OutputStream getStream()
  {
    return this.currentOutputStream;
  }

  public boolean isInMemory()
  {
    return true ^ isThresholdExceeded();
  }

  protected void thresholdReached()
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(this.outputFile);
    this.memoryOutputStream.writeTo(localFileOutputStream);
    this.currentOutputStream = localFileOutputStream;
    this.memoryOutputStream = null;
  }

  public void writeTo(OutputStream paramOutputStream)
  {
    if (!this.closed)
      throw new IOException("Stream not closed");
    FileInputStream localFileInputStream;
    if (isInMemory())
      this.memoryOutputStream.writeTo(paramOutputStream);
    else
      localFileInputStream = new FileInputStream(this.outputFile);
    try
    {
      IOUtils.copy(localFileInputStream, paramOutputStream);
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.DeferredFileOutputStream
 * JD-Core Version:    0.6.1
 */