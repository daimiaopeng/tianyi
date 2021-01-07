package android.support.v4.app;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction
  implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator
{
  static final int OP_ADD = 1;
  static final int OP_ATTACH = 7;
  static final int OP_DETACH = 6;
  static final int OP_HIDE = 4;
  static final int OP_NULL = 0;
  static final int OP_REMOVE = 3;
  static final int OP_REPLACE = 2;
  static final int OP_SET_PRIMARY_NAV = 8;
  static final int OP_SHOW = 5;
  static final int OP_UNSET_PRIMARY_NAV = 9;
  static final String TAG = "FragmentManager";
  boolean mAddToBackStack;
  boolean mAllowAddToBackStack = true;
  int mBreadCrumbShortTitleRes;
  CharSequence mBreadCrumbShortTitleText;
  int mBreadCrumbTitleRes;
  CharSequence mBreadCrumbTitleText;
  ArrayList<Runnable> mCommitRunnables;
  boolean mCommitted;
  int mEnterAnim;
  int mExitAnim;
  int mIndex = -1;
  final FragmentManagerImpl mManager;

  @Nullable
  String mName;
  ArrayList<Op> mOps = new ArrayList();
  int mPopEnterAnim;
  int mPopExitAnim;
  boolean mReorderingAllowed = false;
  ArrayList<String> mSharedElementSourceNames;
  ArrayList<String> mSharedElementTargetNames;
  int mTransition;
  int mTransitionStyle;

  public BackStackRecord(FragmentManagerImpl paramFragmentManagerImpl)
  {
    this.mManager = paramFragmentManagerImpl;
  }

  private void doAddOp(int paramInt1, Fragment paramFragment, @Nullable String paramString, int paramInt2)
  {
    Class localClass = paramFragment.getClass();
    int i = localClass.getModifiers();
    if ((!localClass.isAnonymousClass()) && (Modifier.isPublic(i)) && ((!localClass.isMemberClass()) || (Modifier.isStatic(i))))
    {
      paramFragment.mFragmentManager = this.mManager;
      if (paramString != null)
      {
        if ((paramFragment.mTag != null) && (!paramString.equals(paramFragment.mTag)))
        {
          StringBuilder localStringBuilder4 = new StringBuilder();
          localStringBuilder4.append("Can't change tag of fragment ");
          localStringBuilder4.append(paramFragment);
          localStringBuilder4.append(": was ");
          localStringBuilder4.append(paramFragment.mTag);
          localStringBuilder4.append(" now ");
          localStringBuilder4.append(paramString);
          throw new IllegalStateException(localStringBuilder4.toString());
        }
        paramFragment.mTag = paramString;
      }
      if (paramInt1 != 0)
      {
        if (paramInt1 == -1)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("Can't add fragment ");
          localStringBuilder2.append(paramFragment);
          localStringBuilder2.append(" with tag ");
          localStringBuilder2.append(paramString);
          localStringBuilder2.append(" to container view with no id");
          throw new IllegalArgumentException(localStringBuilder2.toString());
        }
        if ((paramFragment.mFragmentId != 0) && (paramFragment.mFragmentId != paramInt1))
        {
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("Can't change container ID of fragment ");
          localStringBuilder3.append(paramFragment);
          localStringBuilder3.append(": was ");
          localStringBuilder3.append(paramFragment.mFragmentId);
          localStringBuilder3.append(" now ");
          localStringBuilder3.append(paramInt1);
          throw new IllegalStateException(localStringBuilder3.toString());
        }
        paramFragment.mFragmentId = paramInt1;
        paramFragment.mContainerId = paramInt1;
      }
      addOp(new Op(paramInt2, paramFragment));
      return;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Fragment ");
    localStringBuilder1.append(localClass.getCanonicalName());
    localStringBuilder1.append(" must be a public static class to be  properly recreated from");
    localStringBuilder1.append(" instance state.");
    throw new IllegalStateException(localStringBuilder1.toString());
  }

