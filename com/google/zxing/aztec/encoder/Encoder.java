package com.google.zxing.aztec.encoder;

import I;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import java.lang.reflect.Array;
import java.util.Arrays;

public final class Encoder
{
  private static final int[][] CHAR_MAP = (int[][])Array.newInstance(I.class, new int[] { 5, 256 });
  public static final int DEFAULT_EC_PERCENT = 33;
  private static final int[][] LATCH_TABLE;
  private static final int[] NB_BITS;
  private static final int[] NB_BITS_COMPACT;
  private static final int[][] SHIFT_TABLE = (int[][])Array.newInstance(I.class, new int[] { 6, 6 });
  private static final int TABLE_BINARY = 5;
  private static final int TABLE_DIGIT = 2;
  private static final int TABLE_LOWER = 1;
  private static final int TABLE_MIXED = 3;
  private static final int TABLE_PUNCT = 4;
  private static final int TABLE_UPPER;
  private static final int[] WORD_SIZE = { 4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };

  static
  {
    LATCH_TABLE = (int[][])Array.newInstance(I.class, new int[] { 6, 6 });
    int[] arrayOfInt1 = CHAR_MAP[0];
    int i = 1;
    arrayOfInt1[32] = i;
    for (int j = 65; j <= 90; j++)
      CHAR_MAP[0][j] = (2 + (j - 65));
    CHAR_MAP[i][32] = i;
    for (int k = 97; k <= 122; k++)
      CHAR_MAP[i][k] = (2 + (k - 97));
    CHAR_MAP[2][32] = i;
    for (int m = 48; m <= 57; m++)
      CHAR_MAP[2][m] = (2 + (m - 48));
    CHAR_MAP[2][44] = 12;
    CHAR_MAP[2][46] = 13;
    int[] arrayOfInt2 = { 0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127 };
    for (int n = 0; n < arrayOfInt2.length; n++)
      CHAR_MAP[3][arrayOfInt2[n]] = n;
    int[] arrayOfInt3 = { 0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125 };
    for (int i1 = 0; i1 < arrayOfInt3.length; i1++)
      if (arrayOfInt3[i1] > 0)
        CHAR_MAP[4][arrayOfInt3[i1]] = i1;
    int[][] arrayOfInt4 = SHIFT_TABLE;
    int i2 = arrayOfInt4.length;
    for (int i3 = 0; i3 < i2; i3++)
      Arrays.fill(arrayOfInt4[i3], -1);
    int[][] arrayOfInt5 = LATCH_TABLE;
    int i4 = arrayOfInt5.length;
    for (int i5 = 0; i5 < i4; i5++)
      Arrays.fill(arrayOfInt5[i5], -1);
    SHIFT_TABLE[0][4] = 0;
    LATCH_TABLE[0][i] = 28;
    LATCH_TABLE[0][3] = 29;
    LATCH_TABLE[0][2] = 30;
    SHIFT_TABLE[0][5] = 31;
    SHIFT_TABLE[i][4] = 0;
    SHIFT_TABLE[i][0] = 28;
    LATCH_TABLE[i][3] = 29;
    LATCH_TABLE[i][2] = 30;
    SHIFT_TABLE[i][5] = 31;
    SHIFT_TABLE[3][4] = 0;
    LATCH_TABLE[3][i] = 28;
    LATCH_TABLE[3][0] = 29;
    LATCH_TABLE[3][4] = 30;
    SHIFT_TABLE[3][5] = 31;
    LATCH_TABLE[4][0] = 31;
    SHIFT_TABLE[2][4] = 0;
    LATCH_TABLE[2][0] = 30;
    SHIFT_TABLE[2][0] = 31;
    NB_BITS_COMPACT = new int[5];
    for (int i6 = 1; i6 < NB_BITS_COMPACT.length; i6++)
      NB_BITS_COMPACT[i6] = (i6 * (88 + i6 * 16));
    NB_BITS = new int[33];
    while (i < NB_BITS.length)
    {
      NB_BITS[i] = (i * (112 + i * 16));
      i++;
    }
  }

  static int[] bitsToWords(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[paramInt2];
    int i = paramBitArray.getSize() / paramInt1;
    for (int j = 0; j < i; j++)
    {
      int k = 0;
      int m = 0;
      while (k < paramInt1)
      {
        int n;
        if (paramBitArray.get(k + j * paramInt1))
          n = 1 << paramInt1 - k - 1;
        else
          n = 0;
        m |= n;
        k++;
      }
      arrayOfInt[j] = m;
    }
    return arrayOfInt;
  }

