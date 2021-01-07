package org.apache.http.conn.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@Deprecated
public class ConnManagerParamBean extends HttpAbstractParamBean
{
  public ConnManagerParamBean(HttpParams paramHttpParams)
  {
    super((HttpParams)null);
    throw new RuntimeException("Stub!");
  }

  public void setConnectionsPerRoute(ConnPerRouteBean paramConnPerRouteBean)
  {
    throw new RuntimeException("Stub!");
  }

  public void setMaxTotalConnections(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public void setTimeout(long paramLong)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.params.ConnManagerParamBean
 * JD-Core Version:    0.6.1
 */