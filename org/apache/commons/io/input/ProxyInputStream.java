package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.InputStream;

public abstract class ProxyInputStream extends FilterInputStream
{
  public ProxyInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public int available()
  {
    return this.in.available();
  }

  public void close()
  {
    this.in.close();
  }

  public void mark(int paramInt)
  {
    try
    {
      this.in.mark(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean markSupported()
  {
    return this.in.markSupported();
  }

  public int read()
  {
    return this.in.read();
  }

  public int read(byte[] paramArrayOfByte)
  {
    return this.in.read(paramArrayOfByte);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return this.in.read(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void reset()
  {
    try
    {
      this.in.reset();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long skip(long paramLong)
  {
    return this.in.skip(paramLong);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.ProxyInputStream
 * JD-Core Version:    0.6.1
 */