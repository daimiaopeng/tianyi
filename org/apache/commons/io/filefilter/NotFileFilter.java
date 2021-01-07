package org.apache.commons.io.filefilter;

import java.io.File;

public class NotFileFilter extends AbstractFileFilter
{
  private IOFileFilter filter;

  public NotFileFilter(IOFileFilter paramIOFileFilter)
  {
    if (paramIOFileFilter == null)
      throw new IllegalArgumentException("The filter must not be null");
    this.filter = paramIOFileFilter;
  }

  public boolean accept(File paramFile)
  {
    return true ^ this.filter.accept(paramFile);
  }

  public boolean accept(File paramFile, String paramString)
  {
    return true ^ this.filter.accept(paramFile, paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.NotFileFilter
 * JD-Core Version:    0.6.1
 */