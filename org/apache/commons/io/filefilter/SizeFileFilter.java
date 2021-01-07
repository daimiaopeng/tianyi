package org.apache.commons.io.filefilter;

import java.io.File;

public class SizeFileFilter extends AbstractFileFilter
{
  private boolean acceptLarger;
  private long size;

  public SizeFileFilter(long paramLong)
  {
    this(paramLong, true);
  }

  public SizeFileFilter(long paramLong, boolean paramBoolean)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("The size must be non-negative");
    this.size = paramLong;
    this.acceptLarger = paramBoolean;
  }

  public boolean accept(File paramFile)
  {
    boolean bool;
    if (paramFile.length() < this.size)
      bool = true;
    else
      bool = false;
    if (this.acceptLarger)
      if (!bool)
        bool = true;
      else
        bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.SizeFileFilter
 * JD-Core Version:    0.6.1
 */