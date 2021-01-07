package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class ShortcutManagerCompat
{

  @VisibleForTesting
  static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

  @VisibleForTesting
  static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

  @NonNull
  public static Intent createShortcutResultIntent(@NonNull Context paramContext, @NonNull ShortcutInfoCompat paramShortcutInfoCompat)
  {
    Intent localIntent;
    if (Build.VERSION.SDK_INT >= 26)
      localIntent = ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).createShortcutResultIntent(paramShortcutInfoCompat.toShortcutInfo());
    else
      localIntent = null;
    if (localIntent == null)
      localIntent = new Intent();
    return paramShortcutInfoCompat.addToIntent(localIntent);
  }

  public static boolean isRequestPinShortcutSupported(@NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
    if (ContextCompat.checkSelfPermission(paramContext, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0)
      return false;
    Iterator localIterator = paramContext.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();
    while (localIterator.hasNext())
    {
      String str = ((ResolveInfo)localIterator.next()).activityInfo.permission;
      if ((TextUtils.isEmpty(str)) || ("com.android.launcher.permission.INSTALL_SHORTCUT".equals(str)))
        return true;
    }
    return false;
  }

  public static boolean requestPinShortcut(@NonNull Context paramContext, @NonNull ShortcutInfoCompat paramShortcutInfoCompat, @Nullable IntentSender paramIntentSender)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).requestPinShortcut(paramShortcutInfoCompat.toShortcutInfo(), paramIntentSender);
    if (!isRequestPinShortcutSupported(paramContext))
      return false;
    Intent localIntent = paramShortcutInfoCompat.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
    if (paramIntentSender == null)
    {
      paramContext.sendBroadcast(localIntent);
      return true;
    }
    paramContext.sendOrderedBroadcast(localIntent, null, new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        try
        {
          this.val$callback.sendIntent(paramAnonymousContext, 0, null, null, null);
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
        }
      }
    }
    , null, -1, null, null);
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.pm.ShortcutManagerCompat
 * JD-Core Version:    0.6.1
 */