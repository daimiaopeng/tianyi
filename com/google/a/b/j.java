package com.google.a.b;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public final class j<V> extends AbstractMap<String, V>
{
  private static final Map.Entry[] b = new c[2];
  private static final int i = new Random().nextInt();
  private c<V> a = new c();
  private c<V>[] c = (c[])b;
  private int d;
  private int e = -1;
  private Set<String> f;
  private Set<Map.Entry<String, V>> g;
  private Collection<V> h;

  private c<V> a(String paramString)
  {
    if (paramString == null)
      return null;
    int j = b(paramString);
    c[] arrayOfc = this.c;
    c localc = arrayOfc[(j & -1 + arrayOfc.length)];
    while (localc != null)
    {
      String str = localc.a;
      if ((str != paramString) && ((localc.c != j) || (!paramString.equals(str))))
        localc = localc.d;
      else
        return localc;
    }
    return null;
  }

  private void a(c<V> paramc)
  {
    paramc.f.e = paramc.e;
    paramc.e.f = paramc.f;
    paramc.f = null;
    paramc.e = null;
  }

  private void a(String paramString, V paramV, int paramInt1, int paramInt2)
  {
    c localc1 = this.a;
    c localc2 = localc1.f;
    c localc3 = new c(paramString, paramV, paramInt1, this.c[paramInt2], localc1, localc2);
    c[] arrayOfc = this.c;
    localc1.f = localc3;
    localc2.e = localc3;
    arrayOfc[paramInt2] = localc3;
  }

  private boolean a(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 != null) && ((paramObject1 instanceof String)))
    {
      int j = b((String)paramObject1);
      c[] arrayOfc = this.c;
      int k = j & arrayOfc.length - 1;
      c localc1 = arrayOfc[k];
      c localc2 = null;
      while (true)
      {
        c localc3 = localc2;
        localc2 = localc1;
        if (localc2 == null)
          break;
        if ((localc2.c == j) && (paramObject1.equals(localc2.a)))
        {
          if (paramObject2 == null ? localc2.b != null : !paramObject2.equals(localc2.b))
            return false;
          if (localc3 == null)
            arrayOfc[k] = localc2.d;
          else
            localc3.d = localc2.d;
          this.d -= 1;
          a(localc2);
          return true;
        }
        localc1 = localc2.d;
      }
      return false;
    }
    return false;
  }

  private c<V>[] a()
  {
    c[] arrayOfc1 = this.c;
    int j = arrayOfc1.length;
    if (j == 1073741824)
      return arrayOfc1;
    c[] arrayOfc2 = a(j * 2);
    if (this.d == 0)
      return arrayOfc2;
    for (int k = 0; k < j; k++)
    {
      c localc1 = arrayOfc1[k];
      if (localc1 != null)
      {
        int m = j & localc1.c;
        arrayOfc2[(k | m)] = localc1;
        c localc2 = localc1.d;
        Object localObject1 = localc1;
        Object localObject2 = localc2;
        Object localObject3 = null;
        while (localObject2 != null)
        {
          int n = j & ((c)localObject2).c;
          if (n != m)
          {
            if (localObject3 == null)
              arrayOfc2[(k | n)] = localObject2;
            else
              localObject3.d = ((c)localObject2);
            localObject3 = localObject1;
            m = n;
          }
          c localc3 = ((c)localObject2).d;
          localObject1 = localObject2;
          localObject2 = localc3;
        }
        if (localObject3 != null)
          localObject3.d = null;
      }
    }
    return arrayOfc2;
  }

  private c<V>[] a(int paramInt)
  {
    c[] arrayOfc = (c[])new c[paramInt];
    this.c = arrayOfc;
    this.e = ((paramInt >> 1) + (paramInt >> 2));
    return arrayOfc;
  }

  private static int b(String paramString)
  {
    int j = i;
    for (int k = 0; k < paramString.length(); k++)
    {
      int n = j + paramString.charAt(k);
      int i1 = n + n << 10;
      j = i1 ^ i1 >>> 6;
    }
    int m = j ^ (j >>> 20 ^ j >>> 12);
    return m ^ m >>> 7 ^ m >>> 4;
  }

  public V a(String paramString, V paramV)
  {
    if (paramString == null)
      throw new NullPointerException("key == null");
    int j = b(paramString);
    c[] arrayOfc = this.c;
    int k = j & -1 + arrayOfc.length;
    for (c localc = arrayOfc[k]; localc != null; localc = localc.d)
      if ((localc.c == j) && (paramString.equals(localc.a)))
      {
        Object localObject = localc.b;
        localc.b = paramV;
        return localObject;
      }
    int m = this.d;
    this.d = (m + 1);
    if (m > this.e)
      k = j & -1 + a().length;
    a(paramString, paramV, j, k);
    return null;
  }

  public void clear()
  {
    if (this.d != 0)
    {
      Arrays.fill(this.c, null);
      this.d = 0;
    }
    c localc1 = this.a;
    c localc2;
    for (Object localObject = localc1.e; localObject != localc1; localObject = localc2)
    {
      localc2 = ((c)localObject).e;
      ((c)localObject).f = null;
      ((c)localObject).e = null;
    }
    localc1.f = localc1;
    localc1.e = localc1;
  }

  public boolean containsKey(Object paramObject)
  {
    boolean bool;
    if (((paramObject instanceof String)) && (a((String)paramObject) != null))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public Set<Map.Entry<String, V>> entrySet()
  {
    Object localObject = this.g;
    if (localObject == null)
    {
      localObject = new a(null);
      this.g = ((Set)localObject);
    }
    return localObject;
  }

  public V get(Object paramObject)
  {
    if ((paramObject instanceof String))
    {
      c localc = a((String)paramObject);
      Object localObject = null;
      if (localc != null)
        localObject = localc.b;
      return localObject;
    }
    return null;
  }

  public Set<String> keySet()
  {
    Object localObject = this.f;
    if (localObject == null)
    {
      localObject = new b(null);
      this.f = ((Set)localObject);
    }
    return localObject;
  }

  public V remove(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof String)))
    {
      int j = b((String)paramObject);
      c[] arrayOfc = this.c;
      int k = j & -1 + arrayOfc.length;
      Object localObject1 = arrayOfc[k];
      Object localObject2 = null;
      while (localObject1 != null)
      {
        if ((((c)localObject1).c == j) && (paramObject.equals(((c)localObject1).a)))
        {
          if (localObject2 == null)
            arrayOfc[k] = ((c)localObject1).d;
          else
            localObject2.d = ((c)localObject1).d;
          this.d = (-1 + this.d);
          a((c)localObject1);
          return ((c)localObject1).b;
        }
        c localc = ((c)localObject1).d;
        localObject2 = localObject1;
        localObject1 = localc;
      }
      return null;
    }
    return null;
  }

  public int size()
  {
    return this.d;
  }

  public Collection<V> values()
  {
    Object localObject = this.h;
    if (localObject == null)
    {
      localObject = new e(null);
      this.h = ((Collection)localObject);
    }
    return localObject;
  }

  private final class a extends AbstractSet<Map.Entry<String, V>>
  {
    private a()
    {
    }

    public void clear()
    {
      j.this.clear();
    }

    public boolean contains(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      Object localObject = j.this.get(localEntry.getKey());
      boolean bool1 = false;
      if (localObject != null)
      {
        boolean bool2 = localObject.equals(localEntry.getValue());
        bool1 = false;
        if (bool2)
          bool1 = true;
      }
      return bool1;
    }

    public Iterator<Map.Entry<String, V>> iterator()
    {
      // Byte code:
      //   0: new 46	com/google/a/b/j$a$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 49	com/google/a/b/j$a$1:<init>	(Lcom/google/a/b/j$a;)V
      //   8: areturn
    }

    public boolean remove(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      return j.a(j.this, localEntry.getKey(), localEntry.getValue());
    }

    public int size()
    {
      return j.b(j.this);
    }
  }

  private final class b extends AbstractSet<String>
  {
    private b()
    {
    }

    public void clear()
    {
      j.this.clear();
    }

    public boolean contains(Object paramObject)
    {
      return j.this.containsKey(paramObject);
    }

    public Iterator<String> iterator()
    {
      // Byte code:
      //   0: new 31	com/google/a/b/j$b$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 34	com/google/a/b/j$b$1:<init>	(Lcom/google/a/b/j$b;)V
      //   8: areturn
    }

    public boolean remove(Object paramObject)
    {
      int i = j.b(j.this);
      j.this.remove(paramObject);
      boolean bool;
      if (j.b(j.this) != i)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public int size()
    {
      return j.b(j.this);
    }
  }

  static class c<V>
    implements Map.Entry<String, V>
  {
    final String a;
    V b;
    final int c;
    c<V> d;
    c<V> e;
    c<V> f;

    c()
    {
      this(null, null, 0, null, null, null);
      this.f = this;
      this.e = this;
    }

    c(String paramString, V paramV, int paramInt, c<V> paramc1, c<V> paramc2, c<V> paramc3)
    {
      this.a = paramString;
      this.b = paramV;
      this.c = paramInt;
      this.d = paramc1;
      this.e = paramc2;
      this.f = paramc3;
    }

    public final String a()
    {
      return this.a;
    }

    public final boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      Object localObject = localEntry.getValue();
      boolean bool1 = this.a.equals(localEntry.getKey());
      boolean bool2 = false;
      if (bool1)
      {
        if (this.b == null)
        {
          bool2 = false;
          if (localObject != null)
            break label82;
        }
        else
        {
          boolean bool3 = this.b.equals(localObject);
          bool2 = false;
          if (!bool3)
            break label82;
        }
        bool2 = true;
      }
      label82: return bool2;
    }

    public final V getValue()
    {
      return this.b;
    }

    public final int hashCode()
    {
      int i;
      if (this.a == null)
        i = 0;
      else
        i = this.a.hashCode();
      int j;
      if (this.b == null)
        j = 0;
      else
        j = this.b.hashCode();
      return i ^ j;
    }

    public final V setValue(V paramV)
    {
      Object localObject = this.b;
      this.b = paramV;
      return localObject;
    }

    public final String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.a);
      localStringBuilder.append("=");
      localStringBuilder.append(this.b);
      return localStringBuilder.toString();
    }
  }

  private abstract class d<T>
    implements Iterator<T>
  {
    j.c<V> b = j.a(j.this).e;
    j.c<V> c = null;

    private d()
    {
    }

    final j.c<V> b()
    {
      j.c localc = this.b;
      if (localc == j.a(j.this))
        throw new NoSuchElementException();
      this.b = localc.e;
      this.c = localc;
      return localc;
    }

    public final boolean hasNext()
    {
      boolean bool;
      if (this.b != j.a(j.this))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public final void remove()
    {
      if (this.c == null)
        throw new IllegalStateException();
      j.this.remove(this.c.a);
      this.c = null;
    }
  }

  private final class e extends AbstractCollection<V>
  {
    private e()
    {
    }

    public void clear()
    {
      j.this.clear();
    }

    public boolean contains(Object paramObject)
    {
      return j.this.containsValue(paramObject);
    }

    public Iterator<V> iterator()
    {
      // Byte code:
      //   0: new 31	com/google/a/b/j$e$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 34	com/google/a/b/j$e$1:<init>	(Lcom/google/a/b/j$e;)V
      //   8: areturn
    }

    public int size()
    {
      return j.b(j.this);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.j
 * JD-Core Version:    0.6.1
 */