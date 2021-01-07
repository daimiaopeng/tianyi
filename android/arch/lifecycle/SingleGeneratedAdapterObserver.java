package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver
  implements GenericLifecycleObserver
{
  private final b a;

  SingleGeneratedAdapterObserver(b paramb)
  {
    this.a = paramb;
  }

  public void a(e parame, c.a parama)
  {
    this.a.a(parame, parama, false, null);
    this.a.a(parame, parama, true, null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.SingleGeneratedAdapterObserver
 * JD-Core Version:    0.6.1
 */