package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.coordinatorlayout.R.attr;
import android.support.coordinatorlayout.R.style;
import android.support.coordinatorlayout.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DirectedAcyclicGraph;
import android.support.v4.widget.ViewGroupUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup
  implements NestedScrollingParent2
{
  static final Class<?>[] CONSTRUCTOR_PARAMS = { Context.class, AttributeSet.class };
  static final int EVENT_NESTED_SCROLL = 1;
  static final int EVENT_PRE_DRAW = 0;
  static final int EVENT_VIEW_REMOVED = 2;
  static final String TAG = "CoordinatorLayout";
  static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
  private static final int TYPE_ON_INTERCEPT = 0;
  private static final int TYPE_ON_TOUCH = 1;
  static final String WIDGET_PACKAGE_NAME;
  static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors = new ThreadLocal();
  private static final Pools.Pool<Rect> sRectPool = new Pools.SynchronizedPool(12);
  private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
  private View mBehaviorTouchView;
  private final DirectedAcyclicGraph<View> mChildDag = new DirectedAcyclicGraph();
  private final List<View> mDependencySortedChildren = new ArrayList();
  private boolean mDisallowInterceptReset;
  private boolean mDrawStatusBarBackground;
  private boolean mIsAttachedToWindow;
  private int[] mKeylines;
  private WindowInsetsCompat mLastInsets;
  private boolean mNeedsPreDrawListener;
  private final NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
  private View mNestedScrollingTarget;
  ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
  private OnPreDrawListener mOnPreDrawListener;
  private Paint mScrimPaint;
  private Drawable mStatusBarBackground;
  private final List<View> mTempDependenciesList = new ArrayList();
  private final int[] mTempIntPair = new int[2];
  private final List<View> mTempList1 = new ArrayList();

  static
  {
    Package localPackage = CoordinatorLayout.class.getPackage();
    String str;
    if (localPackage != null)
      str = localPackage.getName();
    else
      str = null;
    WIDGET_PACKAGE_NAME = str;
    if (Build.VERSION.SDK_INT >= 21)
      TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
    else
      TOP_SORTED_CHILDREN_COMPARATOR = null;
  }

  public CoordinatorLayout(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }

  public CoordinatorLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.coordinatorLayoutStyle);
  }

  public CoordinatorLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    int i = 0;
    TypedArray localTypedArray;
    if (paramInt == 0)
      localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CoordinatorLayout, 0, R.style.Widget_Support_CoordinatorLayout);
    else
      localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CoordinatorLayout, paramInt, 0);
    int j = localTypedArray.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
    if (j != 0)
    {
      Resources localResources = paramContext.getResources();
      this.mKeylines = localResources.getIntArray(j);
      float f = localResources.getDisplayMetrics().density;
      int k = this.mKeylines.length;
      while (i < k)
      {
        this.mKeylines[i] = (int)(f * this.mKeylines[i]);
        i++;
      }
    }
    this.mStatusBarBackground = localTypedArray.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
    localTypedArray.recycle();
    setupForInsets();
    super.setOnHierarchyChangeListener(new HierarchyChangeListener());
  }

  @NonNull
  private static Rect acquireTempRect()
  {
    Rect localRect = (Rect)sRectPool.acquire();
    if (localRect == null)
      localRect = new Rect();
    return localRect;
  }

  private static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2)
      return paramInt2;
    if (paramInt1 > paramInt3)
      return paramInt3;
    return paramInt1;
  }

  private void constrainChildRect(LayoutParams paramLayoutParams, Rect paramRect, int paramInt1, int paramInt2)
  {
    int i = getWidth();
    int j = getHeight();
    int k = Math.max(getPaddingLeft() + paramLayoutParams.leftMargin, Math.min(paramRect.left, i - getPaddingRight() - paramInt1 - paramLayoutParams.rightMargin));
    int m = Math.max(getPaddingTop() + paramLayoutParams.topMargin, Math.min(paramRect.top, j - getPaddingBottom() - paramInt2 - paramLayoutParams.bottomMargin));
    paramRect.set(k, m, paramInt1 + k, paramInt2 + m);
  }

  private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (paramWindowInsetsCompat.isConsumed())
      return paramWindowInsetsCompat;
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      View localView = getChildAt(i);
      if (ViewCompat.getFitsSystemWindows(localView))
      {
        Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
        if (localBehavior != null)
        {
          paramWindowInsetsCompat = localBehavior.onApplyWindowInsets(this, localView, paramWindowInsetsCompat);
          if (paramWindowInsetsCompat.isConsumed())
            break;
        }
      }
      i++;
    }
    return paramWindowInsetsCompat;
  }

  private void getDesiredAnchoredChildRectWithoutConstraints(View paramView, int paramInt1, Rect paramRect1, Rect paramRect2, LayoutParams paramLayoutParams, int paramInt2, int paramInt3)
  {
    int i = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(paramLayoutParams.gravity), paramInt1);
    int j = GravityCompat.getAbsoluteGravity(resolveGravity(paramLayoutParams.anchorGravity), paramInt1);
    int k = i & 0x7;
    int m = i & 0x70;
    int n = j & 0x7;
    int i1 = j & 0x70;
    int i2;
    if (n != 1)
    {
      if (n != 5)
        i2 = paramRect1.left;
      else
        i2 = paramRect1.right;
    }
    else
      i2 = paramRect1.left + paramRect1.width() / 2;
    int i3;
    if (i1 != 16)
    {
      if (i1 != 80)
        i3 = paramRect1.top;
      else
        i3 = paramRect1.bottom;
    }
    else
      i3 = paramRect1.top + paramRect1.height() / 2;
    if (k != 1)
    {
      if (k != 5)
        i2 -= paramInt2;
    }
    else
      i2 -= paramInt2 / 2;
    if (m != 16)
    {
      if (m != 80)
        i3 -= paramInt3;
    }
    else
      i3 -= paramInt3 / 2;
    paramRect2.set(i2, i3, paramInt2 + i2, paramInt3 + i3);
  }

  private int getKeyline(int paramInt)
  {
    if (this.mKeylines == null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("No keylines defined for ");
      localStringBuilder1.append(this);
      localStringBuilder1.append(" - attempted index lookup ");
      localStringBuilder1.append(paramInt);
      Log.e("CoordinatorLayout", localStringBuilder1.toString());
      return 0;
    }
    if ((paramInt >= 0) && (paramInt < this.mKeylines.length))
      return this.mKeylines[paramInt];
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Keyline index ");
    localStringBuilder2.append(paramInt);
    localStringBuilder2.append(" out of range for ");
    localStringBuilder2.append(this);
    Log.e("CoordinatorLayout", localStringBuilder2.toString());
    return 0;
  }

  private void getTopSortedChildren(List<View> paramList)
  {
    paramList.clear();
    boolean bool = isChildrenDrawingOrderEnabled();
    int i = getChildCount();
    for (int j = i - 1; j >= 0; j--)
    {
      int k;
      if (bool)
        k = getChildDrawingOrder(i, j);
      else
        k = j;
      paramList.add(getChildAt(k));
    }
    if (TOP_SORTED_CHILDREN_COMPARATOR != null)
      Collections.sort(paramList, TOP_SORTED_CHILDREN_COMPARATOR);
  }

  private boolean hasDependencies(View paramView)
  {
    return this.mChildDag.hasOutgoingEdges(paramView);
  }

  private void layoutChild(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect localRect1 = acquireTempRect();
    localRect1.set(getPaddingLeft() + localLayoutParams.leftMargin, getPaddingTop() + localLayoutParams.topMargin, getWidth() - getPaddingRight() - localLayoutParams.rightMargin, getHeight() - getPaddingBottom() - localLayoutParams.bottomMargin);
    if ((this.mLastInsets != null) && (ViewCompat.getFitsSystemWindows(this)) && (!ViewCompat.getFitsSystemWindows(paramView)))
    {
      localRect1.left += this.mLastInsets.getSystemWindowInsetLeft();
      localRect1.top += this.mLastInsets.getSystemWindowInsetTop();
      localRect1.right -= this.mLastInsets.getSystemWindowInsetRight();
      localRect1.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
    }
    Rect localRect2 = acquireTempRect();
    GravityCompat.apply(resolveGravity(localLayoutParams.gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), localRect1, localRect2, paramInt);
    paramView.layout(localRect2.left, localRect2.top, localRect2.right, localRect2.bottom);
    releaseTempRect(localRect1);
    releaseTempRect(localRect2);
  }

  private void layoutChildWithAnchor(View paramView1, View paramView2, int paramInt)
  {
    Rect localRect1 = acquireTempRect();
    Rect localRect2 = acquireTempRect();
    try
    {
      getDescendantRect(paramView2, localRect1);
      getDesiredAnchoredChildRect(paramView1, paramInt, localRect1, localRect2);
      paramView1.layout(localRect2.left, localRect2.top, localRect2.right, localRect2.bottom);
      return;
    }
    finally
    {
      releaseTempRect(localRect1);
      releaseTempRect(localRect2);
    }
  }

  private void layoutChildWithKeyline(View paramView, int paramInt1, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(localLayoutParams.gravity), paramInt2);
    int j = i & 0x7;
    int k = i & 0x70;
    int m = getWidth();
    int n = getHeight();
    int i1 = paramView.getMeasuredWidth();
    int i2 = paramView.getMeasuredHeight();
    if (paramInt2 == 1)
      paramInt1 = m - paramInt1;
    int i3 = getKeyline(paramInt1) - i1;
    if (j != 1)
    {
      if (j == 5)
        i3 += i1;
    }
    else
      i3 += i1 / 2;
    int i4;
    if (k != 16)
    {
      if (k != 80)
        i4 = 0;
      else
        i4 = i2 + 0;
    }
    else
      i4 = 0 + i2 / 2;
    int i5 = Math.max(getPaddingLeft() + localLayoutParams.leftMargin, Math.min(i3, m - getPaddingRight() - i1 - localLayoutParams.rightMargin));
    int i6 = Math.max(getPaddingTop() + localLayoutParams.topMargin, Math.min(i4, n - getPaddingBottom() - i2 - localLayoutParams.bottomMargin));
    paramView.layout(i5, i6, i1 + i5, i2 + i6);
  }

  private void offsetChildByInset(View paramView, Rect paramRect, int paramInt)
  {
    if (!ViewCompat.isLaidOut(paramView))
      return;
    if ((paramView.getWidth() > 0) && (paramView.getHeight() > 0))
    {
      LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
      Behavior localBehavior = localLayoutParams.getBehavior();
      Rect localRect1 = acquireTempRect();
      Rect localRect2 = acquireTempRect();
      localRect2.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
      if ((localBehavior != null) && (localBehavior.getInsetDodgeRect(this, paramView, localRect1)))
      {
        if (!localRect2.contains(localRect1))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Rect should be within the child's bounds. Rect:");
          localStringBuilder.append(localRect1.toShortString());
          localStringBuilder.append(" | Bounds:");
          localStringBuilder.append(localRect2.toShortString());
          throw new IllegalArgumentException(localStringBuilder.toString());
        }
      }
      else
        localRect1.set(localRect2);
      releaseTempRect(localRect2);
      if (localRect1.isEmpty())
      {
        releaseTempRect(localRect1);
        return;
      }
      int i = GravityCompat.getAbsoluteGravity(localLayoutParams.dodgeInsetEdges, paramInt);
      if ((i & 0x30) == 48)
      {
        int i2 = localRect1.top - localLayoutParams.topMargin - localLayoutParams.mInsetOffsetY;
        if (i2 < paramRect.top)
        {
          setInsetOffsetY(paramView, paramRect.top - i2);
          j = 1;
          break label257;
        }
      }
      int j = 0;
      label257: if ((i & 0x50) == 80)
      {
        int i1 = getHeight() - localRect1.bottom - localLayoutParams.bottomMargin + localLayoutParams.mInsetOffsetY;
        if (i1 < paramRect.bottom)
        {
          setInsetOffsetY(paramView, i1 - paramRect.bottom);
          j = 1;
        }
      }
      if (j == 0)
        setInsetOffsetY(paramView, 0);
      if ((i & 0x3) == 3)
      {
        int n = localRect1.left - localLayoutParams.leftMargin - localLayoutParams.mInsetOffsetX;
        if (n < paramRect.left)
        {
          setInsetOffsetX(paramView, paramRect.left - n);
          k = 1;
          break label383;
        }
      }
      int k = 0;
      label383: if ((i & 0x5) == 5)
      {
        int m = getWidth() - localRect1.right - localLayoutParams.rightMargin + localLayoutParams.mInsetOffsetX;
        if (m < paramRect.right)
        {
          setInsetOffsetX(paramView, m - paramRect.right);
          k = 1;
        }
      }
      if (k == 0)
        setInsetOffsetX(paramView, 0);
      releaseTempRect(localRect1);
      return;
    }
  }

  static Behavior parseBehavior(Context paramContext, AttributeSet paramAttributeSet, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    if (paramString.startsWith("."))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramContext.getPackageName());
      localStringBuilder1.append(paramString);
      paramString = localStringBuilder1.toString();
    }
    else if ((paramString.indexOf('.') < 0) && (!TextUtils.isEmpty(WIDGET_PACKAGE_NAME)))
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(WIDGET_PACKAGE_NAME);
      localStringBuilder3.append('.');
      localStringBuilder3.append(paramString);
      paramString = localStringBuilder3.toString();
    }
    try
    {
      Object localObject = (Map)sConstructors.get();
      if (localObject == null)
      {
        localObject = new HashMap();
        sConstructors.set(localObject);
      }
      Constructor localConstructor = (Constructor)((Map)localObject).get(paramString);
      if (localConstructor == null)
      {
        localConstructor = paramContext.getClassLoader().loadClass(paramString).getConstructor(CONSTRUCTOR_PARAMS);
        localConstructor.setAccessible(true);
        ((Map)localObject).put(paramString, localConstructor);
      }
      Behavior localBehavior = (Behavior)localConstructor.newInstance(new Object[] { paramContext, paramAttributeSet });
      return localBehavior;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Could not inflate Behavior subclass ");
      localStringBuilder2.append(paramString);
      throw new RuntimeException(localStringBuilder2.toString(), localException);
    }
  }

  private boolean performIntercept(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = paramMotionEvent.getActionMasked();
    List localList = this.mTempList1;
    getTopSortedChildren(localList);
    int j = localList.size();
    MotionEvent localMotionEvent = null;
    int k = 0;
    boolean bool1 = false;
    int m = 0;
    while (k < j)
    {
      View localView = (View)localList.get(k);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      Behavior localBehavior = localLayoutParams.getBehavior();
      if (((bool1) || (m != 0)) && (i != 0))
      {
        if (localBehavior == null)
          break label292;
        if (localMotionEvent == null)
        {
          long l = SystemClock.uptimeMillis();
          localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        }
      }
      switch (paramInt)
      {
      default:
        break;
      case 1:
        localBehavior.onTouchEvent(this, localView, localMotionEvent);
        break;
      case 0:
        localBehavior.onInterceptTouchEvent(this, localView, localMotionEvent);
        break;
        if ((!bool1) && (localBehavior != null))
        {
          switch (paramInt)
          {
          default:
            break;
          case 1:
            bool1 = localBehavior.onTouchEvent(this, localView, paramMotionEvent);
            break;
          case 0:
            bool1 = localBehavior.onInterceptTouchEvent(this, localView, paramMotionEvent);
          }
          if (bool1)
            this.mBehaviorTouchView = localView;
        }
        boolean bool2 = localLayoutParams.didBlockInteraction();
        boolean bool3 = localLayoutParams.isBlockingInteractionBelow(this, localView);
        if ((bool3) && (!bool2))
          m = 1;
        else
          m = 0;
        if ((bool3) && (m == 0))
          break label298;
      }
      label292: k++;
    }
    label298: localList.clear();
    return bool1;
  }

  private void prepareChildren()
  {
    this.mDependencySortedChildren.clear();
    this.mChildDag.clear();
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView1 = getChildAt(j);
      LayoutParams localLayoutParams = getResolvedLayoutParams(localView1);
      localLayoutParams.findAnchorView(this, localView1);
      this.mChildDag.addNode(localView1);
      for (int k = 0; k < i; k++)
        if (k != j)
        {
          View localView2 = getChildAt(k);
          if (localLayoutParams.dependsOn(this, localView1, localView2))
          {
            if (!this.mChildDag.contains(localView2))
              this.mChildDag.addNode(localView2);
            this.mChildDag.addEdge(localView2, localView1);
          }
        }
    }
    this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
    Collections.reverse(this.mDependencySortedChildren);
  }

  private static void releaseTempRect(@NonNull Rect paramRect)
  {
    paramRect.setEmpty();
    sRectPool.release(paramRect);
  }

  private void resetTouchBehaviors(boolean paramBoolean)
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
      if (localBehavior != null)
      {
        long l = SystemClock.uptimeMillis();
        MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        if (paramBoolean)
          localBehavior.onInterceptTouchEvent(this, localView, localMotionEvent);
        else
          localBehavior.onTouchEvent(this, localView, localMotionEvent);
        localMotionEvent.recycle();
      }
    }
    for (int k = 0; k < i; k++)
      ((LayoutParams)getChildAt(k).getLayoutParams()).resetTouchBehaviorTracking();
    this.mBehaviorTouchView = null;
    this.mDisallowInterceptReset = false;
  }

  private static int resolveAnchoredChildGravity(int paramInt)
  {
    if (paramInt == 0)
      paramInt = 17;
    return paramInt;
  }

  private static int resolveGravity(int paramInt)
  {
    if ((paramInt & 0x7) == 0)
      paramInt |= 8388611;
    if ((paramInt & 0x70) == 0)
      paramInt |= 48;
    return paramInt;
  }

  private static int resolveKeylineGravity(int paramInt)
  {
    if (paramInt == 0)
      paramInt = 8388661;
    return paramInt;
  }

  private void setInsetOffsetX(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.mInsetOffsetX != paramInt)
    {
      ViewCompat.offsetLeftAndRight(paramView, paramInt - localLayoutParams.mInsetOffsetX);
      localLayoutParams.mInsetOffsetX = paramInt;
    }
  }

  private void setInsetOffsetY(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.mInsetOffsetY != paramInt)
    {
      ViewCompat.offsetTopAndBottom(paramView, paramInt - localLayoutParams.mInsetOffsetY);
      localLayoutParams.mInsetOffsetY = paramInt;
    }
  }

  private void setupForInsets()
  {
    if (Build.VERSION.SDK_INT < 21)
      return;
    if (ViewCompat.getFitsSystemWindows(this))
    {
      if (this.mApplyWindowInsetsListener == null)
        this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener()
        {
          public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
          {
            return CoordinatorLayout.this.setWindowInsets(paramAnonymousWindowInsetsCompat);
          }
        };
      ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
      setSystemUiVisibility(1280);
    }
    else
    {
      ViewCompat.setOnApplyWindowInsetsListener(this, null);
    }
  }

  void addPreDrawListener()
  {
    if (this.mIsAttachedToWindow)
    {
      if (this.mOnPreDrawListener == null)
        this.mOnPreDrawListener = new OnPreDrawListener();
      getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    }
    this.mNeedsPreDrawListener = true;
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

  public void dispatchDependentViewsChanged(@NonNull View paramView)
  {
    List localList = this.mChildDag.getIncomingEdges(paramView);
    if ((localList != null) && (!localList.isEmpty()))
      for (int i = 0; i < localList.size(); i++)
      {
        View localView = (View)localList.get(i);
        Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
        if (localBehavior != null)
          localBehavior.onDependentViewChanged(this, localView, paramView);
      }
  }

  public boolean doViewsOverlap(@NonNull View paramView1, @NonNull View paramView2)
  {
    if ((paramView1.getVisibility() == 0) && (paramView2.getVisibility() == 0))
    {
      Rect localRect1 = acquireTempRect();
      boolean bool1;
      if (paramView1.getParent() != this)
        bool1 = true;
      else
        bool1 = false;
      getChildRect(paramView1, bool1, localRect1);
      Rect localRect2 = acquireTempRect();
      boolean bool2;
      if (paramView2.getParent() != this)
        bool2 = true;
      else
        bool2 = false;
      getChildRect(paramView2, bool2, localRect2);
      try
      {
        int i = localRect1.left;
        int j = localRect2.right;
        boolean bool3 = false;
        if (i <= j)
        {
          int k = localRect1.top;
          int m = localRect2.bottom;
          bool3 = false;
          if (k <= m)
          {
            int n = localRect1.right;
            int i1 = localRect2.left;
            bool3 = false;
            if (n >= i1)
            {
              int i2 = localRect1.bottom;
              int i3 = localRect2.top;
              bool3 = false;
              if (i2 >= i3)
                bool3 = true;
            }
          }
        }
        return bool3;
      }
      finally
      {
        releaseTempRect(localRect1);
        releaseTempRect(localRect2);
      }
    }
    return false;
  }

  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.mBehavior != null)
    {
      float f = localLayoutParams.mBehavior.getScrimOpacity(this, paramView);
      if (f > 0.0F)
      {
        if (this.mScrimPaint == null)
          this.mScrimPaint = new Paint();
        this.mScrimPaint.setColor(localLayoutParams.mBehavior.getScrimColor(this, paramView));
        this.mScrimPaint.setAlpha(clamp(Math.round(f * 255.0F), 0, 255));
        int i = paramCanvas.save();
        if (paramView.isOpaque())
          paramCanvas.clipRect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom(), Region.Op.DIFFERENCE);
        paramCanvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
        paramCanvas.restoreToCount(i);
      }
    }
    return super.drawChild(paramCanvas, paramView, paramLong);
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    Drawable localDrawable = this.mStatusBarBackground;
    boolean bool1 = false;
    if (localDrawable != null)
    {
      boolean bool2 = localDrawable.isStateful();
      bool1 = false;
      if (bool2)
        bool1 = false | localDrawable.setState(arrayOfInt);
    }
    if (bool1)
      invalidate();
  }

  void ensurePreDrawListener()
  {
    int i = getChildCount();
    int k;
    for (int j = 0; ; j++)
    {
      k = 0;
      if (j >= i)
        break;
      if (hasDependencies(getChildAt(j)))
      {
        k = 1;
        break;
      }
    }
    if (k != this.mNeedsPreDrawListener)
      if (k != 0)
        addPreDrawListener();
      else
        removePreDrawListener();
  }

  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }

  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams))
      return new LayoutParams((LayoutParams)paramLayoutParams);
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    return new LayoutParams(paramLayoutParams);
  }

  void getChildRect(View paramView, boolean paramBoolean, Rect paramRect)
  {
    if ((!paramView.isLayoutRequested()) && (paramView.getVisibility() != 8))
    {
      if (paramBoolean)
        getDescendantRect(paramView, paramRect);
      else
        paramRect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
      return;
    }
    paramRect.setEmpty();
  }

  @NonNull
  public List<View> getDependencies(@NonNull View paramView)
  {
    List localList = this.mChildDag.getOutgoingEdges(paramView);
    this.mTempDependenciesList.clear();
    if (localList != null)
      this.mTempDependenciesList.addAll(localList);
    return this.mTempDependenciesList;
  }

  @VisibleForTesting
  final List<View> getDependencySortedChildren()
  {
    prepareChildren();
    return Collections.unmodifiableList(this.mDependencySortedChildren);
  }

  @NonNull
  public List<View> getDependents(@NonNull View paramView)
  {
    List localList = this.mChildDag.getIncomingEdges(paramView);
    this.mTempDependenciesList.clear();
    if (localList != null)
      this.mTempDependenciesList.addAll(localList);
    return this.mTempDependenciesList;
  }

  void getDescendantRect(View paramView, Rect paramRect)
  {
    ViewGroupUtils.getDescendantRect(this, paramView, paramRect);
  }

  void getDesiredAnchoredChildRect(View paramView, int paramInt, Rect paramRect1, Rect paramRect2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    getDesiredAnchoredChildRectWithoutConstraints(paramView, paramInt, paramRect1, paramRect2, localLayoutParams, i, j);
    constrainChildRect(localLayoutParams, paramRect2, i, j);
  }

  void getLastChildRect(View paramView, Rect paramRect)
  {
    paramRect.set(((LayoutParams)paramView.getLayoutParams()).getLastChildRect());
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public final WindowInsetsCompat getLastWindowInsets()
  {
    return this.mLastInsets;
  }

  public int getNestedScrollAxes()
  {
    return this.mNestedScrollingParentHelper.getNestedScrollAxes();
  }

  LayoutParams getResolvedLayoutParams(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!localLayoutParams.mBehaviorResolved)
      if ((paramView instanceof AttachedBehavior))
      {
        Behavior localBehavior = ((AttachedBehavior)paramView).getBehavior();
        if (localBehavior == null)
          Log.e("CoordinatorLayout", "Attached behavior class is null");
        localLayoutParams.setBehavior(localBehavior);
        localLayoutParams.mBehaviorResolved = true;
      }
      else
      {
        Class localClass = paramView.getClass();
        DefaultBehavior localDefaultBehavior = null;
        while (localClass != null)
        {
          localDefaultBehavior = (DefaultBehavior)localClass.getAnnotation(DefaultBehavior.class);
          if (localDefaultBehavior != null)
            break;
          localClass = localClass.getSuperclass();
        }
        if (localDefaultBehavior != null)
          try
          {
            localLayoutParams.setBehavior((Behavior)localDefaultBehavior.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
          }
          catch (Exception localException)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Default behavior class ");
            localStringBuilder.append(localDefaultBehavior.value().getName());
            localStringBuilder.append(" could not be instantiated. Did you forget");
            localStringBuilder.append(" a default constructor?");
            Log.e("CoordinatorLayout", localStringBuilder.toString(), localException);
          }
        localLayoutParams.mBehaviorResolved = true;
      }
    return localLayoutParams;
  }

  @Nullable
  public Drawable getStatusBarBackground()
  {
    return this.mStatusBarBackground;
  }

  protected int getSuggestedMinimumHeight()
  {
    return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
  }

  protected int getSuggestedMinimumWidth()
  {
    return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
  }

  public boolean isPointInChildBounds(@NonNull View paramView, int paramInt1, int paramInt2)
  {
    Rect localRect = acquireTempRect();
    getDescendantRect(paramView, localRect);
    try
    {
      boolean bool = localRect.contains(paramInt1, paramInt2);
      return bool;
    }
    finally
    {
      releaseTempRect(localRect);
    }
  }

  void offsetChildToAnchor(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.mAnchorView != null)
    {
      Rect localRect1 = acquireTempRect();
      Rect localRect2 = acquireTempRect();
      Rect localRect3 = acquireTempRect();
      getDescendantRect(localLayoutParams.mAnchorView, localRect1);
      getChildRect(paramView, false, localRect2);
      int i = paramView.getMeasuredWidth();
      int j = paramView.getMeasuredHeight();
      getDesiredAnchoredChildRectWithoutConstraints(paramView, paramInt, localRect1, localRect3, localLayoutParams, i, j);
      int k;
      if (localRect3.left == localRect2.left)
      {
        int i1 = localRect3.top;
        int i2 = localRect2.top;
        k = 0;
        if (i1 == i2);
      }
      else
      {
        k = 1;
      }
      constrainChildRect(localLayoutParams, localRect3, i, j);
      int m = localRect3.left - localRect2.left;
      int n = localRect3.top - localRect2.top;
      if (m != 0)
        ViewCompat.offsetLeftAndRight(paramView, m);
      if (n != 0)
        ViewCompat.offsetTopAndBottom(paramView, n);
      if (k != 0)
      {
        Behavior localBehavior = localLayoutParams.getBehavior();
        if (localBehavior != null)
          localBehavior.onDependentViewChanged(this, paramView, localLayoutParams.mAnchorView);
      }
      releaseTempRect(localRect1);
      releaseTempRect(localRect2);
      releaseTempRect(localRect3);
    }
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    resetTouchBehaviors(false);
    if (this.mNeedsPreDrawListener)
    {
      if (this.mOnPreDrawListener == null)
        this.mOnPreDrawListener = new OnPreDrawListener();
      getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    }
    if ((this.mLastInsets == null) && (ViewCompat.getFitsSystemWindows(this)))
      ViewCompat.requestApplyInsets(this);
    this.mIsAttachedToWindow = true;
  }

  final void onChildViewsChanged(int paramInt)
  {
    int i = ViewCompat.getLayoutDirection(this);
    int j = this.mDependencySortedChildren.size();
    Rect localRect1 = acquireTempRect();
    Rect localRect2 = acquireTempRect();
    Rect localRect3 = acquireTempRect();
    for (int k = 0; k < j; k++)
    {
      View localView1 = (View)this.mDependencySortedChildren.get(k);
      LayoutParams localLayoutParams1 = (LayoutParams)localView1.getLayoutParams();
      if ((paramInt != 0) || (localView1.getVisibility() != 8))
      {
        for (int m = 0; m < k; m++)
        {
          View localView3 = (View)this.mDependencySortedChildren.get(m);
          if (localLayoutParams1.mAnchorDirectChild == localView3)
            offsetChildToAnchor(localView1, i);
        }
        getChildRect(localView1, true, localRect2);
        if ((localLayoutParams1.insetEdge != 0) && (!localRect2.isEmpty()))
        {
          int i1 = GravityCompat.getAbsoluteGravity(localLayoutParams1.insetEdge, i);
          int i2 = i1 & 0x70;
          if (i2 != 48)
          {
            if (i2 == 80)
              localRect1.bottom = Math.max(localRect1.bottom, getHeight() - localRect2.top);
          }
          else
            localRect1.top = Math.max(localRect1.top, localRect2.bottom);
          int i3 = i1 & 0x7;
          if (i3 != 3)
          {
            if (i3 == 5)
              localRect1.right = Math.max(localRect1.right, getWidth() - localRect2.left);
          }
          else
            localRect1.left = Math.max(localRect1.left, localRect2.right);
        }
        if ((localLayoutParams1.dodgeInsetEdges != 0) && (localView1.getVisibility() == 0))
          offsetChildByInset(localView1, localRect1, i);
        if (paramInt != 2)
        {
          getLastChildRect(localView1, localRect3);
          if (!localRect3.equals(localRect2))
            recordLastChildRect(localView1, localRect2);
        }
        else
        {
          for (int n = k + 1; n < j; n++)
          {
            View localView2 = (View)this.mDependencySortedChildren.get(n);
            LayoutParams localLayoutParams2 = (LayoutParams)localView2.getLayoutParams();
            Behavior localBehavior = localLayoutParams2.getBehavior();
            if ((localBehavior != null) && (localBehavior.layoutDependsOn(this, localView2, localView1)))
              if ((paramInt == 0) && (localLayoutParams2.getChangedAfterNestedScroll()))
              {
                localLayoutParams2.resetChangedAfterNestedScroll();
              }
              else
              {
                boolean bool;
                if (paramInt != 2)
                {
                  bool = localBehavior.onDependentViewChanged(this, localView2, localView1);
                }
                else
                {
                  localBehavior.onDependentViewRemoved(this, localView2, localView1);
                  bool = true;
                }
                if (paramInt == 1)
                  localLayoutParams2.setChangedAfterNestedScroll(bool);
              }
          }
        }
      }
    }
    releaseTempRect(localRect1);
    releaseTempRect(localRect2);
    releaseTempRect(localRect3);
  }

  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    resetTouchBehaviors(false);
    if ((this.mNeedsPreDrawListener) && (this.mOnPreDrawListener != null))
      getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
    if (this.mNestedScrollingTarget != null)
      onStopNestedScroll(this.mNestedScrollingTarget);
    this.mIsAttachedToWindow = false;
  }

  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.mDrawStatusBarBackground) && (this.mStatusBarBackground != null))
    {
      int i;
      if (this.mLastInsets != null)
        i = this.mLastInsets.getSystemWindowInsetTop();
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
    if (i == 0)
      resetTouchBehaviors(true);
    boolean bool = performIntercept(paramMotionEvent, 0);
    if ((i == 1) || (i == 3))
      resetTouchBehaviors(true);
    return bool;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = ViewCompat.getLayoutDirection(this);
    int j = this.mDependencySortedChildren.size();
    for (int k = 0; k < j; k++)
    {
      View localView = (View)this.mDependencySortedChildren.get(k);
      if (localView.getVisibility() != 8)
      {
        Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
        if ((localBehavior == null) || (!localBehavior.onLayoutChild(this, localView, i)))
          onLayoutChild(localView, i);
      }
    }
  }

  public void onLayoutChild(@NonNull View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.checkAnchorChanged())
      throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
    if (localLayoutParams.mAnchorView != null)
      layoutChildWithAnchor(paramView, localLayoutParams.mAnchorView, paramInt);
    else if (localLayoutParams.keyline >= 0)
      layoutChildWithKeyline(paramView, localLayoutParams.keyline, paramInt);
    else
      layoutChild(paramView, paramInt);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    prepareChildren();
    ensurePreDrawListener();
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = getPaddingRight();
    int m = getPaddingBottom();
    int n = ViewCompat.getLayoutDirection(this);
    int i1;
    if (n == 1)
      i1 = 1;
    else
      i1 = 0;
    int i2 = View.MeasureSpec.getMode(paramInt1);
    int i3 = View.MeasureSpec.getSize(paramInt1);
    int i4 = View.MeasureSpec.getMode(paramInt2);
    int i5 = View.MeasureSpec.getSize(paramInt2);
    int i6 = i + k;
    int i7 = j + m;
    int i8 = getSuggestedMinimumWidth();
    int i9 = getSuggestedMinimumHeight();
    int i10;
    if ((this.mLastInsets != null) && (ViewCompat.getFitsSystemWindows(this)))
      i10 = 1;
    else
      i10 = 0;
    int i11 = this.mDependencySortedChildren.size();
    int i12 = i8;
    int i13 = i9;
    int i14 = 0;
    int i15 = 0;
    while (i15 < i11)
    {
      View localView1 = (View)this.mDependencySortedChildren.get(i15);
      int i27;
      int i24;
      if (localView1.getVisibility() == 8)
      {
        i27 = i15;
        i24 = i11;
      }
      else
      {
        LayoutParams localLayoutParams = (LayoutParams)localView1.getLayoutParams();
        int i18;
        int i19;
        if ((localLayoutParams.keyline >= 0) && (i2 != 0))
        {
          int i39 = getKeyline(localLayoutParams.keyline);
          i18 = i13;
          int i40 = 0x7 & GravityCompat.getAbsoluteGravity(resolveKeylineGravity(localLayoutParams.gravity), n);
          i19 = i14;
          if (((i40 == 3) && (i1 == 0)) || ((i40 == 5) && (i1 != 0)))
          {
            i20 = Math.max(0, i3 - k - i39);
            break label338;
          }
          if (((i40 == 5) && (i1 == 0)) || ((i40 == 3) && (i1 != 0)))
          {
            i20 = Math.max(0, i39 - i);
            break label338;
          }
        }
        else
        {
          i18 = i13;
          i19 = i14;
        }
        int i20 = 0;
        label338: int i21;
        int i22;
        if ((i10 != 0) && (!ViewCompat.getFitsSystemWindows(localView1)))
        {
          int i35 = this.mLastInsets.getSystemWindowInsetLeft() + this.mLastInsets.getSystemWindowInsetRight();
          int i36 = this.mLastInsets.getSystemWindowInsetTop() + this.mLastInsets.getSystemWindowInsetBottom();
          int i37 = View.MeasureSpec.makeMeasureSpec(i3 - i35, i2);
          int i38 = View.MeasureSpec.makeMeasureSpec(i5 - i36, i4);
          i21 = i37;
          i22 = i38;
        }
        else
        {
          i21 = paramInt1;
          i22 = paramInt2;
        }
        Behavior localBehavior = localLayoutParams.getBehavior();
        int i25;
        View localView2;
        int i26;
        int i23;
        if (localBehavior != null)
        {
          i25 = i18;
          localView2 = localView1;
          i26 = i19;
          int i32 = i21;
          i23 = i12;
          int i33 = i20;
          i27 = i15;
          int i34 = i22;
          i24 = i11;
          if (!localBehavior.onMeasureChild(this, localView1, i32, i33, i34, 0))
            break label526;
        }
        View localView3;
        while (true)
        {
          localView3 = localView2;
          break;
          localView2 = localView1;
          i23 = i12;
          i24 = i11;
          i25 = i18;
          i26 = i19;
          i27 = i15;
          label526: onMeasureChild(localView2, i21, i20, i22, 0);
        }
        int i28 = i6 + localView3.getMeasuredWidth() + localLayoutParams.leftMargin + localLayoutParams.rightMargin;
        int i29 = Math.max(i23, i28);
        int i30 = i7 + localView3.getMeasuredHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
        i13 = Math.max(i25, i30);
        int i31 = localView3.getMeasuredState();
        i14 = View.combineMeasuredStates(i26, i31);
        i12 = i29;
      }
      i15 = i27 + 1;
      i11 = i24;
    }
    int i16 = i14;
    int i17 = i13;
    setMeasuredDimension(View.resolveSizeAndState(i12, paramInt1, 0xFF000000 & i16), View.resolveSizeAndState(i17, paramInt2, i16 << 16));
  }

  public void onMeasureChild(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    measureChildWithMargins(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    int i = getChildCount();
    int j = 0;
    boolean bool = false;
    while (j < i)
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isNestedScrollAccepted(0))
        {
          Behavior localBehavior = localLayoutParams.getBehavior();
          if (localBehavior != null)
            bool |= localBehavior.onNestedFling(this, localView, paramView, paramFloat1, paramFloat2, paramBoolean);
        }
      }
      j++;
    }
    if (bool)
      onChildViewsChanged(1);
    return bool;
  }

  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    int i = getChildCount();
    int j = 0;
    boolean bool = false;
    while (j < i)
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isNestedScrollAccepted(0))
        {
          Behavior localBehavior = localLayoutParams.getBehavior();
          if (localBehavior != null)
            bool |= localBehavior.onNestedPreFling(this, localView, paramView, paramFloat1, paramFloat2);
        }
      }
      j++;
    }
    return bool;
  }

  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt, 0);
  }

  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    int i = getChildCount();
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    while (k < i)
    {
      View localView = getChildAt(k);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isNestedScrollAccepted(paramInt3))
        {
          Behavior localBehavior = localLayoutParams.getBehavior();
          if (localBehavior != null)
          {
            int[] arrayOfInt = this.mTempIntPair;
            this.mTempIntPair[1] = 0;
            arrayOfInt[0] = 0;
            localBehavior.onNestedPreScroll(this, localView, paramView, paramInt1, paramInt2, this.mTempIntPair, paramInt3);
            int i1;
            if (paramInt1 > 0)
              i1 = Math.max(m, this.mTempIntPair[0]);
            else
              i1 = Math.min(m, this.mTempIntPair[0]);
            int i2;
            if (paramInt2 > 0)
              i2 = Math.max(n, this.mTempIntPair[1]);
            else
              i2 = Math.min(n, this.mTempIntPair[1]);
            m = i1;
            n = i2;
            j = 1;
          }
        }
      }
      k++;
    }
    paramArrayOfInt[0] = m;
    paramArrayOfInt[1] = n;
    if (j != 0)
      onChildViewsChanged(1);
  }

  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }

  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = getChildCount();
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      View localView = getChildAt(k);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isNestedScrollAccepted(paramInt5))
        {
          Behavior localBehavior = localLayoutParams.getBehavior();
          if (localBehavior != null)
          {
            localBehavior.onNestedScroll(this, localView, paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
            j = 1;
          }
        }
      }
    }
    if (j != 0)
      onChildViewsChanged(1);
  }

  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }

  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    this.mNestedScrollingParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    this.mNestedScrollingTarget = paramView2;
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (localLayoutParams.isNestedScrollAccepted(paramInt2))
      {
        Behavior localBehavior = localLayoutParams.getBehavior();
        if (localBehavior != null)
          localBehavior.onNestedScrollAccepted(this, localView, paramView1, paramView2, paramInt1, paramInt2);
      }
    }
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
    SparseArray localSparseArray = localSavedState.behaviorStates;
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      View localView = getChildAt(i);
      int k = localView.getId();
      Behavior localBehavior = getResolvedLayoutParams(localView).getBehavior();
      if ((k != -1) && (localBehavior != null))
      {
        Parcelable localParcelable = (Parcelable)localSparseArray.get(k);
        if (localParcelable != null)
          localBehavior.onRestoreInstanceState(this, localView, localParcelable);
      }
      i++;
    }
  }

  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    SparseArray localSparseArray = new SparseArray();
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      int k = localView.getId();
      Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
      if ((k != -1) && (localBehavior != null))
      {
        Parcelable localParcelable = localBehavior.onSaveInstanceState(this, localView);
        if (localParcelable != null)
          localSparseArray.append(k, localParcelable);
      }
    }
    localSavedState.behaviorStates = localSparseArray;
    return localSavedState;
  }

  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    return onStartNestedScroll(paramView1, paramView2, paramInt, 0);
  }

  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    int j = 0;
    boolean bool1 = false;
    while (j < i)
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        Behavior localBehavior = localLayoutParams.getBehavior();
        if (localBehavior != null)
        {
          boolean bool2 = localBehavior.onStartNestedScroll(this, localView, paramView1, paramView2, paramInt1, paramInt2);
          boolean bool3 = bool1 | bool2;
          localLayoutParams.setNestedScrollAccepted(paramInt2, bool2);
          bool1 = bool3;
        }
        else
        {
          localLayoutParams.setNestedScrollAccepted(paramInt2, false);
        }
      }
      j++;
    }
    return bool1;
  }

  public void onStopNestedScroll(View paramView)
  {
    onStopNestedScroll(paramView, 0);
  }

  public void onStopNestedScroll(View paramView, int paramInt)
  {
    this.mNestedScrollingParentHelper.onStopNestedScroll(paramView, paramInt);
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (localLayoutParams.isNestedScrollAccepted(paramInt))
      {
        Behavior localBehavior = localLayoutParams.getBehavior();
        if (localBehavior != null)
          localBehavior.onStopNestedScroll(this, localView, paramView, paramInt);
        localLayoutParams.resetNestedScroll(paramInt);
        localLayoutParams.resetChangedAfterNestedScroll();
      }
    }
    this.mNestedScrollingTarget = null;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    boolean bool1;
    if (this.mBehaviorTouchView == null)
    {
      bool1 = performIntercept(paramMotionEvent, 1);
      if (bool1)
        break label34;
    }
    label34: Behavior localBehavior;
    do
    {
      bool2 = false;
      break;
      bool1 = false;
      localBehavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
    }
    while (localBehavior == null);
    boolean bool2 = localBehavior.onTouchEvent(this, this.mBehaviorTouchView, paramMotionEvent);
    MotionEvent localMotionEvent;
    if (this.mBehaviorTouchView == null)
    {
      bool2 |= super.onTouchEvent(paramMotionEvent);
      localMotionEvent = null;
    }
    else
    {
      localMotionEvent = null;
      if (bool1)
      {
        long l = SystemClock.uptimeMillis();
        localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        super.onTouchEvent(localMotionEvent);
      }
    }
    if (localMotionEvent != null)
      localMotionEvent.recycle();
    if ((i == 1) || (i == 3))
      resetTouchBehaviors(false);
    return bool2;
  }

  void recordLastChildRect(View paramView, Rect paramRect)
  {
    ((LayoutParams)paramView.getLayoutParams()).setLastChildRect(paramRect);
  }

  void removePreDrawListener()
  {
    if ((this.mIsAttachedToWindow) && (this.mOnPreDrawListener != null))
      getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
    this.mNeedsPreDrawListener = false;
  }

  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    Behavior localBehavior = ((LayoutParams)paramView.getLayoutParams()).getBehavior();
    if ((localBehavior != null) && (localBehavior.onRequestChildRectangleOnScreen(this, paramView, paramRect, paramBoolean)))
      return true;
    return super.requestChildRectangleOnScreen(paramView, paramRect, paramBoolean);
  }

  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    if ((paramBoolean) && (!this.mDisallowInterceptReset))
    {
      resetTouchBehaviors(false);
      this.mDisallowInterceptReset = true;
    }
  }

  public void setFitsSystemWindows(boolean paramBoolean)
  {
    super.setFitsSystemWindows(paramBoolean);
    setupForInsets();
  }

  public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener paramOnHierarchyChangeListener)
  {
    this.mOnHierarchyChangeListener = paramOnHierarchyChangeListener;
  }

  public void setStatusBarBackground(@Nullable Drawable paramDrawable)
  {
    if (this.mStatusBarBackground != paramDrawable)
    {
      if (this.mStatusBarBackground != null)
        this.mStatusBarBackground.setCallback(null);
      Drawable localDrawable1 = null;
      if (paramDrawable != null)
        localDrawable1 = paramDrawable.mutate();
      this.mStatusBarBackground = localDrawable1;
      if (this.mStatusBarBackground != null)
      {
        if (this.mStatusBarBackground.isStateful())
          this.mStatusBarBackground.setState(getDrawableState());
        DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
        Drawable localDrawable2 = this.mStatusBarBackground;
        boolean bool;
        if (getVisibility() == 0)
          bool = true;
        else
          bool = false;
        localDrawable2.setVisible(bool, false);
        this.mStatusBarBackground.setCallback(this);
      }
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }

  public void setStatusBarBackgroundColor(@ColorInt int paramInt)
  {
    setStatusBarBackground(new ColorDrawable(paramInt));
  }

  public void setStatusBarBackgroundResource(@DrawableRes int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0)
      localDrawable = ContextCompat.getDrawable(getContext(), paramInt);
    else
      localDrawable = null;
    setStatusBarBackground(localDrawable);
  }

  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    boolean bool;
    if (paramInt == 0)
      bool = true;
    else
      bool = false;
    if ((this.mStatusBarBackground != null) && (this.mStatusBarBackground.isVisible() != bool))
      this.mStatusBarBackground.setVisible(bool, false);
  }

  final WindowInsetsCompat setWindowInsets(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (!ObjectsCompat.equals(this.mLastInsets, paramWindowInsetsCompat))
    {
      this.mLastInsets = paramWindowInsetsCompat;
      boolean bool1;
      if ((paramWindowInsetsCompat != null) && (paramWindowInsetsCompat.getSystemWindowInsetTop() > 0))
        bool1 = true;
      else
        bool1 = false;
      this.mDrawStatusBarBackground = bool1;
      boolean bool2 = this.mDrawStatusBarBackground;
      boolean bool3 = false;
      if (!bool2)
      {
        Drawable localDrawable = getBackground();
        bool3 = false;
        if (localDrawable == null)
          bool3 = true;
      }
      setWillNotDraw(bool3);
      paramWindowInsetsCompat = dispatchApplyWindowInsetsToBehaviors(paramWindowInsetsCompat);
      requestLayout();
    }
    return paramWindowInsetsCompat;
  }

  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    boolean bool;
    if ((!super.verifyDrawable(paramDrawable)) && (paramDrawable != this.mStatusBarBackground))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public static abstract interface AttachedBehavior
  {
    @NonNull
    public abstract CoordinatorLayout.Behavior getBehavior();
  }

  public static abstract class Behavior<V extends View>
  {
    public Behavior()
    {
    }

    public Behavior(Context paramContext, AttributeSet paramAttributeSet)
    {
    }

    @Nullable
    public static Object getTag(@NonNull View paramView)
    {
      return ((CoordinatorLayout.LayoutParams)paramView.getLayoutParams()).mBehaviorTag;
    }

    public static void setTag(@NonNull View paramView, @Nullable Object paramObject)
    {
      ((CoordinatorLayout.LayoutParams)paramView.getLayoutParams()).mBehaviorTag = paramObject;
    }

    public boolean blocksInteractionBelow(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV)
    {
      boolean bool;
      if (getScrimOpacity(paramCoordinatorLayout, paramV) > 0.0F)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public boolean getInsetDodgeRect(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull Rect paramRect)
    {
      return false;
    }

    @ColorInt
    public int getScrimColor(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV)
    {
      return -16777216;
    }

    @FloatRange(from=0.0D, to=1.0D)
    public float getScrimOpacity(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV)
    {
      return 0.0F;
    }

    public boolean layoutDependsOn(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView)
    {
      return false;
    }

    @NonNull
    public WindowInsetsCompat onApplyWindowInsets(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull WindowInsetsCompat paramWindowInsetsCompat)
    {
      return paramWindowInsetsCompat;
    }

    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams paramLayoutParams)
    {
    }

    public boolean onDependentViewChanged(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView)
    {
      return false;
    }

    public void onDependentViewRemoved(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView)
    {
    }

    public void onDetachedFromLayoutParams()
    {
    }

    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull MotionEvent paramMotionEvent)
    {
      return false;
    }

    public boolean onLayoutChild(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, int paramInt)
    {
      return false;
    }

    public boolean onMeasureChild(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return false;
    }

    public boolean onNestedFling(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
    {
      return false;
    }

    public boolean onNestedPreFling(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, float paramFloat1, float paramFloat2)
    {
      return false;
    }

    @Deprecated
    public void onNestedPreScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt1, int paramInt2, @NonNull int[] paramArrayOfInt)
    {
    }

    public void onNestedPreScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt1, int paramInt2, @NonNull int[] paramArrayOfInt, int paramInt3)
    {
      if (paramInt3 == 0)
        onNestedPreScroll(paramCoordinatorLayout, paramV, paramView, paramInt1, paramInt2, paramArrayOfInt);
    }

    @Deprecated
    public void onNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
    }

    public void onNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      if (paramInt5 == 0)
        onNestedScroll(paramCoordinatorLayout, paramV, paramView, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Deprecated
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView1, @NonNull View paramView2, int paramInt)
    {
    }

    public void onNestedScrollAccepted(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0)
        onNestedScrollAccepted(paramCoordinatorLayout, paramV, paramView1, paramView2, paramInt1);
    }

    public boolean onRequestChildRectangleOnScreen(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull Rect paramRect, boolean paramBoolean)
    {
      return false;
    }

    public void onRestoreInstanceState(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull Parcelable paramParcelable)
    {
    }

    @Nullable
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV)
    {
      return View.BaseSavedState.EMPTY_STATE;
    }

    @Deprecated
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView1, @NonNull View paramView2, int paramInt)
    {
      return false;
    }

    public boolean onStartNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0)
        return onStartNestedScroll(paramCoordinatorLayout, paramV, paramView1, paramView2, paramInt1);
      return false;
    }

    @Deprecated
    public void onStopNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView)
    {
    }

    public void onStopNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull View paramView, int paramInt)
    {
      if (paramInt == 0)
        onStopNestedScroll(paramCoordinatorLayout, paramV, paramView);
    }

    public boolean onTouchEvent(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull V paramV, @NonNull MotionEvent paramMotionEvent)
    {
      return false;
    }
  }

  @Deprecated
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface DefaultBehavior
  {
    public abstract Class<? extends CoordinatorLayout.Behavior> value();
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DispatchChangeEvent
  {
  }

  private class HierarchyChangeListener
    implements ViewGroup.OnHierarchyChangeListener
  {
    HierarchyChangeListener()
    {
    }

    public void onChildViewAdded(View paramView1, View paramView2)
    {
      if (CoordinatorLayout.this.mOnHierarchyChangeListener != null)
        CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(paramView1, paramView2);
    }

    public void onChildViewRemoved(View paramView1, View paramView2)
    {
      CoordinatorLayout.this.onChildViewsChanged(2);
      if (CoordinatorLayout.this.mOnHierarchyChangeListener != null)
        CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(paramView1, paramView2);
    }
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams
  {
    public int anchorGravity = 0;
    public int dodgeInsetEdges = 0;
    public int gravity = 0;
    public int insetEdge = 0;
    public int keyline = -1;
    View mAnchorDirectChild;
    int mAnchorId = -1;
    View mAnchorView;
    CoordinatorLayout.Behavior mBehavior;
    boolean mBehaviorResolved = false;
    Object mBehaviorTag;
    private boolean mDidAcceptNestedScrollNonTouch;
    private boolean mDidAcceptNestedScrollTouch;
    private boolean mDidBlockInteraction;
    private boolean mDidChangeAfterNestedScroll;
    int mInsetOffsetX;
    int mInsetOffsetY;
    final Rect mLastChildRect = new Rect();

    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    LayoutParams(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CoordinatorLayout_Layout);
      this.gravity = localTypedArray.getInteger(R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
      this.mAnchorId = localTypedArray.getResourceId(R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
      this.anchorGravity = localTypedArray.getInteger(R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
      this.keyline = localTypedArray.getInteger(R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
      this.insetEdge = localTypedArray.getInt(R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
      this.dodgeInsetEdges = localTypedArray.getInt(R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
      this.mBehaviorResolved = localTypedArray.hasValue(R.styleable.CoordinatorLayout_Layout_layout_behavior);
      if (this.mBehaviorResolved)
        this.mBehavior = CoordinatorLayout.parseBehavior(paramContext, paramAttributeSet, localTypedArray.getString(R.styleable.CoordinatorLayout_Layout_layout_behavior));
      localTypedArray.recycle();
      if (this.mBehavior != null)
        this.mBehavior.onAttachedToLayoutParams(this);
    }

    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }

    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }

    private void resolveAnchorView(View paramView, CoordinatorLayout paramCoordinatorLayout)
    {
      this.mAnchorView = paramCoordinatorLayout.findViewById(this.mAnchorId);
      if (this.mAnchorView != null)
      {
        if (this.mAnchorView == paramCoordinatorLayout)
        {
          if (paramCoordinatorLayout.isInEditMode())
          {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return;
          }
          throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
        }
        View localView = this.mAnchorView;
        for (ViewParent localViewParent = this.mAnchorView.getParent(); (localViewParent != paramCoordinatorLayout) && (localViewParent != null); localViewParent = localViewParent.getParent())
        {
          if (localViewParent == paramView)
          {
            if (paramCoordinatorLayout.isInEditMode())
            {
              this.mAnchorDirectChild = null;
              this.mAnchorView = null;
              return;
            }
            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
          }
          if ((localViewParent instanceof View))
            localView = (View)localViewParent;
        }
        this.mAnchorDirectChild = localView;
        return;
      }
      if (paramCoordinatorLayout.isInEditMode())
      {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not find CoordinatorLayout descendant view with id ");
      localStringBuilder.append(paramCoordinatorLayout.getResources().getResourceName(this.mAnchorId));
      localStringBuilder.append(" to anchor view ");
      localStringBuilder.append(paramView);
      throw new IllegalStateException(localStringBuilder.toString());
    }

    private boolean shouldDodge(View paramView, int paramInt)
    {
      int i = GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).insetEdge, paramInt);
      boolean bool;
      if ((i != 0) && ((i & GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, paramInt)) == i))
        bool = true;
      else
        bool = false;
      return bool;
    }

    private boolean verifyAnchorView(View paramView, CoordinatorLayout paramCoordinatorLayout)
    {
      if (this.mAnchorView.getId() != this.mAnchorId)
        return false;
      View localView = this.mAnchorView;
      ViewParent localViewParent = this.mAnchorView.getParent();
      while (localViewParent != paramCoordinatorLayout)
        if ((localViewParent != null) && (localViewParent != paramView))
        {
          if ((localViewParent instanceof View))
            localView = (View)localViewParent;
          localViewParent = localViewParent.getParent();
        }
        else
        {
          this.mAnchorDirectChild = null;
          this.mAnchorView = null;
          return false;
        }
      this.mAnchorDirectChild = localView;
      return true;
    }

    boolean checkAnchorChanged()
    {
      boolean bool;
      if ((this.mAnchorView == null) && (this.mAnchorId != -1))
        bool = true;
      else
        bool = false;
      return bool;
    }

    boolean dependsOn(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      boolean bool;
      if ((paramView2 != this.mAnchorDirectChild) && (!shouldDodge(paramView2, ViewCompat.getLayoutDirection(paramCoordinatorLayout))) && ((this.mBehavior == null) || (!this.mBehavior.layoutDependsOn(paramCoordinatorLayout, paramView1, paramView2))))
        bool = false;
      else
        bool = true;
      return bool;
    }

    boolean didBlockInteraction()
    {
      if (this.mBehavior == null)
        this.mDidBlockInteraction = false;
      return this.mDidBlockInteraction;
    }

    View findAnchorView(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      if (this.mAnchorId == -1)
      {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
        return null;
      }
      if ((this.mAnchorView == null) || (!verifyAnchorView(paramView, paramCoordinatorLayout)))
        resolveAnchorView(paramView, paramCoordinatorLayout);
      return this.mAnchorView;
    }

    @IdRes
    public int getAnchorId()
    {
      return this.mAnchorId;
    }

    @Nullable
    public CoordinatorLayout.Behavior getBehavior()
    {
      return this.mBehavior;
    }

    boolean getChangedAfterNestedScroll()
    {
      return this.mDidChangeAfterNestedScroll;
    }

    Rect getLastChildRect()
    {
      return this.mLastChildRect;
    }

    void invalidateAnchor()
    {
      this.mAnchorDirectChild = null;
      this.mAnchorView = null;
    }

    boolean isBlockingInteractionBelow(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      if (this.mDidBlockInteraction)
        return true;
      boolean bool1 = this.mDidBlockInteraction;
      boolean bool2;
      if (this.mBehavior != null)
        bool2 = this.mBehavior.blocksInteractionBelow(paramCoordinatorLayout, paramView);
      else
        bool2 = false;
      boolean bool3 = bool2 | bool1;
      this.mDidBlockInteraction = bool3;
      return bool3;
    }

    boolean isNestedScrollAccepted(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return false;
      case 1:
        return this.mDidAcceptNestedScrollNonTouch;
      case 0:
      }
      return this.mDidAcceptNestedScrollTouch;
    }

    void resetChangedAfterNestedScroll()
    {
      this.mDidChangeAfterNestedScroll = false;
    }

    void resetNestedScroll(int paramInt)
    {
      setNestedScrollAccepted(paramInt, false);
    }

    void resetTouchBehaviorTracking()
    {
      this.mDidBlockInteraction = false;
    }

    public void setAnchorId(@IdRes int paramInt)
    {
      invalidateAnchor();
      this.mAnchorId = paramInt;
    }

    public void setBehavior(@Nullable CoordinatorLayout.Behavior paramBehavior)
    {
      if (this.mBehavior != paramBehavior)
      {
        if (this.mBehavior != null)
          this.mBehavior.onDetachedFromLayoutParams();
        this.mBehavior = paramBehavior;
        this.mBehaviorTag = null;
        this.mBehaviorResolved = true;
        if (paramBehavior != null)
          paramBehavior.onAttachedToLayoutParams(this);
      }
    }

    void setChangedAfterNestedScroll(boolean paramBoolean)
    {
      this.mDidChangeAfterNestedScroll = paramBoolean;
    }

    void setLastChildRect(Rect paramRect)
    {
      this.mLastChildRect.set(paramRect);
    }

    void setNestedScrollAccepted(int paramInt, boolean paramBoolean)
    {
      switch (paramInt)
      {
      default:
        break;
      case 1:
        this.mDidAcceptNestedScrollNonTouch = paramBoolean;
        break;
      case 0:
        this.mDidAcceptNestedScrollTouch = paramBoolean;
      }
    }
  }

  class OnPreDrawListener
    implements ViewTreeObserver.OnPreDrawListener
  {
    OnPreDrawListener()
    {
    }

    public boolean onPreDraw()
    {
      CoordinatorLayout.this.onChildViewsChanged(0);
      return true;
    }
  }

  protected static class SavedState extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public CoordinatorLayout.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new CoordinatorLayout.SavedState(paramAnonymousParcel, null);
      }

      public CoordinatorLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new CoordinatorLayout.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }

      public CoordinatorLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new CoordinatorLayout.SavedState[paramAnonymousInt];
      }
    };
    SparseArray<Parcelable> behaviorStates;

    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      int i = paramParcel.readInt();
      int[] arrayOfInt = new int[i];
      paramParcel.readIntArray(arrayOfInt);
      Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(paramClassLoader);
      this.behaviorStates = new SparseArray(i);
      for (int j = 0; j < i; j++)
        this.behaviorStates.append(arrayOfInt[j], arrayOfParcelable[j]);
    }

    public SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      SparseArray localSparseArray = this.behaviorStates;
      int i = 0;
      int j;
      if (localSparseArray != null)
        j = this.behaviorStates.size();
      else
        j = 0;
      paramParcel.writeInt(j);
      int[] arrayOfInt = new int[j];
      Parcelable[] arrayOfParcelable = new Parcelable[j];
      while (i < j)
      {
        arrayOfInt[i] = this.behaviorStates.keyAt(i);
        arrayOfParcelable[i] = ((Parcelable)this.behaviorStates.valueAt(i));
        i++;
      }
      paramParcel.writeIntArray(arrayOfInt);
      paramParcel.writeParcelableArray(arrayOfParcelable, paramInt);
    }
  }

  static class ViewElevationComparator
    implements Comparator<View>
  {
    public int compare(View paramView1, View paramView2)
    {
      float f1 = ViewCompat.getZ(paramView1);
      float f2 = ViewCompat.getZ(paramView2);
      if (f1 > f2)
        return -1;
      if (f1 < f2)
        return 1;
      return 0;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.design.widget.CoordinatorLayout
 * JD-Core Version:    0.6.1
 */