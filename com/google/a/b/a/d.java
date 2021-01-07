package com.google.a.b.a;

import com.google.a.d.a;
import com.google.a.d.c;
import com.google.a.g;
import com.google.a.k;
import com.google.a.l;
import com.google.a.n;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class d extends a
{
  private static final Reader a = new Reader()
  {
    public void close()
    {
      throw new AssertionError();
    }

    public int read(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new AssertionError();
    }
  };
  private static final Object b = new Object();
  private final List<Object> c;

  private void a(c paramc)
  {
    if (f() != paramc)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected ");
      localStringBuilder.append(paramc);
      localStringBuilder.append(" but was ");
      localStringBuilder.append(f());
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }

  private Object q()
  {
    return this.c.get(-1 + this.c.size());
  }

  private Object r()
  {
    return this.c.remove(-1 + this.c.size());
  }

  public void a()
  {
    a(c.a);
    g localg = (g)q();
    this.c.add(localg.iterator());
  }

  public void b()
  {
    a(c.b);
    r();
    r();
  }

  public void c()
  {
    a(c.c);
    l locall = (l)q();
    this.c.add(locall.o().iterator());
  }

  public void close()
  {
    this.c.clear();
    this.c.add(b);
  }

  public void d()
  {
    a(c.d);
    r();
    r();
  }

  public boolean e()
  {
    c localc = f();
    boolean bool;
    if ((localc != c.d) && (localc != c.b))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public c f()
  {
    if (this.c.isEmpty())
      return c.j;
    Object localObject = q();
    if ((localObject instanceof Iterator))
    {
      boolean bool = this.c.get(-2 + this.c.size()) instanceof l;
      Iterator localIterator = (Iterator)localObject;
      if (localIterator.hasNext())
      {
        if (bool)
          return c.e;
        this.c.add(localIterator.next());
        return f();
      }
      c localc;
      if (bool)
        localc = c.d;
      else
        localc = c.b;
      return localc;
    }
    if ((localObject instanceof l))
      return c.c;
    if ((localObject instanceof g))
      return c.a;
    if ((localObject instanceof n))
    {
      n localn = (n)localObject;
      if (localn.q())
        return c.f;
      if (localn.o())
        return c.h;
      if (localn.p())
        return c.g;
      throw new AssertionError();
    }
    if ((localObject instanceof k))
      return c.i;
    if (localObject == b)
      throw new IllegalStateException("JsonReader is closed");
    throw new AssertionError();
  }

  public String g()
  {
    a(c.e);
    Map.Entry localEntry = (Map.Entry)((Iterator)q()).next();
    this.c.add(localEntry.getValue());
    return (String)localEntry.getKey();
  }

  public String h()
  {
    c localc = f();
    if ((localc != c.f) && (localc != c.g))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected ");
      localStringBuilder.append(c.f);
      localStringBuilder.append(" but was ");
      localStringBuilder.append(localc);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return ((n)r()).b();
  }

  public boolean i()
  {
    a(c.h);
    return ((n)r()).f();
  }

  public void j()
  {
    a(c.i);
    r();
  }

  public double k()
  {
    c localc = f();
    if ((localc != c.g) && (localc != c.f))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Expected ");
      localStringBuilder2.append(c.g);
      localStringBuilder2.append(" but was ");
      localStringBuilder2.append(localc);
      throw new IllegalStateException(localStringBuilder2.toString());
    }
    double d = ((n)q()).c();
    if ((!p()) && ((Double.isNaN(d)) || (Double.isInfinite(d))))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("JSON forbids NaN and infinities: ");
      localStringBuilder1.append(d);
      throw new NumberFormatException(localStringBuilder1.toString());
    }
    r();
    return d;
  }

  public long l()
  {
    c localc = f();
    if ((localc != c.g) && (localc != c.f))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected ");
      localStringBuilder.append(c.g);
      localStringBuilder.append(" but was ");
      localStringBuilder.append(localc);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    long l = ((n)q()).d();
    r();
    return l;
  }

  public int m()
  {
    c localc = f();
    if ((localc != c.g) && (localc != c.f))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected ");
      localStringBuilder.append(c.g);
      localStringBuilder.append(" but was ");
      localStringBuilder.append(localc);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    int i = ((n)q()).e();
    r();
    return i;
  }

  public void n()
  {
    if (f() == c.e)
      g();
    else
      r();
  }

  public void o()
  {
    a(c.e);
    Map.Entry localEntry = (Map.Entry)((Iterator)q()).next();
    this.c.add(localEntry.getValue());
    this.c.add(new n((String)localEntry.getKey()));
  }

  public String toString()
  {
    return getClass().getSimpleName();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.d
 * JD-Core Version:    0.6.1
 */