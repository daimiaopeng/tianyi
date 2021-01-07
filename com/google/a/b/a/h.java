package com.google.a.b.a;

import com.google.a.b.g;
import com.google.a.e;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public final class h
  implements s
{
  private final com.google.a.b.c a;
  private final com.google.a.d b;
  private final com.google.a.b.d c;

  public h(com.google.a.b.c paramc, com.google.a.d paramd, com.google.a.b.d paramd1)
  {
    this.a = paramc;
    this.b = paramd;
    this.c = paramd1;
  }

  private b a(final e parame, final Field paramField, String paramString, final com.google.a.c.a<?> parama, boolean paramBoolean1, boolean paramBoolean2)
  {
    final boolean bool = com.google.a.b.h.a(parama.a());
    b local1 = new b(paramString, paramBoolean1, paramBoolean2)
    {
      final r<?> a = parame.a(parama);

      void a(com.google.a.d.a paramAnonymousa, Object paramAnonymousObject)
      {
        Object localObject = this.a.b(paramAnonymousa);
        if ((localObject != null) || (!bool))
          paramField.set(paramAnonymousObject, localObject);
      }

      void a(com.google.a.d.d paramAnonymousd, Object paramAnonymousObject)
      {
        Object localObject = paramField.get(paramAnonymousObject);
        new k(parame, this.a, parama.b()).a(paramAnonymousd, localObject);
      }
    };
    return local1;
  }

  private String a(Field paramField)
  {
    com.google.a.a.b localb = (com.google.a.a.b)paramField.getAnnotation(com.google.a.a.b.class);
    String str;
    if (localb == null)
      str = this.b.a(paramField);
    else
      str = localb.a();
    return str;
  }

  private Map<String, b> a(e parame, com.google.a.c.a<?> parama, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface())
      return localLinkedHashMap;
    Type localType1 = parama.b();
    Object localObject1 = parama;
    for (Object localObject2 = paramClass; localObject2 != Object.class; localObject2 = ((com.google.a.c.a)localObject1).a())
    {
      for (Field localField : ((Class)localObject2).getDeclaredFields())
      {
        boolean bool1 = a(localField, true);
        boolean bool2 = a(localField, false);
        if ((bool1) || (bool2))
        {
          localField.setAccessible(true);
          Type localType2 = com.google.a.b.b.a(((com.google.a.c.a)localObject1).b(), (Class)localObject2, localField.getGenericType());
          b localb1 = a(parame, localField, a(localField), com.google.a.c.a.a(localType2), bool1, bool2);
          b localb2 = (b)localLinkedHashMap.put(localb1.g, localb1);
          if (localb2 != null)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(localType1);
            localStringBuilder.append(" declares multiple JSON fields named ");
            localStringBuilder.append(localb2.g);
            throw new IllegalArgumentException(localStringBuilder.toString());
          }
        }
      }
      localObject1 = com.google.a.c.a.a(com.google.a.b.b.a(((com.google.a.c.a)localObject1).b(), (Class)localObject2, ((Class)localObject2).getGenericSuperclass()));
    }
    return localLinkedHashMap;
  }

  public <T> r<T> a(e parame, com.google.a.c.a<T> parama)
  {
    Class localClass = parama.a();
    if (!Object.class.isAssignableFrom(localClass))
      return null;
    return new a(this.a.a(parama), a(parame, parama, localClass), null);
  }

  public boolean a(Field paramField, boolean paramBoolean)
  {
    boolean bool;
    if ((!this.c.a(paramField.getType(), paramBoolean)) && (!this.c.a(paramField, paramBoolean)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public final class a<T> extends r<T>
  {
    private final g<T> b;
    private final Map<String, h.b> c;

    private a(Map<String, h.b> arg2)
    {
      Object localObject1;
      this.b = localObject1;
      Object localObject2;
      this.c = localObject2;
    }

    // ERROR //
    public void a(com.google.a.d.d paramd, T paramT)
    {
      // Byte code:
      //   0: aload_2
      //   1: ifnonnull +9 -> 10
      //   4: aload_1
      //   5: invokevirtual 36	com/google/a/d/d:f	()Lcom/google/a/d/d;
      //   8: pop
      //   9: return
      //   10: aload_1
      //   11: invokevirtual 39	com/google/a/d/d:d	()Lcom/google/a/d/d;
      //   14: pop
      //   15: aload_0
      //   16: getfield 24	com/google/a/b/a/h$a:c	Ljava/util/Map;
      //   19: invokeinterface 45 1 0
      //   24: invokeinterface 51 1 0
      //   29: astore 4
      //   31: aload 4
      //   33: invokeinterface 57 1 0
      //   38: ifeq +43 -> 81
      //   41: aload 4
      //   43: invokeinterface 61 1 0
      //   48: checkcast 63	com/google/a/b/a/h$b
      //   51: astore 6
      //   53: aload 6
      //   55: getfield 67	com/google/a/b/a/h$b:h	Z
      //   58: ifeq -27 -> 31
      //   61: aload_1
      //   62: aload 6
      //   64: getfield 71	com/google/a/b/a/h$b:g	Ljava/lang/String;
      //   67: invokevirtual 74	com/google/a/d/d:a	(Ljava/lang/String;)Lcom/google/a/d/d;
      //   70: pop
      //   71: aload 6
      //   73: aload_1
      //   74: aload_2
      //   75: invokevirtual 76	com/google/a/b/a/h$b:a	(Lcom/google/a/d/d;Ljava/lang/Object;)V
      //   78: goto -47 -> 31
      //   81: aload_1
      //   82: invokevirtual 79	com/google/a/d/d:e	()Lcom/google/a/d/d;
      //   85: pop
      //   86: return
      //   87: new 81	java/lang/AssertionError
      //   90: dup
      //   91: invokespecial 82	java/lang/AssertionError:<init>	()V
      //   94: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   15	78	87	java/lang/IllegalAccessException
    }

    public T b(com.google.a.d.a parama)
    {
      if (parama.f() == com.google.a.d.c.i)
      {
        parama.j();
        return null;
      }
      Object localObject = this.b.a();
      try
      {
        parama.c();
        while (parama.e())
        {
          String str = parama.g();
          h.b localb = (h.b)this.c.get(str);
          if ((localb != null) && (localb.i))
            localb.a(parama, localObject);
          else
            parama.n();
        }
        parama.d();
        return localObject;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
        throw new p(localIllegalStateException);
      }
    }
  }

  static abstract class b
  {
    final String g;
    final boolean h;
    final boolean i;

    protected b(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.g = paramString;
      this.h = paramBoolean1;
      this.i = paramBoolean2;
    }

    abstract void a(com.google.a.d.a parama, Object paramObject);

    abstract void a(com.google.a.d.d paramd, Object paramObject);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.h
 * JD-Core Version:    0.6.1
 */