package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper
{
  private int mNestedScrollAxes;
  private final ViewGroup mViewGroup;

  public NestedScrollingParentHelper(@NonNull ViewGroup paramViewGroup)
  {
    this.mViewGroup = paramViewGroup;
  }

  public int getNestedScrollAxes()
  {
    return this.mNestedScrollAxes;
  }

  public void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt)
  {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }

  public void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2)
  {
    this.mNestedScrollAxes = paramInt1;
  }

  public void onStopNestedScroll(@NonNull View paramView)
  {
    onStopNestedScroll(paramView, 0);
  }

  public void onStopNestedScroll(@NonNull View paramView, int paramInt)
  {
    this.mNestedScrollAxes = 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.NestedScrollingParentHelper
 * JD-Core Version:    0.6.1
 */