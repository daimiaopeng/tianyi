package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class ap extends k
  implements Cloneable
{
  private static ao m = new ao();
  private static Map<String, String> n = new HashMap();
  public boolean a = true;
  public boolean b = true;
  public boolean c = true;
  public String d = "";
  public String e = "";
  public ao f = null;
  public Map<String, String> g = null;
  public long h = 0L;
  public int i = 0;
  private String j = "";
  private String k = "";
  private int l = 0;

  static
  {
    n.put("", "");
  }

  public final void a(i parami)
  {
    this.a = parami.a(0, true);
    this.b = parami.a(1, true);
    this.c = parami.a(2, true);
    this.d = parami.b(3, false);
    this.e = parami.b(4, false);
    this.f = ((ao)parami.a(m, 5, false));
    this.g = ((Map)parami.a(n, 6, false));
    this.h = parami.a(this.h, 7, false);
    this.j = parami.b(8, false);
    this.k = parami.b(9, false);
    this.l = parami.a(this.l, 10, false);
    this.i = parami.a(this.i, 11, false);
  }

  public final void a(j paramj)
  {
    paramj.a(this.a, 0);
    paramj.a(this.b, 1);
    paramj.a(this.c, 2);
    if (this.d != null)
      paramj.a(this.d, 3);
    if (this.e != null)
      paramj.a(this.e, 4);
    if (this.f != null)
      paramj.a(this.f, 5);
    if (this.g != null)
      paramj.a(this.g, 6);
    paramj.a(this.h, 7);
    if (this.j != null)
      paramj.a(this.j, 8);
    if (this.k != null)
      paramj.a(this.k, 9);
    paramj.a(this.l, 10);
    paramj.a(this.i, 11);
  }

  public final void a(StringBuilder paramStringBuilder, int paramInt)
  {
    h localh = new h(paramStringBuilder, paramInt);
    localh.a(this.a, "enable");
    localh.a(this.b, "enableUserInfo");
    localh.a(this.c, "enableQuery");
    localh.a(this.d, "url");
    localh.a(this.e, "expUrl");
    localh.a(this.f, "security");
    localh.a(this.g, "valueMap");
    localh.a(this.h, "strategylastUpdateTime");
    localh.a(this.j, "httpsUrl");
    localh.a(this.k, "httpsExpUrl");
    localh.a(this.l, "eventRecordCount");
    localh.a(this.i, "eventTimeInterval");
  }

  // ERROR //
  public final Object clone()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 173	java/lang/Object:clone	()Ljava/lang/Object;
    //   4: astore_1
    //   5: goto +19 -> 24
    //   8: getstatic 175	com/tencent/bugly/proguard/ap:o	Z
    //   11: ifne +11 -> 22
    //   14: new 177	java/lang/AssertionError
    //   17: dup
    //   18: invokespecial 178	java/lang/AssertionError:<init>	()V
    //   21: athrow
    //   22: aconst_null
    //   23: astore_1
    //   24: aload_1
    //   25: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	5	8	java/lang/CloneNotSupportedException
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    ap localap = (ap)paramObject;
    return (l.a(this.a, localap.a)) && (l.a(this.b, localap.b)) && (l.a(this.c, localap.c)) && (l.a(this.d, localap.d)) && (l.a(this.e, localap.e)) && (l.a(this.f, localap.f)) && (l.a(this.g, localap.g)) && (l.a(this.h, localap.h)) && (l.a(this.j, localap.j)) && (l.a(this.k, localap.k)) && (l.a(this.l, localap.l)) && (l.a(this.i, localap.i));
  }

  public final int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.ap
 * JD-Core Version:    0.6.1
 */