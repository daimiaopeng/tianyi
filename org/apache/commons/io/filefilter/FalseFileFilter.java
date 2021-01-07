package org.apache.commons.io.filefilter;

import java.io.File;

public class FalseFileFilter
  implements IOFileFilter
{
  public static final IOFileFilter FALSE = new FalseFileFilter();
  public static final IOFileFilter INSTANCE = FALSE;

  public boolean accept(File paramFile)
  {
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.FalseFileFilter
 * JD-Core Version:    0.6.1
 */