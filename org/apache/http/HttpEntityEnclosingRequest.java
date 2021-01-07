package org.apache.http;

@Deprecated
public abstract interface HttpEntityEnclosingRequest extends HttpRequest
{
  public abstract boolean expectContinue();

  public abstract HttpEntity getEntity();

  public abstract void setEntity(HttpEntity paramHttpEntity);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpEntityEnclosingRequest
 * JD-Core Version:    0.6.1
 */