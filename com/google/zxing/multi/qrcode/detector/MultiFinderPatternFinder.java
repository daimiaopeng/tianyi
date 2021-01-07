package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MultiFinderPatternFinder extends FinderPatternFinder
{
  private static final float DIFF_MODSIZE_CUTOFF = 0.5F;
  private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05F;
  private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
  private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0F;
  private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0F;

  MultiFinderPatternFinder(BitMatrix paramBitMatrix)
  {
    super(paramBitMatrix);
  }

  MultiFinderPatternFinder(BitMatrix paramBitMatrix, ResultPointCallback paramResultPointCallback)
  {
    super(paramBitMatrix, paramResultPointCallback);
  }

  private FinderPattern[][] selectMutipleBestPatterns()
  {
    List localList = getPossibleCenters();
    int i = localList.size();
    int j = 3;
    if (i < j)
      throw NotFoundException.getNotFoundInstance();
    if (i == j)
    {
      FinderPattern[][] arrayOfFinderPattern; = new FinderPattern[1][];
      FinderPattern[] arrayOfFinderPattern2 = new FinderPattern[j];
      arrayOfFinderPattern2[0] = ((FinderPattern)localList.get(0));
      arrayOfFinderPattern2[1] = ((FinderPattern)localList.get(1));
      arrayOfFinderPattern2[2] = ((FinderPattern)localList.get(2));
      arrayOfFinderPattern;[0] = arrayOfFinderPattern2;
      return arrayOfFinderPattern;;
    }
    Collections.sort(localList, new ModuleSizeComparator(null));
    ArrayList localArrayList = new ArrayList();
    int k = 0;
    while (k < i - 2)
    {
      FinderPattern localFinderPattern1 = (FinderPattern)localList.get(k);
      if (localFinderPattern1 != null)
        label555: label563: 
        while (true)
        {
          int m = k + 1;
          while (true)
          {
            if (m >= i - 1)
              break label563;
            FinderPattern localFinderPattern2 = (FinderPattern)localList.get(m);
            if (localFinderPattern2 != null)
              while (true)
              {
                float f1 = (localFinderPattern1.getEstimatedModuleSize() - localFinderPattern2.getEstimatedModuleSize()) / Math.min(localFinderPattern1.getEstimatedModuleSize(), localFinderPattern2.getEstimatedModuleSize());
                float f2 = Math.abs(localFinderPattern1.getEstimatedModuleSize() - localFinderPattern2.getEstimatedModuleSize());
                float f3 = 0.5F;
                boolean bool = f2 < f3;
                float f4 = 0.05F;
                if ((bool) && (f1 >= f4))
                  break;
                int n = m + 1;
                while (true)
                {
                  if (n >= i)
                    break label555;
                  FinderPattern localFinderPattern3 = (FinderPattern)localList.get(n);
                  if (localFinderPattern3 != null)
                  {
                    float f5 = (localFinderPattern2.getEstimatedModuleSize() - localFinderPattern3.getEstimatedModuleSize()) / Math.min(localFinderPattern2.getEstimatedModuleSize(), localFinderPattern3.getEstimatedModuleSize());
                    if ((Math.abs(localFinderPattern2.getEstimatedModuleSize() - localFinderPattern3.getEstimatedModuleSize()) > f3) && (f5 >= f4))
                      break;
                    FinderPattern[] arrayOfFinderPattern1 = new FinderPattern[j];
                    arrayOfFinderPattern1[0] = localFinderPattern1;
                    arrayOfFinderPattern1[1] = localFinderPattern2;
                    arrayOfFinderPattern1[2] = localFinderPattern3;
                    ResultPoint.orderBestPatterns(arrayOfFinderPattern1);
                    FinderPatternInfo localFinderPatternInfo = new FinderPatternInfo(arrayOfFinderPattern1);
                    float f6 = ResultPoint.distance(localFinderPatternInfo.getTopLeft(), localFinderPatternInfo.getBottomLeft());
                    float f7 = ResultPoint.distance(localFinderPatternInfo.getTopRight(), localFinderPatternInfo.getBottomLeft());
                    float f8 = ResultPoint.distance(localFinderPatternInfo.getTopLeft(), localFinderPatternInfo.getTopRight());
                    float f9 = (f6 + f8) / (2.0F * localFinderPattern1.getEstimatedModuleSize());
                    if ((f9 <= 180.0F) && (f9 >= 9.0F) && (Math.abs((f6 - f8) / Math.min(f6, f8)) < 0.1F))
                    {
                      float f10 = (float)Math.sqrt(f6 * f6 + f8 * f8);
                      if (Math.abs((f7 - f10) / Math.min(f7, f10)) < 0.1F)
                        localArrayList.add(arrayOfFinderPattern1);
                    }
                  }
                  n++;
                  j = 3;
                  f3 = 0.5F;
                  f4 = 0.05F;
                }
              }
            m++;
            j = 3;
          }
        }
      k++;
      j = 3;
    }
    if (!localArrayList.isEmpty())
      return (FinderPattern[][])localArrayList.toArray(new FinderPattern[localArrayList.size()][]);
    throw NotFoundException.getNotFoundInstance();
  }

  public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> paramMap)
  {
    int i = 0;
    int j;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
      j = 1;
    else
      j = 0;
    BitMatrix localBitMatrix = getImage();
    int k = localBitMatrix.getHeight();
    int m = localBitMatrix.getWidth();
    int n = (int)(3.0F * (k / 228.0F));
    if ((n < 3) || (j != 0))
      n = 3;
    int[] arrayOfInt = new int[5];
    int i1 = n - 1;
    while (i1 < k)
    {
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 0;
      arrayOfInt[4] = 0;
      int i3 = 0;
      int i4 = 0;
      while (i3 < m)
      {
        if (localBitMatrix.get(i3, i1))
        {
          if ((i4 & 0x1) == 1)
            i4++;
          arrayOfInt[i4] = (1 + arrayOfInt[i4]);
        }
        else if ((i4 & 0x1) == 0)
        {
          if (i4 == 4)
          {
            if ((foundPatternCross(arrayOfInt)) && (handlePossibleCenter(arrayOfInt, i1, i3)))
            {
              arrayOfInt[0] = 0;
              arrayOfInt[1] = 0;
              arrayOfInt[2] = 0;
              arrayOfInt[3] = 0;
              arrayOfInt[4] = 0;
              i4 = 0;
            }
            else
            {
              arrayOfInt[0] = arrayOfInt[2];
              arrayOfInt[1] = arrayOfInt[3];
              arrayOfInt[2] = arrayOfInt[4];
              arrayOfInt[3] = 1;
              arrayOfInt[4] = 0;
              i4 = 3;
            }
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
        i3++;
      }
      if (foundPatternCross(arrayOfInt))
        handlePossibleCenter(arrayOfInt, i1, m);
      i1 += n;
    }
    FinderPattern[][] arrayOfFinderPattern = selectMutipleBestPatterns();
    ArrayList localArrayList = new ArrayList();
    int i2 = arrayOfFinderPattern.length;
    while (i < i2)
    {
      FinderPattern[] arrayOfFinderPattern1 = arrayOfFinderPattern[i];
      ResultPoint.orderBestPatterns(arrayOfFinderPattern1);
      localArrayList.add(new FinderPatternInfo(arrayOfFinderPattern1));
      i++;
    }
    if (localArrayList.isEmpty())
      return EMPTY_RESULT_ARRAY;
    return (FinderPatternInfo[])localArrayList.toArray(new FinderPatternInfo[localArrayList.size()]);
  }

  private static final class ModuleSizeComparator
    implements Serializable, Comparator<FinderPattern>
  {
    public int compare(FinderPattern paramFinderPattern1, FinderPattern paramFinderPattern2)
    {
      double d = paramFinderPattern2.getEstimatedModuleSize() - paramFinderPattern1.getEstimatedModuleSize();
      int i;
      if (d < 0.0D)
        i = -1;
      else if (d > 0.0D)
        i = 1;
      else
        i = 0;
      return i;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder
 * JD-Core Version:    0.6.1
 */