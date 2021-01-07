package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;

public class ActivityOptionsCompat
{
  public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
  public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

  @NonNull
  public static ActivityOptionsCompat makeBasic()
  {
    if (Build.VERSION.SDK_INT >= 23)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeBasic());
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeClipRevealAnimation(@NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeClipRevealAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeCustomAnimation(@NonNull Context paramContext, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(paramContext, paramInt1, paramInt2));
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeScaleUpAnimation(@NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity paramActivity, @NonNull View paramView, @NonNull String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(paramActivity, paramView, paramString));
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity paramActivity, android.support.v4.util.Pair<View, String>[] paramArrayOfPair)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      android.util.Pair[] arrayOfPair = null;
      if (paramArrayOfPair != null)
      {
        arrayOfPair = new android.util.Pair[paramArrayOfPair.length];
        for (int i = 0; i < paramArrayOfPair.length; i++)
          arrayOfPair[i] = android.util.Pair.create(paramArrayOfPair[i].first, paramArrayOfPair[i].second);
      }
      return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(paramActivity, arrayOfPair));
    }
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeTaskLaunchBehind()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind());
    return new ActivityOptionsCompat();
  }

  @NonNull
  public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(@NonNull View paramView, @NonNull Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));
    return new ActivityOptionsCompat();
  }

  @Nullable
  public Rect getLaunchBounds()
  {
    return null;
  }

  public void requestUsageTimeReport(@NonNull PendingIntent paramPendingIntent)
  {
  }

  @NonNull
  public ActivityOptionsCompat setLaunchBounds(@Nullable Rect paramRect)
  {
    return this;
  }

  @Nullable
  public Bundle toBundle()
  {
    return null;
  }

  public void update(@NonNull ActivityOptionsCompat paramActivityOptionsCompat)
  {
  }

  @RequiresApi(16)
  private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat
  {
    private final ActivityOptions mActivityOptions;

    ActivityOptionsCompatImpl(ActivityOptions paramActivityOptions)
    {
      this.mActivityOptions = paramActivityOptions;
    }

    public Rect getLaunchBounds()
    {
      if (Build.VERSION.SDK_INT < 24)
        return null;
      return this.mActivityOptions.getLaunchBounds();
    }

    public void requestUsageTimeReport(PendingIntent paramPendingIntent)
    {
      if (Build.VERSION.SDK_INT >= 23)
        this.mActivityOptions.requestUsageTimeReport(paramPendingIntent);
    }

    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect paramRect)
    {
      if (Build.VERSION.SDK_INT < 24)
        return this;
      return new ActivityOptionsCompatImpl(this.mActivityOptions.setLaunchBounds(paramRect));
    }

    public Bundle toBundle()
    {
      return this.mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsCompatImpl))
      {
        ActivityOptionsCompatImpl localActivityOptionsCompatImpl = (ActivityOptionsCompatImpl)paramActivityOptionsCompat;
        this.mActivityOptions.update(localActivityOptionsCompatImpl.mActivityOptions);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActivityOptionsCompat
 * JD-Core Version:    0.6.1
 */