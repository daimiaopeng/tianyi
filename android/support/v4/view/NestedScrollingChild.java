package android.support.v4.view;

import android.support.annotation.Nullable;

public abstract interface NestedScrollingChild
{
  public abstract boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean);

  public abstract boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2);

  public abstract boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfInt1, @Nullable int[] paramArrayOfInt2);

  public abstract boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, @Nullable int[] paramArrayOfInt);

  public abstract boolean hasNestedScrollingParent();

  public abstract boolean isNestedScrollingEnabled();

  public abstract void setNestedScrollingEnabled(boolean paramBoolean);

  public abstract boolean startNestedScroll(int paramInt);

  public abstract void stopNestedScroll();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.NestedScrollingChild
 * JD-Core Version:    0.6.1
 */