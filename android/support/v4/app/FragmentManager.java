package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager
{
  public static final int POP_BACK_STACK_INCLUSIVE = 1;

  public static void enableDebugLogging(boolean paramBoolean)
  {
    FragmentManagerImpl.DEBUG = paramBoolean;
  }

  public abstract void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener paramOnBackStackChangedListener);

  @NonNull
  public abstract FragmentTransaction beginTransaction();

  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);

  public abstract boolean executePendingTransactions();

  @Nullable
  public abstract Fragment findFragmentById(@IdRes int paramInt);

  @Nullable
  public abstract Fragment findFragmentByTag(@Nullable String paramString);

  @NonNull
  public abstract BackStackEntry getBackStackEntryAt(int paramInt);

  public abstract int getBackStackEntryCount();

  @Nullable
  public abstract Fragment getFragment(@NonNull Bundle paramBundle, @NonNull String paramString);

  @NonNull
  public abstract List<Fragment> getFragments();

  @Nullable
  public abstract Fragment getPrimaryNavigationFragment();

  public abstract boolean isDestroyed();

  public abstract boolean isStateSaved();

  @Deprecated
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public FragmentTransaction openTransaction()
  {
    return beginTransaction();
  }

  public abstract void popBackStack();

  public abstract void popBackStack(int paramInt1, int paramInt2);

  public abstract void popBackStack(@Nullable String paramString, int paramInt);

  public abstract boolean popBackStackImmediate();

  public abstract boolean popBackStackImmediate(int paramInt1, int paramInt2);

  public abstract boolean popBackStackImmediate(@Nullable String paramString, int paramInt);

  public abstract void putFragment(@NonNull Bundle paramBundle, @NonNull String paramString, @NonNull Fragment paramFragment);

  public abstract void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean);

  public abstract void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener paramOnBackStackChangedListener);

  @Nullable
  public abstract Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment);

  public abstract void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks);

  public static abstract interface BackStackEntry
  {
    @Nullable
    public abstract CharSequence getBreadCrumbShortTitle();

    @StringRes
    public abstract int getBreadCrumbShortTitleRes();

    @Nullable
    public abstract CharSequence getBreadCrumbTitle();

    @StringRes
    public abstract int getBreadCrumbTitleRes();

    public abstract int getId();

    @Nullable
    public abstract String getName();
  }

  public static abstract class FragmentLifecycleCallbacks
  {
    public void onFragmentActivityCreated(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @Nullable Bundle paramBundle)
    {
    }

    public void onFragmentAttached(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @NonNull Context paramContext)
    {
    }

    public void onFragmentCreated(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @Nullable Bundle paramBundle)
    {
    }

    public void onFragmentDestroyed(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }

    public void onFragmentDetached(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }

    public void onFragmentPaused(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }

    public void onFragmentPreAttached(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @NonNull Context paramContext)
    {
    }

    public void onFragmentPreCreated(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @Nullable Bundle paramBundle)
    {
    }

    public void onFragmentResumed(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }

    public void onFragmentSaveInstanceState(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @NonNull Bundle paramBundle)
    {
    }

    public void onFragmentStarted(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }

    public void onFragmentStopped(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }

    public void onFragmentViewCreated(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, @NonNull View paramView, @Nullable Bundle paramBundle)
    {
    }

    public void onFragmentViewDestroyed(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment)
    {
    }
  }

  public static abstract interface OnBackStackChangedListener
  {
    public abstract void onBackStackChanged();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.FragmentManager
 * JD-Core Version:    0.6.1
 */