package com.karics.library.zxing.b;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import com.karics.library.zxing.android.CaptureActivity;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public final class c extends Thread
{
  private final CaptureActivity a;
  private final Map<DecodeHintType, Object> b;
  private Handler c;
  private final CountDownLatch d;

  public c(CaptureActivity paramCaptureActivity, Collection<BarcodeFormat> paramCollection, Map<DecodeHintType, ?> paramMap, String paramString, ResultPointCallback paramResultPointCallback)
  {
    this.a = paramCaptureActivity;
    this.d = new CountDownLatch(1);
    this.b = new EnumMap(DecodeHintType.class);
    if (paramMap != null)
      this.b.putAll(paramMap);
    if ((paramCollection == null) || (paramCollection.isEmpty()))
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramCaptureActivity);
      paramCollection = EnumSet.noneOf(BarcodeFormat.class);
      if (localSharedPreferences.getBoolean("preferences_decode_1D_product", true))
        paramCollection.addAll(a.a);
      if (localSharedPreferences.getBoolean("preferences_decode_1D_industrial", true))
        paramCollection.addAll(a.b);
      if (localSharedPreferences.getBoolean("preferences_decode_QR", true))
        paramCollection.addAll(a.c);
      if (localSharedPreferences.getBoolean("preferences_decode_Data_Matrix", true))
        paramCollection.addAll(a.d);
      if (localSharedPreferences.getBoolean("preferences_decode_Aztec", false))
        paramCollection.addAll(a.e);
      if (localSharedPreferences.getBoolean("preferences_decode_PDF417", false))
        paramCollection.addAll(a.f);
    }
    this.b.put(DecodeHintType.POSSIBLE_FORMATS, paramCollection);
    if (paramString != null)
      this.b.put(DecodeHintType.CHARACTER_SET, paramString);
    this.b.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, paramResultPointCallback);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Hints: ");
    localStringBuilder.append(this.b);
    Log.i("DecodeThread", localStringBuilder.toString());
  }

  public Handler a()
  {
    try
    {
      this.d.await();
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    return this.c;
  }

  public void run()
  {
    Looper.prepare();
    this.c = new b(this.a, this.b);
    this.d.countDown();
    Looper.loop();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.b.c
 * JD-Core Version:    0.6.1
 */