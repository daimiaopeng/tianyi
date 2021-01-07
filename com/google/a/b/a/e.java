package com.google.a.b.a;

import com.google.a.d.d;
import com.google.a.g;
import com.google.a.i;
import com.google.a.k;
import com.google.a.l;
import com.google.a.n;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class e extends d
{
  private static final Writer a = new Writer()
  {
    public void close()
    {
      throw new AssertionError();
    }

    public void flush()
    {
      throw new AssertionError();
    }

    public void write(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new AssertionError();
    }
  };
  private static final n b = new n("closed");
  private final List<i> c = new ArrayList();
  private String d;
  private i e = k.a;

  public e()
  {
    super(a);
  }

  private void a(i parami)
  {
    if (this.d != null)
    {
      if ((!parami.j()) || (i()))
        ((l)j()).a(this.d, parami);
      this.d = null;
    }
    else if (this.c.isEmpty())
    {
      this.e = parami;
    }
    else
    {
      i locali = j();
      if (!(locali instanceof g))
        break label85;
      ((g)locali).a(parami);
    }
    return;
    label85: throw new IllegalStateException();
  }

  private i j()
  {
    return (i)this.c.get(-1 + this.c.size());
  }

  public d a(long paramLong)
  {
    a(new n(Long.valueOf(paramLong)));
    return this;
  }

  public d a(Number paramNumber)
  {
    if (paramNumber == null)
      return f();
    if (!g())
    {
      double d1 = paramNumber.doubleValue();
      if ((Double.isNaN(d1)) || (Double.isInfinite(d1)))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("JSON forbids NaN and infinities: ");
        localStringBuilder.append(paramNumber);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
    }
    a(new n(paramNumber));
    return this;
  }

  public d a(String paramString)
  {
    if ((!this.c.isEmpty()) && (this.d == null))
    {
      if ((j() instanceof l))
      {
        this.d = paramString;
        return this;
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }

  public d a(boolean paramBoolean)
  {
    a(new n(Boolean.valueOf(paramBoolean)));
    return this;
  }

  public i a()
  {
    if (!this.c.isEmpty())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected one JSON element but was ");
      localStringBuilder.append(this.c);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return this.e;
  }

  public d b()
  {
    g localg = new g();
    a(localg);
    this.c.add(localg);
    return this;
  }

  public d b(String paramString)
  {
    if (paramString == null)
      return f();
    a(new n(paramString));
    return this;
  }

  public d c()
  {
    if ((!this.c.isEmpty()) && (this.d == null))
    {
      if ((j() instanceof g))
      {
        this.c.remove(-1 + this.c.size());
        return this;
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }

  public void close()
  {
    if (!this.c.isEmpty())
      throw new IOException("Incomplete document");
    this.c.add(b);
  }

  public d d()
  {
    l locall = new l();
    a(locall);
    this.c.add(locall);
    return this;
  }

  public d e()
  {
    if ((!this.c.isEmpty()) && (this.d == null))
    {
      if ((j() instanceof l))
      {
        this.c.remove(-1 + this.c.size());
        return this;
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }

  public d f()
  {
    a(k.a);
    return this;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.e
 * JD-Core Version:    0.6.1
 */