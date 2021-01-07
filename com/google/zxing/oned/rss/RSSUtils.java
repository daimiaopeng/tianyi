package com.google.zxing.oned.rss;

public final class RSSUtils
{
  private static int combins(int paramInt1, int paramInt2)
  {
    int i = paramInt1 - paramInt2;
    if (i > paramInt2)
    {
      int m = i;
      i = paramInt2;
      paramInt2 = m;
    }
    int j = 1;
    int k = 1;
    while (paramInt1 > paramInt2)
    {
      j *= paramInt1;
      if (k <= i)
      {
        j /= k;
        k++;
      }
      paramInt1--;
    }
    while (k <= i)
    {
      j /= k;
      k++;
    }
    return j;
  }

  static int[] elements(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[2 + paramArrayOfInt.length];
    int i = paramInt2 << 1;
    arrayOfInt[0] = 1;
    int j = 1;
    int k = 1;
    int m = 10;
    while (j < i - 2)
    {
      int i5 = j - 1;
      paramArrayOfInt[i5] -= arrayOfInt[i5];
      int i6 = j + 1;
      paramArrayOfInt[j] -= arrayOfInt[j];
      k += arrayOfInt[j] + arrayOfInt[i6];
      if (arrayOfInt[j] < m)
        m = arrayOfInt[j];
      j += 2;
    }
    int n = i - 1;
    arrayOfInt[n] = (paramInt1 - k);
    if (arrayOfInt[n] < m)
      m = arrayOfInt[n];
    int i1 = 0;
    if (m > 1)
      while (i1 < i)
      {
        int i2 = arrayOfInt[i1];
        int i3 = m - 1;
        arrayOfInt[i1] = (i2 + i3);
        int i4 = i1 + 1;
        arrayOfInt[i4] -= i3;
        i1 += 2;
      }
    return arrayOfInt;
  }

  public static int getRSSvalue(int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
  {
    int[] arrayOfInt = paramArrayOfInt;
    int i = arrayOfInt.length;
    int j = arrayOfInt.length;
    int k = 0;
    int m = 0;
    while (k < j)
    {
      m += arrayOfInt[k];
      k++;
    }
    int n = m;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    while (true)
    {
      int i4 = i - 1;
      if (i1 >= i4)
        break;
      int i5 = 1 << i1;
      int i6 = i2 | i5;
      int i7 = 1;
      while (i7 < arrayOfInt[i1])
      {
        int i8 = n - i7;
        int i9 = i8 - 1;
        int i10 = i - i1;
        int i11 = i10 - 2;
        int i12 = combins(i9, i11);
        if ((paramBoolean) && (i6 == 0))
        {
          int i15 = i10 - 1;
          if (i8 - i15 >= i15)
            i12 -= combins(i8 - i10, i11);
        }
        if (i10 - 1 > 1)
        {
          int i13 = i8 - i11;
          int i14 = 0;
          while (i13 > paramInt)
          {
            i14 += combins(-1 + (i8 - i13), i10 - 3);
            i13--;
          }
          i12 -= i14 * (i4 - i1);
        }
        else if (i8 > paramInt)
        {
          i12--;
        }
        i3 += i12;
        i7++;
        i6 &= (i5 ^ 0xFFFFFFFF);
        arrayOfInt = paramArrayOfInt;
      }
      n -= i7;
      i1++;
      i2 = i6;
      arrayOfInt = paramArrayOfInt;
    }
    return i3;
  }

  static int[] getRSSwidths(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int i = paramInt3;
    int[] arrayOfInt = new int[i];
    int j = paramInt1;
    int k = paramInt2;
    int m = 0;
    int n = 0;
    int i1 = i - 1;
    if (m < i1)
    {
      int i2 = 1 << m;
      int i3 = n | i2;
      int i4 = j;
      int i5 = i3;
      int i6 = 1;
      while (true)
      {
        int i7 = k - i6;
        int i8 = i7 - 1;
        int i9 = i - m;
        int i10 = i9 - 2;
        int i11 = combins(i8, i10);
        if ((paramBoolean) && (i5 == 0))
        {
          int i15 = i9 - 1;
          if (i7 - i15 >= i15)
            i11 -= combins(i7 - i9, i10);
        }
        if (i9 - 1 > 1)
        {
          int i13 = i7 - i10;
          int i14 = 0;
          while (i13 > paramInt4)
          {
            i14 += combins(-1 + (i7 - i13), i9 - 3);
            i13--;
          }
          i11 -= i14 * (i1 - m);
        }
        else if (i7 > paramInt4)
        {
          i11--;
        }
        i4 -= i11;
        if (i4 < 0)
        {
          int i12 = i4 + i11;
          arrayOfInt[m] = i6;
          m++;
          n = i5;
          k = i7;
          j = i12;
          i = paramInt3;
          break;
        }
        i6++;
        i5 &= (i2 ^ 0xFFFFFFFF);
        i = paramInt3;
      }
    }
    arrayOfInt[m] = k;
    return arrayOfInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.RSSUtils
 * JD-Core Version:    0.6.1
 */