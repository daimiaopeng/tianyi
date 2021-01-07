package org.apache.http.entity;

import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public class StringEntity extends AbstractHttpEntity
{
  protected final byte[] content = null;

  public StringEntity(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public StringEntity(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }

  public Object clone()
  {
    throw new RuntimeException("Stub!");
  }

  public InputStream getContent()
  {
    throw new RuntimeException("Stub!");
  }

  public long getContentLength()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isRepeatable()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isStreaming()
  {
    throw new RuntimeException("Stub!");
  }

  public void writeTo(OutputStream paramOutputStream)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.StringEntity
 * JD-Core Version:    0.6.1
 */