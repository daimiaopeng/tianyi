package org.apache.http.entity;

import java.io.OutputStream;

@Deprecated
public abstract interface ContentProducer
{
  public abstract void writeTo(OutputStream paramOutputStream);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.ContentProducer
 * JD-Core Version:    0.6.1
 */