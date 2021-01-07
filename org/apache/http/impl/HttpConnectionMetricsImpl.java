package org.apache.http.impl;

import org.apache.http.HttpConnectionMetrics;
import org.apache.http.io.HttpTransportMetrics;

@Deprecated
public class HttpConnectionMetricsImpl
  implements HttpConnectionMetrics
{
  public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
  public static final String REQUEST_COUNT = "http.request-count";
  public static final String RESPONSE_COUNT = "http.response-count";
  public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";

  public HttpConnectionMetricsImpl(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    throw new RuntimeException("Stub!");
  }

  public Object getMetric(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public long getReceivedBytesCount()
  {
    throw new RuntimeException("Stub!");
  }

  public long getRequestCount()
  {
    throw new RuntimeException("Stub!");
  }

  public long getResponseCount()
  {
    throw new RuntimeException("Stub!");
  }

  public long getSentBytesCount()
  {
    throw new RuntimeException("Stub!");
  }

  public void incrementRequestCount()
  {
    throw new RuntimeException("Stub!");
  }

  public void incrementResponseCount()
  {
    throw new RuntimeException("Stub!");
  }

  public void reset()
  {
    throw new RuntimeException("Stub!");
  }

  public void setMetric(String paramString, Object paramObject)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.HttpConnectionMetricsImpl
 * JD-Core Version:    0.6.1
 */