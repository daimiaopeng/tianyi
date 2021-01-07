package com.cndatacom.e;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class f
{
  ProgressDialog a = null;
  private Context b;
  private Activity c;

  public f()
  {
  }

  public f(Context paramContext, Activity paramActivity)
  {
    this.b = paramContext;
    this.c = paramActivity;
  }

  public void a()
  {
    if ((this.a != null) && (this.a.isShowing()))
      this.a.dismiss();
  }

  public void a(String paramString)
  {
    if (this.a == null)
    {
      this.a = new ProgressDialog(this.b);
      this.a.setCanceledOnTouchOutside(false);
      this.a.setProgressStyle(0);
      this.a.setMessage(paramString);
    }
    if (!this.a.isShowing())
      this.a.show();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.f
 * JD-Core Version:    0.6.1
 */