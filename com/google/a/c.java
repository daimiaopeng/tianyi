package com.google.a;

import java.lang.reflect.Field;

public enum c
  implements d
{
  static
  {
    c[] arrayOfc = new c[5];
    arrayOfc[0] = a;
    arrayOfc[1] = b;
    arrayOfc[2] = c;
    arrayOfc[3] = d;
    arrayOfc[4] = e;
  }

  private static String a(char paramChar, String paramString, int paramInt)
  {
    String str;
    if (paramInt < paramString.length())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramChar);
      localStringBuilder.append(paramString.substring(paramInt));
      str = localStringBuilder.toString();
    }
    else
    {
      str = String.valueOf(paramChar);
    }
    return str;
  }

  private static String b(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    for (char c1 = paramString.charAt(0); (i < -1 + paramString.length()) && (!Character.isLetter(c1)); c1 = paramString.charAt(i))
    {
      localStringBuilder.append(c1);
      i++;
    }
    if (i == paramString.length())
      return localStringBuilder.toString();
    if (!Character.isUpperCase(c1))
    {
      localStringBuilder.append(a(Character.toUpperCase(c1), paramString, i + 1));
      return localStringBuilder.toString();
    }
    return paramString;
  }

  private static String b(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramString1.length(); i++)
    {
      char c1 = paramString1.charAt(i);
      if ((Character.isUpperCase(c1)) && (localStringBuilder.length() != 0))
        localStringBuilder.append(paramString2);
      localStringBuilder.append(c1);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.c
 * JD-Core Version:    0.6.1
 */