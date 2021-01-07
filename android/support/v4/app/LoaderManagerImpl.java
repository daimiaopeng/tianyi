package android.support.v4.app;

import android.arch.lifecycle.e;
import android.arch.lifecycle.j;
import android.arch.lifecycle.k;
import android.arch.lifecycle.n;
import android.arch.lifecycle.o;
import android.arch.lifecycle.o.a;
import android.arch.lifecycle.p;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager
{
  static boolean DEBUG = false;
  static final String TAG = "LoaderManager";

  @NonNull
  private final e mLifecycleOwner;

  @NonNull
  private final LoaderViewModel mLoaderViewModel;

  LoaderManagerImpl(@NonNull e parame, @NonNull p paramp)
  {
    this.mLifecycleOwner = parame;
    this.mLoaderViewModel = LoaderViewModel.getInstance(paramp);
  }

  @MainThread
  @NonNull
  private <D> Loader<D> createAndInstallLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks, @Nullable Loader<D> paramLoader)
  {
    try
    {
      this.mLoaderViewModel.startCreatingLoader();
      Loader localLoader = paramLoaderCallbacks.onCreateLoader(paramInt, paramBundle);
      if (localLoader == null)
        throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
      if ((localLoader.getClass().isMemberClass()) && (!Modifier.isStatic(localLoader.getClass().getModifiers())))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
        localStringBuilder2.append(localLoader);
        throw new IllegalArgumentException(localStringBuilder2.toString());
      }
      LoaderInfo localLoaderInfo = new LoaderInfo(paramInt, paramBundle, localLoader, paramLoader);
      if (DEBUG)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("  Created new loader ");
        localStringBuilder1.append(localLoaderInfo);
        Log.v("LoaderManager", localStringBuilder1.toString());
      }
      this.mLoaderViewModel.putLoader(paramInt, localLoaderInfo);
      return localLoaderInfo.setCallback(this.mLifecycleOwner, paramLoaderCallbacks);
    }
    finally
    {
      this.mLoaderViewModel.finishCreatingLoader();
    }
  }

  @MainThread
  public void destroyLoader(int paramInt)
  {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader");
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("destroyLoader must be called on the main thread");
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("destroyLoader in ");
      localStringBuilder.append(this);
      localStringBuilder.append(" of ");
      localStringBuilder.append(paramInt);
      Log.v("LoaderManager", localStringBuilder.toString());
    }
    LoaderInfo localLoaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    if (localLoaderInfo != null)
    {
      localLoaderInfo.destroy(true);
      this.mLoaderViewModel.removeLoader(paramInt);
    }
  }

  @Deprecated
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    this.mLoaderViewModel.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }

  @Nullable
  public <D> Loader<D> getLoader(int paramInt)
  {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader");
    LoaderInfo localLoaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    Loader localLoader;
    if (localLoaderInfo != null)
      localLoader = localLoaderInfo.getLoader();
    else
      localLoader = null;
    return localLoader;
  }

  public boolean hasRunningLoaders()
  {
    return this.mLoaderViewModel.hasRunningLoaders();
  }

  @MainThread
  @NonNull
  public <D> Loader<D> initLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
  {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader");
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("initLoader must be called on the main thread");
    LoaderInfo localLoaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    if (DEBUG)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("initLoader in ");
      localStringBuilder1.append(this);
      localStringBuilder1.append(": args=");
      localStringBuilder1.append(paramBundle);
      Log.v("LoaderManager", localStringBuilder1.toString());
    }
    if (localLoaderInfo == null)
      return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, null);
    if (DEBUG)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("  Re-using existing loader ");
      localStringBuilder2.append(localLoaderInfo);
      Log.v("LoaderManager", localStringBuilder2.toString());
    }
    return localLoaderInfo.setCallback(this.mLifecycleOwner, paramLoaderCallbacks);
  }

  public void markForRedelivery()
  {
    this.mLoaderViewModel.markForRedelivery();
  }

  @MainThread
  @NonNull
  public <D> Loader<D> restartLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
  {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader");
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("restartLoader must be called on the main thread");
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("restartLoader in ");
      localStringBuilder.append(this);
      localStringBuilder.append(": args=");
      localStringBuilder.append(paramBundle);
      Log.v("LoaderManager", localStringBuilder.toString());
    }
    LoaderInfo localLoaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    Loader localLoader = null;
    if (localLoaderInfo != null)
      localLoader = localLoaderInfo.destroy(false);
    return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, localLoader);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("LoaderManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(this.mLifecycleOwner, localStringBuilder);
    localStringBuilder.append("}}");
    return localStringBuilder.toString();
  }

  public static class LoaderInfo<D> extends j<D>
    implements Loader.OnLoadCompleteListener<D>
  {

    @Nullable
    private final Bundle mArgs;
    private final int mId;
    private e mLifecycleOwner;

    @NonNull
    private final Loader<D> mLoader;
    private LoaderManagerImpl.LoaderObserver<D> mObserver;
    private Loader<D> mPriorLoader;

    LoaderInfo(int paramInt, @Nullable Bundle paramBundle, @NonNull Loader<D> paramLoader1, @Nullable Loader<D> paramLoader2)
    {
      this.mId = paramInt;
      this.mArgs = paramBundle;
      this.mLoader = paramLoader1;
      this.mPriorLoader = paramLoader2;
      this.mLoader.registerListener(paramInt, this);
    }

    @MainThread
    Loader<D> destroy(boolean paramBoolean)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Destroying: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      this.mLoader.cancelLoad();
      this.mLoader.abandon();
      LoaderManagerImpl.LoaderObserver localLoaderObserver = this.mObserver;
      if (localLoaderObserver != null)
      {
        removeObserver(localLoaderObserver);
        if (paramBoolean)
          localLoaderObserver.reset();
      }
      this.mLoader.unregisterListener(this);
      if (((localLoaderObserver != null) && (!localLoaderObserver.hasDeliveredData())) || (paramBoolean))
      {
        this.mLoader.reset();
        return this.mPriorLoader;
      }
      return this.mLoader;
    }

    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mId=");
      paramPrintWriter.print(this.mId);
      paramPrintWriter.print(" mArgs=");
      paramPrintWriter.println(this.mArgs);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mLoader=");
      paramPrintWriter.println(this.mLoader);
      Loader localLoader = this.mLoader;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramString);
      localStringBuilder1.append("  ");
      localLoader.dump(localStringBuilder1.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      if (this.mObserver != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mCallbacks=");
        paramPrintWriter.println(this.mObserver);
        LoaderManagerImpl.LoaderObserver localLoaderObserver = this.mObserver;
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(paramString);
        localStringBuilder2.append("  ");
        localLoaderObserver.dump(localStringBuilder2.toString(), paramPrintWriter);
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mData=");
      paramPrintWriter.println(getLoader().dataToString(getValue()));
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStarted=");
      paramPrintWriter.println(hasActiveObservers());
    }

    @NonNull
    Loader<D> getLoader()
    {
      return this.mLoader;
    }

    boolean isCallbackWaitingForData()
    {
      if (!hasActiveObservers())
        return false;
      LoaderManagerImpl.LoaderObserver localLoaderObserver = this.mObserver;
      boolean bool1 = false;
      if (localLoaderObserver != null)
      {
        boolean bool2 = this.mObserver.hasDeliveredData();
        bool1 = false;
        if (!bool2)
          bool1 = true;
      }
      return bool1;
    }

    void markForRedelivery()
    {
      e locale = this.mLifecycleOwner;
      LoaderManagerImpl.LoaderObserver localLoaderObserver = this.mObserver;
      if ((locale != null) && (localLoaderObserver != null))
      {
        super.removeObserver(localLoaderObserver);
        observe(locale, localLoaderObserver);
      }
    }

    protected void onActive()
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Starting: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      this.mLoader.startLoading();
    }

    protected void onInactive()
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Stopping: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      this.mLoader.stopLoading();
    }

    public void onLoadComplete(@NonNull Loader<D> paramLoader, @Nullable D paramD)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("onLoadComplete: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        setValue(paramD);
      }
      else
      {
        if (LoaderManagerImpl.DEBUG)
          Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
        postValue(paramD);
      }
    }

    public void removeObserver(@NonNull k<? super D> paramk)
    {
      super.removeObserver(paramk);
      this.mLifecycleOwner = null;
      this.mObserver = null;
    }

    @MainThread
    @NonNull
    Loader<D> setCallback(@NonNull e parame, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
    {
      LoaderManagerImpl.LoaderObserver localLoaderObserver = new LoaderManagerImpl.LoaderObserver(this.mLoader, paramLoaderCallbacks);
      observe(parame, localLoaderObserver);
      if (this.mObserver != null)
        removeObserver(this.mObserver);
      this.mLifecycleOwner = parame;
      this.mObserver = localLoaderObserver;
      return this.mLoader;
    }

    public void setValue(D paramD)
    {
      super.setValue(paramD);
      if (this.mPriorLoader != null)
      {
        this.mPriorLoader.reset();
        this.mPriorLoader = null;
      }
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(64);
      localStringBuilder.append("LoaderInfo{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" #");
      localStringBuilder.append(this.mId);
      localStringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(this.mLoader, localStringBuilder);
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
    }
  }

  static class LoaderObserver<D>
    implements k<D>
  {

    @NonNull
    private final LoaderManager.LoaderCallbacks<D> mCallback;
    private boolean mDeliveredData = false;

    @NonNull
    private final Loader<D> mLoader;

    LoaderObserver(@NonNull Loader<D> paramLoader, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
    {
      this.mLoader = paramLoader;
      this.mCallback = paramLoaderCallbacks;
    }

    public void dump(String paramString, PrintWriter paramPrintWriter)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mDeliveredData=");
      paramPrintWriter.println(this.mDeliveredData);
    }

    boolean hasDeliveredData()
    {
      return this.mDeliveredData;
    }

    public void onChanged(@Nullable D paramD)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  onLoadFinished in ");
        localStringBuilder.append(this.mLoader);
        localStringBuilder.append(": ");
        localStringBuilder.append(this.mLoader.dataToString(paramD));
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      this.mCallback.onLoadFinished(this.mLoader, paramD);
      this.mDeliveredData = true;
    }

    @MainThread
    void reset()
    {
      if (this.mDeliveredData)
      {
        if (LoaderManagerImpl.DEBUG)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("  Resetting: ");
          localStringBuilder.append(this.mLoader);
          Log.v("LoaderManager", localStringBuilder.toString());
        }
        this.mCallback.onLoaderReset(this.mLoader);
      }
    }

    public String toString()
    {
      return this.mCallback.toString();
    }
  }

  static class LoaderViewModel extends n
  {
    private static final o.a FACTORY = new o.a()
    {
      @NonNull
      public <T extends n> T create(@NonNull Class<T> paramAnonymousClass)
      {
        return new LoaderManagerImpl.LoaderViewModel();
      }
    };
    private boolean mCreatingLoader = false;
    private SparseArrayCompat<LoaderManagerImpl.LoaderInfo> mLoaders = new SparseArrayCompat();

    @NonNull
    static LoaderViewModel getInstance(p paramp)
    {
      return (LoaderViewModel)new o(paramp, FACTORY).a(LoaderViewModel.class);
    }

    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      if (this.mLoaders.size() > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Loaders:");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("    ");
        String str = localStringBuilder.toString();
        for (int i = 0; i < this.mLoaders.size(); i++)
        {
          LoaderManagerImpl.LoaderInfo localLoaderInfo = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(this.mLoaders.keyAt(i));
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localLoaderInfo.toString());
          localLoaderInfo.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        }
      }
    }

    void finishCreatingLoader()
    {
      this.mCreatingLoader = false;
    }

    <D> LoaderManagerImpl.LoaderInfo<D> getLoader(int paramInt)
    {
      return (LoaderManagerImpl.LoaderInfo)this.mLoaders.get(paramInt);
    }

    boolean hasRunningLoaders()
    {
      int i = this.mLoaders.size();
      for (int j = 0; j < i; j++)
        if (((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(j)).isCallbackWaitingForData())
          return true;
      return false;
    }

    boolean isCreatingLoader()
    {
      return this.mCreatingLoader;
    }

    void markForRedelivery()
    {
      int i = this.mLoaders.size();
      for (int j = 0; j < i; j++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(j)).markForRedelivery();
    }

    protected void onCleared()
    {
      super.onCleared();
      int i = this.mLoaders.size();
      for (int j = 0; j < i; j++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(j)).destroy(true);
      this.mLoaders.clear();
    }

    void putLoader(int paramInt, @NonNull LoaderManagerImpl.LoaderInfo paramLoaderInfo)
    {
      this.mLoaders.put(paramInt, paramLoaderInfo);
    }

    void removeLoader(int paramInt)
    {
      this.mLoaders.remove(paramInt);
    }

    void startCreatingLoader()
    {
      this.mCreatingLoader = true;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.LoaderManagerImpl
 * JD-Core Version:    0.6.1
 */