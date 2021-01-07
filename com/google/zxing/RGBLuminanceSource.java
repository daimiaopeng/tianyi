package com.google.zxing;

public final class RGBLuminanceSource extends LuminanceSource
{
  private final int dataHeight;
  private final int dataWidth;
  private final int left;
  private final byte[] luminances;
  private final int top;

  public RGBLuminanceSource(int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    super(paramInt1, paramInt2);
    this.dataWidth = paramInt1;
    this.dataHeight = paramInt2;
    this.left = 0;
    this.top = 0;
    this.luminances = new byte[paramInt1 * paramInt2];
    for (int i = 0; i < paramInt2; i++)
    {
      int j = i * paramInt1;
      for (int k = 0; k < paramInt1; k++)
      {
        int m = j + k;
        int n = paramArrayOfInt[m];
        int i1 = 0xFF & n >> 16;
        int i2 = 0xFF & n >> 8;
        int i3 = n & 0xFF;
        if ((i1 == i2) && (i2 == i3))
          this.luminances[m] = (byte)i1;
        else
          this.luminances[m] = (byte)(i3 + (i2 + (i1 + i2)) >> 2);
      }
    }
  }

  private RGBLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super(paramInt5, paramInt6);
    if ((paramInt5 + paramInt3 <= paramInt1) && (paramInt6 + paramInt4 <= paramInt2))
    {
      this.luminances = paramArrayOfByte;
      this.dataWidth = paramInt1;
      this.dataHeight = paramInt2;
      this.left = paramInt3;
      this.top = paramInt4;
      return;
    }
    throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
  }

  public LuminanceSource crop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RGBLuminanceSource localRGBLuminanceSource = new RGBLuminanceSource(this.luminances, this.dataWidth, this.dataHeight, paramInt1 + this.left, paramInt2 + this.top, paramInt3, paramInt4);
    return localRGBLuminanceSource;
  }

  public byte[] getMatrix()
  {
    int i = getWidth();
    int j = getHeight();
    if ((i == this.dataWidth) && (j == this.dataHeight))
      return this.luminances;
    int k = i * j;
    byte[] arrayOfByte1 = new byte[k];
    int m = this.top * this.dataWidth + this.left;
    int n = this.dataWidth;
    int i1 = 0;
    if (i == n)
    {
      System.arraycopy(this.luminances, m, arrayOfByte1, 0, k);
      return arrayOfByte1;
    }
    byte[] arrayOfByte2 = this.luminances;
    while (i1 < j)
    {
      System.arraycopy(arrayOfByte2, m, arrayOfByte1, i1 * i, i);
      m += this.dataWidth;
      i1++;
    }
    return arrayOfByte1;
  }

  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramInt >= 0) && (paramInt < getHeight()))
    {
      int i = getWidth();
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i))
        paramArrayOfByte = new byte[i];
      int j = (paramInt + this.top) * this.dataWidth + this.left;
      System.arraycopy(this.luminances, j, paramArrayOfByte, 0, i);
      return paramArrayOfByte;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Requested row is outside the image: ");
    localStringBuilder.append(paramInt);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public boolean isCropSupported()
  {
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.RGBLuminanceSource
 * JD-Core Version:    0.6.1
 */