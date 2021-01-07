package com.qihoo.jiagutracker;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.stub.StubApp;
import java.util.List;
import org.json.JSONObject;

@QVMProtect
public class ViewWatcher
{
  private static ViewInfo lastFocusView = null;
  private static ViewWatcher sInstance;

  static
  {
    StubApp.interface11(2740);
    sInstance = null;
  }

  public static native ViewWatcher getInstance();

  public static native int getScreenHeight(Context paramContext);

  public static native int getScreenWidth(Context paramContext);

  private static native List<ViewInfo> rTraverse(View paramView, String paramString);

  public static native void recordFocusView(Activity paramActivity);

  private static native List<ViewInfo> watch(ViewGroup paramViewGroup, String paramString);

  public static native JSONObject watchOver();

  private static native JSONObject watchViewTree(Activity paramActivity);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.ViewWatcher
 * JD-Core Version:    0.6.1
 */