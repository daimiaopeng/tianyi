package com.google.a.d;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class d
  implements Closeable
{
  private static final String[] a = new String[''];
  private static final String[] b;
  private final Writer c;
  private final List<b> d = new ArrayList();
  private String e;
  private String f;
  private boolean g;
  private boolean h;
  private String i;
  private boolean j;

  static
  {
    for (int k = 0; k <= 31; k++)
    {
      String[] arrayOfString = a;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(k);
      arrayOfString[k] = String.format("\\u%04x", arrayOfObject);
    }
    a[34] = "\\\"";
    a[92] = "\\\\";
    a[9] = "\\t";
    a[8] = "\\b";
    a[10] = "\\n";
    a[13] = "\\r";
    a[12] = "\\f";
    b = (String[])a.clone();
    b[60] = "\\u003c";
    b[62] = "\\u003e";
    b[38] = "\\u0026";
    b[61] = "\\u003d";
    b[39] = "\\u0027";
  }

  public d(Writer paramWriter)
  {
    this.d.add(b.f);
    this.f = ":";
    this.j = true;
    if (paramWriter == null)
      throw new NullPointerException("out == null");
    this.c = paramWriter;
  }

  private b a()
  {
    int k = this.d.size();
    if (k == 0)
      throw new IllegalStateException("JsonWriter is closed.");
    return (b)this.d.get(k - 1);
  }

  private d a(b paramb1, b paramb2, String paramString)
  {
    b localb = a();
    if ((localb != paramb2) && (localb != paramb1))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Nesting problem: ");
      localStringBuilder2.append(this.d);
      throw new IllegalStateException(localStringBuilder2.toString());
    }
    if (this.i != null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Dangling name: ");
      localStringBuilder1.append(this.i);
      throw new IllegalStateException(localStringBuilder1.toString());
    }
    this.d.remove(-1 + this.d.size());
    if (localb == paramb2)
      k();
    this.c.write(paramString);
    return this;
  }

  private d a(b paramb, String paramString)
  {
    e(true);
    this.d.add(paramb);
    this.c.write(paramString);
    return this;
  }

  private void a(b paramb)
  {
    this.d.set(-1 + this.d.size(), paramb);
  }

  private void d(String paramString)
  {
    String[] arrayOfString;
    if (this.h)
      arrayOfString = b;
    else
      arrayOfString = a;
    this.c.write("\"");
    int k = paramString.length();
    int m = 0;
    int n = 0;
    while (m < k)
    {
      int i1 = paramString.charAt(m);
      String str;
      if (i1 < 128)
      {
        str = arrayOfString[i1];
        if (str == null)
          break label138;
      }
      else if (i1 == 8232)
      {
        str = "\\u2028";
      }
      else
      {
        if (i1 != 8233)
          break label138;
        str = "\\u2029";
      }
      if (n < m)
        this.c.write(paramString, n, m - n);
      this.c.write(str);
      n = m + 1;
      label138: m++;
    }
    if (n < k)
      this.c.write(paramString, n, k - n);
    this.c.write("\"");
  }

  private void e(boolean paramBoolean)
  {
    switch (1.a[a().ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Nesting problem: ");
      localStringBuilder.append(this.d);
      throw new IllegalStateException(localStringBuilder.toString());
    case 5:
      this.c.append(this.f);
      a(b.e);
      break;
    case 4:
      this.c.append(',');
      k();
      break;
    case 3:
      a(b.b);
      k();
      break;
    case 1:
      if (!this.g)
        throw new IllegalStateException("JSON must have only one top-level value.");
      break;
    case 2:
    }
    if ((!this.g) && (!paramBoolean))
      throw new IllegalStateException("JSON must start with an array or an object.");
    a(b.g);
  }

  private void j()
  {
    if (this.i != null)
    {
      l();
      d(this.i);
      this.i = null;
    }
  }

  private void k()
  {
    if (this.e == null)
      return;
    this.c.write("\n");
    for (int k = 1; k < this.d.size(); k++)
      this.c.write(this.e);
  }

  private void l()
  {
    b localb = a();
    if (localb == b.e)
    {
      this.c.write(44);
    }
    else if (localb != b.c)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Nesting problem: ");
      localStringBuilder.append(this.d);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    k();
    a(b.d);
  }

  public d a(long paramLong)
  {
    j();
    e(false);
    this.c.write(Long.toString(paramLong));
    return this;
  }

  public d a(Number paramNumber)
  {
    if (paramNumber == null)
      return f();
    j();
    String str = paramNumber.toString();
    if ((!this.g) && ((str.equals("-Infinity")) || (str.equals("Infinity")) || (str.equals("NaN"))))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Numeric values must be finite, but was ");
      localStringBuilder.append(paramNumber);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    e(false);
    this.c.append(str);
    return this;
  }

  public d a(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("name == null");
    if (this.i != null)
      throw new IllegalStateException();
    if (this.d.isEmpty())
      throw new IllegalStateException("JsonWriter is closed.");
    this.i = paramString;
    return this;
  }

  public d a(boolean paramBoolean)
  {
    j();
    e(false);
    Writer localWriter = this.c;
    String str;
    if (paramBoolean)
      str = "true";
    else
      str = "false";
    localWriter.write(str);
    return this;
  }

  public d b()
  {
    j();
    return a(b.a, "[");
  }

  public d b(String paramString)
  {
    if (paramString == null)
      return f();
    j();
    e(false);
    d(paramString);
    return this;
  }

  public final void b(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  public d c()
  {
    return a(b.a, b.b, "]");
  }

  public final void c(String paramString)
  {
    if (paramString.length() == 0)
    {
      this.e = null;
      this.f = ":";
    }
    else
    {
      this.e = paramString;
      this.f = ": ";
    }
  }

  public final void c(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }

  public void close()
  {
    this.c.close();
    int k = this.d.size();
    if ((k <= 1) && ((k != 1) || (this.d.get(k - 1) == b.g)))
    {
      this.d.clear();
      return;
    }
    throw new IOException("Incomplete document");
  }

  public d d()
  {
    j();
    return a(b.c, "{");
  }

  public final void d(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }

  public d e()
  {
    return a(b.c, b.e, "}");
  }

  public d f()
  {
    if (this.i != null)
      if (this.j)
      {
        j();
      }
      else
      {
        this.i = null;
        return this;
      }
    e(false);
    this.c.write("null");
    return this;
  }

  public boolean g()
  {
    return this.g;
  }

  public final boolean h()
  {
    return this.h;
  }

  public final boolean i()
  {
    return this.j;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.d.d
 * JD-Core Version:    0.6.1
 */