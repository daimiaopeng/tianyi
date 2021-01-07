package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Detector
{
  private final BitMatrix image;
  private final WhiteRectangleDetector rectangleDetector;

  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
    this.rectangleDetector = new WhiteRectangleDetector(paramBitMatrix);
  }

  private ResultPoint correctTopRight(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
  {
    float f1 = distance(paramResultPoint1, paramResultPoint2);
    float f2 = paramInt;
    float f3 = f1 / f2;
    int i = distance(paramResultPoint3, paramResultPoint4);
    float f4 = paramResultPoint4.getX() - paramResultPoint3.getX();
    float f5 = i;
    float f6 = f4 / f5;
    float f7 = (paramResultPoint4.getY() - paramResultPoint3.getY()) / f5;
    ResultPoint localResultPoint1 = new ResultPoint(paramResultPoint4.getX() + f6 * f3, paramResultPoint4.getY() + f3 * f7);
    float f8 = distance(paramResultPoint1, paramResultPoint3) / f2;
    int j = distance(paramResultPoint2, paramResultPoint4);
    float f9 = paramResultPoint4.getX() - paramResultPoint2.getX();
    float f10 = j;
    float f11 = f9 / f10;
    float f12 = (paramResultPoint4.getY() - paramResultPoint2.getY()) / f10;
    ResultPoint localResultPoint2 = new ResultPoint(paramResultPoint4.getX() + f11 * f8, paramResultPoint4.getY() + f8 * f12);
    if (!isValid(localResultPoint1))
    {
      if (isValid(localResultPoint2))
        return localResultPoint2;
      return null;
    }
    if (!isValid(localResultPoint2))
      return localResultPoint1;
    if (Math.abs(transitionsBetween(paramResultPoint3, localResultPoint1).getTransitions() - transitionsBetween(paramResultPoint2, localResultPoint1).getTransitions()) <= Math.abs(transitionsBetween(paramResultPoint3, localResultPoint2).getTransitions() - transitionsBetween(paramResultPoint2, localResultPoint2).getTransitions()))
      localResultPoint2 = localResultPoint1;
    return localResultPoint2;
  }

  private ResultPoint correctTopRightRectangular(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt1, int paramInt2)
  {
    float f1 = distance(paramResultPoint1, paramResultPoint2) / paramInt1;
    int i = distance(paramResultPoint3, paramResultPoint4);
    float f2 = paramResultPoint4.getX() - paramResultPoint3.getX();
    float f3 = i;
    float f4 = f2 / f3;
    float f5 = (paramResultPoint4.getY() - paramResultPoint3.getY()) / f3;
    ResultPoint localResultPoint1 = new ResultPoint(paramResultPoint4.getX() + f4 * f1, paramResultPoint4.getY() + f1 * f5);
    float f6 = distance(paramResultPoint1, paramResultPoint3) / paramInt2;
    int j = distance(paramResultPoint2, paramResultPoint4);
    float f7 = paramResultPoint4.getX() - paramResultPoint2.getX();
    float f8 = j;
    float f9 = f7 / f8;
    float f10 = (paramResultPoint4.getY() - paramResultPoint2.getY()) / f8;
    ResultPoint localResultPoint2 = new ResultPoint(paramResultPoint4.getX() + f9 * f6, paramResultPoint4.getY() + f6 * f10);
    if (!isValid(localResultPoint1))
    {
      if (isValid(localResultPoint2))
        return localResultPoint2;
      return null;
    }
    if (!isValid(localResultPoint2))
      return localResultPoint1;
    if (Math.abs(paramInt1 - transitionsBetween(paramResultPoint3, localResultPoint1).getTransitions()) + Math.abs(paramInt2 - transitionsBetween(paramResultPoint2, localResultPoint1).getTransitions()) <= Math.abs(paramInt1 - transitionsBetween(paramResultPoint3, localResultPoint2).getTransitions()) + Math.abs(paramInt2 - transitionsBetween(paramResultPoint2, localResultPoint2).getTransitions()))
      return localResultPoint1;
    return localResultPoint2;
  }

  private static int distance(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    return MathUtils.round(ResultPoint.distance(paramResultPoint1, paramResultPoint2));
  }

  private static void increment(Map<ResultPoint, Integer> paramMap, ResultPoint paramResultPoint)
  {
    Integer localInteger = (Integer)paramMap.get(paramResultPoint);
    int i = 1;
    if (localInteger != null)
      i += localInteger.intValue();
    paramMap.put(paramResultPoint, Integer.valueOf(i));
  }

  private boolean isValid(ResultPoint paramResultPoint)
  {
    boolean bool;
    if ((paramResultPoint.getX() >= 0.0F) && (paramResultPoint.getX() < this.image.getWidth()) && (paramResultPoint.getY() > 0.0F) && (paramResultPoint.getY() < this.image.getHeight()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt1, int paramInt2)
  {
    GridSampler localGridSampler = GridSampler.getInstance();
    float f1 = paramInt1 - 0.5F;
    float f2 = paramInt2 - 0.5F;
    return localGridSampler.sampleGrid(paramBitMatrix, paramInt1, paramInt2, 0.5F, 0.5F, f1, 0.5F, f1, f2, 0.5F, f2, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint4.getX(), paramResultPoint4.getY(), paramResultPoint3.getX(), paramResultPoint3.getY(), paramResultPoint2.getX(), paramResultPoint2.getY());
  }

  private ResultPointsAndTransitions transitionsBetween(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    int i = (int)paramResultPoint1.getX();
    int j = (int)paramResultPoint1.getY();
    int k = (int)paramResultPoint2.getX();
    int m = (int)paramResultPoint2.getY();
    int n = Math.abs(m - j);
    int i1 = Math.abs(k - i);
    int i2 = 0;
    int i3;
    if (n > i1)
      i3 = 1;
    else
      i3 = 0;
    if (i3 != 0)
    {
      int i13 = j;
      j = i;
      i = i13;
      int i14 = m;
      m = k;
      k = i14;
    }
    int i4 = Math.abs(k - i);
    int i5 = Math.abs(m - j);
    int i6 = -i4 >> 1;
    int i7 = -1;
    int i8;
    if (j < m)
      i8 = 1;
    else
      i8 = -1;
    if (i < k)
      i7 = 1;
    BitMatrix localBitMatrix1 = this.image;
    int i9;
    if (i3 != 0)
      i9 = j;
    else
      i9 = i;
    int i10;
    if (i3 != 0)
      i10 = i;
    else
      i10 = j;
    boolean bool1 = localBitMatrix1.get(i9, i10);
    while (i != k)
    {
      BitMatrix localBitMatrix2 = this.image;
      int i11;
      if (i3 != 0)
        i11 = j;
      else
        i11 = i;
      int i12;
      if (i3 != 0)
        i12 = i;
      else
        i12 = j;
      boolean bool2 = localBitMatrix2.get(i11, i12);
      if (bool2 != bool1)
      {
        i2++;
        bool1 = bool2;
      }
      i6 += i5;
      if (i6 > 0)
      {
        if (j == m)
          break;
        j += i8;
        i6 -= i4;
      }
      i += i7;
    }
    return new ResultPointsAndTransitions(paramResultPoint1, paramResultPoint2, i2, null);
  }

  public DetectorResult detect()
  {
    ResultPoint[] arrayOfResultPoint1 = this.rectangleDetector.detect();
    ResultPoint localResultPoint1 = arrayOfResultPoint1[0];
    ResultPoint localResultPoint2 = arrayOfResultPoint1[1];
    ResultPoint localResultPoint3 = arrayOfResultPoint1[2];
    ResultPoint localResultPoint4 = arrayOfResultPoint1[3];
    ArrayList localArrayList = new ArrayList(4);
    localArrayList.add(transitionsBetween(localResultPoint1, localResultPoint2));
    localArrayList.add(transitionsBetween(localResultPoint1, localResultPoint3));
    localArrayList.add(transitionsBetween(localResultPoint2, localResultPoint4));
    localArrayList.add(transitionsBetween(localResultPoint3, localResultPoint4));
    Object localObject1 = null;
    Collections.sort(localArrayList, new ResultPointsAndTransitionsComparator(null));
    ResultPointsAndTransitions localResultPointsAndTransitions1 = (ResultPointsAndTransitions)localArrayList.get(0);
    ResultPointsAndTransitions localResultPointsAndTransitions2 = (ResultPointsAndTransitions)localArrayList.get(1);
    HashMap localHashMap = new HashMap();
    increment(localHashMap, localResultPointsAndTransitions1.getFrom());
    increment(localHashMap, localResultPointsAndTransitions1.getTo());
    increment(localHashMap, localResultPointsAndTransitions2.getFrom());
    increment(localHashMap, localResultPointsAndTransitions2.getTo());
    Iterator localIterator = localHashMap.entrySet().iterator();
    Object localObject2 = null;
    Object localObject3 = null;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ResultPoint localResultPoint11 = (ResultPoint)localEntry.getKey();
      if (((Integer)localEntry.getValue()).intValue() == 2)
        localObject2 = localResultPoint11;
      else if (localObject1 == null)
        localObject1 = localResultPoint11;
      else
        localObject3 = localResultPoint11;
    }
    if ((localObject1 != null) && (localObject2 != null) && (localObject3 != null))
    {
      ResultPoint[] arrayOfResultPoint2 = { localObject1, localObject2, localObject3 };
      ResultPoint.orderBestPatterns(arrayOfResultPoint2);
      ResultPoint localResultPoint5 = arrayOfResultPoint2[0];
      ResultPoint localResultPoint6 = arrayOfResultPoint2[1];
      ResultPoint localResultPoint7 = arrayOfResultPoint2[2];
      Object localObject4;
      if (!localHashMap.containsKey(localResultPoint1))
        localObject4 = localResultPoint1;
      else if (!localHashMap.containsKey(localResultPoint2))
        localObject4 = localResultPoint2;
      else if (!localHashMap.containsKey(localResultPoint3))
        localObject4 = localResultPoint3;
      else
        localObject4 = localResultPoint4;
      int i = transitionsBetween(localResultPoint7, (ResultPoint)localObject4).getTransitions();
      int j = transitionsBetween(localResultPoint5, (ResultPoint)localObject4).getTransitions();
      if ((i & 0x1) == 1)
        i++;
      int k = i + 2;
      if ((j & 0x1) == 1)
        j++;
      int m = j + 2;
      BitMatrix localBitMatrix;
      ResultPoint localResultPoint8;
      if ((k * 4 < m * 7) && (m * 4 < k * 7))
      {
        int i4 = Math.min(m, k);
        ResultPoint localResultPoint10 = correctTopRight(localResultPoint6, localResultPoint5, localResultPoint7, (ResultPoint)localObject4, i4);
        if (localResultPoint10 != null)
          localObject4 = localResultPoint10;
        int i5 = 1 + Math.max(transitionsBetween(localResultPoint7, (ResultPoint)localObject4).getTransitions(), transitionsBetween(localResultPoint5, (ResultPoint)localObject4).getTransitions());
        if ((i5 & 0x1) == 1)
          i5++;
        int i6 = i5;
        localBitMatrix = sampleGrid(this.image, localResultPoint7, localResultPoint6, localResultPoint5, (ResultPoint)localObject4, i6, i6);
        localResultPoint8 = localResultPoint7;
      }
      else
      {
        Object localObject5 = localObject4;
        localResultPoint8 = localResultPoint7;
        ResultPoint localResultPoint9 = correctTopRightRectangular(localResultPoint6, localResultPoint5, localResultPoint7, localObject5, k, m);
        if (localResultPoint9 != null)
          localObject4 = localResultPoint9;
        int n = transitionsBetween(localResultPoint8, (ResultPoint)localObject4).getTransitions();
        int i1 = transitionsBetween(localResultPoint5, (ResultPoint)localObject4).getTransitions();
        if ((n & 0x1) == 1)
          n++;
        int i2 = n;
        if ((i1 & 0x1) == 1)
          i1++;
        int i3 = i1;
        localBitMatrix = sampleGrid(this.image, localResultPoint8, localResultPoint6, localResultPoint5, (ResultPoint)localObject4, i2, i3);
      }
      return new DetectorResult(localBitMatrix, new ResultPoint[] { localResultPoint8, localResultPoint6, localResultPoint5, localObject4 });
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static final class ResultPointsAndTransitions
  {
    private final ResultPoint from;
    private final ResultPoint to;
    private final int transitions;

    private ResultPointsAndTransitions(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, int paramInt)
    {
      this.from = paramResultPoint1;
      this.to = paramResultPoint2;
      this.transitions = paramInt;
    }

    ResultPoint getFrom()
    {
      return this.from;
    }

    ResultPoint getTo()
    {
      return this.to;
    }

    public int getTransitions()
    {
      return this.transitions;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.from);
      localStringBuilder.append("/");
      localStringBuilder.append(this.to);
      localStringBuilder.append('/');
      localStringBuilder.append(this.transitions);
      return localStringBuilder.toString();
    }
  }

  private static final class ResultPointsAndTransitionsComparator
    implements Serializable, Comparator<Detector.ResultPointsAndTransitions>
  {
    public int compare(Detector.ResultPointsAndTransitions paramResultPointsAndTransitions1, Detector.ResultPointsAndTransitions paramResultPointsAndTransitions2)
    {
      return paramResultPointsAndTransitions1.getTransitions() - paramResultPointsAndTransitions2.getTransitions();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.detector.Detector
 * JD-Core Version:    0.6.1
 */