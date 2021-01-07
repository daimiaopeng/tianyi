package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public abstract class OneDimensionalCodeWriter
  implements Writer
{
  protected static int appendPattern(boolean[] paramArrayOfBoolean, int paramInt, int[] paramArrayOfInt, boolean paramBoolean)
  {
    int i = paramArrayOfInt.length;
    int j = paramInt;
    boolean bool = paramBoolean;
    int k = 0;
    int m = 0;
    while (k < i)
    {
      int n = paramArrayOfInt[k];
      int i1 = j;
      int i2 = 0;
      while (i2 < n)
      {
        int i3 = i1 + 1;
        paramArrayOfBoolean[i1] = bool;
        i2++;
        i1 = i3;
      }
      m += n;
      if (!bool)
        bool = true;
      else
        bool = false;
      k++;
      j = i1;
    }
    return m;
  }

  private static BitMatrix renderResult(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramArrayOfBoolean.length;
    int j = paramInt3 + i;
    int k = Math.max(paramInt1, j);
    int m = Math.max(1, paramInt2);
    int n = k / j;
    int i1 = (k - i * n) / 2;
    BitMatrix localBitMatrix = new BitMatrix(k, m);
    int i2 = i1;
    int i3 = 0;
    while (i3 < i)
    {
      if (paramArrayOfBoolean[i3] != 0)
        localBitMatrix.setRegion(i2, 0, n, m);
      i3++;
      i2 += n;
    }
    return localBitMatrix;
  }

  public final BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramString.length() == 0)
      throw new IllegalArgumentException("Found empty contents");
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      int i = getDefaultMargin();
      if (paramMap != null)
      {
        Integer localInteger = (Integer)paramMap.get(EncodeHintType.MARGIN);
        if (localInteger != null)
          i = localInteger.intValue();
      }
      return renderResult(encode(paramString), paramInt1, paramInt2, i);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Negative size is not allowed. Input: ");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append('x');
    localStringBuilder.append(paramInt2);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public abstract boolean[] encode(String paramString);

  public int getDefaultMargin()
  {
    return 10;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.OneDimensionalCodeWriter
 * JD-Core Version:    0.6.1
 */