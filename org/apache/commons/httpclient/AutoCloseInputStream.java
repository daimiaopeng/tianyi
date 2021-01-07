package org.apache.commons.httpclient;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class AutoCloseInputStream extends FilterInputStream
{
  private boolean selfClosed = false;
  private boolean streamOpen = true;
  private ResponseConsumedWatcher watcher = null;

  public AutoCloseInputStream(InputStream paramInputStream, ResponseConsumedWatcher paramResponseConsumedWatcher)
  {
    super(paramInputStream);
    this.watcher = paramResponseConsumedWatcher;
  }

  private void checkClose(int paramInt)
  {
    if (paramInt == -1)
      notifyWatcher();
  }

  private boolean isReadAllowed()
  {
    if ((!this.streamOpen) && (this.selfClosed))
      throw new IOException("Attempted read on closed stream.");
    return this.streamOpen;
  }

  private void notifyWatcher()
  {
    if (this.streamOpen)
    {
      super.close();
      this.streamOpen = false;
      if (this.watcher != null)
        this.watcher.responseConsumed();
    }
  }

  public int available()
  {
    int i;
    if (isReadAllowed())
      i = super.available();
    else
      i = 0;
    return i;
  }

  public void close()
  {
    if (!this.selfClosed)
    {
      this.selfClosed = true;
      notifyWatcher();
    }
  }

  public int read()
  {
    int i;
    if (isReadAllowed())
    {
      i = super.read();
      checkClose(i);
    }
    else
    {
      i = -1;
    }
    return i;
  }

  public int read(byte[] paramArrayOfByte)
  {
    int i;
    if (isReadAllowed())
    {
      i = super.read(paramArrayOfByte);
      checkClose(i);
    }
    else
    {
      i = -1;
    }
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i;
    if (isReadAllowed())
    {
      i = super.read(paramArrayOfByte, paramInt1, paramInt2);
      checkClose(i);
    }
    else
    {
      i = -1;
    }
    return i;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.AutoCloseInputStream
 * JD-Core Version:    0.6.1
 */