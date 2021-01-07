package com.qihoo.jiagutracker.listen;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class OnTouchListenerCallback
{
  private static OnTouchListenerCallback sInstance = null;

  static
  {
    StubApp.interface11(2745);
  }

  public static native OnTouchListenerCallback getInstance();

  @SuppressLint({"ResourceType"})
  private native String getViewTree(View paramView);

  public native void onTouch(View paramView, MotionEvent paramMotionEvent, String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.listen.OnTouchListenerCallback
 * JD-Core Version:    0.6.1
 */