package com.lahm.library;

public class EasyProtectorLib
{
  public static boolean checkIsRunningInEmulator()
  {
    return EmulatorCheckUtil.getSingleInstance().readSysProperty();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.lahm.library.EasyProtectorLib
 * JD-Core Version:    0.6.1
 */