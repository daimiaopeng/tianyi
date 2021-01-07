package com.google.zxing.pdf417.encoder;

import B;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.lang.reflect.Array;
import java.util.Map;

public final class PDF417Writer
  implements Writer
{
  private static BitMatrix bitMatrixFromEncoder(PDF417 paramPDF417, String paramString, int paramInt1, int paramInt2)
  {
    paramPDF417.generateBarcodeLogic(paramString, 2);
    byte[][] arrayOfByte1 = paramPDF417.getBarcodeMatrix().getScaledMatrix(2, 8);
    int i;
    if (paramInt2 > paramInt1)
      i = 1;
    else
      i = 0;
    int j;
    if (arrayOfByte1[0].length < arrayOfByte1.length)
      j = 1;
    else
      j = 0;
    int k;
    if ((i ^ j) != 0)
    {
      arrayOfByte1 = rotateArray(arrayOfByte1);
      k = 1;
    }
    else
    {
      k = 0;
    }
    int m = paramInt1 / arrayOfByte1[0].length;
    int n = paramInt2 / arrayOfByte1.length;
    if (m >= n)
      m = n;
    if (m > 1)
    {
      byte[][] arrayOfByte2 = paramPDF417.getBarcodeMatrix().getScaledMatrix(m * 2, 2 * (m * 4));
      if (k != 0)
        arrayOfByte2 = rotateArray(arrayOfByte2);
      return bitMatrixFrombitArray(arrayOfByte2);
    }
    return bitMatrixFrombitArray(arrayOfByte1);
  }

  private static BitMatrix bitMatrixFrombitArray(byte[][] paramArrayOfByte)
  {
    BitMatrix localBitMatrix = new BitMatrix(60 + paramArrayOfByte[0].length, 60 + paramArrayOfByte.length);
    localBitMatrix.clear();
    int i = -30 + localBitMatrix.getHeight();
    int j = 0;
    while (j < paramArrayOfByte.length)
    {
      for (int k = 0; k < paramArrayOfByte[0].length; k++)
        if (paramArrayOfByte[j][k] == 1)
          localBitMatrix.set(k + 30, i);
      j++;
      i--;
    }
    return localBitMatrix;
  }

  private static byte[][] rotateArray(byte[][] paramArrayOfByte)
  {
    byte[][] arrayOfByte = (byte[][])Array.newInstance(B.class, new int[] { paramArrayOfByte[0].length, paramArrayOfByte.length });
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = -1 + (paramArrayOfByte.length - i);
      for (int k = 0; k < paramArrayOfByte[0].length; k++)
        arrayOfByte[k][j] = paramArrayOfByte[i][k];
    }
    return arrayOfByte;
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.PDF_417)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode PDF_417, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    PDF417 localPDF417 = new PDF417();
    if (paramMap != null)
    {
      if (paramMap.containsKey(EncodeHintType.PDF417_COMPACT))
        localPDF417.setCompact(((Boolean)paramMap.get(EncodeHintType.PDF417_COMPACT)).booleanValue());
      if (paramMap.containsKey(EncodeHintType.PDF417_COMPACTION))
        localPDF417.setCompaction((Compaction)paramMap.get(EncodeHintType.PDF417_COMPACTION));
      if (paramMap.containsKey(EncodeHintType.PDF417_DIMENSIONS))
      {
        Dimensions localDimensions = (Dimensions)paramMap.get(EncodeHintType.PDF417_DIMENSIONS);
        localPDF417.setDimensions(localDimensions.getMaxCols(), localDimensions.getMinCols(), localDimensions.getMaxRows(), localDimensions.getMinRows());
      }
    }
    return bitMatrixFromEncoder(localPDF417, paramString, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.encoder.PDF417Writer
 * JD-Core Version:    0.6.1
 */