  static void drawBullsEye(BitMatrix paramBitMatrix, int paramInt1, int paramInt2)
  {
    for (int i = 0; i < paramInt2; i += 2)
    {
      int n = paramInt1 - i;
      for (int i1 = n; ; i1++)
      {
        int i2 = paramInt1 + i;
        if (i1 > i2)
          break;
        paramBitMatrix.set(i1, n);
        paramBitMatrix.set(i1, i2);
        paramBitMatrix.set(n, i1);
        paramBitMatrix.set(i2, i1);
      }
    }
    int j = paramInt1 - paramInt2;
    paramBitMatrix.set(j, j);
    int k = j + 1;
    paramBitMatrix.set(k, j);
    paramBitMatrix.set(j, k);
    int m = paramInt1 + paramInt2;
    paramBitMatrix.set(m, j);
    paramBitMatrix.set(m, k);
    paramBitMatrix.set(m, m - 1);
  }

  static void drawModeMessage(BitMatrix paramBitMatrix, boolean paramBoolean, int paramInt, BitArray paramBitArray)
  {
    int i = 0;
    if (paramBoolean)
      while (i < 7)
      {
        if (paramBitArray.get(i))
        {
          int i4 = paramInt / 2;
          paramBitMatrix.set(i + (i4 - 3), i4 - 5);
        }
        if (paramBitArray.get(i + 7))
        {
          int i3 = paramInt / 2;
          paramBitMatrix.set(i3 + 5, i + (i3 - 3));
        }
        if (paramBitArray.get(20 - i))
        {
          int i2 = paramInt / 2;
          paramBitMatrix.set(i + (i2 - 3), i2 + 5);
        }
        if (paramBitArray.get(27 - i))
        {
          int i1 = paramInt / 2;
          paramBitMatrix.set(i1 - 5, i + (i1 - 3));
        }
        i++;
      }
    while (i < 10)
    {
      if (paramBitArray.get(i))
      {
        int n = paramInt / 2;
        paramBitMatrix.set(i + (n - 5) + i / 5, n - 7);
      }
      if (paramBitArray.get(i + 10))
      {
        int m = paramInt / 2;
        paramBitMatrix.set(m + 7, i + (m - 5) + i / 5);
      }
      if (paramBitArray.get(29 - i))
      {
        int k = paramInt / 2;
        paramBitMatrix.set(i + (k - 5) + i / 5, k + 7);
      }
      if (paramBitArray.get(39 - i))
      {
        int j = paramInt / 2;
        paramBitMatrix.set(j - 7, i + (j - 5) + i / 5);
      }
      i++;
    }
  }

