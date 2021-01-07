package com.cndatacom.xjhui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.b.c;
import com.stub.StubApp;

@SuppressLint({"NewApi"})
public class LoginActivity extends Activity
  implements View.OnClickListener
{
  private ImageView a;
  private EditText b;
  private EditText c;
  private CheckBox d;
  private CheckBox e;
  private Button f;
  private Context g;
  private o h;
  private SharedPreferences i;
  private AsyncTask<Object, Object, Object> j = null;

  static
  {
    StubApp.interface11(1250);
  }

  private void a()
  {
    this.g = this;
    this.i = getSharedPreferences("TrineaAndroidCommon", 0);
    this.h = new o(this);
    this.a = ((ImageView)findViewById(2131034180));
    this.b = ((EditText)findViewById(2131034163));
    this.c = ((EditText)findViewById(2131034164));
    this.d = ((CheckBox)findViewById(2131034143));
    this.e = ((CheckBox)findViewById(2131034144));
    this.f = ((Button)findViewById(2131034135));
    this.a.setOnClickListener(this);
    this.f.setOnClickListener(this);
    this.d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          LoginActivity.a(LoginActivity.this).setBackgroundResource(2130968610);
          LoginActivity.b(LoginActivity.this).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else
        {
          LoginActivity.a(LoginActivity.this).setBackgroundResource(2130968611);
          LoginActivity.b(LoginActivity.this).setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        m.a(LoginActivity.c(LoginActivity.this), "eyeischeck", paramAnonymousBoolean);
      }
    });
    this.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        m.a(LoginActivity.c(LoginActivity.this), "isRememberPassword", paramAnonymousBoolean);
      }
    });
  }

  private void a(Object paramObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LoginActivity callbackRequest : ");
    localStringBuilder.append(paramObject);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    this.j = null;
    this.h.b();
    int k = Integer.parseInt(paramObject.toString());
    if (k == 0)
    {
      Toast.makeText(this.g, "登录成功", 0).show();
      m.a(this.g, "iscandozdcl", true);
      com.cndatacom.xjhui.b.a.b.d(this.g);
      c.e(this.g);
      c.a(this.g);
      MainUiActivity.a();
      finish();
    }
    else
    {
      c.a(this.g, com.cndatacom.xjhui.b.b.a(k), k);
    }
  }

  private void a(final String paramString1, final String paramString2)
  {
    this.j = new AsyncTask()
    {
      // ERROR //
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        // Byte code:
        //   0: sipush 333
        //   3: istore_2
        //   4: aload_0
        //   5: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   8: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   11: invokestatic 37	com/cndatacom/xjhui/b/g:b	(Landroid/content/Context;)Z
        //   14: istore 4
        //   16: new 39	java/lang/StringBuilder
        //   19: dup
        //   20: invokespecial 40	java/lang/StringBuilder:<init>	()V
        //   23: astore 5
        //   25: aload 5
        //   27: ldc 42
        //   29: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   32: pop
        //   33: aload 5
        //   35: iload 4
        //   37: invokevirtual 49	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   40: pop
        //   41: ldc 51
        //   43: aload 5
        //   45: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   48: invokestatic 59	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   51: iload 4
        //   53: ifeq +13 -> 66
        //   56: aload_0
        //   57: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   60: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   63: invokestatic 63	com/cndatacom/xjhui/b/g:e	(Landroid/content/Context;)V
        //   66: aload_0
        //   67: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   70: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   73: invokestatic 68	com/cndatacom/xjhui/b/a:a	(Landroid/content/Context;)J
        //   76: lstore 8
        //   78: new 39	java/lang/StringBuilder
        //   81: dup
        //   82: invokespecial 40	java/lang/StringBuilder:<init>	()V
        //   85: astore 10
        //   87: aload 10
        //   89: ldc 70
        //   91: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   94: pop
        //   95: aload 10
        //   97: lload 8
        //   99: invokevirtual 73	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
        //   102: pop
        //   103: aload 10
        //   105: ldc 75
        //   107: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: pop
        //   111: ldc 51
        //   113: aload 10
        //   115: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   118: invokestatic 59	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   121: lload 8
        //   123: invokestatic 81	java/lang/Thread:sleep	(J)V
        //   126: aload_0
        //   127: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   130: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   133: ldc 83
        //   135: invokestatic 86	com/cndatacom/xjhui/b/a:a	(Landroid/content/Context;Ljava/lang/String;)I
        //   138: istore 14
        //   140: iload 14
        //   142: sipush 303
        //   145: if_icmpeq +355 -> 500
        //   148: iload 14
        //   150: tableswitch	default:+22 -> 172, 991:+343->493, 992:+336->486
        //   173: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   176: invokestatic 90	com/cndatacom/xjhui/b/a/b:e	(Landroid/content/Context;)Z
        //   179: ifeq +293 -> 472
        //   182: aload_0
        //   183: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   186: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   189: aload_0
        //   190: getfield 20	com/cndatacom/xjhui/LoginActivity$3:a	Ljava/lang/String;
        //   193: iconst_0
        //   194: invokestatic 95	com/cndatacom/xjhui/b/i:a	(Landroid/content/Context;Ljava/lang/String;Z)I
        //   197: istore 16
        //   199: iload 16
        //   201: sipush 200
        //   204: if_icmpeq +10 -> 214
        //   207: iload 16
        //   209: bipush 13
        //   211: if_icmpne +20 -> 231
        //   214: aload_0
        //   215: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   218: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   221: aload_0
        //   222: getfield 20	com/cndatacom/xjhui/LoginActivity$3:a	Ljava/lang/String;
        //   225: iconst_0
        //   226: invokestatic 95	com/cndatacom/xjhui/b/i:a	(Landroid/content/Context;Ljava/lang/String;Z)I
        //   229: istore 16
        //   231: iload 16
        //   233: ifne +299 -> 532
        //   236: aload_0
        //   237: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   240: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   243: invokestatic 97	com/cndatacom/xjhui/b/a/b:a	(Landroid/content/Context;)Z
        //   246: ifeq +286 -> 532
        //   249: aload_0
        //   250: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   253: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   256: aload_0
        //   257: getfield 20	com/cndatacom/xjhui/LoginActivity$3:a	Ljava/lang/String;
        //   260: aload_0
        //   261: getfield 22	com/cndatacom/xjhui/LoginActivity$3:b	Ljava/lang/String;
        //   264: ldc 99
        //   266: invokestatic 104	com/cndatacom/xjhui/b/d:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //   269: istore 19
        //   271: iload 19
        //   273: sipush 200
        //   276: if_icmpeq +19 -> 295
        //   279: iload 19
        //   281: bipush 13
        //   283: if_icmpne +6 -> 289
        //   286: goto +9 -> 295
        //   289: iload 19
        //   291: istore_2
        //   292: goto +24 -> 316
        //   295: aload_0
        //   296: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   299: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   302: aload_0
        //   303: getfield 20	com/cndatacom/xjhui/LoginActivity$3:a	Ljava/lang/String;
        //   306: aload_0
        //   307: getfield 22	com/cndatacom/xjhui/LoginActivity$3:b	Ljava/lang/String;
        //   310: ldc 99
        //   312: invokestatic 104	com/cndatacom/xjhui/b/d:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //   315: istore_2
        //   316: iload_2
        //   317: ifne +203 -> 520
        //   320: aload_0
        //   321: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   324: invokestatic 32	com/cndatacom/xjhui/LoginActivity:c	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/Context;
        //   327: invokestatic 105	com/cndatacom/xjhui/b/a/b:b	(Landroid/content/Context;)Z
        //   330: ifeq +190 -> 520
        //   333: aload_0
        //   334: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   337: invokestatic 108	com/cndatacom/xjhui/LoginActivity:e	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/SharedPreferences;
        //   340: invokeinterface 114 1 0
        //   345: astore 20
        //   347: aload 20
        //   349: ldc 116
        //   351: ldc 118
        //   353: invokeinterface 124 3 0
        //   358: pop
        //   359: aload 20
        //   361: ldc 126
        //   363: aload_0
        //   364: getfield 20	com/cndatacom/xjhui/LoginActivity$3:a	Ljava/lang/String;
        //   367: invokeinterface 124 3 0
        //   372: pop
        //   373: aload 20
        //   375: ldc 128
        //   377: aload_0
        //   378: getfield 22	com/cndatacom/xjhui/LoginActivity$3:b	Ljava/lang/String;
        //   381: invokeinterface 124 3 0
        //   386: pop
        //   387: aload 20
        //   389: ldc 130
        //   391: aload_0
        //   392: getfield 22	com/cndatacom/xjhui/LoginActivity$3:b	Ljava/lang/String;
        //   395: invokeinterface 124 3 0
        //   400: pop
        //   401: aload 20
        //   403: ldc 132
        //   405: invokestatic 138	java/lang/System:currentTimeMillis	()J
        //   408: invokeinterface 142 4 0
        //   413: pop
        //   414: aload_0
        //   415: getfield 18	com/cndatacom/xjhui/LoginActivity$3:c	Lcom/cndatacom/xjhui/LoginActivity;
        //   418: invokestatic 108	com/cndatacom/xjhui/LoginActivity:e	(Lcom/cndatacom/xjhui/LoginActivity;)Landroid/content/SharedPreferences;
        //   421: ldc 144
        //   423: iconst_0
        //   424: invokeinterface 148 3 0
        //   429: ifeq +20 -> 449
        //   432: aload 20
        //   434: ldc 150
        //   436: aload_0
        //   437: getfield 22	com/cndatacom/xjhui/LoginActivity$3:b	Ljava/lang/String;
        //   440: invokeinterface 124 3 0
        //   445: pop
        //   446: goto +15 -> 461
        //   449: aload 20
        //   451: ldc 150
        //   453: ldc 99
        //   455: invokeinterface 124 3 0
        //   460: pop
        //   461: aload 20
        //   463: invokeinterface 154 1 0
        //   468: pop
        //   469: goto +51 -> 520
        //   472: ldc 51
        //   474: ldc 156
        //   476: invokestatic 59	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   479: sipush 992
        //   482: istore_2
        //   483: goto +37 -> 520
        //   486: sipush 992
        //   489: invokestatic 162	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   492: areturn
        //   493: sipush 991
        //   496: invokestatic 162	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   499: areturn
        //   500: sipush 303
        //   503: invokestatic 162	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   506: astore 15
        //   508: aload 15
        //   510: areturn
        //   511: astore_3
        //   512: ldc 51
        //   514: aload_3
        //   515: ldc 164
        //   517: invokestatic 167	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
        //   520: iload_2
        //   521: invokestatic 162	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   524: areturn
        //   525: astore_3
        //   526: iload 19
        //   528: istore_2
        //   529: goto -17 -> 512
        //   532: iload 16
        //   534: istore_2
        //   535: goto -15 -> 520
        //   538: astore 17
        //   540: iload 16
        //   542: istore 18
        //   544: aload 17
        //   546: astore_3
        //   547: iload 18
        //   549: istore_2
        //   550: goto -38 -> 512
        //
        // Exception table:
        //   from	to	target	type
        //   4	199	511	java/lang/Exception
        //   320	508	511	java/lang/Exception
        //   295	316	525	java/lang/Exception
        //   214	271	538	java/lang/Exception
      }

      protected void onCancelled()
      {
        LoginActivity.a(LoginActivity.this, null);
        LoginActivity.d(LoginActivity.this).b();
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        LoginActivity.a(LoginActivity.this, paramAnonymousObject);
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
        LoginActivity.d(LoginActivity.this).a();
      }
    };
    this.j.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  private void b()
  {
    this.b.setText(this.i.getString("UID", ""));
    this.c.setText(this.i.getString("PID", ""));
    this.d.setChecked(this.i.getBoolean("eyeischeck", false));
    this.e.setChecked(this.i.getBoolean("isRememberPassword", false));
  }

  private void c()
  {
    String str1 = this.b.getText().toString();
    String str2 = this.c.getText().toString();
    if (TextUtils.isEmpty(str1))
    {
      this.b.requestFocus();
      Toast.makeText(this.g, "账号不能为空", 0).show();
      return;
    }
    if (TextUtils.isEmpty(str2))
    {
      this.c.requestFocus();
      Toast.makeText(this.g, "密码不能为空", 0).show();
      return;
    }
    if (this.j == null)
      a(str1, str2);
    else
      this.h.a();
  }

  public void onClick(View paramView)
  {
    int k = paramView.getId();
    if (k != 2131034135)
    {
      if (k == 2131034180)
        finish();
    }
    else
      c();
  }

  protected native void onCreate(Bundle paramBundle);

  public void onDestroy()
  {
    if ((this.j != null) && (!this.j.isCancelled()))
      this.j.cancel(true);
    super.onDestroy();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.LoginActivity
 * JD-Core Version:    0.6.1
 */