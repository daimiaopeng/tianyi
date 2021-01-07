package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public class ProxyOutputStream extends FilterOutputStream
{
  public ProxyOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }

  public void close()
  {
    this.out.close();
  }

  public void flush()
  {
    this.out.flush();
  }

  public void write(int paramInt)
  {
    this.out.write(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
  {
    this.out.write(paramArrayOfByte);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.ProxyOutputStream
 * JD-Core Version:    0.6.1
 */