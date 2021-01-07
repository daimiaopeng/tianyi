package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class i
{
  private ByteBuffer a;
  private String b = "GBK";

  public i()
  {
  }

  public i(byte[] paramArrayOfByte)
  {
    this.a = ByteBuffer.wrap(paramArrayOfByte);
  }

  public i(byte[] paramArrayOfByte, int paramInt)
  {
    this.a = ByteBuffer.wrap(paramArrayOfByte);
    this.a.position(4);
  }

  private double a(double paramDouble, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      int i = locala.a;
      if (i != 12)
        switch (i)
        {
        default:
          throw new g("type mismatch.");
        case 5:
          paramDouble = this.a.getDouble();
          break;
        case 4:
          paramDouble = this.a.getFloat();
          break;
        }
      else
        paramDouble = 0.0D;
    }
    else if (paramBoolean)
    {
      throw new g("require field not exist.");
    }
    return paramDouble;
  }

  private float a(float paramFloat, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      int i = locala.a;
      if (i != 4)
      {
        if (i != 12)
          throw new g("type mismatch.");
        paramFloat = 0.0F;
      }
      else
      {
        paramFloat = this.a.getFloat();
      }
    }
    else if (paramBoolean)
    {
      throw new g("require field not exist.");
    }
    return paramFloat;
  }

  private static int a(a parama, ByteBuffer paramByteBuffer)
  {
    int i = paramByteBuffer.get();
    parama.a = (byte)(i & 0xF);
    parama.b = ((i & 0xF0) >> 4);
    if (parama.b == 15)
    {
      parama.b = paramByteBuffer.get();
      return 2;
    }
    return 1;
  }

  private <K, V> Map<K, V> a(Map<K, V> paramMap1, Map<K, V> paramMap2, int paramInt, boolean paramBoolean)
  {
    if ((paramMap2 != null) && (!paramMap2.isEmpty()))
    {
      Map.Entry localEntry = (Map.Entry)paramMap2.entrySet().iterator().next();
      Object localObject1 = localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      if (a(paramInt))
      {
        a locala = new a();
        a(locala, this.a);
        if (locala.a != 8)
          throw new g("type mismatch.");
        int i = a(0, 0, true);
        if (i < 0)
        {
          StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
          localStringBuilder.append(i);
          throw new g(localStringBuilder.toString());
        }
        for (int j = 0; j < i; j++)
          paramMap1.put(a(localObject1, 0, true), a(localObject2, 1, true));
      }
      if (paramBoolean)
        throw new g("require field not exist.");
      return paramMap1;
    }
    return new HashMap();
  }

  private void a()
  {
    a locala = new a();
    do
    {
      a(locala, this.a);
      a(locala.a);
    }
    while (locala.a != 11);
  }

  private void a(byte paramByte)
  {
    int i = 0;
    switch (paramByte)
    {
    default:
      throw new g("invalid type.");
    case 13:
      a locala3 = new a();
      a(locala3, this.a);
      if (locala3.a != 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("skipField with invalid type, type value: ");
        localStringBuilder.append(paramByte);
        localStringBuilder.append(", ");
        localStringBuilder.append(locala3.a);
        throw new g(localStringBuilder.toString());
      }
      int i1 = a(0, 0, true);
      this.a.position(i1 + this.a.position());
      return;
    case 11:
    case 12:
      return;
    case 10:
      a();
      return;
    case 9:
      int n = a(0, 0, true);
      while (i < n)
      {
        a locala2 = new a();
        a(locala2, this.a);
        a(locala2.a);
        i++;
      }
      return;
    case 8:
      int m = a(0, 0, true);
      while (i < m << 1)
      {
        a locala1 = new a();
        a(locala1, this.a);
        a(locala1.a);
        i++;
      }
      return;
    case 7:
      int k = this.a.getInt();
      this.a.position(k + this.a.position());
      return;
    case 6:
      int j = this.a.get();
      if (j < 0)
        j += 256;
      this.a.position(j + this.a.position());
      return;
    case 5:
      this.a.position(8 + this.a.position());
      return;
    case 4:
      this.a.position(4 + this.a.position());
      return;
    case 3:
      this.a.position(8 + this.a.position());
      return;
    case 2:
      this.a.position(4 + this.a.position());
      return;
    case 1:
      this.a.position(2 + this.a.position());
      return;
    case 0:
    }
    this.a.position(1 + this.a.position());
  }

  // ERROR //
  private boolean a(int paramInt)
  {
    // Byte code:
    //   0: new 36	com/tencent/bugly/proguard/i$a
    //   3: dup
    //   4: invokespecial 37	com/tencent/bugly/proguard/i$a:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: getfield 25	com/tencent/bugly/proguard/i:a	Ljava/nio/ByteBuffer;
    //   13: invokevirtual 151	java/nio/ByteBuffer:duplicate	()Ljava/nio/ByteBuffer;
    //   16: invokestatic 40	com/tencent/bugly/proguard/i:a	(Lcom/tencent/bugly/proguard/i$a;Ljava/nio/ByteBuffer;)I
    //   19: istore_3
    //   20: iload_1
    //   21: aload_2
    //   22: getfield 68	com/tencent/bugly/proguard/i$a:b	I
    //   25: if_icmple +43 -> 68
    //   28: aload_2
    //   29: getfield 43	com/tencent/bugly/proguard/i$a:a	B
    //   32: bipush 11
    //   34: if_icmpne +6 -> 40
    //   37: goto +31 -> 68
    //   40: aload_0
    //   41: getfield 25	com/tencent/bugly/proguard/i:a	Ljava/nio/ByteBuffer;
    //   44: iload_3
    //   45: aload_0
    //   46: getfield 25	com/tencent/bugly/proguard/i:a	Ljava/nio/ByteBuffer;
    //   49: invokevirtual 140	java/nio/ByteBuffer:position	()I
    //   52: iadd
    //   53: invokevirtual 30	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   56: pop
    //   57: aload_0
    //   58: aload_2
    //   59: getfield 43	com/tencent/bugly/proguard/i$a:a	B
    //   62: invokespecial 128	com/tencent/bugly/proguard/i:a	(B)V
    //   65: goto -57 -> 8
    //   68: aload_2
    //   69: getfield 68	com/tencent/bugly/proguard/i$a:b	I
    //   72: istore 4
    //   74: iload_1
    //   75: iload 4
    //   77: if_icmpne +5 -> 82
    //   80: iconst_1
    //   81: ireturn
    //   82: iconst_0
    //   83: ireturn
    //   84: iconst_0
    //   85: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	74	84	com/tencent/bugly/proguard/g
    //   0	74	84	java/nio/BufferUnderflowException
  }

  private <T> T[] a(T[] paramArrayOfT, int paramInt, boolean paramBoolean)
  {
    if ((paramArrayOfT != null) && (paramArrayOfT.length != 0))
      return b(paramArrayOfT[0], paramInt, paramBoolean);
    throw new g("unable to get type of key and value.");
  }

  private <T> T[] b(T paramT, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      Object[] arrayOfObject = (Object[])Array.newInstance(paramT.getClass(), i);
      for (int j = 0; j < i; j++)
        arrayOfObject[j] = a(paramT, 0, true);
      return arrayOfObject;
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    return null;
  }

  private boolean[] d(int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      arrayOfBoolean = new boolean[i];
      for (int j = 0; j < i; j++)
      {
        int k;
        if (a((byte)0, 0, true) != 0)
          k = 1;
        else
          k = 0;
        arrayOfBoolean[j] = k;
      }
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    boolean[] arrayOfBoolean = null;
    return arrayOfBoolean;
  }

  private short[] e(int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      arrayOfShort = new short[i];
      for (int j = 0; j < i; j++)
        arrayOfShort[j] = a(arrayOfShort[0], 0, true);
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    short[] arrayOfShort = null;
    return arrayOfShort;
  }

  private int[] f(int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      arrayOfInt = new int[i];
      for (int j = 0; j < i; j++)
        arrayOfInt[j] = a(arrayOfInt[0], 0, true);
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    int[] arrayOfInt = null;
    return arrayOfInt;
  }

  private long[] g(int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      arrayOfLong = new long[i];
      for (int j = 0; j < i; j++)
        arrayOfLong[j] = a(arrayOfLong[0], 0, true);
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    long[] arrayOfLong = null;
    return arrayOfLong;
  }

  private float[] h(int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      arrayOfFloat = new float[i];
      for (int j = 0; j < i; j++)
        arrayOfFloat[j] = a(arrayOfFloat[0], 0, true);
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    float[] arrayOfFloat = null;
    return arrayOfFloat;
  }

  private double[] i(int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      if (locala.a != 9)
        throw new g("type mismatch.");
      int i = a(0, 0, true);
      if (i < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("size invalid: ");
        localStringBuilder.append(i);
        throw new g(localStringBuilder.toString());
      }
      arrayOfDouble = new double[i];
      for (int j = 0; j < i; j++)
        arrayOfDouble[j] = a(arrayOfDouble[0], 0, true);
    }
    if (paramBoolean)
      throw new g("require field not exist.");
    double[] arrayOfDouble = null;
    return arrayOfDouble;
  }

  public final byte a(byte paramByte, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      int i = locala.a;
      if (i != 0)
      {
        if (i != 12)
          throw new g("type mismatch.");
        paramByte = 0;
      }
      else
      {
        paramByte = this.a.get();
      }
    }
    else if (paramBoolean)
    {
      throw new g("require field not exist.");
    }
    return paramByte;
  }

  public final int a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (a(paramInt2))
    {
      a locala = new a();
      a(locala, this.a);
      int i = locala.a;
      if (i != 12)
        switch (i)
        {
        default:
          throw new g("type mismatch.");
        case 2:
          paramInt1 = this.a.getInt();
          break;
        case 1:
          paramInt1 = this.a.getShort();
          break;
        case 0:
          paramInt1 = this.a.get();
          break;
        }
      else
        paramInt1 = 0;
    }
    else if (paramBoolean)
    {
      throw new g("require field not exist.");
    }
    return paramInt1;
  }

  public final int a(String paramString)
  {
    this.b = paramString;
    return 0;
  }

  public final long a(long paramLong, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      int i = locala.a;
      if (i != 12)
        switch (i)
        {
        default:
          throw new g("type mismatch.");
        case 3:
          paramLong = this.a.getLong();
          break;
        case 2:
          paramLong = this.a.getInt();
          break;
        case 1:
          paramLong = this.a.getShort();
          break;
        case 0:
          paramLong = this.a.get();
          break;
        }
      else
        paramLong = 0L;
    }
    else if (paramBoolean)
    {
      throw new g("require field not exist.");
    }
    return paramLong;
  }

  public final k a(k paramk, int paramInt, boolean paramBoolean)
  {
    k localk;
    if (a(paramInt))
    {
      try
      {
        localk = (k)paramk.getClass().newInstance();
        a locala = new a();
        a(locala, this.a);
        if (locala.a != 10)
          throw new g("type mismatch.");
        localk.a(this);
        a();
      }
      catch (Exception localException)
      {
        throw new g(localException.getMessage());
      }
    }
    else
    {
      if (paramBoolean)
        throw new g("require field not exist.");
      localk = null;
    }
    return localk;
  }

  public final <T> Object a(T paramT, int paramInt, boolean paramBoolean)
  {
    boolean bool1 = paramT instanceof Byte;
    int i = 0;
    if (bool1)
      return Byte.valueOf(a((byte)0, paramInt, paramBoolean));
    if ((paramT instanceof Boolean))
    {
      int j = a((byte)0, paramInt, paramBoolean);
      boolean bool2 = false;
      if (j != 0)
        bool2 = true;
      return Boolean.valueOf(bool2);
    }
    if ((paramT instanceof Short))
      return Short.valueOf(a((short)0, paramInt, paramBoolean));
    if ((paramT instanceof Integer))
      return Integer.valueOf(a(0, paramInt, paramBoolean));
    if ((paramT instanceof Long))
      return Long.valueOf(a(0L, paramInt, paramBoolean));
    if ((paramT instanceof Float))
      return Float.valueOf(a(0.0F, paramInt, paramBoolean));
    if ((paramT instanceof Double))
      return Double.valueOf(a(0.0D, paramInt, paramBoolean));
    if ((paramT instanceof String))
      return String.valueOf(b(paramInt, paramBoolean));
    if ((paramT instanceof Map))
    {
      Map localMap = (Map)paramT;
      return (HashMap)a(new HashMap(), localMap, paramInt, paramBoolean);
    }
    if ((paramT instanceof List))
    {
      List localList = (List)paramT;
      if ((localList != null) && (!localList.isEmpty()))
      {
        Object[] arrayOfObject = b(localList.get(0), paramInt, paramBoolean);
        if (arrayOfObject == null)
          return null;
        ArrayList localArrayList = new ArrayList();
        while (i < arrayOfObject.length)
        {
          localArrayList.add(arrayOfObject[i]);
          i++;
        }
        return localArrayList;
      }
      return new ArrayList();
    }
    if ((paramT instanceof k))
      return a((k)paramT, paramInt, paramBoolean);
    if (paramT.getClass().isArray())
    {
      if ((!(paramT instanceof byte[])) && (!(paramT instanceof Byte[])))
      {
        if ((paramT instanceof boolean[]))
          return d(paramInt, paramBoolean);
        if ((paramT instanceof short[]))
          return e(paramInt, paramBoolean);
        if ((paramT instanceof int[]))
          return f(paramInt, paramBoolean);
        if ((paramT instanceof long[]))
          return g(paramInt, paramBoolean);
        if ((paramT instanceof float[]))
          return h(paramInt, paramBoolean);
        if ((paramT instanceof double[]))
          return i(paramInt, paramBoolean);
        return a((Object[])paramT, paramInt, paramBoolean);
      }
      return c(paramInt, paramBoolean);
    }
    throw new g("read object error: unsupport type.");
  }

  public final <K, V> HashMap<K, V> a(Map<K, V> paramMap, int paramInt, boolean paramBoolean)
  {
    return (HashMap)a(new HashMap(), paramMap, paramInt, paramBoolean);
  }

  public final short a(short paramShort, int paramInt, boolean paramBoolean)
  {
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      int i = locala.a;
      if (i != 12)
        switch (i)
        {
        default:
          throw new g("type mismatch.");
        case 1:
          paramShort = this.a.getShort();
          break;
        case 0:
          paramShort = (short)this.a.get();
          break;
        }
      else
        paramShort = 0;
    }
    else if (paramBoolean)
    {
      throw new g("require field not exist.");
    }
    return paramShort;
  }

  public final void a(byte[] paramArrayOfByte)
  {
    if (this.a != null)
      this.a.clear();
    this.a = ByteBuffer.wrap(paramArrayOfByte);
  }

  public final boolean a(int paramInt, boolean paramBoolean)
  {
    return a((byte)0, paramInt, paramBoolean) != 0;
  }

  public final String b(int paramInt, boolean paramBoolean)
  {
    String str;
    if (a(paramInt))
    {
      a locala = new a();
      a(locala, this.a);
      switch (locala.a)
      {
      default:
        throw new g("type mismatch.");
      case 7:
        int j = this.a.getInt();
        if ((j <= 104857600) && (j >= 0))
        {
          byte[] arrayOfByte2 = new byte[j];
          this.a.get(arrayOfByte2);
          try
          {
            str = new String(arrayOfByte2, this.b);
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException1)
          {
            str = new String(arrayOfByte2);
            tmpTernaryOp = localUnsupportedEncodingException1;
          }
        }
        StringBuilder localStringBuilder = new StringBuilder("String too long: ");
        localStringBuilder.append(j);
        throw new g(localStringBuilder.toString());
      case 6:
        int i = this.a.get();
        if (i < 0)
          i += 256;
        byte[] arrayOfByte1 = new byte[i];
        this.a.get(arrayOfByte1);
        try
        {
          str = new String(arrayOfByte1, this.b);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          str = new String(arrayOfByte1);
          tmpTernaryOp = localUnsupportedEncodingException2;
        }
      }
    }
    else
    {
      if (paramBoolean)
        throw new g("require field not exist.");
      str = null;
    }
    return str;
  }

  public final byte[] c(int paramInt, boolean paramBoolean)
  {
    Object localObject;
    if (a(paramInt))
    {
      a locala1 = new a();
      a(locala1, this.a);
      int i = locala1.a;
      if (i != 9)
      {
        if (i != 13)
          throw new g("type mismatch.");
        a locala2 = new a();
        a(locala2, this.a);
        if (locala2.a != 0)
        {
          StringBuilder localStringBuilder2 = new StringBuilder("type mismatch, tag: ");
          localStringBuilder2.append(paramInt);
          localStringBuilder2.append(", type: ");
          localStringBuilder2.append(locala1.a);
          localStringBuilder2.append(", ");
          localStringBuilder2.append(locala2.a);
          throw new g(localStringBuilder2.toString());
        }
        int m = a(0, 0, true);
        if (m < 0)
        {
          StringBuilder localStringBuilder3 = new StringBuilder("invalid size, tag: ");
          localStringBuilder3.append(paramInt);
          localStringBuilder3.append(", type: ");
          localStringBuilder3.append(locala1.a);
          localStringBuilder3.append(", ");
          localStringBuilder3.append(locala2.a);
          localStringBuilder3.append(", size: ");
          localStringBuilder3.append(m);
          throw new g(localStringBuilder3.toString());
        }
        localObject = new byte[m];
        this.a.get((byte[])localObject);
      }
      else
      {
        int j = a(0, 0, true);
        if (j < 0)
        {
          StringBuilder localStringBuilder1 = new StringBuilder("size invalid: ");
          localStringBuilder1.append(j);
          throw new g(localStringBuilder1.toString());
        }
        byte[] arrayOfByte = new byte[j];
        for (int k = 0; k < j; k++)
          arrayOfByte[k] = a(arrayOfByte[0], 0, true);
        localObject = arrayOfByte;
      }
    }
    else
    {
      if (paramBoolean)
        throw new g("require field not exist.");
      localObject = null;
    }
    return localObject;
  }

  public static final class a
  {
    public byte a;
    public int b;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.i
 * JD-Core Version:    0.6.1
 */