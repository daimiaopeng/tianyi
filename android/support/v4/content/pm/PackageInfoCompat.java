package android.support.v4.content.pm;

import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class PackageInfoCompat
{
  public static long getLongVersionCode(@NonNull PackageInfo paramPackageInfo)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramPackageInfo.getLongVersionCode();
    return paramPackageInfo.versionCode;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.pm.PackageInfoCompat
 * JD-Core Version:    0.6.1
 */