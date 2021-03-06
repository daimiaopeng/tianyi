package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Pools
{
  public static abstract interface Pool<T>
  {
    @Nullable
    public abstract T acquire();

    public abstract boolean release(@NonNull T paramT);
  }

  public static class SimplePool<T>
    implements Pools.Pool<T>
  {
    private final Object[] mPool;
    private int mPoolSize;

    public SimplePool(int paramInt)
    {
      if (paramInt <= 0)
        throw new IllegalArgumentException("The max pool size must be > 0");
      this.mPool = new Object[paramInt];
    }

    private boolean isInPool(@NonNull T paramT)
    {
      for (int i = 0; i < this.mPoolSize; i++)
        if (this.mPool[i] == paramT)
          return true;
      return false;
    }

    public T acquire()
    {
      if (this.mPoolSize > 0)
      {
        int i = -1 + this.mPoolSize;
        Object localObject = this.mPool[i];
        this.mPool[i] = null;
        this.mPoolSize = (-1 + this.mPoolSize);
        return localObject;
      }
      return null;
    }

    public boolean release(@NonNull T paramT)
    {
      if (isInPool(paramT))
        throw new IllegalStateException("Already in the pool!");
      if (this.mPoolSize < this.mPool.length)
      {
        this.mPool[this.mPoolSize] = paramT;
        this.mPoolSize = (1 + this.mPoolSize);
        return true;
      }
      return false;
    }
  }

  public static class SynchronizedPool<T> extends Pools.SimplePool<T>
  {
    private final Object mLock = new Object();

    public SynchronizedPool(int paramInt)
    {
      super();
    }

    public T acquire()
    {
      synchronized (this.mLock)
      {
        Object localObject3 = super.acquire();
        return localObject3;
      }
    }

    public boolean release(@NonNull T paramT)
    {
      synchronized (this.mLock)
      {
        boolean bool = super.release(paramT);
        return bool;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.Pools
 * JD-Core Version:    0.6.1
 */