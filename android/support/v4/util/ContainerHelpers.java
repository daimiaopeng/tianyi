package android.support.v4.util;

class ContainerHelpers
{
  static final int[] EMPTY_INTS = new int[0];
  static final long[] EMPTY_LONGS = new long[0];
  static final Object[] EMPTY_OBJECTS = new Object[0];

  static int binarySearch(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = paramInt1 - 1;
    int j = 0;
    while (j <= i)
    {
      int k = j + i >>> 1;
      int m = paramArrayOfInt[k];
      if (m < paramInt2)
        j = k + 1;
      else if (m > paramInt2)
        i = k - 1;
      else
        return k;
    }
    return j ^ 0xFFFFFFFF;
  }

  static int binarySearch(long[] paramArrayOfLong, int paramInt, long paramLong)
  {
    int i = paramInt - 1;
    int j = 0;
    while (j <= i)
    {
      int k = j + i >>> 1;
      long l = paramArrayOfLong[k];
      if (l < paramLong)
        j = k + 1;
      else if (l > paramLong)
        i = k - 1;
      else
        return k;
    }
    return j ^ 0xFFFFFFFF;
  }

  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    boolean bool;
    if ((paramObject1 != paramObject2) && ((paramObject1 == null) || (!paramObject1.equals(paramObject2))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public static int idealByteArraySize(int paramInt)
  {
    for (int i = 4; i < 32; i++)
    {
      int j = -12 + (1 << i);
      if (paramInt <= j)
        return j;
    }
    return paramInt;
  }

  public static int idealIntArraySize(int paramInt)
  {
    return idealByteArraySize(paramInt * 4) / 4;
  }

  public static int idealLongArraySize(int paramInt)
  {
    return idealByteArraySize(paramInt * 8) / 8;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.ContainerHelpers
 * JD-Core Version:    0.6.1
 */