package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.Reader;

public abstract class ProxyReader extends FilterReader
{
  public ProxyReader(Reader paramReader)
  {
    super(paramReader);
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

  public int read(char[] paramArrayOfChar)
  {
    return this.in.read(paramArrayOfChar);
  }

  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    return this.in.read(paramArrayOfChar, paramInt1, paramInt2);
  }

  public boolean ready()
  {
    return this.in.ready();
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
 * Qualified Name:     org.apache.commons.io.input.ProxyReader
 * JD-Core Version:    0.6.1
 */