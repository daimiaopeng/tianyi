package com.google.zxing;

public final class PlanarYUVLuminanceSource extends LuminanceSource
{
  private static final int THUMBNAIL_SCALE_FACTOR = 2;
  private final int dataHeight;
  private final int dataWidth;
  private final int left;
  private final int top;
  private final byte[] yuvData;

  public PlanarYUVLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    super(paramInt5, paramInt6);
    if ((paramInt3 + paramInt5 <= paramInt1) && (paramInt4 + paramInt6 <= paramInt2))
    {
      this.yuvData = paramArrayOfByte;
      this.dataWidth = paramInt1;
      this.dataHeight = paramInt2;
      this.left = paramInt3;
      this.top = paramInt4;
      if (paramBoolean)
        reverseHorizontal(paramInt5, paramInt6);
      return;
    }
    throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
  }

  private void reverseHorizontal(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = this.yuvData;
    int i = this.top * this.dataWidth + this.left;
    int j = 0;
    while (j < paramInt2)
    {
      int k = i + paramInt1 / 2;
      int m = -1 + (i + paramInt1);
      int n = i;
      while (n < k)
      {
        int i1 = arrayOfByte[n];
        arrayOfByte[n] = arrayOfByte[m];
        arrayOfByte[m] = i1;
        n++;
        m--;
      }
      j++;
      i += this.dataWidth;
    }
  }

  public LuminanceSource crop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    PlanarYUVLuminanceSource localPlanarYUVLuminanceSource = new PlanarYUVLuminanceSource(this.yuvData, this.dataWidth, this.dataHeight, paramInt1 + this.left, paramInt2 + this.top, paramInt3, paramInt4, false);
    return localPlanarYUVLuminanceSource;
  }

  public byte[] getMatrix()
  {
    int i = getWidth();
    int j = getHeight();
    if ((i == this.dataWidth) && (j == this.dataHeight))
      return this.yuvData;
    int k = i * j;
    byte[] arrayOfByte1 = new byte[k];
    int m = this.top * this.dataWidth + this.left;
    int n = this.dataWidth;
    int i1 = 0;
    if (i == n)
    {
      System.arraycopy(this.yuvData, m, arrayOfByte1, 0, k);
      return arrayOfByte1;
    }
    byte[] arrayOfByte2 = this.yuvData;
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
      System.arraycopy(this.yuvData, j, paramArrayOfByte, 0, i);
      return paramArrayOfByte;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Requested row is outside the image: ");
    localStringBuilder.append(paramInt);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public int getThumbnailHeight()
  {
    return getHeight() / 2;
  }

  public int getThumbnailWidth()
  {
    return getWidth() / 2;
  }

  public boolean isCropSupported()
  {
    return true;
  }

  public int[] renderThumbnail()
  {
    int i = getWidth() / 2;
    int j = getHeight() / 2;
    int[] arrayOfInt = new int[i * j];
    byte[] arrayOfByte = this.yuvData;
    int k = this.top * this.dataWidth + this.left;
    for (int m = 0; m < j; m++)
    {
      int n = m * i;
      for (int i1 = 0; i1 < i; i1++)
      {
        int i2 = 0xFF & arrayOfByte[(k + i1 * 2)];
        arrayOfInt[(n + i1)] = (0xFF000000 | i2 * 65793);
      }
      k += 2 * this.dataWidth;
    }
    return arrayOfInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.PlanarYUVLuminanceSource
 * JD-Core Version:    0.6.1
 */