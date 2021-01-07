package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint
{
  private int count;
  private final float estimatedModuleSize;

  FinderPattern(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this(paramFloat1, paramFloat2, paramFloat3, 1);
  }

  private FinderPattern(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt)
  {
    super(paramFloat1, paramFloat2);
    this.estimatedModuleSize = paramFloat3;
    this.count = paramInt;
  }

  boolean aboutEquals(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if ((Math.abs(paramFloat2 - getY()) <= paramFloat1) && (Math.abs(paramFloat3 - getX()) <= paramFloat1))
    {
      float f = Math.abs(paramFloat1 - this.estimatedModuleSize);
      boolean bool1;
      if (f > 1.0F)
      {
        boolean bool2 = f < this.estimatedModuleSize;
        bool1 = false;
        if (bool2);
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }

  FinderPattern combineEstimate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    int i = 1 + this.count;
    float f1 = paramFloat2 + this.count * getX();
    float f2 = i;
    return new FinderPattern(f1 / f2, (paramFloat1 + this.count * getY()) / f2, (paramFloat3 + this.count * this.estimatedModuleSize) / f2, i);
  }

  int getCount()
  {
    return this.count;
  }

  public float getEstimatedModuleSize()
  {
    return this.estimatedModuleSize;
  }

  void incrementCount()
  {
    this.count = (1 + this.count);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPattern
 * JD-Core Version:    0.6.1
 */