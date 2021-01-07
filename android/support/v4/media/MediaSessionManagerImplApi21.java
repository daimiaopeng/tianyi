package android.support.v4.media;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class MediaSessionManagerImplApi21 extends MediaSessionManagerImplBase
{
  MediaSessionManagerImplApi21(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }

  private boolean hasMediaControlPermission(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl)
  {
    boolean bool;
    if (getContext().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", paramRemoteUserInfoImpl.getPid(), paramRemoteUserInfoImpl.getUid()) == 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl)
  {
    boolean bool;
    if ((!hasMediaControlPermission(paramRemoteUserInfoImpl)) && (!super.isTrustedForMediaControl(paramRemoteUserInfoImpl)))
      bool = false;
    else
      bool = true;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaSessionManagerImplApi21
 * JD-Core Version:    0.6.1
 */