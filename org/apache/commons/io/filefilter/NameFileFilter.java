package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.io.IOCase;

public class NameFileFilter extends AbstractFileFilter
{
  private IOCase caseSensitivity;
  private String[] names;

  public NameFileFilter(String paramString)
  {
    this(paramString, null);
  }

  public NameFileFilter(String paramString, IOCase paramIOCase)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The wildcard must not be null");
    this.names = new String[] { paramString };
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public NameFileFilter(List paramList)
  {
    this(paramList, null);
  }

  public NameFileFilter(List paramList, IOCase paramIOCase)
  {
    if (paramList == null)
      throw new IllegalArgumentException("The list of names must not be null");
    this.names = ((String[])paramList.toArray(new String[paramList.size()]));
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public NameFileFilter(String[] paramArrayOfString)
  {
    this(paramArrayOfString, null);
  }

  public NameFileFilter(String[] paramArrayOfString, IOCase paramIOCase)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("The array of names must not be null");
    this.names = paramArrayOfString;
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public boolean accept(File paramFile)
  {
    String str = paramFile.getName();
    for (int i = 0; i < this.names.length; i++)
      if (this.caseSensitivity.checkEquals(str, this.names[i]))
        return true;
    return false;
  }

  public boolean accept(File paramFile, String paramString)
  {
    for (int i = 0; i < this.names.length; i++)
      if (this.caseSensitivity.checkEquals(paramString, this.names[i]))
        return true;
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.NameFileFilter
 * JD-Core Version:    0.6.1
 */