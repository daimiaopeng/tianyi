package org.apache.commons.io.filefilter;

import java.io.File;

public class CanReadFileFilter extends AbstractFileFilter
{
  public static final IOFileFilter CANNOT_READ = new NotFileFilter(CAN_READ);
  public static final IOFileFilter CAN_READ = new CanReadFileFilter();
  public static final IOFileFilter READ_ONLY = new AndFileFilter(CAN_READ, CanWriteFileFilter.CANNOT_WRITE);

  public boolean accept(File paramFile)
  {
    return paramFile.canRead();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.CanReadFileFilter
 * JD-Core Version:    0.6.1
 */