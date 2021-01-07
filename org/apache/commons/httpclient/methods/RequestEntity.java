package org.apache.commons.httpclient.methods;

import java.io.OutputStream;

public abstract interface RequestEntity
{
  public abstract long getContentLength();

  public abstract String getContentType();

  public abstract boolean isRepeatable();

  public abstract void writeRequest(OutputStream paramOutputStream);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.RequestEntity
 * JD-Core Version:    0.6.1
 */