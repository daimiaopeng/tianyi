package org.apache.http.impl.client;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;

@Deprecated
public class TunnelRefusedException extends HttpException
{
  public TunnelRefusedException(String paramString, HttpResponse paramHttpResponse)
  {
    throw new RuntimeException("Stub!");
  }

  public HttpResponse getResponse()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.TunnelRefusedException
 * JD-Core Version:    0.6.1
 */