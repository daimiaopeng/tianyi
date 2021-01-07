package com.cndatacom.campus.jscportal;

import android.app.Application;
import android.os.Handler;
import android.os.Process;
import android.widget.Toast;
import com.cndatacom.e.j;
import com.cndatacom.e.k;
import com.cndatacom.e.m;
import com.stub.StubApp;
import com.tencent.bugly.crashreport.CrashReport;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application
{
  private static MyApplication a;
  private ExecutorService b;
  private Handler c = new Handler();

  public static MyApplication a()
  {
    return a;
  }

  public ExecutorService b()
  {
    if (this.b == null)
      this.b = Executors.newFixedThreadPool(15);
    return this.b;
  }

  public void onCreate()
  {
    super.onCreate();
    CrashReport.initCrashReport(StubApp.getOrigApplicationContext(getApplicationContext()), "f923aca419", false);
    CrashReport.putUserData(StubApp.getOrigApplicationContext(getApplicationContext()), "clientid", com.cndatacom.xjhui.b.a.d.e(StubApp.getOrigApplicationContext(getApplicationContext())));
    CrashReport.putUserData(StubApp.getOrigApplicationContext(getApplicationContext()), "account", m.b(StubApp.getOrigApplicationContext(getApplicationContext()), "UID", ""));
    a = this;
    j.a("TrineaAndroidCommon", true, false, false);
    j.b("TrineaAndroidCommon", "MyApplication onCreate");
    k.b(this);
    Thread.setDefaultUncaughtExceptionHandler(a.a());
    if (com.cndatacom.e.d.a())
    {
      Toast.makeText(StubApp.getOrigApplicationContext(getApplicationContext()), "不支持该设备", 1).show();
      this.c.postDelayed(new Runnable()
      {
        public void run()
        {
          Process.killProcess(Process.myPid());
        }
      }
      , 5000L);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.campus.jscportal.MyApplication
 * JD-Core Version:    0.6.1
 */