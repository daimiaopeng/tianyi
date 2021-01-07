package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector
{
  private static final int MAX_MODULES = 32;
  private final BitMatrix image;

  public MonochromeRectangleDetector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }

  private int[] blackWhiteRange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int i = paramInt3 + paramInt4 >> 1;
    int j = i;
    while (j >= paramInt3)
      if (paramBoolean ? this.image.get(j, paramInt1) : this.image.get(paramInt1, j))
      {
        j--;
      }
      else
      {
        int i2 = j;
        do
          i2--;
        while ((i2 >= paramInt3) && (paramBoolean ? !this.image.get(i2, paramInt1) : !this.image.get(paramInt1, i2)));
        int i3 = j - i2;
        if ((i2 < paramInt3) || (i3 > paramInt2))
          break;
        j = i2;
      }
    int k = j + 1;
    while (i < paramInt4)
      if (paramBoolean ? this.image.get(i, paramInt1) : this.image.get(paramInt1, i))
      {
        i++;
      }
      else
      {
        int n = i;
        do
          n++;
        while ((n < paramInt4) && (paramBoolean ? !this.image.get(n, paramInt1) : !this.image.get(paramInt1, n)));
        int i1 = n - i;
        if ((n >= paramInt4) || (i1 > paramInt2))
          break;
        i = n;
      }
    int m = i - 1;
    int[] arrayOfInt;
    if (m > k)
      arrayOfInt = new int[] { k, m };
    else
      arrayOfInt = null;
    return arrayOfInt;
  }

  private ResultPoint findCornerFromCenter(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    int i = paramInt1;
    int j = paramInt5;
    int[] arrayOfInt;
    for (Object localObject = null; (j < paramInt8) && (j >= paramInt7) && (i < paramInt4) && (i >= paramInt3); localObject = arrayOfInt)
    {
      if (paramInt2 == 0)
        arrayOfInt = blackWhiteRange(j, paramInt9, paramInt3, paramInt4, true);
      else
        arrayOfInt = blackWhiteRange(i, paramInt9, paramInt7, paramInt8, false);
      if (arrayOfInt == null)
      {
        if (localObject == null)
          throw NotFoundException.getNotFoundInstance();
        if (paramInt2 == 0)
        {
          int n = j - paramInt6;
          if (localObject[0] < paramInt1)
          {
            if (localObject[1] > paramInt1)
            {
              if (paramInt6 > 0);
              float f3;
              for (int i1 = localObject[0]; ; i1 = localObject[1])
              {
                f3 = i1;
                break;
              }
              return new ResultPoint(f3, n);
            }
            return new ResultPoint(localObject[0], n);
          }
          return new ResultPoint(localObject[1], n);
        }
        int k = i - paramInt2;
        if (localObject[0] < paramInt5)
        {
          if (localObject[1] > paramInt5)
          {
            float f1 = k;
            if (paramInt2 < 0);
            float f2;
            for (int m = localObject[0]; ; m = localObject[1])
            {
              f2 = m;
              break;
            }
            return new ResultPoint(f1, f2);
          }
          return new ResultPoint(k, localObject[0]);
        }
        return new ResultPoint(k, localObject[1]);
      }
      j += paramInt6;
      i += paramInt2;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public ResultPoint[] detect()
  {
    int i = this.image.getHeight();
    int j = this.image.getWidth();
    int k = i >> 1;
    int m = j >> 1;
    int n = Math.max(1, i / 256);
    int i1 = Math.max(1, j / 256);
    int i2 = -n;
    int i3 = m >> 1;
    int i4 = -1 + (int)findCornerFromCenter(m, 0, 0, j, k, i2, 0, i, i3).getY();
    int i5 = -i1;
    int i6 = k >> 1;
    ResultPoint localResultPoint1 = findCornerFromCenter(m, i5, 0, j, k, 0, i4, i, i6);
    int i7 = -1 + (int)localResultPoint1.getX();
    ResultPoint localResultPoint2 = findCornerFromCenter(m, i1, i7, j, k, 0, i4, i, i6);
    int i8 = 1 + (int)localResultPoint2.getX();
    ResultPoint localResultPoint3 = findCornerFromCenter(m, 0, i7, i8, k, n, i4, i, i3);
    return new ResultPoint[] { findCornerFromCenter(m, 0, i7, i8, k, i2, i4, 1 + (int)localResultPoint3.getY(), m >> 2), localResultPoint1, localResultPoint2, localResultPoint3 };
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.detector.MonochromeRectangleDetector
 * JD-Core Version:    0.6.1
 */