package org.apache.commons.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class FilenameUtils
{
  private static final char EXTENSION_SEPARATOR = '.';
  private static final char OTHER_SEPARATOR = '\000';
  private static final char SYSTEM_SEPARATOR = '\000';
  private static final char UNIX_SEPARATOR = '/';
  private static final char WINDOWS_SEPARATOR = '\\';

  static
  {
    if (isSystemWindows())
      OTHER_SEPARATOR = '/';
    else
      OTHER_SEPARATOR = '\\';
  }

  public static String concat(String paramString1, String paramString2)
  {
    int i = getPrefixLength(paramString2);
    if (i < 0)
      return null;
    if (i > 0)
      return normalize(paramString2);
    if (paramString1 == null)
      return null;
    int j = paramString1.length();
    if (j == 0)
      return normalize(paramString2);
    if (isSeparator(paramString1.charAt(j - 1)))
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(paramString1);
      localStringBuffer1.append(paramString2);
      return normalize(localStringBuffer1.toString());
    }
    StringBuffer localStringBuffer2 = new StringBuffer();
    localStringBuffer2.append(paramString1);
    localStringBuffer2.append('/');
    localStringBuffer2.append(paramString2);
    return normalize(localStringBuffer2.toString());
  }

  private static String doGetFullPath(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    int i = getPrefixLength(paramString);
    if (i < 0)
      return null;
    if (i >= paramString.length())
    {
      if (paramBoolean)
        return getPrefix(paramString);
      return paramString;
    }
    boolean bool = indexOfLastSeparator(paramString);
    if (bool)
      return paramString.substring(0, i);
    return paramString.substring(0, bool + paramBoolean);
  }

  private static String doGetPath(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    int i = getPrefixLength(paramString);
    if (i < 0)
      return null;
    int j = indexOfLastSeparator(paramString);
    if ((i < paramString.length()) && (j >= 0))
      return paramString.substring(i, j + paramInt);
    return "";
  }

  private static String doNormalize(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    int i = paramString.length();
    if (i == 0)
      return paramString;
    int j = getPrefixLength(paramString);
    if (j < 0)
      return null;
    char[] arrayOfChar = new char[i + 2];
    paramString.getChars(0, paramString.length(), arrayOfChar, 0);
    for (int k = 0; k < arrayOfChar.length; k++)
      if (arrayOfChar[k] == OTHER_SEPARATOR)
        arrayOfChar[k] = SYSTEM_SEPARATOR;
    int m;
    int n;
    if (arrayOfChar[(i - 1)] != SYSTEM_SEPARATOR)
    {
      m = i + 1;
      arrayOfChar[i] = SYSTEM_SEPARATOR;
      n = 0;
    }
    else
    {
      m = i;
      n = 1;
    }
    int i1 = j + 1;
    int i2 = m;
    for (int i3 = i1; i3 < i2; i3++)
      if (arrayOfChar[i3] == SYSTEM_SEPARATOR)
      {
        int i13 = i3 - 1;
        if (arrayOfChar[i13] == SYSTEM_SEPARATOR)
        {
          System.arraycopy(arrayOfChar, i3, arrayOfChar, i13, i2 - i3);
          i2--;
          i3--;
        }
      }
    for (int i4 = i1; i4 < i2; i4++)
      if (arrayOfChar[i4] == SYSTEM_SEPARATOR)
      {
        int i12 = i4 - 1;
        if ((arrayOfChar[i12] == '.') && ((i4 == i1) || (arrayOfChar[(i4 - 2)] == SYSTEM_SEPARATOR)))
        {
          if (i4 == i2 - 1)
            n = 1;
          System.arraycopy(arrayOfChar, i4 + 1, arrayOfChar, i12, i2 - i4);
          i2 -= 2;
          i4--;
        }
      }
    int i5 = j + 2;
    int i6 = n;
    int i8;
    label502: for (int i7 = i5; i7 < i2; i7 = i8 + 1)
      if ((arrayOfChar[i7] == SYSTEM_SEPARATOR) && (arrayOfChar[(i7 - 1)] == '.') && (arrayOfChar[(i7 - 2)] == '.') && ((i7 == i5) || (arrayOfChar[(i7 - 3)] == SYSTEM_SEPARATOR)))
      {
        if (i7 == i5)
          return null;
        if (i7 == i2 - 1)
          i6 = 1;
        for (int i9 = i7 - 4; i9 >= j; i9--)
          if (arrayOfChar[i9] == SYSTEM_SEPARATOR)
          {
            int i11 = i7 + 1;
            i8 = i9 + 1;
            System.arraycopy(arrayOfChar, i11, arrayOfChar, i8, i2 - i7);
            i2 -= i7 - i9;
            break label502;
          }
        int i10 = i7 + 1;
        System.arraycopy(arrayOfChar, i10, arrayOfChar, j, i2 - i7);
        i2 -= i10 - j;
        i8 = i1;
      }
      else
      {
        i8 = i7;
      }
    if (i2 <= 0)
      return "";
    if (i2 <= j)
      return new String(arrayOfChar, 0, i2);
    if ((i6 != 0) && (paramBoolean))
      return new String(arrayOfChar, 0, i2);
    return new String(arrayOfChar, 0, i2 - 1);
  }

  public static boolean equals(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, false, IOCase.SENSITIVE);
  }

  public static boolean equals(String paramString1, String paramString2, boolean paramBoolean, IOCase paramIOCase)
  {
    if ((paramString1 != null) && (paramString2 != null))
    {
      if (paramBoolean)
      {
        paramString1 = normalize(paramString1);
        paramString2 = normalize(paramString2);
      }
      if (paramIOCase == null)
        paramIOCase = IOCase.SENSITIVE;
      return paramIOCase.checkEquals(paramString1, paramString2);
    }
    boolean bool;
    if (paramString1 == paramString2)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static boolean equalsNormalized(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, true, IOCase.SENSITIVE);
  }

  public static boolean equalsNormalizedOnSystem(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, true, IOCase.SYSTEM);
  }

  public static boolean equalsOnSystem(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, false, IOCase.SYSTEM);
  }

  public static String getBaseName(String paramString)
  {
    return removeExtension(getName(paramString));
  }

  public static String getExtension(String paramString)
  {
    if (paramString == null)
      return null;
    int i = indexOfExtension(paramString);
    if (i == -1)
      return "";
    return paramString.substring(i + 1);
  }

  public static String getFullPath(String paramString)
  {
    return doGetFullPath(paramString, true);
  }

  public static String getFullPathNoEndSeparator(String paramString)
  {
    return doGetFullPath(paramString, false);
  }

  public static String getName(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.substring(1 + indexOfLastSeparator(paramString));
  }

  public static String getPath(String paramString)
  {
    return doGetPath(paramString, 1);
  }

  public static String getPathNoEndSeparator(String paramString)
  {
    return doGetPath(paramString, 0);
  }

  public static String getPrefix(String paramString)
  {
    if (paramString == null)
      return null;
    int i = getPrefixLength(paramString);
    if (i < 0)
      return null;
    if (i > paramString.length())
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(paramString);
      localStringBuffer.append('/');
      return localStringBuffer.toString();
    }
    return paramString.substring(0, i);
  }

  public static int getPrefixLength(String paramString)
  {
    if (paramString == null)
      return -1;
    int i = paramString.length();
    if (i == 0)
      return 0;
    char c1 = paramString.charAt(0);
    if (c1 == ':')
      return -1;
    if (i == 1)
    {
      if (c1 == '~')
        return 2;
      return isSeparator(c1);
    }
    if (c1 == '~')
    {
      int n = paramString.indexOf('/', 1);
      int i1 = paramString.indexOf('\\', 1);
      if ((n == -1) && (i1 == -1))
        return i + 1;
      if (n == -1)
        n = i1;
      if (i1 == -1)
        i1 = n;
      return 1 + Math.min(n, i1);
    }
    char c2 = paramString.charAt(1);
    if (c2 == ':')
    {
      int m = Character.toUpperCase(c1);
      if ((m >= 65) && (m <= 90))
      {
        if ((i != 2) && (isSeparator(paramString.charAt(2))))
          return 3;
        return 2;
      }
      return -1;
    }
    if ((isSeparator(c1)) && (isSeparator(c2)))
    {
      int j = paramString.indexOf('/', 2);
      int k = paramString.indexOf('\\', 2);
      if (((j != -1) || (k != -1)) && (j != 2) && (k != 2))
      {
        if (j == -1)
          j = k;
        if (k == -1)
          k = j;
        return 1 + Math.min(j, k);
      }
      return -1;
    }
    return isSeparator(c1);
  }

  public static int indexOfExtension(String paramString)
  {
    int i = -1;
    if (paramString == null)
      return i;
    int j = paramString.lastIndexOf('.');
    if (indexOfLastSeparator(paramString) <= j)
      i = j;
    return i;
  }

  public static int indexOfLastSeparator(String paramString)
  {
    if (paramString == null)
      return -1;
    return Math.max(paramString.lastIndexOf('/'), paramString.lastIndexOf('\\'));
  }

  public static boolean isExtension(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return false;
    if ((paramString2 != null) && (paramString2.length() != 0))
      return getExtension(paramString1).equals(paramString2);
    int i = indexOfExtension(paramString1);
    boolean bool = false;
    if (i == -1)
      bool = true;
    return bool;
  }

  public static boolean isExtension(String paramString, Collection paramCollection)
  {
    if (paramString == null)
      return false;
    if ((paramCollection != null) && (!paramCollection.isEmpty()))
    {
      String str = getExtension(paramString);
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        if (str.equals(localIterator.next()))
          return true;
      return false;
    }
    int i = indexOfExtension(paramString);
    boolean bool = false;
    if (i == -1)
      bool = true;
    return bool;
  }

  public static boolean isExtension(String paramString, String[] paramArrayOfString)
  {
    if (paramString == null)
      return false;
    if ((paramArrayOfString != null) && (paramArrayOfString.length != 0))
    {
      String str = getExtension(paramString);
      for (int j = 0; j < paramArrayOfString.length; j++)
        if (str.equals(paramArrayOfString[j]))
          return true;
      return false;
    }
    int i = indexOfExtension(paramString);
    boolean bool = false;
    if (i == -1)
      bool = true;
    return bool;
  }

  private static boolean isSeparator(char paramChar)
  {
    boolean bool;
    if ((paramChar != '/') && (paramChar != '\\'))
      bool = false;
    else
      bool = true;
    return bool;
  }

  static boolean isSystemWindows()
  {
    boolean bool;
    if (SYSTEM_SEPARATOR == '\\')
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static String normalize(String paramString)
  {
    return doNormalize(paramString, true);
  }

  public static String normalizeNoEndSeparator(String paramString)
  {
    return doNormalize(paramString, false);
  }

  public static String removeExtension(String paramString)
  {
    if (paramString == null)
      return null;
    int i = indexOfExtension(paramString);
    if (i == -1)
      return paramString;
    return paramString.substring(0, i);
  }

  public static String separatorsToSystem(String paramString)
  {
    if (paramString == null)
      return null;
    if (isSystemWindows())
      return separatorsToWindows(paramString);
    return separatorsToUnix(paramString);
  }

  public static String separatorsToUnix(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf('\\') != -1))
      return paramString.replace('\\', '/');
    return paramString;
  }

  public static String separatorsToWindows(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf('/') != -1))
      return paramString.replace('/', '\\');
    return paramString;
  }

  static String[] splitOnTokens(String paramString)
  {
    if ((paramString.indexOf("?") == -1) && (paramString.indexOf("*") == -1))
      return new String[] { paramString };
    char[] arrayOfChar = paramString.toCharArray();
    ArrayList localArrayList = new ArrayList();
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < arrayOfChar.length; i++)
      if ((arrayOfChar[i] != '?') && (arrayOfChar[i] != '*'))
      {
        localStringBuffer.append(arrayOfChar[i]);
      }
      else
      {
        if (localStringBuffer.length() != 0)
        {
          localArrayList.add(localStringBuffer.toString());
          localStringBuffer.setLength(0);
        }
        if (arrayOfChar[i] == '?')
          localArrayList.add("?");
        else if ((localArrayList.size() == 0) || ((i > 0) && (!localArrayList.get(localArrayList.size() - 1).equals("*"))))
          localArrayList.add("*");
      }
    if (localStringBuffer.length() != 0)
      localArrayList.add(localStringBuffer.toString());
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }

  public static boolean wildcardMatch(String paramString1, String paramString2)
  {
    return wildcardMatch(paramString1, paramString2, IOCase.SENSITIVE);
  }

  public static boolean wildcardMatch(String paramString1, String paramString2, IOCase paramIOCase)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return true;
    if ((paramString1 != null) && (paramString2 != null))
    {
      if (paramIOCase == null)
        paramIOCase = IOCase.SENSITIVE;
      String str = paramIOCase.convertCase(paramString1);
      String[] arrayOfString = splitOnTokens(paramIOCase.convertCase(paramString2));
      Stack localStack = new Stack();
      int i = 0;
      int j = 0;
      int k = 0;
      label269: 
      do
      {
        if (localStack.size() > 0)
        {
          int[] arrayOfInt = (int[])localStack.pop();
          j = arrayOfInt[0];
          i = arrayOfInt[1];
          k = 1;
        }
        while (j < arrayOfString.length)
        {
          if (arrayOfString[j].equals("?"))
            i++;
          while (true)
          {
            k = 0;
            break;
            if (arrayOfString[j].equals("*"))
            {
              if (j == arrayOfString.length - 1)
                i = str.length();
              k = 1;
              break;
            }
            if (k != 0)
            {
              i = str.indexOf(arrayOfString[j], i);
              if (i == -1)
                break label269;
              int m = str.indexOf(arrayOfString[j], i + 1);
              if (m >= 0)
                localStack.push(new int[] { j, m });
            }
            else
            {
              if (!str.startsWith(arrayOfString[j], i))
                break label269;
            }
            i += arrayOfString[j].length();
          }
          j++;
        }
        if ((j == arrayOfString.length) && (i == str.length()))
          return true;
      }
      while (localStack.size() > 0);
      return false;
    }
    return false;
  }

  public static boolean wildcardMatchOnSystem(String paramString1, String paramString2)
  {
    return wildcardMatch(paramString1, paramString2, IOCase.SYSTEM);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FilenameUtils
 * JD-Core Version:    0.6.1
 */