package com.common.busi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.stub.StubApp;

@QVMProtect
public final class f extends Thread
{
  private a a = null;
  private Context b;
  private String c = "";

  static
  {
    StubApp.interface11(2711);
  }

  public f(Context paramContext, String paramString)
  {
    this.b = paramContext;
    this.c = paramString;
  }

  private native void a(int paramInt, String paramString1, String paramString2, Intent paramIntent);

  private static native void a(Context paramContext, Intent paramIntent, int paramInt);

  private static native void a(Context paramContext, String paramString, Uri paramUri, ContentValues paramContentValues);

  private native void a(String paramString);

  public final native void run();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.common.busi.f
 * JD-Core Version:    0.6.1
 */