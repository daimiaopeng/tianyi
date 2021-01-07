package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Action;
import android.app.Notification.Action.Builder;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.InboxStyle;
import android.app.Notification.MessagingStyle;
import android.app.Notification.MessagingStyle.Message;
import android.app.PendingIntent;
import android.app.RemoteInput.Builder;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.compat.R.color;
import android.support.compat.R.dimen;
import android.support.compat.R.drawable;
import android.support.compat.R.id;
import android.support.compat.R.integer;
import android.support.compat.R.layout;
import android.support.compat.R.string;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat
{
  public static final int BADGE_ICON_LARGE = 2;
  public static final int BADGE_ICON_NONE = 0;
  public static final int BADGE_ICON_SMALL = 1;
  public static final String CATEGORY_ALARM = "alarm";
  public static final String CATEGORY_CALL = "call";
  public static final String CATEGORY_EMAIL = "email";
  public static final String CATEGORY_ERROR = "err";
  public static final String CATEGORY_EVENT = "event";
  public static final String CATEGORY_MESSAGE = "msg";
  public static final String CATEGORY_PROGRESS = "progress";
  public static final String CATEGORY_PROMO = "promo";
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  public static final String CATEGORY_REMINDER = "reminder";
  public static final String CATEGORY_SERVICE = "service";
  public static final String CATEGORY_SOCIAL = "social";
  public static final String CATEGORY_STATUS = "status";
  public static final String CATEGORY_SYSTEM = "sys";
  public static final String CATEGORY_TRANSPORT = "transport";

  @ColorInt
  public static final int COLOR_DEFAULT = 0;
  public static final int DEFAULT_ALL = -1;
  public static final int DEFAULT_LIGHTS = 4;
  public static final int DEFAULT_SOUND = 1;
  public static final int DEFAULT_VIBRATE = 2;
  public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
  public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
  public static final String EXTRA_BIG_TEXT = "android.bigText";
  public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
  public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
  public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
  public static final String EXTRA_INFO_TEXT = "android.infoText";
  public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
  public static final String EXTRA_LARGE_ICON = "android.largeIcon";
  public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
  public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
  public static final String EXTRA_MESSAGES = "android.messages";
  public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
  public static final String EXTRA_PEOPLE = "android.people";
  public static final String EXTRA_PICTURE = "android.picture";
  public static final String EXTRA_PROGRESS = "android.progress";
  public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
  public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
  public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
  public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
  public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
  public static final String EXTRA_SHOW_WHEN = "android.showWhen";
  public static final String EXTRA_SMALL_ICON = "android.icon";
  public static final String EXTRA_SUB_TEXT = "android.subText";
  public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
  public static final String EXTRA_TEMPLATE = "android.template";
  public static final String EXTRA_TEXT = "android.text";
  public static final String EXTRA_TEXT_LINES = "android.textLines";
  public static final String EXTRA_TITLE = "android.title";
  public static final String EXTRA_TITLE_BIG = "android.title.big";
  public static final int FLAG_AUTO_CANCEL = 16;
  public static final int FLAG_FOREGROUND_SERVICE = 64;
  public static final int FLAG_GROUP_SUMMARY = 512;

  @Deprecated
  public static final int FLAG_HIGH_PRIORITY = 128;
  public static final int FLAG_INSISTENT = 4;
  public static final int FLAG_LOCAL_ONLY = 256;
  public static final int FLAG_NO_CLEAR = 32;
  public static final int FLAG_ONGOING_EVENT = 2;
  public static final int FLAG_ONLY_ALERT_ONCE = 8;
  public static final int FLAG_SHOW_LIGHTS = 1;
  public static final int GROUP_ALERT_ALL = 0;
  public static final int GROUP_ALERT_CHILDREN = 2;
  public static final int GROUP_ALERT_SUMMARY = 1;
  public static final int PRIORITY_DEFAULT = 0;
  public static final int PRIORITY_HIGH = 1;
  public static final int PRIORITY_LOW = -1;
  public static final int PRIORITY_MAX = 2;
  public static final int PRIORITY_MIN = -2;
  public static final int STREAM_DEFAULT = -1;
  public static final int VISIBILITY_PRIVATE = 0;
  public static final int VISIBILITY_PUBLIC = 1;
  public static final int VISIBILITY_SECRET = -1;

  public static Action getAction(Notification paramNotification, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 20)
      return getActionCompatFromAction(paramNotification.actions[paramInt]);
    if (Build.VERSION.SDK_INT >= 19)
    {
      Notification.Action localAction = paramNotification.actions[paramInt];
      SparseArray localSparseArray = paramNotification.extras.getSparseParcelableArray("android.support.actionExtras");
      Bundle localBundle = null;
      if (localSparseArray != null)
        localBundle = (Bundle)localSparseArray.get(paramInt);
      return NotificationCompatJellybean.readAction(localAction.icon, localAction.title, localAction.actionIntent, localBundle);
    }
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getAction(paramNotification, paramInt);
    return null;
  }

  @RequiresApi(20)
  static Action getActionCompatFromAction(Notification.Action paramAction)
  {
    android.app.RemoteInput[] arrayOfRemoteInput = paramAction.getRemoteInputs();
    Object localObject;
    if (arrayOfRemoteInput == null)
    {
      localObject = null;
    }
    else
    {
      RemoteInput[] arrayOfRemoteInput1 = new RemoteInput[arrayOfRemoteInput.length];
      for (int k = 0; k < arrayOfRemoteInput.length; k++)
      {
        android.app.RemoteInput localRemoteInput = arrayOfRemoteInput[k];
        RemoteInput localRemoteInput1 = new RemoteInput(localRemoteInput.getResultKey(), localRemoteInput.getLabel(), localRemoteInput.getChoices(), localRemoteInput.getAllowFreeFormInput(), localRemoteInput.getExtras(), null);
        arrayOfRemoteInput1[k] = localRemoteInput1;
      }
      localObject = arrayOfRemoteInput1;
    }
    boolean bool1;
    if (Build.VERSION.SDK_INT >= 24)
    {
      if ((!paramAction.getExtras().getBoolean("android.support.allowGeneratedReplies")) && (!paramAction.getAllowGeneratedReplies()))
        bool1 = false;
      else
        bool1 = true;
    }
    else
      bool1 = paramAction.getExtras().getBoolean("android.support.allowGeneratedReplies");
    boolean bool2 = bool1;
    boolean bool3 = paramAction.getExtras().getBoolean("android.support.action.showsUserInterface", true);
    if (Build.VERSION.SDK_INT >= 28);
    int j;
    for (int i = paramAction.getSemanticAction(); ; i = paramAction.getExtras().getInt("android.support.action.semanticAction", 0))
    {
      j = i;
      break;
    }
    Action localAction = new Action(paramAction.icon, paramAction.title, paramAction.actionIntent, paramAction.getExtras(), localObject, null, bool2, j, bool3);
    return localAction;
  }

  public static int getActionCount(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      Notification.Action[] arrayOfAction = paramNotification.actions;
      int i = 0;
      if (arrayOfAction != null)
        i = paramNotification.actions.length;
      return i;
    }
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getActionCount(paramNotification);
    return 0;
  }

  public static int getBadgeIconType(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramNotification.getBadgeIconType();
    return 0;
  }

  public static String getCategory(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return paramNotification.category;
    return null;
  }

  public static String getChannelId(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramNotification.getChannelId();
    return null;
  }

  @RequiresApi(19)
  public static CharSequence getContentTitle(Notification paramNotification)
  {
    return paramNotification.extras.getCharSequence("android.title");
  }

  @Nullable
  public static Bundle getExtras(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return paramNotification.extras;
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getExtras(paramNotification);
    return null;
  }

  public static String getGroup(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20)
      return paramNotification.getGroup();
    if (Build.VERSION.SDK_INT >= 19)
      return paramNotification.extras.getString("android.support.groupKey");
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.groupKey");
    return null;
  }

  public static int getGroupAlertBehavior(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramNotification.getGroupAlertBehavior();
    return 0;
  }

  @RequiresApi(21)
  public static List<Action> getInvisibleActions(Notification paramNotification)
  {
    ArrayList localArrayList = new ArrayList();
    Bundle localBundle1 = paramNotification.extras.getBundle("android.car.EXTENSIONS");
    if (localBundle1 == null)
      return localArrayList;
    Bundle localBundle2 = localBundle1.getBundle("invisible_actions");
    if (localBundle2 != null)
      for (int i = 0; i < localBundle2.size(); i++)
        localArrayList.add(NotificationCompatJellybean.getActionFromBundle(localBundle2.getBundle(Integer.toString(i))));
    return localArrayList;
  }

  public static boolean getLocalOnly(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      int i = 0x100 & paramNotification.flags;
      boolean bool = false;
      if (i != 0)
        bool = true;
      return bool;
    }
    if (Build.VERSION.SDK_INT >= 19)
      return paramNotification.extras.getBoolean("android.support.localOnly");
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.localOnly");
    return false;
  }

  static Notification[] getNotificationArrayFromBundle(Bundle paramBundle, String paramString)
  {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    if ((!(arrayOfParcelable instanceof Notification[])) && (arrayOfParcelable != null))
    {
      Notification[] arrayOfNotification = new Notification[arrayOfParcelable.length];
      for (int i = 0; i < arrayOfParcelable.length; i++)
        arrayOfNotification[i] = ((Notification)arrayOfParcelable[i]);
      paramBundle.putParcelableArray(paramString, arrayOfNotification);
      return arrayOfNotification;
    }
    return (Notification[])arrayOfParcelable;
  }

  public static String getShortcutId(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramNotification.getShortcutId();
    return null;
  }

  public static String getSortKey(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20)
      return paramNotification.getSortKey();
    if (Build.VERSION.SDK_INT >= 19)
      return paramNotification.extras.getString("android.support.sortKey");
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.sortKey");
    return null;
  }

  public static long getTimeoutAfter(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramNotification.getTimeoutAfter();
    return 0L;
  }

  public static boolean isGroupSummary(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      int i = 0x200 & paramNotification.flags;
      boolean bool = false;
      if (i != 0)
        bool = true;
      return bool;
    }
    if (Build.VERSION.SDK_INT >= 19)
      return paramNotification.extras.getBoolean("android.support.isGroupSummary");
    if (Build.VERSION.SDK_INT >= 16)
      return NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.isGroupSummary");
    return false;
  }

  public static class Action
  {
    static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
    static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
    public static final int SEMANTIC_ACTION_ARCHIVE = 5;
    public static final int SEMANTIC_ACTION_CALL = 10;
    public static final int SEMANTIC_ACTION_DELETE = 4;
    public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
    public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
    public static final int SEMANTIC_ACTION_MUTE = 6;
    public static final int SEMANTIC_ACTION_NONE = 0;
    public static final int SEMANTIC_ACTION_REPLY = 1;
    public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
    public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
    public static final int SEMANTIC_ACTION_UNMUTE = 7;
    public PendingIntent actionIntent;
    public int icon;
    private boolean mAllowGeneratedReplies;
    private final RemoteInput[] mDataOnlyRemoteInputs;
    final Bundle mExtras;
    private final RemoteInput[] mRemoteInputs;
    private final int mSemanticAction;
    boolean mShowsUserInterface = true;
    public CharSequence title;

    public Action(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
    {
      this(paramInt, paramCharSequence, paramPendingIntent, new Bundle(), null, null, true, 0, true);
    }

    Action(int paramInt1, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput1, RemoteInput[] paramArrayOfRemoteInput2, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
    {
      this.icon = paramInt1;
      this.title = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      this.actionIntent = paramPendingIntent;
      if (paramBundle == null)
        paramBundle = new Bundle();
      this.mExtras = paramBundle;
      this.mRemoteInputs = paramArrayOfRemoteInput1;
      this.mDataOnlyRemoteInputs = paramArrayOfRemoteInput2;
      this.mAllowGeneratedReplies = paramBoolean1;
      this.mSemanticAction = paramInt2;
      this.mShowsUserInterface = paramBoolean2;
    }

    public PendingIntent getActionIntent()
    {
      return this.actionIntent;
    }

    public boolean getAllowGeneratedReplies()
    {
      return this.mAllowGeneratedReplies;
    }

    public RemoteInput[] getDataOnlyRemoteInputs()
    {
      return this.mDataOnlyRemoteInputs;
    }

    public Bundle getExtras()
    {
      return this.mExtras;
    }

    public int getIcon()
    {
      return this.icon;
    }

    public RemoteInput[] getRemoteInputs()
    {
      return this.mRemoteInputs;
    }

    public int getSemanticAction()
    {
      return this.mSemanticAction;
    }

    public boolean getShowsUserInterface()
    {
      return this.mShowsUserInterface;
    }

    public CharSequence getTitle()
    {
      return this.title;
    }

    public static final class Builder
    {
      private boolean mAllowGeneratedReplies = true;
      private final Bundle mExtras;
      private final int mIcon;
      private final PendingIntent mIntent;
      private ArrayList<RemoteInput> mRemoteInputs;
      private int mSemanticAction;
      private boolean mShowsUserInterface = true;
      private final CharSequence mTitle;

      public Builder(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
      {
        this(paramInt, paramCharSequence, paramPendingIntent, new Bundle(), null, true, 0, true);
      }

      private Builder(int paramInt1, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
      {
        this.mIcon = paramInt1;
        this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
        this.mIntent = paramPendingIntent;
        this.mExtras = paramBundle;
        ArrayList localArrayList;
        if (paramArrayOfRemoteInput == null)
          localArrayList = null;
        else
          localArrayList = new ArrayList(Arrays.asList(paramArrayOfRemoteInput));
        this.mRemoteInputs = localArrayList;
        this.mAllowGeneratedReplies = paramBoolean1;
        this.mSemanticAction = paramInt2;
        this.mShowsUserInterface = paramBoolean2;
      }

      public Builder(NotificationCompat.Action paramAction)
      {
        this(paramAction.icon, paramAction.title, paramAction.actionIntent, new Bundle(paramAction.mExtras), paramAction.getRemoteInputs(), paramAction.getAllowGeneratedReplies(), paramAction.getSemanticAction(), paramAction.mShowsUserInterface);
      }

      public Builder addExtras(Bundle paramBundle)
      {
        if (paramBundle != null)
          this.mExtras.putAll(paramBundle);
        return this;
      }

      public Builder addRemoteInput(RemoteInput paramRemoteInput)
      {
        if (this.mRemoteInputs == null)
          this.mRemoteInputs = new ArrayList();
        this.mRemoteInputs.add(paramRemoteInput);
        return this;
      }

      public NotificationCompat.Action build()
      {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        if (this.mRemoteInputs != null)
        {
          Iterator localIterator = this.mRemoteInputs.iterator();
          while (localIterator.hasNext())
          {
            RemoteInput localRemoteInput = (RemoteInput)localIterator.next();
            if (localRemoteInput.isDataOnly())
              localArrayList1.add(localRemoteInput);
            else
              localArrayList2.add(localRemoteInput);
          }
        }
        RemoteInput[] arrayOfRemoteInput1;
        if (localArrayList1.isEmpty())
          arrayOfRemoteInput1 = null;
        else
          arrayOfRemoteInput1 = (RemoteInput[])localArrayList1.toArray(new RemoteInput[localArrayList1.size()]);
        boolean bool = localArrayList2.isEmpty();
        RemoteInput[] arrayOfRemoteInput2 = null;
        if (bool);
        RemoteInput[] arrayOfRemoteInput3;
        while (true)
        {
          arrayOfRemoteInput3 = arrayOfRemoteInput2;
          break;
          arrayOfRemoteInput2 = (RemoteInput[])localArrayList2.toArray(new RemoteInput[localArrayList2.size()]);
        }
        NotificationCompat.Action localAction = new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput3, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
        return localAction;
      }

      public Builder extend(NotificationCompat.Action.Extender paramExtender)
      {
        paramExtender.extend(this);
        return this;
      }

      public Bundle getExtras()
      {
        return this.mExtras;
      }

      public Builder setAllowGeneratedReplies(boolean paramBoolean)
      {
        this.mAllowGeneratedReplies = paramBoolean;
        return this;
      }

      public Builder setSemanticAction(int paramInt)
      {
        this.mSemanticAction = paramInt;
        return this;
      }

      public Builder setShowsUserInterface(boolean paramBoolean)
      {
        this.mShowsUserInterface = paramBoolean;
        return this;
      }
    }

    public static abstract interface Extender
    {
      public abstract NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder paramBuilder);
    }

    @Retention(RetentionPolicy.SOURCE)
    public static @interface SemanticAction
    {
    }

    public static final class WearableExtender
      implements NotificationCompat.Action.Extender
    {
      private static final int DEFAULT_FLAGS = 1;
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      private static final int FLAG_AVAILABLE_OFFLINE = 1;
      private static final int FLAG_HINT_DISPLAY_INLINE = 4;
      private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
      private static final String KEY_CANCEL_LABEL = "cancelLabel";
      private static final String KEY_CONFIRM_LABEL = "confirmLabel";
      private static final String KEY_FLAGS = "flags";
      private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
      private CharSequence mCancelLabel;
      private CharSequence mConfirmLabel;
      private int mFlags = 1;
      private CharSequence mInProgressLabel;

      public WearableExtender()
      {
      }

      public WearableExtender(NotificationCompat.Action paramAction)
      {
        Bundle localBundle = paramAction.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (localBundle != null)
        {
          this.mFlags = localBundle.getInt("flags", 1);
          this.mInProgressLabel = localBundle.getCharSequence("inProgressLabel");
          this.mConfirmLabel = localBundle.getCharSequence("confirmLabel");
          this.mCancelLabel = localBundle.getCharSequence("cancelLabel");
        }
      }

      private void setFlag(int paramInt, boolean paramBoolean)
      {
        if (paramBoolean)
          this.mFlags = (paramInt | this.mFlags);
        else
          this.mFlags &= (paramInt ^ 0xFFFFFFFF);
      }

      public WearableExtender clone()
      {
        WearableExtender localWearableExtender = new WearableExtender();
        localWearableExtender.mFlags = this.mFlags;
        localWearableExtender.mInProgressLabel = this.mInProgressLabel;
        localWearableExtender.mConfirmLabel = this.mConfirmLabel;
        localWearableExtender.mCancelLabel = this.mCancelLabel;
        return localWearableExtender;
      }

      public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder paramBuilder)
      {
        Bundle localBundle = new Bundle();
        if (this.mFlags != 1)
          localBundle.putInt("flags", this.mFlags);
        if (this.mInProgressLabel != null)
          localBundle.putCharSequence("inProgressLabel", this.mInProgressLabel);
        if (this.mConfirmLabel != null)
          localBundle.putCharSequence("confirmLabel", this.mConfirmLabel);
        if (this.mCancelLabel != null)
          localBundle.putCharSequence("cancelLabel", this.mCancelLabel);
        paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", localBundle);
        return paramBuilder;
      }

      @Deprecated
      public CharSequence getCancelLabel()
      {
        return this.mCancelLabel;
      }

      @Deprecated
      public CharSequence getConfirmLabel()
      {
        return this.mConfirmLabel;
      }

      public boolean getHintDisplayActionInline()
      {
        boolean bool;
        if ((0x4 & this.mFlags) != 0)
          bool = true;
        else
          bool = false;
        return bool;
      }

      public boolean getHintLaunchesActivity()
      {
        boolean bool;
        if ((0x2 & this.mFlags) != 0)
          bool = true;
        else
          bool = false;
        return bool;
      }

      @Deprecated
      public CharSequence getInProgressLabel()
      {
        return this.mInProgressLabel;
      }

      public boolean isAvailableOffline()
      {
        int i = this.mFlags;
        int j = 1;
        if ((i & j) == 0)
          j = 0;
        return j;
      }

      public WearableExtender setAvailableOffline(boolean paramBoolean)
      {
        setFlag(1, paramBoolean);
        return this;
      }

      @Deprecated
      public WearableExtender setCancelLabel(CharSequence paramCharSequence)
      {
        this.mCancelLabel = paramCharSequence;
        return this;
      }

      @Deprecated
      public WearableExtender setConfirmLabel(CharSequence paramCharSequence)
      {
        this.mConfirmLabel = paramCharSequence;
        return this;
      }

      public WearableExtender setHintDisplayActionInline(boolean paramBoolean)
      {
        setFlag(4, paramBoolean);
        return this;
      }

      public WearableExtender setHintLaunchesActivity(boolean paramBoolean)
      {
        setFlag(2, paramBoolean);
        return this;
      }

      @Deprecated
      public WearableExtender setInProgressLabel(CharSequence paramCharSequence)
      {
        this.mInProgressLabel = paramCharSequence;
        return this;
      }
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface BadgeIconType
  {
  }

  public static class BigPictureStyle extends NotificationCompat.Style
  {
    private Bitmap mBigLargeIcon;
    private boolean mBigLargeIconSet;
    private Bitmap mPicture;

    public BigPictureStyle()
    {
    }

    public BigPictureStyle(NotificationCompat.Builder paramBuilder)
    {
      setBuilder(paramBuilder);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        Notification.BigPictureStyle localBigPictureStyle = new Notification.BigPictureStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
        if (this.mBigLargeIconSet)
          localBigPictureStyle.bigLargeIcon(this.mBigLargeIcon);
        if (this.mSummaryTextSet)
          localBigPictureStyle.setSummaryText(this.mSummaryText);
      }
    }

    public BigPictureStyle bigLargeIcon(Bitmap paramBitmap)
    {
      this.mBigLargeIcon = paramBitmap;
      this.mBigLargeIconSet = true;
      return this;
    }

    public BigPictureStyle bigPicture(Bitmap paramBitmap)
    {
      this.mPicture = paramBitmap;
      return this;
    }

    public BigPictureStyle setBigContentTitle(CharSequence paramCharSequence)
    {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public BigPictureStyle setSummaryText(CharSequence paramCharSequence)
    {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }

  public static class BigTextStyle extends NotificationCompat.Style
  {
    private CharSequence mBigText;

    public BigTextStyle()
    {
    }

    public BigTextStyle(NotificationCompat.Builder paramBuilder)
    {
      setBuilder(paramBuilder);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        Notification.BigTextStyle localBigTextStyle = new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
        if (this.mSummaryTextSet)
          localBigTextStyle.setSummaryText(this.mSummaryText);
      }
    }

    public BigTextStyle bigText(CharSequence paramCharSequence)
    {
      this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public BigTextStyle setBigContentTitle(CharSequence paramCharSequence)
    {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public BigTextStyle setSummaryText(CharSequence paramCharSequence)
    {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }

  public static class Builder
  {
    private static final int MAX_CHARSEQUENCE_LENGTH = 5120;

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public ArrayList<NotificationCompat.Action> mActions = new ArrayList();
    int mBadgeIcon = 0;
    RemoteViews mBigContentView;
    String mCategory;
    String mChannelId;
    int mColor = 0;
    boolean mColorized;
    boolean mColorizedSet;
    CharSequence mContentInfo;
    PendingIntent mContentIntent;
    CharSequence mContentText;
    CharSequence mContentTitle;
    RemoteViews mContentView;

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public Context mContext;
    Bundle mExtras;
    PendingIntent mFullScreenIntent;
    int mGroupAlertBehavior = 0;
    String mGroupKey;
    boolean mGroupSummary;
    RemoteViews mHeadsUpContentView;
    ArrayList<NotificationCompat.Action> mInvisibleActions = new ArrayList();
    Bitmap mLargeIcon;
    boolean mLocalOnly = false;
    Notification mNotification = new Notification();
    int mNumber;

    @Deprecated
    public ArrayList<String> mPeople;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    Notification mPublicVersion;
    CharSequence[] mRemoteInputHistory;
    String mShortcutId;
    boolean mShowWhen = true;
    String mSortKey;
    NotificationCompat.Style mStyle;
    CharSequence mSubText;
    RemoteViews mTickerView;
    long mTimeout;
    boolean mUseChronometer;
    int mVisibility = 0;

    @Deprecated
    public Builder(Context paramContext)
    {
      this(paramContext, null);
    }

    public Builder(@NonNull Context paramContext, @NonNull String paramString)
    {
      this.mContext = paramContext;
      this.mChannelId = paramString;
      this.mNotification.when = System.currentTimeMillis();
      this.mNotification.audioStreamType = -1;
      this.mPriority = 0;
      this.mPeople = new ArrayList();
    }

    protected static CharSequence limitCharSequenceLength(CharSequence paramCharSequence)
    {
      if (paramCharSequence == null)
        return paramCharSequence;
      if (paramCharSequence.length() > 5120)
        paramCharSequence = paramCharSequence.subSequence(0, 5120);
      return paramCharSequence;
    }

    private Bitmap reduceLargeIconSize(Bitmap paramBitmap)
    {
      if ((paramBitmap != null) && (Build.VERSION.SDK_INT < 27))
      {
        Resources localResources = this.mContext.getResources();
        int i = localResources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
        int j = localResources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
        if ((paramBitmap.getWidth() <= i) && (paramBitmap.getHeight() <= j))
          return paramBitmap;
        double d = Math.min(i / Math.max(1, paramBitmap.getWidth()), j / Math.max(1, paramBitmap.getHeight()));
        return Bitmap.createScaledBitmap(paramBitmap, (int)Math.ceil(d * paramBitmap.getWidth()), (int)Math.ceil(d * paramBitmap.getHeight()), true);
      }
      return paramBitmap;
    }

    private void setFlag(int paramInt, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        Notification localNotification2 = this.mNotification;
        localNotification2.flags = (paramInt | localNotification2.flags);
      }
      else
      {
        Notification localNotification1 = this.mNotification;
        localNotification1.flags &= (paramInt ^ 0xFFFFFFFF);
      }
    }

    public Builder addAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
    {
      this.mActions.add(new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent));
      return this;
    }

    public Builder addAction(NotificationCompat.Action paramAction)
    {
      this.mActions.add(paramAction);
      return this;
    }

    public Builder addExtras(Bundle paramBundle)
    {
      if (paramBundle != null)
        if (this.mExtras == null)
          this.mExtras = new Bundle(paramBundle);
        else
          this.mExtras.putAll(paramBundle);
      return this;
    }

    @RequiresApi(21)
    public Builder addInvisibleAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
    {
      return addInvisibleAction(new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent));
    }

    @RequiresApi(21)
    public Builder addInvisibleAction(NotificationCompat.Action paramAction)
    {
      this.mInvisibleActions.add(paramAction);
      return this;
    }

    public Builder addPerson(String paramString)
    {
      this.mPeople.add(paramString);
      return this;
    }

    public Notification build()
    {
      return new NotificationCompatBuilder(this).build();
    }

    public Builder extend(NotificationCompat.Extender paramExtender)
    {
      paramExtender.extend(this);
      return this;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getBigContentView()
    {
      return this.mBigContentView;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public int getColor()
    {
      return this.mColor;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getContentView()
    {
      return this.mContentView;
    }

    public Bundle getExtras()
    {
      if (this.mExtras == null)
        this.mExtras = new Bundle();
      return this.mExtras;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getHeadsUpContentView()
    {
      return this.mHeadsUpContentView;
    }

    @Deprecated
    public Notification getNotification()
    {
      return build();
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public int getPriority()
    {
      return this.mPriority;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public long getWhenIfShowing()
    {
      long l;
      if (this.mShowWhen)
        l = this.mNotification.when;
      else
        l = 0L;
      return l;
    }

    public Builder setAutoCancel(boolean paramBoolean)
    {
      setFlag(16, paramBoolean);
      return this;
    }

    public Builder setBadgeIconType(int paramInt)
    {
      this.mBadgeIcon = paramInt;
      return this;
    }

    public Builder setCategory(String paramString)
    {
      this.mCategory = paramString;
      return this;
    }

    public Builder setChannelId(@NonNull String paramString)
    {
      this.mChannelId = paramString;
      return this;
    }

    public Builder setColor(@ColorInt int paramInt)
    {
      this.mColor = paramInt;
      return this;
    }

    public Builder setColorized(boolean paramBoolean)
    {
      this.mColorized = paramBoolean;
      this.mColorizedSet = true;
      return this;
    }

    public Builder setContent(RemoteViews paramRemoteViews)
    {
      this.mNotification.contentView = paramRemoteViews;
      return this;
    }

    public Builder setContentInfo(CharSequence paramCharSequence)
    {
      this.mContentInfo = limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public Builder setContentIntent(PendingIntent paramPendingIntent)
    {
      this.mContentIntent = paramPendingIntent;
      return this;
    }

    public Builder setContentText(CharSequence paramCharSequence)
    {
      this.mContentText = limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public Builder setContentTitle(CharSequence paramCharSequence)
    {
      this.mContentTitle = limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public Builder setCustomBigContentView(RemoteViews paramRemoteViews)
    {
      this.mBigContentView = paramRemoteViews;
      return this;
    }

    public Builder setCustomContentView(RemoteViews paramRemoteViews)
    {
      this.mContentView = paramRemoteViews;
      return this;
    }

    public Builder setCustomHeadsUpContentView(RemoteViews paramRemoteViews)
    {
      this.mHeadsUpContentView = paramRemoteViews;
      return this;
    }

    public Builder setDefaults(int paramInt)
    {
      this.mNotification.defaults = paramInt;
      if ((paramInt & 0x4) != 0)
      {
        Notification localNotification = this.mNotification;
        localNotification.flags = (0x1 | localNotification.flags);
      }
      return this;
    }

    public Builder setDeleteIntent(PendingIntent paramPendingIntent)
    {
      this.mNotification.deleteIntent = paramPendingIntent;
      return this;
    }

    public Builder setExtras(Bundle paramBundle)
    {
      this.mExtras = paramBundle;
      return this;
    }

    public Builder setFullScreenIntent(PendingIntent paramPendingIntent, boolean paramBoolean)
    {
      this.mFullScreenIntent = paramPendingIntent;
      setFlag(128, paramBoolean);
      return this;
    }

    public Builder setGroup(String paramString)
    {
      this.mGroupKey = paramString;
      return this;
    }

    public Builder setGroupAlertBehavior(int paramInt)
    {
      this.mGroupAlertBehavior = paramInt;
      return this;
    }

    public Builder setGroupSummary(boolean paramBoolean)
    {
      this.mGroupSummary = paramBoolean;
      return this;
    }

    public Builder setLargeIcon(Bitmap paramBitmap)
    {
      this.mLargeIcon = reduceLargeIconSize(paramBitmap);
      return this;
    }

    public Builder setLights(@ColorInt int paramInt1, int paramInt2, int paramInt3)
    {
      this.mNotification.ledARGB = paramInt1;
      this.mNotification.ledOnMS = paramInt2;
      this.mNotification.ledOffMS = paramInt3;
      int i;
      if ((this.mNotification.ledOnMS != 0) && (this.mNotification.ledOffMS != 0))
        i = 1;
      else
        i = 0;
      this.mNotification.flags = (i | 0xFFFFFFFE & this.mNotification.flags);
      return this;
    }

    public Builder setLocalOnly(boolean paramBoolean)
    {
      this.mLocalOnly = paramBoolean;
      return this;
    }

    public Builder setNumber(int paramInt)
    {
      this.mNumber = paramInt;
      return this;
    }

    public Builder setOngoing(boolean paramBoolean)
    {
      setFlag(2, paramBoolean);
      return this;
    }

    public Builder setOnlyAlertOnce(boolean paramBoolean)
    {
      setFlag(8, paramBoolean);
      return this;
    }

    public Builder setPriority(int paramInt)
    {
      this.mPriority = paramInt;
      return this;
    }

    public Builder setProgress(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      this.mProgressMax = paramInt1;
      this.mProgress = paramInt2;
      this.mProgressIndeterminate = paramBoolean;
      return this;
    }

    public Builder setPublicVersion(Notification paramNotification)
    {
      this.mPublicVersion = paramNotification;
      return this;
    }

    public Builder setRemoteInputHistory(CharSequence[] paramArrayOfCharSequence)
    {
      this.mRemoteInputHistory = paramArrayOfCharSequence;
      return this;
    }

    public Builder setShortcutId(String paramString)
    {
      this.mShortcutId = paramString;
      return this;
    }

    public Builder setShowWhen(boolean paramBoolean)
    {
      this.mShowWhen = paramBoolean;
      return this;
    }

    public Builder setSmallIcon(int paramInt)
    {
      this.mNotification.icon = paramInt;
      return this;
    }

    public Builder setSmallIcon(int paramInt1, int paramInt2)
    {
      this.mNotification.icon = paramInt1;
      this.mNotification.iconLevel = paramInt2;
      return this;
    }

    public Builder setSortKey(String paramString)
    {
      this.mSortKey = paramString;
      return this;
    }

    public Builder setSound(Uri paramUri)
    {
      this.mNotification.sound = paramUri;
      this.mNotification.audioStreamType = -1;
      if (Build.VERSION.SDK_INT >= 21)
        this.mNotification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
      return this;
    }

    public Builder setSound(Uri paramUri, int paramInt)
    {
      this.mNotification.sound = paramUri;
      this.mNotification.audioStreamType = paramInt;
      if (Build.VERSION.SDK_INT >= 21)
        this.mNotification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(paramInt).build();
      return this;
    }

    public Builder setStyle(NotificationCompat.Style paramStyle)
    {
      if (this.mStyle != paramStyle)
      {
        this.mStyle = paramStyle;
        if (this.mStyle != null)
          this.mStyle.setBuilder(this);
      }
      return this;
    }

    public Builder setSubText(CharSequence paramCharSequence)
    {
      this.mSubText = limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public Builder setTicker(CharSequence paramCharSequence)
    {
      this.mNotification.tickerText = limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public Builder setTicker(CharSequence paramCharSequence, RemoteViews paramRemoteViews)
    {
      this.mNotification.tickerText = limitCharSequenceLength(paramCharSequence);
      this.mTickerView = paramRemoteViews;
      return this;
    }

    public Builder setTimeoutAfter(long paramLong)
    {
      this.mTimeout = paramLong;
      return this;
    }

    public Builder setUsesChronometer(boolean paramBoolean)
    {
      this.mUseChronometer = paramBoolean;
      return this;
    }

    public Builder setVibrate(long[] paramArrayOfLong)
    {
      this.mNotification.vibrate = paramArrayOfLong;
      return this;
    }

    public Builder setVisibility(int paramInt)
    {
      this.mVisibility = paramInt;
      return this;
    }

    public Builder setWhen(long paramLong)
    {
      this.mNotification.when = paramLong;
      return this;
    }
  }

  public static final class CarExtender
    implements NotificationCompat.Extender
  {

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    private static final String EXTRA_COLOR = "app_color";
    private static final String EXTRA_CONVERSATION = "car_conversation";

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
    private static final String EXTRA_LARGE_ICON = "large_icon";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ON_READ = "on_read";
    private static final String KEY_ON_REPLY = "on_reply";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_REMOTE_INPUT = "remote_input";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";
    private int mColor = 0;
    private Bitmap mLargeIcon;
    private UnreadConversation mUnreadConversation;

    public CarExtender()
    {
    }

    public CarExtender(Notification paramNotification)
    {
      if (Build.VERSION.SDK_INT < 21)
        return;
      Bundle localBundle;
      if (NotificationCompat.getExtras(paramNotification) == null)
        localBundle = null;
      else
        localBundle = NotificationCompat.getExtras(paramNotification).getBundle("android.car.EXTENSIONS");
      if (localBundle != null)
      {
        this.mLargeIcon = ((Bitmap)localBundle.getParcelable("large_icon"));
        this.mColor = localBundle.getInt("app_color", 0);
        this.mUnreadConversation = getUnreadConversationFromBundle(localBundle.getBundle("car_conversation"));
      }
    }

    @RequiresApi(21)
    private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation paramUnreadConversation)
    {
      Bundle localBundle1 = new Bundle();
      String[] arrayOfString = paramUnreadConversation.getParticipants();
      int i = 0;
      String str;
      if ((arrayOfString != null) && (paramUnreadConversation.getParticipants().length > 1))
        str = paramUnreadConversation.getParticipants()[0];
      else
        str = null;
      Parcelable[] arrayOfParcelable = new Parcelable[paramUnreadConversation.getMessages().length];
      while (i < arrayOfParcelable.length)
      {
        Bundle localBundle2 = new Bundle();
        localBundle2.putString("text", paramUnreadConversation.getMessages()[i]);
        localBundle2.putString("author", str);
        arrayOfParcelable[i] = localBundle2;
        i++;
      }
      localBundle1.putParcelableArray("messages", arrayOfParcelable);
      RemoteInput localRemoteInput = paramUnreadConversation.getRemoteInput();
      if (localRemoteInput != null)
        localBundle1.putParcelable("remote_input", new RemoteInput.Builder(localRemoteInput.getResultKey()).setLabel(localRemoteInput.getLabel()).setChoices(localRemoteInput.getChoices()).setAllowFreeFormInput(localRemoteInput.getAllowFreeFormInput()).addExtras(localRemoteInput.getExtras()).build());
      localBundle1.putParcelable("on_reply", paramUnreadConversation.getReplyPendingIntent());
      localBundle1.putParcelable("on_read", paramUnreadConversation.getReadPendingIntent());
      localBundle1.putStringArray("participants", paramUnreadConversation.getParticipants());
      localBundle1.putLong("timestamp", paramUnreadConversation.getLatestTimestamp());
      return localBundle1;
    }

    @RequiresApi(21)
    private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle paramBundle)
    {
      if (paramBundle == null)
        return null;
      Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("messages");
      label91: String[] arrayOfString1;
      if (arrayOfParcelable != null)
      {
        String[] arrayOfString3 = new String[arrayOfParcelable.length];
        for (int i = 0; i < arrayOfString3.length; i++)
        {
          if (!(arrayOfParcelable[i] instanceof Bundle))
          {
            j = 0;
            break label91;
          }
          arrayOfString3[i] = ((Bundle)arrayOfParcelable[i]).getString("text");
          if (arrayOfString3[i] == null)
          {
            j = 0;
            break label91;
          }
        }
        int j = 1;
        if (j != 0)
          arrayOfString1 = arrayOfString3;
        else
          return null;
      }
      else
      {
        arrayOfString1 = null;
      }
      PendingIntent localPendingIntent1 = (PendingIntent)paramBundle.getParcelable("on_read");
      PendingIntent localPendingIntent2 = (PendingIntent)paramBundle.getParcelable("on_reply");
      android.app.RemoteInput localRemoteInput = (android.app.RemoteInput)paramBundle.getParcelable("remote_input");
      String[] arrayOfString2 = paramBundle.getStringArray("participants");
      if ((arrayOfString2 != null) && (arrayOfString2.length == 1))
      {
        RemoteInput localRemoteInput1 = null;
        if (localRemoteInput != null)
        {
          String str = localRemoteInput.getResultKey();
          CharSequence localCharSequence = localRemoteInput.getLabel();
          CharSequence[] arrayOfCharSequence = localRemoteInput.getChoices();
          boolean bool = localRemoteInput.getAllowFreeFormInput();
          Bundle localBundle = localRemoteInput.getExtras();
          localRemoteInput1 = new RemoteInput(str, localCharSequence, arrayOfCharSequence, bool, localBundle, null);
        }
        RemoteInput localRemoteInput2 = localRemoteInput1;
        UnreadConversation localUnreadConversation = new UnreadConversation(arrayOfString1, localRemoteInput2, localPendingIntent2, localPendingIntent1, arrayOfString2, paramBundle.getLong("timestamp"));
        return localUnreadConversation;
      }
      return null;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder)
    {
      if (Build.VERSION.SDK_INT < 21)
        return paramBuilder;
      Bundle localBundle = new Bundle();
      if (this.mLargeIcon != null)
        localBundle.putParcelable("large_icon", this.mLargeIcon);
      if (this.mColor != 0)
        localBundle.putInt("app_color", this.mColor);
      if (this.mUnreadConversation != null)
        localBundle.putBundle("car_conversation", getBundleForUnreadConversation(this.mUnreadConversation));
      paramBuilder.getExtras().putBundle("android.car.EXTENSIONS", localBundle);
      return paramBuilder;
    }

    @ColorInt
    public int getColor()
    {
      return this.mColor;
    }

    public Bitmap getLargeIcon()
    {
      return this.mLargeIcon;
    }

    public UnreadConversation getUnreadConversation()
    {
      return this.mUnreadConversation;
    }

    public CarExtender setColor(@ColorInt int paramInt)
    {
      this.mColor = paramInt;
      return this;
    }

    public CarExtender setLargeIcon(Bitmap paramBitmap)
    {
      this.mLargeIcon = paramBitmap;
      return this;
    }

    public CarExtender setUnreadConversation(UnreadConversation paramUnreadConversation)
    {
      this.mUnreadConversation = paramUnreadConversation;
      return this;
    }

    public static class UnreadConversation
    {
      private final long mLatestTimestamp;
      private final String[] mMessages;
      private final String[] mParticipants;
      private final PendingIntent mReadPendingIntent;
      private final RemoteInput mRemoteInput;
      private final PendingIntent mReplyPendingIntent;

      UnreadConversation(String[] paramArrayOfString1, RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong)
      {
        this.mMessages = paramArrayOfString1;
        this.mRemoteInput = paramRemoteInput;
        this.mReadPendingIntent = paramPendingIntent2;
        this.mReplyPendingIntent = paramPendingIntent1;
        this.mParticipants = paramArrayOfString2;
        this.mLatestTimestamp = paramLong;
      }

      public long getLatestTimestamp()
      {
        return this.mLatestTimestamp;
      }

      public String[] getMessages()
      {
        return this.mMessages;
      }

      public String getParticipant()
      {
        String str;
        if (this.mParticipants.length > 0)
          str = this.mParticipants[0];
        else
          str = null;
        return str;
      }

      public String[] getParticipants()
      {
        return this.mParticipants;
      }

      public PendingIntent getReadPendingIntent()
      {
        return this.mReadPendingIntent;
      }

      public RemoteInput getRemoteInput()
      {
        return this.mRemoteInput;
      }

      public PendingIntent getReplyPendingIntent()
      {
        return this.mReplyPendingIntent;
      }

      public static class Builder
      {
        private long mLatestTimestamp;
        private final List<String> mMessages = new ArrayList();
        private final String mParticipant;
        private PendingIntent mReadPendingIntent;
        private RemoteInput mRemoteInput;
        private PendingIntent mReplyPendingIntent;

        public Builder(String paramString)
        {
          this.mParticipant = paramString;
        }

        public Builder addMessage(String paramString)
        {
          this.mMessages.add(paramString);
          return this;
        }

        public NotificationCompat.CarExtender.UnreadConversation build()
        {
          String[] arrayOfString1 = (String[])this.mMessages.toArray(new String[this.mMessages.size()]);
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = this.mParticipant;
          NotificationCompat.CarExtender.UnreadConversation localUnreadConversation = new NotificationCompat.CarExtender.UnreadConversation(arrayOfString1, this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, arrayOfString2, this.mLatestTimestamp);
          return localUnreadConversation;
        }

        public Builder setLatestTimestamp(long paramLong)
        {
          this.mLatestTimestamp = paramLong;
          return this;
        }

        public Builder setReadPendingIntent(PendingIntent paramPendingIntent)
        {
          this.mReadPendingIntent = paramPendingIntent;
          return this;
        }

        public Builder setReplyAction(PendingIntent paramPendingIntent, RemoteInput paramRemoteInput)
        {
          this.mRemoteInput = paramRemoteInput;
          this.mReplyPendingIntent = paramPendingIntent;
          return this;
        }
      }
    }
  }

  public static class DecoratedCustomViewStyle extends NotificationCompat.Style
  {
    private static final int MAX_ACTION_BUTTONS = 3;

    private RemoteViews createRemoteViews(RemoteViews paramRemoteViews, boolean paramBoolean)
    {
      int i = R.layout.notification_template_custom_big;
      boolean bool = true;
      RemoteViews localRemoteViews1 = applyStandardTemplate(bool, i, false);
      localRemoteViews1.removeAllViews(R.id.actions);
      if ((paramBoolean) && (this.mBuilder.mActions != null))
      {
        int k = Math.min(this.mBuilder.mActions.size(), 3);
        if (k > 0)
          for (int m = 0; m < k; m++)
          {
            RemoteViews localRemoteViews2 = generateActionButton((NotificationCompat.Action)this.mBuilder.mActions.get(m));
            localRemoteViews1.addView(R.id.actions, localRemoteViews2);
          }
      }
      bool = false;
      int j;
      if (bool)
        j = 0;
      else
        j = 8;
      localRemoteViews1.setViewVisibility(R.id.actions, j);
      localRemoteViews1.setViewVisibility(R.id.action_divider, j);
      buildIntoRemoteViews(localRemoteViews1, paramRemoteViews);
      return localRemoteViews1;
    }

    private RemoteViews generateActionButton(NotificationCompat.Action paramAction)
    {
      int i;
      if (paramAction.actionIntent == null)
        i = 1;
      else
        i = 0;
      String str = this.mBuilder.mContext.getPackageName();
      int j;
      if (i != 0)
        j = R.layout.notification_action_tombstone;
      else
        j = R.layout.notification_action;
      RemoteViews localRemoteViews = new RemoteViews(str, j);
      localRemoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(paramAction.getIcon(), this.mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
      localRemoteViews.setTextViewText(R.id.action_text, paramAction.title);
      if (i == 0)
        localRemoteViews.setOnClickPendingIntent(R.id.action_container, paramAction.actionIntent);
      if (Build.VERSION.SDK_INT >= 15)
        localRemoteViews.setContentDescription(R.id.action_container, paramAction.title);
      return localRemoteViews;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        return null;
      RemoteViews localRemoteViews = this.mBuilder.getBigContentView();
      if (localRemoteViews == null)
        localRemoteViews = this.mBuilder.getContentView();
      if (localRemoteViews == null)
        return null;
      return createRemoteViews(localRemoteViews, true);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        return null;
      if (this.mBuilder.getContentView() == null)
        return null;
      return createRemoteViews(this.mBuilder.getContentView(), false);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        return null;
      RemoteViews localRemoteViews1 = this.mBuilder.getHeadsUpContentView();
      RemoteViews localRemoteViews2;
      if (localRemoteViews1 != null)
        localRemoteViews2 = localRemoteViews1;
      else
        localRemoteViews2 = this.mBuilder.getContentView();
      if (localRemoteViews1 == null)
        return null;
      return createRemoteViews(localRemoteViews2, true);
    }
  }

  public static abstract interface Extender
  {
    public abstract NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder);
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface GroupAlertBehavior
  {
  }

  public static class InboxStyle extends NotificationCompat.Style
  {
    private ArrayList<CharSequence> mTexts = new ArrayList();

    public InboxStyle()
    {
    }

    public InboxStyle(NotificationCompat.Builder paramBuilder)
    {
      setBuilder(paramBuilder);
    }

    public InboxStyle addLine(CharSequence paramCharSequence)
    {
      this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence));
      return this;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        Notification.InboxStyle localInboxStyle = new Notification.InboxStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
        if (this.mSummaryTextSet)
          localInboxStyle.setSummaryText(this.mSummaryText);
        Iterator localIterator = this.mTexts.iterator();
        while (localIterator.hasNext())
          localInboxStyle.addLine((CharSequence)localIterator.next());
      }
    }

    public InboxStyle setBigContentTitle(CharSequence paramCharSequence)
    {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }

    public InboxStyle setSummaryText(CharSequence paramCharSequence)
    {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }

  public static class MessagingStyle extends NotificationCompat.Style
  {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;

    @Nullable
    private CharSequence mConversationTitle;

    @Nullable
    private Boolean mIsGroupConversation;
    private final List<Message> mMessages = new ArrayList();
    private Person mUser;

    private MessagingStyle()
    {
    }

    public MessagingStyle(@NonNull Person paramPerson)
    {
      if (TextUtils.isEmpty(paramPerson.getName()))
        throw new IllegalArgumentException("User's name must not be empty.");
      this.mUser = paramPerson;
    }

    @Deprecated
    public MessagingStyle(@NonNull CharSequence paramCharSequence)
    {
      this.mUser = new Person.Builder().setName(paramCharSequence).build();
    }

    // ERROR //
    @Nullable
    public static MessagingStyle extractMessagingStyleFromNotification(Notification paramNotification)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokestatic 72	android/support/v4/app/NotificationCompat:getExtras	(Landroid/app/Notification;)Landroid/os/Bundle;
      //   4: astore_1
      //   5: aload_1
      //   6: ifnull +23 -> 29
      //   9: aload_1
      //   10: ldc 74
      //   12: invokevirtual 80	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
      //   15: ifne +14 -> 29
      //   18: aload_1
      //   19: ldc 82
      //   21: invokevirtual 80	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
      //   24: ifne +5 -> 29
      //   27: aconst_null
      //   28: areturn
      //   29: new 2	android/support/v4/app/NotificationCompat$MessagingStyle
      //   32: dup
      //   33: invokespecial 83	android/support/v4/app/NotificationCompat$MessagingStyle:<init>	()V
      //   36: astore_2
      //   37: aload_2
      //   38: aload_1
      //   39: invokevirtual 87	android/support/v4/app/NotificationCompat$MessagingStyle:restoreFromCompatExtras	(Landroid/os/Bundle;)V
      //   42: aload_2
      //   43: areturn
      //   44: aconst_null
      //   45: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   29	42	44	java/lang/ClassCastException
    }

    @Nullable
    private Message findLatestIncomingMessage()
    {
      for (int i = -1 + this.mMessages.size(); i >= 0; i--)
      {
        Message localMessage = (Message)this.mMessages.get(i);
        if ((localMessage.getPerson() != null) && (!TextUtils.isEmpty(localMessage.getPerson().getName())))
          return localMessage;
      }
      if (!this.mMessages.isEmpty())
        return (Message)this.mMessages.get(-1 + this.mMessages.size());
      return null;
    }

    private boolean hasMessagesWithoutSender()
    {
      for (int i = this.mMessages.size() - 1; i >= 0; i--)
      {
        Message localMessage = (Message)this.mMessages.get(i);
        if ((localMessage.getPerson() != null) && (localMessage.getPerson().getName() == null))
          return true;
      }
      return false;
    }

    @NonNull
    private TextAppearanceSpan makeFontColorSpan(int paramInt)
    {
      TextAppearanceSpan localTextAppearanceSpan = new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(paramInt), null);
      return localTextAppearanceSpan;
    }

    private CharSequence makeMessageLine(Message paramMessage)
    {
      BidiFormatter localBidiFormatter = BidiFormatter.getInstance();
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
      int i;
      if (Build.VERSION.SDK_INT >= 21)
        i = 1;
      else
        i = 0;
      int j;
      if (i != 0)
        j = -16777216;
      else
        j = -1;
      Object localObject1;
      if (paramMessage.getPerson() == null)
        localObject1 = "";
      else
        localObject1 = paramMessage.getPerson().getName();
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = this.mUser.getName();
        if ((i != 0) && (this.mBuilder.getColor() != 0))
          j = this.mBuilder.getColor();
      }
      CharSequence localCharSequence = localBidiFormatter.unicodeWrap((CharSequence)localObject1);
      localSpannableStringBuilder.append(localCharSequence);
      localSpannableStringBuilder.setSpan(makeFontColorSpan(j), localSpannableStringBuilder.length() - localCharSequence.length(), localSpannableStringBuilder.length(), 33);
      Object localObject2;
      if (paramMessage.getText() == null)
        localObject2 = "";
      else
        localObject2 = paramMessage.getText();
      localSpannableStringBuilder.append("  ").append(localBidiFormatter.unicodeWrap((CharSequence)localObject2));
      return localSpannableStringBuilder;
    }

    public void addCompatExtras(Bundle paramBundle)
    {
      super.addCompatExtras(paramBundle);
      paramBundle.putCharSequence("android.selfDisplayName", this.mUser.getName());
      paramBundle.putBundle("android.messagingStyleUser", this.mUser.toBundle());
      paramBundle.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
      if ((this.mConversationTitle != null) && (this.mIsGroupConversation.booleanValue()))
        paramBundle.putCharSequence("android.conversationTitle", this.mConversationTitle);
      if (!this.mMessages.isEmpty())
        paramBundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(this.mMessages));
      if (this.mIsGroupConversation != null)
        paramBundle.putBoolean("android.isGroupConversation", this.mIsGroupConversation.booleanValue());
    }

    public MessagingStyle addMessage(Message paramMessage)
    {
      this.mMessages.add(paramMessage);
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0);
      return this;
    }

    public MessagingStyle addMessage(CharSequence paramCharSequence, long paramLong, Person paramPerson)
    {
      addMessage(new Message(paramCharSequence, paramLong, paramPerson));
      return this;
    }

    @Deprecated
    public MessagingStyle addMessage(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2)
    {
      this.mMessages.add(new Message(paramCharSequence1, paramLong, new Person.Builder().setName(paramCharSequence2).build()));
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0);
      return this;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      setGroupConversation(isGroupConversation());
      if (Build.VERSION.SDK_INT >= 24)
      {
        Notification.MessagingStyle localMessagingStyle;
        if (Build.VERSION.SDK_INT >= 28)
          localMessagingStyle = new Notification.MessagingStyle(this.mUser.toAndroidPerson());
        else
          localMessagingStyle = new Notification.MessagingStyle(this.mUser.getName());
        if ((this.mIsGroupConversation.booleanValue()) || (Build.VERSION.SDK_INT >= 28))
          localMessagingStyle.setConversationTitle(this.mConversationTitle);
        if (Build.VERSION.SDK_INT >= 28)
          localMessagingStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
        Iterator localIterator = this.mMessages.iterator();
        while (localIterator.hasNext())
        {
          Message localMessage3 = (Message)localIterator.next();
          Notification.MessagingStyle.Message localMessage;
          if (Build.VERSION.SDK_INT >= 28)
          {
            Person localPerson = localMessage3.getPerson();
            CharSequence localCharSequence4 = localMessage3.getText();
            long l = localMessage3.getTimestamp();
            android.app.Person localPerson1;
            if (localPerson == null)
              localPerson1 = null;
            else
              localPerson1 = localPerson.toAndroidPerson();
            localMessage = new Notification.MessagingStyle.Message(localCharSequence4, l, localPerson1);
          }
          else
          {
            CharSequence localCharSequence3;
            if (localMessage3.getPerson() != null)
              localCharSequence3 = localMessage3.getPerson().getName();
            else
              localCharSequence3 = null;
            localMessage = new Notification.MessagingStyle.Message(localMessage3.getText(), localMessage3.getTimestamp(), localCharSequence3);
          }
          if (localMessage3.getDataMimeType() != null)
            localMessage.setData(localMessage3.getDataMimeType(), localMessage3.getDataUri());
          localMessagingStyle.addMessage(localMessage);
        }
        localMessagingStyle.setBuilder(paramNotificationBuilderWithBuilderAccessor.getBuilder());
      }
      else
      {
        Message localMessage1 = findLatestIncomingMessage();
        if ((this.mConversationTitle != null) && (this.mIsGroupConversation.booleanValue()))
        {
          paramNotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(this.mConversationTitle);
        }
        else if (localMessage1 != null)
        {
          paramNotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle("");
          if (localMessage1.getPerson() != null)
            paramNotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(localMessage1.getPerson().getName());
        }
        if (localMessage1 != null)
        {
          Notification.Builder localBuilder = paramNotificationBuilderWithBuilderAccessor.getBuilder();
          CharSequence localCharSequence2;
          if (this.mConversationTitle != null)
            localCharSequence2 = makeMessageLine(localMessage1);
          else
            localCharSequence2 = localMessage1.getText();
          localBuilder.setContentText(localCharSequence2);
        }
        if (Build.VERSION.SDK_INT >= 16)
        {
          SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
          int i;
          if ((this.mConversationTitle == null) && (!hasMessagesWithoutSender()))
            i = 0;
          else
            i = 1;
          for (int j = this.mMessages.size() - 1; j >= 0; j--)
          {
            Message localMessage2 = (Message)this.mMessages.get(j);
            CharSequence localCharSequence1;
            if (i != 0)
              localCharSequence1 = makeMessageLine(localMessage2);
            else
              localCharSequence1 = localMessage2.getText();
            if (j != this.mMessages.size() - 1)
              localSpannableStringBuilder.insert(0, "\n");
            localSpannableStringBuilder.insert(0, localCharSequence1);
          }
          new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(null).bigText(localSpannableStringBuilder);
        }
      }
    }

    @Nullable
    public CharSequence getConversationTitle()
    {
      return this.mConversationTitle;
    }

    public List<Message> getMessages()
    {
      return this.mMessages;
    }

    public Person getUser()
    {
      return this.mUser;
    }

    @Deprecated
    public CharSequence getUserDisplayName()
    {
      return this.mUser.getName();
    }

    public boolean isGroupConversation()
    {
      if ((this.mBuilder != null) && (this.mBuilder.mContext.getApplicationInfo().targetSdkVersion < 28) && (this.mIsGroupConversation == null))
      {
        CharSequence localCharSequence = this.mConversationTitle;
        boolean bool2 = false;
        if (localCharSequence != null)
          bool2 = true;
        return bool2;
      }
      Boolean localBoolean = this.mIsGroupConversation;
      boolean bool1 = false;
      if (localBoolean != null)
        bool1 = this.mIsGroupConversation.booleanValue();
      return bool1;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    protected void restoreFromCompatExtras(Bundle paramBundle)
    {
      this.mMessages.clear();
      if (paramBundle.containsKey("android.messagingStyleUser"))
        this.mUser = Person.fromBundle(paramBundle.getBundle("android.messagingStyleUser"));
      else
        this.mUser = new Person.Builder().setName(paramBundle.getString("android.selfDisplayName")).build();
      this.mConversationTitle = paramBundle.getCharSequence("android.conversationTitle");
      if (this.mConversationTitle == null)
        this.mConversationTitle = paramBundle.getCharSequence("android.hiddenConversationTitle");
      Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("android.messages");
      if (arrayOfParcelable != null)
        this.mMessages.addAll(Message.getMessagesFromBundleArray(arrayOfParcelable));
      if (paramBundle.containsKey("android.isGroupConversation"))
        this.mIsGroupConversation = Boolean.valueOf(paramBundle.getBoolean("android.isGroupConversation"));
    }

    public MessagingStyle setConversationTitle(@Nullable CharSequence paramCharSequence)
    {
      this.mConversationTitle = paramCharSequence;
      return this;
    }

    public MessagingStyle setGroupConversation(boolean paramBoolean)
    {
      this.mIsGroupConversation = Boolean.valueOf(paramBoolean);
      return this;
    }

    public static final class Message
    {
      static final String KEY_DATA_MIME_TYPE = "type";
      static final String KEY_DATA_URI = "uri";
      static final String KEY_EXTRAS_BUNDLE = "extras";
      static final String KEY_NOTIFICATION_PERSON = "sender_person";
      static final String KEY_PERSON = "person";
      static final String KEY_SENDER = "sender";
      static final String KEY_TEXT = "text";
      static final String KEY_TIMESTAMP = "time";

      @Nullable
      private String mDataMimeType;

      @Nullable
      private Uri mDataUri;
      private Bundle mExtras = new Bundle();

      @Nullable
      private final Person mPerson;
      private final CharSequence mText;
      private final long mTimestamp;

      public Message(CharSequence paramCharSequence, long paramLong, @Nullable Person paramPerson)
      {
        this.mText = paramCharSequence;
        this.mTimestamp = paramLong;
        this.mPerson = paramPerson;
      }

      @Deprecated
      public Message(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2)
      {
        this(paramCharSequence1, paramLong, new Person.Builder().setName(paramCharSequence2).build());
      }

      @NonNull
      static Bundle[] getBundleArrayForMessages(List<Message> paramList)
      {
        Bundle[] arrayOfBundle = new Bundle[paramList.size()];
        int i = paramList.size();
        for (int j = 0; j < i; j++)
          arrayOfBundle[j] = ((Message)paramList.get(j)).toBundle();
        return arrayOfBundle;
      }

      @Nullable
      static Message getMessageFromBundle(Bundle paramBundle)
      {
        Person localPerson;
        label195: 
        while (true)
          try
          {
            if ((paramBundle.containsKey("text")) && (paramBundle.containsKey("time")))
            {
              if (paramBundle.containsKey("person"))
              {
                localPerson = Person.fromBundle(paramBundle.getBundle("person"));
              }
              else if ((paramBundle.containsKey("sender_person")) && (Build.VERSION.SDK_INT >= 28))
              {
                localPerson = Person.fromAndroidPerson((android.app.Person)paramBundle.getParcelable("sender_person"));
              }
              else
              {
                if (!paramBundle.containsKey("sender"))
                  break label195;
                localPerson = new Person.Builder().setName(paramBundle.getCharSequence("sender")).build();
              }
              Message localMessage = new Message(paramBundle.getCharSequence("text"), paramBundle.getLong("time"), localPerson);
              if ((paramBundle.containsKey("type")) && (paramBundle.containsKey("uri")))
                localMessage.setData(paramBundle.getString("type"), (Uri)paramBundle.getParcelable("uri"));
              if (paramBundle.containsKey("extras"))
                localMessage.getExtras().putAll(paramBundle.getBundle("extras"));
              return localMessage;
            }
            else
            {
              return null;
            }
          }
          catch (ClassCastException localClassCastException)
          {
            return null;
            localPerson = null;
            tmpTernaryOp = localClassCastException;
          }
      }

      @NonNull
      static List<Message> getMessagesFromBundleArray(Parcelable[] paramArrayOfParcelable)
      {
        ArrayList localArrayList = new ArrayList(paramArrayOfParcelable.length);
        for (int i = 0; i < paramArrayOfParcelable.length; i++)
          if ((paramArrayOfParcelable[i] instanceof Bundle))
          {
            Message localMessage = getMessageFromBundle((Bundle)paramArrayOfParcelable[i]);
            if (localMessage != null)
              localArrayList.add(localMessage);
          }
        return localArrayList;
      }

      private Bundle toBundle()
      {
        Bundle localBundle = new Bundle();
        if (this.mText != null)
          localBundle.putCharSequence("text", this.mText);
        localBundle.putLong("time", this.mTimestamp);
        if (this.mPerson != null)
        {
          localBundle.putCharSequence("sender", this.mPerson.getName());
          if (Build.VERSION.SDK_INT >= 28)
            localBundle.putParcelable("sender_person", this.mPerson.toAndroidPerson());
          else
            localBundle.putBundle("person", this.mPerson.toBundle());
        }
        if (this.mDataMimeType != null)
          localBundle.putString("type", this.mDataMimeType);
        if (this.mDataUri != null)
          localBundle.putParcelable("uri", this.mDataUri);
        if (this.mExtras != null)
          localBundle.putBundle("extras", this.mExtras);
        return localBundle;
      }

      @Nullable
      public String getDataMimeType()
      {
        return this.mDataMimeType;
      }

      @Nullable
      public Uri getDataUri()
      {
        return this.mDataUri;
      }

      @NonNull
      public Bundle getExtras()
      {
        return this.mExtras;
      }

      @Nullable
      public Person getPerson()
      {
        return this.mPerson;
      }

      @Deprecated
      @Nullable
      public CharSequence getSender()
      {
        CharSequence localCharSequence;
        if (this.mPerson == null)
          localCharSequence = null;
        else
          localCharSequence = this.mPerson.getName();
        return localCharSequence;
      }

      @NonNull
      public CharSequence getText()
      {
        return this.mText;
      }

      public long getTimestamp()
      {
        return this.mTimestamp;
      }

      public Message setData(String paramString, Uri paramUri)
      {
        this.mDataMimeType = paramString;
        this.mDataUri = paramUri;
        return this;
      }
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface NotificationVisibility
  {
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface StreamType
  {
  }

  public static abstract class Style
  {
    CharSequence mBigContentTitle;

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    protected NotificationCompat.Builder mBuilder;
    CharSequence mSummaryText;
    boolean mSummaryTextSet = false;

    private int calculateTopPadding()
    {
      Resources localResources = this.mBuilder.mContext.getResources();
      int i = localResources.getDimensionPixelSize(R.dimen.notification_top_pad);
      int j = localResources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
      float f = (constrain(localResources.getConfiguration().fontScale, 1.0F, 1.3F) - 1.0F) / 0.3F;
      return Math.round((1.0F - f) * i + f * j);
    }

    private static float constrain(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      if (paramFloat1 < paramFloat2)
        paramFloat1 = paramFloat2;
      else if (paramFloat1 > paramFloat3)
        paramFloat1 = paramFloat3;
      return paramFloat1;
    }

    private Bitmap createColoredBitmap(int paramInt1, int paramInt2, int paramInt3)
    {
      Drawable localDrawable = this.mBuilder.mContext.getResources().getDrawable(paramInt1);
      int i;
      if (paramInt3 == 0)
        i = localDrawable.getIntrinsicWidth();
      else
        i = paramInt3;
      if (paramInt3 == 0)
        paramInt3 = localDrawable.getIntrinsicHeight();
      Bitmap localBitmap = Bitmap.createBitmap(i, paramInt3, Bitmap.Config.ARGB_8888);
      localDrawable.setBounds(0, 0, i, paramInt3);
      if (paramInt2 != 0)
        localDrawable.mutate().setColorFilter(new PorterDuffColorFilter(paramInt2, PorterDuff.Mode.SRC_IN));
      localDrawable.draw(new Canvas(localBitmap));
      return localBitmap;
    }

    private Bitmap createIconWithBackground(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int i = R.drawable.notification_icon_background;
      if (paramInt4 == 0)
        paramInt4 = 0;
      Bitmap localBitmap = createColoredBitmap(i, paramInt4, paramInt2);
      Canvas localCanvas = new Canvas(localBitmap);
      Drawable localDrawable = this.mBuilder.mContext.getResources().getDrawable(paramInt1).mutate();
      localDrawable.setFilterBitmap(true);
      int j = (paramInt2 - paramInt3) / 2;
      int k = paramInt3 + j;
      localDrawable.setBounds(j, j, k, k);
      localDrawable.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
      localDrawable.draw(localCanvas);
      return localBitmap;
    }

    private void hideNormalContent(RemoteViews paramRemoteViews)
    {
      paramRemoteViews.setViewVisibility(R.id.title, 8);
      paramRemoteViews.setViewVisibility(R.id.text2, 8);
      paramRemoteViews.setViewVisibility(R.id.text, 8);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void addCompatExtras(Bundle paramBundle)
    {
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews applyStandardTemplate(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
    {
      Resources localResources = this.mBuilder.mContext.getResources();
      RemoteViews localRemoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), paramInt);
      int i = this.mBuilder.getPriority();
      boolean bool1 = true;
      int j;
      if (i < -1)
        j = 1;
      else
        j = 0;
      if ((Build.VERSION.SDK_INT >= 16) && (Build.VERSION.SDK_INT < 21))
        if (j != 0)
        {
          localRemoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
          localRemoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
        }
        else
        {
          localRemoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
          localRemoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
        }
      if (this.mBuilder.mLargeIcon != null)
      {
        if (Build.VERSION.SDK_INT >= 16)
        {
          localRemoteViews.setViewVisibility(R.id.icon, 0);
          localRemoteViews.setImageViewBitmap(R.id.icon, this.mBuilder.mLargeIcon);
        }
        else
        {
          localRemoteViews.setViewVisibility(R.id.icon, 8);
        }
        if ((paramBoolean1) && (this.mBuilder.mNotification.icon != 0))
        {
          int i8 = localResources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
          int i9 = i8 - 2 * localResources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding);
          if (Build.VERSION.SDK_INT >= 21)
          {
            Bitmap localBitmap2 = createIconWithBackground(this.mBuilder.mNotification.icon, i8, i9, this.mBuilder.getColor());
            localRemoteViews.setImageViewBitmap(R.id.right_icon, localBitmap2);
          }
          else
          {
            localRemoteViews.setImageViewBitmap(R.id.right_icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
          }
          localRemoteViews.setViewVisibility(R.id.right_icon, 0);
        }
      }
      else if ((paramBoolean1) && (this.mBuilder.mNotification.icon != 0))
      {
        localRemoteViews.setViewVisibility(R.id.icon, 0);
        if (Build.VERSION.SDK_INT >= 21)
        {
          int i6 = localResources.getDimensionPixelSize(R.dimen.notification_large_icon_width) - localResources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
          int i7 = localResources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
          Bitmap localBitmap1 = createIconWithBackground(this.mBuilder.mNotification.icon, i6, i7, this.mBuilder.getColor());
          localRemoteViews.setImageViewBitmap(R.id.icon, localBitmap1);
        }
        else
        {
          localRemoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
        }
      }
      if (this.mBuilder.mContentTitle != null)
        localRemoteViews.setTextViewText(R.id.title, this.mBuilder.mContentTitle);
      int k;
      if (this.mBuilder.mContentText != null)
      {
        localRemoteViews.setTextViewText(R.id.text, this.mBuilder.mContentText);
        k = 1;
      }
      else
      {
        k = 0;
      }
      boolean bool2;
      if ((Build.VERSION.SDK_INT < 21) && (this.mBuilder.mLargeIcon != null))
        bool2 = true;
      else
        bool2 = false;
      if (this.mBuilder.mContentInfo != null)
      {
        localRemoteViews.setTextViewText(R.id.info, this.mBuilder.mContentInfo);
        localRemoteViews.setViewVisibility(R.id.info, 0);
      }
      while (true)
      {
        bool3 = true;
        m = 1;
        break label677;
        if (this.mBuilder.mNumber <= 0)
          break;
        int i5 = localResources.getInteger(R.integer.status_bar_notification_info_maxnum);
        if (this.mBuilder.mNumber > i5)
        {
          localRemoteViews.setTextViewText(R.id.info, localResources.getString(R.string.status_bar_notification_info_overflow));
        }
        else
        {
          NumberFormat localNumberFormat = NumberFormat.getIntegerInstance();
          localRemoteViews.setTextViewText(R.id.info, localNumberFormat.format(this.mBuilder.mNumber));
        }
        localRemoteViews.setViewVisibility(R.id.info, 0);
      }
      localRemoteViews.setViewVisibility(R.id.info, 8);
      int m = k;
      boolean bool3 = bool2;
      label677: int n;
      if ((this.mBuilder.mSubText != null) && (Build.VERSION.SDK_INT >= 16))
      {
        localRemoteViews.setTextViewText(R.id.text, this.mBuilder.mSubText);
        if (this.mBuilder.mContentText != null)
        {
          localRemoteViews.setTextViewText(R.id.text2, this.mBuilder.mContentText);
          localRemoteViews.setViewVisibility(R.id.text2, 0);
          n = 1;
        }
        else
        {
          localRemoteViews.setViewVisibility(R.id.text2, 8);
        }
      }
      else
      {
        n = 0;
      }
      if ((n != 0) && (Build.VERSION.SDK_INT >= 16))
      {
        if (paramBoolean2)
        {
          float f = localResources.getDimensionPixelSize(R.dimen.notification_subtext_size);
          localRemoteViews.setTextViewTextSize(R.id.text, 0, f);
        }
        localRemoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
      }
      if (this.mBuilder.getWhenIfShowing() != 0L)
      {
        if ((this.mBuilder.mUseChronometer) && (Build.VERSION.SDK_INT >= 16))
        {
          localRemoteViews.setViewVisibility(R.id.chronometer, 0);
          localRemoteViews.setLong(R.id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
          localRemoteViews.setBoolean(R.id.chronometer, "setStarted", bool1);
        }
        else
        {
          localRemoteViews.setViewVisibility(R.id.time, 0);
          localRemoteViews.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
        }
      }
      else
        bool1 = bool3;
      int i1 = R.id.right_side;
      int i2;
      if (bool1)
        i2 = 0;
      else
        i2 = 8;
      localRemoteViews.setViewVisibility(i1, i2);
      int i3 = R.id.line3;
      int i4;
      if (m != 0)
        i4 = 0;
      else
        i4 = 8;
      localRemoteViews.setViewVisibility(i3, i4);
      return localRemoteViews;
    }

    public Notification build()
    {
      Notification localNotification;
      if (this.mBuilder != null)
        localNotification = this.mBuilder.build();
      else
        localNotification = null;
      return localNotification;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void buildIntoRemoteViews(RemoteViews paramRemoteViews1, RemoteViews paramRemoteViews2)
    {
      hideNormalContent(paramRemoteViews1);
      paramRemoteViews1.removeAllViews(R.id.notification_main_column);
      paramRemoteViews1.addView(R.id.notification_main_column, paramRemoteViews2.clone());
      paramRemoteViews1.setViewVisibility(R.id.notification_main_column, 0);
      if (Build.VERSION.SDK_INT >= 21)
        paramRemoteViews1.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public Bitmap createColoredBitmap(int paramInt1, int paramInt2)
    {
      return createColoredBitmap(paramInt1, paramInt2, 0);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    protected void restoreFromCompatExtras(Bundle paramBundle)
    {
    }

    public void setBuilder(NotificationCompat.Builder paramBuilder)
    {
      if (this.mBuilder != paramBuilder)
      {
        this.mBuilder = paramBuilder;
        if (this.mBuilder != null)
          this.mBuilder.setStyle(this);
      }
    }
  }

  public static final class WearableExtender
    implements NotificationCompat.Extender
  {
    private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    private static final int DEFAULT_FLAGS = 1;
    private static final int DEFAULT_GRAVITY = 80;
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
    private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
    private static final int FLAG_HINT_HIDE_ICON = 2;
    private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    private static final int FLAG_START_SCROLL_BOTTOM = 8;
    private static final String KEY_ACTIONS = "actions";
    private static final String KEY_BACKGROUND = "background";
    private static final String KEY_BRIDGE_TAG = "bridgeTag";
    private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    private static final String KEY_CONTENT_ICON = "contentIcon";
    private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    private static final String KEY_DISMISSAL_ID = "dismissalId";
    private static final String KEY_DISPLAY_INTENT = "displayIntent";
    private static final String KEY_FLAGS = "flags";
    private static final String KEY_GRAVITY = "gravity";
    private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    private static final String KEY_PAGES = "pages";
    public static final int SCREEN_TIMEOUT_LONG = -1;
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    public static final int SIZE_DEFAULT = 0;
    public static final int SIZE_FULL_SCREEN = 5;
    public static final int SIZE_LARGE = 4;
    public static final int SIZE_MEDIUM = 3;
    public static final int SIZE_SMALL = 2;
    public static final int SIZE_XSMALL = 1;
    public static final int UNSET_ACTION_INDEX = -1;
    private ArrayList<NotificationCompat.Action> mActions = new ArrayList();
    private Bitmap mBackground;
    private String mBridgeTag;
    private int mContentActionIndex = -1;
    private int mContentIcon;
    private int mContentIconGravity = 8388613;
    private int mCustomContentHeight;
    private int mCustomSizePreset = 0;
    private String mDismissalId;
    private PendingIntent mDisplayIntent;
    private int mFlags = 1;
    private int mGravity = 80;
    private int mHintScreenTimeout;
    private ArrayList<Notification> mPages = new ArrayList();

    public WearableExtender()
    {
    }

    public WearableExtender(Notification paramNotification)
    {
      Bundle localBundle1 = NotificationCompat.getExtras(paramNotification);
      Bundle localBundle2;
      if (localBundle1 != null)
        localBundle2 = localBundle1.getBundle("android.wearable.EXTENSIONS");
      else
        localBundle2 = null;
      if (localBundle2 != null)
      {
        ArrayList localArrayList = localBundle2.getParcelableArrayList("actions");
        if ((Build.VERSION.SDK_INT >= 16) && (localArrayList != null))
        {
          NotificationCompat.Action[] arrayOfAction = new NotificationCompat.Action[localArrayList.size()];
          for (int i = 0; i < arrayOfAction.length; i++)
            if (Build.VERSION.SDK_INT >= 20)
              arrayOfAction[i] = NotificationCompat.getActionCompatFromAction((Notification.Action)localArrayList.get(i));
            else if (Build.VERSION.SDK_INT >= 16)
              arrayOfAction[i] = NotificationCompatJellybean.getActionFromBundle((Bundle)localArrayList.get(i));
          Collections.addAll(this.mActions, (NotificationCompat.Action[])arrayOfAction);
        }
        this.mFlags = localBundle2.getInt("flags", 1);
        this.mDisplayIntent = ((PendingIntent)localBundle2.getParcelable("displayIntent"));
        Notification[] arrayOfNotification = NotificationCompat.getNotificationArrayFromBundle(localBundle2, "pages");
        if (arrayOfNotification != null)
          Collections.addAll(this.mPages, arrayOfNotification);
        this.mBackground = ((Bitmap)localBundle2.getParcelable("background"));
        this.mContentIcon = localBundle2.getInt("contentIcon");
        this.mContentIconGravity = localBundle2.getInt("contentIconGravity", 8388613);
        this.mContentActionIndex = localBundle2.getInt("contentActionIndex", -1);
        this.mCustomSizePreset = localBundle2.getInt("customSizePreset", 0);
        this.mCustomContentHeight = localBundle2.getInt("customContentHeight");
        this.mGravity = localBundle2.getInt("gravity", 80);
        this.mHintScreenTimeout = localBundle2.getInt("hintScreenTimeout");
        this.mDismissalId = localBundle2.getString("dismissalId");
        this.mBridgeTag = localBundle2.getString("bridgeTag");
      }
    }

    @RequiresApi(20)
    private static Notification.Action getActionFromActionCompat(NotificationCompat.Action paramAction)
    {
      Notification.Action.Builder localBuilder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
      Bundle localBundle;
      if (paramAction.getExtras() != null)
        localBundle = new Bundle(paramAction.getExtras());
      else
        localBundle = new Bundle();
      localBundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      if (Build.VERSION.SDK_INT >= 24)
        localBuilder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
      localBuilder.addExtras(localBundle);
      RemoteInput[] arrayOfRemoteInput = paramAction.getRemoteInputs();
      if (arrayOfRemoteInput != null)
      {
        android.app.RemoteInput[] arrayOfRemoteInput1 = RemoteInput.fromCompat(arrayOfRemoteInput);
        int i = arrayOfRemoteInput1.length;
        for (int j = 0; j < i; j++)
          localBuilder.addRemoteInput(arrayOfRemoteInput1[j]);
      }
      return localBuilder.build();
    }

    private void setFlag(int paramInt, boolean paramBoolean)
    {
      if (paramBoolean)
        this.mFlags = (paramInt | this.mFlags);
      else
        this.mFlags &= (paramInt ^ 0xFFFFFFFF);
    }

    public WearableExtender addAction(NotificationCompat.Action paramAction)
    {
      this.mActions.add(paramAction);
      return this;
    }

    public WearableExtender addActions(List<NotificationCompat.Action> paramList)
    {
      this.mActions.addAll(paramList);
      return this;
    }

    public WearableExtender addPage(Notification paramNotification)
    {
      this.mPages.add(paramNotification);
      return this;
    }

    public WearableExtender addPages(List<Notification> paramList)
    {
      this.mPages.addAll(paramList);
      return this;
    }

    public WearableExtender clearActions()
    {
      this.mActions.clear();
      return this;
    }

    public WearableExtender clearPages()
    {
      this.mPages.clear();
      return this;
    }

    public WearableExtender clone()
    {
      WearableExtender localWearableExtender = new WearableExtender();
      localWearableExtender.mActions = new ArrayList(this.mActions);
      localWearableExtender.mFlags = this.mFlags;
      localWearableExtender.mDisplayIntent = this.mDisplayIntent;
      localWearableExtender.mPages = new ArrayList(this.mPages);
      localWearableExtender.mBackground = this.mBackground;
      localWearableExtender.mContentIcon = this.mContentIcon;
      localWearableExtender.mContentIconGravity = this.mContentIconGravity;
      localWearableExtender.mContentActionIndex = this.mContentActionIndex;
      localWearableExtender.mCustomSizePreset = this.mCustomSizePreset;
      localWearableExtender.mCustomContentHeight = this.mCustomContentHeight;
      localWearableExtender.mGravity = this.mGravity;
      localWearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
      localWearableExtender.mDismissalId = this.mDismissalId;
      localWearableExtender.mBridgeTag = this.mBridgeTag;
      return localWearableExtender;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder)
    {
      Bundle localBundle = new Bundle();
      if (!this.mActions.isEmpty())
        if (Build.VERSION.SDK_INT >= 16)
        {
          ArrayList localArrayList = new ArrayList(this.mActions.size());
          Iterator localIterator = this.mActions.iterator();
          while (localIterator.hasNext())
          {
            NotificationCompat.Action localAction = (NotificationCompat.Action)localIterator.next();
            if (Build.VERSION.SDK_INT >= 20)
              localArrayList.add(getActionFromActionCompat(localAction));
            else if (Build.VERSION.SDK_INT >= 16)
              localArrayList.add(NotificationCompatJellybean.getBundleForAction(localAction));
          }
          localBundle.putParcelableArrayList("actions", localArrayList);
        }
        else
        {
          localBundle.putParcelableArrayList("actions", null);
        }
      if (this.mFlags != 1)
        localBundle.putInt("flags", this.mFlags);
      if (this.mDisplayIntent != null)
        localBundle.putParcelable("displayIntent", this.mDisplayIntent);
      if (!this.mPages.isEmpty())
        localBundle.putParcelableArray("pages", (Parcelable[])this.mPages.toArray(new Notification[this.mPages.size()]));
      if (this.mBackground != null)
        localBundle.putParcelable("background", this.mBackground);
      if (this.mContentIcon != 0)
        localBundle.putInt("contentIcon", this.mContentIcon);
      if (this.mContentIconGravity != 8388613)
        localBundle.putInt("contentIconGravity", this.mContentIconGravity);
      if (this.mContentActionIndex != -1)
        localBundle.putInt("contentActionIndex", this.mContentActionIndex);
      if (this.mCustomSizePreset != 0)
        localBundle.putInt("customSizePreset", this.mCustomSizePreset);
      if (this.mCustomContentHeight != 0)
        localBundle.putInt("customContentHeight", this.mCustomContentHeight);
      if (this.mGravity != 80)
        localBundle.putInt("gravity", this.mGravity);
      if (this.mHintScreenTimeout != 0)
        localBundle.putInt("hintScreenTimeout", this.mHintScreenTimeout);
      if (this.mDismissalId != null)
        localBundle.putString("dismissalId", this.mDismissalId);
      if (this.mBridgeTag != null)
        localBundle.putString("bridgeTag", this.mBridgeTag);
      paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", localBundle);
      return paramBuilder;
    }

    public List<NotificationCompat.Action> getActions()
    {
      return this.mActions;
    }

    public Bitmap getBackground()
    {
      return this.mBackground;
    }

    public String getBridgeTag()
    {
      return this.mBridgeTag;
    }

    public int getContentAction()
    {
      return this.mContentActionIndex;
    }

    @Deprecated
    public int getContentIcon()
    {
      return this.mContentIcon;
    }

    @Deprecated
    public int getContentIconGravity()
    {
      return this.mContentIconGravity;
    }

    public boolean getContentIntentAvailableOffline()
    {
      int i = this.mFlags;
      int j = 1;
      if ((i & j) == 0)
        j = 0;
      return j;
    }

    @Deprecated
    public int getCustomContentHeight()
    {
      return this.mCustomContentHeight;
    }

    @Deprecated
    public int getCustomSizePreset()
    {
      return this.mCustomSizePreset;
    }

    public String getDismissalId()
    {
      return this.mDismissalId;
    }

    public PendingIntent getDisplayIntent()
    {
      return this.mDisplayIntent;
    }

    @Deprecated
    public int getGravity()
    {
      return this.mGravity;
    }

    public boolean getHintAmbientBigPicture()
    {
      boolean bool;
      if ((0x20 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    @Deprecated
    public boolean getHintAvoidBackgroundClipping()
    {
      boolean bool;
      if ((0x10 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public boolean getHintContentIntentLaunchesActivity()
    {
      boolean bool;
      if ((0x40 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    @Deprecated
    public boolean getHintHideIcon()
    {
      boolean bool;
      if ((0x2 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    @Deprecated
    public int getHintScreenTimeout()
    {
      return this.mHintScreenTimeout;
    }

    @Deprecated
    public boolean getHintShowBackgroundOnly()
    {
      boolean bool;
      if ((0x4 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public List<Notification> getPages()
    {
      return this.mPages;
    }

    public boolean getStartScrollBottom()
    {
      boolean bool;
      if ((0x8 & this.mFlags) != 0)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public WearableExtender setBackground(Bitmap paramBitmap)
    {
      this.mBackground = paramBitmap;
      return this;
    }

    public WearableExtender setBridgeTag(String paramString)
    {
      this.mBridgeTag = paramString;
      return this;
    }

    public WearableExtender setContentAction(int paramInt)
    {
      this.mContentActionIndex = paramInt;
      return this;
    }

    @Deprecated
    public WearableExtender setContentIcon(int paramInt)
    {
      this.mContentIcon = paramInt;
      return this;
    }

    @Deprecated
    public WearableExtender setContentIconGravity(int paramInt)
    {
      this.mContentIconGravity = paramInt;
      return this;
    }

    public WearableExtender setContentIntentAvailableOffline(boolean paramBoolean)
    {
      setFlag(1, paramBoolean);
      return this;
    }

    @Deprecated
    public WearableExtender setCustomContentHeight(int paramInt)
    {
      this.mCustomContentHeight = paramInt;
      return this;
    }

    @Deprecated
    public WearableExtender setCustomSizePreset(int paramInt)
    {
      this.mCustomSizePreset = paramInt;
      return this;
    }

    public WearableExtender setDismissalId(String paramString)
    {
      this.mDismissalId = paramString;
      return this;
    }

    public WearableExtender setDisplayIntent(PendingIntent paramPendingIntent)
    {
      this.mDisplayIntent = paramPendingIntent;
      return this;
    }

    @Deprecated
    public WearableExtender setGravity(int paramInt)
    {
      this.mGravity = paramInt;
      return this;
    }

    public WearableExtender setHintAmbientBigPicture(boolean paramBoolean)
    {
      setFlag(32, paramBoolean);
      return this;
    }

    @Deprecated
    public WearableExtender setHintAvoidBackgroundClipping(boolean paramBoolean)
    {
      setFlag(16, paramBoolean);
      return this;
    }

    public WearableExtender setHintContentIntentLaunchesActivity(boolean paramBoolean)
    {
      setFlag(64, paramBoolean);
      return this;
    }

    @Deprecated
    public WearableExtender setHintHideIcon(boolean paramBoolean)
    {
      setFlag(2, paramBoolean);
      return this;
    }

    @Deprecated
    public WearableExtender setHintScreenTimeout(int paramInt)
    {
      this.mHintScreenTimeout = paramInt;
      return this;
    }

    @Deprecated
    public WearableExtender setHintShowBackgroundOnly(boolean paramBoolean)
    {
      setFlag(4, paramBoolean);
      return this;
    }

    public WearableExtender setStartScrollBottom(boolean paramBoolean)
    {
      setFlag(8, paramBoolean);
      return this;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationCompat
 * JD-Core Version:    0.6.1
 */