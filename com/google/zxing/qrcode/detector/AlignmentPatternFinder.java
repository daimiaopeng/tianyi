package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class AlignmentPatternFinder
{
  private final int[] crossCheckStateCount;
  private final int height;
  private final BitMatrix image;
  private final float moduleSize;
  private final List<AlignmentPattern> possibleCenters;
  private final ResultPointCallback resultPointCallback;
  private final int startX;
  private final int startY;
  private final int width;

  AlignmentPatternFinder(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new ArrayList(5);
    this.startX = paramInt1;
    this.startY = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.moduleSize = paramFloat;
    this.crossCheckStateCount = new int[3];
    this.resultPointCallback = paramResultPointCallback;
  }

  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[2] - paramArrayOfInt[1] / 2.0F;
  }

  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int i = localBitMatrix.getHeight();
    int[] arrayOfInt = this.crossCheckStateCount;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    for (int j = paramInt1; (j >= 0) && (localBitMatrix.get(paramInt2, j)) && (arrayOfInt[1] <= paramInt3); j--)
      arrayOfInt[1] = (1 + arrayOfInt[1]);
    float f = (0.0F / 0.0F);
    if ((j >= 0) && (arrayOfInt[1] <= paramInt3))
    {
      while ((j >= 0) && (!localBitMatrix.get(paramInt2, j)) && (arrayOfInt[0] <= paramInt3))
      {
        arrayOfInt[0] = (1 + arrayOfInt[0]);
        j--;
      }
      if (arrayOfInt[0] > paramInt3)
        return f;
      for (int k = paramInt1 + 1; (k < i) && (localBitMatrix.get(paramInt2, k)) && (arrayOfInt[1] <= paramInt3); k++)
        arrayOfInt[1] = (1 + arrayOfInt[1]);
      if ((k != i) && (arrayOfInt[1] <= paramInt3))
      {
        while ((k < i) && (!localBitMatrix.get(paramInt2, k)) && (arrayOfInt[2] <= paramInt3))
        {
          arrayOfInt[2] = (1 + arrayOfInt[2]);
          k++;
        }
        if (arrayOfInt[2] > paramInt3)
          return f;
        if (5 * Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] - paramInt4) >= paramInt4 * 2)
          return f;
        if (foundPatternCross(arrayOfInt))
          f = centerFromEnd(arrayOfInt, k);
        return f;
      }
      return f;
    }
    return f;
  }

  private boolean foundPatternCross(int[] paramArrayOfInt)
  {
    float f1 = this.moduleSize;
    float f2 = f1 / 2.0F;
    for (int i = 0; i < 3; i++)
      if (Math.abs(f1 - paramArrayOfInt[i]) >= f2)
        return false;
    return true;
  }

  private AlignmentPattern handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2];
    float f1 = centerFromEnd(paramArrayOfInt, paramInt2);
    float f2 = crossCheckVertical(paramInt1, (int)f1, 2 * paramArrayOfInt[1], i);
    if (!Float.isNaN(f2))
    {
      float f3 = paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2] / 3.0F;
      Iterator localIterator = this.possibleCenters.iterator();
      while (localIterator.hasNext())
      {
        AlignmentPattern localAlignmentPattern2 = (AlignmentPattern)localIterator.next();
        if (localAlignmentPattern2.aboutEquals(f3, f2, f1))
          return localAlignmentPattern2.combineEstimate(f2, f1, f3);
      }
      AlignmentPattern localAlignmentPattern1 = new AlignmentPattern(f1, f2, f3);
      this.possibleCenters.add(localAlignmentPattern1);
      if (this.resultPointCallback != null)
        this.resultPointCallback.foundPossibleResultPoint(localAlignmentPattern1);
    }
    return null;
  }

  AlignmentPattern find()
  {
    int i = this.startX;
    int j = this.height;
    int k = i + this.width;
    int m = this.startY + (j >> 1);
    int[] arrayOfInt = new int[3];
    for (int n = 0; n < j; n++)
    {
      int i1;
      if ((n & 0x1) == 0)
        i1 = n + 1 >> 1;
      else
        i1 = -(n + 1 >> 1);
      int i2 = i1 + m;
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 0;
      for (int i3 = i; (i3 < k) && (!this.image.get(i3, i2)); i3++);
      int i4 = 0;
      while (i3 < k)
      {
        if (this.image.get(i3, i2))
        {
          if (i4 == 1)
          {
            arrayOfInt[i4] = (1 + arrayOfInt[i4]);
          }
          else if (i4 == 2)
          {
            if (foundPatternCross(arrayOfInt))
            {
              AlignmentPattern localAlignmentPattern2 = handlePossibleCenter(arrayOfInt, i2, i3);
              if (localAlignmentPattern2 != null)
                return localAlignmentPattern2;
            }
            arrayOfInt[0] = arrayOfInt[2];
            arrayOfInt[1] = 1;
            arrayOfInt[2] = 0;
            i4 = 1;
          }
          else
          {
            i4++;
            arrayOfInt[i4] = (1 + arrayOfInt[i4]);
          }
        }
        else
        {
          if (i4 == 1)
            i4++;
          arrayOfInt[i4] = (1 + arrayOfInt[i4]);
        }
        i3++;
      }
      if (foundPatternCross(arrayOfInt))
      {
        AlignmentPattern localAlignmentPattern1 = handlePossibleCenter(arrayOfInt, i2, k);
        if (localAlignmentPattern1 != null)
          return localAlignmentPattern1;
      }
    }
    if (!this.possibleCenters.isEmpty())
      return (AlignmentPattern)this.possibleCenters.get(0);
    throw NotFoundException.getNotFoundInstance();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.AlignmentPatternFinder
 * JD-Core Version:    0.6.1
 */