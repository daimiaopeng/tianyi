package com.cndatacom.dykeylib;

public class DykeyJNILib
{
  static
  {
    System.loadLibrary("DyKey");
  }

  public static native DyKeyCodeInfo dyKeyCode(String paramString1, String paramString2);

  public static native DyKeyDecodeInfo dyKeyDecode(String paramString1, String paramString2);

  public static native DyKeyInfo dyKeyInfo(String paramString);

  public static native int dyKeySetDir(String paramString);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.dykeylib.DykeyJNILib
 * JD-Core Version:    0.6.1
 */