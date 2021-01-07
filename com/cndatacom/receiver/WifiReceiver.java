package com.cndatacom.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.cndatacom.e.j;
import com.cndatacom.e.l;
import com.cndatacom.e.m;
import com.cndatacom.xjhui.b.a;
import com.cndatacom.xjhui.b.c;

public class WifiReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    int i = str.hashCode();
    if (i != -1875733435)
    {
      if (i != -1172645946)
      {
        if ((i == -343630553) && (str.equals("android.net.wifi.STATE_CHANGE")))
        {
          j = 2;
          break label83;
        }
      }
      else if (str.equals("android.net.conn.CONNECTIVITY_CHANGE"))
      {
        j = 1;
        break label83;
      }
    }
    else if (str.equals("android.net.wifi.WIFI_STATE_CHANGED"))
    {
      j = 0;
      break label83;
    }
    int j = -1;
    switch (j)
    {
    default:
      break;
    case 1:
    case 2:
      if (l.a(paramContext) == 3)
      {
        WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("WiFi connected : ");
        localStringBuilder.append(localWifiInfo.getSSID());
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        a.b(paramContext);
        if (TextUtils.isEmpty(m.a(paramContext, "schoolid")))
          c.a(paramContext, "WifiReceiver");
      }
      else
      {
        j.b("TrineaAndroidCommon", "WiFi disconnected");
        paramContext.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
      }
      break;
    case 0:
      label83: if (paramIntent.getIntExtra("wifi_state", 1) != 3)
      {
        j.b("TrineaAndroidCommon", "WiFi disconnected");
        paramContext.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
      }
      break;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.receiver.WifiReceiver
 * JD-Core Version:    0.6.1
 */