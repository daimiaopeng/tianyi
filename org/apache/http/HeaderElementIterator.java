package org.apache.http;

import java.util.Iterator;

@Deprecated
public abstract interface HeaderElementIterator extends Iterator
{
  public abstract boolean hasNext();

  public abstract HeaderElement nextElement();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.HeaderElementIterator
 * JD-Core Version:    0.6.1
 */