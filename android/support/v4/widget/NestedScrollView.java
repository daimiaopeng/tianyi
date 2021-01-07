package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollView extends FrameLayout
  implements NestedScrollingChild2, NestedScrollingParent2, ScrollingView
{
  private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
  static final int ANIMATED_SCROLL_GAP = 250;
  private static final int INVALID_POINTER = -1;
  static final float MAX_SCROLL_FACTOR = 0.5F;
  private static final int[] SCROLLVIEW_STYLEABLE = { 16843130 };
  private static final String TAG = "NestedScrollView";
  private int mActivePointerId = -1;
  private final NestedScrollingChildHelper mChildHelper;
  private View mChildToScrollTo = null;
  private EdgeEffect mEdgeGlowBottom;
  private EdgeEffect mEdgeGlowTop;
  private boolean mFillViewport;
  private boolean mIsBeingDragged = false;
  private boolean mIsLaidOut = false;
  private boolean mIsLayoutDirty = true;
  private int mLastMotionY;
  private long mLastScroll;
  private int mLastScrollerY;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private int mNestedYOffset;
  private OnScrollChangeListener mOnScrollChangeListener;
  private final NestedScrollingParentHelper mParentHelper;
  private SavedState mSavedState;
  private final int[] mScrollConsumed = new int[2];
  private final int[] mScrollOffset = new int[2];
  private OverScroller mScroller;
  private boolean mSmoothScrollingEnabled = true;
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  private float mVerticalScrollFactor;

  public NestedScrollView(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }

  public NestedScrollView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public NestedScrollView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initScrollView();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, SCROLLVIEW_STYLEABLE, paramInt, 0);
    setFillViewport(localTypedArray.getBoolean(0, false));
    localTypedArray.recycle();
    this.mParentHelper = new NestedScrollingParentHelper(this);
    this.mChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
    ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
  }

  private boolean canScroll()
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      int i = localView.getHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
      int j = getHeight() - getPaddingTop() - getPaddingBottom();
      boolean bool = false;
      if (i > j)
        bool = true;
      return bool;
    }
    return false;
  }

  private static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 < paramInt3) && (paramInt1 >= 0))
    {
      if (paramInt2 + paramInt1 > paramInt3)
        return paramInt3 - paramInt2;
      return paramInt1;
    }
    return 0;
  }

  private void doScrollY(int paramInt)
  {
    if (paramInt != 0)
      if (this.mSmoothScrollingEnabled)
        smoothScrollBy(0, paramInt);
      else
        scrollBy(0, paramInt);
  }

  private void endDrag()
  {
    this.mIsBeingDragged = false;
    recycleVelocityTracker();
    stopNestedScroll(0);
    if (this.mEdgeGlowTop != null)
    {
      this.mEdgeGlowTop.onRelease();
      this.mEdgeGlowBottom.onRelease();
    }
  }

  private void ensureGlows()
  {
    if (getOverScrollMode() != 2)
    {
      if (this.mEdgeGlowTop == null)
      {
        Context localContext = getContext();
        this.mEdgeGlowTop = new EdgeEffect(localContext);
        this.mEdgeGlowBottom = new EdgeEffect(localContext);
      }
    }
    else
    {
      this.mEdgeGlowTop = null;
      this.mEdgeGlowBottom = null;
    }
  }

  private View findFocusableViewInBounds(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = getFocusables(2);
    int i = localArrayList.size();
    Object localObject = null;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      View localView = (View)localArrayList.get(j);
      int m = localView.getTop();
      int n = localView.getBottom();
      if ((paramInt1 < n) && (m < paramInt2))
      {
        int i1;
        if ((paramInt1 < m) && (n < paramInt2))
          i1 = 1;
        else
          i1 = 0;
        if (localObject == null)
        {
          localObject = localView;
          k = i1;
        }
        else
        {
          int i2;
          if (((paramBoolean) && (m < localObject.getTop())) || ((!paramBoolean) && (n > localObject.getBottom())))
            i2 = 1;
          else
            i2 = 0;
          if (k != 0)
          {
            if ((i1 == 0) || (i2 == 0))
              break label188;
          }
          else
          {
            if (i1 != 0)
            {
              localObject = localView;
              k = 1;
              break label188;
            }
            if (i2 == 0)
              break label188;
          }
          localObject = localView;
        }
      }
      label188: j++;
    }
    return localObject;
  }

  private void flingWithNestedDispatch(int paramInt)
  {
    int i = getScrollY();
    boolean bool;
    if (((i <= 0) && (paramInt <= 0)) || ((i >= getScrollRange()) && (paramInt >= 0)))
      bool = false;
    else
      bool = true;
    float f = paramInt;
    if (!dispatchNestedPreFling(0.0F, f))
    {
      dispatchNestedFling(0.0F, f, bool);
      fling(paramInt);
    }
  }

  private float getVerticalScrollFactorCompat()
  {
    if (this.mVerticalScrollFactor == 0.0F)
    {
      TypedValue localTypedValue = new TypedValue();
      Context localContext = getContext();
      if (!localContext.getTheme().resolveAttribute(16842829, localTypedValue, true))
        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
      this.mVerticalScrollFactor = localTypedValue.getDimension(localContext.getResources().getDisplayMetrics());
    }
    return this.mVerticalScrollFactor;
  }

  private boolean inChild(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      int i = getScrollY();
      View localView = getChildAt(0);
      int j = localView.getTop() - i;
      boolean bool = false;
      if (paramInt2 >= j)
      {
        int k = localView.getBottom() - i;
        bool = false;
        if (paramInt2 < k)
        {
          int m = localView.getLeft();
          bool = false;
          if (paramInt1 >= m)
          {
            int n = localView.getRight();
            bool = false;
            if (paramInt1 < n)
              bool = true;
          }
        }
      }
      return bool;
    }
    return false;
  }

  private void initOrResetVelocityTracker()
  {
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    else
      this.mVelocityTracker.clear();
  }

  private void initScrollView()
  {
    this.mScroller = new OverScroller(getContext());
    setFocusable(true);
    setDescendantFocusability(262144);
    setWillNotDraw(false);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
  }

  private void initVelocityTrackerIfNotExists()
  {
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
  }

  private boolean isOffScreen(View paramView)
  {
    return true ^ isWithinDeltaOfScreen(paramView, 0, getHeight());
  }

  private static boolean isViewDescendantOf(View paramView1, View paramView2)
  {
    boolean bool = true;
    if (paramView1 == paramView2)
      return bool;
    ViewParent localViewParent = paramView1.getParent();
    if ((!(localViewParent instanceof ViewGroup)) || (!isViewDescendantOf((View)localViewParent, paramView2)))
      bool = false;
    return bool;
  }

  private boolean isWithinDeltaOfScreen(View paramView, int paramInt1, int paramInt2)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    boolean bool;
    if ((paramInt1 + this.mTempRect.bottom >= getScrollY()) && (this.mTempRect.top - paramInt1 <= paramInt2 + getScrollY()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId)
    {
      int j;
      if (i == 0)
        j = 1;
      else
        j = 0;
      this.mLastMotionY = (int)paramMotionEvent.getY(j);
      this.mActivePointerId = paramMotionEvent.getPointerId(j);
      if (this.mVelocityTracker != null)
        this.mVelocityTracker.clear();
    }
  }

  private void recycleVelocityTracker()
  {
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }

  private boolean scrollAndFocus(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = getHeight();
    int j = getScrollY();
    int k = i + j;
    boolean bool1;
    if (paramInt1 == 33)
      bool1 = true;
    else
      bool1 = false;
    Object localObject = findFocusableViewInBounds(bool1, paramInt2, paramInt3);
    if (localObject == null)
      localObject = this;
    boolean bool2;
    if ((paramInt2 >= j) && (paramInt3 <= k))
    {
      bool2 = false;
    }
    else
    {
      int m;
      if (bool1)
        m = paramInt2 - j;
      else
        m = paramInt3 - k;
      doScrollY(m);
      bool2 = true;
    }
    if (localObject != findFocus())
      ((View)localObject).requestFocus(paramInt1);
    return bool2;
  }

  private void scrollToChild(View paramView)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    int i = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    if (i != 0)
      scrollBy(0, i);
  }

  private boolean scrollToChildRect(Rect paramRect, boolean paramBoolean)
  {
    int i = computeScrollDeltaToGetChildRectOnScreen(paramRect);
    boolean bool;
    if (i != 0)
      bool = true;
    else
      bool = false;
    if (bool)
      if (paramBoolean)
        scrollBy(0, i);
      else
        smoothScrollBy(0, i);
    return bool;
  }

  public void addView(View paramView)
  {
    if (getChildCount() > 0)
      throw new IllegalStateException("ScrollView can host only one direct child");
    super.addView(paramView);
  }

  public void addView(View paramView, int paramInt)
  {
    if (getChildCount() > 0)
      throw new IllegalStateException("ScrollView can host only one direct child");
    super.addView(paramView, paramInt);
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0)
      throw new IllegalStateException("ScrollView can host only one direct child");
    super.addView(paramView, paramInt, paramLayoutParams);
  }

  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0)
      throw new IllegalStateException("ScrollView can host only one direct child");
    super.addView(paramView, paramLayoutParams);
  }

  public boolean arrowScroll(int paramInt)
  {
    View localView1 = findFocus();
    if (localView1 == this)
      localView1 = null;
    View localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    int i = getMaxScrollAmount();
    if ((localView2 != null) && (isWithinDeltaOfScreen(localView2, i, getHeight())))
    {
      localView2.getDrawingRect(this.mTempRect);
      offsetDescendantRectToMyCoords(localView2, this.mTempRect);
      doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      localView2.requestFocus(paramInt);
    }
    else
    {
      if ((paramInt == 33) && (getScrollY() < i))
      {
        i = getScrollY();
      }
      else if ((paramInt == 130) && (getChildCount() > 0))
      {
        View localView3 = getChildAt(0);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView3.getLayoutParams();
        i = Math.min(localView3.getBottom() + localLayoutParams.bottomMargin - (getScrollY() + getHeight() - getPaddingBottom()), i);
      }
      if (i == 0)
        return false;
      if (paramInt != 130)
        i = -i;
      doScrollY(i);
    }
    if ((localView1 != null) && (localView1.isFocused()) && (isOffScreen(localView1)))
    {
      int j = getDescendantFocusability();
      setDescendantFocusability(131072);
      requestFocus();
      setDescendantFocusability(j);
    }
    return true;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int computeHorizontalScrollExtent()
  {
    return super.computeHorizontalScrollExtent();
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int computeHorizontalScrollOffset()
  {
    return super.computeHorizontalScrollOffset();
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int computeHorizontalScrollRange()
  {
    return super.computeHorizontalScrollRange();
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      this.mScroller.getCurrX();
      int i = this.mScroller.getCurrY();
      int j = i - this.mLastScrollerY;
      int[] arrayOfInt = this.mScrollConsumed;
      if (dispatchNestedPreScroll(0, j, arrayOfInt, null, 1))
        j -= this.mScrollConsumed[1];
      int k = j;
      if (k != 0)
      {
        int m = getScrollRange();
        int n = getScrollY();
        overScrollByCompat(0, k, getScrollX(), n, 0, m, 0, 0, false);
        int i1 = getScrollY() - n;
        if (!dispatchNestedScroll(0, i1, 0, k - i1, null, 1))
        {
          int i2 = getOverScrollMode();
          int i3;
          if ((i2 != 0) && ((i2 != 1) || (m <= 0)))
            i3 = 0;
          else
            i3 = 1;
          if (i3 != 0)
          {
            ensureGlows();
            if ((i <= 0) && (n > 0))
              this.mEdgeGlowTop.onAbsorb((int)this.mScroller.getCurrVelocity());
            else if ((i >= m) && (n < m))
              this.mEdgeGlowBottom.onAbsorb((int)this.mScroller.getCurrVelocity());
          }
        }
      }
      this.mLastScrollerY = i;
      ViewCompat.postInvalidateOnAnimation(this);
    }
    else
    {
      if (hasNestedScrollingParent(1))
        stopNestedScroll(1);
      this.mLastScrollerY = 0;
    }
  }

  protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect)
  {
    if (getChildCount() == 0)
      return 0;
    int i = getHeight();
    int j = getScrollY();
    int k = j + i;
    int m = getVerticalFadingEdgeLength();
    if (paramRect.top > 0)
      j += m;
    View localView = getChildAt(0);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    int n;
    if (paramRect.bottom < localView.getHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin)
      n = k - m;
    else
      n = k;
    int i2;
    if ((paramRect.bottom > n) && (paramRect.top > j))
    {
      int i5;
      if (paramRect.height() > i)
        i5 = 0 + (paramRect.top - j);
      else
        i5 = 0 + (paramRect.bottom - n);
      i2 = Math.min(i5, localView.getBottom() + localLayoutParams.bottomMargin - k);
    }
    else
    {
      int i1 = paramRect.top;
      i2 = 0;
      if (i1 < j)
      {
        int i3 = paramRect.bottom;
        i2 = 0;
        if (i3 < n)
        {
          int i4;
          if (paramRect.height() > i)
            i4 = 0 - (n - paramRect.bottom);
          else
            i4 = 0 - (j - paramRect.top);
          i2 = Math.max(i4, -getScrollY());
        }
      }
    }
    return i2;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int computeVerticalScrollExtent()
  {
    return super.computeVerticalScrollExtent();
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int computeVerticalScrollOffset()
  {
    return Math.max(0, super.computeVerticalScrollOffset());
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int computeVerticalScrollRange()
  {
    int i = getChildCount();
    int j = getHeight() - getPaddingBottom() - getPaddingTop();
    if (i == 0)
      return j;
    View localView = getChildAt(0);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    int k = localView.getBottom() + localLayoutParams.bottomMargin;
    int m = getScrollY();
    int n = Math.max(0, k - j);
    if (m < 0)
      k -= m;
    else if (m > n)
      k += m - n;
    return k;
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    boolean bool;
    if ((!super.dispatchKeyEvent(paramKeyEvent)) && (!executeKeyEvent(paramKeyEvent)))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return this.mChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }

  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return this.mChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }

  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, 0);
  }

  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt3)
  {
    return this.mChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramInt3);
  }

  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, 0);
  }

  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5)
  {
    return this.mChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramInt5);
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (this.mEdgeGlowTop != null)
    {
      int i = getScrollY();
      if (!this.mEdgeGlowTop.isFinished())
      {
        int i2 = paramCanvas.save();
        int i3 = getWidth();
        int i4 = getHeight();
        int i5 = Math.min(0, i);
        int i6;
        if ((Build.VERSION.SDK_INT >= 21) && (!getClipToPadding()))
        {
          i6 = 0;
        }
        else
        {
          i3 -= getPaddingLeft() + getPaddingRight();
          i6 = 0 + getPaddingLeft();
        }
        if ((Build.VERSION.SDK_INT >= 21) && (getClipToPadding()))
        {
          i4 -= getPaddingTop() + getPaddingBottom();
          i5 += getPaddingTop();
        }
        paramCanvas.translate(i6, i5);
        this.mEdgeGlowTop.setSize(i3, i4);
        if (this.mEdgeGlowTop.draw(paramCanvas))
          ViewCompat.postInvalidateOnAnimation(this);
        paramCanvas.restoreToCount(i2);
      }
      if (!this.mEdgeGlowBottom.isFinished())
      {
        int j = paramCanvas.save();
        int k = getWidth();
        int m = getHeight();
        int n = m + Math.max(getScrollRange(), i);
        int i1;
        if (Build.VERSION.SDK_INT >= 21)
        {
          boolean bool = getClipToPadding();
          i1 = 0;
          if (!bool);
        }
        else
        {
          k -= getPaddingLeft() + getPaddingRight();
          i1 = 0 + getPaddingLeft();
        }
        if ((Build.VERSION.SDK_INT >= 21) && (getClipToPadding()))
        {
          m -= getPaddingTop() + getPaddingBottom();
          n -= getPaddingBottom();
        }
        paramCanvas.translate(i1 - k, n);
        paramCanvas.rotate(180.0F, k, 0.0F);
        this.mEdgeGlowBottom.setSize(k, m);
        if (this.mEdgeGlowBottom.draw(paramCanvas))
          ViewCompat.postInvalidateOnAnimation(this);
        paramCanvas.restoreToCount(j);
      }
    }
  }

  public boolean executeKeyEvent(@NonNull KeyEvent paramKeyEvent)
  {
    this.mTempRect.setEmpty();
    boolean bool1 = canScroll();
    int i = 130;
    if (!bool1)
    {
      if ((isFocused()) && (paramKeyEvent.getKeyCode() != 4))
      {
        View localView1 = findFocus();
        if (localView1 == this)
          localView1 = null;
        View localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, i);
        boolean bool3 = false;
        if (localView2 != null)
        {
          bool3 = false;
          if (localView2 != this)
          {
            boolean bool4 = localView2.requestFocus(i);
            bool3 = false;
            if (bool4)
              bool3 = true;
          }
        }
        return bool3;
      }
      return false;
    }
    int j = paramKeyEvent.getAction();
    boolean bool2 = false;
    if (j == 0)
    {
      int k = paramKeyEvent.getKeyCode();
      if (k != 62)
      {
        switch (k)
        {
        default:
          bool2 = false;
          break;
        case 20:
          if (!paramKeyEvent.isAltPressed())
            bool2 = arrowScroll(i);
          else
            bool2 = fullScroll(i);
          break;
        case 19:
          if (!paramKeyEvent.isAltPressed())
            bool2 = arrowScroll(33);
          else
            bool2 = fullScroll(33);
          break;
        }
      }
      else
      {
        if (paramKeyEvent.isShiftPressed())
          i = 33;
        pageScroll(i);
      }
    }
    return bool2;
  }

  public void fling(int paramInt)
  {
    if (getChildCount() > 0)
    {
      startNestedScroll(2, 1);
      this.mScroller.fling(getScrollX(), getScrollY(), 0, paramInt, 0, 0, -2147483648, 2147483647, 0, 0);
      this.mLastScrollerY = getScrollY();
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }

  public boolean fullScroll(int paramInt)
  {
    int i;
    if (paramInt == 130)
      i = 1;
    else
      i = 0;
    int j = getHeight();
    this.mTempRect.top = 0;
    this.mTempRect.bottom = j;
    if (i != 0)
    {
      int k = getChildCount();
      if (k > 0)
      {
        View localView = getChildAt(k - 1);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
        this.mTempRect.bottom = (localView.getBottom() + localLayoutParams.bottomMargin + getPaddingBottom());
        this.mTempRect.top = (this.mTempRect.bottom - j);
      }
    }
    return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
  }

  protected float getBottomFadingEdgeStrength()
  {
    if (getChildCount() == 0)
      return 0.0F;
    View localView = getChildAt(0);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    int i = getVerticalFadingEdgeLength();
    int j = getHeight() - getPaddingBottom();
    int k = localView.getBottom() + localLayoutParams.bottomMargin - getScrollY() - j;
    if (k < i)
      return k / i;
    return 1.0F;
  }

  public int getMaxScrollAmount()
  {
    return (int)(0.5F * getHeight());
  }

  public int getNestedScrollAxes()
  {
    return this.mParentHelper.getNestedScrollAxes();
  }

  int getScrollRange()
  {
    int i = getChildCount();
    int j = 0;
    if (i > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      j = Math.max(0, localView.getHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin - (getHeight() - getPaddingTop() - getPaddingBottom()));
    }
    return j;
  }

  protected float getTopFadingEdgeStrength()
  {
    if (getChildCount() == 0)
      return 0.0F;
    int i = getVerticalFadingEdgeLength();
    int j = getScrollY();
    if (j < i)
      return j / i;
    return 1.0F;
  }

  public boolean hasNestedScrollingParent()
  {
    return hasNestedScrollingParent(0);
  }

  public boolean hasNestedScrollingParent(int paramInt)
  {
    return this.mChildHelper.hasNestedScrollingParent(paramInt);
  }

  public boolean isFillViewport()
  {
    return this.mFillViewport;
  }

  public boolean isNestedScrollingEnabled()
  {
    return this.mChildHelper.isNestedScrollingEnabled();
  }

  public boolean isSmoothScrollingEnabled()
  {
    return this.mSmoothScrollingEnabled;
  }

  protected void measureChild(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
  }

  protected void measureChildWithMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, paramInt2 + (getPaddingLeft() + getPaddingRight() + localMarginLayoutParams.leftMargin + localMarginLayoutParams.rightMargin), localMarginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin, 0));
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mIsLaidOut = false;
  }

  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (((0x2 & paramMotionEvent.getSource()) != 0) && (paramMotionEvent.getAction() == 8) && (!this.mIsBeingDragged))
    {
      float f = paramMotionEvent.getAxisValue(9);
      if (f != 0.0F)
      {
        int i = (int)(f * getVerticalScrollFactorCompat());
        int j = getScrollRange();
        int k = getScrollY();
        int m = k - i;
        if (m < 0)
          m = 0;
        else if (m > j)
          m = j;
        if (m != k)
        {
          super.scrollTo(getScrollX(), m);
          return true;
        }
      }
    }
    return false;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i == 2) && (this.mIsBeingDragged))
      return true;
    int j = i & 0xFF;
    if (j != 6)
      switch (j)
      {
      default:
        break;
      case 2:
        int m = this.mActivePointerId;
        if (m == -1)
          break;
        int n = paramMotionEvent.findPointerIndex(m);
        if (n == -1)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Invalid pointerId=");
          localStringBuilder.append(m);
          localStringBuilder.append(" in onInterceptTouchEvent");
          Log.e("NestedScrollView", localStringBuilder.toString());
        }
        else
        {
          int i1 = (int)paramMotionEvent.getY(n);
          if ((Math.abs(i1 - this.mLastMotionY) > this.mTouchSlop) && ((0x2 & getNestedScrollAxes()) == 0))
          {
            this.mIsBeingDragged = true;
            this.mLastMotionY = i1;
            initVelocityTrackerIfNotExists();
            this.mVelocityTracker.addMovement(paramMotionEvent);
            this.mNestedYOffset = 0;
            ViewParent localViewParent = getParent();
            if (localViewParent != null)
              localViewParent.requestDisallowInterceptTouchEvent(true);
          }
        }
        break;
      case 1:
      case 3:
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
        recycleVelocityTracker();
        if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))
          ViewCompat.postInvalidateOnAnimation(this);
        stopNestedScroll(0);
        break;
      case 0:
        int k = (int)paramMotionEvent.getY();
        if (!inChild((int)paramMotionEvent.getX(), k))
        {
          this.mIsBeingDragged = false;
          recycleVelocityTracker();
        }
        else
        {
          this.mLastMotionY = k;
          this.mActivePointerId = paramMotionEvent.getPointerId(0);
          initOrResetVelocityTracker();
          this.mVelocityTracker.addMovement(paramMotionEvent);
          this.mScroller.computeScrollOffset();
          this.mIsBeingDragged = (true ^ this.mScroller.isFinished());
          startNestedScroll(2, 0);
        }
        break;
      }
    else
      onSecondaryPointerUp(paramMotionEvent);
    return this.mIsBeingDragged;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mIsLayoutDirty = false;
    if ((this.mChildToScrollTo != null) && (isViewDescendantOf(this.mChildToScrollTo, this)))
      scrollToChild(this.mChildToScrollTo);
    this.mChildToScrollTo = null;
    if (!this.mIsLaidOut)
    {
      if (this.mSavedState != null)
      {
        scrollTo(getScrollX(), this.mSavedState.scrollPosition);
        this.mSavedState = null;
      }
      int i = getChildCount();
      int j = 0;
      if (i > 0)
      {
        View localView = getChildAt(0);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
        j = localView.getMeasuredHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
      }
      int k = paramInt4 - paramInt2 - getPaddingTop() - getPaddingBottom();
      int m = getScrollY();
      int n = clamp(m, k, j);
      if (n != m)
        scrollTo(getScrollX(), n);
    }
    scrollTo(getScrollX(), getScrollY());
    this.mIsLaidOut = true;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!this.mFillViewport)
      return;
    if (View.MeasureSpec.getMode(paramInt2) == 0)
      return;
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      int i = localView.getMeasuredHeight();
      int j = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - localLayoutParams.topMargin - localLayoutParams.bottomMargin;
      if (i < j)
        localView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + localLayoutParams.leftMargin + localLayoutParams.rightMargin, localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(j, 1073741824));
    }
  }

  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      flingWithNestedDispatch((int)paramFloat2);
      return true;
    }
    return false;
  }

  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    return dispatchNestedPreFling(paramFloat1, paramFloat2);
  }

  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt, 0);
  }

  public void onNestedPreScroll(@NonNull View paramView, int paramInt1, int paramInt2, @NonNull int[] paramArrayOfInt, int paramInt3)
  {
    dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt, null, paramInt3);
  }

  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }

  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = getScrollY();
    scrollBy(0, paramInt4);
    int j = getScrollY() - i;
    dispatchNestedScroll(0, j, 0, paramInt4 - j, null, paramInt5);
  }

  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }

  public void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2)
  {
    this.mParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    startNestedScroll(2, paramInt2);
  }

  protected void onOverScrolled(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    super.scrollTo(paramInt1, paramInt2);
  }

  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    if (paramInt == 2)
      paramInt = 130;
    else if (paramInt == 1)
      paramInt = 33;
    View localView;
    if (paramRect == null)
      localView = FocusFinder.getInstance().findNextFocus(this, null, paramInt);
    else
      localView = FocusFinder.getInstance().findNextFocusFromRect(this, paramRect, paramInt);
    if (localView == null)
      return false;
    if (isOffScreen(localView))
      return false;
    return localView.requestFocus(paramInt, paramRect);
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    this.mSavedState = localSavedState;
    requestLayout();
  }

  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.scrollPosition = getScrollY();
    return localSavedState;
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollChangeListener != null)
      this.mOnScrollChangeListener.onScrollChange(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findFocus();
    if ((localView != null) && (this != localView))
    {
      if (isWithinDeltaOfScreen(localView, 0, paramInt4))
      {
        localView.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(localView, this.mTempRect);
        doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      }
      return;
    }
  }

  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    return onStartNestedScroll(paramView1, paramView2, paramInt, 0);
  }

  public boolean onStartNestedScroll(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2)
  {
    boolean bool;
    if ((paramInt1 & 0x2) != 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void onStopNestedScroll(View paramView)
  {
    onStopNestedScroll(paramView, 0);
  }

  public void onStopNestedScroll(@NonNull View paramView, int paramInt)
  {
    this.mParentHelper.onStopNestedScroll(paramView, paramInt);
    stopNestedScroll(paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    initVelocityTrackerIfNotExists();
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    int i = paramMotionEvent.getActionMasked();
    if (i == 0)
      this.mNestedYOffset = 0;
    localMotionEvent.offsetLocation(0.0F, this.mNestedYOffset);
    switch (i)
    {
    case 4:
    default:
      break;
    case 6:
      onSecondaryPointerUp(paramMotionEvent);
      this.mLastMotionY = (int)paramMotionEvent.getY(paramMotionEvent.findPointerIndex(this.mActivePointerId));
      break;
    case 5:
      int i8 = paramMotionEvent.getActionIndex();
      this.mLastMotionY = (int)paramMotionEvent.getY(i8);
      this.mActivePointerId = paramMotionEvent.getPointerId(i8);
      break;
    case 3:
      if ((this.mIsBeingDragged) && (getChildCount() > 0) && (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())))
        ViewCompat.postInvalidateOnAnimation(this);
      this.mActivePointerId = -1;
      endDrag();
      break;
    case 2:
      int k = paramMotionEvent.findPointerIndex(this.mActivePointerId);
      if (k == -1)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Invalid pointerId=");
        localStringBuilder.append(this.mActivePointerId);
        localStringBuilder.append(" in onTouchEvent");
        Log.e("NestedScrollView", localStringBuilder.toString());
      }
      else
      {
        int m = (int)paramMotionEvent.getY(k);
        int n = this.mLastMotionY - m;
        int[] arrayOfInt1 = this.mScrollConsumed;
        int[] arrayOfInt2 = this.mScrollOffset;
        if (dispatchNestedPreScroll(0, n, arrayOfInt1, arrayOfInt2, 0))
        {
          n -= this.mScrollConsumed[1];
          localMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
          this.mNestedYOffset += this.mScrollOffset[1];
        }
        if ((!this.mIsBeingDragged) && (Math.abs(n) > this.mTouchSlop))
        {
          ViewParent localViewParent2 = getParent();
          if (localViewParent2 != null)
            localViewParent2.requestDisallowInterceptTouchEvent(true);
          this.mIsBeingDragged = true;
          if (n > 0)
            n -= this.mTouchSlop;
          else
            n += this.mTouchSlop;
        }
        int i1 = n;
        if (this.mIsBeingDragged)
        {
          this.mLastMotionY = (m - this.mScrollOffset[1]);
          int i2 = getScrollY();
          int i3 = getScrollRange();
          int i4 = getOverScrollMode();
          int i5;
          if ((i4 != 0) && ((i4 != 1) || (i3 <= 0)))
            i5 = 0;
          else
            i5 = 1;
          if ((overScrollByCompat(0, i1, 0, getScrollY(), 0, i3, 0, 0, true)) && (!hasNestedScrollingParent(0)))
            this.mVelocityTracker.clear();
          int i6 = getScrollY() - i2;
          if (dispatchNestedScroll(0, i6, 0, i1 - i6, this.mScrollOffset, 0))
          {
            this.mLastMotionY -= this.mScrollOffset[1];
            localMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
            this.mNestedYOffset += this.mScrollOffset[1];
          }
          else if (i5 != 0)
          {
            ensureGlows();
            int i7 = i2 + i1;
            if (i7 < 0)
            {
              EdgeEffectCompat.onPull(this.mEdgeGlowTop, i1 / getHeight(), paramMotionEvent.getX(k) / getWidth());
              if (!this.mEdgeGlowBottom.isFinished())
                this.mEdgeGlowBottom.onRelease();
            }
            else if (i7 > i3)
            {
              EdgeEffectCompat.onPull(this.mEdgeGlowBottom, i1 / getHeight(), 1.0F - paramMotionEvent.getX(k) / getWidth());
              if (!this.mEdgeGlowTop.isFinished())
                this.mEdgeGlowTop.onRelease();
            }
            if ((this.mEdgeGlowTop != null) && ((!this.mEdgeGlowTop.isFinished()) || (!this.mEdgeGlowBottom.isFinished())))
              ViewCompat.postInvalidateOnAnimation(this);
          }
        }
      }
      break;
    case 1:
      VelocityTracker localVelocityTracker = this.mVelocityTracker;
      localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
      int j = (int)localVelocityTracker.getYVelocity(this.mActivePointerId);
      if (Math.abs(j) > this.mMinimumVelocity)
        flingWithNestedDispatch(-j);
      else if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))
        ViewCompat.postInvalidateOnAnimation(this);
      this.mActivePointerId = -1;
      endDrag();
      break;
    case 0:
      if (getChildCount() == 0)
        return false;
      boolean bool = true ^ this.mScroller.isFinished();
      this.mIsBeingDragged = bool;
      if (bool)
      {
        ViewParent localViewParent1 = getParent();
        if (localViewParent1 != null)
          localViewParent1.requestDisallowInterceptTouchEvent(true);
      }
      if (!this.mScroller.isFinished())
        this.mScroller.abortAnimation();
      this.mLastMotionY = (int)paramMotionEvent.getY();
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      startNestedScroll(2, 0);
    }
    if (this.mVelocityTracker != null)
      this.mVelocityTracker.addMovement(localMotionEvent);
    localMotionEvent.recycle();
    return true;
  }

  boolean overScrollByCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
  {
    int i = getOverScrollMode();
    int j;
    if (computeHorizontalScrollRange() > computeHorizontalScrollExtent())
      j = 1;
    else
      j = 0;
    int k;
    if (computeVerticalScrollRange() > computeVerticalScrollExtent())
      k = 1;
    else
      k = 0;
    int m;
    if ((i != 0) && ((i != 1) || (j == 0)))
      m = 0;
    else
      m = 1;
    int n;
    if ((i != 0) && ((i != 1) || (k == 0)))
      n = 0;
    else
      n = 1;
    int i1 = paramInt3 + paramInt1;
    int i2;
    if (m == 0)
      i2 = 0;
    else
      i2 = paramInt7;
    int i3 = paramInt4 + paramInt2;
    int i4;
    if (n == 0)
      i4 = 0;
    else
      i4 = paramInt8;
    int i5 = -i2;
    int i6 = i2 + paramInt5;
    int i7 = -i4;
    int i8 = i4 + paramInt6;
    if (i1 > i6)
      i5 = i6;
    while (i1 < i5)
    {
      bool1 = true;
      break;
    }
    i5 = i1;
    boolean bool1 = false;
    if (i3 > i8)
      i7 = i8;
    while (i3 < i7)
    {
      bool2 = true;
      break;
    }
    i7 = i3;
    boolean bool2 = false;
    if ((bool2) && (!hasNestedScrollingParent(1)))
    {
      OverScroller localOverScroller = this.mScroller;
      int i9 = getScrollRange();
      localOverScroller.springBack(i5, i7, 0, 0, 0, i9);
    }
    onOverScrolled(i5, i7, bool1, bool2);
    boolean bool3;
    if (!bool1)
    {
      bool3 = false;
      if (!bool2);
    }
    else
    {
      bool3 = true;
    }
    return bool3;
  }

  public boolean pageScroll(int paramInt)
  {
    int i;
    if (paramInt == 130)
      i = 1;
    else
      i = 0;
    int j = getHeight();
    if (i != 0)
    {
      this.mTempRect.top = (j + getScrollY());
      int k = getChildCount();
      if (k > 0)
      {
        View localView = getChildAt(k - 1);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
        int m = localView.getBottom() + localLayoutParams.bottomMargin + getPaddingBottom();
        if (j + this.mTempRect.top > m)
          this.mTempRect.top = (m - j);
      }
    }
    else
    {
      this.mTempRect.top = (getScrollY() - j);
      if (this.mTempRect.top < 0)
        this.mTempRect.top = 0;
    }
    this.mTempRect.bottom = (j + this.mTempRect.top);
    return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
  }

  public void requestChildFocus(View paramView1, View paramView2)
  {
    if (!this.mIsLayoutDirty)
      scrollToChild(paramView2);
    else
      this.mChildToScrollTo = paramView2;
    super.requestChildFocus(paramView1, paramView2);
  }

  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    paramRect.offset(paramView.getLeft() - paramView.getScrollX(), paramView.getTop() - paramView.getScrollY());
    return scrollToChildRect(paramRect, paramBoolean);
  }

  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    if (paramBoolean)
      recycleVelocityTracker();
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }

  public void requestLayout()
  {
    this.mIsLayoutDirty = true;
    super.requestLayout();
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      int i = getWidth() - getPaddingLeft() - getPaddingRight();
      int j = localView.getWidth() + localLayoutParams.leftMargin + localLayoutParams.rightMargin;
      int k = getHeight() - getPaddingTop() - getPaddingBottom();
      int m = localView.getHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
      int n = clamp(paramInt1, i, j);
      int i1 = clamp(paramInt2, k, m);
      if ((n != getScrollX()) || (i1 != getScrollY()))
        super.scrollTo(n, i1);
    }
  }

  public void setFillViewport(boolean paramBoolean)
  {
    if (paramBoolean != this.mFillViewport)
    {
      this.mFillViewport = paramBoolean;
      requestLayout();
    }
  }

  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    this.mChildHelper.setNestedScrollingEnabled(paramBoolean);
  }

  public void setOnScrollChangeListener(@Nullable OnScrollChangeListener paramOnScrollChangeListener)
  {
    this.mOnScrollChangeListener = paramOnScrollChangeListener;
  }

  public void setSmoothScrollingEnabled(boolean paramBoolean)
  {
    this.mSmoothScrollingEnabled = paramBoolean;
  }

  public boolean shouldDelayChildPressedState()
  {
    return true;
  }

  public final void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (getChildCount() == 0)
      return;
    if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250L)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      int i = localView.getHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
      int j = getHeight() - getPaddingTop() - getPaddingBottom();
      int k = getScrollY();
      int m = Math.max(0, i - j);
      int n = Math.max(0, Math.min(paramInt2 + k, m)) - k;
      this.mLastScrollerY = getScrollY();
      this.mScroller.startScroll(getScrollX(), k, 0, n);
      ViewCompat.postInvalidateOnAnimation(this);
    }
    else
    {
      if (!this.mScroller.isFinished())
        this.mScroller.abortAnimation();
      scrollBy(paramInt1, paramInt2);
    }
    this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
  }

  public final void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1 - getScrollX(), paramInt2 - getScrollY());
  }

  public boolean startNestedScroll(int paramInt)
  {
    return startNestedScroll(paramInt, 0);
  }

  public boolean startNestedScroll(int paramInt1, int paramInt2)
  {
    return this.mChildHelper.startNestedScroll(paramInt1, paramInt2);
  }

  public void stopNestedScroll()
  {
    stopNestedScroll(0);
  }

  public void stopNestedScroll(int paramInt)
  {
    this.mChildHelper.stopNestedScroll(paramInt);
  }

  static class AccessibilityDelegate extends AccessibilityDelegateCompat
  {
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      NestedScrollView localNestedScrollView = (NestedScrollView)paramView;
      paramAccessibilityEvent.setClassName(ScrollView.class.getName());
      boolean bool;
      if (localNestedScrollView.getScrollRange() > 0)
        bool = true;
      else
        bool = false;
      paramAccessibilityEvent.setScrollable(bool);
      paramAccessibilityEvent.setScrollX(localNestedScrollView.getScrollX());
      paramAccessibilityEvent.setScrollY(localNestedScrollView.getScrollY());
      AccessibilityRecordCompat.setMaxScrollX(paramAccessibilityEvent, localNestedScrollView.getScrollX());
      AccessibilityRecordCompat.setMaxScrollY(paramAccessibilityEvent, localNestedScrollView.getScrollRange());
    }

    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      NestedScrollView localNestedScrollView = (NestedScrollView)paramView;
      paramAccessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
      if (localNestedScrollView.isEnabled())
      {
        int i = localNestedScrollView.getScrollRange();
        if (i > 0)
        {
          paramAccessibilityNodeInfoCompat.setScrollable(true);
          if (localNestedScrollView.getScrollY() > 0)
            paramAccessibilityNodeInfoCompat.addAction(8192);
          if (localNestedScrollView.getScrollY() < i)
            paramAccessibilityNodeInfoCompat.addAction(4096);
        }
      }
    }

    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle))
        return true;
      NestedScrollView localNestedScrollView = (NestedScrollView)paramView;
      if (!localNestedScrollView.isEnabled())
        return false;
      if (paramInt != 4096)
      {
        if (paramInt != 8192)
          return false;
        int j = localNestedScrollView.getHeight() - localNestedScrollView.getPaddingBottom() - localNestedScrollView.getPaddingTop();
        int k = Math.max(localNestedScrollView.getScrollY() - j, 0);
        if (k != localNestedScrollView.getScrollY())
        {
          localNestedScrollView.smoothScrollTo(0, k);
          return true;
        }
        return false;
      }
      int i = Math.min(localNestedScrollView.getHeight() - localNestedScrollView.getPaddingBottom() - localNestedScrollView.getPaddingTop() + localNestedScrollView.getScrollY(), localNestedScrollView.getScrollRange());
      if (i != localNestedScrollView.getScrollY())
      {
        localNestedScrollView.smoothScrollTo(0, i);
        return true;
      }
      return false;
    }
  }

  public static abstract interface OnScrollChangeListener
  {
    public abstract void onScrollChange(NestedScrollView paramNestedScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public NestedScrollView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new NestedScrollView.SavedState(paramAnonymousParcel);
      }

      public NestedScrollView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new NestedScrollView.SavedState[paramAnonymousInt];
      }
    };
    public int scrollPosition;

    SavedState(Parcel paramParcel)
    {
      super();
      this.scrollPosition = paramParcel.readInt();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("HorizontalScrollView.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" scrollPosition=");
      localStringBuilder.append(this.scrollPosition);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.scrollPosition);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.NestedScrollView
 * JD-Core Version:    0.6.1
 */