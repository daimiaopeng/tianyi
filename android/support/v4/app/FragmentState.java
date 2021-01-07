package android.support.v4.app;

import android.arch.lifecycle.p;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState
  implements Parcelable
{
  public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator()
  {
    public FragmentState createFromParcel(Parcel paramAnonymousParcel)
    {
      return new FragmentState(paramAnonymousParcel);
    }

    public FragmentState[] newArray(int paramAnonymousInt)
    {
      return new FragmentState[paramAnonymousInt];
    }
  };
  final Bundle mArguments;
  final String mClassName;
  final int mContainerId;
  final boolean mDetached;
  final int mFragmentId;
  final boolean mFromLayout;
  final boolean mHidden;
  final int mIndex;
  Fragment mInstance;
  final boolean mRetainInstance;
  Bundle mSavedFragmentState;
  final String mTag;

  FragmentState(Parcel paramParcel)
  {
    this.mClassName = paramParcel.readString();
    this.mIndex = paramParcel.readInt();
    boolean bool1;
    if (paramParcel.readInt() != 0)
      bool1 = true;
    else
      bool1 = false;
    this.mFromLayout = bool1;
    this.mFragmentId = paramParcel.readInt();
    this.mContainerId = paramParcel.readInt();
    this.mTag = paramParcel.readString();
    boolean bool2;
    if (paramParcel.readInt() != 0)
      bool2 = true;
    else
      bool2 = false;
    this.mRetainInstance = bool2;
    boolean bool3;
    if (paramParcel.readInt() != 0)
      bool3 = true;
    else
      bool3 = false;
    this.mDetached = bool3;
    this.mArguments = paramParcel.readBundle();
    int i = paramParcel.readInt();
    boolean bool4 = false;
    if (i != 0)
      bool4 = true;
    this.mHidden = bool4;
    this.mSavedFragmentState = paramParcel.readBundle();
  }

  FragmentState(Fragment paramFragment)
  {
    this.mClassName = paramFragment.getClass().getName();
    this.mIndex = paramFragment.mIndex;
    this.mFromLayout = paramFragment.mFromLayout;
    this.mFragmentId = paramFragment.mFragmentId;
    this.mContainerId = paramFragment.mContainerId;
    this.mTag = paramFragment.mTag;
    this.mRetainInstance = paramFragment.mRetainInstance;
    this.mDetached = paramFragment.mDetached;
    this.mArguments = paramFragment.mArguments;
    this.mHidden = paramFragment.mHidden;
  }

  public int describeContents()
  {
    return 0;
  }

  public Fragment instantiate(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment, FragmentManagerNonConfig paramFragmentManagerNonConfig, p paramp)
  {
    if (this.mInstance == null)
    {
      Context localContext = paramFragmentHostCallback.getContext();
      if (this.mArguments != null)
        this.mArguments.setClassLoader(localContext.getClassLoader());
      if (paramFragmentContainer != null)
        this.mInstance = paramFragmentContainer.instantiate(localContext, this.mClassName, this.mArguments);
      else
        this.mInstance = Fragment.instantiate(localContext, this.mClassName, this.mArguments);
      if (this.mSavedFragmentState != null)
      {
        this.mSavedFragmentState.setClassLoader(localContext.getClassLoader());
        this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
      }
      this.mInstance.setIndex(this.mIndex, paramFragment);
      this.mInstance.mFromLayout = this.mFromLayout;
      this.mInstance.mRestored = true;
      this.mInstance.mFragmentId = this.mFragmentId;
      this.mInstance.mContainerId = this.mContainerId;
      this.mInstance.mTag = this.mTag;
      this.mInstance.mRetainInstance = this.mRetainInstance;
      this.mInstance.mDetached = this.mDetached;
      this.mInstance.mHidden = this.mHidden;
      this.mInstance.mFragmentManager = paramFragmentHostCallback.mFragmentManager;
      if (FragmentManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Instantiated fragment ");
        localStringBuilder.append(this.mInstance);
        Log.v("FragmentManager", localStringBuilder.toString());
      }
    }
    this.mInstance.mChildNonConfig = paramFragmentManagerNonConfig;
    this.mInstance.mViewModelStore = paramp;
    return this.mInstance;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mClassName);
    paramParcel.writeInt(this.mIndex);
    paramParcel.writeInt(this.mFromLayout);
    paramParcel.writeInt(this.mFragmentId);
    paramParcel.writeInt(this.mContainerId);
    paramParcel.writeString(this.mTag);
    paramParcel.writeInt(this.mRetainInstance);
    paramParcel.writeInt(this.mDetached);
    paramParcel.writeBundle(this.mArguments);
    paramParcel.writeInt(this.mHidden);
    paramParcel.writeBundle(this.mSavedFragmentState);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.FragmentState
 * JD-Core Version:    0.6.1
 */