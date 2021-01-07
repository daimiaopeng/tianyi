package org.apache.commons.httpclient.methods.multipart;

import java.io.InputStream;

public abstract interface PartSource
{
  public abstract InputStream createInputStream();

  public abstract String getFileName();

  public abstract long getLength();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.PartSource
 * JD-Core Version:    0.6.1
 */