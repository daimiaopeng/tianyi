package com.cndatacom.e;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class o
{
  private Context a;
  private ProgressDialog b = null;

  public o(Context paramContext)
  {
    this.a = paramContext;
  }

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
  }

  public static void a(Context paramContext, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener)
  {
    new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setPositiveButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).setNegativeButton("确定", paramOnClickListener).create().show();
  }

  public void a()
  {
    if (this.b == null)
    {
      this.b = new ProgressDialog(this.a);
      this.b.setProgressStyle(0);
      this.b.setCanceledOnTouchOutside(false);
      this.b.setMessage("通讯中，请稍后！");
    }
    if (!this.b.isShowing())
      this.b.show();
  }

  public void b()
  {
    if ((this.b != null) && (this.b.isShowing()))
      this.b.dismiss();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.o
 * JD-Core Version:    0.6.1
 */