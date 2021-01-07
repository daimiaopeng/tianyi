package org.jsoup.helper;

import java.util.Collection;
import java.util.Iterator;

public final class StringUtil
{
  private static final String[] padding = { "", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          " };

  public static void appendNormalisedWhitespace(StringBuilder paramStringBuilder, String paramString, boolean paramBoolean)
  {
    int i = paramString.length();
    int j = 0;
    int k = 0;
    int m = 0;
    while (j < i)
    {
      int n = paramString.codePointAt(j);
      if (isWhitespace(n))
      {
        if (((!paramBoolean) || (k != 0)) && (m == 0))
        {
          paramStringBuilder.append(' ');
          m = 1;
        }
      }
      else
      {
        paramStringBuilder.appendCodePoint(n);
        k = 1;
        m = 0;
      }
      j += Character.charCount(n);
    }
  }

  public static boolean in(String paramString, String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      if (paramArrayOfString[j].equals(paramString))
        return true;
    return false;
  }

  public static boolean isBlank(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      int i = paramString.length();
      for (int j = 0; j < i; j++)
        if (!isWhitespace(paramString.codePointAt(j)))
          return false;
      return true;
    }
    return true;
  }

  public static boolean isNumeric(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      int i = paramString.length();
      for (int j = 0; j < i; j++)
        if (!Character.isDigit(paramString.codePointAt(j)))
          return false;
      return true;
    }
    return false;
  }

  public static boolean isWhitespace(int paramInt)
  {
    boolean bool;
    if ((paramInt != 32) && (paramInt != 9) && (paramInt != 10) && (paramInt != 12) && (paramInt != 13))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public static String join(Collection paramCollection, String paramString)
  {
    return join(paramCollection.iterator(), paramString);
  }

  public static String join(Iterator paramIterator, String paramString)
  {
    if (!paramIterator.hasNext())
      return "";
    String str = paramIterator.next().toString();
    if (!paramIterator.hasNext())
      return str;
    StringBuilder localStringBuilder = new StringBuilder(64);
    localStringBuilder.append(str);
    while (paramIterator.hasNext())
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(paramIterator.next());
    }
    return localStringBuilder.toString();
  }

  public static String normaliseWhitespace(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    appendNormalisedWhitespace(localStringBuilder, paramString, false);
    return localStringBuilder.toString();
  }

  public static String padding(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("width must be > 0");
    if (paramInt < padding.length)
      return padding[paramInt];
    char[] arrayOfChar = new char[paramInt];
    for (int i = 0; i < paramInt; i++)
      arrayOfChar[i] = ' ';
    return String.valueOf(arrayOfChar);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.helper.StringUtil
 * JD-Core Version:    0.6.1
 */