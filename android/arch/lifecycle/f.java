package android.arch.lifecycle;

import android.arch.a.b.a;
import android.arch.a.b.b.d;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class f extends c
{
  private a<d, a> a = new a();
  private c.b b;
  private final WeakReference<e> c;
  private int d = 0;
  private boolean e = false;
  private boolean f = false;
  private ArrayList<c.b> g = new ArrayList();

  public f(@NonNull e parame)
  {
    this.c = new WeakReference(parame);
    this.b = c.b.b;
  }

  static c.b a(@NonNull c.b paramb1, @Nullable c.b paramb2)
  {
    if ((paramb2 != null) && (paramb2.compareTo(paramb1) < 0))
      paramb1 = paramb2;
    return paramb1;
  }

  private void a(e parame)
  {
    b.d locald = this.a.c();
    while ((locald.hasNext()) && (!this.f))
    {
      Map.Entry localEntry = (Map.Entry)locald.next();
      a locala = (a)localEntry.getValue();
      while ((locala.a.compareTo(this.b) < 0) && (!this.f) && (this.a.c(localEntry.getKey())))
      {
        c(locala.a);
        locala.a(parame, e(locala.a));
        c();
      }
    }
  }

  static c.b b(c.a parama)
  {
    switch (1.a[parama.ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected event value ");
      localStringBuilder.append(parama);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 6:
      return c.b.a;
    case 5:
      return c.b.e;
    case 3:
    case 4:
      return c.b.d;
    case 1:
    case 2:
    }
    return c.b.c;
  }

  private void b(c.b paramb)
  {
    if (this.b == paramb)
      return;
    this.b = paramb;
    if ((!this.e) && (this.d == 0))
    {
      this.e = true;
      d();
      this.e = false;
      return;
    }
    this.f = true;
  }

  private void b(e parame)
  {
    Iterator localIterator = this.a.b();
    while ((localIterator.hasNext()) && (!this.f))
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      a locala = (a)localEntry.getValue();
      while ((locala.a.compareTo(this.b) > 0) && (!this.f) && (this.a.c(localEntry.getKey())))
      {
        c.a locala1 = d(locala.a);
        c(b(locala1));
        locala.a(parame, locala1);
        c();
      }
    }
  }

  private boolean b()
  {
    int i = this.a.a();
    boolean bool = true;
    if (i == 0)
      return bool;
    c.b localb1 = ((a)this.a.d().getValue()).a;
    c.b localb2 = ((a)this.a.e().getValue()).a;
    if ((localb1 != localb2) || (this.b != localb2))
      bool = false;
    return bool;
  }

  private c.b c(d paramd)
  {
    Map.Entry localEntry = this.a.d(paramd);
    c.b localb1;
    if (localEntry != null)
      localb1 = ((a)localEntry.getValue()).a;
    else
      localb1 = null;
    boolean bool = this.g.isEmpty();
    c.b localb2 = null;
    if (!bool)
      localb2 = (c.b)this.g.get(-1 + this.g.size());
    return a(a(this.b, localb1), localb2);
  }

  private void c()
  {
    this.g.remove(-1 + this.g.size());
  }

  private void c(c.b paramb)
  {
    this.g.add(paramb);
  }

  private static c.a d(c.b paramb)
  {
    switch (1.b[paramb.ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected state value ");
      localStringBuilder.append(paramb);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 5:
      throw new IllegalArgumentException();
    case 4:
      return c.a.ON_PAUSE;
    case 3:
      return c.a.ON_STOP;
    case 2:
      return c.a.ON_DESTROY;
    case 1:
    }
    throw new IllegalArgumentException();
  }

  private void d()
  {
    e locale = (e)this.c.get();
    if (locale == null)
    {
      Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
      return;
    }
    while (!b())
    {
      this.f = false;
      if (this.b.compareTo(((a)this.a.d().getValue()).a) < 0)
        b(locale);
      Map.Entry localEntry = this.a.e();
      if ((!this.f) && (localEntry != null) && (this.b.compareTo(((a)localEntry.getValue()).a) > 0))
        a(locale);
    }
    this.f = false;
  }

  private static c.a e(c.b paramb)
  {
    switch (1.b[paramb.ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected state value ");
      localStringBuilder.append(paramb);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 4:
      throw new IllegalArgumentException();
    case 3:
      return c.a.ON_RESUME;
    case 2:
      return c.a.ON_START;
    case 1:
    case 5:
    }
    return c.a.ON_CREATE;
  }

  @NonNull
  public c.b a()
  {
    return this.b;
  }

  public void a(@NonNull c.a parama)
  {
    b(b(parama));
  }

  @MainThread
  public void a(@NonNull c.b paramb)
  {
    b(paramb);
  }

  public void a(@NonNull d paramd)
  {
    c.b localb1;
    if (this.b == c.b.a)
      localb1 = c.b.a;
    else
      localb1 = c.b.b;
    a locala = new a(paramd, localb1);
    if ((a)this.a.a(paramd, locala) != null)
      return;
    e locale = (e)this.c.get();
    if (locale == null)
      return;
    int i;
    if ((this.d == 0) && (!this.e))
      i = 0;
    else
      i = 1;
    c.b localb2 = c(paramd);
    this.d = (1 + this.d);
    while ((locala.a.compareTo(localb2) < 0) && (this.a.c(paramd)))
    {
      c(locala.a);
      locala.a(locale, e(locala.a));
      c();
      localb2 = c(paramd);
    }
    if (i == 0)
      d();
    this.d -= 1;
  }

  public void b(@NonNull d paramd)
  {
    this.a.b(paramd);
  }

  static class a
  {
    c.b a;
    GenericLifecycleObserver b;

    a(d paramd, c.b paramb)
    {
      this.b = h.a(paramd);
      this.a = paramb;
    }

    void a(e parame, c.a parama)
    {
      c.b localb = f.b(parama);
      this.a = f.a(this.a, localb);
      this.b.a(parame, parama);
      this.a = localb;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.f
 * JD-Core Version:    0.6.1
 */