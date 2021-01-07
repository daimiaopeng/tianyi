package org.apache.commons.httpclient;

class HttpMethodBase$1
  implements ResponseConsumedWatcher
{
  private final HttpMethodBase this$0;

  HttpMethodBase$1(HttpMethodBase paramHttpMethodBase)
  {
    this.this$0 = paramHttpMethodBase;
  }

  public void responseConsumed()
  {
    this.this$0.responseBodyConsumed();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodBase.1
 * JD-Core Version:    0.6.1
 */