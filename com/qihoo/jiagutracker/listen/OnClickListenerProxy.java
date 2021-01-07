package com.qihoo.jiagutracker.listen;

import android.view.View;
import android.view.View.OnClickListener;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class OnClickListenerProxy
  implements View.OnClickListener
{
  private String mClassName;
  private View.OnClickListener mOnClickListener;
  private OnClickListenerCallback mOnClickListenerCallback;

  static
  {
    StubApp.interface11(2742);
  }

  public OnClickListenerProxy(View.OnClickListener paramOnClickListener, OnClickListenerCallback paramOnClickListenerCallback, String paramString)
  {
    this.mOnClickListener = paramOnClickListener;
    this.mOnClickListenerCallback = paramOnClickListenerCallback;
    this.mClassName = paramString;
  }

  public native void onClick(View paramView);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.listen.OnClickListenerProxy
 * JD-Core Version:    0.6.1
 */