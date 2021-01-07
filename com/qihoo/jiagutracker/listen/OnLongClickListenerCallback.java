package com.qihoo.jiagutracker.listen;

import android.annotation.SuppressLint;
import android.view.View;
import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class OnLongClickListenerCallback
{
  private static OnLongClickListenerCallback sInstance = null;

  static
  {
    StubApp.interface11(2743);
  }

  public static native OnLongClickListenerCallback getInstance();

  @SuppressLint({"ResourceType"})
  private native String getViewTree(View paramView);

  public native void onLongClickProxy(View paramView, String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.listen.OnLongClickListenerCallback
 * JD-Core Version:    0.6.1
 */