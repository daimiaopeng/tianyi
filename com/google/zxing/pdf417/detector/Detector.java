package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import java.util.Arrays;
import java.util.Map;

public final class Detector
{
  private static final int INTEGER_MATH_SHIFT = 8;
  private static final int MAX_AVG_VARIANCE = 107;
  private static final int MAX_INDIVIDUAL_VARIANCE = 204;
  private static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
  private static final int[] START_PATTERN = { 8, 1, 1, 1, 1, 1, 1, 3 };
  private static final int[] START_PATTERN_REVERSE = { 3, 1, 1, 1, 1, 1, 1, 8 };
  private static final int[] STOP_PATTERN = { 7, 1, 1, 3, 1, 1, 1, 2, 1 };
  private static final int[] STOP_PATTERN_REVERSE = { 1, 2, 1, 1, 1, 3, 1, 1, 7 };
  private final BinaryBitmap image;

  public Detector(BinaryBitmap paramBinaryBitmap)
  {
    this.image = paramBinaryBitmap;
  }

  private static int computeDimension(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, float paramFloat)
  {
    return 17 * ((8 + (MathUtils.round(ResultPoint.distance(paramResultPoint1, paramResultPoint2) / paramFloat) + MathUtils.round(ResultPoint.distance(paramResultPoint3, paramResultPoint4) / paramFloat) >> 1)) / 17);
  }

  private static float computeModuleWidth(ResultPoint[] paramArrayOfResultPoint)
  {
    return ((ResultPoint.distance(paramArrayOfResultPoint[0], paramArrayOfResultPoint[4]) + ResultPoint.distance(paramArrayOfResultPoint[1], paramArrayOfResultPoint[5])) / 34.0F + (ResultPoint.distance(paramArrayOfResultPoint[6], paramArrayOfResultPoint[2]) + ResultPoint.distance(paramArrayOfResultPoint[7], paramArrayOfResultPoint[3])) / 36.0F) / 2.0F;
  }

  private static int computeYDimension(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, float paramFloat)
  {
    return MathUtils.round(ResultPoint.distance(paramResultPoint1, paramResultPoint3) / paramFloat) + MathUtils.round(ResultPoint.distance(paramResultPoint2, paramResultPoint4) / paramFloat) >> 1;
  }

