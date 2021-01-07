package org.apache.commons.io;

import java.io.Serializable;

public final class IOCase
  implements Serializable
{
  public static final IOCase INSENSITIVE = new IOCase("Insensitive", false);
  public static final IOCase SENSITIVE = new IOCase("Sensitive", true);
  public static final IOCase SYSTEM = new IOCase("System", true ^ FilenameUtils.isSystemWindows());
  private static final long serialVersionUID = -6343169151696340687L;
  private final String name;
  private final transient boolean sensitive;

  private IOCase(String paramString, boolean paramBoolean)
  {
    this.name = paramString;
    this.sensitive = paramBoolean;
  }

  public static IOCase forName(String paramString)
  {
    if (SENSITIVE.name.equals(paramString))
      return SENSITIVE;
    if (INSENSITIVE.name.equals(paramString))
      return INSENSITIVE;
    if (SYSTEM.name.equals(paramString))
      return SYSTEM;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Invalid IOCase name: ");
    localStringBuffer.append(paramString);
    throw new IllegalArgumentException(localStringBuffer.toString());
  }

  private Object readResolve()
  {
    return forName(this.name);
  }

  public boolean checkEndsWith(String paramString1, String paramString2)
  {
    int i = paramString2.length();
    return paramString1.regionMatches(true ^ this.sensitive, paramString1.length() - i, paramString2, 0, i);
  }

  public boolean checkEquals(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
    {
      boolean bool;
      if (this.sensitive)
        bool = paramString1.equals(paramString2);
      else
        bool = paramString1.equalsIgnoreCase(paramString2);
      return bool;
    }
    throw new NullPointerException("The strings must not be null");
  }

  public boolean checkRegionMatches(String paramString1, int paramInt, String paramString2)
  {
    return paramString1.regionMatches(true ^ this.sensitive, paramInt, paramString2, 0, paramString2.length());
  }

  public boolean checkStartsWith(String paramString1, String paramString2)
  {
    return paramString1.regionMatches(true ^ this.sensitive, 0, paramString2, 0, paramString2.length());
  }

  String convertCase(String paramString)
  {
    if (paramString == null)
      return null;
    if (!this.sensitive)
      paramString = paramString.toLowerCase();
    return paramString;
  }

  public String getName()
  {
    return this.name;
  }

  public boolean isCaseSensitive()
  {
    return this.sensitive;
  }

  public String toString()
  {
    return this.name;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.IOCase
 * JD-Core Version:    0.6.1
 */