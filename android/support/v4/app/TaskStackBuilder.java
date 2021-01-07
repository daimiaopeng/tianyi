package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder
  implements Iterable<Intent>
{
  private static final String TAG = "TaskStackBuilder";
  private final ArrayList<Intent> mIntents = new ArrayList();
  private final Context mSourceContext;

  private TaskStackBuilder(Context paramContext)
  {
    this.mSourceContext = paramContext;
  }

  @NonNull
  public static TaskStackBuilder create(@NonNull Context paramContext)
  {
    return new TaskStackBuilder(paramContext);
  }

  @Deprecated
  public static TaskStackBuilder from(Context paramContext)
  {
    return create(paramContext);
  }

  @NonNull
  public TaskStackBuilder addNextIntent(@NonNull Intent paramIntent)
  {
    this.mIntents.add(paramIntent);
    return this;
  }

  @NonNull
  public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent paramIntent)
  {
    ComponentName localComponentName = paramIntent.getComponent();
    if (localComponentName == null)
      localComponentName = paramIntent.resolveActivity(this.mSourceContext.getPackageManager());
    if (localComponentName != null)
      addParentStack(localComponentName);
    addNextIntent(paramIntent);
    return this;
  }

  @NonNull
  public TaskStackBuilder addParentStack(@NonNull Activity paramActivity)
  {
    Intent localIntent;
    if ((paramActivity instanceof SupportParentable))
      localIntent = ((SupportParentable)paramActivity).getSupportParentActivityIntent();
    else
      localIntent = null;
    if (localIntent == null)
      localIntent = NavUtils.getParentActivityIntent(paramActivity);
    if (localIntent != null)
    {
      ComponentName localComponentName = localIntent.getComponent();
      if (localComponentName == null)
        localComponentName = localIntent.resolveActivity(this.mSourceContext.getPackageManager());
      addParentStack(localComponentName);
      addNextIntent(localIntent);
    }
    return this;
  }

  public TaskStackBuilder addParentStack(ComponentName paramComponentName)
  {
    int i = this.mIntents.size();
    try
    {
      for (Intent localIntent = NavUtils.getParentActivityIntent(this.mSourceContext, paramComponentName); localIntent != null; localIntent = NavUtils.getParentActivityIntent(this.mSourceContext, localIntent.getComponent()))
        this.mIntents.add(i, localIntent);
      return this;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      throw new IllegalArgumentException(localNameNotFoundException);
    }
  }

  @NonNull
  public TaskStackBuilder addParentStack(@NonNull Class<?> paramClass)
  {
    return addParentStack(new ComponentName(this.mSourceContext, paramClass));
  }

  @Nullable
  public Intent editIntentAt(int paramInt)
  {
    return (Intent)this.mIntents.get(paramInt);
  }

  @Deprecated
  public Intent getIntent(int paramInt)
  {
    return editIntentAt(paramInt);
  }

  public int getIntentCount()
  {
    return this.mIntents.size();
  }

  @NonNull
  public Intent[] getIntents()
  {
    Intent[] arrayOfIntent = new Intent[this.mIntents.size()];
    if (arrayOfIntent.length == 0)
      return arrayOfIntent;
    arrayOfIntent[0] = new Intent((Intent)this.mIntents.get(0)).addFlags(268484608);
    for (int i = 1; i < arrayOfIntent.length; i++)
      arrayOfIntent[i] = new Intent((Intent)this.mIntents.get(i));
    return arrayOfIntent;
  }

  @Nullable
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2)
  {
    return getPendingIntent(paramInt1, paramInt2, null);
  }

  @Nullable
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, @Nullable Bundle paramBundle)
  {
    if (this.mIntents.isEmpty())
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
    Intent[] arrayOfIntent = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
    arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
    if (Build.VERSION.SDK_INT >= 16)
      return PendingIntent.getActivities(this.mSourceContext, paramInt1, arrayOfIntent, paramInt2, paramBundle);
    return PendingIntent.getActivities(this.mSourceContext, paramInt1, arrayOfIntent, paramInt2);
  }

  @Deprecated
  public Iterator<Intent> iterator()
  {
    return this.mIntents.iterator();
  }

  public void startActivities()
  {
    startActivities(null);
  }

  public void startActivities(@Nullable Bundle paramBundle)
  {
    if (this.mIntents.isEmpty())
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    Intent[] arrayOfIntent = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
    arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
    if (!ContextCompat.startActivities(this.mSourceContext, arrayOfIntent, paramBundle))
    {
      Intent localIntent = new Intent(arrayOfIntent[(-1 + arrayOfIntent.length)]);
      localIntent.addFlags(268435456);
      this.mSourceContext.startActivity(localIntent);
    }
  }

  public static abstract interface SupportParentable
  {
    @Nullable
    public abstract Intent getSupportParentActivityIntent();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.TaskStackBuilder
 * JD-Core Version:    0.6.1
 */