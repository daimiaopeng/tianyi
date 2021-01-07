package com.tencent.bugly.proguard;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public final class f extends k
{
  private static byte[] k;
  private static Map<String, String> l;
  public short a = 0;
  public int b = 0;
  public String c = null;
  public String d = null;
  public byte[] e;
  private byte f = 0;
  private int g = 0;
  private int h = 0;
  private Map<String, String> i;
  private Map<String, String> j;

  public final void a(i parami)
  {
    try
    {
      this.a = parami.a(this.a, 1, true);
      this.f = parami.a(this.f, 2, true);
      this.g = parami.a(this.g, 3, true);
      this.b = parami.a(this.b, 4, true);
      this.c = parami.b(5, true);
      this.d = parami.b(6, true);
      if (k == null)
        k = new byte[] { 0 };
      this.e = ((byte[])parami.c(7, true));
      this.h = parami.a(this.h, 8, true);
      if (l == null)
      {
        HashMap localHashMap1 = new HashMap();
        l = localHashMap1;
        localHashMap1.put("", "");
      }
      this.i = ((Map)parami.a(l, 9, true));
      if (l == null)
      {
        HashMap localHashMap2 = new HashMap();
        l = localHashMap2;
        localHashMap2.put("", "");
      }
      this.j = ((Map)parami.a(l, 10, true));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      PrintStream localPrintStream = System.out;
      StringBuilder localStringBuilder = new StringBuilder("RequestPacket decode error ");
      localStringBuilder.append(e.a(this.e));
      localPrintStream.println(localStringBuilder.toString());
      throw new RuntimeException(localException);
    }
  }

  public final void a(j paramj)
  {
    paramj.a(this.a, 1);
    paramj.a(this.f, 2);
    paramj.a(this.g, 3);
    paramj.a(this.b, 4);
    paramj.a(this.c, 5);
    paramj.a(this.d, 6);
    paramj.a(this.e, 7);
    paramj.a(this.h, 8);
    paramj.a(this.i, 9);
    paramj.a(this.j, 10);
  }

  public final void a(StringBuilder paramStringBuilder, int paramInt)
  {
    h localh = new h(paramStringBuilder, paramInt);
    localh.a(this.a, "iVersion");
    localh.a(this.f, "cPacketType");
    localh.a(this.g, "iMessageType");
    localh.a(this.b, "iRequestId");
    localh.a(this.c, "sServantName");
    localh.a(this.d, "sFuncName");
    localh.a(this.e, "sBuffer");
    localh.a(this.h, "iTimeout");
    localh.a(this.i, "context");
    localh.a(this.j, "status");
  }

  // ERROR //
  public final Object clone()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 201	java/lang/Object:clone	()Ljava/lang/Object;
    //   4: astore_1
    //   5: goto +19 -> 24
    //   8: getstatic 203	com/tencent/bugly/proguard/f:m	Z
    //   11: ifne +11 -> 22
    //   14: new 205	java/lang/AssertionError
    //   17: dup
    //   18: invokespecial 206	java/lang/AssertionError:<init>	()V
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
    f localf = (f)paramObject;
    return (l.a(1, localf.a)) && (l.a(1, localf.f)) && (l.a(1, localf.g)) && (l.a(1, localf.b)) && (l.a(Integer.valueOf(1), localf.c)) && (l.a(Integer.valueOf(1), localf.d)) && (l.a(Integer.valueOf(1), localf.e)) && (l.a(1, localf.h)) && (l.a(Integer.valueOf(1), localf.i)) && (l.a(Integer.valueOf(1), localf.j));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.f
 * JD-Core Version:    0.6.1
 */