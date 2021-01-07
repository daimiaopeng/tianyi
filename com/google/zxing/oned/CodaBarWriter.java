package com.google.zxing.oned;

import java.util.Arrays;

public final class CodaBarWriter extends OneDimensionalCodeWriter
{
  private static final char[] END_CHARS = { 84, 78, 42, 69 };
  private static final char[] START_CHARS = { 65, 66, 67, 68 };

  public boolean[] encode(String paramString)
  {
    if (!CodaBarReader.arrayContains(START_CHARS, Character.toUpperCase(paramString.charAt(0))))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Codabar should start with one of the following: ");
      localStringBuilder1.append(Arrays.toString(START_CHARS));
      throw new IllegalArgumentException(localStringBuilder1.toString());
    }
    if (!CodaBarReader.arrayContains(END_CHARS, Character.toUpperCase(paramString.charAt(paramString.length() - 1))))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Codabar should end with one of the following: ");
      localStringBuilder2.append(Arrays.toString(END_CHARS));
      throw new IllegalArgumentException(localStringBuilder2.toString());
    }
    char[] arrayOfChar = { 47, 58, 43, 46 };
    int i = 1;
    int j = 20;
    while (i < paramString.length() - 1)
    {
      if ((!Character.isDigit(paramString.charAt(i))) && (paramString.charAt(i) != '-') && (paramString.charAt(i) != '$'))
      {
        if (CodaBarReader.arrayContains(arrayOfChar, paramString.charAt(i)))
        {
          j += 10;
        }
        else
        {
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("Cannot encode : '");
          localStringBuilder3.append(paramString.charAt(i));
          localStringBuilder3.append('\'');
          throw new IllegalArgumentException(localStringBuilder3.toString());
        }
      }
      else
        j += 9;
      i++;
    }
    boolean[] arrayOfBoolean = new boolean[j + (paramString.length() - 1)];
    int k = 0;
    int m = 0;
    while (k < paramString.length())
    {
      int n = Character.toUpperCase(paramString.charAt(k));
      if (k == paramString.length() - 1)
        if (n != 42)
        {
          if (n != 69)
          {
            if (n != 78)
            {
              if (n == 84)
                n = 65;
            }
            else
              n = 66;
          }
          else
            n = 68;
        }
        else
          n = 67;
      for (int i1 = 0; i1 < CodaBarReader.ALPHABET.length; i1++)
        if (n == CodaBarReader.ALPHABET[i1])
        {
          i2 = CodaBarReader.CHARACTER_ENCODINGS[i1];
          break label427;
        }
      int i2 = 0;
      label427: int i3 = m;
      int i4 = 0;
      int i5 = 1;
      while (true)
      {
        for (int i6 = 0; ; i6++)
        {
          if (i4 >= 7)
            break label497;
          arrayOfBoolean[i3] = i5;
          i3++;
          if (((0x1 & i2 >> 6 - i4) == 0) || (i6 == 1))
            break;
        }
        i5 ^= 1;
        i4++;
      }
      label497: if (k < paramString.length() - 1)
      {
        arrayOfBoolean[i3] = false;
        i3++;
      }
      m = i3;
      k++;
    }
    return arrayOfBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.CodaBarWriter
 * JD-Core Version:    0.6.1
 */