package android.support.v4.database;

import android.database.CursorWindow;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class CursorWindowCompat
{
  @NonNull
  public static CursorWindow create(@Nullable String paramString, long paramLong)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return new CursorWindow(paramString, paramLong);
    if (Build.VERSION.SDK_INT >= 15)
      return new CursorWindow(paramString);
    return new CursorWindow(false);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.database.CursorWindowCompat
 * JD-Core Version:    0.6.1
 */