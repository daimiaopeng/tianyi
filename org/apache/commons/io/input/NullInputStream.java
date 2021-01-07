package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class NullInputStream extends InputStream
{
  private boolean eof;
  private long mark = -1L;
  private boolean markSupported;
  private long position;
  private long readlimit;
  private long size;
  private boolean throwEofException;

  public NullInputStream(long paramLong)
  {
    this(paramLong, true, false);
  }

  public NullInputStream(long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.size = paramLong;
    this.markSupported = paramBoolean1;
    this.throwEofException = paramBoolean2;
  }

  private int doEndOfFile()
  {
    this.eof = true;
    if (this.throwEofException)
      throw new EOFException();
    return -1;
  }

  public int available()
  {
    long l = this.size - this.position;
    if (l <= 0L)
      return 0;
    if (l > 2147483647L)
      return 2147483647;
    return (int)l;
  }

  public void close()
  {
    this.eof = false;
    this.position = 0L;
    this.mark = -1L;
  }

  public long getPosition()
  {
    return this.position;
  }

  public long getSize()
  {
    return this.size;
  }

  public void mark(int paramInt)
  {
    try
    {
      if (!this.markSupported)
        throw new UnsupportedOperationException("Mark not supported");
      this.mark = this.position;
      this.readlimit = paramInt;
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
    return this.markSupported;
  }

  protected int processByte()
  {
    return 0;
  }

  protected void processBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
  }

  public int read()
  {
    if (this.eof)
      throw new IOException("Read after end of file");
    if (this.position == this.size)
      return doEndOfFile();
    this.position = (1L + this.position);
    return processByte();
  }

  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.eof)
      throw new IOException("Read after end of file");
    if (this.position == this.size)
      return doEndOfFile();
    this.position += paramInt2;
    if (this.position > this.size)
    {
      paramInt2 -= (int)(this.position - this.size);
      this.position = this.size;
    }
    processBytes(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }

  public void reset()
  {
    try
    {
      if (!this.markSupported)
        throw new UnsupportedOperationException("Mark not supported");
      if (this.mark < 0L)
        throw new IOException("No position has been marked");
      if (this.position > this.mark + this.readlimit)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Marked position [");
        localStringBuffer.append(this.mark);
        localStringBuffer.append("] is no longer valid - passed the read limit [");
        localStringBuffer.append(this.readlimit);
        localStringBuffer.append("]");
        throw new IOException(localStringBuffer.toString());
      }
      this.position = this.mark;
      this.eof = false;
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
    if (this.eof)
      throw new IOException("Skip after end of file");
    if (this.position == this.size)
      return doEndOfFile();
    this.position = (paramLong + this.position);
    if (this.position > this.size)
    {
      long l = paramLong - (this.position - this.size);
      this.position = this.size;
      paramLong = l;
    }
    return paramLong;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.NullInputStream
 * JD-Core Version:    0.6.1
 */