package android.arch.a.b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class b<K, V>
  implements Iterable<Map.Entry<K, V>>
{
  private c<K, V> a;
  private c<K, V> b;
  private WeakHashMap<f<K, V>, Boolean> c = new WeakHashMap();
  private int d = 0;

  public int a()
  {
    return this.d;
  }

  protected c<K, V> a(K paramK)
  {
    for (c localc = this.a; (localc != null) && (!localc.a.equals(paramK)); localc = localc.c);
    return localc;
  }

  public V a(@NonNull K paramK, @NonNull V paramV)
  {
    c localc = a(paramK);
    if (localc != null)
      return localc.b;
    b(paramK, paramV);
    return null;
  }

  protected c<K, V> b(@NonNull K paramK, @NonNull V paramV)
  {
    c localc = new c(paramK, paramV);
    this.d = (1 + this.d);
    if (this.b == null)
    {
      this.a = localc;
      this.b = this.a;
      return localc;
    }
    this.b.c = localc;
    localc.d = this.b;
    this.b = localc;
    return localc;
  }

  public V b(@NonNull K paramK)
  {
    c localc = a(paramK);
    if (localc == null)
      return null;
    this.d = (-1 + this.d);
    if (!this.c.isEmpty())
    {
      Iterator localIterator = this.c.keySet().iterator();
      while (localIterator.hasNext())
        ((f)localIterator.next()).a_(localc);
    }
    if (localc.d != null)
      localc.d.c = localc.c;
    else
      this.a = localc.c;
    if (localc.c != null)
      localc.c.d = localc.d;
    else
      this.b = localc.d;
    localc.c = null;
    localc.d = null;
    return localc.b;
  }

  public Iterator<Map.Entry<K, V>> b()
  {
    b localb = new b(this.b, this.a);
    this.c.put(localb, Boolean.valueOf(false));
    return localb;
  }

  public b<K, V>.d c()
  {
    d locald = new d(null);
    this.c.put(locald, Boolean.valueOf(false));
    return locald;
  }

  public Map.Entry<K, V> d()
  {
    return this.a;
  }

  public Map.Entry<K, V> e()
  {
    return this.b;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this)
      return bool;
    if (!(paramObject instanceof b))
      return false;
    b localb = (b)paramObject;
    if (a() != localb.a())
      return false;
    Iterator localIterator1 = iterator();
    Iterator localIterator2 = localb.iterator();
    while ((localIterator1.hasNext()) && (localIterator2.hasNext()))
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      Object localObject = localIterator2.next();
      if (((localEntry == null) && (localObject != null)) || ((localEntry != null) && (!localEntry.equals(localObject))))
        return false;
    }
    if ((localIterator1.hasNext()) || (localIterator2.hasNext()))
      bool = false;
    return bool;
  }

  @NonNull
  public Iterator<Map.Entry<K, V>> iterator()
  {
    a locala = new a(this.a, this.b);
    this.c.put(locala, Boolean.valueOf(false));
    return locala;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((Map.Entry)localIterator.next()).toString());
      if (localIterator.hasNext())
        localStringBuilder.append(", ");
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }

  static class a<K, V> extends b.e<K, V>
  {
    a(b.c<K, V> paramc1, b.c<K, V> paramc2)
    {
      super(paramc2);
    }

    b.c<K, V> a(b.c<K, V> paramc)
    {
      return paramc.c;
    }

    b.c<K, V> b(b.c<K, V> paramc)
    {
      return paramc.d;
    }
  }

  private static class b<K, V> extends b.e<K, V>
  {
    b(b.c<K, V> paramc1, b.c<K, V> paramc2)
    {
      super(paramc2);
    }

    b.c<K, V> a(b.c<K, V> paramc)
    {
      return paramc.d;
    }

    b.c<K, V> b(b.c<K, V> paramc)
    {
      return paramc.c;
    }
  }

  static class c<K, V>
    implements Map.Entry<K, V>
  {

    @NonNull
    final K a;

    @NonNull
    final V b;
    c<K, V> c;
    c<K, V> d;

    c(@NonNull K paramK, @NonNull V paramV)
    {
      this.a = paramK;
      this.b = paramV;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (paramObject == this)
        return bool;
      if (!(paramObject instanceof c))
        return false;
      c localc = (c)paramObject;
      if ((!this.a.equals(localc.a)) || (!this.b.equals(localc.b)))
        bool = false;
      return bool;
    }

    @NonNull
    public K getKey()
    {
      return this.a;
    }

    @NonNull
    public V getValue()
    {
      return this.b;
    }

    public V setValue(V paramV)
    {
      throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.a);
      localStringBuilder.append("=");
      localStringBuilder.append(this.b);
      return localStringBuilder.toString();
    }
  }

  private class d
    implements b.f<K, V>, Iterator<Map.Entry<K, V>>
  {
    private b.c<K, V> b;
    private boolean c = true;

    private d()
    {
    }

    public Map.Entry<K, V> a()
    {
      if (this.c)
      {
        this.c = false;
        this.b = b.a(b.this);
      }
      else
      {
        b.c localc;
        if (this.b != null)
          localc = this.b.c;
        else
          localc = null;
        this.b = localc;
      }
      return this.b;
    }

    public void a_(@NonNull b.c<K, V> paramc)
    {
      if (paramc == this.b)
      {
        this.b = this.b.d;
        boolean bool;
        if (this.b == null)
          bool = true;
        else
          bool = false;
        this.c = bool;
      }
    }

    public boolean hasNext()
    {
      if (this.c)
      {
        b.c localc3 = b.a(b.this);
        boolean bool2 = false;
        if (localc3 != null)
          bool2 = true;
        return bool2;
      }
      b.c localc1 = this.b;
      boolean bool1 = false;
      if (localc1 != null)
      {
        b.c localc2 = this.b.c;
        bool1 = false;
        if (localc2 != null)
          bool1 = true;
      }
      return bool1;
    }
  }

  private static abstract class e<K, V>
    implements b.f<K, V>, Iterator<Map.Entry<K, V>>
  {
    b.c<K, V> a;
    b.c<K, V> b;

    e(b.c<K, V> paramc1, b.c<K, V> paramc2)
    {
      this.a = paramc2;
      this.b = paramc1;
    }

    private b.c<K, V> b()
    {
      if ((this.b != this.a) && (this.a != null))
        return a(this.b);
      return null;
    }

    abstract b.c<K, V> a(b.c<K, V> paramc);

    public Map.Entry<K, V> a()
    {
      b.c localc = this.b;
      this.b = b();
      return localc;
    }

    public void a_(@NonNull b.c<K, V> paramc)
    {
      if ((this.a == paramc) && (paramc == this.b))
      {
        this.b = null;
        this.a = null;
      }
      if (this.a == paramc)
        this.a = b(this.a);
      if (this.b == paramc)
        this.b = b();
    }

    abstract b.c<K, V> b(b.c<K, V> paramc);

    public boolean hasNext()
    {
      boolean bool;
      if (this.b != null)
        bool = true;
      else
        bool = false;
      return bool;
    }
  }

  static abstract interface f<K, V>
  {
    public abstract void a_(@NonNull b.c<K, V> paramc);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.a.b.b
 * JD-Core Version:    0.6.1
 */