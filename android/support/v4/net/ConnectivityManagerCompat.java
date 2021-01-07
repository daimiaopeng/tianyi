package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat
{
  public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
  public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
  public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

  @Nullable
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static NetworkInfo getNetworkInfoFromBroadcast(@NonNull ConnectivityManager paramConnectivityManager, @NonNull Intent paramIntent)
  {
    NetworkInfo localNetworkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    if (localNetworkInfo != null)
      return paramConnectivityManager.getNetworkInfo(localNetworkInfo.getType());
    return null;
  }

  public static int getRestrictBackgroundStatus(@NonNull ConnectivityManager paramConnectivityManager)
  {
    if (Build.VERSION.SDK_INT >= 24)
      return paramConnectivityManager.getRestrictBackgroundStatus();
    return 3;
  }

  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static boolean isActiveNetworkMetered(@NonNull ConnectivityManager paramConnectivityManager)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return paramConnectivityManager.isActiveNetworkMetered();
    NetworkInfo localNetworkInfo = paramConnectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return true;
    switch (localNetworkInfo.getType())
    {
    case 8:
    default:
      return true;
    case 1:
    case 7:
    case 9:
      return false;
    case 0:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    return true;
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface RestrictBackgroundStatus
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.net.ConnectivityManagerCompat
 * JD-Core Version:    0.6.1
 */