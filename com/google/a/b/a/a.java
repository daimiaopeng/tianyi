package com.google.a.b.a;

import com.google.a.b.b;
import com.google.a.d.c;
import com.google.a.d.d;
import com.google.a.e;
import com.google.a.r;
import com.google.a.s;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class a<E> extends r<Object>
{
  public static final s a = new s()
  {
    public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
    {
      Type localType1 = paramAnonymousa.b();
      if ((!(localType1 instanceof GenericArrayType)) && ((!(localType1 instanceof Class)) || (!((Class)localType1).isArray())))
        return null;
      Type localType2 = b.g(localType1);
      return new a(paramAnonymouse, paramAnonymouse.a(com.google.a.c.a.a(localType2)), b.e(localType2));
    }
  };
  private final Class<E> b;
  private final r<E> c;

  public a(e parame, r<E> paramr, Class<E> paramClass)
  {
    this.c = new k(parame, paramr, paramClass);
    this.b = paramClass;
  }

  public void a(d paramd, Object paramObject)
  {
    if (paramObject == null)
    {
      paramd.f();
      return;
    }
    paramd.b();
    int i = 0;
    int j = Array.getLength(paramObject);
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      this.c.a(paramd, localObject);
      i++;
    }
    paramd.c();
  }

  public Object b(com.google.a.d.a parama)
  {
    if (parama.f() == c.i)
    {
      parama.j();
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    parama.a();
    while (parama.e())
      localArrayList.add(this.c.b(parama));
    parama.b();
    Object localObject = Array.newInstance(this.b, localArrayList.size());
    for (int i = 0; i < localArrayList.size(); i++)
      Array.set(localObject, i, localArrayList.get(i));
    return localObject;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.a
 * JD-Core Version:    0.6.1
 */