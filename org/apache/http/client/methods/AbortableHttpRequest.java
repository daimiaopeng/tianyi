package org.apache.http.client.methods;

import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;

@Deprecated
public abstract interface AbortableHttpRequest
{
  public abstract void abort();

  public abstract void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest);

  public abstract void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.AbortableHttpRequest
 * JD-Core Version:    0.6.1
 */