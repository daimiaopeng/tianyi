package com.google.zxing.qrcode.encoder;

import B;
import java.lang.reflect.Array;

public final class ByteMatrix
{
  private final byte[][] bytes;
  private final int height;
  private final int width;

  public ByteMatrix(int paramInt1, int paramInt2)
  {
    this.bytes = ((byte[][])Array.newInstance(B.class, new int[] { paramInt2, paramInt1 }));
    this.width = paramInt1;
    this.height = paramInt2;
  }

  public void clear(byte paramByte)
  {
    for (int i = 0; i < this.height; i++)
      for (int j = 0; j < this.width; j++)
        this.bytes[i][j] = paramByte;
  }

  public byte get(int paramInt1, int paramInt2)
  {
    return this.bytes[paramInt2][paramInt1];
  }

  public byte[][] getArray()
  {
    return this.bytes;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void set(int paramInt1, int paramInt2, byte paramByte)
  {
    this.bytes[paramInt2][paramInt1] = paramByte;
  }

  public void set(int paramInt1, int paramInt2, int paramInt3)
  {
    this.bytes[paramInt2][paramInt1] = (byte)paramInt3;
  }

  public void set(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.bytes[paramInt2][paramInt1] = (byte)paramBoolean;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(2 + 2 * this.width * this.height);
    for (int i = 0; i < this.height; i++)
    {
      for (int j = 0; j < this.width; j++)
        switch (this.bytes[i][j])
        {
        default:
          localStringBuilder.append("  ");
          break;
        case 1:
          localStringBuilder.append(" 1");
          break;
        case 0:
          localStringBuilder.append(" 0");
        }
      localStringBuilder.append('\n');
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.ByteMatrix
 * JD-Core Version:    0.6.1
 */