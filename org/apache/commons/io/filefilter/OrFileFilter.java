package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OrFileFilter extends AbstractFileFilter
  implements ConditionalFileFilter
{
  private List fileFilters;

  public OrFileFilter()
  {
    this.fileFilters = new ArrayList();
  }

  public OrFileFilter(List paramList)
  {
    if (paramList == null)
      this.fileFilters = new ArrayList();
    else
      this.fileFilters = new ArrayList(paramList);
  }

  public OrFileFilter(IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    if ((paramIOFileFilter1 != null) && (paramIOFileFilter2 != null))
    {
      this.fileFilters = new ArrayList();
      addFileFilter(paramIOFileFilter1);
      addFileFilter(paramIOFileFilter2);
      return;
    }
    throw new IllegalArgumentException("The filters must not be null");
  }

  public boolean accept(File paramFile)
  {
    Iterator localIterator = this.fileFilters.iterator();
    while (localIterator.hasNext())
      if (((IOFileFilter)localIterator.next()).accept(paramFile))
        return true;
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    Iterator localIterator = this.fileFilters.iterator();
    while (localIterator.hasNext())
      if (((IOFileFilter)localIterator.next()).accept(paramFile, paramString))
        return true;
    return false;
  }

  public void addFileFilter(IOFileFilter paramIOFileFilter)
  {
    this.fileFilters.add(paramIOFileFilter);
  }

  public List getFileFilters()
  {
    return Collections.unmodifiableList(this.fileFilters);
  }

  public boolean removeFileFilter(IOFileFilter paramIOFileFilter)
  {
    return this.fileFilters.remove(paramIOFileFilter);
  }

  public void setFileFilters(List paramList)
  {
    this.fileFilters = paramList;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.OrFileFilter
 * JD-Core Version:    0.6.1
 */