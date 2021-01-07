package android.support.v4.app;

import android.app.AlarmManager;
import android.app.AlarmManager.AlarmClockInfo;
import android.app.PendingIntent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class AlarmManagerCompat
{
  public static void setAlarmClock(@NonNull AlarmManager paramAlarmManager, long paramLong, @NonNull PendingIntent paramPendingIntent1, @NonNull PendingIntent paramPendingIntent2)
  {
    if (Build.VERSION.SDK_INT >= 21)
      paramAlarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(paramLong, paramPendingIntent1), paramPendingIntent2);
    else
      setExact(paramAlarmManager, 0, paramLong, paramPendingIntent2);
  }

  public static void setAndAllowWhileIdle(@NonNull AlarmManager paramAlarmManager, int paramInt, long paramLong, @NonNull PendingIntent paramPendingIntent)
  {
    if (Build.VERSION.SDK_INT >= 23)
      paramAlarmManager.setAndAllowWhileIdle(paramInt, paramLong, paramPendingIntent);
    else
      paramAlarmManager.set(paramInt, paramLong, paramPendingIntent);
  }

  public static void setExact(@NonNull AlarmManager paramAlarmManager, int paramInt, long paramLong, @NonNull PendingIntent paramPendingIntent)
  {
    if (Build.VERSION.SDK_INT >= 19)
      paramAlarmManager.setExact(paramInt, paramLong, paramPendingIntent);
    else
      paramAlarmManager.set(paramInt, paramLong, paramPendingIntent);
  }

  public static void setExactAndAllowWhileIdle(@NonNull AlarmManager paramAlarmManager, int paramInt, long paramLong, @NonNull PendingIntent paramPendingIntent)
  {
    if (Build.VERSION.SDK_INT >= 23)
      paramAlarmManager.setExactAndAllowWhileIdle(paramInt, paramLong, paramPendingIntent);
    else
      setExact(paramAlarmManager, paramInt, paramLong, paramPendingIntent);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.AlarmManagerCompat
 * JD-Core Version:    0.6.1
 */