package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R.attr;
import android.support.compat.R.styleable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public final class ColorStateListInflaterCompat
{
  private static final int DEFAULT_COLOR = -65536;

  @NonNull
  public static ColorStateList createFromXml(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @Nullable Resources.Theme paramTheme)
  {
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
    if (i != 2)
      throw new XmlPullParserException("No start tag found");
    return createFromXmlInner(paramResources, paramXmlPullParser, localAttributeSet, paramTheme);
  }

  @NonNull
  public static ColorStateList createFromXmlInner(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme)
  {
    String str = paramXmlPullParser.getName();
    if (!str.equals("selector"))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramXmlPullParser.getPositionDescription());
      localStringBuilder.append(": invalid color state list tag ");
      localStringBuilder.append(str);
      throw new XmlPullParserException(localStringBuilder.toString());
    }
    return inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
  }

  private static ColorStateList inflate(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme)
  {
    int i = paramXmlPullParser.getDepth();
    int j = 1;
    int k = i + j;
    Object localObject = new int[20][];
    int[] arrayOfInt1 = new int[localObject.length];
    int m = 0;
    while (true)
    {
      int n = paramXmlPullParser.next();
      if (n == j)
        break;
      int i1 = paramXmlPullParser.getDepth();
      if ((i1 < k) && (n == 3))
        break;
      if ((n == 2) && (i1 <= k) && (paramXmlPullParser.getName().equals("item")))
      {
        TypedArray localTypedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ColorStateListItem);
        int i2 = localTypedArray.getColor(R.styleable.ColorStateListItem_android_color, -65281);
        float f = 1.0F;
        if (localTypedArray.hasValue(R.styleable.ColorStateListItem_android_alpha))
          f = localTypedArray.getFloat(R.styleable.ColorStateListItem_android_alpha, f);
        else if (localTypedArray.hasValue(R.styleable.ColorStateListItem_alpha))
          f = localTypedArray.getFloat(R.styleable.ColorStateListItem_alpha, f);
        localTypedArray.recycle();
        int i3 = paramAttributeSet.getAttributeCount();
        int[] arrayOfInt3 = new int[i3];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3)
        {
          int i7 = paramAttributeSet.getAttributeNameResource(i4);
          if ((i7 != 16843173) && (i7 != 16843551) && (i7 != R.attr.alpha))
          {
            int i8 = i5 + 1;
            if (!paramAttributeSet.getAttributeBooleanValue(i4, false))
              i7 = -i7;
            arrayOfInt3[i5] = i7;
            i5 = i8;
          }
          i4++;
        }
        int[] arrayOfInt4 = StateSet.trimStateSet(arrayOfInt3, i5);
        int i6 = modulateColorAlpha(i2, f);
        if (m != 0)
          arrayOfInt4.length;
        arrayOfInt1 = GrowingArrayUtils.append(arrayOfInt1, m, i6);
        localObject = (int[][])GrowingArrayUtils.append((Object[])localObject, m, arrayOfInt4);
        m++;
      }
      j = 1;
    }
    int[] arrayOfInt2 = new int[m];
    int[][] arrayOfInt = new int[m][];
    System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, m);
    System.arraycopy(localObject, 0, arrayOfInt, 0, m);
    return new ColorStateList(arrayOfInt, arrayOfInt2);
  }

  @ColorInt
  private static int modulateColorAlpha(@ColorInt int paramInt, @FloatRange(from=0.0D, to=1.0D) float paramFloat)
  {
    int i = Math.round(paramFloat * Color.alpha(paramInt));
    return paramInt & 0xFFFFFF | i << 24;
  }

  private static TypedArray obtainAttributes(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    TypedArray localTypedArray;
    if (paramTheme == null)
      localTypedArray = paramResources.obtainAttributes(paramAttributeSet, paramArrayOfInt);
    else
      localTypedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, 0, 0);
    return localTypedArray;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.ColorStateListInflaterCompat
 * JD-Core Version:    0.6.1
 */