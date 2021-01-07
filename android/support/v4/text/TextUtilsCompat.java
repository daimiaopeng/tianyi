package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

public final class TextUtilsCompat
{
  private static final String ARAB_SCRIPT_SUBTAG = "Arab";
  private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
  private static final Locale ROOT = new Locale("", "");

  private static int getLayoutDirectionFromFirstChar(@NonNull Locale paramLocale)
  {
    switch (Character.getDirectionality(paramLocale.getDisplayName(paramLocale).charAt(0)))
    {
    default:
      return 0;
    case 1:
    case 2:
    }
    return 1;
  }

  public static int getLayoutDirectionFromLocale(@Nullable Locale paramLocale)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return TextUtils.getLayoutDirectionFromLocale(paramLocale);
    if ((paramLocale != null) && (!paramLocale.equals(ROOT)))
    {
      String str = ICUCompat.maximizeAndGetScript(paramLocale);
      if (str == null)
        return getLayoutDirectionFromFirstChar(paramLocale);
      if ((str.equalsIgnoreCase("Arab")) || (str.equalsIgnoreCase("Hebr")))
        return 1;
    }
    return 0;
  }

  @NonNull
  public static String htmlEncode(@NonNull String paramString)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return TextUtils.htmlEncode(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      if (c != '"')
      {
        if (c != '<')
        {
          if (c != '>')
            switch (c)
            {
            default:
              localStringBuilder.append(c);
              break;
            case '\'':
              localStringBuilder.append("&#39;");
              break;
            case '&':
              localStringBuilder.append("&amp;");
              break;
            }
          else
            localStringBuilder.append("&gt;");
        }
        else
          localStringBuilder.append("&lt;");
      }
      else
        localStringBuilder.append("&quot;");
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.text.TextUtilsCompat
 * JD-Core Version:    0.6.1
 */