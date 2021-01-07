package org.apache.http.io;

@Deprecated
public abstract interface HttpTransportMetrics
{
  public abstract long getBytesTransferred();

  public abstract void reset();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.io.HttpTransportMetrics
 * JD-Core Version:    0.6.1
 */