package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader extends UPCEANReader
{
  static final int[] FIRST_DIGIT_ENCODINGS = { 0, 11, 13, 14, 19, 25, 28, 21, 22, 26 };
  private final int[] decodeMiddleCounters = new int[4];

  private static void determineFirstDigit(StringBuilder paramStringBuilder, int paramInt)
  {
    for (int i = 0; i < 10; i++)
      if (paramInt == FIRST_DIGIT_ENCODINGS[i])
      {
        paramStringBuilder.insert(0, (char)(i + 48));
        return;
      }
    throw NotFoundException.getNotFoundInstance();
  }

  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int i = paramBitArray.getSize();
    int j = paramArrayOfInt[1];
    int k = 0;
    int m = 0;
    while ((k < 6) && (j < i))
    {
      int i5 = decodeDigit(paramBitArray, arrayOfInt, j, L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(48 + i5 % 10));
      int i6 = arrayOfInt.length;
      int i7 = j;
      for (int i8 = 0; i8 < i6; i8++)
        i7 += arrayOfInt[i8];
      if (i5 >= 10)
        m |= 1 << 5 - k;
      k++;
      j = i7;
    }
    determineFirstDigit(paramStringBuilder, m);
    int n = findGuardPattern(paramBitArray, j, true, MIDDLE_PATTERN)[1];
    int i1 = 0;
    while ((i1 < 6) && (n < i))
    {
      paramStringBuilder.append((char)(48 + decodeDigit(paramBitArray, arrayOfInt, n, L_PATTERNS)));
      int i2 = arrayOfInt.length;
      int i3 = n;
      for (int i4 = 0; i4 < i2; i4++)
        i3 += arrayOfInt[i4];
      i1++;
      n = i3;
    }
    return n;
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.EAN_13;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.EAN13Reader
 * JD-Core Version:    0.6.1
 */