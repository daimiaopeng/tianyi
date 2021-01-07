package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat
{
  private static final String TAG = "CompoundButtonCompat";
  private static Field sButtonDrawableField;
  private static boolean sButtonDrawableFieldFetched;

  @Nullable
  public static Drawable getButtonDrawable(@NonNull CompoundButton paramCompoundButton)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramCompoundButton.getButtonDrawable();
    if (!sButtonDrawableFieldFetched)
    {
      try
      {
        sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
        sButtonDrawableField.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", localNoSuchFieldException);
      }
      sButtonDrawableFieldFetched = true;
    }
    if (sButtonDrawableField != null)
      try
      {
        Drawable localDrawable = (Drawable)sButtonDrawableField.get(paramCompoundButton);
        return localDrawable;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", localIllegalAccessException);
        sButtonDrawableField = null;
      }
    return null;
  }

  @Nullable
  public static ColorStateList getButtonTintList(@NonNull CompoundButton paramCompoundButton)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramCompoundButton.getButtonTintList();
    if ((paramCompoundButton instanceof TintableCompoundButton))
      return ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintList();
    return null;
  }

  @Nullable
  public static PorterDuff.Mode getButtonTintMode(@NonNull CompoundButton paramCompoundButton)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramCompoundButton.getButtonTintMode();
    if ((paramCompoundButton instanceof TintableCompoundButton))
      return ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintMode();
    return null;
  }

  public static void setButtonTintList(@NonNull CompoundButton paramCompoundButton, @Nullable ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramCompoundButton.setButtonTintList(paramColorStateList);
    else if ((paramCompoundButton instanceof TintableCompoundButton))
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintList(paramColorStateList);
  }

  public static void setButtonTintMode(@NonNull CompoundButton paramCompoundButton, @Nullable PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramCompoundButton.setButtonTintMode(paramMode);
    else if ((paramCompoundButton instanceof TintableCompoundButton))
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintMode(paramMode);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.CompoundButtonCompat
 * JD-Core Version:    0.6.1
 */