package com.qihoo.jiagutracker.listen;

import android.annotation.SuppressLint;
import android.view.View;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class OnClickListenerCallback
{
  private static OnClickListenerCallback sInstance;

  static
  {
    StubApp.interface11(2741);
  }

  public static native OnClickListenerCallback getInstance();

  @SuppressLint({"ResourceType"})
  private native String getViewTree(View paramView);

  public native void onClickProxy(View paramView, String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.listen.OnClickListenerCallback
 * JD-Core Version:    0.6.1
 */