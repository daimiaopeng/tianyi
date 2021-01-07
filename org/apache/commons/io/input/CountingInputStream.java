package org.apache.commons.io.input;

import java.io.InputStream;

public class CountingInputStream extends ProxyInputStream
{
  private long count;

  public CountingInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public long getByteCount()
  {
    try
    {
      long l = this.count;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getCount()
  {
    try
    {
      long l = getByteCount();
      if (l > 2147483647L)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("The byte count ");
        localStringBuffer.append(l);
        localStringBuffer.append(" is too large to be converted to an int");
        throw new ArithmeticException(localStringBuffer.toString());
      }
      int i = (int)l;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int read()
  {
    int i = super.read();
    long l1 = this.count;
    long l2;
    if (i >= 0)
      l2 = 1L;
    else
      l2 = 0L;
    this.count = (l1 + l2);
    return i;
  }

  public int read(byte[] paramArrayOfByte)
  {
    int i = super.read(paramArrayOfByte);
    long l1 = this.count;
    long l2;
    if (i >= 0)
      l2 = i;
    else
      l2 = 0L;
    this.count = (l1 + l2);
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
    long l1 = this.count;
    long l2;
    if (i >= 0)
      l2 = i;
    else
      l2 = 0L;
    this.count = (l1 + l2);
    return i;
  }

  public long resetByteCount()
  {
    try
    {
      long l = this.count;
      this.count = 0L;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int resetCount()
  {
    try
    {
      long l = resetByteCount();
      if (l > 2147483647L)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("The byte count ");
        localStringBuffer.append(l);
        localStringBuffer.append(" is too large to be converted to an int");
        throw new ArithmeticException(localStringBuffer.toString());
      }
      int i = (int)l;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long skip(long paramLong)
  {
    long l = super.skip(paramLong);
    this.count = (l + this.count);
    return l;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.CountingInputStream
 * JD-Core Version:    0.6.1
 */