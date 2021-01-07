package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader
{
  private static final int MAX_AVG_VARIANCE = 51;
  private static final float MAX_FINDER_PATTERN_RATIO = 0.8928571F;
  private static final int MAX_INDIVIDUAL_VARIANCE = 115;
  private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667F;
  private final int[] dataCharacterCounters = new int[8];
  private final int[] decodeFinderCounters = new int[4];
  private final int[] evenCounts = new int[this.dataCharacterCounters.length / 2];
  private final float[] evenRoundingErrors = new float[4];
  private final int[] oddCounts = new int[this.dataCharacterCounters.length / 2];
  private final float[] oddRoundingErrors = new float[4];

  protected static int count(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += paramArrayOfInt[j];
      j++;
    }
    return k;
  }

  protected static void decrement(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    float f = paramArrayOfFloat[0];
    int i = 1;
    int j = 0;
    while (i < paramArrayOfInt.length)
    {
      if (paramArrayOfFloat[i] < f)
      {
        f = paramArrayOfFloat[i];
        j = i;
      }
      i++;
    }
    paramArrayOfInt[j] -= 1;
  }

  protected static void increment(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    float f = paramArrayOfFloat[0];
    int i = 1;
    int j = 0;
    while (i < paramArrayOfInt.length)
    {
      if (paramArrayOfFloat[i] > f)
      {
        f = paramArrayOfFloat[i];
        j = i;
      }
      i++;
    }
    paramArrayOfInt[j] = (1 + paramArrayOfInt[j]);
  }

  protected static boolean isFinderPattern(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt[0] + paramArrayOfInt[1];
    int j = i + paramArrayOfInt[2] + paramArrayOfInt[3];
    float f = i / j;
    if ((f >= 0.7916667F) && (f <= 0.8928571F))
    {
      int k = -2147483648;
      int m = paramArrayOfInt.length;
      int n = 0;
      int i1 = 2147483647;
      while (n < m)
      {
        int i3 = paramArrayOfInt[n];
        if (i3 > k)
          k = i3;
        if (i3 < i1)
          i1 = i3;
        n++;
      }
      int i2 = i1 * 10;
      boolean bool = false;
      if (k < i2)
        bool = true;
      return bool;
    }
    return false;
  }

  protected static int parseFinderValue(int[] paramArrayOfInt, int[][] paramArrayOfInt1)
  {
    for (int i = 0; i < paramArrayOfInt1.length; i++)
      if (patternMatchVariance(paramArrayOfInt, paramArrayOfInt1[i], 115) < 51)
        return i;
    throw NotFoundException.getNotFoundInstance();
  }

  protected final int[] getDataCharacterCounters()
  {
    return this.dataCharacterCounters;
  }

  protected final int[] getDecodeFinderCounters()
  {
    return this.decodeFinderCounters;
  }

  protected final int[] getEvenCounts()
  {
    return this.evenCounts;
  }

  protected final float[] getEvenRoundingErrors()
  {
    return this.evenRoundingErrors;
  }

  protected final int[] getOddCounts()
  {
    return this.oddCounts;
  }

  protected final float[] getOddRoundingErrors()
  {
    return this.oddRoundingErrors;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.AbstractRSSReader
 * JD-Core Version:    0.6.1
 */