package org.apache.commons.io.filefilter;

import java.io.File;

public class CanWriteFileFilter extends AbstractFileFilter
{
  public static final IOFileFilter CANNOT_WRITE = new NotFileFilter(CAN_WRITE);
  public static final IOFileFilter CAN_WRITE = new CanWriteFileFilter();

  public boolean accept(File paramFile)
  {
    return paramFile.canWrite();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.CanWriteFileFilter
 * JD-Core Version:    0.6.1
 */