package com.google.zxing.datamatrix.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;

  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }

  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion)
  {
    Version.ECBlocks localECBlocks = paramVersion.getECBlocks();
    Version.ECB[] arrayOfECB = localECBlocks.getECBlocks();
    int i = arrayOfECB.length;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += arrayOfECB[j].getCount();
      j++;
    }
    DataBlock[] arrayOfDataBlock = new DataBlock[k];
    int m = arrayOfECB.length;
    int n = 0;
    int i18;
    for (int i1 = 0; n < m; i1 = i18)
    {
      Version.ECB localECB = arrayOfECB[n];
      i18 = i1;
      int i19 = 0;
      while (i19 < localECB.getCount())
      {
        int i20 = localECB.getDataCodewords();
        int i21 = i20 + localECBlocks.getECCodewords();
        int i22 = i18 + 1;
        arrayOfDataBlock[i18] = new DataBlock(i20, new byte[i21]);
        i19++;
        i18 = i22;
      }
      n++;
    }
    int i2 = arrayOfDataBlock[0].codewords.length - localECBlocks.getECCodewords();
    int i3 = i2 - 1;
    int i4 = 0;
    int i15;
    for (int i5 = 0; i4 < i3; i5 = i15)
    {
      i15 = i5;
      int i16 = 0;
      while (i16 < i1)
      {
        byte[] arrayOfByte3 = arrayOfDataBlock[i16].codewords;
        int i17 = i15 + 1;
        arrayOfByte3[i4] = paramArrayOfByte[i15];
        i16++;
        i15 = i17;
      }
      i4++;
    }
    int i6;
    if (paramVersion.getVersionNumber() == 24)
      i6 = 1;
    else
      i6 = 0;
    int i7;
    if (i6 != 0)
      i7 = 8;
    else
      i7 = i1;
    int i8 = i5;
    int i9 = 0;
    while (i9 < i7)
    {
      byte[] arrayOfByte2 = arrayOfDataBlock[i9].codewords;
      int i14 = i8 + 1;
      arrayOfByte2[i3] = paramArrayOfByte[i8];
      i9++;
      i8 = i14;
    }
    int i10 = arrayOfDataBlock[0].codewords.length;
    while (i2 < i10)
    {
      int i11 = 0;
      while (i11 < i1)
      {
        int i12;
        if ((i6 != 0) && (i11 > 7))
          i12 = i2 - 1;
        else
          i12 = i2;
        byte[] arrayOfByte1 = arrayOfDataBlock[i11].codewords;
        int i13 = i8 + 1;
        arrayOfByte1[i12] = paramArrayOfByte[i8];
        i11++;
        i8 = i13;
      }
      i2++;
    }
    if (i8 != paramArrayOfByte.length)
      throw new IllegalArgumentException();
    return arrayOfDataBlock;
  }

  byte[] getCodewords()
  {
    return this.codewords;
  }

  int getNumDataCodewords()
  {
    return this.numDataCodewords;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.decoder.DataBlock
 * JD-Core Version:    0.6.1
 */