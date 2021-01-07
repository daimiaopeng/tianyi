package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class NavUtils
{
  public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
  private static final String TAG = "NavUtils";

  // ERROR //
  @Nullable
  public static Intent getParentActivityIntent(@NonNull Activity paramActivity)
  {
    // Byte code:
    //   0: getstatic 27	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 16
    //   5: if_icmplt +17 -> 22
    //   8: aload_0
    //   9: invokevirtual 32	android/app/Activity:getParentActivityIntent	()Landroid/content/Intent;
    //   12: astore 9
    //   14: aload 9
    //   16: ifnull +6 -> 22
    //   19: aload 9
    //   21: areturn
    //   22: aload_0
    //   23: invokestatic 36	android/support/v4/app/NavUtils:getParentActivityName	(Landroid/app/Activity;)Ljava/lang/String;
    //   26: astore_1
    //   27: aload_1
    //   28: ifnonnull +5 -> 33
    //   31: aconst_null
    //   32: areturn
    //   33: new 38	android/content/ComponentName
    //   36: dup
    //   37: aload_0
    //   38: aload_1
    //   39: invokespecial 41	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   42: astore_2
    //   43: aload_0
    //   44: aload_2
    //   45: invokestatic 44	android/support/v4/app/NavUtils:getParentActivityName	(Landroid/content/Context;Landroid/content/ComponentName;)Ljava/lang/String;
    //   48: ifnonnull +12 -> 60
    //   51: aload_2
    //   52: invokestatic 50	android/content/Intent:makeMainActivity	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   55: astore 8
    //   57: goto +16 -> 73
    //   60: new 46	android/content/Intent
    //   63: dup
    //   64: invokespecial 51	android/content/Intent:<init>	()V
    //   67: aload_2
    //   68: invokevirtual 54	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   71: astore 8
    //   73: aload 8
    //   75: areturn
    //   76: new 56	java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial 57	java/lang/StringBuilder:<init>	()V
    //   83: astore_3
    //   84: aload_3
    //   85: ldc 59
    //   87: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload_3
    //   92: aload_1
    //   93: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload_3
    //   98: ldc 65
    //   100: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: ldc 11
    //   106: aload_3
    //   107: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 75	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aconst_null
    //   115: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   43	73	76	android/content/pm/PackageManager$NameNotFoundException
  }

  @Nullable
  public static Intent getParentActivityIntent(@NonNull Context paramContext, @NonNull ComponentName paramComponentName)
  {
    String str = getParentActivityName(paramContext, paramComponentName);
    if (str == null)
      return null;
    ComponentName localComponentName = new ComponentName(paramComponentName.getPackageName(), str);
    Intent localIntent;
    if (getParentActivityName(paramContext, localComponentName) == null)
      localIntent = Intent.makeMainActivity(localComponentName);
    else
      localIntent = new Intent().setComponent(localComponentName);
    return localIntent;
  }

  @Nullable
  public static Intent getParentActivityIntent(@NonNull Context paramContext, @NonNull Class<?> paramClass)
  {
    String str = getParentActivityName(paramContext, new ComponentName(paramContext, paramClass));
    if (str == null)
      return null;
    ComponentName localComponentName = new ComponentName(paramContext, str);
    Intent localIntent;
    if (getParentActivityName(paramContext, localComponentName) == null)
      localIntent = Intent.makeMainActivity(localComponentName);
    else
      localIntent = new Intent().setComponent(localComponentName);
    return localIntent;
  }

  @Nullable
  public static String getParentActivityName(@NonNull Activity paramActivity)
  {
    try
    {
      String str = getParentActivityName(paramActivity, paramActivity.getComponentName());
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new IllegalArgumentException(localNameNotFoundException);
    }
  }

  @Nullable
  public static String getParentActivityName(@NonNull Context paramContext, @NonNull ComponentName paramComponentName)
  {
    ActivityInfo localActivityInfo = paramContext.getPackageManager().getActivityInfo(paramComponentName, 128);
    if (Build.VERSION.SDK_INT >= 16)
    {
      String str2 = localActivityInfo.parentActivityName;
      if (str2 != null)
        return str2;
    }
    if (localActivityInfo.metaData == null)
      return null;
    String str1 = localActivityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
    if (str1 == null)
      return null;
    if (str1.charAt(0) == '.')
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getPackageName());
      localStringBuilder.append(str1);
      str1 = localStringBuilder.toString();
    }
    return str1;
  }

  public static void navigateUpFromSameTask(@NonNull Activity paramActivity)
  {
    Intent localIntent = getParentActivityIntent(paramActivity);
    if (localIntent == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Activity ");
      localStringBuilder.append(paramActivity.getClass().getSimpleName());
      localStringBuilder.append(" does not have a parent activity name specified.");
      localStringBuilder.append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ");
      localStringBuilder.append(" element in your manifest?)");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    navigateUpTo(paramActivity, localIntent);
  }

  public static void navigateUpTo(@NonNull Activity paramActivity, @NonNull Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramActivity.navigateUpTo(paramIntent);
    }
    else
    {
      paramIntent.addFlags(67108864);
      paramActivity.startActivity(paramIntent);
      paramActivity.finish();
    }
  }

  public static boolean shouldUpRecreateTask(@NonNull Activity paramActivity, @NonNull Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return paramActivity.shouldUpRecreateTask(paramIntent);
    String str = paramActivity.getIntent().getAction();
    boolean bool;
    if ((str != null) && (!str.equals("android.intent.action.MAIN")))
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NavUtils
 * JD-Core Version:    0.6.1
 */