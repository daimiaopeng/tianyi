package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

public class WildcardFilter extends AbstractFileFilter
{
  private String[] wildcards;

  public WildcardFilter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The wildcard must not be null");
    this.wildcards = new String[] { paramString };
  }

  public WildcardFilter(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("The wildcard list must not be null");
    this.wildcards = ((String[])paramList.toArray(new String[paramList.size()]));
  }

  public WildcardFilter(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("The wildcard array must not be null");
    this.wildcards = paramArrayOfString;
  }

  public boolean accept(File paramFile)
  {
    if (paramFile.isDirectory())
      return false;
    for (int i = 0; i < this.wildcards.length; i++)
      if (FilenameUtils.wildcardMatch(paramFile.getName(), this.wildcards[i]))
        return true;
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    if ((paramFile != null) && (new File(paramFile, paramString).isDirectory()))
      return false;
    for (int i = 0; i < this.wildcards.length; i++)
      if (FilenameUtils.wildcardMatch(paramString, this.wildcards[i]))
        return true;
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.WildcardFilter
 * JD-Core Version:    0.6.1
 */