package android.support.v4.os;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;

public class UserManagerCompat
{
  public static boolean isUserUnlocked(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24)
      return ((UserManager)paramContext.getSystemService(UserManager.class)).isUserUnlocked();
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.UserManagerCompat
 * JD-Core Version:    0.6.1
 */