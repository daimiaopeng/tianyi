package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup
{
  private static final int CLOSE_ENOUGH = 2;
  private static final Comparator<ItemInfo> COMPARATOR = new Comparator()
  {
    public int compare(ViewPager.ItemInfo paramAnonymousItemInfo1, ViewPager.ItemInfo paramAnonymousItemInfo2)
    {
      return paramAnonymousItemInfo1.position - paramAnonymousItemInfo2.position;
    }
  };
  private static final boolean DEBUG = false;
  private static final int DEFAULT_GUTTER_SIZE = 16;
  private static final int DEFAULT_OFFSCREEN_PAGES = 1;
  private static final int DRAW_ORDER_DEFAULT = 0;
  private static final int DRAW_ORDER_FORWARD = 1;
  private static final int DRAW_ORDER_REVERSE = 2;
  private static final int INVALID_POINTER = -1;
  static final int[] LAYOUT_ATTRS = { 16842931 };
  private static final int MAX_SETTLE_DURATION = 600;
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  private static final int MIN_FLING_VELOCITY = 400;
  public static final int SCROLL_STATE_DRAGGING = 1;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_SETTLING = 2;
  private static final String TAG = "ViewPager";
  private static final boolean USE_CACHE;
  private static final Interpolator sInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      float f = paramAnonymousFloat - 1.0F;
      return 1.0F + f * (f * (f * (f * f)));
    }
  };
  private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
  private int mActivePointerId = -1;
  PagerAdapter mAdapter;
  private List<OnAdapterChangeListener> mAdapterChangeListeners;
  private int mBottomPageBounds;
  private boolean mCalledSuper;
  private int mChildHeightMeasureSpec;
  private int mChildWidthMeasureSpec;
  private int mCloseEnough;
  int mCurItem;
  private int mDecorChildCount;
  private int mDefaultGutterSize;
  private int mDrawingOrder;
  private ArrayList<View> mDrawingOrderedChildren;
  private final Runnable mEndScrollRunnable = new Runnable()
  {
    public void run()
    {
      ViewPager.this.setScrollState(0);
      ViewPager.this.populate();
    }
  };
  private int mExpectedAdapterCount;
  private long mFakeDragBeginTime;
  private boolean mFakeDragging;
  private boolean mFirstLayout = true;
  private float mFirstOffset = -3.402824E+038F;
  private int mFlingDistance;
  private int mGutterSize;
  private boolean mInLayout;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private OnPageChangeListener mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsScrollStarted;
  private boolean mIsUnableToDrag;
  private final ArrayList<ItemInfo> mItems = new ArrayList();
  private float mLastMotionX;
  private float mLastMotionY;
  private float mLastOffset = 3.4028235E+38F;
  private EdgeEffect mLeftEdge;
  private Drawable mMarginDrawable;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private boolean mNeedCalculatePageOffsets = false;
  private PagerObserver mObserver;
  private int mOffscreenPageLimit = 1;
  private OnPageChangeListener mOnPageChangeListener;
  private List<OnPageChangeListener> mOnPageChangeListeners;
  private int mPageMargin;
  private PageTransformer mPageTransformer;
  private int mPageTransformerLayerType;
  private boolean mPopulatePending;
  private Parcelable mRestoredAdapterState = null;
  private ClassLoader mRestoredClassLoader = null;
  private int mRestoredCurItem = -1;
  private EdgeEffect mRightEdge;
  private int mScrollState = 0;
  private Scroller mScroller;
  private boolean mScrollingCacheEnabled;
  private final ItemInfo mTempItem = new ItemInfo();
  private final Rect mTempRect = new Rect();
  private int mTopPageBounds;
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;

  public ViewPager(@NonNull Context paramContext)
  {
    super(paramContext);
    initViewPager();
  }

  public ViewPager(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }

  private void calculatePageOffsets(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2)
  {
    int i = this.mAdapter.getCount();
    int j = getClientWidth();
    float f1;
    if (j > 0)
      f1 = this.mPageMargin / j;
    else
      f1 = 0.0F;
    if (paramItemInfo2 != null)
    {
      int i7 = paramItemInfo2.position;
      if (i7 < paramItemInfo1.position)
      {
        float f7 = f1 + (paramItemInfo2.offset + paramItemInfo2.widthFactor);
        int i10 = i7 + 1;
        int i11 = 0;
        while ((i10 <= paramItemInfo1.position) && (i11 < this.mItems.size()))
        {
          for (ItemInfo localItemInfo4 = (ItemInfo)this.mItems.get(i11); (i10 > localItemInfo4.position) && (i11 < -1 + this.mItems.size()); localItemInfo4 = (ItemInfo)this.mItems.get(i11))
            i11++;
          while (i10 < localItemInfo4.position)
          {
            f7 += f1 + this.mAdapter.getPageWidth(i10);
            i10++;
          }
          localItemInfo4.offset = f7;
          f7 += f1 + localItemInfo4.widthFactor;
          i10++;
        }
      }
      if (i7 > paramItemInfo1.position)
      {
        int i8 = -1 + this.mItems.size();
        float f6 = paramItemInfo2.offset;
        for (int i9 = i7 - 1; (i9 >= paramItemInfo1.position) && (i8 >= 0); i9--)
        {
          for (ItemInfo localItemInfo3 = (ItemInfo)this.mItems.get(i8); (i9 < localItemInfo3.position) && (i8 > 0); localItemInfo3 = (ItemInfo)this.mItems.get(i8))
            i8--;
          while (i9 > localItemInfo3.position)
          {
            f6 -= f1 + this.mAdapter.getPageWidth(i9);
            i9--;
          }
          f6 -= f1 + localItemInfo3.widthFactor;
          localItemInfo3.offset = f6;
        }
      }
    }
    int k = this.mItems.size();
    float f2 = paramItemInfo1.offset;
    int m = -1 + paramItemInfo1.position;
    float f3;
    if (paramItemInfo1.position == 0)
      f3 = paramItemInfo1.offset;
    else
      f3 = -3.402824E+038F;
    this.mFirstOffset = f3;
    int n = paramItemInfo1.position;
    int i1 = i - 1;
    float f4;
    if (n == i1)
      f4 = paramItemInfo1.offset + paramItemInfo1.widthFactor - 1.0F;
    else
      f4 = 3.4028235E+38F;
    this.mLastOffset = f4;
    int i2 = paramInt - 1;
    while (i2 >= 0)
    {
      ItemInfo localItemInfo2 = (ItemInfo)this.mItems.get(i2);
      while (m > localItemInfo2.position)
      {
        PagerAdapter localPagerAdapter2 = this.mAdapter;
        int i6 = m - 1;
        f2 -= f1 + localPagerAdapter2.getPageWidth(m);
        m = i6;
      }
      f2 -= f1 + localItemInfo2.widthFactor;
      localItemInfo2.offset = f2;
      if (localItemInfo2.position == 0)
        this.mFirstOffset = f2;
      i2--;
      m--;
    }
    float f5 = f1 + (paramItemInfo1.offset + paramItemInfo1.widthFactor);
    int i3 = 1 + paramItemInfo1.position;
    int i4 = paramInt + 1;
    while (i4 < k)
    {
      ItemInfo localItemInfo1 = (ItemInfo)this.mItems.get(i4);
      while (i3 < localItemInfo1.position)
      {
        PagerAdapter localPagerAdapter1 = this.mAdapter;
        int i5 = i3 + 1;
        f5 += f1 + localPagerAdapter1.getPageWidth(i3);
        i3 = i5;
      }
      if (localItemInfo1.position == i1)
        this.mLastOffset = (f5 + localItemInfo1.widthFactor - 1.0F);
      localItemInfo1.offset = f5;
      f5 += f1 + localItemInfo1.widthFactor;
      i4++;
      i3++;
    }
    this.mNeedCalculatePageOffsets = false;
  }

  private void completeScroll(boolean paramBoolean)
  {
    int i;
    if (this.mScrollState == 2)
      i = 1;
    else
      i = 0;
    if (i != 0)
    {
      setScrollingCacheEnabled(false);
      if ((true ^ this.mScroller.isFinished()))
      {
        this.mScroller.abortAnimation();
        int m = getScrollX();
        int n = getScrollY();
        int i1 = this.mScroller.getCurrX();
        int i2 = this.mScroller.getCurrY();
        if ((m != i1) || (n != i2))
        {
          scrollTo(i1, i2);
          if (i1 != m)
            pageScrolled(i1);
        }
      }
    }
    this.mPopulatePending = false;
    int j = i;
    for (int k = 0; k < this.mItems.size(); k++)
    {
      ItemInfo localItemInfo = (ItemInfo)this.mItems.get(k);
      if (localItemInfo.scrolling)
      {
        localItemInfo.scrolling = false;
        j = 1;
      }
    }
    if (j != 0)
      if (paramBoolean)
        ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
      else
        this.mEndScrollRunnable.run();
  }

  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    if ((Math.abs(paramInt3) > this.mFlingDistance) && (Math.abs(paramInt2) > this.mMinimumVelocity))
    {
      if (paramInt2 <= 0)
        paramInt1++;
    }
    else
    {
      float f;
      if (paramInt1 >= this.mCurItem)
        f = 0.4F;
      else
        f = 0.6F;
      paramInt1 += (int)(paramFloat + f);
    }
    if (this.mItems.size() > 0)
    {
      ItemInfo localItemInfo1 = (ItemInfo)this.mItems.get(0);
      ItemInfo localItemInfo2 = (ItemInfo)this.mItems.get(-1 + this.mItems.size());
      paramInt1 = Math.max(localItemInfo1.position, Math.min(paramInt1, localItemInfo2.position));
    }
    return paramInt1;
  }

  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    if (this.mOnPageChangeListeners != null)
    {
      int i = 0;
      int j = this.mOnPageChangeListeners.size();
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)this.mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null)
          localOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        i++;
      }
    }
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
  }

  private void dispatchOnPageSelected(int paramInt)
  {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageSelected(paramInt);
    if (this.mOnPageChangeListeners != null)
    {
      int i = 0;
      int j = this.mOnPageChangeListeners.size();
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)this.mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null)
          localOnPageChangeListener.onPageSelected(paramInt);
        i++;
      }
    }
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageSelected(paramInt);
  }

  private void dispatchOnScrollStateChanged(int paramInt)
  {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageScrollStateChanged(paramInt);
    if (this.mOnPageChangeListeners != null)
    {
      int i = 0;
      int j = this.mOnPageChangeListeners.size();
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)this.mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null)
          localOnPageChangeListener.onPageScrollStateChanged(paramInt);
        i++;
      }
    }
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageScrollStateChanged(paramInt);
  }

  private void enableLayers(boolean paramBoolean)
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      int k;
      if (paramBoolean)
        k = this.mPageTransformerLayerType;
      else
        k = 0;
      getChildAt(j).setLayerType(k, null);
    }
  }

  private void endDrag()
  {
    this.mIsBeingDragged = false;
    this.mIsUnableToDrag = false;
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }

  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView)
  {
    if (paramRect == null)
      paramRect = new Rect();
    if (paramView == null)
    {
      paramRect.set(0, 0, 0, 0);
      return paramRect;
    }
    paramRect.left = paramView.getLeft();
    paramRect.right = paramView.getRight();
    paramRect.top = paramView.getTop();
    paramRect.bottom = paramView.getBottom();
    ViewGroup localViewGroup;
    for (ViewParent localViewParent = paramView.getParent(); ((localViewParent instanceof ViewGroup)) && (localViewParent != this); localViewParent = localViewGroup.getParent())
    {
      localViewGroup = (ViewGroup)localViewParent;
      paramRect.left += localViewGroup.getLeft();
      paramRect.right += localViewGroup.getRight();
      paramRect.top += localViewGroup.getTop();
      paramRect.bottom += localViewGroup.getBottom();
    }
    return paramRect;
  }

  private int getClientWidth()
  {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }

  private ItemInfo infoForCurrentScrollPosition()
  {
    int i = getClientWidth();
    float f1;
    if (i > 0)
      f1 = getScrollX() / i;
    else
      f1 = 0.0F;
    float f2;
    if (i > 0)
      f2 = this.mPageMargin / i;
    else
      f2 = 0.0F;
    Object localObject = null;
    int j = 0;
    int k = 1;
    int m = -1;
    float f3 = 0.0F;
    float f4 = 0.0F;
    while (j < this.mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)this.mItems.get(j);
      if (k == 0)
      {
        int n = localItemInfo.position;
        int i1 = m + 1;
        if (n != i1)
        {
          localItemInfo = this.mTempItem;
          localItemInfo.offset = (f2 + (f3 + f4));
          localItemInfo.position = i1;
          localItemInfo.widthFactor = this.mAdapter.getPageWidth(localItemInfo.position);
          j--;
        }
      }
      f3 = localItemInfo.offset;
      float f5 = f2 + (f3 + localItemInfo.widthFactor);
      if ((k == 0) && (f1 < f3))
        return localObject;
      if ((f1 >= f5) && (j != this.mItems.size() - 1))
      {
        m = localItemInfo.position;
        f4 = localItemInfo.widthFactor;
        j++;
        localObject = localItemInfo;
        k = 0;
      }
      else
      {
        return localItemInfo;
      }
    }
    return localObject;
  }

  private static boolean isDecorView(@NonNull View paramView)
  {
    boolean bool;
    if (paramView.getClass().getAnnotation(DecorView.class) != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean isGutterDrag(float paramFloat1, float paramFloat2)
  {
    boolean bool;
    if (((paramFloat1 < this.mGutterSize) && (paramFloat2 > 0.0F)) || ((paramFloat1 > getWidth() - this.mGutterSize) && (paramFloat2 < 0.0F)))
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
      this.mLastMotionX = paramMotionEvent.getX(j);
      this.mActivePointerId = paramMotionEvent.getPointerId(j);
      if (this.mVelocityTracker != null)
        this.mVelocityTracker.clear();
    }
  }

  private boolean pageScrolled(int paramInt)
  {
    if (this.mItems.size() == 0)
    {
      if (this.mFirstLayout)
        return false;
      this.mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (!this.mCalledSuper)
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
      return false;
    }
    ItemInfo localItemInfo = infoForCurrentScrollPosition();
    int i = getClientWidth();
    int j = i + this.mPageMargin;
    float f1 = this.mPageMargin;
    float f2 = i;
    float f3 = f1 / f2;
    int k = localItemInfo.position;
    float f4 = (paramInt / f2 - localItemInfo.offset) / (f3 + localItemInfo.widthFactor);
    int m = (int)(f4 * j);
    this.mCalledSuper = false;
    onPageScrolled(k, f4, m);
    if (!this.mCalledSuper)
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    return true;
  }

  private boolean performDrag(float paramFloat)
  {
    float f1 = this.mLastMotionX - paramFloat;
    this.mLastMotionX = paramFloat;
    float f2 = f1 + getScrollX();
    float f3 = getClientWidth();
    float f4 = f3 * this.mFirstOffset;
    float f5 = f3 * this.mLastOffset;
    ItemInfo localItemInfo1 = (ItemInfo)this.mItems.get(0);
    ItemInfo localItemInfo2 = (ItemInfo)this.mItems.get(this.mItems.size() - 1);
    int i;
    if (localItemInfo1.position != 0)
    {
      f4 = f3 * localItemInfo1.offset;
      i = 0;
    }
    else
    {
      i = 1;
    }
    int j;
    if (localItemInfo2.position != this.mAdapter.getCount() - 1)
    {
      f5 = f3 * localItemInfo2.offset;
      j = 0;
    }
    else
    {
      j = 1;
    }
    boolean bool1;
    if (f2 < f4)
    {
      bool1 = false;
      if (i != 0)
      {
        float f7 = f4 - f2;
        this.mLeftEdge.onPull(Math.abs(f7) / f3);
        bool1 = true;
      }
      f2 = f4;
    }
    else
    {
      boolean bool2 = f2 < f5;
      bool1 = false;
      if (bool2)
      {
        bool1 = false;
        if (j != 0)
        {
          float f8 = f2 - f5;
          this.mRightEdge.onPull(Math.abs(f8) / f3);
          bool1 = true;
        }
        f2 = f5;
      }
    }
    float f6 = this.mLastMotionX;
    int k = (int)f2;
    this.mLastMotionX = (f6 + (f2 - k));
    scrollTo(k, getScrollY());
    pageScrolled(k);
    return bool1;
  }

  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 > 0) && (!this.mItems.isEmpty()))
    {
      if (!this.mScroller.isFinished())
      {
        this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
      }
      else
      {
        int j = paramInt3 + (paramInt1 - getPaddingLeft() - getPaddingRight());
        int k = paramInt4 + (paramInt2 - getPaddingLeft() - getPaddingRight());
        scrollTo((int)(getScrollX() / k * j), getScrollY());
      }
    }
    else
    {
      ItemInfo localItemInfo = infoForPosition(this.mCurItem);
      float f;
      if (localItemInfo != null)
        f = Math.min(localItemInfo.offset, this.mLastOffset);
      else
        f = 0.0F;
      int i = (int)(f * paramInt1 - getPaddingLeft() - getPaddingRight());
      if (i != getScrollX())
      {
        completeScroll(false);
        scrollTo(i, getScrollY());
      }
    }
  }

  private void removeNonDecorViews()
  {
    for (int i = 0; i < getChildCount(); i++)
      if (!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor)
      {
        removeViewAt(i);
        i--;
      }
  }

  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null)
      localViewParent.requestDisallowInterceptTouchEvent(paramBoolean);
  }

  private boolean resetTouch()
  {
    this.mActivePointerId = -1;
    endDrag();
    this.mLeftEdge.onRelease();
    this.mRightEdge.onRelease();
    boolean bool;
    if ((!this.mLeftEdge.isFinished()) && (!this.mRightEdge.isFinished()))
      bool = false;
    else
      bool = true;
    return bool;
  }

  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    ItemInfo localItemInfo = infoForPosition(paramInt1);
    int i;
    if (localItemInfo != null)
      i = (int)(getClientWidth() * Math.max(this.mFirstOffset, Math.min(localItemInfo.offset, this.mLastOffset)));
    else
      i = 0;
    if (paramBoolean1)
    {
      smoothScrollTo(i, 0, paramInt2);
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1);
    }
    else
    {
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1);
      completeScroll(false);
      scrollTo(i, 0);
      pageScrolled(i);
    }
  }

  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (this.mScrollingCacheEnabled != paramBoolean)
      this.mScrollingCacheEnabled = paramBoolean;
  }

  private void sortChildDrawingOrder()
  {
    if (this.mDrawingOrder != 0)
    {
      if (this.mDrawingOrderedChildren == null)
        this.mDrawingOrderedChildren = new ArrayList();
      else
        this.mDrawingOrderedChildren.clear();
      int i = getChildCount();
      for (int j = 0; j < i; j++)
      {
        View localView = getChildAt(j);
        this.mDrawingOrderedChildren.add(localView);
      }
      Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
    }
  }

  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    int i = paramArrayList.size();
    int j = getDescendantFocusability();
    if (j != 393216)
      for (int k = 0; k < getChildCount(); k++)
      {
        View localView = getChildAt(k);
        if (localView.getVisibility() == 0)
        {
          ItemInfo localItemInfo = infoForChild(localView);
          if ((localItemInfo != null) && (localItemInfo.position == this.mCurItem))
            localView.addFocusables(paramArrayList, paramInt1, paramInt2);
        }
      }
    if ((j != 262144) || (i == paramArrayList.size()))
    {
      if (!isFocusable())
        return;
      if (((paramInt2 & 0x1) == 1) && (isInTouchMode()) && (!isFocusableInTouchMode()))
        return;
      if (paramArrayList != null)
        paramArrayList.add(this);
    }
  }

  ItemInfo addNewItem(int paramInt1, int paramInt2)
  {
    ItemInfo localItemInfo = new ItemInfo();
    localItemInfo.position = paramInt1;
    localItemInfo.object = this.mAdapter.instantiateItem(this, paramInt1);
    localItemInfo.widthFactor = this.mAdapter.getPageWidth(paramInt1);
    if ((paramInt2 >= 0) && (paramInt2 < this.mItems.size()))
      this.mItems.add(paramInt2, localItemInfo);
    else
      this.mItems.add(localItemInfo);
    return localItemInfo;
  }

  public void addOnAdapterChangeListener(@NonNull OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    if (this.mAdapterChangeListeners == null)
      this.mAdapterChangeListeners = new ArrayList();
    this.mAdapterChangeListeners.add(paramOnAdapterChangeListener);
  }

  public void addOnPageChangeListener(@NonNull OnPageChangeListener paramOnPageChangeListener)
  {
    if (this.mOnPageChangeListeners == null)
      this.mOnPageChangeListeners = new ArrayList();
    this.mOnPageChangeListeners.add(paramOnPageChangeListener);
  }

  public void addTouchables(ArrayList<View> paramArrayList)
  {
    for (int i = 0; i < getChildCount(); i++)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (localItemInfo.position == this.mCurItem))
          localView.addTouchables(paramArrayList);
      }
    }
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (!checkLayoutParams(paramLayoutParams))
      paramLayoutParams = generateLayoutParams(paramLayoutParams);
    LayoutParams localLayoutParams = (LayoutParams)paramLayoutParams;
    localLayoutParams.isDecor |= isDecorView(paramView);
    if (this.mInLayout)
    {
      if ((localLayoutParams != null) && (localLayoutParams.isDecor))
        throw new IllegalStateException("Cannot add pager decor view during layout");
      localLayoutParams.needsMeasure = true;
      addViewInLayout(paramView, paramInt, paramLayoutParams);
    }
    else
    {
      super.addView(paramView, paramInt, paramLayoutParams);
    }
  }

  public boolean arrowScroll(int paramInt)
  {
    View localView1 = findFocus();
    View localView2;
    if (localView1 == this)
    {
      localView2 = null;
    }
    else
    {
      if (localView1 != null)
      {
        for (ViewParent localViewParent1 = localView1.getParent(); (localViewParent1 instanceof ViewGroup); localViewParent1 = localViewParent1.getParent())
          if (localViewParent1 == this)
          {
            n = 1;
            break label60;
          }
        int n = 0;
        label60: if (n == 0)
        {
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append(localView1.getClass().getSimpleName());
          for (ViewParent localViewParent2 = localView1.getParent(); (localViewParent2 instanceof ViewGroup); localViewParent2 = localViewParent2.getParent())
          {
            localStringBuilder1.append(" => ");
            localStringBuilder1.append(localViewParent2.getClass().getSimpleName());
          }
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("arrowScroll tried to find focus based on non-child current focused view ");
          localStringBuilder2.append(localStringBuilder1.toString());
          Log.e("ViewPager", localStringBuilder2.toString());
          localView2 = null;
          break label183;
        }
      }
      localView2 = localView1;
    }
    label183: View localView3 = FocusFinder.getInstance().findNextFocus(this, localView2, paramInt);
    boolean bool1;
    if ((localView3 != null) && (localView3 != localView2))
    {
      boolean bool2;
      if (paramInt == 17)
      {
        int k = getChildRectInPagerCoordinates(this.mTempRect, localView3).left;
        int m = getChildRectInPagerCoordinates(this.mTempRect, localView2).left;
        if ((localView2 != null) && (k >= m))
          bool2 = pageLeft();
        else
          bool2 = localView3.requestFocus();
      }
      while (true)
      {
        bool1 = bool2;
        break;
        bool1 = false;
        if (paramInt != 66)
          break;
        int i = getChildRectInPagerCoordinates(this.mTempRect, localView3).left;
        int j = getChildRectInPagerCoordinates(this.mTempRect, localView2).left;
        if ((localView2 != null) && (i <= j))
          bool2 = pageRight();
        else
          bool2 = localView3.requestFocus();
      }
    }
    if ((paramInt != 17) && (paramInt != 1))
    {
      if (paramInt != 66)
      {
        bool1 = false;
        if (paramInt != 2);
      }
      else
      {
        bool1 = pageRight();
      }
    }
    else
      bool1 = pageLeft();
    if (bool1)
      playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
    return bool1;
  }

  public boolean beginFakeDrag()
  {
    if (this.mIsBeingDragged)
      return false;
    this.mFakeDragging = true;
    setScrollState(1);
    this.mLastMotionX = 0.0F;
    this.mInitialMotionX = 0.0F;
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    else
      this.mVelocityTracker.clear();
    long l = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
    this.mVelocityTracker.addMovement(localMotionEvent);
    localMotionEvent.recycle();
    this.mFakeDragBeginTime = l;
    return true;
  }

  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool1 = paramView instanceof ViewGroup;
    int i = 1;
    if (bool1)
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int j = paramView.getScrollX();
      int k = paramView.getScrollY();
      for (int m = localViewGroup.getChildCount() - i; m >= 0; m--)
      {
        View localView = localViewGroup.getChildAt(m);
        int n = paramInt2 + j;
        if ((n >= localView.getLeft()) && (n < localView.getRight()))
        {
          int i1 = paramInt3 + k;
          if ((i1 >= localView.getTop()) && (i1 < localView.getBottom()) && (canScroll(localView, true, paramInt1, n - localView.getLeft(), i1 - localView.getTop())))
            return i;
        }
      }
    }
    boolean bool2;
    if ((!paramBoolean) || (!paramView.canScrollHorizontally(-paramInt1)))
      bool2 = false;
    return bool2;
  }

  public boolean canScrollHorizontally(int paramInt)
  {
    if (this.mAdapter == null)
      return false;
    int i = getClientWidth();
    int j = getScrollX();
    if (paramInt < 0)
    {
      int m = (int)(i * this.mFirstOffset);
      boolean bool2 = false;
      if (j > m)
        bool2 = true;
      return bool2;
    }
    if (paramInt > 0)
    {
      int k = (int)(i * this.mLastOffset);
      boolean bool1 = false;
      if (j < k)
        bool1 = true;
      return bool1;
    }
    return false;
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    boolean bool;
    if (((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void clearOnPageChangeListeners()
  {
    if (this.mOnPageChangeListeners != null)
      this.mOnPageChangeListeners.clear();
  }

  public void computeScroll()
  {
    this.mIsScrollStarted = true;
    if ((!this.mScroller.isFinished()) && (this.mScroller.computeScrollOffset()))
    {
      int i = getScrollX();
      int j = getScrollY();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      if ((i != k) || (j != m))
      {
        scrollTo(k, m);
        if (!pageScrolled(k))
        {
          this.mScroller.abortAnimation();
          scrollTo(0, m);
        }
      }
      ViewCompat.postInvalidateOnAnimation(this);
      return;
    }
    completeScroll(true);
  }

  void dataSetChanged()
  {
    int i = this.mAdapter.getCount();
    this.mExpectedAdapterCount = i;
    int j;
    if ((this.mItems.size() < 1 + 2 * this.mOffscreenPageLimit) && (this.mItems.size() < i))
      j = 1;
    else
      j = 0;
    int k = this.mCurItem;
    int m = j;
    int n = k;
    int i1 = 0;
    int i2 = 0;
    while (i1 < this.mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)this.mItems.get(i1);
      int i5 = this.mAdapter.getItemPosition(localItemInfo.object);
      if (i5 != -1)
      {
        if (i5 == -2)
        {
          this.mItems.remove(i1);
          i1--;
          if (i2 == 0)
          {
            this.mAdapter.startUpdate(this);
            i2 = 1;
          }
          this.mAdapter.destroyItem(this, localItemInfo.position, localItemInfo.object);
          if (this.mCurItem == localItemInfo.position)
            n = Math.max(0, Math.min(this.mCurItem, i - 1));
        }
        while (true)
        {
          m = 1;
          break;
          if (localItemInfo.position == i5)
            break;
          if (localItemInfo.position == this.mCurItem)
            n = i5;
          localItemInfo.position = i5;
        }
      }
      i1++;
    }
    if (i2 != 0)
      this.mAdapter.finishUpdate(this);
    Collections.sort(this.mItems, COMPARATOR);
    if (m != 0)
    {
      int i3 = getChildCount();
      for (int i4 = 0; i4 < i3; i4++)
      {
        LayoutParams localLayoutParams = (LayoutParams)getChildAt(i4).getLayoutParams();
        if (!localLayoutParams.isDecor)
          localLayoutParams.widthFactor = 0.0F;
      }
      setCurrentItemInternal(n, false, true);
      requestLayout();
    }
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

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 4096)
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (localItemInfo.position == this.mCurItem) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent)))
          return true;
      }
    }
    return false;
  }

  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin(0.4712389F * (paramFloat - 0.5F));
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int i = getOverScrollMode();
    boolean bool2;
    if ((i != 0) && ((i != 1) || (this.mAdapter == null) || (this.mAdapter.getCount() <= 1)))
    {
      this.mLeftEdge.finish();
      this.mRightEdge.finish();
      bool2 = false;
    }
    else
    {
      boolean bool1 = this.mLeftEdge.isFinished();
      bool2 = false;
      if (!bool1)
      {
        int n = paramCanvas.save();
        int i1 = getHeight() - getPaddingTop() - getPaddingBottom();
        int i2 = getWidth();
        paramCanvas.rotate(270.0F);
        paramCanvas.translate(-i1 + getPaddingTop(), this.mFirstOffset * i2);
        this.mLeftEdge.setSize(i1, i2);
        bool2 = false | this.mLeftEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(n);
      }
      if (!this.mRightEdge.isFinished())
      {
        int j = paramCanvas.save();
        int k = getWidth();
        int m = getHeight() - getPaddingTop() - getPaddingBottom();
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-getPaddingTop(), -(1.0F + this.mLastOffset) * k);
        this.mRightEdge.setSize(m, k);
        bool2 |= this.mRightEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(j);
      }
    }
    if (bool2)
      ViewCompat.postInvalidateOnAnimation(this);
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = this.mMarginDrawable;
    if ((localDrawable != null) && (localDrawable.isStateful()))
      localDrawable.setState(getDrawableState());
  }

  public void endFakeDrag()
  {
    if (!this.mFakeDragging)
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    if (this.mAdapter != null)
    {
      VelocityTracker localVelocityTracker = this.mVelocityTracker;
      localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
      int i = (int)localVelocityTracker.getXVelocity(this.mActivePointerId);
      this.mPopulatePending = true;
      int j = getClientWidth();
      int k = getScrollX();
      ItemInfo localItemInfo = infoForCurrentScrollPosition();
      setCurrentItemInternal(determineTargetPage(localItemInfo.position, (k / j - localItemInfo.offset) / localItemInfo.widthFactor, i, (int)(this.mLastMotionX - this.mInitialMotionX)), true, true, i);
    }
    endDrag();
    this.mFakeDragging = false;
  }

  public boolean executeKeyEvent(@NonNull KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
    {
      int i = paramKeyEvent.getKeyCode();
      if (i != 61)
      {
        switch (i)
        {
        default:
          break;
        case 22:
          if (paramKeyEvent.hasModifiers(2))
            bool = pageRight();
          else
            bool = arrowScroll(66);
          break;
        case 21:
          if (paramKeyEvent.hasModifiers(2))
            bool = pageLeft();
          else
            bool = arrowScroll(17);
          break;
        }
      }
      else
      {
        if (paramKeyEvent.hasNoModifiers())
        {
          bool = arrowScroll(2);
          break label130;
        }
        if (paramKeyEvent.hasModifiers(1))
        {
          bool = arrowScroll(1);
          break label130;
        }
      }
    }
    boolean bool = false;
    label130: return bool;
  }

  public void fakeDragBy(float paramFloat)
  {
    if (!this.mFakeDragging)
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    if (this.mAdapter == null)
      return;
    this.mLastMotionX = (paramFloat + this.mLastMotionX);
    float f1 = getScrollX() - paramFloat;
    float f2 = getClientWidth();
    float f3 = f2 * this.mFirstOffset;
    float f4 = f2 * this.mLastOffset;
    ItemInfo localItemInfo1 = (ItemInfo)this.mItems.get(0);
    ItemInfo localItemInfo2 = (ItemInfo)this.mItems.get(-1 + this.mItems.size());
    if (localItemInfo1.position != 0)
      f3 = f2 * localItemInfo1.offset;
    if (localItemInfo2.position != -1 + this.mAdapter.getCount())
      f4 = f2 * localItemInfo2.offset;
    if (f1 < f3)
      f1 = f3;
    else if (f1 > f4)
      f1 = f4;
    float f5 = this.mLastMotionX;
    int i = (int)f1;
    this.mLastMotionX = (f5 + (f1 - i));
    scrollTo(i, getScrollY());
    pageScrolled(i);
    long l = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(this.mFakeDragBeginTime, l, 2, this.mLastMotionX, 0.0F, 0);
    this.mVelocityTracker.addMovement(localMotionEvent);
    localMotionEvent.recycle();
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return generateDefaultLayoutParams();
  }

  @Nullable
  public PagerAdapter getAdapter()
  {
    return this.mAdapter;
  }

  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (this.mDrawingOrder == 2)
      paramInt2 = paramInt1 - 1 - paramInt2;
    return ((LayoutParams)((View)this.mDrawingOrderedChildren.get(paramInt2)).getLayoutParams()).childIndex;
  }

  public int getCurrentItem()
  {
    return this.mCurItem;
  }

  public int getOffscreenPageLimit()
  {
    return this.mOffscreenPageLimit;
  }

  public int getPageMargin()
  {
    return this.mPageMargin;
  }

  ItemInfo infoForAnyChild(View paramView)
  {
    while (true)
    {
      ViewParent localViewParent = paramView.getParent();
      if (localViewParent == this)
        break label34;
      if ((localViewParent == null) || (!(localViewParent instanceof View)))
        break;
      paramView = (View)localViewParent;
    }
    return null;
    label34: return infoForChild(paramView);
  }

  ItemInfo infoForChild(View paramView)
  {
    for (int i = 0; i < this.mItems.size(); i++)
    {
      ItemInfo localItemInfo = (ItemInfo)this.mItems.get(i);
      if (this.mAdapter.isViewFromObject(paramView, localItemInfo.object))
        return localItemInfo;
    }
    return null;
  }

  ItemInfo infoForPosition(int paramInt)
  {
    for (int i = 0; i < this.mItems.size(); i++)
    {
      ItemInfo localItemInfo = (ItemInfo)this.mItems.get(i);
      if (localItemInfo.position == paramInt)
        return localItemInfo;
    }
    return null;
  }

  void initViewPager()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    this.mScroller = new Scroller(localContext, sInterpolator);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    float f = localContext.getResources().getDisplayMetrics().density;
    this.mTouchSlop = localViewConfiguration.getScaledPagingTouchSlop();
    this.mMinimumVelocity = (int)(400.0F * f);
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    this.mLeftEdge = new EdgeEffect(localContext);
    this.mRightEdge = new EdgeEffect(localContext);
    this.mFlingDistance = (int)(25.0F * f);
    this.mCloseEnough = (int)(2.0F * f);
    this.mDefaultGutterSize = (int)(f * 16.0F);
    ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
    if (ViewCompat.getImportantForAccessibility(this) == 0)
      ViewCompat.setImportantForAccessibility(this, 1);
    ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
    {
      private final Rect mTempRect = new Rect();

      public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
      {
        WindowInsetsCompat localWindowInsetsCompat1 = ViewCompat.onApplyWindowInsets(paramAnonymousView, paramAnonymousWindowInsetsCompat);
        if (localWindowInsetsCompat1.isConsumed())
          return localWindowInsetsCompat1;
        Rect localRect = this.mTempRect;
        localRect.left = localWindowInsetsCompat1.getSystemWindowInsetLeft();
        localRect.top = localWindowInsetsCompat1.getSystemWindowInsetTop();
        localRect.right = localWindowInsetsCompat1.getSystemWindowInsetRight();
        localRect.bottom = localWindowInsetsCompat1.getSystemWindowInsetBottom();
        int i = 0;
        int j = ViewPager.this.getChildCount();
        while (i < j)
        {
          WindowInsetsCompat localWindowInsetsCompat2 = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(i), localWindowInsetsCompat1);
          localRect.left = Math.min(localWindowInsetsCompat2.getSystemWindowInsetLeft(), localRect.left);
          localRect.top = Math.min(localWindowInsetsCompat2.getSystemWindowInsetTop(), localRect.top);
          localRect.right = Math.min(localWindowInsetsCompat2.getSystemWindowInsetRight(), localRect.right);
          localRect.bottom = Math.min(localWindowInsetsCompat2.getSystemWindowInsetBottom(), localRect.bottom);
          i++;
        }
        return localWindowInsetsCompat1.replaceSystemWindowInsets(localRect.left, localRect.top, localRect.right, localRect.bottom);
      }
    });
  }

  public boolean isFakeDragging()
  {
    return this.mFakeDragging;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }

  protected void onDetachedFromWindow()
  {
    removeCallbacks(this.mEndScrollRunnable);
    if ((this.mScroller != null) && (!this.mScroller.isFinished()))
      this.mScroller.abortAnimation();
    super.onDetachedFromWindow();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.mPageMargin > 0) && (this.mMarginDrawable != null) && (this.mItems.size() > 0) && (this.mAdapter != null))
    {
      int i = getScrollX();
      int j = getWidth();
      float f1 = this.mPageMargin;
      float f2 = j;
      float f3 = f1 / f2;
      ArrayList localArrayList1 = this.mItems;
      int k = 0;
      ItemInfo localItemInfo = (ItemInfo)localArrayList1.get(0);
      float f4 = localItemInfo.offset;
      int m = this.mItems.size();
      int n = localItemInfo.position;
      int i1 = ((ItemInfo)this.mItems.get(m - 1)).position;
      while (n < i1)
      {
        while ((n > localItemInfo.position) && (k < m))
        {
          ArrayList localArrayList2 = this.mItems;
          k++;
          localItemInfo = (ItemInfo)localArrayList2.get(k);
        }
        float f6;
        if (n == localItemInfo.position)
        {
          float f8 = f2 * (localItemInfo.offset + localItemInfo.widthFactor);
          float f9 = f3 + (localItemInfo.offset + localItemInfo.widthFactor);
          f6 = f8;
          f4 = f9;
        }
        else
        {
          float f5 = this.mAdapter.getPageWidth(n);
          f6 = f2 * (f4 + f5);
          f4 += f5 + f3;
        }
        float f7;
        if (f6 + this.mPageMargin > i)
        {
          Drawable localDrawable = this.mMarginDrawable;
          int i2 = Math.round(f6);
          int i3 = this.mTopPageBounds;
          int i4 = Math.round(f6 + this.mPageMargin);
          f7 = f3;
          localDrawable.setBounds(i2, i3, i4, this.mBottomPageBounds);
          this.mMarginDrawable.draw(paramCanvas);
        }
        else
        {
          f7 = f3;
        }
        if (f6 > i + j)
          break;
        n++;
        f3 = f7;
      }
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 0xFF & paramMotionEvent.getAction();
    if ((i != 3) && (i != 1))
    {
      if (i != 0)
      {
        if (this.mIsBeingDragged)
          return true;
        if (this.mIsUnableToDrag)
          return false;
      }
      if (i != 0)
      {
        if (i != 2)
        {
          if (i == 6)
            onSecondaryPointerUp(paramMotionEvent);
        }
        else
        {
          int j = this.mActivePointerId;
          if (j != -1)
          {
            int k = paramMotionEvent.findPointerIndex(j);
            float f3 = paramMotionEvent.getX(k);
            float f4 = f3 - this.mLastMotionX;
            float f5 = Math.abs(f4);
            float f6 = paramMotionEvent.getY(k);
            float f7 = Math.abs(f6 - this.mInitialMotionY);
            if ((f4 != 0.0F) && (!isGutterDrag(this.mLastMotionX, f4)) && (canScroll(this, false, (int)f4, (int)f3, (int)f6)))
            {
              this.mLastMotionX = f3;
              this.mLastMotionY = f6;
              this.mIsUnableToDrag = true;
              return false;
            }
            if ((f5 > this.mTouchSlop) && (f5 * 0.5F > f7))
            {
              this.mIsBeingDragged = true;
              requestParentDisallowInterceptTouchEvent(true);
              setScrollState(1);
              float f8;
              if (f4 > 0.0F)
                f8 = this.mInitialMotionX + this.mTouchSlop;
              else
                f8 = this.mInitialMotionX - this.mTouchSlop;
              this.mLastMotionX = f8;
              this.mLastMotionY = f6;
              setScrollingCacheEnabled(true);
            }
            else if (f7 > this.mTouchSlop)
            {
              this.mIsUnableToDrag = true;
            }
            if ((this.mIsBeingDragged) && (performDrag(f3)))
              ViewCompat.postInvalidateOnAnimation(this);
          }
        }
      }
      else
      {
        float f1 = paramMotionEvent.getX();
        this.mInitialMotionX = f1;
        this.mLastMotionX = f1;
        float f2 = paramMotionEvent.getY();
        this.mInitialMotionY = f2;
        this.mLastMotionY = f2;
        this.mActivePointerId = paramMotionEvent.getPointerId(0);
        this.mIsUnableToDrag = false;
        this.mIsScrollStarted = true;
        this.mScroller.computeScrollOffset();
        if ((this.mScrollState == 2) && (Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough))
        {
          this.mScroller.abortAnimation();
          this.mPopulatePending = false;
          populate();
          this.mIsBeingDragged = true;
          requestParentDisallowInterceptTouchEvent(true);
          setScrollState(1);
        }
        else
        {
          completeScroll(false);
          this.mIsBeingDragged = false;
        }
      }
      if (this.mVelocityTracker == null)
        this.mVelocityTracker = VelocityTracker.obtain();
      this.mVelocityTracker.addMovement(paramMotionEvent);
      return this.mIsBeingDragged;
    }
    resetTouch();
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    int j = paramInt3 - paramInt1;
    int k = paramInt4 - paramInt2;
    int m = getPaddingLeft();
    int n = getPaddingTop();
    int i1 = getPaddingRight();
    int i2 = getPaddingBottom();
    int i3 = getScrollX();
    int i4 = i2;
    int i5 = 0;
    int i6 = n;
    int i7 = m;
    for (int i8 = 0; i8 < i; i8++)
    {
      View localView2 = getChildAt(i8);
      if (localView2.getVisibility() != 8)
      {
        LayoutParams localLayoutParams2 = (LayoutParams)localView2.getLayoutParams();
        if (localLayoutParams2.isDecor)
        {
          int i12 = 0x7 & localLayoutParams2.gravity;
          int i13 = 0x70 & localLayoutParams2.gravity;
          int i14;
          if (i12 != 1)
          {
            if (i12 != 3)
            {
              if (i12 != 5)
              {
                i14 = i7;
              }
              else
              {
                i14 = j - i1 - localView2.getMeasuredWidth();
                i1 += localView2.getMeasuredWidth();
              }
            }
            else
            {
              int i18 = i7 + localView2.getMeasuredWidth();
              i14 = i7;
              i7 = i18;
            }
          }
          else
            i14 = Math.max((j - localView2.getMeasuredWidth()) / 2, i7);
          int i15;
          if (i13 != 16)
          {
            if (i13 != 48)
            {
              if (i13 != 80)
              {
                i15 = i6;
              }
              else
              {
                i15 = k - i4 - localView2.getMeasuredHeight();
                i4 += localView2.getMeasuredHeight();
              }
            }
            else
            {
              int i17 = i6 + localView2.getMeasuredHeight();
              i15 = i6;
              i6 = i17;
            }
          }
          else
            i15 = Math.max((k - localView2.getMeasuredHeight()) / 2, i6);
          int i16 = i14 + i3;
          localView2.layout(i16, i15, i16 + localView2.getMeasuredWidth(), i15 + localView2.getMeasuredHeight());
          i5++;
        }
      }
    }
    int i9 = j - i7 - i1;
    for (int i10 = 0; i10 < i; i10++)
    {
      View localView1 = getChildAt(i10);
      if (localView1.getVisibility() != 8)
      {
        LayoutParams localLayoutParams1 = (LayoutParams)localView1.getLayoutParams();
        if (!localLayoutParams1.isDecor)
        {
          ItemInfo localItemInfo = infoForChild(localView1);
          if (localItemInfo != null)
          {
            float f = i9;
            int i11 = i7 + (int)(f * localItemInfo.offset);
            if (localLayoutParams1.needsMeasure)
            {
              localLayoutParams1.needsMeasure = false;
              localView1.measure(View.MeasureSpec.makeMeasureSpec((int)(f * localLayoutParams1.widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec(k - i6 - i4, 1073741824));
            }
            localView1.layout(i11, i6, i11 + localView1.getMeasuredWidth(), i6 + localView1.getMeasuredHeight());
          }
        }
      }
    }
    this.mTopPageBounds = i6;
    this.mBottomPageBounds = (k - i4);
    this.mDecorChildCount = i5;
    if (this.mFirstLayout)
      scrollToItem(this.mCurItem, false, 0, false);
    this.mFirstLayout = false;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(getDefaultSize(0, paramInt1), getDefaultSize(0, paramInt2));
    int i = getMeasuredWidth();
    this.mGutterSize = Math.min(i / 10, this.mDefaultGutterSize);
    int j = i - getPaddingLeft() - getPaddingRight();
    int k = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    int m = getChildCount();
    int n = k;
    int i1 = j;
    boolean bool;
    int i3;
    for (int i2 = 0; ; i2++)
    {
      bool = true;
      i3 = 1073741824;
      if (i2 >= m)
        break;
      View localView2 = getChildAt(i2);
      if (localView2.getVisibility() != 8)
      {
        LayoutParams localLayoutParams2 = (LayoutParams)localView2.getLayoutParams();
        if ((localLayoutParams2 != null) && (localLayoutParams2.isDecor))
        {
          int i6 = 0x7 & localLayoutParams2.gravity;
          int i7 = 0x70 & localLayoutParams2.gravity;
          int i8;
          if ((i7 != 48) && (i7 != 80))
            i8 = 0;
          else
            i8 = 1;
          if ((i6 != 3) && (i6 != 5))
            bool = false;
          int i9 = -2147483648;
          if (i8 != 0)
            i9 = 1073741824;
          while (!bool)
          {
            i10 = -2147483648;
            break;
          }
          int i10 = 1073741824;
          int i11;
          if (localLayoutParams2.width != -2)
          {
            if (localLayoutParams2.width != -1)
              i11 = localLayoutParams2.width;
            else
              i11 = i1;
            i9 = 1073741824;
          }
          else
          {
            i11 = i1;
          }
          int i12;
          if (localLayoutParams2.height != -2)
          {
            if (localLayoutParams2.height != -1)
              i12 = localLayoutParams2.height;
            else
              i12 = n;
          }
          else
          {
            i12 = n;
            i3 = i10;
          }
          localView2.measure(View.MeasureSpec.makeMeasureSpec(i11, i9), View.MeasureSpec.makeMeasureSpec(i12, i3));
          if (i8 != 0)
            n -= localView2.getMeasuredHeight();
          else if (bool)
            i1 -= localView2.getMeasuredWidth();
        }
      }
    }
    this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(i1, i3);
    this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(n, i3);
    this.mInLayout = bool;
    populate();
    int i4 = 0;
    this.mInLayout = false;
    int i5 = getChildCount();
    while (i4 < i5)
    {
      View localView1 = getChildAt(i4);
      if (localView1.getVisibility() != 8)
      {
        LayoutParams localLayoutParams1 = (LayoutParams)localView1.getLayoutParams();
        if ((localLayoutParams1 == null) || (!localLayoutParams1.isDecor))
          localView1.measure(View.MeasureSpec.makeMeasureSpec((int)(i1 * localLayoutParams1.widthFactor), i3), this.mChildHeightMeasureSpec);
      }
      i4++;
    }
  }

  @CallSuper
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    int i = this.mDecorChildCount;
    int j = 0;
    if (i > 0)
    {
      int n = getScrollX();
      int i1 = getPaddingLeft();
      int i2 = getPaddingRight();
      int i3 = getWidth();
      int i4 = getChildCount();
      int i5 = i2;
      int i6 = i1;
      for (int i7 = 0; i7 < i4; i7++)
      {
        View localView2 = getChildAt(i7);
        LayoutParams localLayoutParams = (LayoutParams)localView2.getLayoutParams();
        if (localLayoutParams.isDecor)
        {
          int i8 = 0x7 & localLayoutParams.gravity;
          int i9;
          if (i8 != 1)
          {
            if (i8 != 3)
            {
              if (i8 != 5)
              {
                i11 = i6;
                break label194;
              }
              i9 = i3 - i5 - localView2.getMeasuredWidth();
              i5 += localView2.getMeasuredWidth();
            }
            else
            {
              i11 = i6 + localView2.getWidth();
              break label194;
            }
          }
          else
            i9 = Math.max((i3 - localView2.getMeasuredWidth()) / 2, i6);
          int i10 = i9;
          int i11 = i6;
          i6 = i10;
          label194: int i12 = i6 + n - localView2.getLeft();
          if (i12 != 0)
            localView2.offsetLeftAndRight(i12);
          i6 = i11;
        }
      }
    }
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (this.mPageTransformer != null)
    {
      int k = getScrollX();
      int m = getChildCount();
      while (j < m)
      {
        View localView1 = getChildAt(j);
        if (!((LayoutParams)localView1.getLayoutParams()).isDecor)
        {
          float f = localView1.getLeft() - k / getClientWidth();
          this.mPageTransformer.transformPage(localView1, f);
        }
        j++;
      }
    }
    this.mCalledSuper = true;
  }

  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int i = getChildCount();
    int j = paramInt & 0x2;
    int k = -1;
    int n;
    int m;
    if (j != 0)
    {
      k = i;
      n = 1;
      m = 0;
    }
    else
    {
      m = i - 1;
      n = -1;
    }
    while (m != k)
    {
      View localView = getChildAt(m);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (localItemInfo.position == this.mCurItem) && (localView.requestFocus(paramInt, paramRect)))
          return true;
      }
      m += n;
    }
    return false;
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    if (this.mAdapter != null)
    {
      this.mAdapter.restoreState(localSavedState.adapterState, localSavedState.loader);
      setCurrentItemInternal(localSavedState.position, false, true);
    }
    else
    {
      this.mRestoredCurItem = localSavedState.position;
      this.mRestoredAdapterState = localSavedState.adapterState;
      this.mRestoredClassLoader = localSavedState.loader;
    }
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.position = this.mCurItem;
    if (this.mAdapter != null)
      localSavedState.adapterState = this.mAdapter.saveState();
    return localSavedState;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
      recomputeScrollPosition(paramInt1, paramInt3, this.mPageMargin, this.mPageMargin);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mFakeDragging)
      return true;
    int i = paramMotionEvent.getAction();
    boolean bool1 = false;
    if ((i == 0) && (paramMotionEvent.getEdgeFlags() != 0))
      return false;
    if ((this.mAdapter != null) && (this.mAdapter.getCount() != 0))
    {
      if (this.mVelocityTracker == null)
        this.mVelocityTracker = VelocityTracker.obtain();
      this.mVelocityTracker.addMovement(paramMotionEvent);
      switch (0xFF & paramMotionEvent.getAction())
      {
      case 4:
      default:
        bool1 = false;
        break;
      case 6:
        onSecondaryPointerUp(paramMotionEvent);
        this.mLastMotionX = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId));
        bool1 = false;
        break;
      case 5:
        int i1 = paramMotionEvent.getActionIndex();
        this.mLastMotionX = paramMotionEvent.getX(i1);
        this.mActivePointerId = paramMotionEvent.getPointerId(i1);
        bool1 = false;
        break;
      case 3:
        boolean bool4 = this.mIsBeingDragged;
        bool1 = false;
        if (bool4)
        {
          scrollToItem(this.mCurItem, true, 0, false);
          bool1 = resetTouch();
        }
        break;
      case 2:
        if (!this.mIsBeingDragged)
        {
          int n = paramMotionEvent.findPointerIndex(this.mActivePointerId);
          if (n == -1)
          {
            bool1 = resetTouch();
            break;
          }
          float f6 = paramMotionEvent.getX(n);
          float f7 = Math.abs(f6 - this.mLastMotionX);
          float f8 = paramMotionEvent.getY(n);
          float f9 = Math.abs(f8 - this.mLastMotionY);
          if ((f7 > this.mTouchSlop) && (f7 > f9))
          {
            this.mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            float f10;
            if (f6 - this.mInitialMotionX > 0.0F)
              f10 = this.mInitialMotionX + this.mTouchSlop;
            else
              f10 = this.mInitialMotionX - this.mTouchSlop;
            this.mLastMotionX = f10;
            this.mLastMotionY = f8;
            setScrollState(1);
            setScrollingCacheEnabled(true);
            ViewParent localViewParent = getParent();
            if (localViewParent != null)
              localViewParent.requestDisallowInterceptTouchEvent(true);
          }
        }
        boolean bool3 = this.mIsBeingDragged;
        bool1 = false;
        if (bool3)
          bool1 = false | performDrag(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId)));
        break;
      case 1:
        boolean bool2 = this.mIsBeingDragged;
        bool1 = false;
        if (bool2)
        {
          VelocityTracker localVelocityTracker = this.mVelocityTracker;
          localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
          int j = (int)localVelocityTracker.getXVelocity(this.mActivePointerId);
          this.mPopulatePending = true;
          int k = getClientWidth();
          int m = getScrollX();
          ItemInfo localItemInfo = infoForCurrentScrollPosition();
          float f3 = this.mPageMargin;
          float f4 = k;
          float f5 = f3 / f4;
          setCurrentItemInternal(determineTargetPage(localItemInfo.position, (m / f4 - localItemInfo.offset) / (f5 + localItemInfo.widthFactor), j, (int)(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, j);
          bool1 = resetTouch();
        }
        break;
      case 0:
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        float f1 = paramMotionEvent.getX();
        this.mInitialMotionX = f1;
        this.mLastMotionX = f1;
        float f2 = paramMotionEvent.getY();
        this.mInitialMotionY = f2;
        this.mLastMotionY = f2;
        this.mActivePointerId = paramMotionEvent.getPointerId(0);
      }
      if (bool1)
        ViewCompat.postInvalidateOnAnimation(this);
      return true;
    }
    return false;
  }

  boolean pageLeft()
  {
    if (this.mCurItem > 0)
    {
      setCurrentItem(this.mCurItem - 1, true);
      return true;
    }
    return false;
  }

  boolean pageRight()
  {
    if ((this.mAdapter != null) && (this.mCurItem < this.mAdapter.getCount() - 1))
    {
      setCurrentItem(1 + this.mCurItem, true);
      return true;
    }
    return false;
  }

  void populate()
  {
    populate(this.mCurItem);
  }

  // ERROR //
  void populate(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   4: iload_1
    //   5: if_icmpeq +20 -> 25
    //   8: aload_0
    //   9: aload_0
    //   10: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   13: invokevirtual 493	android/support/v4/view/ViewPager:infoForPosition	(I)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   16: astore_2
    //   17: aload_0
    //   18: iload_1
    //   19: putfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   22: goto +5 -> 27
    //   25: aconst_null
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   31: ifnonnull +8 -> 39
    //   34: aload_0
    //   35: invokespecial 1150	android/support/v4/view/ViewPager:sortChildDrawingOrder	()V
    //   38: return
    //   39: aload_0
    //   40: getfield 270	android/support/v4/view/ViewPager:mPopulatePending	Z
    //   43: ifeq +8 -> 51
    //   46: aload_0
    //   47: invokespecial 1150	android/support/v4/view/ViewPager:sortChildDrawingOrder	()V
    //   50: return
    //   51: aload_0
    //   52: invokevirtual 1154	android/support/v4/view/ViewPager:getWindowToken	()Landroid/os/IBinder;
    //   55: ifnonnull +4 -> 59
    //   58: return
    //   59: aload_0
    //   60: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   63: aload_0
    //   64: invokevirtual 751	android/support/v4/view/PagerAdapter:startUpdate	(Landroid/view/ViewGroup;)V
    //   67: aload_0
    //   68: getfield 175	android/support/v4/view/ViewPager:mOffscreenPageLimit	I
    //   71: istore_3
    //   72: iconst_0
    //   73: aload_0
    //   74: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   77: iload_3
    //   78: isub
    //   79: invokestatic 307	java/lang/Math:max	(II)I
    //   82: istore 4
    //   84: aload_0
    //   85: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   88: invokevirtual 207	android/support/v4/view/PagerAdapter:getCount	()I
    //   91: istore 5
    //   93: iload 5
    //   95: iconst_1
    //   96: isub
    //   97: iload_3
    //   98: aload_0
    //   99: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   102: iadd
    //   103: invokestatic 304	java/lang/Math:min	(II)I
    //   106: istore 6
    //   108: iload 5
    //   110: aload_0
    //   111: getfield 740	android/support/v4/view/ViewPager:mExpectedAdapterCount	I
    //   114: if_icmpeq +144 -> 258
    //   117: aload_0
    //   118: invokevirtual 1155	android/support/v4/view/ViewPager:getResources	()Landroid/content/res/Resources;
    //   121: aload_0
    //   122: invokevirtual 1158	android/support/v4/view/ViewPager:getId	()I
    //   125: invokevirtual 1162	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   128: astore 35
    //   130: goto +12 -> 142
    //   133: aload_0
    //   134: invokevirtual 1158	android/support/v4/view/ViewPager:getId	()I
    //   137: invokestatic 1167	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   140: astore 35
    //   142: new 635	java/lang/StringBuilder
    //   145: dup
    //   146: invokespecial 636	java/lang/StringBuilder:<init>	()V
    //   149: astore 36
    //   151: aload 36
    //   153: ldc_w 1169
    //   156: invokevirtual 644	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload 36
    //   162: aload_0
    //   163: getfield 740	android/support/v4/view/ViewPager:mExpectedAdapterCount	I
    //   166: invokevirtual 1172	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload 36
    //   172: ldc_w 1174
    //   175: invokevirtual 644	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload 36
    //   181: iload 5
    //   183: invokevirtual 1172	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload 36
    //   189: ldc_w 1176
    //   192: invokevirtual 644	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload 36
    //   198: aload 35
    //   200: invokevirtual 644	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: pop
    //   204: aload 36
    //   206: ldc_w 1178
    //   209: invokevirtual 644	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload 36
    //   215: aload_0
    //   216: invokevirtual 419	java/lang/Object:getClass	()Ljava/lang/Class;
    //   219: invokevirtual 1181	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: aload 36
    //   225: ldc_w 1183
    //   228: invokevirtual 644	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload 36
    //   234: aload_0
    //   235: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   238: invokevirtual 419	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: invokevirtual 1181	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: new 457	java/lang/IllegalStateException
    //   248: dup
    //   249: aload 36
    //   251: invokevirtual 651	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: invokespecial 462	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   257: athrow
    //   258: iconst_0
    //   259: istore 7
    //   261: iload 7
    //   263: aload_0
    //   264: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   267: invokevirtual 224	java/util/ArrayList:size	()I
    //   270: if_icmpge +50 -> 320
    //   273: aload_0
    //   274: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   277: iload 7
    //   279: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   282: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   285: astore 8
    //   287: aload 8
    //   289: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   292: aload_0
    //   293: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   296: if_icmplt +18 -> 314
    //   299: aload 8
    //   301: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   304: aload_0
    //   305: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   308: if_icmpne +12 -> 320
    //   311: goto +12 -> 323
    //   314: iinc 7 1
    //   317: goto -56 -> 261
    //   320: aconst_null
    //   321: astore 8
    //   323: aload 8
    //   325: ifnonnull +20 -> 345
    //   328: iload 5
    //   330: ifle +15 -> 345
    //   333: aload_0
    //   334: aload_0
    //   335: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   338: iload 7
    //   340: invokevirtual 1185	android/support/v4/view/ViewPager:addNewItem	(II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   343: astore 8
    //   345: aload 8
    //   347: ifnull +618 -> 965
    //   350: iload 7
    //   352: iconst_1
    //   353: isub
    //   354: istore 19
    //   356: iload 19
    //   358: iflt +20 -> 378
    //   361: aload_0
    //   362: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   365: iload 19
    //   367: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   370: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   373: astore 20
    //   375: goto +6 -> 381
    //   378: aconst_null
    //   379: astore 20
    //   381: aload_0
    //   382: invokespecial 210	android/support/v4/view/ViewPager:getClientWidth	()I
    //   385: istore 21
    //   387: iload 21
    //   389: ifgt +9 -> 398
    //   392: fconst_0
    //   393: fstore 22
    //   395: goto +22 -> 417
    //   398: fconst_2
    //   399: aload 8
    //   401: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   404: fsub
    //   405: aload_0
    //   406: invokevirtual 406	android/support/v4/view/ViewPager:getPaddingLeft	()I
    //   409: i2f
    //   410: iload 21
    //   412: i2f
    //   413: fdiv
    //   414: fadd
    //   415: fstore 22
    //   417: iconst_m1
    //   418: aload_0
    //   419: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   422: iadd
    //   423: istore 23
    //   425: iload 7
    //   427: istore 24
    //   429: fconst_0
    //   430: fstore 25
    //   432: iload 23
    //   434: iflt +203 -> 637
    //   437: fload 25
    //   439: fload 22
    //   441: fcmpl
    //   442: iflt +89 -> 531
    //   445: iload 23
    //   447: iload 4
    //   449: if_icmpge +82 -> 531
    //   452: aload 20
    //   454: ifnonnull +6 -> 460
    //   457: goto +180 -> 637
    //   460: iload 23
    //   462: aload 20
    //   464: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   467: if_icmpne +164 -> 631
    //   470: aload 20
    //   472: getfield 273	android/support/v4/view/ViewPager$ItemInfo:scrolling	Z
    //   475: ifne +156 -> 631
    //   478: aload_0
    //   479: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   482: iload 19
    //   484: invokevirtual 747	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   487: pop
    //   488: aload_0
    //   489: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   492: aload_0
    //   493: iload 23
    //   495: aload 20
    //   497: getfield 588	android/support/v4/view/ViewPager$ItemInfo:object	Ljava/lang/Object;
    //   500: invokevirtual 755	android/support/v4/view/PagerAdapter:destroyItem	(Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   503: iinc 19 255
    //   506: iinc 24 255
    //   509: iload 19
    //   511: iflt +113 -> 624
    //   514: aload_0
    //   515: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   518: iload 19
    //   520: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   523: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   526: astore 33
    //   528: goto +99 -> 627
    //   531: aload 20
    //   533: ifnull +48 -> 581
    //   536: iload 23
    //   538: aload 20
    //   540: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   543: if_icmpne +38 -> 581
    //   546: fload 25
    //   548: aload 20
    //   550: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   553: fadd
    //   554: fstore 25
    //   556: iinc 19 255
    //   559: iload 19
    //   561: iflt +63 -> 624
    //   564: aload_0
    //   565: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   568: iload 19
    //   570: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   573: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   576: astore 33
    //   578: goto +49 -> 627
    //   581: fload 25
    //   583: aload_0
    //   584: iload 23
    //   586: iload 19
    //   588: iconst_1
    //   589: iadd
    //   590: invokevirtual 1185	android/support/v4/view/ViewPager:addNewItem	(II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   593: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   596: fadd
    //   597: fstore 25
    //   599: iinc 24 1
    //   602: iload 19
    //   604: iflt +20 -> 624
    //   607: aload_0
    //   608: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   611: iload 19
    //   613: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   616: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   619: astore 33
    //   621: goto +6 -> 627
    //   624: aconst_null
    //   625: astore 33
    //   627: aload 33
    //   629: astore 20
    //   631: iinc 23 255
    //   634: goto -202 -> 432
    //   637: aload 8
    //   639: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   642: fstore 26
    //   644: iload 24
    //   646: iconst_1
    //   647: iadd
    //   648: istore 27
    //   650: fload 26
    //   652: fconst_2
    //   653: fcmpg
    //   654: ifge +285 -> 939
    //   657: iload 27
    //   659: aload_0
    //   660: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   663: invokevirtual 224	java/util/ArrayList:size	()I
    //   666: if_icmpge +20 -> 686
    //   669: aload_0
    //   670: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   673: iload 27
    //   675: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   678: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   681: astore 28
    //   683: goto +6 -> 689
    //   686: aconst_null
    //   687: astore 28
    //   689: iload 21
    //   691: ifgt +9 -> 700
    //   694: fconst_0
    //   695: fstore 29
    //   697: goto +16 -> 713
    //   700: fconst_2
    //   701: aload_0
    //   702: invokevirtual 409	android/support/v4/view/ViewPager:getPaddingRight	()I
    //   705: i2f
    //   706: iload 21
    //   708: i2f
    //   709: fdiv
    //   710: fadd
    //   711: fstore 29
    //   713: aload_0
    //   714: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   717: istore 30
    //   719: iinc 30 1
    //   722: iload 30
    //   724: iload 5
    //   726: if_icmpge +213 -> 939
    //   729: fload 26
    //   731: fload 29
    //   733: fcmpl
    //   734: iflt +96 -> 830
    //   737: iload 30
    //   739: iload 6
    //   741: if_icmple +89 -> 830
    //   744: aload 28
    //   746: ifnonnull +6 -> 752
    //   749: goto +190 -> 939
    //   752: iload 30
    //   754: aload 28
    //   756: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   759: if_icmpne +177 -> 936
    //   762: aload 28
    //   764: getfield 273	android/support/v4/view/ViewPager$ItemInfo:scrolling	Z
    //   767: ifne +169 -> 936
    //   770: aload_0
    //   771: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   774: iload 27
    //   776: invokevirtual 747	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   779: pop
    //   780: aload_0
    //   781: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   784: aload_0
    //   785: iload 30
    //   787: aload 28
    //   789: getfield 588	android/support/v4/view/ViewPager$ItemInfo:object	Ljava/lang/Object;
    //   792: invokevirtual 755	android/support/v4/view/PagerAdapter:destroyItem	(Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   795: iload 27
    //   797: aload_0
    //   798: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   801: invokevirtual 224	java/util/ArrayList:size	()I
    //   804: if_icmpge +20 -> 824
    //   807: aload_0
    //   808: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   811: iload 27
    //   813: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   816: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   819: astore 28
    //   821: goto +115 -> 936
    //   824: aconst_null
    //   825: astore 28
    //   827: goto +109 -> 936
    //   830: aload 28
    //   832: ifnull +55 -> 887
    //   835: iload 30
    //   837: aload 28
    //   839: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   842: if_icmpne +45 -> 887
    //   845: fload 26
    //   847: aload 28
    //   849: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   852: fadd
    //   853: fstore 26
    //   855: iinc 27 1
    //   858: iload 27
    //   860: aload_0
    //   861: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   864: invokevirtual 224	java/util/ArrayList:size	()I
    //   867: if_icmpge -43 -> 824
    //   870: aload_0
    //   871: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   874: iload 27
    //   876: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   879: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   882: astore 28
    //   884: goto +52 -> 936
    //   887: aload_0
    //   888: iload 30
    //   890: iload 27
    //   892: invokevirtual 1185	android/support/v4/view/ViewPager:addNewItem	(II)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   895: astore 31
    //   897: iinc 27 1
    //   900: fload 26
    //   902: aload 31
    //   904: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   907: fadd
    //   908: fstore 26
    //   910: iload 27
    //   912: aload_0
    //   913: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   916: invokevirtual 224	java/util/ArrayList:size	()I
    //   919: if_icmpge -95 -> 824
    //   922: aload_0
    //   923: getfield 151	android/support/v4/view/ViewPager:mItems	Ljava/util/ArrayList;
    //   926: iload 27
    //   928: invokevirtual 228	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   931: checkcast 153	android/support/v4/view/ViewPager$ItemInfo
    //   934: astore 28
    //   936: goto -217 -> 719
    //   939: aload_0
    //   940: aload 8
    //   942: iload 24
    //   944: aload_2
    //   945: invokespecial 1187	android/support/v4/view/ViewPager:calculatePageOffsets	(Landroid/support/v4/view/ViewPager$ItemInfo;ILandroid/support/v4/view/ViewPager$ItemInfo;)V
    //   948: aload_0
    //   949: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   952: aload_0
    //   953: aload_0
    //   954: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   957: aload 8
    //   959: getfield 588	android/support/v4/view/ViewPager$ItemInfo:object	Ljava/lang/Object;
    //   962: invokevirtual 1190	android/support/v4/view/PagerAdapter:setPrimaryItem	(Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   965: aload_0
    //   966: getfield 201	android/support/v4/view/ViewPager:mAdapter	Landroid/support/v4/view/PagerAdapter;
    //   969: aload_0
    //   970: invokevirtual 758	android/support/v4/view/PagerAdapter:finishUpdate	(Landroid/view/ViewGroup;)V
    //   973: aload_0
    //   974: invokevirtual 337	android/support/v4/view/ViewPager:getChildCount	()I
    //   977: istore 9
    //   979: iconst_0
    //   980: istore 10
    //   982: iload 10
    //   984: iload 9
    //   986: if_icmpge +85 -> 1071
    //   989: aload_0
    //   990: iload 10
    //   992: invokevirtual 343	android/support/v4/view/ViewPager:getChildAt	(I)Landroid/view/View;
    //   995: astore 16
    //   997: aload 16
    //   999: invokevirtual 503	android/view/View:getLayoutParams	()Landroid/view/ViewGroup$LayoutParams;
    //   1002: checkcast 505	android/support/v4/view/ViewPager$LayoutParams
    //   1005: astore 17
    //   1007: aload 17
    //   1009: iload 10
    //   1011: putfield 900	android/support/v4/view/ViewPager$LayoutParams:childIndex	I
    //   1014: aload 17
    //   1016: getfield 508	android/support/v4/view/ViewPager$LayoutParams:isDecor	Z
    //   1019: ifne +46 -> 1065
    //   1022: aload 17
    //   1024: getfield 759	android/support/v4/view/ViewPager$LayoutParams:widthFactor	F
    //   1027: fconst_0
    //   1028: fcmpl
    //   1029: ifne +36 -> 1065
    //   1032: aload_0
    //   1033: aload 16
    //   1035: invokevirtual 566	android/support/v4/view/ViewPager:infoForChild	(Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1038: astore 18
    //   1040: aload 18
    //   1042: ifnull +23 -> 1065
    //   1045: aload 17
    //   1047: aload 18
    //   1049: getfield 221	android/support/v4/view/ViewPager$ItemInfo:widthFactor	F
    //   1052: putfield 759	android/support/v4/view/ViewPager$LayoutParams:widthFactor	F
    //   1055: aload 17
    //   1057: aload 18
    //   1059: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   1062: putfield 1191	android/support/v4/view/ViewPager$LayoutParams:position	I
    //   1065: iinc 10 1
    //   1068: goto -86 -> 982
    //   1071: aload_0
    //   1072: invokespecial 1150	android/support/v4/view/ViewPager:sortChildDrawingOrder	()V
    //   1075: aload_0
    //   1076: invokevirtual 1194	android/support/v4/view/ViewPager:hasFocus	()Z
    //   1079: ifeq +108 -> 1187
    //   1082: aload_0
    //   1083: invokevirtual 632	android/support/v4/view/ViewPager:findFocus	()Landroid/view/View;
    //   1086: astore 11
    //   1088: aload 11
    //   1090: ifnull +14 -> 1104
    //   1093: aload_0
    //   1094: aload 11
    //   1096: invokevirtual 1196	android/support/v4/view/ViewPager:infoForAnyChild	(Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1099: astore 12
    //   1101: goto +6 -> 1107
    //   1104: aconst_null
    //   1105: astore 12
    //   1107: aload 12
    //   1109: ifnull +15 -> 1124
    //   1112: aload 12
    //   1114: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   1117: aload_0
    //   1118: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   1121: if_icmpeq +66 -> 1187
    //   1124: iconst_0
    //   1125: istore 13
    //   1127: iload 13
    //   1129: aload_0
    //   1130: invokevirtual 337	android/support/v4/view/ViewPager:getChildCount	()I
    //   1133: if_icmpge +54 -> 1187
    //   1136: aload_0
    //   1137: iload 13
    //   1139: invokevirtual 343	android/support/v4/view/ViewPager:getChildAt	(I)Landroid/view/View;
    //   1142: astore 14
    //   1144: aload_0
    //   1145: aload 14
    //   1147: invokevirtual 566	android/support/v4/view/ViewPager:infoForChild	(Landroid/view/View;)Landroid/support/v4/view/ViewPager$ItemInfo;
    //   1150: astore 15
    //   1152: aload 15
    //   1154: ifnull +27 -> 1181
    //   1157: aload 15
    //   1159: getfield 215	android/support/v4/view/ViewPager$ItemInfo:position	I
    //   1162: aload_0
    //   1163: getfield 298	android/support/v4/view/ViewPager:mCurItem	I
    //   1166: if_icmpne +15 -> 1181
    //   1169: aload 14
    //   1171: iconst_2
    //   1172: invokevirtual 1198	android/view/View:requestFocus	(I)Z
    //   1175: ifeq +6 -> 1181
    //   1178: goto +9 -> 1187
    //   1181: iinc 13 1
    //   1184: goto -57 -> 1127
    //   1187: return
    //
    // Exception table:
    //   from	to	target	type
    //   117	130	133	android/content/res/Resources$NotFoundException
  }

  public void removeOnAdapterChangeListener(@NonNull OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    if (this.mAdapterChangeListeners != null)
      this.mAdapterChangeListeners.remove(paramOnAdapterChangeListener);
  }

  public void removeOnPageChangeListener(@NonNull OnPageChangeListener paramOnPageChangeListener)
  {
    if (this.mOnPageChangeListeners != null)
      this.mOnPageChangeListeners.remove(paramOnPageChangeListener);
  }

  public void removeView(View paramView)
  {
    if (this.mInLayout)
      removeViewInLayout(paramView);
    else
      super.removeView(paramView);
  }

  public void setAdapter(@Nullable PagerAdapter paramPagerAdapter)
  {
    PagerAdapter localPagerAdapter1 = this.mAdapter;
    int i = 0;
    if (localPagerAdapter1 != null)
    {
      this.mAdapter.setViewPagerObserver(null);
      this.mAdapter.startUpdate(this);
      for (int k = 0; k < this.mItems.size(); k++)
      {
        ItemInfo localItemInfo = (ItemInfo)this.mItems.get(k);
        this.mAdapter.destroyItem(this, localItemInfo.position, localItemInfo.object);
      }
      this.mAdapter.finishUpdate(this);
      this.mItems.clear();
      removeNonDecorViews();
      this.mCurItem = 0;
      scrollTo(0, 0);
    }
    PagerAdapter localPagerAdapter2 = this.mAdapter;
    this.mAdapter = paramPagerAdapter;
    this.mExpectedAdapterCount = 0;
    if (this.mAdapter != null)
    {
      if (this.mObserver == null)
        this.mObserver = new PagerObserver();
      this.mAdapter.setViewPagerObserver(this.mObserver);
      this.mPopulatePending = false;
      boolean bool = this.mFirstLayout;
      this.mFirstLayout = true;
      this.mExpectedAdapterCount = this.mAdapter.getCount();
      if (this.mRestoredCurItem >= 0)
      {
        this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
        setCurrentItemInternal(this.mRestoredCurItem, false, true);
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
      }
      else if (!bool)
      {
        populate();
      }
      else
      {
        requestLayout();
      }
    }
    if ((this.mAdapterChangeListeners != null) && (!this.mAdapterChangeListeners.isEmpty()))
    {
      int j = this.mAdapterChangeListeners.size();
      while (i < j)
      {
        ((OnAdapterChangeListener)this.mAdapterChangeListeners.get(i)).onAdapterChanged(this, localPagerAdapter2, paramPagerAdapter);
        i++;
      }
    }
  }

  public void setCurrentItem(int paramInt)
  {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, true ^ this.mFirstLayout, false);
  }

  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }

  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }

  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    if ((this.mAdapter != null) && (this.mAdapter.getCount() > 0))
    {
      if ((!paramBoolean2) && (this.mCurItem == paramInt1) && (this.mItems.size() != 0))
      {
        setScrollingCacheEnabled(false);
        return;
      }
      int i = 1;
      if (paramInt1 < 0)
        paramInt1 = 0;
      else if (paramInt1 >= this.mAdapter.getCount())
        paramInt1 = this.mAdapter.getCount() - i;
      int j = this.mOffscreenPageLimit;
      if ((paramInt1 > j + this.mCurItem) || (paramInt1 < this.mCurItem - j))
        for (int k = 0; k < this.mItems.size(); k++)
          ((ItemInfo)this.mItems.get(k)).scrolling = i;
      if (this.mCurItem == paramInt1)
        i = 0;
      if (this.mFirstLayout)
      {
        this.mCurItem = paramInt1;
        if (i != 0)
          dispatchOnPageSelected(paramInt1);
        requestLayout();
      }
      else
      {
        populate(paramInt1);
        scrollToItem(paramInt1, paramBoolean1, paramInt2, i);
      }
      return;
    }
    setScrollingCacheEnabled(false);
  }

  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    OnPageChangeListener localOnPageChangeListener = this.mInternalPageChangeListener;
    this.mInternalPageChangeListener = paramOnPageChangeListener;
    return localOnPageChangeListener;
  }

  public void setOffscreenPageLimit(int paramInt)
  {
    if (paramInt < 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested offscreen page limit ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" too small; defaulting to ");
      localStringBuilder.append(1);
      Log.w("ViewPager", localStringBuilder.toString());
      paramInt = 1;
    }
    if (paramInt != this.mOffscreenPageLimit)
    {
      this.mOffscreenPageLimit = paramInt;
      populate();
    }
  }

  @Deprecated
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.mOnPageChangeListener = paramOnPageChangeListener;
  }

  public void setPageMargin(int paramInt)
  {
    int i = this.mPageMargin;
    this.mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }

  public void setPageMarginDrawable(@DrawableRes int paramInt)
  {
    setPageMarginDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }

  public void setPageMarginDrawable(@Nullable Drawable paramDrawable)
  {
    this.mMarginDrawable = paramDrawable;
    if (paramDrawable != null)
      refreshDrawableState();
    boolean bool;
    if (paramDrawable == null)
      bool = true;
    else
      bool = false;
    setWillNotDraw(bool);
    invalidate();
  }

  public void setPageTransformer(boolean paramBoolean, @Nullable PageTransformer paramPageTransformer)
  {
    setPageTransformer(paramBoolean, paramPageTransformer, 2);
  }

  public void setPageTransformer(boolean paramBoolean, @Nullable PageTransformer paramPageTransformer, int paramInt)
  {
    int i = 1;
    boolean bool1;
    if (paramPageTransformer != null)
      bool1 = true;
    else
      bool1 = false;
    boolean bool2;
    if (this.mPageTransformer != null)
      bool2 = true;
    else
      bool2 = false;
    int j;
    if (bool1 != bool2)
      j = 1;
    else
      j = 0;
    this.mPageTransformer = paramPageTransformer;
    setChildrenDrawingOrderEnabled(bool1);
    if (bool1)
    {
      if (paramBoolean)
        i = 2;
      this.mDrawingOrder = i;
      this.mPageTransformerLayerType = paramInt;
    }
    else
    {
      this.mDrawingOrder = 0;
    }
    if (j != 0)
      populate();
  }

  void setScrollState(int paramInt)
  {
    if (this.mScrollState == paramInt)
      return;
    this.mScrollState = paramInt;
    if (this.mPageTransformer != null)
    {
      boolean bool;
      if (paramInt != 0)
        bool = true;
      else
        bool = false;
      enableLayers(bool);
    }
    dispatchOnScrollStateChanged(paramInt);
  }

  void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }

  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0)
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i;
    if ((this.mScroller != null) && (!this.mScroller.isFinished()))
      i = 1;
    else
      i = 0;
    int j;
    if (i != 0)
    {
      if (this.mIsScrollStarted)
        j = this.mScroller.getCurrX();
      else
        j = this.mScroller.getStartX();
      this.mScroller.abortAnimation();
      setScrollingCacheEnabled(false);
    }
    else
    {
      j = getScrollX();
    }
    int k = j;
    int m = getScrollY();
    int n = paramInt1 - k;
    int i1 = paramInt2 - m;
    if ((n == 0) && (i1 == 0))
    {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    }
    setScrollingCacheEnabled(true);
    setScrollState(2);
    int i2 = getClientWidth();
    int i3 = i2 / 2;
    float f1 = 1.0F * Math.abs(n);
    float f2 = i2;
    float f3 = Math.min(1.0F, f1 / f2);
    float f4 = i3;
    float f5 = f4 + f4 * distanceInfluenceForSnapDuration(f3);
    int i4 = Math.abs(paramInt3);
    int i5;
    if (i4 > 0)
    {
      i5 = 4 * Math.round(1000.0F * Math.abs(f5 / i4));
    }
    else
    {
      float f6 = f2 * this.mAdapter.getPageWidth(this.mCurItem);
      i5 = (int)(100.0F * (1.0F + Math.abs(n) / (f6 + this.mPageMargin)));
    }
    int i6 = Math.min(i5, 600);
    this.mIsScrollStarted = false;
    this.mScroller.startScroll(k, m, n, i1, i6);
    ViewCompat.postInvalidateOnAnimation(this);
  }

  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    boolean bool;
    if ((!super.verifyDrawable(paramDrawable)) && (paramDrawable != this.mMarginDrawable))
      bool = false;
    else
      bool = true;
    return bool;
  }

  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  @Target({java.lang.annotation.ElementType.TYPE})
  public static @interface DecorView
  {
  }

  static class ItemInfo
  {
    Object object;
    float offset;
    int position;
    boolean scrolling;
    float widthFactor;
  }

  public static class LayoutParams extends ViewGroup.LayoutParams
  {
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor = 0.0F;

    public LayoutParams()
    {
      super(-1);
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, ViewPager.LAYOUT_ATTRS);
      this.gravity = localTypedArray.getInteger(0, 48);
      localTypedArray.recycle();
    }
  }

  class MyAccessibilityDelegate extends AccessibilityDelegateCompat
  {
    MyAccessibilityDelegate()
    {
    }

    private boolean canScroll()
    {
      PagerAdapter localPagerAdapter = ViewPager.this.mAdapter;
      int i = 1;
      if ((localPagerAdapter == null) || (ViewPager.this.mAdapter.getCount() <= i))
        i = 0;
      return i;
    }

    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(ViewPager.class.getName());
      paramAccessibilityEvent.setScrollable(canScroll());
      if ((paramAccessibilityEvent.getEventType() == 4096) && (ViewPager.this.mAdapter != null))
      {
        paramAccessibilityEvent.setItemCount(ViewPager.this.mAdapter.getCount());
        paramAccessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
        paramAccessibilityEvent.setToIndex(ViewPager.this.mCurItem);
      }
    }

    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      paramAccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (ViewPager.this.canScrollHorizontally(1))
        paramAccessibilityNodeInfoCompat.addAction(4096);
      if (ViewPager.this.canScrollHorizontally(-1))
        paramAccessibilityNodeInfoCompat.addAction(8192);
    }

    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle))
        return true;
      if (paramInt != 4096)
      {
        if (paramInt != 8192)
          return false;
        if (ViewPager.this.canScrollHorizontally(-1))
        {
          ViewPager.this.setCurrentItem(ViewPager.this.mCurItem - 1);
          return true;
        }
        return false;
      }
      if (ViewPager.this.canScrollHorizontally(1))
      {
        ViewPager.this.setCurrentItem(1 + ViewPager.this.mCurItem);
        return true;
      }
      return false;
    }
  }

  public static abstract interface OnAdapterChangeListener
  {
    public abstract void onAdapterChanged(@NonNull ViewPager paramViewPager, @Nullable PagerAdapter paramPagerAdapter1, @Nullable PagerAdapter paramPagerAdapter2);
  }

  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageScrollStateChanged(int paramInt);

    public abstract void onPageScrolled(int paramInt1, float paramFloat, @Px int paramInt2);

    public abstract void onPageSelected(int paramInt);
  }

  public static abstract interface PageTransformer
  {
    public abstract void transformPage(@NonNull View paramView, float paramFloat);
  }

  private class PagerObserver extends DataSetObserver
  {
    PagerObserver()
    {
    }

    public void onChanged()
    {
      ViewPager.this.dataSetChanged();
    }

    public void onInvalidated()
    {
      ViewPager.this.dataSetChanged();
    }
  }

  public static class SavedState extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public ViewPager.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ViewPager.SavedState(paramAnonymousParcel, null);
      }

      public ViewPager.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new ViewPager.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }

      public ViewPager.SavedState[] newArray(int paramAnonymousInt)
      {
        return new ViewPager.SavedState[paramAnonymousInt];
      }
    };
    Parcelable adapterState;
    ClassLoader loader;
    int position;

    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      if (paramClassLoader == null)
        paramClassLoader = getClass().getClassLoader();
      this.position = paramParcel.readInt();
      this.adapterState = paramParcel.readParcelable(paramClassLoader);
      this.loader = paramClassLoader;
    }

    public SavedState(@NonNull Parcelable paramParcelable)
    {
      super();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("FragmentPager.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" position=");
      localStringBuilder.append(this.position);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.position);
      paramParcel.writeParcelable(this.adapterState, paramInt);
    }
  }

  public static class SimpleOnPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
    }
  }

  static class ViewPositionComparator
    implements Comparator<View>
  {
    public int compare(View paramView1, View paramView2)
    {
      ViewPager.LayoutParams localLayoutParams1 = (ViewPager.LayoutParams)paramView1.getLayoutParams();
      ViewPager.LayoutParams localLayoutParams2 = (ViewPager.LayoutParams)paramView2.getLayoutParams();
      if (localLayoutParams1.isDecor != localLayoutParams2.isDecor)
      {
        int i;
        if (localLayoutParams1.isDecor)
          i = 1;
        else
          i = -1;
        return i;
      }
      return localLayoutParams1.position - localLayoutParams2.position;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.ViewPager
 * JD-Core Version:    0.6.1
 */