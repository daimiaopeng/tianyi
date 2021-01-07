package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter
  implements Writer
{
  private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix paramByteMatrix)
  {
    int i = paramByteMatrix.getWidth();
    int j = paramByteMatrix.getHeight();
    BitMatrix localBitMatrix = new BitMatrix(i, j);
    localBitMatrix.clear();
    for (int k = 0; k < i; k++)
      for (int m = 0; m < j; m++)
        if (paramByteMatrix.get(k, m) == 1)
          localBitMatrix.set(k, m);
    return localBitMatrix;
  }

  private static BitMatrix encodeLowLevel(DefaultPlacement paramDefaultPlacement, SymbolInfo paramSymbolInfo)
  {
    int i = paramSymbolInfo.getSymbolDataWidth();
    int j = paramSymbolInfo.getSymbolDataHeight();
    ByteMatrix localByteMatrix = new ByteMatrix(paramSymbolInfo.getSymbolWidth(), paramSymbolInfo.getSymbolHeight());
    int k = 0;
    int m = 0;
    while (k < j)
    {
      if (k % paramSymbolInfo.matrixHeight == 0)
      {
        int i4 = 0;
        int i5 = 0;
        while (i4 < paramSymbolInfo.getSymbolWidth())
        {
          boolean bool2;
          if (i4 % 2 == 0)
            bool2 = true;
          else
            bool2 = false;
          localByteMatrix.set(i5, m, bool2);
          i5++;
          i4++;
        }
        m++;
      }
      int n = 0;
      int i1 = 0;
      while (n < i)
      {
        if (n % paramSymbolInfo.matrixWidth == 0)
        {
          localByteMatrix.set(i1, m, true);
          i1++;
        }
        localByteMatrix.set(i1, m, paramDefaultPlacement.getBit(n, k));
        i1++;
        if (n % paramSymbolInfo.matrixWidth == paramSymbolInfo.matrixWidth - 1)
        {
          boolean bool1;
          if (k % 2 == 0)
            bool1 = true;
          else
            bool1 = false;
          localByteMatrix.set(i1, m, bool1);
          i1++;
        }
        n++;
      }
      m++;
      if (k % paramSymbolInfo.matrixHeight == paramSymbolInfo.matrixHeight - 1)
      {
        int i2 = 0;
        int i3 = 0;
        while (i2 < paramSymbolInfo.getSymbolWidth())
        {
          localByteMatrix.set(i3, m, true);
          i3++;
          i2++;
        }
        m++;
      }
      k++;
    }
    return convertByteMatrixToBitMatrix(localByteMatrix);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramString.length() == 0)
      throw new IllegalArgumentException("Found empty contents");
    if (paramBarcodeFormat != BarcodeFormat.DATA_MATRIX)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Can only encode DATA_MATRIX, but got ");
      localStringBuilder1.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder1.toString());
    }
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      Object localObject1 = SymbolShapeHint.FORCE_NONE;
      Object localObject2 = null;
      Dimension localDimension1;
      if (paramMap != null)
      {
        SymbolShapeHint localSymbolShapeHint = (SymbolShapeHint)paramMap.get(EncodeHintType.DATA_MATRIX_SHAPE);
        if (localSymbolShapeHint != null)
          localObject1 = localSymbolShapeHint;
        localDimension1 = (Dimension)paramMap.get(EncodeHintType.MIN_SIZE);
        if (localDimension1 == null)
          localDimension1 = null;
        Dimension localDimension2 = (Dimension)paramMap.get(EncodeHintType.MAX_SIZE);
        localObject2 = null;
        if (localDimension2 != null)
          localObject2 = localDimension2;
      }
      else
      {
        localDimension1 = null;
      }
      String str = HighLevelEncoder.encodeHighLevel(paramString, (SymbolShapeHint)localObject1, localDimension1, localObject2);
      SymbolInfo localSymbolInfo = SymbolInfo.lookup(str.length(), (SymbolShapeHint)localObject1, localDimension1, localObject2, true);
      DefaultPlacement localDefaultPlacement = new DefaultPlacement(ErrorCorrection.encodeECC200(str, localSymbolInfo), localSymbolInfo.getSymbolDataWidth(), localSymbolInfo.getSymbolDataHeight());
      localDefaultPlacement.place();
      return encodeLowLevel(localDefaultPlacement, localSymbolInfo);
    }
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Requested dimensions are too small: ");
    localStringBuilder2.append(paramInt1);
    localStringBuilder2.append('x');
    localStringBuilder2.append(paramInt2);
    throw new IllegalArgumentException(localStringBuilder2.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.DataMatrixWriter
 * JD-Core Version:    0.6.1
 */