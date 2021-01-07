package com.google.a.b;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class b
{
  static final Type[] a = new Type[0];

  private static int a(Object[] paramArrayOfObject, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
      if (paramObject.equals(paramArrayOfObject[i]))
        return i;
    throw new NoSuchElementException();
  }

  private static Class<?> a(TypeVariable<?> paramTypeVariable)
  {
    GenericDeclaration localGenericDeclaration = paramTypeVariable.getGenericDeclaration();
    Class localClass;
    if ((localGenericDeclaration instanceof Class))
      localClass = (Class)localGenericDeclaration;
    else
      localClass = null;
    return localClass;
  }

  public static GenericArrayType a(Type paramType)
  {
    return new a(paramType);
  }

  public static ParameterizedType a(Type paramType1, Type paramType2, Type[] paramArrayOfType)
  {
    return new b(paramType1, paramType2, paramArrayOfType);
  }

  public static Type a(Type paramType, Class<?> paramClass)
  {
    Type localType = b(paramType, paramClass, Collection.class);
    if ((localType instanceof WildcardType))
      localType = ((WildcardType)localType).getUpperBounds()[0];
    if ((localType instanceof ParameterizedType))
      return ((ParameterizedType)localType).getActualTypeArguments()[0];
    return Object.class;
  }

  static Type a(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == paramClass1)
      return paramType;
    if (paramClass2.isInterface())
    {
      Class[] arrayOfClass = paramClass1.getInterfaces();
      int i = 0;
      int j = arrayOfClass.length;
      while (i < j)
      {
        if (arrayOfClass[i] == paramClass2)
          return paramClass1.getGenericInterfaces()[i];
        if (paramClass2.isAssignableFrom(arrayOfClass[i]))
          return a(paramClass1.getGenericInterfaces()[i], arrayOfClass[i], paramClass2);
        i++;
      }
    }
    if (!paramClass1.isInterface())
      while (paramClass1 != Object.class)
      {
        Class localClass = paramClass1.getSuperclass();
        if (localClass == paramClass2)
          return paramClass1.getGenericSuperclass();
        if (paramClass2.isAssignableFrom(localClass))
          return a(paramClass1.getGenericSuperclass(), localClass, paramClass2);
        paramClass1 = localClass;
      }
    return paramClass2;
  }

  public static Type a(Type paramType1, Class<?> paramClass, Type paramType2)
  {
    while ((paramType2 instanceof TypeVariable))
    {
      TypeVariable localTypeVariable = (TypeVariable)paramType2;
      Type localType9 = a(paramType1, paramClass, localTypeVariable);
      if (localType9 == localTypeVariable)
        return localType9;
      paramType2 = localType9;
    }
    if ((paramType2 instanceof Class))
    {
      Object localObject = (Class)paramType2;
      if (((Class)localObject).isArray())
      {
        Class localClass = ((Class)localObject).getComponentType();
        Type localType8 = a(paramType1, paramClass, localClass);
        if (localClass != localType8)
          localObject = a(localType8);
        return localObject;
      }
    }
    if ((paramType2 instanceof GenericArrayType))
    {
      GenericArrayType localGenericArrayType = (GenericArrayType)paramType2;
      Type localType6 = localGenericArrayType.getGenericComponentType();
      Type localType7 = a(paramType1, paramClass, localType6);
      if (localType6 != localType7)
        localGenericArrayType = a(localType7);
      return localGenericArrayType;
    }
    boolean bool = paramType2 instanceof ParameterizedType;
    int i = 0;
    if (bool)
    {
      ParameterizedType localParameterizedType = (ParameterizedType)paramType2;
      Type localType3 = localParameterizedType.getOwnerType();
      Type localType4 = a(paramType1, paramClass, localType3);
      int j;
      if (localType4 != localType3)
        j = 1;
      else
        j = 0;
      Type[] arrayOfType3 = localParameterizedType.getActualTypeArguments();
      int k = arrayOfType3.length;
      while (i < k)
      {
        Type localType5 = a(paramType1, paramClass, arrayOfType3[i]);
        if (localType5 != arrayOfType3[i])
        {
          if (j == 0)
          {
            arrayOfType3 = (Type[])arrayOfType3.clone();
            j = 1;
          }
          arrayOfType3[i] = localType5;
        }
        i++;
      }
      if (j != 0)
        localParameterizedType = a(localType4, localParameterizedType.getRawType(), arrayOfType3);
      return localParameterizedType;
    }
    if ((paramType2 instanceof WildcardType))
    {
      WildcardType localWildcardType = (WildcardType)paramType2;
      Type[] arrayOfType1 = localWildcardType.getLowerBounds();
      Type[] arrayOfType2 = localWildcardType.getUpperBounds();
      if (arrayOfType1.length == 1)
      {
        Type localType2 = a(paramType1, paramClass, arrayOfType1[0]);
        if (localType2 != arrayOfType1[0])
          return c(localType2);
      }
      else if (arrayOfType2.length == 1)
      {
        Type localType1 = a(paramType1, paramClass, arrayOfType2[0]);
        if (localType1 != arrayOfType2[0])
          return b(localType1);
      }
      return localWildcardType;
    }
    return paramType2;
  }

  static Type a(Type paramType, Class<?> paramClass, TypeVariable<?> paramTypeVariable)
  {
    Class localClass = a(paramTypeVariable);
    if (localClass == null)
      return paramTypeVariable;
    Type localType = a(paramType, paramClass, localClass);
    if ((localType instanceof ParameterizedType))
    {
      int i = a(localClass.getTypeParameters(), paramTypeVariable);
      return ((ParameterizedType)localType).getActualTypeArguments()[i];
    }
    return paramTypeVariable;
  }

  static boolean a(Object paramObject1, Object paramObject2)
  {
    boolean bool;
    if ((paramObject1 != paramObject2) && ((paramObject1 == null) || (!paramObject1.equals(paramObject2))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public static boolean a(Type paramType1, Type paramType2)
  {
    boolean bool = true;
    if (paramType1 == paramType2)
      return bool;
    if ((paramType1 instanceof Class))
      return paramType1.equals(paramType2);
    if ((paramType1 instanceof ParameterizedType))
    {
      if (!(paramType2 instanceof ParameterizedType))
        return false;
      ParameterizedType localParameterizedType1 = (ParameterizedType)paramType1;
      ParameterizedType localParameterizedType2 = (ParameterizedType)paramType2;
      if ((!a(localParameterizedType1.getOwnerType(), localParameterizedType2.getOwnerType())) || (!localParameterizedType1.getRawType().equals(localParameterizedType2.getRawType())) || (!Arrays.equals(localParameterizedType1.getActualTypeArguments(), localParameterizedType2.getActualTypeArguments())))
        bool = false;
      return bool;
    }
    if ((paramType1 instanceof GenericArrayType))
    {
      if (!(paramType2 instanceof GenericArrayType))
        return false;
      GenericArrayType localGenericArrayType1 = (GenericArrayType)paramType1;
      GenericArrayType localGenericArrayType2 = (GenericArrayType)paramType2;
      return a(localGenericArrayType1.getGenericComponentType(), localGenericArrayType2.getGenericComponentType());
    }
    if ((paramType1 instanceof WildcardType))
    {
      if (!(paramType2 instanceof WildcardType))
        return false;
      WildcardType localWildcardType1 = (WildcardType)paramType1;
      WildcardType localWildcardType2 = (WildcardType)paramType2;
      if ((!Arrays.equals(localWildcardType1.getUpperBounds(), localWildcardType2.getUpperBounds())) || (!Arrays.equals(localWildcardType1.getLowerBounds(), localWildcardType2.getLowerBounds())))
        bool = false;
      return bool;
    }
    if ((paramType1 instanceof TypeVariable))
    {
      if (!(paramType2 instanceof TypeVariable))
        return false;
      TypeVariable localTypeVariable1 = (TypeVariable)paramType1;
      TypeVariable localTypeVariable2 = (TypeVariable)paramType2;
      if ((localTypeVariable1.getGenericDeclaration() != localTypeVariable2.getGenericDeclaration()) || (!localTypeVariable1.getName().equals(localTypeVariable2.getName())))
        bool = false;
      return bool;
    }
    return false;
  }

  private static int b(Object paramObject)
  {
    int i;
    if (paramObject != null)
      i = paramObject.hashCode();
    else
      i = 0;
    return i;
  }

  static Type b(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    a.a(paramClass2.isAssignableFrom(paramClass1));
    return a(paramType, paramClass1, a(paramType, paramClass1, paramClass2));
  }

  public static WildcardType b(Type paramType)
  {
    return new c(new Type[] { paramType }, a);
  }

  public static Type[] b(Type paramType, Class<?> paramClass)
  {
    if (paramType == Properties.class)
      return new Type[] { String.class, String.class };
    Type localType = b(paramType, paramClass, Map.class);
    if ((localType instanceof ParameterizedType))
      return ((ParameterizedType)localType).getActualTypeArguments();
    return new Type[] { Object.class, Object.class };
  }

  public static WildcardType c(Type paramType)
  {
    return new c(new Type[] { Object.class }, new Type[] { paramType });
  }

  public static Type d(Type paramType)
  {
    if ((paramType instanceof Class))
    {
      Object localObject = (Class)paramType;
      if (((Class)localObject).isArray())
        localObject = new a(d(((Class)localObject).getComponentType()));
      return localObject;
    }
    if ((paramType instanceof ParameterizedType))
    {
      ParameterizedType localParameterizedType = (ParameterizedType)paramType;
      return new b(localParameterizedType.getOwnerType(), localParameterizedType.getRawType(), localParameterizedType.getActualTypeArguments());
    }
    if ((paramType instanceof GenericArrayType))
      return new a(((GenericArrayType)paramType).getGenericComponentType());
    if ((paramType instanceof WildcardType))
    {
      WildcardType localWildcardType = (WildcardType)paramType;
      return new c(localWildcardType.getUpperBounds(), localWildcardType.getLowerBounds());
    }
    return paramType;
  }

  public static Class<?> e(Type paramType)
  {
    if ((paramType instanceof Class))
      return (Class)paramType;
    if ((paramType instanceof ParameterizedType))
    {
      Type localType = ((ParameterizedType)paramType).getRawType();
      a.a(localType instanceof Class);
      return (Class)localType;
    }
    if ((paramType instanceof GenericArrayType))
      return Array.newInstance(e(((GenericArrayType)paramType).getGenericComponentType()), 0).getClass();
    if ((paramType instanceof TypeVariable))
      return Object.class;
    if ((paramType instanceof WildcardType))
      return e(((WildcardType)paramType).getUpperBounds()[0]);
    String str;
    if (paramType == null)
      str = "null";
    else
      str = paramType.getClass().getName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
    localStringBuilder.append(paramType);
    localStringBuilder.append("> is of type ");
    localStringBuilder.append(str);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public static String f(Type paramType)
  {
    String str;
    if ((paramType instanceof Class))
      str = ((Class)paramType).getName();
    else
      str = paramType.toString();
    return str;
  }

  public static Type g(Type paramType)
  {
    Object localObject;
    if ((paramType instanceof GenericArrayType))
      localObject = ((GenericArrayType)paramType).getGenericComponentType();
    else
      localObject = ((Class)paramType).getComponentType();
    return localObject;
  }

  private static void i(Type paramType)
  {
    boolean bool;
    if (((paramType instanceof Class)) && (((Class)paramType).isPrimitive()))
      bool = false;
    else
      bool = true;
    a.a(bool);
  }

  private static final class a
    implements Serializable, GenericArrayType
  {
    private static final long serialVersionUID;
    private final Type a;

    public a(Type paramType)
    {
      this.a = b.d(paramType);
    }

    public boolean equals(Object paramObject)
    {
      boolean bool;
      if (((paramObject instanceof GenericArrayType)) && (b.a(this, (GenericArrayType)paramObject)))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public Type getGenericComponentType()
    {
      return this.a;
    }

    public int hashCode()
    {
      return this.a.hashCode();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(b.f(this.a));
      localStringBuilder.append("[]");
      return localStringBuilder.toString();
    }
  }

  private static final class b
    implements Serializable, ParameterizedType
  {
    private static final long serialVersionUID;
    private final Type a;
    private final Type b;
    private final Type[] c;

    public b(Type paramType1, Type paramType2, Type[] paramArrayOfType)
    {
      boolean bool1 = paramType2 instanceof Class;
      int i = 0;
      if (bool1)
      {
        Class localClass = (Class)paramType2;
        boolean bool2 = true;
        boolean bool3;
        if ((paramType1 == null) && (localClass.getEnclosingClass() != null))
          bool3 = false;
        else
          bool3 = true;
        a.a(bool3);
        if ((paramType1 != null) && (localClass.getEnclosingClass() == null))
          bool2 = false;
        a.a(bool2);
      }
      Type localType;
      if (paramType1 == null)
        localType = null;
      else
        localType = b.d(paramType1);
      this.a = localType;
      this.b = b.d(paramType2);
      this.c = ((Type[])paramArrayOfType.clone());
      while (i < this.c.length)
      {
        a.a(this.c[i]);
        b.h(this.c[i]);
        this.c[i] = b.d(this.c[i]);
        i++;
      }
    }

    public boolean equals(Object paramObject)
    {
      boolean bool;
      if (((paramObject instanceof ParameterizedType)) && (b.a(this, (ParameterizedType)paramObject)))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public Type[] getActualTypeArguments()
    {
      return (Type[])this.c.clone();
    }

    public Type getOwnerType()
    {
      return this.a;
    }

    public Type getRawType()
    {
      return this.b;
    }

    public int hashCode()
    {
      return Arrays.hashCode(this.c) ^ this.b.hashCode() ^ b.a(this.a);
    }

    public String toString()
    {
      int i = this.c.length;
      int j = 1;
      StringBuilder localStringBuilder = new StringBuilder(30 * (i + j));
      localStringBuilder.append(b.f(this.b));
      if (this.c.length == 0)
        return localStringBuilder.toString();
      localStringBuilder.append("<");
      localStringBuilder.append(b.f(this.c[0]));
      while (j < this.c.length)
      {
        localStringBuilder.append(", ");
        localStringBuilder.append(b.f(this.c[j]));
        j++;
      }
      localStringBuilder.append(">");
      return localStringBuilder.toString();
    }
  }

  private static final class c
    implements Serializable, WildcardType
  {
    private static final long serialVersionUID;
    private final Type a;
    private final Type b;

    public c(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
    {
      int i = paramArrayOfType2.length;
      int j = 1;
      boolean bool1;
      if (i <= j)
        bool1 = true;
      else
        bool1 = false;
      a.a(bool1);
      boolean bool2;
      if (paramArrayOfType1.length == j)
        bool2 = true;
      else
        bool2 = false;
      a.a(bool2);
      if (paramArrayOfType2.length == j)
      {
        a.a(paramArrayOfType2[0]);
        b.h(paramArrayOfType2[0]);
        if (paramArrayOfType1[0] != Object.class)
          j = 0;
        a.a(j);
        this.b = b.d(paramArrayOfType2[0]);
        this.a = Object.class;
      }
      else
      {
        a.a(paramArrayOfType1[0]);
        b.h(paramArrayOfType1[0]);
        this.b = null;
        this.a = b.d(paramArrayOfType1[0]);
      }
    }

    public boolean equals(Object paramObject)
    {
      boolean bool;
      if (((paramObject instanceof WildcardType)) && (b.a(this, (WildcardType)paramObject)))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public Type[] getLowerBounds()
    {
      Type[] arrayOfType;
      if (this.b != null)
      {
        arrayOfType = new Type[1];
        arrayOfType[0] = this.b;
      }
      else
      {
        arrayOfType = b.a;
      }
      return arrayOfType;
    }

    public Type[] getUpperBounds()
    {
      Type[] arrayOfType = new Type[1];
      arrayOfType[0] = this.a;
      return arrayOfType;
    }

    public int hashCode()
    {
      int i;
      if (this.b != null)
        i = 31 + this.b.hashCode();
      else
        i = 1;
      return i ^ 31 + this.a.hashCode();
    }

    public String toString()
    {
      if (this.b != null)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("? super ");
        localStringBuilder1.append(b.f(this.b));
        return localStringBuilder1.toString();
      }
      if (this.a == Object.class)
        return "?";
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("? extends ");
      localStringBuilder2.append(b.f(this.a));
      return localStringBuilder2.toString();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.b
 * JD-Core Version:    0.6.1
 */