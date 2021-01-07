package android.arch.lifecycle;

class FullLifecycleObserverAdapter
  implements GenericLifecycleObserver
{
  private final FullLifecycleObserver a;

  FullLifecycleObserverAdapter(FullLifecycleObserver paramFullLifecycleObserver)
  {
    this.a = paramFullLifecycleObserver;
  }

  public void a(e parame, c.a parama)
  {
    switch (1.a[parama.ordinal()])
    {
    default:
      break;
    case 7:
      throw new IllegalArgumentException("ON_ANY must not been send by anybody");
    case 6:
      this.a.f(parame);
      break;
    case 5:
      this.a.e(parame);
      break;
    case 4:
      this.a.d(parame);
      break;
    case 3:
      this.a.c(parame);
      break;
    case 2:
      this.a.b(parame);
      break;
    case 1:
      this.a.a(parame);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.FullLifecycleObserverAdapter
 * JD-Core Version:    0.6.1
 */