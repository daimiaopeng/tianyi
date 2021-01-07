package android.support.v4.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils
{
  public static int[] append(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    if (paramInt1 + 1 > paramArrayOfInt.length)
    {
      int[] arrayOfInt = new int[growSize(paramInt1)];
      System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramInt1);
      paramArrayOfInt = arrayOfInt;
    }
    paramArrayOfInt[paramInt1] = paramInt2;
    return paramArrayOfInt;
  }

  public static long[] append(long[] paramArrayOfLong, int paramInt, long paramLong)
  {
    if (paramInt + 1 > paramArrayOfLong.length)
    {
      long[] arrayOfLong = new long[growSize(paramInt)];
      System.arraycopy(paramArrayOfLong, 0, arrayOfLong, 0, paramInt);
      paramArrayOfLong = arrayOfLong;
    }
    paramArrayOfLong[paramInt] = paramLong;
    return paramArrayOfLong;
  }

  public static <T> T[] append(T[] paramArrayOfT, int paramInt, T paramT)
  {
    if (paramInt + 1 > paramArrayOfT.length)
    {
      Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), growSize(paramInt));
      System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, paramInt);
      paramArrayOfT = arrayOfObject;
    }
    paramArrayOfT[paramInt] = paramT;
    return paramArrayOfT;
  }

  public static boolean[] append(boolean[] paramArrayOfBoolean, int paramInt, boolean paramBoolean)
  {
    if (paramInt + 1 > paramArrayOfBoolean.length)
    {
      boolean[] arrayOfBoolean = new boolean[growSize(paramInt)];
      System.arraycopy(paramArrayOfBoolean, 0, arrayOfBoolean, 0, paramInt);
      paramArrayOfBoolean = arrayOfBoolean;
    }
    paramArrayOfBoolean[paramInt] = paramBoolean;
    return paramArrayOfBoolean;
  }

  public static int growSize(int paramInt)
  {
    int i;
    if (paramInt <= 4)
      i = 8;
    else
      i = paramInt * 2;
    return i;
  }

  public static int[] insert(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 + 1 <= paramArrayOfInt.length)
    {
      System.arraycopy(paramArrayOfInt, paramInt2, paramArrayOfInt, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfInt[paramInt2] = paramInt3;
      return paramArrayOfInt;
    }
    int[] arrayOfInt = new int[growSize(paramInt1)];
    System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramInt2);
    arrayOfInt[paramInt2] = paramInt3;
    System.arraycopy(paramArrayOfInt, paramInt2, arrayOfInt, paramInt2 + 1, paramArrayOfInt.length - paramInt2);
    return arrayOfInt;
  }

  public static long[] insert(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramInt1 + 1 <= paramArrayOfLong.length)
    {
      System.arraycopy(paramArrayOfLong, paramInt2, paramArrayOfLong, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfLong[paramInt2] = paramLong;
      return paramArrayOfLong;
    }
    long[] arrayOfLong = new long[growSize(paramInt1)];
    System.arraycopy(paramArrayOfLong, 0, arrayOfLong, 0, paramInt2);
    arrayOfLong[paramInt2] = paramLong;
    System.arraycopy(paramArrayOfLong, paramInt2, arrayOfLong, paramInt2 + 1, paramArrayOfLong.length - paramInt2);
    return arrayOfLong;
  }

  public static <T> T[] insert(T[] paramArrayOfT, int paramInt1, int paramInt2, T paramT)
  {
    if (paramInt1 + 1 <= paramArrayOfT.length)
    {
      System.arraycopy(paramArrayOfT, paramInt2, paramArrayOfT, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfT[paramInt2] = paramT;
      return paramArrayOfT;
    }
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), growSize(paramInt1));
    System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, paramInt2);
    arrayOfObject[paramInt2] = paramT;
    System.arraycopy(paramArrayOfT, paramInt2, arrayOfObject, paramInt2 + 1, paramArrayOfT.length - paramInt2);
    return arrayOfObject;
  }

  public static boolean[] insert(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt1 + 1 <= paramArrayOfBoolean.length)
    {
      System.arraycopy(paramArrayOfBoolean, paramInt2, paramArrayOfBoolean, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfBoolean[paramInt2] = paramBoolean;
      return paramArrayOfBoolean;
    }
    boolean[] arrayOfBoolean = new boolean[growSize(paramInt1)];
    System.arraycopy(paramArrayOfBoolean, 0, arrayOfBoolean, 0, paramInt2);
    arrayOfBoolean[paramInt2] = paramBoolean;
    System.arraycopy(paramArrayOfBoolean, paramInt2, arrayOfBoolean, paramInt2 + 1, paramArrayOfBoolean.length - paramInt2);
    return arrayOfBoolean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.GrowingArrayUtils
 * JD-Core Version:    0.6.1
 */