  private static boolean isFragmentPostponed(Op paramOp)
  {
    Fragment localFragment = paramOp.fragment;
    boolean bool;
    if ((localFragment != null) && (localFragment.mAdded) && (localFragment.mView != null) && (!localFragment.mDetached) && (!localFragment.mHidden) && (localFragment.isPostponed()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public FragmentTransaction add(int paramInt, Fragment paramFragment)
  {
    doAddOp(paramInt, paramFragment, null, 1);
    return this;
  }

  public FragmentTransaction add(int paramInt, Fragment paramFragment, @Nullable String paramString)
  {
    doAddOp(paramInt, paramFragment, paramString, 1);
    return this;
  }

  public FragmentTransaction add(Fragment paramFragment, @Nullable String paramString)
  {
    doAddOp(0, paramFragment, paramString, 1);
    return this;
  }

  void addOp(Op paramOp)
  {
    this.mOps.add(paramOp);
    paramOp.enterAnim = this.mEnterAnim;
    paramOp.exitAnim = this.mExitAnim;
    paramOp.popEnterAnim = this.mPopEnterAnim;
    paramOp.popExitAnim = this.mPopExitAnim;
  }

  public FragmentTransaction addSharedElement(View paramView, String paramString)
  {
    if (FragmentTransition.supportsTransition())
    {
      String str = ViewCompat.getTransitionName(paramView);
      if (str == null)
        throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
      if (this.mSharedElementSourceNames == null)
      {
        this.mSharedElementSourceNames = new ArrayList();
        this.mSharedElementTargetNames = new ArrayList();
      }
      else
      {
        if (this.mSharedElementTargetNames.contains(paramString))
        {
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append("A shared element with the target name '");
          localStringBuilder1.append(paramString);
          localStringBuilder1.append("' has already been added to the transaction.");
          throw new IllegalArgumentException(localStringBuilder1.toString());
        }
        if (this.mSharedElementSourceNames.contains(str))
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("A shared element with the source name '");
          localStringBuilder2.append(str);
          localStringBuilder2.append(" has already been added to the transaction.");
          throw new IllegalArgumentException(localStringBuilder2.toString());
        }
      }
      this.mSharedElementSourceNames.add(str);
      this.mSharedElementTargetNames.add(paramString);
    }
    return this;
  }

  public FragmentTransaction addToBackStack(@Nullable String paramString)
  {
    if (!this.mAllowAddToBackStack)
      throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    this.mAddToBackStack = true;
    this.mName = paramString;
    return this;
  }

  public FragmentTransaction attach(Fragment paramFragment)
  {
    addOp(new Op(7, paramFragment));
    return this;
  }

  void bumpBackStackNesting(int paramInt)
  {
    if (!this.mAddToBackStack)
      return;
    if (FragmentManagerImpl.DEBUG)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Bump nesting in ");
      localStringBuilder1.append(this);
      localStringBuilder1.append(" by ");
      localStringBuilder1.append(paramInt);
      Log.v("FragmentManager", localStringBuilder1.toString());
    }
    int i = this.mOps.size();
    for (int j = 0; j < i; j++)
    {
      Op localOp = (Op)this.mOps.get(j);
      if (localOp.fragment != null)
      {
        Fragment localFragment = localOp.fragment;
        localFragment.mBackStackNesting = (paramInt + localFragment.mBackStackNesting);
        if (FragmentManagerImpl.DEBUG)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("Bump nesting of ");
          localStringBuilder2.append(localOp.fragment);
          localStringBuilder2.append(" to ");
          localStringBuilder2.append(localOp.fragment.mBackStackNesting);
          Log.v("FragmentManager", localStringBuilder2.toString());
        }
      }
    }
  }

  public int commit()
  {
    return commitInternal(false);
  }

  public int commitAllowingStateLoss()
  {
    return commitInternal(true);
  }

  int commitInternal(boolean paramBoolean)
  {
    if (this.mCommitted)
      throw new IllegalStateException("commit already called");
    if (FragmentManagerImpl.DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Commit: ");
      localStringBuilder.append(this);
      Log.v("FragmentManager", localStringBuilder.toString());
      PrintWriter localPrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
      dump("  ", null, localPrintWriter, null);
      localPrintWriter.close();
    }
    this.mCommitted = true;
    if (this.mAddToBackStack)
      this.mIndex = this.mManager.allocBackStackIndex(this);
    else
      this.mIndex = -1;
    this.mManager.enqueueAction(this, paramBoolean);
    return this.mIndex;
  }

  public void commitNow()
  {
    disallowAddToBackStack();
    this.mManager.execSingleAction(this, false);
  }

  public void commitNowAllowingStateLoss()
  {
    disallowAddToBackStack();
    this.mManager.execSingleAction(this, true);
  }

  public FragmentTransaction detach(Fragment paramFragment)
  {
    addOp(new Op(6, paramFragment));
    return this;
  }

