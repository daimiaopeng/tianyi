package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class AgeFileFilter extends AbstractFileFilter
{
  private boolean acceptOlder;
  private long cutoff;

  public AgeFileFilter(long paramLong)
  {
    this(paramLong, true);
  }

  public AgeFileFilter(long paramLong, boolean paramBoolean)
  {
    this.acceptOlder = paramBoolean;
    this.cutoff = paramLong;
  }

  public AgeFileFilter(File paramFile)
  {
    this(paramFile, true);
  }

  public AgeFileFilter(File paramFile, boolean paramBoolean)
  {
    this(paramFile.lastModified(), paramBoolean);
  }

  public AgeFileFilter(Date paramDate)
  {
    this(paramDate, true);
  }

  public AgeFileFilter(Date paramDate, boolean paramBoolean)
  {
    this(paramDate.getTime(), paramBoolean);
  }

  public boolean accept(File paramFile)
  {
    boolean bool = FileUtils.isFileNewer(paramFile, this.cutoff);
    if (this.acceptOlder)
      if (!bool)
        bool = true;
      else
        bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.AgeFileFilter
 * JD-Core Version:    0.6.1
 */