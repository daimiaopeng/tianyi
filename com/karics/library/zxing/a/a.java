package com.karics.library.zxing.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

final class a
  implements Camera.AutoFocusCallback
{
  private static final String a = "a";
  private static final Collection<String> b = new ArrayList(2);
  private boolean c;
  private boolean d;
  private final boolean e;
  private final Camera f;
  private AsyncTask<?, ?, ?> g;

  static
  {
    b.add("auto");
    b.add("macro");
  }

  a(Context paramContext, Camera paramCamera)
  {
    this.f = paramCamera;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str1 = paramCamera.getParameters().getFocusMode();
    boolean bool = true;
    if ((!localSharedPreferences.getBoolean("preferences_auto_focus", bool)) || (!b.contains(str1)))
      bool = false;
    this.e = bool;
    String str2 = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Current focus mode '");
    localStringBuilder.append(str1);
    localStringBuilder.append("'; use auto focus? ");
    localStringBuilder.append(this.e);
    Log.i(str2, localStringBuilder.toString());
    a();
  }

  @SuppressLint({"NewApi"})
  private void c()
  {
    try
    {
      if ((!this.c) && (this.g == null))
      {
        a locala = new a(null);
        try
        {
          locala.execute(new Object[0]);
          this.g = locala;
        }
        catch (RejectedExecutionException localRejectedExecutionException)
        {
          Log.w(a, "Could not request auto focus", localRejectedExecutionException);
        }
      }
      return;
    }
    finally
    {
    }
  }

  private void d()
  {
    try
    {
      if (this.g != null)
      {
        if (this.g.getStatus() != AsyncTask.Status.FINISHED)
          this.g.cancel(true);
        this.g = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void a()
  {
    try
    {
      if (this.e)
      {
        this.g = null;
        if (!this.c)
        {
          boolean bool = this.d;
          if (!bool)
            try
            {
              this.f.autoFocus(this);
              this.d = true;
            }
            catch (RuntimeException localRuntimeException)
            {
              Log.w(a, "Unexpected exception while focusing", localRuntimeException);
              c();
            }
        }
      }
      return;
    }
    finally
    {
    }
  }

  void b()
  {
    try
    {
      this.c = true;
      if (this.e)
      {
        d();
        try
        {
          this.f.cancelAutoFocus();
        }
        catch (RuntimeException localRuntimeException)
        {
          Log.w(a, "Unexpected exception while cancelling focusing", localRuntimeException);
        }
      }
      return;
    }
    finally
    {
    }
  }

  public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
  {
    try
    {
      this.d = false;
      c();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
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
        Thread.sleep(2000L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
      a.this.a();
      return null;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.a.a
 * JD-Core Version:    0.6.1
 */