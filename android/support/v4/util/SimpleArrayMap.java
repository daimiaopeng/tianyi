package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ConcurrentModificationException;

public class SimpleArrayMap<K, V>
{
  private static final int BASE_SIZE = 4;
  private static final int CACHE_SIZE = 10;
  private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
  private static final boolean DEBUG = false;
  private static final String TAG = "ArrayMap";

  @Nullable
  static Object[] mBaseCache;
  static int mBaseCacheSize;

  @Nullable
  static Object[] mTwiceBaseCache;
  static int mTwiceBaseCacheSize;
  Object[] mArray;
  int[] mHashes;
  int mSize;

  public SimpleArrayMap()
  {
    this.mHashes = ContainerHelpers.EMPTY_INTS;
    this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    this.mSize = 0;
  }

  public SimpleArrayMap(int paramInt)
  {
    if (paramInt == 0)
    {
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    }
    else
    {
      allocArrays(paramInt);
    }
    this.mSize = 0;
  }

  public SimpleArrayMap(SimpleArrayMap<K, V> paramSimpleArrayMap)
  {
    this();
    if (paramSimpleArrayMap != null)
      putAll(paramSimpleArrayMap);
  }

  private void allocArrays(int paramInt)
  {
    if (paramInt == 8)
      try
      {
        if (mTwiceBaseCache != null)
        {
          Object[] arrayOfObject2 = mTwiceBaseCache;
          this.mArray = arrayOfObject2;
          mTwiceBaseCache = (Object[])arrayOfObject2[0];
          this.mHashes = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          mTwiceBaseCacheSize -= 1;
          return;
        }
        break label157;
      }
      finally
      {
        localObject2 = finally;
        throw localObject2;
      }
    else if (paramInt == 4)
      try
      {
        if (mBaseCache != null)
        {
          Object[] arrayOfObject1 = mBaseCache;
          this.mArray = arrayOfObject1;
          mBaseCache = (Object[])arrayOfObject1[0];
          this.mHashes = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          mBaseCacheSize -= 1;
          return;
        }
      }
      finally
      {
        localObject1 = finally;
        throw localObject1;
      }
    label157: this.mHashes = new int[paramInt];
    this.mArray = new Object[paramInt << 1];
  }

  // ERROR //
  private static int binarySearchHashes(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: iload_2
    //   3: invokestatic 75	android/support/v4/util/ContainerHelpers:binarySearch	([III)I
    //   6: istore_3
    //   7: iload_3
    //   8: ireturn
    //   9: new 77	java/util/ConcurrentModificationException
    //   12: dup
    //   13: invokespecial 78	java/util/ConcurrentModificationException:<init>	()V
    //   16: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	9	java/lang/ArrayIndexOutOfBoundsException
  }

