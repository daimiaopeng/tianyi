package com.google.a.b;

import com.google.a.a.c;
import com.google.a.b;
import com.google.a.e;
import com.google.a.r;
import com.google.a.s;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class d
  implements s, Cloneable
{
  public static final d a = new d();
  private double b = -1.0D;
  private int c = 136;
  private boolean d = true;
  private boolean e;
  private List<com.google.a.a> f = Collections.emptyList();
  private List<com.google.a.a> g = Collections.emptyList();

  private boolean a(c paramc)
  {
    return (paramc == null) || (paramc.a() <= this.b);
  }

  private boolean a(c paramc, com.google.a.a.d paramd)
  {
    boolean bool;
    if ((a(paramc)) && (a(paramd)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean a(com.google.a.a.d paramd)
  {
    return (paramd == null) || (paramd.a() > this.b);
  }

  private boolean a(Class<?> paramClass)
  {
    boolean bool;
    if ((!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass())))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean b(Class<?> paramClass)
  {
    boolean bool;
    if ((paramClass.isMemberClass()) && (!c(paramClass)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean c(Class<?> paramClass)
  {
    boolean bool;
    if ((0x8 & paramClass.getModifiers()) != 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  // ERROR //
  protected d a()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 93	java/lang/Object:clone	()Ljava/lang/Object;
    //   4: checkcast 2	com/google/a/b/d
    //   7: astore_1
    //   8: aload_1
    //   9: areturn
    //   10: new 95	java/lang/AssertionError
    //   13: dup
    //   14: invokespecial 96	java/lang/AssertionError:<init>	()V
    //   17: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	10	java/lang/CloneNotSupportedException
  }

  public <T> r<T> a(final e parame, final com.google.a.c.a<T> parama)
  {
    Class localClass = parama.a();
    final boolean bool1 = a(localClass, true);
    final boolean bool2 = a(localClass, false);
    if ((!bool1) && (!bool2))
      return null;
    r local1 = new r()
    {
      private r<T> f;

      private r<T> a()
      {
        r localr = this.f;
        if (localr == null)
        {
          localr = parame.a(d.this, parama);
          this.f = localr;
        }
        return localr;
      }

      public void a(com.google.a.d.d paramAnonymousd, T paramAnonymousT)
      {
        if (bool1)
        {
          paramAnonymousd.f();
          return;
        }
        a().a(paramAnonymousd, paramAnonymousT);
      }

      public T b(com.google.a.d.a paramAnonymousa)
      {
        if (bool2)
        {
          paramAnonymousa.n();
          return null;
        }
        return a().b(paramAnonymousa);
      }
    };
    return local1;
  }

  public boolean a(Class<?> paramClass, boolean paramBoolean)
  {
    if ((this.b != -1.0D) && (!a((c)paramClass.getAnnotation(c.class), (com.google.a.a.d)paramClass.getAnnotation(com.google.a.a.d.class))))
      return true;
    if ((!this.d) && (b(paramClass)))
      return true;
    if (a(paramClass))
      return true;
    List localList;
    if (paramBoolean)
      localList = this.f;
    else
      localList = this.g;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
      if (((com.google.a.a)localIterator.next()).a(paramClass))
        return true;
    return false;
  }

  public boolean a(Field paramField, boolean paramBoolean)
  {
    if ((this.c & paramField.getModifiers()) != 0)
      return true;
    if ((this.b != -1.0D) && (!a((c)paramField.getAnnotation(c.class), (com.google.a.a.d)paramField.getAnnotation(com.google.a.a.d.class))))
      return true;
    if (paramField.isSynthetic())
      return true;
    if (this.e)
    {
      com.google.a.a.a locala = (com.google.a.a.a)paramField.getAnnotation(com.google.a.a.a.class);
      if ((locala == null) || (paramBoolean ? !locala.a() : !locala.b()))
        return true;
    }
    if ((!this.d) && (b(paramField.getType())))
      return true;
    if (a(paramField.getType()))
      return true;
    List localList;
    if (paramBoolean)
      localList = this.f;
    else
      localList = this.g;
    if (!localList.isEmpty())
    {
      b localb = new b(paramField);
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
        if (((com.google.a.a)localIterator.next()).a(localb))
          return true;
    }
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.d
 * JD-Core Version:    0.6.1
 */