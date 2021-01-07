package com.google.zxing.qrcode.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;

  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }

  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    if (paramArrayOfByte.length != paramVersion.getTotalCodewords())
      throw new IllegalArgumentException();
    Version.ECBlocks localECBlocks = paramVersion.getECBlocksForLevel(paramErrorCorrectionLevel);
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
        int i21 = i20 + localECBlocks.getECCodewordsPerBlock();
        int i22 = i18 + 1;
        arrayOfDataBlock[i18] = new DataBlock(i20, new byte[i21]);
        i19++;
        i18 = i22;
      }
      n++;
    }
    int i2 = arrayOfDataBlock[0].codewords.length;
    for (int i3 = -1 + arrayOfDataBlock.length; (i3 >= 0) && (arrayOfDataBlock[i3].codewords.length != i2); i3--);
    int i4 = i3 + 1;
    int i5 = i2 - localECBlocks.getECCodewordsPerBlock();
    int i6 = 0;
    int i15;
    for (int i7 = 0; i6 < i5; i7 = i15)
    {
      i15 = i7;
      int i16 = 0;
      while (i16 < i1)
      {
        byte[] arrayOfByte3 = arrayOfDataBlock[i16].codewords;
        int i17 = i15 + 1;
        arrayOfByte3[i6] = paramArrayOfByte[i15];
        i16++;
        i15 = i17;
      }
      i6++;
    }
    int i8 = i4;
    while (i8 < i1)
    {
      byte[] arrayOfByte2 = arrayOfDataBlock[i8].codewords;
      int i14 = i7 + 1;
      arrayOfByte2[i5] = paramArrayOfByte[i7];
      i8++;
      i7 = i14;
    }
    int i9 = arrayOfDataBlock[0].codewords.length;
    while (i5 < i9)
    {
      int i10 = i7;
      int i11 = 0;
      while (i11 < i1)
      {
        int i12;
        if (i11 < i4)
          i12 = i5;
        else
          i12 = i5 + 1;
        byte[] arrayOfByte1 = arrayOfDataBlock[i11].codewords;
        int i13 = i10 + 1;
        arrayOfByte1[i12] = paramArrayOfByte[i10];
        i11++;
        i10 = i13;
      }
      i5++;
      i7 = i10;
    }
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
 * Qualified Name:     com.google.zxing.qrcode.decoder.DataBlock
 * JD-Core Version:    0.6.1
 */