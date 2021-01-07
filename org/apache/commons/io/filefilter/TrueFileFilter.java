package org.apache.commons.io.filefilter;

import java.io.File;

public class TrueFileFilter
  implements IOFileFilter
{
  public static final IOFileFilter INSTANCE = TRUE;
  public static final IOFileFilter TRUE = new TrueFileFilter();

  public boolean accept(File paramFile)
  {
    return true;
  }

  public boolean accept(File paramFile, String paramString)
  {
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.TrueFileFilter
 * JD-Core Version:    0.6.1
 */