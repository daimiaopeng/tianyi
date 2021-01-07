package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeaderGroup
{
  private List headers = new ArrayList();

  public void addHeader(Header paramHeader)
  {
    this.headers.add(paramHeader);
  }

  public void clear()
  {
    this.headers.clear();
  }

  public boolean containsHeader(String paramString)
  {
    Iterator localIterator = this.headers.iterator();
    while (localIterator.hasNext())
      if (((Header)localIterator.next()).getName().equalsIgnoreCase(paramString))
        return true;
    return false;
  }

  public Header[] getAllHeaders()
  {
    return (Header[])this.headers.toArray(new Header[this.headers.size()]);
  }

  public Header getCondensedHeader(String paramString)
  {
    Header[] arrayOfHeader = getHeaders(paramString);
    if (arrayOfHeader.length == 0)
      return null;
    int i = arrayOfHeader.length;
    int j = 1;
    if (i == j)
      return new Header(arrayOfHeader[0].getName(), arrayOfHeader[0].getValue());
    StringBuffer localStringBuffer = new StringBuffer(arrayOfHeader[0].getValue());
    while (j < arrayOfHeader.length)
    {
      localStringBuffer.append(", ");
      localStringBuffer.append(arrayOfHeader[j].getValue());
      j++;
    }
    return new Header(paramString.toLowerCase(), localStringBuffer.toString());
  }

  public Header getFirstHeader(String paramString)
  {
    Iterator localIterator = this.headers.iterator();
    while (localIterator.hasNext())
    {
      Header localHeader = (Header)localIterator.next();
      if (localHeader.getName().equalsIgnoreCase(paramString))
        return localHeader;
    }
    return null;
  }

  public Header[] getHeaders(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.headers.iterator();
    while (localIterator.hasNext())
    {
      Header localHeader = (Header)localIterator.next();
      if (localHeader.getName().equalsIgnoreCase(paramString))
        localArrayList.add(localHeader);
    }
    return (Header[])localArrayList.toArray(new Header[localArrayList.size()]);
  }

  public Iterator getIterator()
  {
    return this.headers.iterator();
  }

  public Header getLastHeader(String paramString)
  {
    for (int i = -1 + this.headers.size(); i >= 0; i--)
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        return localHeader;
    }
    return null;
  }

  public void removeHeader(Header paramHeader)
  {
    this.headers.remove(paramHeader);
  }

  public void setHeaders(Header[] paramArrayOfHeader)
  {
    clear();
    for (int i = 0; i < paramArrayOfHeader.length; i++)
      addHeader(paramArrayOfHeader[i]);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HeaderGroup
 * JD-Core Version:    0.6.1
 */