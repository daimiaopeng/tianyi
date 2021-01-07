package org.apache.http.entity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

@Deprecated
public abstract class AbstractHttpEntity
  implements HttpEntity
{
  protected boolean chunked;
  protected Header contentEncoding;
  protected Header contentType;

  protected AbstractHttpEntity()
  {
    throw new RuntimeException("Stub!");
  }

  public void consumeContent()
  {
    throw new RuntimeException("Stub!");
  }

  public Header getContentEncoding()
  {
    throw new RuntimeException("Stub!");
  }

  public Header getContentType()
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isChunked()
  {
    throw new RuntimeException("Stub!");
  }

  public void setChunked(boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }

  public void setContentEncoding(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public void setContentEncoding(Header paramHeader)
  {
    throw new RuntimeException("Stub!");
  }

  public void setContentType(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public void setContentType(Header paramHeader)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.AbstractHttpEntity
 * JD-Core Version:    0.6.1
 */