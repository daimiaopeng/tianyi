package com.stub.stub01;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.stub.stub01.adl.DownloadManager;
import com.stub.stub01.adl.b;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class UpdateThread extends Thread
  implements b
{
  private static final boolean ENABLE_NOTIFICATION = true;
  private static final long RECORD_SIZE = 102400L;
  public static final String TAG = "UpdateThread";
  private static Thread thread;
  private Notification.Builder downloadNotificationBuilder;
  private long lasttime;
  private Context mContext;
  private Intent mIntent;
  private NotificationManager mNotificationManager;
  private DownloadManager manager;
  private int notificationID;
  private long record;

  private UpdateThread(Context paramContext, Intent paramIntent)
  {
    this.mContext = paramContext;
    this.mIntent = paramIntent;
  }

  private void branch(com.stub.stub01.adl.d paramd)
  {
    com.stub.stub01.a.d locald = this.manager.getTask$5dff8210();
    if (locald == null);
    while (true)
    {
      return;
      if (paramd == com.stub.stub01.adl.d.c)
      {
        if (!doDone$24b78518(locald))
          restartDownload(locald.a(), locald.b(), locald.d());
      }
      else
      {
        if (paramd == com.stub.stub01.adl.d.a)
          startDownload();
        if (paramd == com.stub.stub01.adl.d.b)
        {
          if (verifyExist$24b78518(locald))
          {
            if ((new File(locald.h()).lastModified() - locald.i() > 10000L) && (locald.e() < locald.d()))
              startDownload();
            else if (locald.e() < locald.d());
          }
          else
            restartDownload(locald.a(), locald.b(), locald.d());
        }
        else if (paramd == com.stub.stub01.adl.d.d)
          if (verifyExist$24b78518(locald))
            startDownload();
          else
            restartDownload(locald.a(), locald.b(), locald.d());
      }
    }
  }

  private boolean doDone$24b78518(com.stub.stub01.a.d paramd)
  {
    File localFile = new File(paramd.h());
    boolean bool1 = verifyExist$24b78518(paramd);
    boolean bool2 = false;
    if (!bool1);
    while (true)
    {
      return bool2;
      boolean bool3 = localFile.length() < paramd.d();
      bool2 = false;
      if (!bool3)
      {
        String str = getPKNameFromAPK(this.mContext, paramd.h());
        if (str == null)
        {
          localFile.delete();
          bool2 = false;
        }
        else
        {
          int i = getVersionFromAPK(this.mContext, paramd.h());
          if (i < 0)
          {
            localFile.delete();
            bool2 = false;
          }
          else
          {
            Integer localInteger = getInstalledAppVersion(this.mContext, str);
            if ((localInteger != null) && ((i <= localInteger.intValue()) || (localInteger.intValue() < 0)))
            {
              bool2 = true;
            }
            else
            {
              Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramd.h()));
              localIntent.setFlags(268435456);
              localIntent.setDataAndType(Uri.fromFile(new File(paramd.h())), "application/vnd.android.package-archive");
              this.mContext.startActivity(localIntent);
              bool2 = true;
            }
          }
        }
      }
    }
  }

  private String getFileName(String paramString)
  {
    String str2;
    if (!paramString.contains(".apk"))
      str2 = "NewApplication.apk";
    while (true)
    {
      return str2;
      String[] arrayOfString = paramString.split(".apk");
      String str1 = arrayOfString[0].substring(1 + arrayOfString[0].lastIndexOf("/"));
      if (TextUtils.isEmpty(str1))
        str2 = "NewApplication.apk";
      else
        str2 = str1 + ".apk";
    }
  }

  public static Integer getInstalledAppVersion(Context paramContext, String paramString)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    for (Object localObject1 = null; ; localObject1 = Integer.valueOf(localPackageInfo.versionCode))
    {
      if (!localIterator.hasNext())
        break label85;
      localPackageInfo = (PackageInfo)localIterator.next();
      if (!localPackageInfo.packageName.equals(paramString))
        break label87;
      if ((0x1 & localPackageInfo.applicationInfo.flags) != 0)
        break;
    }
    label85: label87: for (Object localObject2 = Integer.valueOf(-1); ; localObject2 = localObject1)
    {
      localObject1 = localObject2;
      break;
      return localObject1;
    }
  }

  private int getNotificationID(Context paramContext)
  {
    return 19 * (paramContext.getPackageName().hashCode() * getClass().getSimpleName().hashCode());
  }

  public static String getPKNameFromAPK(Context paramContext, String paramString)
  {
    PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 1);
    String str = null;
    if (localPackageInfo != null)
      str = localPackageInfo.applicationInfo.packageName;
    return str;
  }

  public static int getVersionFromAPK(Context paramContext, String paramString)
  {
    PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 1);
    int i = -1;
    if (localPackageInfo != null)
      i = localPackageInfo.versionCode;
    return i;
  }

  private void restartDownload(String paramString1, String paramString2, long paramLong)
  {
    showToast("正在使用wifi网络,应用下载中");
    this.manager.registerListener(this);
    this.manager.restartDownload(paramString1, paramString2, paramLong);
  }

  private void startDownload()
  {
    showToast("正在使用wifi网络,应用下载中");
    this.manager.registerListener(this);
    this.manager.startDownload();
  }

  public static void startDownload(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (thread == null)
      {
        UpdateThread localUpdateThread = new UpdateThread(paramContext, paramIntent);
        thread = localUpdateThread;
        localUpdateThread.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private boolean verifyExist$24b78518(com.stub.stub01.a.d paramd)
  {
    File localFile = new File(paramd.h());
    if ((localFile.exists()) && (localFile.length() == paramd.d()) && (paramd.e() <= paramd.d()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void done$24b7851c(com.stub.stub01.a.d paramd)
  {
    if (!doDone$24b78518(paramd))
      this.mNotificationManager.cancel(this.notificationID);
    while (true)
    {
      return;
      this.downloadNotificationBuilder.setProgress(100, 100, false);
      this.downloadNotificationBuilder.setSmallIcon(17301634);
      this.downloadNotificationBuilder.setContentText("下载完成 文件目录位于/sdcard/download");
      this.mNotificationManager.notify(this.notificationID, this.downloadNotificationBuilder.getNotification());
    }
  }

  public void downloading$24b7851c(com.stub.stub01.a.d paramd)
  {
    if (paramd.e() - this.record >= 102400L)
    {
      long l = System.currentTimeMillis();
      float f1 = (float)(l - this.lasttime) / 1000.0F;
      this.lasttime = l;
      float f2 = 100.0F / f1;
      this.manager.refresh$24b7851c(paramd);
      this.record = paramd.e();
      this.downloadNotificationBuilder.setProgress(100, (int)(100L * paramd.e() / paramd.d()), false);
      this.downloadNotificationBuilder.setContentText("正在下载(" + new Float(f2).intValue() + "k/s)");
      this.mNotificationManager.notify(this.notificationID, this.downloadNotificationBuilder.getNotification());
    }
  }

  public void fail$24b7851c(com.stub.stub01.a.d paramd)
  {
    new StringBuilder().append(getClass().getSimpleName()).append("->fail(DownloadTask): ");
    this.mNotificationManager.cancel(this.notificationID);
  }

  public void onCreate()
  {
    if (this.mContext == null);
    while (true)
    {
      return;
      this.mNotificationManager = ((NotificationManager)this.mContext.getSystemService("notification"));
      this.downloadNotificationBuilder = new Notification.Builder(this.mContext);
      if ((this.downloadNotificationBuilder != null) && (this.mNotificationManager != null))
        this.notificationID = getNotificationID(this.mContext);
    }
  }

  public int onStartCommand(Intent paramIntent)
  {
    long l = 0L;
    int i = 0;
    if (paramIntent == null);
    Context localContext;
    do
    {
      return i;
      localContext = this.mContext;
      i = 0;
    }
    while (localContext == null);
    this.manager = DownloadManager.getInstance(this.mContext);
    String str2;
    String str1;
    boolean bool1;
    if (paramIntent.hasExtra("download"))
    {
      Bundle localBundle = paramIntent.getBundleExtra("download");
      str2 = localBundle.getString("url", null);
      str1 = localBundle.getString("md5", null);
      localBundle.getString("name", null);
      localBundle.getString("path", null);
      l = localBundle.getLong("contentLength", l);
      bool1 = localBundle.getBoolean("init_only", false);
    }
    while (true)
    {
      boolean bool2 = TextUtils.isEmpty(str2);
      i = 0;
      if (bool2)
        break;
      com.stub.stub01.adl.d locald = this.manager.init(str2, str1, l);
      if (bool1)
      {
        i = 1;
        break;
      }
      branch(locald);
      i = 1;
      break;
      bool1 = false;
      str1 = null;
      str2 = null;
    }
  }

  public void pause$24b7851c(com.stub.stub01.a.d paramd)
  {
    this.downloadNotificationBuilder.setSmallIcon(17301634);
    this.downloadNotificationBuilder.setContentText("暂停下载");
    this.mNotificationManager.notify(this.notificationID, this.downloadNotificationBuilder.getNotification());
  }

  public void run()
  {
    super.run();
    onCreate();
    onStartCommand(this.mIntent);
  }

  public void showToast(String paramString)
  {
    new UpdateThread.ShowToastThread(this, paramString).start();
  }

  public void start$24b7851c(com.stub.stub01.a.d paramd)
  {
    this.record = paramd.e();
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramd.h()));
    localIntent.setFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(new File(paramd.h())), "application/vnd.android.package-archive");
    PendingIntent localPendingIntent = PendingIntent.getActivity(this.mContext, 0, localIntent, 134217728);
    this.downloadNotificationBuilder.setContentIntent(localPendingIntent);
    this.downloadNotificationBuilder.setContentText("正在下载(0k/s)");
    this.downloadNotificationBuilder.setContentTitle(getFileName(paramd.a()));
    this.downloadNotificationBuilder.setSmallIcon(17301633);
    this.downloadNotificationBuilder.setProgress(100, (int)(100L * paramd.e() / paramd.d()), false);
    this.mNotificationManager.notify(this.notificationID, this.downloadNotificationBuilder.getNotification());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.stub01.UpdateThread
 * JD-Core Version:    0.6.1
 */