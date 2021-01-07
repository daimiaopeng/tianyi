package com.qihoo.jiagutracker;

import android.app.Instrumentation;
import com.qihoo.jiagutracker.Instrument.InstrumentCallback;
import com.stub.StubApp;
import java.util.Set;

@QVMProtect
public class InstrumentationTrack
{
  private static InstrumentationTrack sInstance;
  public Set<InstrumentCallback> mCallbacks;
  private Instrumentation mInstrumentation;

  static
  {
    StubApp.interface11(2733);
  }

  private native boolean doHook();

  public static native InstrumentationTrack getInstance();

  public native boolean initInstrumentationTrack(Set<InstrumentCallback> paramSet);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.InstrumentationTrack
 * JD-Core Version:    0.6.1
 */