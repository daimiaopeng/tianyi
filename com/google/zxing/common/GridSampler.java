package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public abstract class GridSampler
{
  private static GridSampler gridSampler = new DefaultGridSampler();

  protected static void checkAndNudgePoints(BitMatrix paramBitMatrix, float[] paramArrayOfFloat)
  {
    int i = paramBitMatrix.getWidth();
    int j = paramBitMatrix.getHeight();
    int k = 0;
    int m = 1;
    while ((k < paramArrayOfFloat.length) && (m != 0))
    {
      int i5 = (int)paramArrayOfFloat[k];
      int i6 = k + 1;
      int i7 = (int)paramArrayOfFloat[i6];
      if ((i5 >= -1) && (i5 <= i) && (i7 >= -1) && (i7 <= j))
      {
        if (i5 == -1)
          paramArrayOfFloat[k] = 0.0F;
        while (true)
        {
          m = 1;
          break label112;
          if (i5 != i)
            break;
          paramArrayOfFloat[k] = i - 1;
        }
        m = 0;
        label112: if (i7 == -1)
          paramArrayOfFloat[i6] = 0.0F;
        while (true)
        {
          m = 1;
          break;
          if (i7 != j)
            break;
          paramArrayOfFloat[i6] = j - 1;
        }
        k += 2;
      }
      else
      {
        throw NotFoundException.getNotFoundInstance();
      }
    }
    int n = -2 + paramArrayOfFloat.length;
    int i1 = 1;
    while ((n >= 0) && (i1 != 0))
    {
      int i2 = (int)paramArrayOfFloat[n];
      int i3 = n + 1;
      int i4 = (int)paramArrayOfFloat[i3];
      if ((i2 >= -1) && (i2 <= i) && (i4 >= -1) && (i4 <= j))
      {
        if (i2 == -1)
          paramArrayOfFloat[n] = 0.0F;
        while (true)
        {
          i1 = 1;
          break label260;
          if (i2 != i)
            break;
          paramArrayOfFloat[n] = i - 1;
        }
        i1 = 0;
        label260: if (i4 == -1)
          paramArrayOfFloat[i3] = 0.0F;
        while (true)
        {
          i1 = 1;
          break;
          if (i4 != j)
            break;
          paramArrayOfFloat[i3] = j - 1;
        }
        n -= 2;
      }
      else
      {
        throw NotFoundException.getNotFoundInstance();
      }
    }
  }

  public static GridSampler getInstance()
  {
    return gridSampler;
  }

  public static void setGridSampler(GridSampler paramGridSampler)
  {
    gridSampler = paramGridSampler;
  }

  public abstract BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16);

  public abstract BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, PerspectiveTransform paramPerspectiveTransform);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.GridSampler
 * JD-Core Version:    0.6.1
 */