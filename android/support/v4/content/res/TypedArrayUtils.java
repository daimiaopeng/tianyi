package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypedArrayUtils
{
  private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

  public static int getAttr(@NonNull Context paramContext, int paramInt1, int paramInt2)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(paramInt1, localTypedValue, true);
    if (localTypedValue.resourceId != 0)
      return paramInt1;
    return paramInt2;
  }

  public static boolean getBoolean(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2, boolean paramBoolean)
  {
    return paramTypedArray.getBoolean(paramInt1, paramTypedArray.getBoolean(paramInt2, paramBoolean));
  }

  @Nullable
  public static Drawable getDrawable(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2)
  {
    Drawable localDrawable = paramTypedArray.getDrawable(paramInt1);
    if (localDrawable == null)
      localDrawable = paramTypedArray.getDrawable(paramInt2);
    return localDrawable;
  }

  public static int getInt(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2, int paramInt3)
  {
    return paramTypedArray.getInt(paramInt1, paramTypedArray.getInt(paramInt2, paramInt3));
  }

  public static boolean getNamedBoolean(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, @StyleableRes int paramInt, boolean paramBoolean)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return paramBoolean;
    return paramTypedArray.getBoolean(paramInt, paramBoolean);
  }

  @ColorInt
  public static int getNamedColor(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, @StyleableRes int paramInt1, @ColorInt int paramInt2)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return paramInt2;
    return paramTypedArray.getColor(paramInt1, paramInt2);
  }

  public static ComplexColorCompat getNamedComplexColor(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @Nullable Resources.Theme paramTheme, @NonNull String paramString, @StyleableRes int paramInt1, @ColorInt int paramInt2)
  {
    if (hasAttribute(paramXmlPullParser, paramString))
    {
      TypedValue localTypedValue = new TypedValue();
      paramTypedArray.getValue(paramInt1, localTypedValue);
      if ((localTypedValue.type >= 28) && (localTypedValue.type <= 31))
        return ComplexColorCompat.from(localTypedValue.data);
      ComplexColorCompat localComplexColorCompat = ComplexColorCompat.inflate(paramTypedArray.getResources(), paramTypedArray.getResourceId(paramInt1, 0), paramTheme);
      if (localComplexColorCompat != null)
        return localComplexColorCompat;
    }
    return ComplexColorCompat.from(paramInt2);
  }

  public static float getNamedFloat(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, @StyleableRes int paramInt, float paramFloat)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return paramFloat;
    return paramTypedArray.getFloat(paramInt, paramFloat);
  }

  public static int getNamedInt(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, @StyleableRes int paramInt1, int paramInt2)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return paramInt2;
    return paramTypedArray.getInt(paramInt1, paramInt2);
  }

  @AnyRes
  public static int getNamedResourceId(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, @StyleableRes int paramInt1, @AnyRes int paramInt2)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return paramInt2;
    return paramTypedArray.getResourceId(paramInt1, paramInt2);
  }

  @Nullable
  public static String getNamedString(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, @StyleableRes int paramInt)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return null;
    return paramTypedArray.getString(paramInt);
  }

  @AnyRes
  public static int getResourceId(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2, @AnyRes int paramInt3)
  {
    return paramTypedArray.getResourceId(paramInt1, paramTypedArray.getResourceId(paramInt2, paramInt3));
  }

  @Nullable
  public static String getString(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2)
  {
    String str = paramTypedArray.getString(paramInt1);
    if (str == null)
      str = paramTypedArray.getString(paramInt2);
    return str;
  }

  @Nullable
  public static CharSequence getText(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2)
  {
    CharSequence localCharSequence = paramTypedArray.getText(paramInt1);
    if (localCharSequence == null)
      localCharSequence = paramTypedArray.getText(paramInt2);
    return localCharSequence;
  }

  @Nullable
  public static CharSequence[] getTextArray(@NonNull TypedArray paramTypedArray, @StyleableRes int paramInt1, @StyleableRes int paramInt2)
  {
    CharSequence[] arrayOfCharSequence = paramTypedArray.getTextArray(paramInt1);
    if (arrayOfCharSequence == null)
      arrayOfCharSequence = paramTypedArray.getTextArray(paramInt2);
    return arrayOfCharSequence;
  }

  public static boolean hasAttribute(@NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString)
  {
    boolean bool;
    if (paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", paramString) != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  @NonNull
  public static TypedArray obtainAttributes(@NonNull Resources paramResources, @Nullable Resources.Theme paramTheme, @NonNull AttributeSet paramAttributeSet, @NonNull int[] paramArrayOfInt)
  {
    if (paramTheme == null)
      return paramResources.obtainAttributes(paramAttributeSet, paramArrayOfInt);
    return paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, 0, 0);
  }

  @Nullable
  public static TypedValue peekNamedValue(@NonNull TypedArray paramTypedArray, @NonNull XmlPullParser paramXmlPullParser, @NonNull String paramString, int paramInt)
  {
    if (!hasAttribute(paramXmlPullParser, paramString))
      return null;
    return paramTypedArray.peekValue(paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.TypedArrayUtils
 * JD-Core Version:    0.6.1
 */