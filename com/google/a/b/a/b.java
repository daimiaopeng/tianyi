package com.google.a.b.a;

import com.google.a.b.g;
import com.google.a.d.d;
import com.google.a.e;
import com.google.a.r;
import com.google.a.s;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class b
  implements s
{
  private final com.google.a.b.c a;

  public b(com.google.a.b.c paramc)
  {
    this.a = paramc;
  }

  public <T> r<T> a(e parame, com.google.a.c.a<T> parama)
  {
    Type localType1 = parama.b();
    Class localClass = parama.a();
    if (!Collection.class.isAssignableFrom(localClass))
      return null;
    Type localType2 = com.google.a.b.b.a(localType1, localClass);
    r localr = parame.a(com.google.a.c.a.a(localType2));
    g localg = this.a.a(parama);
    a locala = new a(parame, localType2, localr, localg);
    return locala;
  }

  private final class a<E> extends r<Collection<E>>
  {
    private final r<E> b;
    private final g<? extends Collection<E>> c;

    public a(Type paramr, r<E> paramg, g<? extends Collection<E>> arg4)
    {
      r localr;
      this.b = new k(paramr, localr, paramg);
      Object localObject;
      this.c = localObject;
    }

    public Collection<E> a(com.google.a.d.a parama)
    {
      if (parama.f() == com.google.a.d.c.i)
      {
        parama.j();
        return null;
      }
      Collection localCollection = (Collection)this.c.a();
      parama.a();
      while (parama.e())
        localCollection.add(this.b.b(parama));
      parama.b();
      return localCollection;
    }

    public void a(d paramd, Collection<E> paramCollection)
    {
      if (paramCollection == null)
      {
        paramd.f();
        return;
      }
      paramd.b();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        this.b.a(paramd, localObject);
      }
      paramd.c();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.b
 * JD-Core Version:    0.6.1
 */