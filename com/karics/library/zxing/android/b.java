package com.karics.library.zxing.android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.karics.library.zxing.a.d;
import com.karics.library.zxing.b.c;
import com.karics.library.zxing.view.a;
import java.util.Collection;
import java.util.Map;

public final class b extends Handler
{
  private static final String a = "b";
  private final CaptureActivity b;
  private final c c;
  private a d;
  private final d e;

  public b(CaptureActivity paramCaptureActivity, Collection<BarcodeFormat> paramCollection, Map<DecodeHintType, ?> paramMap, String paramString, d paramd)
  {
    this.b = paramCaptureActivity;
    c localc = new c(paramCaptureActivity, paramCollection, paramMap, paramString, new a(paramCaptureActivity.a()));
    this.c = localc;
    this.c.start();
    this.d = a.b;
    this.e = paramd;
    paramd.c();
    b();
  }

  public void a()
  {
    this.d = a.c;
    this.e.d();
    Message.obtain(this.c.a(), 2131034211).sendToTarget();
    try
    {
      this.c.join(500L);
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    removeMessages(2131034158);
    removeMessages(2131034157);
  }

  public void b()
  {
    if (this.d == a.b)
    {
      this.d = a.a;
      this.e.a(this.c.a(), 2131034156);
    }
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      break;
    case 2131034215:
      this.b.setResult(-1, (Intent)paramMessage.obj);
      this.b.finish();
      break;
    case 2131034214:
      b();
      break;
    case 2131034187:
      String str1 = (String)paramMessage.obj;
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addFlags(524288);
      localIntent.setData(Uri.parse(str1));
      ResolveInfo localResolveInfo = this.b.getPackageManager().resolveActivity(localIntent, 65536);
      String str2 = null;
      if (localResolveInfo != null)
      {
        ActivityInfo localActivityInfo = localResolveInfo.activityInfo;
        str2 = null;
        if (localActivityInfo != null)
        {
          str2 = localResolveInfo.activityInfo.packageName;
          String str4 = a;
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("Using browser in package ");
          localStringBuilder2.append(str2);
          Log.d(str4, localStringBuilder2.toString());
        }
      }
      if (("com.android.browser".equals(str2)) || ("com.android.chrome".equals(str2)))
      {
        localIntent.setPackage(str2);
        localIntent.addFlags(268435456);
        localIntent.putExtra("com.android.browser.application_id", str2);
      }
      String str3;
      StringBuilder localStringBuilder1;
      try
      {
        this.b.startActivity(localIntent);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        str3 = a;
        localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Can't find anything to handle VIEW of URI ");
        localStringBuilder1.append(str1);
        Log.w(str3, localStringBuilder1.toString());
        tmpTernaryOp = localActivityNotFoundException;
      }
    case 2131034158:
      this.d = a.b;
      Bundle localBundle = paramMessage.getData();
      float f = 1.0F;
      Bitmap localBitmap = null;
      if (localBundle != null)
      {
        byte[] arrayOfByte = localBundle.getByteArray("barcode_bitmap");
        localBitmap = null;
        if (arrayOfByte != null)
          localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, null).copy(Bitmap.Config.ARGB_8888, true);
        f = localBundle.getFloat("barcode_scaled_factor");
      }
      this.b.a((Result)paramMessage.obj, localBitmap, f);
      break;
    case 2131034157:
      this.d = a.a;
      this.e.a(this.c.a(), 2131034156);
    }
  }

  private static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.android.b
 * JD-Core Version:    0.6.1
 */