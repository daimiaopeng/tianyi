package com.cndatacom.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.Locale;

public class k
{
  private static byte a(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
  }

  public static boolean a(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    try
    {
      for (Method localMethod : localConnectivityManager.getClass().getMethods())
        if (localMethod.getName().equals("getTetheredIfaces"))
        {
          String[] arrayOfString = (String[])localMethod.invoke(localConnectivityManager, new Object[0]);
          if ((arrayOfString != null) && (arrayOfString.length > 0))
          {
            int k = arrayOfString.length;
            if (k > 0)
              return true;
          }
        }
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "MyTools isNetworkSharing Exception");
    }
    return false;
  }

  public static byte[] a(String paramString)
  {
    byte[] arrayOfByte;
    try
    {
      String str = paramString.toUpperCase(Locale.CHINA);
      int i = str.length() / 2;
      arrayOfByte = new byte[i];
      try
      {
        char[] arrayOfChar = str.toCharArray();
        for (int j = 0; j < i; j++)
        {
          int k = j * 2;
          arrayOfByte[j] = (byte)(a(arrayOfChar[k]) << 4 | a(arrayOfChar[(k + 1)]));
        }
      }
      catch (Exception localException1)
      {
      }
    }
    catch (Exception localException2)
    {
      arrayOfByte = null;
    }
    j.a("TrineaAndroidCommon", localException2, "MyTools handleHexStringToByteArray Exception");
    return arrayOfByte;
  }

  public static void b(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = a.a(this.a).a();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("getGoogleAdvertisingId : ");
          localStringBuilder.append(str);
          j.b("TrineaAndroidCommon", localStringBuilder.toString());
          if (!TextUtils.isEmpty(str))
            m.a(this.a, "GoogleAdvertisingId", str);
        }
        catch (Exception localException)
        {
        }
      }
    }).start();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.k
 * JD-Core Version:    0.6.1
 */