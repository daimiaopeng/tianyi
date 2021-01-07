package com.google.a.b.a;

import com.google.a.d.c;
import com.google.a.d.d;
import com.google.a.e;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class j extends r<Time>
{
  public static final s a = new s()
  {
    public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
    {
      j localj;
      if (paramAnonymousa.a() == Time.class)
        localj = new j();
      else
        localj = null;
      return localj;
    }
  };
  private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

  public Time a(com.google.a.d.a parama)
  {
    try
    {
      if (parama.f() == c.i)
      {
        parama.j();
        return null;
      }
      try
      {
        Time localTime = new Time(this.b.parse(parama.h()).getTime());
        return localTime;
      }
      catch (ParseException localParseException)
      {
        throw new p(localParseException);
      }
    }
    finally
    {
    }
  }

  public void a(d paramd, Time paramTime)
  {
    String str;
    if (paramTime == null)
      str = null;
    try
    {
      str = this.b.format(paramTime);
      paramd.b(str);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.a.j
 * JD-Core Version:    0.6.1
 */