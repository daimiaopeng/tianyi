package com.google.a.b;

import com.google.a.b.a.l;
import com.google.a.d.d;
import com.google.a.r;
import java.io.Writer;

public final class i
{
  public static Writer a(Appendable paramAppendable)
  {
    Object localObject;
    if ((paramAppendable instanceof Writer))
      localObject = (Writer)paramAppendable;
    else
      localObject = new a(paramAppendable, null);
    return localObject;
  }

  public static void a(com.google.a.i parami, d paramd)
  {
    l.P.a(paramd, parami);
  }

  private static class a extends Writer
  {
    private final Appendable a;
    private final a b = new a();

    private a(Appendable paramAppendable)
    {
      this.a = paramAppendable;
    }

    public void close()
    {
    }

    public void flush()
    {
    }

    public void write(int paramInt)
    {
      this.a.append((char)paramInt);
    }

    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      this.b.a = paramArrayOfChar;
      this.a.append(this.b, paramInt1, paramInt2 + paramInt1);
    }

    static class a
      implements CharSequence
    {
      char[] a;

      public char charAt(int paramInt)
      {
        return this.a[paramInt];
      }

      public int length()
      {
        return this.a.length;
      }

      public CharSequence subSequence(int paramInt1, int paramInt2)
      {
        return new String(this.a, paramInt1, paramInt2 - paramInt1);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.i
 * JD-Core Version:    0.6.1
 */