package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View.OnTouchListener;
import android.widget.PopupMenu;

public final class PopupMenuCompat
{
  @Nullable
  public static View.OnTouchListener getDragToOpenListener(@NonNull Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return ((PopupMenu)paramObject).getDragToOpenListener();
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.PopupMenuCompat
 * JD-Core Version:    0.6.1
 */