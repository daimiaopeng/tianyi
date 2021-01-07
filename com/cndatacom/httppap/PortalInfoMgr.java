package com.cndatacom.httppap;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.cndatacom.e.j;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Map;

public class PortalInfoMgr
{
  private Map<String, String> infoKeys = null;
  private boolean isInitial = false;
  private Context mContext = null;
  private String mHostname = null;
  private String mMacaddr = null;

  public static String genMD5(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    String str;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      char[] arrayOfChar2 = new char[32];
      int i = 0;
      int j = 0;
      while (i < 16)
      {
        int k = arrayOfByte[i];
        int m = j + 1;
        arrayOfChar2[j] = arrayOfChar1[(0xF & k >>> 4)];
        j = m + 1;
        arrayOfChar2[m] = arrayOfChar1[(k & 0xF)];
        i++;
      }
      str = new String(arrayOfChar2);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      str = null;
    }
    return str;
  }

  private String getClientId()
  {
    String str = ((TelephonyManager)this.mContext.getSystemService("phone")).getDeviceId();
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("tmDevice=");
    localStringBuilder1.append(str);
    Log.i("info1", localStringBuilder1.toString());
    if ((str == null) || ("".equals(str)))
      str = getSerialNumber();
    if ((str == null) || ("".equals(str)))
      str = getDeviceId(this.mContext);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("tmDevice=");
    localStringBuilder2.append(str);
    Log.i("info1", localStringBuilder2.toString());
    return new String(genMD5(str.getBytes()));
  }

  private String getDeviceId(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  private String getHostname()
  {
    if (this.mHostname != null)
      return this.mHostname;
    new Thread(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: invokestatic 29	java/net/InetAddress:getLocalHost	()Ljava/net/InetAddress;
        //   3: astore_1
        //   4: goto +5 -> 9
        //   7: aconst_null
        //   8: astore_1
        //   9: aload_1
        //   10: ifnull +22 -> 32
        //   13: aload_0
        //   14: getfield 17	com/cndatacom/httppap/PortalInfoMgr$1:this$0	Lcom/cndatacom/httppap/PortalInfoMgr;
        //   17: new 31	java/lang/String
        //   20: dup
        //   21: aload_1
        //   22: invokevirtual 34	java/net/InetAddress:getHostName	()Ljava/lang/String;
        //   25: invokespecial 37	java/lang/String:<init>	(Ljava/lang/String;)V
        //   28: invokestatic 41	com/cndatacom/httppap/PortalInfoMgr:access$002	(Lcom/cndatacom/httppap/PortalInfoMgr;Ljava/lang/String;)Ljava/lang/String;
        //   31: pop
        //   32: return
        //
        // Exception table:
        //   from	to	target	type
        //   0	4	7	java/net/UnknownHostException
      }
    }).start();
    if (this.mHostname != null)
      return this.mHostname;
    return new String("lmh-PC");
  }

  private String getIPv4()
  {
    return getIPV4();
  }

  private String getIPv6()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration2;
        while (!localEnumeration2.hasMoreElements())
        {
          if (!localEnumeration1.hasMoreElements())
            break;
          localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration2.nextElement();
      }
      while ((localInetAddress.isLoopbackAddress()) || (localInetAddress.isAnyLocalAddress()));
      String str = localInetAddress.getHostAddress().toString();
      return str;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return "fe80::d59c:43f8:b941:28d3%11";
  }

  private String getMACAddr()
  {
    if (this.mMacaddr != null)
      return this.mMacaddr;
    new Thread(new Runnable()
    {
      public void run()
      {
        String str = ((WifiManager)PortalInfoMgr.this.mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (str != null)
          PortalInfoMgr.access$202(PortalInfoMgr.this, str);
      }
    }).start();
    if (this.mMacaddr != null)
      return this.mMacaddr;
    return new String("3C-97-0E-00-AF-34");
  }

  public static String getSrvErrInfo(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" getSrvErrInfo:");
    localStringBuilder.append(paramInt);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    String str;
    if (paramInt != 200)
    {
      if (paramInt != 990)
      {
        if (paramInt != 1002)
          switch (paramInt)
          {
          default:
            switch (paramInt)
            {
            default:
              switch (paramInt)
              {
              default:
                switch (paramInt)
                {
                default:
                  switch (paramInt)
                  {
                  default:
                    str = new String("未知错误。");
                    break;
                  case 902:
                    str = new String("Portal服务器错误。");
                    break;
                  case 901:
                    str = new String("数据库错误。");
                  }
                  break;
                case 301:
                  str = new String("正在等待服务端进行验证。");
                  break;
                case 300:
                  str = new String("正在等待终端发起认证。");
                }
                break;
              case 105:
                str = new String("用户账号受限。");
                break;
              case 104:
                str = new String("对应的用户不存在。");
                break;
              case 103:
                str = new String("网关可容纳的接入数已满。");
                break;
              case 102:
                str = new String("接入认证被拒绝。");
                break;
              case 101:
                str = new String("无效的挑战值。");
                break;
              case 100:
                str = new String("无效的用户名、密码。");
              }
              break;
            case 15:
              str = new String("校验值错误。");
              break;
            case 14:
              str = new String("无效的ticket。");
              break;
            case 13:
              str = new String("无效的算法ID。");
              break;
            case 12:
              str = new String("无效的客户端ID。");
              break;
            case 11:
              str = new String("无效的客户端MAC地址。");
              break;
            case 10:
              str = new String("无效的客户端IP地址。");
            }
            break;
          case 3:
            str = new String("不支持该客户端。");
            break;
          case 2:
            str = new String("不支持该系统。");
            break;
          case 1:
            str = new String("无效的请求。");
            break;
          case 0:
            str = new String("无错误。");
            break;
          }
        else
          str = "用户帐号因欠费已停机，请续费后再使用。";
      }
      else
        str = new String("系统错误。");
    }
    else
      str = new String("密钥过期。");
    return str;
  }

  public String formatInt2IPAddr(int paramInt)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = (paramInt & 0xFF);
    arrayOfInt[1] = (0xFF & paramInt >> 8);
    arrayOfInt[2] = (0xFF & paramInt >> 16);
    arrayOfInt[3] = (0xFF & paramInt >> 24);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(arrayOfInt[0]);
    localStringBuilder.append(".");
    localStringBuilder.append(arrayOfInt[1]);
    localStringBuilder.append(".");
    localStringBuilder.append(arrayOfInt[2]);
    localStringBuilder.append(".");
    localStringBuilder.append(arrayOfInt[3]);
    return localStringBuilder.toString();
  }

  String getIPV4()
  {
    WifiManager localWifiManager = (WifiManager)this.mContext.getSystemService("wifi");
    if (localWifiManager.isWifiEnabled())
    {
      String str = intToIp(localWifiManager.getConnectionInfo().getIpAddress());
      Log.i("ppp", str);
      return str;
    }
    return "0.0.0.0";
  }

  public String getInfo(String paramString)
  {
    if (this.infoKeys == null)
      return null;
    Object localObject = this.infoKeys.get(paramString);
    if ((localObject instanceof String))
      return (String)localObject;
    return null;
  }

  // ERROR //
  public String getSerialNumber()
  {
    // Byte code:
    //   0: ldc_w 307
    //   3: invokestatic 313	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: astore_2
    //   7: aload_2
    //   8: ldc_w 314
    //   11: iconst_1
    //   12: anewarray 309	java/lang/Class
    //   15: dup
    //   16: iconst_0
    //   17: ldc 71
    //   19: aastore
    //   20: invokevirtual 318	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   23: aload_2
    //   24: iconst_1
    //   25: anewarray 4	java/lang/Object
    //   28: dup
    //   29: iconst_0
    //   30: ldc_w 320
    //   33: aastore
    //   34: invokevirtual 326	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   37: checkcast 71	java/lang/String
    //   40: astore_1
    //   41: goto +5 -> 46
    //   44: aconst_null
    //   45: astore_1
    //   46: aload_1
    //   47: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	41	44	java/lang/Exception
  }

  public Context getmContext()
  {
    return this.mContext;
  }

  String intToIp(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(0xFF & paramInt >> 8);
    localStringBuilder.append(".");
    localStringBuilder.append(0xFF & paramInt >> 16);
    localStringBuilder.append(".");
    localStringBuilder.append(0xFF & paramInt >> 24);
    return localStringBuilder.toString();
  }

  public boolean isInitial()
  {
    return this.isInitial;
  }

  public void refreshNetInfo()
  {
    String str1 = getHostname();
    String str2 = getMACAddr();
    String str3 = getIPv4();
    String str4 = getIPv6();
    String str5 = getClientId();
    this.infoKeys.put("JSPORTAL_HOST_NAME", str1);
    this.infoKeys.put("JSPORTAL_MAC_ADDR", str2);
    this.infoKeys.put("JSPORTAL_IPADDR_V4", str3);
    this.infoKeys.put("JSPORTAL_IPADDR_V6", str4);
    this.infoKeys.put("JSPORTAL_CLIENT_ID", str5);
  }

  public void setInfo(String paramString1, String paramString2)
  {
    if (this.infoKeys == null)
      return;
    this.infoKeys.put(paramString1, paramString2);
  }

  public void setInitial(boolean paramBoolean)
  {
    this.isInitial = paramBoolean;
  }

  public void setmContext(Context paramContext)
  {
    this.mContext = paramContext;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.httppap.PortalInfoMgr
 * JD-Core Version:    0.6.1
 */