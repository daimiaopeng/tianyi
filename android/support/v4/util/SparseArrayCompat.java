package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SparseArrayCompat<E>
  implements Cloneable
{
  private static final Object DELETED = new Object();
  private boolean mGarbage = false;
  private int[] mKeys;
  private int mSize;
  private Object[] mValues;

  public SparseArrayCompat()
  {
    this(10);
  }

  public SparseArrayCompat(int paramInt)
  {
    if (paramInt == 0)
    {
      this.mKeys = ContainerHelpers.EMPTY_INTS;
      this.mValues = ContainerHelpers.EMPTY_OBJECTS;
    }
    else
    {
      int i = ContainerHelpers.idealIntArraySize(paramInt);
      this.mKeys = new int[i];
      this.mValues = new Object[i];
    }
    this.mSize = 0;
  }

  private void gc()
  {
    int i = this.mSize;
    int[] arrayOfInt = this.mKeys;
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
          arrayOfInt[k] = arrayOfInt[j];
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

  public void append(int paramInt, E paramE)
  {
    if ((this.mSize != 0) && (paramInt <= this.mKeys[(-1 + this.mSize)]))
    {
      put(paramInt, paramE);
      return;
    }
    if ((this.mGarbage) && (this.mSize >= this.mKeys.length))
      gc();
    int i = this.mSize;
    if (i >= this.mKeys.length)
    {
      int j = ContainerHelpers.idealIntArraySize(i + 1);
      int[] arrayOfInt = new int[j];
      Object[] arrayOfObject = new Object[j];
      System.arraycopy(this.mKeys, 0, arrayOfInt, 0, this.mKeys.length);
      System.arraycopy(this.mValues, 0, arrayOfObject, 0, this.mValues.length);
      this.mKeys = arrayOfInt;
      this.mValues = arrayOfObject;
    }
    this.mKeys[i] = paramInt;
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

  public SparseArrayCompat<E> clone()
  {
    try
    {
      SparseArrayCompat localSparseArrayCompat = (SparseArrayCompat)super.clone();
      localSparseArrayCompat.mKeys = ((int[])this.mKeys.clone());
      localSparseArrayCompat.mValues = ((Object[])this.mValues.clone());
      return localSparseArrayCompat;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }

  public boolean containsKey(int paramInt)
  {
    boolean bool;
    if (indexOfKey(paramInt) >= 0)
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

  public void delete(int paramInt)
  {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
    if ((i >= 0) && (this.mValues[i] != DELETED))
    {
      this.mValues[i] = DELETED;
      this.mGarbage = true;
    }
  }

  @Nullable
  public E get(int paramInt)
  {
    return get(paramInt, null);
  }

  public E get(int paramInt, E paramE)
  {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
    if ((i >= 0) && (this.mValues[i] != DELETED))
      return this.mValues[i];
    return paramE;
  }

  public int indexOfKey(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
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

  public int keyAt(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return this.mKeys[paramInt];
  }

  public void put(int paramInt, E paramE)
  {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
    if (i >= 0)
    {
      this.mValues[i] = paramE;
    }
    else
    {
      int j = i ^ 0xFFFFFFFF;
      if ((j < this.mSize) && (this.mValues[j] == DELETED))
      {
        this.mKeys[j] = paramInt;
        this.mValues[j] = paramE;
        return;
      }
      if ((this.mGarbage) && (this.mSize >= this.mKeys.length))
      {
        gc();
        j = 0xFFFFFFFF ^ ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
      }
      if (this.mSize >= this.mKeys.length)
      {
        int m = ContainerHelpers.idealIntArraySize(1 + this.mSize);
        int[] arrayOfInt3 = new int[m];
        Object[] arrayOfObject = new Object[m];
        System.arraycopy(this.mKeys, 0, arrayOfInt3, 0, this.mKeys.length);
        System.arraycopy(this.mValues, 0, arrayOfObject, 0, this.mValues.length);
        this.mKeys = arrayOfInt3;
        this.mValues = arrayOfObject;
      }
      if (this.mSize - j != 0)
      {
        int[] arrayOfInt1 = this.mKeys;
        int[] arrayOfInt2 = this.mKeys;
        int k = j + 1;
        System.arraycopy(arrayOfInt1, j, arrayOfInt2, k, this.mSize - j);
        System.arraycopy(this.mValues, j, this.mValues, k, this.mSize - j);
      }
      this.mKeys[j] = paramInt;
      this.mValues[j] = paramE;
      this.mSize = (1 + this.mSize);
    }
  }

  public void putAll(@NonNull SparseArrayCompat<? extends E> paramSparseArrayCompat)
  {
    int i = paramSparseArrayCompat.size();
    for (int j = 0; j < i; j++)
      put(paramSparseArrayCompat.keyAt(j), paramSparseArrayCompat.valueAt(j));
  }

  public void remove(int paramInt)
  {
    delete(paramInt);
  }

  public void removeAt(int paramInt)
  {
    if (this.mValues[paramInt] != DELETED)
    {
      this.mValues[paramInt] = DELETED;
      this.mGarbage = true;
    }
  }

  public void removeAtRange(int paramInt1, int paramInt2)
  {
    int i = Math.min(this.mSize, paramInt2 + paramInt1);
    while (paramInt1 < i)
    {
      removeAt(paramInt1);
      paramInt1++;
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
 * Qualified Name:     android.support.v4.util.SparseArrayCompat
 * JD-Core Version:    0.6.1
 */