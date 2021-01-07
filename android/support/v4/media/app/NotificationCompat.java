package android.support.v4.media.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedMediaCustomViewStyle;
import android.app.Notification.MediaStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.media.session.MediaSession.Token;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.mediacompat.R.color;
import android.support.mediacompat.R.id;
import android.support.mediacompat.R.integer;
import android.support.mediacompat.R.layout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.widget.RemoteViews;
import java.util.ArrayList;

public class NotificationCompat
{
  public static class DecoratedMediaCustomViewStyle extends NotificationCompat.MediaStyle
  {
    private void setBackgroundColor(RemoteViews paramRemoteViews)
    {
      int i;
      if (this.mBuilder.getColor() != 0)
        i = this.mBuilder.getColor();
      else
        i = this.mBuilder.mContext.getResources().getColor(R.color.notification_material_background_media_default_color);
      paramRemoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.DecoratedMediaCustomViewStyle()));
      else
        super.apply(paramNotificationBuilderWithBuilderAccessor);
    }

    int getBigContentViewLayoutResource(int paramInt)
    {
      int i;
      if (paramInt <= 3)
        i = R.layout.notification_template_big_media_narrow_custom;
      else
        i = R.layout.notification_template_big_media_custom;
      return i;
    }

    int getContentViewLayoutResource()
    {
      int i;
      if (this.mBuilder.getContentView() != null)
        i = R.layout.notification_template_media_custom;
      else
        i = super.getContentViewLayoutResource();
      return i;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        return null;
      RemoteViews localRemoteViews1;
      if (this.mBuilder.getBigContentView() != null)
        localRemoteViews1 = this.mBuilder.getBigContentView();
      else
        localRemoteViews1 = this.mBuilder.getContentView();
      if (localRemoteViews1 == null)
        return null;
      RemoteViews localRemoteViews2 = generateBigContentView();
      buildIntoRemoteViews(localRemoteViews2, localRemoteViews1);
      if (Build.VERSION.SDK_INT >= 21)
        setBackgroundColor(localRemoteViews2);
      return localRemoteViews2;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        return null;
      int i;
      if (this.mBuilder.getContentView() != null)
        i = 1;
      else
        i = 0;
      if (Build.VERSION.SDK_INT >= 21)
      {
        int j;
        if (i == 0)
        {
          RemoteViews localRemoteViews3 = this.mBuilder.getBigContentView();
          j = 0;
          if (localRemoteViews3 == null);
        }
        else
        {
          j = 1;
        }
        if (j != 0)
        {
          RemoteViews localRemoteViews2 = generateContentView();
          if (i != 0)
            buildIntoRemoteViews(localRemoteViews2, this.mBuilder.getContentView());
          setBackgroundColor(localRemoteViews2);
          return localRemoteViews2;
        }
      }
      else
      {
        RemoteViews localRemoteViews1 = generateContentView();
        if (i != 0)
        {
          buildIntoRemoteViews(localRemoteViews1, this.mBuilder.getContentView());
          return localRemoteViews1;
        }
      }
      return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
        return null;
      RemoteViews localRemoteViews1;
      if (this.mBuilder.getHeadsUpContentView() != null)
        localRemoteViews1 = this.mBuilder.getHeadsUpContentView();
      else
        localRemoteViews1 = this.mBuilder.getContentView();
      if (localRemoteViews1 == null)
        return null;
      RemoteViews localRemoteViews2 = generateBigContentView();
      buildIntoRemoteViews(localRemoteViews2, localRemoteViews1);
      if (Build.VERSION.SDK_INT >= 21)
        setBackgroundColor(localRemoteViews2);
      return localRemoteViews2;
    }
  }

  public static class MediaStyle extends NotificationCompat.Style
  {
    private static final int MAX_MEDIA_BUTTONS = 5;
    private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
    int[] mActionsToShowInCompact = null;
    PendingIntent mCancelButtonIntent;
    boolean mShowCancelButton;
    MediaSessionCompat.Token mToken;

    public MediaStyle()
    {
    }

    public MediaStyle(NotificationCompat.Builder paramBuilder)
    {
      setBuilder(paramBuilder);
    }

    private RemoteViews generateMediaActionButton(NotificationCompat.Action paramAction)
    {
      int i;
      if (paramAction.getActionIntent() == null)
        i = 1;
      else
        i = 0;
      RemoteViews localRemoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), R.layout.notification_media_action);
      localRemoteViews.setImageViewResource(R.id.action0, paramAction.getIcon());
      if (i == 0)
        localRemoteViews.setOnClickPendingIntent(R.id.action0, paramAction.getActionIntent());
      if (Build.VERSION.SDK_INT >= 15)
        localRemoteViews.setContentDescription(R.id.action0, paramAction.getTitle());
      return localRemoteViews;
    }

    public static MediaSessionCompat.Token getMediaSession(Notification paramNotification)
    {
      Bundle localBundle = android.support.v4.app.NotificationCompat.getExtras(paramNotification);
      if (localBundle != null)
        if (Build.VERSION.SDK_INT >= 21)
        {
          Parcelable localParcelable = localBundle.getParcelable("android.mediaSession");
          if (localParcelable != null)
            return MediaSessionCompat.Token.fromToken(localParcelable);
        }
        else
        {
          IBinder localIBinder = BundleCompat.getBinder(localBundle, "android.mediaSession");
          if (localIBinder != null)
          {
            Parcel localParcel = Parcel.obtain();
            localParcel.writeStrongBinder(localIBinder);
            localParcel.setDataPosition(0);
            MediaSessionCompat.Token localToken = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(localParcel);
            localParcel.recycle();
            return localToken;
          }
        }
      return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21)
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.MediaStyle()));
      else if (this.mShowCancelButton)
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
    }

    @RequiresApi(21)
    Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle paramMediaStyle)
    {
      if (this.mActionsToShowInCompact != null)
        paramMediaStyle.setShowActionsInCompactView(this.mActionsToShowInCompact);
      if (this.mToken != null)
        paramMediaStyle.setMediaSession((MediaSession.Token)this.mToken.getToken());
      return paramMediaStyle;
    }

    RemoteViews generateBigContentView()
    {
      int i = Math.min(this.mBuilder.mActions.size(), 5);
      RemoteViews localRemoteViews1 = applyStandardTemplate(false, getBigContentViewLayoutResource(i), false);
      localRemoteViews1.removeAllViews(R.id.media_actions);
      if (i > 0)
        for (int j = 0; j < i; j++)
        {
          RemoteViews localRemoteViews2 = generateMediaActionButton((NotificationCompat.Action)this.mBuilder.mActions.get(j));
          localRemoteViews1.addView(R.id.media_actions, localRemoteViews2);
        }
      if (this.mShowCancelButton)
      {
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 0);
        localRemoteViews1.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
        localRemoteViews1.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
      }
      else
      {
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 8);
      }
      return localRemoteViews1;
    }

    RemoteViews generateContentView()
    {
      RemoteViews localRemoteViews1 = applyStandardTemplate(false, getContentViewLayoutResource(), true);
      int i = this.mBuilder.mActions.size();
      int j;
      if (this.mActionsToShowInCompact == null)
        j = 0;
      else
        j = Math.min(this.mActionsToShowInCompact.length, 3);
      localRemoteViews1.removeAllViews(R.id.media_actions);
      if (j > 0)
        for (int k = 0; k < j; k++)
        {
          if (k >= i)
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(k);
            arrayOfObject[1] = Integer.valueOf(i - 1);
            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", arrayOfObject));
          }
          RemoteViews localRemoteViews2 = generateMediaActionButton((NotificationCompat.Action)this.mBuilder.mActions.get(this.mActionsToShowInCompact[k]));
          localRemoteViews1.addView(R.id.media_actions, localRemoteViews2);
        }
      if (this.mShowCancelButton)
      {
        localRemoteViews1.setViewVisibility(R.id.end_padder, 8);
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 0);
        localRemoteViews1.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
        localRemoteViews1.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
      }
      else
      {
        localRemoteViews1.setViewVisibility(R.id.end_padder, 0);
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 8);
      }
      return localRemoteViews1;
    }

    int getBigContentViewLayoutResource(int paramInt)
    {
      int i;
      if (paramInt <= 3)
        i = R.layout.notification_template_big_media_narrow;
      else
        i = R.layout.notification_template_big_media;
      return i;
    }

    int getContentViewLayoutResource()
    {
      return R.layout.notification_template_media;
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21)
        return null;
      return generateBigContentView();
    }

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21)
        return null;
      return generateContentView();
    }

    public MediaStyle setCancelButtonIntent(PendingIntent paramPendingIntent)
    {
      this.mCancelButtonIntent = paramPendingIntent;
      return this;
    }

    public MediaStyle setMediaSession(MediaSessionCompat.Token paramToken)
    {
      this.mToken = paramToken;
      return this;
    }

    public MediaStyle setShowActionsInCompactView(int[] paramArrayOfInt)
    {
      this.mActionsToShowInCompact = paramArrayOfInt;
      return this;
    }

    public MediaStyle setShowCancelButton(boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT < 21)
        this.mShowCancelButton = paramBoolean;
      return this;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.app.NotificationCompat
 * JD-Core Version:    0.6.1
 */