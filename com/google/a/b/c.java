package com.google.a.b;

import com.google.a.c.a;
import com.google.a.f;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class c
{
  private final Map<Type, f<?>> a;

  public c()
  {
    this(Collections.emptyMap());
  }

  public c(Map<Type, f<?>> paramMap)
  {
    this.a = paramMap;
  }

  // ERROR //
  private <T> g<T> a(Class<? super T> paramClass)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: anewarray 27	java/lang/Class
    //   5: invokevirtual 31	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   8: astore_2
    //   9: aload_2
    //   10: invokevirtual 37	java/lang/reflect/Constructor:isAccessible	()Z
    //   13: ifne +8 -> 21
    //   16: aload_2
    //   17: iconst_1
    //   18: invokevirtual 41	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   21: new 43	com/google/a/b/c$2
    //   24: dup
    //   25: aload_0
    //   26: aload_2
    //   27: invokespecial 46	com/google/a/b/c$2:<init>	(Lcom/google/a/b/c;Ljava/lang/reflect/Constructor;)V
    //   30: astore_3
    //   31: aload_3
    //   32: areturn
    //   33: aconst_null
    //   34: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	31	33	java/lang/NoSuchMethodException
  }

  private <T> g<T> a(final Type paramType, final Class<? super T> paramClass)
  {
    return new g()
    {
      private final k d = k.a();

      public T a()
      {
        try
        {
          Object localObject = this.d.a(paramClass);
          return localObject;
        }
        catch (Exception localException)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unable to invoke no-args constructor for ");
          localStringBuilder.append(paramType);
          localStringBuilder.append(". ");
          localStringBuilder.append("Register an InstanceCreator with Gson for this type may fix this problem.");
          throw new RuntimeException(localStringBuilder.toString(), localException);
        }
      }
    };
  }

  private <T> g<T> b(Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass))
        return new g()
        {
          public T a()
          {
            return new TreeSet();
          }
        };
      if (Set.class.isAssignableFrom(paramClass))
        return new g()
        {
          public T a()
          {
            return new LinkedHashSet();
          }
        };
      if (Queue.class.isAssignableFrom(paramClass))
        return new g()
        {
          public T a()
          {
            return new LinkedList();
          }
        };
      return new g()
      {
        public T a()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
      return new g()
      {
        public T a()
        {
          return new LinkedHashMap();
        }
      };
    return null;
  }

  public <T> g<T> a(a<T> parama)
  {
    final Type localType = parama.b();
    Class localClass = parama.a();
    final f localf = (f)this.a.get(localType);
    if (localf != null)
      return new g()
      {
        public T a()
        {
          return localf.a(localType);
        }
      };
    g localg1 = a(localClass);
    if (localg1 != null)
      return localg1;
    g localg2 = b(localClass);
    if (localg2 != null)
      return localg2;
    return a(localType, localClass);
  }

  public String toString()
  {
    return this.a.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.c
 * JD-Core Version:    0.6.1
 */