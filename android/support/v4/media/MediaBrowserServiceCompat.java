package android.support.v4.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.service.media.MediaBrowserService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class MediaBrowserServiceCompat extends Service
{
  static final boolean DEBUG = false;
  private static final float EPSILON = 1.0E-005F;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String KEY_MEDIA_ITEM = "media_item";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final String KEY_SEARCH_RESULTS = "search_results";

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final int RESULT_ERROR = -1;
  static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
  static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
  static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final int RESULT_OK = 0;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static final int RESULT_PROGRESS_UPDATE = 1;
  public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
  static final String TAG = "MBServiceCompat";
  final ArrayMap<IBinder, ConnectionRecord> mConnections = new ArrayMap();
  ConnectionRecord mCurConnection;
  final ServiceHandler mHandler = new ServiceHandler();
  private MediaBrowserServiceImpl mImpl;
  MediaSessionCompat.Token mSession;

  void addSubscription(String paramString, ConnectionRecord paramConnectionRecord, IBinder paramIBinder, Bundle paramBundle)
  {
    Object localObject = (List)paramConnectionRecord.subscriptions.get(paramString);
    if (localObject == null)
      localObject = new ArrayList();
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      if ((paramIBinder == localPair.first) && (MediaBrowserCompatUtils.areSameOptions(paramBundle, (Bundle)localPair.second)))
        return;
    }
    ((List)localObject).add(new Pair(paramIBinder, paramBundle));
    paramConnectionRecord.subscriptions.put(paramString, localObject);
    performLoadChildren(paramString, paramConnectionRecord, paramBundle, null);
    this.mCurConnection = paramConnectionRecord;
    onSubscribe(paramString, paramBundle);
    this.mCurConnection = null;
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

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public void attachToBaseContext(Context paramContext)
  {
    attachBaseContext(paramContext);
  }

  public void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
  }

  public final Bundle getBrowserRootHints()
  {
    return this.mImpl.getBrowserRootHints();
  }

  @NonNull
  public final MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo()
  {
    return this.mImpl.getCurrentBrowserInfo();
  }

  @Nullable
  public MediaSessionCompat.Token getSessionToken()
  {
    return this.mSession;
  }

  boolean isValidPackage(String paramString, int paramInt)
  {
    if (paramString == null)
      return false;
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (arrayOfString[j].equals(paramString))
        return true;
    return false;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void notifyChildrenChanged(@NonNull MediaSessionManager.RemoteUserInfo paramRemoteUserInfo, @NonNull String paramString, @NonNull Bundle paramBundle)
  {
    if (paramRemoteUserInfo == null)
      throw new IllegalArgumentException("remoteUserInfo cannot be null in notifyChildrenChanged");
    if (paramString == null)
      throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    if (paramBundle == null)
      throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    this.mImpl.notifyChildrenChanged(paramRemoteUserInfo, paramString, paramBundle);
  }

  public void notifyChildrenChanged(@NonNull String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    this.mImpl.notifyChildrenChanged(paramString, null);
  }

  public void notifyChildrenChanged(@NonNull String paramString, @NonNull Bundle paramBundle)
  {
    if (paramString == null)
      throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    if (paramBundle == null)
      throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    this.mImpl.notifyChildrenChanged(paramString, paramBundle);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mImpl.onBind(paramIntent);
  }

  public void onCreate()
  {
    super.onCreate();
    if (Build.VERSION.SDK_INT >= 28)
      this.mImpl = new MediaBrowserServiceImplApi28();
    else if (Build.VERSION.SDK_INT >= 26)
      this.mImpl = new MediaBrowserServiceImplApi26();
    else if (Build.VERSION.SDK_INT >= 23)
      this.mImpl = new MediaBrowserServiceImplApi23();
    else if (Build.VERSION.SDK_INT >= 21)
      this.mImpl = new MediaBrowserServiceImplApi21();
    else
      this.mImpl = new MediaBrowserServiceImplBase();
    this.mImpl.onCreate();
  }

  public void onCustomAction(@NonNull String paramString, Bundle paramBundle, @NonNull Result<Bundle> paramResult)
  {
    paramResult.sendError(null);
  }

  @Nullable
  public abstract BrowserRoot onGetRoot(@NonNull String paramString, int paramInt, @Nullable Bundle paramBundle);

  public abstract void onLoadChildren(@NonNull String paramString, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult);

  public void onLoadChildren(@NonNull String paramString, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult, @NonNull Bundle paramBundle)
  {
    paramResult.setFlags(1);
    onLoadChildren(paramString, paramResult);
  }

  public void onLoadItem(String paramString, @NonNull Result<MediaBrowserCompat.MediaItem> paramResult)
  {
    paramResult.setFlags(2);
    paramResult.sendResult(null);
  }

  public void onSearch(@NonNull String paramString, Bundle paramBundle, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult)
  {
    paramResult.setFlags(4);
    paramResult.sendResult(null);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void onSubscribe(String paramString, Bundle paramBundle)
  {
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void onUnsubscribe(String paramString)
  {
  }

  void performCustomAction(String paramString, Bundle paramBundle, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    Result local4 = new Result(paramString)
    {
      void onErrorSent(Bundle paramAnonymousBundle)
      {
        paramResultReceiver.send(-1, paramAnonymousBundle);
      }

      void onProgressUpdateSent(Bundle paramAnonymousBundle)
      {
        paramResultReceiver.send(1, paramAnonymousBundle);
      }

      void onResultSent(Bundle paramAnonymousBundle)
      {
        paramResultReceiver.send(0, paramAnonymousBundle);
      }
    };
    this.mCurConnection = paramConnectionRecord;
    onCustomAction(paramString, paramBundle, local4);
    this.mCurConnection = null;
    if (!local4.isDone())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onCustomAction must call detach() or sendResult() or sendError() before returning for action=");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" extras=");
      localStringBuilder.append(paramBundle);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }

  void performLoadChildren(final String paramString, final ConnectionRecord paramConnectionRecord, final Bundle paramBundle1, final Bundle paramBundle2)
  {
    Result local1 = new Result(paramString)
    {
      // ERROR //
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 23	android/support/v4/media/MediaBrowserServiceCompat$1:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
        //   4: getfield 46	android/support/v4/media/MediaBrowserServiceCompat:mConnections	Landroid/support/v4/util/ArrayMap;
        //   7: aload_0
        //   8: getfield 25	android/support/v4/media/MediaBrowserServiceCompat$1:val$connection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
        //   11: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
        //   14: invokeinterface 58 1 0
        //   19: invokevirtual 64	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   22: aload_0
        //   23: getfield 25	android/support/v4/media/MediaBrowserServiceCompat$1:val$connection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
        //   26: if_acmpeq +69 -> 95
        //   29: getstatic 68	android/support/v4/media/MediaBrowserServiceCompat:DEBUG	Z
        //   32: ifeq +62 -> 94
        //   35: new 70	java/lang/StringBuilder
        //   38: dup
        //   39: invokespecial 73	java/lang/StringBuilder:<init>	()V
        //   42: astore 8
        //   44: aload 8
        //   46: ldc 75
        //   48: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   51: pop
        //   52: aload 8
        //   54: aload_0
        //   55: getfield 25	android/support/v4/media/MediaBrowserServiceCompat$1:val$connection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
        //   58: getfield 82	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:pkg	Ljava/lang/String;
        //   61: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   64: pop
        //   65: aload 8
        //   67: ldc 84
        //   69: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   72: pop
        //   73: aload 8
        //   75: aload_0
        //   76: getfield 27	android/support/v4/media/MediaBrowserServiceCompat$1:val$parentId	Ljava/lang/String;
        //   79: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   82: pop
        //   83: ldc 86
        //   85: aload 8
        //   87: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   90: invokestatic 96	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   93: pop
        //   94: return
        //   95: iconst_1
        //   96: aload_0
        //   97: invokevirtual 100	android/support/v4/media/MediaBrowserServiceCompat$1:getFlags	()I
        //   100: iand
        //   101: ifeq +16 -> 117
        //   104: aload_0
        //   105: getfield 23	android/support/v4/media/MediaBrowserServiceCompat$1:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
        //   108: aload_1
        //   109: aload_0
        //   110: getfield 29	android/support/v4/media/MediaBrowserServiceCompat$1:val$subscribeOptions	Landroid/os/Bundle;
        //   113: invokevirtual 104	android/support/v4/media/MediaBrowserServiceCompat:applyOptions	(Ljava/util/List;Landroid/os/Bundle;)Ljava/util/List;
        //   116: astore_1
        //   117: aload_0
        //   118: getfield 25	android/support/v4/media/MediaBrowserServiceCompat$1:val$connection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
        //   121: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
        //   124: aload_0
        //   125: getfield 27	android/support/v4/media/MediaBrowserServiceCompat$1:val$parentId	Ljava/lang/String;
        //   128: aload_1
        //   129: aload_0
        //   130: getfield 29	android/support/v4/media/MediaBrowserServiceCompat$1:val$subscribeOptions	Landroid/os/Bundle;
        //   133: aload_0
        //   134: getfield 31	android/support/v4/media/MediaBrowserServiceCompat$1:val$notifyChildrenChangedOptions	Landroid/os/Bundle;
        //   137: invokeinterface 108 5 0
        //   142: goto +56 -> 198
        //   145: new 70	java/lang/StringBuilder
        //   148: dup
        //   149: invokespecial 73	java/lang/StringBuilder:<init>	()V
        //   152: astore_2
        //   153: aload_2
        //   154: ldc 110
        //   156: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: pop
        //   160: aload_2
        //   161: aload_0
        //   162: getfield 27	android/support/v4/media/MediaBrowserServiceCompat$1:val$parentId	Ljava/lang/String;
        //   165: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: pop
        //   169: aload_2
        //   170: ldc 112
        //   172: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: pop
        //   176: aload_2
        //   177: aload_0
        //   178: getfield 25	android/support/v4/media/MediaBrowserServiceCompat$1:val$connection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
        //   181: getfield 82	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:pkg	Ljava/lang/String;
        //   184: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: pop
        //   188: ldc 86
        //   190: aload_2
        //   191: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   194: invokestatic 115	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
        //   197: pop
        //   198: return
        //
        // Exception table:
        //   from	to	target	type
        //   117	142	145	android/os/RemoteException
      }
    };
    this.mCurConnection = paramConnectionRecord;
    if (paramBundle1 == null)
      onLoadChildren(paramString, local1);
    else
      onLoadChildren(paramString, local1, paramBundle1);
    this.mCurConnection = null;
    if (!local1.isDone())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onLoadChildren must call detach() or sendResult() before returning for package=");
      localStringBuilder.append(paramConnectionRecord.pkg);
      localStringBuilder.append(" id=");
      localStringBuilder.append(paramString);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }

  void performLoadItem(String paramString, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    Result local2 = new Result(paramString)
    {
      void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem)
      {
        if ((0x2 & getFlags()) != 0)
        {
          paramResultReceiver.send(-1, null);
          return;
        }
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("media_item", paramAnonymousMediaItem);
        paramResultReceiver.send(0, localBundle);
      }
    };
    this.mCurConnection = paramConnectionRecord;
    onLoadItem(paramString, local2);
    this.mCurConnection = null;
    if (!local2.isDone())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onLoadItem must call detach() or sendResult() before returning for id=");
      localStringBuilder.append(paramString);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }

  void performSearch(String paramString, Bundle paramBundle, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    Result local3 = new Result(paramString)
    {
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList)
      {
        if (((0x4 & getFlags()) == 0) && (paramAnonymousList != null))
        {
          Bundle localBundle = new Bundle();
          localBundle.putParcelableArray("search_results", (Parcelable[])paramAnonymousList.toArray(new MediaBrowserCompat.MediaItem[0]));
          paramResultReceiver.send(0, localBundle);
          return;
        }
        paramResultReceiver.send(-1, null);
      }
    };
    this.mCurConnection = paramConnectionRecord;
    onSearch(paramString, paramBundle, local3);
    this.mCurConnection = null;
    if (!local3.isDone())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onSearch must call detach() or sendResult() before returning for query=");
      localStringBuilder.append(paramString);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }

  boolean removeSubscription(String paramString, ConnectionRecord paramConnectionRecord, IBinder paramIBinder)
  {
    if (paramIBinder == null);
    while (true)
    {
      try
      {
        Object localObject2 = paramConnectionRecord.subscriptions.remove(paramString);
        bool = false;
        if (localObject2 != null)
          bool = true;
        this.mCurConnection = paramConnectionRecord;
        onUnsubscribe(paramString);
        this.mCurConnection = null;
        return bool;
      }
      finally
      {
        break;
      }
      List localList = (List)paramConnectionRecord.subscriptions.get(paramString);
      boolean bool = false;
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
          if (paramIBinder == ((Pair)localIterator.next()).first)
          {
            localIterator.remove();
            bool = true;
          }
        if (localList.size() == 0)
          paramConnectionRecord.subscriptions.remove(paramString);
      }
    }
    this.mCurConnection = paramConnectionRecord;
    onUnsubscribe(paramString);
    this.mCurConnection = null;
    throw localObject1;
  }

  public void setSessionToken(MediaSessionCompat.Token paramToken)
  {
    if (paramToken == null)
      throw new IllegalArgumentException("Session token may not be null.");
    if (this.mSession != null)
      throw new IllegalStateException("The session token has already been set.");
    this.mSession = paramToken;
    this.mImpl.setSessionToken(paramToken);
  }

  public static final class BrowserRoot
  {
    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";

    @Deprecated
    public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
    private final Bundle mExtras;
    private final String mRootId;

    public BrowserRoot(@NonNull String paramString, @Nullable Bundle paramBundle)
    {
      if (paramString == null)
        throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
      this.mRootId = paramString;
      this.mExtras = paramBundle;
    }

    public Bundle getExtras()
    {
      return this.mExtras;
    }

    public String getRootId()
    {
      return this.mRootId;
    }
  }

  private class ConnectionRecord
    implements IBinder.DeathRecipient
  {
    public final MediaSessionManager.RemoteUserInfo browserInfo;
    public final MediaBrowserServiceCompat.ServiceCallbacks callbacks;
    public final int pid;
    public final String pkg;
    public MediaBrowserServiceCompat.BrowserRoot root;
    public final Bundle rootHints;
    public final HashMap<String, List<Pair<IBinder, Bundle>>> subscriptions = new HashMap();
    public final int uid;

    ConnectionRecord(String paramInt1, int paramInt2, int paramBundle, Bundle paramServiceCallbacks, MediaBrowserServiceCompat.ServiceCallbacks arg6)
    {
      this.pkg = paramInt1;
      this.pid = paramInt2;
      this.uid = paramBundle;
      this.browserInfo = new MediaSessionManager.RemoteUserInfo(paramInt1, paramInt2, paramBundle);
      this.rootHints = paramServiceCallbacks;
      Object localObject;
      this.callbacks = localObject;
    }

    public void binderDied()
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          MediaBrowserServiceCompat.this.mConnections.remove(MediaBrowserServiceCompat.ConnectionRecord.this.callbacks.asBinder());
        }
      });
    }
  }

  static abstract interface MediaBrowserServiceImpl
  {
    public abstract Bundle getBrowserRootHints();

    public abstract MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo();

    public abstract void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo, String paramString, Bundle paramBundle);

    public abstract void notifyChildrenChanged(String paramString, Bundle paramBundle);

    public abstract IBinder onBind(Intent paramIntent);

    public abstract void onCreate();

    public abstract void setSessionToken(MediaSessionCompat.Token paramToken);
  }

  @RequiresApi(21)
  class MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy
  {
    Messenger mMessenger;
    final List<Bundle> mRootExtrasList = new ArrayList();
    Object mServiceObj;

    MediaBrowserServiceImplApi21()
    {
    }

    public Bundle getBrowserRootHints()
    {
      if (this.mMessenger == null)
        return null;
      if (MediaBrowserServiceCompat.this.mCurConnection == null)
        throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
      Bundle localBundle;
      if (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null)
        localBundle = null;
      else
        localBundle = new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
      return localBundle;
    }

    public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection == null)
        throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
      return MediaBrowserServiceCompat.this.mCurConnection.browserInfo;
    }

    public void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo paramRemoteUserInfo, String paramString, Bundle paramBundle)
    {
      notifyChildrenChangedForCompat(paramRemoteUserInfo, paramString, paramBundle);
    }

    public void notifyChildrenChanged(String paramString, Bundle paramBundle)
    {
      notifyChildrenChangedForFramework(paramString, paramBundle);
      notifyChildrenChangedForCompat(paramString, paramBundle);
    }

    void notifyChildrenChangedForCompat(final MediaSessionManager.RemoteUserInfo paramRemoteUserInfo, final String paramString, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          for (int i = 0; i < MediaBrowserServiceCompat.this.mConnections.size(); i++)
          {
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.valueAt(i);
            if (localConnectionRecord.browserInfo.equals(paramRemoteUserInfo))
              MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this.notifyChildrenChangedForCompatOnHandler(localConnectionRecord, paramString, paramBundle);
          }
        }
      });
    }

    void notifyChildrenChangedForCompat(final String paramString, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
          while (localIterator.hasNext())
          {
            IBinder localIBinder = (IBinder)localIterator.next();
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
            MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this.notifyChildrenChangedForCompatOnHandler(localConnectionRecord, paramString, paramBundle);
          }
        }
      });
    }

    void notifyChildrenChangedForCompatOnHandler(MediaBrowserServiceCompat.ConnectionRecord paramConnectionRecord, String paramString, Bundle paramBundle)
    {
      List localList = (List)paramConnectionRecord.subscriptions.get(paramString);
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          Pair localPair = (Pair)localIterator.next();
          if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)localPair.second))
            MediaBrowserServiceCompat.this.performLoadChildren(paramString, paramConnectionRecord, (Bundle)localPair.second, paramBundle);
        }
      }
    }

    void notifyChildrenChangedForFramework(String paramString, Bundle paramBundle)
    {
      MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, paramString);
    }

    public IBinder onBind(Intent paramIntent)
    {
      return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, paramIntent);
    }

    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi21.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
    }

    public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
    {
      Bundle localBundle;
      if ((paramBundle != null) && (paramBundle.getInt("extra_client_version", 0) != 0))
      {
        paramBundle.remove("extra_client_version");
        this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
        localBundle = new Bundle();
        localBundle.putInt("extra_service_version", 2);
        BundleCompat.putBinder(localBundle, "extra_messenger", this.mMessenger.getBinder());
        if (MediaBrowserServiceCompat.this.mSession != null)
        {
          IMediaSession localIMediaSession = MediaBrowserServiceCompat.this.mSession.getExtraBinder();
          IBinder localIBinder;
          if (localIMediaSession == null)
            localIBinder = null;
          else
            localIBinder = localIMediaSession.asBinder();
          BundleCompat.putBinder(localBundle, "extra_session_binder", localIBinder);
        }
        else
        {
          this.mRootExtrasList.add(localBundle);
        }
      }
      else
      {
        localBundle = null;
      }
      MediaBrowserServiceCompat localMediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
      MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(MediaBrowserServiceCompat.this, paramString, -1, paramInt, paramBundle, null);
      localMediaBrowserServiceCompat.mCurConnection = localConnectionRecord;
      MediaBrowserServiceCompat.BrowserRoot localBrowserRoot = MediaBrowserServiceCompat.this.onGetRoot(paramString, paramInt, paramBundle);
      MediaBrowserServiceCompat.this.mCurConnection = null;
      if (localBrowserRoot == null)
        return null;
      if (localBundle == null)
        localBundle = localBrowserRoot.getExtras();
      else if (localBrowserRoot.getExtras() != null)
        localBundle.putAll(localBrowserRoot.getExtras());
      return new MediaBrowserServiceCompatApi21.BrowserRoot(localBrowserRoot.getRootId(), localBundle);
    }

    public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> paramResultWrapper)
    {
      MediaBrowserServiceCompat.Result local2 = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }

        void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList)
        {
          if (paramAnonymousList != null)
          {
            localArrayList = new ArrayList();
            Iterator localIterator = paramAnonymousList.iterator();
            while (localIterator.hasNext())
            {
              MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)localIterator.next();
              Parcel localParcel = Parcel.obtain();
              localMediaItem.writeToParcel(localParcel, 0);
              localArrayList.add(localParcel);
            }
          }
          ArrayList localArrayList = null;
          paramResultWrapper.sendResult(localArrayList);
        }
      };
      MediaBrowserServiceCompat.this.onLoadChildren(paramString, local2);
    }

    public void setSessionToken(final MediaSessionCompat.Token paramToken)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          if (!MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this.mRootExtrasList.isEmpty())
          {
            IMediaSession localIMediaSession = paramToken.getExtraBinder();
            if (localIMediaSession != null)
            {
              Iterator localIterator = MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this.mRootExtrasList.iterator();
              while (localIterator.hasNext())
                BundleCompat.putBinder((Bundle)localIterator.next(), "extra_session_binder", localIMediaSession.asBinder());
            }
            MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this.mRootExtrasList.clear();
          }
          MediaBrowserServiceCompatApi21.setSessionToken(MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.this.mServiceObj, paramToken.getToken());
        }
      });
    }
  }

  @RequiresApi(23)
  class MediaBrowserServiceImplApi23 extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompatApi23.ServiceCompatProxy
  {
    MediaBrowserServiceImplApi23()
    {
      super();
    }

    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi23.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
    }

    public void onLoadItem(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> paramResultWrapper)
    {
      MediaBrowserServiceCompat.Result local1 = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }

        void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem)
        {
          if (paramAnonymousMediaItem == null)
          {
            paramResultWrapper.sendResult(null);
          }
          else
          {
            Parcel localParcel = Parcel.obtain();
            paramAnonymousMediaItem.writeToParcel(localParcel, 0);
            paramResultWrapper.sendResult(localParcel);
          }
        }
      };
      MediaBrowserServiceCompat.this.onLoadItem(paramString, local1);
    }
  }

  @RequiresApi(26)
  class MediaBrowserServiceImplApi26 extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi23
    implements MediaBrowserServiceCompatApi26.ServiceCompatProxy
  {
    MediaBrowserServiceImplApi26()
    {
      super();
    }

    public Bundle getBrowserRootHints()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection != null)
      {
        Bundle localBundle;
        if (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null)
          localBundle = null;
        else
          localBundle = new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
        return localBundle;
      }
      return MediaBrowserServiceCompatApi26.getBrowserRootHints(this.mServiceObj);
    }

    void notifyChildrenChangedForFramework(String paramString, Bundle paramBundle)
    {
      if (paramBundle != null)
        MediaBrowserServiceCompatApi26.notifyChildrenChanged(this.mServiceObj, paramString, paramBundle);
      else
        super.notifyChildrenChangedForFramework(paramString, paramBundle);
    }

    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi26.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
    }

    public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi26.ResultWrapper paramResultWrapper, Bundle paramBundle)
    {
      MediaBrowserServiceCompat.Result local1 = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }

        void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList)
        {
          if (paramAnonymousList != null)
          {
            localArrayList = new ArrayList();
            Iterator localIterator = paramAnonymousList.iterator();
            while (localIterator.hasNext())
            {
              MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)localIterator.next();
              Parcel localParcel = Parcel.obtain();
              localMediaItem.writeToParcel(localParcel, 0);
              localArrayList.add(localParcel);
            }
          }
          ArrayList localArrayList = null;
          paramResultWrapper.sendResult(localArrayList, getFlags());
        }
      };
      MediaBrowserServiceCompat.this.onLoadChildren(paramString, local1, paramBundle);
    }
  }

  @RequiresApi(28)
  class MediaBrowserServiceImplApi28 extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi26
  {
    MediaBrowserServiceImplApi28()
    {
      super();
    }

    public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection != null)
        return MediaBrowserServiceCompat.this.mCurConnection.browserInfo;
      return new MediaSessionManager.RemoteUserInfo(((MediaBrowserService)this.mServiceObj).getCurrentBrowserInfo());
    }
  }

  class MediaBrowserServiceImplBase
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
  {
    private Messenger mMessenger;

    MediaBrowserServiceImplBase()
    {
    }

    public Bundle getBrowserRootHints()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection == null)
        throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
      Bundle localBundle;
      if (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null)
        localBundle = null;
      else
        localBundle = new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
      return localBundle;
    }

    public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection == null)
        throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
      return MediaBrowserServiceCompat.this.mCurConnection.browserInfo;
    }

    public void notifyChildrenChanged(@NonNull final MediaSessionManager.RemoteUserInfo paramRemoteUserInfo, @NonNull final String paramString, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          for (int i = 0; i < MediaBrowserServiceCompat.this.mConnections.size(); i++)
          {
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.valueAt(i);
            if (localConnectionRecord.browserInfo.equals(paramRemoteUserInfo))
            {
              MediaBrowserServiceCompat.MediaBrowserServiceImplBase.this.notifyChildrenChangedOnHandler(localConnectionRecord, paramString, paramBundle);
              break;
            }
          }
        }
      });
    }

    public void notifyChildrenChanged(@NonNull final String paramString, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
          while (localIterator.hasNext())
          {
            IBinder localIBinder = (IBinder)localIterator.next();
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
            MediaBrowserServiceCompat.MediaBrowserServiceImplBase.this.notifyChildrenChangedOnHandler(localConnectionRecord, paramString, paramBundle);
          }
        }
      });
    }

    void notifyChildrenChangedOnHandler(MediaBrowserServiceCompat.ConnectionRecord paramConnectionRecord, String paramString, Bundle paramBundle)
    {
      List localList = (List)paramConnectionRecord.subscriptions.get(paramString);
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          Pair localPair = (Pair)localIterator.next();
          if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)localPair.second))
            MediaBrowserServiceCompat.this.performLoadChildren(paramString, paramConnectionRecord, (Bundle)localPair.second, paramBundle);
        }
      }
    }

    public IBinder onBind(Intent paramIntent)
    {
      if ("android.media.browse.MediaBrowserService".equals(paramIntent.getAction()))
        return this.mMessenger.getBinder();
      return null;
    }

    public void onCreate()
    {
      this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
    }

    public void setSessionToken(final MediaSessionCompat.Token paramToken)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.values().iterator();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord;
          StringBuilder localStringBuilder;
          while (localIterator.hasNext())
          {
            localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)localIterator.next();
            try
            {
              localConnectionRecord.callbacks.onConnect(localConnectionRecord.root.getRootId(), paramToken, localConnectionRecord.root.getExtras());
            }
            catch (RemoteException localRemoteException)
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Connection for ");
              localStringBuilder.append(localConnectionRecord.pkg);
              localStringBuilder.append(" is no longer valid.");
              Log.w("MBServiceCompat", localStringBuilder.toString());
              localIterator.remove();
              tmpTernaryOp = localRemoteException;
            }
          }
        }
      });
    }
  }

  public static class Result<T>
  {
    private final Object mDebug;
    private boolean mDetachCalled;
    private int mFlags;
    private boolean mSendErrorCalled;
    private boolean mSendProgressUpdateCalled;
    private boolean mSendResultCalled;

    Result(Object paramObject)
    {
      this.mDebug = paramObject;
    }

    private void checkExtraFields(Bundle paramBundle)
    {
      if (paramBundle == null)
        return;
      if (paramBundle.containsKey("android.media.browse.extra.DOWNLOAD_PROGRESS"))
      {
        float f = paramBundle.getFloat("android.media.browse.extra.DOWNLOAD_PROGRESS");
        if ((f < -1.0E-005F) || (f > 1.00001F))
          throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
      }
    }

    public void detach()
    {
      if (this.mDetachCalled)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("detach() called when detach() had already been called for: ");
        localStringBuilder1.append(this.mDebug);
        throw new IllegalStateException(localStringBuilder1.toString());
      }
      if (this.mSendResultCalled)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("detach() called when sendResult() had already been called for: ");
        localStringBuilder2.append(this.mDebug);
        throw new IllegalStateException(localStringBuilder2.toString());
      }
      if (this.mSendErrorCalled)
      {
        StringBuilder localStringBuilder3 = new StringBuilder();
        localStringBuilder3.append("detach() called when sendError() had already been called for: ");
        localStringBuilder3.append(this.mDebug);
        throw new IllegalStateException(localStringBuilder3.toString());
      }
      this.mDetachCalled = true;
    }

    int getFlags()
    {
      return this.mFlags;
    }

    boolean isDone()
    {
      boolean bool;
      if ((!this.mDetachCalled) && (!this.mSendResultCalled) && (!this.mSendErrorCalled))
        bool = false;
      else
        bool = true;
      return bool;
    }

    void onErrorSent(Bundle paramBundle)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("It is not supported to send an error for ");
      localStringBuilder.append(this.mDebug);
      throw new UnsupportedOperationException(localStringBuilder.toString());
    }

    void onProgressUpdateSent(Bundle paramBundle)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("It is not supported to send an interim update for ");
      localStringBuilder.append(this.mDebug);
      throw new UnsupportedOperationException(localStringBuilder.toString());
    }

    void onResultSent(T paramT)
    {
    }

    public void sendError(Bundle paramBundle)
    {
      if ((!this.mSendResultCalled) && (!this.mSendErrorCalled))
      {
        this.mSendErrorCalled = true;
        onErrorSent(paramBundle);
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("sendError() called when either sendResult() or sendError() had already been called for: ");
      localStringBuilder.append(this.mDebug);
      throw new IllegalStateException(localStringBuilder.toString());
    }

    public void sendProgressUpdate(Bundle paramBundle)
    {
      if ((!this.mSendResultCalled) && (!this.mSendErrorCalled))
      {
        checkExtraFields(paramBundle);
        this.mSendProgressUpdateCalled = true;
        onProgressUpdateSent(paramBundle);
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: ");
      localStringBuilder.append(this.mDebug);
      throw new IllegalStateException(localStringBuilder.toString());
    }

    public void sendResult(T paramT)
    {
      if ((!this.mSendResultCalled) && (!this.mSendErrorCalled))
      {
        this.mSendResultCalled = true;
        onResultSent(paramT);
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("sendResult() called when either sendResult() or sendError() had already been called for: ");
      localStringBuilder.append(this.mDebug);
      throw new IllegalStateException(localStringBuilder.toString());
    }

    void setFlags(int paramInt)
    {
      this.mFlags = paramInt;
    }
  }

  private class ServiceBinderImpl
  {
    ServiceBinderImpl()
    {
    }

    public void addSubscription(final String paramString, final IBinder paramIBinder, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.ServiceHandler localServiceHandler = MediaBrowserServiceCompat.this.mHandler;
      Runnable local3 = new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
          if (localConnectionRecord == null)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("addSubscription for callback that isn't registered id=");
            localStringBuilder.append(paramString);
            Log.w("MBServiceCompat", localStringBuilder.toString());
            return;
          }
          MediaBrowserServiceCompat.this.addSubscription(paramString, localConnectionRecord, paramIBinder, paramBundle);
        }
      };
      localServiceHandler.postOrRun(local3);
    }

    public void connect(final String paramString, final int paramInt1, final int paramInt2, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (!MediaBrowserServiceCompat.this.isValidPackage(paramString, paramInt2))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Package/uid mismatch: uid=");
        localStringBuilder.append(paramInt2);
        localStringBuilder.append(" package=");
        localStringBuilder.append(paramString);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      MediaBrowserServiceCompat.ServiceHandler localServiceHandler = MediaBrowserServiceCompat.this.mHandler;
      Runnable local1 = new Runnable()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 28	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
          //   4: invokeinterface 48 1 0
          //   9: astore_1
          //   10: aload_0
          //   11: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   14: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   17: getfield 58	android/support/v4/media/MediaBrowserServiceCompat:mConnections	Landroid/support/v4/util/ArrayMap;
          //   20: aload_1
          //   21: invokevirtual 64	android/support/v4/util/ArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   24: pop
          //   25: new 66	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord
          //   28: dup
          //   29: aload_0
          //   30: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   33: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   36: aload_0
          //   37: getfield 30	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$pkg	Ljava/lang/String;
          //   40: aload_0
          //   41: getfield 32	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$pid	I
          //   44: aload_0
          //   45: getfield 34	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$uid	I
          //   48: aload_0
          //   49: getfield 36	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$rootHints	Landroid/os/Bundle;
          //   52: aload_0
          //   53: getfield 28	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
          //   56: invokespecial 69	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:<init>	(Landroid/support/v4/media/MediaBrowserServiceCompat;Ljava/lang/String;IILandroid/os/Bundle;Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;)V
          //   59: astore_3
          //   60: aload_0
          //   61: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   64: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   67: aload_3
          //   68: putfield 73	android/support/v4/media/MediaBrowserServiceCompat:mCurConnection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
          //   71: aload_3
          //   72: aload_0
          //   73: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   76: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   79: aload_0
          //   80: getfield 30	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$pkg	Ljava/lang/String;
          //   83: aload_0
          //   84: getfield 34	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$uid	I
          //   87: aload_0
          //   88: getfield 36	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$rootHints	Landroid/os/Bundle;
          //   91: invokevirtual 77	android/support/v4/media/MediaBrowserServiceCompat:onGetRoot	(Ljava/lang/String;ILandroid/os/Bundle;)Landroid/support/v4/media/MediaBrowserServiceCompat$BrowserRoot;
          //   94: putfield 81	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:root	Landroid/support/v4/media/MediaBrowserServiceCompat$BrowserRoot;
          //   97: aload_0
          //   98: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   101: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   104: aconst_null
          //   105: putfield 73	android/support/v4/media/MediaBrowserServiceCompat:mCurConnection	Landroid/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord;
          //   108: aload_3
          //   109: getfield 81	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:root	Landroid/support/v4/media/MediaBrowserServiceCompat$BrowserRoot;
          //   112: ifnonnull +115 -> 227
          //   115: new 83	java/lang/StringBuilder
          //   118: dup
          //   119: invokespecial 84	java/lang/StringBuilder:<init>	()V
          //   122: astore 4
          //   124: aload 4
          //   126: ldc 86
          //   128: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   131: pop
          //   132: aload 4
          //   134: aload_0
          //   135: getfield 30	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$pkg	Ljava/lang/String;
          //   138: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   141: pop
          //   142: aload 4
          //   144: ldc 92
          //   146: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   149: pop
          //   150: aload 4
          //   152: aload_0
          //   153: invokevirtual 96	java/lang/Object:getClass	()Ljava/lang/Class;
          //   156: invokevirtual 102	java/lang/Class:getName	()Ljava/lang/String;
          //   159: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   162: pop
          //   163: ldc 104
          //   165: aload 4
          //   167: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   170: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
          //   173: pop
          //   174: aload_0
          //   175: getfield 28	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
          //   178: invokeinterface 116 1 0
          //   183: goto +170 -> 353
          //   186: new 83	java/lang/StringBuilder
          //   189: dup
          //   190: invokespecial 84	java/lang/StringBuilder:<init>	()V
          //   193: astore 10
          //   195: aload 10
          //   197: ldc 118
          //   199: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   202: pop
          //   203: aload 10
          //   205: aload_0
          //   206: getfield 30	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$pkg	Ljava/lang/String;
          //   209: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   212: pop
          //   213: ldc 104
          //   215: aload 10
          //   217: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   220: invokestatic 121	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
          //   223: pop
          //   224: goto +129 -> 353
          //   227: aload_0
          //   228: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   231: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   234: getfield 58	android/support/v4/media/MediaBrowserServiceCompat:mConnections	Landroid/support/v4/util/ArrayMap;
          //   237: aload_1
          //   238: aload_3
          //   239: invokevirtual 125	android/support/v4/util/ArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
          //   242: pop
          //   243: aload_1
          //   244: aload_3
          //   245: iconst_0
          //   246: invokeinterface 131 3 0
          //   251: aload_0
          //   252: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   255: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   258: getfield 135	android/support/v4/media/MediaBrowserServiceCompat:mSession	Landroid/support/v4/media/session/MediaSessionCompat$Token;
          //   261: ifnull +92 -> 353
          //   264: aload_0
          //   265: getfield 28	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
          //   268: aload_3
          //   269: getfield 81	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:root	Landroid/support/v4/media/MediaBrowserServiceCompat$BrowserRoot;
          //   272: invokevirtual 140	android/support/v4/media/MediaBrowserServiceCompat$BrowserRoot:getRootId	()Ljava/lang/String;
          //   275: aload_0
          //   276: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   279: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   282: getfield 135	android/support/v4/media/MediaBrowserServiceCompat:mSession	Landroid/support/v4/media/session/MediaSessionCompat$Token;
          //   285: aload_3
          //   286: getfield 81	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:root	Landroid/support/v4/media/MediaBrowserServiceCompat$BrowserRoot;
          //   289: invokevirtual 144	android/support/v4/media/MediaBrowserServiceCompat$BrowserRoot:getExtras	()Landroid/os/Bundle;
          //   292: invokeinterface 148 4 0
          //   297: goto +56 -> 353
          //   300: new 83	java/lang/StringBuilder
          //   303: dup
          //   304: invokespecial 84	java/lang/StringBuilder:<init>	()V
          //   307: astore 14
          //   309: aload 14
          //   311: ldc 150
          //   313: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   316: pop
          //   317: aload 14
          //   319: aload_0
          //   320: getfield 30	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:val$pkg	Ljava/lang/String;
          //   323: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   326: pop
          //   327: ldc 104
          //   329: aload 14
          //   331: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   334: invokestatic 121	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
          //   337: pop
          //   338: aload_0
          //   339: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$1:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   342: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   345: getfield 58	android/support/v4/media/MediaBrowserServiceCompat:mConnections	Landroid/support/v4/util/ArrayMap;
          //   348: aload_1
          //   349: invokevirtual 64	android/support/v4/util/ArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   352: pop
          //   353: return
          //
          // Exception table:
          //   from	to	target	type
          //   174	183	186	android/os/RemoteException
          //   227	297	300	android/os/RemoteException
        }
      };
      localServiceHandler.postOrRun(local1);
    }

    public void disconnect(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          if (localConnectionRecord != null)
            localConnectionRecord.callbacks.asBinder().unlinkToDeath(localConnectionRecord, 0);
        }
      });
    }

    public void getMediaItem(final String paramString, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if ((!TextUtils.isEmpty(paramString)) && (paramResultReceiver != null))
      {
        MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
        {
          public void run()
          {
            IBinder localIBinder = paramServiceCallbacks.asBinder();
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
            if (localConnectionRecord == null)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("getMediaItem for callback that isn't registered id=");
              localStringBuilder.append(paramString);
              Log.w("MBServiceCompat", localStringBuilder.toString());
              return;
            }
            MediaBrowserServiceCompat.this.performLoadItem(paramString, localConnectionRecord, paramResultReceiver);
          }
        });
        return;
      }
    }

    public void registerCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, final String paramString, final int paramInt1, final int paramInt2, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.ServiceHandler localServiceHandler = MediaBrowserServiceCompat.this.mHandler;
      Runnable local6 = new Runnable()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 28	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:val$callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
          //   4: invokeinterface 48 1 0
          //   9: astore_1
          //   10: aload_0
          //   11: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   14: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   17: getfield 58	android/support/v4/media/MediaBrowserServiceCompat:mConnections	Landroid/support/v4/util/ArrayMap;
          //   20: aload_1
          //   21: invokevirtual 64	android/support/v4/util/ArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   24: pop
          //   25: new 66	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord
          //   28: dup
          //   29: aload_0
          //   30: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   33: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   36: aload_0
          //   37: getfield 30	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:val$pkg	Ljava/lang/String;
          //   40: aload_0
          //   41: getfield 32	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:val$pid	I
          //   44: aload_0
          //   45: getfield 34	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:val$uid	I
          //   48: aload_0
          //   49: getfield 36	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:val$rootHints	Landroid/os/Bundle;
          //   52: aload_0
          //   53: getfield 28	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:val$callbacks	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;
          //   56: invokespecial 69	android/support/v4/media/MediaBrowserServiceCompat$ConnectionRecord:<init>	(Landroid/support/v4/media/MediaBrowserServiceCompat;Ljava/lang/String;IILandroid/os/Bundle;Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceCallbacks;)V
          //   59: astore_3
          //   60: aload_0
          //   61: getfield 26	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl$6:this$1	Landroid/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl;
          //   64: getfield 52	android/support/v4/media/MediaBrowserServiceCompat$ServiceBinderImpl:this$0	Landroid/support/v4/media/MediaBrowserServiceCompat;
          //   67: getfield 58	android/support/v4/media/MediaBrowserServiceCompat:mConnections	Landroid/support/v4/util/ArrayMap;
          //   70: aload_1
          //   71: aload_3
          //   72: invokevirtual 73	android/support/v4/util/ArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
          //   75: pop
          //   76: aload_1
          //   77: aload_3
          //   78: iconst_0
          //   79: invokeinterface 79 3 0
          //   84: goto +11 -> 95
          //   87: ldc 81
          //   89: ldc 83
          //   91: invokestatic 89	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
          //   94: pop
          //   95: return
          //
          // Exception table:
          //   from	to	target	type
          //   76	84	87	android/os/RemoteException
        }
      };
      localServiceHandler.postOrRun(local6);
    }

    public void removeSubscription(final String paramString, final IBinder paramIBinder, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
          if (localConnectionRecord == null)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("removeSubscription for callback that isn't registered id=");
            localStringBuilder1.append(paramString);
            Log.w("MBServiceCompat", localStringBuilder1.toString());
            return;
          }
          if (!MediaBrowserServiceCompat.this.removeSubscription(paramString, localConnectionRecord, paramIBinder))
          {
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("removeSubscription called for ");
            localStringBuilder2.append(paramString);
            localStringBuilder2.append(" which is not subscribed");
            Log.w("MBServiceCompat", localStringBuilder2.toString());
          }
        }
      });
    }

    public void search(final String paramString, final Bundle paramBundle, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if ((!TextUtils.isEmpty(paramString)) && (paramResultReceiver != null))
      {
        MediaBrowserServiceCompat.ServiceHandler localServiceHandler = MediaBrowserServiceCompat.this.mHandler;
        Runnable local8 = new Runnable()
        {
          public void run()
          {
            IBinder localIBinder = paramServiceCallbacks.asBinder();
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
            if (localConnectionRecord == null)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("search for callback that isn't registered query=");
              localStringBuilder.append(paramString);
              Log.w("MBServiceCompat", localStringBuilder.toString());
              return;
            }
            MediaBrowserServiceCompat.this.performSearch(paramString, paramBundle, localConnectionRecord, paramResultReceiver);
          }
        };
        localServiceHandler.postOrRun(local8);
        return;
      }
    }

    public void sendCustomAction(final String paramString, final Bundle paramBundle, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if ((!TextUtils.isEmpty(paramString)) && (paramResultReceiver != null))
      {
        MediaBrowserServiceCompat.ServiceHandler localServiceHandler = MediaBrowserServiceCompat.this.mHandler;
        Runnable local9 = new Runnable()
        {
          public void run()
          {
            IBinder localIBinder = paramServiceCallbacks.asBinder();
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
            if (localConnectionRecord == null)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("sendCustomAction for callback that isn't registered action=");
              localStringBuilder.append(paramString);
              localStringBuilder.append(", extras=");
              localStringBuilder.append(paramBundle);
              Log.w("MBServiceCompat", localStringBuilder.toString());
              return;
            }
            MediaBrowserServiceCompat.this.performCustomAction(paramString, paramBundle, localConnectionRecord, paramResultReceiver);
          }
        };
        localServiceHandler.postOrRun(local9);
        return;
      }
    }

    public void unregisterCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          if (localConnectionRecord != null)
            localIBinder.unlinkToDeath(localConnectionRecord, 0);
        }
      });
    }
  }

  private static abstract interface ServiceCallbacks
  {
    public abstract IBinder asBinder();

    public abstract void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle);

    public abstract void onConnectFailed();

    public abstract void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle1, Bundle paramBundle2);
  }

  private static class ServiceCallbacksCompat
    implements MediaBrowserServiceCompat.ServiceCallbacks
  {
    final Messenger mCallbacks;

    ServiceCallbacksCompat(Messenger paramMessenger)
    {
      this.mCallbacks = paramMessenger;
    }

    private void sendRequest(int paramInt, Bundle paramBundle)
    {
      Message localMessage = Message.obtain();
      localMessage.what = paramInt;
      localMessage.arg1 = 2;
      localMessage.setData(paramBundle);
      this.mCallbacks.send(localMessage);
    }

    public IBinder asBinder()
    {
      return this.mCallbacks.getBinder();
    }

    public void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
      if (paramBundle == null)
        paramBundle = new Bundle();
      paramBundle.putInt("extra_service_version", 2);
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putParcelable("data_media_session_token", paramToken);
      localBundle.putBundle("data_root_hints", paramBundle);
      sendRequest(1, localBundle);
    }

    public void onConnectFailed()
    {
      sendRequest(2, null);
    }

    public void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle1, Bundle paramBundle2)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putBundle("data_options", paramBundle1);
      localBundle.putBundle("data_notify_children_changed_options", paramBundle2);
      if (paramList != null)
      {
        ArrayList localArrayList;
        if ((paramList instanceof ArrayList))
          localArrayList = (ArrayList)paramList;
        else
          localArrayList = new ArrayList(paramList);
        localBundle.putParcelableArrayList("data_media_item_list", localArrayList);
      }
      sendRequest(3, localBundle);
    }
  }

  private final class ServiceHandler extends Handler
  {
    private final MediaBrowserServiceCompat.ServiceBinderImpl mServiceBinderImpl = new MediaBrowserServiceCompat.ServiceBinderImpl(MediaBrowserServiceCompat.this);

    ServiceHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      Bundle localBundle1 = paramMessage.getData();
      switch (paramMessage.what)
      {
      default:
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unhandled message: ");
        localStringBuilder.append(paramMessage);
        localStringBuilder.append("\n  Service version: ");
        localStringBuilder.append(2);
        localStringBuilder.append("\n  Client version: ");
        localStringBuilder.append(paramMessage.arg1);
        Log.w("MBServiceCompat", localStringBuilder.toString());
        break;
      case 9:
        Bundle localBundle6 = localBundle1.getBundle("data_custom_action_extras");
        MediaSessionCompat.ensureClassLoader(localBundle6);
        this.mServiceBinderImpl.sendCustomAction(localBundle1.getString("data_custom_action"), localBundle6, (ResultReceiver)localBundle1.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 8:
        Bundle localBundle5 = localBundle1.getBundle("data_search_extras");
        MediaSessionCompat.ensureClassLoader(localBundle5);
        this.mServiceBinderImpl.search(localBundle1.getString("data_search_query"), localBundle5, (ResultReceiver)localBundle1.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 7:
        this.mServiceBinderImpl.unregisterCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 6:
        Bundle localBundle4 = localBundle1.getBundle("data_root_hints");
        MediaSessionCompat.ensureClassLoader(localBundle4);
        this.mServiceBinderImpl.registerCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo), localBundle1.getString("data_package_name"), localBundle1.getInt("data_calling_pid"), localBundle1.getInt("data_calling_uid"), localBundle4);
        break;
      case 5:
        this.mServiceBinderImpl.getMediaItem(localBundle1.getString("data_media_item_id"), (ResultReceiver)localBundle1.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 4:
        this.mServiceBinderImpl.removeSubscription(localBundle1.getString("data_media_item_id"), BundleCompat.getBinder(localBundle1, "data_callback_token"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 3:
        Bundle localBundle3 = localBundle1.getBundle("data_options");
        MediaSessionCompat.ensureClassLoader(localBundle3);
        this.mServiceBinderImpl.addSubscription(localBundle1.getString("data_media_item_id"), BundleCompat.getBinder(localBundle1, "data_callback_token"), localBundle3, new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 2:
        this.mServiceBinderImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
        break;
      case 1:
        Bundle localBundle2 = localBundle1.getBundle("data_root_hints");
        MediaSessionCompat.ensureClassLoader(localBundle2);
        this.mServiceBinderImpl.connect(localBundle1.getString("data_package_name"), localBundle1.getInt("data_calling_pid"), localBundle1.getInt("data_calling_uid"), localBundle2, new MediaBrowserServiceCompat.ServiceCallbacksCompat(paramMessage.replyTo));
      }
    }

    public void postOrRun(Runnable paramRunnable)
    {
      if (Thread.currentThread() == getLooper().getThread())
        paramRunnable.run();
      else
        post(paramRunnable);
    }

    public boolean sendMessageAtTime(Message paramMessage, long paramLong)
    {
      Bundle localBundle = paramMessage.getData();
      localBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      localBundle.putInt("data_calling_uid", Binder.getCallingUid());
      localBundle.putInt("data_calling_pid", Binder.getCallingPid());
      return super.sendMessageAtTime(paramMessage, paramLong);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat
 * JD-Core Version:    0.6.1
 */