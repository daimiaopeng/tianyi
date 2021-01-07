package com.cndatacom.e;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.stub.StubApp;

public class p
{
  private static PowerManager.WakeLock a;
  private static WifiManager.WifiLock b;
  private Context c;

  public p(Context paramContext)
  {
    this.c = paramContext;
  }

  protected static WifiManager.WifiLock a(Context paramContext)
  {
    try
    {
      if (b == null)
      {
        b = ((WifiManager)StubApp.getOrigApplicationContext(paramContext.getApplicationContext()).getSystemService("wifi")).createWifiLock("notifiertest");
        b.setReferenceCounted(true);
      }
      WifiManager.WifiLock localWifiLock = b;
      return localWifiLock;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a()
  {
    try
    {
      j.b("TrineaAndroidCommon", "WifiLockManager takeWifiLock");
      if (a == null)
      {
        a = ((PowerManager)this.c.getSystemService("power")).newWakeLock(1, "notifiertest");
        a.setReferenceCounted(false);
      }
      a.acquire();
      if (!a(this.c).isHeld())
        a(this.c).acquire();
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "WifiLockManager takeWifiLock Exception");
    }
  }

  public void b()
  {
    try
    {
      j.b("TrineaAndroidCommon", "WifiLockManager releaseWifiLock");
      if (a(this.c).isHeld())
        a(this.c).release();
      if (a != null)
        a.release();
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "WifiLockManager releaseWifiLock Exception");
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.p
 * JD-Core Version:    0.6.1
 */