package com.stub.plugin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import java.lang.reflect.Method;

public class Stub01 extends Activity
  implements DialogInterface.OnDismissListener, View.OnClickListener, View.OnTouchListener
{
  public static transient boolean isMeTopActivity = false;
  private Class<?> delegateClz = null;
  private Object delegateImpl = null;
  private String source = null;

  public static boolean isMeTopActivity()
  {
    return isMeTopActivity;
  }

  public void finish()
  {
    super.finish();
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "finish";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "finish", new Class[0]);
      ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      label80: return;
    }
    catch (Throwable localThrowable)
    {
      break label80;
    }
  }

  public Resources getResources()
  {
    Resources localResources;
    try
    {
      if ((this.source != null) && (!this.source.contains("com.stub.stub05")) && (!this.source.contains("rpa")))
      {
        localResources = super.getResources();
      }
      else
      {
        if (TextUtils.isEmpty(this.source))
        {
          Intent localIntent = getIntent();
          if (localIntent != null)
            this.source = localIntent.getStringExtra("source");
        }
        try
        {
          Class localClass2;
          if (this.source.contains("com.stub.stub05"))
            localClass2 = Class.forName("com.stub.stub05.util.ResourcesHelper");
          Class localClass1;
          for (localObject1 = localClass2; ; localObject1 = localClass1)
          {
            Object localObject2 = ReflectionUtil.invokeStatic(ReflectionUtil.getMethod((Class)localObject1, "getResources", new Class[0]), new Object[0]);
            if (localObject2 == null)
              break;
            localResources = (Resources)localObject2;
            if (localResources != null)
              break label187;
            localResources = super.getResources();
            break label187;
            if (!this.source.contains("adsdk"))
              break label181;
            localClass1 = Class.forName("com.stub.adsdk.rpa.util.ResourcesHelper");
          }
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          while (true)
            localObject1 = null;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localResources = null;
        continue;
        localResources = null;
        continue;
        label181: Object localObject1 = null;
      }
    }
    label187: return localResources;
  }

  public void onBackPressed()
  {
    super.onBackPressed();
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onBackPressed";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onBackPressed", new Class[0]);
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      }
      label94: return;
    }
    catch (Throwable localThrowable)
    {
      break label94;
    }
  }

  public void onClick(View paramView)
  {
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onClick";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onClick", new Class[] { View.class });
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[] { paramView });
      }
      label101: return;
    }
    catch (Throwable localThrowable)
    {
      break label101;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    try
    {
      Intent localIntent = getIntent();
      if (localIntent != null)
      {
        if (!localIntent.hasExtra("appupdate"))
          break label210;
        Util.setThemeWithSdkVersion(this);
      }
      while (true)
      {
        super.onCreate(paramBundle);
        if (localIntent != null)
        {
          this.source = localIntent.getStringExtra("source");
          if (TextUtils.isEmpty(this.source))
            this.source = "com.stub.stub01.StartActivity";
        }
        this.delegateClz = Class.forName(this.source);
        String[] arrayOfString = new String[3];
        arrayOfString[0] = "Stub01";
        arrayOfString[1] = "onCreate";
        arrayOfString[2] = ("source= " + this.source);
        MyLog.log(arrayOfString);
        if (this.delegateClz == null)
          break;
        this.delegateImpl = this.delegateClz.newInstance();
        if ((this.delegateImpl == null) || (this.delegateClz == null))
          break;
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onCreate", new Class[] { Bundle.class, Object.class });
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[] { paramBundle, this });
        break;
        label210: if (!localIntent.hasExtra("pull"))
        {
          boolean bool = localIntent.hasExtra("ls");
          if (!bool);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      finish();
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onDestroy";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      isMeTopActivity = false;
      if ((this.delegateImpl != null) && (this.delegateClz != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onDestroy", new Class[0]);
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      }
      label98: return;
    }
    catch (Throwable localThrowable)
    {
      break label98;
    }
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onDismiss";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onDismiss", new Class[] { DialogInterface.class });
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[] { paramDialogInterface });
      }
      label101: return;
    }
    catch (Throwable localThrowable)
    {
      break label101;
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onKeyDown";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Class localClass = this.delegateClz;
        Class[] arrayOfClass = new Class[2];
        arrayOfClass[0] = Integer.TYPE;
        arrayOfClass[1] = KeyEvent.class;
        Method localMethod = ReflectionUtil.getMethod(localClass, "onKeyDown", arrayOfClass);
        Object localObject1 = this.delegateImpl;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        arrayOfObject[1] = paramKeyEvent;
        Object localObject2 = ReflectionUtil.invoke(localObject1, localMethod, arrayOfObject);
        if (localObject2 != null)
        {
          boolean bool2 = ((Boolean)localObject2).booleanValue();
          bool1 = bool2;
          return bool1;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        boolean bool1 = super.onKeyDown(paramInt, paramKeyEvent);
    }
  }

  protected void onPause()
  {
    super.onPause();
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onPause";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateImpl != null) && (this.delegateClz != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onPause", new Class[0]);
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      }
      label94: return;
    }
    catch (Throwable localThrowable)
    {
      break label94;
    }
  }

  protected void onResume()
  {
    super.onResume();
    try
    {
      isMeTopActivity = false;
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onResume";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateImpl != null) && (this.delegateClz != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onResume", new Class[0]);
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      }
      label98: isMeTopActivity = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      break label98;
    }
  }

  protected void onStop()
  {
    super.onStop();
    try
    {
      isMeTopActivity = false;
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onStop";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateImpl != null) && (this.delegateClz != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onStop", new Class[0]);
        ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      }
      label98: return;
    }
    catch (Throwable localThrowable)
    {
      break label98;
    }
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onTouch";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onTouch", new Class[] { View.class, MotionEvent.class });
        Object localObject = ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[] { paramView, paramMotionEvent });
        if (localObject != null)
        {
          boolean bool2 = ((Boolean)localObject).booleanValue();
          bool1 = bool2;
          return bool1;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        boolean bool1 = super.onTouchEvent(paramMotionEvent);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onTouchEvent";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onTouchEvent", new Class[] { MotionEvent.class });
        Object localObject = ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[] { paramMotionEvent });
        if (localObject != null)
        {
          boolean bool2 = ((Boolean)localObject).booleanValue();
          bool1 = bool2;
          return bool1;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        boolean bool1 = super.onTouchEvent(paramMotionEvent);
    }
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "onWindowFocusChanged";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      if ((this.delegateClz != null) && (this.delegateImpl != null))
      {
        Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "onWindowFocusChanged", new Class[] { Boolean.class });
        Object localObject = this.delegateImpl;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(paramBoolean);
        ReflectionUtil.invoke(localObject, localMethod, arrayOfObject);
      }
      else
      {
        super.onWindowFocusChanged(paramBoolean);
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void startActivity(Intent paramIntent)
  {
    super.startActivity(paramIntent);
    try
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = "Stub01";
      arrayOfString[1] = "startActivity";
      arrayOfString[2] = "enter";
      arrayOfString[3] = ("source= " + this.source);
      MyLog.log(arrayOfString);
      Method localMethod = ReflectionUtil.getMethod(this.delegateClz, "startActivity", new Class[] { Intent.class });
      ReflectionUtil.invoke(this.delegateImpl, localMethod, new Object[0]);
      label88: return;
    }
    catch (Throwable localThrowable)
    {
      break label88;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.Stub01
 * JD-Core Version:    0.6.1
 */