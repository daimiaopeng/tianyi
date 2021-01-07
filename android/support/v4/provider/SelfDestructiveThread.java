package android.support.v4.provider;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SelfDestructiveThread
{
  private static final int MSG_DESTRUCTION = 0;
  private static final int MSG_INVOKE_RUNNABLE = 1;
  private Handler.Callback mCallback = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return true;
      case 1:
        SelfDestructiveThread.this.onInvokeRunnable((Runnable)paramAnonymousMessage.obj);
        return true;
      case 0:
      }
      SelfDestructiveThread.this.onDestruction();
      return true;
    }
  };
  private final int mDestructAfterMillisec;

  @GuardedBy("mLock")
  private int mGeneration;

  @GuardedBy("mLock")
  private Handler mHandler;
  private final Object mLock = new Object();
  private final int mPriority;

  @GuardedBy("mLock")
  private HandlerThread mThread;
  private final String mThreadName;

  public SelfDestructiveThread(String paramString, int paramInt1, int paramInt2)
  {
    this.mThreadName = paramString;
    this.mPriority = paramInt1;
    this.mDestructAfterMillisec = paramInt2;
    this.mGeneration = 0;
  }

  private void post(Runnable paramRunnable)
  {
    synchronized (this.mLock)
    {
      if (this.mThread == null)
      {
        this.mThread = new HandlerThread(this.mThreadName, this.mPriority);
        this.mThread.start();
        this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
        this.mGeneration = (1 + this.mGeneration);
      }
      this.mHandler.removeMessages(0);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramRunnable));
      return;
    }
  }

  @VisibleForTesting
  public int getGeneration()
  {
    synchronized (this.mLock)
    {
      int i = this.mGeneration;
      return i;
    }
  }

  @VisibleForTesting
  public boolean isRunning()
  {
    while (true)
    {
      synchronized (this.mLock)
      {
        if (this.mThread != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }

  void onDestruction()
  {
    synchronized (this.mLock)
    {
      if (this.mHandler.hasMessages(1))
        return;
      this.mThread.quit();
      this.mThread = null;
      this.mHandler = null;
      return;
    }
  }

  void onInvokeRunnable(Runnable paramRunnable)
  {
    paramRunnable.run();
    synchronized (this.mLock)
    {
      this.mHandler.removeMessages(0);
      this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), this.mDestructAfterMillisec);
      return;
    }
  }

  public <T> void postAndReply(final Callable<T> paramCallable, final ReplyCallback<T> paramReplyCallback)
  {
    post(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 25	android/support/v4/provider/SelfDestructiveThread$2:val$callable	Ljava/util/concurrent/Callable;
        //   4: invokeinterface 41 1 0
        //   9: astore_1
        //   10: goto +5 -> 15
        //   13: aconst_null
        //   14: astore_1
        //   15: aload_0
        //   16: getfield 27	android/support/v4/provider/SelfDestructiveThread$2:val$callingHandler	Landroid/os/Handler;
        //   19: new 43	android/support/v4/provider/SelfDestructiveThread$2$1
        //   22: dup
        //   23: aload_0
        //   24: aload_1
        //   25: invokespecial 46	android/support/v4/provider/SelfDestructiveThread$2$1:<init>	(Landroid/support/v4/provider/SelfDestructiveThread$2;Ljava/lang/Object;)V
        //   28: invokevirtual 52	android/os/Handler:post	(Ljava/lang/Runnable;)Z
        //   31: pop
        //   32: return
        //
        // Exception table:
        //   from	to	target	type
        //   0	10	13	java/lang/Exception
      }
    });
  }

  public <T> T postAndWait(final Callable<T> paramCallable, int paramInt)
  {
    final ReentrantLock localReentrantLock = new ReentrantLock();
    final Condition localCondition = localReentrantLock.newCondition();
    final AtomicReference localAtomicReference = new AtomicReference();
    final AtomicBoolean localAtomicBoolean = new AtomicBoolean(true);
    Runnable local3 = new Runnable()
    {
      public void run()
      {
        try
        {
          localAtomicReference.set(paramCallable.call());
        }
        catch (Exception localException)
        {
        }
        localReentrantLock.lock();
        try
        {
          localAtomicBoolean.set(false);
          localCondition.signal();
          return;
        }
        finally
        {
          localReentrantLock.unlock();
        }
      }
    };
    post(local3);
    localReentrantLock.lock();
    try
    {
      if (!localAtomicBoolean.get())
      {
        Object localObject3 = localAtomicReference.get();
        return localObject3;
      }
      long l1 = TimeUnit.MILLISECONDS.toNanos(paramInt);
      do
      {
        try
        {
          long l2 = localCondition.awaitNanos(l1);
          l1 = l2;
        }
        catch (InterruptedException localInterruptedException)
        {
        }
        if (!localAtomicBoolean.get())
        {
          Object localObject2 = localAtomicReference.get();
          return localObject2;
        }
      }
      while (l1 > 0L);
      throw new InterruptedException("timeout");
    }
    finally
    {
      localReentrantLock.unlock();
    }
  }

  public static abstract interface ReplyCallback<T>
  {
    public abstract void onReply(T paramT);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.provider.SelfDestructiveThread
 * JD-Core Version:    0.6.1
 */