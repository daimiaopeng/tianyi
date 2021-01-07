package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class HandlerCompat
{
  public static boolean postDelayed(@NonNull Handler paramHandler, @NonNull Runnable paramRunnable, @Nullable Object paramObject, long paramLong)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramHandler.postDelayed(paramRunnable, paramObject, paramLong);
    Message localMessage = Message.obtain(paramHandler, paramRunnable);
    localMessage.obj = paramObject;
    return paramHandler.sendMessageDelayed(localMessage, paramLong);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.HandlerCompat
 * JD-Core Version:    0.6.1
 */