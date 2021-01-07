package org.apache.commons.io.filefilter;

import java.io.File;

public class FileFileFilter extends AbstractFileFilter
{
  public static final IOFileFilter FILE = new FileFileFilter();

  public boolean accept(File paramFile)
  {
    return paramFile.isFile();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.FileFileFilter
 * JD-Core Version:    0.6.1
 */