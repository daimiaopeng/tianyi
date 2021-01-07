package android.arch.lifecycle;

import android.support.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class a
{
  static a a = new a();
  private final Map<Class, a> b = new HashMap();
  private final Map<Class, Boolean> c = new HashMap();

  private a a(Class paramClass, @Nullable Method[] paramArrayOfMethod)
  {
    Class localClass = paramClass.getSuperclass();
    HashMap localHashMap = new HashMap();
    if (localClass != null)
    {
      a locala2 = b(localClass);
      if (locala2 != null)
        localHashMap.putAll(locala2.b);
    }
    Class[] arrayOfClass1 = paramClass.getInterfaces();
    int i = arrayOfClass1.length;
    for (int j = 0; j < i; j++)
    {
      Iterator localIterator = b(arrayOfClass1[j]).b.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        a(localHashMap, (b)localEntry.getKey(), (c.a)localEntry.getValue(), paramClass);
      }
    }
    if (paramArrayOfMethod == null)
      paramArrayOfMethod = c(paramClass);
    int k = paramArrayOfMethod.length;
    int m = 0;
    boolean bool = false;
    while (m < k)
    {
      Method localMethod = paramArrayOfMethod[m];
      l locall = (l)localMethod.getAnnotation(l.class);
      if (locall != null)
      {
        Class[] arrayOfClass2 = localMethod.getParameterTypes();
        int n;
        if (arrayOfClass2.length > 0)
        {
          if (!arrayOfClass2[0].isAssignableFrom(e.class))
            throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
          n = 1;
        }
        else
        {
          n = 0;
        }
        c.a locala = locall.a();
        if (arrayOfClass2.length > 1)
        {
          if (!arrayOfClass2[1].isAssignableFrom(c.a.class))
            throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
          if (locala != c.a.ON_ANY)
            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
          n = 2;
        }
        if (arrayOfClass2.length > 2)
          throw new IllegalArgumentException("cannot have more than 2 params");
        a(localHashMap, new b(n, localMethod), locala, paramClass);
        bool = true;
      }
      m++;
    }
    a locala1 = new a(localHashMap);
    this.b.put(paramClass, locala1);
    this.c.put(paramClass, Boolean.valueOf(bool));
    return locala1;
  }

  private void a(Map<b, c.a> paramMap, b paramb, c.a parama, Class paramClass)
  {
    c.a locala = (c.a)paramMap.get(paramb);
    if ((locala != null) && (parama != locala))
    {
      Method localMethod = paramb.b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Method ");
      localStringBuilder.append(localMethod.getName());
      localStringBuilder.append(" in ");
      localStringBuilder.append(paramClass.getName());
      localStringBuilder.append(" already declared with different @OnLifecycleEvent value: previous value ");
      localStringBuilder.append(locala);
      localStringBuilder.append(", new value ");
      localStringBuilder.append(parama);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (locala == null)
      paramMap.put(paramb, parama);
  }

  private Method[] c(Class paramClass)
  {
    try
    {
      Method[] arrayOfMethod = paramClass.getDeclaredMethods();
      return arrayOfMethod;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", localNoClassDefFoundError);
    }
  }

  boolean a(Class paramClass)
  {
    if (this.c.containsKey(paramClass))
      return ((Boolean)this.c.get(paramClass)).booleanValue();
    Method[] arrayOfMethod = c(paramClass);
    int i = arrayOfMethod.length;
    for (int j = 0; j < i; j++)
      if ((l)arrayOfMethod[j].getAnnotation(l.class) != null)
      {
        a(paramClass, arrayOfMethod);
        return true;
      }
    this.c.put(paramClass, Boolean.valueOf(false));
    return false;
  }

  a b(Class paramClass)
  {
    a locala = (a)this.b.get(paramClass);
    if (locala != null)
      return locala;
    return a(paramClass, null);
  }

  static class a
  {
    final Map<c.a, List<a.b>> a;
    final Map<a.b, c.a> b;

    a(Map<a.b, c.a> paramMap)
    {
      this.b = paramMap;
      this.a = new HashMap();
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        c.a locala = (c.a)localEntry.getValue();
        Object localObject = (List)this.a.get(locala);
        if (localObject == null)
        {
          localObject = new ArrayList();
          this.a.put(locala, localObject);
        }
        ((List)localObject).add(localEntry.getKey());
      }
    }

    private static void a(List<a.b> paramList, e parame, c.a parama, Object paramObject)
    {
      if (paramList != null)
        for (int i = -1 + paramList.size(); i >= 0; i--)
          ((a.b)paramList.get(i)).a(parame, parama, paramObject);
    }

    void a(e parame, c.a parama, Object paramObject)
    {
      a((List)this.a.get(parama), parame, parama, paramObject);
      a((List)this.a.get(c.a.ON_ANY), parame, parama, paramObject);
    }
  }

  static class b
  {
    final int a;
    final Method b;

    b(int paramInt, Method paramMethod)
    {
      this.a = paramInt;
      this.b = paramMethod;
      this.b.setAccessible(true);
    }

    void a(e parame, c.a parama, Object paramObject)
    {
      while (true)
        try
        {
          switch (this.a)
          {
          case 2:
            this.b.invoke(paramObject, new Object[] { parame, parama });
            break;
          case 1:
            this.b.invoke(paramObject, new Object[] { parame });
            break;
          case 0:
            this.b.invoke(paramObject, new Object[0]);
            return;
          }
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new RuntimeException(localIllegalAccessException);
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          throw new RuntimeException("Failed to call observer method", localInvocationTargetException.getCause());
        }
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject)
        return bool;
      if ((paramObject != null) && (getClass() == paramObject.getClass()))
      {
        b localb = (b)paramObject;
        if ((this.a != localb.a) || (!this.b.getName().equals(localb.b.getName())))
          bool = false;
        return bool;
      }
      return false;
    }

    public int hashCode()
    {
      return 31 * this.a + this.b.getName().hashCode();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.a
 * JD-Core Version:    0.6.1
 */