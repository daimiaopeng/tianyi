package android.support.v4.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.media.session.MediaSessionManager.RemoteUserInfo;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ObjectsCompat;

@RequiresApi(28)
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21
{
  MediaSessionManager mObject;

  MediaSessionManagerImplApi28(Context paramContext)
  {
    super(paramContext);
    this.mObject = ((MediaSessionManager)paramContext.getSystemService("media_session"));
  }

  public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl)
  {
    if ((paramRemoteUserInfoImpl instanceof RemoteUserInfoImplApi28))
      return this.mObject.isTrustedForMediaControl(((RemoteUserInfoImplApi28)paramRemoteUserInfoImpl).mObject);
    return false;
  }

  static final class RemoteUserInfoImplApi28
    implements MediaSessionManager.RemoteUserInfoImpl
  {
    final MediaSessionManager.RemoteUserInfo mObject;

    RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo)
    {
      this.mObject = paramRemoteUserInfo;
    }

    RemoteUserInfoImplApi28(String paramString, int paramInt1, int paramInt2)
    {
      this.mObject = new MediaSessionManager.RemoteUserInfo(paramString, paramInt1, paramInt2);
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject)
        return true;
      if (!(paramObject instanceof RemoteUserInfoImplApi28))
        return false;
      RemoteUserInfoImplApi28 localRemoteUserInfoImplApi28 = (RemoteUserInfoImplApi28)paramObject;
      return this.mObject.equals(localRemoteUserInfoImplApi28.mObject);
    }

    public String getPackageName()
    {
      return this.mObject.getPackageName();
    }

    public int getPid()
    {
      return this.mObject.getPid();
    }

    public int getUid()
    {
      return this.mObject.getUid();
    }

    public int hashCode()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mObject;
      return ObjectsCompat.hash(arrayOfObject);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaSessionManagerImplApi28
 * JD-Core Version:    0.6.1
 */