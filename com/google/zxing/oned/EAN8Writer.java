package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN8Writer extends UPCEANWriter
{
  private static final int CODE_WIDTH = 67;

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.EAN_8)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode EAN_8, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }

  public boolean[] encode(String paramString)
  {
    if (paramString.length() != 8)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested contents should be 8 digits long, but got ");
      localStringBuilder.append(paramString.length());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    boolean[] arrayOfBoolean = new boolean[67];
    int i = 0 + appendPattern(arrayOfBoolean, 0, UPCEANReader.START_END_PATTERN, true);
    int i2;
    for (int j = 0; j <= 3; j = i2)
    {
      i2 = j + 1;
      int i3 = Integer.parseInt(paramString.substring(j, i2));
      i += appendPattern(arrayOfBoolean, i, UPCEANReader.L_PATTERNS[i3], false);
    }
    int k = i + appendPattern(arrayOfBoolean, i, UPCEANReader.MIDDLE_PATTERN, false);
    int n;
    for (int m = 4; m <= 7; m = n)
    {
      n = m + 1;
      int i1 = Integer.parseInt(paramString.substring(m, n));
      k += appendPattern(arrayOfBoolean, k, UPCEANReader.L_PATTERNS[i1], true);
    }
    appendPattern(arrayOfBoolean, k, UPCEANReader.START_END_PATTERN, true);
    return arrayOfBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.EAN8Writer
 * JD-Core Version:    0.6.1
 */