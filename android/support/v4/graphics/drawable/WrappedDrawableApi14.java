package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

class WrappedDrawableApi14 extends Drawable
  implements Drawable.Callback, TintAwareDrawable, WrappedDrawable
{
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private boolean mColorFilterSet;
  private int mCurrentColor;
  private PorterDuff.Mode mCurrentMode;
  Drawable mDrawable;
  private boolean mMutated;
  DrawableWrapperState mState;

  WrappedDrawableApi14(@Nullable Drawable paramDrawable)
  {
    this.mState = mutateConstantState();
    setWrappedDrawable(paramDrawable);
  }

  WrappedDrawableApi14(@NonNull DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
  {
    this.mState = paramDrawableWrapperState;
    updateLocalState(paramResources);
  }

  private void updateLocalState(@Nullable Resources paramResources)
  {
    if ((this.mState != null) && (this.mState.mDrawableState != null))
      setWrappedDrawable(this.mState.mDrawableState.newDrawable(paramResources));
  }

  private boolean updateTint(int[] paramArrayOfInt)
  {
    if (!isCompatTintEnabled())
      return false;
    ColorStateList localColorStateList = this.mState.mTint;
    PorterDuff.Mode localMode = this.mState.mTintMode;
    if ((localColorStateList != null) && (localMode != null))
    {
      int i = localColorStateList.getColorForState(paramArrayOfInt, localColorStateList.getDefaultColor());
      if ((!this.mColorFilterSet) || (i != this.mCurrentColor) || (localMode != this.mCurrentMode))
      {
        setColorFilter(i, localMode);
        this.mCurrentColor = i;
        this.mCurrentMode = localMode;
        this.mColorFilterSet = true;
        return true;
      }
    }
    else
    {
      this.mColorFilterSet = false;
      clearColorFilter();
    }
    return false;
  }

  public void draw(@NonNull Canvas paramCanvas)
  {
    this.mDrawable.draw(paramCanvas);
  }

  public int getChangingConfigurations()
  {
    int i = super.getChangingConfigurations();
    int j;
    if (this.mState != null)
      j = this.mState.getChangingConfigurations();
    else
      j = 0;
    return i | j | this.mDrawable.getChangingConfigurations();
  }

  @Nullable
  public Drawable.ConstantState getConstantState()
  {
    if ((this.mState != null) && (this.mState.canConstantState()))
    {
      this.mState.mChangingConfigurations = getChangingConfigurations();
      return this.mState;
    }
    return null;
  }

  @NonNull
  public Drawable getCurrent()
  {
    return this.mDrawable.getCurrent();
  }

  public int getIntrinsicHeight()
  {
    return this.mDrawable.getIntrinsicHeight();
  }

  public int getIntrinsicWidth()
  {
    return this.mDrawable.getIntrinsicWidth();
  }

  public int getMinimumHeight()
  {
    return this.mDrawable.getMinimumHeight();
  }

  public int getMinimumWidth()
  {
    return this.mDrawable.getMinimumWidth();
  }

  public int getOpacity()
  {
    return this.mDrawable.getOpacity();
  }

  public boolean getPadding(@NonNull Rect paramRect)
  {
    return this.mDrawable.getPadding(paramRect);
  }

  @NonNull
  public int[] getState()
  {
    return this.mDrawable.getState();
  }

  public Region getTransparentRegion()
  {
    return this.mDrawable.getTransparentRegion();
  }

  public final Drawable getWrappedDrawable()
  {
    return this.mDrawable;
  }

  public void invalidateDrawable(@NonNull Drawable paramDrawable)
  {
    invalidateSelf();
  }

  @RequiresApi(19)
  public boolean isAutoMirrored()
  {
    return this.mDrawable.isAutoMirrored();
  }

  protected boolean isCompatTintEnabled()
  {
    return true;
  }

  public boolean isStateful()
  {
    ColorStateList localColorStateList;
    if ((isCompatTintEnabled()) && (this.mState != null))
      localColorStateList = this.mState.mTint;
    else
      localColorStateList = null;
    boolean bool;
    if (((localColorStateList != null) && (localColorStateList.isStateful())) || (this.mDrawable.isStateful()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void jumpToCurrentState()
  {
    this.mDrawable.jumpToCurrentState();
  }

  @NonNull
  public Drawable mutate()
  {
    if ((!this.mMutated) && (super.mutate() == this))
    {
      this.mState = mutateConstantState();
      if (this.mDrawable != null)
        this.mDrawable.mutate();
      if (this.mState != null)
      {
        DrawableWrapperState localDrawableWrapperState = this.mState;
        Drawable.ConstantState localConstantState;
        if (this.mDrawable != null)
          localConstantState = this.mDrawable.getConstantState();
        else
          localConstantState = null;
        localDrawableWrapperState.mDrawableState = localConstantState;
      }
      this.mMutated = true;
    }
    return this;
  }

  @NonNull
  DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateBase(this.mState, null);
  }

  protected void onBoundsChange(Rect paramRect)
  {
    if (this.mDrawable != null)
      this.mDrawable.setBounds(paramRect);
  }

  protected boolean onLevelChange(int paramInt)
  {
    return this.mDrawable.setLevel(paramInt);
  }

  public void scheduleDrawable(@NonNull Drawable paramDrawable, @NonNull Runnable paramRunnable, long paramLong)
  {
    scheduleSelf(paramRunnable, paramLong);
  }

  public void setAlpha(int paramInt)
  {
    this.mDrawable.setAlpha(paramInt);
  }

  @RequiresApi(19)
  public void setAutoMirrored(boolean paramBoolean)
  {
    this.mDrawable.setAutoMirrored(paramBoolean);
  }

  public void setChangingConfigurations(int paramInt)
  {
    this.mDrawable.setChangingConfigurations(paramInt);
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mDrawable.setColorFilter(paramColorFilter);
  }

  public void setDither(boolean paramBoolean)
  {
    this.mDrawable.setDither(paramBoolean);
  }

  public void setFilterBitmap(boolean paramBoolean)
  {
    this.mDrawable.setFilterBitmap(paramBoolean);
  }

  public boolean setState(@NonNull int[] paramArrayOfInt)
  {
    boolean bool1 = this.mDrawable.setState(paramArrayOfInt);
    boolean bool2;
    if ((!updateTint(paramArrayOfInt)) && (!bool1))
      bool2 = false;
    else
      bool2 = true;
    return bool2;
  }

  public void setTint(int paramInt)
  {
    setTintList(ColorStateList.valueOf(paramInt));
  }

  public void setTintList(ColorStateList paramColorStateList)
  {
    this.mState.mTint = paramColorStateList;
    updateTint(getState());
  }

  public void setTintMode(@NonNull PorterDuff.Mode paramMode)
  {
    this.mState.mTintMode = paramMode;
    updateTint(getState());
  }

  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool;
    if ((!super.setVisible(paramBoolean1, paramBoolean2)) && (!this.mDrawable.setVisible(paramBoolean1, paramBoolean2)))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public final void setWrappedDrawable(Drawable paramDrawable)
  {
    if (this.mDrawable != null)
      this.mDrawable.setCallback(null);
    this.mDrawable = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      setVisible(paramDrawable.isVisible(), true);
      setState(paramDrawable.getState());
      setLevel(paramDrawable.getLevel());
      setBounds(paramDrawable.getBounds());
      if (this.mState != null)
        this.mState.mDrawableState = paramDrawable.getConstantState();
    }
    invalidateSelf();
  }

  public void unscheduleDrawable(@NonNull Drawable paramDrawable, @NonNull Runnable paramRunnable)
  {
    unscheduleSelf(paramRunnable);
  }

  protected static abstract class DrawableWrapperState extends Drawable.ConstantState
  {
    int mChangingConfigurations;
    Drawable.ConstantState mDrawableState;
    ColorStateList mTint = null;
    PorterDuff.Mode mTintMode = WrappedDrawableApi14.DEFAULT_TINT_MODE;

    DrawableWrapperState(@Nullable DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
    {
      if (paramDrawableWrapperState != null)
      {
        this.mChangingConfigurations = paramDrawableWrapperState.mChangingConfigurations;
        this.mDrawableState = paramDrawableWrapperState.mDrawableState;
        this.mTint = paramDrawableWrapperState.mTint;
        this.mTintMode = paramDrawableWrapperState.mTintMode;
      }
    }

    boolean canConstantState()
    {
      boolean bool;
      if (this.mDrawableState != null)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public int getChangingConfigurations()
    {
      int i = this.mChangingConfigurations;
      int j;
      if (this.mDrawableState != null)
        j = this.mDrawableState.getChangingConfigurations();
      else
        j = 0;
      return i | j;
    }

    @NonNull
    public Drawable newDrawable()
    {
      return newDrawable(null);
    }

    @NonNull
    public abstract Drawable newDrawable(@Nullable Resources paramResources);
  }

  private static class DrawableWrapperStateBase extends WrappedDrawableApi14.DrawableWrapperState
  {
    DrawableWrapperStateBase(@Nullable WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
    {
      super(paramResources);
    }

    @NonNull
    public Drawable newDrawable(@Nullable Resources paramResources)
    {
      return new WrappedDrawableApi14(this, paramResources);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.WrappedDrawableApi14
 * JD-Core Version:    0.6.1
 */