  public static AztecCode encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, 33);
  }

  public static AztecCode encode(byte[] paramArrayOfByte, int paramInt)
  {
    BitArray localBitArray1 = highLevelEncode(paramArrayOfByte);
    int i = 11 + paramInt * localBitArray1.getSize() / 100;
    int j = i + localBitArray1.getSize();
    int k = 0;
    BitArray localBitArray2 = null;
    int m = 1;
    int n = 0;
    int i1 = 0;
    while (m < NB_BITS_COMPACT.length)
    {
      if (NB_BITS_COMPACT[m] >= j)
      {
        if (n != WORD_SIZE[m])
        {
          n = WORD_SIZE[m];
          localBitArray2 = stuffBits(localBitArray1, n);
        }
        i1 = NB_BITS_COMPACT[m];
        if (i + localBitArray2.getSize() <= NB_BITS_COMPACT[m])
          break;
      }
      m++;
    }
    boolean bool;
    if (m == NB_BITS_COMPACT.length)
    {
      for (m = 1; m < NB_BITS.length; m++)
        if (NB_BITS[m] >= j)
        {
          if (n != WORD_SIZE[m])
          {
            n = WORD_SIZE[m];
            localBitArray2 = stuffBits(localBitArray1, n);
          }
          i1 = NB_BITS[m];
          if (i + localBitArray2.getSize() <= NB_BITS[m])
            break;
        }
      bool = false;
    }
    else
    {
      bool = true;
    }
    if (m == NB_BITS.length)
      throw new IllegalArgumentException("Data too large for an Aztec code");
    int i2 = (n + localBitArray2.getSize() - 1) / n;
    for (int i3 = i2 * n - localBitArray2.getSize(); i3 > 0; i3--)
      localBitArray2.appendBit(true);
    ReedSolomonEncoder localReedSolomonEncoder = new ReedSolomonEncoder(getGF(n));
    int i4 = i1 / n;
    int[] arrayOfInt1 = bitsToWords(localBitArray2, n, i4);
    localReedSolomonEncoder.encode(arrayOfInt1, i4 - i2);
    int i5 = i1 % n;
    BitArray localBitArray3 = new BitArray();
    localBitArray3.appendBits(0, i5);
    int i6 = arrayOfInt1.length;
    for (int i7 = 0; i7 < i6; i7++)
      localBitArray3.appendBits(arrayOfInt1[i7], n);
    BitArray localBitArray4 = generateModeMessage(bool, m, i2);
    int i8;
    if (bool)
      i8 = 11 + m * 4;
    else
      i8 = 14 + m * 4;
    int[] arrayOfInt2 = new int[i8];
    int i9 = 2;
    int i12;
    if (bool)
    {
      for (int i32 = 0; i32 < arrayOfInt2.length; i32++)
        arrayOfInt2[i32] = i32;
      i12 = i8;
    }
    else
    {
      int i10 = i8 + 1;
      int i11 = i8 / 2;
      i12 = i10 + 2 * ((i11 - 1) / 15);
      int i13 = i12 / 2;
      for (int i14 = 0; i14 < i11; i14++)
      {
        int i31 = i14 + i14 / 15;
        arrayOfInt2[(i11 - i14 - 1)] = (-1 + (i13 - i31));
        arrayOfInt2[(i11 + i14)] = (1 + (i31 + i13));
      }
    }
    BitMatrix localBitMatrix = new BitMatrix(i12);
    int i15 = 0;
    int i16 = 0;
    while (i15 < m)
    {
      int i23;
      if (bool)
        i23 = 9 + 4 * (m - i15);
      else
        i23 = 12 + 4 * (m - i15);
      int i24 = 0;
      while (i24 < i23)
      {
        int i25 = i24 * 2;
        while (k < i9)
        {
          if (localBitArray3.get(k + (i16 + i25)))
          {
            int i30 = i15 * 2;
            localBitMatrix.set(arrayOfInt2[(i30 + k)], arrayOfInt2[(i30 + i24)]);
          }
          if (localBitArray3.get(k + (i25 + (i16 + i23 * 2))))
          {
            int i29 = i15 * 2;
            localBitMatrix.set(arrayOfInt2[(i29 + i24)], arrayOfInt2[(i8 - 1 - i29 - k)]);
          }
          if (localBitArray3.get(k + (i25 + (i16 + i23 * 4))))
          {
            int i28 = i8 - 1 - i15 * 2;
            localBitMatrix.set(arrayOfInt2[(i28 - k)], arrayOfInt2[(i28 - i24)]);
          }
          if (localBitArray3.get(k + (i25 + (i16 + i23 * 6))))
          {
            int i26 = i8 - 1;
            int i27 = i15 * 2;
            localBitMatrix.set(arrayOfInt2[(i26 - i27 - i24)], arrayOfInt2[(i27 + k)]);
          }
          k++;
          i9 = 2;
        }
        i24++;
        k = 0;
        i9 = 2;
      }
      i16 += i23 * 8;
      i15++;
      k = 0;
      i9 = 2;
    }
    drawModeMessage(localBitMatrix, bool, i12, localBitArray4);
    if (bool)
    {
      drawBullsEye(localBitMatrix, i12 / 2, 5);
    }
    else
    {
      int i17 = i12 / 2;
      drawBullsEye(localBitMatrix, i17, 7);
      int i18 = 0;
      for (int i19 = 0; i18 < i8 / 2 - 1; i19 += 16)
      {
        for (int i20 = i17 & 0x1; i20 < i12; i20 += 2)
        {
          int i21 = i17 - i19;
          localBitMatrix.set(i21, i20);
          int i22 = i17 + i19;
          localBitMatrix.set(i22, i20);
          localBitMatrix.set(i20, i21);
          localBitMatrix.set(i20, i22);
        }
        i18 += 15;
      }
    }
    AztecCode localAztecCode = new AztecCode();
    localAztecCode.setCompact(bool);
    localAztecCode.setSize(i12);
    localAztecCode.setLayers(m);
    localAztecCode.setCodeWords(i2);
    localAztecCode.setMatrix(localBitMatrix);
    return localAztecCode;
  }

  static BitArray generateCheckWords(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    int i = (paramInt2 + paramBitArray.getSize() - 1) / paramInt2;
    for (int j = i * paramInt2 - paramBitArray.getSize(); j > 0; j--)
      paramBitArray.appendBit(true);
    ReedSolomonEncoder localReedSolomonEncoder = new ReedSolomonEncoder(getGF(paramInt2));
    int k = paramInt1 / paramInt2;
    int[] arrayOfInt = bitsToWords(paramBitArray, paramInt2, k);
    localReedSolomonEncoder.encode(arrayOfInt, k - i);
    int m = paramInt1 % paramInt2;
    BitArray localBitArray = new BitArray();
    int n = 0;
    localBitArray.appendBits(0, m);
    int i1 = arrayOfInt.length;
    while (n < i1)
    {
      localBitArray.appendBits(arrayOfInt[n], paramInt2);
      n++;
    }
    return localBitArray;
  }

  static BitArray generateModeMessage(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    BitArray localBitArray1 = new BitArray();
    BitArray localBitArray2;
    if (paramBoolean)
    {
      localBitArray1.appendBits(paramInt1 - 1, 2);
      localBitArray1.appendBits(paramInt2 - 1, 6);
      localBitArray2 = generateCheckWords(localBitArray1, 28, 4);
    }
    else
    {
      localBitArray1.appendBits(paramInt1 - 1, 5);
      localBitArray1.appendBits(paramInt2 - 1, 11);
      localBitArray2 = generateCheckWords(localBitArray1, 40, 4);
    }
    return localBitArray2;
  }

  static GenericGF getGF(int paramInt)
  {
    if (paramInt != 4)
    {
      if (paramInt != 6)
      {
        if (paramInt != 8)
        {
          if (paramInt != 10)
          {
            if (paramInt != 12)
              return null;
            return GenericGF.AZTEC_DATA_12;
          }
          return GenericGF.AZTEC_DATA_10;
        }
        return GenericGF.AZTEC_DATA_8;
      }
      return GenericGF.AZTEC_DATA_6;
    }
    return GenericGF.AZTEC_PARAM;
  }

  static BitArray highLevelEncode(byte[] paramArrayOfByte)
  {
    BitArray localBitArray = new BitArray();
    int[] arrayOfInt1 = new int[5];
    int[] arrayOfInt2 = new int[5];
    int i = 0;
    int j = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = 0xFF & paramArrayOfByte[i];
      int m;
      if (i < paramArrayOfByte.length - 1)
        m = 0xFF & paramArrayOfByte[(i + 1)];
      else
        m = 0;
      int n;
      if ((k == 13) && (m == 10))
        n = 2;
      else if ((k == 46) && (m == 32))
        n = 3;
      else if ((k == 44) && (m == 32))
        n = 4;
      else if ((k == 58) && (m == 32))
        n = 5;
      else
        n = 0;
      if (n > 0)
      {
        if (j == 4)
        {
          outputWord(localBitArray, 4, n);
          i++;
          break label888;
        }
        if (SHIFT_TABLE[j][4] >= 0)
        {
          outputWord(localBitArray, j, SHIFT_TABLE[j][4]);
          outputWord(localBitArray, 4, n);
          i++;
          break label888;
        }
        if (LATCH_TABLE[j][4] >= 0)
        {
          outputWord(localBitArray, j, LATCH_TABLE[j][4]);
          outputWord(localBitArray, 4, n);
          i++;
          j = 4;
          break label888;
        }
      }
      int i1 = 0;
      int i2 = -1;
      int i3 = -1;
      int i4 = -1;
      while (i1 < 5)
      {
        arrayOfInt1[i1] = CHAR_MAP[i1][k];
        if ((arrayOfInt1[i1] > 0) && (i3 < 0))
          i3 = i1;
        if ((i2 < 0) && (arrayOfInt1[i1] > 0) && (SHIFT_TABLE[j][i1] >= 0))
          i2 = i1;
        arrayOfInt2[i1] = CHAR_MAP[i1][m];
        if ((i4 < 0) && (arrayOfInt1[i1] > 0) && ((m == 0) || (arrayOfInt2[i1] > 0)) && (LATCH_TABLE[j][i1] >= 0))
          i4 = i1;
        i1++;
      }
      if ((i2 < 0) && (i4 < 0))
        for (i5 = 0; i5 < 5; i5++)
          if ((arrayOfInt1[i5] > 0) && (LATCH_TABLE[j][i5] >= 0))
            break label437;
      int i5 = i4;
      label437: if (arrayOfInt1[j] > 0)
      {
        outputWord(localBitArray, j, arrayOfInt1[j]);
      }
      else if (i5 >= 0)
      {
        outputWord(localBitArray, j, LATCH_TABLE[j][i5]);
        outputWord(localBitArray, i5, arrayOfInt1[i5]);
        j = i5;
      }
      else if (i2 >= 0)
      {
        outputWord(localBitArray, j, SHIFT_TABLE[j][i2]);
        outputWord(localBitArray, i2, arrayOfInt1[i2]);
      }
      else
      {
        if (i3 >= 0)
        {
          if (j == 4)
          {
            outputWord(localBitArray, 4, LATCH_TABLE[4][0]);
            i--;
          }
          while (true)
          {
            j = 0;
            break label888;
            if (j != 2)
              break;
            outputWord(localBitArray, 2, LATCH_TABLE[2][0]);
            i--;
          }
        }
        int i6 = i + 1;
        int i7 = 0;
        while (i6 < paramArrayOfByte.length)
        {
          int i9 = 0xFF & paramArrayOfByte[i6];
          for (int i10 = 0; i10 < 5; i10++)
            if (CHAR_MAP[i10][i9] > 0)
            {
              i11 = 0;
              break label645;
            }
          int i11 = 1;
          if (i11 != 0)
          {
            i7 = 0;
          }
          else
          {
            if (i7 >= 1)
            {
              i6 -= i7;
              break;
            }
            i7++;
          }
          i6++;
        }
        int i8 = i6 - i;
        switch (j)
        {
        default:
          break;
        case 4:
          outputWord(localBitArray, j, LATCH_TABLE[j][0]);
          outputWord(localBitArray, 0, SHIFT_TABLE[0][5]);
          break;
        case 2:
          outputWord(localBitArray, j, LATCH_TABLE[j][0]);
          outputWord(localBitArray, 0, SHIFT_TABLE[0][5]);
          j = 0;
          break;
        case 0:
        case 1:
        case 3:
        }
        outputWord(localBitArray, j, SHIFT_TABLE[j][5]);
        if ((i8 >= 32) && (i8 < 63))
          i8 = 31;
        if (i8 > 542)
          i8 = 542;
        if (i8 < 32)
          localBitArray.appendBits(i8, 5);
        else
          localBitArray.appendBits(i8 - 31, 16);
        while (i8 > 0)
        {
          localBitArray.appendBits(paramArrayOfByte[i], 8);
          i8--;
          i++;
        }
        i--;
      }
      label645: label888: i++;
    }
    return localBitArray;
  }

  static void outputWord(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 2)
      paramBitArray.appendBits(paramInt2, 4);
    else if (paramInt1 < 5)
      paramBitArray.appendBits(paramInt2, 5);
    else
      paramBitArray.appendBits(paramInt2, 8);
  }

  static BitArray stuffBits(BitArray paramBitArray, int paramInt)
  {
    BitArray localBitArray = new BitArray();
    int i = paramBitArray.getSize();
    int j = -2 + (1 << paramInt);
    int k = 0;
    while (k < i)
    {
      int i3 = 0;
      int i4 = 0;
      while (i3 < paramInt)
      {
        int i6 = k + i3;
        if ((i6 >= i) || (paramBitArray.get(i6)))
          i4 |= 1 << paramInt - 1 - i3;
        i3++;
      }
      int i5 = i4 & j;
      if (i5 == j)
      {
        localBitArray.appendBits(i5, paramInt);
        k--;
      }
      else if (i5 == 0)
      {
        localBitArray.appendBits(i4 | 0x1, paramInt);
        k--;
      }
      else
      {
        localBitArray.appendBits(i4, paramInt);
      }
      k += paramInt;
    }
    int m = localBitArray.getSize();
    int n = m % paramInt;
    if (n != 0)
    {
      int i1 = 0;
      int i2 = 1;
      while (i1 < n)
      {
        if (!localBitArray.get(m - 1 - i1))
          i2 = 0;
        i1++;
      }
      while (n < paramInt - 1)
      {
        localBitArray.appendBit(true);
        n++;
      }
      localBitArray.appendBit(i2 ^ 0x1);
    }
    return localBitArray;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.aztec.encoder.Encoder
 * JD-Core Version:    0.6.1
 */