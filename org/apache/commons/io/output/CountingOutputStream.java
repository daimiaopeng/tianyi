package org.apache.commons.io.output;

import java.io.OutputStream;

public class CountingOutputStream extends ProxyOutputStream
{
  private long count;

  public CountingOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
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

  public void write(int paramInt)
  {
    this.count = (1L + this.count);
    super.write(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
  {
    this.count += paramArrayOfByte.length;
    super.write(paramArrayOfByte);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.count += paramInt2;
    super.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.CountingOutputStream
 * JD-Core Version:    0.6.1
 */