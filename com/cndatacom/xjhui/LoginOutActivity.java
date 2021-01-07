package com.cndatacom.xjhui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.b.g;
import com.stub.StubApp;

@SuppressLint({"NewApi"})
public class LoginOutActivity extends Activity
  implements View.OnClickListener
{
  public static Context a;
  private ImageView b;
  private TextView c;
  private TextView d;
  private ImageView e;
  private CheckBox f;
  private Button g;
  private o h;
  private SharedPreferences i = null;
  private boolean j;
  private String k;

  static
  {
    StubApp.interface11(1252);
  }

  private void a()
  {
    a = this;
    this.h = new o(this);
    this.i = getSharedPreferences("TrineaAndroidCommon", 0);
    this.b = ((ImageView)findViewById(2131034182));
    this.c = ((TextView)findViewById(2131034239));
    this.d = ((TextView)findViewById(2131034240));
    this.e = ((ImageView)findViewById(2131034183));
    this.f = ((CheckBox)findViewById(2131034147));
    this.g = ((Button)findViewById(2131034136));
    this.b.setOnClickListener(this);
    this.e.setOnClickListener(this);
    this.g.setOnClickListener(this);
    this.f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        LoginOutActivity.a(LoginOutActivity.this, paramAnonymousBoolean);
        m.a(LoginOutActivity.a, "isRememberPassword", paramAnonymousBoolean);
      }
    });
  }

  private void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      this.e.setBackgroundResource(2130968610);
      this.d.setText(this.k);
      if (paramBoolean2)
        m.a(a, "eyeischeck", true);
    }
    else
    {
      this.e.setBackgroundResource(2130968611);
      this.d.setText("···············");
      if (paramBoolean2)
        m.a(a, "eyeischeck", false);
    }
  }

  private void b()
  {
    this.c.setText(this.i.getString("UID", ""));
    this.k = this.i.getString("portal_user_password", "");
    this.j = this.i.getBoolean("isRememberPassword", false);
    this.f.setChecked(this.j);
    a(this.i.getBoolean("eyeischeck", false), false);
  }

  private void c()
  {
    if (this.j)
      m.a(a, "PID", this.k);
    else
      m.a(a, "PID", "");
  }

  public void onClick(View paramView)
  {
    int m = paramView.getId();
    if (m != 2131034136)
    {
      switch (m)
      {
      default:
        break;
      case 2131034183:
        a(this.d.getText().toString().contains("········"), true);
        break;
      case 2131034182:
        finish();
        break;
      }
    }
    else
    {
      c();
      g.a(this, this.h, 2);
    }
  }

  protected native void onCreate(Bundle paramBundle);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.LoginOutActivity
 * JD-Core Version:    0.6.1
 */