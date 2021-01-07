package com.stub.plugin;

import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil
{
  public static Method getMethod(Class<?> paramClass, String paramString, Class<?>[] paramArrayOfClass)
  {
    if ((paramClass != null) && (!TextUtils.isEmpty(paramString)));
    while (true)
    {
      try
      {
        Method localMethod2 = paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
        localMethod1 = localMethod2;
        return localMethod1;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
      Method localMethod1 = null;
    }
  }

  public static Class<?> getSuperClass(Class<?> paramClass)
  {
    if (paramClass != null);
    for (Class localClass = paramClass.getSuperclass(); ; localClass = null)
      return localClass;
  }

  public static Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    if ((paramObject != null) && (paramMethod != null));
    try
    {
      Object localObject2 = paramMethod.invoke(paramObject, paramArrayOfObject);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        Object localObject1 = null;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label23;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      label23: break label23;
    }
  }

  public static Object invokeStatic(Method paramMethod, Object[] paramArrayOfObject)
  {
    Object localObject1 = null;
    if (paramMethod != null);
    try
    {
      Object localObject2 = paramMethod.invoke(null, paramArrayOfObject);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localObject1 = null;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localObject1 = null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localObject1 = null;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.ReflectionUtil
 * JD-Core Version:    0.6.1
 */