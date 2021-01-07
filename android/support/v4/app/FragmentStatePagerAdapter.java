package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FragmentStatePagerAdapter extends PagerAdapter
{
  private static final boolean DEBUG = false;
  private static final String TAG = "FragmentStatePagerAdapt";
  private FragmentTransaction mCurTransaction = null;
  private Fragment mCurrentPrimaryItem = null;
  private final FragmentManager mFragmentManager;
  private ArrayList<Fragment> mFragments = new ArrayList();
  private ArrayList<Fragment.SavedState> mSavedState = new ArrayList();

  public FragmentStatePagerAdapter(FragmentManager paramFragmentManager)
  {
    this.mFragmentManager = paramFragmentManager;
  }

  public void destroyItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject)
  {
    Fragment localFragment = (Fragment)paramObject;
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction();
    while (this.mSavedState.size() <= paramInt)
      this.mSavedState.add(null);
    ArrayList localArrayList = this.mSavedState;
    Fragment.SavedState localSavedState;
    if (localFragment.isAdded())
      localSavedState = this.mFragmentManager.saveFragmentInstanceState(localFragment);
    else
      localSavedState = null;
    localArrayList.set(paramInt, localSavedState);
    this.mFragments.set(paramInt, null);
    this.mCurTransaction.remove(localFragment);
  }

  public void finishUpdate(@NonNull ViewGroup paramViewGroup)
  {
    if (this.mCurTransaction != null)
    {
      this.mCurTransaction.commitNowAllowingStateLoss();
      this.mCurTransaction = null;
    }
  }

  public abstract Fragment getItem(int paramInt);

  @NonNull
  public Object instantiateItem(@NonNull ViewGroup paramViewGroup, int paramInt)
  {
    if (this.mFragments.size() > paramInt)
    {
      Fragment localFragment2 = (Fragment)this.mFragments.get(paramInt);
      if (localFragment2 != null)
        return localFragment2;
    }
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction();
    Fragment localFragment1 = getItem(paramInt);
    if (this.mSavedState.size() > paramInt)
    {
      Fragment.SavedState localSavedState = (Fragment.SavedState)this.mSavedState.get(paramInt);
      if (localSavedState != null)
        localFragment1.setInitialSavedState(localSavedState);
    }
    while (this.mFragments.size() <= paramInt)
      this.mFragments.add(null);
    localFragment1.setMenuVisibility(false);
    localFragment1.setUserVisibleHint(false);
    this.mFragments.set(paramInt, localFragment1);
    this.mCurTransaction.add(paramViewGroup.getId(), localFragment1);
    return localFragment1;
  }

  public boolean isViewFromObject(@NonNull View paramView, @NonNull Object paramObject)
  {
    boolean bool;
    if (((Fragment)paramObject).getView() == paramView)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
    if (paramParcelable != null)
    {
      Bundle localBundle = (Bundle)paramParcelable;
      localBundle.setClassLoader(paramClassLoader);
      Parcelable[] arrayOfParcelable = localBundle.getParcelableArray("states");
      this.mSavedState.clear();
      this.mFragments.clear();
      if (arrayOfParcelable != null)
        for (int j = 0; j < arrayOfParcelable.length; j++)
          this.mSavedState.add((Fragment.SavedState)arrayOfParcelable[j]);
      Iterator localIterator = localBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (str.startsWith("f"))
        {
          int i = Integer.parseInt(str.substring(1));
          Fragment localFragment = this.mFragmentManager.getFragment(localBundle, str);
          if (localFragment != null)
          {
            while (this.mFragments.size() <= i)
              this.mFragments.add(null);
            localFragment.setMenuVisibility(false);
            this.mFragments.set(i, localFragment);
          }
          else
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Bad fragment at key ");
            localStringBuilder.append(str);
            Log.w("FragmentStatePagerAdapt", localStringBuilder.toString());
          }
        }
      }
    }
  }

  public Parcelable saveState()
  {
    Bundle localBundle;
    if (this.mSavedState.size() > 0)
    {
      localBundle = new Bundle();
      Fragment.SavedState[] arrayOfSavedState = new Fragment.SavedState[this.mSavedState.size()];
      this.mSavedState.toArray(arrayOfSavedState);
      localBundle.putParcelableArray("states", arrayOfSavedState);
    }
    else
    {
      localBundle = null;
    }
    for (int i = 0; i < this.mFragments.size(); i++)
    {
      Fragment localFragment = (Fragment)this.mFragments.get(i);
      if ((localFragment != null) && (localFragment.isAdded()))
      {
        if (localBundle == null)
          localBundle = new Bundle();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("f");
        localStringBuilder.append(i);
        String str = localStringBuilder.toString();
        this.mFragmentManager.putFragment(localBundle, str, localFragment);
      }
    }
    return localBundle;
  }

  public void setPrimaryItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject)
  {
    Fragment localFragment = (Fragment)paramObject;
    if (localFragment != this.mCurrentPrimaryItem)
    {
      if (this.mCurrentPrimaryItem != null)
      {
        this.mCurrentPrimaryItem.setMenuVisibility(false);
        this.mCurrentPrimaryItem.setUserVisibleHint(false);
      }
      localFragment.setMenuVisibility(true);
      localFragment.setUserVisibleHint(true);
      this.mCurrentPrimaryItem = localFragment;
    }
  }

  public void startUpdate(@NonNull ViewGroup paramViewGroup)
  {
    if (paramViewGroup.getId() == -1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ViewPager with adapter ");
      localStringBuilder.append(this);
      localStringBuilder.append(" requires a view id");
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.FragmentStatePagerAdapter
 * JD-Core Version:    0.6.1
 */