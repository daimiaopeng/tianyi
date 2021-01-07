package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class o
{
  private final a a;
  private final p b;

  public o(@NonNull p paramp, @NonNull a parama)
  {
    this.a = parama;
    this.b = paramp;
  }

  @MainThread
  @NonNull
  public <T extends n> T a(@NonNull Class<T> paramClass)
  {
    String str = paramClass.getCanonicalName();
    if (str == null)
      throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:");
    localStringBuilder.append(str);
    return a(localStringBuilder.toString(), paramClass);
  }

  @MainThread
  @NonNull
  public <T extends n> T a(@NonNull String paramString, @NonNull Class<T> paramClass)
  {
    n localn1 = this.b.a(paramString);
    if (paramClass.isInstance(localn1))
      return localn1;
    n localn2 = this.a.create(paramClass);
    this.b.a(paramString, localn2);
    return localn2;
  }

  public static abstract interface a
  {
    @NonNull
    public abstract <T extends n> T create(@NonNull Class<T> paramClass);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.o
 * JD-Core Version:    0.6.1
 */