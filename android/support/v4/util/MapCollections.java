package android.support.v4.util;

import android.support.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections<K, V>
{

  @Nullable
  MapCollections<K, V>.EntrySet mEntrySet;

  @Nullable
  MapCollections<K, V>.KeySet mKeySet;

  @Nullable
  MapCollections<K, V>.ValuesCollection mValues;

  public static <K, V> boolean containsAllHelper(Map<K, V> paramMap, Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
      if (!paramMap.containsKey(localIterator.next()))
        return false;
    return true;
  }

  // ERROR //
  public static <T> boolean equalsSetHelper(Set<T> paramSet, Object paramObject)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: aload_1
    //   4: if_acmpne +5 -> 9
    //   7: iload_2
    //   8: ireturn
    //   9: aload_1
    //   10: instanceof 51
    //   13: ifeq +48 -> 61
    //   16: aload_1
    //   17: checkcast 51	java/util/Set
    //   20: astore_3
    //   21: aload_0
    //   22: invokeinterface 55 1 0
    //   27: aload_3
    //   28: invokeinterface 55 1 0
    //   33: if_icmpne +20 -> 53
    //   36: aload_0
    //   37: aload_3
    //   38: invokeinterface 59 2 0
    //   43: istore 4
    //   45: iload 4
    //   47: ifeq +6 -> 53
    //   50: goto +5 -> 55
    //   53: iconst_0
    //   54: istore_2
    //   55: iload_2
    //   56: ireturn
    //   57: iconst_0
    //   58: ireturn
    //   59: iconst_0
    //   60: ireturn
    //   61: iconst_0
    //   62: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   21	45	57	java/lang/ClassCastException
    //   21	45	59	java/lang/NullPointerException
  }

  public static <K, V> boolean removeAllHelper(Map<K, V> paramMap, Collection<?> paramCollection)
  {
    int i = paramMap.size();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
      paramMap.remove(localIterator.next());
    boolean bool;
    if (i != paramMap.size())
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static <K, V> boolean retainAllHelper(Map<K, V> paramMap, Collection<?> paramCollection)
  {
    int i = paramMap.size();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
      if (!paramCollection.contains(localIterator.next()))
        localIterator.remove();
    boolean bool;
    if (i != paramMap.size())
      bool = true;
    else
      bool = false;
    return bool;
  }

  protected abstract void colClear();

  protected abstract Object colGetEntry(int paramInt1, int paramInt2);

  protected abstract Map<K, V> colGetMap();

  protected abstract int colGetSize();

  protected abstract int colIndexOfKey(Object paramObject);

  protected abstract int colIndexOfValue(Object paramObject);

  protected abstract void colPut(K paramK, V paramV);

  protected abstract void colRemoveAt(int paramInt);

  protected abstract V colSetValue(int paramInt, V paramV);

  public Set<Map.Entry<K, V>> getEntrySet()
  {
    if (this.mEntrySet == null)
      this.mEntrySet = new EntrySet();
    return this.mEntrySet;
  }

  public Set<K> getKeySet()
  {
    if (this.mKeySet == null)
      this.mKeySet = new KeySet();
    return this.mKeySet;
  }

  public Collection<V> getValues()
  {
    if (this.mValues == null)
      this.mValues = new ValuesCollection();
    return this.mValues;
  }

  public Object[] toArrayHelper(int paramInt)
  {
    int i = colGetSize();
    Object[] arrayOfObject = new Object[i];
    for (int j = 0; j < i; j++)
      arrayOfObject[j] = colGetEntry(j, paramInt);
    return arrayOfObject;
  }

  public <T> T[] toArrayHelper(T[] paramArrayOfT, int paramInt)
  {
    int i = colGetSize();
    if (paramArrayOfT.length < i)
      paramArrayOfT = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i);
    for (int j = 0; j < i; j++)
      paramArrayOfT[j] = colGetEntry(j, paramInt);
    if (paramArrayOfT.length > i)
      paramArrayOfT[i] = null;
    return paramArrayOfT;
  }

  final class ArrayIterator<T>
    implements Iterator<T>
  {
    boolean mCanRemove = false;
    int mIndex;
    final int mOffset;
    int mSize;

    ArrayIterator(int arg2)
    {
      int i;
      this.mOffset = i;
      this.mSize = MapCollections.this.colGetSize();
    }

    public boolean hasNext()
    {
      boolean bool;
      if (this.mIndex < this.mSize)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public T next()
    {
      if (!hasNext())
        throw new NoSuchElementException();
      Object localObject = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
      this.mIndex = (1 + this.mIndex);
      this.mCanRemove = true;
      return localObject;
    }

    public void remove()
    {
      if (!this.mCanRemove)
        throw new IllegalStateException();
      this.mIndex = (-1 + this.mIndex);
      this.mSize = (-1 + this.mSize);
      this.mCanRemove = false;
      MapCollections.this.colRemoveAt(this.mIndex);
    }
  }

  final class EntrySet
    implements Set<Map.Entry<K, V>>
  {
    EntrySet()
    {
    }

    public boolean add(Map.Entry<K, V> paramEntry)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends Map.Entry<K, V>> paramCollection)
    {
      int i = MapCollections.this.colGetSize();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        MapCollections.this.colPut(localEntry.getKey(), localEntry.getValue());
      }
      boolean bool;
      if (i != MapCollections.this.colGetSize())
        bool = true;
      else
        bool = false;
      return bool;
    }

    public void clear()
    {
      MapCollections.this.colClear();
    }

    public boolean contains(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      int i = MapCollections.this.colIndexOfKey(localEntry.getKey());
      if (i < 0)
        return false;
      return ContainerHelpers.equal(MapCollections.this.colGetEntry(i, 1), localEntry.getValue());
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        if (!contains(localIterator.next()))
          return false;
      return true;
    }

    public boolean equals(Object paramObject)
    {
      return MapCollections.equalsSetHelper(this, paramObject);
    }

    public int hashCode()
    {
      int i = MapCollections.this.colGetSize() - 1;
      int j = 0;
      while (i >= 0)
      {
        Object localObject1 = MapCollections.this.colGetEntry(i, 0);
        Object localObject2 = MapCollections.this.colGetEntry(i, 1);
        int k;
        if (localObject1 == null)
          k = 0;
        else
          k = localObject1.hashCode();
        int m;
        if (localObject2 == null)
          m = 0;
        else
          m = localObject2.hashCode();
        j += (k ^ m);
        i--;
      }
      return j;
    }

    public boolean isEmpty()
    {
      boolean bool;
      if (MapCollections.this.colGetSize() == 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public Iterator<Map.Entry<K, V>> iterator()
    {
      return new MapCollections.MapIterator(MapCollections.this);
    }

    public boolean remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public int size()
    {
      return MapCollections.this.colGetSize();
    }

    public Object[] toArray()
    {
      throw new UnsupportedOperationException();
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      throw new UnsupportedOperationException();
    }
  }

  final class KeySet
    implements Set<K>
  {
    KeySet()
    {
    }

    public boolean add(K paramK)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends K> paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      MapCollections.this.colClear();
    }

    public boolean contains(Object paramObject)
    {
      boolean bool;
      if (MapCollections.this.colIndexOfKey(paramObject) >= 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), paramCollection);
    }

    public boolean equals(Object paramObject)
    {
      return MapCollections.equalsSetHelper(this, paramObject);
    }

    public int hashCode()
    {
      int i = -1 + MapCollections.this.colGetSize();
      int j = 0;
      while (i >= 0)
      {
        Object localObject = MapCollections.this.colGetEntry(i, 0);
        int k;
        if (localObject == null)
          k = 0;
        else
          k = localObject.hashCode();
        j += k;
        i--;
      }
      return j;
    }

    public boolean isEmpty()
    {
      boolean bool;
      if (MapCollections.this.colGetSize() == 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public Iterator<K> iterator()
    {
      return new MapCollections.ArrayIterator(MapCollections.this, 0);
    }

    public boolean remove(Object paramObject)
    {
      int i = MapCollections.this.colIndexOfKey(paramObject);
      if (i >= 0)
      {
        MapCollections.this.colRemoveAt(i);
        return true;
      }
      return false;
    }

    public boolean removeAll(Collection<?> paramCollection)
    {
      return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), paramCollection);
    }

    public boolean retainAll(Collection<?> paramCollection)
    {
      return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), paramCollection);
    }

    public int size()
    {
      return MapCollections.this.colGetSize();
    }

    public Object[] toArray()
    {
      return MapCollections.this.toArrayHelper(0);
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return MapCollections.this.toArrayHelper(paramArrayOfT, 0);
    }
  }

  final class MapIterator
    implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V>
  {
    int mEnd = -1 + MapCollections.this.colGetSize();
    boolean mEntryValid = false;
    int mIndex = -1;

    MapIterator()
    {
    }

    public boolean equals(Object paramObject)
    {
      if (!this.mEntryValid)
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      boolean bool1 = ContainerHelpers.equal(localEntry.getKey(), MapCollections.this.colGetEntry(this.mIndex, 0));
      boolean bool2 = false;
      if (bool1)
      {
        boolean bool3 = ContainerHelpers.equal(localEntry.getValue(), MapCollections.this.colGetEntry(this.mIndex, 1));
        bool2 = false;
        if (bool3)
          bool2 = true;
      }
      return bool2;
    }

    public K getKey()
    {
      if (!this.mEntryValid)
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      return MapCollections.this.colGetEntry(this.mIndex, 0);
    }

    public V getValue()
    {
      if (!this.mEntryValid)
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      return MapCollections.this.colGetEntry(this.mIndex, 1);
    }

    public boolean hasNext()
    {
      boolean bool;
      if (this.mIndex < this.mEnd)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public int hashCode()
    {
      if (!this.mEntryValid)
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      Object localObject1 = MapCollections.this.colGetEntry(this.mIndex, 0);
      Object localObject2 = MapCollections.this.colGetEntry(this.mIndex, 1);
      int i;
      if (localObject1 == null)
        i = 0;
      else
        i = localObject1.hashCode();
      int j;
      if (localObject2 == null)
        j = 0;
      else
        j = localObject2.hashCode();
      return i ^ j;
    }

    public Map.Entry<K, V> next()
    {
      if (!hasNext())
        throw new NoSuchElementException();
      this.mIndex = (1 + this.mIndex);
      this.mEntryValid = true;
      return this;
    }

    public void remove()
    {
      if (!this.mEntryValid)
        throw new IllegalStateException();
      MapCollections.this.colRemoveAt(this.mIndex);
      this.mIndex = (-1 + this.mIndex);
      this.mEnd = (-1 + this.mEnd);
      this.mEntryValid = false;
    }

    public V setValue(V paramV)
    {
      if (!this.mEntryValid)
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      return MapCollections.this.colSetValue(this.mIndex, paramV);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(getKey());
      localStringBuilder.append("=");
      localStringBuilder.append(getValue());
      return localStringBuilder.toString();
    }
  }

  final class ValuesCollection
    implements Collection<V>
  {
    ValuesCollection()
    {
    }

    public boolean add(V paramV)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends V> paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      MapCollections.this.colClear();
    }

    public boolean contains(Object paramObject)
    {
      boolean bool;
      if (MapCollections.this.colIndexOfValue(paramObject) >= 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        if (!contains(localIterator.next()))
          return false;
      return true;
    }

    public boolean isEmpty()
    {
      boolean bool;
      if (MapCollections.this.colGetSize() == 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public Iterator<V> iterator()
    {
      return new MapCollections.ArrayIterator(MapCollections.this, 1);
    }

    public boolean remove(Object paramObject)
    {
      int i = MapCollections.this.colIndexOfValue(paramObject);
      if (i >= 0)
      {
        MapCollections.this.colRemoveAt(i);
        return true;
      }
      return false;
    }

    public boolean removeAll(Collection<?> paramCollection)
    {
      int i = MapCollections.this.colGetSize();
      int j = 0;
      boolean bool = false;
      while (j < i)
      {
        if (paramCollection.contains(MapCollections.this.colGetEntry(j, 1)))
        {
          MapCollections.this.colRemoveAt(j);
          j--;
          i--;
          bool = true;
        }
        j++;
      }
      return bool;
    }

    public boolean retainAll(Collection<?> paramCollection)
    {
      int i = MapCollections.this.colGetSize();
      int j = 0;
      boolean bool = false;
      while (j < i)
      {
        if (!paramCollection.contains(MapCollections.this.colGetEntry(j, 1)))
        {
          MapCollections.this.colRemoveAt(j);
          j--;
          i--;
          bool = true;
        }
        j++;
      }
      return bool;
    }

    public int size()
    {
      return MapCollections.this.colGetSize();
    }

    public Object[] toArray()
    {
      return MapCollections.this.toArrayHelper(1);
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return MapCollections.this.toArrayHelper(paramArrayOfT, 1);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.MapCollections
 * JD-Core Version:    0.6.1
 */