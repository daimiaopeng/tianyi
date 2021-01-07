package org.apache.commons.httpclient;

import java.io.FilterInputStream;
import java.io.InputStream;

class WireLogInputStream extends FilterInputStream
{
  private InputStream in;
  private Wire wire;

  public WireLogInputStream(InputStream paramInputStream, Wire paramWire)
  {
    super(paramInputStream);
    this.in = paramInputStream;
    this.wire = paramWire;
  }

  public int read()
  {
    int i = this.in.read();
    if (i > 0)
      this.wire.input(i);
    return i;
  }

  public int read(byte[] paramArrayOfByte)
  {
    int i = this.in.read(paramArrayOfByte);
    if (i > 0)
      this.wire.input(paramArrayOfByte, 0, i);
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i > 0)
      this.wire.input(paramArrayOfByte, paramInt1, i);
    return i;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.WireLogInputStream
 * JD-Core Version:    0.6.1
 */