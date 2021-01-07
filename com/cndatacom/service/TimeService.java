package com.cndatacom.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.cndatacom.d.a;
import com.cndatacom.d.b;
import com.cndatacom.e.j;
import com.cndatacom.e.k;
import com.cndatacom.e.m;
import com.cndatacom.xjhui.b.e;
import com.cndatacom.xjhui.b.g;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class TimeService extends Service
{
  private Thread a = null;
  private Thread b = null;
  private Thread c = null;
  private Thread d = null;
  private Context e;
  private b f;
  private Handler g = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 0)
      {
        g.a(TimeService.a(TimeService.this), 3);
        TimeService.this.stopSelf();
      }
      if (paramAnonymousMessage.what == 1)
      {
        g.a(TimeService.a(TimeService.this), 6);
        TimeService.this.stopSelf();
      }
      if (paramAnonymousMessage.what == 2)
      {
        g.c(TimeService.a(TimeService.this));
        g.a(TimeService.a(TimeService.this));
        m.a(TimeService.a(TimeService.this), "iscandozdcl", false);
        TimeService.a(TimeService.this).sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
        TimeService.a(TimeService.this).sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "SERVICE_CHANGED"));
        TimeService.this.stopSelf();
      }
      super.handleMessage(paramAnonymousMessage);
    }
  };

  private void a()
  {
    j.b("TrineaAndroidCommon", "TimeService startKeepWork");
    this.a = new Thread()
    {
      private boolean b = false;
      private int c = 0;

      public void interrupt()
      {
        this.b = true;
        super.interrupt();
      }

      public void run()
      {
        if (!this.b);
        label516: 
        while (true)
        {
          int m;
          long l2;
          long l3;
          int i;
          int k;
          int j;
          try
          {
            String str1 = m.b(TimeService.a(TimeService.this), "interval", "");
            String str2 = m.b(TimeService.a(TimeService.this), "UID", "");
            if (!TextUtils.isEmpty(str1))
            {
              m = Integer.parseInt(str1);
              StringBuilder localStringBuilder4 = new StringBuilder();
              localStringBuilder4.append("TimeService interval : ");
              localStringBuilder4.append(m);
              j.b("TrineaAndroidCommon", localStringBuilder4.toString());
              long l1 = System.currentTimeMillis();
              l2 = 0L;
              Thread.sleep(10000L);
              l3 = l2 + 10L;
              if (System.currentTimeMillis() - l1 < m * 1000)
                break label482;
            }
            i = e.a(TimeService.a(TimeService.this), str2);
            if ((i == 13) || (i == 200))
              i = e.a(TimeService.a(TimeService.this), str2);
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("TimeService startKeepWork result : ");
            localStringBuilder1.append(i);
            j.b("TrineaAndroidCommon", localStringBuilder1.toString());
            if ((i == 123) && (!com.cndatacom.xjhui.b.c.a("TSsw", TimeService.a(TimeService.this))))
              i = 302;
            if (i != 302)
              break label501;
            j.b("TrineaAndroidCommon", "TimeService startKeepWork result == 302");
            this.b = true;
            TimeService.b(TimeService.this).sendEmptyMessage(0);
            break;
            if (k < 3)
            {
              Thread.sleep(3000L);
              j = e.a(TimeService.a(TimeService.this), str2);
              if (j != 0)
                break label516;
            }
            if (j == 0)
            {
              this.c = 0;
              j.b("TrineaAndroidCommon", "TimeService startKeepWork successfully");
              break;
            }
            String str3 = m.b(TimeService.a(TimeService.this), "keep-retry", "");
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("TimeService startKeepWork retrytimes : ");
            localStringBuilder2.append(str3);
            j.b("TrineaAndroidCommon", localStringBuilder2.toString());
            if ("".equals(str3))
              str3 = "2";
            this.c = (1 + this.c);
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append("TimeService startKeepWork holdcount : ");
            localStringBuilder3.append(this.c);
            j.b("TrineaAndroidCommon", localStringBuilder3.toString());
            if (this.c != Integer.parseInt(str3))
              break;
            j.b("TrineaAndroidCommon", "TimeService startKeepWork unsuccessfully");
            this.b = true;
            TimeService.b(TimeService.this).sendEmptyMessage(0);
          }
          catch (Exception localException)
          {
            j.a("TrineaAndroidCommon", localException, "TimeService startKeepWork Exception");
          }
          break;
          return;
          label482: if (l3 < m)
          {
            l2 = l3;
            continue;
            label501: if (i != 0)
            {
              j = i;
              k = 0;
              continue;
              k++;
            }
            else
            {
              j = i;
            }
          }
        }
      }
    };
    this.a.start();
  }

  private void b()
  {
    this.b = new Thread()
    {
      private boolean b = false;

      public void interrupt()
      {
        this.b = true;
        super.interrupt();
      }

      public void run()
      {
        while (!this.b)
          try
          {
            j.b("TrineaAndroidCommon", "TimeService checkShareWork");
            Thread.sleep(300000L);
            boolean bool = k.a(TimeService.a(TimeService.this));
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("TimeService checkShareWork result : ");
            localStringBuilder.append(bool);
            j.b("TrineaAndroidCommon", localStringBuilder.toString());
            if (bool)
            {
              this.b = true;
              TimeService.b(TimeService.this).sendEmptyMessage(1);
            }
          }
          catch (Exception localException)
          {
            j.a("TrineaAndroidCommon", localException, "TimeService checkShareWork Exception");
          }
      }
    };
    this.b.start();
  }

  private void c()
  {
    try
    {
      String str = m.a(this.e, "notifyRegister");
      if (!TextUtils.isEmpty(str))
      {
        String[] arrayOfString1 = str.split("//");
        if (arrayOfString1.length > 1)
        {
          String[] arrayOfString2 = arrayOfString1[1].split(":");
          if (arrayOfString2.length > 1)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("host : ");
            localStringBuilder.append(arrayOfString2[0]);
            localStringBuilder.append(" port : ");
            localStringBuilder.append(arrayOfString2[1]);
            j.b("TrineaAndroidCommon", localStringBuilder.toString());
            this.f = new b(arrayOfString2[0], Integer.parseInt(arrayOfString2[1]));
            d();
            e();
          }
        }
      }
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "TimeService initReverseNotify Exception");
    }
  }

  private void d()
  {
    j.b("TrineaAndroidCommon", "TimeService reverseNotifyReceive");
    this.c = new Thread()
    {
      public void run()
      {
        TimeService.c(TimeService.this).a(new com.cndatacom.d.c()
        {
          public void a(a paramAnonymous2a)
          {
            int i = paramAnonymous2a.c;
            if (i != 2)
            {
              if (i == 129)
              {
                int j = paramAnonymous2a.b();
                int k = paramAnonymous2a.c();
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("TimeService reverseNotifyReceive error : ");
                localStringBuilder.append(j);
                localStringBuilder.append(" time : ");
                localStringBuilder.append(k);
                j.b("TrineaAndroidCommon", localStringBuilder.toString());
                if (j == 0)
                  m.a(TimeService.a(TimeService.this), "registerTime", k);
                else
                  m.a(TimeService.a(TimeService.this), "registerTime", -1);
              }
            }
            else
            {
              byte[] arrayOfByte = paramAnonymous2a.d.array();
              j.b("TrineaAndroidCommon", "TimeService reverseNotifyReceive change");
              String str = m.a(TimeService.a(TimeService.this), "ticket");
              if ((!TextUtils.isEmpty(str)) && (Arrays.equals(arrayOfByte, k.a(str))))
              {
                TimeService.c(TimeService.this).a((short)0, (short)130, str);
                TimeService.b(TimeService.this).sendEmptyMessage(2);
              }
            }
          }
        });
      }
    };
    this.c.start();
  }

  private void e()
  {
    this.d = new Thread()
    {
      private boolean b = false;
      private int c = 1;

      public void interrupt()
      {
        this.b = true;
        super.interrupt();
      }

      public void run()
      {
        while (!this.b)
          try
          {
            j.b("TrineaAndroidCommon", "TimeService reverseNotifyRegister");
            m.a(TimeService.a(TimeService.this), "registerTime", -1);
            String str = m.b(TimeService.a(TimeService.this), "ticket", "");
            TimeService.c(TimeService.this).a((short)0, (short)1, str);
            sleep(10000L);
            int i = m.b(TimeService.a(TimeService.this), "registerTime", -1);
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("TimeService reverseNotifyRegister time : ");
            localStringBuilder1.append(i);
            j.a("ReverseNotify", localStringBuilder1.toString());
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("TimeService reverseNotifyRegister time : ");
            localStringBuilder2.append(i);
            j.b("TrineaAndroidCommon", localStringBuilder2.toString());
            if (i > 0)
            {
              this.c = 1;
              sleep(1000 * (i - 10));
            }
            else if (i == 0)
            {
              this.c = 1;
              this.b = true;
            }
            else
            {
              StringBuilder localStringBuilder3 = new StringBuilder();
              localStringBuilder3.append("TimeService reverseNotifyRegister errorCount : ");
              localStringBuilder3.append(this.c);
              j.b("TrineaAndroidCommon", localStringBuilder3.toString());
              if (this.c == 3)
                this.b = true;
              else
                this.c = (1 + this.c);
              sleep(20000L);
            }
          }
          catch (Exception localException)
          {
            j.a("TrineaAndroidCommon", localException, "TimeService reverseNotifyRegister Exception");
          }
      }
    };
    this.d.start();
  }

  private void f()
  {
    if (this.a != null)
    {
      this.a.interrupt();
      if ((this.a.isAlive()) && (!this.a.isInterrupted()))
        this.a.interrupt();
      this.a = null;
    }
    if (this.b != null)
    {
      this.b.interrupt();
      if ((this.b.isAlive()) && (!this.b.isInterrupted()))
        this.b.interrupt();
      this.b = null;
    }
    if (this.c != null)
    {
      this.f.a();
      this.c.interrupt();
      if ((this.c.isAlive()) && (!this.c.isInterrupted()))
      {
        this.f.a();
        this.c.interrupt();
      }
      this.c = null;
    }
    if (this.d != null)
    {
      this.d.interrupt();
      if ((this.d.isAlive()) && (!this.d.isInterrupted()))
        this.d.interrupt();
      this.d = null;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    j.b("TrineaAndroidCommon", "TimeService onBind");
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    j.b("TrineaAndroidCommon", "TimeService onCreate");
    this.e = this;
    a();
    b();
    c();
  }

  public void onDestroy()
  {
    j.b("TrineaAndroidCommon", "TimeService onDestroy");
    f();
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    j.b("TrineaAndroidCommon", "TimeService onStart");
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return super.onStartCommand(paramIntent, 1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.service.TimeService
 * JD-Core Version:    0.6.1
 */