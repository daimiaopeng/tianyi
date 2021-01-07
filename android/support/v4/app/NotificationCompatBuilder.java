package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Action.Builder;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class NotificationCompatBuilder
  implements NotificationBuilderWithBuilderAccessor
{
  private final List<Bundle> mActionExtrasList = new ArrayList();
  private RemoteViews mBigContentView;
  private final Notification.Builder mBuilder;
  private final NotificationCompat.Builder mBuilderCompat;
  private RemoteViews mContentView;
  private final Bundle mExtras = new Bundle();
  private int mGroupAlertBehavior;
  private RemoteViews mHeadsUpContentView;

  NotificationCompatBuilder(NotificationCompat.Builder paramBuilder)
  {
    this.mBuilderCompat = paramBuilder;
    if (Build.VERSION.SDK_INT >= 26)
      this.mBuilder = new Notification.Builder(paramBuilder.mContext, paramBuilder.mChannelId);
    else
      this.mBuilder = new Notification.Builder(paramBuilder.mContext);
    Notification localNotification = paramBuilder.mNotification;
    Notification.Builder localBuilder1 = this.mBuilder.setWhen(localNotification.when).setSmallIcon(localNotification.icon, localNotification.iconLevel).setContent(localNotification.contentView).setTicker(localNotification.tickerText, paramBuilder.mTickerView).setVibrate(localNotification.vibrate).setLights(localNotification.ledARGB, localNotification.ledOnMS, localNotification.ledOffMS);
    boolean bool1;
    if ((0x2 & localNotification.flags) != 0)
      bool1 = true;
    else
      bool1 = false;
    Notification.Builder localBuilder2 = localBuilder1.setOngoing(bool1);
    boolean bool2;
    if ((0x8 & localNotification.flags) != 0)
      bool2 = true;
    else
      bool2 = false;
    Notification.Builder localBuilder3 = localBuilder2.setOnlyAlertOnce(bool2);
    boolean bool3;
    if ((0x10 & localNotification.flags) != 0)
      bool3 = true;
    else
      bool3 = false;
    Notification.Builder localBuilder4 = localBuilder3.setAutoCancel(bool3).setDefaults(localNotification.defaults).setContentTitle(paramBuilder.mContentTitle).setContentText(paramBuilder.mContentText).setContentInfo(paramBuilder.mContentInfo).setContentIntent(paramBuilder.mContentIntent).setDeleteIntent(localNotification.deleteIntent);
    PendingIntent localPendingIntent = paramBuilder.mFullScreenIntent;
    boolean bool4;
    if ((0x80 & localNotification.flags) != 0)
      bool4 = true;
    else
      bool4 = false;
    localBuilder4.setFullScreenIntent(localPendingIntent, bool4).setLargeIcon(paramBuilder.mLargeIcon).setNumber(paramBuilder.mNumber).setProgress(paramBuilder.mProgressMax, paramBuilder.mProgress, paramBuilder.mProgressIndeterminate);
    if (Build.VERSION.SDK_INT < 21)
      this.mBuilder.setSound(localNotification.sound, localNotification.audioStreamType);
    if (Build.VERSION.SDK_INT >= 16)
    {
      this.mBuilder.setSubText(paramBuilder.mSubText).setUsesChronometer(paramBuilder.mUseChronometer).setPriority(paramBuilder.mPriority);
      Iterator localIterator2 = paramBuilder.mActions.iterator();
      while (localIterator2.hasNext())
        addAction((NotificationCompat.Action)localIterator2.next());
      if (paramBuilder.mExtras != null)
        this.mExtras.putAll(paramBuilder.mExtras);
      if (Build.VERSION.SDK_INT < 20)
      {
        if (paramBuilder.mLocalOnly)
          this.mExtras.putBoolean("android.support.localOnly", true);
        if (paramBuilder.mGroupKey != null)
        {
          this.mExtras.putString("android.support.groupKey", paramBuilder.mGroupKey);
          if (paramBuilder.mGroupSummary)
            this.mExtras.putBoolean("android.support.isGroupSummary", true);
          else
            this.mExtras.putBoolean("android.support.useSideChannel", true);
        }
        if (paramBuilder.mSortKey != null)
          this.mExtras.putString("android.support.sortKey", paramBuilder.mSortKey);
      }
      this.mContentView = paramBuilder.mContentView;
      this.mBigContentView = paramBuilder.mBigContentView;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      this.mBuilder.setShowWhen(paramBuilder.mShowWhen);
      if ((Build.VERSION.SDK_INT < 21) && (paramBuilder.mPeople != null) && (!paramBuilder.mPeople.isEmpty()))
        this.mExtras.putStringArray("android.people", (String[])paramBuilder.mPeople.toArray(new String[paramBuilder.mPeople.size()]));
    }
    if (Build.VERSION.SDK_INT >= 20)
    {
      this.mBuilder.setLocalOnly(paramBuilder.mLocalOnly).setGroup(paramBuilder.mGroupKey).setGroupSummary(paramBuilder.mGroupSummary).setSortKey(paramBuilder.mSortKey);
      this.mGroupAlertBehavior = paramBuilder.mGroupAlertBehavior;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.mBuilder.setCategory(paramBuilder.mCategory).setColor(paramBuilder.mColor).setVisibility(paramBuilder.mVisibility).setPublicVersion(paramBuilder.mPublicVersion).setSound(localNotification.sound, localNotification.audioAttributes);
      Iterator localIterator1 = paramBuilder.mPeople.iterator();
      while (localIterator1.hasNext())
      {
        String str = (String)localIterator1.next();
        this.mBuilder.addPerson(str);
      }
      this.mHeadsUpContentView = paramBuilder.mHeadsUpContentView;
      if (paramBuilder.mInvisibleActions.size() > 0)
      {
        Bundle localBundle1 = paramBuilder.getExtras().getBundle("android.car.EXTENSIONS");
        if (localBundle1 == null)
          localBundle1 = new Bundle();
        Bundle localBundle2 = new Bundle();
        for (int i = 0; i < paramBuilder.mInvisibleActions.size(); i++)
          localBundle2.putBundle(Integer.toString(i), NotificationCompatJellybean.getBundleForAction((NotificationCompat.Action)paramBuilder.mInvisibleActions.get(i)));
        localBundle1.putBundle("invisible_actions", localBundle2);
        paramBuilder.getExtras().putBundle("android.car.EXTENSIONS", localBundle1);
        this.mExtras.putBundle("android.car.EXTENSIONS", localBundle1);
      }
    }
    if (Build.VERSION.SDK_INT >= 24)
    {
      this.mBuilder.setExtras(paramBuilder.mExtras).setRemoteInputHistory(paramBuilder.mRemoteInputHistory);
      if (paramBuilder.mContentView != null)
        this.mBuilder.setCustomContentView(paramBuilder.mContentView);
      if (paramBuilder.mBigContentView != null)
        this.mBuilder.setCustomBigContentView(paramBuilder.mBigContentView);
      if (paramBuilder.mHeadsUpContentView != null)
        this.mBuilder.setCustomHeadsUpContentView(paramBuilder.mHeadsUpContentView);
    }
    if (Build.VERSION.SDK_INT >= 26)
    {
      this.mBuilder.setBadgeIconType(paramBuilder.mBadgeIcon).setShortcutId(paramBuilder.mShortcutId).setTimeoutAfter(paramBuilder.mTimeout).setGroupAlertBehavior(paramBuilder.mGroupAlertBehavior);
      if (paramBuilder.mColorizedSet)
        this.mBuilder.setColorized(paramBuilder.mColorized);
      if (!TextUtils.isEmpty(paramBuilder.mChannelId))
        this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
    }
  }

  private void addAction(NotificationCompat.Action paramAction)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      Notification.Action.Builder localBuilder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
      if (paramAction.getRemoteInputs() != null)
      {
        android.app.RemoteInput[] arrayOfRemoteInput = RemoteInput.fromCompat(paramAction.getRemoteInputs());
        int i = arrayOfRemoteInput.length;
        for (int j = 0; j < i; j++)
          localBuilder.addRemoteInput(arrayOfRemoteInput[j]);
      }
      Bundle localBundle;
      if (paramAction.getExtras() != null)
        localBundle = new Bundle(paramAction.getExtras());
      else
        localBundle = new Bundle();
      localBundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      if (Build.VERSION.SDK_INT >= 24)
        localBuilder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
      localBundle.putInt("android.support.action.semanticAction", paramAction.getSemanticAction());
      if (Build.VERSION.SDK_INT >= 28)
        localBuilder.setSemanticAction(paramAction.getSemanticAction());
      localBundle.putBoolean("android.support.action.showsUserInterface", paramAction.getShowsUserInterface());
      localBuilder.addExtras(localBundle);
      this.mBuilder.addAction(localBuilder.build());
    }
    else if (Build.VERSION.SDK_INT >= 16)
    {
      this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, paramAction));
    }
  }

  private void removeSoundAndVibration(Notification paramNotification)
  {
    paramNotification.sound = null;
    paramNotification.vibrate = null;
    paramNotification.defaults = (0xFFFFFFFE & paramNotification.defaults);
    paramNotification.defaults = (0xFFFFFFFD & paramNotification.defaults);
  }

  public Notification build()
  {
    NotificationCompat.Style localStyle = this.mBuilderCompat.mStyle;
    if (localStyle != null)
      localStyle.apply(this);
    RemoteViews localRemoteViews1;
    if (localStyle != null)
      localRemoteViews1 = localStyle.makeContentView(this);
    else
      localRemoteViews1 = null;
    Notification localNotification = buildInternal();
    if (localRemoteViews1 != null)
      localNotification.contentView = localRemoteViews1;
    else if (this.mBuilderCompat.mContentView != null)
      localNotification.contentView = this.mBuilderCompat.mContentView;
    if ((Build.VERSION.SDK_INT >= 16) && (localStyle != null))
    {
      RemoteViews localRemoteViews3 = localStyle.makeBigContentView(this);
      if (localRemoteViews3 != null)
        localNotification.bigContentView = localRemoteViews3;
    }
    if ((Build.VERSION.SDK_INT >= 21) && (localStyle != null))
    {
      RemoteViews localRemoteViews2 = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
      if (localRemoteViews2 != null)
        localNotification.headsUpContentView = localRemoteViews2;
    }
    if ((Build.VERSION.SDK_INT >= 16) && (localStyle != null))
    {
      Bundle localBundle = NotificationCompat.getExtras(localNotification);
      if (localBundle != null)
        localStyle.addCompatExtras(localBundle);
    }
    return localNotification;
  }

  protected Notification buildInternal()
  {
    if (Build.VERSION.SDK_INT >= 26)
      return this.mBuilder.build();
    if (Build.VERSION.SDK_INT >= 24)
    {
      Notification localNotification5 = this.mBuilder.build();
      if (this.mGroupAlertBehavior != 0)
      {
        if ((localNotification5.getGroup() != null) && ((0x200 & localNotification5.flags) != 0) && (this.mGroupAlertBehavior == 2))
          removeSoundAndVibration(localNotification5);
        if ((localNotification5.getGroup() != null) && ((0x200 & localNotification5.flags) == 0) && (this.mGroupAlertBehavior == 1))
          removeSoundAndVibration(localNotification5);
      }
      return localNotification5;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.mBuilder.setExtras(this.mExtras);
      Notification localNotification4 = this.mBuilder.build();
      if (this.mContentView != null)
        localNotification4.contentView = this.mContentView;
      if (this.mBigContentView != null)
        localNotification4.bigContentView = this.mBigContentView;
      if (this.mHeadsUpContentView != null)
        localNotification4.headsUpContentView = this.mHeadsUpContentView;
      if (this.mGroupAlertBehavior != 0)
      {
        if ((localNotification4.getGroup() != null) && ((0x200 & localNotification4.flags) != 0) && (this.mGroupAlertBehavior == 2))
          removeSoundAndVibration(localNotification4);
        if ((localNotification4.getGroup() != null) && ((0x200 & localNotification4.flags) == 0) && (this.mGroupAlertBehavior == 1))
          removeSoundAndVibration(localNotification4);
      }
      return localNotification4;
    }
    if (Build.VERSION.SDK_INT >= 20)
    {
      this.mBuilder.setExtras(this.mExtras);
      Notification localNotification3 = this.mBuilder.build();
      if (this.mContentView != null)
        localNotification3.contentView = this.mContentView;
      if (this.mBigContentView != null)
        localNotification3.bigContentView = this.mBigContentView;
      if (this.mGroupAlertBehavior != 0)
      {
        if ((localNotification3.getGroup() != null) && ((0x200 & localNotification3.flags) != 0) && (this.mGroupAlertBehavior == 2))
          removeSoundAndVibration(localNotification3);
        if ((localNotification3.getGroup() != null) && ((0x200 & localNotification3.flags) == 0) && (this.mGroupAlertBehavior == 1))
          removeSoundAndVibration(localNotification3);
      }
      return localNotification3;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      SparseArray localSparseArray2 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
      if (localSparseArray2 != null)
        this.mExtras.putSparseParcelableArray("android.support.actionExtras", localSparseArray2);
      this.mBuilder.setExtras(this.mExtras);
      Notification localNotification2 = this.mBuilder.build();
      if (this.mContentView != null)
        localNotification2.contentView = this.mContentView;
      if (this.mBigContentView != null)
        localNotification2.bigContentView = this.mBigContentView;
      return localNotification2;
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      Notification localNotification1 = this.mBuilder.build();
      Bundle localBundle1 = NotificationCompat.getExtras(localNotification1);
      Bundle localBundle2 = new Bundle(this.mExtras);
      Iterator localIterator = this.mExtras.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (localBundle1.containsKey(str))
          localBundle2.remove(str);
      }
      localBundle1.putAll(localBundle2);
      SparseArray localSparseArray1 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
      if (localSparseArray1 != null)
        NotificationCompat.getExtras(localNotification1).putSparseParcelableArray("android.support.actionExtras", localSparseArray1);
      if (this.mContentView != null)
        localNotification1.contentView = this.mContentView;
      if (this.mBigContentView != null)
        localNotification1.bigContentView = this.mBigContentView;
      return localNotification1;
    }
    return this.mBuilder.getNotification();
  }

  public Notification.Builder getBuilder()
  {
    return this.mBuilder;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationCompatBuilder
 * JD-Core Version:    0.6.1
 */