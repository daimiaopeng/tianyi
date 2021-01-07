package org.apache.commons.io.filefilter;

import java.io.File;

public class EmptyFileFilter extends AbstractFileFilter
{
  public static final IOFileFilter EMPTY = new EmptyFileFilter();
  public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);

  public boolean accept(File paramFile)
  {
    boolean bool1 = paramFile.isDirectory();
    boolean bool2 = true;
    if (bool1)
    {
      File[] arrayOfFile = paramFile.listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length != 0))
        bool2 = false;
      return bool2;
    }
    if (paramFile.length() != 0L)
      bool2 = false;
    return bool2;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.EmptyFileFilter
 * JD-Core Version:    0.6.1
 */