package org.apache.http;

import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract interface FormattedHeader extends Header
{
  public abstract CharArrayBuffer getBuffer();

  public abstract int getValuePos();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.FormattedHeader
 * JD-Core Version:    0.6.1
 */