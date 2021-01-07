package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector
{
  private static final int CORR = 1;
  private static final int INIT_SIZE = 30;
  private final int downInit;
  private final int height;
  private final BitMatrix image;
  private final int leftInit;
  private final int rightInit;
  private final int upInit;
  private final int width;

  public WhiteRectangleDetector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
    this.height = paramBitMatrix.getHeight();
    this.width = paramBitMatrix.getWidth();
    this.leftInit = (-30 + this.width >> 1);
    this.rightInit = (30 + this.width >> 1);
    this.upInit = (-30 + this.height >> 1);
    this.downInit = (30 + this.height >> 1);
    if ((this.upInit >= 0) && (this.leftInit >= 0) && (this.downInit < this.height) && (this.rightInit < this.width))
      return;
    throw NotFoundException.getNotFoundInstance();
  }

  public WhiteRectangleDetector(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3)
  {
    this.image = paramBitMatrix;
    this.height = paramBitMatrix.getHeight();
    this.width = paramBitMatrix.getWidth();
    int i = paramInt1 >> 1;
    this.leftInit = (paramInt2 - i);
    this.rightInit = (paramInt2 + i);
    this.upInit = (paramInt3 - i);
    this.downInit = (paramInt3 + i);
    if ((this.upInit >= 0) && (this.leftInit >= 0) && (this.downInit < this.height) && (this.rightInit < this.width))
      return;
    throw NotFoundException.getNotFoundInstance();
  }

  private ResultPoint[] centerEdges(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
  {
    float f1 = paramResultPoint1.getX();
    float f2 = paramResultPoint1.getY();
    float f3 = paramResultPoint2.getX();
    float f4 = paramResultPoint2.getY();
    float f5 = paramResultPoint3.getX();
    float f6 = paramResultPoint3.getY();
    float f7 = paramResultPoint4.getX();
    float f8 = paramResultPoint4.getY();
    if (f1 < this.width / 2.0F)
    {
      ResultPoint[] arrayOfResultPoint2 = new ResultPoint[4];
      arrayOfResultPoint2[0] = new ResultPoint(f7 - 1.0F, f8 + 1.0F);
      arrayOfResultPoint2[1] = new ResultPoint(f3 + 1.0F, f4 + 1.0F);
      arrayOfResultPoint2[2] = new ResultPoint(f5 - 1.0F, f6 - 1.0F);
      arrayOfResultPoint2[3] = new ResultPoint(f1 + 1.0F, f2 - 1.0F);
      return arrayOfResultPoint2;
    }
    ResultPoint[] arrayOfResultPoint1 = new ResultPoint[4];
    arrayOfResultPoint1[0] = new ResultPoint(f7 + 1.0F, f8 + 1.0F);
    arrayOfResultPoint1[1] = new ResultPoint(f3 + 1.0F, f4 - 1.0F);
    arrayOfResultPoint1[2] = new ResultPoint(f5 - 1.0F, f6 + 1.0F);
    arrayOfResultPoint1[3] = new ResultPoint(f1 - 1.0F, f2 - 1.0F);
    return arrayOfResultPoint1;
  }

  private boolean containsBlackPoint(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (paramBoolean)
      while (paramInt1 <= paramInt2)
      {
        if (this.image.get(paramInt1, paramInt3))
          return true;
        paramInt1++;
      }
    while (paramInt1 <= paramInt2)
    {
      if (this.image.get(paramInt3, paramInt1))
        return true;
      paramInt1++;
    }
    return false;
  }

  private ResultPoint getBlackPointOnSegment(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int i = MathUtils.round(MathUtils.distance(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
    float f1 = paramFloat3 - paramFloat1;
    float f2 = i;
    float f3 = f1 / f2;
    float f4 = (paramFloat4 - paramFloat2) / f2;
    for (int j = 0; j < i; j++)
    {
      float f5 = j;
      int k = MathUtils.round(paramFloat1 + f5 * f3);
      int m = MathUtils.round(paramFloat2 + f5 * f4);
      if (this.image.get(k, m))
        return new ResultPoint(k, m);
    }
    return null;
  }

  public ResultPoint[] detect()
  {
    int i = this.leftInit;
    int j = this.rightInit;
    int k = this.upInit;
    int m = this.downInit;
    int n = 1;
    int i1 = i;
    int i2 = 1;
    int i3 = 0;
    int i4;
    while (true)
    {
      i4 = 0;
      if (i2 == 0)
        break;
      boolean bool1 = true;
      int i9 = 0;
      while ((bool1) && (j < this.width))
      {
        bool1 = containsBlackPoint(k, m, j, false);
        if (bool1)
        {
          j++;
          i9 = 1;
        }
      }
      if (j >= this.width);
      do
      {
        do
        {
          do
          {
            i4 = 1;
            break;
            boolean bool2 = true;
            while ((bool2) && (m < this.height))
            {
              bool2 = containsBlackPoint(i1, j, m, n);
              if (bool2)
              {
                m++;
                i9 = 1;
              }
            }
          }
          while (m >= this.height);
          boolean bool3 = true;
          while ((bool3) && (i1 >= 0))
          {
            bool3 = containsBlackPoint(k, m, i1, false);
            if (bool3)
            {
              i1--;
              i9 = 1;
            }
          }
        }
        while (i1 < 0);
        boolean bool4 = true;
        while ((bool4) && (k >= 0))
        {
          bool4 = containsBlackPoint(i1, j, k, n);
          if (bool4)
          {
            k--;
            i9 = 1;
          }
        }
      }
      while (k < 0);
      if (i9 != 0)
        i3 = 1;
      i2 = i9;
    }
    if ((i4 == 0) && (i3 != 0))
    {
      int i5 = j - i1;
      ResultPoint localResultPoint1 = null;
      for (int i6 = 1; i6 < i5; i6++)
      {
        localResultPoint1 = getBlackPointOnSegment(i1, m - i6, i1 + i6, m);
        if (localResultPoint1 != null)
          break;
      }
      if (localResultPoint1 == null)
        throw NotFoundException.getNotFoundInstance();
      ResultPoint localResultPoint2 = null;
      for (int i7 = 1; i7 < i5; i7++)
      {
        localResultPoint2 = getBlackPointOnSegment(i1, k + i7, i1 + i7, k);
        if (localResultPoint2 != null)
          break;
      }
      if (localResultPoint2 == null)
        throw NotFoundException.getNotFoundInstance();
      ResultPoint localResultPoint3 = null;
      for (int i8 = 1; i8 < i5; i8++)
      {
        localResultPoint3 = getBlackPointOnSegment(j, k + i8, j - i8, k);
        if (localResultPoint3 != null)
          break;
      }
      ResultPoint localResultPoint4 = null;
      if (localResultPoint3 == null)
        throw NotFoundException.getNotFoundInstance();
      while (n < i5)
      {
        localResultPoint4 = getBlackPointOnSegment(j, m - n, j - n, m);
        if (localResultPoint4 != null)
          break;
        n++;
      }
      if (localResultPoint4 == null)
        throw NotFoundException.getNotFoundInstance();
      return centerEdges(localResultPoint4, localResultPoint1, localResultPoint3, localResultPoint2);
    }
    throw NotFoundException.getNotFoundInstance();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.detector.WhiteRectangleDetector
 * JD-Core Version:    0.6.1
 */