package com.common.busi;

import android.content.Context;
import java.lang.reflect.Method;

public class CustomView
{
  public static void appUpdate(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Class.forName("com.stub.stub08.UpdateEntry").getDeclaredMethod("appUpdate", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      label32: return;
    }
    catch (Throwable localThrowable)
    {
      break label32;
    }
  }

  public static native String getAppkey();

  public static native long getPT();

  public static void hotfix(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Class.forName("com.common.custom.hotfix.HotfixEntry").getDeclaredMethod("hotfix", new Class[] { Context.class, String.class, String.class }).invoke(null, new Object[] { paramContext, paramString1, paramString2 });
      label50: return;
    }
    catch (Throwable localThrowable)
    {
      break label50;
    }
  }

  public static void ls(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Class.forName("com.stub.stub05.Sdk").getDeclaredMethod("init", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      label32: return;
    }
    catch (Throwable localThrowable)
    {
      break label32;
    }
  }

  public static void pull(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Class.forName("com.stub.stub02.p.PullEntry").getDeclaredMethod("pull", new Class[] { Context.class, String.class, String.class }).invoke(null, new Object[] { paramContext, paramString1, paramString2 });
      label50: return;
    }
    catch (Throwable localThrowable)
    {
      break label50;
    }
  }

  public static void upgrade(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Class.forName("com.common.custom.soupgrade.SoEntry").getDeclaredMethod("upgrade", new Class[] { Context.class, String.class, String.class }).invoke(null, new Object[] { paramContext, paramString1, paramString2 });
      label50: return;
    }
    catch (Throwable localThrowable)
    {
      break label50;
    }
  }

  public static void wp(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Class.forName("com.common.busi.WpEntry").getDeclaredMethod("wp", new Class[] { Context.class, String.class, String.class }).invoke(null, new Object[] { paramContext, paramString1, paramString2 });
      label50: return;
    }
    catch (Throwable localThrowable)
    {
      break label50;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.common.busi.CustomView
 * JD-Core Version:    0.6.1
 */