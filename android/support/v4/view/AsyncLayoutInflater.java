package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater
{
  private static final String TAG = "AsyncLayoutInflater";
  Handler mHandler;
  private Handler.Callback mHandlerCallback = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      AsyncLayoutInflater.InflateRequest localInflateRequest = (AsyncLayoutInflater.InflateRequest)paramAnonymousMessage.obj;
      if (localInflateRequest.view == null)
        localInflateRequest.view = AsyncLayoutInflater.this.mInflater.inflate(localInflateRequest.resid, localInflateRequest.parent, false);
      localInflateRequest.callback.onInflateFinished(localInflateRequest.view, localInflateRequest.resid, localInflateRequest.parent);
      AsyncLayoutInflater.this.mInflateThread.releaseRequest(localInflateRequest);
      return true;
    }
  };
  InflateThread mInflateThread;
  LayoutInflater mInflater;

  public AsyncLayoutInflater(@NonNull Context paramContext)
  {
    this.mInflater = new BasicInflater(paramContext);
    this.mHandler = new Handler(this.mHandlerCallback);
    this.mInflateThread = InflateThread.getInstance();
  }

  @UiThread
  public void inflate(@LayoutRes int paramInt, @Nullable ViewGroup paramViewGroup, @NonNull OnInflateFinishedListener paramOnInflateFinishedListener)
  {
    if (paramOnInflateFinishedListener == null)
      throw new NullPointerException("callback argument may not be null!");
    InflateRequest localInflateRequest = this.mInflateThread.obtainRequest();
    localInflateRequest.inflater = this;
    localInflateRequest.resid = paramInt;
    localInflateRequest.parent = paramViewGroup;
    localInflateRequest.callback = paramOnInflateFinishedListener;
    this.mInflateThread.enqueue(localInflateRequest);
  }

  private static class BasicInflater extends LayoutInflater
  {
    private static final String[] sClassPrefixList = { "android.widget.", "android.webkit.", "android.app." };

    BasicInflater(Context paramContext)
    {
      super();
    }

    public LayoutInflater cloneInContext(Context paramContext)
    {
      return new BasicInflater(paramContext);
    }

    protected View onCreateView(String paramString, AttributeSet paramAttributeSet)
    {
      String[] arrayOfString = sClassPrefixList;
      int i = arrayOfString.length;
      int j = 0;
      while (j < i)
      {
        String str = arrayOfString[j];
        try
        {
          View localView = createView(paramString, str, paramAttributeSet);
          if (localView != null)
            return localView;
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          j++;
          tmpTernaryOp = localClassNotFoundException;
        }
      }
      return super.onCreateView(paramString, paramAttributeSet);
    }
  }

  private static class InflateRequest
  {
    AsyncLayoutInflater.OnInflateFinishedListener callback;
    AsyncLayoutInflater inflater;
    ViewGroup parent;
    int resid;
    View view;
  }

  private static class InflateThread extends Thread
  {
    private static final InflateThread sInstance = new InflateThread();
    private ArrayBlockingQueue<AsyncLayoutInflater.InflateRequest> mQueue = new ArrayBlockingQueue(10);
    private Pools.SynchronizedPool<AsyncLayoutInflater.InflateRequest> mRequestPool = new Pools.SynchronizedPool(10);

    static
    {
      sInstance.start();
    }

    public static InflateThread getInstance()
    {
      return sInstance;
    }

    public void enqueue(AsyncLayoutInflater.InflateRequest paramInflateRequest)
    {
      try
      {
        this.mQueue.put(paramInflateRequest);
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        throw new RuntimeException("Failed to enqueue async inflate request", localInterruptedException);
      }
    }

    public AsyncLayoutInflater.InflateRequest obtainRequest()
    {
      AsyncLayoutInflater.InflateRequest localInflateRequest = (AsyncLayoutInflater.InflateRequest)this.mRequestPool.acquire();
      if (localInflateRequest == null)
        localInflateRequest = new AsyncLayoutInflater.InflateRequest();
      return localInflateRequest;
    }

    public void releaseRequest(AsyncLayoutInflater.InflateRequest paramInflateRequest)
    {
      paramInflateRequest.callback = null;
      paramInflateRequest.inflater = null;
      paramInflateRequest.parent = null;
      paramInflateRequest.resid = 0;
      paramInflateRequest.view = null;
      this.mRequestPool.release(paramInflateRequest);
    }

    public void run()
    {
      while (true)
        runInner();
    }

    public void runInner()
    {
      try
      {
        AsyncLayoutInflater.InflateRequest localInflateRequest = (AsyncLayoutInflater.InflateRequest)this.mQueue.take();
        try
        {
          localInflateRequest.view = localInflateRequest.inflater.mInflater.inflate(localInflateRequest.resid, localInflateRequest.parent, false);
        }
        catch (RuntimeException localRuntimeException)
        {
          Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", localRuntimeException);
        }
        Message.obtain(localInflateRequest.inflater.mHandler, 0, localInflateRequest).sendToTarget();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.w("AsyncLayoutInflater", localInterruptedException);
      }
    }
  }

  public static abstract interface OnInflateFinishedListener
  {
    public abstract void onInflateFinished(@NonNull View paramView, @LayoutRes int paramInt, @Nullable ViewGroup paramViewGroup);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.AsyncLayoutInflater
 * JD-Core Version:    0.6.1
 */