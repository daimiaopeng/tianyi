package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BundleCompat
{
  @Nullable
  public static IBinder getBinder(@NonNull Bundle paramBundle, @Nullable String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18)
      return paramBundle.getBinder(paramString);
    return BundleCompatBaseImpl.getBinder(paramBundle, paramString);
  }

  public static void putBinder(@NonNull Bundle paramBundle, @Nullable String paramString, @Nullable IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 18)
      paramBundle.putBinder(paramString, paramIBinder);
    else
      BundleCompatBaseImpl.putBinder(paramBundle, paramString, paramIBinder);
  }

  static class BundleCompatBaseImpl
  {
    private static final String TAG = "BundleCompatBaseImpl";
    private static Method sGetIBinderMethod;
    private static boolean sGetIBinderMethodFetched;
    private static Method sPutIBinderMethod;
    private static boolean sPutIBinderMethodFetched;

    public static IBinder getBinder(Bundle paramBundle, String paramString)
    {
      if (!sGetIBinderMethodFetched)
      {
        try
        {
          sGetIBinderMethod = Bundle.class.getMethod("getIBinder", new Class[] { String.class });
          sGetIBinderMethod.setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", localNoSuchMethodException);
        }
        sGetIBinderMethodFetched = true;
      }
      if (sGetIBinderMethod != null)
        try
        {
          IBinder localIBinder = (IBinder)sGetIBinderMethod.invoke(paramBundle, new Object[] { paramString });
          return localIBinder;
        }
        catch (InvocationTargetException|IllegalAccessException|IllegalArgumentException localInvocationTargetException)
        {
          Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", localInvocationTargetException);
          sGetIBinderMethod = null;
        }
      return null;
    }

    public static void putBinder(Bundle paramBundle, String paramString, IBinder paramIBinder)
    {
      if (!sPutIBinderMethodFetched)
      {
        try
        {
          sPutIBinderMethod = Bundle.class.getMethod("putIBinder", new Class[] { String.class, IBinder.class });
          sPutIBinderMethod.setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", localNoSuchMethodException);
        }
        sPutIBinderMethodFetched = true;
      }
      if (sPutIBinderMethod != null)
        try
        {
          sPutIBinderMethod.invoke(paramBundle, new Object[] { paramString, paramIBinder });
        }
        catch (InvocationTargetException|IllegalAccessException|IllegalArgumentException localInvocationTargetException)
        {
          Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", localInvocationTargetException);
          sPutIBinderMethod = null;
        }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.BundleCompat
 * JD-Core Version:    0.6.1
 */