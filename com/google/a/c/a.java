package com.google.a.c;

import com.google.a.b.b;
import java.lang.reflect.Type;

public class a<T>
{
  final Class<? super T> b;
  final Type c;
  final int d;

  protected a()
  {
    this.c = a(getClass());
    this.b = b.e(this.c);
    this.d = this.c.hashCode();
  }

  a(Type paramType)
  {
    this.c = b.d((Type)com.google.a.b.a.a(paramType));
    this.b = b.e(this.c);
    this.d = this.c.hashCode();
  }

  public static a<?> a(Type paramType)
  {
    return new a(paramType);
  }

  static Type a(Class<?> paramClass)
  {
    Type localType = paramClass.getGenericSuperclass();
    if ((localType instanceof Class))
      throw new RuntimeException("Missing type parameter.");
    return b.d(((java.lang.reflect.ParameterizedType)localType).getActualTypeArguments()[0]);
  }

  public static <T> a<T> b(Class<T> paramClass)
  {
    return new a(paramClass);
  }

  public final Class<? super T> a()
  {
    return this.b;
  }

  public final Type b()
  {
    return this.c;
  }

  public final boolean equals(Object paramObject)
  {
    boolean bool;
    if (((paramObject instanceof a)) && (b.a(this.c, ((a)paramObject).c)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public final int hashCode()
  {
    return this.d;
  }

  public final String toString()
  {
    return b.f(this.c);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.c.a
 * JD-Core Version:    0.6.1
 */