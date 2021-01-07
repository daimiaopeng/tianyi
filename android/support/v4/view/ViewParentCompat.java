package android.support.v4.view;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class ViewParentCompat
{
  private static final String TAG = "ViewParentCompat";

  public static void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
      paramViewParent.notifySubtreeAccessibilityStateChanged(paramView1, paramView2, paramInt);
  }

  public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 21)
      try
      {
        boolean bool = paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
        return bool;
      }
      catch (AbstractMethodError localAbstractMethodError)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ViewParent ");
        localStringBuilder.append(paramViewParent);
        localStringBuilder.append(" does not implement interface ");
        localStringBuilder.append("method onNestedFling");
        Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
      }
    else if ((paramViewParent instanceof NestedScrollingParent))
      return ((NestedScrollingParent)paramViewParent).onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
    return false;
  }

  public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21)
      try
      {
        boolean bool = paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
        return bool;
      }
      catch (AbstractMethodError localAbstractMethodError)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ViewParent ");
        localStringBuilder.append(paramViewParent);
        localStringBuilder.append(" does not implement interface ");
        localStringBuilder.append("method onNestedPreFling");
        Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
      }
    else if ((paramViewParent instanceof NestedScrollingParent))
      return ((NestedScrollingParent)paramViewParent).onNestedPreFling(paramView, paramFloat1, paramFloat2);
    return false;
  }

  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    onNestedPreScroll(paramViewParent, paramView, paramInt1, paramInt2, paramArrayOfInt, 0);
  }

  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    if ((paramViewParent instanceof NestedScrollingParent2))
      ((NestedScrollingParent2)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt, paramInt3);
    else if (paramInt3 == 0)
      if (Build.VERSION.SDK_INT >= 21)
        try
        {
          paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onNestedPreScroll");
          Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
        }
      else if ((paramViewParent instanceof NestedScrollingParent))
        ((NestedScrollingParent)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
  }

  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    onNestedScroll(paramViewParent, paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }

  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if ((paramViewParent instanceof NestedScrollingParent2))
      ((NestedScrollingParent2)paramViewParent).onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    else if (paramInt5 == 0)
      if (Build.VERSION.SDK_INT >= 21)
        try
        {
          paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onNestedScroll");
          Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
        }
      else if ((paramViewParent instanceof NestedScrollingParent))
        ((NestedScrollingParent)paramViewParent).onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    onNestedScrollAccepted(paramViewParent, paramView1, paramView2, paramInt, 0);
  }

  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    if ((paramViewParent instanceof NestedScrollingParent2))
      ((NestedScrollingParent2)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    else if (paramInt2 == 0)
      if (Build.VERSION.SDK_INT >= 21)
        try
        {
          paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt1);
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onNestedScrollAccepted");
          Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
        }
      else if ((paramViewParent instanceof NestedScrollingParent))
        ((NestedScrollingParent)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt1);
  }

  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    return onStartNestedScroll(paramViewParent, paramView1, paramView2, paramInt, 0);
  }

  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    if ((paramViewParent instanceof NestedScrollingParent2))
      return ((NestedScrollingParent2)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt1, paramInt2);
    if (paramInt2 == 0)
      if (Build.VERSION.SDK_INT >= 21)
        try
        {
          boolean bool = paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt1);
          return bool;
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onStartNestedScroll");
          Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
        }
      else if ((paramViewParent instanceof NestedScrollingParent))
        return ((NestedScrollingParent)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt1);
    return false;
  }

  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView)
  {
    onStopNestedScroll(paramViewParent, paramView, 0);
  }

  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView, int paramInt)
  {
    if ((paramViewParent instanceof NestedScrollingParent2))
      ((NestedScrollingParent2)paramViewParent).onStopNestedScroll(paramView, paramInt);
    else if (paramInt == 0)
      if (Build.VERSION.SDK_INT >= 21)
        try
        {
          paramViewParent.onStopNestedScroll(paramView);
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onStopNestedScroll");
          Log.e("ViewParentCompat", localStringBuilder.toString(), localAbstractMethodError);
        }
      else if ((paramViewParent instanceof NestedScrollingParent))
        ((NestedScrollingParent)paramViewParent).onStopNestedScroll(paramView);
  }

  @Deprecated
  public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return paramViewParent.requestSendAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.ViewParentCompat
 * JD-Core Version:    0.6.1
 */