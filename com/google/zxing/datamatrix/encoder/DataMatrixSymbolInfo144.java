package com.google.zxing.datamatrix.encoder;

final class DataMatrixSymbolInfo144 extends SymbolInfo
{
  DataMatrixSymbolInfo144()
  {
    super(false, 1558, 620, 22, 22, 36);
    this.rsBlockData = -1;
    this.rsBlockError = 62;
  }

  public int getDataLengthForInterleavedBlock(int paramInt)
  {
    int i;
    if (paramInt <= 8)
      i = 156;
    else
      i = 155;
    return i;
  }

  public int getInterleavedBlockCount()
  {
    return 10;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.DataMatrixSymbolInfo144
 * JD-Core Version:    0.6.1
 */