package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

public final class FinderPattern
{
  private final ResultPoint[] resultPoints;
  private final int[] startEnd;
  private final int value;

  public FinderPattern(int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3, int paramInt4)
  {
    this.value = paramInt1;
    this.startEnd = paramArrayOfInt;
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    float f1 = paramInt2;
    float f2 = paramInt4;
    arrayOfResultPoint[0] = new ResultPoint(f1, f2);
    arrayOfResultPoint[1] = new ResultPoint(paramInt3, f2);
    this.resultPoints = arrayOfResultPoint;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FinderPattern))
      return false;
    FinderPattern localFinderPattern = (FinderPattern)paramObject;
    int i = this.value;
    int j = localFinderPattern.value;
    boolean bool = false;
    if (i == j)
      bool = true;
    return bool;
  }

  public ResultPoint[] getResultPoints()
  {
    return this.resultPoints;
  }

  public int[] getStartEnd()
  {
    return this.startEnd;
  }

  public int getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return this.value;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.FinderPattern
 * JD-Core Version:    0.6.1
 */