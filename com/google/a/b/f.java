package com.google.a.b;

public final class f extends Number
{
  private final String a;

  public f(String paramString)
  {
    this.a = paramString;
  }

  public double doubleValue()
  {
    return Double.parseDouble(this.a);
  }

  public float floatValue()
  {
    return Float.parseFloat(this.a);
  }

  // ERROR //
  public int intValue()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 13	com/google/a/b/f:a	Ljava/lang/String;
    //   4: invokestatic 39	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   7: istore_3
    //   8: iload_3
    //   9: ireturn
    //   10: aload_0
    //   11: getfield 13	com/google/a/b/f:a	Ljava/lang/String;
    //   14: invokestatic 45	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   17: lstore_1
    //   18: lload_1
    //   19: l2i
    //   20: ireturn
    //   21: new 47	java/math/BigInteger
    //   24: dup
    //   25: aload_0
    //   26: getfield 13	com/google/a/b/f:a	Ljava/lang/String;
    //   29: invokespecial 49	java/math/BigInteger:<init>	(Ljava/lang/String;)V
    //   32: invokevirtual 51	java/math/BigInteger:intValue	()I
    //   35: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	10	java/lang/NumberFormatException
    //   10	18	21	java/lang/NumberFormatException
  }

  // ERROR //
  public long longValue()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 13	com/google/a/b/f:a	Ljava/lang/String;
    //   4: invokestatic 45	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   7: lstore_1
    //   8: lload_1
    //   9: lreturn
    //   10: new 47	java/math/BigInteger
    //   13: dup
    //   14: aload_0
    //   15: getfield 13	com/google/a/b/f:a	Ljava/lang/String;
    //   18: invokespecial 49	java/math/BigInteger:<init>	(Ljava/lang/String;)V
    //   21: invokevirtual 55	java/math/BigInteger:longValue	()J
    //   24: lreturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	10	java/lang/NumberFormatException
  }

  public String toString()
  {
    return this.a;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.f
 * JD-Core Version:    0.6.1
 */