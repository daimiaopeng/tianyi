package com.cndatacom.xjhui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import com.cndatacom.e.m;
import com.cndatacom.e.p;
import com.stub.StubApp;

public class LoginSettingActivity extends Activity
{
  private ImageView a;
  private CheckBox b;
  private CheckBox c;
  private p d;

  static
  {
    StubApp.interface11(1256);
  }

  private void a()
  {
    boolean bool1 = m.b(this, "iskeepwifi", true);
    boolean bool2 = m.b(this, "iszdlj", true);
    this.b.setChecked(bool1);
    this.c.setChecked(bool2);
    this.d = new p(this);
  }

  private void b()
  {
    this.a.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LoginSettingActivity.this.finish();
      }
    });
    this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        m.a(LoginSettingActivity.this, "iskeepwifi", paramAnonymousBoolean);
        if (paramAnonymousBoolean)
          LoginSettingActivity.a(LoginSettingActivity.this).a();
        else
          LoginSettingActivity.a(LoginSettingActivity.this).b();
      }
    });
    this.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        m.a(LoginSettingActivity.this, "iszdlj", paramAnonymousBoolean);
      }
    });
  }

  protected native void onCreate(Bundle paramBundle);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.LoginSettingActivity
 * JD-Core Version:    0.6.1
 */