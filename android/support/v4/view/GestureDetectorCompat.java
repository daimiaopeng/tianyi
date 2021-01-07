package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat
{
  private final GestureDetectorCompatImpl mImpl;

  public GestureDetectorCompat(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener)
  {
    this(paramContext, paramOnGestureListener, null);
  }

  public GestureDetectorCompat(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler)
  {
    if (Build.VERSION.SDK_INT > 17)
      this.mImpl = new GestureDetectorCompatImplJellybeanMr2(paramContext, paramOnGestureListener, paramHandler);
    else
      this.mImpl = new GestureDetectorCompatImplBase(paramContext, paramOnGestureListener, paramHandler);
  }

  public boolean isLongpressEnabled()
  {
    return this.mImpl.isLongpressEnabled();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.onTouchEvent(paramMotionEvent);
  }

  public void setIsLongpressEnabled(boolean paramBoolean)
  {
    this.mImpl.setIsLongpressEnabled(paramBoolean);
  }

  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
  {
    this.mImpl.setOnDoubleTapListener(paramOnDoubleTapListener);
  }

  static abstract interface GestureDetectorCompatImpl
  {
    public abstract boolean isLongpressEnabled();

    public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);

    public abstract void setIsLongpressEnabled(boolean paramBoolean);

    public abstract void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener);
  }

  static class GestureDetectorCompatImplBase
    implements GestureDetectorCompat.GestureDetectorCompatImpl
  {
    private static final int DOUBLE_TAP_TIMEOUT = 0;
    private static final int LONGPRESS_TIMEOUT = 0;
    private static final int LONG_PRESS = 2;
    private static final int SHOW_PRESS = 1;
    private static final int TAP = 3;
    private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    private boolean mAlwaysInBiggerTapRegion;
    private boolean mAlwaysInTapRegion;
    MotionEvent mCurrentDownEvent;
    boolean mDeferConfirmSingleTap;
    GestureDetector.OnDoubleTapListener mDoubleTapListener;
    private int mDoubleTapSlopSquare;
    private float mDownFocusX;
    private float mDownFocusY;
    private final Handler mHandler;
    private boolean mInLongPress;
    private boolean mIsDoubleTapping;
    private boolean mIsLongpressEnabled;
    private float mLastFocusX;
    private float mLastFocusY;
    final GestureDetector.OnGestureListener mListener;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private MotionEvent mPreviousUpEvent;
    boolean mStillDown;
    private int mTouchSlopSquare;
    private VelocityTracker mVelocityTracker;

    GestureDetectorCompatImplBase(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler)
    {
      if (paramHandler != null)
        this.mHandler = new GestureHandler(paramHandler);
      else
        this.mHandler = new GestureHandler();
      this.mListener = paramOnGestureListener;
      if ((paramOnGestureListener instanceof GestureDetector.OnDoubleTapListener))
        setOnDoubleTapListener((GestureDetector.OnDoubleTapListener)paramOnGestureListener);
      init(paramContext);
    }

    private void cancel()
    {
      this.mHandler.removeMessages(1);
      this.mHandler.removeMessages(2);
      this.mHandler.removeMessages(3);
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
      this.mIsDoubleTapping = false;
      this.mStillDown = false;
      this.mAlwaysInTapRegion = false;
      this.mAlwaysInBiggerTapRegion = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mInLongPress)
        this.mInLongPress = false;
    }

    private void cancelTaps()
    {
      this.mHandler.removeMessages(1);
      this.mHandler.removeMessages(2);
      this.mHandler.removeMessages(3);
      this.mIsDoubleTapping = false;
      this.mAlwaysInTapRegion = false;
      this.mAlwaysInBiggerTapRegion = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mInLongPress)
        this.mInLongPress = false;
    }

    private void init(Context paramContext)
    {
      if (paramContext == null)
        throw new IllegalArgumentException("Context must not be null");
      if (this.mListener == null)
        throw new IllegalArgumentException("OnGestureListener must not be null");
      this.mIsLongpressEnabled = true;
      ViewConfiguration localViewConfiguration = ViewConfiguration.get(paramContext);
      int i = localViewConfiguration.getScaledTouchSlop();
      int j = localViewConfiguration.getScaledDoubleTapSlop();
      this.mMinimumFlingVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
      this.mMaximumFlingVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
      this.mTouchSlopSquare = (i * i);
      this.mDoubleTapSlopSquare = (j * j);
    }

    private boolean isConsideredDoubleTap(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, MotionEvent paramMotionEvent3)
    {
      if (!this.mAlwaysInBiggerTapRegion)
        return false;
      if (paramMotionEvent3.getEventTime() - paramMotionEvent2.getEventTime() > DOUBLE_TAP_TIMEOUT)
        return false;
      int i = (int)paramMotionEvent1.getX() - (int)paramMotionEvent3.getX();
      int j = (int)paramMotionEvent1.getY() - (int)paramMotionEvent3.getY();
      int k = i * i + j * j;
      int m = this.mDoubleTapSlopSquare;
      boolean bool = false;
      if (k < m)
        bool = true;
      return bool;
    }

    void dispatchLongPress()
    {
      this.mHandler.removeMessages(3);
      this.mDeferConfirmSingleTap = false;
      this.mInLongPress = true;
      this.mListener.onLongPress(this.mCurrentDownEvent);
    }

    public boolean isLongpressEnabled()
    {
      return this.mIsLongpressEnabled;
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      if (this.mVelocityTracker == null)
        this.mVelocityTracker = VelocityTracker.obtain();
      this.mVelocityTracker.addMovement(paramMotionEvent);
      int j = i & 0xFF;
      int k;
      if (j == 6)
        k = 1;
      else
        k = 0;
      int m;
      if (k != 0)
        m = paramMotionEvent.getActionIndex();
      else
        m = -1;
      int n = paramMotionEvent.getPointerCount();
      int i1 = 0;
      float f1 = 0.0F;
      float f2 = 0.0F;
      while (i1 < n)
      {
        if (m != i1)
        {
          f1 += paramMotionEvent.getX(i1);
          f2 += paramMotionEvent.getY(i1);
        }
        i1++;
      }
      int i2;
      if (k != 0)
        i2 = n - 1;
      else
        i2 = n;
      float f3 = i2;
      float f4 = f1 / f3;
      float f5 = f2 / f3;
      switch (j)
      {
      case 4:
      default:
        bool2 = false;
        break;
      case 6:
        this.mLastFocusX = f4;
        this.mDownFocusX = f4;
        this.mLastFocusY = f5;
        this.mDownFocusY = f5;
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
        int i7 = paramMotionEvent.getActionIndex();
        int i8 = paramMotionEvent.getPointerId(i7);
        float f10 = this.mVelocityTracker.getXVelocity(i8);
        float f11 = this.mVelocityTracker.getYVelocity(i8);
        for (int i9 = 0; ; i9++)
        {
          bool2 = false;
          if (i9 >= n)
            break;
          float f8;
          float f9;
          int i4;
          int i5;
          int i6;
          boolean bool4;
          boolean bool6;
          MotionEvent localMotionEvent;
          boolean bool5;
          VelocityTracker localVelocityTracker;
          int i3;
          float f6;
          float f7;
          boolean bool3;
          boolean bool1;
          if (i9 != i7)
          {
            int i10 = paramMotionEvent.getPointerId(i9);
            if (f10 * this.mVelocityTracker.getXVelocity(i10) + f11 * this.mVelocityTracker.getYVelocity(i10) < 0.0F)
            {
              this.mVelocityTracker.clear();
              bool2 = false;
              break;
            }
          }
        }
      case 5:
        this.mLastFocusX = f4;
        this.mDownFocusX = f4;
        this.mLastFocusY = f5;
        this.mDownFocusY = f5;
        cancelTaps();
        bool2 = false;
        break;
      case 3:
        cancel();
        bool2 = false;
        break;
      case 2:
        if (this.mInLongPress)
        {
          bool2 = false;
        }
        else
        {
          f8 = this.mLastFocusX - f4;
          f9 = this.mLastFocusY - f5;
          if (this.mIsDoubleTapping)
          {
            bool2 = false | this.mDoubleTapListener.onDoubleTapEvent(paramMotionEvent);
          }
          else if (this.mAlwaysInTapRegion)
          {
            i4 = (int)(f4 - this.mDownFocusX);
            i5 = (int)(f5 - this.mDownFocusY);
            i6 = i4 * i4 + i5 * i5;
            if (i6 > this.mTouchSlopSquare)
            {
              bool4 = this.mListener.onScroll(this.mCurrentDownEvent, paramMotionEvent, f8, f9);
              this.mLastFocusX = f4;
              this.mLastFocusY = f5;
              this.mAlwaysInTapRegion = false;
              this.mHandler.removeMessages(3);
              this.mHandler.removeMessages(1);
              this.mHandler.removeMessages(2);
            }
            else
            {
              bool4 = false;
            }
            if (i6 > this.mTouchSlopSquare)
              this.mAlwaysInBiggerTapRegion = false;
          }
          else if (Math.abs(f8) < 1.0F)
          {
            bool6 = Math.abs(f9) < 1.0F;
            bool2 = false;
            if (bool6);
          }
          else
          {
            bool2 = this.mListener.onScroll(this.mCurrentDownEvent, paramMotionEvent, f8, f9);
            this.mLastFocusX = f4;
            this.mLastFocusY = f5;
          }
        }
        break;
      case 1:
        this.mStillDown = false;
        localMotionEvent = MotionEvent.obtain(paramMotionEvent);
        if (this.mIsDoubleTapping)
        {
          bool4 = false | this.mDoubleTapListener.onDoubleTapEvent(paramMotionEvent);
        }
        else
        {
          if (this.mInLongPress)
          {
            this.mHandler.removeMessages(3);
            this.mInLongPress = false;
          }
          else
          {
            if (this.mAlwaysInTapRegion)
            {
              bool5 = this.mListener.onSingleTapUp(paramMotionEvent);
              if ((this.mDeferConfirmSingleTap) && (this.mDoubleTapListener != null))
                this.mDoubleTapListener.onSingleTapConfirmed(paramMotionEvent);
              bool4 = bool5;
              break label869;
            }
            localVelocityTracker = this.mVelocityTracker;
            i3 = paramMotionEvent.getPointerId(0);
            localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
            f6 = localVelocityTracker.getYVelocity(i3);
            f7 = localVelocityTracker.getXVelocity(i3);
            if ((Math.abs(f6) > this.mMinimumFlingVelocity) || (Math.abs(f7) > this.mMinimumFlingVelocity))
              break label849;
          }
          bool4 = false;
          break label869;
          label849: bool4 = this.mListener.onFling(this.mCurrentDownEvent, paramMotionEvent, f7, f6);
        }
        label869: if (this.mPreviousUpEvent != null)
          this.mPreviousUpEvent.recycle();
        this.mPreviousUpEvent = localMotionEvent;
        if (this.mVelocityTracker != null)
        {
          this.mVelocityTracker.recycle();
          this.mVelocityTracker = null;
        }
        this.mIsDoubleTapping = false;
        this.mDeferConfirmSingleTap = false;
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        bool2 = bool4;
        break;
      case 0:
      }
      if (this.mDoubleTapListener != null)
      {
        bool3 = this.mHandler.hasMessages(3);
        if (bool3)
          this.mHandler.removeMessages(3);
        if ((this.mCurrentDownEvent != null) && (this.mPreviousUpEvent != null) && (bool3) && (isConsideredDoubleTap(this.mCurrentDownEvent, this.mPreviousUpEvent, paramMotionEvent)))
        {
          this.mIsDoubleTapping = true;
          bool1 = false | this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | this.mDoubleTapListener.onDoubleTapEvent(paramMotionEvent);
        }
        else
        {
          this.mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
        }
      }
      else
      {
        bool1 = false;
      }
      this.mLastFocusX = f4;
      this.mDownFocusX = f4;
      this.mLastFocusY = f5;
      this.mDownFocusY = f5;
      if (this.mCurrentDownEvent != null)
        this.mCurrentDownEvent.recycle();
      this.mCurrentDownEvent = MotionEvent.obtain(paramMotionEvent);
      this.mAlwaysInTapRegion = true;
      this.mAlwaysInBiggerTapRegion = true;
      this.mStillDown = true;
      this.mInLongPress = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mIsLongpressEnabled)
      {
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageAtTime(2, this.mCurrentDownEvent.getDownTime() + TAP_TIMEOUT + LONGPRESS_TIMEOUT);
      }
      this.mHandler.sendEmptyMessageAtTime(1, this.mCurrentDownEvent.getDownTime() + TAP_TIMEOUT);
      boolean bool2 = bool1 | this.mListener.onDown(paramMotionEvent);
      return bool2;
    }

    public void setIsLongpressEnabled(boolean paramBoolean)
    {
      this.mIsLongpressEnabled = paramBoolean;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
    {
      this.mDoubleTapListener = paramOnDoubleTapListener;
    }

    private class GestureHandler extends Handler
    {
      GestureHandler()
      {
      }

      GestureHandler(Handler arg2)
      {
        super();
      }

      public void handleMessage(Message paramMessage)
      {
        switch (paramMessage.what)
        {
        default:
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown message ");
          localStringBuilder.append(paramMessage);
          throw new RuntimeException(localStringBuilder.toString());
        case 3:
          if (GestureDetectorCompat.GestureDetectorCompatImplBase.this.mDoubleTapListener != null)
            if (!GestureDetectorCompat.GestureDetectorCompatImplBase.this.mStillDown)
              GestureDetectorCompat.GestureDetectorCompatImplBase.this.mDoubleTapListener.onSingleTapConfirmed(GestureDetectorCompat.GestureDetectorCompatImplBase.this.mCurrentDownEvent);
            else
              GestureDetectorCompat.GestureDetectorCompatImplBase.this.mDeferConfirmSingleTap = true;
          break;
        case 2:
          GestureDetectorCompat.GestureDetectorCompatImplBase.this.dispatchLongPress();
          break;
        case 1:
          GestureDetectorCompat.GestureDetectorCompatImplBase.this.mListener.onShowPress(GestureDetectorCompat.GestureDetectorCompatImplBase.this.mCurrentDownEvent);
        }
      }
    }
  }

  static class GestureDetectorCompatImplJellybeanMr2
    implements GestureDetectorCompat.GestureDetectorCompatImpl
  {
    private final GestureDetector mDetector;

    GestureDetectorCompatImplJellybeanMr2(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler)
    {
      this.mDetector = new GestureDetector(paramContext, paramOnGestureListener, paramHandler);
    }

    public boolean isLongpressEnabled()
    {
      return this.mDetector.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      return this.mDetector.onTouchEvent(paramMotionEvent);
    }

    public void setIsLongpressEnabled(boolean paramBoolean)
    {
      this.mDetector.setIsLongpressEnabled(paramBoolean);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
    {
      this.mDetector.setOnDoubleTapListener(paramOnDoubleTapListener);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.GestureDetectorCompat
 * JD-Core Version:    0.6.1
 */