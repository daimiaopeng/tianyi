package org.apache.http.impl.entity;

import org.apache.http.HttpMessage;
import org.apache.http.entity.ContentLengthStrategy;

@Deprecated
public class StrictContentLengthStrategy
  implements ContentLengthStrategy
{
  public StrictContentLengthStrategy()
  {
    throw new RuntimeException("Stub!");
  }

  public long determineLength(HttpMessage paramHttpMessage)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.entity.StrictContentLengthStrategy
 * JD-Core Version:    0.6.1
 */