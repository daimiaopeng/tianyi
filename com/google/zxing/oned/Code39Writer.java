package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code39Writer extends OneDimensionalCodeWriter
{
  private static void toIntArray(int paramInt, int[] paramArrayOfInt)
  {
    for (int i = 0; i < 9; i++)
    {
      int j = 1;
      if ((paramInt & j << i) != 0)
        j = 2;
      paramArrayOfInt[i] = j;
    }
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.CODE_39)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode CODE_39, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }

  public boolean[] encode(String paramString)
  {
    int i = paramString.length();
    if (i > 80)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested contents should be less than 80 digits long, but got ");
      localStringBuilder.append(i);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    int[] arrayOfInt1 = new int[9];
    int j = i + 25;
    int k = 0;
    while (k < i)
    {
      int i4 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(paramString.charAt(k));
      toIntArray(Code39Reader.CHARACTER_ENCODINGS[i4], arrayOfInt1);
      int i5 = arrayOfInt1.length;
      int i6 = j;
      for (int i7 = 0; i7 < i5; i7++)
        i6 += arrayOfInt1[i7];
      k++;
      j = i6;
    }
    boolean[] arrayOfBoolean = new boolean[j];
    toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], arrayOfInt1);
    int m = appendPattern(arrayOfBoolean, 0, arrayOfInt1, true);
    int[] arrayOfInt2 = { 1 };
    int n = m + appendPattern(arrayOfBoolean, m, arrayOfInt2, false);
    for (int i1 = i - 1; i1 >= 0; i1--)
    {
      int i2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(paramString.charAt(i1));
      toIntArray(Code39Reader.CHARACTER_ENCODINGS[i2], arrayOfInt1);
      int i3 = n + appendPattern(arrayOfBoolean, n, arrayOfInt1, true);
      n = i3 + appendPattern(arrayOfBoolean, i3, arrayOfInt2, false);
    }
    toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], arrayOfInt1);
    appendPattern(arrayOfBoolean, n, arrayOfInt1, true);
    return arrayOfBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code39Writer
 * JD-Core Version:    0.6.1
 */