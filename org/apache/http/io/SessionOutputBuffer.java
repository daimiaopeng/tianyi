package org.apache.http.io;

import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract interface SessionOutputBuffer
{
  public abstract void flush();

  public abstract HttpTransportMetrics getMetrics();

  public abstract void write(int paramInt);

  public abstract void write(byte[] paramArrayOfByte);

  public abstract void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  public abstract void writeLine(String paramString);

  public abstract void writeLine(CharArrayBuffer paramCharArrayBuffer);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.io.SessionOutputBuffer
 * JD-Core Version:    0.6.1
 */