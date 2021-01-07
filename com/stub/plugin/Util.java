package com.stub.plugin;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class Util
{
  public static void setThemeWithSdkVersion(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 14)
      paramActivity.setTheme(16973941);
    while (true)
    {
      WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
      localLayoutParams.alpha = 0.0F;
      paramActivity.getWindow().setAttributes(localLayoutParams);
      return;
      paramActivity.setTheme(16973835);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.Util
 * JD-Core Version:    0.6.1
 */