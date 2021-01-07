package org.apache.commons.io.output;

import java.io.OutputStream;

public class DemuxOutputStream extends OutputStream
{
  private InheritableThreadLocal m_streams = new InheritableThreadLocal();

  private OutputStream getStream()
  {
    return (OutputStream)this.m_streams.get();
  }

  public OutputStream bindStream(OutputStream paramOutputStream)
  {
    OutputStream localOutputStream = getStream();
    this.m_streams.set(paramOutputStream);
    return localOutputStream;
  }

  public void close()
  {
    OutputStream localOutputStream = getStream();
    if (localOutputStream != null)
      localOutputStream.close();
  }

  public void flush()
  {
    OutputStream localOutputStream = getStream();
    if (localOutputStream != null)
      localOutputStream.flush();
  }

  public void write(int paramInt)
  {
    OutputStream localOutputStream = getStream();
    if (localOutputStream != null)
      localOutputStream.write(paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.DemuxOutputStream
 * JD-Core Version:    0.6.1
 */