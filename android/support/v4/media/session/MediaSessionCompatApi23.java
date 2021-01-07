package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaSessionCompatApi23
{
  public static Object createCallback(Callback paramCallback)
  {
    return new CallbackProxy(paramCallback);
  }

  public static abstract interface Callback extends MediaSessionCompatApi21.Callback
  {
    public abstract void onPlayFromUri(Uri paramUri, Bundle paramBundle);
  }

  static class CallbackProxy<T extends MediaSessionCompatApi23.Callback> extends MediaSessionCompatApi21.CallbackProxy<T>
  {
    public CallbackProxy(T paramT)
    {
      super();
    }

    public void onPlayFromUri(Uri paramUri, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      ((MediaSessionCompatApi23.Callback)this.mCallback).onPlayFromUri(paramUri, paramBundle);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi23
 * JD-Core Version:    0.6.1
 */