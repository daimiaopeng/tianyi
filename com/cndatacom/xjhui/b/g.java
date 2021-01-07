package com.cndatacom.xjhui.b;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.LoginOutActivity;
import com.cndatacom.xjhui.MainUiActivity;

@SuppressLint({"NewApi"})
public class g
{
  private static AsyncTask<Object, Object, Object> a;

  public static void a(Context paramContext)
  {
    m.a(paramContext, "sCTT", System.currentTimeMillis());
  }

  public static void a(Context paramContext, final int paramInt)
  {
    new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        String str1 = m.b(this.a, "ticket", "");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("doTermAction reason : ");
        localStringBuilder.append(paramInt);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        String str2 = m.b(this.a, "UID", "");
        int i = h.a(this.a, paramInt, str2, str1);
        if ((i == 200) || (i == 13))
          i = h.a(this.a, paramInt, str2, str1);
        return Integer.valueOf(i);
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        int i = Integer.parseInt(paramAnonymousObject.toString());
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("doTermAction result : ");
        localStringBuilder.append(i);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        if (i == 0)
        {
          g.a(this.a);
        }
        else if (i == 14)
        {
          g.a(this.a);
          g.c(this.a);
        }
        else
        {
          m.a(this.a, "SID", "0");
          m.a(this.a, "preTimeMillis", 0L);
        }
        switch (paramInt)
        {
        default:
          break;
        case 7:
          MainUiActivity.b();
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
          break;
        case 6:
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "NETWORK_SHARE"));
          break;
        case 5:
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
          break;
        case 3:
        case 4:
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "SERVICE_CHANGED"));
          break;
        case 2:
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "APP_QUIT"));
        }
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
      }
    }
    .executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  public static void a(final Context paramContext, o paramo, final int paramInt)
  {
    if (a != null)
    {
      paramo.a();
      return;
    }
    a = new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        String str1 = m.b(paramContext, "ticket", "");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("clickTermAction from : ");
        localStringBuilder.append(paramInt);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        String str2 = m.b(paramContext, "UID", "");
        int i = h.a(paramContext, 1, str2, str1);
        if ((i == 200) || (i == 13))
          i = h.a(paramContext, 1, str2, str1);
        if (i == 0)
          try
          {
            Thread.sleep(1000L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        return Integer.valueOf(i);
      }

      protected void onCancelled()
      {
        this.a.b();
        g.a(null);
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        this.a.b();
        int i = Integer.parseInt(paramAnonymousObject.toString());
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("clickTermAction resultcode : ");
        localStringBuilder.append(i);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        if (i == 0)
        {
          g.a(paramContext);
        }
        else if (i == 14)
        {
          g.a(paramContext);
          g.c(paramContext);
        }
        else
        {
          m.a(paramContext, "SID", "0");
          m.a(paramContext, "preTimeMillis", 0L);
        }
        Toast.makeText(paramContext, "退出成功", 0).show();
        m.a(paramContext, "iscandozdcl", false);
        MainUiActivity.b();
        g.a(null);
        if (paramInt == 1)
          paramContext.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
        if (paramInt == 2)
          ((Activity)LoginOutActivity.a).finish();
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
        this.a.a();
      }
    };
    a.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  public static boolean b(Context paramContext)
  {
    String str1 = m.b(paramContext, "ticket", "");
    String str2 = m.b(paramContext, "term-url", "");
    boolean bool;
    if ((!"".equals(str1)) && (!"".equals(str2)))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static void c(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
    localEditor.putString("ticket", "");
    localEditor.putString("expire", "");
    localEditor.putString("userid", "");
    localEditor.putString("keep-retry", "");
    localEditor.putString("keep-url", "");
    localEditor.putString("term-url", "");
    localEditor.putString("SID", "0");
    localEditor.putString("portal_user_password", "");
    localEditor.putLong("preTimeMillis", 0L);
    localEditor.putString("interval", "");
    localEditor.commit();
  }

  public static void d(Context paramContext)
  {
    m.a(paramContext, "ticket", "");
    m.a(paramContext, "term-url", "");
  }

  public static void e(Context paramContext)
  {
    new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        String str1 = m.b(this.a, "ticket", "");
        String str2 = m.b(this.a, "UID", "");
        j.b("TrineaAndroidCommon", "doTermActionBeforeAuth reason : 1");
        int i = h.a(this.a, 1, str2, str1);
        if ((i == 200) || (i == 13))
          i = h.a(this.a, 1, str2, str1);
        return Integer.valueOf(i);
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        int i = Integer.parseInt(paramAnonymousObject.toString());
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("doTermActionBeforeAuth result ： ");
        localStringBuilder.append(i);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        if ((i == 0) || (i == 14))
        {
          g.a(this.a);
          g.d(this.a);
        }
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
      }
    }
    .executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.g
 * JD-Core Version:    0.6.1
 */