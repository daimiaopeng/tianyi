package com.google.zxing.common;

public final class BitSource
{
  private int bitOffset;
  private int byteOffset;
  private final byte[] bytes;

  public BitSource(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
  }

  public int available()
  {
    return 8 * (this.bytes.length - this.byteOffset) - this.bitOffset;
  }

  public int getBitOffset()
  {
    return this.bitOffset;
  }

  public int getByteOffset()
  {
    return this.byteOffset;
  }

  public int readBits(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 32) && (paramInt <= available()))
    {
      int i;
      if (this.bitOffset > 0)
      {
        int m = 8 - this.bitOffset;
        int n;
        if (paramInt < m)
          n = paramInt;
        else
          n = m;
        int i1 = m - n;
        i = (255 >> 8 - n << i1 & this.bytes[this.byteOffset]) >> i1;
        paramInt -= n;
        this.bitOffset = (n + this.bitOffset);
        if (this.bitOffset == 8)
        {
          this.bitOffset = 0;
          this.byteOffset = (1 + this.byteOffset);
        }
      }
      else
      {
        i = 0;
      }
      if (paramInt > 0)
      {
        while (paramInt >= 8)
        {
          i = i << 8 | 0xFF & this.bytes[this.byteOffset];
          this.byteOffset = (1 + this.byteOffset);
          paramInt -= 8;
        }
        if (paramInt > 0)
        {
          int j = 8 - paramInt;
          int k = 255 >> j << j;
          i = i << paramInt | (k & this.bytes[this.byteOffset]) >> j;
          this.bitOffset = (paramInt + this.bitOffset);
        }
      }
      return i;
    }
    throw new IllegalArgumentException(String.valueOf(paramInt));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitSource
 * JD-Core Version:    0.6.1
 */