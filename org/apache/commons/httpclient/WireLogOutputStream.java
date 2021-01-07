package org.apache.commons.httpclient;

import java.io.FilterOutputStream;
import java.io.OutputStream;

class WireLogOutputStream extends FilterOutputStream
{
  private OutputStream out;
  private Wire wire;

  public WireLogOutputStream(OutputStream paramOutputStream, Wire paramWire)
  {
    super(paramOutputStream);
    this.out = paramOutputStream;
    this.wire = paramWire;
  }

  public void write(int paramInt)
  {
    this.out.write(paramInt);
    this.wire.output(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
  {
    this.out.write(paramArrayOfByte);
    this.wire.output(paramArrayOfByte);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    this.wire.output(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.WireLogOutputStream
 * JD-Core Version:    0.6.1
 */