package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper
{
  private boolean mIsNestedScrollingEnabled;
  private ViewParent mNestedScrollingParentNonTouch;
  private ViewParent mNestedScrollingParentTouch;
  private int[] mTempNestedScrollConsumed;
  private final View mView;

  public NestedScrollingChildHelper(@NonNull View paramView)
  {
    this.mView = paramView;
  }

  private ViewParent getNestedScrollingParentForType(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 1:
      return this.mNestedScrollingParentNonTouch;
    case 0:
    }
    return this.mNestedScrollingParentTouch;
  }

  private void setNestedScrollingParentForType(int paramInt, ViewParent paramViewParent)
  {
    switch (paramInt)
    {
    default:
      break;
    case 1:
      this.mNestedScrollingParentNonTouch = paramViewParent;
      break;
    case 0:
      this.mNestedScrollingParentTouch = paramViewParent;
    }
  }

  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getNestedScrollingParentForType(0);
      if (localViewParent != null)
        return ViewParentCompat.onNestedFling(localViewParent, this.mView, paramFloat1, paramFloat2, paramBoolean);
    }
    return false;
  }

  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getNestedScrollingParentForType(0);
      if (localViewParent != null)
        return ViewParentCompat.onNestedPreFling(localViewParent, this.mView, paramFloat1, paramFloat2);
    }
    return false;
  }

  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfInt1, @Nullable int[] paramArrayOfInt2)
  {
    return dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, 0);
  }

  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfInt1, @Nullable int[] paramArrayOfInt2, int paramInt3)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getNestedScrollingParentForType(paramInt3);
      if (localViewParent == null)
        return false;
      boolean bool = true;
      if ((paramInt1 == 0) && (paramInt2 == 0))
      {
        if (paramArrayOfInt2 != null)
        {
          paramArrayOfInt2[0] = 0;
          paramArrayOfInt2[bool] = 0;
        }
      }
      else
      {
        int i;
        int j;
        if (paramArrayOfInt2 != null)
        {
          this.mView.getLocationInWindow(paramArrayOfInt2);
          int k = paramArrayOfInt2[0];
          int m = paramArrayOfInt2[bool];
          i = k;
          j = m;
        }
        else
        {
          i = 0;
          j = 0;
        }
        if (paramArrayOfInt1 == null)
        {
          if (this.mTempNestedScrollConsumed == null)
            this.mTempNestedScrollConsumed = new int[2];
          paramArrayOfInt1 = this.mTempNestedScrollConsumed;
        }
        paramArrayOfInt1[0] = 0;
        paramArrayOfInt1[bool] = 0;
        ViewParentCompat.onNestedPreScroll(localViewParent, this.mView, paramInt1, paramInt2, paramArrayOfInt1, paramInt3);
        if (paramArrayOfInt2 != null)
        {
          this.mView.getLocationInWindow(paramArrayOfInt2);
          paramArrayOfInt2[0] -= i;
          paramArrayOfInt2[bool] -= j;
        }
        if ((paramArrayOfInt1[0] == 0) && (paramArrayOfInt1[bool] == 0))
          bool = false;
        return bool;
      }
    }
    return false;
  }

  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, @Nullable int[] paramArrayOfInt)
  {
    return dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, 0);
  }

  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, @Nullable int[] paramArrayOfInt, int paramInt5)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getNestedScrollingParentForType(paramInt5);
      if (localViewParent == null)
        return false;
      if ((paramInt1 == 0) && (paramInt2 == 0) && (paramInt3 == 0) && (paramInt4 == 0))
      {
        if (paramArrayOfInt != null)
        {
          paramArrayOfInt[0] = 0;
          paramArrayOfInt[1] = 0;
        }
      }
      else
      {
        int i;
        int j;
        if (paramArrayOfInt != null)
        {
          this.mView.getLocationInWindow(paramArrayOfInt);
          int k = paramArrayOfInt[0];
          int m = paramArrayOfInt[1];
          i = k;
          j = m;
        }
        else
        {
          i = 0;
          j = 0;
        }
        ViewParentCompat.onNestedScroll(localViewParent, this.mView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
        if (paramArrayOfInt != null)
        {
          this.mView.getLocationInWindow(paramArrayOfInt);
          paramArrayOfInt[0] -= i;
          paramArrayOfInt[1] -= j;
        }
        return true;
      }
    }
    return false;
  }

  public boolean hasNestedScrollingParent()
  {
    return hasNestedScrollingParent(0);
  }

  public boolean hasNestedScrollingParent(int paramInt)
  {
    boolean bool;
    if (getNestedScrollingParentForType(paramInt) != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isNestedScrollingEnabled()
  {
    return this.mIsNestedScrollingEnabled;
  }

  public void onDetachedFromWindow()
  {
    ViewCompat.stopNestedScroll(this.mView);
  }

  public void onStopNestedScroll(@NonNull View paramView)
  {
    ViewCompat.stopNestedScroll(this.mView);
  }

  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    if (this.mIsNestedScrollingEnabled)
      ViewCompat.stopNestedScroll(this.mView);
    this.mIsNestedScrollingEnabled = paramBoolean;
  }

  public boolean startNestedScroll(int paramInt)
  {
    return startNestedScroll(paramInt, 0);
  }

  public boolean startNestedScroll(int paramInt1, int paramInt2)
  {
    if (hasNestedScrollingParent(paramInt2))
      return true;
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = this.mView.getParent();
      View localView = this.mView;
      while (localViewParent != null)
      {
        if (ViewParentCompat.onStartNestedScroll(localViewParent, localView, this.mView, paramInt1, paramInt2))
        {
          setNestedScrollingParentForType(paramInt2, localViewParent);
          ViewParentCompat.onNestedScrollAccepted(localViewParent, localView, this.mView, paramInt1, paramInt2);
          return true;
        }
        if ((localViewParent instanceof View))
          localView = (View)localViewParent;
        localViewParent = localViewParent.getParent();
      }
    }
    return false;
  }

  public void stopNestedScroll()
  {
    stopNestedScroll(0);
  }

  public void stopNestedScroll(int paramInt)
  {
    ViewParent localViewParent = getNestedScrollingParentForType(paramInt);
    if (localViewParent != null)
    {
      ViewParentCompat.onStopNestedScroll(localViewParent, this.mView, paramInt);
      setNestedScrollingParentForType(paramInt, null);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.NestedScrollingChildHelper
 * JD-Core Version:    0.6.1
 */