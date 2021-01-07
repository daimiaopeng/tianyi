package com.google.a.d;

import com.google.a.b.a.d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class a
  implements Closeable
{
  private static final char[] a = ")]}'\n".toCharArray();
  private final f b = new f();
  private final Reader c;
  private boolean d = false;
  private final char[] e = new char[1024];
  private int f = 0;
  private int g = 0;
  private int h = 1;
  private int i = 1;
  private b[] j = new b[32];
  private int k = 0;
  private c l;
  private String m;
  private String n;
  private int o;
  private int p;
  private boolean q;

  static
  {
    com.google.a.b.e.a = new com.google.a.b.e()
    {
      public void a(a paramAnonymousa)
      {
        if ((paramAnonymousa instanceof d))
        {
          ((d)paramAnonymousa).o();
          return;
        }
        paramAnonymousa.f();
        if (a.a(paramAnonymousa) != c.e)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Expected a name but was ");
          localStringBuilder.append(paramAnonymousa.f());
          localStringBuilder.append(" ");
          localStringBuilder.append(" at line ");
          localStringBuilder.append(a.b(paramAnonymousa));
          localStringBuilder.append(" column ");
          localStringBuilder.append(a.c(paramAnonymousa));
          throw new IllegalStateException(localStringBuilder.toString());
        }
        a.a(paramAnonymousa, a.d(paramAnonymousa));
        a.b(paramAnonymousa, null);
        a.a(paramAnonymousa, c.f);
      }
    };
  }

  public a(Reader paramReader)
  {
    a(b.f);
    this.q = false;
    if (paramReader == null)
      throw new NullPointerException("in == null");
    this.c = paramReader;
  }

  private c a(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i1 = paramArrayOfChar[paramInt1];
    int i2;
    if (i1 == 45)
    {
      int i8 = paramInt1 + 1;
      int i9 = paramArrayOfChar[i8];
      i2 = i8;
      i1 = i9;
    }
    else
    {
      i2 = paramInt1;
    }
    int i3;
    int i4;
    if (i1 == 48)
    {
      i3 = i2 + 1;
      i4 = paramArrayOfChar[i3];
    }
    else
    {
      if ((i1 < 49) || (i1 > 57))
        break label274;
      i3 = i2 + 1;
      for (i4 = paramArrayOfChar[i3]; (i4 >= 48) && (i4 <= 57); i4 = paramArrayOfChar[i3])
        i3++;
    }
    if (i4 == 46)
    {
      i3++;
      for (i4 = paramArrayOfChar[i3]; (i4 >= 48) && (i4 <= 57); i4 = paramArrayOfChar[i3])
        i3++;
    }
    if ((i4 == 101) || (i4 == 69))
    {
      int i5 = i3 + 1;
      int i6 = paramArrayOfChar[i5];
      if ((i6 == 43) || (i6 == 45))
      {
        i5++;
        i6 = paramArrayOfChar[i5];
      }
      if ((i6 >= 48) && (i6 <= 57))
      {
        i3 = i5 + 1;
        for (int i7 = paramArrayOfChar[i3]; (i7 >= 48) && (i7 <= 57); i7 = paramArrayOfChar[i3])
          i3++;
      }
    }
    else
    {
      if (i3 == paramInt1 + paramInt2)
        return c.g;
      return c.f;
    }
    return c.f;
    label274: return c.f;
  }

  private String a(char paramChar)
  {
    char[] arrayOfChar = this.e;
    StringBuilder localStringBuilder = null;
    label177: 
    do
    {
      int i1 = this.f;
      int i2 = this.g;
      int i3 = i1;
      while (true)
      {
        if (i1 >= i2)
          break label177;
        int i4 = i1 + 1;
        char c1 = arrayOfChar[i1];
        if (c1 == paramChar)
        {
          this.f = i4;
          if (this.q)
            return "skipped!";
          if (localStringBuilder == null)
            return this.b.a(arrayOfChar, i3, i4 - i3 - 1);
          localStringBuilder.append(arrayOfChar, i3, i4 - i3 - 1);
          return localStringBuilder.toString();
        }
        if (c1 == '\\')
        {
          this.f = i4;
          if (localStringBuilder == null)
            localStringBuilder = new StringBuilder();
          localStringBuilder.append(arrayOfChar, i3, i4 - i3 - 1);
          localStringBuilder.append(x());
          i1 = this.f;
          i2 = this.g;
          break;
        }
        i1 = i4;
      }
      if (localStringBuilder == null)
        localStringBuilder = new StringBuilder();
      localStringBuilder.append(arrayOfChar, i3, i1 - i3);
      this.f = i1;
    }
    while (a(1));
    throw b("Unterminated string");
  }

  private void a(b paramb)
  {
    if (this.k == this.j.length)
    {
      b[] arrayOfb2 = new b[2 * this.k];
      System.arraycopy(this.j, 0, arrayOfb2, 0, this.k);
      this.j = arrayOfb2;
    }
    b[] arrayOfb1 = this.j;
    int i1 = this.k;
    this.k = (i1 + 1);
    arrayOfb1[i1] = paramb;
  }

  private void a(c paramc)
  {
    f();
    if (this.l != paramc)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected ");
      localStringBuilder.append(paramc);
      localStringBuilder.append(" but was ");
      localStringBuilder.append(f());
      localStringBuilder.append(" at line ");
      localStringBuilder.append(t());
      localStringBuilder.append(" column ");
      localStringBuilder.append(u());
      throw new IllegalStateException(localStringBuilder.toString());
    }
    q();
  }

  private boolean a(int paramInt)
  {
    char[] arrayOfChar = this.e;
    int i1 = this.h;
    int i2 = this.i;
    int i3 = this.f;
    int i4 = i2;
    int i5 = i1;
    for (int i6 = 0; i6 < i3; i6++)
      if (arrayOfChar[i6] == '\n')
      {
        i5++;
        i4 = 1;
      }
      else
      {
        i4++;
      }
    this.h = i5;
    this.i = i4;
    if (this.g != this.f)
    {
      this.g -= this.f;
      System.arraycopy(arrayOfChar, this.f, arrayOfChar, 0, this.g);
    }
    else
    {
      this.g = 0;
    }
    this.f = 0;
    do
    {
      int i7 = this.c.read(arrayOfChar, this.g, arrayOfChar.length - this.g);
      if (i7 == -1)
        break;
      this.g = (i7 + this.g);
      if ((this.h == 1) && (this.i == 1) && (this.g > 0) && (arrayOfChar[0] == 65279))
      {
        this.f = (1 + this.f);
        this.i -= 1;
      }
    }
    while (this.g < paramInt);
    return true;
    return false;
  }

  private boolean a(String paramString)
  {
    int i1 = this.f + paramString.length();
    int i2 = this.g;
    int i3 = 0;
    if (i1 > i2)
      if (a(paramString.length()))
        i3 = 0;
      else
        return false;
    while (true)
    {
      if (i3 >= paramString.length())
        break label91;
      if (this.e[(i3 + this.f)] != paramString.charAt(i3))
      {
        this.f = (1 + this.f);
        break;
      }
      i3++;
    }
    label91: return true;
  }

  private c b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.j[(this.k - 1)] = b.b;
    }
    else
    {
      int i1 = d(true);
      if (i1 != 44)
      {
        if (i1 != 59)
        {
          if (i1 != 93)
            throw b("Unterminated array");
          this.k -= 1;
          c localc3 = c.b;
          this.l = localc3;
          return localc3;
        }
        v();
      }
    }
    int i2 = d(true);
    if ((i2 != 44) && (i2 != 59))
    {
      if (i2 != 93)
      {
        this.f -= 1;
        return s();
      }
      if (paramBoolean)
      {
        this.k -= 1;
        c localc2 = c.b;
        this.l = localc2;
        return localc2;
      }
    }
    v();
    this.f -= 1;
    this.n = "null";
    c localc1 = c.i;
    this.l = localc1;
    return localc1;
  }

  private IOException b(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" at line ");
    localStringBuilder.append(t());
    localStringBuilder.append(" column ");
    localStringBuilder.append(u());
    throw new e(localStringBuilder.toString());
  }

  private c c(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (d(true) != 125)
      {
        this.f -= 1;
      }
      else
      {
        this.k -= 1;
        c localc3 = c.d;
        this.l = localc3;
        return localc3;
      }
    }
    else
    {
      int i1 = d(true);
      if ((i1 != 44) && (i1 != 59))
      {
        if (i1 != 125)
          throw b("Unterminated object");
        this.k -= 1;
        c localc2 = c.d;
        this.l = localc2;
        return localc2;
      }
    }
    int i2 = d(true);
    if (i2 != 34)
    {
      if (i2 != 39)
      {
        v();
        this.f -= 1;
        this.m = e(false);
        if (this.m.length() == 0)
          throw b("Expected name");
      }
      else
      {
        v();
      }
    }
    else
      this.m = a((char)i2);
    this.j[(this.k - 1)] = b.d;
    c localc1 = c.e;
    this.l = localc1;
    return localc1;
  }

  private int d(boolean paramBoolean)
  {
    char[] arrayOfChar = this.e;
    int i1 = this.f;
    int i2 = this.g;
    while (true)
    {
      if (i1 == i2)
      {
        this.f = i1;
        if (!a(1))
        {
          if (paramBoolean)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("End of input at line ");
            localStringBuilder.append(t());
            localStringBuilder.append(" column ");
            localStringBuilder.append(u());
            throw new EOFException(localStringBuilder.toString());
          }
          return -1;
        }
        i1 = this.f;
        i2 = this.g;
      }
      int i3 = i1 + 1;
      int i4 = arrayOfChar[i1];
      if ((i4 != 13) && (i4 != 32))
        if (i4 != 35)
          if (i4 == 47);
      switch (i4)
      {
      default:
        this.f = i3;
        return i4;
        this.f = i3;
        if (i3 == i2)
        {
          this.f -= 1;
          boolean bool = a(2);
          this.f = (1 + this.f);
          if (!bool)
            return i4;
        }
        v();
        int i5 = arrayOfChar[this.f];
        if (i5 != 42)
        {
          if (i5 != 47)
            return i4;
          this.f = (1 + this.f);
          w();
          i1 = this.f;
          i2 = this.g;
        }
        else
        {
          this.f = (1 + this.f);
          if (!a("*/"))
            throw b("Unterminated comment");
          i1 = 2 + this.f;
          i2 = this.g;
          continue;
          this.f = i3;
          v();
          w();
          i1 = this.f;
          i2 = this.g;
        }
        break;
      case 9:
      case 10:
        i1 = i3;
      }
    }
  }

  private String e(boolean paramBoolean)
  {
    this.o = -1;
    this.p = 0;
    StringBuilder localStringBuilder = null;
    label221: int i2;
    label227: boolean bool;
    do
    {
      int i1 = 0;
      do
      {
        while (i1 + this.f < this.g)
          switch (this.e[(i1 + this.f)])
          {
          default:
            i1++;
            break;
          case '#':
          case '/':
          case ';':
          case '=':
          case '\\':
            v();
            break label221;
          case '\t':
          case '\n':
          case '\f':
          case '\r':
          case ' ':
          case ',':
          case ':':
          case '[':
          case ']':
          case '{':
          case '}':
          }
        if (i1 >= this.e.length)
          break label227;
      }
      while (a(i1 + 1));
      this.e[this.g] = '\000';
      i2 = i1;
      break;
      if (localStringBuilder == null)
        localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.e, this.f, i1);
      this.p = (i1 + this.p);
      this.f = (i1 + this.f);
      bool = a(1);
      i2 = 0;
    }
    while (bool);
    String str;
    if ((paramBoolean) && (localStringBuilder == null))
    {
      this.o = this.f;
      str = null;
    }
    else if (this.q)
    {
      str = "skipped!";
    }
    else if (localStringBuilder == null)
    {
      str = this.b.a(this.e, this.f, i2);
    }
    else
    {
      localStringBuilder.append(this.e, this.f, i2);
      str = localStringBuilder.toString();
    }
    this.p = (i2 + this.p);
    this.f = (i2 + this.f);
    return str;
  }

  private void o()
  {
    d(true);
    this.f -= 1;
    if ((this.f + a.length > this.g) && (!a(a.length)))
      return;
    for (int i1 = 0; i1 < a.length; i1++)
      if (this.e[(i1 + this.f)] != a[i1])
        return;
    this.f += a.length;
  }

  private c q()
  {
    f();
    c localc = this.l;
    this.l = null;
    this.n = null;
    this.m = null;
    return localc;
  }

  private c r()
  {
    int i1 = d(true);
    if (i1 != 58)
    {
      if (i1 != 61)
        throw b("Expected ':'");
      v();
      if (((this.f < this.g) || (a(1))) && (this.e[this.f] == '>'))
        this.f = (1 + this.f);
    }
    this.j[(this.k - 1)] = b.e;
    return s();
  }

  private c s()
  {
    int i1 = d(true);
    if (i1 != 34)
    {
      if (i1 != 39)
      {
        if (i1 != 91)
        {
          if (i1 != 123)
          {
            this.f -= 1;
            return y();
          }
          a(b.c);
          c localc3 = c.c;
          this.l = localc3;
          return localc3;
        }
        a(b.a);
        c localc2 = c.a;
        this.l = localc2;
        return localc2;
      }
      v();
    }
    this.n = a((char)i1);
    c localc1 = c.f;
    this.l = localc1;
    return localc1;
  }

  private int t()
  {
    int i1 = this.h;
    for (int i2 = 0; i2 < this.f; i2++)
      if (this.e[i2] == '\n')
        i1++;
    return i1;
  }

  private int u()
  {
    int i1 = this.i;
    for (int i2 = 0; i2 < this.f; i2++)
      if (this.e[i2] == '\n')
        i1 = 1;
      else
        i1++;
    return i1;
  }

  private void v()
  {
    if (!this.d)
      throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
  }

  private void w()
  {
    int i2;
    do
    {
      if ((this.f >= this.g) && (!a(1)))
        break;
      char[] arrayOfChar = this.e;
      int i1 = this.f;
      this.f = (i1 + 1);
      i2 = arrayOfChar[i1];
    }
    while ((i2 != 13) && (i2 != 10));
  }

  private char x()
  {
    if ((this.f == this.g) && (!a(1)))
      throw b("Unterminated escape sequence");
    char[] arrayOfChar = this.e;
    int i1 = this.f;
    this.f = (i1 + 1);
    char c1 = arrayOfChar[i1];
    if (c1 != 'b')
    {
      if (c1 != 'f')
      {
        if (c1 != 'n')
        {
          if (c1 != 'r')
          {
            switch (c1)
            {
            default:
              return c1;
            case 'u':
              if ((4 + this.f > this.g) && (!a(4)))
                throw b("Unterminated escape sequence");
              char c2 = '\000';
              int i2 = this.f;
              int i3 = i2 + 4;
              while (i2 < i3)
              {
                int i4 = this.e[i2];
                int i5 = (char)(c2 << '\004');
                if ((i4 >= 48) && (i4 <= 57))
                {
                  c2 = (char)(i5 + (i4 - 48));
                }
                else if ((i4 >= 97) && (i4 <= 102))
                {
                  c2 = (char)(i5 + (10 + (i4 - 97)));
                }
                else
                {
                  if ((i4 < 65) || (i4 > 70))
                    break label258;
                  c2 = (char)(i5 + (10 + (i4 - 65)));
                }
                i2++;
                continue;
                label258: StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("\\u");
                localStringBuilder.append(this.b.a(this.e, this.f, 4));
                throw new NumberFormatException(localStringBuilder.toString());
              }
              this.f = (4 + this.f);
              return c2;
            case 't':
            }
            return '\t';
          }
          return '\r';
        }
        return '\n';
      }
      return '\f';
    }
    return '\b';
  }

  private c y()
  {
    this.n = e(true);
    if (this.p == 0)
      throw b("Expected literal value");
    this.l = z();
    if (this.l == c.f)
      v();
    return this.l;
  }

  private c z()
  {
    if (this.o == -1)
      return c.f;
    if ((this.p == 4) && (('n' == this.e[this.o]) || ('N' == this.e[this.o])) && (('u' == this.e[(1 + this.o)]) || ('U' == this.e[(1 + this.o)])) && (('l' == this.e[(2 + this.o)]) || ('L' == this.e[(2 + this.o)])) && (('l' == this.e[(3 + this.o)]) || ('L' == this.e[(3 + this.o)])))
    {
      this.n = "null";
      return c.i;
    }
    if ((this.p == 4) && (('t' == this.e[this.o]) || ('T' == this.e[this.o])) && (('r' == this.e[(1 + this.o)]) || ('R' == this.e[(1 + this.o)])) && (('u' == this.e[(2 + this.o)]) || ('U' == this.e[(2 + this.o)])) && (('e' == this.e[(3 + this.o)]) || ('E' == this.e[(3 + this.o)])))
    {
      this.n = "true";
      return c.h;
    }
    if ((this.p == 5) && (('f' == this.e[this.o]) || ('F' == this.e[this.o])) && (('a' == this.e[(1 + this.o)]) || ('A' == this.e[(1 + this.o)])) && (('l' == this.e[(2 + this.o)]) || ('L' == this.e[(2 + this.o)])) && (('s' == this.e[(3 + this.o)]) || ('S' == this.e[(3 + this.o)])) && (('e' == this.e[(4 + this.o)]) || ('E' == this.e[(4 + this.o)])))
    {
      this.n = "false";
      return c.h;
    }
    this.n = this.b.a(this.e, this.o, this.p);
    return a(this.e, this.o, this.p);
  }

  public void a()
  {
    a(c.a);
  }

  public final void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  public void b()
  {
    a(c.b);
  }

  public void c()
  {
    a(c.c);
  }

  public void close()
  {
    this.n = null;
    this.l = null;
    this.j[0] = b.h;
    this.k = 1;
    this.c.close();
  }

  public void d()
  {
    a(c.d);
  }

  public boolean e()
  {
    f();
    boolean bool;
    if ((this.l != c.d) && (this.l != c.b))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public c f()
  {
    if (this.l != null)
      return this.l;
    switch (2.a[this.j[(this.k - 1)].ordinal()])
    {
    default:
      throw new AssertionError();
    case 8:
      throw new IllegalStateException("JsonReader is closed");
    case 7:
      if (d(false) == -1)
        return c.j;
      this.f -= 1;
      if (!this.d)
        throw b("Expected EOF");
      return s();
    case 6:
      return c(false);
    case 5:
      return r();
    case 4:
      return c(true);
    case 3:
      return b(false);
    case 2:
      return b(true);
    case 1:
    }
    if (this.d)
      o();
    this.j[(this.k - 1)] = b.g;
    c localc = s();
    if ((!this.d) && (this.l != c.a) && (this.l != c.c))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected JSON document to start with '[' or '{' but was ");
      localStringBuilder.append(this.l);
      localStringBuilder.append(" at line ");
      localStringBuilder.append(t());
      localStringBuilder.append(" column ");
      localStringBuilder.append(u());
      throw new IOException(localStringBuilder.toString());
    }
    return localc;
  }

  public String g()
  {
    f();
    if (this.l != c.e)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected a name but was ");
      localStringBuilder.append(f());
      localStringBuilder.append(" at line ");
      localStringBuilder.append(t());
      localStringBuilder.append(" column ");
      localStringBuilder.append(u());
      throw new IllegalStateException(localStringBuilder.toString());
    }
    String str = this.m;
    q();
    return str;
  }

  public String h()
  {
    f();
    if ((this.l != c.f) && (this.l != c.g))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected a string but was ");
      localStringBuilder.append(f());
      localStringBuilder.append(" at line ");
      localStringBuilder.append(t());
      localStringBuilder.append(" column ");
      localStringBuilder.append(u());
      throw new IllegalStateException(localStringBuilder.toString());
    }
    String str = this.n;
    q();
    return str;
  }

  public boolean i()
  {
    f();
    if (this.l != c.h)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected a boolean but was ");
      localStringBuilder.append(this.l);
      localStringBuilder.append(" at line ");
      localStringBuilder.append(t());
      localStringBuilder.append(" column ");
      localStringBuilder.append(u());
      throw new IllegalStateException(localStringBuilder.toString());
    }
    boolean bool;
    if (this.n == "true")
      bool = true;
    else
      bool = false;
    q();
    return bool;
  }

  public void j()
  {
    f();
    if (this.l != c.i)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected null but was ");
      localStringBuilder.append(this.l);
      localStringBuilder.append(" at line ");
      localStringBuilder.append(t());
      localStringBuilder.append(" column ");
      localStringBuilder.append(u());
      throw new IllegalStateException(localStringBuilder.toString());
    }
    q();
  }

  public double k()
  {
    f();
    if ((this.l != c.f) && (this.l != c.g))
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("Expected a double but was ");
      localStringBuilder3.append(this.l);
      localStringBuilder3.append(" at line ");
      localStringBuilder3.append(t());
      localStringBuilder3.append(" column ");
      localStringBuilder3.append(u());
      throw new IllegalStateException(localStringBuilder3.toString());
    }
    double d1 = Double.parseDouble(this.n);
    if ((d1 >= 1.0D) && (this.n.startsWith("0")))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("JSON forbids octal prefixes: ");
      localStringBuilder2.append(this.n);
      localStringBuilder2.append(" at line ");
      localStringBuilder2.append(t());
      localStringBuilder2.append(" column ");
      localStringBuilder2.append(u());
      throw new e(localStringBuilder2.toString());
    }
    if ((!this.d) && ((Double.isNaN(d1)) || (Double.isInfinite(d1))))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("JSON forbids NaN and infinities: ");
      localStringBuilder1.append(this.n);
      localStringBuilder1.append(" at line ");
      localStringBuilder1.append(t());
      localStringBuilder1.append(" column ");
      localStringBuilder1.append(u());
      throw new e(localStringBuilder1.toString());
    }
    q();
    return d1;
  }

  // ERROR //
  public long l()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 151	com/google/a/d/a:f	()Lcom/google/a/d/c;
    //   4: pop
    //   5: aload_0
    //   6: getfield 98	com/google/a/d/a:l	Lcom/google/a/d/c;
    //   9: getstatic 106	com/google/a/d/c:f	Lcom/google/a/d/c;
    //   12: if_acmpeq +90 -> 102
    //   15: aload_0
    //   16: getfield 98	com/google/a/d/a:l	Lcom/google/a/d/c;
    //   19: getstatic 104	com/google/a/d/c:g	Lcom/google/a/d/c;
    //   22: if_acmpeq +80 -> 102
    //   25: new 114	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   32: astore 23
    //   34: aload 23
    //   36: ldc_w 370
    //   39: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload 23
    //   45: aload_0
    //   46: getfield 98	com/google/a/d/a:l	Lcom/google/a/d/c;
    //   49: invokevirtual 159	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload 23
    //   55: ldc 163
    //   57: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload 23
    //   63: aload_0
    //   64: invokespecial 167	com/google/a/d/a:t	()I
    //   67: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload 23
    //   73: ldc 172
    //   75: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload 23
    //   81: aload_0
    //   82: invokespecial 175	com/google/a/d/a:u	()I
    //   85: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: new 177	java/lang/IllegalStateException
    //   92: dup
    //   93: aload 23
    //   95: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokespecial 178	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   101: athrow
    //   102: aload_0
    //   103: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   106: invokestatic 376	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   109: lstore 13
    //   111: goto +104 -> 215
    //   114: aload_0
    //   115: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   118: invokestatic 351	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   121: dstore_2
    //   122: dload_2
    //   123: d2l
    //   124: lstore 4
    //   126: lload 4
    //   128: l2d
    //   129: dload_2
    //   130: dcmpl
    //   131: ifeq +80 -> 211
    //   134: new 114	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   141: astore 6
    //   143: aload 6
    //   145: ldc_w 370
    //   148: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload 6
    //   154: aload_0
    //   155: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   158: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aload 6
    //   164: ldc 163
    //   166: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload 6
    //   172: aload_0
    //   173: invokespecial 167	com/google/a/d/a:t	()I
    //   176: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: aload 6
    //   182: ldc 172
    //   184: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: aload 6
    //   190: aload_0
    //   191: invokespecial 175	com/google/a/d/a:u	()I
    //   194: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: new 279	java/lang/NumberFormatException
    //   201: dup
    //   202: aload 6
    //   204: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokespecial 280	java/lang/NumberFormatException:<init>	(Ljava/lang/String;)V
    //   210: athrow
    //   211: lload 4
    //   213: lstore 13
    //   215: lload 13
    //   217: lconst_1
    //   218: lcmp
    //   219: iflt +93 -> 312
    //   222: aload_0
    //   223: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   226: ldc_w 353
    //   229: invokevirtual 356	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   232: ifeq +80 -> 312
    //   235: new 114	java/lang/StringBuilder
    //   238: dup
    //   239: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   242: astore 16
    //   244: aload 16
    //   246: ldc_w 358
    //   249: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: pop
    //   253: aload 16
    //   255: aload_0
    //   256: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   259: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: aload 16
    //   265: ldc 163
    //   267: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: pop
    //   271: aload 16
    //   273: aload_0
    //   274: invokespecial 167	com/google/a/d/a:t	()I
    //   277: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   280: pop
    //   281: aload 16
    //   283: ldc 172
    //   285: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: pop
    //   289: aload 16
    //   291: aload_0
    //   292: invokespecial 175	com/google/a/d/a:u	()I
    //   295: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   298: pop
    //   299: new 218	com/google/a/d/e
    //   302: dup
    //   303: aload 16
    //   305: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   308: invokespecial 219	com/google/a/d/e:<init>	(Ljava/lang/String;)V
    //   311: athrow
    //   312: aload_0
    //   313: invokespecial 180	com/google/a/d/a:q	()Lcom/google/a/d/c;
    //   316: pop
    //   317: lload 13
    //   319: lreturn
    //
    // Exception table:
    //   from	to	target	type
    //   102	111	114	java/lang/NumberFormatException
  }

  // ERROR //
  public int m()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 151	com/google/a/d/a:f	()Lcom/google/a/d/c;
    //   4: pop
    //   5: aload_0
    //   6: getfield 98	com/google/a/d/a:l	Lcom/google/a/d/c;
    //   9: getstatic 106	com/google/a/d/c:f	Lcom/google/a/d/c;
    //   12: if_acmpeq +90 -> 102
    //   15: aload_0
    //   16: getfield 98	com/google/a/d/a:l	Lcom/google/a/d/c;
    //   19: getstatic 104	com/google/a/d/c:g	Lcom/google/a/d/c;
    //   22: if_acmpeq +80 -> 102
    //   25: new 114	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   32: astore 21
    //   34: aload 21
    //   36: ldc_w 378
    //   39: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload 21
    //   45: aload_0
    //   46: getfield 98	com/google/a/d/a:l	Lcom/google/a/d/c;
    //   49: invokevirtual 159	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload 21
    //   55: ldc 163
    //   57: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload 21
    //   63: aload_0
    //   64: invokespecial 167	com/google/a/d/a:t	()I
    //   67: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload 21
    //   73: ldc 172
    //   75: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload 21
    //   81: aload_0
    //   82: invokespecial 175	com/google/a/d/a:u	()I
    //   85: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: new 177	java/lang/IllegalStateException
    //   92: dup
    //   93: aload 21
    //   95: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokespecial 178	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   101: athrow
    //   102: aload_0
    //   103: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   106: invokestatic 384	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   109: istore 12
    //   111: goto +104 -> 215
    //   114: aload_0
    //   115: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   118: invokestatic 351	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   121: dstore_2
    //   122: dload_2
    //   123: d2i
    //   124: istore 4
    //   126: iload 4
    //   128: i2d
    //   129: dload_2
    //   130: dcmpl
    //   131: ifeq +80 -> 211
    //   134: new 114	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   141: astore 5
    //   143: aload 5
    //   145: ldc_w 378
    //   148: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload 5
    //   154: aload_0
    //   155: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   158: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aload 5
    //   164: ldc 163
    //   166: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload 5
    //   172: aload_0
    //   173: invokespecial 167	com/google/a/d/a:t	()I
    //   176: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: aload 5
    //   182: ldc 172
    //   184: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: aload 5
    //   190: aload_0
    //   191: invokespecial 175	com/google/a/d/a:u	()I
    //   194: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: new 279	java/lang/NumberFormatException
    //   201: dup
    //   202: aload 5
    //   204: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokespecial 280	java/lang/NumberFormatException:<init>	(Ljava/lang/String;)V
    //   210: athrow
    //   211: iload 4
    //   213: istore 12
    //   215: iload 12
    //   217: i2l
    //   218: lconst_1
    //   219: lcmp
    //   220: iflt +93 -> 313
    //   223: aload_0
    //   224: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   227: ldc_w 353
    //   230: invokevirtual 356	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   233: ifeq +80 -> 313
    //   236: new 114	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   243: astore 14
    //   245: aload 14
    //   247: ldc_w 358
    //   250: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: aload 14
    //   256: aload_0
    //   257: getfield 141	com/google/a/d/a:n	Ljava/lang/String;
    //   260: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: aload 14
    //   266: ldc 163
    //   268: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: aload 14
    //   274: aload_0
    //   275: invokespecial 167	com/google/a/d/a:t	()I
    //   278: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   281: pop
    //   282: aload 14
    //   284: ldc 172
    //   286: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: pop
    //   290: aload 14
    //   292: aload_0
    //   293: invokespecial 175	com/google/a/d/a:u	()I
    //   296: invokevirtual 170	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: pop
    //   300: new 218	com/google/a/d/e
    //   303: dup
    //   304: aload 14
    //   306: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: invokespecial 219	com/google/a/d/e:<init>	(Ljava/lang/String;)V
    //   312: athrow
    //   313: aload_0
    //   314: invokespecial 180	com/google/a/d/a:q	()Lcom/google/a/d/c;
    //   317: pop
    //   318: iload 12
    //   320: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   102	111	114	java/lang/NumberFormatException
  }

  public void n()
  {
    this.q = true;
    int i1 = 0;
    try
    {
      do
      {
        c localc1 = q();
        if ((localc1 != c.a) && (localc1 != c.c))
        {
          if (localc1 != c.b)
          {
            c localc2 = c.d;
            if (localc1 != localc2);
          }
          else
          {
            i1--;
          }
        }
        else
          i1++;
      }
      while (i1 != 0);
      return;
    }
    finally
    {
      this.q = false;
    }
  }

  public final boolean p()
  {
    return this.d;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append(" at line ");
    localStringBuilder.append(t());
    localStringBuilder.append(" column ");
    localStringBuilder.append(u());
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.d.a
 * JD-Core Version:    0.6.1
 */