package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RestrictTo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class ModernAsyncTask<Params, Progress, Result>
{
  private static final int CORE_POOL_SIZE = 5;
  private static final int KEEP_ALIVE = 1;
  private static final String LOG_TAG = "AsyncTask";
  private static final int MAXIMUM_POOL_SIZE = 128;
  private static final int MESSAGE_POST_PROGRESS = 2;
  private static final int MESSAGE_POST_RESULT = 1;
  public static final Executor THREAD_POOL_EXECUTOR = localThreadPoolExecutor;
  private static volatile Executor sDefaultExecutor = THREAD_POOL_EXECUTOR;
  private static InternalHandler sHandler;
  private static final BlockingQueue<Runnable> sPoolWorkQueue;
  private static final ThreadFactory sThreadFactory = new ThreadFactory()
  {
    private final AtomicInteger mCount = new AtomicInteger(1);

    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ModernAsyncTask #");
      localStringBuilder.append(this.mCount.getAndIncrement());
      return new Thread(paramAnonymousRunnable, localStringBuilder.toString());
    }
  };
  final AtomicBoolean mCancelled = new AtomicBoolean();
  private final FutureTask<Result> mFuture = new FutureTask(this.mWorker)
  {
    // ERROR //
    protected void done()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 31	android/support/v4/content/ModernAsyncTask$3:get	()Ljava/lang/Object;
      //   4: astore 5
      //   6: aload_0
      //   7: getfield 15	android/support/v4/content/ModernAsyncTask$3:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   10: aload 5
      //   12: invokevirtual 35	android/support/v4/content/ModernAsyncTask:postResultIfNotInvoked	(Ljava/lang/Object;)V
      //   15: goto +51 -> 66
      //   18: astore 4
      //   20: new 37	java/lang/RuntimeException
      //   23: dup
      //   24: ldc 39
      //   26: aload 4
      //   28: invokespecial 42	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
      //   31: athrow
      //   32: aload_0
      //   33: getfield 15	android/support/v4/content/ModernAsyncTask$3:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   36: aconst_null
      //   37: invokevirtual 35	android/support/v4/content/ModernAsyncTask:postResultIfNotInvoked	(Ljava/lang/Object;)V
      //   40: goto +26 -> 66
      //   43: astore_3
      //   44: new 37	java/lang/RuntimeException
      //   47: dup
      //   48: ldc 39
      //   50: aload_3
      //   51: invokevirtual 46	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
      //   54: invokespecial 42	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
      //   57: athrow
      //   58: astore_1
      //   59: ldc 48
      //   61: aload_1
      //   62: invokestatic 54	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
      //   65: pop
      //   66: return
      //
      // Exception table:
      //   from	to	target	type
      //   0	15	18	java/lang/Throwable
      //   0	15	32	java/util/concurrent/CancellationException
      //   0	15	43	java/util/concurrent/ExecutionException
      //   0	15	58	java/lang/InterruptedException
    }
  };
  private volatile Status mStatus = Status.PENDING;
  final AtomicBoolean mTaskInvoked = new AtomicBoolean();
  private final WorkerRunnable<Params, Result> mWorker = new WorkerRunnable()
  {
    // ERROR //
    public Result call()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	android/support/v4/content/ModernAsyncTask$2:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   4: getfield 24	android/support/v4/content/ModernAsyncTask:mTaskInvoked	Ljava/util/concurrent/atomic/AtomicBoolean;
      //   7: iconst_1
      //   8: invokevirtual 30	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
      //   11: aconst_null
      //   12: astore_1
      //   13: bipush 10
      //   15: invokestatic 36	android/os/Process:setThreadPriority	(I)V
      //   18: aload_0
      //   19: getfield 15	android/support/v4/content/ModernAsyncTask$2:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   22: aload_0
      //   23: getfield 40	android/support/v4/content/ModernAsyncTask$2:mParams	[Ljava/lang/Object;
      //   26: invokevirtual 44	android/support/v4/content/ModernAsyncTask:doInBackground	([Ljava/lang/Object;)Ljava/lang/Object;
      //   29: astore 5
      //   31: invokestatic 49	android/os/Binder:flushPendingCommands	()V
      //   34: aload_0
      //   35: getfield 15	android/support/v4/content/ModernAsyncTask$2:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   38: aload 5
      //   40: invokevirtual 53	android/support/v4/content/ModernAsyncTask:postResult	(Ljava/lang/Object;)Ljava/lang/Object;
      //   43: pop
      //   44: aload 5
      //   46: areturn
      //   47: astore_3
      //   48: aload 5
      //   50: astore_1
      //   51: goto +32 -> 83
      //   54: astore 6
      //   56: aload 5
      //   58: astore_1
      //   59: aload 6
      //   61: astore_2
      //   62: goto +8 -> 70
      //   65: astore_3
      //   66: goto +17 -> 83
      //   69: astore_2
      //   70: aload_0
      //   71: getfield 15	android/support/v4/content/ModernAsyncTask$2:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   74: getfield 56	android/support/v4/content/ModernAsyncTask:mCancelled	Ljava/util/concurrent/atomic/AtomicBoolean;
      //   77: iconst_1
      //   78: invokevirtual 30	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
      //   81: aload_2
      //   82: athrow
      //   83: aload_0
      //   84: getfield 15	android/support/v4/content/ModernAsyncTask$2:this$0	Landroid/support/v4/content/ModernAsyncTask;
      //   87: aload_1
      //   88: invokevirtual 53	android/support/v4/content/ModernAsyncTask:postResult	(Ljava/lang/Object;)Ljava/lang/Object;
      //   91: pop
      //   92: aload_3
      //   93: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   31	34	47	finally
      //   31	34	54	java/lang/Throwable
      //   13	31	65	finally
      //   70	83	65	finally
      //   13	31	69	java/lang/Throwable
    }
  };

  static
  {
    sPoolWorkQueue = new LinkedBlockingQueue(10);
    ThreadPoolExecutor localThreadPoolExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
  }

  public static void execute(Runnable paramRunnable)
  {
    sDefaultExecutor.execute(paramRunnable);
  }

  private static Handler getHandler()
  {
    try
    {
      if (sHandler == null)
        sHandler = new InternalHandler();
      InternalHandler localInternalHandler = sHandler;
      return localInternalHandler;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static void setDefaultExecutor(Executor paramExecutor)
  {
    sDefaultExecutor = paramExecutor;
  }

  public final boolean cancel(boolean paramBoolean)
  {
    this.mCancelled.set(true);
    return this.mFuture.cancel(paramBoolean);
  }

  protected abstract Result doInBackground(Params[] paramArrayOfParams);

  public final ModernAsyncTask<Params, Progress, Result> execute(Params[] paramArrayOfParams)
  {
    return executeOnExecutor(sDefaultExecutor, paramArrayOfParams);
  }

  public final ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor paramExecutor, Params[] paramArrayOfParams)
  {
    if (this.mStatus != Status.PENDING)
    {
      switch (4.$SwitchMap$androidx$loader$content$ModernAsyncTask$Status[this.mStatus.ordinal()])
      {
      default:
        throw new IllegalStateException("We should never reach this state");
      case 2:
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
      case 1:
      }
      throw new IllegalStateException("Cannot execute task: the task is already running.");
    }
    this.mStatus = Status.RUNNING;
    onPreExecute();
    this.mWorker.mParams = paramArrayOfParams;
    paramExecutor.execute(this.mFuture);
    return this;
  }

  void finish(Result paramResult)
  {
    if (isCancelled())
      onCancelled(paramResult);
    else
      onPostExecute(paramResult);
    this.mStatus = Status.FINISHED;
  }

  public final Result get()
  {
    return this.mFuture.get();
  }

  public final Result get(long paramLong, TimeUnit paramTimeUnit)
  {
    return this.mFuture.get(paramLong, paramTimeUnit);
  }

  public final Status getStatus()
  {
    return this.mStatus;
  }

  public final boolean isCancelled()
  {
    return this.mCancelled.get();
  }

  protected void onCancelled()
  {
  }

  protected void onCancelled(Result paramResult)
  {
    onCancelled();
  }

  protected void onPostExecute(Result paramResult)
  {
  }

  protected void onPreExecute()
  {
  }

  protected void onProgressUpdate(Progress[] paramArrayOfProgress)
  {
  }

  Result postResult(Result paramResult)
  {
    getHandler().obtainMessage(1, new AsyncTaskResult(this, new Object[] { paramResult })).sendToTarget();
    return paramResult;
  }

  void postResultIfNotInvoked(Result paramResult)
  {
    if (!this.mTaskInvoked.get())
      postResult(paramResult);
  }

  protected final void publishProgress(Progress[] paramArrayOfProgress)
  {
    if (!isCancelled())
      getHandler().obtainMessage(2, new AsyncTaskResult(this, paramArrayOfProgress)).sendToTarget();
  }

  private static class AsyncTaskResult<Data>
  {
    final Data[] mData;
    final ModernAsyncTask mTask;

    AsyncTaskResult(ModernAsyncTask paramModernAsyncTask, Data[] paramArrayOfData)
    {
      this.mTask = paramModernAsyncTask;
      this.mData = paramArrayOfData;
    }
  }

  private static class InternalHandler extends Handler
  {
    InternalHandler()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      ModernAsyncTask.AsyncTaskResult localAsyncTaskResult = (ModernAsyncTask.AsyncTaskResult)paramMessage.obj;
      switch (paramMessage.what)
      {
      default:
        break;
      case 2:
        localAsyncTaskResult.mTask.onProgressUpdate(localAsyncTaskResult.mData);
        break;
      case 1:
        localAsyncTaskResult.mTask.finish(localAsyncTaskResult.mData[0]);
      }
    }
  }

  public static enum Status
  {
    static
    {
      FINISHED = new Status("FINISHED", 2);
      Status[] arrayOfStatus = new Status[3];
      arrayOfStatus[0] = PENDING;
      arrayOfStatus[1] = RUNNING;
      arrayOfStatus[2] = FINISHED;
    }
  }

  private static abstract class WorkerRunnable<Params, Result>
    implements Callable<Result>
  {
    Params[] mParams;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.ModernAsyncTask
 * JD-Core Version:    0.6.1
 */