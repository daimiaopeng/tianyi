package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class h
{
  private static Map<Class, Integer> a = new HashMap();
  private static Map<Class, List<Constructor<? extends b>>> b = new HashMap();

  @NonNull
  static GenericLifecycleObserver a(Object paramObject)
  {
    if ((paramObject instanceof FullLifecycleObserver))
      return new FullLifecycleObserverAdapter((FullLifecycleObserver)paramObject);
    if ((paramObject instanceof GenericLifecycleObserver))
      return (GenericLifecycleObserver)paramObject;
    Class localClass = paramObject.getClass();
    if (b(localClass) == 2)
    {
      List localList = (List)b.get(localClass);
      int i = localList.size();
      int j = 0;
      if (i == 1)
        return new SingleGeneratedAdapterObserver(a((Constructor)localList.get(0), paramObject));
      b[] arrayOfb = new b[localList.size()];
      while (j < localList.size())
      {
        arrayOfb[j] = a((Constructor)localList.get(j), paramObject);
        j++;
      }
      return new CompositeGeneratedAdaptersObserver(arrayOfb);
    }
    return new ReflectiveGenericLifecycleObserver(paramObject);
  }

  private static b a(Constructor<? extends b> paramConstructor, Object paramObject)
  {
    try
    {
      b localb = (b)paramConstructor.newInstance(new Object[] { paramObject });
      return localb;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new RuntimeException(localInvocationTargetException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  public static String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString.replace(".", "_"));
    localStringBuilder.append("_LifecycleAdapter");
    return localStringBuilder.toString();
  }

  @Nullable
  private static Constructor<? extends b> a(Class<?> paramClass)
  {
    String str2;
    while (true)
      try
      {
        Package localPackage = paramClass.getPackage();
        String str1 = paramClass.getCanonicalName();
        if (localPackage != null)
        {
          str2 = localPackage.getName();
          if (!str2.isEmpty())
            str1 = str1.substring(1 + str2.length());
          String str3 = a(str1);
          if (!str2.isEmpty())
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(str2);
            localStringBuilder.append(".");
            localStringBuilder.append(str3);
            str3 = localStringBuilder.toString();
          }
          Constructor localConstructor = Class.forName(str3).getDeclaredConstructor(new Class[] { paramClass });
          if (!localConstructor.isAccessible())
            localConstructor.setAccessible(true);
          return localConstructor;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new RuntimeException(localNoSuchMethodException);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        return null;
        str2 = "";
        tmpTernaryOp = localClassNotFoundException;
      }
  }

  private static int b(Class<?> paramClass)
  {
    if (a.containsKey(paramClass))
      return ((Integer)a.get(paramClass)).intValue();
    int i = c(paramClass);
    a.put(paramClass, Integer.valueOf(i));
    return i;
  }

  private static int c(Class<?> paramClass)
  {
    if (paramClass.getCanonicalName() == null)
      return 1;
    Constructor localConstructor = a(paramClass);
    if (localConstructor != null)
    {
      b.put(paramClass, Collections.singletonList(localConstructor));
      return 2;
    }
    if (a.a.a(paramClass))
      return 1;
    Class localClass1 = paramClass.getSuperclass();
    boolean bool = d(localClass1);
    ArrayList localArrayList = null;
    if (bool)
    {
      if (b(localClass1) == 1)
        return 1;
      localArrayList = new ArrayList((Collection)b.get(localClass1));
    }
    for (Class localClass2 : paramClass.getInterfaces())
      if (d(localClass2))
      {
        if (b(localClass2) == 1)
          return 1;
        if (localArrayList == null)
          localArrayList = new ArrayList();
        localArrayList.addAll((Collection)b.get(localClass2));
      }
    if (localArrayList != null)
    {
      b.put(paramClass, localArrayList);
      return 2;
    }
    return 1;
  }

  private static boolean d(Class<?> paramClass)
  {
    boolean bool;
    if ((paramClass != null) && (d.class.isAssignableFrom(paramClass)))
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.h
 * JD-Core Version:    0.6.1
 */