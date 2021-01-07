package com.google.a.b.a;

import com.google.a.b.b;
import com.google.a.b.g;
import com.google.a.d.d;
import com.google.a.n;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class f
  implements s
{
  private final com.google.a.b.c a;
  private final boolean b;

  public f(com.google.a.b.c paramc, boolean paramBoolean)
  {
    this.a = paramc;
    this.b = paramBoolean;
  }

  private r<?> a(com.google.a.e parame, Type paramType)
  {
    r localr;
    if ((paramType != Boolean.TYPE) && (paramType != Boolean.class))
      localr = parame.a(com.google.a.c.a.a(paramType));
    else
      localr = l.f;
    return localr;
  }

  public <T> r<T> a(com.google.a.e parame, com.google.a.c.a<T> parama)
  {
    Type localType = parama.b();
    if (!Map.class.isAssignableFrom(parama.a()))
      return null;
    Type[] arrayOfType = b.b(localType, b.e(localType));
    r localr1 = a(parame, arrayOfType[0]);
    r localr2 = parame.a(com.google.a.c.a.a(arrayOfType[1]));
    g localg = this.a.a(parama);
    a locala = new a(parame, arrayOfType[0], localr1, arrayOfType[1], localr2, localg);
    return locala;
  }

  private final class a<K, V> extends r<Map<K, V>>
  {
    private final r<K> b;
    private final r<V> c;
    private final g<? extends Map<K, V>> d;

    public a(Type paramr, r<K> paramType1, Type paramr1, r<V> paramg, g<? extends Map<K, V>> arg6)
    {
      this.b = new k(paramr, paramr1, paramType1);
      r localr;
      this.c = new k(paramr, localr, paramg);
      Object localObject;
      this.d = localObject;
    }

    private String a(com.google.a.i parami)
    {
      if (parami.i())
      {
        n localn = parami.m();
        if (localn.p())
          return String.valueOf(localn.a());
        if (localn.o())
          return Boolean.toString(localn.f());
        if (localn.q())
          return localn.b();
        throw new AssertionError();
      }
      if (parami.j())
        return "null";
      throw new AssertionError();
    }

    public Map<K, V> a(com.google.a.d.a parama)
    {
      com.google.a.d.c localc = parama.f();
      if (localc == com.google.a.d.c.i)
      {
        parama.j();
        return null;
      }
      Map localMap = (Map)this.d.a();
      if (localc == com.google.a.d.c.a)
      {
        parama.a();
        while (parama.e())
        {
          parama.a();
          Object localObject2 = this.b.b(parama);
          if (localMap.put(localObject2, this.c.b(parama)) != null)
          {
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("duplicate key: ");
            localStringBuilder2.append(localObject2);
            throw new p(localStringBuilder2.toString());
          }
          parama.b();
        }
        parama.b();
      }
      else
      {
        parama.c();
        while (parama.e())
        {
          com.google.a.b.e.a.a(parama);
          Object localObject1 = this.b.b(parama);
          if (localMap.put(localObject1, this.c.b(parama)) != null)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("duplicate key: ");
            localStringBuilder1.append(localObject1);
            throw new p(localStringBuilder1.toString());
          }
        }
        parama.d();
      }
      return localMap;
    }

    public void a(d paramd, Map<K, V> paramMap)
    {
      if (paramMap == null)
      {
        paramd.f();
        return;
      }
      if (!f.a(f.this))
      {
        paramd.d();
        Iterator localIterator2 = paramMap.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
          paramd.a(String.valueOf(localEntry2.getKey()));
          this.c.a(paramd, localEntry2.getValue());
        }
        paramd.e();
        return;
      }
      ArrayList localArrayList1 = new ArrayList(paramMap.size());
      ArrayList localArrayList2 = new ArrayList(paramMap.size());
      Iterator localIterator1 = paramMap.entrySet().iterator();
      int i = 0;
      int j = 0;
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
        com.google.a.i locali = this.b.a(localEntry1.getKey());
        localArrayList1.add(locali);
        localArrayList2.add(localEntry1.getValue());
        int k;
        if ((!locali.g()) && (!locali.h()))
          k = 0;
        else
          k = 1;
        j |= k;
      }
      if (j != 0)
      {
        paramd.b();
        while (i < localArrayList1.size())
        {
          paramd.b();
          com.google.a.b.i.a((com.google.a.i)localArrayList1.get(i), paramd);
          this.c.a(paramd, localArrayList2.get(i));
          paramd.c();
          i++;
        }
        paramd.c();
      }
      else
      {
        paramd.d();
        while (i < localArrayList1.size())
        {
          paramd.a(a((com.google.a.i)localArrayList1.get(i)));
          this.c.a(paramd, localArrayList2.get(i));
          i++;
        }
        paramd.e();
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.f
 * JD-Core Version:    0.6.1
 */