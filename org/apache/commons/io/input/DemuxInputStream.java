package org.apache.commons.io.input;

import java.io.InputStream;

public class DemuxInputStream extends InputStream
{
  private InheritableThreadLocal m_streams = new InheritableThreadLocal();

  private InputStream getStream()
  {
    return (InputStream)this.m_streams.get();
  }

  public InputStream bindStream(InputStream paramInputStream)
  {
    InputStream localInputStream = getStream();
    this.m_streams.set(paramInputStream);
    return localInputStream;
  }

  public void close()
  {
    InputStream localInputStream = getStream();
    if (localInputStream != null)
      localInputStream.close();
  }

  public int read()
  {
    InputStream localInputStream = getStream();
    if (localInputStream != null)
      return localInputStream.read();
    return -1;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.DemuxInputStream
 * JD-Core Version:    0.6.1
 */