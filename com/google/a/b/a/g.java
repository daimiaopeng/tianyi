package com.google.a.b.a;

import com.google.a.b.j;
import com.google.a.d.c;
import com.google.a.d.d;
import com.google.a.e;
import com.google.a.r;
import com.google.a.s;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class g extends r<Object>
{
  public static final s a = new s()
  {
    public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
    {
      if (paramAnonymousa.a() == Object.class)
        return new g(paramAnonymouse, null);
      return null;
    }
  };
  private final e b;

  private g(e parame)
  {
    this.b = parame;
  }

  public void a(d paramd, Object paramObject)
  {
    if (paramObject == null)
    {
      paramd.f();
      return;
    }
    r localr = this.b.a(paramObject.getClass());
    if ((localr instanceof g))
    {
      paramd.d();
      paramd.e();
      return;
    }
    localr.a(paramd, paramObject);
  }

  public Object b(com.google.a.d.a parama)
  {
    c localc = parama.f();
    switch (2.a[localc.ordinal()])
    {
    default:
      throw new IllegalStateException();
    case 6:
      parama.j();
      return null;
    case 5:
      return Boolean.valueOf(parama.i());
    case 4:
      return Double.valueOf(parama.k());
    case 3:
      return parama.h();
    case 2:
      j localj = new j();
      parama.c();
      while (parama.e())
        localj.put(parama.g(), b(parama));
      parama.d();
      return localj;
    case 1:
    }
    ArrayList localArrayList = new ArrayList();
    parama.a();
    while (parama.e())
      localArrayList.add(b(parama));
    parama.b();
    return localArrayList;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.g
 * JD-Core Version:    0.6.1
 */