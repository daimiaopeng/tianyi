package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public abstract interface TintableCompoundButton
{
  @Nullable
  public abstract ColorStateList getSupportButtonTintList();

  @Nullable
  public abstract PorterDuff.Mode getSupportButtonTintMode();

  public abstract void setSupportButtonTintList(@Nullable ColorStateList paramColorStateList);

  public abstract void setSupportButtonTintMode(@Nullable PorterDuff.Mode paramMode);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.TintableCompoundButton
 * JD-Core Version:    0.6.1
 */