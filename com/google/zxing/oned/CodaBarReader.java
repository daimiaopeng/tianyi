package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public final class CodaBarReader extends OneDReader
{
  static final char[] ALPHABET = "0123456789-$:/.+ABCD".toCharArray();
  private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
  static final int[] CHARACTER_ENCODINGS = { 3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14 };
  private static final int MAX_ACCEPTABLE = 512;
  private static final int MIN_CHARACTER_LENGTH = 3;
  private static final int PADDING = 384;
  private static final char[] STARTEND_ENCODING = { 65, 66, 67, 68 };
  private int counterLength = 0;
  private int[] counters = new int[80];
  private final StringBuilder decodeRowResult = new StringBuilder(20);

  static boolean arrayContains(char[] paramArrayOfChar, char paramChar)
  {
    if (paramArrayOfChar != null)
    {
      int i = paramArrayOfChar.length;
      for (int j = 0; j < i; j++)
        if (paramArrayOfChar[j] == paramChar)
          return true;
    }
    return false;
  }

  private void counterAppend(int paramInt)
  {
    this.counters[this.counterLength] = paramInt;
    this.counterLength = (1 + this.counterLength);
    if (this.counterLength >= this.counters.length)
    {
      int[] arrayOfInt = new int[2 * this.counterLength];
      System.arraycopy(this.counters, 0, arrayOfInt, 0, this.counterLength);
      this.counters = arrayOfInt;
    }
  }

  private int findStartPattern()
  {
    for (int i = 1; i < this.counterLength; i += 2)
    {
      int j = toNarrowWidePattern(i);
      if ((j != -1) && (arrayContains(STARTEND_ENCODING, ALPHABET[j])))
      {
        int k = i;
        int m = 0;
        while (k < i + 7)
        {
          m += this.counters[k];
          k++;
        }
        if ((i == 1) || (this.counters[(i - 1)] >= m / 2))
          return i;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private void setCounters(BitArray paramBitArray)
  {
    this.counterLength = 0;
    int i = paramBitArray.getNextUnset(0);
    int j = paramBitArray.getSize();
    if (i >= j)
      throw NotFoundException.getNotFoundInstance();
    int k = 1;
    int m = 0;
    while (i < j)
    {
      if ((k ^ paramBitArray.get(i)) != 0)
      {
        m++;
      }
      else
      {
        counterAppend(m);
        k ^= 1;
        m = 1;
      }
      i++;
    }
    counterAppend(m);
  }

  private int toNarrowWidePattern(int paramInt)
  {
    int i = paramInt + 7;
    if (i >= this.counterLength)
      return -1;
    int[] arrayOfInt = this.counters;
    int j = 2147483647;
    int k = paramInt;
    int m = 2147483647;
    int n = 0;
    while (k < i)
    {
      int i11 = arrayOfInt[k];
      if (i11 < m)
        m = i11;
      if (i11 > n)
        n = i11;
      k += 2;
    }
    int i1 = (m + n) / 2;
    int i2 = paramInt + 1;
    int i3 = 0;
    while (i2 < i)
    {
      int i10 = arrayOfInt[i2];
      if (i10 < j)
        j = i10;
      if (i10 > i3)
        i3 = i10;
      i2 += 2;
    }
    int i4 = (j + i3) / 2;
    int i5 = 0;
    int i6 = 128;
    int i7 = 0;
    int i8;
    while (true)
    {
      i8 = 0;
      if (i5 >= 7)
        break;
      int i9;
      if ((i5 & 0x1) == 0)
        i9 = i1;
      else
        i9 = i4;
      i6 >>= 1;
      if (arrayOfInt[(paramInt + i5)] > i9)
        i7 |= i6;
      i5++;
    }
    while (i8 < CHARACTER_ENCODINGS.length)
    {
      if (CHARACTER_ENCODINGS[i8] == i7)
        return i8;
      i8++;
    }
    return -1;
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    Arrays.fill(this.counters, 0);
    setCounters(paramBitArray);
    int i = findStartPattern();
    this.decodeRowResult.setLength(0);
    int j = i;
    int k;
    do
    {
      k = toNarrowWidePattern(j);
      if (k == -1)
        throw NotFoundException.getNotFoundInstance();
      this.decodeRowResult.append((char)k);
      j += 8;
    }
    while (((this.decodeRowResult.length() <= 1) || (!arrayContains(STARTEND_ENCODING, ALPHABET[k]))) && (j < this.counterLength));
    int[] arrayOfInt = this.counters;
    int m = j - 1;
    int n = arrayOfInt[m];
    int i1 = -8;
    int i2 = 0;
    while (i1 < -1)
    {
      i2 += this.counters[(j + i1)];
      i1++;
    }
    if ((j < this.counterLength) && (n < i2 / 2))
      throw NotFoundException.getNotFoundInstance();
    validatePattern(i);
    for (int i3 = 0; i3 < this.decodeRowResult.length(); i3++)
      this.decodeRowResult.setCharAt(i3, ALPHABET[this.decodeRowResult.charAt(i3)]);
    char c1 = this.decodeRowResult.charAt(0);
    if (!arrayContains(STARTEND_ENCODING, c1))
      throw NotFoundException.getNotFoundInstance();
    char c2 = this.decodeRowResult.charAt(this.decodeRowResult.length() - 1);
    if (!arrayContains(STARTEND_ENCODING, c2))
      throw NotFoundException.getNotFoundInstance();
    if (this.decodeRowResult.length() <= 3)
      throw NotFoundException.getNotFoundInstance();
    this.decodeRowResult.deleteCharAt(this.decodeRowResult.length() - 1);
    this.decodeRowResult.deleteCharAt(0);
    int i4 = 0;
    int i5 = 0;
    while (i4 < i)
    {
      i5 += this.counters[i4];
      i4++;
    }
    float f1 = i5;
    while (i < m)
    {
      i5 += this.counters[i];
      i++;
    }
    float f2 = i5;
    String str = this.decodeRowResult.toString();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    float f3 = paramInt;
    arrayOfResultPoint[0] = new ResultPoint(f1, f3);
    arrayOfResultPoint[1] = new ResultPoint(f2, f3);
    return new Result(str, null, arrayOfResultPoint, BarcodeFormat.CODABAR);
  }

  void validatePattern(int paramInt)
  {
    int[] arrayOfInt1 = { 0, 0, 0, 0 };
    int[] arrayOfInt2 = { 0, 0, 0, 0 };
    int i = -1 + this.decodeRowResult.length();
    int j = paramInt;
    for (int k = 0; ; k++)
    {
      int m = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(k)];
      for (int n = 6; n >= 0; n--)
      {
        int i8 = (n & 0x1) + 2 * (m & 0x1);
        arrayOfInt1[i8] += this.counters[(j + n)];
        arrayOfInt2[i8] = (1 + arrayOfInt2[i8]);
        m >>= 1;
      }
      if (k >= i)
      {
        int[] arrayOfInt3 = new int[4];
        int[] arrayOfInt4 = new int[4];
        int i2;
        for (int i1 = 0; ; i1++)
        {
          i2 = 0;
          if (i1 >= 2)
            break;
          arrayOfInt4[i1] = 0;
          int i7 = i1 + 2;
          arrayOfInt4[i7] = ((arrayOfInt1[i1] << 8) / arrayOfInt2[i1] + (arrayOfInt1[i7] << 8) / arrayOfInt2[i7] >> 1);
          arrayOfInt3[i1] = arrayOfInt4[i7];
          arrayOfInt3[i7] = ((384 + 512 * arrayOfInt1[i7]) / arrayOfInt2[i7]);
        }
        while (true)
        {
          int i3 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i2)];
          int i4 = 6;
          while (i4 >= 0)
          {
            int i5 = (i4 & 0x1) + 2 * (i3 & 0x1);
            int i6 = this.counters[(paramInt + i4)] << 8;
            if ((i6 >= arrayOfInt4[i5]) && (i6 <= arrayOfInt3[i5]))
            {
              i3 >>= 1;
              i4--;
            }
            else
            {
              throw NotFoundException.getNotFoundInstance();
            }
          }
          if (i2 >= i)
            return;
          paramInt += 8;
          i2++;
        }
      }
      j += 8;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.CodaBarReader
 * JD-Core Version:    0.6.1
 */