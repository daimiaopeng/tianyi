package org.apache.http;

@Deprecated
public abstract interface HttpConnectionMetrics
{
  public abstract Object getMetric(String paramString);

  public abstract long getReceivedBytesCount();

  public abstract long getRequestCount();

  public abstract long getResponseCount();

  public abstract long getSentBytesCount();

  public abstract void reset();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpConnectionMetrics
 * JD-Core Version:    0.6.1
 */