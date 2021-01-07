package android.support.v4.view;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.view.Menu;
import android.view.MenuItem;

public final class MenuCompat
{
  @SuppressLint({"NewApi"})
  public static void setGroupDividerEnabled(Menu paramMenu, boolean paramBoolean)
  {
    if ((paramMenu instanceof SupportMenu))
      ((SupportMenu)paramMenu).setGroupDividerEnabled(paramBoolean);
    else if (Build.VERSION.SDK_INT >= 28)
      paramMenu.setGroupDividerEnabled(paramBoolean);
  }

  @Deprecated
  public static void setShowAsAction(MenuItem paramMenuItem, int paramInt)
  {
    paramMenuItem.setShowAsAction(paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.MenuCompat
 * JD-Core Version:    0.6.1
 */