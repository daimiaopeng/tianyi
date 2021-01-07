package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import java.util.Locale;

public final class LocaleListCompat
{
  static final LocaleListInterface IMPL;
  private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

  static
  {
    if (Build.VERSION.SDK_INT >= 24)
      IMPL = new LocaleListCompatApi24Impl();
    else
      IMPL = new LocaleListCompatBaseImpl();
  }

  public static LocaleListCompat create(@NonNull Locale[] paramArrayOfLocale)
  {
    LocaleListCompat localLocaleListCompat = new LocaleListCompat();
    localLocaleListCompat.setLocaleListArray(paramArrayOfLocale);
    return localLocaleListCompat;
  }

  @NonNull
  public static LocaleListCompat forLanguageTags(@Nullable String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      String[] arrayOfString = paramString.split(",", -1);
      Locale[] arrayOfLocale = new Locale[arrayOfString.length];
      for (int i = 0; i < arrayOfLocale.length; i++)
      {
        Locale localLocale;
        if (Build.VERSION.SDK_INT >= 21)
          localLocale = Locale.forLanguageTag(arrayOfString[i]);
        else
          localLocale = LocaleHelper.forLanguageTag(arrayOfString[i]);
        arrayOfLocale[i] = localLocale;
      }
      LocaleListCompat localLocaleListCompat = new LocaleListCompat();
      localLocaleListCompat.setLocaleListArray(arrayOfLocale);
      return localLocaleListCompat;
    }
    return getEmptyLocaleList();
  }

  @NonNull
  @Size(min=1L)
  public static LocaleListCompat getAdjustedDefault()
  {
    if (Build.VERSION.SDK_INT >= 24)
      return wrap(LocaleList.getAdjustedDefault());
    Locale[] arrayOfLocale = new Locale[1];
    arrayOfLocale[0] = Locale.getDefault();
    return create(arrayOfLocale);
  }

  @NonNull
  @Size(min=1L)
  public static LocaleListCompat getDefault()
  {
    if (Build.VERSION.SDK_INT >= 24)
      return wrap(LocaleList.getDefault());
    Locale[] arrayOfLocale = new Locale[1];
    arrayOfLocale[0] = Locale.getDefault();
    return create(arrayOfLocale);
  }

  @NonNull
  public static LocaleListCompat getEmptyLocaleList()
  {
    return sEmptyLocaleList;
  }

  @RequiresApi(24)
  private void setLocaleList(LocaleList paramLocaleList)
  {
    int i = paramLocaleList.size();
    if (i > 0)
    {
      Locale[] arrayOfLocale = new Locale[i];
      for (int j = 0; j < i; j++)
        arrayOfLocale[j] = paramLocaleList.get(j);
      IMPL.setLocaleList(arrayOfLocale);
    }
  }

  private void setLocaleListArray(Locale[] paramArrayOfLocale)
  {
    IMPL.setLocaleList(paramArrayOfLocale);
  }

  @RequiresApi(24)
  public static LocaleListCompat wrap(Object paramObject)
  {
    LocaleListCompat localLocaleListCompat = new LocaleListCompat();
    if ((paramObject instanceof LocaleList))
      localLocaleListCompat.setLocaleList((LocaleList)paramObject);
    return localLocaleListCompat;
  }

  public boolean equals(Object paramObject)
  {
    return IMPL.equals(paramObject);
  }

  public Locale get(int paramInt)
  {
    return IMPL.get(paramInt);
  }

  public Locale getFirstMatch(String[] paramArrayOfString)
  {
    return IMPL.getFirstMatch(paramArrayOfString);
  }

  public int hashCode()
  {
    return IMPL.hashCode();
  }

  @IntRange(from=-1L)
  public int indexOf(Locale paramLocale)
  {
    return IMPL.indexOf(paramLocale);
  }

  public boolean isEmpty()
  {
    return IMPL.isEmpty();
  }

  @IntRange(from=0L)
  public int size()
  {
    return IMPL.size();
  }

  @NonNull
  public String toLanguageTags()
  {
    return IMPL.toLanguageTags();
  }

  public String toString()
  {
    return IMPL.toString();
  }

  @Nullable
  public Object unwrap()
  {
    return IMPL.getLocaleList();
  }

  @RequiresApi(24)
  static class LocaleListCompatApi24Impl
    implements LocaleListInterface
  {
    private LocaleList mLocaleList = new LocaleList(new Locale[0]);

    public boolean equals(Object paramObject)
    {
      return this.mLocaleList.equals(((LocaleListCompat)paramObject).unwrap());
    }

    public Locale get(int paramInt)
    {
      return this.mLocaleList.get(paramInt);
    }

    @Nullable
    public Locale getFirstMatch(String[] paramArrayOfString)
    {
      if (this.mLocaleList != null)
        return this.mLocaleList.getFirstMatch(paramArrayOfString);
      return null;
    }

    public Object getLocaleList()
    {
      return this.mLocaleList;
    }

    public int hashCode()
    {
      return this.mLocaleList.hashCode();
    }

    @IntRange(from=-1L)
    public int indexOf(Locale paramLocale)
    {
      return this.mLocaleList.indexOf(paramLocale);
    }

    public boolean isEmpty()
    {
      return this.mLocaleList.isEmpty();
    }

    public void setLocaleList(@NonNull Locale[] paramArrayOfLocale)
    {
      this.mLocaleList = new LocaleList(paramArrayOfLocale);
    }

    @IntRange(from=0L)
    public int size()
    {
      return this.mLocaleList.size();
    }

    public String toLanguageTags()
    {
      return this.mLocaleList.toLanguageTags();
    }

    public String toString()
    {
      return this.mLocaleList.toString();
    }
  }

  static class LocaleListCompatBaseImpl
    implements LocaleListInterface
  {
    private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);

    public boolean equals(Object paramObject)
    {
      return this.mLocaleList.equals(((LocaleListCompat)paramObject).unwrap());
    }

    public Locale get(int paramInt)
    {
      return this.mLocaleList.get(paramInt);
    }

    @Nullable
    public Locale getFirstMatch(String[] paramArrayOfString)
    {
      if (this.mLocaleList != null)
        return this.mLocaleList.getFirstMatch(paramArrayOfString);
      return null;
    }

    public Object getLocaleList()
    {
      return this.mLocaleList;
    }

    public int hashCode()
    {
      return this.mLocaleList.hashCode();
    }

    @IntRange(from=-1L)
    public int indexOf(Locale paramLocale)
    {
      return this.mLocaleList.indexOf(paramLocale);
    }

    public boolean isEmpty()
    {
      return this.mLocaleList.isEmpty();
    }

    public void setLocaleList(@NonNull Locale[] paramArrayOfLocale)
    {
      this.mLocaleList = new LocaleListHelper(paramArrayOfLocale);
    }

    @IntRange(from=0L)
    public int size()
    {
      return this.mLocaleList.size();
    }

    public String toLanguageTags()
    {
      return this.mLocaleList.toLanguageTags();
    }

    public String toString()
    {
      return this.mLocaleList.toString();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.LocaleListCompat
 * JD-Core Version:    0.6.1
 */