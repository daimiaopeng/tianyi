package com.cndatacom.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;

public class l
{
  public static int a(Context paramContext)
  {
    WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
    if ((localWifiManager != null) && (localWifiManager.getWifiState() == 3))
    {
      if (b(paramContext))
        return 3;
      return 2;
    }
    return 1;
  }

  public static boolean b(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int i = 1;
    NetworkInfo localNetworkInfo = localConnectivityManager.getNetworkInfo(i);
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()) || (!localNetworkInfo.isAvailable()) || (localNetworkInfo.getState() != NetworkInfo.State.CONNECTED))
      i = 0;
    return i;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.l
 * JD-Core Version:    0.6.1
 */