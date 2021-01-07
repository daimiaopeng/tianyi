package com.google.a;

import com.google.a.d.d;
import java.io.IOException;
import java.io.StringWriter;

public abstract class i
{
  public Number a()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public String b()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public double c()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public long d()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public int e()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public boolean f()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public boolean g()
  {
    return this instanceof g;
  }

  public boolean h()
  {
    return this instanceof l;
  }

  public boolean i()
  {
    return this instanceof n;
  }

  public boolean j()
  {
    return this instanceof k;
  }

  public l k()
  {
    if (h())
      return (l)this;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Not a JSON Object: ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }

  public g l()
  {
    if (g())
      return (g)this;
    throw new IllegalStateException("This is not a JSON Array.");
  }

  public n m()
  {
    if (i())
      return (n)this;
    throw new IllegalStateException("This is not a JSON Primitive.");
  }

  Boolean n()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public String toString()
  {
    try
    {
      StringWriter localStringWriter = new StringWriter();
      d locald = new d(localStringWriter);
      locald.b(true);
      com.google.a.b.i.a(this, locald);
      String str = localStringWriter.toString();
      return str;
    }
    catch (IOException localIOException)
    {
      throw new AssertionError(localIOException);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.i
 * JD-Core Version:    0.6.1
 */