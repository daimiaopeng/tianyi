package com.google.zxing.common;

public final class BitArray
{
  private int[] bits;
  private int size;

  public BitArray()
  {
    this.size = 0;
    this.bits = new int[1];
  }

  public BitArray(int paramInt)
  {
    this.size = paramInt;
    this.bits = makeArray(paramInt);
  }

  private void ensureCapacity(int paramInt)
  {
    if (paramInt > this.bits.length << 5)
    {
      int[] arrayOfInt = makeArray(paramInt);
      System.arraycopy(this.bits, 0, arrayOfInt, 0, this.bits.length);
      this.bits = arrayOfInt;
    }
  }

  private static int[] makeArray(int paramInt)
  {
    return new int[paramInt + 31 >> 5];
  }

  public void appendBit(boolean paramBoolean)
  {
    ensureCapacity(1 + this.size);
    if (paramBoolean)
    {
      int[] arrayOfInt = this.bits;
      int i = this.size >> 5;
      arrayOfInt[i] |= 1 << (0x1F & this.size);
    }
    this.size = (1 + this.size);
  }

  public void appendBitArray(BitArray paramBitArray)
  {
    int i = paramBitArray.size;
    ensureCapacity(i + this.size);
    for (int j = 0; j < i; j++)
      appendBit(paramBitArray.get(j));
  }

  public void appendBits(int paramInt1, int paramInt2)
  {
    if ((paramInt2 >= 0) && (paramInt2 <= 32))
    {
      ensureCapacity(paramInt2 + this.size);
      while (paramInt2 > 0)
      {
        int i = paramInt1 >> paramInt2 - 1;
        int j = 1;
        if ((i & j) != j)
          j = 0;
        appendBit(j);
        paramInt2--;
      }
      return;
    }
    throw new IllegalArgumentException("Num bits must be between 0 and 32");
  }

  public void clear()
  {
    int i = this.bits.length;
    for (int j = 0; j < i; j++)
      this.bits[j] = 0;
  }

  public void flip(int paramInt)
  {
    int[] arrayOfInt = this.bits;
    int i = paramInt >> 5;
    arrayOfInt[i] ^= 1 << (paramInt & 0x1F);
  }

  public boolean get(int paramInt)
  {
    int i = this.bits[(paramInt >> 5)];
    int j = paramInt & 0x1F;
    int k = 1;
    if ((i & k << j) == 0)
      k = 0;
    return k;
  }

  public int[] getBitArray()
  {
    return this.bits;
  }

  public int getNextSet(int paramInt)
  {
    if (paramInt >= this.size)
      return this.size;
    int i = paramInt >> 5;
    for (int j = this.bits[i] & (0xFFFFFFFF ^ (1 << (paramInt & 0x1F)) - 1); j == 0; j = this.bits[i])
    {
      i++;
      if (i == this.bits.length)
        return this.size;
    }
    int k = (i << 5) + Integer.numberOfTrailingZeros(j);
    if (k > this.size)
      k = this.size;
    return k;
  }

  public int getNextUnset(int paramInt)
  {
    if (paramInt >= this.size)
      return this.size;
    int i = paramInt >> 5;
    for (int j = (0xFFFFFFFF ^ this.bits[i]) & (0xFFFFFFFF ^ (1 << (paramInt & 0x1F)) - 1); j == 0; j = 0xFFFFFFFF ^ this.bits[i])
    {
      i++;
      if (i == this.bits.length)
        return this.size;
    }
    int k = (i << 5) + Integer.numberOfTrailingZeros(j);
    if (k > this.size)
      k = this.size;
    return k;
  }

  public int getSize()
  {
    return this.size;
  }

  public int getSizeInBytes()
  {
    return 7 + this.size >> 3;
  }

  public boolean isRange(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt2 < paramInt1)
      throw new IllegalArgumentException();
    if (paramInt2 == paramInt1)
      return true;
    int i = paramInt2 - 1;
    int j = paramInt1 >> 5;
    int k = i >> 5;
    for (int m = j; m <= k; m++)
    {
      int n;
      if (m > j)
        n = 0;
      else
        n = paramInt1 & 0x1F;
      int i1;
      if (m < k)
        i1 = 31;
      else
        i1 = i & 0x1F;
      int i2;
      if ((n == 0) && (i1 == 31))
      {
        i2 = -1;
      }
      else
      {
        i2 = 0;
        while (n <= i1)
        {
          i2 |= 1 << n;
          n++;
        }
      }
      int i3 = i2 & this.bits[m];
      if (!paramBoolean)
        i2 = 0;
      if (i3 != i2)
        return false;
    }
    return true;
  }

  public void reverse()
  {
    int[] arrayOfInt = new int[this.bits.length];
    int i = this.size;
    for (int j = 0; j < i; j++)
      if (get(i - j - 1))
      {
        int k = j >> 5;
        arrayOfInt[k] |= 1 << (j & 0x1F);
      }
    this.bits = arrayOfInt;
  }

  public void set(int paramInt)
  {
    int[] arrayOfInt = this.bits;
    int i = paramInt >> 5;
    arrayOfInt[i] |= 1 << (paramInt & 0x1F);
  }

  public void setBulk(int paramInt1, int paramInt2)
  {
    this.bits[(paramInt1 >> 5)] = paramInt2;
  }

  public void setRange(int paramInt1, int paramInt2)
  {
    if (paramInt2 < paramInt1)
      throw new IllegalArgumentException();
    if (paramInt2 == paramInt1)
      return;
    int i = paramInt2 - 1;
    int j = paramInt1 >> 5;
    int k = i >> 5;
    for (int m = j; m <= k; m++)
    {
      int n;
      if (m > j)
        n = 0;
      else
        n = paramInt1 & 0x1F;
      int i1;
      if (m < k)
        i1 = 31;
      else
        i1 = i & 0x1F;
      int i2 = 0;
      if (n == 0)
      {
        i2 = 0;
        if (i1 == 31)
        {
          i2 = -1;
          break label129;
        }
      }
      while (n <= i1)
      {
        i2 |= 1 << n;
        n++;
      }
      label129: int[] arrayOfInt = this.bits;
      arrayOfInt[m] = (i2 | arrayOfInt[m]);
    }
  }

  public void toBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int i = paramInt1;
    int j = 0;
    while (j < paramInt3)
    {
      int k = i;
      int m = 0;
      int n = 0;
      while (m < 8)
      {
        if (get(k))
          n |= 1 << 7 - m;
        k++;
        m++;
      }
      paramArrayOfByte[(paramInt2 + j)] = (byte)n;
      j++;
      i = k;
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(this.size);
    for (int i = 0; i < this.size; i++)
    {
      if ((i & 0x7) == 0)
        localStringBuilder.append(' ');
      char c;
      if (get(i))
        c = 'X';
      else
        c = '.';
      localStringBuilder.append(c);
    }
    return localStringBuilder.toString();
  }

  public void xor(BitArray paramBitArray)
  {
    if (this.bits.length != paramBitArray.bits.length)
      throw new IllegalArgumentException("Sizes don't match");
    for (int i = 0; i < this.bits.length; i++)
    {
      int[] arrayOfInt = this.bits;
      arrayOfInt[i] ^= paramBitArray.bits[i];
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitArray
 * JD-Core Version:    0.6.1
 */