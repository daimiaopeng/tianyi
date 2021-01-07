package org.apache.http;

import java.util.Iterator;

@Deprecated
public abstract interface TokenIterator extends Iterator
{
  public abstract boolean hasNext();

  public abstract String nextToken();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.TokenIterator
 * JD-Core Version:    0.6.1
 */