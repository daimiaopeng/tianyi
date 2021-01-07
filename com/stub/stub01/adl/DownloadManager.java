package com.stub.stub01.adl;

import android.content.Context;
import android.os.Environment;
import android.webkit.URLUtil;
import java.io.File;
import java.security.MessageDigest;

public class DownloadManager
{
  public static final String APP_MIMETYPE = "application/vnd.android.package-archive";
  public static final String STREAM_MIMETYPE = "application/octet-stream";
  public static final String TAG = "appdownload";
  private static DownloadManager instance;
  private a dbHelper;
  private b listener;
  private Context mContext;
  private c operator;
  private com.stub.stub01.a.d task$9706d1;

  private DownloadManager(Context paramContext)
  {
    this.mContext = paramContext;
    this.dbHelper = new a(this.mContext, "downloads.db");
  }

  public static DownloadManager getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new DownloadManager(paramContext);
    return instance;
  }

  public static String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer("");
      for (int i = 0; i < arrayOfByte.length; i++)
      {
        int j = arrayOfByte[i];
        if (j < 0)
          j += 256;
        if (j < 16)
          localStringBuffer.append("0");
        localStringBuffer.append(Integer.toHexString(j));
      }
      String str2 = localStringBuffer.toString();
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
        String str1 = null;
    }
  }

  public void clean$24b7851c(com.stub.stub01.a.d paramd)
  {
    if (this.dbHelper != null)
      this.dbHelper.c(paramd);
  }

  public com.stub.stub01.a.d getTask$5dff8210()
  {
    return this.task$9706d1;
  }

  public d init(String paramString1, String paramString2, long paramLong)
  {
    com.stub.stub01.b.a(new String[] { "appdownload", "DownloadManager init(String url, String md5, String path, String name)" });
    d locald;
    if (!URLUtil.isValidUrl(paramString1))
      locald = d.e;
    while (true)
    {
      return locald;
      String str1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
      File localFile = new File(str1);
      if (!localFile.exists())
        localFile.mkdir();
      String str2 = md5(paramString1) + ".apk";
      com.stub.stub01.a.d locald1 = this.dbHelper.a(d.b);
      if (locald1 != null)
      {
        if (this.operator != null)
          this.operator.a(true);
        locald1.a(d.d);
        this.dbHelper.b(locald1);
      }
      com.stub.stub01.a.d locald2 = this.dbHelper.a(paramString1);
      this.task$9706d1 = locald2;
      if (locald2 == null)
      {
        com.stub.stub01.b.a(new String[] { "appdownload", "dbHelper.query(url) == null" });
        this.task$9706d1 = new com.stub.stub01.a.d();
        this.task$9706d1.c(paramString1);
        this.task$9706d1.d(paramString2);
        this.task$9706d1.b(str2);
        this.task$9706d1.a(str1);
        this.task$9706d1.c(System.currentTimeMillis());
        this.task$9706d1.a(d.a);
        this.task$9706d1.a(paramLong);
        this.dbHelper.a(this.task$9706d1);
        locald = d.a;
      }
      else
      {
        com.stub.stub01.b.a(new String[] { "appdownload", "dbHelper.query(url) != null" });
        this.task$9706d1.c(paramString1);
        this.task$9706d1.d(paramString2);
        this.task$9706d1.a(str1);
        this.task$9706d1.b(str2);
        locald = this.task$9706d1.f();
      }
    }
  }

  void onDone$24b7851c(com.stub.stub01.a.d paramd)
  {
    paramd.a(d.c);
    refresh$24b7851c(paramd);
    if (this.listener != null)
      this.listener.done$24b7851c(paramd);
  }

  void onDownload$24b7851c(com.stub.stub01.a.d paramd)
  {
    refresh$24b7851c(paramd);
    if (this.listener != null)
      this.listener.downloading$24b7851c(paramd);
  }

  void onFail$24b7851c(com.stub.stub01.a.d paramd)
  {
    paramd.a(d.e);
    clean$24b7851c(paramd);
    if (this.listener != null)
      this.listener.fail$24b7851c(paramd);
  }

  void onPause$24b7851c(com.stub.stub01.a.d paramd)
  {
    paramd.a(d.d);
    refresh$24b7851c(paramd);
    if (this.listener != null)
      this.listener.pause$24b7851c(paramd);
  }

  void onStart$24b7851c(com.stub.stub01.a.d paramd)
  {
    paramd.a(d.b);
    refresh$24b7851c(paramd);
    if (this.listener != null)
      this.listener.start$24b7851c(paramd);
  }

  public void pauseDownload()
  {
    if (this.operator != null)
      this.operator.a(true);
  }

  public void refresh$24b7851c(com.stub.stub01.a.d paramd)
  {
    if (this.dbHelper != null)
      this.dbHelper.b(paramd);
  }

  public void registerListener(b paramb)
  {
    this.listener = paramb;
  }

  public boolean restartDownload(String paramString1, String paramString2, long paramLong)
  {
    boolean bool = true;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "appdownload";
    arrayOfString[bool] = (getClass().getSimpleName() + "->restartDownload(): ");
    com.stub.stub01.b.a(arrayOfString);
    if (this.task$9706d1 != null)
    {
      com.stub.stub01.a.d locald = this.dbHelper.a(this.task$9706d1.a());
      this.task$9706d1 = locald;
      if (locald != null)
        this.dbHelper.c(this.task$9706d1);
      String str1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
      File localFile = new File(str1);
      if (!localFile.exists())
        localFile.mkdir();
      String str2 = md5(paramString1) + ".apk";
      this.task$9706d1.c(paramString1);
      this.task$9706d1.d(paramString2);
      this.task$9706d1.b(str2);
      this.task$9706d1.a(str1);
      this.task$9706d1.b(0L);
      this.task$9706d1.a(0L);
      this.task$9706d1.a(d.a);
      this.task$9706d1.c(System.currentTimeMillis());
      this.dbHelper.a(this.task$9706d1);
      this.operator = new c(this, this.task$9706d1);
      this.operator.execute(new String[0]);
      this.task$9706d1.a(d.b);
    }
    while (true)
    {
      return bool;
      new StringBuilder("operator is null ").append(this.operator).append(" or task is null!").append(this.task$9706d1);
      bool = false;
    }
  }

  public boolean startDownload()
  {
    boolean bool = true;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "appdownload";
    arrayOfString[bool] = (getClass().getSimpleName() + "->startDownload(): ");
    com.stub.stub01.b.a(arrayOfString);
    if (this.task$9706d1 != null)
    {
      this.operator = new c(this, this.task$9706d1);
      this.operator.execute(new String[0]);
      this.task$9706d1.a(d.b);
    }
    while (true)
    {
      return bool;
      new StringBuilder("operator is null ").append(this.operator).append(" or task is null!").append(this.task$9706d1);
      bool = false;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.stub01.adl.DownloadManager
 * JD-Core Version:    0.6.1
 */