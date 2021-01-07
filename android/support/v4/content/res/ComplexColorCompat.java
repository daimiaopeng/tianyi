package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public final class ComplexColorCompat
{
  private static final String LOG_TAG = "ComplexColorCompat";
  private int mColor;
  private final ColorStateList mColorStateList;
  private final Shader mShader;

  private ComplexColorCompat(Shader paramShader, ColorStateList paramColorStateList, @ColorInt int paramInt)
  {
    this.mShader = paramShader;
    this.mColorStateList = paramColorStateList;
    this.mColor = paramInt;
  }

  @NonNull
  private static ComplexColorCompat createFromXml(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme)
  {
    XmlResourceParser localXmlResourceParser = paramResources.getXml(paramInt);
    AttributeSet localAttributeSet = Xml.asAttributeSet(localXmlResourceParser);
    int i;
    do
    {
      i = localXmlResourceParser.next();
      j = 1;
    }
    while ((i != 2) && (i != j));
    if (i != 2)
      throw new XmlPullParserException("No start tag found");
    String str = localXmlResourceParser.getName();
    int k = str.hashCode();
    if (k != 89650992)
    {
      if ((k == 1191572447) && (str.equals("selector")))
      {
        j = 0;
        break label119;
      }
    }
    else
      if (str.equals("gradient"))
        break label119;
    int j = -1;
    switch (j)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localXmlResourceParser.getPositionDescription());
      localStringBuilder.append(": unsupported complex color tag ");
      localStringBuilder.append(str);
      throw new XmlPullParserException(localStringBuilder.toString());
    case 1:
      label119: return from(GradientColorInflaterCompat.createFromXmlInner(paramResources, localXmlResourceParser, localAttributeSet, paramTheme));
    case 0:
    }
    return from(ColorStateListInflaterCompat.createFromXmlInner(paramResources, localXmlResourceParser, localAttributeSet, paramTheme));
  }

  static ComplexColorCompat from(@ColorInt int paramInt)
  {
    return new ComplexColorCompat(null, null, paramInt);
  }

  static ComplexColorCompat from(@NonNull ColorStateList paramColorStateList)
  {
    return new ComplexColorCompat(null, paramColorStateList, paramColorStateList.getDefaultColor());
  }

  static ComplexColorCompat from(@NonNull Shader paramShader)
  {
    return new ComplexColorCompat(paramShader, null, 0);
  }

  @Nullable
  public static ComplexColorCompat inflate(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme)
  {
    try
    {
      ComplexColorCompat localComplexColorCompat = createFromXml(paramResources, paramInt, paramTheme);
      return localComplexColorCompat;
    }
    catch (Exception localException)
    {
      Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", localException);
    }
    return null;
  }

  @ColorInt
  public int getColor()
  {
    return this.mColor;
  }

  @Nullable
  public Shader getShader()
  {
    return this.mShader;
  }

  public boolean isGradient()
  {
    boolean bool;
    if (this.mShader != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isStateful()
  {
    boolean bool;
    if ((this.mShader == null) && (this.mColorStateList != null) && (this.mColorStateList.isStateful()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean onStateChanged(int[] paramArrayOfInt)
  {
    if (isStateful())
    {
      int i = this.mColorStateList.getColorForState(paramArrayOfInt, this.mColorStateList.getDefaultColor());
      if (i != this.mColor)
      {
        bool = true;
        this.mColor = i;
        break label43;
      }
    }
    boolean bool = false;
    label43: return bool;
  }

  public void setColor(@ColorInt int paramInt)
  {
    this.mColor = paramInt;
  }

  public boolean willDraw()
  {
    boolean bool;
    if ((!isGradient()) && (this.mColor == 0))
      bool = false;
    else
      bool = true;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.ComplexColorCompat
 * JD-Core Version:    0.6.1
 */