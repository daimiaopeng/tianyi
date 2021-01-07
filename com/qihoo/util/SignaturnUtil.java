package com.qihoo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.view.Window;

public class SignaturnUtil
{
  public static final boolean DEBUG = false;
  public static final String SIMPLE = SignaturnUtil.class.getSimpleName();
  public static final String TAG = "Huacai";
  public static boolean been = false;

  public static void showDialog(Context paramContext, final String paramString1, final String paramString2, final boolean paramBoolean)
  {
    synchronized (new Thread(new Runnable()
    {
      public void run()
      {
        Looper.prepare();
        AlertDialog localAlertDialog = new AlertDialog.Builder(this.val$context).setMessage(paramString1).setCancelable(false).setPositiveButton(paramString2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            synchronized (Thread.currentThread())
            {
              if (Build.VERSION.SDK_INT >= 19)
                ???.notify();
              return;
            }
          }
        }).create();
        localAlertDialog.getWindow().setType(2005);
        if (paramBoolean)
          localAlertDialog.dismiss();
        while (true)
        {
          Looper.loop();
          return;
          localAlertDialog.show();
        }
      }
    }))
    {
      try
      {
        ???.start();
        if (Build.VERSION.SDK_INT >= 19)
          ???.wait();
        while (true)
        {
          label41: return;
          Thread.sleep(3000L);
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        break label41;
      }
    }
  }

  public static native void showUnofficalDialogNative(Context paramContext);

  static class UnofficalLifecycle
    implements Application.ActivityLifecycleCallbacks
  {
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
    {
      if (paramActivity.getClass().getName().equals("com.qihoo.util.StartActivity"));
      while (true)
      {
        return;
        if (!SignaturnUtil.been)
        {
          SignaturnUtil.showUnofficalDialogNative(paramActivity);
          SignaturnUtil.been = true;
        }
      }
    }

    public void onActivityDestroyed(Activity paramActivity)
    {
    }

    public void onActivityPaused(Activity paramActivity)
    {
    }

    public void onActivityResumed(Activity paramActivity)
    {
    }

    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
    {
    }

    public void onActivityStarted(Activity paramActivity)
    {
    }

    public void onActivityStopped(Activity paramActivity)
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.util.SignaturnUtil
 * JD-Core Version:    0.6.1
 */