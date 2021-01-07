package android.support.v4.os;

import android.content.res.Configuration;
import android.os.Build.VERSION;
import java.util.Locale;

public final class ConfigurationCompat
{
  public static LocaleListCompat getLocales(Configuration paramConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 24)
      return LocaleListCompat.wrap(paramConfiguration.getLocales());
    Locale[] arrayOfLocale = new Locale[1];
    arrayOfLocale[0] = paramConfiguration.locale;
    return LocaleListCompat.create(arrayOfLocale);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.ConfigurationCompat
 * JD-Core Version:    0.6.1
 */