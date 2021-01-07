package org.apache.http;

import java.io.Serializable;

@Deprecated
public final class HttpVersion extends ProtocolVersion
  implements Serializable
{
  public static final String HTTP = "HTTP";
  public static final HttpVersion HTTP_0_9;
  public static final HttpVersion HTTP_1_0;
  public static final HttpVersion HTTP_1_1;

  public HttpVersion(int paramInt1, int paramInt2)
  {
    super((String)null, 0, 0);
    throw new RuntimeException("Stub!");
  }

  public ProtocolVersion forVersion(int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpVersion
 * JD-Core Version:    0.6.1
 */