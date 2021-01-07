package android.support.v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.IBinder;

public abstract class NotificationCompatSideChannelService extends Service
{
  public abstract void cancel(String paramString1, int paramInt, String paramString2);

  public abstract void cancelAll(String paramString);

  void checkPermission(int paramInt, String paramString)
  {
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (arrayOfString[j].equals(paramString))
        return;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("NotificationSideChannelService: Uid ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" is not authorized for package ");
    localStringBuilder.append(paramString);
    throw new SecurityException(localStringBuilder.toString());
  }

  public abstract void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification);

  public IBinder onBind(Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"))
    {
      if (Build.VERSION.SDK_INT > 19)
        return null;
      return new NotificationSideChannelStub();
    }
    return null;
  }

  private class NotificationSideChannelStub extends INotificationSideChannel.Stub
  {
    NotificationSideChannelStub()
    {
    }

    public void cancel(String paramString1, int paramInt, String paramString2)
    {
      NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), paramString1);
      long l = clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.cancel(paramString1, paramInt, paramString2);
        return;
      }
      finally
      {
        restoreCallingIdentity(l);
      }
    }

    public void cancelAll(String paramString)
    {
      NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), paramString);
      long l = clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.cancelAll(paramString);
        return;
      }
      finally
      {
        restoreCallingIdentity(l);
      }
    }

    public void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification)
    {
      NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), paramString1);
      long l = clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.notify(paramString1, paramInt, paramString2, paramNotification);
        return;
      }
      finally
      {
        restoreCallingIdentity(l);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationCompatSideChannelService
 * JD-Core Version:    0.6.1
 */