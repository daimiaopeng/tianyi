package android.support.v4.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.v4.util.ObjectsCompat;
import android.text.TextUtils;

class MediaSessionManagerImplBase
  implements MediaSessionManager.MediaSessionManagerImpl
{
  private static final boolean DEBUG = false;
  private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
  private static final String PERMISSION_MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
  private static final String PERMISSION_STATUS_BAR_SERVICE = "android.permission.STATUS_BAR_SERVICE";
  private static final String TAG = "MediaSessionManager";
  ContentResolver mContentResolver;
  Context mContext;

  MediaSessionManagerImplBase(Context paramContext)
  {
    this.mContext = paramContext;
    this.mContentResolver = this.mContext.getContentResolver();
  }

  private boolean isPermissionGranted(MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl, String paramString)
  {
    if (paramRemoteUserInfoImpl.getPid() < 0)
    {
      int j = this.mContext.getPackageManager().checkPermission(paramString, paramRemoteUserInfoImpl.getPackageName());
      boolean bool2 = false;
      if (j == 0)
        bool2 = true;
      return bool2;
    }
    int i = this.mContext.checkPermission(paramString, paramRemoteUserInfoImpl.getPid(), paramRemoteUserInfoImpl.getUid());
    boolean bool1 = false;
    if (i == 0)
      bool1 = true;
    return bool1;
  }

  public Context getContext()
  {
    return this.mContext;
  }

  boolean isEnabledNotificationListener(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl)
  {
    String str = Settings.Secure.getString(this.mContentResolver, "enabled_notification_listeners");
    if (str != null)
    {
      String[] arrayOfString = str.split(":");
      for (int i = 0; i < arrayOfString.length; i++)
      {
        ComponentName localComponentName = ComponentName.unflattenFromString(arrayOfString[i]);
        if ((localComponentName != null) && (localComponentName.getPackageName().equals(paramRemoteUserInfoImpl.getPackageName())))
          return true;
      }
    }
    return false;
  }

  // ERROR //
  public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 39	android/support/v4/media/MediaSessionManagerImplBase:mContext	Landroid/content/Context;
    //   4: invokevirtual 59	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: aload_1
    //   8: invokeinterface 63 1 0
    //   13: iconst_0
    //   14: invokevirtual 112	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   17: astore 7
    //   19: aload 7
    //   21: getfield 118	android/content/pm/ApplicationInfo:uid	I
    //   24: aload_1
    //   25: invokeinterface 72 1 0
    //   30: if_icmpeq +71 -> 101
    //   33: getstatic 33	android/support/v4/media/MediaSessionManagerImplBase:DEBUG	Z
    //   36: ifeq +63 -> 99
    //   39: new 120	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   46: astore 10
    //   48: aload 10
    //   50: ldc 123
    //   52: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload 10
    //   58: aload_1
    //   59: invokeinterface 63 1 0
    //   64: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload 10
    //   70: ldc 129
    //   72: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: aload 10
    //   78: aload_1
    //   79: invokeinterface 72 1 0
    //   84: invokevirtual 132	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: ldc 22
    //   90: aload 10
    //   92: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokestatic 140	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   98: pop
    //   99: iconst_0
    //   100: ireturn
    //   101: aload_0
    //   102: aload_1
    //   103: ldc 19
    //   105: invokespecial 142	android/support/v4/media/MediaSessionManagerImplBase:isPermissionGranted	(Landroid/support/v4/media/MediaSessionManager$RemoteUserInfoImpl;Ljava/lang/String;)Z
    //   108: ifne +40 -> 148
    //   111: aload_0
    //   112: aload_1
    //   113: ldc 16
    //   115: invokespecial 142	android/support/v4/media/MediaSessionManagerImplBase:isPermissionGranted	(Landroid/support/v4/media/MediaSessionManager$RemoteUserInfoImpl;Ljava/lang/String;)Z
    //   118: ifne +30 -> 148
    //   121: aload_1
    //   122: invokeinterface 72 1 0
    //   127: sipush 1000
    //   130: if_icmpeq +18 -> 148
    //   133: aload_0
    //   134: aload_1
    //   135: invokevirtual 144	android/support/v4/media/MediaSessionManagerImplBase:isEnabledNotificationListener	(Landroid/support/v4/media/MediaSessionManager$RemoteUserInfoImpl;)Z
    //   138: istore 9
    //   140: iconst_0
    //   141: istore 8
    //   143: iload 9
    //   145: ifeq +6 -> 151
    //   148: iconst_1
    //   149: istore 8
    //   151: iload 8
    //   153: ireturn
    //   154: getstatic 33	android/support/v4/media/MediaSessionManagerImplBase:DEBUG	Z
    //   157: ifeq +46 -> 203
    //   160: new 120	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   167: astore_2
    //   168: aload_2
    //   169: ldc 146
    //   171: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: pop
    //   175: aload_2
    //   176: aload_1
    //   177: invokeinterface 63 1 0
    //   182: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: pop
    //   186: aload_2
    //   187: ldc 148
    //   189: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: pop
    //   193: ldc 22
    //   195: aload_2
    //   196: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 140	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   202: pop
    //   203: iconst_0
    //   204: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	19	154	android/content/pm/PackageManager$NameNotFoundException
  }

  static class RemoteUserInfoImplBase
    implements MediaSessionManager.RemoteUserInfoImpl
  {
    private String mPackageName;
    private int mPid;
    private int mUid;

    RemoteUserInfoImplBase(String paramString, int paramInt1, int paramInt2)
    {
      this.mPackageName = paramString;
      this.mPid = paramInt1;
      this.mUid = paramInt2;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject)
        return bool;
      if (!(paramObject instanceof RemoteUserInfoImplBase))
        return false;
      RemoteUserInfoImplBase localRemoteUserInfoImplBase = (RemoteUserInfoImplBase)paramObject;
      if ((!TextUtils.equals(this.mPackageName, localRemoteUserInfoImplBase.mPackageName)) || (this.mPid != localRemoteUserInfoImplBase.mPid) || (this.mUid != localRemoteUserInfoImplBase.mUid))
        bool = false;
      return bool;
    }

    public String getPackageName()
    {
      return this.mPackageName;
    }

    public int getPid()
    {
      return this.mPid;
    }

    public int getUid()
    {
      return this.mUid;
    }

    public int hashCode()
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.mPackageName;
      arrayOfObject[1] = Integer.valueOf(this.mPid);
      arrayOfObject[2] = Integer.valueOf(this.mUid);
      return ObjectsCompat.hash(arrayOfObject);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaSessionManagerImplBase
 * JD-Core Version:    0.6.1
 */