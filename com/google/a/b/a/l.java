package com.google.a.b.a;

import com.google.a.b.f;
import com.google.a.d.c;
import com.google.a.d.d;
import com.google.a.e;
import com.google.a.g;
import com.google.a.i;
import com.google.a.j;
import com.google.a.k;
import com.google.a.n;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public final class l
{
  public static final r<StringBuffer> A = new r()
  {
    public StringBuffer a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return new StringBuffer(paramAnonymousa.h());
    }

    public void a(d paramAnonymousd, StringBuffer paramAnonymousStringBuffer)
    {
      String str;
      if (paramAnonymousStringBuffer == null)
        str = null;
      else
        str = paramAnonymousStringBuffer.toString();
      paramAnonymousd.b(str);
    }
  };
  public static final s B = a(StringBuffer.class, A);
  public static final r<URL> C = new r()
  {
    public URL a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      String str = paramAnonymousa.h();
      URL localURL;
      if ("null".equals(str))
        localURL = null;
      else
        localURL = new URL(str);
      return localURL;
    }

    public void a(d paramAnonymousd, URL paramAnonymousURL)
    {
      String str;
      if (paramAnonymousURL == null)
        str = null;
      else
        str = paramAnonymousURL.toExternalForm();
      paramAnonymousd.b(str);
    }
  };
  public static final s D = a(URL.class, C);
  public static final r<URI> E = new r()
  {
    public URI a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        String str = paramAnonymousa.h();
        URI localURI;
        if ("null".equals(str))
          localURI = null;
        else
          localURI = new URI(str);
        return localURI;
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new j(localURISyntaxException);
      }
    }

    public void a(d paramAnonymousd, URI paramAnonymousURI)
    {
      String str;
      if (paramAnonymousURI == null)
        str = null;
      else
        str = paramAnonymousURI.toASCIIString();
      paramAnonymousd.b(str);
    }
  };
  public static final s F = a(URI.class, E);
  public static final r<InetAddress> G = new r()
  {
    public InetAddress a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return InetAddress.getByName(paramAnonymousa.h());
    }

    public void a(d paramAnonymousd, InetAddress paramAnonymousInetAddress)
    {
      String str;
      if (paramAnonymousInetAddress == null)
        str = null;
      else
        str = paramAnonymousInetAddress.getHostAddress();
      paramAnonymousd.b(str);
    }
  };
  public static final s H = b(InetAddress.class, G);
  public static final r<UUID> I = new r()
  {
    public UUID a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return UUID.fromString(paramAnonymousa.h());
    }

    public void a(d paramAnonymousd, UUID paramAnonymousUUID)
    {
      String str;
      if (paramAnonymousUUID == null)
        str = null;
      else
        str = paramAnonymousUUID.toString();
      paramAnonymousd.b(str);
    }
  };
  public static final s J = a(UUID.class, I);
  public static final s K = new s()
  {
    public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
    {
      if (paramAnonymousa.a() != Timestamp.class)
        return null;
      return new r()
      {
        public Timestamp a(com.google.a.d.a paramAnonymous2a)
        {
          Date localDate = (Date)this.a.b(paramAnonymous2a);
          Timestamp localTimestamp;
          if (localDate != null)
            localTimestamp = new Timestamp(localDate.getTime());
          else
            localTimestamp = null;
          return localTimestamp;
        }

        public void a(d paramAnonymous2d, Timestamp paramAnonymous2Timestamp)
        {
          this.a.a(paramAnonymous2d, paramAnonymous2Timestamp);
        }
      };
    }
  };
  public static final r<Calendar> L = new r()
  {
    public Calendar a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      paramAnonymousa.c();
      int i = 0;
      int j = 0;
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      while (paramAnonymousa.f() != c.d)
      {
        String str = paramAnonymousa.g();
        int i2 = paramAnonymousa.m();
        if ("year".equals(str))
          i = i2;
        else if ("month".equals(str))
          j = i2;
        else if ("dayOfMonth".equals(str))
          k = i2;
        else if ("hourOfDay".equals(str))
          m = i2;
        else if ("minute".equals(str))
          n = i2;
        else if ("second".equals(str))
          i1 = i2;
      }
      paramAnonymousa.d();
      GregorianCalendar localGregorianCalendar = new GregorianCalendar(i, j, k, m, n, i1);
      return localGregorianCalendar;
    }

    public void a(d paramAnonymousd, Calendar paramAnonymousCalendar)
    {
      if (paramAnonymousCalendar == null)
      {
        paramAnonymousd.f();
        return;
      }
      paramAnonymousd.d();
      paramAnonymousd.a("year");
      paramAnonymousd.a(paramAnonymousCalendar.get(1));
      paramAnonymousd.a("month");
      paramAnonymousd.a(paramAnonymousCalendar.get(2));
      paramAnonymousd.a("dayOfMonth");
      paramAnonymousd.a(paramAnonymousCalendar.get(5));
      paramAnonymousd.a("hourOfDay");
      paramAnonymousd.a(paramAnonymousCalendar.get(11));
      paramAnonymousd.a("minute");
      paramAnonymousd.a(paramAnonymousCalendar.get(12));
      paramAnonymousd.a("second");
      paramAnonymousd.a(paramAnonymousCalendar.get(13));
      paramAnonymousd.e();
    }
  };
  public static final s M = b(Calendar.class, GregorianCalendar.class, L);
  public static final r<Locale> N = new r()
  {
    public Locale a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      StringTokenizer localStringTokenizer = new StringTokenizer(paramAnonymousa.h(), "_");
      String str1;
      if (localStringTokenizer.hasMoreElements())
        str1 = localStringTokenizer.nextToken();
      else
        str1 = null;
      String str2;
      if (localStringTokenizer.hasMoreElements())
        str2 = localStringTokenizer.nextToken();
      else
        str2 = null;
      boolean bool = localStringTokenizer.hasMoreElements();
      String str3 = null;
      if (bool)
        str3 = localStringTokenizer.nextToken();
      if ((str2 == null) && (str3 == null))
        return new Locale(str1);
      if (str3 == null)
        return new Locale(str1, str2);
      return new Locale(str1, str2, str3);
    }

    public void a(d paramAnonymousd, Locale paramAnonymousLocale)
    {
      String str;
      if (paramAnonymousLocale == null)
        str = null;
      else
        str = paramAnonymousLocale.toString();
      paramAnonymousd.b(str);
    }
  };
  public static final s O = a(Locale.class, N);
  public static final r<i> P = new r()
  {
    public i a(com.google.a.d.a paramAnonymousa)
    {
      switch (l.25.a[paramAnonymousa.f().ordinal()])
      {
      default:
        throw new IllegalArgumentException();
      case 6:
        com.google.a.l locall = new com.google.a.l();
        paramAnonymousa.c();
        while (paramAnonymousa.e())
          locall.a(paramAnonymousa.g(), a(paramAnonymousa));
        paramAnonymousa.d();
        return locall;
      case 5:
        g localg = new g();
        paramAnonymousa.a();
        while (paramAnonymousa.e())
          localg.a(a(paramAnonymousa));
        paramAnonymousa.b();
        return localg;
      case 4:
        paramAnonymousa.j();
        return k.a;
      case 3:
        return new n(paramAnonymousa.h());
      case 2:
        return new n(Boolean.valueOf(paramAnonymousa.i()));
      case 1:
      }
      return new n(new f(paramAnonymousa.h()));
    }

    public void a(d paramAnonymousd, i paramAnonymousi)
    {
      if ((paramAnonymousi != null) && (!paramAnonymousi.j()))
      {
        if (paramAnonymousi.i())
        {
          n localn = paramAnonymousi.m();
          if (localn.p())
            paramAnonymousd.a(localn.a());
          else if (localn.o())
            paramAnonymousd.a(localn.f());
          else
            paramAnonymousd.b(localn.b());
        }
        else if (paramAnonymousi.g())
        {
          paramAnonymousd.b();
          Iterator localIterator2 = paramAnonymousi.l().iterator();
          while (localIterator2.hasNext())
            a(paramAnonymousd, (i)localIterator2.next());
          paramAnonymousd.c();
        }
        else if (paramAnonymousi.h())
        {
          paramAnonymousd.d();
          Iterator localIterator1 = paramAnonymousi.k().o().iterator();
          while (localIterator1.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator1.next();
            paramAnonymousd.a((String)localEntry.getKey());
            a(paramAnonymousd, (i)localEntry.getValue());
          }
          paramAnonymousd.e();
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Couldn't write ");
          localStringBuilder.append(paramAnonymousi.getClass());
          throw new IllegalArgumentException(localStringBuilder.toString());
        }
      }
      else
        paramAnonymousd.f();
    }
  };
  public static final s Q = a(i.class, P);
  public static final s R = a();
  public static final r<Class> a = new r()
  {
    public Class a(com.google.a.d.a paramAnonymousa)
    {
      throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }

    public void a(d paramAnonymousd, Class paramAnonymousClass)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Attempted to serialize java.lang.Class: ");
      localStringBuilder.append(paramAnonymousClass.getName());
      localStringBuilder.append(". Forgot to register a type adapter?");
      throw new UnsupportedOperationException(localStringBuilder.toString());
    }
  };
  public static final s b = a(Class.class, a);
  public static final r<BitSet> c = new r()
  {
    public BitSet a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      BitSet localBitSet = new BitSet();
      paramAnonymousa.a();
      c localc = paramAnonymousa.f();
      int i = 0;
      while (localc != c.b)
      {
        int j = l.25.a[localc.ordinal()];
        boolean bool = true;
        String str;
        switch (j)
        {
        default:
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("Invalid bitset value type: ");
          localStringBuilder2.append(localc);
          throw new p(localStringBuilder2.toString());
        case 3:
          str = paramAnonymousa.h();
        case 2:
        case 1:
        }
        do
        {
          try
          {
            int k = Integer.parseInt(str);
            if (k != 0)
              break;
            bool = false;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("Error: Expecting: bitset number value (1, 0), Found: ");
            localStringBuilder1.append(str);
            throw new p(localStringBuilder1.toString());
            bool = paramAnonymousa.i();
            tmpTernaryOp = localNumberFormatException;
          }
          StringBuilder localStringBuilder1;
          break;
        }
        while (paramAnonymousa.m() == 0);
        if (bool)
          localBitSet.set(i);
        i++;
        localc = paramAnonymousa.f();
      }
      paramAnonymousa.b();
      return localBitSet;
    }

    public void a(d paramAnonymousd, BitSet paramAnonymousBitSet)
    {
      if (paramAnonymousBitSet == null)
      {
        paramAnonymousd.f();
        return;
      }
      paramAnonymousd.b();
      for (int i = 0; i < paramAnonymousBitSet.length(); i++)
        paramAnonymousd.a(paramAnonymousBitSet.get(i));
      paramAnonymousd.c();
    }
  };
  public static final s d = a(BitSet.class, c);
  public static final r<Boolean> e = new r()
  {
    public Boolean a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      if (paramAnonymousa.f() == c.f)
        return Boolean.valueOf(Boolean.parseBoolean(paramAnonymousa.h()));
      return Boolean.valueOf(paramAnonymousa.i());
    }

    public void a(d paramAnonymousd, Boolean paramAnonymousBoolean)
    {
      if (paramAnonymousBoolean == null)
      {
        paramAnonymousd.f();
        return;
      }
      paramAnonymousd.a(paramAnonymousBoolean.booleanValue());
    }
  };
  public static final r<Boolean> f = new r()
  {
    public Boolean a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return Boolean.valueOf(paramAnonymousa.h());
    }

    public void a(d paramAnonymousd, Boolean paramAnonymousBoolean)
    {
      String str;
      if (paramAnonymousBoolean == null)
        str = "null";
      else
        str = paramAnonymousBoolean.toString();
      paramAnonymousd.b(str);
    }
  };
  public static final s g = a(Boolean.TYPE, Boolean.class, e);
  public static final r<Number> h = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Byte localByte = Byte.valueOf((byte)paramAnonymousa.m());
        return localByte;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new p(localNumberFormatException);
      }
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final s i = a(Byte.TYPE, Byte.class, h);
  public static final r<Number> j = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Short localShort = Short.valueOf((short)paramAnonymousa.m());
        return localShort;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new p(localNumberFormatException);
      }
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final s k = a(Short.TYPE, Short.class, j);
  public static final r<Number> l = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Integer localInteger = Integer.valueOf(paramAnonymousa.m());
        return localInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new p(localNumberFormatException);
      }
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final s m = a(Integer.TYPE, Integer.class, l);
  public static final r<Number> n = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Long localLong = Long.valueOf(paramAnonymousa.l());
        return localLong;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new p(localNumberFormatException);
      }
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final r<Number> o = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return Float.valueOf((float)paramAnonymousa.k());
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final r<Number> p = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return Double.valueOf(paramAnonymousa.k());
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final r<Number> q = new r()
  {
    public Number a(com.google.a.d.a paramAnonymousa)
    {
      c localc = paramAnonymousa.f();
      int i = l.25.a[localc.ordinal()];
      if (i != 1)
      {
        if (i != 4)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Expecting number, got: ");
          localStringBuilder.append(localc);
          throw new p(localStringBuilder.toString());
        }
        paramAnonymousa.j();
        return null;
      }
      return new f(paramAnonymousa.h());
    }

    public void a(d paramAnonymousd, Number paramAnonymousNumber)
    {
      paramAnonymousd.a(paramAnonymousNumber);
    }
  };
  public static final s r = a(Number.class, q);
  public static final r<Character> s = new r()
  {
    public Character a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      String str = paramAnonymousa.h();
      if (str.length() != 1)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Expecting character, got: ");
        localStringBuilder.append(str);
        throw new p(localStringBuilder.toString());
      }
      return Character.valueOf(str.charAt(0));
    }

    public void a(d paramAnonymousd, Character paramAnonymousCharacter)
    {
      String str;
      if (paramAnonymousCharacter == null)
        str = null;
      else
        str = String.valueOf(paramAnonymousCharacter);
      paramAnonymousd.b(str);
    }
  };
  public static final s t = a(Character.TYPE, Character.class, s);
  public static final r<String> u = new r()
  {
    public String a(com.google.a.d.a paramAnonymousa)
    {
      c localc = paramAnonymousa.f();
      if (localc == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      if (localc == c.h)
        return Boolean.toString(paramAnonymousa.i());
      return paramAnonymousa.h();
    }

    public void a(d paramAnonymousd, String paramAnonymousString)
    {
      paramAnonymousd.b(paramAnonymousString);
    }
  };
  public static final r<BigDecimal> v = new r()
  {
    public BigDecimal a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        BigDecimal localBigDecimal = new BigDecimal(paramAnonymousa.h());
        return localBigDecimal;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new p(localNumberFormatException);
      }
    }

    public void a(d paramAnonymousd, BigDecimal paramAnonymousBigDecimal)
    {
      paramAnonymousd.a(paramAnonymousBigDecimal);
    }
  };
  public static final r<BigInteger> w = new r()
  {
    public BigInteger a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        BigInteger localBigInteger = new BigInteger(paramAnonymousa.h());
        return localBigInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new p(localNumberFormatException);
      }
    }

    public void a(d paramAnonymousd, BigInteger paramAnonymousBigInteger)
    {
      paramAnonymousd.a(paramAnonymousBigInteger);
    }
  };
  public static final s x = a(String.class, u);
  public static final r<StringBuilder> y = new r()
  {
    public StringBuilder a(com.google.a.d.a paramAnonymousa)
    {
      if (paramAnonymousa.f() == c.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return new StringBuilder(paramAnonymousa.h());
    }

    public void a(d paramAnonymousd, StringBuilder paramAnonymousStringBuilder)
    {
      String str;
      if (paramAnonymousStringBuilder == null)
        str = null;
      else
        str = paramAnonymousStringBuilder.toString();
      paramAnonymousd.b(str);
    }
  };
  public static final s z = a(StringBuilder.class, y);

  public static s a()
  {
    return new s()
    {
      public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
      {
        Class localClass = paramAnonymousa.a();
        if ((Enum.class.isAssignableFrom(localClass)) && (localClass != Enum.class))
        {
          if (!localClass.isEnum())
            localClass = localClass.getSuperclass();
          return new l.a(localClass);
        }
        return null;
      }
    };
  }

  public static <TT> s a(Class<TT> paramClass, final r<TT> paramr)
  {
    return new s()
    {
      public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
      {
        r localr;
        if (paramAnonymousa.a() == this.a)
          localr = paramr;
        else
          localr = null;
        return localr;
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Factory[type=");
        localStringBuilder.append(this.a.getName());
        localStringBuilder.append(",adapter=");
        localStringBuilder.append(paramr);
        localStringBuilder.append("]");
        return localStringBuilder.toString();
      }
    };
  }

  public static <TT> s a(Class<TT> paramClass1, final Class<TT> paramClass2, final r<? super TT> paramr)
  {
    return new s()
    {
      public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
      {
        Class localClass = paramAnonymousa.a();
        r localr;
        if ((localClass != this.a) && (localClass != paramClass2))
          localr = null;
        else
          localr = paramr;
        return localr;
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Factory[type=");
        localStringBuilder.append(paramClass2.getName());
        localStringBuilder.append("+");
        localStringBuilder.append(this.a.getName());
        localStringBuilder.append(",adapter=");
        localStringBuilder.append(paramr);
        localStringBuilder.append("]");
        return localStringBuilder.toString();
      }
    };
  }

  public static <TT> s b(Class<TT> paramClass, final r<TT> paramr)
  {
    return new s()
    {
      public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
      {
        r localr;
        if (this.a.isAssignableFrom(paramAnonymousa.a()))
          localr = paramr;
        else
          localr = null;
        return localr;
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Factory[typeHierarchy=");
        localStringBuilder.append(this.a.getName());
        localStringBuilder.append(",adapter=");
        localStringBuilder.append(paramr);
        localStringBuilder.append("]");
        return localStringBuilder.toString();
      }
    };
  }

  public static <TT> s b(Class<TT> paramClass, final Class<? extends TT> paramClass1, final r<? super TT> paramr)
  {
    return new s()
    {
      public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
      {
        Class localClass = paramAnonymousa.a();
        r localr;
        if ((localClass != this.a) && (localClass != paramClass1))
          localr = null;
        else
          localr = paramr;
        return localr;
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Factory[type=");
        localStringBuilder.append(this.a.getName());
        localStringBuilder.append("+");
        localStringBuilder.append(paramClass1.getName());
        localStringBuilder.append(",adapter=");
        localStringBuilder.append(paramr);
        localStringBuilder.append("]");
        return localStringBuilder.toString();
      }
    };
  }

  private static final class a<T extends Enum<T>> extends r<T>
  {
    private final Map<String, T> a;
    private final Map<T, String> b;

    // ERROR //
    public a(Class<T> paramClass)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 17	com/google/a/r:<init>	()V
      //   4: aload_0
      //   5: new 19	java/util/HashMap
      //   8: dup
      //   9: invokespecial 20	java/util/HashMap:<init>	()V
      //   12: putfield 22	com/google/a/b/a/l$a:a	Ljava/util/Map;
      //   15: aload_0
      //   16: new 19	java/util/HashMap
      //   19: dup
      //   20: invokespecial 20	java/util/HashMap:<init>	()V
      //   23: putfield 24	com/google/a/b/a/l$a:b	Ljava/util/Map;
      //   26: aload_1
      //   27: invokevirtual 30	java/lang/Class:getEnumConstants	()[Ljava/lang/Object;
      //   30: checkcast 32	[Ljava/lang/Enum;
      //   33: astore_2
      //   34: aload_2
      //   35: arraylength
      //   36: istore_3
      //   37: iconst_0
      //   38: istore 4
      //   40: iload 4
      //   42: iload_3
      //   43: if_icmpge +80 -> 123
      //   46: aload_2
      //   47: iload 4
      //   49: aaload
      //   50: astore 5
      //   52: aload 5
      //   54: invokevirtual 38	java/lang/Enum:name	()Ljava/lang/String;
      //   57: astore 6
      //   59: aload_1
      //   60: aload 6
      //   62: invokevirtual 42	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   65: ldc 44
      //   67: invokevirtual 50	java/lang/reflect/Field:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
      //   70: checkcast 44	com/google/a/a/b
      //   73: astore 7
      //   75: aload 7
      //   77: ifnull +12 -> 89
      //   80: aload 7
      //   82: invokeinterface 52 1 0
      //   87: astore 6
      //   89: aload_0
      //   90: getfield 22	com/google/a/b/a/l$a:a	Ljava/util/Map;
      //   93: aload 6
      //   95: aload 5
      //   97: invokeinterface 58 3 0
      //   102: pop
      //   103: aload_0
      //   104: getfield 24	com/google/a/b/a/l$a:b	Ljava/util/Map;
      //   107: aload 5
      //   109: aload 6
      //   111: invokeinterface 58 3 0
      //   116: pop
      //   117: iinc 4 1
      //   120: goto -80 -> 40
      //   123: return
      //   124: new 60	java/lang/AssertionError
      //   127: dup
      //   128: invokespecial 61	java/lang/AssertionError:<init>	()V
      //   131: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   26	117	124	java/lang/NoSuchFieldException
    }

    public T a(com.google.a.d.a parama)
    {
      if (parama.f() == c.i)
      {
        parama.j();
        return null;
      }
      return (Enum)this.a.get(parama.h());
    }

    public void a(d paramd, T paramT)
    {
      String str;
      if (paramT == null)
        str = null;
      else
        str = (String)this.b.get(paramT);
      paramd.b(str);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.l
 * JD-Core Version:    0.6.1
 */