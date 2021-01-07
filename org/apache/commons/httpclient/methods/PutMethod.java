package org.apache.commons.httpclient.methods;

public class PutMethod extends EntityEnclosingMethod
{
  public PutMethod()
  {
  }

  public PutMethod(String paramString)
  {
    super(paramString);
  }

  public String getName()
  {
    return "PUT";
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.PutMethod
 * JD-Core Version:    0.6.1
 */