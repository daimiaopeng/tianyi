package org.apache.http.impl.conn;

import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public class LoggingSessionOutputBuffer
  implements SessionOutputBuffer
{
  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire)
  {
    throw new RuntimeException("Stub!");
  }

  public void flush()
  {
    throw new RuntimeException("Stub!");
  }

  public HttpTransportMetrics getMetrics()
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
 * Qualified Name:     org.apache.http.impl.conn.LoggingSessionOutputBuffer
 * JD-Core Version:    0.6.1
 */