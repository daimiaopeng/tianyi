package android.support.v4.app;

import android.arch.lifecycle.e;
import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager
{
  public static void enableDebugLogging(boolean paramBoolean)
  {
    LoaderManagerImpl.DEBUG = paramBoolean;
  }

  @NonNull
  public static <T extends e,  extends q> LoaderManager getInstance(@NonNull T paramT)
  {
    return new LoaderManagerImpl(paramT, ((q)paramT).getViewModelStore());
  }

  @MainThread
  public abstract void destroyLoader(int paramInt);

  @Deprecated
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);

  @Nullable
  public abstract <D> Loader<D> getLoader(int paramInt);

  public boolean hasRunningLoaders()
  {
    return false;
  }

  @MainThread
  @NonNull
  public abstract <D> Loader<D> initLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderCallbacks<D> paramLoaderCallbacks);

  public abstract void markForRedelivery();

  @MainThread
  @NonNull
  public abstract <D> Loader<D> restartLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderCallbacks<D> paramLoaderCallbacks);

  public static abstract interface LoaderCallbacks<D>
  {
    @MainThread
    @NonNull
    public abstract Loader<D> onCreateLoader(int paramInt, @Nullable Bundle paramBundle);

    @MainThread
    public abstract void onLoadFinished(@NonNull Loader<D> paramLoader, D paramD);

    @MainThread
    public abstract void onLoaderReset(@NonNull Loader<D> paramLoader);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.LoaderManager
 * JD-Core Version:    0.6.1
 */