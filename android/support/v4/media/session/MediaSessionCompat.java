package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataEditor;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.media.RemoteControlClient.OnMetadataUpdateListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import android.support.v4.media.MediaSessionManager.RemoteUserInfo;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.VolumeProviderCompat.Callback;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat
{

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
  public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
  public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
  public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
  public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
  public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
  public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
  private static final String DATA_CALLING_PACKAGE = "data_calling_pkg";
  private static final String DATA_CALLING_PID = "data_calling_pid";
  private static final String DATA_CALLING_UID = "data_calling_uid";
  private static final String DATA_EXTRAS = "data_extras";
  public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
  public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
  public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String KEY_SESSION_TOKEN2_BUNDLE = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
  private static final int MAX_BITMAP_SIZE_IN_DP = 320;
  public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
  public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
  public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
  static final String TAG = "MediaSessionCompat";
  static int sMaxBitmapSize;
  private final ArrayList<OnActiveChangeListener> mActiveListeners = new ArrayList();
  private final MediaControllerCompat mController;
  private final MediaSessionImpl mImpl;

  private MediaSessionCompat(Context paramContext, MediaSessionImpl paramMediaSessionImpl)
  {
    this.mImpl = paramMediaSessionImpl;
    if ((Build.VERSION.SDK_INT >= 21) && (!MediaSessionCompatApi21.hasCallback(paramMediaSessionImpl.getMediaSession())))
      setCallback(new Callback()
      {
      });
    this.mController = new MediaControllerCompat(paramContext, this);
  }

  public MediaSessionCompat(Context paramContext, String paramString)
  {
    this(paramContext, paramString, null, null);
  }

  public MediaSessionCompat(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
  {
    this(paramContext, paramString, paramComponentName, paramPendingIntent, null);
  }

  private MediaSessionCompat(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent, Bundle paramBundle)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("context must not be null");
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("tag must not be null or empty");
    if (paramComponentName == null)
    {
      paramComponentName = MediaButtonReceiver.getMediaButtonReceiverComponent(paramContext);
      if (paramComponentName == null)
        Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
    }
    if ((paramComponentName != null) && (paramPendingIntent == null))
    {
      Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
      localIntent.setComponent(paramComponentName);
      paramPendingIntent = PendingIntent.getBroadcast(paramContext, 0, localIntent, 0);
    }
    if (Build.VERSION.SDK_INT >= 28)
    {
      this.mImpl = new MediaSessionImplApi28(paramContext, paramString, paramBundle);
      setCallback(new Callback()
      {
      });
      this.mImpl.setMediaButtonReceiver(paramPendingIntent);
    }
    else if (Build.VERSION.SDK_INT >= 21)
    {
      this.mImpl = new MediaSessionImplApi21(paramContext, paramString, paramBundle);
      setCallback(new Callback()
      {
      });
      this.mImpl.setMediaButtonReceiver(paramPendingIntent);
    }
    else if (Build.VERSION.SDK_INT >= 19)
    {
      this.mImpl = new MediaSessionImplApi19(paramContext, paramString, paramComponentName, paramPendingIntent);
    }
    else if (Build.VERSION.SDK_INT >= 18)
    {
      this.mImpl = new MediaSessionImplApi18(paramContext, paramString, paramComponentName, paramPendingIntent);
    }
    else
    {
      this.mImpl = new MediaSessionImplBase(paramContext, paramString, paramComponentName, paramPendingIntent);
    }
    this.mController = new MediaControllerCompat(paramContext, this);
    if (sMaxBitmapSize == 0)
      sMaxBitmapSize = (int)(0.5F + TypedValue.applyDimension(1, 320.0F, paramContext.getResources().getDisplayMetrics()));
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public MediaSessionCompat(Context paramContext, String paramString, Bundle paramBundle)
  {
    this(paramContext, paramString, null, null, paramBundle);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static void ensureClassLoader(@Nullable Bundle paramBundle)
  {
    if (paramBundle != null)
      paramBundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
  }

  public static MediaSessionCompat fromMediaSession(Context paramContext, Object paramObject)
  {
    if ((paramContext != null) && (paramObject != null) && (Build.VERSION.SDK_INT >= 21))
      return new MediaSessionCompat(paramContext, new MediaSessionImplApi21(paramObject));
    return null;
  }

  static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat paramPlaybackStateCompat, MediaMetadataCompat paramMediaMetadataCompat)
  {
    if (paramPlaybackStateCompat != null)
    {
      long l1 = paramPlaybackStateCompat.getPosition();
      long l2 = -1L;
      if (l1 != l2)
      {
        if ((paramPlaybackStateCompat.getState() == 3) || (paramPlaybackStateCompat.getState() == 4) || (paramPlaybackStateCompat.getState() == 5))
        {
          long l3 = paramPlaybackStateCompat.getLastPositionUpdateTime();
          if (l3 > 0L)
          {
            long l4 = SystemClock.elapsedRealtime();
            long l5 = ()(paramPlaybackStateCompat.getPlaybackSpeed() * (float)(l4 - l3)) + paramPlaybackStateCompat.getPosition();
            if ((paramMediaMetadataCompat != null) && (paramMediaMetadataCompat.containsKey("android.media.metadata.DURATION")))
              l2 = paramMediaMetadataCompat.getLong("android.media.metadata.DURATION");
            long l6;
            if ((l2 >= 0L) && (l5 > l2))
              l6 = l2;
            else if (l5 < 0L)
              l6 = 0L;
            else
              l6 = l5;
            return new PlaybackStateCompat.Builder(paramPlaybackStateCompat).setState(paramPlaybackStateCompat.getState(), l6, paramPlaybackStateCompat.getPlaybackSpeed(), l4).build();
          }
        }
        return paramPlaybackStateCompat;
      }
    }
    return paramPlaybackStateCompat;
  }

  public void addOnActiveChangeListener(OnActiveChangeListener paramOnActiveChangeListener)
  {
    if (paramOnActiveChangeListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.mActiveListeners.add(paramOnActiveChangeListener);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public String getCallingPackage()
  {
    return this.mImpl.getCallingPackage();
  }

  public MediaControllerCompat getController()
  {
    return this.mController;
  }

  @NonNull
  public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo()
  {
    return this.mImpl.getCurrentControllerInfo();
  }

  public Object getMediaSession()
  {
    return this.mImpl.getMediaSession();
  }

  public Object getRemoteControlClient()
  {
    return this.mImpl.getRemoteControlClient();
  }

  public Token getSessionToken()
  {
    return this.mImpl.getSessionToken();
  }

  public boolean isActive()
  {
    return this.mImpl.isActive();
  }

  public void release()
  {
    this.mImpl.release();
  }

  public void removeOnActiveChangeListener(OnActiveChangeListener paramOnActiveChangeListener)
  {
    if (paramOnActiveChangeListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.mActiveListeners.remove(paramOnActiveChangeListener);
  }

  public void sendSessionEvent(String paramString, Bundle paramBundle)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("event cannot be null or empty");
    this.mImpl.sendSessionEvent(paramString, paramBundle);
  }

  public void setActive(boolean paramBoolean)
  {
    this.mImpl.setActive(paramBoolean);
    Iterator localIterator = this.mActiveListeners.iterator();
    while (localIterator.hasNext())
      ((OnActiveChangeListener)localIterator.next()).onActiveChanged();
  }

  public void setCallback(Callback paramCallback)
  {
    setCallback(paramCallback, null);
  }

  public void setCallback(Callback paramCallback, Handler paramHandler)
  {
    if (paramCallback == null)
    {
      this.mImpl.setCallback(null, null);
    }
    else
    {
      MediaSessionImpl localMediaSessionImpl = this.mImpl;
      if (paramHandler == null)
        paramHandler = new Handler();
      localMediaSessionImpl.setCallback(paramCallback, paramHandler);
    }
  }

  public void setCaptioningEnabled(boolean paramBoolean)
  {
    this.mImpl.setCaptioningEnabled(paramBoolean);
  }

  public void setExtras(Bundle paramBundle)
  {
    this.mImpl.setExtras(paramBundle);
  }

  public void setFlags(int paramInt)
  {
    this.mImpl.setFlags(paramInt);
  }

  public void setMediaButtonReceiver(PendingIntent paramPendingIntent)
  {
    this.mImpl.setMediaButtonReceiver(paramPendingIntent);
  }

  public void setMetadata(MediaMetadataCompat paramMediaMetadataCompat)
  {
    this.mImpl.setMetadata(paramMediaMetadataCompat);
  }

  public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
  {
    this.mImpl.setPlaybackState(paramPlaybackStateCompat);
  }

  public void setPlaybackToLocal(int paramInt)
  {
    this.mImpl.setPlaybackToLocal(paramInt);
  }

  public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
  {
    if (paramVolumeProviderCompat == null)
      throw new IllegalArgumentException("volumeProvider may not be null!");
    this.mImpl.setPlaybackToRemote(paramVolumeProviderCompat);
  }

  public void setQueue(List<QueueItem> paramList)
  {
    this.mImpl.setQueue(paramList);
  }

  public void setQueueTitle(CharSequence paramCharSequence)
  {
    this.mImpl.setQueueTitle(paramCharSequence);
  }

  public void setRatingType(int paramInt)
  {
    this.mImpl.setRatingType(paramInt);
  }

  public void setRepeatMode(int paramInt)
  {
    this.mImpl.setRepeatMode(paramInt);
  }

  public void setSessionActivity(PendingIntent paramPendingIntent)
  {
    this.mImpl.setSessionActivity(paramPendingIntent);
  }

  public void setShuffleMode(int paramInt)
  {
    this.mImpl.setShuffleMode(paramInt);
  }

  public static abstract class Callback
  {
    private CallbackHandler mCallbackHandler = null;
    final Object mCallbackObj;
    private boolean mMediaPlayPauseKeyPending;
    WeakReference<MediaSessionCompat.MediaSessionImpl> mSessionImpl;

    public Callback()
    {
      if (Build.VERSION.SDK_INT >= 24)
        this.mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
      else if (Build.VERSION.SDK_INT >= 23)
        this.mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
      else if (Build.VERSION.SDK_INT >= 21)
        this.mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
      else
        this.mCallbackObj = null;
    }

    void handleMediaPlayPauseKeySingleTapIfPending(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo)
    {
      if (!this.mMediaPlayPauseKeyPending)
        return;
      this.mMediaPlayPauseKeyPending = false;
      this.mCallbackHandler.removeMessages(1);
      MediaSessionCompat.MediaSessionImpl localMediaSessionImpl = (MediaSessionCompat.MediaSessionImpl)this.mSessionImpl.get();
      if (localMediaSessionImpl == null)
        return;
      PlaybackStateCompat localPlaybackStateCompat = localMediaSessionImpl.getPlaybackState();
      long l;
      if (localPlaybackStateCompat == null)
        l = 0L;
      else
        l = localPlaybackStateCompat.getActions();
      int i;
      if ((localPlaybackStateCompat != null) && (localPlaybackStateCompat.getState() == 3))
        i = 1;
      else
        i = 0;
      int j;
      if ((l & 0x204) != 0L)
        j = 1;
      else
        j = 0;
      boolean bool = (l & 0x202) < 0L;
      int k = 0;
      if (bool)
        k = 1;
      localMediaSessionImpl.setCurrentControllerInfo(paramRemoteUserInfo);
      if ((i != 0) && (k != 0))
        onPause();
      else if ((i == 0) && (j != 0))
        onPlay();
      localMediaSessionImpl.setCurrentControllerInfo(null);
    }

    public void onAddQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
    }

    public void onAddQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
    }

    public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
    }

    public void onCustomAction(String paramString, Bundle paramBundle)
    {
    }

    public void onFastForward()
    {
    }

    public boolean onMediaButtonEvent(Intent paramIntent)
    {
      if (Build.VERSION.SDK_INT >= 27)
        return false;
      MediaSessionCompat.MediaSessionImpl localMediaSessionImpl = (MediaSessionCompat.MediaSessionImpl)this.mSessionImpl.get();
      if ((localMediaSessionImpl != null) && (this.mCallbackHandler != null))
      {
        KeyEvent localKeyEvent = (KeyEvent)paramIntent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if ((localKeyEvent != null) && (localKeyEvent.getAction() == 0))
        {
          MediaSessionManager.RemoteUserInfo localRemoteUserInfo = localMediaSessionImpl.getCurrentControllerInfo();
          int i = localKeyEvent.getKeyCode();
          if ((i != 79) && (i != 85))
          {
            handleMediaPlayPauseKeySingleTapIfPending(localRemoteUserInfo);
            return false;
          }
          if (localKeyEvent.getRepeatCount() > 0)
          {
            handleMediaPlayPauseKeySingleTapIfPending(localRemoteUserInfo);
          }
          else if (this.mMediaPlayPauseKeyPending)
          {
            this.mCallbackHandler.removeMessages(1);
            this.mMediaPlayPauseKeyPending = false;
            PlaybackStateCompat localPlaybackStateCompat = localMediaSessionImpl.getPlaybackState();
            long l;
            if (localPlaybackStateCompat == null)
              l = 0L;
            else
              l = localPlaybackStateCompat.getActions();
            if ((l & 0x20) != 0L)
              onSkipToNext();
          }
          else
          {
            this.mMediaPlayPauseKeyPending = true;
            this.mCallbackHandler.sendMessageDelayed(this.mCallbackHandler.obtainMessage(1, localRemoteUserInfo), ViewConfiguration.getDoubleTapTimeout());
          }
          return true;
        }
        return false;
      }
      return false;
    }

    public void onPause()
    {
    }

    public void onPlay()
    {
    }

    public void onPlayFromMediaId(String paramString, Bundle paramBundle)
    {
    }

    public void onPlayFromSearch(String paramString, Bundle paramBundle)
    {
    }

    public void onPlayFromUri(Uri paramUri, Bundle paramBundle)
    {
    }

    public void onPrepare()
    {
    }

    public void onPrepareFromMediaId(String paramString, Bundle paramBundle)
    {
    }

    public void onPrepareFromSearch(String paramString, Bundle paramBundle)
    {
    }

    public void onPrepareFromUri(Uri paramUri, Bundle paramBundle)
    {
    }

    public void onRemoveQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
    }

    @Deprecated
    public void onRemoveQueueItemAt(int paramInt)
    {
    }

    public void onRewind()
    {
    }

    public void onSeekTo(long paramLong)
    {
    }

    public void onSetCaptioningEnabled(boolean paramBoolean)
    {
    }

    public void onSetRating(RatingCompat paramRatingCompat)
    {
    }

    public void onSetRating(RatingCompat paramRatingCompat, Bundle paramBundle)
    {
    }

    public void onSetRepeatMode(int paramInt)
    {
    }

    public void onSetShuffleMode(int paramInt)
    {
    }

    public void onSkipToNext()
    {
    }

    public void onSkipToPrevious()
    {
    }

    public void onSkipToQueueItem(long paramLong)
    {
    }

    public void onStop()
    {
    }

    void setSessionImpl(MediaSessionCompat.MediaSessionImpl paramMediaSessionImpl, Handler paramHandler)
    {
      this.mSessionImpl = new WeakReference(paramMediaSessionImpl);
      if (this.mCallbackHandler != null)
        this.mCallbackHandler.removeCallbacksAndMessages(null);
      this.mCallbackHandler = new CallbackHandler(paramHandler.getLooper());
    }

    private class CallbackHandler extends Handler
    {
      private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;

      CallbackHandler(Looper arg2)
      {
        super();
      }

      public void handleMessage(Message paramMessage)
      {
        if (paramMessage.what == 1)
          MediaSessionCompat.Callback.this.handleMediaPlayPauseKeySingleTapIfPending((MediaSessionManager.RemoteUserInfo)paramMessage.obj);
      }
    }

    @RequiresApi(21)
    private class StubApi21
      implements MediaSessionCompatApi21.Callback
    {
      StubApi21()
      {
      }

      // ERROR //
      public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
      {
        // Byte code:
        //   0: aload_1
        //   1: ldc 24
        //   3: invokevirtual 30	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   6: ifeq +97 -> 103
        //   9: aload_0
        //   10: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   13: getfield 36	android/support/v4/media/session/MediaSessionCompat$Callback:mSessionImpl	Ljava/lang/ref/WeakReference;
        //   16: invokevirtual 42	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
        //   19: checkcast 44	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi21
        //   22: astore 9
        //   24: aload 9
        //   26: ifnull +301 -> 327
        //   29: new 46	android/os/Bundle
        //   32: dup
        //   33: invokespecial 47	android/os/Bundle:<init>	()V
        //   36: astore 10
        //   38: aload 9
        //   40: invokevirtual 51	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi21:getSessionToken	()Landroid/support/v4/media/session/MediaSessionCompat$Token;
        //   43: astore 11
        //   45: aload 11
        //   47: invokevirtual 57	android/support/v4/media/session/MediaSessionCompat$Token:getExtraBinder	()Landroid/support/v4/media/session/IMediaSession;
        //   50: astore 12
        //   52: aload 12
        //   54: ifnonnull +9 -> 63
        //   57: aconst_null
        //   58: astore 13
        //   60: goto +12 -> 72
        //   63: aload 12
        //   65: invokeinterface 63 1 0
        //   70: astore 13
        //   72: aload 10
        //   74: ldc 65
        //   76: aload 13
        //   78: invokestatic 71	android/support/v4/app/BundleCompat:putBinder	(Landroid/os/Bundle;Ljava/lang/String;Landroid/os/IBinder;)V
        //   81: aload 10
        //   83: ldc 73
        //   85: aload 11
        //   87: invokevirtual 77	android/support/v4/media/session/MediaSessionCompat$Token:getSessionToken2Bundle	()Landroid/os/Bundle;
        //   90: invokevirtual 81	android/os/Bundle:putBundle	(Ljava/lang/String;Landroid/os/Bundle;)V
        //   93: aload_3
        //   94: iconst_0
        //   95: aload 10
        //   97: invokevirtual 87	android/os/ResultReceiver:send	(ILandroid/os/Bundle;)V
        //   100: goto +227 -> 327
        //   103: aload_1
        //   104: ldc 89
        //   106: invokevirtual 30	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   109: ifeq +22 -> 131
        //   112: aload_0
        //   113: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   116: aload_2
        //   117: ldc 91
        //   119: invokevirtual 95	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
        //   122: checkcast 97	android/support/v4/media/MediaDescriptionCompat
        //   125: invokevirtual 101	android/support/v4/media/session/MediaSessionCompat$Callback:onAddQueueItem	(Landroid/support/v4/media/MediaDescriptionCompat;)V
        //   128: goto +199 -> 327
        //   131: aload_1
        //   132: ldc 103
        //   134: invokevirtual 30	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   137: ifeq +28 -> 165
        //   140: aload_0
        //   141: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   144: aload_2
        //   145: ldc 91
        //   147: invokevirtual 95	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
        //   150: checkcast 97	android/support/v4/media/MediaDescriptionCompat
        //   153: aload_2
        //   154: ldc 105
        //   156: invokevirtual 109	android/os/Bundle:getInt	(Ljava/lang/String;)I
        //   159: invokevirtual 112	android/support/v4/media/session/MediaSessionCompat$Callback:onAddQueueItem	(Landroid/support/v4/media/MediaDescriptionCompat;I)V
        //   162: goto +165 -> 327
        //   165: aload_1
        //   166: ldc 114
        //   168: invokevirtual 30	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   171: ifeq +22 -> 193
        //   174: aload_0
        //   175: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   178: aload_2
        //   179: ldc 91
        //   181: invokevirtual 95	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
        //   184: checkcast 97	android/support/v4/media/MediaDescriptionCompat
        //   187: invokevirtual 117	android/support/v4/media/session/MediaSessionCompat$Callback:onRemoveQueueItem	(Landroid/support/v4/media/MediaDescriptionCompat;)V
        //   190: goto +137 -> 327
        //   193: aload_1
        //   194: ldc 119
        //   196: invokevirtual 30	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   199: ifeq +107 -> 306
        //   202: aload_0
        //   203: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   206: getfield 36	android/support/v4/media/session/MediaSessionCompat$Callback:mSessionImpl	Ljava/lang/ref/WeakReference;
        //   209: invokevirtual 42	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
        //   212: checkcast 44	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi21
        //   215: astore 5
        //   217: aload 5
        //   219: ifnull +108 -> 327
        //   222: aload 5
        //   224: getfield 123	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi21:mQueue	Ljava/util/List;
        //   227: ifnull +100 -> 327
        //   230: aload_2
        //   231: ldc 105
        //   233: iconst_m1
        //   234: invokevirtual 126	android/os/Bundle:getInt	(Ljava/lang/String;I)I
        //   237: istore 6
        //   239: aconst_null
        //   240: astore 7
        //   242: iload 6
        //   244: iflt +42 -> 286
        //   247: aload 5
        //   249: getfield 123	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi21:mQueue	Ljava/util/List;
        //   252: invokeinterface 132 1 0
        //   257: istore 8
        //   259: aconst_null
        //   260: astore 7
        //   262: iload 6
        //   264: iload 8
        //   266: if_icmpge +20 -> 286
        //   269: aload 5
        //   271: getfield 123	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi21:mQueue	Ljava/util/List;
        //   274: iload 6
        //   276: invokeinterface 135 2 0
        //   281: checkcast 137	android/support/v4/media/session/MediaSessionCompat$QueueItem
        //   284: astore 7
        //   286: aload 7
        //   288: ifnull +39 -> 327
        //   291: aload_0
        //   292: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   295: aload 7
        //   297: invokevirtual 141	android/support/v4/media/session/MediaSessionCompat$QueueItem:getDescription	()Landroid/support/v4/media/MediaDescriptionCompat;
        //   300: invokevirtual 117	android/support/v4/media/session/MediaSessionCompat$Callback:onRemoveQueueItem	(Landroid/support/v4/media/MediaDescriptionCompat;)V
        //   303: goto +24 -> 327
        //   306: aload_0
        //   307: getfield 15	android/support/v4/media/session/MediaSessionCompat$Callback$StubApi21:this$0	Landroid/support/v4/media/session/MediaSessionCompat$Callback;
        //   310: aload_1
        //   311: aload_2
        //   312: aload_3
        //   313: invokevirtual 143	android/support/v4/media/session/MediaSessionCompat$Callback:onCommand	(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/ResultReceiver;)V
        //   316: goto +11 -> 327
        //   319: ldc 145
        //   321: ldc 147
        //   323: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   326: pop
        //   327: return
        //
        // Exception table:
        //   from	to	target	type
        //   0	316	319	android/os/BadParcelableException
      }

      public void onCustomAction(String paramString, Bundle paramBundle)
      {
        Bundle localBundle = paramBundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
        MediaSessionCompat.ensureClassLoader(localBundle);
        if (paramString.equals("android.support.v4.media.session.action.PLAY_FROM_URI"))
        {
          Uri localUri2 = (Uri)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
          MediaSessionCompat.Callback.this.onPlayFromUri(localUri2, localBundle);
        }
        else if (paramString.equals("android.support.v4.media.session.action.PREPARE"))
        {
          MediaSessionCompat.Callback.this.onPrepare();
        }
        else if (paramString.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID"))
        {
          String str2 = paramBundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
          MediaSessionCompat.Callback.this.onPrepareFromMediaId(str2, localBundle);
        }
        else if (paramString.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH"))
        {
          String str1 = paramBundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
          MediaSessionCompat.Callback.this.onPrepareFromSearch(str1, localBundle);
        }
        else if (paramString.equals("android.support.v4.media.session.action.PREPARE_FROM_URI"))
        {
          Uri localUri1 = (Uri)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
          MediaSessionCompat.Callback.this.onPrepareFromUri(localUri1, localBundle);
        }
        else if (paramString.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED"))
        {
          boolean bool = paramBundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
          MediaSessionCompat.Callback.this.onSetCaptioningEnabled(bool);
        }
        else if (paramString.equals("android.support.v4.media.session.action.SET_REPEAT_MODE"))
        {
          int j = paramBundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
          MediaSessionCompat.Callback.this.onSetRepeatMode(j);
        }
        else if (paramString.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE"))
        {
          int i = paramBundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
          MediaSessionCompat.Callback.this.onSetShuffleMode(i);
        }
        else if (paramString.equals("android.support.v4.media.session.action.SET_RATING"))
        {
          RatingCompat localRatingCompat = (RatingCompat)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
          MediaSessionCompat.Callback.this.onSetRating(localRatingCompat, localBundle);
        }
        else
        {
          MediaSessionCompat.Callback.this.onCustomAction(paramString, paramBundle);
        }
      }

      public void onFastForward()
      {
        MediaSessionCompat.Callback.this.onFastForward();
      }

      public boolean onMediaButtonEvent(Intent paramIntent)
      {
        return MediaSessionCompat.Callback.this.onMediaButtonEvent(paramIntent);
      }

      public void onPause()
      {
        MediaSessionCompat.Callback.this.onPause();
      }

      public void onPlay()
      {
        MediaSessionCompat.Callback.this.onPlay();
      }

      public void onPlayFromMediaId(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPlayFromMediaId(paramString, paramBundle);
      }

      public void onPlayFromSearch(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPlayFromSearch(paramString, paramBundle);
      }

      public void onRewind()
      {
        MediaSessionCompat.Callback.this.onRewind();
      }

      public void onSeekTo(long paramLong)
      {
        MediaSessionCompat.Callback.this.onSeekTo(paramLong);
      }

      public void onSetRating(Object paramObject)
      {
        MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(paramObject));
      }

      public void onSetRating(Object paramObject, Bundle paramBundle)
      {
      }

      public void onSkipToNext()
      {
        MediaSessionCompat.Callback.this.onSkipToNext();
      }

      public void onSkipToPrevious()
      {
        MediaSessionCompat.Callback.this.onSkipToPrevious();
      }

      public void onSkipToQueueItem(long paramLong)
      {
        MediaSessionCompat.Callback.this.onSkipToQueueItem(paramLong);
      }

      public void onStop()
      {
        MediaSessionCompat.Callback.this.onStop();
      }
    }

    @RequiresApi(23)
    private class StubApi23 extends MediaSessionCompat.Callback.StubApi21
      implements MediaSessionCompatApi23.Callback
    {
      StubApi23()
      {
        super();
      }

      public void onPlayFromUri(Uri paramUri, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPlayFromUri(paramUri, paramBundle);
      }
    }

    @RequiresApi(24)
    private class StubApi24 extends MediaSessionCompat.Callback.StubApi23
      implements MediaSessionCompatApi24.Callback
    {
      StubApi24()
      {
        super();
      }

      public void onPrepare()
      {
        MediaSessionCompat.Callback.this.onPrepare();
      }

      public void onPrepareFromMediaId(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPrepareFromMediaId(paramString, paramBundle);
      }

      public void onPrepareFromSearch(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPrepareFromSearch(paramString, paramBundle);
      }

      public void onPrepareFromUri(Uri paramUri, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPrepareFromUri(paramUri, paramBundle);
      }
    }
  }

  static abstract interface MediaSessionImpl
  {
    public abstract String getCallingPackage();

    public abstract MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

    public abstract Object getMediaSession();

    public abstract PlaybackStateCompat getPlaybackState();

    public abstract Object getRemoteControlClient();

    public abstract MediaSessionCompat.Token getSessionToken();

    public abstract boolean isActive();

    public abstract void release();

    public abstract void sendSessionEvent(String paramString, Bundle paramBundle);

    public abstract void setActive(boolean paramBoolean);

    public abstract void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler);

    public abstract void setCaptioningEnabled(boolean paramBoolean);

    public abstract void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo);

    public abstract void setExtras(Bundle paramBundle);

    public abstract void setFlags(int paramInt);

    public abstract void setMediaButtonReceiver(PendingIntent paramPendingIntent);

    public abstract void setMetadata(MediaMetadataCompat paramMediaMetadataCompat);

    public abstract void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat);

    public abstract void setPlaybackToLocal(int paramInt);

    public abstract void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat);

    public abstract void setQueue(List<MediaSessionCompat.QueueItem> paramList);

    public abstract void setQueueTitle(CharSequence paramCharSequence);

    public abstract void setRatingType(int paramInt);

    public abstract void setRepeatMode(int paramInt);

    public abstract void setSessionActivity(PendingIntent paramPendingIntent);

    public abstract void setShuffleMode(int paramInt);
  }

  @RequiresApi(18)
  static class MediaSessionImplApi18 extends MediaSessionCompat.MediaSessionImplBase
  {
    private static boolean sIsMbrPendingIntentSupported = true;

    MediaSessionImplApi18(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
    {
      super(paramString, paramComponentName, paramPendingIntent);
    }

    int getRccTransportControlFlagsFromActions(long paramLong)
    {
      int i = super.getRccTransportControlFlagsFromActions(paramLong);
      if ((paramLong & 0x100) != 0L)
        i |= 256;
      return i;
    }

    // ERROR //
    void registerMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      // Byte code:
      //   0: getstatic 28	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi18:sIsMbrPendingIntentSupported	Z
      //   3: ifeq +26 -> 29
      //   6: aload_0
      //   7: getfield 32	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi18:mAudioManager	Landroid/media/AudioManager;
      //   10: aload_1
      //   11: invokevirtual 37	android/media/AudioManager:registerMediaButtonEventReceiver	(Landroid/app/PendingIntent;)V
      //   14: goto +15 -> 29
      //   17: ldc 39
      //   19: ldc 41
      //   21: invokestatic 47	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   24: pop
      //   25: iconst_0
      //   26: putstatic 28	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi18:sIsMbrPendingIntentSupported	Z
      //   29: getstatic 28	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplApi18:sIsMbrPendingIntentSupported	Z
      //   32: ifne +9 -> 41
      //   35: aload_0
      //   36: aload_1
      //   37: aload_2
      //   38: invokespecial 49	android/support/v4/media/session/MediaSessionCompat$MediaSessionImplBase:registerMediaButtonEventReceiver	(Landroid/app/PendingIntent;Landroid/content/ComponentName;)V
      //   41: return
      //
      // Exception table:
      //   from	to	target	type
      //   6	14	17	java/lang/NullPointerException
    }

    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      super.setCallback(paramCallback, paramHandler);
      if (paramCallback == null)
      {
        this.mRcc.setPlaybackPositionUpdateListener(null);
      }
      else
      {
        RemoteControlClient.OnPlaybackPositionUpdateListener local1 = new RemoteControlClient.OnPlaybackPositionUpdateListener()
        {
          public void onPlaybackPositionUpdate(long paramAnonymousLong)
          {
            MediaSessionCompat.MediaSessionImplApi18.this.postToHandler(18, -1, -1, Long.valueOf(paramAnonymousLong), null);
          }
        };
        this.mRcc.setPlaybackPositionUpdateListener(local1);
      }
    }

    void setRccState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      long l1 = paramPlaybackStateCompat.getPosition();
      float f = paramPlaybackStateCompat.getPlaybackSpeed();
      long l2 = paramPlaybackStateCompat.getLastPositionUpdateTime();
      long l3 = SystemClock.elapsedRealtime();
      if (paramPlaybackStateCompat.getState() == 3)
      {
        long l4 = 0L;
        if (l1 > l4)
        {
          if (l2 > l4)
          {
            l4 = l3 - l2;
            if ((f > 0.0F) && (f != 1.0F))
              l4 = ()(f * (float)l4);
          }
          l1 += l4;
        }
      }
      this.mRcc.setPlaybackState(getRccStateFromState(paramPlaybackStateCompat.getState()), l1, f);
    }

    void unregisterMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      if (sIsMbrPendingIntentSupported)
        this.mAudioManager.unregisterMediaButtonEventReceiver(paramPendingIntent);
      else
        super.unregisterMediaButtonEventReceiver(paramPendingIntent, paramComponentName);
    }
  }

  @RequiresApi(19)
  static class MediaSessionImplApi19 extends MediaSessionCompat.MediaSessionImplApi18
  {
    MediaSessionImplApi19(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
    {
      super(paramString, paramComponentName, paramPendingIntent);
    }

    RemoteControlClient.MetadataEditor buildRccMetadata(Bundle paramBundle)
    {
      RemoteControlClient.MetadataEditor localMetadataEditor = super.buildRccMetadata(paramBundle);
      long l;
      if (this.mState == null)
        l = 0L;
      else
        l = this.mState.getActions();
      if ((l & 0x80) != 0L)
        localMetadataEditor.addEditableKey(268435457);
      if (paramBundle == null)
        return localMetadataEditor;
      if (paramBundle.containsKey("android.media.metadata.YEAR"))
        localMetadataEditor.putLong(8, paramBundle.getLong("android.media.metadata.YEAR"));
      if (paramBundle.containsKey("android.media.metadata.RATING"))
        localMetadataEditor.putObject(101, paramBundle.getParcelable("android.media.metadata.RATING"));
      if (paramBundle.containsKey("android.media.metadata.USER_RATING"))
        localMetadataEditor.putObject(268435457, paramBundle.getParcelable("android.media.metadata.USER_RATING"));
      return localMetadataEditor;
    }

    int getRccTransportControlFlagsFromActions(long paramLong)
    {
      int i = super.getRccTransportControlFlagsFromActions(paramLong);
      if ((paramLong & 0x80) != 0L)
        i |= 512;
      return i;
    }

    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      super.setCallback(paramCallback, paramHandler);
      if (paramCallback == null)
      {
        this.mRcc.setMetadataUpdateListener(null);
      }
      else
      {
        RemoteControlClient.OnMetadataUpdateListener local1 = new RemoteControlClient.OnMetadataUpdateListener()
        {
          public void onMetadataUpdate(int paramAnonymousInt, Object paramAnonymousObject)
          {
            if ((paramAnonymousInt == 268435457) && ((paramAnonymousObject instanceof Rating)))
              MediaSessionCompat.MediaSessionImplApi19.this.postToHandler(19, -1, -1, RatingCompat.fromRating(paramAnonymousObject), null);
          }
        };
        this.mRcc.setMetadataUpdateListener(local1);
      }
    }
  }

  @RequiresApi(21)
  static class MediaSessionImplApi21
    implements MediaSessionCompat.MediaSessionImpl
  {
    boolean mCaptioningEnabled;
    boolean mDestroyed = false;
    final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks = new RemoteCallbackList();
    MediaMetadataCompat mMetadata;
    PlaybackStateCompat mPlaybackState;
    List<MediaSessionCompat.QueueItem> mQueue;
    int mRatingType;
    int mRepeatMode;
    final Object mSessionObj;
    int mShuffleMode;
    final MediaSessionCompat.Token mToken;

    MediaSessionImplApi21(Context paramContext, String paramString, Bundle paramBundle)
    {
      this.mSessionObj = MediaSessionCompatApi21.createSession(paramContext, paramString);
      this.mToken = new MediaSessionCompat.Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new ExtraSession(), paramBundle);
    }

    MediaSessionImplApi21(Object paramObject)
    {
      this.mSessionObj = MediaSessionCompatApi21.verifySession(paramObject);
      this.mToken = new MediaSessionCompat.Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new ExtraSession());
    }

    public String getCallingPackage()
    {
      if (Build.VERSION.SDK_INT < 24)
        return null;
      return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
    }

    public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo()
    {
      return null;
    }

    public Object getMediaSession()
    {
      return this.mSessionObj;
    }

    public PlaybackStateCompat getPlaybackState()
    {
      return this.mPlaybackState;
    }

    public Object getRemoteControlClient()
    {
      return null;
    }

    public MediaSessionCompat.Token getSessionToken()
    {
      return this.mToken;
    }

    public boolean isActive()
    {
      return MediaSessionCompatApi21.isActive(this.mSessionObj);
    }

    public void release()
    {
      this.mDestroyed = true;
      MediaSessionCompatApi21.release(this.mSessionObj);
    }

    public void sendSessionEvent(String paramString, Bundle paramBundle)
    {
      if (Build.VERSION.SDK_INT < 23)
      {
        for (int i = -1 + this.mExtraControllerCallbacks.beginBroadcast(); i >= 0; i--)
        {
          IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);
          try
          {
            localIMediaControllerCallback.onEvent(paramString, paramBundle);
          }
          catch (RemoteException localRemoteException)
          {
          }
        }
        this.mExtraControllerCallbacks.finishBroadcast();
      }
      MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, paramString, paramBundle);
    }

    public void setActive(boolean paramBoolean)
    {
      MediaSessionCompatApi21.setActive(this.mSessionObj, paramBoolean);
    }

    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      Object localObject1 = this.mSessionObj;
      Object localObject2;
      if (paramCallback == null)
        localObject2 = null;
      else
        localObject2 = paramCallback.mCallbackObj;
      MediaSessionCompatApi21.setCallback(localObject1, localObject2, paramHandler);
      if (paramCallback != null)
        paramCallback.setSessionImpl(this, paramHandler);
    }

    public void setCaptioningEnabled(boolean paramBoolean)
    {
      if (this.mCaptioningEnabled != paramBoolean)
      {
        this.mCaptioningEnabled = paramBoolean;
        for (int i = -1 + this.mExtraControllerCallbacks.beginBroadcast(); i >= 0; i--)
        {
          IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);
          try
          {
            localIMediaControllerCallback.onCaptioningEnabledChanged(paramBoolean);
          }
          catch (RemoteException localRemoteException)
          {
          }
        }
        this.mExtraControllerCallbacks.finishBroadcast();
      }
    }

    public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo)
    {
    }

    public void setExtras(Bundle paramBundle)
    {
      MediaSessionCompatApi21.setExtras(this.mSessionObj, paramBundle);
    }

    public void setFlags(int paramInt)
    {
      MediaSessionCompatApi21.setFlags(this.mSessionObj, paramInt);
    }

    public void setMediaButtonReceiver(PendingIntent paramPendingIntent)
    {
      MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, paramPendingIntent);
    }

    public void setMetadata(MediaMetadataCompat paramMediaMetadataCompat)
    {
      this.mMetadata = paramMediaMetadataCompat;
      Object localObject1 = this.mSessionObj;
      Object localObject2;
      if (paramMediaMetadataCompat == null)
        localObject2 = null;
      else
        localObject2 = paramMediaMetadataCompat.getMediaMetadata();
      MediaSessionCompatApi21.setMetadata(localObject1, localObject2);
    }

    public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      this.mPlaybackState = paramPlaybackStateCompat;
      for (int i = -1 + this.mExtraControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onPlaybackStateChanged(paramPlaybackStateCompat);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mExtraControllerCallbacks.finishBroadcast();
      Object localObject1 = this.mSessionObj;
      Object localObject2;
      if (paramPlaybackStateCompat == null)
        localObject2 = null;
      else
        localObject2 = paramPlaybackStateCompat.getPlaybackState();
      MediaSessionCompatApi21.setPlaybackState(localObject1, localObject2);
    }

    public void setPlaybackToLocal(int paramInt)
    {
      MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, paramInt);
    }

    public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
    {
      MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, paramVolumeProviderCompat.getVolumeProvider());
    }

    public void setQueue(List<MediaSessionCompat.QueueItem> paramList)
    {
      this.mQueue = paramList;
      if (paramList != null)
      {
        localArrayList = new ArrayList();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          localArrayList.add(((MediaSessionCompat.QueueItem)localIterator.next()).getQueueItem());
      }
      ArrayList localArrayList = null;
      MediaSessionCompatApi21.setQueue(this.mSessionObj, localArrayList);
    }

    public void setQueueTitle(CharSequence paramCharSequence)
    {
      MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, paramCharSequence);
    }

    public void setRatingType(int paramInt)
    {
      if (Build.VERSION.SDK_INT < 22)
        this.mRatingType = paramInt;
      else
        MediaSessionCompatApi22.setRatingType(this.mSessionObj, paramInt);
    }

    public void setRepeatMode(int paramInt)
    {
      if (this.mRepeatMode != paramInt)
      {
        this.mRepeatMode = paramInt;
        for (int i = -1 + this.mExtraControllerCallbacks.beginBroadcast(); i >= 0; i--)
        {
          IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);
          try
          {
            localIMediaControllerCallback.onRepeatModeChanged(paramInt);
          }
          catch (RemoteException localRemoteException)
          {
          }
        }
        this.mExtraControllerCallbacks.finishBroadcast();
      }
    }

    public void setSessionActivity(PendingIntent paramPendingIntent)
    {
      MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, paramPendingIntent);
    }

    public void setShuffleMode(int paramInt)
    {
      if (this.mShuffleMode != paramInt)
      {
        this.mShuffleMode = paramInt;
        for (int i = -1 + this.mExtraControllerCallbacks.beginBroadcast(); i >= 0; i--)
        {
          IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(i);
          try
          {
            localIMediaControllerCallback.onShuffleModeChanged(paramInt);
          }
          catch (RemoteException localRemoteException)
          {
          }
        }
        this.mExtraControllerCallbacks.finishBroadcast();
      }
    }

    class ExtraSession extends IMediaSession.Stub
    {
      ExtraSession()
      {
      }

      public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        throw new AssertionError();
      }

      public void addQueueItemAt(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
      {
        throw new AssertionError();
      }

      public void adjustVolume(int paramInt1, int paramInt2, String paramString)
      {
        throw new AssertionError();
      }

      public void fastForward()
      {
        throw new AssertionError();
      }

      public Bundle getExtras()
      {
        throw new AssertionError();
      }

      public long getFlags()
      {
        throw new AssertionError();
      }

      public PendingIntent getLaunchPendingIntent()
      {
        throw new AssertionError();
      }

      public MediaMetadataCompat getMetadata()
      {
        throw new AssertionError();
      }

      public String getPackageName()
      {
        throw new AssertionError();
      }

      public PlaybackStateCompat getPlaybackState()
      {
        return MediaSessionCompat.getStateWithUpdatedPosition(MediaSessionCompat.MediaSessionImplApi21.this.mPlaybackState, MediaSessionCompat.MediaSessionImplApi21.this.mMetadata);
      }

      public List<MediaSessionCompat.QueueItem> getQueue()
      {
        return null;
      }

      public CharSequence getQueueTitle()
      {
        throw new AssertionError();
      }

      public int getRatingType()
      {
        return MediaSessionCompat.MediaSessionImplApi21.this.mRatingType;
      }

      public int getRepeatMode()
      {
        return MediaSessionCompat.MediaSessionImplApi21.this.mRepeatMode;
      }

      public int getShuffleMode()
      {
        return MediaSessionCompat.MediaSessionImplApi21.this.mShuffleMode;
      }

      public String getTag()
      {
        throw new AssertionError();
      }

      public ParcelableVolumeInfo getVolumeAttributes()
      {
        throw new AssertionError();
      }

      public boolean isCaptioningEnabled()
      {
        return MediaSessionCompat.MediaSessionImplApi21.this.mCaptioningEnabled;
      }

      public boolean isShuffleModeEnabledRemoved()
      {
        return false;
      }

      public boolean isTransportControlEnabled()
      {
        throw new AssertionError();
      }

      public void next()
      {
        throw new AssertionError();
      }

      public void pause()
      {
        throw new AssertionError();
      }

      public void play()
      {
        throw new AssertionError();
      }

      public void playFromMediaId(String paramString, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void playFromSearch(String paramString, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void playFromUri(Uri paramUri, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void prepare()
      {
        throw new AssertionError();
      }

      public void prepareFromMediaId(String paramString, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void prepareFromSearch(String paramString, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void prepareFromUri(Uri paramUri, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void previous()
      {
        throw new AssertionError();
      }

      public void rate(RatingCompat paramRatingCompat)
      {
        throw new AssertionError();
      }

      public void rateWithExtras(RatingCompat paramRatingCompat, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        if (!MediaSessionCompat.MediaSessionImplApi21.this.mDestroyed)
        {
          String str = MediaSessionCompat.MediaSessionImplApi21.this.getCallingPackage();
          if (str == null)
            str = "android.media.session.MediaController";
          MediaSessionManager.RemoteUserInfo localRemoteUserInfo = new MediaSessionManager.RemoteUserInfo(str, getCallingPid(), getCallingUid());
          MediaSessionCompat.MediaSessionImplApi21.this.mExtraControllerCallbacks.register(paramIMediaControllerCallback, localRemoteUserInfo);
        }
      }

      public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        throw new AssertionError();
      }

      public void removeQueueItemAt(int paramInt)
      {
        throw new AssertionError();
      }

      public void rewind()
      {
        throw new AssertionError();
      }

      public void seekTo(long paramLong)
      {
        throw new AssertionError();
      }

      public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
      {
        throw new AssertionError();
      }

      public void sendCustomAction(String paramString, Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public boolean sendMediaButton(KeyEvent paramKeyEvent)
      {
        throw new AssertionError();
      }

      public void setCaptioningEnabled(boolean paramBoolean)
      {
        throw new AssertionError();
      }

      public void setRepeatMode(int paramInt)
      {
        throw new AssertionError();
      }

      public void setShuffleMode(int paramInt)
      {
        throw new AssertionError();
      }

      public void setShuffleModeEnabledRemoved(boolean paramBoolean)
      {
      }

      public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
      {
        throw new AssertionError();
      }

      public void skipToQueueItem(long paramLong)
      {
        throw new AssertionError();
      }

      public void stop()
      {
        throw new AssertionError();
      }

      public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        MediaSessionCompat.MediaSessionImplApi21.this.mExtraControllerCallbacks.unregister(paramIMediaControllerCallback);
      }
    }
  }

  @RequiresApi(28)
  static class MediaSessionImplApi28 extends MediaSessionCompat.MediaSessionImplApi21
  {
    MediaSessionImplApi28(Context paramContext, String paramString, Bundle paramBundle)
    {
      super(paramString, paramBundle);
    }

    MediaSessionImplApi28(Object paramObject)
    {
      super();
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo()
    {
      return new MediaSessionManager.RemoteUserInfo(((MediaSession)this.mSessionObj).getCurrentControllerInfo());
    }

    public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo)
    {
    }
  }

  static class MediaSessionImplBase
    implements MediaSessionCompat.MediaSessionImpl
  {
    static final int RCC_PLAYSTATE_NONE;
    final AudioManager mAudioManager;
    volatile MediaSessionCompat.Callback mCallback;
    boolean mCaptioningEnabled;
    private final Context mContext;
    final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks = new RemoteCallbackList();
    boolean mDestroyed = false;
    Bundle mExtras;
    int mFlags;
    private MessageHandler mHandler;
    boolean mIsActive = false;
    private boolean mIsMbrRegistered = false;
    private boolean mIsRccRegistered = false;
    int mLocalStream;
    final Object mLock = new Object();
    private final ComponentName mMediaButtonReceiverComponentName;
    private final PendingIntent mMediaButtonReceiverIntent;
    MediaMetadataCompat mMetadata;
    final String mPackageName;
    List<MediaSessionCompat.QueueItem> mQueue;
    CharSequence mQueueTitle;
    int mRatingType;
    final RemoteControlClient mRcc;
    private MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
    int mRepeatMode;
    PendingIntent mSessionActivity;
    int mShuffleMode;
    PlaybackStateCompat mState;
    private final MediaSessionStub mStub;
    final String mTag;
    private final MediaSessionCompat.Token mToken;
    private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback()
    {
      public void onVolumeChanged(VolumeProviderCompat paramAnonymousVolumeProviderCompat)
      {
        if (MediaSessionCompat.MediaSessionImplBase.this.mVolumeProvider != paramAnonymousVolumeProviderCompat)
          return;
        ParcelableVolumeInfo localParcelableVolumeInfo = new ParcelableVolumeInfo(MediaSessionCompat.MediaSessionImplBase.this.mVolumeType, MediaSessionCompat.MediaSessionImplBase.this.mLocalStream, paramAnonymousVolumeProviderCompat.getVolumeControl(), paramAnonymousVolumeProviderCompat.getMaxVolume(), paramAnonymousVolumeProviderCompat.getCurrentVolume());
        MediaSessionCompat.MediaSessionImplBase.this.sendVolumeInfoChanged(localParcelableVolumeInfo);
      }
    };
    VolumeProviderCompat mVolumeProvider;
    int mVolumeType;

    public MediaSessionImplBase(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
    {
      if (paramComponentName == null)
        throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
      this.mContext = paramContext;
      this.mPackageName = paramContext.getPackageName();
      this.mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
      this.mTag = paramString;
      this.mMediaButtonReceiverComponentName = paramComponentName;
      this.mMediaButtonReceiverIntent = paramPendingIntent;
      this.mStub = new MediaSessionStub();
      this.mToken = new MediaSessionCompat.Token(this.mStub);
      this.mRatingType = 0;
      this.mVolumeType = 1;
      this.mLocalStream = 3;
      this.mRcc = new RemoteControlClient(paramPendingIntent);
    }

    private void sendCaptioningEnabled(boolean paramBoolean)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onCaptioningEnabledChanged(paramBoolean);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendEvent(String paramString, Bundle paramBundle)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onEvent(paramString, paramBundle);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendExtras(Bundle paramBundle)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onExtrasChanged(paramBundle);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendMetadata(MediaMetadataCompat paramMediaMetadataCompat)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onMetadataChanged(paramMediaMetadataCompat);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendQueue(List<MediaSessionCompat.QueueItem> paramList)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onQueueChanged(paramList);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendQueueTitle(CharSequence paramCharSequence)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onQueueTitleChanged(paramCharSequence);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendRepeatMode(int paramInt)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onRepeatModeChanged(paramInt);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendSessionDestroyed()
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onSessionDestroyed();
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
      this.mControllerCallbacks.kill();
    }

    private void sendShuffleMode(int paramInt)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onShuffleModeChanged(paramInt);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    private void sendState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onPlaybackStateChanged(paramPlaybackStateCompat);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    void adjustVolume(int paramInt1, int paramInt2)
    {
      if (this.mVolumeType == 2)
      {
        if (this.mVolumeProvider != null)
          this.mVolumeProvider.onAdjustVolume(paramInt1);
      }
      else
        this.mAudioManager.adjustStreamVolume(this.mLocalStream, paramInt1, paramInt2);
    }

    RemoteControlClient.MetadataEditor buildRccMetadata(Bundle paramBundle)
    {
      RemoteControlClient.MetadataEditor localMetadataEditor = this.mRcc.editMetadata(true);
      if (paramBundle == null)
        return localMetadataEditor;
      if (paramBundle.containsKey("android.media.metadata.ART"))
      {
        Bitmap localBitmap2 = (Bitmap)paramBundle.getParcelable("android.media.metadata.ART");
        if (localBitmap2 != null)
          localBitmap2 = localBitmap2.copy(localBitmap2.getConfig(), false);
        localMetadataEditor.putBitmap(100, localBitmap2);
      }
      else if (paramBundle.containsKey("android.media.metadata.ALBUM_ART"))
      {
        Bitmap localBitmap1 = (Bitmap)paramBundle.getParcelable("android.media.metadata.ALBUM_ART");
        if (localBitmap1 != null)
          localBitmap1 = localBitmap1.copy(localBitmap1.getConfig(), false);
        localMetadataEditor.putBitmap(100, localBitmap1);
      }
      if (paramBundle.containsKey("android.media.metadata.ALBUM"))
        localMetadataEditor.putString(1, paramBundle.getString("android.media.metadata.ALBUM"));
      if (paramBundle.containsKey("android.media.metadata.ALBUM_ARTIST"))
        localMetadataEditor.putString(13, paramBundle.getString("android.media.metadata.ALBUM_ARTIST"));
      if (paramBundle.containsKey("android.media.metadata.ARTIST"))
        localMetadataEditor.putString(2, paramBundle.getString("android.media.metadata.ARTIST"));
      if (paramBundle.containsKey("android.media.metadata.AUTHOR"))
        localMetadataEditor.putString(3, paramBundle.getString("android.media.metadata.AUTHOR"));
      if (paramBundle.containsKey("android.media.metadata.COMPILATION"))
        localMetadataEditor.putString(15, paramBundle.getString("android.media.metadata.COMPILATION"));
      if (paramBundle.containsKey("android.media.metadata.COMPOSER"))
        localMetadataEditor.putString(4, paramBundle.getString("android.media.metadata.COMPOSER"));
      if (paramBundle.containsKey("android.media.metadata.DATE"))
        localMetadataEditor.putString(5, paramBundle.getString("android.media.metadata.DATE"));
      if (paramBundle.containsKey("android.media.metadata.DISC_NUMBER"))
        localMetadataEditor.putLong(14, paramBundle.getLong("android.media.metadata.DISC_NUMBER"));
      if (paramBundle.containsKey("android.media.metadata.DURATION"))
        localMetadataEditor.putLong(9, paramBundle.getLong("android.media.metadata.DURATION"));
      if (paramBundle.containsKey("android.media.metadata.GENRE"))
        localMetadataEditor.putString(6, paramBundle.getString("android.media.metadata.GENRE"));
      if (paramBundle.containsKey("android.media.metadata.TITLE"))
        localMetadataEditor.putString(7, paramBundle.getString("android.media.metadata.TITLE"));
      if (paramBundle.containsKey("android.media.metadata.TRACK_NUMBER"))
        localMetadataEditor.putLong(0, paramBundle.getLong("android.media.metadata.TRACK_NUMBER"));
      if (paramBundle.containsKey("android.media.metadata.WRITER"))
        localMetadataEditor.putString(11, paramBundle.getString("android.media.metadata.WRITER"));
      return localMetadataEditor;
    }

    public String getCallingPackage()
    {
      return null;
    }

    public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo()
    {
      synchronized (this.mLock)
      {
        MediaSessionManager.RemoteUserInfo localRemoteUserInfo = this.mRemoteUserInfo;
        return localRemoteUserInfo;
      }
    }

    public Object getMediaSession()
    {
      return null;
    }

    public PlaybackStateCompat getPlaybackState()
    {
      synchronized (this.mLock)
      {
        PlaybackStateCompat localPlaybackStateCompat = this.mState;
        return localPlaybackStateCompat;
      }
    }

    int getRccStateFromState(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return -1;
      case 10:
      case 11:
        return 6;
      case 9:
        return 7;
      case 7:
        return 9;
      case 6:
      case 8:
        return 8;
      case 5:
        return 5;
      case 4:
        return 4;
      case 3:
        return 3;
      case 2:
        return 2;
      case 1:
        return 1;
      case 0:
      }
      return 0;
    }

    int getRccTransportControlFlagsFromActions(long paramLong)
    {
      int i;
      if ((paramLong & 1L) != 0L)
        i = 32;
      else
        i = 0;
      if ((paramLong & 0x2) != 0L)
        i |= 16;
      if ((paramLong & 0x4) != 0L)
        i |= 4;
      if ((paramLong & 0x8) != 0L)
        i |= 2;
      if ((paramLong & 0x10) != 0L)
        i |= 1;
      if ((paramLong & 0x20) != 0L)
        i |= 128;
      if ((paramLong & 0x40) != 0L)
        i |= 64;
      if ((paramLong & 0x200) != 0L)
        i |= 8;
      return i;
    }

    public Object getRemoteControlClient()
    {
      return null;
    }

    public MediaSessionCompat.Token getSessionToken()
    {
      return this.mToken;
    }

    public boolean isActive()
    {
      return this.mIsActive;
    }

    void postToHandler(int paramInt1, int paramInt2, int paramInt3, Object paramObject, Bundle paramBundle)
    {
      synchronized (this.mLock)
      {
        if (this.mHandler != null)
        {
          Message localMessage = this.mHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject);
          Bundle localBundle = new Bundle();
          localBundle.putString("data_calling_pkg", "android.media.session.MediaController");
          localBundle.putInt("data_calling_pid", Binder.getCallingPid());
          localBundle.putInt("data_calling_uid", Binder.getCallingUid());
          if (paramBundle != null)
            localBundle.putBundle("data_extras", paramBundle);
          localMessage.setData(localBundle);
          localMessage.sendToTarget();
        }
        return;
      }
    }

    void registerMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      this.mAudioManager.registerMediaButtonEventReceiver(paramComponentName);
    }

    public void release()
    {
      this.mIsActive = false;
      this.mDestroyed = true;
      update();
      sendSessionDestroyed();
    }

    public void sendSessionEvent(String paramString, Bundle paramBundle)
    {
      sendEvent(paramString, paramBundle);
    }

    void sendVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
    {
      for (int i = -1 + this.mControllerCallbacks.beginBroadcast(); i >= 0; i--)
      {
        IMediaControllerCallback localIMediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(i);
        try
        {
          localIMediaControllerCallback.onVolumeInfoChanged(paramParcelableVolumeInfo);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      this.mControllerCallbacks.finishBroadcast();
    }

    public void setActive(boolean paramBoolean)
    {
      if (paramBoolean == this.mIsActive)
        return;
      this.mIsActive = paramBoolean;
      if (update())
      {
        setMetadata(this.mMetadata);
        setPlaybackState(this.mState);
      }
    }

    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      this.mCallback = paramCallback;
      if (paramCallback != null)
      {
        if (paramHandler == null)
          paramHandler = new Handler();
        synchronized (this.mLock)
        {
          if (this.mHandler != null)
            this.mHandler.removeCallbacksAndMessages(null);
          this.mHandler = new MessageHandler(paramHandler.getLooper());
          this.mCallback.setSessionImpl(this, paramHandler);
        }
      }
    }

    public void setCaptioningEnabled(boolean paramBoolean)
    {
      if (this.mCaptioningEnabled != paramBoolean)
      {
        this.mCaptioningEnabled = paramBoolean;
        sendCaptioningEnabled(paramBoolean);
      }
    }

    public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo)
    {
      synchronized (this.mLock)
      {
        this.mRemoteUserInfo = paramRemoteUserInfo;
        return;
      }
    }

    public void setExtras(Bundle paramBundle)
    {
      this.mExtras = paramBundle;
      sendExtras(paramBundle);
    }

    public void setFlags(int paramInt)
    {
      synchronized (this.mLock)
      {
        this.mFlags = paramInt;
        update();
        return;
      }
    }

    public void setMediaButtonReceiver(PendingIntent paramPendingIntent)
    {
    }

    public void setMetadata(MediaMetadataCompat paramMediaMetadataCompat)
    {
      if (paramMediaMetadataCompat != null)
        paramMediaMetadataCompat = new MediaMetadataCompat.Builder(paramMediaMetadataCompat, MediaSessionCompat.sMaxBitmapSize).build();
      synchronized (this.mLock)
      {
        this.mMetadata = paramMediaMetadataCompat;
        sendMetadata(paramMediaMetadataCompat);
        if (!this.mIsActive)
          return;
        Bundle localBundle;
        if (paramMediaMetadataCompat == null)
          localBundle = null;
        else
          localBundle = paramMediaMetadataCompat.getBundle();
        buildRccMetadata(localBundle).apply();
        return;
      }
    }

    public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      synchronized (this.mLock)
      {
        this.mState = paramPlaybackStateCompat;
        sendState(paramPlaybackStateCompat);
        if (!this.mIsActive)
          return;
        if (paramPlaybackStateCompat == null)
        {
          this.mRcc.setPlaybackState(0);
          this.mRcc.setTransportControlFlags(0);
        }
        else
        {
          setRccState(paramPlaybackStateCompat);
          this.mRcc.setTransportControlFlags(getRccTransportControlFlagsFromActions(paramPlaybackStateCompat.getActions()));
        }
        return;
      }
    }

    public void setPlaybackToLocal(int paramInt)
    {
      if (this.mVolumeProvider != null)
        this.mVolumeProvider.setCallback(null);
      this.mLocalStream = paramInt;
      this.mVolumeType = 1;
      ParcelableVolumeInfo localParcelableVolumeInfo = new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream));
      sendVolumeInfoChanged(localParcelableVolumeInfo);
    }

    public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
    {
      if (paramVolumeProviderCompat == null)
        throw new IllegalArgumentException("volumeProvider may not be null");
      if (this.mVolumeProvider != null)
        this.mVolumeProvider.setCallback(null);
      this.mVolumeType = 2;
      this.mVolumeProvider = paramVolumeProviderCompat;
      ParcelableVolumeInfo localParcelableVolumeInfo = new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume());
      sendVolumeInfoChanged(localParcelableVolumeInfo);
      paramVolumeProviderCompat.setCallback(this.mVolumeCallback);
    }

    public void setQueue(List<MediaSessionCompat.QueueItem> paramList)
    {
      this.mQueue = paramList;
      sendQueue(paramList);
    }

    public void setQueueTitle(CharSequence paramCharSequence)
    {
      this.mQueueTitle = paramCharSequence;
      sendQueueTitle(paramCharSequence);
    }

    public void setRatingType(int paramInt)
    {
      this.mRatingType = paramInt;
    }

    void setRccState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      this.mRcc.setPlaybackState(getRccStateFromState(paramPlaybackStateCompat.getState()));
    }

    public void setRepeatMode(int paramInt)
    {
      if (this.mRepeatMode != paramInt)
      {
        this.mRepeatMode = paramInt;
        sendRepeatMode(paramInt);
      }
    }

    public void setSessionActivity(PendingIntent paramPendingIntent)
    {
      synchronized (this.mLock)
      {
        this.mSessionActivity = paramPendingIntent;
        return;
      }
    }

    public void setShuffleMode(int paramInt)
    {
      if (this.mShuffleMode != paramInt)
      {
        this.mShuffleMode = paramInt;
        sendShuffleMode(paramInt);
      }
    }

    void setVolumeTo(int paramInt1, int paramInt2)
    {
      if (this.mVolumeType == 2)
      {
        if (this.mVolumeProvider != null)
          this.mVolumeProvider.onSetVolumeTo(paramInt1);
      }
      else
        this.mAudioManager.setStreamVolume(this.mLocalStream, paramInt1, paramInt2);
    }

    void unregisterMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      this.mAudioManager.unregisterMediaButtonEventReceiver(paramComponentName);
    }

    boolean update()
    {
      boolean bool = this.mIsActive;
      int i = 1;
      if (bool)
      {
        if ((!this.mIsMbrRegistered) && ((i & this.mFlags) != 0))
        {
          registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
          this.mIsMbrRegistered = i;
        }
        else if ((this.mIsMbrRegistered) && ((i & this.mFlags) == 0))
        {
          unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
          this.mIsMbrRegistered = false;
        }
        if ((!this.mIsRccRegistered) && ((0x2 & this.mFlags) != 0))
        {
          this.mAudioManager.registerRemoteControlClient(this.mRcc);
          this.mIsRccRegistered = i;
          break label215;
        }
        if ((this.mIsRccRegistered) && ((0x2 & this.mFlags) == 0))
        {
          this.mRcc.setPlaybackState(0);
          this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
          this.mIsRccRegistered = false;
        }
      }
      else
      {
        if (this.mIsMbrRegistered)
        {
          unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
          this.mIsMbrRegistered = false;
        }
        if (this.mIsRccRegistered)
        {
          this.mRcc.setPlaybackState(0);
          this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
          this.mIsRccRegistered = false;
        }
      }
      i = 0;
      label215: return i;
    }

    private static final class Command
    {
      public final String command;
      public final Bundle extras;
      public final ResultReceiver stub;

      public Command(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
      {
        this.command = paramString;
        this.extras = paramBundle;
        this.stub = paramResultReceiver;
      }
    }

    class MediaSessionStub extends IMediaSession.Stub
    {
      MediaSessionStub()
      {
      }

      public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        postToHandler(25, paramMediaDescriptionCompat);
      }

      public void addQueueItemAt(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
      {
        postToHandler(26, paramMediaDescriptionCompat, paramInt);
      }

      public void adjustVolume(int paramInt1, int paramInt2, String paramString)
      {
        MediaSessionCompat.MediaSessionImplBase.this.adjustVolume(paramInt1, paramInt2);
      }

      public void fastForward()
      {
        postToHandler(16);
      }

      public Bundle getExtras()
      {
        synchronized (MediaSessionCompat.MediaSessionImplBase.this.mLock)
        {
          Bundle localBundle = MediaSessionCompat.MediaSessionImplBase.this.mExtras;
          return localBundle;
        }
      }

      public long getFlags()
      {
        synchronized (MediaSessionCompat.MediaSessionImplBase.this.mLock)
        {
          long l = MediaSessionCompat.MediaSessionImplBase.this.mFlags;
          return l;
        }
      }

      public PendingIntent getLaunchPendingIntent()
      {
        synchronized (MediaSessionCompat.MediaSessionImplBase.this.mLock)
        {
          PendingIntent localPendingIntent = MediaSessionCompat.MediaSessionImplBase.this.mSessionActivity;
          return localPendingIntent;
        }
      }

      public MediaMetadataCompat getMetadata()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mMetadata;
      }

      public String getPackageName()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mPackageName;
      }

      public PlaybackStateCompat getPlaybackState()
      {
        synchronized (MediaSessionCompat.MediaSessionImplBase.this.mLock)
        {
          PlaybackStateCompat localPlaybackStateCompat = MediaSessionCompat.MediaSessionImplBase.this.mState;
          MediaMetadataCompat localMediaMetadataCompat = MediaSessionCompat.MediaSessionImplBase.this.mMetadata;
          return MediaSessionCompat.getStateWithUpdatedPosition(localPlaybackStateCompat, localMediaMetadataCompat);
        }
      }

      public List<MediaSessionCompat.QueueItem> getQueue()
      {
        synchronized (MediaSessionCompat.MediaSessionImplBase.this.mLock)
        {
          List localList = MediaSessionCompat.MediaSessionImplBase.this.mQueue;
          return localList;
        }
      }

      public CharSequence getQueueTitle()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mQueueTitle;
      }

      public int getRatingType()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mRatingType;
      }

      public int getRepeatMode()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mRepeatMode;
      }

      public int getShuffleMode()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mShuffleMode;
      }

      public String getTag()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mTag;
      }

      public ParcelableVolumeInfo getVolumeAttributes()
      {
        synchronized (MediaSessionCompat.MediaSessionImplBase.this.mLock)
        {
          int i = MediaSessionCompat.MediaSessionImplBase.this.mVolumeType;
          int j = MediaSessionCompat.MediaSessionImplBase.this.mLocalStream;
          VolumeProviderCompat localVolumeProviderCompat = MediaSessionCompat.MediaSessionImplBase.this.mVolumeProvider;
          int i1;
          int n;
          int i2;
          if (i == 2)
          {
            int i3 = localVolumeProviderCompat.getVolumeControl();
            int i4 = localVolumeProviderCompat.getMaxVolume();
            i1 = localVolumeProviderCompat.getCurrentVolume();
            n = i4;
            i2 = i3;
          }
          else
          {
            int k = MediaSessionCompat.MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(j);
            int m = MediaSessionCompat.MediaSessionImplBase.this.mAudioManager.getStreamVolume(j);
            n = k;
            i1 = m;
            i2 = 2;
          }
          ParcelableVolumeInfo localParcelableVolumeInfo = new ParcelableVolumeInfo(i, j, i2, n, i1);
          return localParcelableVolumeInfo;
        }
      }

      public boolean isCaptioningEnabled()
      {
        return MediaSessionCompat.MediaSessionImplBase.this.mCaptioningEnabled;
      }

      public boolean isShuffleModeEnabledRemoved()
      {
        return false;
      }

      public boolean isTransportControlEnabled()
      {
        boolean bool;
        if ((0x2 & MediaSessionCompat.MediaSessionImplBase.this.mFlags) != 0)
          bool = true;
        else
          bool = false;
        return bool;
      }

      public void next()
      {
        postToHandler(14);
      }

      public void pause()
      {
        postToHandler(12);
      }

      public void play()
      {
        postToHandler(7);
      }

      public void playFromMediaId(String paramString, Bundle paramBundle)
      {
        postToHandler(8, paramString, paramBundle);
      }

      public void playFromSearch(String paramString, Bundle paramBundle)
      {
        postToHandler(9, paramString, paramBundle);
      }

      public void playFromUri(Uri paramUri, Bundle paramBundle)
      {
        postToHandler(10, paramUri, paramBundle);
      }

      void postToHandler(int paramInt)
      {
        MediaSessionCompat.MediaSessionImplBase.this.postToHandler(paramInt, 0, 0, null, null);
      }

      void postToHandler(int paramInt1, int paramInt2)
      {
        MediaSessionCompat.MediaSessionImplBase.this.postToHandler(paramInt1, paramInt2, 0, null, null);
      }

      void postToHandler(int paramInt, Object paramObject)
      {
        MediaSessionCompat.MediaSessionImplBase.this.postToHandler(paramInt, 0, 0, paramObject, null);
      }

      void postToHandler(int paramInt1, Object paramObject, int paramInt2)
      {
        MediaSessionCompat.MediaSessionImplBase.this.postToHandler(paramInt1, paramInt2, 0, paramObject, null);
      }

      void postToHandler(int paramInt, Object paramObject, Bundle paramBundle)
      {
        MediaSessionCompat.MediaSessionImplBase.this.postToHandler(paramInt, 0, 0, paramObject, paramBundle);
      }

      public void prepare()
      {
        postToHandler(3);
      }

      public void prepareFromMediaId(String paramString, Bundle paramBundle)
      {
        postToHandler(4, paramString, paramBundle);
      }

      public void prepareFromSearch(String paramString, Bundle paramBundle)
      {
        postToHandler(5, paramString, paramBundle);
      }

      public void prepareFromUri(Uri paramUri, Bundle paramBundle)
      {
        postToHandler(6, paramUri, paramBundle);
      }

      public void previous()
      {
        postToHandler(15);
      }

      public void rate(RatingCompat paramRatingCompat)
      {
        postToHandler(19, paramRatingCompat);
      }

      public void rateWithExtras(RatingCompat paramRatingCompat, Bundle paramBundle)
      {
        postToHandler(31, paramRatingCompat, paramBundle);
      }

      public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        if (MediaSessionCompat.MediaSessionImplBase.this.mDestroyed)
        {
          try
          {
            paramIMediaControllerCallback.onSessionDestroyed();
          }
          catch (Exception localException)
          {
          }
          return;
        }
        MediaSessionManager.RemoteUserInfo localRemoteUserInfo = new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", getCallingPid(), getCallingUid());
        MediaSessionCompat.MediaSessionImplBase.this.mControllerCallbacks.register(paramIMediaControllerCallback, localRemoteUserInfo);
      }

      public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        postToHandler(27, paramMediaDescriptionCompat);
      }

      public void removeQueueItemAt(int paramInt)
      {
        postToHandler(28, paramInt);
      }

      public void rewind()
      {
        postToHandler(17);
      }

      public void seekTo(long paramLong)
      {
        postToHandler(18, Long.valueOf(paramLong));
      }

      public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
      {
        postToHandler(1, new MediaSessionCompat.MediaSessionImplBase.Command(paramString, paramBundle, paramResultReceiverWrapper.mResultReceiver));
      }

      public void sendCustomAction(String paramString, Bundle paramBundle)
      {
        postToHandler(20, paramString, paramBundle);
      }

      public boolean sendMediaButton(KeyEvent paramKeyEvent)
      {
        int i = MediaSessionCompat.MediaSessionImplBase.this.mFlags;
        int j = 1;
        if ((i & j) == 0)
          j = 0;
        if (j != 0)
          postToHandler(21, paramKeyEvent);
        return j;
      }

      public void setCaptioningEnabled(boolean paramBoolean)
      {
        postToHandler(29, Boolean.valueOf(paramBoolean));
      }

      public void setRepeatMode(int paramInt)
      {
        postToHandler(23, paramInt);
      }

      public void setShuffleMode(int paramInt)
      {
        postToHandler(30, paramInt);
      }

      public void setShuffleModeEnabledRemoved(boolean paramBoolean)
      {
      }

      public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
      {
        MediaSessionCompat.MediaSessionImplBase.this.setVolumeTo(paramInt1, paramInt2);
      }

      public void skipToQueueItem(long paramLong)
      {
        postToHandler(11, Long.valueOf(paramLong));
      }

      public void stop()
      {
        postToHandler(13);
      }

      public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        MediaSessionCompat.MediaSessionImplBase.this.mControllerCallbacks.unregister(paramIMediaControllerCallback);
      }
    }

    class MessageHandler extends Handler
    {
      private static final int KEYCODE_MEDIA_PAUSE = 127;
      private static final int KEYCODE_MEDIA_PLAY = 126;
      private static final int MSG_ADD_QUEUE_ITEM = 25;
      private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
      private static final int MSG_ADJUST_VOLUME = 2;
      private static final int MSG_COMMAND = 1;
      private static final int MSG_CUSTOM_ACTION = 20;
      private static final int MSG_FAST_FORWARD = 16;
      private static final int MSG_MEDIA_BUTTON = 21;
      private static final int MSG_NEXT = 14;
      private static final int MSG_PAUSE = 12;
      private static final int MSG_PLAY = 7;
      private static final int MSG_PLAY_MEDIA_ID = 8;
      private static final int MSG_PLAY_SEARCH = 9;
      private static final int MSG_PLAY_URI = 10;
      private static final int MSG_PREPARE = 3;
      private static final int MSG_PREPARE_MEDIA_ID = 4;
      private static final int MSG_PREPARE_SEARCH = 5;
      private static final int MSG_PREPARE_URI = 6;
      private static final int MSG_PREVIOUS = 15;
      private static final int MSG_RATE = 19;
      private static final int MSG_RATE_EXTRA = 31;
      private static final int MSG_REMOVE_QUEUE_ITEM = 27;
      private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
      private static final int MSG_REWIND = 17;
      private static final int MSG_SEEK_TO = 18;
      private static final int MSG_SET_CAPTIONING_ENABLED = 29;
      private static final int MSG_SET_REPEAT_MODE = 23;
      private static final int MSG_SET_SHUFFLE_MODE = 30;
      private static final int MSG_SET_VOLUME = 22;
      private static final int MSG_SKIP_TO_ITEM = 11;
      private static final int MSG_STOP = 13;

      public MessageHandler(Looper arg2)
      {
        super();
      }

      private void onMediaButtonEvent(KeyEvent paramKeyEvent, MediaSessionCompat.Callback paramCallback)
      {
        if ((paramKeyEvent != null) && (paramKeyEvent.getAction() == 0))
        {
          long l;
          if (MediaSessionCompat.MediaSessionImplBase.this.mState == null)
            l = 0L;
          else
            l = MediaSessionCompat.MediaSessionImplBase.this.mState.getActions();
          int i = paramKeyEvent.getKeyCode();
          if (i != 79)
            switch (i)
            {
            default:
              switch (i)
              {
              default:
                break;
              case 127:
                if ((l & 0x2) == 0L)
                  break;
                paramCallback.onPause();
                break;
              case 126:
                if ((l & 0x4) == 0L)
                  break;
                paramCallback.onPlay();
              }
              break;
            case 90:
              if ((l & 0x40) == 0L)
                break;
              paramCallback.onFastForward();
              break;
            case 89:
              if ((l & 0x8) == 0L)
                break;
              paramCallback.onRewind();
              break;
            case 88:
              if ((l & 0x10) == 0L)
                break;
              paramCallback.onSkipToPrevious();
              break;
            case 87:
              if ((l & 0x20) == 0L)
                break;
              paramCallback.onSkipToNext();
              break;
            case 86:
              if ((l & 1L) == 0L)
                break;
              paramCallback.onStop();
              break;
            case 85:
            }
          else
            Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
          return;
        }
      }

      public void handleMessage(Message paramMessage)
      {
        MediaSessionCompat.Callback localCallback = MediaSessionCompat.MediaSessionImplBase.this.mCallback;
        if (localCallback == null)
          return;
        Bundle localBundle1 = paramMessage.getData();
        MediaSessionCompat.ensureClassLoader(localBundle1);
        MediaSessionCompat.MediaSessionImplBase.this.setCurrentControllerInfo(new MediaSessionManager.RemoteUserInfo(localBundle1.getString("data_calling_pkg"), localBundle1.getInt("data_calling_pid"), localBundle1.getInt("data_calling_uid")));
        Bundle localBundle2 = localBundle1.getBundle("data_extras");
        MediaSessionCompat.ensureClassLoader(localBundle2);
        while (true)
        {
          try
          {
            switch (paramMessage.what)
            {
            case 31:
              localCallback.onSetRating((RatingCompat)paramMessage.obj, localBundle2);
              break;
            case 30:
              localCallback.onSetShuffleMode(paramMessage.arg1);
              break;
            case 29:
              localCallback.onSetCaptioningEnabled(((Boolean)paramMessage.obj).booleanValue());
              break;
            case 28:
              if (MediaSessionCompat.MediaSessionImplBase.this.mQueue != null)
              {
                if ((paramMessage.arg1 < 0) || (paramMessage.arg1 >= MediaSessionCompat.MediaSessionImplBase.this.mQueue.size()))
                  break label736;
                localQueueItem = (MediaSessionCompat.QueueItem)MediaSessionCompat.MediaSessionImplBase.this.mQueue.get(paramMessage.arg1);
                if (localQueueItem != null)
                  localCallback.onRemoveQueueItem(localQueueItem.getDescription());
              }
              break;
            case 27:
              localCallback.onRemoveQueueItem((MediaDescriptionCompat)paramMessage.obj);
              break;
            case 26:
              localCallback.onAddQueueItem((MediaDescriptionCompat)paramMessage.obj, paramMessage.arg1);
              break;
            case 25:
              localCallback.onAddQueueItem((MediaDescriptionCompat)paramMessage.obj);
              break;
            case 23:
              localCallback.onSetRepeatMode(paramMessage.arg1);
              break;
            case 22:
              MediaSessionCompat.MediaSessionImplBase.this.setVolumeTo(paramMessage.arg1, 0);
              break;
            case 21:
              KeyEvent localKeyEvent = (KeyEvent)paramMessage.obj;
              Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
              localIntent.putExtra("android.intent.extra.KEY_EVENT", localKeyEvent);
              if (!localCallback.onMediaButtonEvent(localIntent))
                onMediaButtonEvent(localKeyEvent, localCallback);
              break;
            case 20:
              localCallback.onCustomAction((String)paramMessage.obj, localBundle2);
              break;
            case 19:
              localCallback.onSetRating((RatingCompat)paramMessage.obj);
              break;
            case 18:
              localCallback.onSeekTo(((Long)paramMessage.obj).longValue());
              break;
            case 17:
              localCallback.onRewind();
              break;
            case 16:
              localCallback.onFastForward();
              break;
            case 15:
              localCallback.onSkipToPrevious();
              break;
            case 14:
              localCallback.onSkipToNext();
              break;
            case 13:
              localCallback.onStop();
              break;
            case 12:
              localCallback.onPause();
              break;
            case 11:
              localCallback.onSkipToQueueItem(((Long)paramMessage.obj).longValue());
              break;
            case 10:
              localCallback.onPlayFromUri((Uri)paramMessage.obj, localBundle2);
              break;
            case 9:
              localCallback.onPlayFromSearch((String)paramMessage.obj, localBundle2);
              break;
            case 8:
              localCallback.onPlayFromMediaId((String)paramMessage.obj, localBundle2);
              break;
            case 7:
              localCallback.onPlay();
              break;
            case 6:
              localCallback.onPrepareFromUri((Uri)paramMessage.obj, localBundle2);
              break;
            case 5:
              localCallback.onPrepareFromSearch((String)paramMessage.obj, localBundle2);
              break;
            case 4:
              localCallback.onPrepareFromMediaId((String)paramMessage.obj, localBundle2);
              break;
            case 3:
              localCallback.onPrepare();
              break;
            case 2:
              MediaSessionCompat.MediaSessionImplBase.this.adjustVolume(paramMessage.arg1, 0);
              break;
            case 1:
              MediaSessionCompat.MediaSessionImplBase.Command localCommand = (MediaSessionCompat.MediaSessionImplBase.Command)paramMessage.obj;
              localCallback.onCommand(localCommand.command, localCommand.extras, localCommand.stub);
              return;
            case 24:
            }
          }
          finally
          {
            MediaSessionCompat.MediaSessionImplBase.this.setCurrentControllerInfo(null);
          }
          continue;
          label736: MediaSessionCompat.QueueItem localQueueItem = null;
        }
      }
    }
  }

  public static abstract interface OnActiveChangeListener
  {
    public abstract void onActiveChanged();
  }

  public static final class QueueItem
    implements Parcelable
  {
    public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator()
    {
      public MediaSessionCompat.QueueItem createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaSessionCompat.QueueItem(paramAnonymousParcel);
      }

      public MediaSessionCompat.QueueItem[] newArray(int paramAnonymousInt)
      {
        return new MediaSessionCompat.QueueItem[paramAnonymousInt];
      }
    };
    public static final int UNKNOWN_ID = -1;
    private final MediaDescriptionCompat mDescription;
    private final long mId;
    private Object mItem;

    QueueItem(Parcel paramParcel)
    {
      this.mDescription = ((MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel));
      this.mId = paramParcel.readLong();
    }

    public QueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, long paramLong)
    {
      this(null, paramMediaDescriptionCompat, paramLong);
    }

    private QueueItem(Object paramObject, MediaDescriptionCompat paramMediaDescriptionCompat, long paramLong)
    {
      if (paramMediaDescriptionCompat == null)
        throw new IllegalArgumentException("Description cannot be null.");
      if (paramLong == -1L)
        throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
      this.mDescription = paramMediaDescriptionCompat;
      this.mId = paramLong;
      this.mItem = paramObject;
    }

    public static QueueItem fromQueueItem(Object paramObject)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
        return new QueueItem(paramObject, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(paramObject)), MediaSessionCompatApi21.QueueItem.getQueueId(paramObject));
      return null;
    }

    public static List<QueueItem> fromQueueItemList(List<?> paramList)
    {
      if ((paramList != null) && (Build.VERSION.SDK_INT >= 21))
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          localArrayList.add(fromQueueItem(localIterator.next()));
        return localArrayList;
      }
      return null;
    }

    public int describeContents()
    {
      return 0;
    }

    public MediaDescriptionCompat getDescription()
    {
      return this.mDescription;
    }

    public long getQueueId()
    {
      return this.mId;
    }

    public Object getQueueItem()
    {
      if ((this.mItem == null) && (Build.VERSION.SDK_INT >= 21))
      {
        this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
        return this.mItem;
      }
      return this.mItem;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("MediaSession.QueueItem {Description=");
      localStringBuilder.append(this.mDescription);
      localStringBuilder.append(", Id=");
      localStringBuilder.append(this.mId);
      localStringBuilder.append(" }");
      return localStringBuilder.toString();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      this.mDescription.writeToParcel(paramParcel, paramInt);
      paramParcel.writeLong(this.mId);
    }
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final class ResultReceiverWrapper
    implements Parcelable
  {
    public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new Parcelable.Creator()
    {
      public MediaSessionCompat.ResultReceiverWrapper createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaSessionCompat.ResultReceiverWrapper(paramAnonymousParcel);
      }

      public MediaSessionCompat.ResultReceiverWrapper[] newArray(int paramAnonymousInt)
      {
        return new MediaSessionCompat.ResultReceiverWrapper[paramAnonymousInt];
      }
    };
    ResultReceiver mResultReceiver;

    ResultReceiverWrapper(Parcel paramParcel)
    {
      this.mResultReceiver = ((ResultReceiver)ResultReceiver.CREATOR.createFromParcel(paramParcel));
    }

    public ResultReceiverWrapper(ResultReceiver paramResultReceiver)
    {
      this.mResultReceiver = paramResultReceiver;
    }

    public int describeContents()
    {
      return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      this.mResultReceiver.writeToParcel(paramParcel, paramInt);
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface SessionFlags
  {
  }

  public static final class Token
    implements Parcelable
  {
    public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator()
    {
      public MediaSessionCompat.Token createFromParcel(Parcel paramAnonymousParcel)
      {
        Object localObject;
        if (Build.VERSION.SDK_INT >= 21)
          localObject = paramAnonymousParcel.readParcelable(null);
        else
          localObject = paramAnonymousParcel.readStrongBinder();
        return new MediaSessionCompat.Token(localObject);
      }

      public MediaSessionCompat.Token[] newArray(int paramAnonymousInt)
      {
        return new MediaSessionCompat.Token[paramAnonymousInt];
      }
    };
    private IMediaSession mExtraBinder;
    private final Object mInner;
    private Bundle mSessionToken2Bundle;

    Token(Object paramObject)
    {
      this(paramObject, null, null);
    }

    Token(Object paramObject, IMediaSession paramIMediaSession)
    {
      this(paramObject, paramIMediaSession, null);
    }

    Token(Object paramObject, IMediaSession paramIMediaSession, Bundle paramBundle)
    {
      this.mInner = paramObject;
      this.mExtraBinder = paramIMediaSession;
      this.mSessionToken2Bundle = paramBundle;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static Token fromBundle(Bundle paramBundle)
    {
      if (paramBundle == null)
        return null;
      IMediaSession localIMediaSession = IMediaSession.Stub.asInterface(BundleCompat.getBinder(paramBundle, "android.support.v4.media.session.EXTRA_BINDER"));
      Bundle localBundle = paramBundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE");
      Token localToken1 = (Token)paramBundle.getParcelable("android.support.v4.media.session.TOKEN");
      Token localToken2;
      if (localToken1 == null)
        localToken2 = null;
      else
        localToken2 = new Token(localToken1.mInner, localIMediaSession, localBundle);
      return localToken2;
    }

    public static Token fromToken(Object paramObject)
    {
      return fromToken(paramObject, null);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static Token fromToken(Object paramObject, IMediaSession paramIMediaSession)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
        return new Token(MediaSessionCompatApi21.verifyToken(paramObject), paramIMediaSession);
      return null;
    }

    public int describeContents()
    {
      return 0;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject)
        return bool;
      if (!(paramObject instanceof Token))
        return false;
      Token localToken = (Token)paramObject;
      if (this.mInner == null)
      {
        if (localToken.mInner != null)
          bool = false;
        return bool;
      }
      if (localToken.mInner == null)
        return false;
      return this.mInner.equals(localToken.mInner);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public IMediaSession getExtraBinder()
    {
      return this.mExtraBinder;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle getSessionToken2Bundle()
    {
      return this.mSessionToken2Bundle;
    }

    public Object getToken()
    {
      return this.mInner;
    }

    public int hashCode()
    {
      if (this.mInner == null)
        return 0;
      return this.mInner.hashCode();
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void setExtraBinder(IMediaSession paramIMediaSession)
    {
      this.mExtraBinder = paramIMediaSession;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void setSessionToken2Bundle(Bundle paramBundle)
    {
      this.mSessionToken2Bundle = paramBundle;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle toBundle()
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.TOKEN", this);
      if (this.mExtraBinder != null)
        BundleCompat.putBinder(localBundle, "android.support.v4.media.session.EXTRA_BINDER", this.mExtraBinder.asBinder());
      if (this.mSessionToken2Bundle != null)
        localBundle.putBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE", this.mSessionToken2Bundle);
      return localBundle;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      if (Build.VERSION.SDK_INT >= 21)
        paramParcel.writeParcelable((Parcelable)this.mInner, paramInt);
      else
        paramParcel.writeStrongBinder((IBinder)this.mInner);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat
 * JD-Core Version:    0.6.1
 */