package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
  private static final String DEFAULT_CLASS_NAME = "android.view.View";
  public static final int HOST_ID = -1;
  public static final int INVALID_ID = -2147483648;
  private static final Rect INVALID_PARENT_BOUNDS = new Rect(2147483647, 2147483647, -2147483648, -2147483648);
  private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new FocusStrategy.BoundsAdapter()
  {
    public void obtainBounds(AccessibilityNodeInfoCompat paramAnonymousAccessibilityNodeInfoCompat, Rect paramAnonymousRect)
    {
      paramAnonymousAccessibilityNodeInfoCompat.getBoundsInParent(paramAnonymousRect);
    }
  };
  private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter()
  {
    public AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> paramAnonymousSparseArrayCompat, int paramAnonymousInt)
    {
      return (AccessibilityNodeInfoCompat)paramAnonymousSparseArrayCompat.valueAt(paramAnonymousInt);
    }

    public int size(SparseArrayCompat<AccessibilityNodeInfoCompat> paramAnonymousSparseArrayCompat)
    {
      return paramAnonymousSparseArrayCompat.size();
    }
  };
  int mAccessibilityFocusedVirtualViewId = -2147483648;
  private final View mHost;
  private int mHoveredVirtualViewId = -2147483648;
  int mKeyboardFocusedVirtualViewId = -2147483648;
  private final AccessibilityManager mManager;
  private MyNodeProvider mNodeProvider;
  private final int[] mTempGlobalRect = new int[2];
  private final Rect mTempParentRect = new Rect();
  private final Rect mTempScreenRect = new Rect();
  private final Rect mTempVisibleRect = new Rect();

  public ExploreByTouchHelper(@NonNull View paramView)
  {
    if (paramView == null)
      throw new IllegalArgumentException("View may not be null");
    this.mHost = paramView;
    this.mManager = ((AccessibilityManager)paramView.getContext().getSystemService("accessibility"));
    paramView.setFocusable(true);
    if (ViewCompat.getImportantForAccessibility(paramView) == 0)
      ViewCompat.setImportantForAccessibility(paramView, 1);
  }

  private boolean clearAccessibilityFocus(int paramInt)
  {
    if (this.mAccessibilityFocusedVirtualViewId == paramInt)
    {
      this.mAccessibilityFocusedVirtualViewId = -2147483648;
      this.mHost.invalidate();
      sendEventForVirtualView(paramInt, 65536);
      return true;
    }
    return false;
  }

  private boolean clickKeyboardFocusedVirtualView()
  {
    boolean bool;
    if ((this.mKeyboardFocusedVirtualViewId != -2147483648) && (onPerformActionForVirtualView(this.mKeyboardFocusedVirtualViewId, 16, null)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private AccessibilityEvent createEvent(int paramInt1, int paramInt2)
  {
    if (paramInt1 != -1)
      return createEventForChild(paramInt1, paramInt2);
    return createEventForHost(paramInt2);
  }

  private AccessibilityEvent createEventForChild(int paramInt1, int paramInt2)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt2);
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = obtainAccessibilityNodeInfo(paramInt1);
    localAccessibilityEvent.getText().add(localAccessibilityNodeInfoCompat.getText());
    localAccessibilityEvent.setContentDescription(localAccessibilityNodeInfoCompat.getContentDescription());
    localAccessibilityEvent.setScrollable(localAccessibilityNodeInfoCompat.isScrollable());
    localAccessibilityEvent.setPassword(localAccessibilityNodeInfoCompat.isPassword());
    localAccessibilityEvent.setEnabled(localAccessibilityNodeInfoCompat.isEnabled());
    localAccessibilityEvent.setChecked(localAccessibilityNodeInfoCompat.isChecked());
    onPopulateEventForVirtualView(paramInt1, localAccessibilityEvent);
    if ((localAccessibilityEvent.getText().isEmpty()) && (localAccessibilityEvent.getContentDescription() == null))
      throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
    localAccessibilityEvent.setClassName(localAccessibilityNodeInfoCompat.getClassName());
    AccessibilityRecordCompat.setSource(localAccessibilityEvent, this.mHost, paramInt1);
    localAccessibilityEvent.setPackageName(this.mHost.getContext().getPackageName());
    return localAccessibilityEvent;
  }

  private AccessibilityEvent createEventForHost(int paramInt)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt);
    this.mHost.onInitializeAccessibilityEvent(localAccessibilityEvent);
    return localAccessibilityEvent;
  }

  @NonNull
  private AccessibilityNodeInfoCompat createNodeForChild(int paramInt)
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat1 = AccessibilityNodeInfoCompat.obtain();
    localAccessibilityNodeInfoCompat1.setEnabled(true);
    localAccessibilityNodeInfoCompat1.setFocusable(true);
    localAccessibilityNodeInfoCompat1.setClassName("android.view.View");
    localAccessibilityNodeInfoCompat1.setBoundsInParent(INVALID_PARENT_BOUNDS);
    localAccessibilityNodeInfoCompat1.setBoundsInScreen(INVALID_PARENT_BOUNDS);
    localAccessibilityNodeInfoCompat1.setParent(this.mHost);
    onPopulateNodeForVirtualView(paramInt, localAccessibilityNodeInfoCompat1);
    if ((localAccessibilityNodeInfoCompat1.getText() == null) && (localAccessibilityNodeInfoCompat1.getContentDescription() == null))
      throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
    localAccessibilityNodeInfoCompat1.getBoundsInParent(this.mTempParentRect);
    if (this.mTempParentRect.equals(INVALID_PARENT_BOUNDS))
      throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    int i = localAccessibilityNodeInfoCompat1.getActions();
    if ((i & 0x40) != 0)
      throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    if ((i & 0x80) != 0)
      throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    localAccessibilityNodeInfoCompat1.setPackageName(this.mHost.getContext().getPackageName());
    localAccessibilityNodeInfoCompat1.setSource(this.mHost, paramInt);
    if (this.mAccessibilityFocusedVirtualViewId == paramInt)
    {
      localAccessibilityNodeInfoCompat1.setAccessibilityFocused(true);
      localAccessibilityNodeInfoCompat1.addAction(128);
    }
    else
    {
      localAccessibilityNodeInfoCompat1.setAccessibilityFocused(false);
      localAccessibilityNodeInfoCompat1.addAction(64);
    }
    boolean bool;
    if (this.mKeyboardFocusedVirtualViewId == paramInt)
      bool = true;
    else
      bool = false;
    if (bool)
      localAccessibilityNodeInfoCompat1.addAction(2);
    else if (localAccessibilityNodeInfoCompat1.isFocusable())
      localAccessibilityNodeInfoCompat1.addAction(1);
    localAccessibilityNodeInfoCompat1.setFocused(bool);
    this.mHost.getLocationOnScreen(this.mTempGlobalRect);
    localAccessibilityNodeInfoCompat1.getBoundsInScreen(this.mTempScreenRect);
    if (this.mTempScreenRect.equals(INVALID_PARENT_BOUNDS))
    {
      localAccessibilityNodeInfoCompat1.getBoundsInParent(this.mTempScreenRect);
      if (localAccessibilityNodeInfoCompat1.mParentVirtualDescendantId != -1)
      {
        AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat2 = AccessibilityNodeInfoCompat.obtain();
        for (int j = localAccessibilityNodeInfoCompat1.mParentVirtualDescendantId; j != -1; j = localAccessibilityNodeInfoCompat2.mParentVirtualDescendantId)
        {
          localAccessibilityNodeInfoCompat2.setParent(this.mHost, -1);
          localAccessibilityNodeInfoCompat2.setBoundsInParent(INVALID_PARENT_BOUNDS);
          onPopulateNodeForVirtualView(j, localAccessibilityNodeInfoCompat2);
          localAccessibilityNodeInfoCompat2.getBoundsInParent(this.mTempParentRect);
          this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
        }
        localAccessibilityNodeInfoCompat2.recycle();
      }
      this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
    }
    if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect))
    {
      this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
      if (this.mTempScreenRect.intersect(this.mTempVisibleRect))
      {
        localAccessibilityNodeInfoCompat1.setBoundsInScreen(this.mTempScreenRect);
        if (isVisibleToUser(this.mTempScreenRect))
          localAccessibilityNodeInfoCompat1.setVisibleToUser(true);
      }
    }
    return localAccessibilityNodeInfoCompat1;
  }

  @NonNull
  private AccessibilityNodeInfoCompat createNodeForHost()
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(this.mHost);
    ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, localAccessibilityNodeInfoCompat);
    ArrayList localArrayList = new ArrayList();
    getVisibleVirtualViews(localArrayList);
    if ((localAccessibilityNodeInfoCompat.getChildCount() > 0) && (localArrayList.size() > 0))
      throw new RuntimeException("Views cannot have both real and virtual children");
    int i = 0;
    int j = localArrayList.size();
    while (i < j)
    {
      localAccessibilityNodeInfoCompat.addChild(this.mHost, ((Integer)localArrayList.get(i)).intValue());
      i++;
    }
    return localAccessibilityNodeInfoCompat;
  }

  private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes()
  {
    ArrayList localArrayList = new ArrayList();
    getVisibleVirtualViews(localArrayList);
    SparseArrayCompat localSparseArrayCompat = new SparseArrayCompat();
    for (int i = 0; i < localArrayList.size(); i++)
      localSparseArrayCompat.put(i, createNodeForChild(i));
    return localSparseArrayCompat;
  }

  private void getBoundsInParent(int paramInt, Rect paramRect)
  {
    obtainAccessibilityNodeInfo(paramInt).getBoundsInParent(paramRect);
  }

  private static Rect guessPreviouslyFocusedRect(@NonNull View paramView, int paramInt, @NonNull Rect paramRect)
  {
    int i = paramView.getWidth();
    int j = paramView.getHeight();
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          paramRect.set(0, -1, i, -1);
        }
        else
        {
          paramRect.set(-1, 0, -1, j);
        }
      }
      else
        paramRect.set(0, j, i, j);
    }
    else
      paramRect.set(i, 0, i, j);
    return paramRect;
  }

  private boolean isVisibleToUser(Rect paramRect)
  {
    if ((paramRect != null) && (!paramRect.isEmpty()))
    {
      if (this.mHost.getWindowVisibility() != 0)
        return false;
      ViewParent localViewParent = this.mHost.getParent();
      while ((localViewParent instanceof View))
      {
        View localView = (View)localViewParent;
        if ((localView.getAlpha() > 0.0F) && (localView.getVisibility() == 0))
          localViewParent = localView.getParent();
        else
          return false;
      }
      boolean bool = false;
      if (localViewParent != null)
        bool = true;
      return bool;
    }
    return false;
  }

  private static int keyToDirection(int paramInt)
  {
    switch (paramInt)
    {
    case 20:
    default:
      return 130;
    case 22:
      return 66;
    case 21:
      return 17;
    case 19:
    }
    return 33;
  }

  private boolean moveFocus(int paramInt, @Nullable Rect paramRect)
  {
    SparseArrayCompat localSparseArrayCompat = getAllNodes();
    int i = this.mKeyboardFocusedVirtualViewId;
    int j = -2147483648;
    if (i == j);
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat2;
    for (AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat1 = null; ; localAccessibilityNodeInfoCompat1 = (AccessibilityNodeInfoCompat)localSparseArrayCompat.get(i))
    {
      localAccessibilityNodeInfoCompat2 = localAccessibilityNodeInfoCompat1;
      break;
    }
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat3;
    if ((paramInt != 17) && (paramInt != 33) && (paramInt != 66) && (paramInt != 130))
    {
      switch (paramInt)
      {
      default:
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      case 1:
      case 2:
      }
      boolean bool;
      if (ViewCompat.getLayoutDirection(this.mHost) == 1)
        bool = true;
      else
        bool = false;
      localAccessibilityNodeInfoCompat3 = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInRelativeDirection(localSparseArrayCompat, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, localAccessibilityNodeInfoCompat2, paramInt, bool, false);
    }
    else
    {
      Rect localRect = new Rect();
      if (this.mKeyboardFocusedVirtualViewId != j)
        getBoundsInParent(this.mKeyboardFocusedVirtualViewId, localRect);
      else if (paramRect != null)
        localRect.set(paramRect);
      else
        guessPreviouslyFocusedRect(this.mHost, paramInt, localRect);
      localAccessibilityNodeInfoCompat3 = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInAbsoluteDirection(localSparseArrayCompat, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, localAccessibilityNodeInfoCompat2, localRect, paramInt);
    }
    if (localAccessibilityNodeInfoCompat3 != null)
      j = localSparseArrayCompat.keyAt(localSparseArrayCompat.indexOfValue(localAccessibilityNodeInfoCompat3));
    return requestKeyboardFocusForVirtualView(j);
  }

  private boolean performActionForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (paramInt2 != 64)
    {
      if (paramInt2 != 128)
      {
        switch (paramInt2)
        {
        default:
          return onPerformActionForVirtualView(paramInt1, paramInt2, paramBundle);
        case 2:
          return clearKeyboardFocusForVirtualView(paramInt1);
        case 1:
        }
        return requestKeyboardFocusForVirtualView(paramInt1);
      }
      return clearAccessibilityFocus(paramInt1);
    }
    return requestAccessibilityFocus(paramInt1);
  }

  private boolean performActionForHost(int paramInt, Bundle paramBundle)
  {
    return ViewCompat.performAccessibilityAction(this.mHost, paramInt, paramBundle);
  }

  private boolean requestAccessibilityFocus(int paramInt)
  {
    if ((this.mManager.isEnabled()) && (this.mManager.isTouchExplorationEnabled()))
    {
      if (this.mAccessibilityFocusedVirtualViewId != paramInt)
      {
        if (this.mAccessibilityFocusedVirtualViewId != -2147483648)
          clearAccessibilityFocus(this.mAccessibilityFocusedVirtualViewId);
        this.mAccessibilityFocusedVirtualViewId = paramInt;
        this.mHost.invalidate();
        sendEventForVirtualView(paramInt, 32768);
        return true;
      }
      return false;
    }
    return false;
  }

  private void updateHoveredVirtualView(int paramInt)
  {
    if (this.mHoveredVirtualViewId == paramInt)
      return;
    int i = this.mHoveredVirtualViewId;
    this.mHoveredVirtualViewId = paramInt;
    sendEventForVirtualView(paramInt, 128);
    sendEventForVirtualView(i, 256);
  }

  public final boolean clearKeyboardFocusForVirtualView(int paramInt)
  {
    if (this.mKeyboardFocusedVirtualViewId != paramInt)
      return false;
    this.mKeyboardFocusedVirtualViewId = -2147483648;
    onVirtualViewKeyboardFocusChanged(paramInt, false);
    sendEventForVirtualView(paramInt, 8);
    return true;
  }

  public final boolean dispatchHoverEvent(@NonNull MotionEvent paramMotionEvent)
  {
    if ((this.mManager.isEnabled()) && (this.mManager.isTouchExplorationEnabled()))
    {
      int i = paramMotionEvent.getAction();
      if (i != 7)
        switch (i)
        {
        default:
          return false;
        case 10:
          if (this.mHoveredVirtualViewId != -2147483648)
          {
            updateHoveredVirtualView(-2147483648);
            return true;
          }
          return false;
        case 9:
        }
      int j = getVirtualViewAt(paramMotionEvent.getX(), paramMotionEvent.getY());
      updateHoveredVirtualView(j);
      boolean bool = false;
      if (j != -2147483648)
        bool = true;
      return bool;
    }
    return false;
  }

  public final boolean dispatchKeyEvent(@NonNull KeyEvent paramKeyEvent)
  {
    int i = paramKeyEvent.getAction();
    int j = 0;
    if (i != 1)
    {
      int k = paramKeyEvent.getKeyCode();
      if (k != 61)
      {
        if (k != 66)
        {
          switch (k)
          {
          default:
            j = 0;
            break;
          case 19:
          case 20:
          case 21:
          case 22:
            boolean bool3 = paramKeyEvent.hasNoModifiers();
            j = 0;
            if (!bool3)
              break;
            int n = keyToDirection(k);
            int i1 = 1 + paramKeyEvent.getRepeatCount();
            for (int i2 = 0; (j < i1) && (moveFocus(n, null)); i2 = 1)
              j++;
            j = i2;
            break;
          case 23:
          }
        }
        else
        {
          boolean bool2 = paramKeyEvent.hasNoModifiers();
          j = 0;
          if (bool2)
          {
            int m = paramKeyEvent.getRepeatCount();
            j = 0;
            if (m == 0)
            {
              clickKeyboardFocusedVirtualView();
              j = 1;
            }
          }
        }
      }
      else if (paramKeyEvent.hasNoModifiers())
      {
        j = moveFocus(2, null);
      }
      else
      {
        boolean bool1 = paramKeyEvent.hasModifiers(1);
        j = 0;
        if (bool1)
          j = moveFocus(1, null);
      }
    }
    return j;
  }

  public final int getAccessibilityFocusedVirtualViewId()
  {
    return this.mAccessibilityFocusedVirtualViewId;
  }

  public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
  {
    if (this.mNodeProvider == null)
      this.mNodeProvider = new MyNodeProvider();
    return this.mNodeProvider;
  }

  @Deprecated
  public int getFocusedVirtualView()
  {
    return getAccessibilityFocusedVirtualViewId();
  }

  public final int getKeyboardFocusedVirtualViewId()
  {
    return this.mKeyboardFocusedVirtualViewId;
  }

  protected abstract int getVirtualViewAt(float paramFloat1, float paramFloat2);

  protected abstract void getVisibleVirtualViews(List<Integer> paramList);

  public final void invalidateRoot()
  {
    invalidateVirtualView(-1, 1);
  }

  public final void invalidateVirtualView(int paramInt)
  {
    invalidateVirtualView(paramInt, 0);
  }

  public final void invalidateVirtualView(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != -2147483648) && (this.mManager.isEnabled()))
    {
      ViewParent localViewParent = this.mHost.getParent();
      if (localViewParent != null)
      {
        AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, 2048);
        AccessibilityEventCompat.setContentChangeTypes(localAccessibilityEvent, paramInt2);
        ViewParentCompat.requestSendAccessibilityEvent(localViewParent, this.mHost, localAccessibilityEvent);
      }
    }
  }

  @NonNull
  AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int paramInt)
  {
    if (paramInt == -1)
      return createNodeForHost();
    return createNodeForChild(paramInt);
  }

  public final void onFocusChanged(boolean paramBoolean, int paramInt, @Nullable Rect paramRect)
  {
    if (this.mKeyboardFocusedVirtualViewId != -2147483648)
      clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
    if (paramBoolean)
      moveFocus(paramInt, paramRect);
  }

  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
    onPopulateEventForHost(paramAccessibilityEvent);
  }

  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
    onPopulateNodeForHost(paramAccessibilityNodeInfoCompat);
  }

  protected abstract boolean onPerformActionForVirtualView(int paramInt1, int paramInt2, @Nullable Bundle paramBundle);

  protected void onPopulateEventForHost(@NonNull AccessibilityEvent paramAccessibilityEvent)
  {
  }

  protected void onPopulateEventForVirtualView(int paramInt, @NonNull AccessibilityEvent paramAccessibilityEvent)
  {
  }

  protected void onPopulateNodeForHost(@NonNull AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
  }

  protected abstract void onPopulateNodeForVirtualView(int paramInt, @NonNull AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);

  protected void onVirtualViewKeyboardFocusChanged(int paramInt, boolean paramBoolean)
  {
  }

  boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (paramInt1 != -1)
      return performActionForChild(paramInt1, paramInt2, paramBundle);
    return performActionForHost(paramInt2, paramBundle);
  }

  public final boolean requestKeyboardFocusForVirtualView(int paramInt)
  {
    if ((!this.mHost.isFocused()) && (!this.mHost.requestFocus()))
      return false;
    if (this.mKeyboardFocusedVirtualViewId == paramInt)
      return false;
    if (this.mKeyboardFocusedVirtualViewId != -2147483648)
      clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
    this.mKeyboardFocusedVirtualViewId = paramInt;
    onVirtualViewKeyboardFocusChanged(paramInt, true);
    sendEventForVirtualView(paramInt, 8);
    return true;
  }

  public final boolean sendEventForVirtualView(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != -2147483648) && (this.mManager.isEnabled()))
    {
      ViewParent localViewParent = this.mHost.getParent();
      if (localViewParent == null)
        return false;
      AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, paramInt2);
      return ViewParentCompat.requestSendAccessibilityEvent(localViewParent, this.mHost, localAccessibilityEvent);
    }
    return false;
  }

  private class MyNodeProvider extends AccessibilityNodeProviderCompat
  {
    MyNodeProvider()
    {
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
    {
      return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(paramInt));
    }

    public AccessibilityNodeInfoCompat findFocus(int paramInt)
    {
      int i;
      if (paramInt == 2)
        i = ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId;
      else
        i = ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
      if (i == -2147483648)
        return null;
      return createAccessibilityNodeInfo(i);
    }

    public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      return ExploreByTouchHelper.this.performAction(paramInt1, paramInt2, paramBundle);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ExploreByTouchHelper
 * JD-Core Version:    0.6.1
 */