  private static void freeArrays(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8)
      try
      {
        if (mTwiceBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mTwiceBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          for (int j = (paramInt << 1) - 1; j >= 2; j--)
            paramArrayOfObject[j] = null;
          mTwiceBaseCache = paramArrayOfObject;
          mTwiceBaseCacheSize = 1 + mTwiceBaseCacheSize;
        }
        return;
      }
      finally
      {
        localObject2 = finally;
        throw localObject2;
      }
    else if (paramArrayOfInt.length == 4)
      try
      {
        if (mBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          for (int i = (paramInt << 1) - 1; i >= 2; i--)
            paramArrayOfObject[i] = null;
          mBaseCache = paramArrayOfObject;
          mBaseCacheSize = 1 + mBaseCacheSize;
        }
      }
      finally
      {
        localObject1 = finally;
        throw localObject1;
      }
  }

  public void clear()
  {
    if (this.mSize > 0)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      int i = this.mSize;
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
      freeArrays(arrayOfInt, arrayOfObject, i);
    }
    if (this.mSize > 0)
      throw new ConcurrentModificationException();
  }

  public boolean containsKey(@Nullable Object paramObject)
  {
    boolean bool;
    if (indexOfKey(paramObject) >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean containsValue(Object paramObject)
  {
    boolean bool;
    if (indexOfValue(paramObject) >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void ensureCapacity(int paramInt)
  {
    int i = this.mSize;
    if (this.mHashes.length < paramInt)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      if (this.mSize > 0)
      {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, i);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, i << 1);
      }
      freeArrays(arrayOfInt, arrayOfObject, i);
    }
    if (this.mSize != i)
      throw new ConcurrentModificationException();
  }

  // ERROR //
  public boolean equals(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: if_acmpne +5 -> 7
    //   5: iconst_1
    //   6: ireturn
    //   7: aload_1
    //   8: instanceof 2
    //   11: ifeq +111 -> 122
    //   14: aload_1
    //   15: checkcast 2	android/support/v4/util/SimpleArrayMap
    //   18: astore 8
    //   20: aload_0
    //   21: invokevirtual 109	android/support/v4/util/SimpleArrayMap:size	()I
    //   24: aload 8
    //   26: invokevirtual 109	android/support/v4/util/SimpleArrayMap:size	()I
    //   29: if_icmpeq +5 -> 34
    //   32: iconst_0
    //   33: ireturn
    //   34: iconst_0
    //   35: istore 9
    //   37: iload 9
    //   39: aload_0
    //   40: getfield 47	android/support/v4/util/SimpleArrayMap:mSize	I
    //   43: if_icmpge +73 -> 116
    //   46: aload_0
    //   47: iload 9
    //   49: invokevirtual 113	android/support/v4/util/SimpleArrayMap:keyAt	(I)Ljava/lang/Object;
    //   52: astore 10
    //   54: aload_0
    //   55: iload 9
    //   57: invokevirtual 116	android/support/v4/util/SimpleArrayMap:valueAt	(I)Ljava/lang/Object;
    //   60: astore 11
    //   62: aload 8
    //   64: aload 10
    //   66: invokevirtual 120	android/support/v4/util/SimpleArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   69: astore 12
    //   71: aload 11
    //   73: ifnonnull +21 -> 94
    //   76: aload 12
    //   78: ifnonnull +159 -> 237
    //   81: aload 8
    //   83: aload 10
    //   85: invokevirtual 122	android/support/v4/util/SimpleArrayMap:containsKey	(Ljava/lang/Object;)Z
    //   88: ifne +22 -> 110
    //   91: goto +146 -> 237
    //   94: aload 11
    //   96: aload 12
    //   98: invokevirtual 124	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   101: istore 13
    //   103: iload 13
    //   105: ifne +5 -> 110
    //   108: iconst_0
    //   109: ireturn
    //   110: iinc 9 1
    //   113: goto -76 -> 37
    //   116: iconst_1
    //   117: ireturn
    //   118: iconst_0
    //   119: ireturn
    //   120: iconst_0
    //   121: ireturn
    //   122: aload_1
    //   123: instanceof 126
    //   126: ifeq +109 -> 235
    //   129: aload_1
    //   130: checkcast 126	java/util/Map
    //   133: astore_2
    //   134: aload_0
    //   135: invokevirtual 109	android/support/v4/util/SimpleArrayMap:size	()I
    //   138: aload_2
    //   139: invokeinterface 127 1 0
    //   144: if_icmpeq +5 -> 149
    //   147: iconst_0
    //   148: ireturn
    //   149: iconst_0
    //   150: istore_3
    //   151: iload_3
    //   152: aload_0
    //   153: getfield 47	android/support/v4/util/SimpleArrayMap:mSize	I
    //   156: if_icmpge +73 -> 229
    //   159: aload_0
    //   160: iload_3
    //   161: invokevirtual 113	android/support/v4/util/SimpleArrayMap:keyAt	(I)Ljava/lang/Object;
    //   164: astore 4
    //   166: aload_0
    //   167: iload_3
    //   168: invokevirtual 116	android/support/v4/util/SimpleArrayMap:valueAt	(I)Ljava/lang/Object;
    //   171: astore 5
    //   173: aload_2
    //   174: aload 4
    //   176: invokeinterface 128 2 0
    //   181: astore 6
    //   183: aload 5
    //   185: ifnonnull +22 -> 207
    //   188: aload 6
    //   190: ifnonnull +49 -> 239
    //   193: aload_2
    //   194: aload 4
    //   196: invokeinterface 129 2 0
    //   201: ifne +22 -> 223
    //   204: goto +35 -> 239
    //   207: aload 5
    //   209: aload 6
    //   211: invokevirtual 124	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   214: istore 7
    //   216: iload 7
    //   218: ifne +5 -> 223
    //   221: iconst_0
    //   222: ireturn
    //   223: iinc 3 1
    //   226: goto -75 -> 151
    //   229: iconst_1
    //   230: ireturn
    //   231: iconst_0
    //   232: ireturn
    //   233: iconst_0
    //   234: ireturn
    //   235: iconst_0
    //   236: ireturn
    //   237: iconst_0
    //   238: ireturn
    //   239: iconst_0
    //   240: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   37	103	118	java/lang/ClassCastException
    //   37	103	120	java/lang/NullPointerException
    //   151	216	231	java/lang/ClassCastException
    //   151	216	233	java/lang/NullPointerException
  }

  @Nullable
  public V get(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    Object localObject;
    if (i >= 0)
      localObject = this.mArray[(1 + (i << 1))];
    else
      localObject = null;
    return localObject;
  }

  public int hashCode()
  {
    int[] arrayOfInt = this.mHashes;
    Object[] arrayOfObject = this.mArray;
    int i = this.mSize;
    int j = 0;
    int k = 1;
    int m = 0;
    while (j < i)
    {
      Object localObject = arrayOfObject[k];
      int n = arrayOfInt[j];
      int i1;
      if (localObject == null)
        i1 = 0;
      else
        i1 = localObject.hashCode();
      m += (i1 ^ n);
      j++;
      k += 2;
    }
    return m;
  }

  int indexOf(Object paramObject, int paramInt)
  {
    int i = this.mSize;
    if (i == 0)
      return -1;
    int j = binarySearchHashes(this.mHashes, i, paramInt);
    if (j < 0)
      return j;
    if (paramObject.equals(this.mArray[(j << 1)]))
      return j;
    for (int k = j + 1; (k < i) && (this.mHashes[k] == paramInt); k++)
      if (paramObject.equals(this.mArray[(k << 1)]))
        return k;
    for (int m = j - 1; (m >= 0) && (this.mHashes[m] == paramInt); m--)
      if (paramObject.equals(this.mArray[(m << 1)]))
        return m;
    return k ^ 0xFFFFFFFF;
  }

  public int indexOfKey(@Nullable Object paramObject)
  {
    int i;
    if (paramObject == null)
      i = indexOfNull();
    else
      i = indexOf(paramObject, paramObject.hashCode());
    return i;
  }

  int indexOfNull()
  {
    int i = this.mSize;
    if (i == 0)
      return -1;
    int j = binarySearchHashes(this.mHashes, i, 0);
    if (j < 0)
      return j;
    if (this.mArray[(j << 1)] == null)
      return j;
    for (int k = j + 1; (k < i) && (this.mHashes[k] == 0); k++)
      if (this.mArray[(k << 1)] == null)
        return k;
    for (int m = j - 1; (m >= 0) && (this.mHashes[m] == 0); m--)
      if (this.mArray[(m << 1)] == null)
        return m;
    return k ^ 0xFFFFFFFF;
  }

  int indexOfValue(Object paramObject)
  {
    int i = 2 * this.mSize;
    Object[] arrayOfObject = this.mArray;
    if (paramObject == null)
      for (int k = 1; k < i; k += 2)
        if (arrayOfObject[k] == null)
          return k >> 1;
    for (int j = 1; j < i; j += 2)
      if (paramObject.equals(arrayOfObject[j]))
        return j >> 1;
    return -1;
  }

  public boolean isEmpty()
  {
    boolean bool;
    if (this.mSize <= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public K keyAt(int paramInt)
  {
    return this.mArray[(paramInt << 1)];
  }

  @Nullable
  public V put(K paramK, V paramV)
  {
    int i = this.mSize;
    int n;
    int m;
    if (paramK == null)
    {
      n = indexOfNull();
      m = 0;
    }
    else
    {
      int j = paramK.hashCode();
      int k = indexOf(paramK, j);
      m = j;
      n = k;
    }
    if (n >= 0)
    {
      int i5 = 1 + (n << 1);
      Object localObject = this.mArray[i5];
      this.mArray[i5] = paramV;
      return localObject;
    }
    int i1 = n ^ 0xFFFFFFFF;
    if (i >= this.mHashes.length)
    {
      int i4 = 4;
      if (i >= 8)
        i4 = i + (i >> 1);
      else if (i >= i4)
        i4 = 8;
      int[] arrayOfInt3 = this.mHashes;
      Object[] arrayOfObject2 = this.mArray;
      allocArrays(i4);
      if (i != this.mSize)
        throw new ConcurrentModificationException();
      if (this.mHashes.length > 0)
      {
        System.arraycopy(arrayOfInt3, 0, this.mHashes, 0, arrayOfInt3.length);
        System.arraycopy(arrayOfObject2, 0, this.mArray, 0, arrayOfObject2.length);
      }
      freeArrays(arrayOfInt3, arrayOfObject2, i);
    }
    if (i1 < i)
    {
      int[] arrayOfInt1 = this.mHashes;
      int[] arrayOfInt2 = this.mHashes;
      int i3 = i1 + 1;
      System.arraycopy(arrayOfInt1, i1, arrayOfInt2, i3, i - i1);
      System.arraycopy(this.mArray, i1 << 1, this.mArray, i3 << 1, this.mSize - i1 << 1);
    }
    if ((i == this.mSize) && (i1 < this.mHashes.length))
    {
      this.mHashes[i1] = m;
      Object[] arrayOfObject1 = this.mArray;
      int i2 = i1 << 1;
      arrayOfObject1[i2] = paramK;
      this.mArray[(i2 + 1)] = paramV;
      this.mSize = (1 + this.mSize);
      return null;
    }
    throw new ConcurrentModificationException();
  }

  public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> paramSimpleArrayMap)
  {
    int i = paramSimpleArrayMap.mSize;
    ensureCapacity(i + this.mSize);
    int j = this.mSize;
    int k = 0;
    if (j == 0)
    {
      if (i > 0)
      {
        System.arraycopy(paramSimpleArrayMap.mHashes, 0, this.mHashes, 0, i);
        System.arraycopy(paramSimpleArrayMap.mArray, 0, this.mArray, 0, i << 1);
        this.mSize = i;
      }
    }
    else
      while (k < i)
      {
        put(paramSimpleArrayMap.keyAt(k), paramSimpleArrayMap.valueAt(k));
        k++;
      }
  }

  @Nullable
  public V remove(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0)
      return removeAt(i);
    return null;
  }

  public V removeAt(int paramInt)
  {
    Object[] arrayOfObject1 = this.mArray;
    int i = paramInt << 1;
    Object localObject = arrayOfObject1[(i + 1)];
    int j = this.mSize;
    int i2;
    if (j <= 1)
    {
      freeArrays(this.mHashes, this.mArray, j);
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      i2 = 0;
    }
    else
    {
      int k = j - 1;
      int m = this.mHashes.length;
      int n = 8;
      if ((m > n) && (this.mSize < this.mHashes.length / 3))
      {
        if (j > n)
          n = j + (j >> 1);
        int[] arrayOfInt3 = this.mHashes;
        Object[] arrayOfObject3 = this.mArray;
        allocArrays(n);
        if (j != this.mSize)
          throw new ConcurrentModificationException();
        if (paramInt > 0)
        {
          System.arraycopy(arrayOfInt3, 0, this.mHashes, 0, paramInt);
          System.arraycopy(arrayOfObject3, 0, this.mArray, 0, i);
        }
        if (paramInt < k)
        {
          int i5 = paramInt + 1;
          int[] arrayOfInt4 = this.mHashes;
          int i6 = k - paramInt;
          System.arraycopy(arrayOfInt3, i5, arrayOfInt4, paramInt, i6);
          System.arraycopy(arrayOfObject3, i5 << 1, this.mArray, i, i6 << 1);
        }
      }
      else
      {
        if (paramInt < k)
        {
          int[] arrayOfInt1 = this.mHashes;
          int i3 = paramInt + 1;
          int[] arrayOfInt2 = this.mHashes;
          int i4 = k - paramInt;
          System.arraycopy(arrayOfInt1, i3, arrayOfInt2, paramInt, i4);
          System.arraycopy(this.mArray, i3 << 1, this.mArray, i, i4 << 1);
        }
        Object[] arrayOfObject2 = this.mArray;
        int i1 = k << 1;
        arrayOfObject2[i1] = null;
        this.mArray[(i1 + 1)] = null;
      }
      i2 = k;
    }
    if (j != this.mSize)
      throw new ConcurrentModificationException();
    this.mSize = i2;
    return localObject;
  }

  public V setValueAt(int paramInt, V paramV)
  {
    int i = 1 + (paramInt << 1);
    Object localObject = this.mArray[i];
    this.mArray[i] = paramV;
    return localObject;
  }

  public int size()
  {
    return this.mSize;
  }

  public String toString()
  {
    if (isEmpty())
      return "{}";
    StringBuilder localStringBuilder = new StringBuilder(28 * this.mSize);
    localStringBuilder.append('{');
    for (int i = 0; i < this.mSize; i++)
    {
      if (i > 0)
        localStringBuilder.append(", ");
      Object localObject1 = keyAt(i);
      if (localObject1 != this)
        localStringBuilder.append(localObject1);
      else
        localStringBuilder.append("(this Map)");
      localStringBuilder.append('=');
      Object localObject2 = valueAt(i);
      if (localObject2 != this)
        localStringBuilder.append(localObject2);
      else
        localStringBuilder.append("(this Map)");
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  public V valueAt(int paramInt)
  {
    return this.mArray[(1 + (paramInt << 1))];
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.SimpleArrayMap
 * JD-Core Version:    0.6.1
 */