package android.support.v4.os;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
abstract interface LocaleListInterface
{
  public abstract boolean equals(Object paramObject);

  public abstract Locale get(int paramInt);

  @Nullable
  public abstract Locale getFirstMatch(String[] paramArrayOfString);

  public abstract Object getLocaleList();

  public abstract int hashCode();

  @IntRange(from=-1L)
  public abstract int indexOf(Locale paramLocale);

  public abstract boolean isEmpty();

  public abstract void setLocaleList(@NonNull Locale[] paramArrayOfLocale);

  @IntRange(from=0L)
  public abstract int size();

  public abstract String toLanguageTags();

  public abstract String toString();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.LocaleListInterface
 * JD-Core Version:    0.6.1
 */