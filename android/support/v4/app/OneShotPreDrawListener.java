package android.support.v4.app;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class OneShotPreDrawListener
  implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener
{
  private final Runnable mRunnable;
  private final View mView;
  private ViewTreeObserver mViewTreeObserver;

  private OneShotPreDrawListener(View paramView, Runnable paramRunnable)
  {
    this.mView = paramView;
    this.mViewTreeObserver = paramView.getViewTreeObserver();
    this.mRunnable = paramRunnable;
  }

  public static OneShotPreDrawListener add(View paramView, Runnable paramRunnable)
  {
    OneShotPreDrawListener localOneShotPreDrawListener = new OneShotPreDrawListener(paramView, paramRunnable);
    paramView.getViewTreeObserver().addOnPreDrawListener(localOneShotPreDrawListener);
    paramView.addOnAttachStateChangeListener(localOneShotPreDrawListener);
    return localOneShotPreDrawListener;
  }

  public boolean onPreDraw()
  {
    removeListener();
    this.mRunnable.run();
    return true;
  }

  public void onViewAttachedToWindow(View paramView)
  {
    this.mViewTreeObserver = paramView.getViewTreeObserver();
  }

  public void onViewDetachedFromWindow(View paramView)
  {
    removeListener();
  }

  public void removeListener()
  {
    if (this.mViewTreeObserver.isAlive())
      this.mViewTreeObserver.removeOnPreDrawListener(this);
    else
      this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
    this.mView.removeOnAttachStateChangeListener(this);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.OneShotPreDrawListener
 * JD-Core Version:    0.6.1
 */