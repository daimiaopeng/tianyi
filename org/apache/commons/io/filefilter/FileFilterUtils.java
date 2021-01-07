package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;

public class FileFilterUtils
{
  private static IOFileFilter cvsFilter;
  private static IOFileFilter svnFilter;

  public static IOFileFilter ageFileFilter(long paramLong)
  {
    return new AgeFileFilter(paramLong);
  }

  public static IOFileFilter ageFileFilter(long paramLong, boolean paramBoolean)
  {
    return new AgeFileFilter(paramLong, paramBoolean);
  }

  public static IOFileFilter ageFileFilter(File paramFile)
  {
    return new AgeFileFilter(paramFile);
  }

  public static IOFileFilter ageFileFilter(File paramFile, boolean paramBoolean)
  {
    return new AgeFileFilter(paramFile, paramBoolean);
  }

  public static IOFileFilter ageFileFilter(Date paramDate)
  {
    return new AgeFileFilter(paramDate);
  }

  public static IOFileFilter ageFileFilter(Date paramDate, boolean paramBoolean)
  {
    return new AgeFileFilter(paramDate, paramBoolean);
  }

  public static IOFileFilter andFileFilter(IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return new AndFileFilter(paramIOFileFilter1, paramIOFileFilter2);
  }

  public static IOFileFilter asFileFilter(FileFilter paramFileFilter)
  {
    return new DelegateFileFilter(paramFileFilter);
  }

  public static IOFileFilter asFileFilter(FilenameFilter paramFilenameFilter)
  {
    return new DelegateFileFilter(paramFilenameFilter);
  }

  public static IOFileFilter directoryFileFilter()
  {
    return DirectoryFileFilter.DIRECTORY;
  }

  public static IOFileFilter falseFileFilter()
  {
    return FalseFileFilter.FALSE;
  }

  public static IOFileFilter fileFileFilter()
  {
    return FileFileFilter.FILE;
  }

  public static IOFileFilter makeCVSAware(IOFileFilter paramIOFileFilter)
  {
    if (cvsFilter == null)
      cvsFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter("CVS")));
    if (paramIOFileFilter == null)
      return cvsFilter;
    return andFileFilter(paramIOFileFilter, cvsFilter);
  }

  public static IOFileFilter makeDirectoryOnly(IOFileFilter paramIOFileFilter)
  {
    if (paramIOFileFilter == null)
      return DirectoryFileFilter.DIRECTORY;
    return new AndFileFilter(DirectoryFileFilter.DIRECTORY, paramIOFileFilter);
  }

  public static IOFileFilter makeFileOnly(IOFileFilter paramIOFileFilter)
  {
    if (paramIOFileFilter == null)
      return FileFileFilter.FILE;
    return new AndFileFilter(FileFileFilter.FILE, paramIOFileFilter);
  }

  public static IOFileFilter makeSVNAware(IOFileFilter paramIOFileFilter)
  {
    if (svnFilter == null)
      svnFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter(".svn")));
    if (paramIOFileFilter == null)
      return svnFilter;
    return andFileFilter(paramIOFileFilter, svnFilter);
  }

  public static IOFileFilter nameFileFilter(String paramString)
  {
    return new NameFileFilter(paramString);
  }

  public static IOFileFilter notFileFilter(IOFileFilter paramIOFileFilter)
  {
    return new NotFileFilter(paramIOFileFilter);
  }

  public static IOFileFilter orFileFilter(IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return new OrFileFilter(paramIOFileFilter1, paramIOFileFilter2);
  }

  public static IOFileFilter prefixFileFilter(String paramString)
  {
    return new PrefixFileFilter(paramString);
  }

  public static IOFileFilter sizeFileFilter(long paramLong)
  {
    return new SizeFileFilter(paramLong);
  }

  public static IOFileFilter sizeFileFilter(long paramLong, boolean paramBoolean)
  {
    return new SizeFileFilter(paramLong, paramBoolean);
  }

  public static IOFileFilter sizeRangeFileFilter(long paramLong1, long paramLong2)
  {
    return new AndFileFilter(new SizeFileFilter(paramLong1, true), new SizeFileFilter(paramLong2 + 1L, false));
  }

  public static IOFileFilter suffixFileFilter(String paramString)
  {
    return new SuffixFileFilter(paramString);
  }

  public static IOFileFilter trueFileFilter()
  {
    return TrueFileFilter.TRUE;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.FileFilterUtils
 * JD-Core Version:    0.6.1
 */