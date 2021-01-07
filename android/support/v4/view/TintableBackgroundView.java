package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public abstract interface TintableBackgroundView
{
  @Nullable
  public abstract ColorStateList getSupportBackgroundTintList();

  @Nullable
  public abstract PorterDuff.Mode getSupportBackgroundTintMode();

  public abstract void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList);

  public abstract void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.TintableBackgroundView
 * JD-Core Version:    0.6.1
 */