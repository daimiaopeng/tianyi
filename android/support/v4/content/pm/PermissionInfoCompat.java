package android.support.v4.content.pm;

import android.annotation.SuppressLint;
import android.content.pm.PermissionInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionInfoCompat
{
  @SuppressLint({"WrongConstant"})
  public static int getProtection(@NonNull PermissionInfo paramPermissionInfo)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramPermissionInfo.getProtection();
    return 0xF & paramPermissionInfo.protectionLevel;
  }

  @SuppressLint({"WrongConstant"})
  public static int getProtectionFlags(@NonNull PermissionInfo paramPermissionInfo)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramPermissionInfo.getProtectionFlags();
    return 0xFFFFFFF0 & paramPermissionInfo.protectionLevel;
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static @interface Protection
  {
  }

  @Retention(RetentionPolicy.SOURCE)
  @SuppressLint({"UniqueConstants"})
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static @interface ProtectionFlags
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.pm.PermissionInfoCompat
 * JD-Core Version:    0.6.1
 */