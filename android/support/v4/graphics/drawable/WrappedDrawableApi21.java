package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Method;

@RequiresApi(21)
class WrappedDrawableApi21 extends WrappedDrawableApi14
{
  private static final String TAG = "WrappedDrawableApi21";
  private static Method sIsProjectedDrawableMethod;

  WrappedDrawableApi21(Drawable paramDrawable)
  {
    super(paramDrawable);
    findAndCacheIsProjectedDrawableMethod();
  }

  WrappedDrawableApi21(WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
    findAndCacheIsProjectedDrawableMethod();
  }

  private void findAndCacheIsProjectedDrawableMethod()
  {
    if (sIsProjectedDrawableMethod == null)
      try
      {
        sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
      }
      catch (Exception localException)
      {
        Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", localException);
      }
  }

  @NonNull
  public Rect getDirtyBounds()
  {
    return this.mDrawable.getDirtyBounds();
  }

  public void getOutline(@NonNull Outline paramOutline)
  {
    this.mDrawable.getOutline(paramOutline);
  }

  protected boolean isCompatTintEnabled()
  {
    if (Build.VERSION.SDK_INT == 21)
    {
      Drawable localDrawable = this.mDrawable;
      boolean bool1;
      if ((!(localDrawable instanceof GradientDrawable)) && (!(localDrawable instanceof DrawableContainer)) && (!(localDrawable instanceof InsetDrawable)))
      {
        boolean bool2 = localDrawable instanceof RippleDrawable;
        bool1 = false;
        if (!bool2);
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }

  public boolean isProjected()
  {
    if ((this.mDrawable != null) && (sIsProjectedDrawableMethod != null))
      try
      {
        boolean bool = ((Boolean)sIsProjectedDrawableMethod.invoke(this.mDrawable, new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException)
      {
        Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", localException);
      }
    return false;
  }

  @NonNull
  WrappedDrawableApi14.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateLollipop(this.mState, null);
  }

  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    this.mDrawable.setHotspot(paramFloat1, paramFloat2);
  }

  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean setState(@NonNull int[] paramArrayOfInt)
  {
    if (super.setState(paramArrayOfInt))
    {
      invalidateSelf();
      return true;
    }
    return false;
  }

  public void setTint(int paramInt)
  {
    if (isCompatTintEnabled())
      super.setTint(paramInt);
    else
      this.mDrawable.setTint(paramInt);
  }

  public void setTintList(ColorStateList paramColorStateList)
  {
    if (isCompatTintEnabled())
      super.setTintList(paramColorStateList);
    else
      this.mDrawable.setTintList(paramColorStateList);
  }

  public void setTintMode(PorterDuff.Mode paramMode)
  {
    if (isCompatTintEnabled())
      super.setTintMode(paramMode);
    else
      this.mDrawable.setTintMode(paramMode);
  }

  private static class DrawableWrapperStateLollipop extends WrappedDrawableApi14.DrawableWrapperState
  {
    DrawableWrapperStateLollipop(@Nullable WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
    {
      super(paramResources);
    }

    @NonNull
    public Drawable newDrawable(@Nullable Resources paramResources)
    {
      return new WrappedDrawableApi21(this, paramResources);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.WrappedDrawableApi21
 * JD-Core Version:    0.6.1
 */