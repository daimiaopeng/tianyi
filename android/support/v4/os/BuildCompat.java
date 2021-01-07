package android.support.v4.os;

import android.os.Build.VERSION;

public class BuildCompat
{
  @Deprecated
  public static boolean isAtLeastN()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 24)
      bool = true;
    else
      bool = false;
    return bool;
  }

  @Deprecated
  public static boolean isAtLeastNMR1()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 25)
      bool = true;
    else
      bool = false;
    return bool;
  }

  @Deprecated
  public static boolean isAtLeastO()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 26)
      bool = true;
    else
      bool = false;
    return bool;
  }

  @Deprecated
  public static boolean isAtLeastOMR1()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 27)
      bool = true;
    else
      bool = false;
    return bool;
  }

  @Deprecated
  public static boolean isAtLeastP()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 28)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static boolean isAtLeastQ()
  {
    int i = Build.VERSION.CODENAME.length();
    int j = 1;
    if ((i != j) || (Build.VERSION.CODENAME.charAt(0) < 'Q') || (Build.VERSION.CODENAME.charAt(0) > 'Z'))
      j = 0;
    return j;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.BuildCompat
 * JD-Core Version:    0.6.1
 */