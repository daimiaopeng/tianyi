package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

abstract class DataMask
{
  private static final DataMask[] DATA_MASKS = arrayOfDataMask;

  static
  {
    DataMask[] arrayOfDataMask = new DataMask[8];
    arrayOfDataMask[0] = new DataMask000(null);
    arrayOfDataMask[1] = new DataMask001(null);
    arrayOfDataMask[2] = new DataMask010(null);
    arrayOfDataMask[3] = new DataMask011(null);
    arrayOfDataMask[4] = new DataMask100(null);
    arrayOfDataMask[5] = new DataMask101(null);
    arrayOfDataMask[6] = new DataMask110(null);
    arrayOfDataMask[7] = new DataMask111(null);
  }

  static DataMask forReference(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= 7))
      return DATA_MASKS[paramInt];
    throw new IllegalArgumentException();
  }

  abstract boolean isMasked(int paramInt1, int paramInt2);

  final void unmaskBitMatrix(BitMatrix paramBitMatrix, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
      for (int j = 0; j < paramInt; j++)
        if (isMasked(i, j))
          paramBitMatrix.flip(j, i);
  }

  private static final class DataMask000 extends DataMask
  {
    private DataMask000()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      int i = paramInt1 + paramInt2;
      int j = 1;
      if ((i & j) != 0)
        j = 0;
      return j;
    }
  }

  private static final class DataMask001 extends DataMask
  {
    private DataMask001()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      int i = 1;
      if ((paramInt1 & i) != 0)
        i = 0;
      return i;
    }
  }

  private static final class DataMask010 extends DataMask
  {
    private DataMask010()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      boolean bool;
      if (paramInt2 % 3 == 0)
        bool = true;
      else
        bool = false;
      return bool;
    }
  }

  private static final class DataMask011 extends DataMask
  {
    private DataMask011()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      boolean bool;
      if ((paramInt1 + paramInt2) % 3 == 0)
        bool = true;
      else
        bool = false;
      return bool;
    }
  }

  private static final class DataMask100 extends DataMask
  {
    private DataMask100()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      int i = 1;
      if ((i & (paramInt1 >>> i) + paramInt2 / 3) != 0)
        i = 0;
      return i;
    }
  }

  private static final class DataMask101 extends DataMask
  {
    private DataMask101()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      int i = paramInt1 * paramInt2;
      boolean bool;
      if ((i & 0x1) + i % 3 == 0)
        bool = true;
      else
        bool = false;
      return bool;
    }
  }

  private static final class DataMask110 extends DataMask
  {
    private DataMask110()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      int i = paramInt1 * paramInt2;
      int j = (i & 0x1) + i % 3;
      int k = 1;
      if ((j & k) != 0)
        k = 0;
      return k;
    }
  }

  private static final class DataMask111 extends DataMask
  {
    private DataMask111()
    {
      super();
    }

    boolean isMasked(int paramInt1, int paramInt2)
    {
      int i = paramInt1 + paramInt2;
      int j = 1;
      if ((0x1 & (i & j) + paramInt1 * paramInt2 % 3) != 0)
        j = 0;
      return j;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.DataMask
 * JD-Core Version:    0.6.1
 */