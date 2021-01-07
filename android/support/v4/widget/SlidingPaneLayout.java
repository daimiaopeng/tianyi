package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup
{
  private static final int DEFAULT_FADE_COLOR = -858993460;
  private static final int DEFAULT_OVERHANG_SIZE = 32;
  private static final int MIN_FLING_VELOCITY = 400;
  private static final String TAG = "SlidingPaneLayout";
  private boolean mCanSlide;
  private int mCoveredFadeColor;
  private boolean mDisplayListReflectionLoaded;
  final ViewDragHelper mDragHelper;
  private boolean mFirstLayout = true;
  private Method mGetDisplayList;
  private float mInitialMotionX;
  private float mInitialMotionY;
  boolean mIsUnableToDrag;
  private final int mOverhangSize;
  private PanelSlideListener mPanelSlideListener;
  private int mParallaxBy;
  private float mParallaxOffset;
  final ArrayList<DisableLayerRunnable> mPostedRunnables = new ArrayList();
  boolean mPreservedOpenState;
  private Field mRecreateDisplayList;
  private Drawable mShadowDrawableLeft;
  private Drawable mShadowDrawableRight;
  float mSlideOffset;
  int mSlideRange;
  View mSlideableView;
  private int mSliderFadeColor = -858993460;
  private final Rect mTmpRect = new Rect();

  public SlidingPaneLayout(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }

  public SlidingPaneLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SlidingPaneLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    float f = paramContext.getResources().getDisplayMetrics().density;
    this.mOverhangSize = (int)(0.5F + 32.0F * f);
    setWillNotDraw(false);
    ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
    ViewCompat.setImportantForAccessibility(this, 1);
    this.mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback());
    this.mDragHelper.setMinVelocity(f * 400.0F);
  }

  private boolean closePane(View paramView, int paramInt)
  {
    if ((!this.mFirstLayout) && (!smoothSlideTo(0.0F, paramInt)))
      return false;
    this.mPreservedOpenState = false;
    return true;
  }

  private void dimChildView(View paramView, float paramFloat, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((paramFloat > 0.0F) && (paramInt != 0))
    {
      int i = (int)(paramFloat * (0xFF000000 & paramInt) >>> 24) << 24 | paramInt & 0xFFFFFF;
      if (localLayoutParams.dimPaint == null)
        localLayoutParams.dimPaint = new Paint();
      localLayoutParams.dimPaint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_OVER));
      if (paramView.getLayerType() != 2)
        paramView.setLayerType(2, localLayoutParams.dimPaint);
      invalidateChildRegion(paramView);
    }
    else if (paramView.getLayerType() != 0)
    {
      if (localLayoutParams.dimPaint != null)
        localLayoutParams.dimPaint.setColorFilter(null);
      DisableLayerRunnable localDisableLayerRunnable = new DisableLayerRunnable(paramView);
      this.mPostedRunnables.add(localDisableLayerRunnable);
      ViewCompat.postOnAnimation(this, localDisableLayerRunnable);
    }
  }

  private boolean openPane(View paramView, int paramInt)
  {
    if ((!this.mFirstLayout) && (!smoothSlideTo(1.0F, paramInt)))
      return false;
    this.mPreservedOpenState = true;
    return true;
  }

  private void parallaxOtherViews(float paramFloat)
  {
    boolean bool1 = isLayoutRtlSupport();
    LayoutParams localLayoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    boolean bool2 = localLayoutParams.dimWhenOffset;
    int i = 0;
    if (bool2)
    {
      int i1;
      if (bool1)
        i1 = localLayoutParams.rightMargin;
      else
        i1 = localLayoutParams.leftMargin;
      if (i1 <= 0)
      {
        j = 1;
        break label63;
      }
    }
    int j = 0;
    label63: int k = getChildCount();
    while (i < k)
    {
      View localView = getChildAt(i);
      if (localView != this.mSlideableView)
      {
        int m = (int)((1.0F - this.mParallaxOffset) * this.mParallaxBy);
        this.mParallaxOffset = paramFloat;
        int n = m - (int)((1.0F - paramFloat) * this.mParallaxBy);
        if (bool1)
          n = -n;
        localView.offsetLeftAndRight(n);
        if (j != 0)
        {
          float f;
          if (bool1)
            f = this.mParallaxOffset - 1.0F;
          else
            f = 1.0F - this.mParallaxOffset;
          dimChildView(localView, f, this.mCoveredFadeColor);
        }
      }
      i++;
    }
  }

  private static boolean viewIsOpaque(View paramView)
  {
    boolean bool1 = paramView.isOpaque();
    boolean bool2 = true;
    if (bool1)
      return bool2;
    if (Build.VERSION.SDK_INT >= 18)
      return false;
    Drawable localDrawable = paramView.getBackground();
    if (localDrawable != null)
    {
      if (localDrawable.getOpacity() != -1)
        bool2 = false;
      return bool2;
    }
    return false;
  }

  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool1 = paramView instanceof ViewGroup;
    int i = 1;
    if (bool1)
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int k = paramView.getScrollX();
      int m = paramView.getScrollY();
      for (int n = localViewGroup.getChildCount() - i; n >= 0; n--)
      {
        View localView = localViewGroup.getChildAt(n);
        int i1 = paramInt2 + k;
        if ((i1 >= localView.getLeft()) && (i1 < localView.getRight()))
        {
          int i2 = paramInt3 + m;
          if ((i2 >= localView.getTop()) && (i2 < localView.getBottom()) && (canScroll(localView, true, paramInt1, i1 - localView.getLeft(), i2 - localView.getTop())))
            return i;
        }
      }
    }
    boolean bool2;
    if (paramBoolean)
    {
      int j;
      if (isLayoutRtlSupport())
        j = paramInt1;
      else
        j = -paramInt1;
      if (paramView.canScrollHorizontally(j));
    }
    else
    {
      bool2 = false;
    }
    return bool2;
  }

  @Deprecated
  public boolean canSlide()
  {
    return this.mCanSlide;
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

  public boolean closePane()
  {
    return closePane(this.mSlideableView, 0);
  }

  public void computeScroll()
  {
    if (this.mDragHelper.continueSettling(true))
    {
      if (!this.mCanSlide)
      {
        this.mDragHelper.abort();
        return;
      }
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }

  void dispatchOnPanelClosed(View paramView)
  {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelClosed(paramView);
    sendAccessibilityEvent(32);
  }

  void dispatchOnPanelOpened(View paramView)
  {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelOpened(paramView);
    sendAccessibilityEvent(32);
  }

  void dispatchOnPanelSlide(View paramView)
  {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelSlide(paramView, this.mSlideOffset);
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    Drawable localDrawable;
    if (isLayoutRtlSupport())
      localDrawable = this.mShadowDrawableRight;
    else
      localDrawable = this.mShadowDrawableLeft;
    View localView;
    if (getChildCount() > 1)
      localView = getChildAt(1);
    else
      localView = null;
    if ((localView != null) && (localDrawable != null))
    {
      int i = localView.getTop();
      int j = localView.getBottom();
      int k = localDrawable.getIntrinsicWidth();
      int i2;
      int i1;
      if (isLayoutRtlSupport())
      {
        i2 = localView.getRight();
        i1 = k + i2;
      }
      else
      {
        int m = localView.getLeft();
        int n = m - k;
        i1 = m;
        i2 = n;
      }
      localDrawable.setBounds(i2, i, i1, j);
      localDrawable.draw(paramCanvas);
      return;
    }
  }

  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = paramCanvas.save();
    if ((this.mCanSlide) && (!localLayoutParams.slideable) && (this.mSlideableView != null))
    {
      paramCanvas.getClipBounds(this.mTmpRect);
      if (isLayoutRtlSupport())
        this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
      else
        this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
      paramCanvas.clipRect(this.mTmpRect);
    }
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(i);
    return bool;
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
    LayoutParams localLayoutParams;
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
      localLayoutParams = new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    else
      localLayoutParams = new LayoutParams(paramLayoutParams);
    return localLayoutParams;
  }

  @ColorInt
  public int getCoveredFadeColor()
  {
    return this.mCoveredFadeColor;
  }

  @Px
  public int getParallaxDistance()
  {
    return this.mParallaxBy;
  }

  @ColorInt
  public int getSliderFadeColor()
  {
    return this.mSliderFadeColor;
  }

  void invalidateChildRegion(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17)
    {
      ViewCompat.setLayerPaint(paramView, ((LayoutParams)paramView.getLayoutParams()).dimPaint);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      if (!this.mDisplayListReflectionLoaded)
      {
        try
        {
          this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[])null);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", localNoSuchMethodException);
        }
        try
        {
          this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
          this.mRecreateDisplayList.setAccessible(true);
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", localNoSuchFieldException);
        }
        this.mDisplayListReflectionLoaded = true;
      }
      if ((this.mGetDisplayList != null) && (this.mRecreateDisplayList != null))
      {
        try
        {
          this.mRecreateDisplayList.setBoolean(paramView, true);
          this.mGetDisplayList.invoke(paramView, (Object[])null);
        }
        catch (Exception localException)
        {
          Log.e("SlidingPaneLayout", "Error refreshing display list state", localException);
        }
      }
      else
      {
        paramView.invalidate();
        return;
      }
    }
    ViewCompat.postInvalidateOnAnimation(this, paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
  }

  boolean isDimmed(View paramView)
  {
    if (paramView == null)
      return false;
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    boolean bool1 = this.mCanSlide;
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = localLayoutParams.dimWhenOffset;
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = this.mSlideOffset < 0.0F;
        bool2 = false;
        if (bool4)
          bool2 = true;
      }
    }
    return bool2;
  }

  boolean isLayoutRtlSupport()
  {
    int i = ViewCompat.getLayoutDirection(this);
    int j = 1;
    if (i != j)
      j = 0;
    return j;
  }

  public boolean isOpen()
  {
    boolean bool;
    if ((this.mCanSlide) && (this.mSlideOffset != 1.0F))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean isSlideable()
  {
    return this.mCanSlide;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mFirstLayout = true;
    int i = this.mPostedRunnables.size();
    for (int j = 0; j < i; j++)
      ((DisableLayerRunnable)this.mPostedRunnables.get(j)).run();
    this.mPostedRunnables.clear();
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    boolean bool = this.mCanSlide;
    int j = 1;
    if ((!bool) && (i == 0) && (getChildCount() > j))
    {
      View localView = getChildAt(j);
      if (localView != null)
        this.mPreservedOpenState = (j ^ this.mDragHelper.isViewUnder(localView, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()));
    }
    if ((this.mCanSlide) && ((!this.mIsUnableToDrag) || (i == 0)))
    {
      if ((i != 3) && (i != j))
      {
        if (i != 0)
        {
          if (i == 2)
          {
            float f3 = paramMotionEvent.getX();
            float f4 = paramMotionEvent.getY();
            float f5 = Math.abs(f3 - this.mInitialMotionX);
            float f6 = Math.abs(f4 - this.mInitialMotionY);
            if ((f5 > this.mDragHelper.getTouchSlop()) && (f6 > f5))
            {
              this.mDragHelper.cancel();
              this.mIsUnableToDrag = j;
              return false;
            }
          }
        }
        else
        {
          this.mIsUnableToDrag = false;
          float f1 = paramMotionEvent.getX();
          float f2 = paramMotionEvent.getY();
          this.mInitialMotionX = f1;
          this.mInitialMotionY = f2;
          if ((this.mDragHelper.isViewUnder(this.mSlideableView, (int)f1, (int)f2)) && (isDimmed(this.mSlideableView)))
          {
            k = 1;
            break label258;
          }
        }
        int k = 0;
        label258: if ((!this.mDragHelper.shouldInterceptTouchEvent(paramMotionEvent)) && (k == 0))
          j = 0;
        return j;
      }
      this.mDragHelper.cancel();
      return false;
    }
    this.mDragHelper.cancel();
    return super.onInterceptTouchEvent(paramMotionEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool1 = isLayoutRtlSupport();
    if (bool1)
      this.mDragHelper.setEdgeTrackingEnabled(2);
    else
      this.mDragHelper.setEdgeTrackingEnabled(1);
    int i = paramInt3 - paramInt1;
    int j;
    if (bool1)
      j = getPaddingRight();
    else
      j = getPaddingLeft();
    int k;
    if (bool1)
      k = getPaddingLeft();
    else
      k = getPaddingRight();
    int m = getPaddingTop();
    int n = getChildCount();
    if (this.mFirstLayout)
    {
      float f;
      if ((this.mCanSlide) && (this.mPreservedOpenState))
        f = 1.0F;
      else
        f = 0.0F;
      this.mSlideOffset = f;
    }
    int i1 = j;
    int i2 = i1;
    for (int i3 = 0; i3 < n; i3++)
    {
      View localView = getChildAt(i3);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        int i5 = localView.getMeasuredWidth();
        int i6;
        if (localLayoutParams.slideable)
        {
          int i10 = localLayoutParams.leftMargin + localLayoutParams.rightMargin;
          int i11 = i - k;
          int i12 = Math.min(i1, i11 - this.mOverhangSize) - i2 - i10;
          this.mSlideRange = i12;
          int i13;
          if (bool1)
            i13 = localLayoutParams.rightMargin;
          else
            i13 = localLayoutParams.leftMargin;
          boolean bool2;
          if (i12 + (i2 + i13) + i5 / 2 > i11)
            bool2 = true;
          else
            bool2 = false;
          localLayoutParams.dimWhenOffset = bool2;
          int i14 = (int)(i12 * this.mSlideOffset);
          i6 = i2 + (i13 + i14);
          this.mSlideOffset = (i14 / this.mSlideRange);
        }
        else
        {
          if ((this.mCanSlide) && (this.mParallaxBy != 0))
          {
            i7 = (int)((1.0F - this.mSlideOffset) * this.mParallaxBy);
            i6 = i1;
            break label370;
          }
          i6 = i1;
        }
        int i7 = 0;
        label370: int i9;
        int i8;
        if (bool1)
        {
          i9 = i7 + (i - i6);
          i8 = i9 - i5;
        }
        else
        {
          i8 = i6 - i7;
          i9 = i8 + i5;
        }
        localView.layout(i8, m, i9, m + localView.getMeasuredHeight());
        i1 += localView.getWidth();
        i2 = i6;
      }
    }
    if (this.mFirstLayout)
    {
      if (this.mCanSlide)
      {
        if (this.mParallaxBy != 0)
          parallaxOtherViews(this.mSlideOffset);
        if (((LayoutParams)this.mSlideableView.getLayoutParams()).dimWhenOffset)
          dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
      }
      else
      {
        for (int i4 = 0; i4 < n; i4++)
          dimChildView(getChildAt(i4), 0.0F, this.mSliderFadeColor);
      }
      updateObscuredViewsVisibility(this.mSlideableView);
    }
    this.mFirstLayout = false;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    int m = View.MeasureSpec.getSize(paramInt2);
    if (i != 1073741824)
    {
      if (isInEditMode())
      {
        if ((i != -2147483648) && (i == 0))
          j = 300;
      }
      else
        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
    }
    else if (k == 0)
      if (isInEditMode())
      {
        if (k == 0)
        {
          k = -2147483648;
          m = 300;
        }
      }
      else
        throw new IllegalStateException("Height must not be UNSPECIFIED");
    int i1;
    int n;
    if (k != -2147483648)
    {
      if (k != 1073741824)
      {
        i1 = 0;
        n = 0;
      }
      else
      {
        i1 = m - getPaddingTop() - getPaddingBottom();
        n = i1;
      }
    }
    else
    {
      n = m - getPaddingTop() - getPaddingBottom();
      i1 = 0;
    }
    int i2 = j - getPaddingLeft() - getPaddingRight();
    int i3 = getChildCount();
    if (i3 > 2)
      Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
    this.mSlideableView = null;
    int i4 = i1;
    int i5 = i2;
    int i6 = 0;
    boolean bool1 = false;
    float f = 0.0F;
    int i7;
    while (true)
    {
      i7 = 8;
      if (i6 >= i3)
        break;
      View localView2 = getChildAt(i6);
      LayoutParams localLayoutParams2 = (LayoutParams)localView2.getLayoutParams();
      if (localView2.getVisibility() == i7)
      {
        localLayoutParams2.dimWhenOffset = false;
      }
      else if (localLayoutParams2.weight > 0.0F)
      {
        f += localLayoutParams2.weight;
        if (localLayoutParams2.width == 0);
      }
      else
      {
        int i20 = localLayoutParams2.leftMargin + localLayoutParams2.rightMargin;
        int i21;
        if (localLayoutParams2.width == -2)
          i21 = View.MeasureSpec.makeMeasureSpec(i2 - i20, -2147483648);
        else if (localLayoutParams2.width == -1)
          i21 = View.MeasureSpec.makeMeasureSpec(i2 - i20, 1073741824);
        else
          i21 = View.MeasureSpec.makeMeasureSpec(localLayoutParams2.width, 1073741824);
        int i22;
        if (localLayoutParams2.height == -2)
          i22 = View.MeasureSpec.makeMeasureSpec(n, -2147483648);
        else if (localLayoutParams2.height == -1)
          i22 = View.MeasureSpec.makeMeasureSpec(n, 1073741824);
        else
          i22 = View.MeasureSpec.makeMeasureSpec(localLayoutParams2.height, 1073741824);
        localView2.measure(i21, i22);
        int i23 = localView2.getMeasuredWidth();
        int i24 = localView2.getMeasuredHeight();
        if ((k == -2147483648) && (i24 > i4))
          i4 = Math.min(i24, n);
        i5 -= i23;
        boolean bool2;
        if (i5 < 0)
          bool2 = true;
        else
          bool2 = false;
        localLayoutParams2.slideable = bool2;
        boolean bool3 = bool2 | bool1;
        if (localLayoutParams2.slideable)
          this.mSlideableView = localView2;
        bool1 = bool3;
      }
      i6++;
    }
    if ((bool1) || (f > 0.0F))
    {
      int i8 = i2 - this.mOverhangSize;
      int i9 = 0;
      while (i9 < i3)
      {
        View localView1 = getChildAt(i9);
        int i14;
        if (localView1.getVisibility() == i7)
          i14 = i8;
        while (true)
        {
          break label1018;
          LayoutParams localLayoutParams1 = (LayoutParams)localView1.getLayoutParams();
          if (localView1.getVisibility() == i7)
            break;
          int i10;
          if ((localLayoutParams1.width == 0) && (localLayoutParams1.weight > 0.0F))
            i10 = 1;
          else
            i10 = 0;
          int i11;
          if (i10 != 0)
            i11 = 0;
          else
            i11 = localView1.getMeasuredWidth();
          if ((bool1) && (localView1 != this.mSlideableView))
          {
            if ((localLayoutParams1.width >= 0) || ((i11 <= i8) && (localLayoutParams1.weight <= 0.0F)))
              break;
            int i19;
            int i18;
            if (i10 != 0)
            {
              if (localLayoutParams1.height == -2)
              {
                i19 = View.MeasureSpec.makeMeasureSpec(n, -2147483648);
                i18 = 1073741824;
              }
              else if (localLayoutParams1.height == -1)
              {
                i18 = 1073741824;
                i19 = View.MeasureSpec.makeMeasureSpec(n, i18);
              }
              else
              {
                i18 = 1073741824;
                i19 = View.MeasureSpec.makeMeasureSpec(localLayoutParams1.height, i18);
              }
            }
            else
            {
              i18 = 1073741824;
              i19 = View.MeasureSpec.makeMeasureSpec(localView1.getMeasuredHeight(), i18);
            }
            localView1.measure(View.MeasureSpec.makeMeasureSpec(i8, i18), i19);
            break;
          }
          if (localLayoutParams1.weight <= 0.0F)
            break;
          int i12;
          if (localLayoutParams1.width == 0)
          {
            if (localLayoutParams1.height == -2)
            {
              i13 = View.MeasureSpec.makeMeasureSpec(n, -2147483648);
              break label923;
            }
            if (localLayoutParams1.height == -1)
              i12 = View.MeasureSpec.makeMeasureSpec(n, 1073741824);
            else
              i12 = View.MeasureSpec.makeMeasureSpec(localLayoutParams1.height, 1073741824);
          }
          else
          {
            i12 = View.MeasureSpec.makeMeasureSpec(localView1.getMeasuredHeight(), 1073741824);
          }
          int i13 = i12;
          label923: if (bool1)
          {
            int i16 = i2 - (localLayoutParams1.leftMargin + localLayoutParams1.rightMargin);
            i14 = i8;
            int i17 = View.MeasureSpec.makeMeasureSpec(i16, 1073741824);
            if (i11 != i16)
              localView1.measure(i17, i13);
          }
          else
          {
            i14 = i8;
            int i15 = Math.max(0, i5);
            localView1.measure(View.MeasureSpec.makeMeasureSpec(i11 + (int)(localLayoutParams1.weight * i15 / f), 1073741824), i13);
          }
        }
        label1018: i9++;
        i8 = i14;
        i7 = 8;
      }
    }
    setMeasuredDimension(j, i4 + getPaddingTop() + getPaddingBottom());
    this.mCanSlide = bool1;
    if ((this.mDragHelper.getViewDragState() != 0) && (!bool1))
      this.mDragHelper.abort();
  }

  void onPanelDragged(int paramInt)
  {
    if (this.mSlideableView == null)
    {
      this.mSlideOffset = 0.0F;
      return;
    }
    boolean bool = isLayoutRtlSupport();
    LayoutParams localLayoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    int i = this.mSlideableView.getWidth();
    if (bool)
      paramInt = getWidth() - paramInt - i;
    int j;
    if (bool)
      j = getPaddingRight();
    else
      j = getPaddingLeft();
    int k;
    if (bool)
      k = localLayoutParams.rightMargin;
    else
      k = localLayoutParams.leftMargin;
    this.mSlideOffset = (paramInt - (j + k) / this.mSlideRange);
    if (this.mParallaxBy != 0)
      parallaxOtherViews(this.mSlideOffset);
    if (localLayoutParams.dimWhenOffset)
      dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
    dispatchOnPanelSlide(this.mSlideableView);
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
    if (localSavedState.isOpen)
      openPane();
    else
      closePane();
    this.mPreservedOpenState = localSavedState.isOpen;
  }

  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    boolean bool;
    if (isSlideable())
      bool = isOpen();
    else
      bool = this.mPreservedOpenState;
    localSavedState.isOpen = bool;
    return localSavedState;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
      this.mFirstLayout = true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.mCanSlide)
      return super.onTouchEvent(paramMotionEvent);
    this.mDragHelper.processTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getActionMasked())
    {
    default:
      break;
    case 1:
      if (isDimmed(this.mSlideableView))
      {
        float f3 = paramMotionEvent.getX();
        float f4 = paramMotionEvent.getY();
        float f5 = f3 - this.mInitialMotionX;
        float f6 = f4 - this.mInitialMotionY;
        int i = this.mDragHelper.getTouchSlop();
        if ((f5 * f5 + f6 * f6 < i * i) && (this.mDragHelper.isViewUnder(this.mSlideableView, (int)f3, (int)f4)))
          closePane(this.mSlideableView, 0);
      }
      break;
    case 0:
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.mInitialMotionX = f1;
      this.mInitialMotionY = f2;
    }
    return true;
  }

  public boolean openPane()
  {
    return openPane(this.mSlideableView, 0);
  }

  public void requestChildFocus(View paramView1, View paramView2)
  {
    super.requestChildFocus(paramView1, paramView2);
    if ((!isInTouchMode()) && (!this.mCanSlide))
    {
      boolean bool;
      if (paramView1 == this.mSlideableView)
        bool = true;
      else
        bool = false;
      this.mPreservedOpenState = bool;
    }
  }

  void setAllChildrenVisible()
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() == 4)
        localView.setVisibility(0);
    }
  }

  public void setCoveredFadeColor(@ColorInt int paramInt)
  {
    this.mCoveredFadeColor = paramInt;
  }

  public void setPanelSlideListener(@Nullable PanelSlideListener paramPanelSlideListener)
  {
    this.mPanelSlideListener = paramPanelSlideListener;
  }

  public void setParallaxDistance(@Px int paramInt)
  {
    this.mParallaxBy = paramInt;
    requestLayout();
  }

  @Deprecated
  public void setShadowDrawable(Drawable paramDrawable)
  {
    setShadowDrawableLeft(paramDrawable);
  }

  public void setShadowDrawableLeft(@Nullable Drawable paramDrawable)
  {
    this.mShadowDrawableLeft = paramDrawable;
  }

  public void setShadowDrawableRight(@Nullable Drawable paramDrawable)
  {
    this.mShadowDrawableRight = paramDrawable;
  }

  @Deprecated
  public void setShadowResource(@DrawableRes int paramInt)
  {
    setShadowDrawable(getResources().getDrawable(paramInt));
  }

  public void setShadowResourceLeft(int paramInt)
  {
    setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), paramInt));
  }

  public void setShadowResourceRight(int paramInt)
  {
    setShadowDrawableRight(ContextCompat.getDrawable(getContext(), paramInt));
  }

  public void setSliderFadeColor(@ColorInt int paramInt)
  {
    this.mSliderFadeColor = paramInt;
  }

  @Deprecated
  public void smoothSlideClosed()
  {
    closePane();
  }

  @Deprecated
  public void smoothSlideOpen()
  {
    openPane();
  }

  boolean smoothSlideTo(float paramFloat, int paramInt)
  {
    if (!this.mCanSlide)
      return false;
    boolean bool = isLayoutRtlSupport();
    LayoutParams localLayoutParams = (LayoutParams)this.mSlideableView.getLayoutParams();
    int i;
    if (bool)
    {
      int j = getPaddingRight() + localLayoutParams.rightMargin;
      int k = this.mSlideableView.getWidth();
      i = (int)(getWidth() - (j + paramFloat * this.mSlideRange + k));
    }
    else
    {
      i = (int)(getPaddingLeft() + localLayoutParams.leftMargin + paramFloat * this.mSlideRange);
    }
    if (this.mDragHelper.smoothSlideViewTo(this.mSlideableView, i, this.mSlideableView.getTop()))
    {
      setAllChildrenVisible();
      ViewCompat.postInvalidateOnAnimation(this);
      return true;
    }
    return false;
  }

  void updateObscuredViewsVisibility(View paramView)
  {
    View localView1 = paramView;
    boolean bool1 = isLayoutRtlSupport();
    int i;
    if (bool1)
      i = getWidth() - getPaddingRight();
    else
      i = getPaddingLeft();
    int j;
    if (bool1)
      j = getPaddingLeft();
    else
      j = getWidth() - getPaddingRight();
    int k = getPaddingTop();
    int m = getHeight() - getPaddingBottom();
    int n;
    int i1;
    int i2;
    int i3;
    if ((localView1 != null) && (viewIsOpaque(paramView)))
    {
      n = paramView.getLeft();
      i1 = paramView.getRight();
      i2 = paramView.getTop();
      i3 = paramView.getBottom();
    }
    else
    {
      n = 0;
      i1 = 0;
      i2 = 0;
      i3 = 0;
    }
    int i4 = getChildCount();
    int i5 = 0;
    while (i5 < i4)
    {
      View localView2 = getChildAt(i5);
      if (localView2 == localView1)
        break;
      boolean bool2;
      if (localView2.getVisibility() == 8)
      {
        bool2 = bool1;
      }
      else
      {
        int i6;
        if (bool1)
          i6 = j;
        else
          i6 = i;
        int i7 = Math.max(i6, localView2.getLeft());
        int i8 = Math.max(k, localView2.getTop());
        int i9;
        if (bool1)
        {
          bool2 = bool1;
          i9 = i;
        }
        else
        {
          bool2 = bool1;
          i9 = j;
        }
        int i10 = Math.min(i9, localView2.getRight());
        int i11 = Math.min(m, localView2.getBottom());
        int i12;
        if ((i7 >= n) && (i8 >= i2) && (i10 <= i1) && (i11 <= i3))
          i12 = 4;
        else
          i12 = 0;
        localView2.setVisibility(i12);
      }
      i5++;
      bool1 = bool2;
      localView1 = paramView;
    }
  }

  class AccessibilityDelegate extends AccessibilityDelegateCompat
  {
    private final Rect mTmpRect = new Rect();

    AccessibilityDelegate()
    {
    }

    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat1, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat2)
    {
      Rect localRect = this.mTmpRect;
      paramAccessibilityNodeInfoCompat2.getBoundsInParent(localRect);
      paramAccessibilityNodeInfoCompat1.setBoundsInParent(localRect);
      paramAccessibilityNodeInfoCompat2.getBoundsInScreen(localRect);
      paramAccessibilityNodeInfoCompat1.setBoundsInScreen(localRect);
      paramAccessibilityNodeInfoCompat1.setVisibleToUser(paramAccessibilityNodeInfoCompat2.isVisibleToUser());
      paramAccessibilityNodeInfoCompat1.setPackageName(paramAccessibilityNodeInfoCompat2.getPackageName());
      paramAccessibilityNodeInfoCompat1.setClassName(paramAccessibilityNodeInfoCompat2.getClassName());
      paramAccessibilityNodeInfoCompat1.setContentDescription(paramAccessibilityNodeInfoCompat2.getContentDescription());
      paramAccessibilityNodeInfoCompat1.setEnabled(paramAccessibilityNodeInfoCompat2.isEnabled());
      paramAccessibilityNodeInfoCompat1.setClickable(paramAccessibilityNodeInfoCompat2.isClickable());
      paramAccessibilityNodeInfoCompat1.setFocusable(paramAccessibilityNodeInfoCompat2.isFocusable());
      paramAccessibilityNodeInfoCompat1.setFocused(paramAccessibilityNodeInfoCompat2.isFocused());
      paramAccessibilityNodeInfoCompat1.setAccessibilityFocused(paramAccessibilityNodeInfoCompat2.isAccessibilityFocused());
      paramAccessibilityNodeInfoCompat1.setSelected(paramAccessibilityNodeInfoCompat2.isSelected());
      paramAccessibilityNodeInfoCompat1.setLongClickable(paramAccessibilityNodeInfoCompat2.isLongClickable());
      paramAccessibilityNodeInfoCompat1.addAction(paramAccessibilityNodeInfoCompat2.getActions());
      paramAccessibilityNodeInfoCompat1.setMovementGranularities(paramAccessibilityNodeInfoCompat2.getMovementGranularities());
    }

    public boolean filter(View paramView)
    {
      return SlidingPaneLayout.this.isDimmed(paramView);
    }

    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(paramAccessibilityNodeInfoCompat);
      super.onInitializeAccessibilityNodeInfo(paramView, localAccessibilityNodeInfoCompat);
      copyNodeInfoNoChildren(paramAccessibilityNodeInfoCompat, localAccessibilityNodeInfoCompat);
      localAccessibilityNodeInfoCompat.recycle();
      paramAccessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
      paramAccessibilityNodeInfoCompat.setSource(paramView);
      ViewParent localViewParent = ViewCompat.getParentForAccessibility(paramView);
      if ((localViewParent instanceof View))
        paramAccessibilityNodeInfoCompat.setParent((View)localViewParent);
      int i = SlidingPaneLayout.this.getChildCount();
      for (int j = 0; j < i; j++)
      {
        View localView = SlidingPaneLayout.this.getChildAt(j);
        if ((!filter(localView)) && (localView.getVisibility() == 0))
        {
          ViewCompat.setImportantForAccessibility(localView, 1);
          paramAccessibilityNodeInfoCompat.addChild(localView);
        }
      }
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      if (!filter(paramView))
        return super.onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
      return false;
    }
  }

  private class DisableLayerRunnable
    implements Runnable
  {
    final View mChildView;

    DisableLayerRunnable(View arg2)
    {
      Object localObject;
      this.mChildView = localObject;
    }

    public void run()
    {
      if (this.mChildView.getParent() == SlidingPaneLayout.this)
      {
        this.mChildView.setLayerType(0, null);
        SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
      }
      SlidingPaneLayout.this.mPostedRunnables.remove(this);
    }
  }

  private class DragHelperCallback extends ViewDragHelper.Callback
  {
    DragHelperCallback()
    {
    }

    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      SlidingPaneLayout.LayoutParams localLayoutParams = (SlidingPaneLayout.LayoutParams)SlidingPaneLayout.this.mSlideableView.getLayoutParams();
      int k;
      if (SlidingPaneLayout.this.isLayoutRtlSupport())
      {
        int m = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.getPaddingRight() + localLayoutParams.rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth());
        int n = m - SlidingPaneLayout.this.mSlideRange;
        k = Math.max(Math.min(paramInt1, m), n);
      }
      else
      {
        int i = SlidingPaneLayout.this.getPaddingLeft() + localLayoutParams.leftMargin;
        int j = i + SlidingPaneLayout.this.mSlideRange;
        k = Math.min(Math.max(paramInt1, i), j);
      }
      return k;
    }

    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      return paramView.getTop();
    }

    public int getViewHorizontalDragRange(View paramView)
    {
      return SlidingPaneLayout.this.mSlideRange;
    }

    public void onEdgeDragStarted(int paramInt1, int paramInt2)
    {
      SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, paramInt2);
    }

    public void onViewCaptured(View paramView, int paramInt)
    {
      SlidingPaneLayout.this.setAllChildrenVisible();
    }

    public void onViewDragStateChanged(int paramInt)
    {
      if (SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0)
        if (SlidingPaneLayout.this.mSlideOffset == 0.0F)
        {
          SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
          SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
          SlidingPaneLayout.this.mPreservedOpenState = false;
        }
        else
        {
          SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
          SlidingPaneLayout.this.mPreservedOpenState = true;
        }
    }

    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      SlidingPaneLayout.this.onPanelDragged(paramInt1);
      SlidingPaneLayout.this.invalidate();
    }

    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      SlidingPaneLayout.LayoutParams localLayoutParams = (SlidingPaneLayout.LayoutParams)paramView.getLayoutParams();
      int i;
      if (SlidingPaneLayout.this.isLayoutRtlSupport())
      {
        int j = SlidingPaneLayout.this.getPaddingRight() + localLayoutParams.rightMargin;
        if ((paramFloat1 < 0.0F) || ((paramFloat1 == 0.0F) && (SlidingPaneLayout.this.mSlideOffset > 0.5F)))
          j += SlidingPaneLayout.this.mSlideRange;
        int k = SlidingPaneLayout.this.mSlideableView.getWidth();
        i = SlidingPaneLayout.this.getWidth() - j - k;
      }
      else
      {
        i = SlidingPaneLayout.this.getPaddingLeft() + localLayoutParams.leftMargin;
        if ((paramFloat1 > 0.0F) || ((paramFloat1 == 0.0F) && (SlidingPaneLayout.this.mSlideOffset > 0.5F)))
          i += SlidingPaneLayout.this.mSlideRange;
      }
      SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(i, paramView.getTop());
      SlidingPaneLayout.this.invalidate();
    }

    public boolean tryCaptureView(View paramView, int paramInt)
    {
      if (SlidingPaneLayout.this.mIsUnableToDrag)
        return false;
      return ((SlidingPaneLayout.LayoutParams)paramView.getLayoutParams()).slideable;
    }
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams
  {
    private static final int[] ATTRS = { 16843137 };
    Paint dimPaint;
    boolean dimWhenOffset;
    boolean slideable;
    public float weight = 0.0F;

    public LayoutParams()
    {
      super(-1);
    }

    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    public LayoutParams(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
      this.weight = localTypedArray.getFloat(0, 0.0F);
      localTypedArray.recycle();
    }

    public LayoutParams(@NonNull LayoutParams paramLayoutParams)
    {
      super();
      this.weight = paramLayoutParams.weight;
    }

    public LayoutParams(@NonNull ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }

    public LayoutParams(@NonNull ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
  }

  public static abstract interface PanelSlideListener
  {
    public abstract void onPanelClosed(@NonNull View paramView);

    public abstract void onPanelOpened(@NonNull View paramView);

    public abstract void onPanelSlide(@NonNull View paramView, float paramFloat);
  }

  static class SavedState extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public SlidingPaneLayout.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new SlidingPaneLayout.SavedState(paramAnonymousParcel, null);
      }

      public SlidingPaneLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new SlidingPaneLayout.SavedState(paramAnonymousParcel, null);
      }

      public SlidingPaneLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new SlidingPaneLayout.SavedState[paramAnonymousInt];
      }
    };
    boolean isOpen;

    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      boolean bool;
      if (paramParcel.readInt() != 0)
        bool = true;
      else
        bool = false;
      this.isOpen = bool;
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.isOpen);
    }
  }

  public static class SimplePanelSlideListener
    implements SlidingPaneLayout.PanelSlideListener
  {
    public void onPanelClosed(View paramView)
    {
    }

    public void onPanelOpened(View paramView)
    {
    }

    public void onPanelSlide(View paramView, float paramFloat)
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout
 * JD-Core Version:    0.6.1
 */