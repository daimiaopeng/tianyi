package com.google.zxing.qrcode.encoder;

final class MaskUtil
{
  private static final int N1 = 3;
  private static final int N2 = 3;
  private static final int N3 = 40;
  private static final int N4 = 10;

  static int applyMaskPenaltyRule1(ByteMatrix paramByteMatrix)
  {
    return applyMaskPenaltyRule1Internal(paramByteMatrix, true) + applyMaskPenaltyRule1Internal(paramByteMatrix, false);
  }

  private static int applyMaskPenaltyRule1Internal(ByteMatrix paramByteMatrix, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
      i = paramByteMatrix.getHeight();
    else
      i = paramByteMatrix.getWidth();
    int j;
    if (paramBoolean)
      j = paramByteMatrix.getWidth();
    else
      j = paramByteMatrix.getHeight();
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int k = 0;
    int m = 0;
    while (k < i)
    {
      int n = m;
      int i1 = 0;
      int i2 = 0;
      int i3 = -1;
      while (i1 < j)
      {
        int i4;
        if (paramBoolean)
          i4 = arrayOfByte[k][i1];
        else
          i4 = arrayOfByte[i1][k];
        if (i4 == i3)
        {
          i2++;
        }
        else
        {
          if (i2 >= 5)
            n += 3 + (i2 - 5);
          i2 = 1;
          i3 = i4;
        }
        i1++;
      }
      if (i2 >= 5)
        n += 3 + (i2 - 5);
      m = n;
      k++;
    }
    return m;
  }

  static int applyMaskPenaltyRule2(ByteMatrix paramByteMatrix)
  {
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int i = paramByteMatrix.getWidth();
    int j = paramByteMatrix.getHeight();
    int k = 0;
    int n;
    for (int m = 0; k < j - 1; m = n)
    {
      n = m;
      int i3;
      for (int i1 = 0; i1 < i - 1; i1 = i3)
      {
        int i2 = arrayOfByte[k][i1];
        byte[] arrayOfByte1 = arrayOfByte[k];
        i3 = i1 + 1;
        if (i2 == arrayOfByte1[i3])
        {
          int i4 = k + 1;
          if ((i2 == arrayOfByte[i4][i1]) && (i2 == arrayOfByte[i4][i3]))
            n++;
        }
      }
      k++;
    }
    return m * 3;
  }

  static int applyMaskPenaltyRule3(ByteMatrix paramByteMatrix)
  {
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int i = paramByteMatrix.getWidth();
    int j = paramByteMatrix.getHeight();
    int k = 0;
    int n;
    for (int m = 0; k < j; m = n)
    {
      n = m;
      for (int i1 = 0; i1 < i; i1++)
      {
        int i2 = i1 + 6;
        if ((i2 < i) && (arrayOfByte[k][i1] == 1) && (arrayOfByte[k][(i1 + 1)] == 0) && (arrayOfByte[k][(i1 + 2)] == 1) && (arrayOfByte[k][(i1 + 3)] == 1) && (arrayOfByte[k][(i1 + 4)] == 1) && (arrayOfByte[k][(i1 + 5)] == 0) && (arrayOfByte[k][i2] == 1))
        {
          int i6 = i1 + 10;
          if ((i6 >= i) || (arrayOfByte[k][(i1 + 7)] != 0) || (arrayOfByte[k][(i1 + 8)] != 0) || (arrayOfByte[k][(i1 + 9)] != 0) || (arrayOfByte[k][i6] != 0))
          {
            int i7 = i1 - 4;
            if ((i7 < 0) || (arrayOfByte[k][(i1 - 1)] != 0) || (arrayOfByte[k][(i1 - 2)] != 0) || (arrayOfByte[k][(i1 - 3)] != 0) || (arrayOfByte[k][i7] != 0));
          }
          else
          {
            n += 40;
          }
        }
        int i3 = k + 6;
        if ((i3 < j) && (arrayOfByte[k][i1] == 1) && (arrayOfByte[(k + 1)][i1] == 0) && (arrayOfByte[(k + 2)][i1] == 1) && (arrayOfByte[(k + 3)][i1] == 1) && (arrayOfByte[(k + 4)][i1] == 1) && (arrayOfByte[(k + 5)][i1] == 0) && (arrayOfByte[i3][i1] == 1))
        {
          int i4 = k + 10;
          if ((i4 >= j) || (arrayOfByte[(k + 7)][i1] != 0) || (arrayOfByte[(k + 8)][i1] != 0) || (arrayOfByte[(k + 9)][i1] != 0) || (arrayOfByte[i4][i1] != 0))
          {
            int i5 = k - 4;
            if ((i5 < 0) || (arrayOfByte[(k - 1)][i1] != 0) || (arrayOfByte[(k - 2)][i1] != 0) || (arrayOfByte[(k - 3)][i1] != 0) || (arrayOfByte[i5][i1] != 0));
          }
          else
          {
            n += 40;
          }
        }
      }
      k++;
    }
    return m;
  }

  static int applyMaskPenaltyRule4(ByteMatrix paramByteMatrix)
  {
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int i = paramByteMatrix.getWidth();
    int j = paramByteMatrix.getHeight();
    int k = 0;
    int i1;
    for (int m = 0; k < j; m = i1)
    {
      byte[] arrayOfByte1 = arrayOfByte[k];
      i1 = m;
      for (int i2 = 0; i2 < i; i2++)
        if (arrayOfByte1[i2] == 1)
          i1++;
      k++;
    }
    int n = paramByteMatrix.getHeight() * paramByteMatrix.getWidth();
    return 10 * (int)(20.0D * Math.abs(m / n - 0.5D));
  }

  static boolean getDataMaskBit(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 1;
    int j;
    switch (paramInt1)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid mask pattern: ");
      localStringBuilder.append(paramInt1);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 7:
      j = i & paramInt3 * paramInt2 % 3 + (0x1 & paramInt3 + paramInt2);
      break;
    case 6:
      int m = paramInt3 * paramInt2;
      j = i & (m & 0x1) + m % 3;
      break;
    case 5:
      int k = paramInt3 * paramInt2;
      j = (k & 0x1) + k % 3;
      break;
    case 4:
      j = i & (paramInt3 >>> 1) + paramInt2 / 3;
      break;
    case 3:
      j = (paramInt3 + paramInt2) % 3;
      break;
    case 2:
      j = paramInt2 % 3;
      break;
    case 1:
      j = paramInt3 & 0x1;
      break;
    case 0:
      j = 0x1 & paramInt3 + paramInt2;
    }
    if (j != 0)
      i = 0;
    return i;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.MaskUtil
 * JD-Core Version:    0.6.1
 */