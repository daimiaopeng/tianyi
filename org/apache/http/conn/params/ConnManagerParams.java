package org.apache.http.conn.params;

import org.apache.http.params.HttpParams;

@Deprecated
public final class ConnManagerParams
  implements ConnManagerPNames
{
  public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

  public ConnManagerParams()
  {
    throw new RuntimeException("Stub!");
  }

  public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public static int getMaxTotalConnections(HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public static long getTimeout(HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public static void setMaxConnectionsPerRoute(HttpParams paramHttpParams, ConnPerRoute paramConnPerRoute)
  {
    throw new RuntimeException("Stub!");
  }

  public static void setMaxTotalConnections(HttpParams paramHttpParams, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public static void setTimeout(HttpParams paramHttpParams, long paramLong)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.params.ConnManagerParams
 * JD-Core Version:    0.6.1
 */