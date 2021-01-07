package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class ITFReader extends OneDReader
{
  private static final int[] DEFAULT_ALLOWED_LENGTHS = { 44, 24, 20, 18, 16, 14, 12, 10, 8, 6 };
  private static final int[] END_PATTERN_REVERSED = { 1, 1, 3 };
  private static final int MAX_AVG_VARIANCE = 107;
  private static final int MAX_INDIVIDUAL_VARIANCE = 204;
  private static final int N = 1;
  static final int[][] PATTERNS = { { 1, 1, 3, 3, 1 }, { 3, 1, 1, 1, 3 }, { 1, 3, 1, 1, 3 }, { 3, 3, 1, 1, 1 }, { 1, 1, 3, 1, 3 }, { 3, 1, 3, 1, 1 }, { 1, 3, 3, 1, 1 }, { 1, 1, 1, 3, 3 }, { 3, 1, 1, 3, 1 }, { 1, 3, 1, 3, 1 } };
  private static final int[] START_PATTERN = { 1, 1, 1, 1 };
  private static final int W = 3;
  private int narrowLineWidth = -1;

  private static int decodeDigit(int[] paramArrayOfInt)
  {
    int i = PATTERNS.length;
    int j = 107;
    int k = -1;
    for (int m = 0; m < i; m++)
    {
      int n = patternMatchVariance(paramArrayOfInt, PATTERNS[m], 204);
      if (n < j)
      {
        k = m;
        j = n;
      }
    }
    if (k >= 0)
      return k;
    throw NotFoundException.getNotFoundInstance();
  }

  private static void decodeMiddle(BitArray paramBitArray, int paramInt1, int paramInt2, StringBuilder paramStringBuilder)
  {
    int[] arrayOfInt1 = new int[10];
    int[] arrayOfInt2 = new int[5];
    int[] arrayOfInt3 = new int[5];
    while (paramInt1 < paramInt2)
    {
      recordPattern(paramBitArray, paramInt1, arrayOfInt1);
      int i = 0;
      for (int j = 0; j < 5; j++)
      {
        int m = j << 1;
        arrayOfInt2[j] = arrayOfInt1[m];
        arrayOfInt3[j] = arrayOfInt1[(m + 1)];
      }
      paramStringBuilder.append((char)(48 + decodeDigit(arrayOfInt2)));
      paramStringBuilder.append((char)(48 + decodeDigit(arrayOfInt3)));
      int k = arrayOfInt1.length;
      while (i < k)
      {
        paramInt1 += arrayOfInt1[i];
        i++;
      }
    }
  }

  private static int[] findGuardPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i];
    int j = paramBitArray.getSize();
    int k = paramInt;
    int m = 0;
    int n = 0;
    while (paramInt < j)
    {
      if ((m ^ paramBitArray.get(paramInt)) != 0)
      {
        arrayOfInt[n] = (1 + arrayOfInt[n]);
      }
      else
      {
        int i1 = i - 1;
        if (n == i1)
        {
          if (patternMatchVariance(arrayOfInt, paramArrayOfInt, 204) < 107)
            return new int[] { k, paramInt };
          k += arrayOfInt[0] + arrayOfInt[1];
          int i2 = i - 2;
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, i2);
          arrayOfInt[i2] = 0;
          arrayOfInt[i1] = 0;
          n--;
        }
        else
        {
          n++;
        }
        arrayOfInt[n] = 1;
        m ^= 1;
      }
      paramInt++;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int skipWhiteSpace(BitArray paramBitArray)
  {
    int i = paramBitArray.getSize();
    int j = paramBitArray.getNextSet(0);
    if (j == i)
      throw NotFoundException.getNotFoundInstance();
    return j;
  }

  private void validateQuietZone(BitArray paramBitArray, int paramInt)
  {
    int i = 10 * this.narrowLineWidth;
    for (int j = paramInt - 1; (i > 0) && (j >= 0) && (!paramBitArray.get(j)); j--)
      i--;
    if (i != 0)
      throw NotFoundException.getNotFoundInstance();
  }

  int[] decodeEnd(BitArray paramBitArray)
  {
    paramBitArray.reverse();
    try
    {
      int[] arrayOfInt = findGuardPattern(paramBitArray, skipWhiteSpace(paramBitArray), END_PATTERN_REVERSED);
      validateQuietZone(paramBitArray, arrayOfInt[0]);
      int i = arrayOfInt[0];
      arrayOfInt[0] = (paramBitArray.getSize() - arrayOfInt[1]);
      arrayOfInt[1] = (paramBitArray.getSize() - i);
      return arrayOfInt;
    }
    finally
    {
      paramBitArray.reverse();
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    int[] arrayOfInt1 = decodeStart(paramBitArray);
    int[] arrayOfInt2 = decodeEnd(paramBitArray);
    StringBuilder localStringBuilder = new StringBuilder(20);
    decodeMiddle(paramBitArray, arrayOfInt1[1], arrayOfInt2[0], localStringBuilder);
    String str = localStringBuilder.toString();
    int[] arrayOfInt3;
    if (paramMap != null)
      arrayOfInt3 = (int[])paramMap.get(DecodeHintType.ALLOWED_LENGTHS);
    else
      arrayOfInt3 = null;
    if (arrayOfInt3 == null)
      arrayOfInt3 = DEFAULT_ALLOWED_LENGTHS;
    int i = str.length();
    int j = arrayOfInt3.length;
    for (int k = 0; k < j; k++)
      if (i == arrayOfInt3[k])
      {
        m = 1;
        break label127;
      }
    int m = 0;
    label127: if (m == 0)
      throw FormatException.getFormatInstance();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    float f1 = arrayOfInt1[1];
    float f2 = paramInt;
    arrayOfResultPoint[0] = new ResultPoint(f1, f2);
    arrayOfResultPoint[1] = new ResultPoint(arrayOfInt2[0], f2);
    return new Result(str, null, arrayOfResultPoint, BarcodeFormat.ITF);
  }

  int[] decodeStart(BitArray paramBitArray)
  {
    int[] arrayOfInt = findGuardPattern(paramBitArray, skipWhiteSpace(paramBitArray), START_PATTERN);
    this.narrowLineWidth = (arrayOfInt[1] - arrayOfInt[0] >> 2);
    validateQuietZone(paramBitArray, arrayOfInt[0]);
    return arrayOfInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.ITFReader
 * JD-Core Version:    0.6.1
 */