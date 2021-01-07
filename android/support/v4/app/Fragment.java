package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.c;
import android.arch.lifecycle.c.a;
import android.arch.lifecycle.e;
import android.arch.lifecycle.f;
import android.arch.lifecycle.j;
import android.arch.lifecycle.p;
import android.arch.lifecycle.q;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Fragment
  implements e, q, ComponentCallbacks, View.OnCreateContextMenuListener
{
  static final int ACTIVITY_CREATED = 2;
  static final int CREATED = 1;
  static final int INITIALIZING = 0;
  static final int RESUMED = 4;
  static final int STARTED = 3;
  static final Object USE_DEFAULT_TRANSITION = new Object();
  private static final SimpleArrayMap<String, Class<?>> sClassMap = new SimpleArrayMap();
  boolean mAdded;
  AnimationInfo mAnimationInfo;
  Bundle mArguments;
  int mBackStackNesting;
  boolean mCalled;
  FragmentManagerImpl mChildFragmentManager;
  FragmentManagerNonConfig mChildNonConfig;
  ViewGroup mContainer;
  int mContainerId;
  boolean mDeferStart;
  boolean mDetached;
  int mFragmentId;
  FragmentManagerImpl mFragmentManager;
  boolean mFromLayout;
  boolean mHasMenu;
  boolean mHidden;
  boolean mHiddenChanged;
  FragmentHostCallback mHost;
  boolean mInLayout;
  int mIndex = -1;
  View mInnerView;
  boolean mIsCreated;
  boolean mIsNewlyAdded;
  LayoutInflater mLayoutInflater;
  f mLifecycleRegistry = new f(this);
  boolean mMenuVisible = true;
  Fragment mParentFragment;
  boolean mPerformedCreateView;
  float mPostponedAlpha;
  boolean mRemoving;
  boolean mRestored;
  boolean mRetainInstance;
  boolean mRetaining;
  Bundle mSavedFragmentState;

  @Nullable
  Boolean mSavedUserVisibleHint;
  SparseArray<Parcelable> mSavedViewState;
  int mState = 0;
  String mTag;
  Fragment mTarget;
  int mTargetIndex = -1;
  int mTargetRequestCode;
  boolean mUserVisibleHint = true;
  View mView;
  e mViewLifecycleOwner;
  j<e> mViewLifecycleOwnerLiveData = new j();
  f mViewLifecycleRegistry;
  p mViewModelStore;
  String mWho;

  private AnimationInfo ensureAnimationInfo()
  {
    if (this.mAnimationInfo == null)
      this.mAnimationInfo = new AnimationInfo();
    return this.mAnimationInfo;
  }

  public static Fragment instantiate(Context paramContext, String paramString)
  {
    return instantiate(paramContext, paramString, null);
  }

  public static Fragment instantiate(Context paramContext, String paramString, @Nullable Bundle paramBundle)
  {
    try
    {
      Class localClass = (Class)sClassMap.get(paramString);
      if (localClass == null)
      {
        localClass = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, localClass);
      }
      Fragment localFragment = (Fragment)localClass.getConstructor(new Class[0]).newInstance(new Object[0]);
      if (paramBundle != null)
      {
        paramBundle.setClassLoader(localFragment.getClass().getClassLoader());
        localFragment.setArguments(paramBundle);
      }
      return localFragment;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      StringBuilder localStringBuilder5 = new StringBuilder();
      localStringBuilder5.append("Unable to instantiate fragment ");
      localStringBuilder5.append(paramString);
      localStringBuilder5.append(": calling Fragment constructor caused an exception");
      throw new InstantiationException(localStringBuilder5.toString(), localInvocationTargetException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append("Unable to instantiate fragment ");
      localStringBuilder4.append(paramString);
      localStringBuilder4.append(": could not find Fragment constructor");
      throw new InstantiationException(localStringBuilder4.toString(), localNoSuchMethodException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("Unable to instantiate fragment ");
      localStringBuilder3.append(paramString);
      localStringBuilder3.append(": make sure class name exists, is public, and has an");
      localStringBuilder3.append(" empty constructor that is public");
      throw new InstantiationException(localStringBuilder3.toString(), localIllegalAccessException);
    }
    catch (InstantiationException localInstantiationException)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Unable to instantiate fragment ");
      localStringBuilder2.append(paramString);
      localStringBuilder2.append(": make sure class name exists, is public, and has an");
      localStringBuilder2.append(" empty constructor that is public");
      throw new InstantiationException(localStringBuilder2.toString(), localInstantiationException);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Unable to instantiate fragment ");
      localStringBuilder1.append(paramString);
      localStringBuilder1.append(": make sure class name exists, is public, and has an");
      localStringBuilder1.append(" empty constructor that is public");
      throw new InstantiationException(localStringBuilder1.toString(), localClassNotFoundException);
    }
  }

  // ERROR //
  static boolean isSupportFragmentClass(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 106	android/support/v4/app/Fragment:sClassMap	Landroid/support/v4/util/SimpleArrayMap;
    //   3: aload_1
    //   4: invokevirtual 157	android/support/v4/util/SimpleArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   7: checkcast 159	java/lang/Class
    //   10: astore_2
    //   11: aload_2
    //   12: ifnonnull +21 -> 33
    //   15: aload_0
    //   16: invokevirtual 165	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   19: aload_1
    //   20: invokevirtual 171	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   23: astore_2
    //   24: getstatic 106	android/support/v4/app/Fragment:sClassMap	Landroid/support/v4/util/SimpleArrayMap;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 175	android/support/v4/util/SimpleArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   32: pop
    //   33: ldc 2
    //   35: aload_2
    //   36: invokevirtual 232	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   39: istore_3
    //   40: iload_3
    //   41: ireturn
    //   42: iconst_0
    //   43: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	40	42	java/lang/ClassNotFoundException
  }

  void callStartTransitionListener()
  {
    OnStartEnterTransitionListener localOnStartEnterTransitionListener;
    if (this.mAnimationInfo == null)
    {
      localOnStartEnterTransitionListener = null;
    }
    else
    {
      this.mAnimationInfo.mEnterTransitionPostponed = false;
      localOnStartEnterTransitionListener = this.mAnimationInfo.mStartEnterTransitionListener;
      this.mAnimationInfo.mStartEnterTransitionListener = null;
    }
    if (localOnStartEnterTransitionListener != null)
      localOnStartEnterTransitionListener.onStartEnterTransition();
  }

  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(this.mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(this.mContainerId));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(this.mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(this.mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(this.mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(this.mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(this.mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(this.mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(this.mRemoving);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(this.mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(this.mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(this.mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(this.mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(this.mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(this.mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(this.mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(this.mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(this.mUserVisibleHint);
    if (this.mFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(this.mFragmentManager);
    }
    if (this.mHost != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHost=");
      paramPrintWriter.println(this.mHost);
    }
    if (this.mParentFragment != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(this.mParentFragment);
    }
    if (this.mArguments != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(this.mArguments);
    }
    if (this.mSavedFragmentState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(this.mSavedFragmentState);
    }
    if (this.mSavedViewState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(this.mSavedViewState);
    }
    if (this.mTarget != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(this.mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(this.mTargetRequestCode);
    }
    if (getNextAnim() != 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(getNextAnim());
    }
    if (this.mContainer != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(this.mContainer);
    }
    if (this.mView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(this.mView);
    }
    if (this.mInnerView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mInnerView=");
      paramPrintWriter.println(this.mView);
    }
    if (getAnimatingAway() != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(getAnimatingAway());
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(getStateAfterAnimating());
    }
    if (getContext() != null)
      LoaderManager.getInstance(this).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    if (this.mChildFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Child ");
      localStringBuilder1.append(this.mChildFragmentManager);
      localStringBuilder1.append(":");
      paramPrintWriter.println(localStringBuilder1.toString());
      FragmentManagerImpl localFragmentManagerImpl = this.mChildFragmentManager;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString);
      localStringBuilder2.append("  ");
      localFragmentManagerImpl.dump(localStringBuilder2.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }

  public final boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  Fragment findFragmentByWho(String paramString)
  {
    if (paramString.equals(this.mWho))
      return this;
    if (this.mChildFragmentManager != null)
      return this.mChildFragmentManager.findFragmentByWho(paramString);
    return null;
  }

  @Nullable
  public final FragmentActivity getActivity()
  {
    FragmentActivity localFragmentActivity;
    if (this.mHost == null)
      localFragmentActivity = null;
    else
      localFragmentActivity = (FragmentActivity)this.mHost.getActivity();
    return localFragmentActivity;
  }

  public boolean getAllowEnterTransitionOverlap()
  {
    boolean bool;
    if ((this.mAnimationInfo != null) && (this.mAnimationInfo.mAllowEnterTransitionOverlap != null))
      bool = this.mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
    else
      bool = true;
    return bool;
  }

  public boolean getAllowReturnTransitionOverlap()
  {
    boolean bool;
    if ((this.mAnimationInfo != null) && (this.mAnimationInfo.mAllowReturnTransitionOverlap != null))
      bool = this.mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
    else
      bool = true;
    return bool;
  }

  View getAnimatingAway()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mAnimatingAway;
  }

  Animator getAnimator()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mAnimator;
  }

  @Nullable
  public final Bundle getArguments()
  {
    return this.mArguments;
  }

  @NonNull
  public final FragmentManager getChildFragmentManager()
  {
    if (this.mChildFragmentManager == null)
    {
      instantiateChildFragmentManager();
      if (this.mState >= 4)
        this.mChildFragmentManager.dispatchResume();
      else if (this.mState >= 3)
        this.mChildFragmentManager.dispatchStart();
      else if (this.mState >= 2)
        this.mChildFragmentManager.dispatchActivityCreated();
      else if (this.mState >= 1)
        this.mChildFragmentManager.dispatchCreate();
    }
    return this.mChildFragmentManager;
  }

  @Nullable
  public Context getContext()
  {
    Context localContext;
    if (this.mHost == null)
      localContext = null;
    else
      localContext = this.mHost.getContext();
    return localContext;
  }

  @Nullable
  public Object getEnterTransition()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mEnterTransition;
  }

  SharedElementCallback getEnterTransitionCallback()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mEnterTransitionCallback;
  }

  @Nullable
  public Object getExitTransition()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mExitTransition;
  }

  SharedElementCallback getExitTransitionCallback()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mExitTransitionCallback;
  }

  @Nullable
  public final FragmentManager getFragmentManager()
  {
    return this.mFragmentManager;
  }

  @Nullable
  public final Object getHost()
  {
    Object localObject;
    if (this.mHost == null)
      localObject = null;
    else
      localObject = this.mHost.onGetHost();
    return localObject;
  }

  public final int getId()
  {
    return this.mFragmentId;
  }

  public final LayoutInflater getLayoutInflater()
  {
    if (this.mLayoutInflater == null)
      return performGetLayoutInflater(null);
    return this.mLayoutInflater;
  }

  @Deprecated
  @NonNull
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public LayoutInflater getLayoutInflater(@Nullable Bundle paramBundle)
  {
    if (this.mHost == null)
      throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    LayoutInflater localLayoutInflater = this.mHost.onGetLayoutInflater();
    getChildFragmentManager();
    LayoutInflaterCompat.setFactory2(localLayoutInflater, this.mChildFragmentManager.getLayoutInflaterFactory());
    return localLayoutInflater;
  }

  public c getLifecycle()
  {
    return this.mLifecycleRegistry;
  }

  @Deprecated
  public LoaderManager getLoaderManager()
  {
    return LoaderManager.getInstance(this);
  }

  int getNextAnim()
  {
    if (this.mAnimationInfo == null)
      return 0;
    return this.mAnimationInfo.mNextAnim;
  }

  int getNextTransition()
  {
    if (this.mAnimationInfo == null)
      return 0;
    return this.mAnimationInfo.mNextTransition;
  }

  int getNextTransitionStyle()
  {
    if (this.mAnimationInfo == null)
      return 0;
    return this.mAnimationInfo.mNextTransitionStyle;
  }

  @Nullable
  public final Fragment getParentFragment()
  {
    return this.mParentFragment;
  }

  public Object getReenterTransition()
  {
    if (this.mAnimationInfo == null)
      return null;
    Object localObject;
    if (this.mAnimationInfo.mReenterTransition == USE_DEFAULT_TRANSITION)
      localObject = getExitTransition();
    else
      localObject = this.mAnimationInfo.mReenterTransition;
    return localObject;
  }

  @NonNull
  public final Resources getResources()
  {
    return requireContext().getResources();
  }

  public final boolean getRetainInstance()
  {
    return this.mRetainInstance;
  }

  @Nullable
  public Object getReturnTransition()
  {
    if (this.mAnimationInfo == null)
      return null;
    Object localObject;
    if (this.mAnimationInfo.mReturnTransition == USE_DEFAULT_TRANSITION)
      localObject = getEnterTransition();
    else
      localObject = this.mAnimationInfo.mReturnTransition;
    return localObject;
  }

  @Nullable
  public Object getSharedElementEnterTransition()
  {
    if (this.mAnimationInfo == null)
      return null;
    return this.mAnimationInfo.mSharedElementEnterTransition;
  }

  @Nullable
  public Object getSharedElementReturnTransition()
  {
    if (this.mAnimationInfo == null)
      return null;
    Object localObject;
    if (this.mAnimationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION)
      localObject = getSharedElementEnterTransition();
    else
      localObject = this.mAnimationInfo.mSharedElementReturnTransition;
    return localObject;
  }

  int getStateAfterAnimating()
  {
    if (this.mAnimationInfo == null)
      return 0;
    return this.mAnimationInfo.mStateAfterAnimating;
  }

  @NonNull
  public final String getString(@StringRes int paramInt)
  {
    return getResources().getString(paramInt);
  }

  @NonNull
  public final String getString(@StringRes int paramInt, Object[] paramArrayOfObject)
  {
    return getResources().getString(paramInt, paramArrayOfObject);
  }

  @Nullable
  public final String getTag()
  {
    return this.mTag;
  }

  @Nullable
  public final Fragment getTargetFragment()
  {
    return this.mTarget;
  }

  public final int getTargetRequestCode()
  {
    return this.mTargetRequestCode;
  }

  @NonNull
  public final CharSequence getText(@StringRes int paramInt)
  {
    return getResources().getText(paramInt);
  }

  public boolean getUserVisibleHint()
  {
    return this.mUserVisibleHint;
  }

  @Nullable
  public View getView()
  {
    return this.mView;
  }

  @MainThread
  @NonNull
  public e getViewLifecycleOwner()
  {
    if (this.mViewLifecycleOwner == null)
      throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    return this.mViewLifecycleOwner;
  }

  @NonNull
  public LiveData<e> getViewLifecycleOwnerLiveData()
  {
    return this.mViewLifecycleOwnerLiveData;
  }

  @NonNull
  public p getViewModelStore()
  {
    if (getContext() == null)
      throw new IllegalStateException("Can't access ViewModels from detached fragment");
    if (this.mViewModelStore == null)
      this.mViewModelStore = new p();
    return this.mViewModelStore;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public final boolean hasOptionsMenu()
  {
    return this.mHasMenu;
  }

  public final int hashCode()
  {
    return super.hashCode();
  }

  void initState()
  {
    this.mIndex = -1;
    this.mWho = null;
    this.mAdded = false;
    this.mRemoving = false;
    this.mFromLayout = false;
    this.mInLayout = false;
    this.mRestored = false;
    this.mBackStackNesting = 0;
    this.mFragmentManager = null;
    this.mChildFragmentManager = null;
    this.mHost = null;
    this.mFragmentId = 0;
    this.mContainerId = 0;
    this.mTag = null;
    this.mHidden = false;
    this.mDetached = false;
    this.mRetaining = false;
  }

  void instantiateChildFragmentManager()
  {
    if (this.mHost == null)
      throw new IllegalStateException("Fragment has not been attached yet.");
    this.mChildFragmentManager = new FragmentManagerImpl();
    this.mChildFragmentManager.attachController(this.mHost, new FragmentContainer()
    {
      public Fragment instantiate(Context paramAnonymousContext, String paramAnonymousString, Bundle paramAnonymousBundle)
      {
        return Fragment.this.mHost.instantiate(paramAnonymousContext, paramAnonymousString, paramAnonymousBundle);
      }

      @Nullable
      public View onFindViewById(int paramAnonymousInt)
      {
        if (Fragment.this.mView == null)
          throw new IllegalStateException("Fragment does not have a view");
        return Fragment.this.mView.findViewById(paramAnonymousInt);
      }

      public boolean onHasView()
      {
        boolean bool;
        if (Fragment.this.mView != null)
          bool = true;
        else
          bool = false;
        return bool;
      }
    }
    , this);
  }

  public final boolean isAdded()
  {
    boolean bool;
    if ((this.mHost != null) && (this.mAdded))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public final boolean isDetached()
  {
    return this.mDetached;
  }

  public final boolean isHidden()
  {
    return this.mHidden;
  }

  boolean isHideReplaced()
  {
    if (this.mAnimationInfo == null)
      return false;
    return this.mAnimationInfo.mIsHideReplaced;
  }

  final boolean isInBackStack()
  {
    boolean bool;
    if (this.mBackStackNesting > 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public final boolean isInLayout()
  {
    return this.mInLayout;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public final boolean isMenuVisible()
  {
    return this.mMenuVisible;
  }

  boolean isPostponed()
  {
    if (this.mAnimationInfo == null)
      return false;
    return this.mAnimationInfo.mEnterTransitionPostponed;
  }

  public final boolean isRemoving()
  {
    return this.mRemoving;
  }

  public final boolean isResumed()
  {
    boolean bool;
    if (this.mState >= 4)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public final boolean isStateSaved()
  {
    if (this.mFragmentManager == null)
      return false;
    return this.mFragmentManager.isStateSaved();
  }

  public final boolean isVisible()
  {
    boolean bool;
    if ((isAdded()) && (!isHidden()) && (this.mView != null) && (this.mView.getWindowToken() != null) && (this.mView.getVisibility() == 0))
      bool = true;
    else
      bool = false;
    return bool;
  }

  void noteStateNotSaved()
  {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved();
  }

  @CallSuper
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    this.mCalled = true;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  @Deprecated
  @CallSuper
  public void onAttach(Activity paramActivity)
  {
    this.mCalled = true;
  }

  @CallSuper
  public void onAttach(Context paramContext)
  {
    this.mCalled = true;
    Activity localActivity;
    if (this.mHost == null)
      localActivity = null;
    else
      localActivity = this.mHost.getActivity();
    if (localActivity != null)
    {
      this.mCalled = false;
      onAttach(localActivity);
    }
  }

  public void onAttachFragment(Fragment paramFragment)
  {
  }

  @CallSuper
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    this.mCalled = true;
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }

  @CallSuper
  public void onCreate(@Nullable Bundle paramBundle)
  {
    this.mCalled = true;
    restoreChildFragmentState(paramBundle);
    if ((this.mChildFragmentManager != null) && (!this.mChildFragmentManager.isStateAtLeast(1)))
      this.mChildFragmentManager.dispatchCreate();
  }

  public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }

  public Animator onCreateAnimator(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }

  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
  }

  @Nullable
  public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    return null;
  }

  @CallSuper
  public void onDestroy()
  {
    boolean bool = true;
    this.mCalled = bool;
    FragmentActivity localFragmentActivity = getActivity();
    if ((localFragmentActivity == null) || (!localFragmentActivity.isChangingConfigurations()))
      bool = false;
    if ((this.mViewModelStore != null) && (!bool))
      this.mViewModelStore.a();
  }

  public void onDestroyOptionsMenu()
  {
  }

  @CallSuper
  public void onDestroyView()
  {
    this.mCalled = true;
    if (this.mView != null)
      this.mViewLifecycleRegistry.a(c.a.ON_DESTROY);
  }

  @CallSuper
  public void onDetach()
  {
    this.mCalled = true;
  }

  @NonNull
  public LayoutInflater onGetLayoutInflater(@Nullable Bundle paramBundle)
  {
    return getLayoutInflater(paramBundle);
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
  }

  @Deprecated
  @CallSuper
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    this.mCalled = true;
  }

  @CallSuper
  public void onInflate(Context paramContext, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    this.mCalled = true;
    Activity localActivity;
    if (this.mHost == null)
      localActivity = null;
    else
      localActivity = this.mHost.getActivity();
    if (localActivity != null)
    {
      this.mCalled = false;
      onInflate(localActivity, paramAttributeSet, paramBundle);
    }
  }

  @CallSuper
  public void onLowMemory()
  {
    this.mCalled = true;
  }

  public void onMultiWindowModeChanged(boolean paramBoolean)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }

  public void onOptionsMenuClosed(Menu paramMenu)
  {
  }

  @CallSuper
  public void onPause()
  {
    this.mCalled = true;
  }

  public void onPictureInPictureModeChanged(boolean paramBoolean)
  {
  }

  public void onPrepareOptionsMenu(Menu paramMenu)
  {
  }

  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
  }

  @CallSuper
  public void onResume()
  {
    this.mCalled = true;
  }

  public void onSaveInstanceState(@NonNull Bundle paramBundle)
  {
  }

  @CallSuper
  public void onStart()
  {
    this.mCalled = true;
  }

  @CallSuper
  public void onStop()
  {
    this.mCalled = true;
  }

  public void onViewCreated(@NonNull View paramView, @Nullable Bundle paramBundle)
  {
  }

  @CallSuper
  public void onViewStateRestored(@Nullable Bundle paramBundle)
  {
    this.mCalled = true;
    if (this.mView != null)
      this.mViewLifecycleRegistry.a(c.a.ON_CREATE);
  }

  @Nullable
  FragmentManager peekChildFragmentManager()
  {
    return this.mChildFragmentManager;
  }

  void performActivityCreated(Bundle paramBundle)
  {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved();
    this.mState = 2;
    this.mCalled = false;
    onActivityCreated(paramBundle);
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onActivityCreated()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchActivityCreated();
  }

  void performConfigurationChanged(Configuration paramConfiguration)
  {
    onConfigurationChanged(paramConfiguration);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchConfigurationChanged(paramConfiguration);
  }

  boolean performContextItemSelected(MenuItem paramMenuItem)
  {
    if (!this.mHidden)
    {
      if (onContextItemSelected(paramMenuItem))
        return true;
      if ((this.mChildFragmentManager != null) && (this.mChildFragmentManager.dispatchContextItemSelected(paramMenuItem)))
        return true;
    }
    return false;
  }

  void performCreate(Bundle paramBundle)
  {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved();
    this.mState = 1;
    this.mCalled = false;
    onCreate(paramBundle);
    this.mIsCreated = true;
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onCreate()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
    this.mLifecycleRegistry.a(c.a.ON_CREATE);
  }

  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    boolean bool1 = this.mHidden;
    boolean bool2 = false;
    if (!bool1)
    {
      boolean bool3 = this.mHasMenu;
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = this.mMenuVisible;
        bool2 = false;
        if (bool4)
        {
          bool2 = true;
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
        }
      }
      if (this.mChildFragmentManager != null)
        bool2 |= this.mChildFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
    }
    return bool2;
  }

  void performCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.noteStateNotSaved();
    this.mPerformedCreateView = true;
    this.mViewLifecycleOwner = new e()
    {
      public c getLifecycle()
      {
        if (Fragment.this.mViewLifecycleRegistry == null)
          Fragment.this.mViewLifecycleRegistry = new f(Fragment.this.mViewLifecycleOwner);
        return Fragment.this.mViewLifecycleRegistry;
      }
    };
    this.mViewLifecycleRegistry = null;
    this.mView = onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (this.mView != null)
    {
      this.mViewLifecycleOwner.getLifecycle();
      this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
    }
    else
    {
      if (this.mViewLifecycleRegistry != null)
        throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
      this.mViewLifecycleOwner = null;
    }
  }

  void performDestroy()
  {
    this.mLifecycleRegistry.a(c.a.ON_DESTROY);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchDestroy();
    this.mState = 0;
    this.mCalled = false;
    this.mIsCreated = false;
    onDestroy();
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onDestroy()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
    this.mChildFragmentManager = null;
  }

  void performDestroyView()
  {
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchDestroyView();
    this.mState = 1;
    this.mCalled = false;
    onDestroyView();
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onDestroyView()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
    LoaderManager.getInstance(this).markForRedelivery();
    this.mPerformedCreateView = false;
  }

  void performDetach()
  {
    this.mCalled = false;
    onDetach();
    this.mLayoutInflater = null;
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Fragment ");
      localStringBuilder1.append(this);
      localStringBuilder1.append(" did not call through to super.onDetach()");
      throw new SuperNotCalledException(localStringBuilder1.toString());
    }
    if (this.mChildFragmentManager != null)
    {
      if (!this.mRetaining)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Child FragmentManager of ");
        localStringBuilder2.append(this);
        localStringBuilder2.append(" was not ");
        localStringBuilder2.append(" destroyed and this fragment is not retaining instance");
        throw new IllegalStateException(localStringBuilder2.toString());
      }
      this.mChildFragmentManager.dispatchDestroy();
      this.mChildFragmentManager = null;
    }
  }

  @NonNull
  LayoutInflater performGetLayoutInflater(@Nullable Bundle paramBundle)
  {
    this.mLayoutInflater = onGetLayoutInflater(paramBundle);
    return this.mLayoutInflater;
  }

  void performLowMemory()
  {
    onLowMemory();
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchLowMemory();
  }

  void performMultiWindowModeChanged(boolean paramBoolean)
  {
    onMultiWindowModeChanged(paramBoolean);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchMultiWindowModeChanged(paramBoolean);
  }

  boolean performOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!this.mHidden)
    {
      if ((this.mHasMenu) && (this.mMenuVisible) && (onOptionsItemSelected(paramMenuItem)))
        return true;
      if ((this.mChildFragmentManager != null) && (this.mChildFragmentManager.dispatchOptionsItemSelected(paramMenuItem)))
        return true;
    }
    return false;
  }

  void performOptionsMenuClosed(Menu paramMenu)
  {
    if (!this.mHidden)
    {
      if ((this.mHasMenu) && (this.mMenuVisible))
        onOptionsMenuClosed(paramMenu);
      if (this.mChildFragmentManager != null)
        this.mChildFragmentManager.dispatchOptionsMenuClosed(paramMenu);
    }
  }

  void performPause()
  {
    if (this.mView != null)
      this.mViewLifecycleRegistry.a(c.a.ON_PAUSE);
    this.mLifecycleRegistry.a(c.a.ON_PAUSE);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchPause();
    this.mState = 3;
    this.mCalled = false;
    onPause();
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onPause()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
  }

  void performPictureInPictureModeChanged(boolean paramBoolean)
  {
    onPictureInPictureModeChanged(paramBoolean);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchPictureInPictureModeChanged(paramBoolean);
  }

  boolean performPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool1 = this.mHidden;
    boolean bool2 = false;
    if (!bool1)
    {
      boolean bool3 = this.mHasMenu;
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = this.mMenuVisible;
        bool2 = false;
        if (bool4)
        {
          bool2 = true;
          onPrepareOptionsMenu(paramMenu);
        }
      }
      if (this.mChildFragmentManager != null)
        bool2 |= this.mChildFragmentManager.dispatchPrepareOptionsMenu(paramMenu);
    }
    return bool2;
  }

  void performResume()
  {
    if (this.mChildFragmentManager != null)
    {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    }
    this.mState = 4;
    this.mCalled = false;
    onResume();
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onResume()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
    if (this.mChildFragmentManager != null)
    {
      this.mChildFragmentManager.dispatchResume();
      this.mChildFragmentManager.execPendingActions();
    }
    this.mLifecycleRegistry.a(c.a.ON_RESUME);
    if (this.mView != null)
      this.mViewLifecycleRegistry.a(c.a.ON_RESUME);
  }

  void performSaveInstanceState(Bundle paramBundle)
  {
    onSaveInstanceState(paramBundle);
    if (this.mChildFragmentManager != null)
    {
      Parcelable localParcelable = this.mChildFragmentManager.saveAllState();
      if (localParcelable != null)
        paramBundle.putParcelable("android:support:fragments", localParcelable);
    }
  }

  void performStart()
  {
    if (this.mChildFragmentManager != null)
    {
      this.mChildFragmentManager.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    }
    this.mState = 3;
    this.mCalled = false;
    onStart();
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onStart()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchStart();
    this.mLifecycleRegistry.a(c.a.ON_START);
    if (this.mView != null)
      this.mViewLifecycleRegistry.a(c.a.ON_START);
  }

  void performStop()
  {
    this.mLifecycleRegistry.a(c.a.ON_STOP);
    if (this.mChildFragmentManager != null)
      this.mChildFragmentManager.dispatchStop();
    this.mState = 2;
    this.mCalled = false;
    onStop();
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onStop()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
  }

  public void postponeEnterTransition()
  {
    ensureAnimationInfo().mEnterTransitionPostponed = true;
  }

  public void registerForContextMenu(View paramView)
  {
    paramView.setOnCreateContextMenuListener(this);
  }

  public final void requestPermissions(@NonNull String[] paramArrayOfString, int paramInt)
  {
    if (this.mHost == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to Activity");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.mHost.onRequestPermissionsFromFragment(this, paramArrayOfString, paramInt);
  }

  @NonNull
  public final FragmentActivity requireActivity()
  {
    FragmentActivity localFragmentActivity = getActivity();
    if (localFragmentActivity == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to an activity.");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return localFragmentActivity;
  }

  @NonNull
  public final Context requireContext()
  {
    Context localContext = getContext();
    if (localContext == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to a context.");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return localContext;
  }

  @NonNull
  public final FragmentManager requireFragmentManager()
  {
    FragmentManager localFragmentManager = getFragmentManager();
    if (localFragmentManager == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not associated with a fragment manager.");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return localFragmentManager;
  }

  @NonNull
  public final Object requireHost()
  {
    Object localObject = getHost();
    if (localObject == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to a host.");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return localObject;
  }

  void restoreChildFragmentState(@Nullable Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      Parcelable localParcelable = paramBundle.getParcelable("android:support:fragments");
      if (localParcelable != null)
      {
        if (this.mChildFragmentManager == null)
          instantiateChildFragmentManager();
        this.mChildFragmentManager.restoreAllState(localParcelable, this.mChildNonConfig);
        this.mChildNonConfig = null;
        this.mChildFragmentManager.dispatchCreate();
      }
    }
  }

  final void restoreViewState(Bundle paramBundle)
  {
    if (this.mSavedViewState != null)
    {
      this.mInnerView.restoreHierarchyState(this.mSavedViewState);
      this.mSavedViewState = null;
    }
    this.mCalled = false;
    onViewStateRestored(paramBundle);
    if (!this.mCalled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onViewStateRestored()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
  }

  public void setAllowEnterTransitionOverlap(boolean paramBoolean)
  {
    ensureAnimationInfo().mAllowEnterTransitionOverlap = Boolean.valueOf(paramBoolean);
  }

  public void setAllowReturnTransitionOverlap(boolean paramBoolean)
  {
    ensureAnimationInfo().mAllowReturnTransitionOverlap = Boolean.valueOf(paramBoolean);
  }

  void setAnimatingAway(View paramView)
  {
    ensureAnimationInfo().mAnimatingAway = paramView;
  }

  void setAnimator(Animator paramAnimator)
  {
    ensureAnimationInfo().mAnimator = paramAnimator;
  }

  public void setArguments(@Nullable Bundle paramBundle)
  {
    if ((this.mIndex >= 0) && (isStateSaved()))
      throw new IllegalStateException("Fragment already active and state has been saved");
    this.mArguments = paramBundle;
  }

  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback)
  {
    ensureAnimationInfo().mEnterTransitionCallback = paramSharedElementCallback;
  }

  public void setEnterTransition(@Nullable Object paramObject)
  {
    ensureAnimationInfo().mEnterTransition = paramObject;
  }

  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback)
  {
    ensureAnimationInfo().mExitTransitionCallback = paramSharedElementCallback;
  }

  public void setExitTransition(@Nullable Object paramObject)
  {
    ensureAnimationInfo().mExitTransition = paramObject;
  }

  public void setHasOptionsMenu(boolean paramBoolean)
  {
    if (this.mHasMenu != paramBoolean)
    {
      this.mHasMenu = paramBoolean;
      if ((isAdded()) && (!isHidden()))
        this.mHost.onSupportInvalidateOptionsMenu();
    }
  }

  void setHideReplaced(boolean paramBoolean)
  {
    ensureAnimationInfo().mIsHideReplaced = paramBoolean;
  }

  final void setIndex(int paramInt, Fragment paramFragment)
  {
    this.mIndex = paramInt;
    if (paramFragment != null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramFragment.mWho);
      localStringBuilder1.append(":");
      localStringBuilder1.append(this.mIndex);
      this.mWho = localStringBuilder1.toString();
    }
    else
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("android:fragment:");
      localStringBuilder2.append(this.mIndex);
      this.mWho = localStringBuilder2.toString();
    }
  }

  public void setInitialSavedState(@Nullable SavedState paramSavedState)
  {
    if (this.mIndex >= 0)
      throw new IllegalStateException("Fragment already active");
    Bundle localBundle;
    if ((paramSavedState != null) && (paramSavedState.mState != null))
      localBundle = paramSavedState.mState;
    else
      localBundle = null;
    this.mSavedFragmentState = localBundle;
  }

  public void setMenuVisibility(boolean paramBoolean)
  {
    if (this.mMenuVisible != paramBoolean)
    {
      this.mMenuVisible = paramBoolean;
      if ((this.mHasMenu) && (isAdded()) && (!isHidden()))
        this.mHost.onSupportInvalidateOptionsMenu();
    }
  }

  void setNextAnim(int paramInt)
  {
    if ((this.mAnimationInfo == null) && (paramInt == 0))
      return;
    ensureAnimationInfo().mNextAnim = paramInt;
  }

  void setNextTransition(int paramInt1, int paramInt2)
  {
    if ((this.mAnimationInfo == null) && (paramInt1 == 0) && (paramInt2 == 0))
      return;
    ensureAnimationInfo();
    this.mAnimationInfo.mNextTransition = paramInt1;
    this.mAnimationInfo.mNextTransitionStyle = paramInt2;
  }

  void setOnStartEnterTransitionListener(OnStartEnterTransitionListener paramOnStartEnterTransitionListener)
  {
    ensureAnimationInfo();
    if (paramOnStartEnterTransitionListener == this.mAnimationInfo.mStartEnterTransitionListener)
      return;
    if ((paramOnStartEnterTransitionListener != null) && (this.mAnimationInfo.mStartEnterTransitionListener != null))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Trying to set a replacement startPostponedEnterTransition on ");
      localStringBuilder.append(this);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    if (this.mAnimationInfo.mEnterTransitionPostponed)
      this.mAnimationInfo.mStartEnterTransitionListener = paramOnStartEnterTransitionListener;
    if (paramOnStartEnterTransitionListener != null)
      paramOnStartEnterTransitionListener.startListening();
  }

  public void setReenterTransition(@Nullable Object paramObject)
  {
    ensureAnimationInfo().mReenterTransition = paramObject;
  }

  public void setRetainInstance(boolean paramBoolean)
  {
    this.mRetainInstance = paramBoolean;
  }

  public void setReturnTransition(@Nullable Object paramObject)
  {
    ensureAnimationInfo().mReturnTransition = paramObject;
  }

  public void setSharedElementEnterTransition(@Nullable Object paramObject)
  {
    ensureAnimationInfo().mSharedElementEnterTransition = paramObject;
  }

  public void setSharedElementReturnTransition(@Nullable Object paramObject)
  {
    ensureAnimationInfo().mSharedElementReturnTransition = paramObject;
  }

  void setStateAfterAnimating(int paramInt)
  {
    ensureAnimationInfo().mStateAfterAnimating = paramInt;
  }

  public void setTargetFragment(@Nullable Fragment paramFragment, int paramInt)
  {
    FragmentManager localFragmentManager1 = getFragmentManager();
    FragmentManager localFragmentManager2;
    if (paramFragment != null)
      localFragmentManager2 = paramFragment.getFragmentManager();
    else
      localFragmentManager2 = null;
    if ((localFragmentManager1 != null) && (localFragmentManager2 != null) && (localFragmentManager1 != localFragmentManager2))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Fragment ");
      localStringBuilder2.append(paramFragment);
      localStringBuilder2.append(" must share the same FragmentManager to be set as a target fragment");
      throw new IllegalArgumentException(localStringBuilder2.toString());
    }
    for (Fragment localFragment = paramFragment; localFragment != null; localFragment = localFragment.getTargetFragment())
      if (localFragment == this)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Setting ");
        localStringBuilder1.append(paramFragment);
        localStringBuilder1.append(" as the target of ");
        localStringBuilder1.append(this);
        localStringBuilder1.append(" would create a target cycle");
        throw new IllegalArgumentException(localStringBuilder1.toString());
      }
    this.mTarget = paramFragment;
    this.mTargetRequestCode = paramInt;
  }

  public void setUserVisibleHint(boolean paramBoolean)
  {
    if ((!this.mUserVisibleHint) && (paramBoolean) && (this.mState < 3) && (this.mFragmentManager != null) && (isAdded()) && (this.mIsCreated))
      this.mFragmentManager.performPendingDeferredStart(this);
    this.mUserVisibleHint = paramBoolean;
    boolean bool;
    if ((this.mState < 3) && (!paramBoolean))
      bool = true;
    else
      bool = false;
    this.mDeferStart = bool;
    if (this.mSavedFragmentState != null)
      this.mSavedUserVisibleHint = Boolean.valueOf(paramBoolean);
  }

  public boolean shouldShowRequestPermissionRationale(@NonNull String paramString)
  {
    if (this.mHost != null)
      return this.mHost.onShouldShowRequestPermissionRationale(paramString);
    return false;
  }

  public void startActivity(Intent paramIntent)
  {
    startActivity(paramIntent, null);
  }

  public void startActivity(Intent paramIntent, @Nullable Bundle paramBundle)
  {
    if (this.mHost == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to Activity");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.mHost.onStartActivityFromFragment(this, paramIntent, -1, paramBundle);
  }

  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    startActivityForResult(paramIntent, paramInt, null);
  }

  public void startActivityForResult(Intent paramIntent, int paramInt, @Nullable Bundle paramBundle)
  {
    if (this.mHost == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to Activity");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.mHost.onStartActivityFromFragment(this, paramIntent, paramInt, paramBundle);
  }

  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle)
  {
    if (this.mHost == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" not attached to Activity");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.mHost.onStartIntentSenderFromFragment(this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }

  public void startPostponedEnterTransition()
  {
    if ((this.mFragmentManager != null) && (this.mFragmentManager.mHost != null))
    {
      if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper())
        this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Runnable()
        {
          public void run()
          {
            Fragment.this.callStartTransitionListener();
          }
        });
      else
        callStartTransitionListener();
    }
    else
      ensureAnimationInfo().mEnterTransitionPostponed = false;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, localStringBuilder);
    if (this.mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(this.mIndex);
    }
    if (this.mFragmentId != 0)
    {
      localStringBuilder.append(" id=0x");
      localStringBuilder.append(Integer.toHexString(this.mFragmentId));
    }
    if (this.mTag != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(this.mTag);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  public void unregisterForContextMenu(View paramView)
  {
    paramView.setOnCreateContextMenuListener(null);
  }

  static class AnimationInfo
  {
    Boolean mAllowEnterTransitionOverlap;
    Boolean mAllowReturnTransitionOverlap;
    View mAnimatingAway;
    Animator mAnimator;
    Object mEnterTransition = null;
    SharedElementCallback mEnterTransitionCallback = null;
    boolean mEnterTransitionPostponed;
    Object mExitTransition = null;
    SharedElementCallback mExitTransitionCallback = null;
    boolean mIsHideReplaced;
    int mNextAnim;
    int mNextTransition;
    int mNextTransitionStyle;
    Object mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
    Object mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    Object mSharedElementEnterTransition = null;
    Object mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    Fragment.OnStartEnterTransitionListener mStartEnterTransitionListener;
    int mStateAfterAnimating;
  }

  public static class InstantiationException extends RuntimeException
  {
    public InstantiationException(String paramString, Exception paramException)
    {
      super(paramException);
    }
  }

  static abstract interface OnStartEnterTransitionListener
  {
    public abstract void onStartEnterTransition();

    public abstract void startListening();
  }

  public static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public Fragment.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new Fragment.SavedState(paramAnonymousParcel, null);
      }

      public Fragment.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new Fragment.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }

      public Fragment.SavedState[] newArray(int paramAnonymousInt)
      {
        return new Fragment.SavedState[paramAnonymousInt];
      }
    };
    final Bundle mState;

    SavedState(Bundle paramBundle)
    {
      this.mState = paramBundle;
    }

    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      this.mState = paramParcel.readBundle();
      if ((paramClassLoader != null) && (this.mState != null))
        this.mState.setClassLoader(paramClassLoader);
    }

    public int describeContents()
    {
      return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeBundle(this.mState);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.Fragment
 * JD-Core Version:    0.6.1
 */