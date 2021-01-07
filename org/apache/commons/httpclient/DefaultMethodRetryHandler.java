package org.apache.commons.httpclient;

public class DefaultMethodRetryHandler
  implements MethodRetryHandler
{
  private boolean requestSentRetryEnabled = false;
  private int retryCount = 3;

  public int getRetryCount()
  {
    return this.retryCount;
  }

  public boolean isRequestSentRetryEnabled()
  {
    return this.requestSentRetryEnabled;
  }

  public boolean retryMethod(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpRecoverableException paramHttpRecoverableException, int paramInt, boolean paramBoolean)
  {
    boolean bool;
    if (((!paramBoolean) || (this.requestSentRetryEnabled)) && (paramInt <= this.retryCount))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void setRequestSentRetryEnabled(boolean paramBoolean)
  {
    this.requestSentRetryEnabled = paramBoolean;
  }

  public void setRetryCount(int paramInt)
  {
    this.retryCount = paramInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.DefaultMethodRetryHandler
 * JD-Core Version:    0.6.1
 */