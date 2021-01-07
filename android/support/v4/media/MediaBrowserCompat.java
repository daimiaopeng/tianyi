package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaBrowserCompat
{
  public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
  public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
  static final boolean DEBUG = false;
  public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
  public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
  public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
  public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
  static final String TAG = "MediaBrowserCompat";
  private final MediaBrowserImpl mImpl;

  public MediaBrowserCompat(Context paramContext, ComponentName paramComponentName, ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 26)
      this.mImpl = new MediaBrowserImplApi26(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    else if (Build.VERSION.SDK_INT >= 23)
      this.mImpl = new MediaBrowserImplApi23(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    else if (Build.VERSION.SDK_INT >= 21)
      this.mImpl = new MediaBrowserImplApi21(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    else
      this.mImpl = new MediaBrowserImplBase(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
  }

  public void connect()
  {
    this.mImpl.connect();
  }

  public void disconnect()
  {
    this.mImpl.disconnect();
  }

  @Nullable
  public Bundle getExtras()
  {
    return this.mImpl.getExtras();
  }

  public void getItem(@NonNull String paramString, @NonNull ItemCallback paramItemCallback)
  {
    this.mImpl.getItem(paramString, paramItemCallback);
  }

  @Nullable
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public Bundle getNotifyChildrenChangedOptions()
  {
    return this.mImpl.getNotifyChildrenChangedOptions();
  }

  @NonNull
  public String getRoot()
  {
    return this.mImpl.getRoot();
  }

  @NonNull
  public ComponentName getServiceComponent()
  {
    return this.mImpl.getServiceComponent();
  }

  @NonNull
  public MediaSessionCompat.Token getSessionToken()
  {
    return this.mImpl.getSessionToken();
  }

  public boolean isConnected()
  {
    return this.mImpl.isConnected();
  }

  public void search(@NonNull String paramString, Bundle paramBundle, @NonNull SearchCallback paramSearchCallback)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("query cannot be empty");
    if (paramSearchCallback == null)
      throw new IllegalArgumentException("callback cannot be null");
    this.mImpl.search(paramString, paramBundle, paramSearchCallback);
  }

  public void sendCustomAction(@NonNull String paramString, Bundle paramBundle, @Nullable CustomActionCallback paramCustomActionCallback)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("action cannot be empty");
    this.mImpl.sendCustomAction(paramString, paramBundle, paramCustomActionCallback);
  }

  public void subscribe(@NonNull String paramString, @NonNull Bundle paramBundle, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("parentId is empty");
    if (paramSubscriptionCallback == null)
      throw new IllegalArgumentException("callback is null");
    if (paramBundle == null)
      throw new IllegalArgumentException("options are null");
    this.mImpl.subscribe(paramString, paramBundle, paramSubscriptionCallback);
  }

  public void subscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("parentId is empty");
    if (paramSubscriptionCallback == null)
      throw new IllegalArgumentException("callback is null");
    this.mImpl.subscribe(paramString, null, paramSubscriptionCallback);
  }

  public void unsubscribe(@NonNull String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("parentId is empty");
    this.mImpl.unsubscribe(paramString, null);
  }

  public void unsubscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("parentId is empty");
    if (paramSubscriptionCallback == null)
      throw new IllegalArgumentException("callback is null");
    this.mImpl.unsubscribe(paramString, paramSubscriptionCallback);
  }

  private static class CallbackHandler extends Handler
  {
    private final WeakReference<MediaBrowserCompat.MediaBrowserServiceCallbackImpl> mCallbackImplRef;
    private WeakReference<Messenger> mCallbacksMessengerRef;

    CallbackHandler(MediaBrowserCompat.MediaBrowserServiceCallbackImpl paramMediaBrowserServiceCallbackImpl)
    {
      this.mCallbackImplRef = new WeakReference(paramMediaBrowserServiceCallbackImpl);
    }

    // ERROR //
    public void handleMessage(Message paramMessage)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 27	android/support/v4/media/MediaBrowserCompat$CallbackHandler:mCallbacksMessengerRef	Ljava/lang/ref/WeakReference;
      //   4: ifnull +289 -> 293
      //   7: aload_0
      //   8: getfield 27	android/support/v4/media/MediaBrowserCompat$CallbackHandler:mCallbacksMessengerRef	Ljava/lang/ref/WeakReference;
      //   11: invokevirtual 31	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   14: ifnull +279 -> 293
      //   17: aload_0
      //   18: getfield 21	android/support/v4/media/MediaBrowserCompat$CallbackHandler:mCallbackImplRef	Ljava/lang/ref/WeakReference;
      //   21: invokevirtual 31	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   24: ifnonnull +6 -> 30
      //   27: goto +266 -> 293
      //   30: aload_1
      //   31: invokevirtual 37	android/os/Message:getData	()Landroid/os/Bundle;
      //   34: astore_2
      //   35: aload_2
      //   36: invokestatic 43	android/support/v4/media/session/MediaSessionCompat:ensureClassLoader	(Landroid/os/Bundle;)V
      //   39: aload_0
      //   40: getfield 21	android/support/v4/media/MediaBrowserCompat$CallbackHandler:mCallbackImplRef	Ljava/lang/ref/WeakReference;
      //   43: invokevirtual 31	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   46: checkcast 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserServiceCallbackImpl
      //   49: astore_3
      //   50: aload_0
      //   51: getfield 27	android/support/v4/media/MediaBrowserCompat$CallbackHandler:mCallbacksMessengerRef	Ljava/lang/ref/WeakReference;
      //   54: invokevirtual 31	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   57: checkcast 47	android/os/Messenger
      //   60: astore 4
      //   62: aload_1
      //   63: getfield 51	android/os/Message:what	I
      //   66: tableswitch	default:+228 -> 294, 1:+90->156, 2:+79->145, 3:+26->92
      //   93: ldc 53
      //   95: invokevirtual 59	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
      //   98: astore 7
      //   100: aload 7
      //   102: invokestatic 43	android/support/v4/media/session/MediaSessionCompat:ensureClassLoader	(Landroid/os/Bundle;)V
      //   105: aload_2
      //   106: ldc 61
      //   108: invokevirtual 59	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
      //   111: astore 8
      //   113: aload 8
      //   115: invokestatic 43	android/support/v4/media/session/MediaSessionCompat:ensureClassLoader	(Landroid/os/Bundle;)V
      //   118: aload_3
      //   119: aload 4
      //   121: aload_2
      //   122: ldc 63
      //   124: invokevirtual 67	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   127: aload_2
      //   128: ldc 69
      //   130: invokevirtual 73	android/os/Bundle:getParcelableArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
      //   133: aload 7
      //   135: aload 8
      //   137: invokeinterface 77 6 0
      //   142: goto +150 -> 292
      //   145: aload_3
      //   146: aload 4
      //   148: invokeinterface 81 2 0
      //   153: goto +139 -> 292
      //   156: aload_2
      //   157: ldc 83
      //   159: invokevirtual 59	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
      //   162: astore 6
      //   164: aload 6
      //   166: invokestatic 43	android/support/v4/media/session/MediaSessionCompat:ensureClassLoader	(Landroid/os/Bundle;)V
      //   169: aload_3
      //   170: aload 4
      //   172: aload_2
      //   173: ldc 63
      //   175: invokevirtual 67	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   178: aload_2
      //   179: ldc 85
      //   181: invokevirtual 89	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   184: checkcast 91	android/support/v4/media/session/MediaSessionCompat$Token
      //   187: aload 6
      //   189: invokeinterface 95 5 0
      //   194: goto +98 -> 292
      //   197: new 97	java/lang/StringBuilder
      //   200: dup
      //   201: invokespecial 98	java/lang/StringBuilder:<init>	()V
      //   204: astore 9
      //   206: aload 9
      //   208: ldc 100
      //   210: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   213: pop
      //   214: aload 9
      //   216: aload_1
      //   217: invokevirtual 107	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   220: pop
      //   221: aload 9
      //   223: ldc 109
      //   225: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   228: pop
      //   229: aload 9
      //   231: iconst_1
      //   232: invokevirtual 112	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   235: pop
      //   236: aload 9
      //   238: ldc 114
      //   240: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   243: pop
      //   244: aload 9
      //   246: aload_1
      //   247: getfield 117	android/os/Message:arg1	I
      //   250: invokevirtual 112	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   253: pop
      //   254: ldc 119
      //   256: aload 9
      //   258: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   261: invokestatic 129	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   264: pop
      //   265: goto +27 -> 292
      //   268: ldc 119
      //   270: ldc 131
      //   272: invokestatic 134	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   275: pop
      //   276: aload_1
      //   277: getfield 51	android/os/Message:what	I
      //   280: iconst_1
      //   281: if_icmpne +11 -> 292
      //   284: aload_3
      //   285: aload 4
      //   287: invokeinterface 81 2 0
      //   292: return
      //   293: return
      //   294: goto -97 -> 197
      //
      // Exception table:
      //   from	to	target	type
      //   62	265	268	android/os/BadParcelableException
    }

    void setCallbacksMessenger(Messenger paramMessenger)
    {
      this.mCallbacksMessengerRef = new WeakReference(paramMessenger);
    }
  }

  public static class ConnectionCallback
  {
    ConnectionCallbackInternal mConnectionCallbackInternal;
    final Object mConnectionCallbackObj;

    public ConnectionCallback()
    {
      if (Build.VERSION.SDK_INT >= 21)
        this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
      else
        this.mConnectionCallbackObj = null;
    }

    public void onConnected()
    {
    }

    public void onConnectionFailed()
    {
    }

    public void onConnectionSuspended()
    {
    }

    void setInternalConnectionCallback(ConnectionCallbackInternal paramConnectionCallbackInternal)
    {
      this.mConnectionCallbackInternal = paramConnectionCallbackInternal;
    }

    static abstract interface ConnectionCallbackInternal
    {
      public abstract void onConnected();

      public abstract void onConnectionFailed();

      public abstract void onConnectionSuspended();
    }

    private class StubApi21
      implements MediaBrowserCompatApi21.ConnectionCallback
    {
      StubApi21()
      {
      }

      public void onConnected()
      {
        if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null)
          MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
        MediaBrowserCompat.ConnectionCallback.this.onConnected();
      }

      public void onConnectionFailed()
      {
        if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null)
          MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
        MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
      }

      public void onConnectionSuspended()
      {
        if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null)
          MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
        MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
      }
    }
  }

  public static abstract class CustomActionCallback
  {
    public void onError(String paramString, Bundle paramBundle1, Bundle paramBundle2)
    {
    }

    public void onProgressUpdate(String paramString, Bundle paramBundle1, Bundle paramBundle2)
    {
    }

    public void onResult(String paramString, Bundle paramBundle1, Bundle paramBundle2)
    {
    }
  }

  private static class CustomActionResultReceiver extends ResultReceiver
  {
    private final String mAction;
    private final MediaBrowserCompat.CustomActionCallback mCallback;
    private final Bundle mExtras;

    CustomActionResultReceiver(String paramString, Bundle paramBundle, MediaBrowserCompat.CustomActionCallback paramCustomActionCallback, Handler paramHandler)
    {
      super();
      this.mAction = paramString;
      this.mExtras = paramBundle;
      this.mCallback = paramCustomActionCallback;
    }

    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (this.mCallback == null)
        return;
      MediaSessionCompat.ensureClassLoader(paramBundle);
      switch (paramInt)
      {
      default:
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unknown result code: ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" (extras=");
        localStringBuilder.append(this.mExtras);
        localStringBuilder.append(", resultData=");
        localStringBuilder.append(paramBundle);
        localStringBuilder.append(")");
        Log.w("MediaBrowserCompat", localStringBuilder.toString());
        break;
      case 1:
        this.mCallback.onProgressUpdate(this.mAction, this.mExtras, paramBundle);
        break;
      case 0:
        this.mCallback.onResult(this.mAction, this.mExtras, paramBundle);
        break;
      case -1:
        this.mCallback.onError(this.mAction, this.mExtras, paramBundle);
      }
    }
  }

  public static abstract class ItemCallback
  {
    final Object mItemCallbackObj;

    public ItemCallback()
    {
      if (Build.VERSION.SDK_INT >= 23)
        this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
      else
        this.mItemCallbackObj = null;
    }

    public void onError(@NonNull String paramString)
    {
    }

    public void onItemLoaded(MediaBrowserCompat.MediaItem paramMediaItem)
    {
    }

    private class StubApi23
      implements MediaBrowserCompatApi23.ItemCallback
    {
      StubApi23()
      {
      }

      public void onError(@NonNull String paramString)
      {
        MediaBrowserCompat.ItemCallback.this.onError(paramString);
      }

      public void onItemLoaded(Parcel paramParcel)
      {
        if (paramParcel == null)
        {
          MediaBrowserCompat.ItemCallback.this.onItemLoaded(null);
        }
        else
        {
          paramParcel.setDataPosition(0);
          MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(paramParcel);
          paramParcel.recycle();
          MediaBrowserCompat.ItemCallback.this.onItemLoaded(localMediaItem);
        }
      }
    }
  }

  private static class ItemReceiver extends ResultReceiver
  {
    private final MediaBrowserCompat.ItemCallback mCallback;
    private final String mMediaId;

    ItemReceiver(String paramString, MediaBrowserCompat.ItemCallback paramItemCallback, Handler paramHandler)
    {
      super();
      this.mMediaId = paramString;
      this.mCallback = paramItemCallback;
    }

    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("media_item")))
      {
        Parcelable localParcelable = paramBundle.getParcelable("media_item");
        if ((localParcelable != null) && (!(localParcelable instanceof MediaBrowserCompat.MediaItem)))
          this.mCallback.onError(this.mMediaId);
        else
          this.mCallback.onItemLoaded((MediaBrowserCompat.MediaItem)localParcelable);
        return;
      }
      this.mCallback.onError(this.mMediaId);
    }
  }

  static abstract interface MediaBrowserImpl
  {
    public abstract void connect();

    public abstract void disconnect();

    @Nullable
    public abstract Bundle getExtras();

    public abstract void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback);

    @Nullable
    public abstract Bundle getNotifyChildrenChangedOptions();

    @NonNull
    public abstract String getRoot();

    public abstract ComponentName getServiceComponent();

    @NonNull
    public abstract MediaSessionCompat.Token getSessionToken();

    public abstract boolean isConnected();

    public abstract void search(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SearchCallback paramSearchCallback);

    public abstract void sendCustomAction(@NonNull String paramString, Bundle paramBundle, @Nullable MediaBrowserCompat.CustomActionCallback paramCustomActionCallback);

    public abstract void subscribe(@NonNull String paramString, @Nullable Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);

    public abstract void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
  }

  @RequiresApi(21)
  static class MediaBrowserImplApi21
    implements MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal, MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl
  {
    protected final Object mBrowserObj;
    protected Messenger mCallbacksMessenger;
    final Context mContext;
    protected final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    private Bundle mNotifyChildrenChangedOptions;
    protected final Bundle mRootHints;
    protected MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    protected int mServiceVersion;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();

    MediaBrowserImplApi21(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      this.mContext = paramContext;
      Bundle localBundle;
      if (paramBundle != null)
        localBundle = new Bundle(paramBundle);
      else
        localBundle = new Bundle();
      this.mRootHints = localBundle;
      this.mRootHints.putInt("extra_client_version", 1);
      paramConnectionCallback.setInternalConnectionCallback(this);
      this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(paramContext, paramComponentName, paramConnectionCallback.mConnectionCallbackObj, this.mRootHints);
    }

    public void connect()
    {
      MediaBrowserCompatApi21.connect(this.mBrowserObj);
    }

    // ERROR //
    public void disconnect()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   4: ifnull +32 -> 36
      //   7: aload_0
      //   8: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   11: ifnull +25 -> 36
      //   14: aload_0
      //   15: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   18: aload_0
      //   19: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   22: invokevirtual 100	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:unregisterCallbackMessenger	(Landroid/os/Messenger;)V
      //   25: goto +11 -> 36
      //   28: ldc 102
      //   30: ldc 104
      //   32: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   35: pop
      //   36: aload_0
      //   37: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   40: invokestatic 112	android/support/v4/media/MediaBrowserCompatApi21:disconnect	(Ljava/lang/Object;)V
      //   43: return
      //
      // Exception table:
      //   from	to	target	type
      //   14	25	28	android/os/RemoteException
    }

    @Nullable
    public Bundle getExtras()
    {
      return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
    }

    // ERROR //
    public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic 127	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   4: ifeq +13 -> 17
      //   7: new 129	java/lang/IllegalArgumentException
      //   10: dup
      //   11: ldc 131
      //   13: invokespecial 134	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   16: athrow
      //   17: aload_2
      //   18: ifnonnull +13 -> 31
      //   21: new 129	java/lang/IllegalArgumentException
      //   24: dup
      //   25: ldc 136
      //   27: invokespecial 134	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   30: athrow
      //   31: aload_0
      //   32: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   35: invokestatic 140	android/support/v4/media/MediaBrowserCompatApi21:isConnected	(Ljava/lang/Object;)Z
      //   38: ifne +30 -> 68
      //   41: ldc 102
      //   43: ldc 142
      //   45: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   48: pop
      //   49: aload_0
      //   50: getfield 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   53: new 144	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21$1
      //   56: dup
      //   57: aload_0
      //   58: aload_2
      //   59: aload_1
      //   60: invokespecial 147	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21$1:<init>	(Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Ljava/lang/String;)V
      //   63: invokevirtual 151	android/support/v4/media/MediaBrowserCompat$CallbackHandler:post	(Ljava/lang/Runnable;)Z
      //   66: pop
      //   67: return
      //   68: aload_0
      //   69: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   72: ifnonnull +22 -> 94
      //   75: aload_0
      //   76: getfield 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   79: new 153	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21$2
      //   82: dup
      //   83: aload_0
      //   84: aload_2
      //   85: aload_1
      //   86: invokespecial 154	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21$2:<init>	(Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Ljava/lang/String;)V
      //   89: invokevirtual 151	android/support/v4/media/MediaBrowserCompat$CallbackHandler:post	(Ljava/lang/Runnable;)Z
      //   92: pop
      //   93: return
      //   94: new 156	android/support/v4/media/MediaBrowserCompat$ItemReceiver
      //   97: dup
      //   98: aload_1
      //   99: aload_2
      //   100: aload_0
      //   101: getfield 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   104: invokespecial 159	android/support/v4/media/MediaBrowserCompat$ItemReceiver:<init>	(Ljava/lang/String;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Landroid/os/Handler;)V
      //   107: astore_3
      //   108: aload_0
      //   109: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   112: aload_1
      //   113: aload_3
      //   114: aload_0
      //   115: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   118: invokevirtual 163	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:getMediaItem	(Ljava/lang/String;Landroid/support/v4/os/ResultReceiver;Landroid/os/Messenger;)V
      //   121: goto +56 -> 177
      //   124: new 165	java/lang/StringBuilder
      //   127: dup
      //   128: invokespecial 166	java/lang/StringBuilder:<init>	()V
      //   131: astore 4
      //   133: aload 4
      //   135: ldc 168
      //   137: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   140: pop
      //   141: aload 4
      //   143: aload_1
      //   144: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   147: pop
      //   148: ldc 102
      //   150: aload 4
      //   152: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   155: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   158: pop
      //   159: aload_0
      //   160: getfield 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   163: new 178	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21$3
      //   166: dup
      //   167: aload_0
      //   168: aload_2
      //   169: aload_1
      //   170: invokespecial 179	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21$3:<init>	(Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Ljava/lang/String;)V
      //   173: invokevirtual 151	android/support/v4/media/MediaBrowserCompat$CallbackHandler:post	(Ljava/lang/Runnable;)Z
      //   176: pop
      //   177: return
      //
      // Exception table:
      //   from	to	target	type
      //   108	121	124	android/os/RemoteException
    }

    public Bundle getNotifyChildrenChangedOptions()
    {
      return this.mNotifyChildrenChangedOptions;
    }

    @NonNull
    public String getRoot()
    {
      return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
    }

    public ComponentName getServiceComponent()
    {
      return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
    }

    @NonNull
    public MediaSessionCompat.Token getSessionToken()
    {
      if (this.mMediaSessionToken == null)
        this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
      return this.mMediaSessionToken;
    }

    public boolean isConnected()
    {
      return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
    }

    // ERROR //
    public void onConnected()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   4: invokestatic 118	android/support/v4/media/MediaBrowserCompatApi21:getExtras	(Ljava/lang/Object;)Landroid/os/Bundle;
      //   7: astore_1
      //   8: aload_1
      //   9: ifnonnull +4 -> 13
      //   12: return
      //   13: aload_0
      //   14: aload_1
      //   15: ldc 208
      //   17: iconst_0
      //   18: invokevirtual 212	android/os/Bundle:getInt	(Ljava/lang/String;I)I
      //   21: putfield 214	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceVersion	I
      //   24: aload_1
      //   25: ldc 216
      //   27: invokestatic 222	android/support/v4/app/BundleCompat:getBinder	(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/IBinder;
      //   30: astore_2
      //   31: aload_2
      //   32: ifnull +71 -> 103
      //   35: aload_0
      //   36: new 96	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper
      //   39: dup
      //   40: aload_2
      //   41: aload_0
      //   42: getfield 60	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mRootHints	Landroid/os/Bundle;
      //   45: invokespecial 225	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:<init>	(Landroid/os/IBinder;Landroid/os/Bundle;)V
      //   48: putfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   51: aload_0
      //   52: new 227	android/os/Messenger
      //   55: dup
      //   56: aload_0
      //   57: getfield 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   60: invokespecial 230	android/os/Messenger:<init>	(Landroid/os/Handler;)V
      //   63: putfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   66: aload_0
      //   67: getfield 45	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   70: aload_0
      //   71: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   74: invokevirtual 233	android/support/v4/media/MediaBrowserCompat$CallbackHandler:setCallbacksMessenger	(Landroid/os/Messenger;)V
      //   77: aload_0
      //   78: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   81: aload_0
      //   82: getfield 52	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mContext	Landroid/content/Context;
      //   85: aload_0
      //   86: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   89: invokevirtual 237	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:registerCallbackMessenger	(Landroid/content/Context;Landroid/os/Messenger;)V
      //   92: goto +11 -> 103
      //   95: ldc 102
      //   97: ldc 239
      //   99: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   102: pop
      //   103: aload_1
      //   104: ldc 241
      //   106: invokestatic 222	android/support/v4/app/BundleCompat:getBinder	(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/IBinder;
      //   109: invokestatic 247	android/support/v4/media/session/IMediaSession$Stub:asInterface	(Landroid/os/IBinder;)Landroid/support/v4/media/session/IMediaSession;
      //   112: astore_3
      //   113: aload_3
      //   114: ifnull +18 -> 132
      //   117: aload_0
      //   118: aload_0
      //   119: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   122: invokestatic 198	android/support/v4/media/MediaBrowserCompatApi21:getSessionToken	(Ljava/lang/Object;)Ljava/lang/Object;
      //   125: aload_3
      //   126: invokestatic 250	android/support/v4/media/session/MediaSessionCompat$Token:fromToken	(Ljava/lang/Object;Landroid/support/v4/media/session/IMediaSession;)Landroid/support/v4/media/session/MediaSessionCompat$Token;
      //   129: putfield 195	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mMediaSessionToken	Landroid/support/v4/media/session/MediaSessionCompat$Token;
      //   132: return
      //
      // Exception table:
      //   from	to	target	type
      //   77	92	95	android/os/RemoteException
    }

    public void onConnectionFailed()
    {
    }

    public void onConnectionFailed(Messenger paramMessenger)
    {
    }

    public void onConnectionSuspended()
    {
      this.mServiceBinderWrapper = null;
      this.mCallbacksMessenger = null;
      this.mMediaSessionToken = null;
      this.mHandler.setCallbacksMessenger(null);
    }

    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle1, Bundle paramBundle2)
    {
      if (this.mCallbacksMessenger != paramMessenger)
        return;
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      if (localSubscription == null)
      {
        if (MediaBrowserCompat.DEBUG)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("onLoadChildren for id that isn't subscribed id=");
          localStringBuilder.append(paramString);
          Log.d("MediaBrowserCompat", localStringBuilder.toString());
        }
        return;
      }
      MediaBrowserCompat.SubscriptionCallback localSubscriptionCallback = localSubscription.getCallback(paramBundle1);
      if (localSubscriptionCallback != null)
        if (paramBundle1 == null)
        {
          if (paramList == null)
          {
            localSubscriptionCallback.onError(paramString);
          }
          else
          {
            this.mNotifyChildrenChangedOptions = paramBundle2;
            localSubscriptionCallback.onChildrenLoaded(paramString, paramList);
            this.mNotifyChildrenChangedOptions = null;
          }
        }
        else if (paramList == null)
        {
          localSubscriptionCallback.onError(paramString, paramBundle1);
        }
        else
        {
          this.mNotifyChildrenChangedOptions = paramBundle2;
          localSubscriptionCallback.onChildrenLoaded(paramString, paramList, paramBundle1);
          this.mNotifyChildrenChangedOptions = null;
        }
    }

    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
    }

    public void search(@NonNull final String paramString, final Bundle paramBundle, @NonNull final MediaBrowserCompat.SearchCallback paramSearchCallback)
    {
      if (!isConnected())
        throw new IllegalStateException("search() called while not connected");
      if (this.mServiceBinderWrapper == null)
      {
        Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramSearchCallback.onError(paramString, paramBundle);
          }
        });
        return;
      }
      MediaBrowserCompat.SearchResultReceiver localSearchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(paramString, paramBundle, paramSearchCallback, this.mHandler);
      try
      {
        this.mServiceBinderWrapper.search(paramString, paramBundle, localSearchResultReceiver, this.mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Remote error searching items with query: ");
        localStringBuilder.append(paramString);
        Log.i("MediaBrowserCompat", localStringBuilder.toString(), localRemoteException);
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramSearchCallback.onError(paramString, paramBundle);
          }
        });
      }
    }

    public void sendCustomAction(@NonNull final String paramString, final Bundle paramBundle, @Nullable final MediaBrowserCompat.CustomActionCallback paramCustomActionCallback)
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Cannot send a custom action (");
        localStringBuilder1.append(paramString);
        localStringBuilder1.append(") with ");
        localStringBuilder1.append("extras ");
        localStringBuilder1.append(paramBundle);
        localStringBuilder1.append(" because the browser is not connected to the ");
        localStringBuilder1.append("service.");
        throw new IllegalStateException(localStringBuilder1.toString());
      }
      if (this.mServiceBinderWrapper == null)
      {
        Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
        if (paramCustomActionCallback != null)
          this.mHandler.post(new Runnable()
          {
            public void run()
            {
              paramCustomActionCallback.onError(paramString, paramBundle, null);
            }
          });
      }
      MediaBrowserCompat.CustomActionResultReceiver localCustomActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(paramString, paramBundle, paramCustomActionCallback, this.mHandler);
      try
      {
        this.mServiceBinderWrapper.sendCustomAction(paramString, paramBundle, localCustomActionResultReceiver, this.mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Remote error sending a custom action: action=");
        localStringBuilder2.append(paramString);
        localStringBuilder2.append(", extras=");
        localStringBuilder2.append(paramBundle);
        Log.i("MediaBrowserCompat", localStringBuilder2.toString(), localRemoteException);
        if (paramCustomActionCallback != null)
          this.mHandler.post(new Runnable()
          {
            public void run()
            {
              paramCustomActionCallback.onError(paramString, paramBundle, null);
            }
          });
      }
    }

    // ERROR //
    public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 50	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   4: aload_1
      //   5: invokevirtual 257	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   8: checkcast 259	android/support/v4/media/MediaBrowserCompat$Subscription
      //   11: astore 4
      //   13: aload 4
      //   15: ifnonnull +23 -> 38
      //   18: new 259	android/support/v4/media/MediaBrowserCompat$Subscription
      //   21: dup
      //   22: invokespecial 362	android/support/v4/media/MediaBrowserCompat$Subscription:<init>	()V
      //   25: astore 4
      //   27: aload_0
      //   28: getfield 50	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   31: aload_1
      //   32: aload 4
      //   34: invokevirtual 366	android/support/v4/util/ArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   37: pop
      //   38: aload_3
      //   39: aload 4
      //   41: invokevirtual 370	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:setSubscription	(Landroid/support/v4/media/MediaBrowserCompat$Subscription;)V
      //   44: aload_2
      //   45: ifnonnull +9 -> 54
      //   48: aconst_null
      //   49: astore 6
      //   51: goto +13 -> 64
      //   54: new 54	android/os/Bundle
      //   57: dup
      //   58: aload_2
      //   59: invokespecial 57	android/os/Bundle:<init>	(Landroid/os/Bundle;)V
      //   62: astore 6
      //   64: aload 4
      //   66: aload 6
      //   68: aload_3
      //   69: invokevirtual 374	android/support/v4/media/MediaBrowserCompat$Subscription:putCallback	(Landroid/os/Bundle;Landroid/support/v4/media/MediaBrowserCompat$SubscriptionCallback;)V
      //   72: aload_0
      //   73: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   76: ifnonnull +18 -> 94
      //   79: aload_0
      //   80: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   83: aload_1
      //   84: aload_3
      //   85: getfield 377	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:mSubscriptionCallbackObj	Ljava/lang/Object;
      //   88: invokestatic 380	android/support/v4/media/MediaBrowserCompatApi21:subscribe	(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
      //   91: goto +60 -> 151
      //   94: aload_0
      //   95: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   98: aload_1
      //   99: aload_3
      //   100: getfield 384	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:mToken	Landroid/os/IBinder;
      //   103: aload 6
      //   105: aload_0
      //   106: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   109: invokevirtual 388	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:addSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Bundle;Landroid/os/Messenger;)V
      //   112: goto +39 -> 151
      //   115: new 165	java/lang/StringBuilder
      //   118: dup
      //   119: invokespecial 166	java/lang/StringBuilder:<init>	()V
      //   122: astore 7
      //   124: aload 7
      //   126: ldc_w 390
      //   129: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   132: pop
      //   133: aload 7
      //   135: aload_1
      //   136: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   139: pop
      //   140: ldc 102
      //   142: aload 7
      //   144: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   147: invokestatic 110	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   150: pop
      //   151: return
      //
      // Exception table:
      //   from	to	target	type
      //   94	112	115	android/os/RemoteException
    }

    // ERROR //
    public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 50	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   4: aload_1
      //   5: invokevirtual 257	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   8: checkcast 259	android/support/v4/media/MediaBrowserCompat$Subscription
      //   11: astore_3
      //   12: aload_3
      //   13: ifnonnull +4 -> 17
      //   16: return
      //   17: aload_0
      //   18: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   21: ifnonnull +106 -> 127
      //   24: aload_2
      //   25: ifnonnull +14 -> 39
      //   28: aload_0
      //   29: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   32: aload_1
      //   33: invokestatic 395	android/support/v4/media/MediaBrowserCompatApi21:unsubscribe	(Ljava/lang/Object;Ljava/lang/String;)V
      //   36: goto +230 -> 266
      //   39: aload_3
      //   40: invokevirtual 399	android/support/v4/media/MediaBrowserCompat$Subscription:getCallbacks	()Ljava/util/List;
      //   43: astore 14
      //   45: aload_3
      //   46: invokevirtual 402	android/support/v4/media/MediaBrowserCompat$Subscription:getOptionsList	()Ljava/util/List;
      //   49: astore 15
      //   51: iconst_m1
      //   52: aload 14
      //   54: invokeinterface 408 1 0
      //   59: iadd
      //   60: istore 16
      //   62: iload 16
      //   64: iflt +42 -> 106
      //   67: aload 14
      //   69: iload 16
      //   71: invokeinterface 411 2 0
      //   76: aload_2
      //   77: if_acmpne +23 -> 100
      //   80: aload 14
      //   82: iload 16
      //   84: invokeinterface 414 2 0
      //   89: pop
      //   90: aload 15
      //   92: iload 16
      //   94: invokeinterface 414 2 0
      //   99: pop
      //   100: iinc 16 255
      //   103: goto -41 -> 62
      //   106: aload 14
      //   108: invokeinterface 408 1 0
      //   113: ifne +153 -> 266
      //   116: aload_0
      //   117: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   120: aload_1
      //   121: invokestatic 395	android/support/v4/media/MediaBrowserCompatApi21:unsubscribe	(Ljava/lang/Object;Ljava/lang/String;)V
      //   124: goto +142 -> 266
      //   127: aload_2
      //   128: ifnonnull +19 -> 147
      //   131: aload_0
      //   132: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   135: aload_1
      //   136: aconst_null
      //   137: aload_0
      //   138: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   141: invokevirtual 418	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:removeSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Messenger;)V
      //   144: goto +122 -> 266
      //   147: aload_3
      //   148: invokevirtual 399	android/support/v4/media/MediaBrowserCompat$Subscription:getCallbacks	()Ljava/util/List;
      //   151: astore 9
      //   153: aload_3
      //   154: invokevirtual 402	android/support/v4/media/MediaBrowserCompat$Subscription:getOptionsList	()Ljava/util/List;
      //   157: astore 10
      //   159: iconst_m1
      //   160: aload 9
      //   162: invokeinterface 408 1 0
      //   167: iadd
      //   168: istore 11
      //   170: iload 11
      //   172: iflt +94 -> 266
      //   175: aload 9
      //   177: iload 11
      //   179: invokeinterface 411 2 0
      //   184: aload_2
      //   185: if_acmpne +39 -> 224
      //   188: aload_0
      //   189: getfield 92	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   192: aload_1
      //   193: aload_2
      //   194: getfield 384	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:mToken	Landroid/os/IBinder;
      //   197: aload_0
      //   198: getfield 94	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   201: invokevirtual 418	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:removeSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Messenger;)V
      //   204: aload 9
      //   206: iload 11
      //   208: invokeinterface 414 2 0
      //   213: pop
      //   214: aload 10
      //   216: iload 11
      //   218: invokeinterface 414 2 0
      //   223: pop
      //   224: iinc 11 255
      //   227: goto -57 -> 170
      //   230: new 165	java/lang/StringBuilder
      //   233: dup
      //   234: invokespecial 166	java/lang/StringBuilder:<init>	()V
      //   237: astore 4
      //   239: aload 4
      //   241: ldc_w 420
      //   244: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   247: pop
      //   248: aload 4
      //   250: aload_1
      //   251: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   254: pop
      //   255: ldc 102
      //   257: aload 4
      //   259: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   262: invokestatic 270	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   265: pop
      //   266: aload_3
      //   267: invokevirtual 422	android/support/v4/media/MediaBrowserCompat$Subscription:isEmpty	()Z
      //   270: ifne +7 -> 277
      //   273: aload_2
      //   274: ifnonnull +12 -> 286
      //   277: aload_0
      //   278: getfield 50	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   281: aload_1
      //   282: invokevirtual 424	android/support/v4/util/ArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   285: pop
      //   286: return
      //
      // Exception table:
      //   from	to	target	type
      //   131	224	230	android/os/RemoteException
    }
  }

  @RequiresApi(23)
  static class MediaBrowserImplApi23 extends MediaBrowserCompat.MediaBrowserImplApi21
  {
    MediaBrowserImplApi23(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }

    public void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (this.mServiceBinderWrapper == null)
        MediaBrowserCompatApi23.getItem(this.mBrowserObj, paramString, paramItemCallback.mItemCallbackObj);
      else
        super.getItem(paramString, paramItemCallback);
    }
  }

  @RequiresApi(26)
  static class MediaBrowserImplApi26 extends MediaBrowserCompat.MediaBrowserImplApi23
  {
    MediaBrowserImplApi26(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }

    public void subscribe(@NonNull String paramString, @Nullable Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if ((this.mServiceBinderWrapper != null) && (this.mServiceVersion >= 2))
        super.subscribe(paramString, paramBundle, paramSubscriptionCallback);
      else if (paramBundle == null)
        MediaBrowserCompatApi21.subscribe(this.mBrowserObj, paramString, paramSubscriptionCallback.mSubscriptionCallbackObj);
      else
        MediaBrowserCompatApi26.subscribe(this.mBrowserObj, paramString, paramBundle, paramSubscriptionCallback.mSubscriptionCallbackObj);
    }

    public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if ((this.mServiceBinderWrapper != null) && (this.mServiceVersion >= 2))
        super.unsubscribe(paramString, paramSubscriptionCallback);
      else if (paramSubscriptionCallback == null)
        MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, paramString);
      else
        MediaBrowserCompatApi26.unsubscribe(this.mBrowserObj, paramString, paramSubscriptionCallback.mSubscriptionCallbackObj);
    }
  }

  static class MediaBrowserImplBase
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl
  {
    static final int CONNECT_STATE_CONNECTED = 3;
    static final int CONNECT_STATE_CONNECTING = 2;
    static final int CONNECT_STATE_DISCONNECTED = 1;
    static final int CONNECT_STATE_DISCONNECTING = 0;
    static final int CONNECT_STATE_SUSPENDED = 4;
    final MediaBrowserCompat.ConnectionCallback mCallback;
    Messenger mCallbacksMessenger;
    final Context mContext;
    private Bundle mExtras;
    final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    private Bundle mNotifyChildrenChangedOptions;
    final Bundle mRootHints;
    private String mRootId;
    MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    final ComponentName mServiceComponent;
    MediaServiceConnection mServiceConnection;
    int mState = 1;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();

    public MediaBrowserImplBase(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      if (paramContext == null)
        throw new IllegalArgumentException("context must not be null");
      if (paramComponentName == null)
        throw new IllegalArgumentException("service component must not be null");
      if (paramConnectionCallback == null)
        throw new IllegalArgumentException("connection callback must not be null");
      this.mContext = paramContext;
      this.mServiceComponent = paramComponentName;
      this.mCallback = paramConnectionCallback;
      Bundle localBundle;
      if (paramBundle == null)
        localBundle = null;
      else
        localBundle = new Bundle(paramBundle);
      this.mRootHints = localBundle;
    }

    private static String getStateLabel(int paramInt)
    {
      switch (paramInt)
      {
      default:
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("UNKNOWN/");
        localStringBuilder.append(paramInt);
        return localStringBuilder.toString();
      case 4:
        return "CONNECT_STATE_SUSPENDED";
      case 3:
        return "CONNECT_STATE_CONNECTED";
      case 2:
        return "CONNECT_STATE_CONNECTING";
      case 1:
        return "CONNECT_STATE_DISCONNECTED";
      case 0:
      }
      return "CONNECT_STATE_DISCONNECTING";
    }

    private boolean isCurrent(Messenger paramMessenger, String paramString)
    {
      if ((this.mCallbacksMessenger == paramMessenger) && (this.mState != 0) && (this.mState != 1))
        return true;
      if ((this.mState != 0) && (this.mState != 1))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(" for ");
        localStringBuilder.append(this.mServiceComponent);
        localStringBuilder.append(" with mCallbacksMessenger=");
        localStringBuilder.append(this.mCallbacksMessenger);
        localStringBuilder.append(" this=");
        localStringBuilder.append(this);
        Log.i("MediaBrowserCompat", localStringBuilder.toString());
      }
      return false;
    }

    public void connect()
    {
      if ((this.mState != 0) && (this.mState != 1))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("connect() called while neigther disconnecting nor disconnected (state=");
        localStringBuilder.append(getStateLabel(this.mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      this.mState = 2;
      this.mHandler.post(new Runnable()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   4: getfield 26	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
          //   7: ifne +4 -> 11
          //   10: return
          //   11: aload_0
          //   12: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   15: iconst_2
          //   16: putfield 26	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
          //   19: getstatic 32	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
          //   22: ifeq +56 -> 78
          //   25: aload_0
          //   26: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   29: getfield 36	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceConnection	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
          //   32: ifnull +46 -> 78
          //   35: new 38	java/lang/StringBuilder
          //   38: dup
          //   39: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   42: astore 15
          //   44: aload 15
          //   46: ldc 41
          //   48: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   51: pop
          //   52: aload 15
          //   54: aload_0
          //   55: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   58: getfield 36	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceConnection	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
          //   61: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   64: pop
          //   65: new 50	java/lang/RuntimeException
          //   68: dup
          //   69: aload 15
          //   71: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   74: invokespecial 57	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
          //   77: athrow
          //   78: aload_0
          //   79: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   82: getfield 61	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
          //   85: ifnull +42 -> 127
          //   88: new 38	java/lang/StringBuilder
          //   91: dup
          //   92: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   95: astore_1
          //   96: aload_1
          //   97: ldc 63
          //   99: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   102: pop
          //   103: aload_1
          //   104: aload_0
          //   105: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   108: getfield 61	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
          //   111: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   114: pop
          //   115: new 50	java/lang/RuntimeException
          //   118: dup
          //   119: aload_1
          //   120: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   123: invokespecial 57	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
          //   126: athrow
          //   127: aload_0
          //   128: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   131: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
          //   134: ifnull +46 -> 180
          //   137: new 38	java/lang/StringBuilder
          //   140: dup
          //   141: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   144: astore 4
          //   146: aload 4
          //   148: ldc 69
          //   150: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   153: pop
          //   154: aload 4
          //   156: aload_0
          //   157: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   160: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
          //   163: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   166: pop
          //   167: new 50	java/lang/RuntimeException
          //   170: dup
          //   171: aload 4
          //   173: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   176: invokespecial 57	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
          //   179: athrow
          //   180: new 71	android/content/Intent
          //   183: dup
          //   184: ldc 73
          //   186: invokespecial 74	android/content/Intent:<init>	(Ljava/lang/String;)V
          //   189: astore 7
          //   191: aload 7
          //   193: aload_0
          //   194: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   197: getfield 78	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceComponent	Landroid/content/ComponentName;
          //   200: invokevirtual 82	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
          //   203: pop
          //   204: aload_0
          //   205: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   208: new 84	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection
          //   211: dup
          //   212: aload_0
          //   213: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   216: invokespecial 86	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:<init>	(Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;)V
          //   219: putfield 36	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceConnection	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
          //   222: aload_0
          //   223: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   226: getfield 90	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mContext	Landroid/content/Context;
          //   229: aload 7
          //   231: aload_0
          //   232: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   235: getfield 36	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceConnection	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
          //   238: iconst_1
          //   239: invokevirtual 96	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
          //   242: istore 13
          //   244: goto +47 -> 291
          //   247: new 38	java/lang/StringBuilder
          //   250: dup
          //   251: invokespecial 39	java/lang/StringBuilder:<init>	()V
          //   254: astore 9
          //   256: aload 9
          //   258: ldc 98
          //   260: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   263: pop
          //   264: aload 9
          //   266: aload_0
          //   267: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   270: getfield 78	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceComponent	Landroid/content/ComponentName;
          //   273: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   276: pop
          //   277: ldc 100
          //   279: aload 9
          //   281: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   284: invokestatic 106	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   287: pop
          //   288: iconst_0
          //   289: istore 13
          //   291: iload 13
          //   293: ifne +20 -> 313
          //   296: aload_0
          //   297: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   300: invokevirtual 109	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:forceCloseConnection	()V
          //   303: aload_0
          //   304: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   307: getfield 113	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallback	Landroid/support/v4/media/MediaBrowserCompat$ConnectionCallback;
          //   310: invokevirtual 118	android/support/v4/media/MediaBrowserCompat$ConnectionCallback:onConnectionFailed	()V
          //   313: getstatic 32	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
          //   316: ifeq +18 -> 334
          //   319: ldc 100
          //   321: ldc 120
          //   323: invokestatic 123	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   326: pop
          //   327: aload_0
          //   328: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$1:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   331: invokevirtual 126	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:dump	()V
          //   334: return
          //
          // Exception table:
          //   from	to	target	type
          //   222	244	247	java/lang/Exception
        }
      });
    }

    public void disconnect()
    {
      this.mState = 0;
      this.mHandler.post(new Runnable()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   4: getfield 26	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
          //   7: ifnull +60 -> 67
          //   10: aload_0
          //   11: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   14: getfield 30	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
          //   17: aload_0
          //   18: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   21: getfield 26	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
          //   24: invokevirtual 35	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:disconnect	(Landroid/os/Messenger;)V
          //   27: goto +40 -> 67
          //   30: new 37	java/lang/StringBuilder
          //   33: dup
          //   34: invokespecial 38	java/lang/StringBuilder:<init>	()V
          //   37: astore_3
          //   38: aload_3
          //   39: ldc 40
          //   41: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   44: pop
          //   45: aload_3
          //   46: aload_0
          //   47: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   50: getfield 48	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceComponent	Landroid/content/ComponentName;
          //   53: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   56: pop
          //   57: ldc 53
          //   59: aload_3
          //   60: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   63: invokestatic 63	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
          //   66: pop
          //   67: aload_0
          //   68: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   71: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
          //   74: istore_1
          //   75: aload_0
          //   76: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   79: invokevirtual 70	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:forceCloseConnection	()V
          //   82: iload_1
          //   83: ifeq +11 -> 94
          //   86: aload_0
          //   87: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   90: iload_1
          //   91: putfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
          //   94: getstatic 76	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
          //   97: ifeq +18 -> 115
          //   100: ldc 53
          //   102: ldc 78
          //   104: invokestatic 81	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   107: pop
          //   108: aload_0
          //   109: getfield 17	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$2:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
          //   112: invokevirtual 84	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:dump	()V
          //   115: return
          //
          // Exception table:
          //   from	to	target	type
          //   10	27	30	android/os/RemoteException
        }
      });
    }

    void dump()
    {
      Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("  mServiceComponent=");
      localStringBuilder1.append(this.mServiceComponent);
      Log.d("MediaBrowserCompat", localStringBuilder1.toString());
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("  mCallback=");
      localStringBuilder2.append(this.mCallback);
      Log.d("MediaBrowserCompat", localStringBuilder2.toString());
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("  mRootHints=");
      localStringBuilder3.append(this.mRootHints);
      Log.d("MediaBrowserCompat", localStringBuilder3.toString());
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append("  mState=");
      localStringBuilder4.append(getStateLabel(this.mState));
      Log.d("MediaBrowserCompat", localStringBuilder4.toString());
      StringBuilder localStringBuilder5 = new StringBuilder();
      localStringBuilder5.append("  mServiceConnection=");
      localStringBuilder5.append(this.mServiceConnection);
      Log.d("MediaBrowserCompat", localStringBuilder5.toString());
      StringBuilder localStringBuilder6 = new StringBuilder();
      localStringBuilder6.append("  mServiceBinderWrapper=");
      localStringBuilder6.append(this.mServiceBinderWrapper);
      Log.d("MediaBrowserCompat", localStringBuilder6.toString());
      StringBuilder localStringBuilder7 = new StringBuilder();
      localStringBuilder7.append("  mCallbacksMessenger=");
      localStringBuilder7.append(this.mCallbacksMessenger);
      Log.d("MediaBrowserCompat", localStringBuilder7.toString());
      StringBuilder localStringBuilder8 = new StringBuilder();
      localStringBuilder8.append("  mRootId=");
      localStringBuilder8.append(this.mRootId);
      Log.d("MediaBrowserCompat", localStringBuilder8.toString());
      StringBuilder localStringBuilder9 = new StringBuilder();
      localStringBuilder9.append("  mMediaSessionToken=");
      localStringBuilder9.append(this.mMediaSessionToken);
      Log.d("MediaBrowserCompat", localStringBuilder9.toString());
    }

    void forceCloseConnection()
    {
      if (this.mServiceConnection != null)
        this.mContext.unbindService(this.mServiceConnection);
      this.mState = 1;
      this.mServiceConnection = null;
      this.mServiceBinderWrapper = null;
      this.mCallbacksMessenger = null;
      this.mHandler.setCallbacksMessenger(null);
      this.mRootId = null;
      this.mMediaSessionToken = null;
    }

    @Nullable
    public Bundle getExtras()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getExtras() called while not connected (state=");
        localStringBuilder.append(getStateLabel(this.mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return this.mExtras;
    }

    // ERROR //
    public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic 220	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   4: ifeq +13 -> 17
      //   7: new 66	java/lang/IllegalArgumentException
      //   10: dup
      //   11: ldc 222
      //   13: invokespecial 71	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   16: athrow
      //   17: aload_2
      //   18: ifnonnull +13 -> 31
      //   21: new 66	java/lang/IllegalArgumentException
      //   24: dup
      //   25: ldc 224
      //   27: invokespecial 71	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   30: athrow
      //   31: aload_0
      //   32: invokevirtual 205	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:isConnected	()Z
      //   35: ifne +30 -> 65
      //   38: ldc 126
      //   40: ldc 226
      //   42: invokestatic 132	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   45: pop
      //   46: aload_0
      //   47: getfield 57	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   50: new 228	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$3
      //   53: dup
      //   54: aload_0
      //   55: aload_2
      //   56: aload_1
      //   57: invokespecial 231	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$3:<init>	(Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Ljava/lang/String;)V
      //   60: invokevirtual 151	android/support/v4/media/MediaBrowserCompat$CallbackHandler:post	(Ljava/lang/Runnable;)Z
      //   63: pop
      //   64: return
      //   65: new 233	android/support/v4/media/MediaBrowserCompat$ItemReceiver
      //   68: dup
      //   69: aload_1
      //   70: aload_2
      //   71: aload_0
      //   72: getfield 57	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   75: invokespecial 236	android/support/v4/media/MediaBrowserCompat$ItemReceiver:<init>	(Ljava/lang/String;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Landroid/os/Handler;)V
      //   78: astore_3
      //   79: aload_0
      //   80: getfield 177	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   83: aload_1
      //   84: aload_3
      //   85: aload_0
      //   86: getfield 115	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
      //   89: invokevirtual 242	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:getMediaItem	(Ljava/lang/String;Landroid/support/v4/os/ResultReceiver;Landroid/os/Messenger;)V
      //   92: goto +56 -> 148
      //   95: new 92	java/lang/StringBuilder
      //   98: dup
      //   99: invokespecial 93	java/lang/StringBuilder:<init>	()V
      //   102: astore 4
      //   104: aload 4
      //   106: ldc 244
      //   108: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: pop
      //   112: aload 4
      //   114: aload_1
      //   115: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   118: pop
      //   119: ldc 126
      //   121: aload 4
      //   123: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   126: invokestatic 132	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   129: pop
      //   130: aload_0
      //   131: getfield 57	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
      //   134: new 246	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$4
      //   137: dup
      //   138: aload_0
      //   139: aload_2
      //   140: aload_1
      //   141: invokespecial 247	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$4:<init>	(Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;Landroid/support/v4/media/MediaBrowserCompat$ItemCallback;Ljava/lang/String;)V
      //   144: invokevirtual 151	android/support/v4/media/MediaBrowserCompat$CallbackHandler:post	(Ljava/lang/Runnable;)Z
      //   147: pop
      //   148: return
      //
      // Exception table:
      //   from	to	target	type
      //   79	92	95	android/os/RemoteException
    }

    public Bundle getNotifyChildrenChangedOptions()
    {
      return this.mNotifyChildrenChangedOptions;
    }

    @NonNull
    public String getRoot()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getRoot() called while not connected(state=");
        localStringBuilder.append(getStateLabel(this.mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return this.mRootId;
    }

    @NonNull
    public ComponentName getServiceComponent()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getServiceComponent() called while not connected (state=");
        localStringBuilder.append(this.mState);
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return this.mServiceComponent;
    }

    @NonNull
    public MediaSessionCompat.Token getSessionToken()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getSessionToken() called while not connected(state=");
        localStringBuilder.append(this.mState);
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return this.mMediaSessionToken;
    }

    public boolean isConnected()
    {
      boolean bool;
      if (this.mState == 3)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public void onConnectionFailed(Messenger paramMessenger)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("onConnectFailed for ");
      localStringBuilder1.append(this.mServiceComponent);
      Log.e("MediaBrowserCompat", localStringBuilder1.toString());
      if (!isCurrent(paramMessenger, "onConnectFailed"))
        return;
      if (this.mState != 2)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("onConnect from service while mState=");
        localStringBuilder2.append(getStateLabel(this.mState));
        localStringBuilder2.append("... ignoring");
        Log.w("MediaBrowserCompat", localStringBuilder2.toString());
        return;
      }
      forceCloseConnection();
      this.mCallback.onConnectionFailed();
    }

    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle1, Bundle paramBundle2)
    {
      if (!isCurrent(paramMessenger, "onLoadChildren"))
        return;
      if (MediaBrowserCompat.DEBUG)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("onLoadChildren for ");
        localStringBuilder1.append(this.mServiceComponent);
        localStringBuilder1.append(" id=");
        localStringBuilder1.append(paramString);
        Log.d("MediaBrowserCompat", localStringBuilder1.toString());
      }
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      if (localSubscription == null)
      {
        if (MediaBrowserCompat.DEBUG)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("onLoadChildren for id that isn't subscribed id=");
          localStringBuilder2.append(paramString);
          Log.d("MediaBrowserCompat", localStringBuilder2.toString());
        }
        return;
      }
      MediaBrowserCompat.SubscriptionCallback localSubscriptionCallback = localSubscription.getCallback(paramBundle1);
      if (localSubscriptionCallback != null)
        if (paramBundle1 == null)
        {
          if (paramList == null)
          {
            localSubscriptionCallback.onError(paramString);
          }
          else
          {
            this.mNotifyChildrenChangedOptions = paramBundle2;
            localSubscriptionCallback.onChildrenLoaded(paramString, paramList);
            this.mNotifyChildrenChangedOptions = null;
          }
        }
        else if (paramList == null)
        {
          localSubscriptionCallback.onError(paramString, paramBundle1);
        }
        else
        {
          this.mNotifyChildrenChangedOptions = paramBundle2;
          localSubscriptionCallback.onChildrenLoaded(paramString, paramList, paramBundle1);
          this.mNotifyChildrenChangedOptions = null;
        }
    }

    // ERROR //
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: ldc_w 328
      //   5: invokespecial 271	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:isCurrent	(Landroid/os/Messenger;Ljava/lang/String;)Z
      //   8: ifne +4 -> 12
      //   11: return
      //   12: aload_0
      //   13: getfield 64	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
      //   16: iconst_2
      //   17: if_icmpeq +55 -> 72
      //   20: new 92	java/lang/StringBuilder
      //   23: dup
      //   24: invokespecial 93	java/lang/StringBuilder:<init>	()V
      //   27: astore 5
      //   29: aload 5
      //   31: ldc_w 273
      //   34: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: pop
      //   38: aload 5
      //   40: aload_0
      //   41: getfield 64	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
      //   44: invokestatic 137	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:getStateLabel	(I)Ljava/lang/String;
      //   47: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   50: pop
      //   51: aload 5
      //   53: ldc_w 275
      //   56: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   59: pop
      //   60: ldc 126
      //   62: aload 5
      //   64: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   67: invokestatic 278	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   70: pop
      //   71: return
      //   72: aload_0
      //   73: aload_2
      //   74: putfield 183	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mRootId	Ljava/lang/String;
      //   77: aload_0
      //   78: aload_3
      //   79: putfield 187	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mMediaSessionToken	Landroid/support/v4/media/session/MediaSessionCompat$Token;
      //   82: aload_0
      //   83: aload 4
      //   85: putfield 209	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mExtras	Landroid/os/Bundle;
      //   88: aload_0
      //   89: iconst_3
      //   90: putfield 64	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
      //   93: getstatic 293	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
      //   96: ifeq +16 -> 112
      //   99: ldc 126
      //   101: ldc_w 330
      //   104: invokestatic 161	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   107: pop
      //   108: aload_0
      //   109: invokevirtual 332	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:dump	()V
      //   112: aload_0
      //   113: getfield 81	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallback	Landroid/support/v4/media/MediaBrowserCompat$ConnectionCallback;
      //   116: invokevirtual 335	android/support/v4/media/MediaBrowserCompat$ConnectionCallback:onConnected	()V
      //   119: aload_0
      //   120: getfield 62	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   123: invokevirtual 339	android/support/v4/util/ArrayMap:entrySet	()Ljava/util/Set;
      //   126: invokeinterface 345 1 0
      //   131: astore 11
      //   133: aload 11
      //   135: invokeinterface 350 1 0
      //   140: ifeq +123 -> 263
      //   143: aload 11
      //   145: invokeinterface 354 1 0
      //   150: checkcast 356	java/util/Map$Entry
      //   153: astore 12
      //   155: aload 12
      //   157: invokeinterface 359 1 0
      //   162: checkcast 361	java/lang/String
      //   165: astore 13
      //   167: aload 12
      //   169: invokeinterface 364 1 0
      //   174: checkcast 303	android/support/v4/media/MediaBrowserCompat$Subscription
      //   177: astore 14
      //   179: aload 14
      //   181: invokevirtual 368	android/support/v4/media/MediaBrowserCompat$Subscription:getCallbacks	()Ljava/util/List;
      //   184: astore 15
      //   186: aload 14
      //   188: invokevirtual 371	android/support/v4/media/MediaBrowserCompat$Subscription:getOptionsList	()Ljava/util/List;
      //   191: astore 16
      //   193: iconst_0
      //   194: istore 17
      //   196: iload 17
      //   198: aload 15
      //   200: invokeinterface 377 1 0
      //   205: if_icmpge -72 -> 133
      //   208: aload_0
      //   209: getfield 177	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   212: aload 13
      //   214: aload 15
      //   216: iload 17
      //   218: invokeinterface 380 2 0
      //   223: checkcast 311	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback
      //   226: getfield 384	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:mToken	Landroid/os/IBinder;
      //   229: aload 16
      //   231: iload 17
      //   233: invokeinterface 380 2 0
      //   238: checkcast 83	android/os/Bundle
      //   241: aload_0
      //   242: getfield 115	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
      //   245: invokevirtual 388	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:addSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Bundle;Landroid/os/Messenger;)V
      //   248: iinc 17 1
      //   251: goto -55 -> 196
      //   254: ldc 126
      //   256: ldc_w 390
      //   259: invokestatic 161	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   262: pop
      //   263: return
      //
      // Exception table:
      //   from	to	target	type
      //   119	248	254	android/os/RemoteException
    }

    public void search(@NonNull final String paramString, final Bundle paramBundle, @NonNull final MediaBrowserCompat.SearchCallback paramSearchCallback)
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("search() called while not connected (state=");
        localStringBuilder1.append(getStateLabel(this.mState));
        localStringBuilder1.append(")");
        throw new IllegalStateException(localStringBuilder1.toString());
      }
      MediaBrowserCompat.SearchResultReceiver localSearchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(paramString, paramBundle, paramSearchCallback, this.mHandler);
      try
      {
        this.mServiceBinderWrapper.search(paramString, paramBundle, localSearchResultReceiver, this.mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Remote error searching items with query: ");
        localStringBuilder2.append(paramString);
        Log.i("MediaBrowserCompat", localStringBuilder2.toString(), localRemoteException);
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramSearchCallback.onError(paramString, paramBundle);
          }
        });
      }
    }

    public void sendCustomAction(@NonNull final String paramString, final Bundle paramBundle, @Nullable final MediaBrowserCompat.CustomActionCallback paramCustomActionCallback)
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Cannot send a custom action (");
        localStringBuilder1.append(paramString);
        localStringBuilder1.append(") with ");
        localStringBuilder1.append("extras ");
        localStringBuilder1.append(paramBundle);
        localStringBuilder1.append(" because the browser is not connected to the ");
        localStringBuilder1.append("service.");
        throw new IllegalStateException(localStringBuilder1.toString());
      }
      MediaBrowserCompat.CustomActionResultReceiver localCustomActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(paramString, paramBundle, paramCustomActionCallback, this.mHandler);
      try
      {
        this.mServiceBinderWrapper.sendCustomAction(paramString, paramBundle, localCustomActionResultReceiver, this.mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Remote error sending a custom action: action=");
        localStringBuilder2.append(paramString);
        localStringBuilder2.append(", extras=");
        localStringBuilder2.append(paramBundle);
        Log.i("MediaBrowserCompat", localStringBuilder2.toString(), localRemoteException);
        if (paramCustomActionCallback != null)
          this.mHandler.post(new Runnable()
          {
            public void run()
            {
              paramCustomActionCallback.onError(paramString, paramBundle, null);
            }
          });
      }
    }

    // ERROR //
    public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 62	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   4: aload_1
      //   5: invokevirtual 301	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   8: checkcast 303	android/support/v4/media/MediaBrowserCompat$Subscription
      //   11: astore 4
      //   13: aload 4
      //   15: ifnonnull +23 -> 38
      //   18: new 303	android/support/v4/media/MediaBrowserCompat$Subscription
      //   21: dup
      //   22: invokespecial 443	android/support/v4/media/MediaBrowserCompat$Subscription:<init>	()V
      //   25: astore 4
      //   27: aload_0
      //   28: getfield 62	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   31: aload_1
      //   32: aload 4
      //   34: invokevirtual 447	android/support/v4/util/ArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   37: pop
      //   38: aload_2
      //   39: ifnonnull +9 -> 48
      //   42: aconst_null
      //   43: astore 6
      //   45: goto +13 -> 58
      //   48: new 83	android/os/Bundle
      //   51: dup
      //   52: aload_2
      //   53: invokespecial 86	android/os/Bundle:<init>	(Landroid/os/Bundle;)V
      //   56: astore 6
      //   58: aload 4
      //   60: aload 6
      //   62: aload_3
      //   63: invokevirtual 451	android/support/v4/media/MediaBrowserCompat$Subscription:putCallback	(Landroid/os/Bundle;Landroid/support/v4/media/MediaBrowserCompat$SubscriptionCallback;)V
      //   66: aload_0
      //   67: invokevirtual 205	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:isConnected	()Z
      //   70: ifeq +60 -> 130
      //   73: aload_0
      //   74: getfield 177	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   77: aload_1
      //   78: aload_3
      //   79: getfield 384	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:mToken	Landroid/os/IBinder;
      //   82: aload 6
      //   84: aload_0
      //   85: getfield 115	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
      //   88: invokevirtual 388	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:addSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Bundle;Landroid/os/Messenger;)V
      //   91: goto +39 -> 130
      //   94: new 92	java/lang/StringBuilder
      //   97: dup
      //   98: invokespecial 93	java/lang/StringBuilder:<init>	()V
      //   101: astore 7
      //   103: aload 7
      //   105: ldc_w 453
      //   108: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: pop
      //   112: aload 7
      //   114: aload_1
      //   115: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   118: pop
      //   119: ldc 126
      //   121: aload 7
      //   123: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   126: invokestatic 161	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   129: pop
      //   130: return
      //
      // Exception table:
      //   from	to	target	type
      //   73	91	94	android/os/RemoteException
    }

    // ERROR //
    public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 62	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   4: aload_1
      //   5: invokevirtual 301	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   8: checkcast 303	android/support/v4/media/MediaBrowserCompat$Subscription
      //   11: astore_3
      //   12: aload_3
      //   13: ifnonnull +4 -> 17
      //   16: return
      //   17: aload_2
      //   18: ifnonnull +26 -> 44
      //   21: aload_0
      //   22: invokevirtual 205	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:isConnected	()Z
      //   25: ifeq +145 -> 170
      //   28: aload_0
      //   29: getfield 177	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   32: aload_1
      //   33: aconst_null
      //   34: aload_0
      //   35: getfield 115	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
      //   38: invokevirtual 459	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:removeSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Messenger;)V
      //   41: goto +129 -> 170
      //   44: aload_3
      //   45: invokevirtual 368	android/support/v4/media/MediaBrowserCompat$Subscription:getCallbacks	()Ljava/util/List;
      //   48: astore 9
      //   50: aload_3
      //   51: invokevirtual 371	android/support/v4/media/MediaBrowserCompat$Subscription:getOptionsList	()Ljava/util/List;
      //   54: astore 10
      //   56: iconst_m1
      //   57: aload 9
      //   59: invokeinterface 377 1 0
      //   64: iadd
      //   65: istore 11
      //   67: iload 11
      //   69: iflt +101 -> 170
      //   72: aload 9
      //   74: iload 11
      //   76: invokeinterface 380 2 0
      //   81: aload_2
      //   82: if_acmpne +46 -> 128
      //   85: aload_0
      //   86: invokevirtual 205	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:isConnected	()Z
      //   89: ifeq +19 -> 108
      //   92: aload_0
      //   93: getfield 177	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   96: aload_1
      //   97: aload_2
      //   98: getfield 384	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:mToken	Landroid/os/IBinder;
      //   101: aload_0
      //   102: getfield 115	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
      //   105: invokevirtual 459	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:removeSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Messenger;)V
      //   108: aload 9
      //   110: iload 11
      //   112: invokeinterface 462 2 0
      //   117: pop
      //   118: aload 10
      //   120: iload 11
      //   122: invokeinterface 462 2 0
      //   127: pop
      //   128: iinc 11 255
      //   131: goto -64 -> 67
      //   134: new 92	java/lang/StringBuilder
      //   137: dup
      //   138: invokespecial 93	java/lang/StringBuilder:<init>	()V
      //   141: astore 4
      //   143: aload 4
      //   145: ldc_w 464
      //   148: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   151: pop
      //   152: aload 4
      //   154: aload_1
      //   155: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   158: pop
      //   159: ldc 126
      //   161: aload 4
      //   163: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   166: invokestatic 161	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   169: pop
      //   170: aload_3
      //   171: invokevirtual 466	android/support/v4/media/MediaBrowserCompat$Subscription:isEmpty	()Z
      //   174: ifne +7 -> 181
      //   177: aload_2
      //   178: ifnonnull +12 -> 190
      //   181: aload_0
      //   182: getfield 62	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   185: aload_1
      //   186: invokevirtual 468	android/support/v4/util/ArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   189: pop
      //   190: return
      //
      // Exception table:
      //   from	to	target	type
      //   21	128	134	android/os/RemoteException
    }

    private class MediaServiceConnection
      implements ServiceConnection
    {
      MediaServiceConnection()
      {
      }

      private void postOrRun(Runnable paramRunnable)
      {
        if (Thread.currentThread() == MediaBrowserCompat.MediaBrowserImplBase.this.mHandler.getLooper().getThread())
          paramRunnable.run();
        else
          MediaBrowserCompat.MediaBrowserImplBase.this.mHandler.post(paramRunnable);
      }

      boolean isCurrent(String paramString)
      {
        if ((MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection == this) && (MediaBrowserCompat.MediaBrowserImplBase.this.mState != 0) && (MediaBrowserCompat.MediaBrowserImplBase.this.mState != 1))
          return true;
        if ((MediaBrowserCompat.MediaBrowserImplBase.this.mState != 0) && (MediaBrowserCompat.MediaBrowserImplBase.this.mState != 1))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append(" for ");
          localStringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
          localStringBuilder.append(" with mServiceConnection=");
          localStringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection);
          localStringBuilder.append(" this=");
          localStringBuilder.append(this);
          Log.i("MediaBrowserCompat", localStringBuilder.toString());
        }
        return false;
      }

      public void onServiceConnected(final ComponentName paramComponentName, final IBinder paramIBinder)
      {
        postOrRun(new Runnable()
        {
          // ERROR //
          public void run()
          {
            // Byte code:
            //   0: getstatic 37	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
            //   3: ifeq +63 -> 66
            //   6: new 39	java/lang/StringBuilder
            //   9: dup
            //   10: invokespecial 40	java/lang/StringBuilder:<init>	()V
            //   13: astore_1
            //   14: aload_1
            //   15: ldc 42
            //   17: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   20: pop
            //   21: aload_1
            //   22: aload_0
            //   23: getfield 23	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:val$name	Landroid/content/ComponentName;
            //   26: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   29: pop
            //   30: aload_1
            //   31: ldc 51
            //   33: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   36: pop
            //   37: aload_1
            //   38: aload_0
            //   39: getfield 25	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:val$binder	Landroid/os/IBinder;
            //   42: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   45: pop
            //   46: ldc 53
            //   48: aload_1
            //   49: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   52: invokestatic 63	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   55: pop
            //   56: aload_0
            //   57: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   60: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   63: invokevirtual 72	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:dump	()V
            //   66: aload_0
            //   67: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   70: ldc 73
            //   72: invokevirtual 77	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:isCurrent	(Ljava/lang/String;)Z
            //   75: ifne +4 -> 79
            //   78: return
            //   79: aload_0
            //   80: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   83: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   86: new 79	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper
            //   89: dup
            //   90: aload_0
            //   91: getfield 25	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:val$binder	Landroid/os/IBinder;
            //   94: aload_0
            //   95: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   98: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   101: getfield 83	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mRootHints	Landroid/os/Bundle;
            //   104: invokespecial 86	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:<init>	(Landroid/os/IBinder;Landroid/os/Bundle;)V
            //   107: putfield 90	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
            //   110: aload_0
            //   111: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   114: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   117: new 92	android/os/Messenger
            //   120: dup
            //   121: aload_0
            //   122: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   125: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   128: getfield 96	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
            //   131: invokespecial 99	android/os/Messenger:<init>	(Landroid/os/Handler;)V
            //   134: putfield 103	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
            //   137: aload_0
            //   138: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   141: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   144: getfield 96	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mHandler	Landroid/support/v4/media/MediaBrowserCompat$CallbackHandler;
            //   147: aload_0
            //   148: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   151: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   154: getfield 103	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
            //   157: invokevirtual 109	android/support/v4/media/MediaBrowserCompat$CallbackHandler:setCallbacksMessenger	(Landroid/os/Messenger;)V
            //   160: aload_0
            //   161: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   164: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   167: iconst_2
            //   168: putfield 113	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mState	I
            //   171: getstatic 37	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
            //   174: ifeq +21 -> 195
            //   177: ldc 53
            //   179: ldc 115
            //   181: invokestatic 63	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   184: pop
            //   185: aload_0
            //   186: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   189: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   192: invokevirtual 72	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:dump	()V
            //   195: aload_0
            //   196: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   199: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   202: getfield 90	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
            //   205: aload_0
            //   206: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   209: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   212: getfield 119	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mContext	Landroid/content/Context;
            //   215: aload_0
            //   216: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   219: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   222: getfield 103	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mCallbacksMessenger	Landroid/os/Messenger;
            //   225: invokevirtual 123	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:connect	(Landroid/content/Context;Landroid/os/Messenger;)V
            //   228: goto +71 -> 299
            //   231: new 39	java/lang/StringBuilder
            //   234: dup
            //   235: invokespecial 40	java/lang/StringBuilder:<init>	()V
            //   238: astore 7
            //   240: aload 7
            //   242: ldc 125
            //   244: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   247: pop
            //   248: aload 7
            //   250: aload_0
            //   251: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   254: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   257: getfield 128	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:mServiceComponent	Landroid/content/ComponentName;
            //   260: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   263: pop
            //   264: ldc 53
            //   266: aload 7
            //   268: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   271: invokestatic 131	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
            //   274: pop
            //   275: getstatic 37	android/support/v4/media/MediaBrowserCompat:DEBUG	Z
            //   278: ifeq +21 -> 299
            //   281: ldc 53
            //   283: ldc 115
            //   285: invokestatic 63	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   288: pop
            //   289: aload_0
            //   290: getfield 21	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1:this$1	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
            //   293: getfield 67	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection:this$0	Landroid/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase;
            //   296: invokevirtual 72	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplBase:dump	()V
            //   299: return
            //
            // Exception table:
            //   from	to	target	type
            //   171	228	231	android/os/RemoteException
          }
        });
      }

      public void onServiceDisconnected(final ComponentName paramComponentName)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (MediaBrowserCompat.DEBUG)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
              localStringBuilder.append(paramComponentName);
              localStringBuilder.append(" this=");
              localStringBuilder.append(this);
              localStringBuilder.append(" mServiceConnection=");
              localStringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection);
              Log.d("MediaBrowserCompat", localStringBuilder.toString());
              MediaBrowserCompat.MediaBrowserImplBase.this.dump();
            }
            if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceDisconnected"))
              return;
            MediaBrowserCompat.MediaBrowserImplBase.this.mServiceBinderWrapper = null;
            MediaBrowserCompat.MediaBrowserImplBase.this.mCallbacksMessenger = null;
            MediaBrowserCompat.MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(null);
            MediaBrowserCompat.MediaBrowserImplBase.this.mState = 4;
            MediaBrowserCompat.MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
          }
        });
      }
    }
  }

  static abstract interface MediaBrowserServiceCallbackImpl
  {
    public abstract void onConnectionFailed(Messenger paramMessenger);

    public abstract void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle1, Bundle paramBundle2);

    public abstract void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle);
  }

  public static class MediaItem
    implements Parcelable
  {
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator()
    {
      public MediaBrowserCompat.MediaItem createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaBrowserCompat.MediaItem(paramAnonymousParcel);
      }

      public MediaBrowserCompat.MediaItem[] newArray(int paramAnonymousInt)
      {
        return new MediaBrowserCompat.MediaItem[paramAnonymousInt];
      }
    };
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;

    MediaItem(Parcel paramParcel)
    {
      this.mFlags = paramParcel.readInt();
      this.mDescription = ((MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel));
    }

    public MediaItem(@NonNull MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      if (paramMediaDescriptionCompat == null)
        throw new IllegalArgumentException("description cannot be null");
      if (TextUtils.isEmpty(paramMediaDescriptionCompat.getMediaId()))
        throw new IllegalArgumentException("description must have a non-empty media id");
      this.mFlags = paramInt;
      this.mDescription = paramMediaDescriptionCompat;
    }

    public static MediaItem fromMediaItem(Object paramObject)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
      {
        int i = MediaBrowserCompatApi21.MediaItem.getFlags(paramObject);
        return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(paramObject)), i);
      }
      return null;
    }

    public static List<MediaItem> fromMediaItemList(List<?> paramList)
    {
      if ((paramList != null) && (Build.VERSION.SDK_INT >= 21))
      {
        ArrayList localArrayList = new ArrayList(paramList.size());
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          localArrayList.add(fromMediaItem(localIterator.next()));
        return localArrayList;
      }
      return null;
    }

    public int describeContents()
    {
      return 0;
    }

    @NonNull
    public MediaDescriptionCompat getDescription()
    {
      return this.mDescription;
    }

    public int getFlags()
    {
      return this.mFlags;
    }

    @Nullable
    public String getMediaId()
    {
      return this.mDescription.getMediaId();
    }

    public boolean isBrowsable()
    {
      int i = this.mFlags;
      int j = 1;
      if ((i & j) == 0)
        j = 0;
      return j;
    }

    public boolean isPlayable()
    {
      boolean bool;
      if ((0x2 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("MediaItem{");
      localStringBuilder.append("mFlags=");
      localStringBuilder.append(this.mFlags);
      localStringBuilder.append(", mDescription=");
      localStringBuilder.append(this.mDescription);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.mFlags);
      this.mDescription.writeToParcel(paramParcel, paramInt);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface Flags
    {
    }
  }

  public static abstract class SearchCallback
  {
    public void onError(@NonNull String paramString, Bundle paramBundle)
    {
    }

    public void onSearchResult(@NonNull String paramString, Bundle paramBundle, @NonNull List<MediaBrowserCompat.MediaItem> paramList)
    {
    }
  }

  private static class SearchResultReceiver extends ResultReceiver
  {
    private final MediaBrowserCompat.SearchCallback mCallback;
    private final Bundle mExtras;
    private final String mQuery;

    SearchResultReceiver(String paramString, Bundle paramBundle, MediaBrowserCompat.SearchCallback paramSearchCallback, Handler paramHandler)
    {
      super();
      this.mQuery = paramString;
      this.mExtras = paramBundle;
      this.mCallback = paramSearchCallback;
    }

    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("search_results")))
      {
        Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("search_results");
        ArrayList localArrayList = null;
        if (arrayOfParcelable != null)
        {
          localArrayList = new ArrayList();
          int i = arrayOfParcelable.length;
          for (int j = 0; j < i; j++)
            localArrayList.add((MediaBrowserCompat.MediaItem)arrayOfParcelable[j]);
        }
        this.mCallback.onSearchResult(this.mQuery, this.mExtras, localArrayList);
        return;
      }
      this.mCallback.onError(this.mQuery, this.mExtras);
    }
  }

  private static class ServiceBinderWrapper
  {
    private Messenger mMessenger;
    private Bundle mRootHints;

    public ServiceBinderWrapper(IBinder paramIBinder, Bundle paramBundle)
    {
      this.mMessenger = new Messenger(paramIBinder);
      this.mRootHints = paramBundle;
    }

    private void sendRequest(int paramInt, Bundle paramBundle, Messenger paramMessenger)
    {
      Message localMessage = Message.obtain();
      localMessage.what = paramInt;
      localMessage.arg1 = 1;
      localMessage.setData(paramBundle);
      localMessage.replyTo = paramMessenger;
      this.mMessenger.send(localMessage);
    }

    void addSubscription(String paramString, IBinder paramIBinder, Bundle paramBundle, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      BundleCompat.putBinder(localBundle, "data_callback_token", paramIBinder);
      localBundle.putBundle("data_options", paramBundle);
      sendRequest(3, localBundle, paramMessenger);
    }

    void connect(Context paramContext, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_package_name", paramContext.getPackageName());
      localBundle.putBundle("data_root_hints", this.mRootHints);
      sendRequest(1, localBundle, paramMessenger);
    }

    void disconnect(Messenger paramMessenger)
    {
      sendRequest(2, null, paramMessenger);
    }

    void getMediaItem(String paramString, ResultReceiver paramResultReceiver, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(5, localBundle, paramMessenger);
    }

    void registerCallbackMessenger(Context paramContext, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_package_name", paramContext.getPackageName());
      localBundle.putBundle("data_root_hints", this.mRootHints);
      sendRequest(6, localBundle, paramMessenger);
    }

    void removeSubscription(String paramString, IBinder paramIBinder, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      BundleCompat.putBinder(localBundle, "data_callback_token", paramIBinder);
      sendRequest(4, localBundle, paramMessenger);
    }

    void search(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_search_query", paramString);
      localBundle.putBundle("data_search_extras", paramBundle);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(8, localBundle, paramMessenger);
    }

    void sendCustomAction(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver, Messenger paramMessenger)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_custom_action", paramString);
      localBundle.putBundle("data_custom_action_extras", paramBundle);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(9, localBundle, paramMessenger);
    }

    void unregisterCallbackMessenger(Messenger paramMessenger)
    {
      sendRequest(7, null, paramMessenger);
    }
  }

  private static class Subscription
  {
    private final List<MediaBrowserCompat.SubscriptionCallback> mCallbacks = new ArrayList();
    private final List<Bundle> mOptionsList = new ArrayList();

    public MediaBrowserCompat.SubscriptionCallback getCallback(Bundle paramBundle)
    {
      for (int i = 0; i < this.mOptionsList.size(); i++)
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), paramBundle))
          return (MediaBrowserCompat.SubscriptionCallback)this.mCallbacks.get(i);
      return null;
    }

    public List<MediaBrowserCompat.SubscriptionCallback> getCallbacks()
    {
      return this.mCallbacks;
    }

    public List<Bundle> getOptionsList()
    {
      return this.mOptionsList;
    }

    public boolean isEmpty()
    {
      return this.mCallbacks.isEmpty();
    }

    public void putCallback(Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      for (int i = 0; i < this.mOptionsList.size(); i++)
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), paramBundle))
        {
          this.mCallbacks.set(i, paramSubscriptionCallback);
          return;
        }
      this.mCallbacks.add(paramSubscriptionCallback);
      this.mOptionsList.add(paramBundle);
    }
  }

  public static abstract class SubscriptionCallback
  {
    final Object mSubscriptionCallbackObj;
    WeakReference<MediaBrowserCompat.Subscription> mSubscriptionRef;
    final IBinder mToken = new Binder();

    public SubscriptionCallback()
    {
      if (Build.VERSION.SDK_INT >= 26)
        this.mSubscriptionCallbackObj = MediaBrowserCompatApi26.createSubscriptionCallback(new StubApi26());
      else if (Build.VERSION.SDK_INT >= 21)
        this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
      else
        this.mSubscriptionCallbackObj = null;
    }

    public void onChildrenLoaded(@NonNull String paramString, @NonNull List<MediaBrowserCompat.MediaItem> paramList)
    {
    }

    public void onChildrenLoaded(@NonNull String paramString, @NonNull List<MediaBrowserCompat.MediaItem> paramList, @NonNull Bundle paramBundle)
    {
    }

    public void onError(@NonNull String paramString)
    {
    }

    public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
    {
    }

    void setSubscription(MediaBrowserCompat.Subscription paramSubscription)
    {
      this.mSubscriptionRef = new WeakReference(paramSubscription);
    }

    private class StubApi21
      implements MediaBrowserCompatApi21.SubscriptionCallback
    {
      StubApi21()
      {
      }

      List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      {
        if (paramList == null)
          return null;
        int i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
        int j = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if ((i == -1) && (j == -1))
          return paramList;
        int k = j * i;
        int m = k + j;
        if ((i >= 0) && (j >= 1) && (k < paramList.size()))
        {
          if (m > paramList.size())
            m = paramList.size();
          return paramList.subList(k, m);
        }
        return Collections.emptyList();
      }

      public void onChildrenLoaded(@NonNull String paramString, List<?> paramList)
      {
        MediaBrowserCompat.Subscription localSubscription;
        if (MediaBrowserCompat.SubscriptionCallback.this.mSubscriptionRef == null)
          localSubscription = null;
        else
          localSubscription = (MediaBrowserCompat.Subscription)MediaBrowserCompat.SubscriptionCallback.this.mSubscriptionRef.get();
        if (localSubscription == null)
        {
          MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, MediaBrowserCompat.MediaItem.fromMediaItemList(paramList));
        }
        else
        {
          List localList1 = MediaBrowserCompat.MediaItem.fromMediaItemList(paramList);
          List localList2 = localSubscription.getCallbacks();
          List localList3 = localSubscription.getOptionsList();
          for (int i = 0; i < localList2.size(); i++)
          {
            Bundle localBundle = (Bundle)localList3.get(i);
            if (localBundle == null)
              MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, localList1);
            else
              MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, applyOptions(localList1, localBundle), localBundle);
          }
        }
      }

      public void onError(@NonNull String paramString)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onError(paramString);
      }
    }

    private class StubApi26 extends MediaBrowserCompat.SubscriptionCallback.StubApi21
      implements MediaBrowserCompatApi26.SubscriptionCallback
    {
      StubApi26()
      {
        super();
      }

      public void onChildrenLoaded(@NonNull String paramString, List<?> paramList, @NonNull Bundle paramBundle)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, MediaBrowserCompat.MediaItem.fromMediaItemList(paramList), paramBundle);
      }

      public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onError(paramString, paramBundle);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat
 * JD-Core Version:    0.6.1
 */