package org.apache.http;

import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public abstract interface HttpEntity
{
  public abstract void consumeContent();

  public abstract InputStream getContent();

  public abstract Header getContentEncoding();

  public abstract long getContentLength();

  public abstract Header getContentType();

  public abstract boolean isChunked();

  public abstract boolean isRepeatable();

  public abstract boolean isStreaming();

  public abstract void writeTo(OutputStream paramOutputStream);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpEntity
 * JD-Core Version:    0.6.1
 */