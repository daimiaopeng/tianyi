package com.jg.ce;

import android.content.Context;
import com.stub.StubApp;

@QVMProtect
public class Interface2
{
  static
  {
    try
    {
      StubApp.interface11(2712);
      System.loadLibrary("jgbugcollector");
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  public static native int interface1(Context paramContext);

  public static native int interface2();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.jg.ce.Interface2
 * JD-Core Version:    0.6.1
 */