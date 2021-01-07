package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

public class DefaultPlacement
{
  private final byte[] bits;
  private final String codewords;
  private final int numcols;
  private final int numrows;

  public DefaultPlacement(String paramString, int paramInt1, int paramInt2)
  {
    this.codewords = paramString;
    this.numcols = paramInt1;
    this.numrows = paramInt2;
    this.bits = new byte[paramInt1 * paramInt2];
    Arrays.fill(this.bits, (byte)-1);
  }

  private void corner1(int paramInt)
  {
    module(this.numrows - 1, 0, paramInt, 1);
    module(this.numrows - 1, 1, paramInt, 2);
    module(this.numrows - 1, 2, paramInt, 3);
    module(0, this.numcols - 2, paramInt, 4);
    module(0, this.numcols - 1, paramInt, 5);
    module(1, this.numcols - 1, paramInt, 6);
    module(2, this.numcols - 1, paramInt, 7);
    module(3, this.numcols - 1, paramInt, 8);
  }

  private void corner2(int paramInt)
  {
    module(this.numrows - 3, 0, paramInt, 1);
    module(this.numrows - 2, 0, paramInt, 2);
    module(this.numrows - 1, 0, paramInt, 3);
    module(0, this.numcols - 4, paramInt, 4);
    module(0, this.numcols - 3, paramInt, 5);
    module(0, this.numcols - 2, paramInt, 6);
    module(0, this.numcols - 1, paramInt, 7);
    module(1, this.numcols - 1, paramInt, 8);
  }

  private void corner3(int paramInt)
  {
    module(this.numrows - 3, 0, paramInt, 1);
    module(this.numrows - 2, 0, paramInt, 2);
    module(this.numrows - 1, 0, paramInt, 3);
    module(0, this.numcols - 2, paramInt, 4);
    module(0, this.numcols - 1, paramInt, 5);
    module(1, this.numcols - 1, paramInt, 6);
    module(2, this.numcols - 1, paramInt, 7);
    module(3, this.numcols - 1, paramInt, 8);
  }

  private void corner4(int paramInt)
  {
    module(this.numrows - 1, 0, paramInt, 1);
    module(this.numrows - 1, this.numcols - 1, paramInt, 2);
    module(0, this.numcols - 3, paramInt, 3);
    module(0, this.numcols - 2, paramInt, 4);
    module(0, this.numcols - 1, paramInt, 5);
    module(1, this.numcols - 3, paramInt, 6);
    module(1, this.numcols - 2, paramInt, 7);
    module(1, this.numcols - 1, paramInt, 8);
  }

  private void module(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 < 0)
    {
      paramInt1 += this.numrows;
      paramInt2 += 4 - (4 + this.numrows) % 8;
    }
    if (paramInt2 < 0)
    {
      paramInt2 += this.numcols;
      paramInt1 += 4 - (4 + this.numcols) % 8;
    }
    int i = this.codewords.charAt(paramInt3);
    int j = 8 - paramInt4;
    int k = 1;
    if ((i & k << j) == 0)
      k = 0;
    setBit(paramInt2, paramInt1, k);
  }

  private void utah(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 - 2;
    int j = paramInt2 - 2;
    module(i, j, paramInt3, 1);
    int k = paramInt2 - 1;
    module(i, k, paramInt3, 2);
    int m = paramInt1 - 1;
    module(m, j, paramInt3, 3);
    module(m, k, paramInt3, 4);
    module(m, paramInt2, paramInt3, 5);
    module(paramInt1, j, paramInt3, 6);
    module(paramInt1, k, paramInt3, 7);
    module(paramInt1, paramInt2, paramInt3, 8);
  }

  public final boolean getBit(int paramInt1, int paramInt2)
  {
    int i = this.bits[(paramInt1 + paramInt2 * this.numcols)];
    int j = 1;
    if (i != j)
      j = 0;
    return j;
  }

  final byte[] getBits()
  {
    return this.bits;
  }

  final int getNumcols()
  {
    return this.numcols;
  }

  final int getNumrows()
  {
    return this.numrows;
  }

  final boolean hasBit(int paramInt1, int paramInt2)
  {
    boolean bool;
    if (this.bits[(paramInt1 + paramInt2 * this.numcols)] >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public final void place()
  {
    int i = 4;
    int j = 0;
    int k = 0;
    do
    {
      if ((i == this.numrows) && (j == 0))
      {
        int i6 = k + 1;
        corner1(k);
        k = i6;
      }
      if ((i == this.numrows - 2) && (j == 0) && (this.numcols % 4 != 0))
      {
        int i5 = k + 1;
        corner2(k);
        k = i5;
      }
      if ((i == this.numrows - 2) && (j == 0) && (this.numcols % 8 == 4))
      {
        int i4 = k + 1;
        corner3(k);
        k = i4;
      }
      if ((i == 4 + this.numrows) && (j == 2) && (this.numcols % 8 == 0))
      {
        int i3 = k + 1;
        corner4(k);
        k = i3;
      }
      do
      {
        if ((i < this.numrows) && (j >= 0) && (!hasBit(j, i)))
        {
          int i2 = k + 1;
          utah(i, j, k);
          k = i2;
        }
        i -= 2;
        j += 2;
      }
      while ((i >= 0) && (j < this.numcols));
      int m = i + 1;
      int n = j + 3;
      do
      {
        if ((m >= 0) && (n < this.numcols) && (!hasBit(n, m)))
        {
          int i1 = k + 1;
          utah(m, n, k);
          k = i1;
        }
        m += 2;
        n -= 2;
      }
      while ((m < this.numrows) && (n >= 0));
      i = m + 3;
      j = n + 1;
    }
    while ((i < this.numrows) || (j < this.numcols));
    if (!hasBit(this.numcols - 1, this.numrows - 1))
    {
      setBit(this.numcols - 1, this.numrows - 1, true);
      setBit(this.numcols - 2, this.numrows - 2, true);
    }
  }

  final void setBit(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.bits[(paramInt1 + paramInt2 * this.numcols)] = paramBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.DefaultPlacement
 * JD-Core Version:    0.6.1
 */