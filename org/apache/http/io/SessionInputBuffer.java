package org.apache.http.io;

import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract interface SessionInputBuffer
{
  public abstract HttpTransportMetrics getMetrics();

  public abstract boolean isDataAvailable(int paramInt);

  public abstract int read();

  public abstract int read(byte[] paramArrayOfByte);

  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  public abstract int readLine(CharArrayBuffer paramCharArrayBuffer);

  public abstract String readLine();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.io.SessionInputBuffer
 * JD-Core Version:    0.6.1
 */