  public FragmentTransaction disallowAddToBackStack()
  {
    if (this.mAddToBackStack)
      throw new IllegalStateException("This transaction is already being added to the back stack");
    this.mAllowAddToBackStack = false;
    return this;
  }

  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    dump(paramString, paramPrintWriter, true);
  }

  public void dump(String paramString, PrintWriter paramPrintWriter, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mName=");
      paramPrintWriter.print(this.mName);
      paramPrintWriter.print(" mIndex=");
      paramPrintWriter.print(this.mIndex);
      paramPrintWriter.print(" mCommitted=");
      paramPrintWriter.println(this.mCommitted);
      if (this.mTransition != 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mTransition=#");
        paramPrintWriter.print(Integer.toHexString(this.mTransition));
        paramPrintWriter.print(" mTransitionStyle=#");
        paramPrintWriter.println(Integer.toHexString(this.mTransitionStyle));
      }
      if ((this.mEnterAnim != 0) || (this.mExitAnim != 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(this.mEnterAnim));
        paramPrintWriter.print(" mExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(this.mExitAnim));
      }
      if ((this.mPopEnterAnim != 0) || (this.mPopExitAnim != 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mPopEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(this.mPopEnterAnim));
        paramPrintWriter.print(" mPopExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(this.mPopExitAnim));
      }
      if ((this.mBreadCrumbTitleRes != 0) || (this.mBreadCrumbTitleText != null))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
        paramPrintWriter.print(" mBreadCrumbTitleText=");
        paramPrintWriter.println(this.mBreadCrumbTitleText);
      }
      if ((this.mBreadCrumbShortTitleRes != 0) || (this.mBreadCrumbShortTitleText != null))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbShortTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
        paramPrintWriter.print(" mBreadCrumbShortTitleText=");
        paramPrintWriter.println(this.mBreadCrumbShortTitleText);
      }
    }
    if (!this.mOps.isEmpty())
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Operations:");
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramString);
      localStringBuilder1.append("    ");
      localStringBuilder1.toString();
      int i = this.mOps.size();
      for (int j = 0; j < i; j++)
      {
        Op localOp = (Op)this.mOps.get(j);
        String str;
        switch (localOp.cmd)
        {
        default:
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("cmd=");
          localStringBuilder2.append(localOp.cmd);
          str = localStringBuilder2.toString();
          break;
        case 9:
          str = "UNSET_PRIMARY_NAV";
          break;
        case 8:
          str = "SET_PRIMARY_NAV";
          break;
        case 7:
          str = "ATTACH";
          break;
        case 6:
          str = "DETACH";
          break;
        case 5:
          str = "SHOW";
          break;
        case 4:
          str = "HIDE";
          break;
        case 3:
          str = "REMOVE";
          break;
        case 2:
          str = "REPLACE";
          break;
        case 1:
          str = "ADD";
          break;
        case 0:
          str = "NULL";
        }
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  Op #");
        paramPrintWriter.print(j);
        paramPrintWriter.print(": ");
        paramPrintWriter.print(str);
        paramPrintWriter.print(" ");
        paramPrintWriter.println(localOp.fragment);
        if (paramBoolean)
        {
          if ((localOp.enterAnim != 0) || (localOp.exitAnim != 0))
          {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("enterAnim=#");
            paramPrintWriter.print(Integer.toHexString(localOp.enterAnim));
            paramPrintWriter.print(" exitAnim=#");
            paramPrintWriter.println(Integer.toHexString(localOp.exitAnim));
          }
          if ((localOp.popEnterAnim != 0) || (localOp.popExitAnim != 0))
          {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("popEnterAnim=#");
            paramPrintWriter.print(Integer.toHexString(localOp.popEnterAnim));
            paramPrintWriter.print(" popExitAnim=#");
            paramPrintWriter.println(Integer.toHexString(localOp.popExitAnim));
          }
        }
      }
    }
  }

  void executeOps()
  {
    int i = this.mOps.size();
    for (int j = 0; j < i; j++)
    {
      Op localOp = (Op)this.mOps.get(j);
      Fragment localFragment = localOp.fragment;
      if (localFragment != null)
        localFragment.setNextTransition(this.mTransition, this.mTransitionStyle);
      int k = localOp.cmd;
      if (k != 1)
      {
        switch (k)
        {
        default:
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown cmd: ");
          localStringBuilder.append(localOp.cmd);
          throw new IllegalArgumentException(localStringBuilder.toString());
        case 9:
          this.mManager.setPrimaryNavigationFragment(null);
          break;
        case 8:
          this.mManager.setPrimaryNavigationFragment(localFragment);
          break;
        case 7:
          localFragment.setNextAnim(localOp.enterAnim);
          this.mManager.attachFragment(localFragment);
          break;
        case 6:
          localFragment.setNextAnim(localOp.exitAnim);
          this.mManager.detachFragment(localFragment);
          break;
        case 5:
          localFragment.setNextAnim(localOp.enterAnim);
          this.mManager.showFragment(localFragment);
          break;
        case 4:
          localFragment.setNextAnim(localOp.exitAnim);
          this.mManager.hideFragment(localFragment);
          break;
        case 3:
          localFragment.setNextAnim(localOp.exitAnim);
          this.mManager.removeFragment(localFragment);
          break;
        }
      }
      else
      {
        localFragment.setNextAnim(localOp.enterAnim);
        this.mManager.addFragment(localFragment, false);
      }
      if ((!this.mReorderingAllowed) && (localOp.cmd != 1) && (localFragment != null))
        this.mManager.moveFragmentToExpectedState(localFragment);
    }
    if (!this.mReorderingAllowed)
      this.mManager.moveToState(this.mManager.mCurState, true);
  }

  void executePopOps(boolean paramBoolean)
  {
    for (int i = this.mOps.size() - 1; i >= 0; i--)
    {
      Op localOp = (Op)this.mOps.get(i);
      Fragment localFragment = localOp.fragment;
      if (localFragment != null)
        localFragment.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
      int j = localOp.cmd;
      if (j != 1)
      {
        switch (j)
        {
        default:
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown cmd: ");
          localStringBuilder.append(localOp.cmd);
          throw new IllegalArgumentException(localStringBuilder.toString());
        case 9:
          this.mManager.setPrimaryNavigationFragment(localFragment);
          break;
        case 8:
          this.mManager.setPrimaryNavigationFragment(null);
          break;
        case 7:
          localFragment.setNextAnim(localOp.popExitAnim);
          this.mManager.detachFragment(localFragment);
          break;
        case 6:
          localFragment.setNextAnim(localOp.popEnterAnim);
          this.mManager.attachFragment(localFragment);
          break;
        case 5:
          localFragment.setNextAnim(localOp.popExitAnim);
          this.mManager.hideFragment(localFragment);
          break;
        case 4:
          localFragment.setNextAnim(localOp.popEnterAnim);
          this.mManager.showFragment(localFragment);
          break;
        case 3:
          localFragment.setNextAnim(localOp.popEnterAnim);
          this.mManager.addFragment(localFragment, false);
          break;
        }
      }
      else
      {
        localFragment.setNextAnim(localOp.popExitAnim);
        this.mManager.removeFragment(localFragment);
      }
      if ((!this.mReorderingAllowed) && (localOp.cmd != 3) && (localFragment != null))
        this.mManager.moveFragmentToExpectedState(localFragment);
    }
    if ((!this.mReorderingAllowed) && (paramBoolean))
      this.mManager.moveToState(this.mManager.mCurState, true);
  }

  Fragment expandOps(ArrayList<Fragment> paramArrayList, Fragment paramFragment)
  {
    Object localObject1 = paramFragment;
    for (int i = 0; i < this.mOps.size(); i++)
    {
      Op localOp1 = (Op)this.mOps.get(i);
      switch (localOp1.cmd)
      {
      case 4:
      case 5:
      default:
        break;
      case 8:
        this.mOps.add(i, new Op(9, (Fragment)localObject1));
        i++;
        localObject1 = localOp1.fragment;
        break;
      case 3:
      case 6:
        paramArrayList.remove(localOp1.fragment);
        if (localOp1.fragment == localObject1)
        {
          this.mOps.add(i, new Op(9, localOp1.fragment));
          i++;
          localObject1 = null;
        }
        break;
      case 2:
        Fragment localFragment1 = localOp1.fragment;
        int j = localFragment1.mContainerId;
        int k = paramArrayList.size() - 1;
        Object localObject2 = localObject1;
        int m = i;
        int n = 0;
        while (k >= 0)
        {
          Fragment localFragment2 = (Fragment)paramArrayList.get(k);
          if (localFragment2.mContainerId == j)
            if (localFragment2 == localFragment1)
            {
              n = 1;
            }
            else
            {
              if (localFragment2 == localObject2)
              {
                this.mOps.add(m, new Op(9, localFragment2));
                m++;
                localObject2 = null;
              }
              Op localOp2 = new Op(3, localFragment2);
              localOp2.enterAnim = localOp1.enterAnim;
              localOp2.popEnterAnim = localOp1.popEnterAnim;
              localOp2.exitAnim = localOp1.exitAnim;
              localOp2.popExitAnim = localOp1.popExitAnim;
              this.mOps.add(m, localOp2);
              paramArrayList.remove(localFragment2);
              m++;
            }
          k--;
        }
        if (n != 0)
        {
          this.mOps.remove(m);
          m--;
        }
        while (true)
        {
          i = m;
          break;
          localOp1.cmd = 1;
          paramArrayList.add(localFragment1);
        }
        localObject1 = localObject2;
        break;
      case 1:
      case 7:
        paramArrayList.add(localOp1.fragment);
      }
    }
    return localObject1;
  }

  public boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    if (FragmentManagerImpl.DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Run: ");
      localStringBuilder.append(this);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    paramArrayList.add(this);
    paramArrayList1.add(Boolean.valueOf(false));
    if (this.mAddToBackStack)
      this.mManager.addBackStackState(this);
    return true;
  }

  @Nullable
  public CharSequence getBreadCrumbShortTitle()
  {
    if (this.mBreadCrumbShortTitleRes != 0)
      return this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
    return this.mBreadCrumbShortTitleText;
  }

  public int getBreadCrumbShortTitleRes()
  {
    return this.mBreadCrumbShortTitleRes;
  }

  @Nullable
  public CharSequence getBreadCrumbTitle()
  {
    if (this.mBreadCrumbTitleRes != 0)
      return this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
    return this.mBreadCrumbTitleText;
  }

  public int getBreadCrumbTitleRes()
  {
    return this.mBreadCrumbTitleRes;
  }

  public int getId()
  {
    return this.mIndex;
  }

  @Nullable
  public String getName()
  {
    return this.mName;
  }

  public int getTransition()
  {
    return this.mTransition;
  }

  public int getTransitionStyle()
  {
    return this.mTransitionStyle;
  }

  public FragmentTransaction hide(Fragment paramFragment)
  {
    addOp(new Op(4, paramFragment));
    return this;
  }

  boolean interactsWith(int paramInt)
  {
    int i = this.mOps.size();
    for (int j = 0; j < i; j++)
    {
      Op localOp = (Op)this.mOps.get(j);
      int k;
      if (localOp.fragment != null)
        k = localOp.fragment.mContainerId;
      else
        k = 0;
      if ((k != 0) && (k == paramInt))
        return true;
    }
    return false;
  }

  boolean interactsWith(ArrayList<BackStackRecord> paramArrayList, int paramInt1, int paramInt2)
  {
    if (paramInt2 == paramInt1)
      return false;
    int i = this.mOps.size();
    int j = 0;
    int k = -1;
    while (j < i)
    {
      Op localOp1 = (Op)this.mOps.get(j);
      int m;
      if (localOp1.fragment != null)
        m = localOp1.fragment.mContainerId;
      else
        m = 0;
      if ((m != 0) && (m != k))
      {
        for (int n = paramInt1; n < paramInt2; n++)
        {
          BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(n);
          int i1 = localBackStackRecord.mOps.size();
          for (int i2 = 0; i2 < i1; i2++)
          {
            Op localOp2 = (Op)localBackStackRecord.mOps.get(i2);
            int i3;
            if (localOp2.fragment != null)
              i3 = localOp2.fragment.mContainerId;
            else
              i3 = 0;
            if (i3 == m)
              return true;
          }
        }
        k = m;
      }
      j++;
    }
    return false;
  }

  public boolean isAddToBackStackAllowed()
  {
    return this.mAllowAddToBackStack;
  }

  public boolean isEmpty()
  {
    return this.mOps.isEmpty();
  }

  boolean isPostponed()
  {
    for (int i = 0; i < this.mOps.size(); i++)
      if (isFragmentPostponed((Op)this.mOps.get(i)))
        return true;
    return false;
  }

  public FragmentTransaction remove(Fragment paramFragment)
  {
    addOp(new Op(3, paramFragment));
    return this;
  }

  public FragmentTransaction replace(int paramInt, Fragment paramFragment)
  {
    return replace(paramInt, paramFragment, null);
  }

  public FragmentTransaction replace(int paramInt, Fragment paramFragment, @Nullable String paramString)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException("Must use non-zero containerViewId");
    doAddOp(paramInt, paramFragment, paramString, 2);
    return this;
  }

  public FragmentTransaction runOnCommit(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      throw new IllegalArgumentException("runnable cannot be null");
    disallowAddToBackStack();
    if (this.mCommitRunnables == null)
      this.mCommitRunnables = new ArrayList();
    this.mCommitRunnables.add(paramRunnable);
    return this;
  }

  public void runOnCommitRunnables()
  {
    if (this.mCommitRunnables != null)
    {
      int i = 0;
      int j = this.mCommitRunnables.size();
      while (i < j)
      {
        ((Runnable)this.mCommitRunnables.get(i)).run();
        i++;
      }
      this.mCommitRunnables = null;
    }
  }

  public FragmentTransaction setAllowOptimization(boolean paramBoolean)
  {
    return setReorderingAllowed(paramBoolean);
  }

  public FragmentTransaction setBreadCrumbShortTitle(int paramInt)
  {
    this.mBreadCrumbShortTitleRes = paramInt;
    this.mBreadCrumbShortTitleText = null;
    return this;
  }

  public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence paramCharSequence)
  {
    this.mBreadCrumbShortTitleRes = 0;
    this.mBreadCrumbShortTitleText = paramCharSequence;
    return this;
  }

  public FragmentTransaction setBreadCrumbTitle(int paramInt)
  {
    this.mBreadCrumbTitleRes = paramInt;
    this.mBreadCrumbTitleText = null;
    return this;
  }

  public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence paramCharSequence)
  {
    this.mBreadCrumbTitleRes = 0;
    this.mBreadCrumbTitleText = paramCharSequence;
    return this;
  }

  public FragmentTransaction setCustomAnimations(int paramInt1, int paramInt2)
  {
    return setCustomAnimations(paramInt1, paramInt2, 0, 0);
  }

  public FragmentTransaction setCustomAnimations(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mEnterAnim = paramInt1;
    this.mExitAnim = paramInt2;
    this.mPopEnterAnim = paramInt3;
    this.mPopExitAnim = paramInt4;
    return this;
  }

  void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener paramOnStartEnterTransitionListener)
  {
    for (int i = 0; i < this.mOps.size(); i++)
    {
      Op localOp = (Op)this.mOps.get(i);
      if (isFragmentPostponed(localOp))
        localOp.fragment.setOnStartEnterTransitionListener(paramOnStartEnterTransitionListener);
    }
  }

  public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment paramFragment)
  {
    addOp(new Op(8, paramFragment));
    return this;
  }

  public FragmentTransaction setReorderingAllowed(boolean paramBoolean)
  {
    this.mReorderingAllowed = paramBoolean;
    return this;
  }

  public FragmentTransaction setTransition(int paramInt)
  {
    this.mTransition = paramInt;
    return this;
  }

  public FragmentTransaction setTransitionStyle(int paramInt)
  {
    this.mTransitionStyle = paramInt;
    return this;
  }

  public FragmentTransaction show(Fragment paramFragment)
  {
    addOp(new Op(5, paramFragment));
    return this;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("BackStackEntry{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    if (this.mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(this.mIndex);
    }
    if (this.mName != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(this.mName);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  Fragment trackAddedFragmentsInPop(ArrayList<Fragment> paramArrayList, Fragment paramFragment)
  {
    for (int i = 0; i < this.mOps.size(); i++)
    {
      Op localOp = (Op)this.mOps.get(i);
      int j = localOp.cmd;
      if (j != 1)
      {
        if (j != 3);
        switch (j)
        {
        default:
          break;
        case 9:
          paramFragment = localOp.fragment;
          break;
        case 8:
          paramFragment = null;
          break;
        case 6:
          paramArrayList.add(localOp.fragment);
          break;
        case 7:
        }
      }
      else
      {
        paramArrayList.remove(localOp.fragment);
      }
    }
    return paramFragment;
  }

  static final class Op
  {
    int cmd;
    int enterAnim;
    int exitAnim;
    Fragment fragment;
    int popEnterAnim;
    int popExitAnim;

    Op()
    {
    }

    Op(int paramInt, Fragment paramFragment)
    {
      this.cmd = paramInt;
      this.fragment = paramFragment;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.BackStackRecord
 * JD-Core Version:    0.6.1
 */