package org.apache.http.impl.io;

import java.io.OutputStream;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract class AbstractSessionOutputBuffer
  implements SessionOutputBuffer
{
  public AbstractSessionOutputBuffer()
  {
    throw new RuntimeException("Stub!");
  }

  public void flush()
  {
    throw new RuntimeException("Stub!");
  }

  protected void flushBuffer()
  {
    throw new RuntimeException("Stub!");
  }

  public HttpTransportMetrics getMetrics()
  {
    throw new RuntimeException("Stub!");
  }

  protected void init(OutputStream paramOutputStream, int paramInt, HttpParams paramHttpParams)
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

  public void writeLine(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.AbstractSessionOutputBuffer
 * JD-Core Version:    0.6.1
 */