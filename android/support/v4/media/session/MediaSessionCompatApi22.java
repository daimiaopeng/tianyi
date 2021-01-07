package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

@RequiresApi(22)
class MediaSessionCompatApi22
{
  public static void setRatingType(Object paramObject, int paramInt)
  {
    ((MediaSession)paramObject).setRatingType(paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi22
 * JD-Core Version:    0.6.1
 */