package org.apache.commons.httpclient;

public abstract interface MethodRetryHandler
{
  public abstract boolean retryMethod(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpRecoverableException paramHttpRecoverableException, int paramInt, boolean paramBoolean);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.MethodRetryHandler
 * JD-Core Version:    0.6.1
 */