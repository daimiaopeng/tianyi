package android.arch.a.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class a extends c
{
  private static volatile a a;

  @NonNull
  private static final Executor d = new Executor()
  {
    public void execute(Runnable paramAnonymousRunnable)
    {
      a.a().b(paramAnonymousRunnable);
    }
  };

  @NonNull
  private static final Executor e = new Executor()
  {
    public void execute(Runnable paramAnonymousRunnable)
    {
      a.a().a(paramAnonymousRunnable);
    }
  };

  @NonNull
  private c b = this.c;

  @NonNull
  private c c = new b();

  @NonNull
  public static a a()
  {
    if (a != null)
      return a;
    try
    {
      if (a == null)
        a = new a();
      return a;
    }
    finally
    {
    }
  }

  public void a(Runnable paramRunnable)
  {
    this.b.a(paramRunnable);
  }

  public void b(Runnable paramRunnable)
  {
    this.b.b(paramRunnable);
  }

  public boolean b()
  {
    return this.b.b();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.a.a.a
 * JD-Core Version:    0.6.1
 */