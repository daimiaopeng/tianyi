package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes.Builder;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.MediaSession.Callback;
import android.media.session.MediaSession.QueueItem;
import android.media.session.MediaSession.Token;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
class MediaSessionCompatApi21
{
  static final String TAG = "MediaSessionCompatApi21";

  public static Object createCallback(Callback paramCallback)
  {
    return new CallbackProxy(paramCallback);
  }

  public static Object createSession(Context paramContext, String paramString)
  {
    return new MediaSession(paramContext, paramString);
  }

  public static Parcelable getSessionToken(Object paramObject)
  {
    return ((MediaSession)paramObject).getSessionToken();
  }

  // ERROR //
  public static boolean hasCallback(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 44	java/lang/Object:getClass	()Ljava/lang/Class;
    //   4: ldc 46
    //   6: invokevirtual 52	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   9: astore_2
    //   10: aload_2
    //   11: ifnull +35 -> 46
    //   14: aload_2
    //   15: iconst_1
    //   16: invokevirtual 58	java/lang/reflect/Field:setAccessible	(Z)V
    //   19: aload_2
    //   20: aload_0
    //   21: invokevirtual 62	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   24: astore_3
    //   25: iconst_0
    //   26: istore 4
    //   28: aload_3
    //   29: ifnull +6 -> 35
    //   32: iconst_1
    //   33: istore 4
    //   35: iload 4
    //   37: ireturn
    //   38: ldc 11
    //   40: ldc 64
    //   42: invokestatic 70	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   45: pop
    //   46: iconst_0
    //   47: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	25	38	java/lang/NoSuchFieldException
    //   0	25	38	java/lang/IllegalAccessException
  }

  public static boolean isActive(Object paramObject)
  {
    return ((MediaSession)paramObject).isActive();
  }

  public static void release(Object paramObject)
  {
    ((MediaSession)paramObject).release();
  }

