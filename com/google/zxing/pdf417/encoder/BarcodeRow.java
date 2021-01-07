package com.google.zxing.pdf417.encoder;

final class BarcodeRow
{
  private int currentLocation;
  private final byte[] row;

  BarcodeRow(int paramInt)
  {
    this.row = new byte[paramInt];
    this.currentLocation = 0;
  }

  void addBar(boolean paramBoolean, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
    {
      int j = this.currentLocation;
      this.currentLocation = (j + 1);
      set(j, paramBoolean);
    }
  }

  byte[] getRow()
  {
    return this.row;
  }

  byte[] getScaledRow(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt * this.row.length];
    for (int i = 0; i < arrayOfByte.length; i++)
      arrayOfByte[i] = this.row[(i / paramInt)];
    return arrayOfByte;
  }

  void set(int paramInt, byte paramByte)
  {
    this.row[paramInt] = paramByte;
  }

  void set(int paramInt, boolean paramBoolean)
  {
    this.row[paramInt] = (byte)paramBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.encoder.BarcodeRow
 * JD-Core Version:    0.6.1
 */