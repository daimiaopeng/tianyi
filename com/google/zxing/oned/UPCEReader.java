package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class UPCEReader extends UPCEANReader
{
  private static final int[] MIDDLE_END_PATTERN = { 1, 1, 1, 1, 1, 1 };
  private static final int[][] NUMSYS_AND_CHECK_DIGIT_PATTERNS = { { 56, 52, 50, 49, 44, 38, 35, 42, 41, 37 }, { 7, 11, 13, 14, 19, 25, 28, 21, 22, 26 } };
  private final int[] decodeMiddleCounters = new int[4];

  public static String convertUPCEtoUPCA(String paramString)
  {
    char[] arrayOfChar = new char[6];
    paramString.getChars(1, 7, arrayOfChar, 0);
    StringBuilder localStringBuilder = new StringBuilder(12);
    localStringBuilder.append(paramString.charAt(0));
    char c = arrayOfChar[5];
    switch (c)
    {
    default:
      localStringBuilder.append(arrayOfChar, 0, 5);
      localStringBuilder.append("0000");
      localStringBuilder.append(c);
      break;
    case '4':
      localStringBuilder.append(arrayOfChar, 0, 4);
      localStringBuilder.append("00000");
      localStringBuilder.append(arrayOfChar[4]);
      break;
    case '3':
      localStringBuilder.append(arrayOfChar, 0, 3);
      localStringBuilder.append("00000");
      localStringBuilder.append(arrayOfChar, 3, 2);
      break;
    case '0':
    case '1':
    case '2':
      localStringBuilder.append(arrayOfChar, 0, 2);
      localStringBuilder.append(c);
      localStringBuilder.append("0000");
      localStringBuilder.append(arrayOfChar, 2, 3);
    }
    localStringBuilder.append(paramString.charAt(7));
    return localStringBuilder.toString();
  }

  private static void determineNumSysAndCheckDigit(StringBuilder paramStringBuilder, int paramInt)
  {
    for (int i = 0; i <= 1; i++)
      for (int j = 0; j < 10; j++)
        if (paramInt == NUMSYS_AND_CHECK_DIGIT_PATTERNS[i][j])
        {
          paramStringBuilder.insert(0, (char)(i + 48));
          paramStringBuilder.append((char)(j + 48));
          return;
        }
    throw NotFoundException.getNotFoundInstance();
  }

  protected boolean checkChecksum(String paramString)
  {
    return super.checkChecksum(convertUPCEtoUPCA(paramString));
  }

  protected int[] decodeEnd(BitArray paramBitArray, int paramInt)
  {
    return findGuardPattern(paramBitArray, paramInt, true, MIDDLE_END_PATTERN);
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
      int n = decodeDigit(paramBitArray, arrayOfInt, j, L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(48 + n % 10));
      int i1 = arrayOfInt.length;
      int i2 = j;
      for (int i3 = 0; i3 < i1; i3++)
        i2 += arrayOfInt[i3];
      if (n >= 10)
        m |= 1 << 5 - k;
      k++;
      j = i2;
    }
    determineNumSysAndCheckDigit(paramStringBuilder, m);
    return j;
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.UPC_E;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEReader
 * JD-Core Version:    0.6.1
 */