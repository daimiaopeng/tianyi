package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;

@Deprecated
public abstract class HttpRequestBase extends AbstractHttpMessage
  implements AbortableHttpRequest, HttpUriRequest
{
  public HttpRequestBase()
  {
    throw new RuntimeException("Stub!");
  }

  public void abort()
  {
    throw new RuntimeException("Stub!");
  }

  public Object clone()
  {
    throw new RuntimeException("Stub!");
  }

  public abstract String getMethod();

  public ProtocolVersion getProtocolVersion()
  {
    throw new RuntimeException("Stub!");
  }

  public RequestLine getRequestLine()
  {
    throw new RuntimeException("Stub!");
  }

  public URI getURI()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isAborted()
  {
    throw new RuntimeException("Stub!");
  }

  public void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest)
  {
    throw new RuntimeException("Stub!");
  }

  public void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger)
  {
    throw new RuntimeException("Stub!");
  }

  public void setURI(URI paramURI)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.HttpRequestBase
 * JD-Core Version:    0.6.1
 */