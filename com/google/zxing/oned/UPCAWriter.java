package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class UPCAWriter
  implements Writer
{
  private final EAN13Writer subWriter = new EAN13Writer();

  private static String preencode(String paramString)
  {
    int i = paramString.length();
    if (i == 11)
    {
      int j = 0;
      int k = 0;
      while (j < 11)
      {
        int m = paramString.charAt(j) - '0';
        int n;
        if (j % 2 == 0)
          n = 3;
        else
          n = 1;
        k += m * n;
        j++;
      }
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(paramString);
      localStringBuilder3.append((1000 - k) % 10);
      paramString = localStringBuilder3.toString();
    }
    else if (i != 12)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Requested contents should be 11 or 12 digits long, but got ");
      localStringBuilder1.append(paramString.length());
      throw new IllegalArgumentException(localStringBuilder1.toString());
    }
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append('0');
    localStringBuilder2.append(paramString);
    return localStringBuilder2.toString();
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.UPC_A)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode UPC-A, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return this.subWriter.encode(preencode(paramString), BarcodeFormat.EAN_13, paramInt1, paramInt2, paramMap);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCAWriter
 * JD-Core Version:    0.6.1
 */