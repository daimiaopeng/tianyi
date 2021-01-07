package com.google.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class g extends i
  implements Iterable<i>
{
  private final List<i> a = new ArrayList();

  public Number a()
  {
    if (this.a.size() == 1)
      return ((i)this.a.get(0)).a();
    throw new IllegalStateException();
  }

  public void a(i parami)
  {
    if (parami == null)
      parami = k.a;
    this.a.add(parami);
  }

  public String b()
  {
    if (this.a.size() == 1)
      return ((i)this.a.get(0)).b();
    throw new IllegalStateException();
  }

  public double c()
  {
    if (this.a.size() == 1)
      return ((i)this.a.get(0)).c();
    throw new IllegalStateException();
  }

  public long d()
  {
    if (this.a.size() == 1)
      return ((i)this.a.get(0)).d();
    throw new IllegalStateException();
  }

  public int e()
  {
    if (this.a.size() == 1)
      return ((i)this.a.get(0)).e();
    throw new IllegalStateException();
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if ((paramObject != this) && ((!(paramObject instanceof g)) || (!((g)paramObject).a.equals(this.a))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean f()
  {
    if (this.a.size() == 1)
      return ((i)this.a.get(0)).f();
    throw new IllegalStateException();
  }

  public int hashCode()
  {
    return this.a.hashCode();
  }

  public Iterator<i> iterator()
  {
    return this.a.iterator();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.g
 * JD-Core Version:    0.6.1
 */