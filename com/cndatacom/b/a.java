package com.cndatacom.b;

import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class a
{
  public static void a(Context paramContext, int paramInt)
  {
    try
    {
      ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramInt);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    if (paramInt > -1)
      localBuilder.setIcon(paramInt);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create();
    localBuilder.show();
  }

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, -1, paramString1, paramString2);
  }

  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent(paramString1);
      localIntent.putExtra("DATA", paramString2);
      paramContext.sendBroadcast(localIntent);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.b.a
 * JD-Core Version:    0.6.1
 */