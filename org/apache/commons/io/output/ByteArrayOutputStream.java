package org.apache.commons.io.output;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayOutputStream extends OutputStream
{
  private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  private List buffers = new ArrayList();
  private int count;
  private byte[] currentBuffer;
  private int currentBufferIndex;
  private int filledBufferSum;

  public ByteArrayOutputStream()
  {
    this(1024);
  }

  public ByteArrayOutputStream(int paramInt)
  {
    if (paramInt < 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Negative initial size: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    needNewBuffer(paramInt);
  }

  private byte[] getBuffer(int paramInt)
  {
    return (byte[])this.buffers.get(paramInt);
  }

  private void needNewBuffer(int paramInt)
  {
    if (this.currentBufferIndex < -1 + this.buffers.size())
    {
      this.filledBufferSum += this.currentBuffer.length;
      this.currentBufferIndex = (1 + this.currentBufferIndex);
      this.currentBuffer = getBuffer(this.currentBufferIndex);
    }
    else
    {
      if (this.currentBuffer == null)
      {
        this.filledBufferSum = 0;
      }
      else
      {
        paramInt = Math.max(this.currentBuffer.length << 1, paramInt - this.filledBufferSum);
        this.filledBufferSum += this.currentBuffer.length;
      }
      this.currentBufferIndex = (1 + this.currentBufferIndex);
      this.currentBuffer = new byte[paramInt];
      this.buffers.add(this.currentBuffer);
    }
  }

  public void close()
  {
  }

  public void reset()
  {
    try
    {
      this.count = 0;
      this.filledBufferSum = 0;
      this.currentBufferIndex = 0;
      this.currentBuffer = getBuffer(this.currentBufferIndex);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int size()
  {
    try
    {
      int i = this.count;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public byte[] toByteArray()
  {
    try
    {
      int i = this.count;
      if (i == 0)
      {
        byte[] arrayOfByte3 = EMPTY_BYTE_ARRAY;
        return arrayOfByte3;
      }
      byte[] arrayOfByte1 = new byte[i];
      int j = i;
      int k = 0;
      int m = 0;
      while (k < this.buffers.size())
      {
        byte[] arrayOfByte2 = getBuffer(k);
        int n = Math.min(arrayOfByte2.length, j);
        System.arraycopy(arrayOfByte2, 0, arrayOfByte1, m, n);
        m += n;
        j -= n;
        if (j == 0)
          break;
        k++;
      }
      return arrayOfByte1;
    }
    finally
    {
    }
  }

  public String toString()
  {
    return new String(toByteArray());
  }

  public String toString(String paramString)
  {
    return new String(toByteArray(), paramString);
  }

  public void write(int paramInt)
  {
    try
    {
      int i = this.count - this.filledBufferSum;
      if (i == this.currentBuffer.length)
      {
        needNewBuffer(1 + this.count);
        i = 0;
      }
      this.currentBuffer[i] = (byte)paramInt;
      this.count = (1 + this.count);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 <= paramArrayOfByte.length) && (paramInt2 >= 0))
    {
      int i = paramInt1 + paramInt2;
      if ((i <= paramArrayOfByte.length) && (i >= 0))
      {
        if (paramInt2 == 0)
          return;
        try
        {
          int j = paramInt2 + this.count;
          int k = this.count - this.filledBufferSum;
          while (paramInt2 > 0)
          {
            int m = Math.min(paramInt2, this.currentBuffer.length - k);
            System.arraycopy(paramArrayOfByte, i - paramInt2, this.currentBuffer, k, m);
            paramInt2 -= m;
            if (paramInt2 > 0)
            {
              needNewBuffer(j);
              k = 0;
            }
          }
          this.count = j;
          return;
        }
        finally
        {
          localObject = finally;
          throw localObject;
        }
      }
    }
    throw new IndexOutOfBoundsException();
  }

  public void writeTo(OutputStream paramOutputStream)
  {
    try
    {
      int i = this.count;
      for (int j = 0; j < this.buffers.size(); j++)
      {
        byte[] arrayOfByte = getBuffer(j);
        int k = Math.min(arrayOfByte.length, i);
        paramOutputStream.write(arrayOfByte, 0, k);
        i -= k;
        if (i == 0)
          break;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.ByteArrayOutputStream
 * JD-Core Version:    0.6.1
 */