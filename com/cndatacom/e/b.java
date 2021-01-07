package com.cndatacom.e;

import android.os.Build;
import android.text.TextUtils;

public class b
{
  public static boolean a()
  {
    String str = Build.SERIAL;
    boolean bool;
    if ((!TextUtils.isEmpty(str)) && (!str.equalsIgnoreCase("unknown")) && (!str.equalsIgnoreCase("android")))
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.b
 * JD-Core Version:    0.6.1
 */