package android.arch.lifecycle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class p
{
  private final HashMap<String, n> a = new HashMap();

  final n a(String paramString)
  {
    return (n)this.a.get(paramString);
  }

  public final void a()
  {
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext())
      ((n)localIterator.next()).onCleared();
    this.a.clear();
  }

  final void a(String paramString, n paramn)
  {
    n localn = (n)this.a.put(paramString, paramn);
    if (localn != null)
      localn.onCleared();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.p
 * JD-Core Version:    0.6.1
 */