package com.qihoo.jiagutracker.utils;

import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;

@QVMProtect
public class ReflectUtil
{
  static
  {
    StubApp.interface11(2748);
  }

  public static native Object getField(Class<?> paramClass, String paramString, Object paramObject);

  private static native Object invokeMethod(Class<?> paramClass, String paramString, Object paramObject, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject);

  public static native Object invokeStaticMethod(String paramString1, String paramString2)
    throws ClassNotFoundException;
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.utils.ReflectUtil
 * JD-Core Version:    0.6.1
 */