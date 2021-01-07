package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public final class ConfigurationHelper
{
  public static int getDensityDpi(@NonNull Resources paramResources)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return paramResources.getConfiguration().densityDpi;
    return paramResources.getDisplayMetrics().densityDpi;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.res.ConfigurationHelper
 * JD-Core Version:    0.6.1
 */