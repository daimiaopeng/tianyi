package com.lahm.library;

import android.text.TextUtils;

public class EmulatorCheckUtil
{
  public static final EmulatorCheckUtil getSingleInstance()
  {
    return SingletonHolder.INSTANCE;
  }

  public boolean readSysProperty()
  {
    String str1 = CommandUtil.getSingleInstance().getProperty("gsm.version.baseband");
    boolean bool1 = TextUtils.isEmpty(str1);
    boolean bool2;
    if ((str1 != null) && (str1.contains("1.0.0.0")))
      bool2 = true;
    else
      bool2 = false;
    int i;
    if ((bool2 | bool1))
      i = 1;
    else
      i = 0;
    String str2 = CommandUtil.getSingleInstance().getProperty("ro.build.flavor");
    boolean bool3 = TextUtils.isEmpty(str2);
    boolean bool4;
    if ((str2 != null) && (str2.contains("vbox")))
      bool4 = true;
    else
      bool4 = false;
    if ((bool4 | bool3))
      i++;
    String str3 = CommandUtil.getSingleInstance().getProperty("ro.product.board");
    boolean bool5 = TextUtils.isEmpty(str3);
    boolean bool6;
    if ((str3 != null) && (str3.contains("android")))
      bool6 = true;
    else
      bool6 = false;
    if ((bool5 | bool6))
      i++;
    String str4 = CommandUtil.getSingleInstance().getProperty("ro.board.platform");
    boolean bool7 = TextUtils.isEmpty(str4);
    boolean bool8;
    if ((str4 != null) && (str4.contains("android")))
      bool8 = true;
    else
      bool8 = false;
    if ((bool7 | bool8))
      i++;
    if ((!TextUtils.isEmpty(str3)) && (!TextUtils.isEmpty(str4)) && (!str3.equals(str4)))
      i++;
    if (TextUtils.isEmpty(CommandUtil.getSingleInstance().exec("cat /proc/self/cgroup")))
      i++;
    boolean bool9 = false;
    if (i > 2)
      bool9 = true;
    return bool9;
  }

  private static class SingletonHolder
  {
    private static final EmulatorCheckUtil INSTANCE = new EmulatorCheckUtil(null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.lahm.library.EmulatorCheckUtil
 * JD-Core Version:    0.6.1
 */