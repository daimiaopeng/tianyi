package android.arch.a.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class b extends c
{
  private final Object a = new Object();
  private ExecutorService b = Executors.newFixedThreadPool(2);

  @Nullable
  private volatile Handler c;

  public void a(Runnable paramRunnable)
  {
    this.b.execute(paramRunnable);
  }

  public void b(Runnable paramRunnable)
  {
    if (this.c == null)
      synchronized (this.a)
      {
        if (this.c == null)
          this.c = new Handler(Looper.getMainLooper());
      }
    this.c.post(paramRunnable);
  }

  public boolean b()
  {
    boolean bool;
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.a.a.b
 * JD-Core Version:    0.6.1
 */