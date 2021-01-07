package com.cndatacom.xjhui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import com.cndatacom.b.a;
import com.cndatacom.e.j;
import com.cndatacom.e.l;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.service.TimeService;
import com.cndatacom.xjhui.b.c;
import com.cndatacom.xjhui.b.g;
import com.cndatacom.xjhui.fragment.MeFragment;
import com.cndatacom.xjhui.fragment.NetworkFragment;
import com.stub.StubApp;

@SuppressLint({"NewApi"})
public class MainUiActivity extends FragmentActivity
{
  public static Context a;
  private a b = null;
  private int c;
  private Fragment d;

  static
  {
    StubApp.interface11(1261);
  }

  public static void a()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(a, TimeService.class);
    a.startService(localIntent);
  }

  private void a(int paramInt)
  {
    if (this.c == paramInt)
      return;
    this.c = paramInt;
    this.d = b(this.c);
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.replace(2131034171, this.d);
    localFragmentTransaction.commitAllowingStateLoss();
  }

  private void a(Bundle paramBundle)
  {
    if (paramBundle == null)
    {
      this.d = NetworkFragment.a();
      getSupportFragmentManager().beginTransaction().add(2131034171, this.d).commit();
      this.c = 0;
      c(0);
    }
    else
    {
      this.c = paramBundle.getInt("fragmentTag", 0);
      c(this.c);
      a(this.c);
    }
  }

  private void a(boolean paramBoolean)
  {
    if ((this.d instanceof NetworkFragment))
      if (paramBoolean)
        ((NetworkFragment)this.d).d();
      else
        ((NetworkFragment)this.d).c();
    if ((this.d instanceof MeFragment))
      ((MeFragment)this.d).c();
  }

  private Fragment b(int paramInt)
  {
    this.c = paramInt;
    if (paramInt == 2)
      return MeFragment.a();
    return NetworkFragment.a();
  }

  public static void b()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(a, TimeService.class);
    a.stopService(localIntent);
  }

  private void c()
  {
    ((RadioGroup)findViewById(2131034216)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          break;
        case 2131034213:
          MainUiActivity.a(MainUiActivity.this, 2);
          break;
        case 2131034212:
          MainUiActivity.a(MainUiActivity.this, 0);
        }
      }
    });
  }

  private void c(int paramInt)
  {
    if (paramInt == 2)
      ((RadioButton)findViewById(2131034213)).setChecked(true);
    else if (paramInt == 0)
      ((RadioButton)findViewById(2131034212)).setChecked(true);
  }

  private void d()
  {
    if ((this.d instanceof NetworkFragment))
      ((NetworkFragment)this.d).e();
    if ((this.d instanceof MeFragment))
      ((MeFragment)this.d).d();
  }

  private void e()
  {
    this.b = new a();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.cndatacom.jscportal.ACTION_STATE");
    registerReceiver(this.b, localIntentFilter);
  }

  private void f()
  {
    try
    {
      a.a(this, 1);
      if (m.b(this, "SID", "0").equals("1"))
      {
        if ("".equals(m.b(this, "WifiInfo", "")))
          System.exit(0);
        if (l.a(StubApp.getOrigApplicationContext(getApplicationContext())) != 3)
          System.exit(0);
        g.a(this, 2);
      }
      else
      {
        System.exit(0);
      }
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "MainUiActivity appQuit Exception");
    }
  }

  protected native void onCreate(Bundle paramBundle);

  public void onDestroy()
  {
    b();
    unregisterReceiver(this.b);
    j.b("TrineaAndroidCommon", "MainUiActivity onDestroy");
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      final AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
      View localView = LayoutInflater.from(this).inflate(2131165195, null);
      localView.findViewById(2131034127).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MainUiActivity.c(MainUiActivity.this);
          localAlertDialog.dismiss();
        }
      });
      localView.findViewById(2131034128).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MainUiActivity.this.moveTaskToBack(false);
          localAlertDialog.dismiss();
        }
      });
      localAlertDialog.show();
      localAlertDialog.getWindow().setContentView(localView);
      localAlertDialog.getWindow().setLayout(-1, -1);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onResume()
  {
    super.onResume();
    j.b("TrineaAndroidCommon", "MainUiActivity onResume");
    a.a(this, 1);
  }

  class a extends BroadcastReceiver
  {
    a()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getStringExtra("DATA");
      switch (str.hashCode())
      {
      default:
        break;
      case 1979936205:
        if (str.equals("APP_QUIT"))
          i = 7;
        break;
      case 1893109080:
        if (str.equals("AUTO_RECONNECT_UNSUCCESSFULLY"))
          i = 6;
        break;
      case 1020822250:
        if (str.equals("SERVICE_CHANGED"))
          i = 3;
        break;
      case 913663551:
        if (str.equals("AUTO_RECONNECT_SUCCESSFULLY"))
          i = 5;
        break;
      case 886105385:
        if (str.equals("ADVERT_CHANGED"))
          i = 0;
        break;
      case -91153859:
        if (str.equals("CHECK_CHANGED"))
          i = 2;
        break;
      case -867213746:
        if (str.equals("NETWORK_SHARE"))
          i = 4;
        break;
      case -1429708602:
        if (str.equals("STATE_CHANGED"))
          i = 1;
        break;
      }
      int i = -1;
      switch (i)
      {
      default:
        break;
      case 7:
        System.exit(0);
        break;
      case 6:
        j.b("TrineaAndroidCommon", "MainUiActivity AUTO_RECONNECT_UNSUCCESSFULLY");
        m.a(paramContext, "iscandozdcl", false);
        o.a(MainUiActivity.a, "温馨提示", "自动重连失败，请用户自己点击登录按钮进行登录。");
        MainUiActivity.a(MainUiActivity.this, false);
        break;
      case 5:
        j.b("TrineaAndroidCommon", "MainUiActivity AUTO_RECONNECT_SUCCESSFULLY");
        Toast.makeText(MainUiActivity.a, "自动重连成功", 0).show();
        c.a(paramContext);
        MainUiActivity.b();
        MainUiActivity.a();
        MainUiActivity.a(MainUiActivity.this, true);
        break;
      case 4:
        j.b("TrineaAndroidCommon", "MainUiActivity NETWORK_SHARE");
        c.a(MainUiActivity.this, "网络已断开，可能的原因：检测到USB或蓝牙网络共享冲突，请关闭对应的网络共享后重新登录。", -1);
        break;
      case 3:
        j.b("TrineaAndroidCommon", "MainUiActivity SERVICE_CHANGED");
        c.a(MainUiActivity.this, "网络已断开，可能的原因：网络状态异常或帐号已在别处登录，请稍后再试（20010009）", 20010009);
        break;
      case 2:
        j.b("TrineaAndroidCommon", "MainUiActivity CHECK_CHANGED");
        MainUiActivity.b(MainUiActivity.this);
        break;
      case 1:
        j.b("TrineaAndroidCommon", "MainUiActivity STATE_CHANGED");
        MainUiActivity.a(MainUiActivity.this, false);
        break;
      case 0:
        if ((MainUiActivity.a(MainUiActivity.this) instanceof NetworkFragment))
          ((NetworkFragment)MainUiActivity.a(MainUiActivity.this)).b();
        break;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.MainUiActivity
 * JD-Core Version:    0.6.1
 */