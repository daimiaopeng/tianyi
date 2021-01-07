package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;

public class PrefixFileFilter extends AbstractFileFilter
{
  private String[] prefixes;

  public PrefixFileFilter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The prefix must not be null");
    this.prefixes = new String[] { paramString };
  }

  public PrefixFileFilter(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("The list of prefixes must not be null");
    this.prefixes = ((String[])paramList.toArray(new String[paramList.size()]));
  }

  public PrefixFileFilter(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("The array of prefixes must not be null");
    this.prefixes = paramArrayOfString;
  }

  public boolean accept(File paramFile)
  {
    String str = paramFile.getName();
    for (int i = 0; i < this.prefixes.length; i++)
      if (str.startsWith(this.prefixes[i]))
        return true;
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    for (int i = 0; i < this.prefixes.length; i++)
      if (paramString.startsWith(this.prefixes[i]))
        return true;
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.PrefixFileFilter
 * JD-Core Version:    0.6.1
 */