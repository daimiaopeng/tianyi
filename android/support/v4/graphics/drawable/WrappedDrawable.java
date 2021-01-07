package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface WrappedDrawable
{
  public abstract Drawable getWrappedDrawable();

  public abstract void setWrappedDrawable(Drawable paramDrawable);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.WrappedDrawable
 * JD-Core Version:    0.6.1
 */