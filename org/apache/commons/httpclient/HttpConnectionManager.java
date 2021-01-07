package org.apache.commons.httpclient;

import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public abstract interface HttpConnectionManager
{
  public abstract void closeIdleConnections(long paramLong);

  public abstract HttpConnection getConnection(HostConfiguration paramHostConfiguration);

  public abstract HttpConnection getConnection(HostConfiguration paramHostConfiguration, long paramLong);

  public abstract HttpConnection getConnectionWithTimeout(HostConfiguration paramHostConfiguration, long paramLong);

  public abstract HttpConnectionManagerParams getParams();

  public abstract void releaseConnection(HttpConnection paramHttpConnection);

  public abstract void setParams(HttpConnectionManagerParams paramHttpConnectionManagerParams);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpConnectionManager
 * JD-Core Version:    0.6.1
 */