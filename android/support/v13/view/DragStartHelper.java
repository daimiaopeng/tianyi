package android.support.v13.view;

import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class DragStartHelper
{
  private boolean mDragging;
  private int mLastTouchX;
  private int mLastTouchY;
  private final OnDragStartListener mListener;
  private final View.OnLongClickListener mLongClickListener = new View.OnLongClickListener()
  {
    public boolean onLongClick(View paramAnonymousView)
    {
      return DragStartHelper.this.onLongClick(paramAnonymousView);
    }
  };
  private final View.OnTouchListener mTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      return DragStartHelper.this.onTouch(paramAnonymousView, paramAnonymousMotionEvent);
    }
  };
  private final View mView;

  public DragStartHelper(View paramView, OnDragStartListener paramOnDragStartListener)
  {
    this.mView = paramView;
    this.mListener = paramOnDragStartListener;
  }

  public void attach()
  {
    this.mView.setOnLongClickListener(this.mLongClickListener);
    this.mView.setOnTouchListener(this.mTouchListener);
  }

  public void detach()
  {
    this.mView.setOnLongClickListener(null);
    this.mView.setOnTouchListener(null);
  }

  public void getTouchPosition(Point paramPoint)
  {
    paramPoint.set(this.mLastTouchX, this.mLastTouchY);
  }

  public boolean onLongClick(View paramView)
  {
    return this.mListener.onDragStart(paramView, this);
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    default:
      break;
    case 2:
      if ((MotionEventCompat.isFromSource(paramMotionEvent, 8194)) && ((0x1 & paramMotionEvent.getButtonState()) != 0) && (!this.mDragging) && ((this.mLastTouchX != i) || (this.mLastTouchY != j)))
      {
        this.mLastTouchX = i;
        this.mLastTouchY = j;
        this.mDragging = this.mListener.onDragStart(paramView, this);
        return this.mDragging;
      }
      break;
    case 1:
    case 3:
      this.mDragging = false;
      break;
    case 0:
      this.mLastTouchX = i;
      this.mLastTouchY = j;
    }
    return false;
  }

  public static abstract interface OnDragStartListener
  {
    public abstract boolean onDragStart(View paramView, DragStartHelper paramDragStartHelper);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v13.view.DragStartHelper
 * JD-Core Version:    0.6.1
 */