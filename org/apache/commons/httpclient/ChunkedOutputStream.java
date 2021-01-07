package org.apache.commons.httpclient;

import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;

public class ChunkedOutputStream extends OutputStream
{
  private static final byte[] CRLF = { 13, 10 };
  private static final byte[] ENDCHUNK = CRLF;
  private static final byte[] ZERO = { 48 };
  private byte[] cache;
  private int cachePosition = 0;
  private OutputStream stream = null;
  private boolean wroteLastChunk = false;

  public ChunkedOutputStream(OutputStream paramOutputStream)
  {
    this(paramOutputStream, 2048);
  }

  public ChunkedOutputStream(OutputStream paramOutputStream, int paramInt)
  {
    this.cache = new byte[paramInt];
    this.stream = paramOutputStream;
  }

  public void close()
  {
    finish();
    super.close();
  }

  public void finish()
  {
    if (!this.wroteLastChunk)
    {
      flushCache();
      writeClosingChunk();
      this.wroteLastChunk = true;
    }
  }

  public void flush()
  {
    this.stream.flush();
  }

  protected void flushCache()
  {
    if (this.cachePosition > 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(Integer.toHexString(this.cachePosition));
      localStringBuffer.append("\r\n");
      byte[] arrayOfByte = EncodingUtil.getAsciiBytes(localStringBuffer.toString());
      this.stream.write(arrayOfByte, 0, arrayOfByte.length);
      this.stream.write(this.cache, 0, this.cachePosition);
      this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
      this.cachePosition = 0;
    }
  }

  protected void flushCacheWithAppend(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(Integer.toHexString(paramInt2 + this.cachePosition));
    localStringBuffer.append("\r\n");
    byte[] arrayOfByte = EncodingUtil.getAsciiBytes(localStringBuffer.toString());
    this.stream.write(arrayOfByte, 0, arrayOfByte.length);
    this.stream.write(this.cache, 0, this.cachePosition);
    this.stream.write(paramArrayOfByte, paramInt1, paramInt2);
    this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
    this.cachePosition = 0;
  }

  public void write(int paramInt)
  {
    this.cache[this.cachePosition] = (byte)paramInt;
    this.cachePosition = (1 + this.cachePosition);
    if (this.cachePosition == this.cache.length)
      flushCache();
  }

  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= this.cache.length - this.cachePosition)
    {
      flushCacheWithAppend(paramArrayOfByte, paramInt1, paramInt2);
    }
    else
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.cache, this.cachePosition, paramInt2);
      this.cachePosition = (paramInt2 + this.cachePosition);
    }
  }

  protected void writeClosingChunk()
  {
    this.stream.write(ZERO, 0, ZERO.length);
    this.stream.write(CRLF, 0, CRLF.length);
    this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ChunkedOutputStream
 * JD-Core Version:    0.6.1
 */