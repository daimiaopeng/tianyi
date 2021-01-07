package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;

public final class d extends c
{
  private static HashMap<String, byte[]> f;
  private static HashMap<String, HashMap<String, byte[]>> g;
  private f e = new f();

  public d()
  {
    this.e.a = 2;
  }

  public final <T> void a(String paramString, T paramT)
  {
    if (paramString.startsWith("."))
    {
      StringBuilder localStringBuilder = new StringBuilder("put name can not startwith . , now is ");
      localStringBuilder.append(paramString);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    super.a(paramString, paramT);
  }

  public final void a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length < 4)
      throw new IllegalArgumentException("decode package must include size head");
    try
    {
      i locali1 = new i(paramArrayOfByte, 4);
      locali1.a(this.b);
      this.e.a(locali1);
      if (this.e.a == 3)
      {
        i locali2 = new i(this.e.e);
        locali2.a(this.b);
        if (f == null)
        {
          HashMap localHashMap1 = new HashMap();
          f = localHashMap1;
          localHashMap1.put("", new byte[0]);
        }
        this.d = locali2.a(f, 0, false);
        return;
      }
      i locali3 = new i(this.e.e);
      locali3.a(this.b);
      if (g == null)
      {
        g = new HashMap();
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("", new byte[0]);
        g.put("", localHashMap2);
      }
      this.a = locali3.a(g, 0, false);
      new HashMap();
      return;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
  }

  public final byte[] a()
  {
    if (this.e.a == 2)
    {
      if (this.e.c.equals(""))
        throw new IllegalArgumentException("servantName can not is null");
      if (this.e.d.equals(""))
        throw new IllegalArgumentException("funcName can not is null");
    }
    else
    {
      if (this.e.c == null)
        this.e.c = "";
      if (this.e.d == null)
        this.e.d = "";
    }
    j localj1 = new j(0);
    localj1.a(this.b);
    if (this.e.a == 2)
      localj1.a(this.a, 0);
    else
      localj1.a(this.d, 0);
    this.e.e = l.a(localj1.a());
    j localj2 = new j(0);
    localj2.a(this.b);
    this.e.a(localj2);
    byte[] arrayOfByte = l.a(localj2.a());
    int i = 4 + arrayOfByte.length;
    ByteBuffer localByteBuffer = ByteBuffer.allocate(i);
    localByteBuffer.putInt(i).put(arrayOfByte).flip();
    return localByteBuffer.array();
  }

  public final void b()
  {
    super.b();
    this.e.a = 3;
  }

  public final void b(int paramInt)
  {
    this.e.b = 1;
  }

  public final void b(String paramString)
  {
    this.e.c = paramString;
  }

  public final void c(String paramString)
  {
    this.e.d = paramString;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.d
 * JD-Core Version:    0.6.1
 */