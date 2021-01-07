package org.apache.http.impl.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;

@Deprecated
public class EntityEnclosingRequestWrapper extends RequestWrapper
  implements HttpEntityEnclosingRequest
{
  public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    super((HttpRequest)null);
    throw new RuntimeException("Stub!");
  }

  public boolean expectContinue()
  {
    throw new RuntimeException("Stub!");
  }

  public HttpEntity getEntity()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isRepeatable()
  {
    throw new RuntimeException("Stub!");
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.EntityEnclosingRequestWrapper
 * JD-Core Version:    0.6.1
 */