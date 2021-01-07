package android.support.v4.content;

import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class IntentCompat
{
  public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
  public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
  public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";

  @NonNull
  public static Intent makeMainSelectorActivity(@NonNull String paramString1, @NonNull String paramString2)
  {
    if (Build.VERSION.SDK_INT >= 15)
      return Intent.makeMainSelectorActivity(paramString1, paramString2);
    Intent localIntent = new Intent(paramString1);
    localIntent.addCategory(paramString2);
    return localIntent;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.IntentCompat
 * JD-Core Version:    0.6.1
 */