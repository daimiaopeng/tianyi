package com.google.zxing.common;

public final class PerspectiveTransform
{
  private final float a11;
  private final float a12;
  private final float a13;
  private final float a21;
  private final float a22;
  private final float a23;
  private final float a31;
  private final float a32;
  private final float a33;

  private PerspectiveTransform(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9)
  {
    this.a11 = paramFloat1;
    this.a12 = paramFloat4;
    this.a13 = paramFloat7;
    this.a21 = paramFloat2;
    this.a22 = paramFloat5;
    this.a23 = paramFloat8;
    this.a31 = paramFloat3;
    this.a32 = paramFloat6;
    this.a33 = paramFloat9;
  }

  public static PerspectiveTransform quadrilateralToQuadrilateral(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16)
  {
    PerspectiveTransform localPerspectiveTransform = quadrilateralToSquare(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8);
    return squareToQuadrilateral(paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16).times(localPerspectiveTransform);
  }

  public static PerspectiveTransform quadrilateralToSquare(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8)
  {
    return squareToQuadrilateral(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8).buildAdjoint();
  }

  public static PerspectiveTransform squareToQuadrilateral(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8)
  {
    float f1 = paramFloat5 + (paramFloat1 - paramFloat3) - paramFloat7;
    float f2 = paramFloat6 + (paramFloat2 - paramFloat4) - paramFloat8;
    if ((f1 == 0.0F) && (f2 == 0.0F))
    {
      PerspectiveTransform localPerspectiveTransform2 = new PerspectiveTransform(paramFloat3 - paramFloat1, paramFloat5 - paramFloat3, paramFloat1, paramFloat4 - paramFloat2, paramFloat6 - paramFloat4, paramFloat2, 0.0F, 0.0F, 1.0F);
      return localPerspectiveTransform2;
    }
    float f3 = paramFloat3 - paramFloat5;
    float f4 = paramFloat7 - paramFloat5;
    float f5 = paramFloat4 - paramFloat6;
    float f6 = paramFloat8 - paramFloat6;
    float f7 = f3 * f6 - f4 * f5;
    float f8 = (f6 * f1 - f4 * f2) / f7;
    float f9 = (f3 * f2 - f1 * f5) / f7;
    PerspectiveTransform localPerspectiveTransform1 = new PerspectiveTransform(paramFloat3 - paramFloat1 + f8 * paramFloat3, paramFloat7 - paramFloat1 + f9 * paramFloat7, paramFloat1, paramFloat4 - paramFloat2 + f8 * paramFloat4, paramFloat8 - paramFloat2 + f9 * paramFloat8, paramFloat2, f8, f9, 1.0F);
    return localPerspectiveTransform1;
  }

  PerspectiveTransform buildAdjoint()
  {
    PerspectiveTransform localPerspectiveTransform = new PerspectiveTransform(this.a22 * this.a33 - this.a23 * this.a32, this.a23 * this.a31 - this.a21 * this.a33, this.a21 * this.a32 - this.a22 * this.a31, this.a13 * this.a32 - this.a12 * this.a33, this.a11 * this.a33 - this.a13 * this.a31, this.a12 * this.a31 - this.a11 * this.a32, this.a12 * this.a23 - this.a13 * this.a22, this.a13 * this.a21 - this.a11 * this.a23, this.a11 * this.a22 - this.a12 * this.a21);
    return localPerspectiveTransform;
  }

  PerspectiveTransform times(PerspectiveTransform paramPerspectiveTransform)
  {
    PerspectiveTransform localPerspectiveTransform = new PerspectiveTransform(this.a11 * paramPerspectiveTransform.a11 + this.a21 * paramPerspectiveTransform.a12 + this.a31 * paramPerspectiveTransform.a13, this.a11 * paramPerspectiveTransform.a21 + this.a21 * paramPerspectiveTransform.a22 + this.a31 * paramPerspectiveTransform.a23, this.a11 * paramPerspectiveTransform.a31 + this.a21 * paramPerspectiveTransform.a32 + this.a31 * paramPerspectiveTransform.a33, this.a12 * paramPerspectiveTransform.a11 + this.a22 * paramPerspectiveTransform.a12 + this.a32 * paramPerspectiveTransform.a13, this.a12 * paramPerspectiveTransform.a21 + this.a22 * paramPerspectiveTransform.a22 + this.a32 * paramPerspectiveTransform.a23, this.a12 * paramPerspectiveTransform.a31 + this.a22 * paramPerspectiveTransform.a32 + this.a32 * paramPerspectiveTransform.a33, this.a13 * paramPerspectiveTransform.a11 + this.a23 * paramPerspectiveTransform.a12 + this.a33 * paramPerspectiveTransform.a13, this.a13 * paramPerspectiveTransform.a21 + this.a23 * paramPerspectiveTransform.a22 + this.a33 * paramPerspectiveTransform.a23, this.a13 * paramPerspectiveTransform.a31 + this.a23 * paramPerspectiveTransform.a32 + this.a33 * paramPerspectiveTransform.a33);
    return localPerspectiveTransform;
  }

  public void transformPoints(float[] paramArrayOfFloat)
  {
    int i = paramArrayOfFloat.length;
    float f1 = this.a11;
    float f2 = this.a12;
    float f3 = this.a13;
    float f4 = this.a21;
    float f5 = this.a22;
    float f6 = this.a23;
    float f7 = this.a31;
    float f8 = this.a32;
    float f9 = this.a33;
    for (int j = 0; j < i; j += 2)
    {
      float f10 = paramArrayOfFloat[j];
      int k = j + 1;
      float f11 = paramArrayOfFloat[k];
      float f12 = f9 + (f3 * f10 + f6 * f11);
      paramArrayOfFloat[j] = ((f7 + (f1 * f10 + f4 * f11)) / f12);
      paramArrayOfFloat[k] = ((f8 + (f10 * f2 + f11 * f5)) / f12);
    }
  }

  public void transformPoints(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    int i = paramArrayOfFloat1.length;
    for (int j = 0; j < i; j++)
    {
      float f1 = paramArrayOfFloat1[j];
      float f2 = paramArrayOfFloat2[j];
      float f3 = f1 * this.a13 + f2 * this.a23 + this.a33;
      paramArrayOfFloat1[j] = ((f1 * this.a11 + f2 * this.a21 + this.a31) / f3);
      paramArrayOfFloat2[j] = ((f1 * this.a12 + f2 * this.a22 + this.a32) / f3);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.PerspectiveTransform
 * JD-Core Version:    0.6.1
 */