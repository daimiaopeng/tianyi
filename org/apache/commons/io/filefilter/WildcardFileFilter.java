package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

public class WildcardFileFilter extends AbstractFileFilter
{
  private IOCase caseSensitivity;
  private String[] wildcards;

  public WildcardFileFilter(String paramString)
  {
    this(paramString, null);
  }

  public WildcardFileFilter(String paramString, IOCase paramIOCase)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The wildcard must not be null");
    this.wildcards = new String[] { paramString };
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public WildcardFileFilter(List paramList)
  {
    this(paramList, null);
  }

  public WildcardFileFilter(List paramList, IOCase paramIOCase)
  {
    if (paramList == null)
      throw new IllegalArgumentException("The wildcard list must not be null");
    this.wildcards = ((String[])paramList.toArray(new String[paramList.size()]));
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public WildcardFileFilter(String[] paramArrayOfString)
  {
    this(paramArrayOfString, null);
  }

  public WildcardFileFilter(String[] paramArrayOfString, IOCase paramIOCase)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("The wildcard array must not be null");
    this.wildcards = paramArrayOfString;
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public boolean accept(File paramFile)
  {
    String str = paramFile.getName();
    for (int i = 0; i < this.wildcards.length; i++)
      if (FilenameUtils.wildcardMatch(str, this.wildcards[i], this.caseSensitivity))
        return true;
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    for (int i = 0; i < this.wildcards.length; i++)
      if (FilenameUtils.wildcardMatch(paramString, this.wildcards[i], this.caseSensitivity))
        return true;
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.WildcardFileFilter
 * JD-Core Version:    0.6.1
 */