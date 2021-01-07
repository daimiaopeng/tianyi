package android.arch.lifecycle;

class ReflectiveGenericLifecycleObserver
  implements GenericLifecycleObserver
{
  private final Object a;
  private final a.a b;

  ReflectiveGenericLifecycleObserver(Object paramObject)
  {
    this.a = paramObject;
    this.b = a.a.b(this.a.getClass());
  }

  public void a(e parame, c.a parama)
  {
    this.b.a(parame, parama, this.a);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.ReflectiveGenericLifecycleObserver
 * JD-Core Version:    0.6.1
 */