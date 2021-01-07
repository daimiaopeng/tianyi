package com.karics.library.zxing.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.karics.library.zxing.view.ViewfinderView;
import com.stub.StubApp;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivity extends Activity
  implements SurfaceHolder.Callback
{
  private static final String b = "CaptureActivity";
  AlertDialog a;
  private com.karics.library.zxing.a.d c;
  private b d;
  private ViewfinderView e;
  private boolean f;
  private e g;
  private Collection<BarcodeFormat> h;
  private Map<DecodeHintType, ?> i;
  private String j;
  private d k;
  private a l;
  private ImageView m;
  private Context n;
  private AsyncTask<Object, Object, Object> o = null;
  private ProgressDialog p;
  private String q;
  private String r;
  private Handler s = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 3)
        CaptureActivity.b(CaptureActivity.this, paramAnonymousMessage.obj.toString());
      super.handleMessage(paramAnonymousMessage);
    }
  };

  static
  {
    StubApp.interface11(1716);
  }

  private void a(SurfaceHolder paramSurfaceHolder)
  {
    if (paramSurfaceHolder == null)
      throw new IllegalStateException("No SurfaceHolder provided");
    if (this.c.a())
      return;
    try
    {
      this.c.a(paramSurfaceHolder);
      if (this.d == null)
      {
        b localb = new b(this, this.h, this.i, this.j, this.c);
        this.d = localb;
      }
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "CaptureActivity initCamera Exception");
      g();
    }
  }

  private void a(String paramString)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    localMessage.obj = paramString;
    this.s.sendMessage(localMessage);
  }

  @SuppressLint({"NewApi"})
  private void a(final String paramString1, final String paramString2)
  {
    this.o = new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("CaptureActivity doAuthForPC authParam : ");
        localStringBuilder.append(paramString2);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        int i;
        if (TextUtils.isEmpty(paramString2))
        {
          if (!com.cndatacom.xjhui.b.a.a.b(CaptureActivity.c(CaptureActivity.this)))
          {
            j.a("CaptureActivity", "CaptureActivity doAuthForPC getConfig");
            j.b("TrineaAndroidCommon", "CaptureActivity doAuthForPC getConfig");
            com.cndatacom.xjhui.b.a.a.c(CaptureActivity.c(CaptureActivity.this));
          }
          if (com.cndatacom.xjhui.b.a.a.b(CaptureActivity.c(CaptureActivity.this)))
          {
            j.a("CaptureActivity", "CaptureActivity doAuthForPC is portalNetwork");
            j.b("TrineaAndroidCommon", "CaptureActivity doAuthForPC is portalNetwork");
            i = com.cndatacom.xjhui.b.d.a(CaptureActivity.c(CaptureActivity.this), CaptureActivity.d(CaptureActivity.this), CaptureActivity.e(CaptureActivity.this), paramString1, "");
            if ((i == 13) || (i == 200))
              i = com.cndatacom.xjhui.b.d.a(CaptureActivity.c(CaptureActivity.this), CaptureActivity.d(CaptureActivity.this), CaptureActivity.e(CaptureActivity.this), paramString1, "");
          }
          else
          {
            j.a("CaptureActivity", "CaptureActivity doAuthForPC no portalNetwork");
            j.b("TrineaAndroidCommon", "CaptureActivity doAuthForPC no portalNetwork");
            i = 992;
          }
        }
        else
        {
          i = com.cndatacom.xjhui.b.d.a(CaptureActivity.c(CaptureActivity.this), CaptureActivity.d(CaptureActivity.this), CaptureActivity.e(CaptureActivity.this), paramString1, paramString2, "");
          if ((i == 13) || (i == 200))
            i = com.cndatacom.xjhui.b.d.a(CaptureActivity.c(CaptureActivity.this), CaptureActivity.d(CaptureActivity.this), CaptureActivity.e(CaptureActivity.this), paramString1, paramString2, "");
        }
        return Integer.valueOf(i);
      }

      protected void onCancelled()
      {
        CaptureActivity.a(CaptureActivity.this, null);
        CaptureActivity.this.e();
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        int i = Integer.parseInt(paramAnonymousObject.toString());
        m.a(CaptureActivity.c(CaptureActivity.this), "PCAccount", CaptureActivity.d(CaptureActivity.this));
        if (i == 0)
        {
          Toast.makeText(CaptureActivity.this, "认证登录成功", 0).show();
          m.a(CaptureActivity.c(CaptureActivity.this), "PCPassword", CaptureActivity.e(CaptureActivity.this));
          CaptureActivity.a(CaptureActivity.this, null);
          CaptureActivity.this.e();
          CaptureActivity.this.finish();
        }
        else
        {
          m.a(CaptureActivity.c(CaptureActivity.this), "PCPassword", "");
          String str = com.cndatacom.xjhui.b.b.a(i);
          CaptureActivity.a(CaptureActivity.this, str);
          CaptureActivity.a(CaptureActivity.this, null);
          CaptureActivity.this.e();
          if (CaptureActivity.a(CaptureActivity.this) != null)
            CaptureActivity.a(CaptureActivity.this).b();
        }
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
        CaptureActivity.this.d();
      }
    };
    this.o.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  private void a(final String paramString1, String paramString2, final String paramString3)
  {
    if (this.a == null)
      this.a = new AlertDialog.Builder(this).create();
    View localView = LayoutInflater.from(this).inflate(2131165197, null);
    TextView localTextView = (TextView)localView.findViewById(2131034236);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("确定要为计算机：");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("\n认证上网吗？");
    localTextView.setText(localStringBuilder.toString());
    localView.findViewById(2131034133).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CaptureActivity.this.a.dismiss();
        if (CaptureActivity.a(CaptureActivity.this) != null)
          CaptureActivity.a(CaptureActivity.this).b();
      }
    });
    localView.findViewById(2131034134).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CaptureActivity.this.a.dismiss();
        if (CaptureActivity.b(CaptureActivity.this) == null)
          CaptureActivity.a(CaptureActivity.this, paramString1, paramString3);
      }
    });
    this.a.setCancelable(false);
    this.a.show();
    this.a.getWindow().setContentView(localView);
    this.a.getWindow().setLayout(-1, -1);
  }

  private void b(String paramString)
  {
    com.cndatacom.b.a.a(this, "温馨提示", paramString);
  }

  private void f()
  {
    this.c = new com.karics.library.zxing.a.d(getApplication());
    this.e = ((ViewfinderView)findViewById(2131034247));
    this.e.setCameraManager(this.c);
    this.d = null;
    SurfaceHolder localSurfaceHolder = ((SurfaceView)findViewById(2131034210)).getHolder();
    if (this.f)
      a(localSurfaceHolder);
    else
      localSurfaceHolder.addCallback(this);
    this.l.a();
    this.k.c();
    this.g = e.d;
    this.h = null;
    this.j = null;
  }

  private void g()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("温馨提示");
    localBuilder.setMessage(getString(2131296305));
    localBuilder.setPositiveButton(2131296270, new c(this));
    localBuilder.setOnCancelListener(new c(this));
    localBuilder.show();
  }

  public ViewfinderView a()
  {
    return this.e;
  }

  public void a(Result paramResult, Bitmap paramBitmap, float paramFloat)
  {
    this.k.a();
    int i1;
    if (paramBitmap != null)
      i1 = 1;
    else
      i1 = 0;
    if (i1 != 0)
    {
      this.l.b();
      String str1 = paramResult.getText();
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("CaptureActivity str : ");
      localStringBuilder1.append(str1);
      j.a("CaptureActivity", localStringBuilder1.toString());
      if (str1.contains("cctp://"))
      {
        String[] arrayOfString = str1.replace("cctp://", "").split("@");
        String str2 = arrayOfString[0];
        String str3 = arrayOfString[1];
        int i2 = arrayOfString.length;
        String str4 = null;
        if (i2 > 2)
          str4 = arrayOfString[2];
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("CaptureActivity ticket : ");
        localStringBuilder2.append(str2);
        j.a("CaptureActivity", localStringBuilder2.toString());
        StringBuilder localStringBuilder3 = new StringBuilder();
        localStringBuilder3.append("CaptureActivity hostname : ");
        localStringBuilder3.append(str3);
        j.a("CaptureActivity", localStringBuilder3.toString());
        StringBuilder localStringBuilder4 = new StringBuilder();
        localStringBuilder4.append("CaptureActivity authParam : ");
        localStringBuilder4.append(str4);
        j.a("CaptureActivity", localStringBuilder4.toString());
        StringBuilder localStringBuilder5 = new StringBuilder();
        localStringBuilder5.append("CaptureActivity account : ");
        localStringBuilder5.append(this.q);
        j.a("CaptureActivity", localStringBuilder5.toString());
        StringBuilder localStringBuilder6 = new StringBuilder();
        localStringBuilder6.append("CaptureActivity password : ");
        localStringBuilder6.append(this.r);
        j.a("CaptureActivity", localStringBuilder6.toString());
        a(str2, str3, str4);
      }
      else
      {
        Toast.makeText(this, "不支持此二维码认证", 1).show();
        if (this.d != null)
          this.d.b();
      }
    }
  }

  public Handler b()
  {
    return this.d;
  }

  public com.karics.library.zxing.a.d c()
  {
    return this.c;
  }

  protected void d()
  {
    if (this.p == null)
    {
      this.p = new ProgressDialog(this);
      this.p.setCanceledOnTouchOutside(false);
      this.p.setProgressStyle(0);
      this.p.setMessage("通讯中，请稍后！");
    }
    this.p.show();
  }

  protected void e()
  {
    if ((this.p != null) && (this.p.isShowing()))
      this.p.dismiss();
  }

  public native void onCreate(Bundle paramBundle);

  protected void onDestroy()
  {
    this.k.d();
    super.onDestroy();
    if ((this.o != null) && (!this.o.isCancelled()))
      this.o.cancel(true);
  }

  protected void onPause()
  {
    if (this.d != null)
    {
      this.d.a();
      this.d = null;
    }
    this.k.b();
    this.l.close();
    this.c.b();
    if (!this.f)
      ((SurfaceView)findViewById(2131034210)).getHolder().removeCallback(this);
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    f();
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (!this.f)
    {
      this.f = true;
      a(paramSurfaceHolder);
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.f = false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.android.CaptureActivity
 * JD-Core Version:    0.6.1
 */