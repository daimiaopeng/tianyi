package com.google.a.b.a;

import com.google.a.d.d;
import com.google.a.e;
import com.google.a.r;
import com.google.a.s;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class c extends r<Date>
{
  public static final s a = new s()
  {
    public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
    {
      c localc;
      if (paramAnonymousa.a() == Date.class)
        localc = new c();
      else
        localc = null;
      return localc;
    }
  };
  private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
  private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);
  private final DateFormat d = a();

  private static DateFormat a()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat;
  }

  // ERROR //
  private Date a(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/google/a/b/a/c:c	Ljava/text/DateFormat;
    //   6: aload_1
    //   7: invokevirtual 70	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   10: astore 6
    //   12: aload_0
    //   13: monitorexit
    //   14: aload 6
    //   16: areturn
    //   17: astore_3
    //   18: goto +44 -> 62
    //   21: aload_0
    //   22: getfield 35	com/google/a/b/a/c:b	Ljava/text/DateFormat;
    //   25: aload_1
    //   26: invokevirtual 70	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   29: astore 5
    //   31: aload_0
    //   32: monitorexit
    //   33: aload 5
    //   35: areturn
    //   36: aload_0
    //   37: getfield 45	com/google/a/b/a/c:d	Ljava/text/DateFormat;
    //   40: aload_1
    //   41: invokevirtual 70	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   44: astore 4
    //   46: aload_0
    //   47: monitorexit
    //   48: aload 4
    //   50: areturn
    //   51: astore_2
    //   52: new 72	com/google/a/p
    //   55: dup
    //   56: aload_1
    //   57: aload_2
    //   58: invokespecial 75	com/google/a/p:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   61: athrow
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_3
    //   65: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	12	17	finally
    //   21	31	17	finally
    //   36	46	17	finally
    //   52	62	17	finally
    //   2	12	21	java/text/ParseException
    //   21	31	36	java/text/ParseException
    //   36	46	51	java/text/ParseException
  }

  public Date a(com.google.a.d.a parama)
  {
    if (parama.f() == com.google.a.d.c.i)
    {
      parama.j();
      return null;
    }
    return a(parama.h());
  }

  public void a(d paramd, Date paramDate)
  {
    if (paramDate == null)
      try
      {
        paramd.f();
        return;
      }
      finally
      {
        break label35;
      }
    paramd.b(this.b.format(paramDate));
    return;
    label35: throw localObject;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.c
 * JD-Core Version:    0.6.1
 */