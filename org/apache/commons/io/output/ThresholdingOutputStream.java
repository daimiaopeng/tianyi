package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public abstract class ThresholdingOutputStream extends OutputStream
{
  private int threshold;
  private boolean thresholdExceeded;
  private long written;

  public ThresholdingOutputStream(int paramInt)
  {
    this.threshold = paramInt;
  }

  protected void checkThreshold(int paramInt)
  {
    if ((!this.thresholdExceeded) && (this.written + paramInt > this.threshold))
    {
      thresholdReached();
      this.thresholdExceeded = true;
    }
  }

  public void close()
  {
    try
    {
      flush();
    }
    catch (IOException localIOException)
    {
    }
    getStream().close();
  }

  public void flush()
  {
    getStream().flush();
  }

  public long getByteCount()
  {
    return this.written;
  }

  protected abstract OutputStream getStream();

  public int getThreshold()
  {
    return this.threshold;
  }

  public boolean isThresholdExceeded()
  {
    boolean bool;
    if (this.written > this.threshold)
      bool = true;
    else
      bool = false;
    return bool;
  }

  protected abstract void thresholdReached();

  public void write(int paramInt)
  {
    checkThreshold(1);
    getStream().write(paramInt);
    this.written = (1L + this.written);
  }

  public void write(byte[] paramArrayOfByte)
  {
    checkThreshold(paramArrayOfByte.length);
    getStream().write(paramArrayOfByte);
    this.written += paramArrayOfByte.length;
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    checkThreshold(paramInt2);
    getStream().write(paramArrayOfByte, paramInt1, paramInt2);
    this.written += paramInt2;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.ThresholdingOutputStream
 * JD-Core Version:    0.6.1
 */