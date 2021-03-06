package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.util.Preconditions;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat
{
  private static final String TAG = "ResourcesCompat";

  @ColorInt
  public static int getColor(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramResources.getColor(paramInt, paramTheme);
    return paramResources.getColor(paramInt);
  }

  @Nullable
  public static ColorStateList getColorStateList(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramResources.getColorStateList(paramInt, paramTheme);
    return paramResources.getColorStateList(paramInt);
  }

  @Nullable
  public static Drawable getDrawable(@NonNull Resources paramResources, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramResources.getDrawable(paramInt, paramTheme);
    return paramResources.getDrawable(paramInt);
  }

  @Nullable
  public static Drawable getDrawableForDensity(@NonNull Resources paramResources, @DrawableRes int paramInt1, int paramInt2, @Nullable Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramResources.getDrawableForDensity(paramInt1, paramInt2, paramTheme);
    if (Build.VERSION.SDK_INT >= 15)
      return paramResources.getDrawableForDensity(paramInt1, paramInt2);
    return paramResources.getDrawable(paramInt1);
  }

  @Nullable
  public static Typeface getFont(@NonNull Context paramContext, @FontRes int paramInt)
  {
    if (paramContext.isRestricted())
      return null;
    return loadFont(paramContext, paramInt, new TypedValue(), 0, null, null, false);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static Typeface getFont(@NonNull Context paramContext, @FontRes int paramInt1, TypedValue paramTypedValue, int paramInt2, @Nullable FontCallback paramFontCallback)
  {
    if (paramContext.isRestricted())
      return null;
    return loadFont(paramContext, paramInt1, paramTypedValue, paramInt2, paramFontCallback, null, true);
  }

  public static void getFont(@NonNull Context paramContext, @FontRes int paramInt, @NonNull FontCallback paramFontCallback, @Nullable Handler paramHandler)
  {
    Preconditions.checkNotNull(paramFontCallback);
    if (paramContext.isRestricted())
    {
      paramFontCallback.callbackFailAsync(-4, paramHandler);
      return;
    }
    loadFont(paramContext, paramInt, new TypedValue(), 0, paramFontCallback, paramHandler, false);
  }

  private static Typeface loadFont(@NonNull Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, @Nullable FontCallback paramFontCallback, @Nullable Handler paramHandler, boolean paramBoolean)
  {
    Resources localResources = paramContext.getResources();
    localResources.getValue(paramInt1, paramTypedValue, true);
    Typeface localTypeface = loadFont(paramContext, localResources, paramTypedValue, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
    if ((localTypeface == null) && (paramFontCallback == null))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Font resource ID #0x");
      localStringBuilder.append(Integer.toHexString(paramInt1));
      localStringBuilder.append(" could not be retrieved.");
      throw new Resources.NotFoundException(localStringBuilder.toString());
    }
    return localTypeface;
  }

  private static Typeface loadFont(@NonNull Context paramContext, Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, @Nullable FontCallback paramFontCallback, @Nullable Handler paramHandler, boolean paramBoolean)
  {
    if (paramTypedValue.string == null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Resource \"");
      localStringBuilder1.append(paramResources.getResourceName(paramInt1));
      localStringBuilder1.append("\" (");
      localStringBuilder1.append(Integer.toHexString(paramInt1));
      localStringBuilder1.append(") is not a Font: ");
      localStringBuilder1.append(paramTypedValue);
      throw new Resources.NotFoundException(localStringBuilder1.toString());
    }
    String str = paramTypedValue.string.toString();
    if (!str.startsWith("res/"))
    {
      if (paramFontCallback != null)
        paramFontCallback.callbackFailAsync(-3, paramHandler);
      return null;
    }
    Typeface localTypeface1 = TypefaceCompat.findFromCache(paramResources, paramInt1, paramInt2);
    if (localTypeface1 != null)
    {
      if (paramFontCallback != null)
        paramFontCallback.callbackSuccessAsync(localTypeface1, paramHandler);
      return localTypeface1;
    }
    try
    {
      if (str.toLowerCase().endsWith(".xml"))
      {
        FontResourcesParserCompat.FamilyResourceEntry localFamilyResourceEntry = FontResourcesParserCompat.parse(paramResources.getXml(paramInt1), paramResources);
        if (localFamilyResourceEntry == null)
        {
          Log.e("ResourcesCompat", "Failed to find font-family tag");
          if (paramFontCallback == null)
            break label365;
          paramFontCallback.callbackFailAsync(-3, paramHandler);
          break label365;
        }
        return TypefaceCompat.createFromResourcesFamilyXml(paramContext, localFamilyResourceEntry, paramResources, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
      }
      Typeface localTypeface2 = TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, paramInt1, str, paramInt2);
      if (paramFontCallback != null)
        if (localTypeface2 != null)
          paramFontCallback.callbackSuccessAsync(localTypeface2, paramHandler);
        else
          paramFontCallback.callbackFailAsync(-3, paramHandler);
      return localTypeface2;
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("Failed to read xml resource ");
      localStringBuilder3.append(str);
      Log.e("ResourcesCompat", localStringBuilder3.toString(), localIOException);
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Failed to parse xml resource ");
      localStringBuilder2.append(str);
      Log.e("ResourcesCompat", localStringBuilder2.toString(), localXmlPullParserException);
    }
    if (paramFontCallback != null)
      paramFontCallback.callbackFailAsync(-3, paramHandler);
    return null;
    label365: return null;
  }

  public static abstract class FontCallback
  {
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public final void callbackFailAsync(final int paramInt, @Nullable Handler paramHandler)
    {
      if (paramHandler == null)
        paramHandler = new Handler(Looper.getMainLooper());
      paramHandler.post(new Runnable()
      {
        public void run()
        {
          ResourcesCompat.FontCallback.this.onFontRetrievalFailed(paramInt);
        }
      });
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public final void callbackSuccessAsync(final Typeface paramTypeface, @Nullable Handler paramHandler)
    {
      if (paramHandler == null)
        paramHandler = new Handler(Looper.getMainLooper());
      paramHandler.post(new Runnable()
      {
        public void run()
        {
          ResourcesCompat.FontCallback.this.onFontRetrieved(paramTypeface);
        }
      });
    }

    public abstract void onFontRetrievalFailed(int paramInt);

    public abstract void onFontRetrieved(@NonNull Typeface paramTypeface);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.ResourcesCompat
 * JD-Core Version:    0.6.1
 */