package com.stub.plugin;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Stub02 extends Service
{
  private Map<String, BusiItem> delegates = new HashMap();

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onDestroy()
  {
    super.onDestroy();
    MyLog.log(new String[] { "Stub02", "onDestroy", "enter" });
    try
    {
      Iterator localIterator = this.delegates.values().iterator();
      while (localIterator.hasNext())
      {
        BusiItem localBusiItem = (BusiItem)localIterator.next();
        Method localMethod = ReflectionUtil.getMethod(localBusiItem.getDelegateClz(), "onDestroy", new Class[0]);
        ReflectionUtil.invoke(localBusiItem.getDelegateImpl(), localMethod, new Object[0]);
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    MyLog.log(new String[] { "Stub02", "onStartCommand", "enter" });
    try
    {
      ReflectionUtil.invokeStatic(ReflectionUtil.getMethod(Class.forName("com.stub.stub02.ScreenReceiver"), "registerScreenActionReceiver", new Class[] { Context.class }), new Object[] { this });
      label53: if (paramIntent != null);
      try
      {
        str = paramIntent.getStringExtra("source");
        String[] arrayOfString = new String[3];
        arrayOfString[0] = "Stub02";
        arrayOfString[1] = "onStartCommand";
        arrayOfString[2] = ("source= " + str);
        MyLog.log(arrayOfString);
        localBusiItem = new BusiItem();
        if (str != null)
        {
          boolean bool = this.delegates.containsKey(str);
          if (bool)
            break label324;
        }
      }
      catch (Throwable localThrowable2)
      {
        try
        {
          String str;
          BusiItem localBusiItem;
          localBusiItem.setDelegateClz(Class.forName(str));
          label154: Class localClass2 = localBusiItem.getDelegateClz();
          if (localClass2 != null);
          try
          {
            localBusiItem.setDelegateImpl(localBusiItem.getDelegateClz().newInstance());
            label179: if ((localBusiItem.getDelegateImpl() != null) && (localBusiItem.getDelegateClz() != null))
            {
              MyLog.log(new String[] { "Stub02", "onStartCommand", "call impl onStartCommand" });
              Class localClass1 = localBusiItem.getDelegateClz();
              Class[] arrayOfClass = new Class[4];
              arrayOfClass[0] = Intent.class;
              arrayOfClass[1] = Integer.TYPE;
              arrayOfClass[2] = Integer.TYPE;
              arrayOfClass[3] = Service.class;
              Method localMethod = ReflectionUtil.getMethod(localClass1, "onStartCommand", arrayOfClass);
              Object localObject = localBusiItem.getDelegateImpl();
              Object[] arrayOfObject = new Object[4];
              arrayOfObject[0] = paramIntent;
              arrayOfObject[1] = Integer.valueOf(paramInt1);
              arrayOfObject[2] = Integer.valueOf(paramInt2);
              arrayOfObject[3] = this;
              ReflectionUtil.invoke(localObject, localMethod, arrayOfObject);
            }
            while (true)
            {
              return super.onStartCommand(paramIntent, paramInt1, paramInt2);
              label324: localBusiItem = (BusiItem)this.delegates.get(str);
              break;
              localThrowable2 = localThrowable2;
            }
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            break label179;
          }
          catch (InstantiationException localInstantiationException)
          {
            break label179;
          }
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          break label154;
        }
      }
    }
    catch (Throwable localThrowable1)
    {
      break label53;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.Stub02
 * JD-Core Version:    0.6.1
 */