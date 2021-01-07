package com.karics.library.zxing.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

public final class d
{
  private static final String a = "d";
  private final Activity b;
  private final BroadcastReceiver c;
  private boolean d;
  private AsyncTask<Object, Object, Object> e;

  public d(Activity paramActivity)
  {
    this.b = paramActivity;
    this.c = new b(null);
    this.d = false;
    a();
  }

  private void f()
  {
    try
    {
      AsyncTask localAsyncTask = this.e;
      if (localAsyncTask != null)
      {
        localAsyncTask.cancel(true);
        this.e = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @SuppressLint({"NewApi"})
  public void a()
  {
    try
    {
      f();
      this.e = new a(null);
      this.e.execute(new Object[0]);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b()
  {
    try
    {
      f();
      if (this.d)
      {
        this.b.unregisterReceiver(this.c);
        this.d = false;
      }
      else
      {
        Log.w(a, "PowerStatusReceiver was never registered?");
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void c()
  {
    try
    {
      if (this.d)
      {
        Log.w(a, "PowerStatusReceiver was already registered?");
      }
      else
      {
        this.b.registerReceiver(this.c, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.d = true;
      }
      a();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void d()
  {
    f();
  }

  private final class a extends AsyncTask<Object, Object, Object>
  {
    private a()
    {
    }

    protected Object doInBackground(Object[] paramArrayOfObject)
    {
      try
      {
        Thread.sleep(300000L);
        Log.i(d.e(), "Finishing activity due to inactivity");
        d.b(d.this).finish();
      }
      catch (InterruptedException localInterruptedException)
      {
      }
      return null;
    }
  }

  private final class b extends BroadcastReceiver
  {
    private b()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.BATTERY_CHANGED".equals(paramIntent.getAction()))
      {
        int i;
        if (paramIntent.getIntExtra("plugged", -1) <= 0)
          i = 1;
        else
          i = 0;
        if (i != 0)
          d.this.a();
        else
          d.a(d.this);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.android.d
 * JD-Core Version:    0.6.1
 */