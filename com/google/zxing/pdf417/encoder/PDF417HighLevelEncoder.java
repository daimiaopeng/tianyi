package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import java.math.BigInteger;
import java.util.Arrays;

final class PDF417HighLevelEncoder
{
  private static final int BYTE_COMPACTION = 1;
  private static final int LATCH_TO_BYTE = 924;
  private static final int LATCH_TO_BYTE_PADDED = 901;
  private static final int LATCH_TO_NUMERIC = 902;
  private static final int LATCH_TO_TEXT = 900;
  private static final byte[] MIXED;
  private static final int NUMERIC_COMPACTION = 2;
  private static final byte[] PUNCTUATION;
  private static final int SHIFT_TO_BYTE = 913;
  private static final int SUBMODE_ALPHA = 0;
  private static final int SUBMODE_LOWER = 1;
  private static final int SUBMODE_MIXED = 2;
  private static final int SUBMODE_PUNCTUATION = 3;
  private static final int TEXT_COMPACTION;
  private static final byte[] TEXT_MIXED_RAW = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0 };
  private static final byte[] TEXT_PUNCTUATION_RAW = { 59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0 };

  static
  {
    MIXED = new byte[''];
    PUNCTUATION = new byte[''];
    Arrays.fill(MIXED, (byte)-1);
    int i = 0;
    for (int j = 0; j < TEXT_MIXED_RAW.length; j = (byte)(j + 1))
    {
      int m = TEXT_MIXED_RAW[j];
      if (m > 0)
        MIXED[m] = j;
    }
    Arrays.fill(PUNCTUATION, (byte)-1);
    while (i < TEXT_PUNCTUATION_RAW.length)
    {
      int k = TEXT_PUNCTUATION_RAW[i];
      if (k > 0)
        PUNCTUATION[k] = i;
      i = (byte)(i + 1);
    }
  }

  private static int determineConsecutiveBinaryCount(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramCharSequence.length();
    for (int j = paramInt; j < i; j++)
    {
      char c1 = paramCharSequence.charAt(j);
      int k = 0;
      while ((k < 13) && (isDigit(c1)))
      {
        k++;
        int i1 = j + k;
        if (i1 >= i)
          break;
        c1 = paramCharSequence.charAt(i1);
      }
      int m = 0;
      if (k >= 13)
        return j - paramInt;
      while ((m < 5) && (isText(c1)))
      {
        m++;
        int n = j + m;
        if (n >= i)
          break;
        c1 = paramCharSequence.charAt(n);
      }
      if (m >= 5)
        return j - paramInt;
      char c2 = paramCharSequence.charAt(j);
      if ((paramArrayOfByte[j] == 63) && (c2 != '?'))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Non-encodable character detected: ");
        localStringBuilder.append(c2);
        localStringBuilder.append(" (Unicode: ");
        localStringBuilder.append(c2);
        localStringBuilder.append(')');
        throw new WriterException(localStringBuilder.toString());
      }
    }
    return j - paramInt;
  }

  private static int determineConsecutiveDigitCount(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    int j = 0;
    if (paramInt < i)
    {
      char c = paramCharSequence.charAt(paramInt);
      while ((isDigit(c)) && (paramInt < i))
      {
        j++;
        paramInt++;
        if (paramInt < i)
          c = paramCharSequence.charAt(paramInt);
      }
    }
    return j;
  }

  private static int determineConsecutiveTextCount(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    int j = paramInt;
    while (j < i)
    {
      char c = paramCharSequence.charAt(j);
      int k = 0;
      while ((k < 13) && (isDigit(c)) && (j < i))
      {
        k++;
        j++;
        if (j < i)
          c = paramCharSequence.charAt(j);
      }
      if (k >= 13)
        return j - paramInt - k;
      if (k <= 0)
      {
        if (!isText(paramCharSequence.charAt(j)))
          break;
        j++;
      }
    }
    return j - paramInt;
  }

  private static void encodeBinary(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, StringBuilder paramStringBuilder)
  {
    if ((paramInt2 == 1) && (paramInt3 == 0))
      paramStringBuilder.append('Α');
    if (paramInt2 >= 6)
    {
      paramStringBuilder.append('Μ');
      char[] arrayOfChar = new char[5];
      for (i = paramInt1; paramInt1 + paramInt2 - i >= 6; i += 6)
      {
        long l1 = 0L;
        int k = 0;
        int m;
        while (true)
        {
          m = 0;
          if (k >= 6)
            break;
          long l2 = (l1 << 8) + 0xFF & paramArrayOfByte[(i + k)];
          k++;
          l1 = l2;
        }
        while (m < 5)
        {
          arrayOfChar[m] = (char)(int)(l1 % 900L);
          l1 /= 900L;
          m++;
        }
        for (int n = arrayOfChar.length - 1; n >= 0; n--)
          paramStringBuilder.append(arrayOfChar[n]);
      }
    }
    int i = paramInt1;
    int j = paramInt1 + paramInt2;
    if (i < j)
      paramStringBuilder.append('΅');
    while (i < j)
    {
      paramStringBuilder.append((char)(0xFF & paramArrayOfByte[i]));
      i++;
    }
  }

  static String encodeHighLevel(String paramString, Compaction paramCompaction)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int i = paramString.length();
    if (paramCompaction == Compaction.TEXT)
    {
      encodeText(paramString, 0, i, localStringBuilder, 0);
    }
    else if (paramCompaction == Compaction.BYTE)
    {
      byte[] arrayOfByte2 = getBytesForMessage(paramString);
      encodeBinary(arrayOfByte2, 0, arrayOfByte2.length, 1, localStringBuilder);
    }
    else if (paramCompaction == Compaction.NUMERIC)
    {
      localStringBuilder.append('Ά');
      encodeNumeric(paramString, 0, i, localStringBuilder);
    }
    else
    {
      byte[] arrayOfByte1 = null;
      int j = 0;
      int k = 0;
      int m = 0;
      while (true)
      {
        if (j >= i)
          break label297;
        int n = determineConsecutiveDigitCount(paramString, j);
        if (n >= 13)
        {
          localStringBuilder.append('Ά');
          k = 2;
          encodeNumeric(paramString, j, n, localStringBuilder);
          j += n;
          break;
        }
        int i1 = determineConsecutiveTextCount(paramString, j);
        if ((i1 < 5) && (n != i))
        {
          if (arrayOfByte1 == null)
            arrayOfByte1 = getBytesForMessage(paramString);
          int i2 = determineConsecutiveBinaryCount(paramString, arrayOfByte1, j);
          if (i2 == 0)
            i2 = 1;
          if ((i2 == 1) && (k == 0))
          {
            encodeBinary(arrayOfByte1, j, 1, 0, localStringBuilder);
          }
          else
          {
            encodeBinary(arrayOfByte1, j, i2, k, localStringBuilder);
            k = 1;
            m = 0;
          }
          j += i2;
        }
        else
        {
          if (k != 0)
          {
            localStringBuilder.append('΄');
            k = 0;
            m = 0;
          }
          m = encodeText(paramString, j, i1, localStringBuilder, m);
          j += i1;
        }
      }
    }
    label297: return localStringBuilder.toString();
  }

  private static void encodeNumeric(String paramString, int paramInt1, int paramInt2, StringBuilder paramStringBuilder)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(1 + paramInt2 / 3);
    BigInteger localBigInteger1 = BigInteger.valueOf(900L);
    BigInteger localBigInteger2 = BigInteger.valueOf(0L);
    int i = 0;
    while (i < paramInt2 - 1)
    {
      localStringBuilder1.setLength(0);
      int j = Math.min(44, paramInt2 - i);
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append('1');
      int k = paramInt1 + i;
      localStringBuilder2.append(paramString.substring(k, k + j));
      BigInteger localBigInteger3 = new BigInteger(localStringBuilder2.toString());
      do
      {
        localStringBuilder1.append((char)localBigInteger3.mod(localBigInteger1).intValue());
        localBigInteger3 = localBigInteger3.divide(localBigInteger1);
      }
      while (!localBigInteger3.equals(localBigInteger2));
      for (int m = -1 + localStringBuilder1.length(); m >= 0; m--)
        paramStringBuilder.append(localStringBuilder1.charAt(m));
      i += j;
    }
  }

  private static int encodeText(CharSequence paramCharSequence, int paramInt1, int paramInt2, StringBuilder paramStringBuilder, int paramInt3)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInt2);
    int i = paramInt3;
    int j = 0;
    while (true)
    {
      int k = paramInt1 + j;
      char c1 = paramCharSequence.charAt(k);
      switch (i)
      {
      default:
        if (isPunctuation(c1))
          localStringBuilder.append((char)PUNCTUATION[c1]);
        break;
      case 2:
        if (isMixed(c1))
        {
          localStringBuilder.append((char)MIXED[c1]);
        }
        else
        {
          if (isAlphaUpper(c1))
          {
            localStringBuilder.append('\034');
            break label554;
          }
          if (isAlphaLower(c1))
          {
            localStringBuilder.append('\033');
          }
          else
          {
            int i2 = k + 1;
            if ((i2 < paramInt2) && (isPunctuation(paramCharSequence.charAt(i2))))
            {
              i = 3;
              localStringBuilder.append('\031');
              continue;
            }
            localStringBuilder.append('\035');
            localStringBuilder.append((char)PUNCTUATION[c1]);
          }
        }
        break;
      case 1:
        if (isAlphaLower(c1))
        {
          if (c1 == ' ')
            localStringBuilder.append('\032');
          else
            localStringBuilder.append((char)(c1 - 'a'));
        }
        else if (isAlphaUpper(c1))
        {
          localStringBuilder.append('\033');
          localStringBuilder.append((char)(c1 - 'A'));
        }
        else if (isMixed(c1))
        {
          localStringBuilder.append('\034');
        }
        else
        {
          localStringBuilder.append('\035');
          localStringBuilder.append((char)PUNCTUATION[c1]);
        }
        break;
      case 0:
        if (isAlphaUpper(c1))
        {
          if (c1 == ' ')
            localStringBuilder.append('\032');
          else
            localStringBuilder.append((char)(c1 - 'A'));
        }
        else
        {
          if (isAlphaLower(c1))
          {
            localStringBuilder.append('\033');
            i = 1;
            continue;
          }
          if (isMixed(c1))
          {
            localStringBuilder.append('\034');
            i = 2;
            continue;
          }
          localStringBuilder.append('\035');
          localStringBuilder.append((char)PUNCTUATION[c1]);
        }
        j++;
        if (j >= paramInt2)
        {
          int m = localStringBuilder.length();
          int n = 0;
          char c2 = '\000';
          while (n < m)
          {
            int i1;
            if (n % 2 != 0)
              i1 = 1;
            else
              i1 = 0;
            if (i1 != 0)
            {
              c2 = (char)(c2 * '\036' + localStringBuilder.charAt(n));
              paramStringBuilder.append(c2);
            }
            else
            {
              c2 = localStringBuilder.charAt(n);
            }
            n++;
          }
          if (m % 2 != 0)
            paramStringBuilder.append((char)(29 + c2 * '\036'));
          return i;
          localStringBuilder.append('\035');
          label554: i = 0;
        }
        break;
      }
    }
  }

  private static byte[] getBytesForMessage(String paramString)
  {
    return paramString.getBytes();
  }

  private static boolean isAlphaLower(char paramChar)
  {
    boolean bool;
    if ((paramChar != ' ') && ((paramChar < 'a') || (paramChar > 'z')))
      bool = false;
    else
      bool = true;
    return bool;
  }

  private static boolean isAlphaUpper(char paramChar)
  {
    boolean bool;
    if ((paramChar != ' ') && ((paramChar < 'A') || (paramChar > 'Z')))
      bool = false;
    else
      bool = true;
    return bool;
  }

  private static boolean isDigit(char paramChar)
  {
    boolean bool;
    if ((paramChar >= '0') && (paramChar <= '9'))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static boolean isMixed(char paramChar)
  {
    boolean bool;
    if (MIXED[paramChar] != -1)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static boolean isPunctuation(char paramChar)
  {
    boolean bool;
    if (PUNCTUATION[paramChar] != -1)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static boolean isText(char paramChar)
  {
    boolean bool;
    if ((paramChar != '\t') && (paramChar != '\n') && (paramChar != '\r') && ((paramChar < ' ') || (paramChar > '~')))
      bool = false;
    else
      bool = true;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder
 * JD-Core Version:    0.6.1
 */