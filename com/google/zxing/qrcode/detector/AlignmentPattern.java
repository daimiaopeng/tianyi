package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class AlignmentPattern extends ResultPoint
{
  private final float estimatedModuleSize;

  AlignmentPattern(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2);
    this.estimatedModuleSize = paramFloat3;
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

  AlignmentPattern combineEstimate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return new AlignmentPattern((paramFloat2 + getX()) / 2.0F, (paramFloat1 + getY()) / 2.0F, (paramFloat3 + this.estimatedModuleSize) / 2.0F);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.AlignmentPattern
 * JD-Core Version:    0.6.1
 */