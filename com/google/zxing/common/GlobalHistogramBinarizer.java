package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public class GlobalHistogramBinarizer extends Binarizer
{
  private static final byte[] EMPTY = new byte[0];
  private static final int LUMINANCE_BITS = 5;
  private static final int LUMINANCE_BUCKETS = 32;
  private static final int LUMINANCE_SHIFT = 3;
  private final int[] buckets = new int[32];
  private byte[] luminances = EMPTY;

  public GlobalHistogramBinarizer(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource);
  }

  private static int estimateBlackPoint(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    while (k < i)
    {
      if (paramArrayOfInt[k] > m)
      {
        m = paramArrayOfInt[k];
        i1 = k;
      }
      if (paramArrayOfInt[k] > n)
        n = paramArrayOfInt[k];
      k++;
    }
    int i2 = 0;
    int i3 = 0;
    while (j < i)
    {
      int i10 = j - i1;
      int i11 = i10 * (i10 * paramArrayOfInt[j]);
      if (i11 > i2)
      {
        i3 = j;
        i2 = i11;
      }
      j++;
    }
    if (i1 > i3)
    {
      int i9 = i1;
      i1 = i3;
      i3 = i9;
    }
    if (i3 - i1 <= i >> 4)
      throw NotFoundException.getNotFoundInstance();
    int i4 = i3 - 1;
    int i5 = -1;
    int i6 = i4;
    while (i4 > i1)
    {
      int i7 = i4 - i1;
      int i8 = i7 * i7 * (i3 - i4) * (n - paramArrayOfInt[i4]);
      if (i8 > i5)
      {
        i6 = i4;
        i5 = i8;
      }
      i4--;
    }
    return i6 << 3;
  }

  private void initArrays(int paramInt)
  {
    if (this.luminances.length < paramInt)
      this.luminances = new byte[paramInt];
    for (int i = 0; i < 32; i++)
      this.buckets[i] = 0;
  }

  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    return new GlobalHistogramBinarizer(paramLuminanceSource);
  }

  public BitMatrix getBlackMatrix()
  {
    LuminanceSource localLuminanceSource = getLuminanceSource();
    int i = localLuminanceSource.getWidth();
    int j = localLuminanceSource.getHeight();
    BitMatrix localBitMatrix = new BitMatrix(i, j);
    initArrays(i);
    int[] arrayOfInt = this.buckets;
    for (int k = 1; k < 5; k++)
    {
      byte[] arrayOfByte2 = localLuminanceSource.getRow(j * k / 5, this.luminances);
      int i3 = (i << 2) / 5;
      for (int i4 = i / 5; i4 < i3; i4++)
      {
        int i5 = (0xFF & arrayOfByte2[i4]) >> 3;
        arrayOfInt[i5] = (1 + arrayOfInt[i5]);
      }
    }
    int m = estimateBlackPoint(arrayOfInt);
    byte[] arrayOfByte1 = localLuminanceSource.getMatrix();
    for (int n = 0; n < j; n++)
    {
      int i1 = n * i;
      for (int i2 = 0; i2 < i; i2++)
        if ((0xFF & arrayOfByte1[(i1 + i2)]) < m)
          localBitMatrix.set(i2, n);
    }
    return localBitMatrix;
  }

  public BitArray getBlackRow(int paramInt, BitArray paramBitArray)
  {
    LuminanceSource localLuminanceSource = getLuminanceSource();
    int i = localLuminanceSource.getWidth();
    if ((paramBitArray != null) && (paramBitArray.getSize() >= i))
      paramBitArray.clear();
    else
      paramBitArray = new BitArray(i);
    initArrays(i);
    byte[] arrayOfByte = localLuminanceSource.getRow(paramInt, this.luminances);
    int[] arrayOfInt = this.buckets;
    for (int j = 0; j < i; j++)
    {
      int i5 = (0xFF & arrayOfByte[j]) >> 3;
      arrayOfInt[i5] = (1 + arrayOfInt[i5]);
    }
    int k = estimateBlackPoint(arrayOfInt);
    int m = 0xFF & arrayOfByte[0];
    int n = 0xFF & arrayOfByte[1];
    int i1 = m;
    int i2 = 1;
    while (i2 < i - 1)
    {
      int i3 = i2 + 1;
      int i4 = 0xFF & arrayOfByte[i3];
      if ((n << 2) - i1 - i4 >> 1 < k)
        paramBitArray.set(i2);
      i1 = n;
      i2 = i3;
      n = i4;
    }
    return paramBitArray;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.GlobalHistogramBinarizer
 * JD-Core Version:    0.6.1
 */