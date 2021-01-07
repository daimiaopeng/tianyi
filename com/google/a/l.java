package com.google.a;

import com.google.a.b.a;
import com.google.a.b.j;
import java.util.Map.Entry;
import java.util.Set;

public final class l extends i
{
  private final j<i> a = new j();

  public void a(String paramString, i parami)
  {
    if (parami == null)
      parami = k.a;
    this.a.a((String)a.a(paramString), parami);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if ((paramObject != this) && ((!(paramObject instanceof l)) || (!((l)paramObject).a.equals(this.a))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public int hashCode()
  {
    return this.a.hashCode();
  }

  public Set<Map.Entry<String, i>> o()
  {
    return this.a.entrySet();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.l
 * JD-Core Version:    0.6.1
 */