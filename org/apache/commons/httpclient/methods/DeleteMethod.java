package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class DeleteMethod extends HttpMethodBase
{
  public DeleteMethod()
  {
  }

  public DeleteMethod(String paramString)
  {
    super(paramString);
  }

  public String getName()
  {
    return "DELETE";
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.DeleteMethod
 * JD-Core Version:    0.6.1
 */