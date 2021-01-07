package org.apache.http;

@Deprecated
public abstract interface HttpConnection
{
  public abstract void close();

  public abstract HttpConnectionMetrics getMetrics();

  public abstract int getSocketTimeout();

  public abstract boolean isOpen();

  public abstract boolean isStale();

  public abstract void setSocketTimeout(int paramInt);

  public abstract void shutdown();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpConnection
 * JD-Core Version:    0.6.1
 */