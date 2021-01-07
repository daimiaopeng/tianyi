package android.support.v4.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;

@RequiresApi(api=28)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class CoreComponentFactory extends AppComponentFactory
{
  private static final String TAG = "CoreComponentFactory";

  static <T> T checkCompatWrapper(T paramT)
  {
    if ((paramT instanceof CompatWrapped))
    {
      Object localObject = ((CompatWrapped)paramT).getWrapper();
      if (localObject != null)
        return localObject;
    }
    return paramT;
  }

  public Activity instantiateActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
  {
    return (Activity)checkCompatWrapper(super.instantiateActivity(paramClassLoader, paramString, paramIntent));
  }

  public Application instantiateApplication(ClassLoader paramClassLoader, String paramString)
  {
    return (Application)checkCompatWrapper(super.instantiateApplication(paramClassLoader, paramString));
  }

  public ContentProvider instantiateProvider(ClassLoader paramClassLoader, String paramString)
  {
    return (ContentProvider)checkCompatWrapper(super.instantiateProvider(paramClassLoader, paramString));
  }

  public BroadcastReceiver instantiateReceiver(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
  {
    return (BroadcastReceiver)checkCompatWrapper(super.instantiateReceiver(paramClassLoader, paramString, paramIntent));
  }

  public Service instantiateService(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
  {
    return (Service)checkCompatWrapper(super.instantiateService(paramClassLoader, paramString, paramIntent));
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static abstract interface CompatWrapped
  {
    public abstract Object getWrapper();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.CoreComponentFactory
 * JD-Core Version:    0.6.1
 */