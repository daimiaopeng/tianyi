package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class NotificationManagerCompat
{
  public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
  private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
  public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
  public static final int IMPORTANCE_DEFAULT = 3;
  public static final int IMPORTANCE_HIGH = 4;
  public static final int IMPORTANCE_LOW = 2;
  public static final int IMPORTANCE_MAX = 5;
  public static final int IMPORTANCE_MIN = 1;
  public static final int IMPORTANCE_NONE = 0;
  public static final int IMPORTANCE_UNSPECIFIED = -1000;
  static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
  private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
  private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
  private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
  private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
  private static final String TAG = "NotifManCompat";

  @GuardedBy("sEnabledNotificationListenersLock")
  private static Set<String> sEnabledNotificationListenerPackages = new HashSet();

  @GuardedBy("sEnabledNotificationListenersLock")
  private static String sEnabledNotificationListeners;
  private static final Object sEnabledNotificationListenersLock = new Object();
  private static final Object sLock = new Object();

  @GuardedBy("sLock")
  private static SideChannelManager sSideChannelManager;
  private final Context mContext;
  private final NotificationManager mNotificationManager;

  private NotificationManagerCompat(Context paramContext)
  {
    this.mContext = paramContext;
    this.mNotificationManager = ((NotificationManager)this.mContext.getSystemService("notification"));
  }

  @NonNull
  public static NotificationManagerCompat from(@NonNull Context paramContext)
  {
    return new NotificationManagerCompat(paramContext);
  }

  // ERROR //
  @NonNull
  public static Set<String> getEnabledListenerPackages(@NonNull Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 98	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: ldc 36
    //   6: invokestatic 104	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_1
    //   10: getstatic 65	android/support/v4/app/NotificationManagerCompat:sEnabledNotificationListenersLock	Ljava/lang/Object;
    //   13: astore_2
    //   14: aload_2
    //   15: monitorenter
    //   16: aload_1
    //   17: ifnull +92 -> 109
    //   20: aload_1
    //   21: getstatic 106	android/support/v4/app/NotificationManagerCompat:sEnabledNotificationListeners	Ljava/lang/String;
    //   24: invokevirtual 112	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   27: ifne +82 -> 109
    //   30: aload_1
    //   31: ldc 114
    //   33: iconst_m1
    //   34: invokevirtual 118	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   37: astore 5
    //   39: new 67	java/util/HashSet
    //   42: dup
    //   43: aload 5
    //   45: arraylength
    //   46: invokespecial 121	java/util/HashSet:<init>	(I)V
    //   49: astore 6
    //   51: aload 5
    //   53: arraylength
    //   54: istore 7
    //   56: iconst_0
    //   57: istore 8
    //   59: iload 8
    //   61: iload 7
    //   63: if_icmpge +34 -> 97
    //   66: aload 5
    //   68: iload 8
    //   70: aaload
    //   71: invokestatic 127	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   74: astore 9
    //   76: aload 9
    //   78: ifnull +44 -> 122
    //   81: aload 6
    //   83: aload 9
    //   85: invokevirtual 131	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   88: invokeinterface 136 2 0
    //   93: pop
    //   94: goto +28 -> 122
    //   97: aload 6
    //   99: putstatic 70	android/support/v4/app/NotificationManagerCompat:sEnabledNotificationListenerPackages	Ljava/util/Set;
    //   102: aload_1
    //   103: putstatic 106	android/support/v4/app/NotificationManagerCompat:sEnabledNotificationListeners	Ljava/lang/String;
    //   106: goto +3 -> 109
    //   109: getstatic 70	android/support/v4/app/NotificationManagerCompat:sEnabledNotificationListenerPackages	Ljava/util/Set;
    //   112: astore_3
    //   113: aload_2
    //   114: monitorexit
    //   115: aload_3
    //   116: areturn
    //   117: aload_2
    //   118: monitorexit
    //   119: aload 4
    //   121: athrow
    //   122: iinc 8 1
    //   125: goto -66 -> 59
    //   128: astore 4
    //   130: goto -13 -> 117
    //
    // Exception table:
    //   from	to	target	type
    //   20	119	128	finally
  }

  private void pushSideChannelQueue(Task paramTask)
  {
    synchronized (sLock)
    {
      if (sSideChannelManager == null)
        sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
      sSideChannelManager.queueTask(paramTask);
      return;
    }
  }

  private static boolean useSideChannelForNotification(Notification paramNotification)
  {
    Bundle localBundle = NotificationCompat.getExtras(paramNotification);
    boolean bool;
    if ((localBundle != null) && (localBundle.getBoolean("android.support.useSideChannel")))
      bool = true;
    else
      bool = false;
    return bool;
  }

  // ERROR //
  public boolean areNotificationsEnabled()
  {
    // Byte code:
    //   0: getstatic 183	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 24
    //   5: if_icmplt +11 -> 16
    //   8: aload_0
    //   9: getfield 87	android/support/v4/app/NotificationManagerCompat:mNotificationManager	Landroid/app/NotificationManager;
    //   12: invokevirtual 185	android/app/NotificationManager:areNotificationsEnabled	()Z
    //   15: ireturn
    //   16: getstatic 183	android/os/Build$VERSION:SDK_INT	I
    //   19: istore_1
    //   20: iconst_1
    //   21: istore_2
    //   22: iload_1
    //   23: bipush 19
    //   25: if_icmplt +171 -> 196
    //   28: aload_0
    //   29: getfield 75	android/support/v4/app/NotificationManagerCompat:mContext	Landroid/content/Context;
    //   32: ldc 187
    //   34: invokevirtual 83	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   37: checkcast 189	android/app/AppOpsManager
    //   40: astore_3
    //   41: aload_0
    //   42: getfield 75	android/support/v4/app/NotificationManagerCompat:mContext	Landroid/content/Context;
    //   45: invokevirtual 193	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   48: astore 4
    //   50: aload_0
    //   51: getfield 75	android/support/v4/app/NotificationManagerCompat:mContext	Landroid/content/Context;
    //   54: invokevirtual 146	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   57: invokevirtual 194	android/content/Context:getPackageName	()Ljava/lang/String;
    //   60: astore 5
    //   62: aload 4
    //   64: getfield 199	android/content/pm/ApplicationInfo:uid	I
    //   67: istore 6
    //   69: ldc 189
    //   71: invokevirtual 204	java/lang/Class:getName	()Ljava/lang/String;
    //   74: invokestatic 208	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   77: astore 7
    //   79: iconst_3
    //   80: anewarray 201	java/lang/Class
    //   83: astore 8
    //   85: aload 8
    //   87: iconst_0
    //   88: getstatic 214	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   91: aastore
    //   92: aload 8
    //   94: iload_2
    //   95: getstatic 214	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   98: aastore
    //   99: aload 8
    //   101: iconst_2
    //   102: ldc 108
    //   104: aastore
    //   105: aload 7
    //   107: ldc 11
    //   109: aload 8
    //   111: invokevirtual 218	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   114: astore 9
    //   116: aload 7
    //   118: ldc 33
    //   120: invokevirtual 222	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   123: ldc 210
    //   125: invokevirtual 228	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   128: checkcast 210	java/lang/Integer
    //   131: invokevirtual 232	java/lang/Integer:intValue	()I
    //   134: istore 10
    //   136: iconst_3
    //   137: anewarray 4	java/lang/Object
    //   140: astore 11
    //   142: aload 11
    //   144: iconst_0
    //   145: iload 10
    //   147: invokestatic 236	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   150: aastore
    //   151: aload 11
    //   153: iload_2
    //   154: iload 6
    //   156: invokestatic 236	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   159: aastore
    //   160: aload 11
    //   162: iconst_2
    //   163: aload 5
    //   165: aastore
    //   166: aload 9
    //   168: aload_3
    //   169: aload 11
    //   171: invokevirtual 242	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   174: checkcast 210	java/lang/Integer
    //   177: invokevirtual 232	java/lang/Integer:intValue	()I
    //   180: istore 12
    //   182: iload 12
    //   184: ifne +6 -> 190
    //   187: goto +5 -> 192
    //   190: iconst_0
    //   191: istore_2
    //   192: iload_2
    //   193: ireturn
    //   194: iload_2
    //   195: ireturn
    //   196: iload_2
    //   197: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   69	182	194	java/lang/ClassNotFoundException
    //   69	182	194	java/lang/NoSuchMethodException
    //   69	182	194	java/lang/NoSuchFieldException
    //   69	182	194	java/lang/reflect/InvocationTargetException
    //   69	182	194	java/lang/IllegalAccessException
    //   69	182	194	java/lang/RuntimeException
  }

  public void cancel(int paramInt)
  {
    cancel(null, paramInt);
  }

  public void cancel(@Nullable String paramString, int paramInt)
  {
    this.mNotificationManager.cancel(paramString, paramInt);
    if (Build.VERSION.SDK_INT <= 19)
      pushSideChannelQueue(new CancelTask(this.mContext.getPackageName(), paramInt, paramString));
  }

  public void cancelAll()
  {
    this.mNotificationManager.cancelAll();
    if (Build.VERSION.SDK_INT <= 19)
      pushSideChannelQueue(new CancelTask(this.mContext.getPackageName()));
  }

  public int getImportance()
  {
    if (Build.VERSION.SDK_INT >= 24)
      return this.mNotificationManager.getImportance();
    return -1000;
  }

  public void notify(int paramInt, @NonNull Notification paramNotification)
  {
    notify(null, paramInt, paramNotification);
  }

  public void notify(@Nullable String paramString, int paramInt, @NonNull Notification paramNotification)
  {
    if (useSideChannelForNotification(paramNotification))
    {
      pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), paramInt, paramString, paramNotification));
      this.mNotificationManager.cancel(paramString, paramInt);
    }
    else
    {
      this.mNotificationManager.notify(paramString, paramInt, paramNotification);
    }
  }

  private static class CancelTask
    implements NotificationManagerCompat.Task
  {
    final boolean all;
    final int id;
    final String packageName;
    final String tag;

    CancelTask(String paramString)
    {
      this.packageName = paramString;
      this.id = 0;
      this.tag = null;
      this.all = true;
    }

    CancelTask(String paramString1, int paramInt, String paramString2)
    {
      this.packageName = paramString1;
      this.id = paramInt;
      this.tag = paramString2;
      this.all = false;
    }

    public void send(INotificationSideChannel paramINotificationSideChannel)
    {
      if (this.all)
        paramINotificationSideChannel.cancelAll(this.packageName);
      else
        paramINotificationSideChannel.cancel(this.packageName, this.id, this.tag);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("CancelTask[");
      localStringBuilder.append("packageName:");
      localStringBuilder.append(this.packageName);
      localStringBuilder.append(", id:");
      localStringBuilder.append(this.id);
      localStringBuilder.append(", tag:");
      localStringBuilder.append(this.tag);
      localStringBuilder.append(", all:");
      localStringBuilder.append(this.all);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }

  private static class NotifyTask
    implements NotificationManagerCompat.Task
  {
    final int id;
    final Notification notif;
    final String packageName;
    final String tag;

    NotifyTask(String paramString1, int paramInt, String paramString2, Notification paramNotification)
    {
      this.packageName = paramString1;
      this.id = paramInt;
      this.tag = paramString2;
      this.notif = paramNotification;
    }

    public void send(INotificationSideChannel paramINotificationSideChannel)
    {
      paramINotificationSideChannel.notify(this.packageName, this.id, this.tag, this.notif);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("NotifyTask[");
      localStringBuilder.append("packageName:");
      localStringBuilder.append(this.packageName);
      localStringBuilder.append(", id:");
      localStringBuilder.append(this.id);
      localStringBuilder.append(", tag:");
      localStringBuilder.append(this.tag);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }

  private static class ServiceConnectedEvent
  {
    final ComponentName componentName;
    final IBinder iBinder;

    ServiceConnectedEvent(ComponentName paramComponentName, IBinder paramIBinder)
    {
      this.componentName = paramComponentName;
      this.iBinder = paramIBinder;
    }
  }

  private static class SideChannelManager
    implements ServiceConnection, Handler.Callback
  {
    private static final int MSG_QUEUE_TASK = 0;
    private static final int MSG_RETRY_LISTENER_QUEUE = 3;
    private static final int MSG_SERVICE_CONNECTED = 1;
    private static final int MSG_SERVICE_DISCONNECTED = 2;
    private Set<String> mCachedEnabledPackages = new HashSet();
    private final Context mContext;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();

    SideChannelManager(Context paramContext)
    {
      this.mContext = paramContext;
      this.mHandlerThread = new HandlerThread("NotificationManagerCompat");
      this.mHandlerThread.start();
      this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
    }

    private boolean ensureServiceBound(ListenerRecord paramListenerRecord)
    {
      if (paramListenerRecord.bound)
        return true;
      Intent localIntent = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(paramListenerRecord.componentName);
      paramListenerRecord.bound = this.mContext.bindService(localIntent, this, 33);
      if (paramListenerRecord.bound)
      {
        paramListenerRecord.retryCount = 0;
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unable to bind to listener ");
        localStringBuilder.append(paramListenerRecord.componentName);
        Log.w("NotifManCompat", localStringBuilder.toString());
        this.mContext.unbindService(this);
      }
      return paramListenerRecord.bound;
    }

    private void ensureServiceUnbound(ListenerRecord paramListenerRecord)
    {
      if (paramListenerRecord.bound)
      {
        this.mContext.unbindService(this);
        paramListenerRecord.bound = false;
      }
      paramListenerRecord.service = null;
    }

    private void handleQueueTask(NotificationManagerCompat.Task paramTask)
    {
      updateListenerMap();
      Iterator localIterator = this.mRecordMap.values().iterator();
      while (localIterator.hasNext())
      {
        ListenerRecord localListenerRecord = (ListenerRecord)localIterator.next();
        localListenerRecord.taskQueue.add(paramTask);
        processListenerQueue(localListenerRecord);
      }
    }

    private void handleRetryListenerQueue(ComponentName paramComponentName)
    {
      ListenerRecord localListenerRecord = (ListenerRecord)this.mRecordMap.get(paramComponentName);
      if (localListenerRecord != null)
        processListenerQueue(localListenerRecord);
    }

    private void handleServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      ListenerRecord localListenerRecord = (ListenerRecord)this.mRecordMap.get(paramComponentName);
      if (localListenerRecord != null)
      {
        localListenerRecord.service = INotificationSideChannel.Stub.asInterface(paramIBinder);
        localListenerRecord.retryCount = 0;
        processListenerQueue(localListenerRecord);
      }
    }

    private void handleServiceDisconnected(ComponentName paramComponentName)
    {
      ListenerRecord localListenerRecord = (ListenerRecord)this.mRecordMap.get(paramComponentName);
      if (localListenerRecord != null)
        ensureServiceUnbound(localListenerRecord);
    }

    // ERROR //
    private void processListenerQueue(ListenerRecord paramListenerRecord)
    {
      // Byte code:
      //   0: ldc 113
      //   2: iconst_3
      //   3: invokestatic 198	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   6: ifeq +63 -> 69
      //   9: new 101	java/lang/StringBuilder
      //   12: dup
      //   13: invokespecial 102	java/lang/StringBuilder:<init>	()V
      //   16: astore_2
      //   17: aload_2
      //   18: ldc 200
      //   20: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   23: pop
      //   24: aload_2
      //   25: aload_1
      //   26: getfield 86	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:componentName	Landroid/content/ComponentName;
      //   29: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   32: pop
      //   33: aload_2
      //   34: ldc 202
      //   36: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   39: pop
      //   40: aload_2
      //   41: aload_1
      //   42: getfield 164	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:taskQueue	Ljava/util/ArrayDeque;
      //   45: invokevirtual 206	java/util/ArrayDeque:size	()I
      //   48: invokevirtual 209	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   51: pop
      //   52: aload_2
      //   53: ldc 211
      //   55: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   58: pop
      //   59: ldc 113
      //   61: aload_2
      //   62: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   65: invokestatic 214	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   68: pop
      //   69: aload_1
      //   70: getfield 164	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:taskQueue	Ljava/util/ArrayDeque;
      //   73: invokevirtual 217	java/util/ArrayDeque:isEmpty	()Z
      //   76: ifeq +4 -> 80
      //   79: return
      //   80: aload_0
      //   81: aload_1
      //   82: invokespecial 219	android/support/v4/app/NotificationManagerCompat$SideChannelManager:ensureServiceBound	(Landroid/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord;)Z
      //   85: ifeq +208 -> 293
      //   88: aload_1
      //   89: getfield 133	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:service	Landroid/support/v4/app/INotificationSideChannel;
      //   92: ifnonnull +6 -> 98
      //   95: goto +198 -> 293
      //   98: aload_1
      //   99: getfield 164	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:taskQueue	Ljava/util/ArrayDeque;
      //   102: invokevirtual 222	java/util/ArrayDeque:peek	()Ljava/lang/Object;
      //   105: checkcast 224	android/support/v4/app/NotificationManagerCompat$Task
      //   108: astore 9
      //   110: aload 9
      //   112: ifnonnull +6 -> 118
      //   115: goto +162 -> 277
      //   118: ldc 113
      //   120: iconst_3
      //   121: invokestatic 198	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   124: ifeq +39 -> 163
      //   127: new 101	java/lang/StringBuilder
      //   130: dup
      //   131: invokespecial 102	java/lang/StringBuilder:<init>	()V
      //   134: astore 19
      //   136: aload 19
      //   138: ldc 226
      //   140: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   143: pop
      //   144: aload 19
      //   146: aload 9
      //   148: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   151: pop
      //   152: ldc 113
      //   154: aload 19
      //   156: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   159: invokestatic 214	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   162: pop
      //   163: aload 9
      //   165: aload_1
      //   166: getfield 133	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:service	Landroid/support/v4/app/INotificationSideChannel;
      //   169: invokeinterface 230 2 0
      //   174: aload_1
      //   175: getfield 164	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:taskQueue	Ljava/util/ArrayDeque;
      //   178: invokevirtual 233	java/util/ArrayDeque:remove	()Ljava/lang/Object;
      //   181: pop
      //   182: goto -84 -> 98
      //   185: astore 14
      //   187: new 101	java/lang/StringBuilder
      //   190: dup
      //   191: invokespecial 102	java/lang/StringBuilder:<init>	()V
      //   194: astore 15
      //   196: aload 15
      //   198: ldc 235
      //   200: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   203: pop
      //   204: aload 15
      //   206: aload_1
      //   207: getfield 86	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:componentName	Landroid/content/ComponentName;
      //   210: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   213: pop
      //   214: ldc 113
      //   216: aload 15
      //   218: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   221: aload 14
      //   223: invokestatic 238	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   226: pop
      //   227: goto +50 -> 277
      //   230: ldc 113
      //   232: iconst_3
      //   233: invokestatic 198	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   236: ifeq +41 -> 277
      //   239: new 101	java/lang/StringBuilder
      //   242: dup
      //   243: invokespecial 102	java/lang/StringBuilder:<init>	()V
      //   246: astore 10
      //   248: aload 10
      //   250: ldc 240
      //   252: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   255: pop
      //   256: aload 10
      //   258: aload_1
      //   259: getfield 86	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:componentName	Landroid/content/ComponentName;
      //   262: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   265: pop
      //   266: ldc 113
      //   268: aload 10
      //   270: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   273: invokestatic 214	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   276: pop
      //   277: aload_1
      //   278: getfield 164	android/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord:taskQueue	Ljava/util/ArrayDeque;
      //   281: invokevirtual 217	java/util/ArrayDeque:isEmpty	()Z
      //   284: ifne +8 -> 292
      //   287: aload_0
      //   288: aload_1
      //   289: invokespecial 243	android/support/v4/app/NotificationManagerCompat$SideChannelManager:scheduleListenerRetry	(Landroid/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord;)V
      //   292: return
      //   293: aload_0
      //   294: aload_1
      //   295: invokespecial 243	android/support/v4/app/NotificationManagerCompat$SideChannelManager:scheduleListenerRetry	(Landroid/support/v4/app/NotificationManagerCompat$SideChannelManager$ListenerRecord;)V
      //   298: return
      //
      // Exception table:
      //   from	to	target	type
      //   118	182	185	android/os/RemoteException
      //   118	182	230	android/os/DeadObjectException
    }

    private void scheduleListenerRetry(ListenerRecord paramListenerRecord)
    {
      if (this.mHandler.hasMessages(3, paramListenerRecord.componentName))
        return;
      paramListenerRecord.retryCount = (1 + paramListenerRecord.retryCount);
      if (paramListenerRecord.retryCount > 6)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Giving up on delivering ");
        localStringBuilder1.append(paramListenerRecord.taskQueue.size());
        localStringBuilder1.append(" tasks to ");
        localStringBuilder1.append(paramListenerRecord.componentName);
        localStringBuilder1.append(" after ");
        localStringBuilder1.append(paramListenerRecord.retryCount);
        localStringBuilder1.append(" retries");
        Log.w("NotifManCompat", localStringBuilder1.toString());
        paramListenerRecord.taskQueue.clear();
        return;
      }
      int i = 1000 * (1 << paramListenerRecord.retryCount - 1);
      if (Log.isLoggable("NotifManCompat", 3))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Scheduling retry for ");
        localStringBuilder2.append(i);
        localStringBuilder2.append(" ms");
        Log.d("NotifManCompat", localStringBuilder2.toString());
      }
      Message localMessage = this.mHandler.obtainMessage(3, paramListenerRecord.componentName);
      this.mHandler.sendMessageDelayed(localMessage, i);
    }

    private void updateListenerMap()
    {
      Set localSet = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
      if (localSet.equals(this.mCachedEnabledPackages))
        return;
      this.mCachedEnabledPackages = localSet;
      List localList = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
      HashSet localHashSet = new HashSet();
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator1.next();
        if (localSet.contains(localResolveInfo.serviceInfo.packageName))
        {
          ComponentName localComponentName2 = new ComponentName(localResolveInfo.serviceInfo.packageName, localResolveInfo.serviceInfo.name);
          if (localResolveInfo.serviceInfo.permission != null)
          {
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append("Permission present on component ");
            localStringBuilder3.append(localComponentName2);
            localStringBuilder3.append(", not adding listener record.");
            Log.w("NotifManCompat", localStringBuilder3.toString());
          }
          else
          {
            localHashSet.add(localComponentName2);
          }
        }
      }
      Iterator localIterator2 = localHashSet.iterator();
      while (localIterator2.hasNext())
      {
        ComponentName localComponentName1 = (ComponentName)localIterator2.next();
        if (!this.mRecordMap.containsKey(localComponentName1))
        {
          if (Log.isLoggable("NotifManCompat", 3))
          {
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("Adding listener record for ");
            localStringBuilder2.append(localComponentName1);
            Log.d("NotifManCompat", localStringBuilder2.toString());
          }
          this.mRecordMap.put(localComponentName1, new ListenerRecord(localComponentName1));
        }
      }
      Iterator localIterator3 = this.mRecordMap.entrySet().iterator();
      while (localIterator3.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator3.next();
        if (!localHashSet.contains(localEntry.getKey()))
        {
          if (Log.isLoggable("NotifManCompat", 3))
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("Removing listener record for ");
            localStringBuilder1.append(localEntry.getKey());
            Log.d("NotifManCompat", localStringBuilder1.toString());
          }
          ensureServiceUnbound((ListenerRecord)localEntry.getValue());
          localIterator3.remove();
        }
      }
    }

    public boolean handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return false;
      case 3:
        handleRetryListenerQueue((ComponentName)paramMessage.obj);
        return true;
      case 2:
        handleServiceDisconnected((ComponentName)paramMessage.obj);
        return true;
      case 1:
        NotificationManagerCompat.ServiceConnectedEvent localServiceConnectedEvent = (NotificationManagerCompat.ServiceConnectedEvent)paramMessage.obj;
        handleServiceConnected(localServiceConnectedEvent.componentName, localServiceConnectedEvent.iBinder);
        return true;
      case 0:
      }
      handleQueueTask((NotificationManagerCompat.Task)paramMessage.obj);
      return true;
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      if (Log.isLoggable("NotifManCompat", 3))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Connected to service ");
        localStringBuilder.append(paramComponentName);
        Log.d("NotifManCompat", localStringBuilder.toString());
      }
      this.mHandler.obtainMessage(1, new NotificationManagerCompat.ServiceConnectedEvent(paramComponentName, paramIBinder)).sendToTarget();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (Log.isLoggable("NotifManCompat", 3))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Disconnected from service ");
        localStringBuilder.append(paramComponentName);
        Log.d("NotifManCompat", localStringBuilder.toString());
      }
      this.mHandler.obtainMessage(2, paramComponentName).sendToTarget();
    }

    public void queueTask(NotificationManagerCompat.Task paramTask)
    {
      this.mHandler.obtainMessage(0, paramTask).sendToTarget();
    }

    private static class ListenerRecord
    {
      boolean bound = false;
      final ComponentName componentName;
      int retryCount = 0;
      INotificationSideChannel service;
      ArrayDeque<NotificationManagerCompat.Task> taskQueue = new ArrayDeque();

      ListenerRecord(ComponentName paramComponentName)
      {
        this.componentName = paramComponentName;
      }
    }
  }

  private static abstract interface Task
  {
    public abstract void send(INotificationSideChannel paramINotificationSideChannel);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationManagerCompat
 * JD-Core Version:    0.6.1
 */