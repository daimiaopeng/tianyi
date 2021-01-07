package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class TraceMethod extends HttpMethodBase
{
  public TraceMethod(String paramString)
  {
    super(paramString);
    setFollowRedirects(false);
  }

  public String getName()
  {
    return "TRACE";
  }

  public void recycle()
  {
    super.recycle();
    setFollowRedirects(false);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.TraceMethod
 * JD-Core Version:    0.6.1
 */