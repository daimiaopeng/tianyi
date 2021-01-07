package com.google.a;

import com.google.a.b.a;
import com.google.a.b.f;
import java.math.BigInteger;

public final class n extends i
{
  private static final Class<?>[] a = arrayOfClass;
  private Object b;

  static
  {
    Class[] arrayOfClass = new Class[16];
    arrayOfClass[0] = Integer.TYPE;
    arrayOfClass[1] = Long.TYPE;
    arrayOfClass[2] = Short.TYPE;
    arrayOfClass[3] = Float.TYPE;
    arrayOfClass[4] = Double.TYPE;
    arrayOfClass[5] = Byte.TYPE;
    arrayOfClass[6] = Boolean.TYPE;
    arrayOfClass[7] = Character.TYPE;
    arrayOfClass[8] = Integer.class;
    arrayOfClass[9] = Long.class;
    arrayOfClass[10] = Short.class;
    arrayOfClass[11] = Float.class;
    arrayOfClass[12] = Double.class;
    arrayOfClass[13] = Byte.class;
    arrayOfClass[14] = Boolean.class;
    arrayOfClass[15] = Character.class;
  }

  public n(Boolean paramBoolean)
  {
    a(paramBoolean);
  }

  public n(Number paramNumber)
  {
    a(paramNumber);
  }

  public n(String paramString)
  {
    a(paramString);
  }

  private static boolean a(n paramn)
  {
    if ((paramn.b instanceof Number))
    {
      Number localNumber = (Number)paramn.b;
      boolean bool1;
      if ((!(localNumber instanceof BigInteger)) && (!(localNumber instanceof Long)) && (!(localNumber instanceof Integer)) && (!(localNumber instanceof Short)))
      {
        boolean bool2 = localNumber instanceof Byte;
        bool1 = false;
        if (!bool2);
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }

  private static boolean b(Object paramObject)
  {
    if ((paramObject instanceof String))
      return true;
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = a;
    int i = arrayOfClass.length;
    for (int j = 0; j < i; j++)
      if (arrayOfClass[j].isAssignableFrom(localClass))
        return true;
    return false;
  }

  public Number a()
  {
    Object localObject;
    if ((this.b instanceof String))
      localObject = new f((String)this.b);
    else
      localObject = (Number)this.b;
    return localObject;
  }

  void a(Object paramObject)
  {
    if ((paramObject instanceof Character))
    {
      this.b = String.valueOf(((Character)paramObject).charValue());
    }
    else
    {
      boolean bool;
      if ((!(paramObject instanceof Number)) && (!b(paramObject)))
        bool = false;
      else
        bool = true;
      a.a(bool);
      this.b = paramObject;
    }
  }

  public String b()
  {
    if (p())
      return a().toString();
    if (o())
      return n().toString();
    return (String)this.b;
  }

  public double c()
  {
    double d;
    if (p())
      d = a().doubleValue();
    else
      d = Double.parseDouble(b());
    return d;
  }

  public long d()
  {
    long l;
    if (p())
      l = a().longValue();
    else
      l = Long.parseLong(b());
    return l;
  }

  public int e()
  {
    int i;
    if (p())
      i = a().intValue();
    else
      i = Integer.parseInt(b());
    return i;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject)
      return bool;
    if ((paramObject != null) && (getClass() == paramObject.getClass()))
    {
      n localn = (n)paramObject;
      if (this.b == null)
      {
        if (localn.b != null)
          bool = false;
        return bool;
      }
      if ((a(this)) && (a(localn)))
      {
        if (a().longValue() != localn.a().longValue())
          bool = false;
        return bool;
      }
      if (((this.b instanceof Number)) && ((localn.b instanceof Number)))
      {
        double d1 = a().doubleValue();
        double d2 = localn.a().doubleValue();
        if ((d1 != d2) && ((!Double.isNaN(d1)) || (!Double.isNaN(d2))))
          bool = false;
        return bool;
      }
      return this.b.equals(localn.b);
    }
    return false;
  }

  public boolean f()
  {
    if (o())
      return n().booleanValue();
    return Boolean.parseBoolean(b());
  }

  public int hashCode()
  {
    if (this.b == null)
      return 31;
    if (a(this))
    {
      long l2 = a().longValue();
      return (int)(l2 ^ l2 >>> 32);
    }
    if ((this.b instanceof Number))
    {
      long l1 = Double.doubleToLongBits(a().doubleValue());
      return (int)(l1 ^ l1 >>> 32);
    }
    return this.b.hashCode();
  }

  Boolean n()
  {
    return (Boolean)this.b;
  }

  public boolean o()
  {
    return this.b instanceof Boolean;
  }

  public boolean p()
  {
    return this.b instanceof Number;
  }

  public boolean q()
  {
    return this.b instanceof String;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.n
 * JD-Core Version:    0.6.1
 */