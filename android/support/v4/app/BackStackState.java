package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;

final class BackStackState
  implements Parcelable
{
  public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator()
  {
    public BackStackState createFromParcel(Parcel paramAnonymousParcel)
    {
      return new BackStackState(paramAnonymousParcel);
    }

    public BackStackState[] newArray(int paramAnonymousInt)
    {
      return new BackStackState[paramAnonymousInt];
    }
  };
  final int mBreadCrumbShortTitleRes;
  final CharSequence mBreadCrumbShortTitleText;
  final int mBreadCrumbTitleRes;
  final CharSequence mBreadCrumbTitleText;
  final int mIndex;
  final String mName;
  final int[] mOps;
  final boolean mReorderingAllowed;
  final ArrayList<String> mSharedElementSourceNames;
  final ArrayList<String> mSharedElementTargetNames;
  final int mTransition;
  final int mTransitionStyle;

  public BackStackState(Parcel paramParcel)
  {
    this.mOps = paramParcel.createIntArray();
    this.mTransition = paramParcel.readInt();
    this.mTransitionStyle = paramParcel.readInt();
    this.mName = paramParcel.readString();
    this.mIndex = paramParcel.readInt();
    this.mBreadCrumbTitleRes = paramParcel.readInt();
    this.mBreadCrumbTitleText = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    this.mBreadCrumbShortTitleRes = paramParcel.readInt();
    this.mBreadCrumbShortTitleText = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    this.mSharedElementSourceNames = paramParcel.createStringArrayList();
    this.mSharedElementTargetNames = paramParcel.createStringArrayList();
    boolean bool;
    if (paramParcel.readInt() != 0)
      bool = true;
    else
      bool = false;
    this.mReorderingAllowed = bool;
  }

  public BackStackState(BackStackRecord paramBackStackRecord)
  {
    int i = paramBackStackRecord.mOps.size();
    this.mOps = new int[i * 6];
    if (!paramBackStackRecord.mAddToBackStack)
      throw new IllegalStateException("Not on back stack");
    int j = 0;
    int i5;
    for (int k = 0; j < i; k = i5)
    {
      BackStackRecord.Op localOp = (BackStackRecord.Op)paramBackStackRecord.mOps.get(j);
      int[] arrayOfInt1 = this.mOps;
      int m = k + 1;
      arrayOfInt1[k] = localOp.cmd;
      int[] arrayOfInt2 = this.mOps;
      int n = m + 1;
      int i1;
      if (localOp.fragment != null)
        i1 = localOp.fragment.mIndex;
      else
        i1 = -1;
      arrayOfInt2[m] = i1;
      int[] arrayOfInt3 = this.mOps;
      int i2 = n + 1;
      arrayOfInt3[n] = localOp.enterAnim;
      int[] arrayOfInt4 = this.mOps;
      int i3 = i2 + 1;
      arrayOfInt4[i2] = localOp.exitAnim;
      int[] arrayOfInt5 = this.mOps;
      int i4 = i3 + 1;
      arrayOfInt5[i3] = localOp.popEnterAnim;
      int[] arrayOfInt6 = this.mOps;
      i5 = i4 + 1;
      arrayOfInt6[i4] = localOp.popExitAnim;
      j++;
    }
    this.mTransition = paramBackStackRecord.mTransition;
    this.mTransitionStyle = paramBackStackRecord.mTransitionStyle;
    this.mName = paramBackStackRecord.mName;
    this.mIndex = paramBackStackRecord.mIndex;
    this.mBreadCrumbTitleRes = paramBackStackRecord.mBreadCrumbTitleRes;
    this.mBreadCrumbTitleText = paramBackStackRecord.mBreadCrumbTitleText;
    this.mBreadCrumbShortTitleRes = paramBackStackRecord.mBreadCrumbShortTitleRes;
    this.mBreadCrumbShortTitleText = paramBackStackRecord.mBreadCrumbShortTitleText;
    this.mSharedElementSourceNames = paramBackStackRecord.mSharedElementSourceNames;
    this.mSharedElementTargetNames = paramBackStackRecord.mSharedElementTargetNames;
    this.mReorderingAllowed = paramBackStackRecord.mReorderingAllowed;
  }

  public int describeContents()
  {
    return 0;
  }

  public BackStackRecord instantiate(FragmentManagerImpl paramFragmentManagerImpl)
  {
    BackStackRecord localBackStackRecord = new BackStackRecord(paramFragmentManagerImpl);
    int i = 0;
    int j = 0;
    while (i < this.mOps.length)
    {
      BackStackRecord.Op localOp = new BackStackRecord.Op();
      int[] arrayOfInt1 = this.mOps;
      int k = i + 1;
      localOp.cmd = arrayOfInt1[i];
      if (FragmentManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Instantiate ");
        localStringBuilder.append(localBackStackRecord);
        localStringBuilder.append(" op #");
        localStringBuilder.append(j);
        localStringBuilder.append(" base fragment #");
        localStringBuilder.append(this.mOps[k]);
        Log.v("FragmentManager", localStringBuilder.toString());
      }
      int[] arrayOfInt2 = this.mOps;
      int m = k + 1;
      int n = arrayOfInt2[k];
      if (n >= 0)
        localOp.fragment = ((Fragment)paramFragmentManagerImpl.mActive.get(n));
      else
        localOp.fragment = null;
      int[] arrayOfInt3 = this.mOps;
      int i1 = m + 1;
      localOp.enterAnim = arrayOfInt3[m];
      int[] arrayOfInt4 = this.mOps;
      int i2 = i1 + 1;
      localOp.exitAnim = arrayOfInt4[i1];
      int[] arrayOfInt5 = this.mOps;
      int i3 = i2 + 1;
      localOp.popEnterAnim = arrayOfInt5[i2];
      int[] arrayOfInt6 = this.mOps;
      int i4 = i3 + 1;
      localOp.popExitAnim = arrayOfInt6[i3];
      localBackStackRecord.mEnterAnim = localOp.enterAnim;
      localBackStackRecord.mExitAnim = localOp.exitAnim;
      localBackStackRecord.mPopEnterAnim = localOp.popEnterAnim;
      localBackStackRecord.mPopExitAnim = localOp.popExitAnim;
      localBackStackRecord.addOp(localOp);
      j++;
      i = i4;
    }
    localBackStackRecord.mTransition = this.mTransition;
    localBackStackRecord.mTransitionStyle = this.mTransitionStyle;
    localBackStackRecord.mName = this.mName;
    localBackStackRecord.mIndex = this.mIndex;
    localBackStackRecord.mAddToBackStack = true;
    localBackStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
    localBackStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
    localBackStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
    localBackStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
    localBackStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
    localBackStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
    localBackStackRecord.mReorderingAllowed = this.mReorderingAllowed;
    localBackStackRecord.bumpBackStackNesting(1);
    return localBackStackRecord;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeIntArray(this.mOps);
    paramParcel.writeInt(this.mTransition);
    paramParcel.writeInt(this.mTransitionStyle);
    paramParcel.writeString(this.mName);
    paramParcel.writeInt(this.mIndex);
    paramParcel.writeInt(this.mBreadCrumbTitleRes);
    TextUtils.writeToParcel(this.mBreadCrumbTitleText, paramParcel, 0);
    paramParcel.writeInt(this.mBreadCrumbShortTitleRes);
    TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, paramParcel, 0);
    paramParcel.writeStringList(this.mSharedElementSourceNames);
    paramParcel.writeStringList(this.mSharedElementTargetNames);
    paramParcel.writeInt(this.mReorderingAllowed);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.BackStackState
 * JD-Core Version:    0.6.1
 */