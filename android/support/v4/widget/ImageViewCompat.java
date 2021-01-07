package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class ImageViewCompat
{
  @Nullable
  public static ColorStateList getImageTintList(@NonNull ImageView paramImageView)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramImageView.getImageTintList();
    ColorStateList localColorStateList;
    if ((paramImageView instanceof TintableImageSourceView))
      localColorStateList = ((TintableImageSourceView)paramImageView).getSupportImageTintList();
    else
      localColorStateList = null;
    return localColorStateList;
  }

  @Nullable
  public static PorterDuff.Mode getImageTintMode(@NonNull ImageView paramImageView)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramImageView.getImageTintMode();
    PorterDuff.Mode localMode;
    if ((paramImageView instanceof TintableImageSourceView))
      localMode = ((TintableImageSourceView)paramImageView).getSupportImageTintMode();
    else
      localMode = null;
    return localMode;
  }

  public static void setImageTintList(@NonNull ImageView paramImageView, @Nullable ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramImageView.setImageTintList(paramColorStateList);
      if (Build.VERSION.SDK_INT == 21)
      {
        Drawable localDrawable = paramImageView.getDrawable();
        int i;
        if ((paramImageView.getImageTintList() != null) && (paramImageView.getImageTintMode() != null))
          i = 1;
        else
          i = 0;
        if ((localDrawable != null) && (i != 0))
        {
          if (localDrawable.isStateful())
            localDrawable.setState(paramImageView.getDrawableState());
          paramImageView.setImageDrawable(localDrawable);
        }
      }
    }
    else if ((paramImageView instanceof TintableImageSourceView))
    {
      ((TintableImageSourceView)paramImageView).setSupportImageTintList(paramColorStateList);
    }
  }

  public static void setImageTintMode(@NonNull ImageView paramImageView, @Nullable PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramImageView.setImageTintMode(paramMode);
      if (Build.VERSION.SDK_INT == 21)
      {
        Drawable localDrawable = paramImageView.getDrawable();
        int i;
        if ((paramImageView.getImageTintList() != null) && (paramImageView.getImageTintMode() != null))
          i = 1;
        else
          i = 0;
        if ((localDrawable != null) && (i != 0))
        {
          if (localDrawable.isStateful())
            localDrawable.setState(paramImageView.getDrawableState());
          paramImageView.setImageDrawable(localDrawable);
        }
      }
    }
    else if ((paramImageView instanceof TintableImageSourceView))
    {
      ((TintableImageSourceView)paramImageView).setSupportImageTintMode(paramMode);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ImageViewCompat
 * JD-Core Version:    0.6.1
 */