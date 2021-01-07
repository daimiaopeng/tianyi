package org.apache.http.conn;

import java.io.InputStream;

@Deprecated
public abstract interface EofSensorWatcher
{
  public abstract boolean eofDetected(InputStream paramInputStream);

  public abstract boolean streamAbort(InputStream paramInputStream);

  public abstract boolean streamClosed(InputStream paramInputStream);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.EofSensorWatcher
 * JD-Core Version:    0.6.1
 */