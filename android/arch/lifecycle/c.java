package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public abstract class c
{
  @MainThread
  @NonNull
  public abstract b a();

  @MainThread
  public abstract void a(@NonNull d paramd);

  @MainThread
  public abstract void b(@NonNull d paramd);

  public static enum a
  {
    static
    {
      ON_RESUME = new a("ON_RESUME", 2);
      ON_PAUSE = new a("ON_PAUSE", 3);
      ON_STOP = new a("ON_STOP", 4);
      ON_DESTROY = new a("ON_DESTROY", 5);
      ON_ANY = new a("ON_ANY", 6);
      a[] arrayOfa = new a[7];
      arrayOfa[0] = ON_CREATE;
      arrayOfa[1] = ON_START;
      arrayOfa[2] = ON_RESUME;
      arrayOfa[3] = ON_PAUSE;
      arrayOfa[4] = ON_STOP;
      arrayOfa[5] = ON_DESTROY;
      arrayOfa[6] = ON_ANY;
    }
  }

  public static enum b
  {
    static
    {
      b[] arrayOfb = new b[5];
      arrayOfb[0] = a;
      arrayOfb[1] = b;
      arrayOfb[2] = c;
      arrayOfb[3] = d;
      arrayOfb[4] = e;
    }

    public boolean a(@NonNull b paramb)
    {
      boolean bool;
      if (compareTo(paramb) >= 0)
        bool = true;
      else
        bool = false;
      return bool;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.c
 * JD-Core Version:    0.6.1
 */