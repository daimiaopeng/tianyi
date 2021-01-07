package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.SupportActivity;
import android.support.v4.app.SupportActivity.ExtraData;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class MediaControllerCompat
{

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
  static final String TAG = "MediaControllerCompat";
  private final MediaControllerImpl mImpl;
  private final HashSet<Callback> mRegisteredCallbacks = new HashSet();
  private final MediaSessionCompat.Token mToken;

  public MediaControllerCompat(Context paramContext, @NonNull MediaSessionCompat.Token paramToken)
  {
    if (paramToken == null)
      throw new IllegalArgumentException("sessionToken must not be null");
    this.mToken = paramToken;
    if (Build.VERSION.SDK_INT >= 24)
      this.mImpl = new MediaControllerImplApi24(paramContext, paramToken);
    else if (Build.VERSION.SDK_INT >= 23)
      this.mImpl = new MediaControllerImplApi23(paramContext, paramToken);
    else if (Build.VERSION.SDK_INT >= 21)
      this.mImpl = new MediaControllerImplApi21(paramContext, paramToken);
    else
      this.mImpl = new MediaControllerImplBase(paramToken);
  }

  public MediaControllerCompat(Context paramContext, @NonNull MediaSessionCompat paramMediaSessionCompat)
  {
    if (paramMediaSessionCompat == null)
      throw new IllegalArgumentException("session must not be null");
    this.mToken = paramMediaSessionCompat.getSessionToken();
    Object localObject1 = null;
    Object localObject2;
    try
    {
      if (Build.VERSION.SDK_INT >= 24)
      {
        localObject2 = new MediaControllerImplApi24(paramContext, this.mToken);
      }
      else if (Build.VERSION.SDK_INT >= 23)
      {
        localObject2 = new MediaControllerImplApi23(paramContext, this.mToken);
      }
      else if (Build.VERSION.SDK_INT >= 21)
      {
        localObject2 = new MediaControllerImplApi21(paramContext, this.mToken);
      }
      else
      {
        MediaControllerImplBase localMediaControllerImplBase = new MediaControllerImplBase(this.mToken);
        localObject1 = localMediaControllerImplBase;
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("MediaControllerCompat", "Failed to create MediaControllerImpl.", localRemoteException);
    }
    while (true)
    {
      this.mImpl = localObject1;
      return;
      localObject1 = localObject2;
    }
  }

  public static MediaControllerCompat getMediaController(@NonNull Activity paramActivity)
  {
    if ((paramActivity instanceof SupportActivity))
    {
      MediaControllerExtraData localMediaControllerExtraData = (MediaControllerExtraData)((SupportActivity)paramActivity).getExtraData(MediaControllerExtraData.class);
      MediaControllerCompat localMediaControllerCompat2 = null;
      if (localMediaControllerExtraData != null)
        localMediaControllerCompat2 = localMediaControllerExtraData.getMediaController();
      return localMediaControllerCompat2;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      Object localObject1 = MediaControllerCompatApi21.getMediaController(paramActivity);
      if (localObject1 == null)
        return null;
      Object localObject2 = MediaControllerCompatApi21.getSessionToken(localObject1);
      try
      {
        MediaControllerCompat localMediaControllerCompat1 = new MediaControllerCompat(paramActivity, MediaSessionCompat.Token.fromToken(localObject2));
        return localMediaControllerCompat1;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getMediaController.", localRemoteException);
      }
    }
    return null;
  }

  public static void setMediaController(@NonNull Activity paramActivity, MediaControllerCompat paramMediaControllerCompat)
  {
    if ((paramActivity instanceof SupportActivity))
      ((SupportActivity)paramActivity).putExtraData(new MediaControllerExtraData(paramMediaControllerCompat));
    if (Build.VERSION.SDK_INT >= 21)
    {
      Object localObject = null;
      if (paramMediaControllerCompat != null)
        localObject = MediaControllerCompatApi21.fromToken(paramActivity, paramMediaControllerCompat.getSessionToken().getToken());
      MediaControllerCompatApi21.setMediaController(paramActivity, localObject);
    }
  }

  static void validateCustomAction(String paramString, Bundle paramBundle)
  {
    if (paramString == null)
      return;
    int i = -1;
    int j = paramString.hashCode();
    if (j != -1348483723)
    {
      if ((j == 503011406) && (paramString.equals("android.support.v4.media.session.action.UNFOLLOW")))
        i = 1;
    }
    else if (paramString.equals("android.support.v4.media.session.action.FOLLOW"))
      i = 0;
    switch (i)
    {
    default:
      break;
    case 0:
    case 1:
      if ((paramBundle == null) || (!paramBundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(".");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      break;
    }
  }

  public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
  {
    this.mImpl.addQueueItem(paramMediaDescriptionCompat);
  }

  public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
  {
    this.mImpl.addQueueItem(paramMediaDescriptionCompat, paramInt);
  }

  public void adjustVolume(int paramInt1, int paramInt2)
  {
    this.mImpl.adjustVolume(paramInt1, paramInt2);
  }

  public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == null)
      throw new IllegalArgumentException("KeyEvent may not be null");
    return this.mImpl.dispatchMediaButtonEvent(paramKeyEvent);
  }

  public Bundle getExtras()
  {
    return this.mImpl.getExtras();
  }

  public long getFlags()
  {
    return this.mImpl.getFlags();
  }

  public Object getMediaController()
  {
    return this.mImpl.getMediaController();
  }

  public MediaMetadataCompat getMetadata()
  {
    return this.mImpl.getMetadata();
  }

  public String getPackageName()
  {
    return this.mImpl.getPackageName();
  }

  public PlaybackInfo getPlaybackInfo()
  {
    return this.mImpl.getPlaybackInfo();
  }

  public PlaybackStateCompat getPlaybackState()
  {
    return this.mImpl.getPlaybackState();
  }

  public List<MediaSessionCompat.QueueItem> getQueue()
  {
    return this.mImpl.getQueue();
  }

  public CharSequence getQueueTitle()
  {
    return this.mImpl.getQueueTitle();
  }

  public int getRatingType()
  {
    return this.mImpl.getRatingType();
  }

  public int getRepeatMode()
  {
    return this.mImpl.getRepeatMode();
  }

  public PendingIntent getSessionActivity()
  {
    return this.mImpl.getSessionActivity();
  }

  public MediaSessionCompat.Token getSessionToken()
  {
    return this.mToken;
  }

  @Nullable
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public Bundle getSessionToken2Bundle()
  {
    return this.mToken.getSessionToken2Bundle();
  }

  public int getShuffleMode()
  {
    return this.mImpl.getShuffleMode();
  }

  public TransportControls getTransportControls()
  {
    return this.mImpl.getTransportControls();
  }

  public boolean isCaptioningEnabled()
  {
    return this.mImpl.isCaptioningEnabled();
  }

  public boolean isSessionReady()
  {
    return this.mImpl.isSessionReady();
  }

  public void registerCallback(@NonNull Callback paramCallback)
  {
    registerCallback(paramCallback, null);
  }

  public void registerCallback(@NonNull Callback paramCallback, Handler paramHandler)
  {
    if (paramCallback == null)
      throw new IllegalArgumentException("callback must not be null");
    if (paramHandler == null)
      paramHandler = new Handler();
    paramCallback.setHandler(paramHandler);
    this.mImpl.registerCallback(paramCallback, paramHandler);
    this.mRegisteredCallbacks.add(paramCallback);
  }

  public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
  {
    this.mImpl.removeQueueItem(paramMediaDescriptionCompat);
  }

  @Deprecated
  public void removeQueueItemAt(int paramInt)
  {
    List localList = getQueue();
    if ((localList != null) && (paramInt >= 0) && (paramInt < localList.size()))
    {
      MediaSessionCompat.QueueItem localQueueItem = (MediaSessionCompat.QueueItem)localList.get(paramInt);
      if (localQueueItem != null)
        removeQueueItem(localQueueItem.getDescription());
    }
  }

  public void sendCommand(@NonNull String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("command must neither be null nor empty");
    this.mImpl.sendCommand(paramString, paramBundle, paramResultReceiver);
  }

  public void setVolumeTo(int paramInt1, int paramInt2)
  {
    this.mImpl.setVolumeTo(paramInt1, paramInt2);
  }

  public void unregisterCallback(@NonNull Callback paramCallback)
  {
    if (paramCallback == null)
      throw new IllegalArgumentException("callback must not be null");
    try
    {
      this.mRegisteredCallbacks.remove(paramCallback);
      this.mImpl.unregisterCallback(paramCallback);
      return;
    }
    finally
    {
      paramCallback.setHandler(null);
    }
  }

  public static abstract class Callback
    implements IBinder.DeathRecipient
  {
    final Object mCallbackObj;
    MessageHandler mHandler;
    IMediaControllerCallback mIControllerCallback;

    public Callback()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        this.mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21(this));
      }
      else
      {
        StubCompat localStubCompat = new StubCompat(this);
        this.mIControllerCallback = localStubCompat;
        this.mCallbackObj = localStubCompat;
      }
    }

    public void binderDied()
    {
      postToHandler(8, null, null);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
    public IMediaControllerCallback getIControllerCallback()
    {
      return this.mIControllerCallback;
    }

    public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo paramPlaybackInfo)
    {
    }

    public void onCaptioningEnabledChanged(boolean paramBoolean)
    {
    }

    public void onExtrasChanged(Bundle paramBundle)
    {
    }

    public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
    {
    }

    public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat)
    {
    }

    public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
    {
    }

    public void onQueueTitleChanged(CharSequence paramCharSequence)
    {
    }

    public void onRepeatModeChanged(int paramInt)
    {
    }

    public void onSessionDestroyed()
    {
    }

    public void onSessionEvent(String paramString, Bundle paramBundle)
    {
    }

    public void onSessionReady()
    {
    }

    public void onShuffleModeChanged(int paramInt)
    {
    }

    void postToHandler(int paramInt, Object paramObject, Bundle paramBundle)
    {
      if (this.mHandler != null)
      {
        Message localMessage = this.mHandler.obtainMessage(paramInt, paramObject);
        localMessage.setData(paramBundle);
        localMessage.sendToTarget();
      }
    }

    void setHandler(Handler paramHandler)
    {
      if (paramHandler == null)
      {
        if (this.mHandler != null)
        {
          this.mHandler.mRegistered = false;
          this.mHandler.removeCallbacksAndMessages(null);
          this.mHandler = null;
        }
      }
      else
      {
        this.mHandler = new MessageHandler(paramHandler.getLooper());
        this.mHandler.mRegistered = true;
      }
    }

    private class MessageHandler extends Handler
    {
      private static final int MSG_DESTROYED = 8;
      private static final int MSG_EVENT = 1;
      private static final int MSG_SESSION_READY = 13;
      private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
      private static final int MSG_UPDATE_EXTRAS = 7;
      private static final int MSG_UPDATE_METADATA = 3;
      private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
      private static final int MSG_UPDATE_QUEUE = 5;
      private static final int MSG_UPDATE_QUEUE_TITLE = 6;
      private static final int MSG_UPDATE_REPEAT_MODE = 9;
      private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
      private static final int MSG_UPDATE_VOLUME = 4;
      boolean mRegistered = false;

      MessageHandler(Looper arg2)
      {
        super();
      }

      public void handleMessage(Message paramMessage)
      {
        if (!this.mRegistered)
          return;
        switch (paramMessage.what)
        {
        case 10:
        default:
          break;
        case 13:
          MediaControllerCompat.Callback.this.onSessionReady();
          break;
        case 12:
          MediaControllerCompat.Callback.this.onShuffleModeChanged(((Integer)paramMessage.obj).intValue());
          break;
        case 11:
          MediaControllerCompat.Callback.this.onCaptioningEnabledChanged(((Boolean)paramMessage.obj).booleanValue());
          break;
        case 9:
          MediaControllerCompat.Callback.this.onRepeatModeChanged(((Integer)paramMessage.obj).intValue());
          break;
        case 8:
          MediaControllerCompat.Callback.this.onSessionDestroyed();
          break;
        case 7:
          Bundle localBundle2 = (Bundle)paramMessage.obj;
          MediaSessionCompat.ensureClassLoader(localBundle2);
          MediaControllerCompat.Callback.this.onExtrasChanged(localBundle2);
          break;
        case 6:
          MediaControllerCompat.Callback.this.onQueueTitleChanged((CharSequence)paramMessage.obj);
          break;
        case 5:
          MediaControllerCompat.Callback.this.onQueueChanged((List)paramMessage.obj);
          break;
        case 4:
          MediaControllerCompat.Callback.this.onAudioInfoChanged((MediaControllerCompat.PlaybackInfo)paramMessage.obj);
          break;
        case 3:
          MediaControllerCompat.Callback.this.onMetadataChanged((MediaMetadataCompat)paramMessage.obj);
          break;
        case 2:
          MediaControllerCompat.Callback.this.onPlaybackStateChanged((PlaybackStateCompat)paramMessage.obj);
          break;
        case 1:
          Bundle localBundle1 = paramMessage.getData();
          MediaSessionCompat.ensureClassLoader(localBundle1);
          MediaControllerCompat.Callback.this.onSessionEvent((String)paramMessage.obj, localBundle1);
        }
      }
    }

    private static class StubApi21
      implements MediaControllerCompatApi21.Callback
    {
      private final WeakReference<MediaControllerCompat.Callback> mCallback;

      StubApi21(MediaControllerCompat.Callback paramCallback)
      {
        this.mCallback = new WeakReference(paramCallback);
      }

      public void onAudioInfoChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
        {
          MediaControllerCompat.PlaybackInfo localPlaybackInfo = new MediaControllerCompat.PlaybackInfo(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
          localCallback.onAudioInfoChanged(localPlaybackInfo);
        }
      }

      public void onExtrasChanged(Bundle paramBundle)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.onExtrasChanged(paramBundle);
      }

      public void onMetadataChanged(Object paramObject)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(paramObject));
      }

      public void onPlaybackStateChanged(Object paramObject)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if ((localCallback != null) && (localCallback.mIControllerCallback == null))
          localCallback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(paramObject));
      }

      public void onQueueChanged(List<?> paramList)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(paramList));
      }

      public void onQueueTitleChanged(CharSequence paramCharSequence)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.onQueueTitleChanged(paramCharSequence);
      }

      public void onSessionDestroyed()
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.onSessionDestroyed();
      }

      public void onSessionEvent(String paramString, Bundle paramBundle)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if ((localCallback != null) && ((localCallback.mIControllerCallback == null) || (Build.VERSION.SDK_INT >= 23)))
          localCallback.onSessionEvent(paramString, paramBundle);
      }
    }

    private static class StubCompat extends IMediaControllerCallback.Stub
    {
      private final WeakReference<MediaControllerCompat.Callback> mCallback;

      StubCompat(MediaControllerCompat.Callback paramCallback)
      {
        this.mCallback = new WeakReference(paramCallback);
      }

      public void onCaptioningEnabledChanged(boolean paramBoolean)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(11, Boolean.valueOf(paramBoolean), null);
      }

      public void onEvent(String paramString, Bundle paramBundle)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(1, paramString, paramBundle);
      }

      public void onExtrasChanged(Bundle paramBundle)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(7, paramBundle, null);
      }

      public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(3, paramMediaMetadataCompat, null);
      }

      public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(2, paramPlaybackStateCompat, null);
      }

      public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(5, paramList, null);
      }

      public void onQueueTitleChanged(CharSequence paramCharSequence)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(6, paramCharSequence, null);
      }

      public void onRepeatModeChanged(int paramInt)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(9, Integer.valueOf(paramInt), null);
      }

      public void onSessionDestroyed()
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(8, null, null);
      }

      public void onSessionReady()
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(13, null, null);
      }

      public void onShuffleModeChanged(int paramInt)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
          localCallback.postToHandler(12, Integer.valueOf(paramInt), null);
      }

      public void onShuffleModeChangedRemoved(boolean paramBoolean)
      {
      }

      public void onVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)this.mCallback.get();
        if (localCallback != null)
        {
          MediaControllerCompat.PlaybackInfo localPlaybackInfo;
          if (paramParcelableVolumeInfo != null)
          {
            int i = paramParcelableVolumeInfo.volumeType;
            int j = paramParcelableVolumeInfo.audioStream;
            int k = paramParcelableVolumeInfo.controlType;
            int m = paramParcelableVolumeInfo.maxVolume;
            int n = paramParcelableVolumeInfo.currentVolume;
            localPlaybackInfo = new MediaControllerCompat.PlaybackInfo(i, j, k, m, n);
          }
          else
          {
            localPlaybackInfo = null;
          }
          localCallback.postToHandler(4, localPlaybackInfo, null);
        }
      }
    }
  }

  private static class MediaControllerExtraData extends SupportActivity.ExtraData
  {
    private final MediaControllerCompat mMediaController;

    MediaControllerExtraData(MediaControllerCompat paramMediaControllerCompat)
    {
      this.mMediaController = paramMediaControllerCompat;
    }

    MediaControllerCompat getMediaController()
    {
      return this.mMediaController;
    }
  }

  static abstract interface MediaControllerImpl
  {
    public abstract void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat);

    public abstract void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt);

    public abstract void adjustVolume(int paramInt1, int paramInt2);

    public abstract boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent);

    public abstract Bundle getExtras();

    public abstract long getFlags();

    public abstract Object getMediaController();

    public abstract MediaMetadataCompat getMetadata();

    public abstract String getPackageName();

    public abstract MediaControllerCompat.PlaybackInfo getPlaybackInfo();

    public abstract PlaybackStateCompat getPlaybackState();

    public abstract List<MediaSessionCompat.QueueItem> getQueue();

    public abstract CharSequence getQueueTitle();

    public abstract int getRatingType();

    public abstract int getRepeatMode();

    public abstract PendingIntent getSessionActivity();

    public abstract int getShuffleMode();

    public abstract MediaControllerCompat.TransportControls getTransportControls();

    public abstract boolean isCaptioningEnabled();

    public abstract boolean isSessionReady();

    public abstract void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler);

    public abstract void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat);

    public abstract void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);

    public abstract void setVolumeTo(int paramInt1, int paramInt2);

    public abstract void unregisterCallback(MediaControllerCompat.Callback paramCallback);
  }

  @RequiresApi(21)
  static class MediaControllerImplApi21
    implements MediaControllerCompat.MediaControllerImpl
  {
    private HashMap<MediaControllerCompat.Callback, ExtraCallback> mCallbackMap = new HashMap();
    protected final Object mControllerObj;
    final Object mLock = new Object();

    @GuardedBy("mLock")
    private final List<MediaControllerCompat.Callback> mPendingCallbacks = new ArrayList();
    final MediaSessionCompat.Token mSessionToken;

    public MediaControllerImplApi21(Context paramContext, MediaSessionCompat.Token paramToken)
    {
      this.mSessionToken = paramToken;
      this.mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, this.mSessionToken.getToken());
      if (this.mControllerObj == null)
        throw new RemoteException();
      if (this.mSessionToken.getExtraBinder() == null)
        requestExtraBinder();
    }

    private void requestExtraBinder()
    {
      sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
    }

    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      if ((0x4 & getFlags()) == 0L)
        throw new UnsupportedOperationException("This session doesn't support queue management operations");
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", paramMediaDescriptionCompat);
      sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", localBundle, null);
    }

    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      if ((0x4 & getFlags()) == 0L)
        throw new UnsupportedOperationException("This session doesn't support queue management operations");
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", paramMediaDescriptionCompat);
      localBundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", paramInt);
      sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", localBundle, null);
    }

    public void adjustVolume(int paramInt1, int paramInt2)
    {
      MediaControllerCompatApi21.adjustVolume(this.mControllerObj, paramInt1, paramInt2);
    }

    public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
    {
      return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, paramKeyEvent);
    }

    public Bundle getExtras()
    {
      return MediaControllerCompatApi21.getExtras(this.mControllerObj);
    }

    public long getFlags()
    {
      return MediaControllerCompatApi21.getFlags(this.mControllerObj);
    }

    public Object getMediaController()
    {
      return this.mControllerObj;
    }

    public MediaMetadataCompat getMetadata()
    {
      Object localObject = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
      MediaMetadataCompat localMediaMetadataCompat;
      if (localObject != null)
        localMediaMetadataCompat = MediaMetadataCompat.fromMediaMetadata(localObject);
      else
        localMediaMetadataCompat = null;
      return localMediaMetadataCompat;
    }

    public String getPackageName()
    {
      return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
    }

    public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
    {
      Object localObject = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
      MediaControllerCompat.PlaybackInfo localPlaybackInfo;
      if (localObject != null)
      {
        int i = MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(localObject);
        int j = MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(localObject);
        int k = MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(localObject);
        int m = MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(localObject);
        int n = MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(localObject);
        localPlaybackInfo = new MediaControllerCompat.PlaybackInfo(i, j, k, m, n);
      }
      else
      {
        localPlaybackInfo = null;
      }
      return localPlaybackInfo;
    }

    public PlaybackStateCompat getPlaybackState()
    {
      if (this.mSessionToken.getExtraBinder() != null)
        try
        {
          PlaybackStateCompat localPlaybackStateCompat2 = this.mSessionToken.getExtraBinder().getPlaybackState();
          return localPlaybackStateCompat2;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", localRemoteException);
        }
      Object localObject = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
      PlaybackStateCompat localPlaybackStateCompat1;
      if (localObject != null)
        localPlaybackStateCompat1 = PlaybackStateCompat.fromPlaybackState(localObject);
      else
        localPlaybackStateCompat1 = null;
      return localPlaybackStateCompat1;
    }

    public List<MediaSessionCompat.QueueItem> getQueue()
    {
      List localList1 = MediaControllerCompatApi21.getQueue(this.mControllerObj);
      List localList2;
      if (localList1 != null)
        localList2 = MediaSessionCompat.QueueItem.fromQueueItemList(localList1);
      else
        localList2 = null;
      return localList2;
    }

    public CharSequence getQueueTitle()
    {
      return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
    }

    public int getRatingType()
    {
      if ((Build.VERSION.SDK_INT < 22) && (this.mSessionToken.getExtraBinder() != null))
        try
        {
          int i = this.mSessionToken.getExtraBinder().getRatingType();
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getRatingType.", localRemoteException);
        }
      return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
    }

    public int getRepeatMode()
    {
      if (this.mSessionToken.getExtraBinder() != null)
        try
        {
          int i = this.mSessionToken.getExtraBinder().getRepeatMode();
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", localRemoteException);
        }
      return -1;
    }

    public PendingIntent getSessionActivity()
    {
      return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
    }

    public int getShuffleMode()
    {
      if (this.mSessionToken.getExtraBinder() != null)
        try
        {
          int i = this.mSessionToken.getExtraBinder().getShuffleMode();
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", localRemoteException);
        }
      return -1;
    }

    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
      MediaControllerCompat.TransportControlsApi21 localTransportControlsApi21;
      if (localObject != null)
        localTransportControlsApi21 = new MediaControllerCompat.TransportControlsApi21(localObject);
      else
        localTransportControlsApi21 = null;
      return localTransportControlsApi21;
    }

    public boolean isCaptioningEnabled()
    {
      if (this.mSessionToken.getExtraBinder() != null)
        try
        {
          boolean bool = this.mSessionToken.getExtraBinder().isCaptioningEnabled();
          return bool;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", localRemoteException);
        }
      return false;
    }

    public boolean isSessionReady()
    {
      boolean bool;
      if (this.mSessionToken.getExtraBinder() != null)
        bool = true;
      else
        bool = false;
      return bool;
    }

    @GuardedBy("mLock")
    void processPendingCallbacksLocked()
    {
      if (this.mSessionToken.getExtraBinder() == null)
        return;
      Iterator localIterator = this.mPendingCallbacks.iterator();
      while (localIterator.hasNext())
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)localIterator.next();
        ExtraCallback localExtraCallback = new ExtraCallback(localCallback);
        this.mCallbackMap.put(localCallback, localExtraCallback);
        localCallback.mIControllerCallback = localExtraCallback;
        try
        {
          this.mSessionToken.getExtraBinder().registerCallbackListener(localExtraCallback);
          localCallback.postToHandler(13, null, null);
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in registerCallback.", localRemoteException);
        }
      }
      this.mPendingCallbacks.clear();
    }

    public final void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
    {
      MediaControllerCompatApi21.registerCallback(this.mControllerObj, paramCallback.mCallbackObj, paramHandler);
      synchronized (this.mLock)
      {
        if (this.mSessionToken.getExtraBinder() != null)
        {
          ExtraCallback localExtraCallback = new ExtraCallback(paramCallback);
          this.mCallbackMap.put(paramCallback, localExtraCallback);
          paramCallback.mIControllerCallback = localExtraCallback;
          try
          {
            this.mSessionToken.getExtraBinder().registerCallbackListener(localExtraCallback);
            paramCallback.postToHandler(13, null, null);
          }
          catch (RemoteException localRemoteException)
          {
            Log.e("MediaControllerCompat", "Dead object in registerCallback.", localRemoteException);
          }
        }
        else
        {
          paramCallback.mIControllerCallback = null;
          this.mPendingCallbacks.add(paramCallback);
        }
        return;
      }
    }

    public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      if ((0x4 & getFlags()) == 0L)
        throw new UnsupportedOperationException("This session doesn't support queue management operations");
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", paramMediaDescriptionCompat);
      sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", localBundle, null);
    }

    public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      MediaControllerCompatApi21.sendCommand(this.mControllerObj, paramString, paramBundle, paramResultReceiver);
    }

    public void setVolumeTo(int paramInt1, int paramInt2)
    {
      MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, paramInt1, paramInt2);
    }

    public final void unregisterCallback(MediaControllerCompat.Callback paramCallback)
    {
      MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, paramCallback.mCallbackObj);
      synchronized (this.mLock)
      {
        IMediaSession localIMediaSession = this.mSessionToken.getExtraBinder();
        if (localIMediaSession != null)
          try
          {
            ExtraCallback localExtraCallback = (ExtraCallback)this.mCallbackMap.remove(paramCallback);
            if (localExtraCallback == null)
              break label99;
            paramCallback.mIControllerCallback = null;
            this.mSessionToken.getExtraBinder().unregisterCallbackListener(localExtraCallback);
          }
          catch (RemoteException localRemoteException)
          {
            Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", localRemoteException);
          }
        else
          this.mPendingCallbacks.remove(paramCallback);
        label99: return;
      }
    }

    private static class ExtraBinderRequestResultReceiver extends ResultReceiver
    {
      private WeakReference<MediaControllerCompat.MediaControllerImplApi21> mMediaControllerImpl;

      ExtraBinderRequestResultReceiver(MediaControllerCompat.MediaControllerImplApi21 paramMediaControllerImplApi21)
      {
        super();
        this.mMediaControllerImpl = new WeakReference(paramMediaControllerImplApi21);
      }

      protected void onReceiveResult(int paramInt, Bundle paramBundle)
      {
        MediaControllerCompat.MediaControllerImplApi21 localMediaControllerImplApi21 = (MediaControllerCompat.MediaControllerImplApi21)this.mMediaControllerImpl.get();
        if ((localMediaControllerImplApi21 != null) && (paramBundle != null))
          synchronized (localMediaControllerImplApi21.mLock)
          {
            localMediaControllerImplApi21.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(paramBundle, "android.support.v4.media.session.EXTRA_BINDER")));
            localMediaControllerImplApi21.mSessionToken.setSessionToken2Bundle(paramBundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
            localMediaControllerImplApi21.processPendingCallbacksLocked();
            return;
          }
      }
    }

    private static class ExtraCallback extends MediaControllerCompat.Callback.StubCompat
    {
      ExtraCallback(MediaControllerCompat.Callback paramCallback)
      {
        super();
      }

      public void onExtrasChanged(Bundle paramBundle)
      {
        throw new AssertionError();
      }

      public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
      {
        throw new AssertionError();
      }

      public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
      {
        throw new AssertionError();
      }

      public void onQueueTitleChanged(CharSequence paramCharSequence)
      {
        throw new AssertionError();
      }

      public void onSessionDestroyed()
      {
        throw new AssertionError();
      }

      public void onVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
      {
        throw new AssertionError();
      }
    }
  }

  @RequiresApi(23)
  static class MediaControllerImplApi23 extends MediaControllerCompat.MediaControllerImplApi21
  {
    public MediaControllerImplApi23(Context paramContext, MediaSessionCompat.Token paramToken)
    {
      super(paramToken);
    }

    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
      MediaControllerCompat.TransportControlsApi23 localTransportControlsApi23;
      if (localObject != null)
        localTransportControlsApi23 = new MediaControllerCompat.TransportControlsApi23(localObject);
      else
        localTransportControlsApi23 = null;
      return localTransportControlsApi23;
    }
  }

  @RequiresApi(24)
  static class MediaControllerImplApi24 extends MediaControllerCompat.MediaControllerImplApi23
  {
    public MediaControllerImplApi24(Context paramContext, MediaSessionCompat.Token paramToken)
    {
      super(paramToken);
    }

    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
      MediaControllerCompat.TransportControlsApi24 localTransportControlsApi24;
      if (localObject != null)
        localTransportControlsApi24 = new MediaControllerCompat.TransportControlsApi24(localObject);
      else
        localTransportControlsApi24 = null;
      return localTransportControlsApi24;
    }
  }

  static class MediaControllerImplBase
    implements MediaControllerCompat.MediaControllerImpl
  {
    private IMediaSession mBinder;
    private MediaControllerCompat.TransportControls mTransportControls;

    public MediaControllerImplBase(MediaSessionCompat.Token paramToken)
    {
      this.mBinder = IMediaSession.Stub.asInterface((IBinder)paramToken.getToken());
    }

    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      try
      {
        if ((0x4 & this.mBinder.getFlags()) == 0L)
          throw new UnsupportedOperationException("This session doesn't support queue management operations");
        this.mBinder.addQueueItem(paramMediaDescriptionCompat);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in addQueueItem.", localRemoteException);
      }
    }

    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      try
      {
        if ((0x4 & this.mBinder.getFlags()) == 0L)
          throw new UnsupportedOperationException("This session doesn't support queue management operations");
        this.mBinder.addQueueItemAt(paramMediaDescriptionCompat, paramInt);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in addQueueItemAt.", localRemoteException);
      }
    }

    public void adjustVolume(int paramInt1, int paramInt2)
    {
      try
      {
        this.mBinder.adjustVolume(paramInt1, paramInt2, null);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in adjustVolume.", localRemoteException);
      }
    }

    public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
    {
      if (paramKeyEvent == null)
        throw new IllegalArgumentException("event may not be null.");
      try
      {
        this.mBinder.sendMediaButton(paramKeyEvent);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", localRemoteException);
      }
      return false;
    }

    public Bundle getExtras()
    {
      try
      {
        Bundle localBundle = this.mBinder.getExtras();
        return localBundle;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getExtras.", localRemoteException);
      }
      return null;
    }

    public long getFlags()
    {
      try
      {
        long l = this.mBinder.getFlags();
        return l;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getFlags.", localRemoteException);
      }
      return 0L;
    }

    public Object getMediaController()
    {
      return null;
    }

    public MediaMetadataCompat getMetadata()
    {
      try
      {
        MediaMetadataCompat localMediaMetadataCompat = this.mBinder.getMetadata();
        return localMediaMetadataCompat;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getMetadata.", localRemoteException);
      }
      return null;
    }

    public String getPackageName()
    {
      try
      {
        String str = this.mBinder.getPackageName();
        return str;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getPackageName.", localRemoteException);
      }
      return null;
    }

    public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
    {
      try
      {
        ParcelableVolumeInfo localParcelableVolumeInfo = this.mBinder.getVolumeAttributes();
        MediaControllerCompat.PlaybackInfo localPlaybackInfo = new MediaControllerCompat.PlaybackInfo(localParcelableVolumeInfo.volumeType, localParcelableVolumeInfo.audioStream, localParcelableVolumeInfo.controlType, localParcelableVolumeInfo.maxVolume, localParcelableVolumeInfo.currentVolume);
        return localPlaybackInfo;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo.", localRemoteException);
      }
      return null;
    }

    public PlaybackStateCompat getPlaybackState()
    {
      try
      {
        PlaybackStateCompat localPlaybackStateCompat = this.mBinder.getPlaybackState();
        return localPlaybackStateCompat;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", localRemoteException);
      }
      return null;
    }

    public List<MediaSessionCompat.QueueItem> getQueue()
    {
      try
      {
        List localList = this.mBinder.getQueue();
        return localList;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getQueue.", localRemoteException);
      }
      return null;
    }

    public CharSequence getQueueTitle()
    {
      try
      {
        CharSequence localCharSequence = this.mBinder.getQueueTitle();
        return localCharSequence;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getQueueTitle.", localRemoteException);
      }
      return null;
    }

    public int getRatingType()
    {
      try
      {
        int i = this.mBinder.getRatingType();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getRatingType.", localRemoteException);
      }
      return 0;
    }

    public int getRepeatMode()
    {
      try
      {
        int i = this.mBinder.getRepeatMode();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", localRemoteException);
      }
      return -1;
    }

    public PendingIntent getSessionActivity()
    {
      try
      {
        PendingIntent localPendingIntent = this.mBinder.getLaunchPendingIntent();
        return localPendingIntent;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", localRemoteException);
      }
      return null;
    }

    public int getShuffleMode()
    {
      try
      {
        int i = this.mBinder.getShuffleMode();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", localRemoteException);
      }
      return -1;
    }

    public MediaControllerCompat.TransportControls getTransportControls()
    {
      if (this.mTransportControls == null)
        this.mTransportControls = new MediaControllerCompat.TransportControlsBase(this.mBinder);
      return this.mTransportControls;
    }

    public boolean isCaptioningEnabled()
    {
      try
      {
        boolean bool = this.mBinder.isCaptioningEnabled();
        return bool;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", localRemoteException);
      }
      return false;
    }

    public boolean isSessionReady()
    {
      return true;
    }

    public void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
    {
      if (paramCallback == null)
        throw new IllegalArgumentException("callback may not be null.");
      try
      {
        this.mBinder.asBinder().linkToDeath(paramCallback, 0);
        this.mBinder.registerCallbackListener((IMediaControllerCallback)paramCallback.mCallbackObj);
        paramCallback.postToHandler(13, null, null);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in registerCallback.", localRemoteException);
        paramCallback.postToHandler(8, null, null);
      }
    }

    public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      try
      {
        if ((0x4 & this.mBinder.getFlags()) == 0L)
          throw new UnsupportedOperationException("This session doesn't support queue management operations");
        this.mBinder.removeQueueItem(paramMediaDescriptionCompat);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in removeQueueItem.", localRemoteException);
      }
    }

    public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      try
      {
        this.mBinder.sendCommand(paramString, paramBundle, new MediaSessionCompat.ResultReceiverWrapper(paramResultReceiver));
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in sendCommand.", localRemoteException);
      }
    }

    public void setVolumeTo(int paramInt1, int paramInt2)
    {
      try
      {
        this.mBinder.setVolumeTo(paramInt1, paramInt2, null);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setVolumeTo.", localRemoteException);
      }
    }

    public void unregisterCallback(MediaControllerCompat.Callback paramCallback)
    {
      if (paramCallback == null)
        throw new IllegalArgumentException("callback may not be null.");
      try
      {
        this.mBinder.unregisterCallbackListener((IMediaControllerCallback)paramCallback.mCallbackObj);
        this.mBinder.asBinder().unlinkToDeath(paramCallback, 0);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", localRemoteException);
      }
    }
  }

  public static final class PlaybackInfo
  {
    public static final int PLAYBACK_TYPE_LOCAL = 1;
    public static final int PLAYBACK_TYPE_REMOTE = 2;
    private final int mAudioStream;
    private final int mCurrentVolume;
    private final int mMaxVolume;
    private final int mPlaybackType;
    private final int mVolumeControl;

    PlaybackInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      this.mPlaybackType = paramInt1;
      this.mAudioStream = paramInt2;
      this.mVolumeControl = paramInt3;
      this.mMaxVolume = paramInt4;
      this.mCurrentVolume = paramInt5;
    }

    public int getAudioStream()
    {
      return this.mAudioStream;
    }

    public int getCurrentVolume()
    {
      return this.mCurrentVolume;
    }

    public int getMaxVolume()
    {
      return this.mMaxVolume;
    }

    public int getPlaybackType()
    {
      return this.mPlaybackType;
    }

    public int getVolumeControl()
    {
      return this.mVolumeControl;
    }
  }

  public static abstract class TransportControls
  {
    public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

    public abstract void fastForward();

    public abstract void pause();

    public abstract void play();

    public abstract void playFromMediaId(String paramString, Bundle paramBundle);

    public abstract void playFromSearch(String paramString, Bundle paramBundle);

    public abstract void playFromUri(Uri paramUri, Bundle paramBundle);

    public abstract void prepare();

    public abstract void prepareFromMediaId(String paramString, Bundle paramBundle);

    public abstract void prepareFromSearch(String paramString, Bundle paramBundle);

    public abstract void prepareFromUri(Uri paramUri, Bundle paramBundle);

    public abstract void rewind();

    public abstract void seekTo(long paramLong);

    public abstract void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle);

    public abstract void sendCustomAction(String paramString, Bundle paramBundle);

    public abstract void setCaptioningEnabled(boolean paramBoolean);

    public abstract void setRating(RatingCompat paramRatingCompat);

    public abstract void setRating(RatingCompat paramRatingCompat, Bundle paramBundle);

    public abstract void setRepeatMode(int paramInt);

    public abstract void setShuffleMode(int paramInt);

    public abstract void skipToNext();

    public abstract void skipToPrevious();

    public abstract void skipToQueueItem(long paramLong);

    public abstract void stop();
  }

  static class TransportControlsApi21 extends MediaControllerCompat.TransportControls
  {
    protected final Object mControlsObj;

    public TransportControlsApi21(Object paramObject)
    {
      this.mControlsObj = paramObject;
    }

    public void fastForward()
    {
      MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
    }

    public void pause()
    {
      MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
    }

    public void play()
    {
      MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
    }

    public void playFromMediaId(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi21.TransportControls.playFromMediaId(this.mControlsObj, paramString, paramBundle);
    }

    public void playFromSearch(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi21.TransportControls.playFromSearch(this.mControlsObj, paramString, paramBundle);
    }

    public void playFromUri(Uri paramUri, Bundle paramBundle)
    {
      if ((paramUri != null) && (!Uri.EMPTY.equals(paramUri)))
      {
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", paramUri);
        localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
        sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", localBundle);
        return;
      }
      throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
    }

    public void prepare()
    {
      sendCustomAction("android.support.v4.media.session.action.PREPARE", null);
    }

    public void prepareFromMediaId(String paramString, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", paramString);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", localBundle);
    }

    public void prepareFromSearch(String paramString, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", paramString);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", localBundle);
    }

    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", paramUri);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", localBundle);
    }

    public void rewind()
    {
      MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
    }

    public void seekTo(long paramLong)
    {
      MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, paramLong);
    }

    public void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle)
    {
      MediaControllerCompat.validateCustomAction(paramCustomAction.getAction(), paramBundle);
      MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, paramCustomAction.getAction(), paramBundle);
    }

    public void sendCustomAction(String paramString, Bundle paramBundle)
    {
      MediaControllerCompat.validateCustomAction(paramString, paramBundle);
      MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, paramString, paramBundle);
    }

    public void setCaptioningEnabled(boolean paramBoolean)
    {
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED", paramBoolean);
      sendCustomAction("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED", localBundle);
    }

    public void setRating(RatingCompat paramRatingCompat)
    {
      Object localObject1 = this.mControlsObj;
      Object localObject2;
      if (paramRatingCompat != null)
        localObject2 = paramRatingCompat.getRating();
      else
        localObject2 = null;
      MediaControllerCompatApi21.TransportControls.setRating(localObject1, localObject2);
    }

    public void setRating(RatingCompat paramRatingCompat, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", paramRatingCompat);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.SET_RATING", localBundle);
    }

    public void setRepeatMode(int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", paramInt);
      sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", localBundle);
    }

    public void setShuffleMode(int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", paramInt);
      sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", localBundle);
    }

    public void skipToNext()
    {
      MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
    }

    public void skipToPrevious()
    {
      MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
    }

    public void skipToQueueItem(long paramLong)
    {
      MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.mControlsObj, paramLong);
    }

    public void stop()
    {
      MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
    }
  }

  @RequiresApi(23)
  static class TransportControlsApi23 extends MediaControllerCompat.TransportControlsApi21
  {
    public TransportControlsApi23(Object paramObject)
    {
      super();
    }

    public void playFromUri(Uri paramUri, Bundle paramBundle)
    {
      MediaControllerCompatApi23.TransportControls.playFromUri(this.mControlsObj, paramUri, paramBundle);
    }
  }

  @RequiresApi(24)
  static class TransportControlsApi24 extends MediaControllerCompat.TransportControlsApi23
  {
    public TransportControlsApi24(Object paramObject)
    {
      super();
    }

    public void prepare()
    {
      MediaControllerCompatApi24.TransportControls.prepare(this.mControlsObj);
    }

    public void prepareFromMediaId(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi24.TransportControls.prepareFromMediaId(this.mControlsObj, paramString, paramBundle);
    }

    public void prepareFromSearch(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi24.TransportControls.prepareFromSearch(this.mControlsObj, paramString, paramBundle);
    }

    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
    {
      MediaControllerCompatApi24.TransportControls.prepareFromUri(this.mControlsObj, paramUri, paramBundle);
    }
  }

  static class TransportControlsBase extends MediaControllerCompat.TransportControls
  {
    private IMediaSession mBinder;

    public TransportControlsBase(IMediaSession paramIMediaSession)
    {
      this.mBinder = paramIMediaSession;
    }

    public void fastForward()
    {
      try
      {
        this.mBinder.fastForward();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in fastForward.", localRemoteException);
      }
    }

    public void pause()
    {
      try
      {
        this.mBinder.pause();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in pause.", localRemoteException);
      }
    }

    public void play()
    {
      try
      {
        this.mBinder.play();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in play.", localRemoteException);
      }
    }

    public void playFromMediaId(String paramString, Bundle paramBundle)
    {
      try
      {
        this.mBinder.playFromMediaId(paramString, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in playFromMediaId.", localRemoteException);
      }
    }

    public void playFromSearch(String paramString, Bundle paramBundle)
    {
      try
      {
        this.mBinder.playFromSearch(paramString, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in playFromSearch.", localRemoteException);
      }
    }

    public void playFromUri(Uri paramUri, Bundle paramBundle)
    {
      try
      {
        this.mBinder.playFromUri(paramUri, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in playFromUri.", localRemoteException);
      }
    }

    public void prepare()
    {
      try
      {
        this.mBinder.prepare();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in prepare.", localRemoteException);
      }
    }

    public void prepareFromMediaId(String paramString, Bundle paramBundle)
    {
      try
      {
        this.mBinder.prepareFromMediaId(paramString, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId.", localRemoteException);
      }
    }

    public void prepareFromSearch(String paramString, Bundle paramBundle)
    {
      try
      {
        this.mBinder.prepareFromSearch(paramString, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in prepareFromSearch.", localRemoteException);
      }
    }

    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
    {
      try
      {
        this.mBinder.prepareFromUri(paramUri, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in prepareFromUri.", localRemoteException);
      }
    }

    public void rewind()
    {
      try
      {
        this.mBinder.rewind();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in rewind.", localRemoteException);
      }
    }

    public void seekTo(long paramLong)
    {
      try
      {
        this.mBinder.seekTo(paramLong);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in seekTo.", localRemoteException);
      }
    }

    public void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle)
    {
      sendCustomAction(paramCustomAction.getAction(), paramBundle);
    }

    public void sendCustomAction(String paramString, Bundle paramBundle)
    {
      MediaControllerCompat.validateCustomAction(paramString, paramBundle);
      try
      {
        this.mBinder.sendCustomAction(paramString, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in sendCustomAction.", localRemoteException);
      }
    }

    public void setCaptioningEnabled(boolean paramBoolean)
    {
      try
      {
        this.mBinder.setCaptioningEnabled(paramBoolean);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setCaptioningEnabled.", localRemoteException);
      }
    }

    public void setRating(RatingCompat paramRatingCompat)
    {
      try
      {
        this.mBinder.rate(paramRatingCompat);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setRating.", localRemoteException);
      }
    }

    public void setRating(RatingCompat paramRatingCompat, Bundle paramBundle)
    {
      try
      {
        this.mBinder.rateWithExtras(paramRatingCompat, paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setRating.", localRemoteException);
      }
    }

    public void setRepeatMode(int paramInt)
    {
      try
      {
        this.mBinder.setRepeatMode(paramInt);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setRepeatMode.", localRemoteException);
      }
    }

    public void setShuffleMode(int paramInt)
    {
      try
      {
        this.mBinder.setShuffleMode(paramInt);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setShuffleMode.", localRemoteException);
      }
    }

    public void skipToNext()
    {
      try
      {
        this.mBinder.next();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in skipToNext.", localRemoteException);
      }
    }

    public void skipToPrevious()
    {
      try
      {
        this.mBinder.previous();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", localRemoteException);
      }
    }

    public void skipToQueueItem(long paramLong)
    {
      try
      {
        this.mBinder.skipToQueueItem(paramLong);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in skipToQueueItem.", localRemoteException);
      }
    }

    public void stop()
    {
      try
      {
        this.mBinder.stop();
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in stop.", localRemoteException);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat
 * JD-Core Version:    0.6.1
 */