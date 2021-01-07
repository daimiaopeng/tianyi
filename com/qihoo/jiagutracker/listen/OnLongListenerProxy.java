package com.qihoo.jiagutracker.listen;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class OnLongListenerProxy
  implements View.OnLongClickListener
{
  private String mClassName;
  private View.OnLongClickListener mOnLongClickListener;
  private OnLongClickListenerCallback mOnlongClickProxyListener;

  static
  {
    StubApp.interface11(2744);
  }

  public OnLongListenerProxy(View.OnLongClickListener paramOnLongClickListener, OnLongClickListenerCallback paramOnLongClickListenerCallback, String paramString)
  {
    this.mOnLongClickListener = paramOnLongClickListener;
    this.mOnlongClickProxyListener = paramOnLongClickListenerCallback;
    this.mClassName = paramString;
  }

  public native boolean onLongClick(View paramView);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.listen.OnLongListenerProxy
 * JD-Core Version:    0.6.1
 */