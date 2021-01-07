package com.qihoo.jiagutracker.listen;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class OnTouchListenerProxy
  implements View.OnTouchListener
{
  private String mClassName;
  private View.OnTouchListener mOnTouchListener;
  private OnTouchListenerCallback mOnTouchListenerCallback;

  static
  {
    StubApp.interface11(2746);
  }

  public OnTouchListenerProxy(View.OnTouchListener paramOnTouchListener, OnTouchListenerCallback paramOnTouchListenerCallback, String paramString)
  {
    this.mOnTouchListener = paramOnTouchListener;
    this.mOnTouchListenerCallback = paramOnTouchListenerCallback;
    this.mClassName = paramString;
  }

  public native boolean onTouch(View paramView, MotionEvent paramMotionEvent);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.listen.OnTouchListenerProxy
 * JD-Core Version:    0.6.1
 */