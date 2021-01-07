package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R.styleable;
import android.support.v4.provider.FontRequest;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class FontResourcesParserCompat
{
  private static final int DEFAULT_TIMEOUT_MILLIS = 500;
  public static final int FETCH_STRATEGY_ASYNC = 1;
  public static final int FETCH_STRATEGY_BLOCKING = 0;
  public static final int INFINITE_TIMEOUT_VALUE = -1;
  private static final int ITALIC = 1;
  private static final int NORMAL_WEIGHT = 400;

  private static int getType(TypedArray paramTypedArray, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramTypedArray.getType(paramInt);
    TypedValue localTypedValue = new TypedValue();
    paramTypedArray.getValue(paramInt, localTypedValue);
    return localTypedValue.type;
  }

  @Nullable
  public static FamilyResourceEntry parse(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
    if (i != 2)
      throw new XmlPullParserException("No start tag found");
    return readFamilies(paramXmlPullParser, paramResources);
  }

  public static List<List<byte[]>> readCerts(Resources paramResources, @ArrayRes int paramInt)
  {
    if (paramInt == 0)
      return Collections.emptyList();
    TypedArray localTypedArray = paramResources.obtainTypedArray(paramInt);
    while (true)
    {
      int i;
      try
      {
        if (localTypedArray.length() == 0)
        {
          List localList = Collections.emptyList();
          return localList;
        }
        ArrayList localArrayList = new ArrayList();
        if (getType(localTypedArray, 0) == 1)
        {
          i = 0;
          if (i < localTypedArray.length())
          {
            int j = localTypedArray.getResourceId(i, 0);
            if (j == 0)
              break label127;
            localArrayList.add(toByteArrayList(paramResources.getStringArray(j)));
            break label127;
          }
        }
        else
        {
          localArrayList.add(toByteArrayList(paramResources.getStringArray(paramInt)));
        }
        return localArrayList;
      }
      finally
      {
        localTypedArray.recycle();
      }
      label127: i++;
    }
  }

  @Nullable
  private static FamilyResourceEntry readFamilies(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    paramXmlPullParser.require(2, null, "font-family");
    if (paramXmlPullParser.getName().equals("font-family"))
      return readFamily(paramXmlPullParser, paramResources);
    skip(paramXmlPullParser);
    return null;
  }

  @Nullable
  private static FamilyResourceEntry readFamily(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    TypedArray localTypedArray = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.FontFamily);
    String str1 = localTypedArray.getString(R.styleable.FontFamily_fontProviderAuthority);
    String str2 = localTypedArray.getString(R.styleable.FontFamily_fontProviderPackage);
    String str3 = localTypedArray.getString(R.styleable.FontFamily_fontProviderQuery);
    int i = localTypedArray.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
    int j = localTypedArray.getInteger(R.styleable.FontFamily_fontProviderFetchStrategy, 1);
    int k = localTypedArray.getInteger(R.styleable.FontFamily_fontProviderFetchTimeout, 500);
    localTypedArray.recycle();
    if ((str1 != null) && (str2 != null) && (str3 != null))
    {
      while (paramXmlPullParser.next() != 3)
        skip(paramXmlPullParser);
      return new ProviderResourceEntry(new FontRequest(str1, str2, str3, readCerts(paramResources, i)), j, k);
    }
    ArrayList localArrayList = new ArrayList();
    while (paramXmlPullParser.next() != 3)
      if (paramXmlPullParser.getEventType() == 2)
        if (paramXmlPullParser.getName().equals("font"))
          localArrayList.add(readFont(paramXmlPullParser, paramResources));
        else
          skip(paramXmlPullParser);
    if (localArrayList.isEmpty())
      return null;
    return new FontFamilyFilesResourceEntry((FontFileResourceEntry[])localArrayList.toArray(new FontFileResourceEntry[localArrayList.size()]));
  }

  private static FontFileResourceEntry readFont(XmlPullParser paramXmlPullParser, Resources paramResources)
  {
    TypedArray localTypedArray = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.FontFamilyFont);
    int i;
    if (localTypedArray.hasValue(R.styleable.FontFamilyFont_fontWeight))
      i = R.styleable.FontFamilyFont_fontWeight;
    else
      i = R.styleable.FontFamilyFont_android_fontWeight;
    int j = localTypedArray.getInt(i, 400);
    int k;
    if (localTypedArray.hasValue(R.styleable.FontFamilyFont_fontStyle))
      k = R.styleable.FontFamilyFont_fontStyle;
    else
      k = R.styleable.FontFamilyFont_android_fontStyle;
    boolean bool;
    if (1 == localTypedArray.getInt(k, 0))
      bool = true;
    else
      bool = false;
    int m;
    if (localTypedArray.hasValue(R.styleable.FontFamilyFont_ttcIndex))
      m = R.styleable.FontFamilyFont_ttcIndex;
    else
      m = R.styleable.FontFamilyFont_android_ttcIndex;
    int n;
    if (localTypedArray.hasValue(R.styleable.FontFamilyFont_fontVariationSettings))
      n = R.styleable.FontFamilyFont_fontVariationSettings;
    else
      n = R.styleable.FontFamilyFont_android_fontVariationSettings;
    String str1 = localTypedArray.getString(n);
    int i1 = localTypedArray.getInt(m, 0);
    int i2;
    if (localTypedArray.hasValue(R.styleable.FontFamilyFont_font))
      i2 = R.styleable.FontFamilyFont_font;
    else
      i2 = R.styleable.FontFamilyFont_android_font;
    int i3 = localTypedArray.getResourceId(i2, 0);
    String str2 = localTypedArray.getString(i2);
    localTypedArray.recycle();
    while (paramXmlPullParser.next() != 3)
      skip(paramXmlPullParser);
    FontFileResourceEntry localFontFileResourceEntry = new FontFileResourceEntry(str2, j, bool, str1, i1, i3);
    return localFontFileResourceEntry;
  }

  private static void skip(XmlPullParser paramXmlPullParser)
  {
    int i = 1;
    while (i > 0)
      switch (paramXmlPullParser.next())
      {
      default:
        break;
      case 3:
        i--;
        break;
      case 2:
        i++;
      }
  }

  private static List<byte[]> toByteArrayList(String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      localArrayList.add(Base64.decode(paramArrayOfString[j], 0));
    return localArrayList;
  }

  public static abstract interface FamilyResourceEntry
  {
  }

  @Retention(RetentionPolicy.SOURCE)
  public static @interface FetchStrategy
  {
  }

  public static final class FontFamilyFilesResourceEntry
    implements FontResourcesParserCompat.FamilyResourceEntry
  {

    @NonNull
    private final FontResourcesParserCompat.FontFileResourceEntry[] mEntries;

    public FontFamilyFilesResourceEntry(@NonNull FontResourcesParserCompat.FontFileResourceEntry[] paramArrayOfFontFileResourceEntry)
    {
      this.mEntries = paramArrayOfFontFileResourceEntry;
    }

    @NonNull
    public FontResourcesParserCompat.FontFileResourceEntry[] getEntries()
    {
      return this.mEntries;
    }
  }

  public static final class FontFileResourceEntry
  {

    @NonNull
    private final String mFileName;
    private boolean mItalic;
    private int mResourceId;
    private int mTtcIndex;
    private String mVariationSettings;
    private int mWeight;

    public FontFileResourceEntry(@NonNull String paramString1, int paramInt1, boolean paramBoolean, @Nullable String paramString2, int paramInt2, int paramInt3)
    {
      this.mFileName = paramString1;
      this.mWeight = paramInt1;
      this.mItalic = paramBoolean;
      this.mVariationSettings = paramString2;
      this.mTtcIndex = paramInt2;
      this.mResourceId = paramInt3;
    }

    @NonNull
    public String getFileName()
    {
      return this.mFileName;
    }

    public int getResourceId()
    {
      return this.mResourceId;
    }

    public int getTtcIndex()
    {
      return this.mTtcIndex;
    }

    @Nullable
    public String getVariationSettings()
    {
      return this.mVariationSettings;
    }

    public int getWeight()
    {
      return this.mWeight;
    }

    public boolean isItalic()
    {
      return this.mItalic;
    }
  }

  public static final class ProviderResourceEntry
    implements FontResourcesParserCompat.FamilyResourceEntry
  {

    @NonNull
    private final FontRequest mRequest;
    private final int mStrategy;
    private final int mTimeoutMs;

    public ProviderResourceEntry(@NonNull FontRequest paramFontRequest, int paramInt1, int paramInt2)
    {
      this.mRequest = paramFontRequest;
      this.mStrategy = paramInt1;
      this.mTimeoutMs = paramInt2;
    }

    public int getFetchStrategy()
    {
      return this.mStrategy;
    }

    @NonNull
    public FontRequest getRequest()
    {
      return this.mRequest;
    }

    public int getTimeout()
    {
      return this.mTimeoutMs;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.FontResourcesParserCompat
 * JD-Core Version:    0.6.1
 */