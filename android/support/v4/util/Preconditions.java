package android.support.v4.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class Preconditions
{
  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }

  public static void checkArgument(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.valueOf(paramObject));
  }

  public static float checkArgumentFinite(float paramFloat, String paramString)
  {
    if (Float.isNaN(paramFloat))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramString);
      localStringBuilder1.append(" must not be NaN");
      throw new IllegalArgumentException(localStringBuilder1.toString());
    }
    if (Float.isInfinite(paramFloat))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString);
      localStringBuilder2.append(" must not be infinite");
      throw new IllegalArgumentException(localStringBuilder2.toString());
    }
    return paramFloat;
  }

  public static float checkArgumentInRange(float paramFloat1, float paramFloat2, float paramFloat3, String paramString)
  {
    if (Float.isNaN(paramFloat1))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(" must not be NaN");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (paramFloat1 < paramFloat2)
    {
      Locale localLocale2 = Locale.US;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Float.valueOf(paramFloat2);
      arrayOfObject2[2] = Float.valueOf(paramFloat3);
      throw new IllegalArgumentException(String.format(localLocale2, "%s is out of range of [%f, %f] (too low)", arrayOfObject2));
    }
    if (paramFloat1 > paramFloat3)
    {
      Locale localLocale1 = Locale.US;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Float.valueOf(paramFloat2);
      arrayOfObject1[2] = Float.valueOf(paramFloat3);
      throw new IllegalArgumentException(String.format(localLocale1, "%s is out of range of [%f, %f] (too high)", arrayOfObject1));
    }
    return paramFloat1;
  }

  public static int checkArgumentInRange(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    if (paramInt1 < paramInt2)
    {
      Locale localLocale2 = Locale.US;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Integer.valueOf(paramInt2);
      arrayOfObject2[2] = Integer.valueOf(paramInt3);
      throw new IllegalArgumentException(String.format(localLocale2, "%s is out of range of [%d, %d] (too low)", arrayOfObject2));
    }
    if (paramInt1 > paramInt3)
    {
      Locale localLocale1 = Locale.US;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Integer.valueOf(paramInt2);
      arrayOfObject1[2] = Integer.valueOf(paramInt3);
      throw new IllegalArgumentException(String.format(localLocale1, "%s is out of range of [%d, %d] (too high)", arrayOfObject1));
    }
    return paramInt1;
  }

  public static long checkArgumentInRange(long paramLong1, long paramLong2, long paramLong3, String paramString)
  {
    if (paramLong1 < paramLong2)
    {
      Locale localLocale2 = Locale.US;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Long.valueOf(paramLong2);
      arrayOfObject2[2] = Long.valueOf(paramLong3);
      throw new IllegalArgumentException(String.format(localLocale2, "%s is out of range of [%d, %d] (too low)", arrayOfObject2));
    }
    if (paramLong1 > paramLong3)
    {
      Locale localLocale1 = Locale.US;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Long.valueOf(paramLong2);
      arrayOfObject1[2] = Long.valueOf(paramLong3);
      throw new IllegalArgumentException(String.format(localLocale1, "%s is out of range of [%d, %d] (too high)", arrayOfObject1));
    }
    return paramLong1;
  }

  @IntRange(from=0L)
  public static int checkArgumentNonnegative(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException();
    return paramInt;
  }

  @IntRange(from=0L)
  public static int checkArgumentNonnegative(int paramInt, String paramString)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException(paramString);
    return paramInt;
  }

  public static long checkArgumentNonnegative(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException();
    return paramLong;
  }

  public static long checkArgumentNonnegative(long paramLong, String paramString)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException(paramString);
    return paramLong;
  }

  public static int checkArgumentPositive(int paramInt, String paramString)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException(paramString);
    return paramInt;
  }

  public static float[] checkArrayElementsInRange(float[] paramArrayOfFloat, float paramFloat1, float paramFloat2, String paramString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(paramString);
    localStringBuilder1.append(" must not be null");
    checkNotNull(paramArrayOfFloat, localStringBuilder1.toString());
    for (int i = 0; i < paramArrayOfFloat.length; i++)
    {
      float f = paramArrayOfFloat[i];
      if (Float.isNaN(f))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(paramString);
        localStringBuilder2.append("[");
        localStringBuilder2.append(i);
        localStringBuilder2.append("] must not be NaN");
        throw new IllegalArgumentException(localStringBuilder2.toString());
      }
      if (f < paramFloat1)
      {
        Locale localLocale2 = Locale.US;
        Object[] arrayOfObject2 = new Object[4];
        arrayOfObject2[0] = paramString;
        arrayOfObject2[1] = Integer.valueOf(i);
        arrayOfObject2[2] = Float.valueOf(paramFloat1);
        arrayOfObject2[3] = Float.valueOf(paramFloat2);
        throw new IllegalArgumentException(String.format(localLocale2, "%s[%d] is out of range of [%f, %f] (too low)", arrayOfObject2));
      }
      if (f > paramFloat2)
      {
        Locale localLocale1 = Locale.US;
        Object[] arrayOfObject1 = new Object[4];
        arrayOfObject1[0] = paramString;
        arrayOfObject1[1] = Integer.valueOf(i);
        arrayOfObject1[2] = Float.valueOf(paramFloat1);
        arrayOfObject1[3] = Float.valueOf(paramFloat2);
        throw new IllegalArgumentException(String.format(localLocale1, "%s[%d] is out of range of [%f, %f] (too high)", arrayOfObject1));
      }
    }
    return paramArrayOfFloat;
  }

  public static <T> T[] checkArrayElementsNotNull(T[] paramArrayOfT, String paramString)
  {
    if (paramArrayOfT == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(" must not be null");
      throw new NullPointerException(localStringBuilder.toString());
    }
    for (int i = 0; i < paramArrayOfT.length; i++)
      if (paramArrayOfT[i] == null)
      {
        Locale localLocale = Locale.US;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = Integer.valueOf(i);
        throw new NullPointerException(String.format(localLocale, "%s[%d] must not be null", arrayOfObject));
      }
    return paramArrayOfT;
  }

  @NonNull
  public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C paramC, String paramString)
  {
    if (paramC == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(" must not be null");
      throw new NullPointerException(localStringBuilder.toString());
    }
    long l = 0L;
    Iterator localIterator = paramC.iterator();
    while (localIterator.hasNext())
    {
      if (localIterator.next() == null)
      {
        Locale localLocale = Locale.US;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = Long.valueOf(l);
        throw new NullPointerException(String.format(localLocale, "%s[%d] must not be null", arrayOfObject));
      }
      l += 1L;
    }
    return paramC;
  }

  public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> paramCollection, String paramString)
  {
    if (paramCollection == null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramString);
      localStringBuilder1.append(" must not be null");
      throw new NullPointerException(localStringBuilder1.toString());
    }
    if (paramCollection.isEmpty())
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString);
      localStringBuilder2.append(" is empty");
      throw new IllegalArgumentException(localStringBuilder2.toString());
    }
    return paramCollection;
  }

  public static int checkFlagsArgument(int paramInt1, int paramInt2)
  {
    if ((paramInt1 & paramInt2) != paramInt1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested flags 0x");
      localStringBuilder.append(Integer.toHexString(paramInt1));
      localStringBuilder.append(", but only 0x");
      localStringBuilder.append(Integer.toHexString(paramInt2));
      localStringBuilder.append(" are allowed");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return paramInt1;
  }

  @NonNull
  public static <T> T checkNotNull(T paramT)
  {
    if (paramT == null)
      throw new NullPointerException();
    return paramT;
  }

  @NonNull
  public static <T> T checkNotNull(T paramT, Object paramObject)
  {
    if (paramT == null)
      throw new NullPointerException(String.valueOf(paramObject));
    return paramT;
  }

  public static void checkState(boolean paramBoolean)
  {
    checkState(paramBoolean, null);
  }

  public static void checkState(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
      throw new IllegalStateException(paramString);
  }

  @NonNull
  public static <T extends CharSequence> T checkStringNotEmpty(T paramT)
  {
    if (TextUtils.isEmpty(paramT))
      throw new IllegalArgumentException();
    return paramT;
  }

  @NonNull
  public static <T extends CharSequence> T checkStringNotEmpty(T paramT, Object paramObject)
  {
    if (TextUtils.isEmpty(paramT))
      throw new IllegalArgumentException(String.valueOf(paramObject));
    return paramT;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.Preconditions
 * JD-Core Version:    0.6.1
 */