package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public final class DrawableCompat
{
  private static final String TAG = "DrawableCompat";
  private static Method sGetLayoutDirectionMethod;
  private static boolean sGetLayoutDirectionMethodFetched;
  private static Method sSetLayoutDirectionMethod;
  private static boolean sSetLayoutDirectionMethodFetched;

  public static void applyTheme(@NonNull Drawable paramDrawable, @NonNull Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.applyTheme(paramTheme);
  }

  public static boolean canApplyTheme(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramDrawable.canApplyTheme();
    return false;
  }

  public static void clearColorFilter(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      paramDrawable.clearColorFilter();
    }
    else if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.clearColorFilter();
      if ((paramDrawable instanceof InsetDrawable))
      {
        clearColorFilter(((InsetDrawable)paramDrawable).getDrawable());
      }
      else if ((paramDrawable instanceof WrappedDrawable))
      {
        clearColorFilter(((WrappedDrawable)paramDrawable).getWrappedDrawable());
      }
      else if ((paramDrawable instanceof DrawableContainer))
      {
        DrawableContainer.DrawableContainerState localDrawableContainerState = (DrawableContainer.DrawableContainerState)((DrawableContainer)paramDrawable).getConstantState();
        if (localDrawableContainerState != null)
        {
          int i = 0;
          int j = localDrawableContainerState.getChildCount();
          while (i < j)
          {
            Drawable localDrawable = localDrawableContainerState.getChild(i);
            if (localDrawable != null)
              clearColorFilter(localDrawable);
            i++;
          }
        }
      }
    }
    else
    {
      paramDrawable.clearColorFilter();
    }
  }

  public static int getAlpha(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return paramDrawable.getAlpha();
    return 0;
  }

  public static ColorFilter getColorFilter(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramDrawable.getColorFilter();
    return null;
  }

  public static int getLayoutDirection(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramDrawable.getLayoutDirection();
    if (Build.VERSION.SDK_INT >= 17)
    {
      if (!sGetLayoutDirectionMethodFetched)
      {
        try
        {
          sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
          sGetLayoutDirectionMethod.setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", localNoSuchMethodException);
        }
        sGetLayoutDirectionMethodFetched = true;
      }
      if (sGetLayoutDirectionMethod != null)
        try
        {
          int i = ((Integer)sGetLayoutDirectionMethod.invoke(paramDrawable, new Object[0])).intValue();
          return i;
        }
        catch (Exception localException)
        {
          Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", localException);
          sGetLayoutDirectionMethod = null;
        }
      return 0;
    }
    return 0;
  }

  public static void inflate(@NonNull Drawable paramDrawable, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    else
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
  }

  public static boolean isAutoMirrored(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return paramDrawable.isAutoMirrored();
    return false;
  }

  @Deprecated
  public static void jumpToCurrentState(@NonNull Drawable paramDrawable)
  {
    paramDrawable.jumpToCurrentState();
  }

  public static void setAutoMirrored(@NonNull Drawable paramDrawable, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19)
      paramDrawable.setAutoMirrored(paramBoolean);
  }

  public static void setHotspot(@NonNull Drawable paramDrawable, float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setHotspot(paramFloat1, paramFloat2);
  }

  public static void setHotspotBounds(@NonNull Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static boolean setLayoutDirection(@NonNull Drawable paramDrawable, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramDrawable.setLayoutDirection(paramInt);
    if (Build.VERSION.SDK_INT >= 17)
    {
      if (!sSetLayoutDirectionMethodFetched)
      {
        try
        {
          Class[] arrayOfClass = new Class[1];
          arrayOfClass[0] = Integer.TYPE;
          sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", arrayOfClass);
          sSetLayoutDirectionMethod.setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", localNoSuchMethodException);
        }
        sSetLayoutDirectionMethodFetched = true;
      }
      if (sSetLayoutDirectionMethod != null)
        try
        {
          Method localMethod = sSetLayoutDirectionMethod;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramInt);
          localMethod.invoke(paramDrawable, arrayOfObject);
          return true;
        }
        catch (Exception localException)
        {
          Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", localException);
          sSetLayoutDirectionMethod = null;
        }
      return false;
    }
    return false;
  }

  public static void setTint(@NonNull Drawable paramDrawable, @ColorInt int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setTint(paramInt);
    else if ((paramDrawable instanceof TintAwareDrawable))
      ((TintAwareDrawable)paramDrawable).setTint(paramInt);
  }

  public static void setTintList(@NonNull Drawable paramDrawable, @Nullable ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setTintList(paramColorStateList);
    else if ((paramDrawable instanceof TintAwareDrawable))
      ((TintAwareDrawable)paramDrawable).setTintList(paramColorStateList);
  }

  public static void setTintMode(@NonNull Drawable paramDrawable, @NonNull PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setTintMode(paramMode);
    else if ((paramDrawable instanceof TintAwareDrawable))
      ((TintAwareDrawable)paramDrawable).setTintMode(paramMode);
  }

  public static <T extends Drawable> T unwrap(@NonNull Drawable paramDrawable)
  {
    if ((paramDrawable instanceof WrappedDrawable))
      return ((WrappedDrawable)paramDrawable).getWrappedDrawable();
    return paramDrawable;
  }

  public static Drawable wrap(@NonNull Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramDrawable;
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (!(paramDrawable instanceof TintAwareDrawable))
        return new WrappedDrawableApi21(paramDrawable);
      return paramDrawable;
    }
    if (!(paramDrawable instanceof TintAwareDrawable))
      return new WrappedDrawableApi14(paramDrawable);
    return paramDrawable;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompat
 * JD-Core Version:    0.6.1
 */