package com.qihoo.jiagutracker;

import android.app.Activity;
import android.os.Bundle;
import com.qihoo.jiagutracker.Instrument.InstrumentCallback;
import com.stub.StubApp;

@QVMProtect
public class JGInstrumentCallback extends InstrumentCallback
{
  public static Activity sActivity;
  private static JGInstrumentCallback sInstance;
  private ViewWatcher mViewWatcher;

  static
  {
    StubApp.interface11(2735);
  }

  private native void addActivity(Activity paramActivity);

  public static native JGInstrumentCallback getInstance();

  public native void afterOnCreate(Activity paramActivity, Bundle paramBundle);

  public native void afterOnDestroy(Activity paramActivity);

  public native void afterOnPause(Activity paramActivity);

  public native void afterOnResume(Activity paramActivity);

  public native void afterOnStart(Activity paramActivity);

  public native void afterOnStop(Activity paramActivity);

  public native void beforeOnCreate(Activity paramActivity, Bundle paramBundle);

  public native void beforeOnPause(Activity paramActivity);

  public native void beforeOnResume(Activity paramActivity);

  public native void beforeOnStart(Activity paramActivity);

  public native void setViewWatcher(ViewWatcher paramViewWatcher);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.JGInstrumentCallback
 * JD-Core Version:    0.6.1
 */