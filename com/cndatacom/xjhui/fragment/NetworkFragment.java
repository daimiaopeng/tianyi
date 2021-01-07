package com.cndatacom.xjhui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.j;
import com.cndatacom.e.l;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.MainUiActivity;
import com.cndatacom.xjhui.b.a;
import com.cndatacom.xjhui.b.a.b;
import com.cndatacom.xjhui.b.c;
import com.cndatacom.xjhui.b.g;
import com.karics.library.zxing.android.CaptureActivity;
import com.stub.StubApp;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"NewApi"})
public class NetworkFragment extends Fragment
  implements View.OnClickListener
{
  static MainUiActivity a;
  private static LinearLayout f;
  private static LinearLayout l;
  private static TextView m;
  private static TextView n;
  private static Timer t;
  private static long v;
  private static Timestamp w;
  private Button b;
  private Button c;
  private LinearLayout d;
  private WebView e;
  private EditText g;
  private EditText h;
  private CheckBox i;
  private CheckBox j;
  private Button k;
  private Button o;
  private RelativeLayout p;
  private o q;
  private SharedPreferences r;
  private Handler s = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 2)
        NetworkFragment.a(NetworkFragment.this);
      super.handleMessage(paramAnonymousMessage);
    }
  };
  private TimerTask u = null;

  public static Fragment a()
  {
    return new NetworkFragment();
  }

  private void a(View paramView)
  {
    this.b = ((Button)paramView.findViewById(2131034140));
    this.c = ((Button)paramView.findViewById(2131034137));
    this.d = ((LinearLayout)paramView.findViewById(2131034198));
    this.e = ((WebView)paramView.findViewById(2131034250));
    p();
    f = (LinearLayout)paramView.findViewById(2131034199);
    this.g = ((EditText)paramView.findViewById(2131034165));
    this.h = ((EditText)paramView.findViewById(2131034166));
    this.i = ((CheckBox)paramView.findViewById(2131034148));
    this.j = ((CheckBox)paramView.findViewById(2131034149));
    this.k = ((Button)paramView.findViewById(2131034138));
    l = (LinearLayout)paramView.findViewById(2131034200);
    m = (TextView)paramView.findViewById(2131034241);
    WindowManager localWindowManager = (WindowManager)getActivity().getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i1 = localDisplayMetrics.widthPixels;
    m.setMaxWidth(i1 / 2);
    n = (TextView)paramView.findViewById(2131034242);
    this.o = ((Button)paramView.findViewById(2131034139));
    this.p = ((RelativeLayout)paramView.findViewById(2131034220));
  }

  private void f()
  {
    this.r = a.getSharedPreferences("TrineaAndroidCommon", 0);
    String str1 = this.r.getString("UID", "");
    String str2 = this.r.getString("PID", "");
    boolean bool = this.r.getBoolean("isRememberPassword", false);
    this.g.setText(str1);
    this.h.setText(str2);
    this.j.setChecked(bool);
    if (this.r.getBoolean("eyeischeck", false))
    {
      this.i.setBackgroundResource(2130968610);
      this.h.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }
    else
    {
      this.i.setBackgroundResource(2130968611);
      this.h.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    this.b.setVisibility(8);
    if (c.a(a, "QRcodeEnable", "QRcodeWhite", "QRcodeBlack"))
      this.b.setVisibility(0);
    else
      this.b.setVisibility(8);
  }

  private void g()
  {
    this.b.setOnClickListener(this);
    this.c.setOnClickListener(this);
    this.k.setOnClickListener(this);
    this.o.setOnClickListener(this);
    this.i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          NetworkFragment.b(NetworkFragment.this).setBackgroundResource(2130968610);
          NetworkFragment.c(NetworkFragment.this).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else
        {
          NetworkFragment.b(NetworkFragment.this).setBackgroundResource(2130968611);
          NetworkFragment.c(NetworkFragment.this).setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        m.a(NetworkFragment.a, "eyeischeck", paramAnonymousBoolean);
      }
    });
    this.j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        m.a(NetworkFragment.a, "isRememberPassword", paramAnonymousBoolean);
      }
    });
  }

  private void h()
  {
    try
    {
      i();
      if (l.a(StubApp.getOrigApplicationContext(a.getApplicationContext())) == 3)
        c.b(a, "NWFCheck");
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "NetworkFragment updateStatus Exception");
    }
  }

  private void i()
  {
    if (l.a(MyApplication.a()) != 3)
    {
      e();
      return;
    }
    if (m.b(a, "SID", "0").equals("1"))
    {
      if (!b.g(a))
        k();
      else
        j();
      return;
    }
    k();
  }

  private void j()
  {
    f.setVisibility(8);
    l.setVisibility(0);
    this.p.setVisibility(8);
    m.setText(m.b(a, "UID", ""));
    this.u = new TimerTask()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 2;
        NetworkFragment.d(NetworkFragment.this).sendMessage(localMessage);
      }
    };
    t = new Timer();
    t.schedule(this.u, 0L, 1000L);
  }

  private void k()
  {
    f.setVisibility(0);
    l.setVisibility(8);
    this.p.setVisibility(8);
    l();
  }

  private void l()
  {
    if (t != null)
    {
      t.cancel();
      t = null;
    }
    v = 0L;
    w = null;
  }

  private void m()
  {
    if (w == null)
    {
      v = m.b(a, "preTimeMillis", 0L);
      if (v == 0L)
        w = new Timestamp(System.currentTimeMillis());
      else
        w = new Timestamp(v);
    }
  }

  private void n()
  {
    try
    {
      if ((n != null) && (w != null) && (m.b(a, "SID", "0").equals("1")))
      {
        long l1 = (new Timestamp(System.currentTimeMillis()).getTime() - w.getTime()) / 1000L;
        long l2 = l1 / 3600L;
        long l3 = l1 % 3600L;
        long l4 = l3 / 60L;
        long l5 = l3 % 60L;
        StringBuilder localStringBuilder1 = new StringBuilder();
        Object localObject1;
        if (l2 > 9L)
        {
          localObject1 = Long.valueOf(l2);
        }
        else
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("0");
          localStringBuilder2.append(l2);
          localObject1 = localStringBuilder2.toString();
        }
        localStringBuilder1.append(localObject1);
        localStringBuilder1.append(" 时 ");
        Object localObject2;
        if (l4 > 9L)
        {
          localObject2 = Long.valueOf(l4);
        }
        else
        {
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("0");
          localStringBuilder3.append(l4);
          localObject2 = localStringBuilder3.toString();
        }
        localStringBuilder1.append(localObject2);
        localStringBuilder1.append(" 分 ");
        Object localObject3;
        if (l5 > 9L)
        {
          localObject3 = Long.valueOf(l5);
        }
        else
        {
          StringBuilder localStringBuilder4 = new StringBuilder();
          localStringBuilder4.append("0");
          localStringBuilder4.append(l5);
          localObject3 = localStringBuilder4.toString();
        }
        localStringBuilder1.append(localObject3);
        localStringBuilder1.append(" 秒 ");
        n.setText(localStringBuilder1);
      }
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "NetworkFragment updateTimes Exception");
    }
  }

  private void o()
  {
    if (l.a(MyApplication.a()) == 3)
    {
      String str = m.a(a, "advertURL");
      if (TextUtils.isEmpty(str))
      {
        this.e.setVisibility(8);
      }
      else
      {
        this.e.setVisibility(0);
        this.e.loadUrl(str);
      }
    }
    else
    {
      b();
    }
  }

  private void p()
  {
    this.d.post(new Runnable()
    {
      public void run()
      {
        int i = c.a(NetworkFragment.a, NetworkFragment.e(NetworkFragment.this).getMeasuredWidth());
        int j = c.a(NetworkFragment.a, NetworkFragment.e(NetworkFragment.this).getMeasuredHeight());
        m.a(NetworkFragment.a, "advertWidth", i);
        m.a(NetworkFragment.a, "advertHeight", j);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("屏幕的宽度：");
        localStringBuilder.append(i);
        localStringBuilder.append("dp");
        j.a("test", localStringBuilder.toString());
      }
    });
    WebSettings localWebSettings = this.e.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setCacheMode(2);
    this.e.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
      }

      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
      }

      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        super.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
        NetworkFragment.f(NetworkFragment.this).setVisibility(8);
      }

      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (paramAnonymousString.equals(m.b(NetworkFragment.a, "advertURL", "")))
        {
          paramAnonymousWebView.loadUrl(paramAnonymousString);
        }
        else
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString));
          NetworkFragment.this.startActivity(localIntent);
        }
        return true;
      }
    });
  }

  public void b()
  {
    this.e.setVisibility(8);
  }

  public void c()
  {
    if (this.e.getVisibility() == 8)
      o();
    f();
    m();
    h();
  }

  public void d()
  {
    l();
    c();
  }

  public void e()
  {
    f.setVisibility(8);
    l.setVisibility(8);
    this.p.setVisibility(0);
    l();
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    a = (MainUiActivity)paramActivity;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      break;
    case 2131034140:
      if (l.isShown())
      {
        String str3 = this.r.getString("UID", "");
        String str4 = this.r.getString("portal_user_password", "");
        if ((!TextUtils.isEmpty(str3)) && (!TextUtils.isEmpty(str4)))
        {
          Intent localIntent = new Intent(a, CaptureActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putString("account", str3);
          localBundle.putString("password", str4);
          localIntent.putExtras(localBundle);
          a.startActivity(localIntent);
        }
        else
        {
          c.b(a);
        }
      }
      else
      {
        c.b(a);
      }
      break;
    case 2131034139:
      g.a(a, this.q, 1);
      break;
    case 2131034138:
      String str1 = this.g.getText().toString();
      String str2 = this.h.getText().toString();
      if (TextUtils.isEmpty(str1))
      {
        Toast.makeText(a, "账号不能为空", 0).show();
        return;
      }
      if (TextUtils.isEmpty(str2))
      {
        Toast.makeText(a, "密码不能为空", 0).show();
        return;
      }
      a.a(a, str1, str2, "", this.q);
      break;
    case 2131034137:
      c.a(a, 0);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2131165200, paramViewGroup, false);
    j.a("test", "NetworkFragment onCreateView");
    this.q = new o(a);
    a(localView);
    f();
    g();
    m();
    i();
    return localView;
  }

  public void onDestroy()
  {
    j.a("test", "NetworkFragment onDestroy()");
    w = null;
    super.onDestroy();
  }

  public void onResume()
  {
    super.onResume();
    j.a("test", "NetworkFragment onResume() 完全退出app，再打开app，会执行两次");
    c();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.fragment.NetworkFragment
 * JD-Core Version:    0.6.1
 */