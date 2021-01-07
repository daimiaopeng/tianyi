package org.apache.commons.io.output;

import java.io.OutputStream;

public class TeeOutputStream extends ProxyOutputStream
{
  protected OutputStream branch;

  public TeeOutputStream(OutputStream paramOutputStream1, OutputStream paramOutputStream2)
  {
    super(paramOutputStream1);
    this.branch = paramOutputStream2;
  }

  public void close()
  {
    super.close();
    this.branch.close();
  }

  public void flush()
  {
    super.flush();
    this.branch.flush();
  }

  public void write(int paramInt)
  {
    try
    {
      super.write(paramInt);
      this.branch.write(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void write(byte[] paramArrayOfByte)
  {
    try
    {
      super.write(paramArrayOfByte);
      this.branch.write(paramArrayOfByte);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      super.write(paramArrayOfByte, paramInt1, paramInt2);
      this.branch.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.TeeOutputStream
 * JD-Core Version:    0.6.1
 */