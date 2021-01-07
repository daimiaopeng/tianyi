package com.qihoo.jiagu;

public class d
{
  public static void a(String paramString)
  {
    try
    {
      if (Runtime.getRuntime().exec(paramString).waitFor() != 0)
        new StringBuilder("Failed to execute cmd:").append(paramString);
      label27: return;
    }
    catch (Exception localException)
    {
      break label27;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagu.d
 * JD-Core Version:    0.6.1
 */