package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

public class ViewDragHelper
{
  private static final int BASE_SETTLE_DURATION = 256;
  public static final int DIRECTION_ALL = 3;
  public static final int DIRECTION_HORIZONTAL = 1;
  public static final int DIRECTION_VERTICAL = 2;
  public static final int EDGE_ALL = 15;
  public static final int EDGE_BOTTOM = 8;
  public static final int EDGE_LEFT = 1;
  public static final int EDGE_RIGHT = 2;
  private static final int EDGE_SIZE = 20;
  public static final int EDGE_TOP = 4;
  public static final int INVALID_POINTER = -1;
  private static final int MAX_SETTLE_DURATION = 600;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_IDLE = 0;
  public static final int STATE_SETTLING = 2;
  private static final String TAG = "ViewDragHelper";
  private static final Interpolator sInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      float f = paramAnonymousFloat - 1.0F;
      return 1.0F + f * (f * (f * (f * f)));
    }
  };
  private int mActivePointerId = -1;
  private final Callback mCallback;
  private View mCapturedView;
  private int mDragState;
  private int[] mEdgeDragsInProgress;
  private int[] mEdgeDragsLocked;
  private int mEdgeSize;
  private int[] mInitialEdgesTouched;
  private float[] mInitialMotionX;
  private float[] mInitialMotionY;
  private float[] mLastMotionX;
  private float[] mLastMotionY;
  private float mMaxVelocity;
  private float mMinVelocity;
  private final ViewGroup mParentView;
  private int mPointersDown;
  private boolean mReleaseInProgress;
  private OverScroller mScroller;
  private final Runnable mSetIdleRunnable = new Runnable()
  {
    public void run()
    {
      ViewDragHelper.this.setDragState(0);
    }
  };
  private int mTouchSlop;
  private int mTrackingEdges;
  private VelocityTracker mVelocityTracker;

  private ViewDragHelper(@NonNull Context paramContext, @NonNull ViewGroup paramViewGroup, @NonNull Callback paramCallback)
  {
    if (paramViewGroup == null)
      throw new IllegalArgumentException("Parent view may not be null");
    if (paramCallback == null)
      throw new IllegalArgumentException("Callback may not be null");
    this.mParentView = paramViewGroup;
    this.mCallback = paramCallback;
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(paramContext);
    this.mEdgeSize = (int)(0.5F + 20.0F * paramContext.getResources().getDisplayMetrics().density);
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMaxVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    this.mMinVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mScroller = new OverScroller(paramContext, sInterpolator);
  }

  private boolean checkNewEdgeDrag(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    float f1 = Math.abs(paramFloat1);
    float f2 = Math.abs(paramFloat2);
    if (((paramInt2 & this.mInitialEdgesTouched[paramInt1]) == paramInt2) && ((paramInt2 & this.mTrackingEdges) != 0) && ((paramInt2 & this.mEdgeDragsLocked[paramInt1]) != paramInt2) && ((paramInt2 & this.mEdgeDragsInProgress[paramInt1]) != paramInt2) && ((f1 > this.mTouchSlop) || (f2 > this.mTouchSlop)))
    {
      if ((f1 < f2 * 0.5F) && (this.mCallback.onEdgeLock(paramInt2)))
      {
        int[] arrayOfInt = this.mEdgeDragsLocked;
        arrayOfInt[paramInt1] = (paramInt2 | arrayOfInt[paramInt1]);
        return false;
      }
      int i = paramInt2 & this.mEdgeDragsInProgress[paramInt1];
      boolean bool1 = false;
      if (i == 0)
      {
        boolean bool2 = f1 < this.mTouchSlop;
        bool1 = false;
        if (bool2)
          bool1 = true;
      }
      return bool1;
    }
    return false;
  }

  private boolean checkTouchSlop(View paramView, float paramFloat1, float paramFloat2)
  {
    if (paramView == null)
      return false;
    int i;
    if (this.mCallback.getViewHorizontalDragRange(paramView) > 0)
      i = 1;
    else
      i = 0;
    int j;
    if (this.mCallback.getViewVerticalDragRange(paramView) > 0)
      j = 1;
    else
      j = 0;
    if ((i != 0) && (j != 0))
    {
      boolean bool5 = paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 < this.mTouchSlop * this.mTouchSlop;
      boolean bool6 = false;
      if (bool5)
        bool6 = true;
      return bool6;
    }
    if (i != 0)
    {
      boolean bool3 = Math.abs(paramFloat1) < this.mTouchSlop;
      boolean bool4 = false;
      if (bool3)
        bool4 = true;
      return bool4;
    }
    if (j != 0)
    {
      boolean bool1 = Math.abs(paramFloat2) < this.mTouchSlop;
      boolean bool2 = false;
      if (bool1)
        bool2 = true;
      return bool2;
    }
    return false;
  }

  private float clampMag(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f = Math.abs(paramFloat1);
    if (f < paramFloat2)
      return 0.0F;
    if (f > paramFloat3)
    {
      if (paramFloat1 <= 0.0F)
        paramFloat3 = -paramFloat3;
      return paramFloat3;
    }
    return paramFloat1;
  }

  private int clampMag(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = Math.abs(paramInt1);
    if (i < paramInt2)
      return 0;
    if (i > paramInt3)
    {
      if (paramInt1 <= 0)
        paramInt3 = -paramInt3;
      return paramInt3;
    }
    return paramInt1;
  }

  private void clearMotionHistory()
  {
    if (this.mInitialMotionX == null)
      return;
    Arrays.fill(this.mInitialMotionX, 0.0F);
    Arrays.fill(this.mInitialMotionY, 0.0F);
    Arrays.fill(this.mLastMotionX, 0.0F);
    Arrays.fill(this.mLastMotionY, 0.0F);
    Arrays.fill(this.mInitialEdgesTouched, 0);
    Arrays.fill(this.mEdgeDragsInProgress, 0);
    Arrays.fill(this.mEdgeDragsLocked, 0);
    this.mPointersDown = 0;
  }

  private void clearMotionHistory(int paramInt)
  {
    if ((this.mInitialMotionX != null) && (isPointerDown(paramInt)))
    {
      this.mInitialMotionX[paramInt] = 0.0F;
      this.mInitialMotionY[paramInt] = 0.0F;
      this.mLastMotionX[paramInt] = 0.0F;
      this.mLastMotionY[paramInt] = 0.0F;
      this.mInitialEdgesTouched[paramInt] = 0;
      this.mEdgeDragsInProgress[paramInt] = 0;
      this.mEdgeDragsLocked[paramInt] = 0;
      this.mPointersDown &= (0xFFFFFFFF ^ 1 << paramInt);
      return;
    }
  }

  private int computeAxisDuration(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0)
      return 0;
    int i = this.mParentView.getWidth();
    int j = i / 2;
    float f1 = Math.min(1.0F, Math.abs(paramInt1) / i);
    float f2 = j;
    float f3 = f2 + f2 * distanceInfluenceForSnapDuration(f1);
    int k = Math.abs(paramInt2);
    int m;
    if (k > 0)
      m = 4 * Math.round(1000.0F * Math.abs(f3 / k));
    else
      m = (int)(256.0F * (1.0F + Math.abs(paramInt1) / paramInt3));
    return Math.min(m, 600);
  }

  private int computeSettleDuration(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = clampMag(paramInt3, (int)this.mMinVelocity, (int)this.mMaxVelocity);
    int j = clampMag(paramInt4, (int)this.mMinVelocity, (int)this.mMaxVelocity);
    int k = Math.abs(paramInt1);
    int m = Math.abs(paramInt2);
    int n = Math.abs(i);
    int i1 = Math.abs(j);
    int i2 = n + i1;
    int i3 = k + m;
    float f1;
    if (i != 0)
      f1 = n;
    float f3;
    for (float f2 = i2; ; f2 = i3)
    {
      f3 = f1 / f2;
      break;
      f1 = k;
    }
    float f4;
    if (j != 0)
      f4 = i1;
    float f6;
    for (float f5 = i2; ; f5 = i3)
    {
      f6 = f4 / f5;
      break;
      f4 = m;
    }
    int i4 = computeAxisDuration(paramInt1, i, this.mCallback.getViewHorizontalDragRange(paramView));
    int i5 = computeAxisDuration(paramInt2, j, this.mCallback.getViewVerticalDragRange(paramView));
    return (int)(f3 * i4 + f6 * i5);
  }

  public static ViewDragHelper create(@NonNull ViewGroup paramViewGroup, float paramFloat, @NonNull Callback paramCallback)
  {
    ViewDragHelper localViewDragHelper = create(paramViewGroup, paramCallback);
    localViewDragHelper.mTouchSlop = (int)(localViewDragHelper.mTouchSlop * (1.0F / paramFloat));
    return localViewDragHelper;
  }

  public static ViewDragHelper create(@NonNull ViewGroup paramViewGroup, @NonNull Callback paramCallback)
  {
    return new ViewDragHelper(paramViewGroup.getContext(), paramViewGroup, paramCallback);
  }

  private void dispatchViewReleased(float paramFloat1, float paramFloat2)
  {
    this.mReleaseInProgress = true;
    this.mCallback.onViewReleased(this.mCapturedView, paramFloat1, paramFloat2);
    this.mReleaseInProgress = false;
    if (this.mDragState == 1)
      setDragState(0);
  }

  private float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin(0.4712389F * (paramFloat - 0.5F));
  }

  private void dragTo(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = this.mCapturedView.getLeft();
    int j = this.mCapturedView.getTop();
    if (paramInt3 != 0)
    {
      paramInt1 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, paramInt1, paramInt3);
      ViewCompat.offsetLeftAndRight(this.mCapturedView, paramInt1 - i);
    }
    int k = paramInt1;
    if (paramInt4 != 0)
    {
      paramInt2 = this.mCallback.clampViewPositionVertical(this.mCapturedView, paramInt2, paramInt4);
      ViewCompat.offsetTopAndBottom(this.mCapturedView, paramInt2 - j);
    }
    int m = paramInt2;
    if ((paramInt3 != 0) || (paramInt4 != 0))
    {
      int n = k - i;
      int i1 = m - j;
      this.mCallback.onViewPositionChanged(this.mCapturedView, k, m, n, i1);
    }
  }

  private void ensureMotionHistorySizeForId(int paramInt)
  {
    if ((this.mInitialMotionX == null) || (this.mInitialMotionX.length <= paramInt))
    {
      int i = paramInt + 1;
      float[] arrayOfFloat1 = new float[i];
      float[] arrayOfFloat2 = new float[i];
      float[] arrayOfFloat3 = new float[i];
      float[] arrayOfFloat4 = new float[i];
      int[] arrayOfInt1 = new int[i];
      int[] arrayOfInt2 = new int[i];
      int[] arrayOfInt3 = new int[i];
      if (this.mInitialMotionX != null)
      {
        System.arraycopy(this.mInitialMotionX, 0, arrayOfFloat1, 0, this.mInitialMotionX.length);
        System.arraycopy(this.mInitialMotionY, 0, arrayOfFloat2, 0, this.mInitialMotionY.length);
        System.arraycopy(this.mLastMotionX, 0, arrayOfFloat3, 0, this.mLastMotionX.length);
        System.arraycopy(this.mLastMotionY, 0, arrayOfFloat4, 0, this.mLastMotionY.length);
        System.arraycopy(this.mInitialEdgesTouched, 0, arrayOfInt1, 0, this.mInitialEdgesTouched.length);
        System.arraycopy(this.mEdgeDragsInProgress, 0, arrayOfInt2, 0, this.mEdgeDragsInProgress.length);
        System.arraycopy(this.mEdgeDragsLocked, 0, arrayOfInt3, 0, this.mEdgeDragsLocked.length);
      }
      this.mInitialMotionX = arrayOfFloat1;
      this.mInitialMotionY = arrayOfFloat2;
      this.mLastMotionX = arrayOfFloat3;
      this.mLastMotionY = arrayOfFloat4;
      this.mInitialEdgesTouched = arrayOfInt1;
      this.mEdgeDragsInProgress = arrayOfInt2;
      this.mEdgeDragsLocked = arrayOfInt3;
    }
  }

  private boolean forceSettleCapturedViewAt(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = this.mCapturedView.getLeft();
    int j = this.mCapturedView.getTop();
    int k = paramInt1 - i;
    int m = paramInt2 - j;
    if ((k == 0) && (m == 0))
    {
      this.mScroller.abortAnimation();
      setDragState(0);
      return false;
    }
    int n = computeSettleDuration(this.mCapturedView, k, m, paramInt3, paramInt4);
    this.mScroller.startScroll(i, j, k, m, n);
    setDragState(2);
    return true;
  }

  private int getEdgesTouched(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 < this.mParentView.getLeft() + this.mEdgeSize)
      i = 1;
    else
      i = 0;
    if (paramInt2 < this.mParentView.getTop() + this.mEdgeSize)
      i |= 4;
    if (paramInt1 > this.mParentView.getRight() - this.mEdgeSize)
      i |= 2;
    if (paramInt2 > this.mParentView.getBottom() - this.mEdgeSize)
      i |= 8;
    return i;
  }

  private boolean isValidPointerForActionMove(int paramInt)
  {
    if (!isPointerDown(paramInt))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Ignoring pointerId=");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" because ACTION_DOWN was not received ");
      localStringBuilder.append("for this pointer before ACTION_MOVE. It likely happened because ");
      localStringBuilder.append(" ViewDragHelper did not receive all the events in the event stream.");
      Log.e("ViewDragHelper", localStringBuilder.toString());
      return false;
    }
    return true;
  }

  private void releaseViewForPointerUp()
  {
    this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
    dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
  }

  private void reportNewEdgeDrags(float paramFloat1, float paramFloat2, int paramInt)
  {
    int i = 1;
    if (!checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, i))
      i = 0;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 4))
      i |= 4;
    if (checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 2))
      i |= 2;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 8))
      i |= 8;
    if (i != 0)
    {
      int[] arrayOfInt = this.mEdgeDragsInProgress;
      arrayOfInt[paramInt] = (i | arrayOfInt[paramInt]);
      this.mCallback.onEdgeDragStarted(i, paramInt);
    }
  }

  private void saveInitialMotion(float paramFloat1, float paramFloat2, int paramInt)
  {
    ensureMotionHistorySizeForId(paramInt);
    float[] arrayOfFloat1 = this.mInitialMotionX;
    this.mLastMotionX[paramInt] = paramFloat1;
    arrayOfFloat1[paramInt] = paramFloat1;
    float[] arrayOfFloat2 = this.mInitialMotionY;
    this.mLastMotionY[paramInt] = paramFloat2;
    arrayOfFloat2[paramInt] = paramFloat2;
    this.mInitialEdgesTouched[paramInt] = getEdgesTouched((int)paramFloat1, (int)paramFloat2);
    this.mPointersDown |= 1 << paramInt;
  }

  private void saveLastMotion(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getPointerCount();
    for (int j = 0; j < i; j++)
    {
      int k = paramMotionEvent.getPointerId(j);
      if (isValidPointerForActionMove(k))
      {
        float f1 = paramMotionEvent.getX(j);
        float f2 = paramMotionEvent.getY(j);
        this.mLastMotionX[k] = f1;
        this.mLastMotionY[k] = f2;
      }
    }
  }

  public void abort()
  {
    cancel();
    if (this.mDragState == 2)
    {
      int i = this.mScroller.getCurrX();
      int j = this.mScroller.getCurrY();
      this.mScroller.abortAnimation();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      this.mCallback.onViewPositionChanged(this.mCapturedView, k, m, k - i, m - j);
    }
    setDragState(0);
  }

  protected boolean canScroll(@NonNull View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
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
        int n = paramInt3 + j;
        if ((n >= localView.getLeft()) && (n < localView.getRight()))
        {
          int i1 = paramInt4 + k;
          if ((i1 >= localView.getTop()) && (i1 < localView.getBottom()) && (canScroll(localView, true, paramInt1, paramInt2, n - localView.getLeft(), i1 - localView.getTop())))
            return i;
        }
      }
    }
    boolean bool2;
    if ((!paramBoolean) || ((!paramView.canScrollHorizontally(-paramInt1)) && (!paramView.canScrollVertically(-paramInt2))))
      bool2 = false;
    return bool2;
  }

  public void cancel()
  {
    this.mActivePointerId = -1;
    clearMotionHistory();
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }

  public void captureChildView(@NonNull View paramView, int paramInt)
  {
    if (paramView.getParent() != this.mParentView)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
      localStringBuilder.append(this.mParentView);
      localStringBuilder.append(")");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    this.mCapturedView = paramView;
    this.mActivePointerId = paramInt;
    this.mCallback.onViewCaptured(paramView, paramInt);
    setDragState(1);
  }

  public boolean checkTouchSlop(int paramInt)
  {
    int i = this.mInitialMotionX.length;
    for (int j = 0; j < i; j++)
      if (checkTouchSlop(paramInt, j))
        return true;
    return false;
  }

  public boolean checkTouchSlop(int paramInt1, int paramInt2)
  {
    if (!isPointerDown(paramInt2))
      return false;
    int i;
    if ((paramInt1 & 0x1) == 1)
      i = 1;
    else
      i = 0;
    int j;
    if ((paramInt1 & 0x2) == 2)
      j = 1;
    else
      j = 0;
    float f1 = this.mLastMotionX[paramInt2] - this.mInitialMotionX[paramInt2];
    float f2 = this.mLastMotionY[paramInt2] - this.mInitialMotionY[paramInt2];
    if ((i != 0) && (j != 0))
    {
      boolean bool5 = f1 * f1 + f2 * f2 < this.mTouchSlop * this.mTouchSlop;
      boolean bool6 = false;
      if (bool5)
        bool6 = true;
      return bool6;
    }
    if (i != 0)
    {
      boolean bool3 = Math.abs(f1) < this.mTouchSlop;
      boolean bool4 = false;
      if (bool3)
        bool4 = true;
      return bool4;
    }
    if (j != 0)
    {
      boolean bool1 = Math.abs(f2) < this.mTouchSlop;
      boolean bool2 = false;
      if (bool1)
        bool2 = true;
      return bool2;
    }
    return false;
  }

  public boolean continueSettling(boolean paramBoolean)
  {
    if (this.mDragState == 2)
    {
      boolean bool2 = this.mScroller.computeScrollOffset();
      int j = this.mScroller.getCurrX();
      int k = this.mScroller.getCurrY();
      int m = j - this.mCapturedView.getLeft();
      int n = k - this.mCapturedView.getTop();
      if (m != 0)
        ViewCompat.offsetLeftAndRight(this.mCapturedView, m);
      if (n != 0)
        ViewCompat.offsetTopAndBottom(this.mCapturedView, n);
      if ((m != 0) || (n != 0))
        this.mCallback.onViewPositionChanged(this.mCapturedView, j, k, m, n);
      if ((bool2) && (j == this.mScroller.getFinalX()) && (k == this.mScroller.getFinalY()))
      {
        this.mScroller.abortAnimation();
        bool2 = false;
      }
      if (!bool2)
        if (paramBoolean)
          this.mParentView.post(this.mSetIdleRunnable);
        else
          setDragState(0);
    }
    int i = this.mDragState;
    boolean bool1 = false;
    if (i == 2)
      bool1 = true;
    return bool1;
  }

  @Nullable
  public View findTopChildUnder(int paramInt1, int paramInt2)
  {
    for (int i = -1 + this.mParentView.getChildCount(); i >= 0; i--)
    {
      View localView = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(i));
      if ((paramInt1 >= localView.getLeft()) && (paramInt1 < localView.getRight()) && (paramInt2 >= localView.getTop()) && (paramInt2 < localView.getBottom()))
        return localView;
    }
    return null;
  }

  public void flingCapturedView(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!this.mReleaseInProgress)
      throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
    this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int)this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int)this.mVelocityTracker.getYVelocity(this.mActivePointerId), paramInt1, paramInt3, paramInt2, paramInt4);
    setDragState(2);
  }

  public int getActivePointerId()
  {
    return this.mActivePointerId;
  }

  @Nullable
  public View getCapturedView()
  {
    return this.mCapturedView;
  }

  @Px
  public int getEdgeSize()
  {
    return this.mEdgeSize;
  }

  public float getMinVelocity()
  {
    return this.mMinVelocity;
  }

  @Px
  public int getTouchSlop()
  {
    return this.mTouchSlop;
  }

  public int getViewDragState()
  {
    return this.mDragState;
  }

  public boolean isCapturedViewUnder(int paramInt1, int paramInt2)
  {
    return isViewUnder(this.mCapturedView, paramInt1, paramInt2);
  }

  public boolean isEdgeTouched(int paramInt)
  {
    int i = this.mInitialEdgesTouched.length;
    for (int j = 0; j < i; j++)
      if (isEdgeTouched(paramInt, j))
        return true;
    return false;
  }

  public boolean isEdgeTouched(int paramInt1, int paramInt2)
  {
    boolean bool;
    if ((isPointerDown(paramInt2)) && ((paramInt1 & this.mInitialEdgesTouched[paramInt2]) != 0))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isPointerDown(int paramInt)
  {
    int i = this.mPointersDown;
    int j = 1;
    if ((i & j << paramInt) == 0)
      j = 0;
    return j;
  }

  public boolean isViewUnder(@Nullable View paramView, int paramInt1, int paramInt2)
  {
    if (paramView == null)
      return false;
    int i = paramView.getLeft();
    boolean bool = false;
    if (paramInt1 >= i)
    {
      int j = paramView.getRight();
      bool = false;
      if (paramInt1 < j)
      {
        int k = paramView.getTop();
        bool = false;
        if (paramInt2 >= k)
        {
          int m = paramView.getBottom();
          bool = false;
          if (paramInt2 < m)
            bool = true;
        }
      }
    }
    return bool;
  }

  public void processTouchEvent(@NonNull MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    int j = paramMotionEvent.getActionIndex();
    if (i == 0)
      cancel();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int k = 0;
    switch (i)
    {
    case 4:
    default:
      break;
    case 6:
      int i8 = paramMotionEvent.getPointerId(j);
      if ((this.mDragState == 1) && (i8 == this.mActivePointerId))
      {
        int i9 = paramMotionEvent.getPointerCount();
        while (k < i9)
        {
          int i11 = paramMotionEvent.getPointerId(k);
          if (i11 != this.mActivePointerId)
          {
            float f11 = paramMotionEvent.getX(k);
            float f12 = paramMotionEvent.getY(k);
            if ((findTopChildUnder((int)f11, (int)f12) == this.mCapturedView) && (tryCaptureViewForDrag(this.mCapturedView, i11)))
            {
              i10 = this.mActivePointerId;
              break label212;
            }
          }
          k++;
        }
        int i10 = -1;
        if (i10 == -1)
          releaseViewForPointerUp();
      }
      clearMotionHistory(i8);
      break;
    case 5:
      int i6 = paramMotionEvent.getPointerId(j);
      float f9 = paramMotionEvent.getX(j);
      float f10 = paramMotionEvent.getY(j);
      saveInitialMotion(f9, f10, i6);
      if (this.mDragState == 0)
      {
        tryCaptureViewForDrag(findTopChildUnder((int)f9, (int)f10), i6);
        int i7 = this.mInitialEdgesTouched[i6];
        if ((i7 & this.mTrackingEdges) != 0)
          this.mCallback.onEdgeTouched(i7 & this.mTrackingEdges, i6);
      }
      else if (isCapturedViewUnder((int)f9, (int)f10))
      {
        tryCaptureViewForDrag(this.mCapturedView, i6);
      }
      break;
    case 3:
      if (this.mDragState == 1)
        dispatchViewReleased(0.0F, 0.0F);
      cancel();
      break;
    case 2:
      if (this.mDragState == 1)
      {
        if (isValidPointerForActionMove(this.mActivePointerId))
        {
          int i3 = paramMotionEvent.findPointerIndex(this.mActivePointerId);
          float f7 = paramMotionEvent.getX(i3);
          float f8 = paramMotionEvent.getY(i3);
          int i4 = (int)(f7 - this.mLastMotionX[this.mActivePointerId]);
          int i5 = (int)(f8 - this.mLastMotionY[this.mActivePointerId]);
          dragTo(i4 + this.mCapturedView.getLeft(), i5 + this.mCapturedView.getTop(), i4, i5);
          saveLastMotion(paramMotionEvent);
        }
      }
      else
      {
        int i1 = paramMotionEvent.getPointerCount();
        while (k < i1)
        {
          int i2 = paramMotionEvent.getPointerId(k);
          if (isValidPointerForActionMove(i2))
          {
            float f3 = paramMotionEvent.getX(k);
            float f4 = paramMotionEvent.getY(k);
            float f5 = f3 - this.mInitialMotionX[i2];
            float f6 = f4 - this.mInitialMotionY[i2];
            reportNewEdgeDrags(f5, f6, i2);
            if (this.mDragState == 1)
              break;
            View localView2 = findTopChildUnder((int)f3, (int)f4);
            if ((checkTouchSlop(localView2, f5, f6)) && (tryCaptureViewForDrag(localView2, i2)))
              break;
          }
          k++;
        }
        saveLastMotion(paramMotionEvent);
      }
      break;
    case 1:
      if (this.mDragState == 1)
        releaseViewForPointerUp();
      cancel();
      break;
    case 0:
      label212: float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      int m = paramMotionEvent.getPointerId(0);
      View localView1 = findTopChildUnder((int)f1, (int)f2);
      saveInitialMotion(f1, f2, m);
      tryCaptureViewForDrag(localView1, m);
      int n = this.mInitialEdgesTouched[m];
      if ((n & this.mTrackingEdges) != 0)
        this.mCallback.onEdgeTouched(n & this.mTrackingEdges, m);
      break;
    }
  }

  void setDragState(int paramInt)
  {
    this.mParentView.removeCallbacks(this.mSetIdleRunnable);
    if (this.mDragState != paramInt)
    {
      this.mDragState = paramInt;
      this.mCallback.onViewDragStateChanged(paramInt);
      if (this.mDragState == 0)
        this.mCapturedView = null;
    }
  }

  public void setEdgeTrackingEnabled(int paramInt)
  {
    this.mTrackingEdges = paramInt;
  }

  public void setMinVelocity(float paramFloat)
  {
    this.mMinVelocity = paramFloat;
  }

  public boolean settleCapturedViewAt(int paramInt1, int paramInt2)
  {
    if (!this.mReleaseInProgress)
      throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    return forceSettleCapturedViewAt(paramInt1, paramInt2, (int)this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int)this.mVelocityTracker.getYVelocity(this.mActivePointerId));
  }

  public boolean shouldInterceptTouchEvent(@NonNull MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    int j = paramMotionEvent.getActionIndex();
    if (i == 0)
      cancel();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (i)
    {
    case 4:
    default:
    case 6:
    case 5:
    case 2:
    case 1:
    case 3:
    case 0:
    }
    while (true)
    {
      break;
      clearMotionHistory(paramMotionEvent.getPointerId(j));
      continue;
      int i15 = paramMotionEvent.getPointerId(j);
      float f7 = paramMotionEvent.getX(j);
      float f8 = paramMotionEvent.getY(j);
      saveInitialMotion(f7, f8, i15);
      if (this.mDragState == 0)
      {
        int i16 = this.mInitialEdgesTouched[i15];
        if ((i16 & this.mTrackingEdges) != 0)
          this.mCallback.onEdgeTouched(i16 & this.mTrackingEdges, i15);
      }
      else if (this.mDragState == 2)
      {
        View localView3 = findTopChildUnder((int)f7, (int)f8);
        if (localView3 == this.mCapturedView)
        {
          tryCaptureViewForDrag(localView3, i15);
          continue;
          if ((this.mInitialMotionX != null) && (this.mInitialMotionY != null))
          {
            int i1 = paramMotionEvent.getPointerCount();
            for (int i2 = 0; i2 < i1; i2++)
            {
              int i3 = paramMotionEvent.getPointerId(i2);
              if (isValidPointerForActionMove(i3))
              {
                float f3 = paramMotionEvent.getX(i2);
                float f4 = paramMotionEvent.getY(i2);
                float f5 = f3 - this.mInitialMotionX[i3];
                float f6 = f4 - this.mInitialMotionY[i3];
                View localView2 = findTopChildUnder((int)f3, (int)f4);
                int i4;
                if ((localView2 != null) && (checkTouchSlop(localView2, f5, f6)))
                  i4 = 1;
                else
                  i4 = 0;
                if (i4 != 0)
                {
                  int i5 = localView2.getLeft();
                  int i6 = (int)f5;
                  int i7 = i5 + i6;
                  int i8 = this.mCallback.clampViewPositionHorizontal(localView2, i7, i6);
                  int i9 = localView2.getTop();
                  int i10 = (int)f6;
                  int i11 = i9 + i10;
                  int i12 = this.mCallback.clampViewPositionVertical(localView2, i11, i10);
                  int i13 = this.mCallback.getViewHorizontalDragRange(localView2);
                  int i14 = this.mCallback.getViewVerticalDragRange(localView2);
                  if (((i13 == 0) || ((i13 > 0) && (i8 == i5))) && ((i14 == 0) || ((i14 > 0) && (i12 == i9))))
                    break;
                }
                else
                {
                  reportNewEdgeDrags(f5, f6, i3);
                  if ((this.mDragState == 1) || ((i4 != 0) && (tryCaptureViewForDrag(localView2, i3))))
                    break;
                }
              }
            }
            saveLastMotion(paramMotionEvent);
            continue;
            cancel();
            continue;
            float f1 = paramMotionEvent.getX();
            float f2 = paramMotionEvent.getY();
            int k = paramMotionEvent.getPointerId(0);
            saveInitialMotion(f1, f2, k);
            View localView1 = findTopChildUnder((int)f1, (int)f2);
            if ((localView1 == this.mCapturedView) && (this.mDragState == 2))
              tryCaptureViewForDrag(localView1, k);
            int m = this.mInitialEdgesTouched[k];
            if ((m & this.mTrackingEdges) != 0)
              this.mCallback.onEdgeTouched(m & this.mTrackingEdges, k);
          }
        }
      }
    }
    int n = this.mDragState;
    boolean bool = false;
    if (n == 1)
      bool = true;
    return bool;
  }

  public boolean smoothSlideViewTo(@NonNull View paramView, int paramInt1, int paramInt2)
  {
    this.mCapturedView = paramView;
    this.mActivePointerId = -1;
    boolean bool = forceSettleCapturedViewAt(paramInt1, paramInt2, 0, 0);
    if ((!bool) && (this.mDragState == 0) && (this.mCapturedView != null))
      this.mCapturedView = null;
    return bool;
  }

  boolean tryCaptureViewForDrag(View paramView, int paramInt)
  {
    if ((paramView == this.mCapturedView) && (this.mActivePointerId == paramInt))
      return true;
    if ((paramView != null) && (this.mCallback.tryCaptureView(paramView, paramInt)))
    {
      this.mActivePointerId = paramInt;
      captureChildView(paramView, paramInt);
      return true;
    }
    return false;
  }

  public static abstract class Callback
  {
    public int clampViewPositionHorizontal(@NonNull View paramView, int paramInt1, int paramInt2)
    {
      return 0;
    }

    public int clampViewPositionVertical(@NonNull View paramView, int paramInt1, int paramInt2)
    {
      return 0;
    }

    public int getOrderedChildIndex(int paramInt)
    {
      return paramInt;
    }

    public int getViewHorizontalDragRange(@NonNull View paramView)
    {
      return 0;
    }

    public int getViewVerticalDragRange(@NonNull View paramView)
    {
      return 0;
    }

    public void onEdgeDragStarted(int paramInt1, int paramInt2)
    {
    }

    public boolean onEdgeLock(int paramInt)
    {
      return false;
    }

    public void onEdgeTouched(int paramInt1, int paramInt2)
    {
    }

    public void onViewCaptured(@NonNull View paramView, int paramInt)
    {
    }

    public void onViewDragStateChanged(int paramInt)
    {
    }

    public void onViewPositionChanged(@NonNull View paramView, int paramInt1, int paramInt2, @Px int paramInt3, @Px int paramInt4)
    {
    }

    public void onViewReleased(@NonNull View paramView, float paramFloat1, float paramFloat2)
    {
    }

    public abstract boolean tryCaptureView(@NonNull View paramView, int paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ViewDragHelper
 * JD-Core Version:    0.6.1
 */