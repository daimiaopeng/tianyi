package org.apache.commons.httpclient;

import java.io.IOException;

public abstract interface HttpMethodRetryHandler
{
  public abstract boolean retryMethod(HttpMethod paramHttpMethod, IOException paramIOException, int paramInt);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodRetryHandler
 * JD-Core Version:    0.6.1
 */