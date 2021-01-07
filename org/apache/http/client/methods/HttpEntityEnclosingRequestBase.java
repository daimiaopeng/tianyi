package org.apache.http.client.methods;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;

@Deprecated
public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase
  implements HttpEntityEnclosingRequest
{
  public HttpEntityEnclosingRequestBase()
  {
    throw new RuntimeException("Stub!");
  }

  public Object clone()
  {
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

  public void setEntity(HttpEntity paramHttpEntity)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.HttpEntityEnclosingRequestBase
 * JD-Core Version:    0.6.1
 */