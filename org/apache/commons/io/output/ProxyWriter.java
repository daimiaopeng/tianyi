package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.Writer;

public class ProxyWriter extends FilterWriter
{
  public ProxyWriter(Writer paramWriter)
  {
    super(paramWriter);
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

  public void write(String paramString)
  {
    this.out.write(paramString);
  }

  public void write(String paramString, int paramInt1, int paramInt2)
  {
    this.out.write(paramString, paramInt1, paramInt2);
  }

  public void write(char[] paramArrayOfChar)
  {
    this.out.write(paramArrayOfChar);
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.out.write(paramArrayOfChar, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.ProxyWriter
 * JD-Core Version:    0.6.1
 */