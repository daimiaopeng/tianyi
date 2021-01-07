package com.qihoo.jiagutracker;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.stub.StubApp;

@QVMProtect
public class JGActivityLifecycleCallbacks
  implements Application.ActivityLifecycleCallbacks
{
  private static JGActivityLifecycleCallbacks sInstance;
  private ViewWatcher mViewWatcher = null;

  static
  {
    StubApp.interface11(2734);
  }

  public static native JGActivityLifecycleCallbacks getInstance();

  public native void onActivityCreated(Activity paramActivity, Bundle paramBundle);

  public native void onActivityDestroyed(Activity paramActivity);

  public native void onActivityPaused(Activity paramActivity);

  public native void onActivityResumed(Activity paramActivity);

  public native void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle);

  public native void onActivityStarted(Activity paramActivity);

  public native void onActivityStopped(Activity paramActivity);

  public native void setViewWatcher(ViewWatcher paramViewWatcher);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.JGActivityLifecycleCallbacks
 * JD-Core Version:    0.6.1
 */