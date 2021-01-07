package com.cndatacom.xjhui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.e.a.a;
import com.cndatacom.e.f;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.xjhui.b.a.d;
import com.stub.StubApp;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Date;

public class AccountDataActivity extends Activity
{
  public f a = new f(this, this);
  TextView b;
  a c = new a(this);
  private LinearLayout d;
  private ImageView e;
  private WebView f = null;
  private boolean g = false;
  private Context h;
  private Thread i = null;

  static
  {
    StubApp.interface11(1244);
  }

  private int a(Date paramDate)
  {
    if (paramDate == null)
      return 0;
    return Integer.valueOf(String.valueOf(paramDate.getTime() / 1000L)).intValue();
  }

  private String a(String paramString1, String paramString2)
  {
    int j = a(new Date());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("$");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("$");
    localStringBuilder.append(j);
    String str1 = localStringBuilder.toString();
    try
    {
      String str2 = URLEncoder.encode(a.b(str1.getBytes("utf-8")), "utf-8");
      return str2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private void a()
  {
    this.f = ((WebView)findViewById(2131034113));
    this.f.getSettings().setJavaScriptEnabled(true);
    this.f.requestFocus();
    this.f.getSettings().setAllowFileAccess(true);
    this.f.getSettings().setCacheMode(-1);
    this.f.getSettings().setDomStorageEnabled(true);
    this.f.getSettings().setDatabaseEnabled(true);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getFilesDir().getAbsolutePath());
    localStringBuilder.append("webCache");
    String str = localStringBuilder.toString();
    this.f.getSettings().setAppCachePath(str);
    this.f.getSettings().setAppCacheEnabled(true);
    this.f.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        AccountDataActivity.a(AccountDataActivity.this, true);
        AccountDataActivity.this.a.a();
        Message localMessage = AccountDataActivity.this.c.obtainMessage();
        localMessage.what = 603;
        AccountDataActivity.this.c.sendMessage(localMessage);
      }

      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
      }

      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
  }

  private void b()
  {
    String str1 = m.b(this.h, "PackageQueryURL", "");
    if (TextUtils.isEmpty(str1))
    {
      j.b("TrineaAndroidCommon", "AccountDataActivity url == null");
      str1 = "http://zsteduapp.10000.gd.cn/GDTC/PackRedirect.html";
    }
    j.b("TrineaAndroidCommon", "AccountDataActivity do");
    String str2 = m.b(this, "UID", "");
    String str3 = m.b(this, "ticket", "");
    String str4 = m.b(this, "domain", "");
    String str5 = m.b(this, "schoolid", "");
    if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str3)))
    {
      String str6 = a(str2, str3);
      if (TextUtils.isEmpty(str6))
      {
        Toast.makeText(this, "token参数获取失败，请重新登录", 0).show();
        d();
        return;
      }
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("");
      localStringBuilder1.append("Token=");
      localStringBuilder1.append(str6);
      String str7 = localStringBuilder1.toString();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(str7);
      localStringBuilder2.append("&domain=");
      localStringBuilder2.append(str4);
      String str8 = localStringBuilder2.toString();
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(str8);
      localStringBuilder3.append("&cid=");
      localStringBuilder3.append(str5);
      String str9 = localStringBuilder3.toString();
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append(str9);
      localStringBuilder4.append("&ostype=");
      localStringBuilder4.append("android");
      String str10 = localStringBuilder4.toString();
      StringBuilder localStringBuilder5 = new StringBuilder();
      localStringBuilder5.append(str10);
      localStringBuilder5.append("&QueryType=1");
      String str11 = localStringBuilder5.toString();
      StringBuilder localStringBuilder6 = new StringBuilder();
      localStringBuilder6.append(str11);
      localStringBuilder6.append("&Version=");
      localStringBuilder6.append(d.d(StubApp.getOrigApplicationContext(getApplicationContext())));
      String str12 = localStringBuilder6.toString();
      StringBuilder localStringBuilder7 = new StringBuilder();
      localStringBuilder7.append(str1);
      localStringBuilder7.append("?");
      localStringBuilder7.append(str12);
      String str13 = localStringBuilder7.toString();
      this.f.loadUrl(str13);
      this.a.a("通讯中，请稍后...");
      c();
      return;
    }
    Toast.makeText(this, "参数获取失败，请重新登录", 0).show();
    d();
  }

  private void c()
  {
    this.i = new Thread()
    {
      public void run()
      {
        try
        {
          Thread.sleep(40000L);
          if (!AccountDataActivity.a(AccountDataActivity.this))
          {
            Message localMessage = AccountDataActivity.this.c.obtainMessage();
            localMessage.what = 604;
            AccountDataActivity.this.c.sendMessage(localMessage);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          j.a("TrineaAndroidCommon", localInterruptedException, "AccountDataActivity waittime InterruptedException");
        }
      }
    };
    this.i.start();
  }

  private void d()
  {
    this.a.a();
    this.f.setVisibility(8);
    this.b.setText("参数获取失败，请重新登录");
    this.b.setVisibility(0);
  }

  private void e()
  {
    findViewById(2131034112).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AccountDataActivity.this.finish();
      }
    });
  }

  public void onBackPressed()
  {
    if (this.f.canGoBack())
      this.f.goBack();
    else
      super.onBackPressed();
  }

  protected native void onCreate(Bundle paramBundle);

  protected void onDestroy()
  {
    super.onDestroy();
    j.a("AccountDataActivity", "帐号信息查询onDestroy");
    this.d.removeView(this.f);
    this.f.removeAllViews();
    this.f.destroy();
    if (this.i != null)
    {
      this.i.interrupt();
      if ((this.i.isAlive()) && (!this.i.isInterrupted()))
        this.i.interrupt();
      this.i = null;
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  static class a extends Handler
  {
    WeakReference<AccountDataActivity> a;

    a(AccountDataActivity paramAccountDataActivity)
    {
      this.a = new WeakReference(paramAccountDataActivity);
    }

    public void handleMessage(Message paramMessage)
    {
      AccountDataActivity localAccountDataActivity = (AccountDataActivity)this.a.get();
      if ((localAccountDataActivity != null) && (!localAccountDataActivity.isFinishing()))
      {
        if (paramMessage.what == 603)
        {
          AccountDataActivity.b(localAccountDataActivity).setVisibility(0);
          localAccountDataActivity.b.setVisibility(4);
        }
        else if (paramMessage.what == 604)
        {
          localAccountDataActivity.a.a();
          AccountDataActivity.b(localAccountDataActivity).setVisibility(4);
          localAccountDataActivity.b.setText("加载失败，请重新进入该功能");
          localAccountDataActivity.b.setVisibility(0);
        }
        super.handleMessage(paramMessage);
        return;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.AccountDataActivity
 * JD-Core Version:    0.6.1
 */