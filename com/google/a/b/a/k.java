package com.google.a.b.a;

import com.google.a.d.d;
import com.google.a.e;
import com.google.a.r;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class k<T> extends r<T>
{
  private final e a;
  private final r<T> b;
  private final Type c;

  k(e parame, r<T> paramr, Type paramType)
  {
    this.a = parame;
    this.b = paramr;
    this.c = paramType;
  }

  private Type a(Type paramType, Object paramObject)
  {
    if ((paramObject != null) && ((paramType == Object.class) || ((paramType instanceof TypeVariable)) || ((paramType instanceof Class))))
      paramType = paramObject.getClass();
    return paramType;
  }

  public void a(d paramd, T paramT)
  {
    r localr = this.b;
    Type localType = a(this.c, paramT);
    if (localType != this.c)
    {
      localr = this.a.a(com.google.a.c.a.a(localType));
      if (((localr instanceof h.a)) && (!(this.b instanceof h.a)))
        localr = this.b;
    }
    localr.a(paramd, paramT);
  }

  public T b(com.google.a.d.a parama)
  {
    return this.b.b(parama);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.k
 * JD-Core Version:    0.6.1
 */