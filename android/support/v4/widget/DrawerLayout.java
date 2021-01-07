package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup
{
  private static final boolean ALLOW_EDGE_LOCK = false;
  static final boolean CAN_HIDE_DESCENDANTS = false;
  private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
  private static final int DEFAULT_SCRIM_COLOR = -1728053248;
  private static final int DRAWER_ELEVATION = 10;
  static final int[] LAYOUT_ATTRS;
  public static final int LOCK_MODE_LOCKED_CLOSED = 1;
  public static final int LOCK_MODE_LOCKED_OPEN = 2;
  public static final int LOCK_MODE_UNDEFINED = 3;
  public static final int LOCK_MODE_UNLOCKED = 0;
  private static final int MIN_DRAWER_MARGIN = 64;
  private static final int MIN_FLING_VELOCITY = 400;
  private static final int PEEK_DELAY = 160;
  private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = false;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_IDLE = 0;
  public static final int STATE_SETTLING = 2;
  private static final String TAG = "DrawerLayout";
  private static final int[] THEME_ATTRS;
  private static final float TOUCH_SLOP_SENSITIVITY = 1.0F;
  private final ChildAccessibilityDelegate mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
  private Rect mChildHitRect;
  private Matrix mChildInvertedMatrix;
  private boolean mChildrenCanceledTouch;
  private boolean mDisallowInterceptRequested;
  private boolean mDrawStatusBarBackground;
  private float mDrawerElevation;
  private int mDrawerState;
  private boolean mFirstLayout = true;
  private boolean mInLayout;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private Object mLastInsets;
  private final ViewDragCallback mLeftCallback;
  private final ViewDragHelper mLeftDragger;

  @Nullable
  private DrawerListener mListener;
  private List<DrawerListener> mListeners;
  private int mLockModeEnd = 3;
  private int mLockModeLeft = 3;
  private int mLockModeRight = 3;
  private int mLockModeStart = 3;
  private int mMinDrawerMargin;
  private final ArrayList<View> mNonDrawerViews;
  private final ViewDragCallback mRightCallback;
  private final ViewDragHelper mRightDragger;
  private int mScrimColor = -1728053248;
  private float mScrimOpacity;
  private Paint mScrimPaint = new Paint();
  private Drawable mShadowEnd = null;
  private Drawable mShadowLeft = null;
  private Drawable mShadowLeftResolved;
  private Drawable mShadowRight = null;
  private Drawable mShadowRightResolved;
  private Drawable mShadowStart = null;
  private Drawable mStatusBarBackground;
  private CharSequence mTitleLeft;
  private CharSequence mTitleRight;

  static
  {
    boolean bool1 = true;
    int[] arrayOfInt1 = new int[bool1];
    arrayOfInt1[0] = 16843828;
    THEME_ATTRS = arrayOfInt1;
    int[] arrayOfInt2 = new int[bool1];
    arrayOfInt2[0] = 16842931;
    LAYOUT_ATTRS = arrayOfInt2;
    boolean bool2;
    if (Build.VERSION.SDK_INT >= 19)
      bool2 = true;
    else
      bool2 = false;
    CAN_HIDE_DESCENDANTS = bool2;
    if (Build.VERSION.SDK_INT < 21)
      bool1 = false;
  }

  public DrawerLayout(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }

  public DrawerLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public DrawerLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setDescendantFocusability(262144);
    float f1 = getResources().getDisplayMetrics().density;
    this.mMinDrawerMargin = (int)(0.5F + 64.0F * f1);
    float f2 = 400.0F * f1;
    this.mLeftCallback = new ViewDragCallback(3);
    this.mRightCallback = new ViewDragCallback(5);
    this.mLeftDragger = ViewDragHelper.create(this, 1.0F, this.mLeftCallback);
    this.mLeftDragger.setEdgeTrackingEnabled(1);
    this.mLeftDragger.setMinVelocity(f2);
    this.mLeftCallback.setDragger(this.mLeftDragger);
    this.mRightDragger = ViewDragHelper.create(this, 1.0F, this.mRightCallback);
    this.mRightDragger.setEdgeTrackingEnabled(2);
    this.mRightDragger.setMinVelocity(f2);
    this.mRightCallback.setDragger(this.mRightDragger);
    setFocusableInTouchMode(true);
    ViewCompat.setImportantForAccessibility(this, 1);
    ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
    setMotionEventSplittingEnabled(false);
    if (ViewCompat.getFitsSystemWindows(this))
    {
      TypedArray localTypedArray;
      if (Build.VERSION.SDK_INT >= 21)
      {
        setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener()
        {
          public WindowInsets onApplyWindowInsets(View paramAnonymousView, WindowInsets paramAnonymousWindowInsets)
          {
            DrawerLayout localDrawerLayout = (DrawerLayout)paramAnonymousView;
            boolean bool;
            if (paramAnonymousWindowInsets.getSystemWindowInsetTop() > 0)
              bool = true;
            else
              bool = false;
            localDrawerLayout.setChildInsets(paramAnonymousWindowInsets, bool);
            return paramAnonymousWindowInsets.consumeSystemWindowInsets();
          }
        });
        setSystemUiVisibility(1280);
        localTypedArray = paramContext.obtainStyledAttributes(THEME_ATTRS);
      }
      try
      {
        this.mStatusBarBackground = localTypedArray.getDrawable(0);
        localTypedArray.recycle();
      }
      finally
      {
        localTypedArray.recycle();
      }
    }
    this.mDrawerElevation = (f1 * 10.0F);
    this.mNonDrawerViews = new ArrayList();
  }

  private boolean dispatchTransformedGenericPointerEvent(MotionEvent paramMotionEvent, View paramView)
  {
    boolean bool;
    if (!paramView.getMatrix().isIdentity())
    {
      MotionEvent localMotionEvent = getTransformedMotionEvent(paramMotionEvent, paramView);
      bool = paramView.dispatchGenericMotionEvent(localMotionEvent);
      localMotionEvent.recycle();
    }
    else
    {
      float f1 = getScrollX() - paramView.getLeft();
      float f2 = getScrollY() - paramView.getTop();
      paramMotionEvent.offsetLocation(f1, f2);
      bool = paramView.dispatchGenericMotionEvent(paramMotionEvent);
      paramMotionEvent.offsetLocation(-f1, -f2);
    }
    return bool;
  }

  private MotionEvent getTransformedMotionEvent(MotionEvent paramMotionEvent, View paramView)
  {
    float f1 = getScrollX() - paramView.getLeft();
    float f2 = getScrollY() - paramView.getTop();
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    localMotionEvent.offsetLocation(f1, f2);
    Matrix localMatrix = paramView.getMatrix();
    if (!localMatrix.isIdentity())
    {
      if (this.mChildInvertedMatrix == null)
        this.mChildInvertedMatrix = new Matrix();
      localMatrix.invert(this.mChildInvertedMatrix);
      localMotionEvent.transform(this.mChildInvertedMatrix);
    }
    return localMotionEvent;
  }

  static String gravityToString(int paramInt)
  {
    if ((paramInt & 0x3) == 3)
      return "LEFT";
    if ((paramInt & 0x5) == 5)
      return "RIGHT";
    return Integer.toHexString(paramInt);
  }

  private static boolean hasOpaqueBackground(View paramView)
  {
    Drawable localDrawable = paramView.getBackground();
    if (localDrawable != null)
    {
      int i = localDrawable.getOpacity();
      boolean bool = false;
      if (i == -1)
        bool = true;
      return bool;
    }
    return false;
  }

  private boolean hasPeekingDrawer()
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
      if (((LayoutParams)getChildAt(j).getLayoutParams()).isPeeking)
        return true;
    return false;
  }

  private boolean hasVisibleDrawer()
  {
    boolean bool;
    if (findVisibleDrawer() != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  static boolean includeChildForAccessibility(View paramView)
  {
    boolean bool;
    if ((ViewCompat.getImportantForAccessibility(paramView) != 4) && (ViewCompat.getImportantForAccessibility(paramView) != 2))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean isInBoundsOfChild(float paramFloat1, float paramFloat2, View paramView)
  {
    if (this.mChildHitRect == null)
      this.mChildHitRect = new Rect();
    paramView.getHitRect(this.mChildHitRect);
    return this.mChildHitRect.contains((int)paramFloat1, (int)paramFloat2);
  }

  private boolean mirror(Drawable paramDrawable, int paramInt)
  {
    if ((paramDrawable != null) && (DrawableCompat.isAutoMirrored(paramDrawable)))
    {
      DrawableCompat.setLayoutDirection(paramDrawable, paramInt);
      return true;
    }
    return false;
  }

  private Drawable resolveLeftShadow()
  {
    int i = ViewCompat.getLayoutDirection(this);
    if (i == 0)
    {
      if (this.mShadowStart != null)
      {
        mirror(this.mShadowStart, i);
        return this.mShadowStart;
      }
    }
    else if (this.mShadowEnd != null)
    {
      mirror(this.mShadowEnd, i);
      return this.mShadowEnd;
    }
    return this.mShadowLeft;
  }

  private Drawable resolveRightShadow()
  {
    int i = ViewCompat.getLayoutDirection(this);
    if (i == 0)
    {
      if (this.mShadowEnd != null)
      {
        mirror(this.mShadowEnd, i);
        return this.mShadowEnd;
      }
    }
    else if (this.mShadowStart != null)
    {
      mirror(this.mShadowStart, i);
      return this.mShadowStart;
    }
    return this.mShadowRight;
  }

  private void resolveShadowDrawables()
  {
    if (SET_DRAWER_SHADOW_FROM_ELEVATION)
      return;
    this.mShadowLeftResolved = resolveLeftShadow();
    this.mShadowRightResolved = resolveRightShadow();
  }

  private void updateChildrenImportantForAccessibility(View paramView, boolean paramBoolean)
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      if (((!paramBoolean) && (!isDrawerView(localView))) || ((paramBoolean) && (localView == paramView)))
        ViewCompat.setImportantForAccessibility(localView, 1);
      else
        ViewCompat.setImportantForAccessibility(localView, 4);
    }
  }

  public void addDrawerListener(@NonNull DrawerListener paramDrawerListener)
  {
    if (paramDrawerListener == null)
      return;
    if (this.mListeners == null)
      this.mListeners = new ArrayList();
    this.mListeners.add(paramDrawerListener);
  }

  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    if (getDescendantFocusability() == 393216)
      return;
    int i = getChildCount();
    int j = 0;
    int k = 0;
    int m = 0;
    while (k < i)
    {
      View localView2 = getChildAt(k);
      if (isDrawerView(localView2))
      {
        if (isDrawerOpen(localView2))
        {
          localView2.addFocusables(paramArrayList, paramInt1, paramInt2);
          m = 1;
        }
      }
      else
        this.mNonDrawerViews.add(localView2);
      k++;
    }
    if (m == 0)
    {
      int n = this.mNonDrawerViews.size();
      while (j < n)
      {
        View localView1 = (View)this.mNonDrawerViews.get(j);
        if (localView1.getVisibility() == 0)
          localView1.addFocusables(paramArrayList, paramInt1, paramInt2);
        j++;
      }
    }
    this.mNonDrawerViews.clear();
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
    if ((findOpenDrawer() == null) && (!isDrawerView(paramView)))
      ViewCompat.setImportantForAccessibility(paramView, 1);
    else
      ViewCompat.setImportantForAccessibility(paramView, 4);
    if (!CAN_HIDE_DESCENDANTS)
      ViewCompat.setAccessibilityDelegate(paramView, this.mChildAccessibilityDelegate);
  }

  void cancelChildViewTouch()
  {
    if (!this.mChildrenCanceledTouch)
    {
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
      int i = getChildCount();
      for (int j = 0; j < i; j++)
        getChildAt(j).dispatchTouchEvent(localMotionEvent);
      localMotionEvent.recycle();
      this.mChildrenCanceledTouch = true;
    }
  }

  boolean checkDrawerViewAbsoluteGravity(View paramView, int paramInt)
  {
    boolean bool;
    if ((paramInt & getDrawerViewAbsoluteGravity(paramView)) == paramInt)
      bool = true;
    else
      bool = false;
    return bool;
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

  public void closeDrawer(int paramInt)
  {
    closeDrawer(paramInt, true);
  }

  public void closeDrawer(int paramInt, boolean paramBoolean)
  {
    View localView = findDrawerWithGravity(paramInt);
    if (localView == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("No drawer view found with gravity ");
      localStringBuilder.append(gravityToString(paramInt));
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    closeDrawer(localView, paramBoolean);
  }

  public void closeDrawer(@NonNull View paramView)
  {
    closeDrawer(paramView, true);
  }

  public void closeDrawer(@NonNull View paramView, boolean paramBoolean)
  {
    if (!isDrawerView(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a sliding drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (this.mFirstLayout)
    {
      localLayoutParams.onScreen = 0.0F;
      localLayoutParams.openState = 0;
    }
    else if (paramBoolean)
    {
      localLayoutParams.openState = (0x4 | localLayoutParams.openState);
      if (checkDrawerViewAbsoluteGravity(paramView, 3))
        this.mLeftDragger.smoothSlideViewTo(paramView, -paramView.getWidth(), paramView.getTop());
      else
        this.mRightDragger.smoothSlideViewTo(paramView, getWidth(), paramView.getTop());
    }
    else
    {
      moveDrawerToOffset(paramView, 0.0F);
      updateDrawerState(localLayoutParams.gravity, 0, paramView);
      paramView.setVisibility(4);
    }
    invalidate();
  }

  public void closeDrawers()
  {
    closeDrawers(false);
  }

  void closeDrawers(boolean paramBoolean)
  {
    int i = getChildCount();
    int j = 0;
    boolean bool = false;
    while (j < i)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if ((isDrawerView(localView)) && ((!paramBoolean) || (localLayoutParams.isPeeking)))
      {
        int k = localView.getWidth();
        if (checkDrawerViewAbsoluteGravity(localView, 3))
          bool |= this.mLeftDragger.smoothSlideViewTo(localView, -k, localView.getTop());
        else
          bool |= this.mRightDragger.smoothSlideViewTo(localView, getWidth(), localView.getTop());
        localLayoutParams.isPeeking = false;
      }
      j++;
    }
    this.mLeftCallback.removeCallbacks();
    this.mRightCallback.removeCallbacks();
    if (bool)
      invalidate();
  }

  public void computeScroll()
  {
    int i = getChildCount();
    float f = 0.0F;
    for (int j = 0; j < i; j++)
      f = Math.max(f, ((LayoutParams)getChildAt(j).getLayoutParams()).onScreen);
    this.mScrimOpacity = f;
    boolean bool1 = this.mLeftDragger.continueSettling(true);
    boolean bool2 = this.mRightDragger.continueSettling(true);
    if ((bool1) || (bool2))
      ViewCompat.postInvalidateOnAnimation(this);
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (((0x2 & paramMotionEvent.getSource()) != 0) && (paramMotionEvent.getAction() != 10) && (this.mScrimOpacity > 0.0F))
    {
      int i = getChildCount();
      if (i != 0)
      {
        float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        for (int j = i - 1; j >= 0; j--)
        {
          View localView = getChildAt(j);
          if ((isInBoundsOfChild(f1, f2, localView)) && (!isContentView(localView)) && (dispatchTransformedGenericPointerEvent(paramMotionEvent, localView)))
            return true;
        }
      }
      return false;
    }
    return super.dispatchGenericMotionEvent(paramMotionEvent);
  }

  void dispatchOnDrawerClosed(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((0x1 & localLayoutParams.openState) == 1)
    {
      localLayoutParams.openState = 0;
      if (this.mListeners != null)
        for (int i = this.mListeners.size() - 1; i >= 0; i--)
          ((DrawerListener)this.mListeners.get(i)).onDrawerClosed(paramView);
      updateChildrenImportantForAccessibility(paramView, false);
      if (hasWindowFocus())
      {
        View localView = getRootView();
        if (localView != null)
          localView.sendAccessibilityEvent(32);
      }
    }
  }

  void dispatchOnDrawerOpened(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((0x1 & localLayoutParams.openState) == 0)
    {
      localLayoutParams.openState = 1;
      if (this.mListeners != null)
        for (int i = this.mListeners.size() - 1; i >= 0; i--)
          ((DrawerListener)this.mListeners.get(i)).onDrawerOpened(paramView);
      updateChildrenImportantForAccessibility(paramView, true);
      if (hasWindowFocus())
        sendAccessibilityEvent(32);
    }
  }

  void dispatchOnDrawerSlide(View paramView, float paramFloat)
  {
    if (this.mListeners != null)
      for (int i = -1 + this.mListeners.size(); i >= 0; i--)
        ((DrawerListener)this.mListeners.get(i)).onDrawerSlide(paramView, paramFloat);
  }

  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    int i = getHeight();
    boolean bool1 = isContentView(paramView);
    int j = getWidth();
    int k = paramCanvas.save();
    int m;
    int n;
    if (bool1)
    {
      int i9 = getChildCount();
      m = j;
      int i10 = 0;
      n = 0;
      while (i10 < i9)
      {
        View localView = getChildAt(i10);
        if ((localView != paramView) && (localView.getVisibility() == 0) && (hasOpaqueBackground(localView)) && (isDrawerView(localView)) && (localView.getHeight() >= i))
          if (checkDrawerViewAbsoluteGravity(localView, 3))
          {
            int i12 = localView.getRight();
            if (i12 > n)
              n = i12;
          }
          else
          {
            int i11 = localView.getLeft();
            if (i11 < m)
              m = i11;
          }
        i10++;
      }
      paramCanvas.clipRect(n, 0, m, getHeight());
    }
    else
    {
      m = j;
      n = 0;
    }
    boolean bool2 = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(k);
    if ((this.mScrimOpacity > 0.0F) && (bool1))
    {
      int i8 = (int)((0xFF000000 & this.mScrimColor) >>> 24 * this.mScrimOpacity) << 24 | 0xFFFFFF & this.mScrimColor;
      this.mScrimPaint.setColor(i8);
      paramCanvas.drawRect(n, 0.0F, m, getHeight(), this.mScrimPaint);
    }
    else if ((this.mShadowLeftResolved != null) && (checkDrawerViewAbsoluteGravity(paramView, 3)))
    {
      int i5 = this.mShadowLeftResolved.getIntrinsicWidth();
      int i6 = paramView.getRight();
      int i7 = this.mLeftDragger.getEdgeSize();
      float f2 = Math.max(0.0F, Math.min(i6 / i7, 1.0F));
      this.mShadowLeftResolved.setBounds(i6, paramView.getTop(), i5 + i6, paramView.getBottom());
      this.mShadowLeftResolved.setAlpha((int)(f2 * 255.0F));
      this.mShadowLeftResolved.draw(paramCanvas);
    }
    else if ((this.mShadowRightResolved != null) && (checkDrawerViewAbsoluteGravity(paramView, 5)))
    {
      int i1 = this.mShadowRightResolved.getIntrinsicWidth();
      int i2 = paramView.getLeft();
      int i3 = getWidth() - i2;
      int i4 = this.mRightDragger.getEdgeSize();
      float f1 = Math.max(0.0F, Math.min(i3 / i4, 1.0F));
      this.mShadowRightResolved.setBounds(i2 - i1, paramView.getTop(), i2, paramView.getBottom());
      this.mShadowRightResolved.setAlpha((int)(f1 * 255.0F));
      this.mShadowRightResolved.draw(paramCanvas);
    }
    return bool2;
  }

  View findDrawerWithGravity(int paramInt)
  {
    int i = 0x7 & GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
    int j = getChildCount();
    for (int k = 0; k < j; k++)
    {
      View localView = getChildAt(k);
      if ((0x7 & getDrawerViewAbsoluteGravity(localView)) == i)
        return localView;
    }
    return null;
  }

  View findOpenDrawer()
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      if ((0x1 & ((LayoutParams)localView.getLayoutParams()).openState) == 1)
        return localView;
    }
    return null;
  }

  View findVisibleDrawer()
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      if ((isDrawerView(localView)) && (isDrawerVisible(localView)))
        return localView;
    }
    return null;
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-1, -1);
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    LayoutParams localLayoutParams;
    if ((paramLayoutParams instanceof LayoutParams))
      localLayoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
    else if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
      localLayoutParams = new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    else
      localLayoutParams = new LayoutParams(paramLayoutParams);
    return localLayoutParams;
  }

  public float getDrawerElevation()
  {
    if (SET_DRAWER_SHADOW_FROM_ELEVATION)
      return this.mDrawerElevation;
    return 0.0F;
  }

  public int getDrawerLockMode(int paramInt)
  {
    int i = ViewCompat.getLayoutDirection(this);
    if (paramInt != 3)
    {
      if (paramInt != 5)
      {
        if (paramInt != 8388611)
        {
          if (paramInt == 8388613)
          {
            if (this.mLockModeEnd != 3)
              return this.mLockModeEnd;
            int n;
            if (i == 0)
              n = this.mLockModeRight;
            else
              n = this.mLockModeLeft;
            if (n != 3)
              return n;
          }
        }
        else
        {
          if (this.mLockModeStart != 3)
            return this.mLockModeStart;
          int m;
          if (i == 0)
            m = this.mLockModeLeft;
          else
            m = this.mLockModeRight;
          if (m != 3)
            return m;
        }
      }
      else
      {
        if (this.mLockModeRight != 3)
          return this.mLockModeRight;
        int k;
        if (i == 0)
          k = this.mLockModeEnd;
        else
          k = this.mLockModeStart;
        if (k != 3)
          return k;
      }
    }
    else
    {
      if (this.mLockModeLeft != 3)
        return this.mLockModeLeft;
      int j;
      if (i == 0)
        j = this.mLockModeStart;
      else
        j = this.mLockModeEnd;
      if (j != 3)
        return j;
    }
    return 0;
  }

  public int getDrawerLockMode(@NonNull View paramView)
  {
    if (!isDrawerView(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return getDrawerLockMode(((LayoutParams)paramView.getLayoutParams()).gravity);
  }

  @Nullable
  public CharSequence getDrawerTitle(int paramInt)
  {
    int i = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
    if (i == 3)
      return this.mTitleLeft;
    if (i == 5)
      return this.mTitleRight;
    return null;
  }

  int getDrawerViewAbsoluteGravity(View paramView)
  {
    return GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
  }

  float getDrawerViewOffset(View paramView)
  {
    return ((LayoutParams)paramView.getLayoutParams()).onScreen;
  }

  @Nullable
  public Drawable getStatusBarBackgroundDrawable()
  {
    return this.mStatusBarBackground;
  }

  boolean isContentView(View paramView)
  {
    boolean bool;
    if (((LayoutParams)paramView.getLayoutParams()).gravity == 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isDrawerOpen(int paramInt)
  {
    View localView = findDrawerWithGravity(paramInt);
    if (localView != null)
      return isDrawerOpen(localView);
    return false;
  }

  public boolean isDrawerOpen(@NonNull View paramView)
  {
    if (!isDrawerView(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    int i = ((LayoutParams)paramView.getLayoutParams()).openState;
    int j = 1;
    if ((i & j) != j)
      j = 0;
    return j;
  }

  boolean isDrawerView(View paramView)
  {
    int i = GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(paramView));
    if ((i & 0x3) != 0)
      return true;
    return (i & 0x5) != 0;
  }

  public boolean isDrawerVisible(int paramInt)
  {
    View localView = findDrawerWithGravity(paramInt);
    if (localView != null)
      return isDrawerVisible(localView);
    return false;
  }

  public boolean isDrawerVisible(@NonNull View paramView)
  {
    if (!isDrawerView(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    boolean bool;
    if (((LayoutParams)paramView.getLayoutParams()).onScreen > 0.0F)
      bool = true;
    else
      bool = false;
    return bool;
  }

  void moveDrawerToOffset(View paramView, float paramFloat)
  {
    float f1 = getDrawerViewOffset(paramView);
    float f2 = paramView.getWidth();
    int i = (int)(f1 * f2);
    int j = (int)(f2 * paramFloat) - i;
    if (!checkDrawerViewAbsoluteGravity(paramView, 3))
      j = -j;
    paramView.offsetLeftAndRight(j);
    setDrawerViewOffset(paramView, paramFloat);
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
  }

  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.mDrawStatusBarBackground) && (this.mStatusBarBackground != null))
    {
      int i;
      if ((Build.VERSION.SDK_INT >= 21) && (this.mLastInsets != null))
        i = ((WindowInsets)this.mLastInsets).getSystemWindowInsetTop();
      else
        i = 0;
      if (i > 0)
      {
        this.mStatusBarBackground.setBounds(0, 0, getWidth(), i);
        this.mStatusBarBackground.draw(paramCanvas);
      }
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    boolean bool1 = this.mLeftDragger.shouldInterceptTouchEvent(paramMotionEvent) | this.mRightDragger.shouldInterceptTouchEvent(paramMotionEvent);
    boolean bool2 = true;
    switch (i)
    {
    default:
      break;
    case 2:
      if (this.mLeftDragger.checkTouchSlop(3))
      {
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
      }
      break;
    case 1:
    case 3:
      closeDrawers(bool2);
      this.mDisallowInterceptRequested = false;
      this.mChildrenCanceledTouch = false;
      break;
    case 0:
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.mInitialMotionX = f1;
      this.mInitialMotionY = f2;
      if (this.mScrimOpacity > 0.0F)
      {
        View localView = this.mLeftDragger.findTopChildUnder((int)f1, (int)f2);
        if ((localView != null) && (isContentView(localView)))
        {
          j = 1;
          break label177;
        }
      }
      j = 0;
      label177: this.mDisallowInterceptRequested = false;
      this.mChildrenCanceledTouch = false;
      break;
    }
    int j = 0;
    if ((!bool1) && (j == 0) && (!hasPeekingDrawer()) && (!this.mChildrenCanceledTouch))
      bool2 = false;
    return bool2;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (hasVisibleDrawer()))
    {
      paramKeyEvent.startTracking();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      View localView = findVisibleDrawer();
      if ((localView != null) && (getDrawerLockMode(localView) == 0))
        closeDrawers();
      boolean bool;
      if (localView != null)
        bool = true;
      else
        bool = false;
      return bool;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mInLayout = true;
    int i = paramInt3 - paramInt1;
    int j = getChildCount();
    for (int k = 0; k < j; k++)
    {
      View localView = getChildAt(k);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (isContentView(localView))
        {
          localView.layout(localLayoutParams.leftMargin, localLayoutParams.topMargin, localLayoutParams.leftMargin + localView.getMeasuredWidth(), localLayoutParams.topMargin + localView.getMeasuredHeight());
        }
        else
        {
          int m = localView.getMeasuredWidth();
          int n = localView.getMeasuredHeight();
          int i2;
          float f2;
          if (checkDrawerViewAbsoluteGravity(localView, 3))
          {
            int i9 = -m;
            float f3 = m;
            i2 = i9 + (int)(f3 * localLayoutParams.onScreen);
            f2 = m + i2 / f3;
          }
          else
          {
            float f1 = m;
            int i1 = i - (int)(f1 * localLayoutParams.onScreen);
            f2 = i - i1 / f1;
            i2 = i1;
          }
          int i3;
          if (f2 != localLayoutParams.onScreen)
            i3 = 1;
          else
            i3 = 0;
          int i4 = 0x70 & localLayoutParams.gravity;
          if (i4 != 16)
          {
            if (i4 != 80)
            {
              localView.layout(i2, localLayoutParams.topMargin, m + i2, n + localLayoutParams.topMargin);
            }
            else
            {
              int i8 = paramInt4 - paramInt2;
              localView.layout(i2, i8 - localLayoutParams.bottomMargin - localView.getMeasuredHeight(), m + i2, i8 - localLayoutParams.bottomMargin);
            }
          }
          else
          {
            int i5 = paramInt4 - paramInt2;
            int i6 = (i5 - n) / 2;
            if (i6 < localLayoutParams.topMargin)
              i6 = localLayoutParams.topMargin;
            else if (i6 + n > i5 - localLayoutParams.bottomMargin)
              i6 = i5 - localLayoutParams.bottomMargin - n;
            localView.layout(i2, i6, m + i2, n + i6);
          }
          if (i3 != 0)
            setDrawerViewOffset(localView, f2);
          int i7;
          if (localLayoutParams.onScreen > 0.0F)
            i7 = 0;
          else
            i7 = 4;
          if (localView.getVisibility() != i7)
            localView.setVisibility(i7);
        }
      }
    }
    this.mInLayout = false;
    this.mFirstLayout = false;
  }

  @SuppressLint({"WrongConstant"})
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    int k = View.MeasureSpec.getSize(paramInt1);
    int m = View.MeasureSpec.getSize(paramInt2);
    if ((i != 1073741824) || (j != 1073741824))
    {
      if (!isInEditMode())
        break label769;
      if ((i != -2147483648) && (i == 0))
        k = 300;
      if ((j != -2147483648) && (j == 0))
        m = 300;
    }
    setMeasuredDimension(k, m);
    int n;
    if ((this.mLastInsets != null) && (ViewCompat.getFitsSystemWindows(this)))
      n = 1;
    else
      n = 0;
    int i1 = ViewCompat.getLayoutDirection(this);
    int i2 = getChildCount();
    int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    while (i3 < i2)
    {
      View localView = getChildAt(i3);
      LayoutParams localLayoutParams;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (n != 0)
        {
          int i8 = GravityCompat.getAbsoluteGravity(localLayoutParams.gravity, i1);
          if (ViewCompat.getFitsSystemWindows(localView))
          {
            if (Build.VERSION.SDK_INT >= 21)
            {
              WindowInsets localWindowInsets2 = (WindowInsets)this.mLastInsets;
              if (i8 == 3)
                localWindowInsets2 = localWindowInsets2.replaceSystemWindowInsets(localWindowInsets2.getSystemWindowInsetLeft(), localWindowInsets2.getSystemWindowInsetTop(), 0, localWindowInsets2.getSystemWindowInsetBottom());
              else if (i8 == 5)
                localWindowInsets2 = localWindowInsets2.replaceSystemWindowInsets(0, localWindowInsets2.getSystemWindowInsetTop(), localWindowInsets2.getSystemWindowInsetRight(), localWindowInsets2.getSystemWindowInsetBottom());
              localView.dispatchApplyWindowInsets(localWindowInsets2);
            }
          }
          else if (Build.VERSION.SDK_INT >= 21)
          {
            WindowInsets localWindowInsets1 = (WindowInsets)this.mLastInsets;
            if (i8 == 3)
              localWindowInsets1 = localWindowInsets1.replaceSystemWindowInsets(localWindowInsets1.getSystemWindowInsetLeft(), localWindowInsets1.getSystemWindowInsetTop(), 0, localWindowInsets1.getSystemWindowInsetBottom());
            else if (i8 == 5)
              localWindowInsets1 = localWindowInsets1.replaceSystemWindowInsets(0, localWindowInsets1.getSystemWindowInsetTop(), localWindowInsets1.getSystemWindowInsetRight(), localWindowInsets1.getSystemWindowInsetBottom());
            localLayoutParams.leftMargin = localWindowInsets1.getSystemWindowInsetLeft();
            localLayoutParams.topMargin = localWindowInsets1.getSystemWindowInsetTop();
            localLayoutParams.rightMargin = localWindowInsets1.getSystemWindowInsetRight();
            localLayoutParams.bottomMargin = localWindowInsets1.getSystemWindowInsetBottom();
          }
        }
        if (isContentView(localView))
          localView.measure(View.MeasureSpec.makeMeasureSpec(k - localLayoutParams.leftMargin - localLayoutParams.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec(m - localLayoutParams.topMargin - localLayoutParams.bottomMargin, 1073741824));
      }
      else
      {
        break label688;
      }
      if (isDrawerView(localView))
      {
        if ((SET_DRAWER_SHADOW_FROM_ELEVATION) && (ViewCompat.getElevation(localView) != this.mDrawerElevation))
          ViewCompat.setElevation(localView, this.mDrawerElevation);
        int i6 = 0x7 & getDrawerViewAbsoluteGravity(localView);
        int i7;
        if (i6 == 3)
          i7 = 1;
        else
          i7 = 0;
        if (((i7 != 0) && (i4 != 0)) || ((i7 == 0) && (i5 != 0)))
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("Child drawer has absolute gravity ");
          localStringBuilder2.append(gravityToString(i6));
          localStringBuilder2.append(" but this ");
          localStringBuilder2.append("DrawerLayout");
          localStringBuilder2.append(" already has a ");
          localStringBuilder2.append("drawer view along that edge");
          throw new IllegalStateException(localStringBuilder2.toString());
        }
        if (i7 != 0)
          i4 = 1;
        else
          i5 = 1;
        localView.measure(getChildMeasureSpec(paramInt1, this.mMinDrawerMargin + localLayoutParams.leftMargin + localLayoutParams.rightMargin, localLayoutParams.width), getChildMeasureSpec(paramInt2, localLayoutParams.topMargin + localLayoutParams.bottomMargin, localLayoutParams.height));
        label688: i3++;
      }
      else
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Child ");
        localStringBuilder1.append(localView);
        localStringBuilder1.append(" at index ");
        localStringBuilder1.append(i3);
        localStringBuilder1.append(" does not have a valid layout_gravity - must be Gravity.LEFT, ");
        localStringBuilder1.append("Gravity.RIGHT or Gravity.NO_GRAVITY");
        throw new IllegalStateException(localStringBuilder1.toString());
      }
    }
    return;
    label769: throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
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
    if (localSavedState.openDrawerGravity != 0)
    {
      View localView = findDrawerWithGravity(localSavedState.openDrawerGravity);
      if (localView != null)
        openDrawer(localView);
    }
    if (localSavedState.lockModeLeft != 3)
      setDrawerLockMode(localSavedState.lockModeLeft, 3);
    if (localSavedState.lockModeRight != 3)
      setDrawerLockMode(localSavedState.lockModeRight, 5);
    if (localSavedState.lockModeStart != 3)
      setDrawerLockMode(localSavedState.lockModeStart, 8388611);
    if (localSavedState.lockModeEnd != 3)
      setDrawerLockMode(localSavedState.lockModeEnd, 8388613);
  }

  public void onRtlPropertiesChanged(int paramInt)
  {
    resolveShadowDrawables();
  }

  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    int i = getChildCount();
    int j = 0;
    while (j < i)
    {
      LayoutParams localLayoutParams = (LayoutParams)getChildAt(j).getLayoutParams();
      int k = localLayoutParams.openState;
      int m = 1;
      int n;
      if (k == m)
        n = 1;
      else
        n = 0;
      if (localLayoutParams.openState != 2)
        m = 0;
      if ((n == 0) && (m == 0))
        j++;
      else
        localSavedState.openDrawerGravity = localLayoutParams.gravity;
    }
    localSavedState.lockModeLeft = this.mLockModeLeft;
    localSavedState.lockModeRight = this.mLockModeRight;
    localSavedState.lockModeStart = this.mLockModeStart;
    localSavedState.lockModeEnd = this.mLockModeEnd;
    return localSavedState;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.mLeftDragger.processTouchEvent(paramMotionEvent);
    this.mRightDragger.processTouchEvent(paramMotionEvent);
    int i = 0xFF & paramMotionEvent.getAction();
    if (i != 3)
    {
      switch (i)
      {
      default:
        break;
      case 1:
        float f3 = paramMotionEvent.getX();
        float f4 = paramMotionEvent.getY();
        View localView1 = this.mLeftDragger.findTopChildUnder((int)f3, (int)f4);
        if ((localView1 != null) && (isContentView(localView1)))
        {
          float f5 = f3 - this.mInitialMotionX;
          float f6 = f4 - this.mInitialMotionY;
          int j = this.mLeftDragger.getTouchSlop();
          if (f5 * f5 + f6 * f6 < j * j)
          {
            View localView2 = findOpenDrawer();
            if ((localView2 != null) && (getDrawerLockMode(localView2) != 2))
            {
              bool = false;
              break label177;
            }
          }
        }
        boolean bool = true;
        closeDrawers(bool);
        this.mDisallowInterceptRequested = false;
        break;
      case 0:
        label177: float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        this.mInitialMotionX = f1;
        this.mInitialMotionY = f2;
        this.mDisallowInterceptRequested = false;
        this.mChildrenCanceledTouch = false;
        break;
      }
    }
    else
    {
      closeDrawers(true);
      this.mDisallowInterceptRequested = false;
      this.mChildrenCanceledTouch = false;
    }
    return true;
  }

  public void openDrawer(int paramInt)
  {
    openDrawer(paramInt, true);
  }

  public void openDrawer(int paramInt, boolean paramBoolean)
  {
    View localView = findDrawerWithGravity(paramInt);
    if (localView == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("No drawer view found with gravity ");
      localStringBuilder.append(gravityToString(paramInt));
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    openDrawer(localView, paramBoolean);
  }

  public void openDrawer(@NonNull View paramView)
  {
    openDrawer(paramView, true);
  }

  public void openDrawer(@NonNull View paramView, boolean paramBoolean)
  {
    if (!isDrawerView(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a sliding drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (this.mFirstLayout)
    {
      localLayoutParams.onScreen = 1.0F;
      localLayoutParams.openState = 1;
      updateChildrenImportantForAccessibility(paramView, true);
    }
    else if (paramBoolean)
    {
      localLayoutParams.openState = (0x2 | localLayoutParams.openState);
      if (checkDrawerViewAbsoluteGravity(paramView, 3))
        this.mLeftDragger.smoothSlideViewTo(paramView, 0, paramView.getTop());
      else
        this.mRightDragger.smoothSlideViewTo(paramView, getWidth() - paramView.getWidth(), paramView.getTop());
    }
    else
    {
      moveDrawerToOffset(paramView, 1.0F);
      updateDrawerState(localLayoutParams.gravity, 0, paramView);
      paramView.setVisibility(0);
    }
    invalidate();
  }

  public void removeDrawerListener(@NonNull DrawerListener paramDrawerListener)
  {
    if (paramDrawerListener == null)
      return;
    if (this.mListeners == null)
      return;
    this.mListeners.remove(paramDrawerListener);
  }

  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    this.mDisallowInterceptRequested = paramBoolean;
    if (paramBoolean)
      closeDrawers(true);
  }

  public void requestLayout()
  {
    if (!this.mInLayout)
      super.requestLayout();
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setChildInsets(Object paramObject, boolean paramBoolean)
  {
    this.mLastInsets = paramObject;
    this.mDrawStatusBarBackground = paramBoolean;
    boolean bool;
    if ((!paramBoolean) && (getBackground() == null))
      bool = true;
    else
      bool = false;
    setWillNotDraw(bool);
    requestLayout();
  }

  public void setDrawerElevation(float paramFloat)
  {
    this.mDrawerElevation = paramFloat;
    for (int i = 0; i < getChildCount(); i++)
    {
      View localView = getChildAt(i);
      if (isDrawerView(localView))
        ViewCompat.setElevation(localView, this.mDrawerElevation);
    }
  }

  @Deprecated
  public void setDrawerListener(DrawerListener paramDrawerListener)
  {
    if (this.mListener != null)
      removeDrawerListener(this.mListener);
    if (paramDrawerListener != null)
      addDrawerListener(paramDrawerListener);
    this.mListener = paramDrawerListener;
  }

  public void setDrawerLockMode(int paramInt)
  {
    setDrawerLockMode(paramInt, 3);
    setDrawerLockMode(paramInt, 5);
  }

  public void setDrawerLockMode(int paramInt1, int paramInt2)
  {
    int i = GravityCompat.getAbsoluteGravity(paramInt2, ViewCompat.getLayoutDirection(this));
    if (paramInt2 != 3)
    {
      if (paramInt2 != 5)
      {
        if (paramInt2 != 8388611)
        {
          if (paramInt2 == 8388613)
            this.mLockModeEnd = paramInt1;
        }
        else
          this.mLockModeStart = paramInt1;
      }
      else
        this.mLockModeRight = paramInt1;
    }
    else
      this.mLockModeLeft = paramInt1;
    if (paramInt1 != 0)
    {
      ViewDragHelper localViewDragHelper;
      if (i == 3)
        localViewDragHelper = this.mLeftDragger;
      else
        localViewDragHelper = this.mRightDragger;
      localViewDragHelper.cancel();
    }
    switch (paramInt1)
    {
    default:
      break;
    case 2:
      View localView2 = findDrawerWithGravity(i);
      if (localView2 != null)
        openDrawer(localView2);
      break;
    case 1:
      View localView1 = findDrawerWithGravity(i);
      if (localView1 != null)
        closeDrawer(localView1);
      break;
    }
  }

  public void setDrawerLockMode(int paramInt, @NonNull View paramView)
  {
    if (!isDrawerView(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a ");
      localStringBuilder.append("drawer with appropriate layout_gravity");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    setDrawerLockMode(paramInt, ((LayoutParams)paramView.getLayoutParams()).gravity);
  }

  public void setDrawerShadow(@DrawableRes int paramInt1, int paramInt2)
  {
    setDrawerShadow(ContextCompat.getDrawable(getContext(), paramInt1), paramInt2);
  }

  public void setDrawerShadow(Drawable paramDrawable, int paramInt)
  {
    if (SET_DRAWER_SHADOW_FROM_ELEVATION)
      return;
    if ((paramInt & 0x800003) == 8388611)
    {
      this.mShadowStart = paramDrawable;
    }
    else if ((paramInt & 0x800005) == 8388613)
    {
      this.mShadowEnd = paramDrawable;
    }
    else if ((paramInt & 0x3) == 3)
    {
      this.mShadowLeft = paramDrawable;
    }
    else
    {
      if ((paramInt & 0x5) != 5)
        return;
      this.mShadowRight = paramDrawable;
    }
    resolveShadowDrawables();
    invalidate();
    return;
  }

  public void setDrawerTitle(int paramInt, @Nullable CharSequence paramCharSequence)
  {
    int i = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
    if (i == 3)
      this.mTitleLeft = paramCharSequence;
    else if (i == 5)
      this.mTitleRight = paramCharSequence;
  }

  void setDrawerViewOffset(View paramView, float paramFloat)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (paramFloat == localLayoutParams.onScreen)
      return;
    localLayoutParams.onScreen = paramFloat;
    dispatchOnDrawerSlide(paramView, paramFloat);
  }

  public void setScrimColor(@ColorInt int paramInt)
  {
    this.mScrimColor = paramInt;
    invalidate();
  }

  public void setStatusBarBackground(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0)
      localDrawable = ContextCompat.getDrawable(getContext(), paramInt);
    else
      localDrawable = null;
    this.mStatusBarBackground = localDrawable;
    invalidate();
  }

  public void setStatusBarBackground(@Nullable Drawable paramDrawable)
  {
    this.mStatusBarBackground = paramDrawable;
    invalidate();
  }

  public void setStatusBarBackgroundColor(@ColorInt int paramInt)
  {
    this.mStatusBarBackground = new ColorDrawable(paramInt);
    invalidate();
  }

  void updateDrawerState(int paramInt1, int paramInt2, View paramView)
  {
    int i = this.mLeftDragger.getViewDragState();
    int j = this.mRightDragger.getViewDragState();
    int k = 2;
    if ((i != 1) && (j != 1))
    {
      if ((i != k) && (j != k))
        k = 0;
    }
    else
      k = 1;
    if ((paramView != null) && (paramInt2 == 0))
    {
      LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
      if (localLayoutParams.onScreen == 0.0F)
        dispatchOnDrawerClosed(paramView);
      else if (localLayoutParams.onScreen == 1.0F)
        dispatchOnDrawerOpened(paramView);
    }
    if (k != this.mDrawerState)
    {
      this.mDrawerState = k;
      if (this.mListeners != null)
        for (int m = this.mListeners.size() - 1; m >= 0; m--)
          ((DrawerListener)this.mListeners.get(m)).onDrawerStateChanged(k);
    }
  }

  class AccessibilityDelegate extends AccessibilityDelegateCompat
  {
    private final Rect mTmpRect = new Rect();

    AccessibilityDelegate()
    {
    }

    private void addChildrenForAccessibility(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat, ViewGroup paramViewGroup)
    {
      int i = paramViewGroup.getChildCount();
      for (int j = 0; j < i; j++)
      {
        View localView = paramViewGroup.getChildAt(j);
        if (DrawerLayout.includeChildForAccessibility(localView))
          paramAccessibilityNodeInfoCompat.addChild(localView);
      }
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
    }

    public boolean dispatchPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      if (paramAccessibilityEvent.getEventType() == 32)
      {
        List localList = paramAccessibilityEvent.getText();
        View localView = DrawerLayout.this.findVisibleDrawer();
        if (localView != null)
        {
          int i = DrawerLayout.this.getDrawerViewAbsoluteGravity(localView);
          CharSequence localCharSequence = DrawerLayout.this.getDrawerTitle(i);
          if (localCharSequence != null)
            localList.add(localCharSequence);
        }
        return true;
      }
      return super.dispatchPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(DrawerLayout.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      if (DrawerLayout.CAN_HIDE_DESCENDANTS)
      {
        super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      }
      else
      {
        AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(paramAccessibilityNodeInfoCompat);
        super.onInitializeAccessibilityNodeInfo(paramView, localAccessibilityNodeInfoCompat);
        paramAccessibilityNodeInfoCompat.setSource(paramView);
        ViewParent localViewParent = ViewCompat.getParentForAccessibility(paramView);
        if ((localViewParent instanceof View))
          paramAccessibilityNodeInfoCompat.setParent((View)localViewParent);
        copyNodeInfoNoChildren(paramAccessibilityNodeInfoCompat, localAccessibilityNodeInfoCompat);
        localAccessibilityNodeInfoCompat.recycle();
        addChildrenForAccessibility(paramAccessibilityNodeInfoCompat, (ViewGroup)paramView);
      }
      paramAccessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
      paramAccessibilityNodeInfoCompat.setFocusable(false);
      paramAccessibilityNodeInfoCompat.setFocused(false);
      paramAccessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
      paramAccessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      if ((!DrawerLayout.CAN_HIDE_DESCENDANTS) && (!DrawerLayout.includeChildForAccessibility(paramView)))
        return false;
      return super.onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
    }
  }

  static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat
  {
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      if (!DrawerLayout.includeChildForAccessibility(paramView))
        paramAccessibilityNodeInfoCompat.setParent(null);
    }
  }

  public static abstract interface DrawerListener
  {
    public abstract void onDrawerClosed(@NonNull View paramView);

    public abstract void onDrawerOpened(@NonNull View paramView);

    public abstract void onDrawerSlide(@NonNull View paramView, float paramFloat);

    public abstract void onDrawerStateChanged(int paramInt);
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams
  {
    private static final int FLAG_IS_CLOSING = 4;
    private static final int FLAG_IS_OPENED = 1;
    private static final int FLAG_IS_OPENING = 2;
    public int gravity = 0;
    boolean isPeeking;
    float onScreen;
    int openState;

    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      this(paramInt1, paramInt2);
      this.gravity = paramInt3;
    }

    public LayoutParams(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, DrawerLayout.LAYOUT_ATTRS);
      this.gravity = localTypedArray.getInt(0, 0);
      localTypedArray.recycle();
    }

    public LayoutParams(@NonNull LayoutParams paramLayoutParams)
    {
      super();
      this.gravity = paramLayoutParams.gravity;
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

  protected static class SavedState extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public DrawerLayout.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new DrawerLayout.SavedState(paramAnonymousParcel, null);
      }

      public DrawerLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new DrawerLayout.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }

      public DrawerLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new DrawerLayout.SavedState[paramAnonymousInt];
      }
    };
    int lockModeEnd;
    int lockModeLeft;
    int lockModeRight;
    int lockModeStart;
    int openDrawerGravity = 0;

    public SavedState(@NonNull Parcel paramParcel, @Nullable ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      this.openDrawerGravity = paramParcel.readInt();
      this.lockModeLeft = paramParcel.readInt();
      this.lockModeRight = paramParcel.readInt();
      this.lockModeStart = paramParcel.readInt();
      this.lockModeEnd = paramParcel.readInt();
    }

    public SavedState(@NonNull Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.openDrawerGravity);
      paramParcel.writeInt(this.lockModeLeft);
      paramParcel.writeInt(this.lockModeRight);
      paramParcel.writeInt(this.lockModeStart);
      paramParcel.writeInt(this.lockModeEnd);
    }
  }

  public static abstract class SimpleDrawerListener
    implements DrawerLayout.DrawerListener
  {
    public void onDrawerClosed(View paramView)
    {
    }

    public void onDrawerOpened(View paramView)
    {
    }

    public void onDrawerSlide(View paramView, float paramFloat)
    {
    }

    public void onDrawerStateChanged(int paramInt)
    {
    }
  }

  private class ViewDragCallback extends ViewDragHelper.Callback
  {
    private final int mAbsGravity;
    private ViewDragHelper mDragger;
    private final Runnable mPeekRunnable = new Runnable()
    {
      public void run()
      {
        DrawerLayout.ViewDragCallback.this.peekDrawer();
      }
    };

    ViewDragCallback(int arg2)
    {
      int i;
      this.mAbsGravity = i;
    }

    private void closeOtherDrawer()
    {
      int i = this.mAbsGravity;
      int j = 3;
      if (i == j)
        j = 5;
      View localView = DrawerLayout.this.findDrawerWithGravity(j);
      if (localView != null)
        DrawerLayout.this.closeDrawer(localView);
    }

    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(paramView, 3))
        return Math.max(-paramView.getWidth(), Math.min(paramInt1, 0));
      int i = DrawerLayout.this.getWidth();
      return Math.max(i - paramView.getWidth(), Math.min(paramInt1, i));
    }

    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      return paramView.getTop();
    }

    public int getViewHorizontalDragRange(View paramView)
    {
      int i;
      if (DrawerLayout.this.isDrawerView(paramView))
        i = paramView.getWidth();
      else
        i = 0;
      return i;
    }

    public void onEdgeDragStarted(int paramInt1, int paramInt2)
    {
      View localView;
      if ((paramInt1 & 0x1) == 1)
        localView = DrawerLayout.this.findDrawerWithGravity(3);
      else
        localView = DrawerLayout.this.findDrawerWithGravity(5);
      if ((localView != null) && (DrawerLayout.this.getDrawerLockMode(localView) == 0))
        this.mDragger.captureChildView(localView, paramInt2);
    }

    public boolean onEdgeLock(int paramInt)
    {
      return false;
    }

    public void onEdgeTouched(int paramInt1, int paramInt2)
    {
      DrawerLayout.this.postDelayed(this.mPeekRunnable, 160L);
    }

    public void onViewCaptured(View paramView, int paramInt)
    {
      ((DrawerLayout.LayoutParams)paramView.getLayoutParams()).isPeeking = false;
      closeOtherDrawer();
    }

    public void onViewDragStateChanged(int paramInt)
    {
      DrawerLayout.this.updateDrawerState(this.mAbsGravity, paramInt, this.mDragger.getCapturedView());
    }

    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int i = paramView.getWidth();
      float f;
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(paramView, 3))
        f = paramInt1 + i / i;
      else
        f = DrawerLayout.this.getWidth() - paramInt1 / i;
      DrawerLayout.this.setDrawerViewOffset(paramView, f);
      int j;
      if (f == 0.0F)
        j = 4;
      else
        j = 0;
      paramView.setVisibility(j);
      DrawerLayout.this.invalidate();
    }

    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      float f = DrawerLayout.this.getDrawerViewOffset(paramView);
      int i = paramView.getWidth();
      int k;
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(paramView, 3))
      {
        if ((paramFloat1 <= 0.0F) && ((paramFloat1 != 0.0F) || (f <= 0.5F)))
          k = -i;
        else
          k = 0;
      }
      else
      {
        int j = DrawerLayout.this.getWidth();
        if ((paramFloat1 < 0.0F) || ((paramFloat1 == 0.0F) && (f > 0.5F)))
          j -= i;
        k = j;
      }
      this.mDragger.settleCapturedViewAt(k, paramView.getTop());
      DrawerLayout.this.invalidate();
    }

    void peekDrawer()
    {
      int i = this.mDragger.getEdgeSize();
      int j;
      if (this.mAbsGravity == 3)
        j = 1;
      else
        j = 0;
      View localView;
      int k;
      if (j != 0)
      {
        localView = DrawerLayout.this.findDrawerWithGravity(3);
        int m = 0;
        if (localView != null)
          m = -localView.getWidth();
        k = m + i;
      }
      else
      {
        localView = DrawerLayout.this.findDrawerWithGravity(5);
        k = DrawerLayout.this.getWidth() - i;
      }
      if ((localView != null) && (((j != 0) && (localView.getLeft() < k)) || ((j == 0) && (localView.getLeft() > k) && (DrawerLayout.this.getDrawerLockMode(localView) == 0))))
      {
        DrawerLayout.LayoutParams localLayoutParams = (DrawerLayout.LayoutParams)localView.getLayoutParams();
        this.mDragger.smoothSlideViewTo(localView, k, localView.getTop());
        localLayoutParams.isPeeking = true;
        DrawerLayout.this.invalidate();
        closeOtherDrawer();
        DrawerLayout.this.cancelChildViewTouch();
      }
    }

    public void removeCallbacks()
    {
      DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
    }

    public void setDragger(ViewDragHelper paramViewDragHelper)
    {
      this.mDragger = paramViewDragHelper;
    }

    public boolean tryCaptureView(View paramView, int paramInt)
    {
      boolean bool;
      if ((DrawerLayout.this.isDrawerView(paramView)) && (DrawerLayout.this.checkDrawerViewAbsoluteGravity(paramView, this.mAbsGravity)) && (DrawerLayout.this.getDrawerLockMode(paramView) == 0))
        bool = true;
      else
        bool = false;
      return bool;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.DrawerLayout
 * JD-Core Version:    0.6.1
 */