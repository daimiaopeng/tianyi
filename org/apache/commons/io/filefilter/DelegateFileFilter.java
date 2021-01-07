package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class DelegateFileFilter extends AbstractFileFilter
{
  private FileFilter fileFilter;
  private FilenameFilter filenameFilter;

  public DelegateFileFilter(FileFilter paramFileFilter)
  {
    if (paramFileFilter == null)
      throw new IllegalArgumentException("The FileFilter must not be null");
    this.fileFilter = paramFileFilter;
  }

  public DelegateFileFilter(FilenameFilter paramFilenameFilter)
  {
    if (paramFilenameFilter == null)
      throw new IllegalArgumentException("The FilenameFilter must not be null");
    this.filenameFilter = paramFilenameFilter;
  }

  public boolean accept(File paramFile)
  {
    if (this.fileFilter != null)
      return this.fileFilter.accept(paramFile);
    return super.accept(paramFile);
  }

  public boolean accept(File paramFile, String paramString)
  {
    if (this.filenameFilter != null)
      return this.filenameFilter.accept(paramFile, paramString);
    return super.accept(paramFile, paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.DelegateFileFilter
 * JD-Core Version:    0.6.1
 */