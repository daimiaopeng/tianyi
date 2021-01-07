package com.qihoo.jiagutracker.Instrument;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public abstract class InstrumentCallback
{
  static
  {
    StubApp.interface11(2732);
  }

  public native void afterOnCreate(Activity paramActivity, Bundle paramBundle);

  public native void afterOnDestroy(Activity paramActivity);

  public native void afterOnNewIntent(Activity paramActivity, Intent paramIntent);

  public native void afterOnPause(Activity paramActivity);

  public native void afterOnResume(Activity paramActivity);

  public native void afterOnStart(Activity paramActivity);

  public native void afterOnStop(Activity paramActivity);

  public native void beforeOnCreate(Activity paramActivity, Bundle paramBundle);

  public native void beforeOnDestroy(Activity paramActivity);

  public native void beforeOnNewActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent);

  public native void beforeOnNewIntent(Activity paramActivity, Intent paramIntent);

  public native void beforeOnPause(Activity paramActivity);

  public native void beforeOnResume(Activity paramActivity);

  public native void beforeOnStart(Activity paramActivity);

  public native void beforeOnStop(Activity paramActivity);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.Instrument.InstrumentCallback
 * JD-Core Version:    0.6.1
 */