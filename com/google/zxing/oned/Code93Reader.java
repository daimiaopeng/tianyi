package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public final class Code93Reader extends OneDReader
{
  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
  private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
  private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
  private static final int[] CHARACTER_ENCODINGS = { 276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350 };
  private final int[] counters = new int[6];
  private final StringBuilder decodeRowResult = new StringBuilder(20);

  private static void checkChecksums(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    checkOneChecksum(paramCharSequence, i - 2, 20);
    checkOneChecksum(paramCharSequence, i - 1, 15);
  }

  private static void checkOneChecksum(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt1 - 1;
    int j = 0;
    int k = 1;
    while (i >= 0)
    {
      j += k * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(paramCharSequence.charAt(i));
      k++;
      if (k > paramInt2)
        k = 1;
      i--;
    }
    if (paramCharSequence.charAt(paramInt1) != ALPHABET[(j % 47)])
      throw ChecksumException.getChecksumInstance();
  }

  private static String decodeExtended(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    for (int j = 0; j < i; j++)
    {
      char c1 = paramCharSequence.charAt(j);
      if ((c1 >= 'a') && (c1 <= 'd'))
      {
        if (j >= i - 1)
          throw FormatException.getFormatInstance();
        j++;
        int k = paramCharSequence.charAt(j);
        char c2;
        switch (c1)
        {
        default:
          c2 = '\000';
          break;
        case 'd':
          if ((k >= 65) && (k <= 90))
            c2 = (char)(k + 32);
          else
            throw FormatException.getFormatInstance();
          break;
        case 'c':
          if ((k >= 65) && (k <= 79))
            c2 = (char)(k - 32);
          else if (k == 90)
            c2 = ':';
          else
            throw FormatException.getFormatInstance();
          break;
        case 'b':
          if ((k >= 65) && (k <= 69))
            c2 = (char)(k - 38);
          else if ((k >= 70) && (k <= 87))
            c2 = (char)(k - 11);
          else
            throw FormatException.getFormatInstance();
          break;
        case 'a':
          if ((k >= 65) && (k <= 90))
            c2 = (char)(k - 64);
          else
            throw FormatException.getFormatInstance();
          break;
        }
        localStringBuilder.append(c2);
      }
      else
      {
        localStringBuilder.append(c1);
      }
    }
    return localStringBuilder.toString();
  }

  private int[] findAsteriskPattern(BitArray paramBitArray)
  {
    int i = paramBitArray.getSize();
    int j = paramBitArray.getNextSet(0);
    Arrays.fill(this.counters, 0);
    int[] arrayOfInt = this.counters;
    int k = arrayOfInt.length;
    int m = j;
    int n = 0;
    int i1 = 0;
    while (j < i)
    {
      if ((n ^ paramBitArray.get(j)) != 0)
      {
        arrayOfInt[i1] = (1 + arrayOfInt[i1]);
      }
      else
      {
        int i2 = k - 1;
        if (i1 == i2)
        {
          if (toPattern(arrayOfInt) == ASTERISK_ENCODING)
            return new int[] { m, j };
          m += arrayOfInt[0] + arrayOfInt[1];
          int i3 = k - 2;
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, i3);
          arrayOfInt[i3] = 0;
          arrayOfInt[i2] = 0;
          i1--;
        }
        else
        {
          i1++;
        }
        arrayOfInt[i1] = 1;
        n ^= 1;
      }
      j++;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static char patternToChar(int paramInt)
  {
    for (int i = 0; i < CHARACTER_ENCODINGS.length; i++)
      if (CHARACTER_ENCODINGS[i] == paramInt)
        return ALPHABET[i];
    throw NotFoundException.getNotFoundInstance();
  }

  private static int toPattern(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int j = paramArrayOfInt.length;
    int k = 0;
    int m = 0;
    while (k < j)
    {
      m += paramArrayOfInt[k];
      k++;
    }
    int n = 0;
    int i1 = 0;
    while (n < i)
    {
      int i2 = 9 * (paramArrayOfInt[n] << 8) / m;
      int i3 = i2 >> 8;
      if ((i2 & 0xFF) > 127)
        i3++;
      if ((i3 >= 1) && (i3 <= 4))
      {
        if ((n & 0x1) == 0)
        {
          int i4 = i1;
          for (int i5 = 0; i5 < i3; i5++)
            i4 = 0x1 | i4 << 1;
          i1 = i4;
        }
        else
        {
          i1 <<= i3;
        }
        n++;
      }
      else
      {
        return -1;
      }
    }
    return i1;
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    int[] arrayOfInt1 = findAsteriskPattern(paramBitArray);
    int i = paramBitArray.getNextSet(arrayOfInt1[1]);
    int j = paramBitArray.getSize();
    int[] arrayOfInt2 = this.counters;
    Arrays.fill(arrayOfInt2, 0);
    StringBuilder localStringBuilder = this.decodeRowResult;
    localStringBuilder.setLength(0);
    while (true)
    {
      recordPattern(paramBitArray, i, arrayOfInt2);
      int k = toPattern(arrayOfInt2);
      if (k < 0)
        throw NotFoundException.getNotFoundInstance();
      char c = patternToChar(k);
      localStringBuilder.append(c);
      int m = arrayOfInt2.length;
      int n = i;
      for (int i1 = 0; i1 < m; i1++)
        n += arrayOfInt2[i1];
      int i2 = paramBitArray.getNextSet(n);
      if (c == '*')
      {
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        if ((i2 != j) && (paramBitArray.get(i2)))
        {
          if (localStringBuilder.length() < 2)
            throw NotFoundException.getNotFoundInstance();
          checkChecksums(localStringBuilder);
          localStringBuilder.setLength(localStringBuilder.length() - 2);
          String str = decodeExtended(localStringBuilder);
          float f1 = arrayOfInt1[1] + arrayOfInt1[0] / 2.0F;
          float f2 = i2 + i / 2.0F;
          ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
          float f3 = paramInt;
          arrayOfResultPoint[0] = new ResultPoint(f1, f3);
          arrayOfResultPoint[1] = new ResultPoint(f2, f3);
          return new Result(str, null, arrayOfResultPoint, BarcodeFormat.CODE_93);
        }
        throw NotFoundException.getNotFoundInstance();
      }
      i = i2;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code93Reader
 * JD-Core Version:    0.6.1
 */