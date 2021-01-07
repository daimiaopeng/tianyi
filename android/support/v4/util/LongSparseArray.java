package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LongSparseArray<E>
  implements Cloneable
{
  private static final Object DELETED = new Object();
  private boolean mGarbage = false;
  private long[] mKeys;
  private int mSize;
  private Object[] mValues;

  public LongSparseArray()
  {
    this(10);
  }

  public LongSparseArray(int paramInt)
  {
    if (paramInt == 0)
    {
      this.mKeys = ContainerHelpers.EMPTY_LONGS;
      this.mValues = ContainerHelpers.EMPTY_OBJECTS;
    }
    else
    {
      int i = ContainerHelpers.idealLongArraySize(paramInt);
      this.mKeys = new long[i];
      this.mValues = new Object[i];
    }
    this.mSize = 0;
  }

  private void gc()
  {
    int i = this.mSize;
    long[] arrayOfLong = this.mKeys;
    Object[] arrayOfObject = this.mValues;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      Object localObject = arrayOfObject[j];
      if (localObject != DELETED)
      {
        if (j != k)
        {
          arrayOfLong[k] = arrayOfLong[j];
          arrayOfObject[k] = localObject;
          arrayOfObject[j] = null;
        }
        k++;
      }
      j++;
    }
    this.mGarbage = false;
    this.mSize = k;
  }

  public void append(long paramLong, E paramE)
  {
    if ((this.mSize != 0) && (paramLong <= this.mKeys[(-1 + this.mSize)]))
    {
      put(paramLong, paramE);
      return;
    }
    if ((this.mGarbage) && (this.mSize >= this.mKeys.length))
      gc();
    int i = this.mSize;
    if (i >= this.mKeys.length)
    {
      int j = ContainerHelpers.idealLongArraySize(i + 1);
      long[] arrayOfLong = new long[j];
      Object[] arrayOfObject = new Object[j];
      System.arraycopy(this.mKeys, 0, arrayOfLong, 0, this.mKeys.length);
      System.arraycopy(this.mValues, 0, arrayOfObject, 0, this.mValues.length);
      this.mKeys = arrayOfLong;
      this.mValues = arrayOfObject;
    }
    this.mKeys[i] = paramLong;
    this.mValues[i] = paramE;
    this.mSize = (i + 1);
  }

  public void clear()
  {
    int i = this.mSize;
    Object[] arrayOfObject = this.mValues;
    for (int j = 0; j < i; j++)
      arrayOfObject[j] = null;
    this.mSize = 0;
    this.mGarbage = false;
  }

  public LongSparseArray<E> clone()
  {
    try
    {
      LongSparseArray localLongSparseArray = (LongSparseArray)super.clone();
      localLongSparseArray.mKeys = ((long[])this.mKeys.clone());
      localLongSparseArray.mValues = ((Object[])this.mValues.clone());
      return localLongSparseArray;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }

  public boolean containsKey(long paramLong)
  {
    boolean bool;
    if (indexOfKey(paramLong) >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean containsValue(E paramE)
  {
    boolean bool;
    if (indexOfValue(paramE) >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void delete(long paramLong)
  {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramLong);
    if ((i >= 0) && (this.mValues[i] != DELETED))
    {
      this.mValues[i] = DELETED;
      this.mGarbage = true;
    }
  }

  @Nullable
  public E get(long paramLong)
  {
    return get(paramLong, null);
  }

  public E get(long paramLong, E paramE)
  {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramLong);
    if ((i >= 0) && (this.mValues[i] != DELETED))
      return this.mValues[i];
    return paramE;
  }

  public int indexOfKey(long paramLong)
  {
    if (this.mGarbage)
      gc();
    return ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramLong);
  }

  public int indexOfValue(E paramE)
  {
    if (this.mGarbage)
      gc();
    for (int i = 0; i < this.mSize; i++)
      if (this.mValues[i] == paramE)
        return i;
    return -1;
  }

  public boolean isEmpty()
  {
    boolean bool;
    if (size() == 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public long keyAt(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return this.mKeys[paramInt];
  }

  public void put(long paramLong, E paramE)
  {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramLong);
    if (i >= 0)
    {
      this.mValues[i] = paramE;
    }
    else
    {
      int j = i ^ 0xFFFFFFFF;
      if ((j < this.mSize) && (this.mValues[j] == DELETED))
      {
        this.mKeys[j] = paramLong;
        this.mValues[j] = paramE;
        return;
      }
      if ((this.mGarbage) && (this.mSize >= this.mKeys.length))
      {
        gc();
        j = 0xFFFFFFFF ^ ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramLong);
      }
      if (this.mSize >= this.mKeys.length)
      {
        int m = ContainerHelpers.idealLongArraySize(1 + this.mSize);
        long[] arrayOfLong3 = new long[m];
        Object[] arrayOfObject = new Object[m];
        System.arraycopy(this.mKeys, 0, arrayOfLong3, 0, this.mKeys.length);
        System.arraycopy(this.mValues, 0, arrayOfObject, 0, this.mValues.length);
        this.mKeys = arrayOfLong3;
        this.mValues = arrayOfObject;
      }
      if (this.mSize - j != 0)
      {
        long[] arrayOfLong1 = this.mKeys;
        long[] arrayOfLong2 = this.mKeys;
        int k = j + 1;
        System.arraycopy(arrayOfLong1, j, arrayOfLong2, k, this.mSize - j);
        System.arraycopy(this.mValues, j, this.mValues, k, this.mSize - j);
      }
      this.mKeys[j] = paramLong;
      this.mValues[j] = paramE;
      this.mSize = (1 + this.mSize);
    }
  }

  public void putAll(@NonNull LongSparseArray<? extends E> paramLongSparseArray)
  {
    int i = paramLongSparseArray.size();
    for (int j = 0; j < i; j++)
      put(paramLongSparseArray.keyAt(j), paramLongSparseArray.valueAt(j));
  }

  public void remove(long paramLong)
  {
    delete(paramLong);
  }

  public void removeAt(int paramInt)
  {
    if (this.mValues[paramInt] != DELETED)
    {
      this.mValues[paramInt] = DELETED;
      this.mGarbage = true;
    }
  }

  public void setValueAt(int paramInt, E paramE)
  {
    if (this.mGarbage)
      gc();
    this.mValues[paramInt] = paramE;
  }

  public int size()
  {
    if (this.mGarbage)
      gc();
    return this.mSize;
  }

  public String toString()
  {
    if (size() <= 0)
      return "{}";
    StringBuilder localStringBuilder = new StringBuilder(28 * this.mSize);
    localStringBuilder.append('{');
    for (int i = 0; i < this.mSize; i++)
    {
      if (i > 0)
        localStringBuilder.append(", ");
      localStringBuilder.append(keyAt(i));
      localStringBuilder.append('=');
      Object localObject = valueAt(i);
      if (localObject != this)
        localStringBuilder.append(localObject);
      else
        localStringBuilder.append("(this Map)");
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  public E valueAt(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return this.mValues[paramInt];
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.LongSparseArray
 * JD-Core Version:    0.6.1
 */