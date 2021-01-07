package org.apache.commons.io.filefilter;

import java.io.File;

public class DirectoryFileFilter extends AbstractFileFilter
{
  public static final IOFileFilter DIRECTORY = new DirectoryFileFilter();
  public static final IOFileFilter INSTANCE = DIRECTORY;

  public boolean accept(File paramFile)
  {
    return paramFile.isDirectory();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.DirectoryFileFilter
 * JD-Core Version:    0.6.1
 */