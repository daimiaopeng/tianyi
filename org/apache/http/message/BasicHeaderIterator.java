package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;

@Deprecated
public class BasicHeaderIterator
  implements HeaderIterator
{
  protected final Header[] allHeaders = null;
  protected int currentIndex;
  protected String headerName;

  public BasicHeaderIterator(Header[] paramArrayOfHeader, String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  protected boolean filterHeader(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  protected int findNext(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean hasNext()
  {
    throw new RuntimeException("Stub!");
  }

  public final Object next()
  {
    throw new RuntimeException("Stub!");
  }

  public Header nextHeader()
  {
    throw new RuntimeException("Stub!");
  }

  public void remove()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicHeaderIterator
 * JD-Core Version:    0.6.1
 */