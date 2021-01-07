package org.apache.http.impl.io;

import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;

@Deprecated
public class ChunkedOutputStream extends OutputStream
{
  public ChunkedOutputStream(SessionOutputBuffer paramSessionOutputBuffer)
  {
    throw new RuntimeException("Stub!");
  }

  public ChunkedOutputStream(SessionOutputBuffer paramSessionOutputBuffer, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public void close()
  {
    throw new RuntimeException("Stub!");
  }

  public void finish()
  {
    throw new RuntimeException("Stub!");
  }

  public void flush()
  {
    throw new RuntimeException("Stub!");
  }

  protected void flushCache()
  {
    throw new RuntimeException("Stub!");
  }

  protected void flushCacheWithAppend(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }

  public void write(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public void write(byte[] paramArrayOfByte)
  {
    throw new RuntimeException("Stub!");
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }

  protected void writeClosingChunk()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.ChunkedOutputStream
 * JD-Core Version:    0.6.1
 */