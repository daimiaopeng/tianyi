package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R.styleable;
import android.util.AttributeSet;
import android.util.Xml;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
final class GradientColorInflaterCompat
{
  private static final int TILE_MODE_CLAMP = 0;
  private static final int TILE_MODE_MIRROR = 2;
  private static final int TILE_MODE_REPEAT = 1;

  private static ColorStops checkColors(@Nullable ColorStops paramColorStops, @ColorInt int paramInt1, @ColorInt int paramInt2, boolean paramBoolean, @ColorInt int paramInt3)
  {
    if (paramColorStops != null)
      return paramColorStops;
    if (paramBoolean)
      return new ColorStops(paramInt1, paramInt3, paramInt2);
    return new ColorStops(paramInt1, paramInt2);
  }

  static Shader createFromXml(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @Nullable Resources.Theme paramTheme)
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

  static Shader createFromXmlInner(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme)
  {
    String str = paramXmlPullParser.getName();
    if (!str.equals("gradient"))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramXmlPullParser.getPositionDescription());
      localStringBuilder.append(": invalid gradient color tag ");
      localStringBuilder.append(str);
      throw new XmlPullParserException(localStringBuilder.toString());
    }
    TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientColor);
    float f1 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "startX", R.styleable.GradientColor_android_startX, 0.0F);
    float f2 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "startY", R.styleable.GradientColor_android_startY, 0.0F);
    float f3 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "endX", R.styleable.GradientColor_android_endX, 0.0F);
    float f4 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "endY", R.styleable.GradientColor_android_endY, 0.0F);
    float f5 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "centerX", R.styleable.GradientColor_android_centerX, 0.0F);
    float f6 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "centerY", R.styleable.GradientColor_android_centerY, 0.0F);
    int i = TypedArrayUtils.getNamedInt(localTypedArray, paramXmlPullParser, "type", R.styleable.GradientColor_android_type, 0);
    int j = TypedArrayUtils.getNamedColor(localTypedArray, paramXmlPullParser, "startColor", R.styleable.GradientColor_android_startColor, 0);
    boolean bool = TypedArrayUtils.hasAttribute(paramXmlPullParser, "centerColor");
    int k = TypedArrayUtils.getNamedColor(localTypedArray, paramXmlPullParser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
    int m = TypedArrayUtils.getNamedColor(localTypedArray, paramXmlPullParser, "endColor", R.styleable.GradientColor_android_endColor, 0);
    int n = TypedArrayUtils.getNamedInt(localTypedArray, paramXmlPullParser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
    float f7 = TypedArrayUtils.getNamedFloat(localTypedArray, paramXmlPullParser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0F);
    localTypedArray.recycle();
    ColorStops localColorStops = checkColors(inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme), j, m, bool, k);
    switch (i)
    {
    default:
      LinearGradient localLinearGradient = new LinearGradient(f1, f2, f3, f4, localColorStops.mColors, localColorStops.mOffsets, parseTileMode(n));
      return localLinearGradient;
    case 2:
      return new SweepGradient(f5, f6, localColorStops.mColors, localColorStops.mOffsets);
    case 1:
    }
    if (f7 <= 0.0F)
      throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    RadialGradient localRadialGradient = new RadialGradient(f5, f6, f7, localColorStops.mColors, localColorStops.mOffsets, parseTileMode(n));
    return localRadialGradient;
  }

  private static ColorStops inflateChildElements(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme)
  {
    int i = 1 + paramXmlPullParser.getDepth();
    ArrayList localArrayList1 = new ArrayList(20);
    ArrayList localArrayList2 = new ArrayList(20);
    while (true)
    {
      int j = paramXmlPullParser.next();
      if (j == 1)
        break label252;
      int k = paramXmlPullParser.getDepth();
      if ((k < i) && (j == 3))
        break label252;
      if ((j == 2) && (k <= i) && (paramXmlPullParser.getName().equals("item")))
      {
        TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientColorItem);
        boolean bool1 = localTypedArray.hasValue(R.styleable.GradientColorItem_android_color);
        boolean bool2 = localTypedArray.hasValue(R.styleable.GradientColorItem_android_offset);
        if ((!bool1) || (!bool2))
          break;
        int m = localTypedArray.getColor(R.styleable.GradientColorItem_android_color, 0);
        float f = localTypedArray.getFloat(R.styleable.GradientColorItem_android_offset, 0.0F);
        localTypedArray.recycle();
        localArrayList2.add(Integer.valueOf(m));
        localArrayList1.add(Float.valueOf(f));
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramXmlPullParser.getPositionDescription());
    localStringBuilder.append(": <item> tag requires a 'color' attribute and a 'offset' ");
    localStringBuilder.append("attribute!");
    throw new XmlPullParserException(localStringBuilder.toString());
    label252: if (localArrayList2.size() > 0)
      return new ColorStops(localArrayList2, localArrayList1);
    return null;
  }

  private static Shader.TileMode parseTileMode(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return Shader.TileMode.CLAMP;
    case 2:
      return Shader.TileMode.MIRROR;
    case 1:
    }
    return Shader.TileMode.REPEAT;
  }

  static final class ColorStops
  {
    final int[] mColors;
    final float[] mOffsets;

    ColorStops(@ColorInt int paramInt1, @ColorInt int paramInt2)
    {
      this.mColors = new int[] { paramInt1, paramInt2 };
      this.mOffsets = new float[] { 0.0F, 1.0F };
    }

    ColorStops(@ColorInt int paramInt1, @ColorInt int paramInt2, @ColorInt int paramInt3)
    {
      this.mColors = new int[] { paramInt1, paramInt2, paramInt3 };
      this.mOffsets = new float[] { 0.0F, 0.5F, 1.0F };
    }

    ColorStops(@NonNull List<Integer> paramList, @NonNull List<Float> paramList1)
    {
      int i = paramList.size();
      this.mColors = new int[i];
      this.mOffsets = new float[i];
      for (int j = 0; j < i; j++)
      {
        this.mColors[j] = ((Integer)paramList.get(j)).intValue();
        this.mOffsets[j] = ((Float)paramList1.get(j)).floatValue();
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.GradientColorInflaterCompat
 * JD-Core Version:    0.6.1
 */