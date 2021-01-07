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

public final class Code39Reader extends OneDReader
{
  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
  static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
  private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
  static final int[] CHARACTER_ENCODINGS = { 52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42 };
  private final int[] counters;
  private final StringBuilder decodeRowResult;
  private final boolean extendedMode;
  private final boolean usingCheckDigit;

  public Code39Reader()
  {
    this(false);
  }

  public Code39Reader(boolean paramBoolean)
  {
    this(paramBoolean, false);
  }

  public Code39Reader(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.usingCheckDigit = paramBoolean1;
    this.extendedMode = paramBoolean2;
    this.decodeRowResult = new StringBuilder(20);
    this.counters = new int[9];
  }

  private static String decodeExtended(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    int j = 0;
    while (j < i)
    {
      char c1 = paramCharSequence.charAt(j);
      if ((c1 != '+') && (c1 != '$') && (c1 != '%') && (c1 != '/'))
      {
        localStringBuilder.append(c1);
      }
      else
      {
        j++;
        int k = paramCharSequence.charAt(j);
        char c2;
        if (c1 != '+')
        {
          if (c1 != '/')
            switch (c1)
            {
            default:
              c2 = '\000';
              break;
            case '%':
              if ((k >= 65) && (k <= 69))
              {
                c2 = (char)(k - 38);
                break;
              }
              if ((k >= 70) && (k <= 87))
              {
                c2 = (char)(k - 11);
                break;
              }
              throw FormatException.getFormatInstance();
            case '$':
              if ((k >= 65) && (k <= 90))
              {
                c2 = (char)(k - 64);
                break;
              }
              throw FormatException.getFormatInstance();
            }
          else if ((k >= 65) && (k <= 79))
            c2 = (char)(k - 32);
          else if (k == 90)
            c2 = ':';
          else
            throw FormatException.getFormatInstance();
        }
        else
        {
          if ((k < 65) || (k > 90))
            break label291;
          c2 = (char)(k + 32);
        }
        localStringBuilder.append(c2);
      }
      j++;
      continue;
      label291: throw FormatException.getFormatInstance();
    }
    return localStringBuilder.toString();
  }

  private static int[] findAsteriskPattern(BitArray paramBitArray, int[] paramArrayOfInt)
  {
    int i = paramBitArray.getSize();
    int j = paramBitArray.getNextSet(0);
    int k = paramArrayOfInt.length;
    int m = j;
    int n = 0;
    int i1 = 0;
    while (j < i)
    {
      if ((n ^ paramBitArray.get(j)) != 0)
      {
        paramArrayOfInt[i1] = (1 + paramArrayOfInt[i1]);
      }
      else
      {
        int i2 = k - 1;
        if (i1 == i2)
        {
          if ((toNarrowWidePattern(paramArrayOfInt) == ASTERISK_ENCODING) && (paramBitArray.isRange(Math.max(0, m - (j - m >> 1)), m, false)))
            return new int[] { m, j };
          m += paramArrayOfInt[0] + paramArrayOfInt[1];
          int i3 = k - 2;
          System.arraycopy(paramArrayOfInt, 2, paramArrayOfInt, 0, i3);
          paramArrayOfInt[i3] = 0;
          paramArrayOfInt[i2] = 0;
          i1--;
        }
        else
        {
          i1++;
        }
        paramArrayOfInt[i1] = 1;
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

  private static int toNarrowWidePattern(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int n;
    for (int j = 0; ; j = n)
    {
      int k = paramArrayOfInt.length;
      int m = 0;
      n = 2147483647;
      while (m < k)
      {
        int i8 = paramArrayOfInt[m];
        if ((i8 < n) && (i8 > j))
          n = i8;
        m++;
      }
      int i1 = 0;
      int i2 = 0;
      int i3 = 0;
      int i4 = 0;
      while (i1 < i)
      {
        int i7 = paramArrayOfInt[i1];
        if (i7 > n)
        {
          i3 |= 1 << i - 1 - i1;
          i2++;
          i4 += i7;
        }
        i1++;
      }
      int i5 = 0;
      if (i2 == 3)
      {
        while ((i5 < i) && (i2 > 0))
        {
          int i6 = paramArrayOfInt[i5];
          if (i6 > n)
          {
            i2--;
            if (i6 << 1 >= i4)
              return -1;
          }
          i5++;
        }
        return i3;
      }
      if (i2 <= 3)
        return -1;
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    int[] arrayOfInt1 = this.counters;
    Arrays.fill(arrayOfInt1, 0);
    StringBuilder localStringBuilder = this.decodeRowResult;
    localStringBuilder.setLength(0);
    int[] arrayOfInt2 = findAsteriskPattern(paramBitArray, arrayOfInt1);
    int i = paramBitArray.getNextSet(arrayOfInt2[1]);
    int j = paramBitArray.getSize();
    while (true)
    {
      recordPattern(paramBitArray, i, arrayOfInt1);
      int k = toNarrowWidePattern(arrayOfInt1);
      if (k < 0)
        throw NotFoundException.getNotFoundInstance();
      char c = patternToChar(k);
      localStringBuilder.append(c);
      int m = arrayOfInt1.length;
      int n = i;
      for (int i1 = 0; i1 < m; i1++)
        n += arrayOfInt1[i1];
      int i2 = paramBitArray.getNextSet(n);
      if (c == '*')
      {
        localStringBuilder.setLength(localStringBuilder.length() - 1);
        int i3 = arrayOfInt1.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3)
        {
          i5 += arrayOfInt1[i4];
          i4++;
        }
        int i6 = i2 - i - i5;
        if ((i2 != j) && (i6 >> 1 < i5))
          throw NotFoundException.getNotFoundInstance();
        if (this.usingCheckDigit)
        {
          int i7 = localStringBuilder.length() - 1;
          int i8 = 0;
          int i9 = 0;
          while (i8 < i7)
          {
            i9 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(this.decodeRowResult.charAt(i8));
            i8++;
          }
          if (localStringBuilder.charAt(i7) != ALPHABET[(i9 % 43)])
            throw ChecksumException.getChecksumInstance();
          localStringBuilder.setLength(i7);
        }
        if (localStringBuilder.length() == 0)
          throw NotFoundException.getNotFoundInstance();
        String str;
        if (this.extendedMode)
          str = decodeExtended(localStringBuilder);
        else
          str = localStringBuilder.toString();
        float f1 = arrayOfInt2[1] + arrayOfInt2[0] / 2.0F;
        float f2 = i2 + i / 2.0F;
        ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
        float f3 = paramInt;
        arrayOfResultPoint[0] = new ResultPoint(f1, f3);
        arrayOfResultPoint[1] = new ResultPoint(f2, f3);
        return new Result(str, null, arrayOfResultPoint, BarcodeFormat.CODE_39);
      }
      i = i2;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code39Reader
 * JD-Core Version:    0.6.1
 */