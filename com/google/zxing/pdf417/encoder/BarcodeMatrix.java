package com.google.zxing.pdf417.encoder;

import B;
import java.lang.reflect.Array;

final class BarcodeMatrix
{
  private int currentRow;
  private final int height;
  private final BarcodeRow[] matrix;
  private final int width;

  BarcodeMatrix(int paramInt1, int paramInt2)
  {
    int i = paramInt1 + 2;
    this.matrix = new BarcodeRow[i];
    int j = this.matrix.length;
    for (int k = 0; k < j; k++)
      this.matrix[k] = new BarcodeRow(1 + 17 * (paramInt2 + 4));
    this.width = (paramInt2 * 17);
    this.height = i;
    this.currentRow = 0;
  }

  BarcodeRow getCurrentRow()
  {
    return this.matrix[this.currentRow];
  }

  byte[][] getMatrix()
  {
    return getScaledMatrix(1, 1);
  }

  byte[][] getScaledMatrix(int paramInt)
  {
    return getScaledMatrix(paramInt, paramInt);
  }

  byte[][] getScaledMatrix(int paramInt1, int paramInt2)
  {
    byte[][] arrayOfByte = (byte[][])Array.newInstance(B.class, new int[] { paramInt2 * this.height, paramInt1 * this.width });
    int i = paramInt2 * this.height;
    for (int j = 0; j < i; j++)
      arrayOfByte[(-1 + (i - j))] = this.matrix[(j / paramInt2)].getScaledRow(paramInt1);
    return arrayOfByte;
  }

  void set(int paramInt1, int paramInt2, byte paramByte)
  {
    this.matrix[paramInt2].set(paramInt1, paramByte);
  }

  void setMatrix(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    set(paramInt1, paramInt2, (byte)paramBoolean);
  }

  void startRow()
  {
    this.currentRow = (1 + this.currentRow);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.encoder.BarcodeMatrix
 * JD-Core Version:    0.6.1
 */