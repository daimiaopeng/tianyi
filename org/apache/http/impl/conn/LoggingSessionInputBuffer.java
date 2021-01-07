package org.apache.http.impl.conn;

import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public class LoggingSessionInputBuffer
  implements SessionInputBuffer
{
  public LoggingSessionInputBuffer(SessionInputBuffer paramSessionInputBuffer, Wire paramWire)
  {
    throw new RuntimeException("Stub!");
  }

  public HttpTransportMetrics getMetrics()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isDataAvailable(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public int read()
  {
    throw new RuntimeException("Stub!");
  }

  public int read(byte[] paramArrayOfByte)
  {
    throw new RuntimeException("Stub!");
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
  {
    throw new RuntimeException("Stub!");
  }

  public String readLine()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.LoggingSessionInputBuffer
 * JD-Core Version:    0.6.1
 */