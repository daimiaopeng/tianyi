package com.cndatacom.e;

public final class c
{
  private static final byte[] a = new byte['ÿ'];
  private static final byte[] b = new byte[64];

  static
  {
    for (int i = 0; i < 255; i++)
      a[i] = -1;
    for (int j = 90; j >= 65; j--)
      a[j] = (byte)(j - 65);
    int m;
    for (int k = 122; ; k--)
    {
      m = 26;
      if (k < 97)
        break;
      a[k] = (byte)(m + (k - 97));
    }
    int i1;
    for (int n = 57; ; n--)
    {
      i1 = 52;
      if (n < 48)
        break;
      a[n] = (byte)(i1 + (n - 48));
    }
    a[43] = 62;
    a[47] = 63;
    for (int i2 = 0; i2 <= 25; i2++)
      b[i2] = (byte)(i2 + 65);
    int i4;
    for (int i3 = 0; ; i3++)
    {
      i4 = 0;
      if (m > 51)
        break;
      b[m] = (byte)(i3 + 97);
      m++;
    }
    while (i1 <= 61)
    {
      b[i1] = (byte)(i4 + 48);
      i1++;
      i4++;
    }
    b[62] = 43;
    b[63] = 47;
  }

  public static byte[] a(byte[] paramArrayOfByte)
  {
    int i = 8 * paramArrayOfByte.length;
    int j = i % 24;
    int k = i / 24;
    byte[] arrayOfByte;
    if (j != 0)
      arrayOfByte = new byte[4 * (k + 1)];
    else
      arrayOfByte = new byte[k * 4];
    for (int m = 0; m < k; m++)
    {
      int i14 = m * 3;
      int i15 = paramArrayOfByte[i14];
      int i16 = paramArrayOfByte[(i14 + 1)];
      int i17 = paramArrayOfByte[(i14 + 2)];
      int i18 = (byte)(i16 & 0xF);
      int i19 = (byte)(i15 & 0x3);
      int i20 = m * 4;
      if ((i15 & 0xFFFFFF80) == 0);
      int i22;
      for (int i21 = i15 >> 2; ; i21 = 0xC0 ^ i15 >> 2)
      {
        i22 = (byte)i21;
        break;
      }
      if ((i16 & 0xFFFFFF80) == 0);
      int i24;
      for (int i23 = i16 >> 4; ; i23 = 0xF0 ^ i16 >> 4)
      {
        i24 = (byte)i23;
        break;
      }
      if ((i17 & 0xFFFFFF80) == 0);
      int i26;
      for (int i25 = i17 >> 6; ; i25 = 0xFC ^ i17 >> 6)
      {
        i26 = (byte)i25;
        break;
      }
      arrayOfByte[i20] = b[i22];
      arrayOfByte[(i20 + 1)] = b[(i24 | i19 << 4)];
      arrayOfByte[(i20 + 2)] = b[(i26 | i18 << 2)];
      arrayOfByte[(i20 + 3)] = b[(i17 & 0x3F)];
    }
    int n = m * 3;
    int i1 = m * 4;
    if (j == 8)
    {
      int i10 = paramArrayOfByte[n];
      int i11 = (byte)(i10 & 0x3);
      if ((i10 & 0xFFFFFF80) == 0);
      int i13;
      for (int i12 = i10 >> 2; ; i12 = 0xC0 ^ i10 >> 2)
      {
        i13 = (byte)i12;
        break;
      }
      arrayOfByte[i1] = b[i13];
      arrayOfByte[(i1 + 1)] = b[(i11 << 4)];
      arrayOfByte[(i1 + 2)] = 61;
      arrayOfByte[(i1 + 3)] = 61;
    }
    else if (j == 16)
    {
      int i2 = paramArrayOfByte[n];
      int i3 = paramArrayOfByte[(n + 1)];
      int i4 = (byte)(i3 & 0xF);
      int i5 = (byte)(i2 & 0x3);
      if ((i2 & 0xFFFFFF80) == 0);
      int i7;
      for (int i6 = i2 >> 2; ; i6 = 0xC0 ^ i2 >> 2)
      {
        i7 = (byte)i6;
        break;
      }
      if ((i3 & 0xFFFFFF80) == 0);
      int i9;
      for (int i8 = i3 >> 4; ; i8 = 0xF0 ^ i3 >> 4)
      {
        i9 = (byte)i8;
        break;
      }
      arrayOfByte[i1] = b[i7];
      arrayOfByte[(i1 + 1)] = b[(i9 | i5 << 4)];
      arrayOfByte[(i1 + 2)] = b[(i4 << 2)];
      arrayOfByte[(i1 + 3)] = 61;
    }
    return arrayOfByte;
  }

  public static byte[] b(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = 0;
    if (i == 0)
      return new byte[0];
    int k = paramArrayOfByte.length / 4;
    int m = paramArrayOfByte.length;
    while (paramArrayOfByte[(m - 1)] == 61)
    {
      m--;
      if (m == 0)
        return new byte[0];
    }
    byte[] arrayOfByte = new byte[m - k];
    int n = 0;
    while (j < k)
    {
      int i1 = j * 4;
      int i2 = paramArrayOfByte[(i1 + 2)];
      int i3 = paramArrayOfByte[(i1 + 3)];
      int i4 = a[paramArrayOfByte[i1]];
      int i5 = a[paramArrayOfByte[(i1 + 1)]];
      if ((i2 != 61) && (i3 != 61))
      {
        int i7 = a[i2];
        int i8 = a[i3];
        arrayOfByte[n] = (byte)(i4 << 2 | i5 >> 4);
        arrayOfByte[(n + 1)] = (byte)((i5 & 0xF) << 4 | 0xF & i7 >> 2);
        arrayOfByte[(n + 2)] = (byte)(i8 | i7 << 6);
      }
      else if (i2 == 61)
      {
        arrayOfByte[n] = (byte)(i4 << 2 | i5 >> 4);
      }
      else if (i3 == 61)
      {
        int i6 = a[i2];
        arrayOfByte[n] = (byte)(i4 << 2 | i5 >> 4);
        arrayOfByte[(n + 1)] = (byte)((i5 & 0xF) << 4 | 0xF & i6 >> 2);
      }
      n += 3;
      j++;
    }
    return arrayOfByte;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.c
 * JD-Core Version:    0.6.1
 */