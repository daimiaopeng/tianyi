package com.qihoo.jiagutracker.Instrument;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;
import java.util.Set;

@QVMProtect
public class CustomInstrumentation extends Instrumentation
{
  private final Set<InstrumentCallback> mCallbacks;
  private final Instrumentation mInstrumentation;

  static
  {
    StubApp.interface11(2731);
  }

  public CustomInstrumentation(Instrumentation paramInstrumentation, Set<InstrumentCallback> paramSet)
  {
    this.mInstrumentation = paramInstrumentation;
    this.mCallbacks = paramSet;
  }

  private native void callActivityOnCreateProxy(Activity paramActivity, Bundle paramBundle, boolean paramBoolean);

  private native void newActivityProxy(ClassLoader paramClassLoader, String paramString, Intent paramIntent);

  private native void onDestroyProxy(Activity paramActivity, boolean paramBoolean);

  private native void onNewIntentProxy(Activity paramActivity, Intent paramIntent, boolean paramBoolean);

  private native void onPauseProxy(Activity paramActivity, boolean paramBoolean);

  private native void onResumeProxy(Activity paramActivity, boolean paramBoolean);

  private native void onStartProxy(Activity paramActivity, boolean paramBoolean);

  private native void onStopProxy(Activity paramActivity, boolean paramBoolean);

  public native void callActivityOnCreate(Activity paramActivity, Bundle paramBundle);

  public native void callActivityOnDestroy(Activity paramActivity);

  public native void callActivityOnNewIntent(Activity paramActivity, Intent paramIntent);

  public native void callActivityOnPause(Activity paramActivity);

  public native void callActivityOnRestart(Activity paramActivity);

  public native void callActivityOnRestoreInstanceState(Activity paramActivity, Bundle paramBundle);

  public native void callActivityOnResume(Activity paramActivity);

  public native void callActivityOnSaveInstanceState(Activity paramActivity, Bundle paramBundle);

  public native void callActivityOnStart(Activity paramActivity);

  public native void callActivityOnStop(Activity paramActivity);

  public native Activity newActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
    throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.Instrument.CustomInstrumentation
 * JD-Core Version:    0.6.1
 */