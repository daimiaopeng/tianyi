package com.google.zxing.common;

public final class BitMatrix
{
  private final int[] bits;
  private final int height;
  private final int rowSize;
  private final int width;

  public BitMatrix(int paramInt)
  {
    this(paramInt, paramInt);
  }

  public BitMatrix(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 1) && (paramInt2 >= 1))
    {
      this.width = paramInt1;
      this.height = paramInt2;
      this.rowSize = (paramInt1 + 31 >> 5);
      this.bits = new int[paramInt2 * this.rowSize];
      return;
    }
    throw new IllegalArgumentException("Both dimensions must be greater than 0");
  }

  public void clear()
  {
    int i = this.bits.length;
    for (int j = 0; j < i; j++)
      this.bits[j] = 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof BitMatrix))
      return false;
    BitMatrix localBitMatrix = (BitMatrix)paramObject;
    if ((this.width == localBitMatrix.width) && (this.height == localBitMatrix.height) && (this.rowSize == localBitMatrix.rowSize) && (this.bits.length == localBitMatrix.bits.length))
    {
      for (int i = 0; i < this.bits.length; i++)
        if (this.bits[i] != localBitMatrix.bits[i])
          return false;
      return true;
    }
    return false;
  }

  public void flip(int paramInt1, int paramInt2)
  {
    int i = paramInt2 * this.rowSize + (paramInt1 >> 5);
    int[] arrayOfInt = this.bits;
    arrayOfInt[i] ^= 1 << (paramInt1 & 0x1F);
  }

  public boolean get(int paramInt1, int paramInt2)
  {
    int i = paramInt2 * this.rowSize + (paramInt1 >> 5);
    int j = this.bits[i] >>> (paramInt1 & 0x1F);
    int k = 1;
    if ((j & k) == 0)
      k = 0;
    return k;
  }

  public int[] getBottomRightOnBit()
  {
    for (int i = this.bits.length - 1; (i >= 0) && (this.bits[i] == 0); i--);
    if (i < 0)
      return null;
    int j = i / this.rowSize;
    int k = i % this.rowSize << 5;
    int m = this.bits[i];
    for (int n = 31; m >>> n == 0; n--);
    return new int[] { k + n, j };
  }

  public int[] getEnclosingRectangle()
  {
    int i = this.width;
    int j = this.height;
    int k = -1;
    int m = j;
    int n = -1;
    int i1 = i;
    int i2 = 0;
    while (i2 < this.height)
    {
      int i5 = n;
      int i6 = k;
      int i7 = i1;
      for (int i8 = 0; i8 < this.rowSize; i8++)
      {
        int i9 = this.bits[(i8 + i2 * this.rowSize)];
        if (i9 != 0)
        {
          if (i2 < m)
            m = i2;
          if (i2 > i5)
            i5 = i2;
          int i10 = i8 * 32;
          int i11 = 31;
          if (i10 < i7)
          {
            for (int i13 = 0; i9 << 31 - i13 == 0; i13++);
            int i14 = i13 + i10;
            if (i14 < i7)
              i7 = i14;
          }
          if (i10 + 31 > i6)
          {
            while (i9 >>> i11 == 0)
              i11--;
            int i12 = i10 + i11;
            if (i12 > i6)
              i6 = i12;
          }
        }
      }
      i2++;
      i1 = i7;
      k = i6;
      n = i5;
    }
    int i3 = k - i1;
    int i4 = n - m;
    if ((i3 >= 0) && (i4 >= 0))
      return new int[] { i1, m, i3, i4 };
    return null;
  }

  public int getHeight()
  {
    return this.height;
  }

  public BitArray getRow(int paramInt, BitArray paramBitArray)
  {
    if ((paramBitArray == null) || (paramBitArray.getSize() < this.width))
      paramBitArray = new BitArray(this.width);
    int i = paramInt * this.rowSize;
    for (int j = 0; j < this.rowSize; j++)
      paramBitArray.setBulk(j << 5, this.bits[(i + j)]);
    return paramBitArray;
  }

  public int[] getTopLeftOnBit()
  {
    for (int i = 0; (i < this.bits.length) && (this.bits[i] == 0); i++);
    if (i == this.bits.length)
      return null;
    int j = i / this.rowSize;
    int k = i % this.rowSize << 5;
    int m = this.bits[i];
    for (int n = 0; m << 31 - n == 0; n++);
    return new int[] { k + n, j };
  }

  public int getWidth()
  {
    return this.width;
  }

  public int hashCode()
  {
    int i = 31 * (31 * (31 * this.width + this.width) + this.height) + this.rowSize;
    int[] arrayOfInt = this.bits;
    int j = arrayOfInt.length;
    for (int k = 0; k < j; k++)
      i = arrayOfInt[k] + i * 31;
    return i;
  }

  public void set(int paramInt1, int paramInt2)
  {
    int i = paramInt2 * this.rowSize + (paramInt1 >> 5);
    int[] arrayOfInt = this.bits;
    arrayOfInt[i] |= 1 << (paramInt1 & 0x1F);
  }

  public void setRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 >= 0) && (paramInt1 >= 0))
    {
      if ((paramInt4 >= 1) && (paramInt3 >= 1))
      {
        int i = paramInt3 + paramInt1;
        int j = paramInt4 + paramInt2;
        if ((j <= this.height) && (i <= this.width))
        {
          while (paramInt2 < j)
          {
            int k = paramInt2 * this.rowSize;
            for (int m = paramInt1; m < i; m++)
            {
              int[] arrayOfInt = this.bits;
              int n = k + (m >> 5);
              arrayOfInt[n] |= 1 << (m & 0x1F);
            }
            paramInt2++;
          }
          return;
        }
        throw new IllegalArgumentException("The region must fit inside the matrix");
      }
      throw new IllegalArgumentException("Height and width must be at least 1");
    }
    throw new IllegalArgumentException("Left and top must be nonnegative");
  }

  public void setRow(int paramInt, BitArray paramBitArray)
  {
    System.arraycopy(paramBitArray.getBitArray(), 0, this.bits, paramInt * this.rowSize, this.rowSize);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(this.height * (1 + this.width));
    for (int i = 0; i < this.height; i++)
    {
      for (int j = 0; j < this.width; j++)
      {
        String str;
        if (get(j, i))
          str = "X ";
        else
          str = "  ";
        localStringBuilder.append(str);
      }
      localStringBuilder.append('\n');
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitMatrix
 * JD-Core Version:    0.6.1
 */