  private static void correctVertices(BitMatrix paramBitMatrix, ResultPoint[] paramArrayOfResultPoint, boolean paramBoolean)
  {
    int i;
    if (Math.abs(paramArrayOfResultPoint[4].getY() - paramArrayOfResultPoint[5].getY()) < 20.0D)
      i = 1;
    else
      i = 0;
    boolean bool = Math.abs(paramArrayOfResultPoint[6].getY() - paramArrayOfResultPoint[7].getY()) < 20.0D;
    int j = 0;
    if (bool)
      j = 1;
    if ((i == 0) && (j == 0))
    {
      int k;
      if (paramBoolean)
        k = 1;
      else
        k = -1;
      findWideBarTopBottom(paramBitMatrix, paramArrayOfResultPoint, 0, 0, 8, 17, k);
      int m;
      if (paramBoolean)
        m = -1;
      else
        m = 1;
      findWideBarTopBottom(paramBitMatrix, paramArrayOfResultPoint, 1, 0, 8, 17, m);
      int n;
      if (paramBoolean)
        n = 1;
      else
        n = -1;
      findWideBarTopBottom(paramBitMatrix, paramArrayOfResultPoint, 2, 11, 7, 18, n);
      int i1;
      if (paramBoolean)
        i1 = -1;
      else
        i1 = 1;
      findWideBarTopBottom(paramBitMatrix, paramArrayOfResultPoint, 3, 11, 7, 18, i1);
      findCrossingPoint(paramArrayOfResultPoint, 12, 4, 5, 8, 10, paramBitMatrix);
      findCrossingPoint(paramArrayOfResultPoint, 13, 4, 5, 9, 11, paramBitMatrix);
      findCrossingPoint(paramArrayOfResultPoint, 14, 6, 7, 8, 10, paramBitMatrix);
      findCrossingPoint(paramArrayOfResultPoint, 15, 6, 7, 9, 11, paramBitMatrix);
      return;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static void findCrossingPoint(ResultPoint[] paramArrayOfResultPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BitMatrix paramBitMatrix)
  {
    ResultPoint localResultPoint = intersection(paramArrayOfResultPoint[paramInt2], paramArrayOfResultPoint[paramInt3], paramArrayOfResultPoint[paramInt4], paramArrayOfResultPoint[paramInt5]);
    if (localResultPoint == null)
      throw NotFoundException.getNotFoundInstance();
    int i = Math.round(localResultPoint.getX());
    int j = Math.round(localResultPoint.getY());
    if ((i >= 0) && (i < paramBitMatrix.getWidth()) && (j >= 0) && (j < paramBitMatrix.getHeight()))
    {
      paramArrayOfResultPoint[paramInt1] = localResultPoint;
      return;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int[] findGuardPattern(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    Arrays.fill(paramArrayOfInt2, 0, paramArrayOfInt2.length, 0);
    int i = paramArrayOfInt1.length;
    int j = paramInt1;
    int k = j;
    boolean bool1 = paramBoolean;
    int m = 0;
    while (j < paramInt1 + paramInt3)
    {
      if ((bool1 ^ paramBitMatrix.get(j, paramInt2)))
      {
        paramArrayOfInt2[m] = (1 + paramArrayOfInt2[m]);
      }
      else
      {
        int n = i - 1;
        if (m == n)
        {
          if (patternMatchVariance(paramArrayOfInt2, paramArrayOfInt1, 204) < 107)
            return new int[] { k, j };
          k += paramArrayOfInt2[0] + paramArrayOfInt2[1];
          int i1 = i - 2;
          System.arraycopy(paramArrayOfInt2, 2, paramArrayOfInt2, 0, i1);
          paramArrayOfInt2[i1] = 0;
          paramArrayOfInt2[n] = 0;
          m--;
        }
        else
        {
          m++;
        }
        boolean bool2 = true;
        paramArrayOfInt2[m] = bool2;
        if (bool1)
          bool2 = false;
        bool1 = bool2;
      }
      j++;
    }
    return null;
  }

  private static ResultPoint[] findVertices(BitMatrix paramBitMatrix, int paramInt)
  {
    int i = paramBitMatrix.getHeight();
    int j = paramBitMatrix.getWidth();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[16];
    int[] arrayOfInt1 = new int[START_PATTERN.length];
    int k = 0;
    int m;
    while (true)
    {
      m = 1;
      if (k >= i)
        break;
      int[] arrayOfInt9 = START_PATTERN;
      int[] arrayOfInt10 = findGuardPattern(paramBitMatrix, 0, k, j, false, arrayOfInt9, arrayOfInt1);
      if (arrayOfInt10 != null)
      {
        float f7 = arrayOfInt10[0];
        float f8 = k;
        arrayOfResultPoint[0] = new ResultPoint(f7, f8);
        arrayOfResultPoint[4] = new ResultPoint(arrayOfInt10[m], f8);
        n = 1;
        break label126;
      }
      k += paramInt;
    }
    int n = 0;
    label126: if (n != 0)
    {
      int i3 = i - 1;
      while (i3 > 0)
      {
        int[] arrayOfInt7 = START_PATTERN;
        int[] arrayOfInt8 = findGuardPattern(paramBitMatrix, 0, i3, j, false, arrayOfInt7, arrayOfInt1);
        if (arrayOfInt8 != null)
        {
          float f5 = arrayOfInt8[0];
          float f6 = i3;
          arrayOfResultPoint[m] = new ResultPoint(f5, f6);
          arrayOfResultPoint[5] = new ResultPoint(arrayOfInt8[m], f6);
          n = 1;
          break label231;
        }
        i3 -= paramInt;
      }
      n = 0;
    }
    label231: int[] arrayOfInt2 = new int[STOP_PATTERN.length];
    if (n != 0)
    {
      int i2 = 0;
      while (i2 < i)
      {
        int[] arrayOfInt5 = STOP_PATTERN;
        int[] arrayOfInt6 = findGuardPattern(paramBitMatrix, 0, i2, j, false, arrayOfInt5, arrayOfInt2);
        if (arrayOfInt6 != null)
        {
          float f3 = arrayOfInt6[m];
          float f4 = i2;
          arrayOfResultPoint[2] = new ResultPoint(f3, f4);
          arrayOfResultPoint[6] = new ResultPoint(arrayOfInt6[0], f4);
          n = 1;
          break label343;
        }
        i2 += paramInt;
      }
      n = 0;
    }
    label343: if (n != 0)
    {
      int i1 = i - m;
      while (i1 > 0)
      {
        int[] arrayOfInt3 = STOP_PATTERN;
        int[] arrayOfInt4 = findGuardPattern(paramBitMatrix, 0, i1, j, false, arrayOfInt3, arrayOfInt2);
        if (arrayOfInt4 != null)
        {
          float f1 = arrayOfInt4[m];
          float f2 = i1;
          arrayOfResultPoint[3] = new ResultPoint(f1, f2);
          arrayOfResultPoint[7] = new ResultPoint(arrayOfInt4[0], f2);
          break label453;
        }
        i1 -= paramInt;
      }
      m = 0;
    }
    else
    {
      m = n;
    }
    label453: if (m == 0)
      arrayOfResultPoint = null;
    return arrayOfResultPoint;
  }

  private static ResultPoint[] findVertices180(BitMatrix paramBitMatrix, int paramInt)
  {
    int i = paramBitMatrix.getHeight();
    int j = paramBitMatrix.getWidth() >> 1;
    ResultPoint[] arrayOfResultPoint = new ResultPoint[16];
    int[] arrayOfInt1 = new int[START_PATTERN_REVERSE.length];
    int k = i - 1;
    int m = k;
    while (m > 0)
    {
      int[] arrayOfInt9 = START_PATTERN_REVERSE;
      int[] arrayOfInt10 = findGuardPattern(paramBitMatrix, j, m, j, true, arrayOfInt9, arrayOfInt1);
      if (arrayOfInt10 != null)
      {
        float f7 = arrayOfInt10[1];
        float f8 = m;
        arrayOfResultPoint[0] = new ResultPoint(f7, f8);
        arrayOfResultPoint[4] = new ResultPoint(arrayOfInt10[0], f8);
        n = 1;
        break label129;
      }
      m -= paramInt;
    }
    int n = 0;
    label129: if (n != 0)
    {
      int i3 = 0;
      while (i3 < i)
      {
        int[] arrayOfInt7 = START_PATTERN_REVERSE;
        int[] arrayOfInt8 = findGuardPattern(paramBitMatrix, j, i3, j, true, arrayOfInt7, arrayOfInt1);
        if (arrayOfInt8 != null)
        {
          float f5 = arrayOfInt8[1];
          float f6 = i3;
          arrayOfResultPoint[1] = new ResultPoint(f5, f6);
          arrayOfResultPoint[5] = new ResultPoint(arrayOfInt8[0], f6);
          n = 1;
          break label231;
        }
        i3 += paramInt;
      }
      n = 0;
    }
    label231: int[] arrayOfInt2 = new int[STOP_PATTERN_REVERSE.length];
    if (n != 0)
    {
      while (k > 0)
      {
        int[] arrayOfInt5 = STOP_PATTERN_REVERSE;
        int[] arrayOfInt6 = findGuardPattern(paramBitMatrix, 0, k, j, false, arrayOfInt5, arrayOfInt2);
        if (arrayOfInt6 != null)
        {
          float f3 = arrayOfInt6[0];
          float f4 = k;
          arrayOfResultPoint[2] = new ResultPoint(f3, f4);
          arrayOfResultPoint[6] = new ResultPoint(arrayOfInt6[1], f4);
          n = 1;
          break label338;
        }
        k -= paramInt;
      }
      n = 0;
    }
    label338: if (n != 0)
    {
      int i2 = 0;
      while (true)
      {
        i1 = 0;
        if (i2 >= i)
          break;
        int[] arrayOfInt3 = STOP_PATTERN_REVERSE;
        int[] arrayOfInt4 = findGuardPattern(paramBitMatrix, 0, i2, j, false, arrayOfInt3, arrayOfInt2);
        if (arrayOfInt4 != null)
        {
          float f1 = arrayOfInt4[0];
          float f2 = i2;
          arrayOfResultPoint[3] = new ResultPoint(f1, f2);
          arrayOfResultPoint[7] = new ResultPoint(arrayOfInt4[1], f2);
          i1 = 1;
          break;
        }
        i2 += paramInt;
      }
    }
    int i1 = n;
    if (i1 == 0)
      arrayOfResultPoint = null;
    return arrayOfResultPoint;
  }

  private static void findWideBarTopBottom(BitMatrix paramBitMatrix, ResultPoint[] paramArrayOfResultPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ResultPoint localResultPoint1 = paramArrayOfResultPoint[paramInt1];
    ResultPoint localResultPoint2 = paramArrayOfResultPoint[(paramInt1 + 4)];
    int i = paramInt3 + paramInt2;
    float f1 = localResultPoint2.getX() - localResultPoint1.getX();
    float f2 = localResultPoint1.getX();
    float f3 = f1 * paramInt2;
    float f4 = paramInt4;
    float f5 = f2 + f3 / f4;
    float f6 = localResultPoint1.getX() + f1 * i / f4;
    int j = Math.round((f5 + f6) / 2.0F);
    int k = Math.round(localResultPoint1.getY());
    for (int m = 1 + (int)Math.max(f5, f6); (m < paramBitMatrix.getWidth()) && ((paramBitMatrix.get(m - 1, k)) || (!paramBitMatrix.get(m, k))); m++);
    int n = m - j;
    int i1 = j;
    int i2 = k;
    int i3 = 0;
    while (i3 == 0)
      if (paramBitMatrix.get(i1, i2))
      {
        int i5 = i1 + n;
        if ((!paramBitMatrix.get(i5, i2)) && (!paramBitMatrix.get(i5 + 1, i2)))
          i3 = 1;
        else
          i3 = 0;
        i2 += paramInt5;
        if (i2 > 0)
          if (i2 < paramBitMatrix.getHeight() - 1)
            continue;
      }
      else if ((i1 > 0) && (paramBitMatrix.get(i1 - 1, i2)))
      {
        i1--;
      }
      else if (i1 < paramBitMatrix.getWidth() - 1)
      {
        int i4 = i1 + 1;
        if (paramBitMatrix.get(i4, i2))
          i1 = i4;
      }
      else
      {
        if (i2 != k)
          i2 -= paramInt5;
        i3 = 1;
      }
    paramArrayOfResultPoint[(paramInt1 + 8)] = new ResultPoint(i1, i2);
  }

  private static ResultPoint intersection(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
  {
    float f1 = paramResultPoint1.getX() - paramResultPoint2.getX();
    float f2 = paramResultPoint3.getX() - paramResultPoint4.getX();
    float f3 = paramResultPoint1.getY() - paramResultPoint2.getY();
    float f4 = paramResultPoint3.getY() - paramResultPoint4.getY();
    float f5 = paramResultPoint1.getX() * paramResultPoint2.getY() - paramResultPoint1.getY() * paramResultPoint2.getX();
    float f6 = paramResultPoint3.getX() * paramResultPoint4.getY() - paramResultPoint3.getY() * paramResultPoint4.getX();
    float f7 = f1 * f4 - f3 * f2;
    if (f7 == 0.0F)
      return null;
    return new ResultPoint((f2 * f5 - f1 * f6) / f7, (f5 * f4 - f3 * f6) / f7);
  }

  private static int patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int i = paramArrayOfInt1.length;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    while (k < i)
    {
      m += paramArrayOfInt1[k];
      n += paramArrayOfInt2[k];
      k++;
    }
    if (m < n)
      return 2147483647;
    int i1 = (m << 8) / n;
    int i2 = paramInt * i1 >> 8;
    int i3 = 0;
    while (j < i)
    {
      int i4 = paramArrayOfInt1[j] << 8;
      int i5 = i1 * paramArrayOfInt2[j];
      int i6;
      if (i4 > i5)
        i6 = i4 - i5;
      else
        i6 = i5 - i4;
      if (i6 > i2)
        return 2147483647;
      i3 += i6;
      j++;
    }
    return i3 / m;
  }

  private BitMatrix sampleLines(ResultPoint[] paramArrayOfResultPoint, int paramInt1, int paramInt2)
  {
    int i = paramInt1 * 8;
    int j = paramInt2 * 4;
    float f1 = i;
    float f2 = j;
    PerspectiveTransform localPerspectiveTransform = PerspectiveTransform.quadrilateralToQuadrilateral(0.0F, 0.0F, f1, 0.0F, 0.0F, f2, f1, f2, paramArrayOfResultPoint[12].getX(), paramArrayOfResultPoint[12].getY(), paramArrayOfResultPoint[14].getX(), paramArrayOfResultPoint[14].getY(), paramArrayOfResultPoint[13].getX(), paramArrayOfResultPoint[13].getY(), paramArrayOfResultPoint[15].getX(), paramArrayOfResultPoint[15].getY());
    return GridSampler.getInstance().sampleGrid(this.image.getBlackMatrix(), i, j, localPerspectiveTransform);
  }

  public DetectorResult detect()
  {
    return detect(null);
  }

  public DetectorResult detect(Map<DecodeHintType, ?> paramMap)
  {
    BitMatrix localBitMatrix1 = this.image.getBlackMatrix();
    ResultPoint[] arrayOfResultPoint1 = findVertices(localBitMatrix1, 8);
    if (arrayOfResultPoint1 == null)
    {
      arrayOfResultPoint1 = findVertices180(localBitMatrix1, 8);
      if (arrayOfResultPoint1 != null)
        correctVertices(localBitMatrix1, arrayOfResultPoint1, true);
    }
    else
    {
      correctVertices(localBitMatrix1, arrayOfResultPoint1, false);
    }
    if (arrayOfResultPoint1 == null)
      throw NotFoundException.getNotFoundInstance();
    float f = computeModuleWidth(arrayOfResultPoint1);
    if (f < 1.0F)
      throw NotFoundException.getNotFoundInstance();
    int i = computeDimension(arrayOfResultPoint1[12], arrayOfResultPoint1[14], arrayOfResultPoint1[13], arrayOfResultPoint1[15], f);
    if (i < 1)
      throw NotFoundException.getNotFoundInstance();
    BitMatrix localBitMatrix2 = new LinesSampler(sampleLines(arrayOfResultPoint1, i, Math.max(computeYDimension(arrayOfResultPoint1[12], arrayOfResultPoint1[14], arrayOfResultPoint1[13], arrayOfResultPoint1[15], f), i)), i).sample();
    ResultPoint[] arrayOfResultPoint2 = new ResultPoint[4];
    arrayOfResultPoint2[0] = arrayOfResultPoint1[5];
    arrayOfResultPoint2[1] = arrayOfResultPoint1[4];
    arrayOfResultPoint2[2] = arrayOfResultPoint1[6];
    arrayOfResultPoint2[3] = arrayOfResultPoint1[7];
    return new DetectorResult(localBitMatrix2, arrayOfResultPoint2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.detector.Detector
 * JD-Core Version:    0.6.1
 */