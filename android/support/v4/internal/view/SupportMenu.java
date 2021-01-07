package android.support.v4.internal.view;

import android.support.annotation.RestrictTo;
import android.view.Menu;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface SupportMenu extends Menu
{
  public static final int CATEGORY_MASK = -65536;
  public static final int CATEGORY_SHIFT = 16;
  public static final int FLAG_KEEP_OPEN_ON_SUBMENU_OPENED = 4;
  public static final int SUPPORTED_MODIFIERS_MASK = 69647;
  public static final int USER_MASK = 65535;
  public static final int USER_SHIFT;

  public abstract void setGroupDividerEnabled(boolean paramBoolean);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.internal.view.SupportMenu
 * JD-Core Version:    0.6.1
 */