package org.apache.commons.io.filefilter;

import java.io.File;

public class HiddenFileFilter extends AbstractFileFilter
{
  public static final IOFileFilter HIDDEN = new HiddenFileFilter();
  public static final IOFileFilter VISIBLE = new NotFileFilter(HIDDEN);

  public boolean accept(File paramFile)
  {
    return paramFile.isHidden();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.HiddenFileFilter
 * JD-Core Version:    0.6.1
 */