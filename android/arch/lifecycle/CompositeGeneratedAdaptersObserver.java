package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver
  implements GenericLifecycleObserver
{
  private final b[] a;

  CompositeGeneratedAdaptersObserver(b[] paramArrayOfb)
  {
    this.a = paramArrayOfb;
  }

  public void a(e parame, c.a parama)
  {
    i locali = new i();
    b[] arrayOfb1 = this.a;
    int i = arrayOfb1.length;
    int j = 0;
    for (int k = 0; k < i; k++)
      arrayOfb1[k].a(parame, parama, false, locali);
    b[] arrayOfb2 = this.a;
    int m = arrayOfb2.length;
    while (j < m)
    {
      arrayOfb2[j].a(parame, parama, true, locali);
      j++;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.CompositeGeneratedAdaptersObserver
 * JD-Core Version:    0.6.1
 */