package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

public class ContentLengthInputStream extends InputStream
{
  private boolean closed = false;
  private long contentLength;
  private long pos = 0L;
  private InputStream wrappedStream = null;

  public ContentLengthInputStream(InputStream paramInputStream, int paramInt)
  {
    this(paramInputStream, paramInt);
  }

  public ContentLengthInputStream(InputStream paramInputStream, long paramLong)
  {
    this.wrappedStream = paramInputStream;
    this.contentLength = paramLong;
  }

  public int available()
  {
    if (this.closed)
      return 0;
    int i = this.wrappedStream.available();
    if (this.pos + i > this.contentLength)
      i = (int)(this.contentLength - this.pos);
    return i;
  }

  public void close()
  {
    if (!this.closed)
      try
      {
        ChunkedInputStream.exhaustInputStream(this);
        this.closed = true;
      }
      finally
      {
        this.closed = true;
      }
  }

  public int read()
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.pos >= this.contentLength)
      return -1;
    this.pos = (1L + this.pos);
    return this.wrappedStream.read();
  }

  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.pos >= this.contentLength)
      return -1;
    if (this.pos + paramInt2 > this.contentLength)
      paramInt2 = (int)(this.contentLength - this.pos);
    int i = this.wrappedStream.read(paramArrayOfByte, paramInt1, paramInt2);
    this.pos += i;
    return i;
  }

  public long skip(long paramLong)
  {
    long l1 = Math.min(paramLong, this.contentLength - this.pos);
    long l2 = this.wrappedStream.skip(l1);
    if (l2 > 0L)
      this.pos = (l2 + this.pos);
    return l2;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ContentLengthInputStream
 * JD-Core Version:    0.6.1
 */