package org.apache.http.entity;

import org.apache.http.HttpMessage;

@Deprecated
public abstract interface ContentLengthStrategy
{
  public static final int CHUNKED = -2;
  public static final int IDENTITY = -1;

  public abstract long determineLength(HttpMessage paramHttpMessage);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.ContentLengthStrategy
 * JD-Core Version:    0.6.1
 */