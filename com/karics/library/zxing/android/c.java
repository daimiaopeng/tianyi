package com.karics.library.zxing.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public final class c
  implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener
{
  private final Activity a;

  public c(Activity paramActivity)
  {
    this.a = paramActivity;
  }

  private void a()
  {
    this.a.finish();
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    a();
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    a();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.android.c
 * JD-Core Version:    0.6.1
 */