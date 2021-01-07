package com.cndatacom.xjhui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cndatacom.e.j;
import com.cndatacom.e.l;
import com.cndatacom.e.m;
import com.cndatacom.xjhui.AboutAppActivity;
import com.cndatacom.xjhui.LoginActivity;
import com.cndatacom.xjhui.LoginOutActivity;
import com.cndatacom.xjhui.LoginSettingActivity;
import com.cndatacom.xjhui.MainUiActivity;
import com.cndatacom.xjhui.b.c;
import com.stub.StubApp;

public class MeFragment extends Fragment
  implements View.OnClickListener
{
  private static TextView b;
  private static boolean i;
  private LinearLayout a;
  private LinearLayout c;
  private LinearLayout d;
  private LinearLayout e;
  private LinearLayout f;
  private LinearLayout g;
  private LinearLayout h;
  private MainUiActivity j;

  public static Fragment a()
  {
    return new MeFragment();
  }

  private void e()
  {
    String str = m.b(this.j, "UID", "");
    TextView localTextView = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(str);
    localTextView.setText(localStringBuilder.toString());
    i = true;
    f();
  }

  private void f()
  {
    if (TextUtils.isEmpty(m.b(this.j, "PackageQueryURL", "")))
      this.d.setVisibility(8);
    else
      this.d.setVisibility(0);
    if (c.a(this.j, "ManagerInternetEnable", "ManagerInternetWhite", "ManagerInternetBlack"))
      this.e.setVisibility(0);
    else
      this.e.setVisibility(8);
  }

  private void g()
  {
    b.setText("");
    i = false;
    h();
  }

  private void h()
  {
    this.d.setVisibility(8);
    this.e.setVisibility(8);
  }

  protected void b()
  {
    if (l.a(StubApp.getOrigApplicationContext(this.j.getApplicationContext())) == 3)
    {
      if (m.b(getContext(), "SID", "0").equals("0"))
        g();
      else if ("".equals(m.b(getContext(), "WifiInfo", "")))
        g();
      else
        e();
    }
    else
      g();
  }

  public void c()
  {
    b();
    try
    {
      if (l.a(StubApp.getOrigApplicationContext(this.j.getApplicationContext())) == 3)
        c.b(this.j, "MFCheck");
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "MeFragment AuthCore.CheckInFrgment Exception");
    }
  }

  public void d()
  {
    g();
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.j = ((MainUiActivity)paramActivity);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      break;
    case 2131034197:
      c.a(this.j, m.b(this.j, "UID", ""), m.b(this.j, "schoolid", ""), true);
      break;
    case 2131034196:
      c.c(this.j);
      break;
    case 2131034195:
      c.d(this.j);
      break;
    case 2131034194:
      Intent localIntent2 = new Intent(this.j, LoginSettingActivity.class);
      this.j.startActivity(localIntent2);
      break;
    case 2131034193:
      if (i)
        this.j.startActivity(new Intent(this.j, LoginOutActivity.class));
      else
        this.j.startActivity(new Intent(this.j, LoginActivity.class));
      break;
    case 2131034192:
      c.a(this.j, 0);
      break;
    case 2131034191:
      Intent localIntent1 = new Intent(this.j, AboutAppActivity.class);
      this.j.startActivity(localIntent1);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2131165199, paramViewGroup, false);
    this.a = ((LinearLayout)localView.findViewById(2131034193));
    b = (TextView)localView.findViewById(2131034237);
    this.c = ((LinearLayout)localView.findViewById(2131034194));
    this.e = ((LinearLayout)localView.findViewById(2131034196));
    this.e.setVisibility(8);
    this.f = ((LinearLayout)localView.findViewById(2131034192));
    this.g = ((LinearLayout)localView.findViewById(2131034197));
    this.h = ((LinearLayout)localView.findViewById(2131034191));
    this.d = ((LinearLayout)localView.findViewById(2131034195));
    this.d.setVisibility(8);
    this.a.setOnClickListener(this);
    this.c.setOnClickListener(this);
    this.e.setOnClickListener(this);
    this.f.setOnClickListener(this);
    this.g.setOnClickListener(this);
    this.h.setOnClickListener(this);
    this.d.setOnClickListener(this);
    b();
    return localView;
  }

  public void onResume()
  {
    c();
    super.onResume();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.fragment.MeFragment
 * JD-Core Version:    0.6.1
 */