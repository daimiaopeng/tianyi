package com.google.a;

import com.google.a.b.a.b;
import com.google.a.b.a.g;
import com.google.a.b.a.l;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class e
{
  final h a = new h()
  {
  };
  final o b = new o()
  {
  };
  private final ThreadLocal<Map<com.google.a.c.a<?>, a<?>>> c = new ThreadLocal()
  {
    protected Map<com.google.a.c.a<?>, e.a<?>> a()
    {
      return new HashMap();
    }
  };
  private final Map<com.google.a.c.a<?>, r<?>> d = Collections.synchronizedMap(new HashMap());
  private final List<s> e;
  private final com.google.a.b.c f;
  private final boolean g;
  private final boolean h;
  private final boolean i;
  private final boolean j;

  public e()
  {
    this(com.google.a.b.d.a, c.a, Collections.emptyMap(), false, false, false, true, false, false, q.a, Collections.emptyList());
  }

  e(com.google.a.b.d paramd, d paramd1, Map<Type, f<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, q paramq, List<s> paramList)
  {
    this.f = new com.google.a.b.c(paramMap);
    this.g = paramBoolean1;
    this.i = paramBoolean3;
    this.h = paramBoolean4;
    this.j = paramBoolean5;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(l.x);
    localArrayList.add(l.m);
    localArrayList.add(l.g);
    localArrayList.add(l.i);
    localArrayList.add(l.k);
    localArrayList.add(l.a(Long.TYPE, Long.class, a(paramq)));
    localArrayList.add(l.a(Double.TYPE, Double.class, a(paramBoolean6)));
    localArrayList.add(l.a(Float.TYPE, Float.class, b(paramBoolean6)));
    localArrayList.add(paramd);
    localArrayList.add(l.r);
    localArrayList.add(l.t);
    localArrayList.add(l.z);
    localArrayList.add(l.B);
    localArrayList.add(l.Q);
    localArrayList.add(g.a);
    localArrayList.addAll(paramList);
    localArrayList.add(l.a(BigDecimal.class, l.v));
    localArrayList.add(l.a(BigInteger.class, l.w));
    localArrayList.add(new b(this.f));
    localArrayList.add(l.D);
    localArrayList.add(l.F);
    localArrayList.add(l.J);
    localArrayList.add(l.O);
    localArrayList.add(l.H);
    localArrayList.add(l.d);
    localArrayList.add(com.google.a.b.a.c.a);
    localArrayList.add(l.M);
    localArrayList.add(com.google.a.b.a.j.a);
    localArrayList.add(com.google.a.b.a.i.a);
    localArrayList.add(l.K);
    localArrayList.add(new com.google.a.b.a.f(this.f, paramBoolean2));
    localArrayList.add(com.google.a.b.a.a.a);
    localArrayList.add(l.R);
    localArrayList.add(l.b);
    localArrayList.add(new com.google.a.b.a.h(this.f, paramd1, paramd));
    this.e = Collections.unmodifiableList(localArrayList);
  }

  private com.google.a.d.d a(Writer paramWriter)
  {
    if (this.i)
      paramWriter.write(")]}'\n");
    com.google.a.d.d locald = new com.google.a.d.d(paramWriter);
    if (this.j)
      locald.c("  ");
    locald.d(this.g);
    return locald;
  }

  private r<Number> a(q paramq)
  {
    if (paramq == q.a)
      return l.n;
    return new r()
    {
      public Number a(com.google.a.d.a paramAnonymousa)
      {
        if (paramAnonymousa.f() == com.google.a.d.c.i)
        {
          paramAnonymousa.j();
          return null;
        }
        return Long.valueOf(paramAnonymousa.l());
      }

      public void a(com.google.a.d.d paramAnonymousd, Number paramAnonymousNumber)
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousd.f();
          return;
        }
        paramAnonymousd.b(paramAnonymousNumber.toString());
      }
    };
  }

  private r<Number> a(boolean paramBoolean)
  {
    if (paramBoolean)
      return l.p;
    return new r()
    {
      public Double a(com.google.a.d.a paramAnonymousa)
      {
        if (paramAnonymousa.f() == com.google.a.d.c.i)
        {
          paramAnonymousa.j();
          return null;
        }
        return Double.valueOf(paramAnonymousa.k());
      }

      public void a(com.google.a.d.d paramAnonymousd, Number paramAnonymousNumber)
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousd.f();
          return;
        }
        double d = paramAnonymousNumber.doubleValue();
        e.a(e.this, d);
        paramAnonymousd.a(paramAnonymousNumber);
      }
    };
  }

  private void a(double paramDouble)
  {
    if ((!Double.isNaN(paramDouble)) && (!Double.isInfinite(paramDouble)))
      return;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramDouble);
    localStringBuilder.append(" is not a valid double value as per JSON specification. To override this");
    localStringBuilder.append(" behavior, use GsonBuilder.serializeSpecialDoubleValues() method.");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  private static void a(Object paramObject, com.google.a.d.a parama)
  {
    if (paramObject != null)
      try
      {
        if (parama.f() != com.google.a.d.c.j)
          throw new j("JSON document was not fully consumed.");
      }
      catch (IOException localIOException)
      {
        throw new j(localIOException);
      }
      catch (com.google.a.d.e locale)
      {
        throw new p(locale);
      }
  }

  private r<Number> b(boolean paramBoolean)
  {
    if (paramBoolean)
      return l.o;
    return new r()
    {
      public Float a(com.google.a.d.a paramAnonymousa)
      {
        if (paramAnonymousa.f() == com.google.a.d.c.i)
        {
          paramAnonymousa.j();
          return null;
        }
        return Float.valueOf((float)paramAnonymousa.k());
      }

      public void a(com.google.a.d.d paramAnonymousd, Number paramAnonymousNumber)
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousd.f();
          return;
        }
        float f = paramAnonymousNumber.floatValue();
        e.a(e.this, f);
        paramAnonymousd.a(paramAnonymousNumber);
      }
    };
  }

  public <T> r<T> a(com.google.a.c.a<T> parama)
  {
    r localr1 = (r)this.d.get(parama);
    if (localr1 != null)
      return localr1;
    Map localMap = (Map)this.c.get();
    a locala1 = (a)localMap.get(parama);
    if (locala1 != null)
      return locala1;
    a locala2 = new a();
    localMap.put(parama, locala2);
    try
    {
      Iterator localIterator = this.e.iterator();
      while (localIterator.hasNext())
      {
        r localr2 = ((s)localIterator.next()).a(this, parama);
        if (localr2 != null)
        {
          locala2.a(localr2);
          this.d.put(parama, localr2);
          return localr2;
        }
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("GSON cannot handle ");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    finally
    {
      localMap.remove(parama);
    }
  }

  public <T> r<T> a(s params, com.google.a.c.a<T> parama)
  {
    Iterator localIterator = this.e.iterator();
    int k = 0;
    while (localIterator.hasNext())
    {
      s locals = (s)localIterator.next();
      if (k == 0)
      {
        if (locals == params)
          k = 1;
      }
      else
      {
        r localr = locals.a(this, parama);
        if (localr != null)
          return localr;
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GSON cannot serialize ");
    localStringBuilder.append(parama);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public <T> r<T> a(Class<T> paramClass)
  {
    return a(com.google.a.c.a.b(paramClass));
  }

  // ERROR //
  public <T> T a(com.google.a.d.a parama, Type paramType)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 409	com/google/a/d/a:p	()Z
    //   4: istore_3
    //   5: iconst_1
    //   6: istore 4
    //   8: aload_1
    //   9: iload 4
    //   11: invokevirtual 411	com/google/a/d/a:a	(Z)V
    //   14: aload_1
    //   15: invokevirtual 319	com/google/a/d/a:f	()Lcom/google/a/d/c;
    //   18: pop
    //   19: iconst_0
    //   20: istore 4
    //   22: aload_0
    //   23: aload_2
    //   24: invokestatic 414	com/google/a/c/a:a	(Ljava/lang/reflect/Type;)Lcom/google/a/c/a;
    //   27: invokevirtual 402	com/google/a/e:a	(Lcom/google/a/c/a;)Lcom/google/a/r;
    //   30: aload_1
    //   31: invokevirtual 417	com/google/a/r:b	(Lcom/google/a/d/a;)Ljava/lang/Object;
    //   34: astore 10
    //   36: aload_1
    //   37: iload_3
    //   38: invokevirtual 411	com/google/a/d/a:a	(Z)V
    //   41: aload 10
    //   43: areturn
    //   44: astore 6
    //   46: goto +51 -> 97
    //   49: astore 8
    //   51: new 334	com/google/a/p
    //   54: dup
    //   55: aload 8
    //   57: invokespecial 335	com/google/a/p:<init>	(Ljava/lang/Throwable;)V
    //   60: athrow
    //   61: astore 7
    //   63: new 334	com/google/a/p
    //   66: dup
    //   67: aload 7
    //   69: invokespecial 335	com/google/a/p:<init>	(Ljava/lang/Throwable;)V
    //   72: athrow
    //   73: astore 5
    //   75: iload 4
    //   77: ifeq +10 -> 87
    //   80: aload_1
    //   81: iload_3
    //   82: invokevirtual 411	com/google/a/d/a:a	(Z)V
    //   85: aconst_null
    //   86: areturn
    //   87: new 334	com/google/a/p
    //   90: dup
    //   91: aload 5
    //   93: invokespecial 335	com/google/a/p:<init>	(Ljava/lang/Throwable;)V
    //   96: athrow
    //   97: aload_1
    //   98: iload_3
    //   99: invokevirtual 411	com/google/a/d/a:a	(Z)V
    //   102: aload 6
    //   104: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	36	44	finally
    //   51	73	44	finally
    //   87	97	44	finally
    //   14	36	49	java/io/IOException
    //   14	36	61	java/lang/IllegalStateException
    //   14	36	73	java/io/EOFException
  }

  public <T> T a(Reader paramReader, Type paramType)
  {
    com.google.a.d.a locala = new com.google.a.d.a(paramReader);
    Object localObject = a(locala, paramType);
    a(localObject, locala);
    return localObject;
  }

  public <T> T a(String paramString, Type paramType)
  {
    if (paramString == null)
      return null;
    return a(new StringReader(paramString), paramType);
  }

  public String a(i parami)
  {
    StringWriter localStringWriter = new StringWriter();
    a(parami, localStringWriter);
    return localStringWriter.toString();
  }

  public String a(Object paramObject)
  {
    if (paramObject == null)
      return a(k.a);
    return a(paramObject, paramObject.getClass());
  }

  public String a(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    a(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }

  // ERROR //
  public void a(i parami, com.google.a.d.d paramd)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 460	com/google/a/d/d:g	()Z
    //   4: istore_3
    //   5: aload_2
    //   6: iconst_1
    //   7: invokevirtual 462	com/google/a/d/d:b	(Z)V
    //   10: aload_2
    //   11: invokevirtual 464	com/google/a/d/d:h	()Z
    //   14: istore 4
    //   16: aload_2
    //   17: aload_0
    //   18: getfield 95	com/google/a/e:h	Z
    //   21: invokevirtual 466	com/google/a/d/d:c	(Z)V
    //   24: aload_2
    //   25: invokevirtual 468	com/google/a/d/d:i	()Z
    //   28: istore 5
    //   30: aload_2
    //   31: aload_0
    //   32: getfield 91	com/google/a/e:g	Z
    //   35: invokevirtual 264	com/google/a/d/d:d	(Z)V
    //   38: aload_1
    //   39: aload_2
    //   40: invokestatic 472	com/google/a/b/i:a	(Lcom/google/a/i;Lcom/google/a/d/d;)V
    //   43: aload_2
    //   44: iload_3
    //   45: invokevirtual 462	com/google/a/d/d:b	(Z)V
    //   48: aload_2
    //   49: iload 4
    //   51: invokevirtual 466	com/google/a/d/d:c	(Z)V
    //   54: aload_2
    //   55: iload 5
    //   57: invokevirtual 264	com/google/a/d/d:d	(Z)V
    //   60: return
    //   61: astore 7
    //   63: goto +15 -> 78
    //   66: astore 6
    //   68: new 326	com/google/a/j
    //   71: dup
    //   72: aload 6
    //   74: invokespecial 332	com/google/a/j:<init>	(Ljava/lang/Throwable;)V
    //   77: athrow
    //   78: aload_2
    //   79: iload_3
    //   80: invokevirtual 462	com/google/a/d/d:b	(Z)V
    //   83: aload_2
    //   84: iload 4
    //   86: invokevirtual 466	com/google/a/d/d:c	(Z)V
    //   89: aload_2
    //   90: iload 5
    //   92: invokevirtual 264	com/google/a/d/d:d	(Z)V
    //   95: aload 7
    //   97: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   38	43	61	finally
    //   68	78	61	finally
    //   38	43	66	java/io/IOException
  }

  public void a(i parami, Appendable paramAppendable)
  {
    try
    {
      a(parami, a(com.google.a.b.i.a(paramAppendable)));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }

  // ERROR //
  public void a(Object paramObject, Type paramType, com.google.a.d.d paramd)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_2
    //   2: invokestatic 414	com/google/a/c/a:a	(Ljava/lang/reflect/Type;)Lcom/google/a/c/a;
    //   5: invokevirtual 402	com/google/a/e:a	(Lcom/google/a/c/a;)Lcom/google/a/r;
    //   8: astore 4
    //   10: aload_3
    //   11: invokevirtual 460	com/google/a/d/d:g	()Z
    //   14: istore 5
    //   16: aload_3
    //   17: iconst_1
    //   18: invokevirtual 462	com/google/a/d/d:b	(Z)V
    //   21: aload_3
    //   22: invokevirtual 464	com/google/a/d/d:h	()Z
    //   25: istore 6
    //   27: aload_3
    //   28: aload_0
    //   29: getfield 95	com/google/a/e:h	Z
    //   32: invokevirtual 466	com/google/a/d/d:c	(Z)V
    //   35: aload_3
    //   36: invokevirtual 468	com/google/a/d/d:i	()Z
    //   39: istore 7
    //   41: aload_3
    //   42: aload_0
    //   43: getfield 91	com/google/a/e:g	Z
    //   46: invokevirtual 264	com/google/a/d/d:d	(Z)V
    //   49: aload 4
    //   51: aload_3
    //   52: aload_1
    //   53: invokevirtual 485	com/google/a/r:a	(Lcom/google/a/d/d;Ljava/lang/Object;)V
    //   56: aload_3
    //   57: iload 5
    //   59: invokevirtual 462	com/google/a/d/d:b	(Z)V
    //   62: aload_3
    //   63: iload 6
    //   65: invokevirtual 466	com/google/a/d/d:c	(Z)V
    //   68: aload_3
    //   69: iload 7
    //   71: invokevirtual 264	com/google/a/d/d:d	(Z)V
    //   74: return
    //   75: astore 9
    //   77: goto +15 -> 92
    //   80: astore 8
    //   82: new 326	com/google/a/j
    //   85: dup
    //   86: aload 8
    //   88: invokespecial 332	com/google/a/j:<init>	(Ljava/lang/Throwable;)V
    //   91: athrow
    //   92: aload_3
    //   93: iload 5
    //   95: invokevirtual 462	com/google/a/d/d:b	(Z)V
    //   98: aload_3
    //   99: iload 6
    //   101: invokevirtual 466	com/google/a/d/d:c	(Z)V
    //   104: aload_3
    //   105: iload 7
    //   107: invokevirtual 264	com/google/a/d/d:d	(Z)V
    //   110: aload 9
    //   112: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   49	56	75	finally
    //   82	92	75	finally
    //   49	56	80	java/io/IOException
  }

  public void a(Object paramObject, Type paramType, Appendable paramAppendable)
  {
    try
    {
      a(paramObject, paramType, a(com.google.a.b.i.a(paramAppendable)));
      return;
    }
    catch (IOException localIOException)
    {
      throw new j(localIOException);
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("{");
    localStringBuilder.append("serializeNulls:");
    localStringBuilder.append(this.g);
    localStringBuilder.append("factories:");
    localStringBuilder.append(this.e);
    localStringBuilder.append(",instanceCreators:");
    localStringBuilder.append(this.f);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  static class a<T> extends r<T>
  {
    private r<T> a;

    public void a(com.google.a.d.d paramd, T paramT)
    {
      if (this.a == null)
        throw new IllegalStateException();
      this.a.a(paramd, paramT);
    }

    public void a(r<T> paramr)
    {
      if (this.a != null)
        throw new AssertionError();
      this.a = paramr;
    }

    public T b(com.google.a.d.a parama)
    {
      if (this.a == null)
        throw new IllegalStateException();
      return this.a.b(parama);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.e
 * JD-Core Version:    0.6.1
 */