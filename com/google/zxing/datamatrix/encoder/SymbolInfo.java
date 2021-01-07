package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;

public class SymbolInfo
{
  public static final SymbolInfo[] PROD_SYMBOLS = arrayOfSymbolInfo;
  private static SymbolInfo[] symbols = PROD_SYMBOLS;
  final int dataCapacity;
  private final int dataRegions;
  final int errorCodewords;
  public final int matrixHeight;
  public final int matrixWidth;
  private final boolean rectangular;
  int rsBlockData;
  int rsBlockError;

  static
  {
    SymbolInfo[] arrayOfSymbolInfo = new SymbolInfo[30];
    SymbolInfo localSymbolInfo1 = new SymbolInfo(false, 3, 5, 8, 8, 1);
    arrayOfSymbolInfo[0] = localSymbolInfo1;
    SymbolInfo localSymbolInfo2 = new SymbolInfo(false, 5, 7, 10, 10, 1);
    arrayOfSymbolInfo[1] = localSymbolInfo2;
    SymbolInfo localSymbolInfo3 = new SymbolInfo(true, 5, 7, 16, 6, 1);
    arrayOfSymbolInfo[2] = localSymbolInfo3;
    SymbolInfo localSymbolInfo4 = new SymbolInfo(false, 8, 10, 12, 12, 1);
    arrayOfSymbolInfo[3] = localSymbolInfo4;
    SymbolInfo localSymbolInfo5 = new SymbolInfo(true, 10, 11, 14, 6, 2);
    arrayOfSymbolInfo[4] = localSymbolInfo5;
    SymbolInfo localSymbolInfo6 = new SymbolInfo(false, 12, 12, 14, 14, 1);
    arrayOfSymbolInfo[5] = localSymbolInfo6;
    SymbolInfo localSymbolInfo7 = new SymbolInfo(true, 16, 14, 24, 10, 1);
    arrayOfSymbolInfo[6] = localSymbolInfo7;
    SymbolInfo localSymbolInfo8 = new SymbolInfo(false, 18, 14, 16, 16, 1);
    arrayOfSymbolInfo[7] = localSymbolInfo8;
    SymbolInfo localSymbolInfo9 = new SymbolInfo(false, 22, 18, 18, 18, 1);
    arrayOfSymbolInfo[8] = localSymbolInfo9;
    SymbolInfo localSymbolInfo10 = new SymbolInfo(true, 22, 18, 16, 10, 2);
    arrayOfSymbolInfo[9] = localSymbolInfo10;
    SymbolInfo localSymbolInfo11 = new SymbolInfo(false, 30, 20, 20, 20, 1);
    arrayOfSymbolInfo[10] = localSymbolInfo11;
    SymbolInfo localSymbolInfo12 = new SymbolInfo(true, 32, 24, 16, 14, 2);
    arrayOfSymbolInfo[11] = localSymbolInfo12;
    SymbolInfo localSymbolInfo13 = new SymbolInfo(false, 36, 24, 22, 22, 1);
    arrayOfSymbolInfo[12] = localSymbolInfo13;
    SymbolInfo localSymbolInfo14 = new SymbolInfo(false, 44, 28, 24, 24, 1);
    arrayOfSymbolInfo[13] = localSymbolInfo14;
    SymbolInfo localSymbolInfo15 = new SymbolInfo(true, 49, 28, 22, 14, 2);
    arrayOfSymbolInfo[14] = localSymbolInfo15;
    SymbolInfo localSymbolInfo16 = new SymbolInfo(false, 62, 36, 14, 14, 4);
    arrayOfSymbolInfo[15] = localSymbolInfo16;
    SymbolInfo localSymbolInfo17 = new SymbolInfo(false, 86, 42, 16, 16, 4);
    arrayOfSymbolInfo[16] = localSymbolInfo17;
    SymbolInfo localSymbolInfo18 = new SymbolInfo(false, 114, 48, 18, 18, 4);
    arrayOfSymbolInfo[17] = localSymbolInfo18;
    SymbolInfo localSymbolInfo19 = new SymbolInfo(false, 144, 56, 20, 20, 4);
    arrayOfSymbolInfo[18] = localSymbolInfo19;
    SymbolInfo localSymbolInfo20 = new SymbolInfo(false, 174, 68, 22, 22, 4);
    arrayOfSymbolInfo[19] = localSymbolInfo20;
    SymbolInfo localSymbolInfo21 = new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42);
    arrayOfSymbolInfo[20] = localSymbolInfo21;
    SymbolInfo localSymbolInfo22 = new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56);
    arrayOfSymbolInfo[21] = localSymbolInfo22;
    SymbolInfo localSymbolInfo23 = new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36);
    arrayOfSymbolInfo[22] = localSymbolInfo23;
    SymbolInfo localSymbolInfo24 = new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48);
    arrayOfSymbolInfo[23] = localSymbolInfo24;
    SymbolInfo localSymbolInfo25 = new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56);
    arrayOfSymbolInfo[24] = localSymbolInfo25;
    SymbolInfo localSymbolInfo26 = new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68);
    arrayOfSymbolInfo[25] = localSymbolInfo26;
    SymbolInfo localSymbolInfo27 = new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56);
    arrayOfSymbolInfo[26] = localSymbolInfo27;
    SymbolInfo localSymbolInfo28 = new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68);
    arrayOfSymbolInfo[27] = localSymbolInfo28;
    SymbolInfo localSymbolInfo29 = new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62);
    arrayOfSymbolInfo[28] = localSymbolInfo29;
    arrayOfSymbolInfo[29] = new DataMatrixSymbolInfo144();
  }

  public SymbolInfo(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt1, paramInt2);
  }

  private SymbolInfo(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    this.rectangular = paramBoolean;
    this.dataCapacity = paramInt1;
    this.errorCodewords = paramInt2;
    this.matrixWidth = paramInt3;
    this.matrixHeight = paramInt4;
    this.dataRegions = paramInt5;
    this.rsBlockData = paramInt6;
    this.rsBlockError = paramInt7;
  }

  public static SymbolInfo lookup(int paramInt)
  {
    return lookup(paramInt, SymbolShapeHint.FORCE_NONE, true);
  }

  public static SymbolInfo lookup(int paramInt, SymbolShapeHint paramSymbolShapeHint)
  {
    return lookup(paramInt, paramSymbolShapeHint, true);
  }

  public static SymbolInfo lookup(int paramInt, SymbolShapeHint paramSymbolShapeHint, Dimension paramDimension1, Dimension paramDimension2, boolean paramBoolean)
  {
    for (SymbolInfo localSymbolInfo : symbols)
      if (((paramSymbolShapeHint != SymbolShapeHint.FORCE_SQUARE) || (!localSymbolInfo.rectangular)) && ((paramSymbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE) || (localSymbolInfo.rectangular)) && ((paramDimension1 == null) || ((localSymbolInfo.getSymbolWidth() >= paramDimension1.getWidth()) && (localSymbolInfo.getSymbolHeight() >= paramDimension1.getHeight()))) && ((paramDimension2 == null) || ((localSymbolInfo.getSymbolWidth() <= paramDimension2.getWidth()) && (localSymbolInfo.getSymbolHeight() <= paramDimension2.getHeight()))) && (paramInt <= localSymbolInfo.dataCapacity))
        return localSymbolInfo;
    if (paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can't find a symbol arrangement that matches the message. Data codewords: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return null;
  }

  private static SymbolInfo lookup(int paramInt, SymbolShapeHint paramSymbolShapeHint, boolean paramBoolean)
  {
    return lookup(paramInt, paramSymbolShapeHint, null, null, paramBoolean);
  }

  public static SymbolInfo lookup(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    SymbolShapeHint localSymbolShapeHint;
    if (paramBoolean1)
      localSymbolShapeHint = SymbolShapeHint.FORCE_NONE;
    else
      localSymbolShapeHint = SymbolShapeHint.FORCE_SQUARE;
    return lookup(paramInt, localSymbolShapeHint, paramBoolean2);
  }

  public static void overrideSymbolSet(SymbolInfo[] paramArrayOfSymbolInfo)
  {
    symbols = paramArrayOfSymbolInfo;
  }

  public int getCodewordCount()
  {
    return this.dataCapacity + this.errorCodewords;
  }

  public int getDataLengthForInterleavedBlock(int paramInt)
  {
    return this.rsBlockData;
  }

  public final int getErrorLengthForInterleavedBlock(int paramInt)
  {
    return this.rsBlockError;
  }

  final int getHorizontalDataRegions()
  {
    int i = this.dataRegions;
    if (i != 4)
    {
      if (i != 16)
      {
        if (i != 36)
        {
          switch (i)
          {
          default:
            throw new IllegalStateException("Cannot handle this number of data regions");
          case 2:
            return 2;
          case 1:
          }
          return 1;
        }
        return 6;
      }
      return 4;
    }
    return 2;
  }

  public int getInterleavedBlockCount()
  {
    return this.dataCapacity / this.rsBlockData;
  }

  public final int getSymbolDataHeight()
  {
    return getVerticalDataRegions() * this.matrixHeight;
  }

  public final int getSymbolDataWidth()
  {
    return getHorizontalDataRegions() * this.matrixWidth;
  }

  public final int getSymbolHeight()
  {
    return getSymbolDataHeight() + 2 * getVerticalDataRegions();
  }

  public final int getSymbolWidth()
  {
    return getSymbolDataWidth() + 2 * getHorizontalDataRegions();
  }

  final int getVerticalDataRegions()
  {
    int i = this.dataRegions;
    if (i != 4)
    {
      if (i != 16)
      {
        if (i != 36)
        {
          switch (i)
          {
          default:
            throw new IllegalStateException("Cannot handle this number of data regions");
          case 2:
            return 1;
          case 1:
          }
          return 1;
        }
        return 6;
      }
      return 4;
    }
    return 2;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    if (this.rectangular)
      str = "Rectangular Symbol:";
    else
      str = "Square Symbol:";
    localStringBuilder.append(str);
    localStringBuilder.append(" data region ");
    localStringBuilder.append(this.matrixWidth);
    localStringBuilder.append('x');
    localStringBuilder.append(this.matrixHeight);
    localStringBuilder.append(", symbol size ");
    localStringBuilder.append(getSymbolWidth());
    localStringBuilder.append('x');
    localStringBuilder.append(getSymbolHeight());
    localStringBuilder.append(", symbol data size ");
    localStringBuilder.append(getSymbolDataWidth());
    localStringBuilder.append('x');
    localStringBuilder.append(getSymbolDataHeight());
    localStringBuilder.append(", codewords ");
    localStringBuilder.append(this.dataCapacity);
    localStringBuilder.append('+');
    localStringBuilder.append(this.errorCodewords);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.SymbolInfo
 * JD-Core Version:    0.6.1
 */