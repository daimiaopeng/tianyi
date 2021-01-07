package com.google.a.b.a;

import com.google.a.d.c;
import com.google.a.d.d;
import com.google.a.e;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class i extends r<java.sql.Date>
{
  public static final s a = new s()
  {
    public <T> r<T> a(e paramAnonymouse, com.google.a.c.a<T> paramAnonymousa)
    {
      i locali;
      if (paramAnonymousa.a() == java.sql.Date.class)
        locali = new i();
      else
        locali = null;
      return locali;
    }
  };
  private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

  public java.sql.Date a(com.google.a.d.a parama)
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
        java.sql.Date localDate = new java.sql.Date(this.b.parse(parama.h()).getTime());
        return localDate;
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

  public void a(d paramd, java.sql.Date paramDate)
  {
    String str;
    if (paramDate == null)
      str = null;
    try
    {
      str = this.b.format(paramDate);
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
 * Qualified Name:     com.google.a.b.a.i
 * JD-Core Version:    0.6.1
 */