package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class ITFWriter extends OneDimensionalCodeWriter
{
  private static final int[] END_PATTERN = { 3, 1, 1 };
  private static final int[] START_PATTERN = { 1, 1, 1, 1 };

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.ITF)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode ITF, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }

  public boolean[] encode(String paramString)
  {
    int i = paramString.length();
    if (i % 2 != 0)
      throw new IllegalArgumentException("The lenght of the input should be even");
    if (i > 80)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested contents should be less than 80 digits long, but got ");
      localStringBuilder.append(i);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    boolean[] arrayOfBoolean = new boolean[9 + i * 9];
    int j = appendPattern(arrayOfBoolean, 0, START_PATTERN, true);
    for (int k = 0; k < i; k += 2)
    {
      int m = Character.digit(paramString.charAt(k), 10);
      int n = Character.digit(paramString.charAt(k + 1), 10);
      int[] arrayOfInt = new int[18];
      for (int i1 = 0; i1 < 5; i1++)
      {
        int i2 = i1 << 1;
        arrayOfInt[i2] = ITFReader.PATTERNS[m][i1];
        arrayOfInt[(i2 + 1)] = ITFReader.PATTERNS[n][i1];
      }
      j += appendPattern(arrayOfBoolean, j, arrayOfInt, true);
    }
    appendPattern(arrayOfBoolean, j, END_PATTERN, true);
    return arrayOfBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.ITFWriter
 * JD-Core Version:    0.6.1
 */