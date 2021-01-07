package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
class NotificationCompatJellybean
{
  static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
  static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
  private static final String KEY_ACTION_INTENT = "actionIntent";
  private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
  private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
  private static final String KEY_CHOICES = "choices";
  private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
  private static final String KEY_EXTRAS = "extras";
  private static final String KEY_ICON = "icon";
  private static final String KEY_LABEL = "label";
  private static final String KEY_REMOTE_INPUTS = "remoteInputs";
  private static final String KEY_RESULT_KEY = "resultKey";
  private static final String KEY_SEMANTIC_ACTION = "semanticAction";
  private static final String KEY_SHOWS_USER_INTERFACE = "showsUserInterface";
  private static final String KEY_TITLE = "title";
  public static final String TAG = "NotificationCompat";
  private static Class<?> sActionClass;
  private static Field sActionIconField;
  private static Field sActionIntentField;
  private static Field sActionTitleField;
  private static boolean sActionsAccessFailed;
  private static Field sActionsField;
  private static final Object sActionsLock = new Object();
  private static Field sExtrasField;
  private static boolean sExtrasFieldAccessFailed;
  private static final Object sExtrasLock = new Object();

  public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> paramList)
  {
    int i = paramList.size();
    SparseArray localSparseArray = null;
    for (int j = 0; j < i; j++)
    {
      Bundle localBundle = (Bundle)paramList.get(j);
      if (localBundle != null)
      {
        if (localSparseArray == null)
          localSparseArray = new SparseArray();
        localSparseArray.put(j, localBundle);
      }
    }
    return localSparseArray;
  }

  private static boolean ensureActionReflectionReadyLocked()
  {
    if (sActionsAccessFailed)
      return false;
    try
    {
      if (sActionsField == null)
      {
        sActionClass = Class.forName("android.app.Notification$Action");
        sActionIconField = sActionClass.getDeclaredField("icon");
        sActionTitleField = sActionClass.getDeclaredField("title");
        sActionIntentField = sActionClass.getDeclaredField("actionIntent");
        sActionsField = Notification.class.getDeclaredField("actions");
        sActionsField.setAccessible(true);
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Log.e("NotificationCompat", "Unable to access notification actions", localNoSuchFieldException);
      sActionsAccessFailed = true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("NotificationCompat", "Unable to access notification actions", localClassNotFoundException);
      sActionsAccessFailed = true;
    }
    return true ^ sActionsAccessFailed;
  }

  private static RemoteInput fromBundle(Bundle paramBundle)
  {
    ArrayList localArrayList = paramBundle.getStringArrayList("allowedDataTypes");
    HashSet localHashSet = new HashSet();
    if (localArrayList != null)
    {
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
        localHashSet.add((String)localIterator.next());
    }
    RemoteInput localRemoteInput = new RemoteInput(paramBundle.getString("resultKey"), paramBundle.getCharSequence("label"), paramBundle.getCharSequenceArray("choices"), paramBundle.getBoolean("allowFreeFormInput"), paramBundle.getBundle("extras"), localHashSet);
    return localRemoteInput;
  }

  private static RemoteInput[] fromBundleArray(Bundle[] paramArrayOfBundle)
  {
    if (paramArrayOfBundle == null)
      return null;
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfBundle.length];
    for (int i = 0; i < paramArrayOfBundle.length; i++)
      arrayOfRemoteInput[i] = fromBundle(paramArrayOfBundle[i]);
    return arrayOfRemoteInput;
  }

  public static NotificationCompat.Action getAction(Notification paramNotification, int paramInt)
  {
    synchronized (sActionsLock)
    {
      try
      {
        Object[] arrayOfObject = getActionObjectsLocked(paramNotification);
        if (arrayOfObject != null)
        {
          Object localObject4 = arrayOfObject[paramInt];
          Bundle localBundle1 = getExtras(paramNotification);
          if (localBundle1 == null)
            break label127;
          SparseArray localSparseArray = localBundle1.getSparseParcelableArray("android.support.actionExtras");
          if (localSparseArray == null)
            break label127;
          localBundle2 = (Bundle)localSparseArray.get(paramInt);
          NotificationCompat.Action localAction = readAction(sActionIconField.getInt(localObject4), (CharSequence)sActionTitleField.get(localObject4), (PendingIntent)sActionIntentField.get(localObject4), localBundle2);
          return localAction;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", localIllegalAccessException);
        sActionsAccessFailed = true;
        return null;
      }
      Object localObject2;
      throw localObject2;
      label127: Bundle localBundle2 = null;
    }
  }

  public static int getActionCount(Notification paramNotification)
  {
    while (true)
    {
      synchronized (sActionsLock)
      {
        Object[] arrayOfObject = getActionObjectsLocked(paramNotification);
        if (arrayOfObject != null)
        {
          i = arrayOfObject.length;
          return i;
        }
      }
      int i = 0;
    }
  }

  static NotificationCompat.Action getActionFromBundle(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle.getBundle("extras");
    boolean bool;
    if (localBundle != null)
      bool = localBundle.getBoolean("android.support.allowGeneratedReplies", false);
    else
      bool = false;
    NotificationCompat.Action localAction = new NotificationCompat.Action(paramBundle.getInt("icon"), paramBundle.getCharSequence("title"), (PendingIntent)paramBundle.getParcelable("actionIntent"), paramBundle.getBundle("extras"), fromBundleArray(getBundleArrayFromBundle(paramBundle, "remoteInputs")), fromBundleArray(getBundleArrayFromBundle(paramBundle, "dataOnlyRemoteInputs")), bool, paramBundle.getInt("semanticAction"), paramBundle.getBoolean("showsUserInterface"));
    return localAction;
  }

  private static Object[] getActionObjectsLocked(Notification paramNotification)
  {
    synchronized (sActionsLock)
    {
      if (!ensureActionReflectionReadyLocked())
        return null;
      try
      {
        Object[] arrayOfObject = (Object[])sActionsField.get(paramNotification);
        return arrayOfObject;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", localIllegalAccessException);
        sActionsAccessFailed = true;
        return null;
      }
    }
  }

  private static Bundle[] getBundleArrayFromBundle(Bundle paramBundle, String paramString)
  {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    if ((!(arrayOfParcelable instanceof Bundle[])) && (arrayOfParcelable != null))
    {
      Bundle[] arrayOfBundle = (Bundle[])Arrays.copyOf(arrayOfParcelable, arrayOfParcelable.length, [Landroid.os.Bundle.class);
      paramBundle.putParcelableArray(paramString, arrayOfBundle);
      return arrayOfBundle;
    }
    return (Bundle[])arrayOfParcelable;
  }

  static Bundle getBundleForAction(NotificationCompat.Action paramAction)
  {
    Bundle localBundle1 = new Bundle();
    localBundle1.putInt("icon", paramAction.getIcon());
    localBundle1.putCharSequence("title", paramAction.getTitle());
    localBundle1.putParcelable("actionIntent", paramAction.getActionIntent());
    Bundle localBundle2;
    if (paramAction.getExtras() != null)
      localBundle2 = new Bundle(paramAction.getExtras());
    else
      localBundle2 = new Bundle();
    localBundle2.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    localBundle1.putBundle("extras", localBundle2);
    localBundle1.putParcelableArray("remoteInputs", toBundleArray(paramAction.getRemoteInputs()));
    localBundle1.putBoolean("showsUserInterface", paramAction.getShowsUserInterface());
    localBundle1.putInt("semanticAction", paramAction.getSemanticAction());
    return localBundle1;
  }

  public static Bundle getExtras(Notification paramNotification)
  {
    synchronized (sExtrasLock)
    {
      if (sExtrasFieldAccessFailed)
        return null;
      try
      {
        if (sExtrasField == null)
        {
          Field localField = Notification.class.getDeclaredField("extras");
          if (!Bundle.class.isAssignableFrom(localField.getType()))
          {
            Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
            sExtrasFieldAccessFailed = true;
            return null;
          }
          localField.setAccessible(true);
          sExtrasField = localField;
        }
        Bundle localBundle = (Bundle)sExtrasField.get(paramNotification);
        if (localBundle == null)
        {
          localBundle = new Bundle();
          sExtrasField.set(paramNotification, localBundle);
        }
        return localBundle;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("NotificationCompat", "Unable to access notification extras", localNoSuchFieldException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.e("NotificationCompat", "Unable to access notification extras", localIllegalAccessException);
      }
      sExtrasFieldAccessFailed = true;
      return null;
    }
  }

  public static NotificationCompat.Action readAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle)
  {
    RemoteInput[] arrayOfRemoteInput1;
    RemoteInput[] arrayOfRemoteInput2;
    boolean bool1;
    if (paramBundle != null)
    {
      RemoteInput[] arrayOfRemoteInput3 = fromBundleArray(getBundleArrayFromBundle(paramBundle, "android.support.remoteInputs"));
      RemoteInput[] arrayOfRemoteInput4 = fromBundleArray(getBundleArrayFromBundle(paramBundle, "android.support.dataRemoteInputs"));
      boolean bool2 = paramBundle.getBoolean("android.support.allowGeneratedReplies");
      arrayOfRemoteInput1 = arrayOfRemoteInput3;
      arrayOfRemoteInput2 = arrayOfRemoteInput4;
      bool1 = bool2;
    }
    else
    {
      arrayOfRemoteInput1 = null;
      arrayOfRemoteInput2 = null;
      bool1 = false;
    }
    NotificationCompat.Action localAction = new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent, paramBundle, arrayOfRemoteInput1, arrayOfRemoteInput2, bool1, 0, true);
    return localAction;
  }

  private static Bundle toBundle(RemoteInput paramRemoteInput)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("resultKey", paramRemoteInput.getResultKey());
    localBundle.putCharSequence("label", paramRemoteInput.getLabel());
    localBundle.putCharSequenceArray("choices", paramRemoteInput.getChoices());
    localBundle.putBoolean("allowFreeFormInput", paramRemoteInput.getAllowFreeFormInput());
    localBundle.putBundle("extras", paramRemoteInput.getExtras());
    Set localSet = paramRemoteInput.getAllowedDataTypes();
    if ((localSet != null) && (!localSet.isEmpty()))
    {
      ArrayList localArrayList = new ArrayList(localSet.size());
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
        localArrayList.add((String)localIterator.next());
      localBundle.putStringArrayList("allowedDataTypes", localArrayList);
    }
    return localBundle;
  }

  private static Bundle[] toBundleArray(RemoteInput[] paramArrayOfRemoteInput)
  {
    if (paramArrayOfRemoteInput == null)
      return null;
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfRemoteInput.length];
    for (int i = 0; i < paramArrayOfRemoteInput.length; i++)
      arrayOfBundle[i] = toBundle(paramArrayOfRemoteInput[i]);
    return arrayOfBundle;
  }

  public static Bundle writeActionAndGetExtras(Notification.Builder paramBuilder, NotificationCompat.Action paramAction)
  {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    Bundle localBundle = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null)
      localBundle.putParcelableArray("android.support.remoteInputs", toBundleArray(paramAction.getRemoteInputs()));
    if (paramAction.getDataOnlyRemoteInputs() != null)
      localBundle.putParcelableArray("android.support.dataRemoteInputs", toBundleArray(paramAction.getDataOnlyRemoteInputs()));
    localBundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    return localBundle;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationCompatJellybean
 * JD-Core Version:    0.6.1
 */