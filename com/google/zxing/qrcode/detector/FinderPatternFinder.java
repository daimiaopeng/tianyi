package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder
{
  private static final int CENTER_QUORUM = 2;
  private static final int INTEGER_MATH_SHIFT = 8;
  protected static final int MAX_MODULES = 57;
  protected static final int MIN_SKIP = 3;
  private final int[] crossCheckStateCount;
  private boolean hasSkipped;
  private final BitMatrix image;
  private final List<FinderPattern> possibleCenters;
  private final ResultPointCallback resultPointCallback;

  public FinderPatternFinder(BitMatrix paramBitMatrix)
  {
    this(paramBitMatrix, null);
  }

  public FinderPatternFinder(BitMatrix paramBitMatrix, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new ArrayList();
    this.crossCheckStateCount = new int[5];
    this.resultPointCallback = paramResultPointCallback;
  }

  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[4] - paramArrayOfInt[3] - paramArrayOfInt[2] / 2.0F;
  }

  private float crossCheckHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int i = localBitMatrix.getWidth();
    int[] arrayOfInt = getCrossCheckStateCount();
    for (int j = paramInt1; (j >= 0) && (localBitMatrix.get(j, paramInt2)); j--)
      arrayOfInt[2] = (1 + arrayOfInt[2]);
    float f = (0.0F / 0.0F);
    if (j < 0)
      return f;
    while ((j >= 0) && (!localBitMatrix.get(j, paramInt2)) && (arrayOfInt[1] <= paramInt3))
    {
      arrayOfInt[1] = (1 + arrayOfInt[1]);
      j--;
    }
    if ((j >= 0) && (arrayOfInt[1] <= paramInt3))
    {
      while ((j >= 0) && (localBitMatrix.get(j, paramInt2)) && (arrayOfInt[0] <= paramInt3))
      {
        arrayOfInt[0] = (1 + arrayOfInt[0]);
        j--;
      }
      if (arrayOfInt[0] > paramInt3)
        return f;
      for (int k = paramInt1 + 1; (k < i) && (localBitMatrix.get(k, paramInt2)); k++)
        arrayOfInt[2] = (1 + arrayOfInt[2]);
      if (k == i)
        return f;
      while ((k < i) && (!localBitMatrix.get(k, paramInt2)) && (arrayOfInt[3] < paramInt3))
      {
        arrayOfInt[3] = (1 + arrayOfInt[3]);
        k++;
      }
      if ((k != i) && (arrayOfInt[3] < paramInt3))
      {
        while ((k < i) && (localBitMatrix.get(k, paramInt2)) && (arrayOfInt[4] < paramInt3))
        {
          arrayOfInt[4] = (1 + arrayOfInt[4]);
          k++;
        }
        if (arrayOfInt[4] >= paramInt3)
          return f;
        if (5 * Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) >= paramInt4)
          return f;
        if (foundPatternCross(arrayOfInt))
          f = centerFromEnd(arrayOfInt, k);
        return f;
      }
      return f;
    }
    return f;
  }

  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int i = localBitMatrix.getHeight();
    int[] arrayOfInt = getCrossCheckStateCount();
    for (int j = paramInt1; (j >= 0) && (localBitMatrix.get(paramInt2, j)); j--)
      arrayOfInt[2] = (1 + arrayOfInt[2]);
    float f = (0.0F / 0.0F);
    if (j < 0)
      return f;
    while ((j >= 0) && (!localBitMatrix.get(paramInt2, j)) && (arrayOfInt[1] <= paramInt3))
    {
      arrayOfInt[1] = (1 + arrayOfInt[1]);
      j--;
    }
    if ((j >= 0) && (arrayOfInt[1] <= paramInt3))
    {
      while ((j >= 0) && (localBitMatrix.get(paramInt2, j)) && (arrayOfInt[0] <= paramInt3))
      {
        arrayOfInt[0] = (1 + arrayOfInt[0]);
        j--;
      }
      if (arrayOfInt[0] > paramInt3)
        return f;
      for (int k = paramInt1 + 1; (k < i) && (localBitMatrix.get(paramInt2, k)); k++)
        arrayOfInt[2] = (1 + arrayOfInt[2]);
      if (k == i)
        return f;
      while ((k < i) && (!localBitMatrix.get(paramInt2, k)) && (arrayOfInt[3] < paramInt3))
      {
        arrayOfInt[3] = (1 + arrayOfInt[3]);
        k++;
      }
      if ((k != i) && (arrayOfInt[3] < paramInt3))
      {
        while ((k < i) && (localBitMatrix.get(paramInt2, k)) && (arrayOfInt[4] < paramInt3))
        {
          arrayOfInt[4] = (1 + arrayOfInt[4]);
          k++;
        }
        if (arrayOfInt[4] >= paramInt3)
          return f;
        if (5 * Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) >= paramInt4 * 2)
          return f;
        if (foundPatternCross(arrayOfInt))
          f = centerFromEnd(arrayOfInt, k);
        return f;
      }
      return f;
    }
    return f;
  }

  private int findRowSkip()
  {
    if (this.possibleCenters.size() <= 1)
      return 0;
    Object localObject = null;
    Iterator localIterator = this.possibleCenters.iterator();
    while (localIterator.hasNext())
    {
      FinderPattern localFinderPattern = (FinderPattern)localIterator.next();
      if (localFinderPattern.getCount() >= 2)
        if (localObject == null)
        {
          localObject = localFinderPattern;
        }
        else
        {
          this.hasSkipped = true;
          return (int)(Math.abs(localObject.getX() - localFinderPattern.getX()) - Math.abs(localObject.getY() - localFinderPattern.getY())) / 2;
        }
    }
    return 0;
  }

  protected static boolean foundPatternCross(int[] paramArrayOfInt)
  {
    int i = 0;
    int j = 0;
    while (i < 5)
    {
      int i6 = paramArrayOfInt[i];
      if (i6 == 0)
        return false;
      j += i6;
      i++;
    }
    if (j < 7)
      return false;
    int k = (j << 8) / 7;
    int m = k / 2;
    int n = Math.abs(k - (paramArrayOfInt[0] << 8));
    boolean bool = false;
    if (n < m)
    {
      int i1 = Math.abs(k - (paramArrayOfInt[1] << 8));
      bool = false;
      if (i1 < m)
      {
        int i2 = Math.abs(k * 3 - (paramArrayOfInt[2] << 8));
        int i3 = m * 3;
        bool = false;
        if (i2 < i3)
        {
          int i4 = Math.abs(k - (paramArrayOfInt[3] << 8));
          bool = false;
          if (i4 < m)
          {
            int i5 = Math.abs(k - (paramArrayOfInt[4] << 8));
            bool = false;
            if (i5 < m)
              bool = true;
          }
        }
      }
    }
    return bool;
  }

  private int[] getCrossCheckStateCount()
  {
    this.crossCheckStateCount[0] = 0;
    this.crossCheckStateCount[1] = 0;
    this.crossCheckStateCount[2] = 0;
    this.crossCheckStateCount[3] = 0;
    this.crossCheckStateCount[4] = 0;
    return this.crossCheckStateCount;
  }

  private boolean haveMultiplyConfirmedCenters()
  {
    int i = this.possibleCenters.size();
    Iterator localIterator1 = this.possibleCenters.iterator();
    float f1 = 0.0F;
    int j = 0;
    float f2 = 0.0F;
    while (localIterator1.hasNext())
    {
      FinderPattern localFinderPattern = (FinderPattern)localIterator1.next();
      if (localFinderPattern.getCount() >= 2)
      {
        j++;
        f2 += localFinderPattern.getEstimatedModuleSize();
      }
    }
    if (j < 3)
      return false;
    float f3 = f2 / i;
    Iterator localIterator2 = this.possibleCenters.iterator();
    while (localIterator2.hasNext())
      f1 += Math.abs(((FinderPattern)localIterator2.next()).getEstimatedModuleSize() - f3);
    boolean bool1 = f1 < f2 * 0.05F;
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    return bool2;
  }

  private FinderPattern[] selectBestPatterns()
  {
    int i = this.possibleCenters.size();
    if (i < 3)
      throw NotFoundException.getNotFoundInstance();
    float f1 = 0.0F;
    if (i > 3)
    {
      Iterator localIterator2 = this.possibleCenters.iterator();
      float f3 = 0.0F;
      float f9;
      for (float f4 = 0.0F; localIterator2.hasNext(); f4 += f9 * f9)
      {
        f9 = ((FinderPattern)localIterator2.next()).getEstimatedModuleSize();
        f3 += f9;
      }
      float f5 = i;
      float f6 = f3 / f5;
      float f7 = (float)Math.sqrt(f4 / f5 - f6 * f6);
      Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f6, null));
      float f8 = Math.max(0.2F * f6, f7);
      for (int j = 0; (j < this.possibleCenters.size()) && (this.possibleCenters.size() > 3); j++)
        if (Math.abs(((FinderPattern)this.possibleCenters.get(j)).getEstimatedModuleSize() - f6) > f8)
        {
          this.possibleCenters.remove(j);
          j--;
        }
    }
    if (this.possibleCenters.size() > 3)
    {
      Iterator localIterator1 = this.possibleCenters.iterator();
      while (localIterator1.hasNext())
        f1 += ((FinderPattern)localIterator1.next()).getEstimatedModuleSize();
      float f2 = f1 / this.possibleCenters.size();
      Collections.sort(this.possibleCenters, new CenterComparator(f2, null));
      this.possibleCenters.subList(3, this.possibleCenters.size()).clear();
    }
    FinderPattern[] arrayOfFinderPattern = new FinderPattern[3];
    arrayOfFinderPattern[0] = ((FinderPattern)this.possibleCenters.get(0));
    arrayOfFinderPattern[1] = ((FinderPattern)this.possibleCenters.get(1));
    arrayOfFinderPattern[2] = ((FinderPattern)this.possibleCenters.get(2));
    return arrayOfFinderPattern;
  }

  final FinderPatternInfo find(Map<DecodeHintType, ?> paramMap)
  {
    int i;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
      i = 1;
    else
      i = 0;
    int j = this.image.getHeight();
    int k = this.image.getWidth();
    int m = j * 3 / 228;
    if ((m < 3) || (i != 0))
      m = 3;
    int[] arrayOfInt = new int[5];
    int n = m - 1;
    int i1 = m;
    boolean bool1 = false;
    while ((n < j) && (!bool1))
    {
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 0;
      arrayOfInt[4] = 0;
      boolean bool2 = bool1;
      int i2 = i1;
      int i3 = 0;
      int i4 = 0;
      while (i3 < k)
      {
        if (this.image.get(i3, n))
        {
          if ((i4 & 0x1) == 1)
            i4++;
          arrayOfInt[i4] = (1 + arrayOfInt[i4]);
        }
        else if ((i4 & 0x1) == 0)
        {
          if (i4 == 4)
          {
            if (foundPatternCross(arrayOfInt))
            {
              if (handlePossibleCenter(arrayOfInt, n, i3))
              {
                if (this.hasSkipped)
                {
                  bool2 = haveMultiplyConfirmedCenters();
                }
                else
                {
                  int i6 = findRowSkip();
                  if (i6 > arrayOfInt[2])
                  {
                    n += i6 - arrayOfInt[2] - 2;
                    i3 = k - 1;
                  }
                }
                arrayOfInt[0] = 0;
                arrayOfInt[1] = 0;
                arrayOfInt[2] = 0;
                arrayOfInt[3] = 0;
                arrayOfInt[4] = 0;
                i2 = 2;
                i4 = 0;
                break label403;
              }
              arrayOfInt[0] = arrayOfInt[2];
              arrayOfInt[1] = arrayOfInt[3];
              arrayOfInt[2] = arrayOfInt[4];
              arrayOfInt[3] = 1;
              arrayOfInt[4] = 0;
            }
            else
            {
              arrayOfInt[0] = arrayOfInt[2];
              arrayOfInt[1] = arrayOfInt[3];
              arrayOfInt[2] = arrayOfInt[4];
              arrayOfInt[3] = 1;
              arrayOfInt[4] = 0;
            }
            i4 = 3;
          }
          else
          {
            i4++;
            arrayOfInt[i4] = (1 + arrayOfInt[i4]);
          }
        }
        else
        {
          arrayOfInt[i4] = (1 + arrayOfInt[i4]);
        }
        label403: i3++;
      }
      if ((foundPatternCross(arrayOfInt)) && (handlePossibleCenter(arrayOfInt, n, k)))
      {
        int i5 = arrayOfInt[0];
        if (this.hasSkipped)
        {
          boolean bool3 = haveMultiplyConfirmedCenters();
          i1 = i5;
          bool1 = bool3;
          break label475;
        }
        i1 = i5;
      }
      else
      {
        i1 = i2;
      }
      bool1 = bool2;
      label475: n += i1;
    }
    FinderPattern[] arrayOfFinderPattern = selectBestPatterns();
    ResultPoint.orderBestPatterns(arrayOfFinderPattern);
    return new FinderPatternInfo(arrayOfFinderPattern);
  }

  protected final BitMatrix getImage()
  {
    return this.image;
  }

  protected final List<FinderPattern> getPossibleCenters()
  {
    return this.possibleCenters;
  }

  protected final boolean handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2] + paramArrayOfInt[3] + paramArrayOfInt[4];
    int j = (int)centerFromEnd(paramArrayOfInt, paramInt2);
    float f1 = crossCheckVertical(paramInt1, j, paramArrayOfInt[2], i);
    if (!Float.isNaN(f1))
    {
      float f2 = crossCheckHorizontal(j, (int)f1, paramArrayOfInt[2], i);
      if (!Float.isNaN(f2))
      {
        float f3 = i / 7.0F;
        int n;
        for (int k = 0; ; k++)
        {
          int m = this.possibleCenters.size();
          n = 0;
          if (k >= m)
            break;
          FinderPattern localFinderPattern2 = (FinderPattern)this.possibleCenters.get(k);
          if (localFinderPattern2.aboutEquals(f3, f1, f2))
          {
            this.possibleCenters.set(k, localFinderPattern2.combineEstimate(f1, f2, f3));
            n = 1;
            break;
          }
        }
        if (n == 0)
        {
          FinderPattern localFinderPattern1 = new FinderPattern(f2, f1, f3);
          this.possibleCenters.add(localFinderPattern1);
          if (this.resultPointCallback != null)
            this.resultPointCallback.foundPossibleResultPoint(localFinderPattern1);
        }
        return true;
      }
    }
    return false;
  }

  private static final class CenterComparator
    implements Serializable, Comparator<FinderPattern>
  {
    private final float average;

    private CenterComparator(float paramFloat)
    {
      this.average = paramFloat;
    }

    public int compare(FinderPattern paramFinderPattern1, FinderPattern paramFinderPattern2)
    {
      if (paramFinderPattern2.getCount() == paramFinderPattern1.getCount())
      {
        float f1 = Math.abs(paramFinderPattern2.getEstimatedModuleSize() - this.average);
        float f2 = Math.abs(paramFinderPattern1.getEstimatedModuleSize() - this.average);
        int i;
        if (f1 < f2)
          i = 1;
        else if (f1 == f2)
          i = 0;
        else
          i = -1;
        return i;
      }
      return paramFinderPattern2.getCount() - paramFinderPattern1.getCount();
    }
  }

  private static final class FurthestFromAverageComparator
    implements Serializable, Comparator<FinderPattern>
  {
    private final float average;

    private FurthestFromAverageComparator(float paramFloat)
    {
      this.average = paramFloat;
    }

    public int compare(FinderPattern paramFinderPattern1, FinderPattern paramFinderPattern2)
    {
      float f1 = Math.abs(paramFinderPattern2.getEstimatedModuleSize() - this.average);
      float f2 = Math.abs(paramFinderPattern1.getEstimatedModuleSize() - this.average);
      int i;
      if (f1 < f2)
        i = -1;
      else if (f1 == f2)
        i = 0;
      else
        i = 1;
      return i;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPatternFinder
 * JD-Core Version:    0.6.1
 */