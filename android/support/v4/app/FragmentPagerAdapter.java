package android.support.v4.app;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentPagerAdapter extends PagerAdapter
{
  private static final boolean DEBUG = false;
  private static final String TAG = "FragmentPagerAdapter";
  private FragmentTransaction mCurTransaction = null;
  private Fragment mCurrentPrimaryItem = null;
  private final FragmentManager mFragmentManager;

  public FragmentPagerAdapter(FragmentManager paramFragmentManager)
  {
    this.mFragmentManager = paramFragmentManager;
  }

  private static String makeFragmentName(int paramInt, long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("android:switcher:");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(":");
    localStringBuilder.append(paramLong);
    return localStringBuilder.toString();
  }

  public void destroyItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject)
  {
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction();
    this.mCurTransaction.detach((Fragment)paramObject);
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

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  @NonNull
  public Object instantiateItem(@NonNull ViewGroup paramViewGroup, int paramInt)
  {
    if (this.mCurTransaction == null)
      this.mCurTransaction = this.mFragmentManager.beginTransaction();
    long l = getItemId(paramInt);
    String str = makeFragmentName(paramViewGroup.getId(), l);
    Fragment localFragment = this.mFragmentManager.findFragmentByTag(str);
    if (localFragment != null)
    {
      this.mCurTransaction.attach(localFragment);
    }
    else
    {
      localFragment = getItem(paramInt);
      this.mCurTransaction.add(paramViewGroup.getId(), localFragment, makeFragmentName(paramViewGroup.getId(), l));
    }
    if (localFragment != this.mCurrentPrimaryItem)
    {
      localFragment.setMenuVisibility(false);
      localFragment.setUserVisibleHint(false);
    }
    return localFragment;
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
  }

  public Parcelable saveState()
  {
    return null;
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
 * Qualified Name:     android.support.v4.app.FragmentPagerAdapter
 * JD-Core Version:    0.6.1
 */