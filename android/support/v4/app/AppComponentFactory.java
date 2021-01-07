package android.support.v4.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@RequiresApi(28)
public class AppComponentFactory extends android.app.AppComponentFactory
{
  public final Activity instantiateActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
  {
    return (Activity)CoreComponentFactory.checkCompatWrapper(instantiateActivityCompat(paramClassLoader, paramString, paramIntent));
  }

  @NonNull
  public Activity instantiateActivityCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString, @Nullable Intent paramIntent)
  {
    try
    {
      Activity localActivity = (Activity)paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localActivity;
    }
    catch (InvocationTargetException|NoSuchMethodException localInvocationTargetException)
    {
      throw new RuntimeException("Couldn't call constructor", localInvocationTargetException);
    }
  }

  public final Application instantiateApplication(ClassLoader paramClassLoader, String paramString)
  {
    return (Application)CoreComponentFactory.checkCompatWrapper(instantiateApplicationCompat(paramClassLoader, paramString));
  }

  @NonNull
  public Application instantiateApplicationCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString)
  {
    try
    {
      Application localApplication = (Application)paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localApplication;
    }
    catch (InvocationTargetException|NoSuchMethodException localInvocationTargetException)
    {
      throw new RuntimeException("Couldn't call constructor", localInvocationTargetException);
    }
  }

  public final ContentProvider instantiateProvider(ClassLoader paramClassLoader, String paramString)
  {
    return (ContentProvider)CoreComponentFactory.checkCompatWrapper(instantiateProviderCompat(paramClassLoader, paramString));
  }

  @NonNull
  public ContentProvider instantiateProviderCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString)
  {
    try
    {
      ContentProvider localContentProvider = (ContentProvider)paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localContentProvider;
    }
    catch (InvocationTargetException|NoSuchMethodException localInvocationTargetException)
    {
      throw new RuntimeException("Couldn't call constructor", localInvocationTargetException);
    }
  }

  public final BroadcastReceiver instantiateReceiver(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
  {
    return (BroadcastReceiver)CoreComponentFactory.checkCompatWrapper(instantiateReceiverCompat(paramClassLoader, paramString, paramIntent));
  }

  @NonNull
  public BroadcastReceiver instantiateReceiverCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString, @Nullable Intent paramIntent)
  {
    try
    {
      BroadcastReceiver localBroadcastReceiver = (BroadcastReceiver)paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localBroadcastReceiver;
    }
    catch (InvocationTargetException|NoSuchMethodException localInvocationTargetException)
    {
      throw new RuntimeException("Couldn't call constructor", localInvocationTargetException);
    }
  }

  public final Service instantiateService(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
  {
    return (Service)CoreComponentFactory.checkCompatWrapper(instantiateServiceCompat(paramClassLoader, paramString, paramIntent));
  }

  @NonNull
  public Service instantiateServiceCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString, @Nullable Intent paramIntent)
  {
    try
    {
      Service localService = (Service)paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localService;
    }
    catch (InvocationTargetException|NoSuchMethodException localInvocationTargetException)
    {
      throw new RuntimeException("Couldn't call constructor", localInvocationTargetException);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.AppComponentFactory
 * JD-Core Version:    0.6.1
 */