package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet<E>
  implements Collection<E>, Set<E>
{
  private static final int BASE_SIZE = 4;
  private static final int CACHE_SIZE = 10;
  private static final boolean DEBUG = false;
  private static final int[] INT = new int[0];
  private static final Object[] OBJECT = new Object[0];
  private static final String TAG = "ArraySet";

  @Nullable
  private static Object[] sBaseCache;
  private static int sBaseCacheSize;

  @Nullable
  private static Object[] sTwiceBaseCache;
  private static int sTwiceBaseCacheSize;
  Object[] mArray;
  private MapCollections<E, E> mCollections;
  private int[] mHashes;
  int mSize;

  public ArraySet()
  {
    this(0);
  }

  public ArraySet(int paramInt)
  {
    if (paramInt == 0)
    {
      this.mHashes = INT;
      this.mArray = OBJECT;
    }
    else
    {
      allocArrays(paramInt);
    }
    this.mSize = 0;
  }

  public ArraySet(@Nullable ArraySet<E> paramArraySet)
  {
    this();
    if (paramArraySet != null)
      addAll(paramArraySet);
  }

  public ArraySet(@Nullable Collection<E> paramCollection)
  {
    this();
    if (paramCollection != null)
      addAll(paramCollection);
  }

  private void allocArrays(int paramInt)
  {
    if (paramInt == 8)
      try
      {
        if (sTwiceBaseCache != null)
        {
          Object[] arrayOfObject2 = sTwiceBaseCache;
          this.mArray = arrayOfObject2;
          sTwiceBaseCache = (Object[])arrayOfObject2[0];
          this.mHashes = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          sTwiceBaseCacheSize -= 1;
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
        if (sBaseCache != null)
        {
          Object[] arrayOfObject1 = sBaseCache;
          this.mArray = arrayOfObject1;
          sBaseCache = (Object[])arrayOfObject1[0];
          this.mHashes = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          sBaseCacheSize -= 1;
          return;
        }
      }
      finally
      {
        localObject1 = finally;
        throw localObject1;
      }
    label157: this.mHashes = new int[paramInt];
    this.mArray = new Object[paramInt];
  }

  private static void freeArrays(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8)
      try
      {
        if (sTwiceBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = sTwiceBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          for (int j = paramInt - 1; j >= 2; j--)
            paramArrayOfObject[j] = null;
          sTwiceBaseCache = paramArrayOfObject;
          sTwiceBaseCacheSize = 1 + sTwiceBaseCacheSize;
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
        if (sBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = sBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          for (int i = paramInt - 1; i >= 2; i--)
            paramArrayOfObject[i] = null;
          sBaseCache = paramArrayOfObject;
          sBaseCacheSize = 1 + sBaseCacheSize;
        }
      }
      finally
      {
        localObject1 = finally;
        throw localObject1;
      }
  }

  private MapCollections<E, E> getCollection()
  {
    if (this.mCollections == null)
      this.mCollections = new MapCollections()
      {
        protected void colClear()
        {
          ArraySet.this.clear();
        }

        protected Object colGetEntry(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          return ArraySet.this.mArray[paramAnonymousInt1];
        }

        protected Map<E, E> colGetMap()
        {
          throw new UnsupportedOperationException("not a map");
        }

        protected int colGetSize()
        {
          return ArraySet.this.mSize;
        }

        protected int colIndexOfKey(Object paramAnonymousObject)
        {
          return ArraySet.this.indexOf(paramAnonymousObject);
        }

        protected int colIndexOfValue(Object paramAnonymousObject)
        {
          return ArraySet.this.indexOf(paramAnonymousObject);
        }

        protected void colPut(E paramAnonymousE1, E paramAnonymousE2)
        {
          ArraySet.this.add(paramAnonymousE1);
        }

        protected void colRemoveAt(int paramAnonymousInt)
        {
          ArraySet.this.removeAt(paramAnonymousInt);
        }

        protected E colSetValue(int paramAnonymousInt, E paramAnonymousE)
        {
          throw new UnsupportedOperationException("not a map");
        }
      };
    return this.mCollections;
  }

  private int indexOf(Object paramObject, int paramInt)
  {
    int i = this.mSize;
    if (i == 0)
      return -1;
    int j = ContainerHelpers.binarySearch(this.mHashes, i, paramInt);
    if (j < 0)
      return j;
    if (paramObject.equals(this.mArray[j]))
      return j;
    for (int k = j + 1; (k < i) && (this.mHashes[k] == paramInt); k++)
      if (paramObject.equals(this.mArray[k]))
        return k;
    for (int m = j - 1; (m >= 0) && (this.mHashes[m] == paramInt); m--)
      if (paramObject.equals(this.mArray[m]))
        return m;
    return k ^ 0xFFFFFFFF;
  }

  private int indexOfNull()
  {
    int i = this.mSize;
    if (i == 0)
      return -1;
    int j = ContainerHelpers.binarySearch(this.mHashes, i, 0);
    if (j < 0)
      return j;
    if (this.mArray[j] == null)
      return j;
    for (int k = j + 1; (k < i) && (this.mHashes[k] == 0); k++)
      if (this.mArray[k] == null)
        return k;
    for (int m = j - 1; (m >= 0) && (this.mHashes[m] == 0); m--)
      if (this.mArray[m] == null)
        return m;
    return k ^ 0xFFFFFFFF;
  }

  public boolean add(@Nullable E paramE)
  {
    int m;
    int k;
    if (paramE == null)
    {
      m = indexOfNull();
      k = 0;
    }
    else
    {
      int i = paramE.hashCode();
      int j = indexOf(paramE, i);
      k = i;
      m = j;
    }
    if (m >= 0)
      return false;
    int n = m ^ 0xFFFFFFFF;
    if (this.mSize >= this.mHashes.length)
    {
      int i2 = this.mSize;
      int i3 = 4;
      if (i2 >= 8)
        i3 = this.mSize + (this.mSize >> 1);
      else if (this.mSize >= i3)
        i3 = 8;
      int[] arrayOfInt3 = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(i3);
      if (this.mHashes.length > 0)
      {
        System.arraycopy(arrayOfInt3, 0, this.mHashes, 0, arrayOfInt3.length);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, arrayOfObject.length);
      }
      freeArrays(arrayOfInt3, arrayOfObject, this.mSize);
    }
    if (n < this.mSize)
    {
      int[] arrayOfInt1 = this.mHashes;
      int[] arrayOfInt2 = this.mHashes;
      int i1 = n + 1;
      System.arraycopy(arrayOfInt1, n, arrayOfInt2, i1, this.mSize - n);
      System.arraycopy(this.mArray, n, this.mArray, i1, this.mSize - n);
    }
    this.mHashes[n] = k;
    this.mArray[n] = paramE;
    this.mSize = (1 + this.mSize);
    return true;
  }

  public void addAll(@NonNull ArraySet<? extends E> paramArraySet)
  {
    int i = paramArraySet.mSize;
    ensureCapacity(i + this.mSize);
    int j = this.mSize;
    int k = 0;
    if (j == 0)
    {
      if (i > 0)
      {
        System.arraycopy(paramArraySet.mHashes, 0, this.mHashes, 0, i);
        System.arraycopy(paramArraySet.mArray, 0, this.mArray, 0, i);
        this.mSize = i;
      }
    }
    else
      while (k < i)
      {
        add(paramArraySet.valueAt(k));
        k++;
      }
  }

  public boolean addAll(@NonNull Collection<? extends E> paramCollection)
  {
    ensureCapacity(this.mSize + paramCollection.size());
    Iterator localIterator = paramCollection.iterator();
    boolean bool = false;
    while (localIterator.hasNext())
      bool |= add(localIterator.next());
    return bool;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void append(E paramE)
  {
    int i = this.mSize;
    int j;
    if (paramE == null)
      j = 0;
    else
      j = paramE.hashCode();
    if (i >= this.mHashes.length)
      throw new IllegalStateException("Array is full");
    if ((i > 0) && (this.mHashes[(i - 1)] > j))
    {
      add(paramE);
      return;
    }
    this.mSize = (i + 1);
    this.mHashes[i] = j;
    this.mArray[i] = paramE;
  }

  public void clear()
  {
    if (this.mSize != 0)
    {
      freeArrays(this.mHashes, this.mArray, this.mSize);
      this.mHashes = INT;
      this.mArray = OBJECT;
      this.mSize = 0;
    }
  }

  public boolean contains(@Nullable Object paramObject)
  {
    boolean bool;
    if (indexOf(paramObject) >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean containsAll(@NonNull Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
      if (!contains(localIterator.next()))
        return false;
    return true;
  }

  public void ensureCapacity(int paramInt)
  {
    if (this.mHashes.length < paramInt)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      if (this.mSize > 0)
      {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, this.mSize);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, this.mSize);
      }
      freeArrays(arrayOfInt, arrayOfObject, this.mSize);
    }
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
    //   8: instanceof 9
    //   11: ifeq +65 -> 76
    //   14: aload_1
    //   15: checkcast 9	java/util/Set
    //   18: astore_2
    //   19: aload_0
    //   20: invokevirtual 169	android/support/v4/util/ArraySet:size	()I
    //   23: aload_2
    //   24: invokeinterface 170 1 0
    //   29: if_icmpeq +5 -> 34
    //   32: iconst_0
    //   33: ireturn
    //   34: iconst_0
    //   35: istore_3
    //   36: iload_3
    //   37: aload_0
    //   38: getfield 57	android/support/v4/util/ArraySet:mSize	I
    //   41: if_icmpge +29 -> 70
    //   44: aload_2
    //   45: aload_0
    //   46: iload_3
    //   47: invokevirtual 124	android/support/v4/util/ArraySet:valueAt	(I)Ljava/lang/Object;
    //   50: invokeinterface 171 2 0
    //   55: istore 4
    //   57: iload 4
    //   59: ifne +5 -> 64
    //   62: iconst_0
    //   63: ireturn
    //   64: iinc 3 1
    //   67: goto -31 -> 36
    //   70: iconst_1
    //   71: ireturn
    //   72: iconst_0
    //   73: ireturn
    //   74: iconst_0
    //   75: ireturn
    //   76: iconst_0
    //   77: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   36	57	72	java/lang/ClassCastException
    //   36	57	74	java/lang/NullPointerException
  }

  public int hashCode()
  {
    int[] arrayOfInt = this.mHashes;
    int i = this.mSize;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += arrayOfInt[j];
      j++;
    }
    return k;
  }

  public int indexOf(@Nullable Object paramObject)
  {
    int i;
    if (paramObject == null)
      i = indexOfNull();
    else
      i = indexOf(paramObject, paramObject.hashCode());
    return i;
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

  public Iterator<E> iterator()
  {
    return getCollection().getKeySet().iterator();
  }

  public boolean remove(@Nullable Object paramObject)
  {
    int i = indexOf(paramObject);
    if (i >= 0)
    {
      removeAt(i);
      return true;
    }
    return false;
  }

  public boolean removeAll(@NonNull ArraySet<? extends E> paramArraySet)
  {
    int i = paramArraySet.mSize;
    int j = this.mSize;
    for (int k = 0; k < i; k++)
      remove(paramArraySet.valueAt(k));
    int m = this.mSize;
    boolean bool = false;
    if (j != m)
      bool = true;
    return bool;
  }

  public boolean removeAll(@NonNull Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    boolean bool = false;
    while (localIterator.hasNext())
      bool |= remove(localIterator.next());
    return bool;
  }

  public E removeAt(int paramInt)
  {
    Object localObject = this.mArray[paramInt];
    if (this.mSize <= 1)
    {
      freeArrays(this.mHashes, this.mArray, this.mSize);
      this.mHashes = INT;
      this.mArray = OBJECT;
      this.mSize = 0;
    }
    else
    {
      int i = this.mHashes.length;
      int j = 8;
      if ((i > j) && (this.mSize < this.mHashes.length / 3))
      {
        if (this.mSize > j)
          j = this.mSize + (this.mSize >> 1);
        int[] arrayOfInt2 = this.mHashes;
        Object[] arrayOfObject = this.mArray;
        allocArrays(j);
        this.mSize -= 1;
        if (paramInt > 0)
        {
          System.arraycopy(arrayOfInt2, 0, this.mHashes, 0, paramInt);
          System.arraycopy(arrayOfObject, 0, this.mArray, 0, paramInt);
        }
        if (paramInt < this.mSize)
        {
          int m = paramInt + 1;
          System.arraycopy(arrayOfInt2, m, this.mHashes, paramInt, this.mSize - paramInt);
          System.arraycopy(arrayOfObject, m, this.mArray, paramInt, this.mSize - paramInt);
        }
      }
      else
      {
        this.mSize -= 1;
        if (paramInt < this.mSize)
        {
          int[] arrayOfInt1 = this.mHashes;
          int k = paramInt + 1;
          System.arraycopy(arrayOfInt1, k, this.mHashes, paramInt, this.mSize - paramInt);
          System.arraycopy(this.mArray, k, this.mArray, paramInt, this.mSize - paramInt);
        }
        this.mArray[this.mSize] = null;
      }
    }
    return localObject;
  }

  public boolean retainAll(@NonNull Collection<?> paramCollection)
  {
    int i = this.mSize - 1;
    boolean bool = false;
    while (i >= 0)
    {
      if (!paramCollection.contains(this.mArray[i]))
      {
        removeAt(i);
        bool = true;
      }
      i--;
    }
    return bool;
  }

  public int size()
  {
    return this.mSize;
  }

  @NonNull
  public Object[] toArray()
  {
    Object[] arrayOfObject = new Object[this.mSize];
    System.arraycopy(this.mArray, 0, arrayOfObject, 0, this.mSize);
    return arrayOfObject;
  }

  @NonNull
  public <T> T[] toArray(@NonNull T[] paramArrayOfT)
  {
    if (paramArrayOfT.length < this.mSize)
      paramArrayOfT = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), this.mSize);
    System.arraycopy(this.mArray, 0, paramArrayOfT, 0, this.mSize);
    if (paramArrayOfT.length > this.mSize)
      paramArrayOfT[this.mSize] = null;
    return paramArrayOfT;
  }

  public String toString()
  {
    if (isEmpty())
      return "{}";
    StringBuilder localStringBuilder = new StringBuilder(14 * this.mSize);
    localStringBuilder.append('{');
    for (int i = 0; i < this.mSize; i++)
    {
      if (i > 0)
        localStringBuilder.append(", ");
      Object localObject = valueAt(i);
      if (localObject != this)
        localStringBuilder.append(localObject);
      else
        localStringBuilder.append("(this Set)");
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  @Nullable
  public E valueAt(int paramInt)
  {
    return this.mArray[paramInt];
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.ArraySet
 * JD-Core Version:    0.6.1
 */