package com.stub.stub07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import java.lang.reflect.Method;

public class Stub01 extends BroadcastReceiver
{
  private static Stub01 mInstance = new Stub01();
  private static final int mJC = 16;

  public static Stub01 getInstance()
  {
    if (mInstance != null);
    for (Stub01 localStub01 = mInstance; ; localStub01 = mInstance)
    {
      return localStub01;
      mInstance = new Stub01();
    }
  }

  public static native void mark1(Location paramLocation);

  public void handleException(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, int paramInt)
  {
    try
    {
      Class localClass1 = Class.forName("com.qihoo.bugreport.CrashReport");
      Class localClass2 = Class.forName("com.qihoo.bugreport.javacrash.ExceptionHandleReporter");
      Object localObject = localClass1.getDeclaredMethod("getExceptionHandlerInstance", new Class[0]).invoke(null, new Object[0]);
      if (localObject != null)
      {
        Class[] arrayOfClass = new Class[4];
        arrayOfClass[0] = Thread.class;
        arrayOfClass[1] = Throwable.class;
        arrayOfClass[2] = Boolean.TYPE;
        arrayOfClass[3] = Integer.TYPE;
        Method localMethod = localClass2.getDeclaredMethod("uncaughtException", arrayOfClass);
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramThread;
        arrayOfObject[1] = paramThrowable;
        arrayOfObject[2] = Boolean.valueOf(paramBoolean);
        arrayOfObject[3] = Integer.valueOf(paramInt);
        localMethod.invoke(localObject, arrayOfObject);
      }
      label126: return;
    }
    catch (Exception localException)
    {
      break label126;
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (paramIntent.getAction().equals("android.net.wifi.STATE_CHANGE"))
      {
        NetworkInfo localNetworkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
        if ((localNetworkInfo == null) || (localNetworkInfo.getState() != NetworkInfo.State.CONNECTED))
          return;
        mark1(null);
      }
    }
    catch (Throwable localThrowable)
    {
      handleException(Thread.currentThread(), localThrowable, true, 16);
    }
    if (paramIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
      mark1(null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.stub07.Stub01
 * JD-Core Version:    0.6.1
 */