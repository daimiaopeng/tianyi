package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;

public class SuffixFileFilter extends AbstractFileFilter
{
  private String[] suffixes;

  public SuffixFileFilter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The suffix must not be null");
    this.suffixes = new String[] { paramString };
  }

  public SuffixFileFilter(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("The list of suffixes must not be null");
    this.suffixes = ((String[])paramList.toArray(new String[paramList.size()]));
  }

  public SuffixFileFilter(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("The array of suffixes must not be null");
    this.suffixes = paramArrayOfString;
  }

  public boolean accept(File paramFile)
  {
    String str = paramFile.getName();
    for (int i = 0; i < this.suffixes.length; i++)
      if (str.endsWith(this.suffixes[i]))
        return true;
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    for (int i = 0; i < this.suffixes.length; i++)
      if (paramString.endsWith(this.suffixes[i]))
        return true;
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.SuffixFileFilter
 * JD-Core Version:    0.6.1
 */