  public static void sendSessionEvent(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaSession)paramObject).sendSessionEvent(paramString, paramBundle);
  }

  public static void setActive(Object paramObject, boolean paramBoolean)
  {
    ((MediaSession)paramObject).setActive(paramBoolean);
  }

  public static void setCallback(Object paramObject1, Object paramObject2, Handler paramHandler)
  {
    ((MediaSession)paramObject1).setCallback((MediaSession.Callback)paramObject2, paramHandler);
  }

  public static void setExtras(Object paramObject, Bundle paramBundle)
  {
    ((MediaSession)paramObject).setExtras(paramBundle);
  }

  public static void setFlags(Object paramObject, int paramInt)
  {
    ((MediaSession)paramObject).setFlags(paramInt);
  }

  public static void setMediaButtonReceiver(Object paramObject, PendingIntent paramPendingIntent)
  {
    ((MediaSession)paramObject).setMediaButtonReceiver(paramPendingIntent);
  }

  public static void setMetadata(Object paramObject1, Object paramObject2)
  {
    ((MediaSession)paramObject1).setMetadata((MediaMetadata)paramObject2);
  }

  public static void setPlaybackState(Object paramObject1, Object paramObject2)
  {
    ((MediaSession)paramObject1).setPlaybackState((PlaybackState)paramObject2);
  }

  public static void setPlaybackToLocal(Object paramObject, int paramInt)
  {
    AudioAttributes.Builder localBuilder = new AudioAttributes.Builder();
    localBuilder.setLegacyStreamType(paramInt);
    ((MediaSession)paramObject).setPlaybackToLocal(localBuilder.build());
  }

  public static void setPlaybackToRemote(Object paramObject1, Object paramObject2)
  {
    ((MediaSession)paramObject1).setPlaybackToRemote((VolumeProvider)paramObject2);
  }

  public static void setQueue(Object paramObject, List<Object> paramList)
  {
    if (paramList == null)
    {
      ((MediaSession)paramObject).setQueue(null);
      return;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      localArrayList.add((MediaSession.QueueItem)localIterator.next());
    ((MediaSession)paramObject).setQueue(localArrayList);
  }

  public static void setQueueTitle(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaSession)paramObject).setQueueTitle(paramCharSequence);
  }

  public static void setSessionActivity(Object paramObject, PendingIntent paramPendingIntent)
  {
    ((MediaSession)paramObject).setSessionActivity(paramPendingIntent);
  }

  public static Object verifySession(Object paramObject)
  {
    if ((paramObject instanceof MediaSession))
      return paramObject;
    throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
  }

  public static Object verifyToken(Object paramObject)
  {
    if ((paramObject instanceof MediaSession.Token))
      return paramObject;
    throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
  }

  static abstract interface Callback
  {
    public abstract void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);

    public abstract void onCustomAction(String paramString, Bundle paramBundle);

    public abstract void onFastForward();

    public abstract boolean onMediaButtonEvent(Intent paramIntent);

    public abstract void onPause();

    public abstract void onPlay();

    public abstract void onPlayFromMediaId(String paramString, Bundle paramBundle);

    public abstract void onPlayFromSearch(String paramString, Bundle paramBundle);

    public abstract void onRewind();

    public abstract void onSeekTo(long paramLong);

    public abstract void onSetRating(Object paramObject);

    public abstract void onSetRating(Object paramObject, Bundle paramBundle);

    public abstract void onSkipToNext();

    public abstract void onSkipToPrevious();

    public abstract void onSkipToQueueItem(long paramLong);

    public abstract void onStop();
  }

  static class CallbackProxy<T extends MediaSessionCompatApi21.Callback> extends MediaSession.Callback
  {
    protected final T mCallback;

    public CallbackProxy(T paramT)
    {
      this.mCallback = paramT;
    }

    public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      this.mCallback.onCommand(paramString, paramBundle, paramResultReceiver);
    }

    public void onCustomAction(String paramString, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      this.mCallback.onCustomAction(paramString, paramBundle);
    }

    public void onFastForward()
    {
      this.mCallback.onFastForward();
    }

    public boolean onMediaButtonEvent(Intent paramIntent)
    {
      boolean bool;
      if ((!this.mCallback.onMediaButtonEvent(paramIntent)) && (!super.onMediaButtonEvent(paramIntent)))
        bool = false;
      else
        bool = true;
      return bool;
    }

    public void onPause()
    {
      this.mCallback.onPause();
    }

    public void onPlay()
    {
      this.mCallback.onPlay();
    }

    public void onPlayFromMediaId(String paramString, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      this.mCallback.onPlayFromMediaId(paramString, paramBundle);
    }

    public void onPlayFromSearch(String paramString, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      this.mCallback.onPlayFromSearch(paramString, paramBundle);
    }

    public void onRewind()
    {
      this.mCallback.onRewind();
    }

    public void onSeekTo(long paramLong)
    {
      this.mCallback.onSeekTo(paramLong);
    }

    public void onSetRating(Rating paramRating)
    {
      this.mCallback.onSetRating(paramRating);
    }

    public void onSkipToNext()
    {
      this.mCallback.onSkipToNext();
    }

    public void onSkipToPrevious()
    {
      this.mCallback.onSkipToPrevious();
    }

    public void onSkipToQueueItem(long paramLong)
    {
      this.mCallback.onSkipToQueueItem(paramLong);
    }

    public void onStop()
    {
      this.mCallback.onStop();
    }
  }

  static class QueueItem
  {
    public static Object createItem(Object paramObject, long paramLong)
    {
      return new MediaSession.QueueItem((MediaDescription)paramObject, paramLong);
    }

    public static Object getDescription(Object paramObject)
    {
      return ((MediaSession.QueueItem)paramObject).getDescription();
    }

    public static long getQueueId(Object paramObject)
    {
      return ((MediaSession.QueueItem)paramObject).getQueueId();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi21
 * JD-Core Version:    0.6.1
 */