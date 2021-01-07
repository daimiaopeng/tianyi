package com.google.zxing.common;

import I;
import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import java.lang.reflect.Array;

public final class HybridBinarizer extends GlobalHistogramBinarizer
{
  private static final int BLOCK_SIZE = 8;
  private static final int BLOCK_SIZE_MASK = 7;
  private static final int BLOCK_SIZE_POWER = 3;
  private static final int MINIMUM_DIMENSION = 40;
  private static final int MIN_DYNAMIC_RANGE = 24;
  private BitMatrix matrix;

  public HybridBinarizer(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource);
  }

  private static int[][] calculateBlackPoints(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[][] arrayOfInt = (int[][])Array.newInstance(I.class, new int[] { paramInt2, paramInt1 });
    for (int i = 0; i < paramInt2; i++)
    {
      int j = i << 3;
      int k = 8;
      int m = paramInt4 - 8;
      if (j <= m)
        m = j;
      int n = 0;
      while (n < paramInt1)
      {
        int i1 = n << 3;
        int i2 = paramInt3 - 8;
        if (i1 > i2)
          i1 = i2;
        int i3 = i1 + m * paramInt3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 255;
        while (i4 < k)
        {
          int i13 = i6;
          int i14 = i5;
          int i15 = 0;
          while (i15 < k)
          {
            int i18 = 0xFF & paramArrayOfByte[(i3 + i15)];
            i14 += i18;
            if (i18 < i7)
              i7 = i18;
            if (i18 > i13)
              i13 = i18;
            i15++;
            k = 8;
          }
          if (i13 - i7 > 24)
            while (true)
            {
              i4++;
              i3 += paramInt3;
              int i16 = 8;
              if (i4 >= i16)
                break;
              int i17 = 0;
              while (i17 < i16)
              {
                i14 += (0xFF & paramArrayOfByte[(i3 + i17)]);
                i17++;
                i16 = 8;
              }
            }
          i5 = i14;
          i4++;
          i3 += paramInt3;
          i6 = i13;
          k = 8;
        }
        int i8 = i5 >> 6;
        if (i6 - i7 <= 24)
        {
          i8 = i7 >> 1;
          if ((i > 0) && (n > 0))
          {
            int i9 = i - 1;
            int i10 = arrayOfInt[i9][n];
            int[] arrayOfInt1 = arrayOfInt[i];
            int i11 = n - 1;
            int i12 = i10 + 2 * arrayOfInt1[i11] + arrayOfInt[i9][i11] >> 2;
            if (i7 < i12)
              i8 = i12;
          }
        }
        arrayOfInt[i][n] = i8;
        n++;
        k = 8;
      }
    }
    return arrayOfInt;
  }

  private static void calculateThresholdForBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[][] paramArrayOfInt, BitMatrix paramBitMatrix)
  {
    for (int i = 0; i < paramInt2; i++)
    {
      int j = i << 3;
      int k = paramInt4 - 8;
      if (j > k)
        j = k;
      for (int m = 0; m < paramInt1; m++)
      {
        int n = m << 3;
        int i1 = paramInt3 - 8;
        if (n <= i1)
          i1 = n;
        int i2 = cap(m, 2, paramInt1 - 3);
        int i3 = cap(i, 2, paramInt2 - 3);
        int i4 = -2;
        int i5 = 0;
        while (i4 <= 2)
        {
          int[] arrayOfInt = paramArrayOfInt[(i3 + i4)];
          i5 += arrayOfInt[(i2 - 2)] + arrayOfInt[(i2 - 1)] + arrayOfInt[i2] + arrayOfInt[(i2 + 1)] + arrayOfInt[(i2 + 2)];
          i4++;
        }
        int i6 = i5 / 25;
        thresholdBlock(paramArrayOfByte, i1, j, i6, paramInt3, paramBitMatrix);
      }
    }
  }

  private static int cap(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2)
      paramInt1 = paramInt2;
    else if (paramInt1 > paramInt3)
      paramInt1 = paramInt3;
    return paramInt1;
  }

  private static void thresholdBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitMatrix paramBitMatrix)
  {
    int i = paramInt1 + paramInt2 * paramInt4;
    int j = 0;
    while (j < 8)
    {
      for (int k = 0; k < 8; k++)
        if ((0xFF & paramArrayOfByte[(i + k)]) <= paramInt3)
          paramBitMatrix.set(paramInt1 + k, paramInt2 + j);
      j++;
      i += paramInt4;
    }
  }

  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    return new HybridBinarizer(paramLuminanceSource);
  }

  public BitMatrix getBlackMatrix()
  {
    if (this.matrix != null)
      return this.matrix;
    LuminanceSource localLuminanceSource = getLuminanceSource();
    int i = localLuminanceSource.getWidth();
    int j = localLuminanceSource.getHeight();
    if ((i >= 40) && (j >= 40))
    {
      byte[] arrayOfByte = localLuminanceSource.getMatrix();
      int k = i >> 3;
      if ((i & 0x7) != 0)
        k++;
      int m = k;
      int n = j >> 3;
      if ((j & 0x7) != 0)
        n++;
      int i1 = n;
      int[][] arrayOfInt = calculateBlackPoints(arrayOfByte, m, i1, i, j);
      BitMatrix localBitMatrix = new BitMatrix(i, j);
      calculateThresholdForBlock(arrayOfByte, m, i1, i, j, arrayOfInt, localBitMatrix);
      this.matrix = localBitMatrix;
    }
    else
    {
      this.matrix = super.getBlackMatrix();
    }
    return this.matrix;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.HybridBinarizer
 * JD-Core Version:    0.6.1
 */