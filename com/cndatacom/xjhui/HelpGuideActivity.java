package com.cndatacom.xjhui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.b.c;
import com.stub.StubApp;
import java.io.File;

public class HelpGuideActivity extends Activity
  implements View.OnClickListener
{
  private ImageView a;
  private TextView b;
  private FrameLayout c;
  private WebView d = null;
  private ProgressBar e;
  private int f;
  private Context g;
  private o h;

  static
  {
    StubApp.interface11(1246);
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  private void a()
  {
    this.f = getIntent().getExtras().getInt("code");
    if (TextUtils.isEmpty(m.a(this.g, "ManualSubErrorDataURL")))
      this.b.setVisibility(8);
    else
      this.b.setVisibility(0);
    WebSettings localWebSettings = this.d.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setCacheMode(-1);
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(getFilesDir().getAbsolutePath());
    localStringBuilder1.append("webCache");
    localWebSettings.setAppCachePath(localStringBuilder1.toString());
    localWebSettings.setAppCacheEnabled(true);
    this.d.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        HelpGuideActivity.a(HelpGuideActivity.this).setVisibility(4);
      }

      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
        HelpGuideActivity.a(HelpGuideActivity.this).setVisibility(0);
      }

      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
    this.d.setWebChromeClient(new WebChromeClient());
    WebView localWebView = this.d;
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("file:///android_asset/index.htm#");
    localStringBuilder2.append(this.f);
    localWebView.loadUrl(localStringBuilder2.toString());
  }

  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i != 2131034179)
    {
      if (i == 2131034238)
        c.a(this.g, this.h);
    }
    else
      finish();
  }

  protected native void onCreate(Bundle paramBundle);

  protected void onDestroy()
  {
    if (this.d != null)
    {
      this.c.removeView(this.d);
      this.d.clearHistory();
      this.d.clearCache(true);
      this.d.destroy();
      this.d = null;
    }
    super.onDestroy();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.HelpGuideActivity
 * JD-Core Version:    0.6.1
 */