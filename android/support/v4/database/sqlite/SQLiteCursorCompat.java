package android.support.v4.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class SQLiteCursorCompat
{
  public static void setFillWindowForwardOnly(@NonNull SQLiteCursor paramSQLiteCursor, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 28)
      paramSQLiteCursor.setFillWindowForwardOnly(paramBoolean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.database.sqlite.SQLiteCursorCompat
 * JD-Core Version:    0.6.1
 */