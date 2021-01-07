package com.google.a;

import com.google.a.b.a.e;
import com.google.a.d.a;
import com.google.a.d.d;
import java.io.IOException;

public abstract class r<T>
{
  public final i a(T paramT)
  {
    try
    {
      e locale = new e();
      a(locale, paramT);
      i locali = locale.a();
      return locali;
    }
    catch (IOException localIOException)
    {
      throw new j(localIOException);
    }
  }

  public abstract void a(d paramd, T paramT);

  public abstract T b(a parama);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.r
 * JD-Core Version:    0.6.1
 */