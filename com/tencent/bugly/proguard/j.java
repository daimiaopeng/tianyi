package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class j
{
  private ByteBuffer a;
  private String b = "GBK";

  public j()
  {
    this(128);
  }

  public j(int paramInt)
  {
    this.a = ByteBuffer.allocate(paramInt);
  }

  private void a(int paramInt)
  {
    if (this.a.remaining() < paramInt)
    {
      ByteBuffer localByteBuffer = ByteBuffer.allocate(paramInt + this.a.capacity() << 1);
      localByteBuffer.put(this.a.array(), 0, this.a.position());
      this.a = localByteBuffer;
    }
  }

  private void b(byte paramByte, int paramInt)
  {
    if (paramInt < 15)
    {
      byte b2 = (byte)(paramByte | paramInt << 4);
      this.a.put(b2);
      return;
    }
    if (paramInt < 256)
    {
      byte b1 = (byte)(paramByte | 0xF0);
      this.a.put(b1);
      this.a.put((byte)paramInt);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder("tag is too large: ");
    localStringBuilder.append(paramInt);
    throw new b(localStringBuilder.toString());
  }

  public final int a(String paramString)
  {
    this.b = paramString;
    return 0;
  }

  public final ByteBuffer a()
  {
    return this.a;
  }

  public final void a(byte paramByte, int paramInt)
  {
    a(3);
    if (paramByte == 0)
    {
      b((byte)12, paramInt);
      return;
    }
    b((byte)0, paramInt);
    this.a.put(paramByte);
  }

  public final void a(int paramInt1, int paramInt2)
  {
    a(6);
    if ((paramInt1 >= -32768) && (paramInt1 <= 32767))
    {
      a((short)paramInt1, paramInt2);
      return;
    }
    b((byte)2, paramInt2);
    this.a.putInt(paramInt1);
  }

  public final void a(long paramLong, int paramInt)
  {
    a(10);
    if ((paramLong >= -2147483648L) && (paramLong <= 2147483647L))
    {
      a((int)paramLong, paramInt);
      return;
    }
    b((byte)3, paramInt);
    this.a.putLong(paramLong);
  }

  public final void a(k paramk, int paramInt)
  {
    a(2);
    b((byte)10, paramInt);
    paramk.a(this);
    a(2);
    b((byte)11, 0);
  }

  public final void a(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Byte))
    {
      a(((Byte)paramObject).byteValue(), paramInt);
      return;
    }
    if ((paramObject instanceof Boolean))
    {
      a((byte)((Boolean)paramObject).booleanValue(), paramInt);
      return;
    }
    if ((paramObject instanceof Short))
    {
      a(((Short)paramObject).shortValue(), paramInt);
      return;
    }
    if ((paramObject instanceof Integer))
    {
      a(((Integer)paramObject).intValue(), paramInt);
      return;
    }
    if ((paramObject instanceof Long))
    {
      a(((Long)paramObject).longValue(), paramInt);
      return;
    }
    if ((paramObject instanceof Float))
    {
      float f2 = ((Float)paramObject).floatValue();
      a(6);
      b((byte)4, paramInt);
      this.a.putFloat(f2);
      return;
    }
    if ((paramObject instanceof Double))
    {
      double d2 = ((Double)paramObject).doubleValue();
      a(10);
      b((byte)5, paramInt);
      this.a.putDouble(d2);
      return;
    }
    if ((paramObject instanceof String))
    {
      a((String)paramObject, paramInt);
      return;
    }
    if ((paramObject instanceof Map))
    {
      a((Map)paramObject, paramInt);
      return;
    }
    if ((paramObject instanceof List))
    {
      a((List)paramObject, paramInt);
      return;
    }
    if ((paramObject instanceof k))
    {
      k localk = (k)paramObject;
      a(2);
      b((byte)10, paramInt);
      localk.a(this);
      a(2);
      b((byte)11, 0);
      return;
    }
    if ((paramObject instanceof byte[]))
    {
      a((byte[])paramObject, paramInt);
      return;
    }
    if ((paramObject instanceof boolean[]))
    {
      boolean[] arrayOfBoolean = (boolean[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfBoolean.length, 0);
      int i8 = arrayOfBoolean.length;
      for (int i9 = 0; i9 < i8; i9++)
        a((byte)arrayOfBoolean[i9], 0);
      return;
    }
    if ((paramObject instanceof short[]))
    {
      short[] arrayOfShort = (short[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfShort.length, 0);
      int i6 = arrayOfShort.length;
      for (int i7 = 0; i7 < i6; i7++)
        a(arrayOfShort[i7], 0);
      return;
    }
    if ((paramObject instanceof int[]))
    {
      int[] arrayOfInt = (int[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfInt.length, 0);
      int i4 = arrayOfInt.length;
      for (int i5 = 0; i5 < i4; i5++)
        a(arrayOfInt[i5], 0);
      return;
    }
    if ((paramObject instanceof long[]))
    {
      long[] arrayOfLong = (long[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfLong.length, 0);
      int i2 = arrayOfLong.length;
      for (int i3 = 0; i3 < i2; i3++)
        a(arrayOfLong[i3], 0);
      return;
    }
    if ((paramObject instanceof float[]))
    {
      float[] arrayOfFloat = (float[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfFloat.length, 0);
      int n = arrayOfFloat.length;
      for (int i1 = 0; i1 < n; i1++)
      {
        float f1 = arrayOfFloat[i1];
        a(6);
        b((byte)4, 0);
        this.a.putFloat(f1);
      }
      return;
    }
    if ((paramObject instanceof double[]))
    {
      double[] arrayOfDouble = (double[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfDouble.length, 0);
      int k = arrayOfDouble.length;
      for (int m = 0; m < k; m++)
      {
        double d1 = arrayOfDouble[m];
        a(10);
        b((byte)5, 0);
        this.a.putDouble(d1);
      }
      return;
    }
    if (paramObject.getClass().isArray())
    {
      Object[] arrayOfObject = (Object[])paramObject;
      a(8);
      b((byte)9, paramInt);
      a(arrayOfObject.length, 0);
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        a(arrayOfObject[j], 0);
      return;
    }
    if ((paramObject instanceof Collection))
    {
      a((Collection)paramObject, paramInt);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder("write object error: unsupport type. ");
    localStringBuilder.append(paramObject.getClass());
    throw new b(localStringBuilder.toString());
  }

  // ERROR //
  public final void a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield 19	com/tencent/bugly/proguard/j:b	Ljava/lang/String;
    //   5: invokevirtual 209	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   8: astore_3
    //   9: goto +8 -> 17
    //   12: aload_1
    //   13: invokevirtual 211	java/lang/String:getBytes	()[B
    //   16: astore_3
    //   17: aload_0
    //   18: bipush 10
    //   20: aload_3
    //   21: arraylength
    //   22: iadd
    //   23: invokespecial 71	com/tencent/bugly/proguard/j:a	(I)V
    //   26: aload_3
    //   27: arraylength
    //   28: sipush 255
    //   31: if_icmple +30 -> 61
    //   34: aload_0
    //   35: bipush 7
    //   37: iload_2
    //   38: invokespecial 73	com/tencent/bugly/proguard/j:b	(BI)V
    //   41: aload_0
    //   42: getfield 27	com/tencent/bugly/proguard/j:a	Ljava/nio/ByteBuffer;
    //   45: aload_3
    //   46: arraylength
    //   47: invokevirtual 80	java/nio/ByteBuffer:putInt	(I)Ljava/nio/ByteBuffer;
    //   50: pop
    //   51: aload_0
    //   52: getfield 27	com/tencent/bugly/proguard/j:a	Ljava/nio/ByteBuffer;
    //   55: aload_3
    //   56: invokevirtual 214	java/nio/ByteBuffer:put	([B)Ljava/nio/ByteBuffer;
    //   59: pop
    //   60: return
    //   61: aload_0
    //   62: bipush 6
    //   64: iload_2
    //   65: invokespecial 73	com/tencent/bugly/proguard/j:b	(BI)V
    //   68: aload_0
    //   69: getfield 27	com/tencent/bugly/proguard/j:a	Ljava/nio/ByteBuffer;
    //   72: aload_3
    //   73: arraylength
    //   74: i2b
    //   75: invokevirtual 49	java/nio/ByteBuffer:put	(B)Ljava/nio/ByteBuffer;
    //   78: pop
    //   79: aload_0
    //   80: getfield 27	com/tencent/bugly/proguard/j:a	Ljava/nio/ByteBuffer;
    //   83: aload_3
    //   84: invokevirtual 214	java/nio/ByteBuffer:put	([B)Ljava/nio/ByteBuffer;
    //   87: pop
    //   88: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	9	12	java/io/UnsupportedEncodingException
  }

  public final <T> void a(Collection<T> paramCollection, int paramInt)
  {
    a(8);
    b((byte)9, paramInt);
    int i;
    if (paramCollection == null)
      i = 0;
    else
      i = paramCollection.size();
    a(i, 0);
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        a(localIterator.next(), 0);
    }
  }

  public final <K, V> void a(Map<K, V> paramMap, int paramInt)
  {
    a(8);
    b((byte)8, paramInt);
    int i;
    if (paramMap == null)
      i = 0;
    else
      i = paramMap.size();
    a(i, 0);
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        a(localEntry.getKey(), 0);
        a(localEntry.getValue(), 1);
      }
    }
  }

  public final void a(short paramShort, int paramInt)
  {
    a(4);
    if ((paramShort >= -128) && (paramShort <= 127))
    {
      a((byte)paramShort, paramInt);
      return;
    }
    b((byte)1, paramInt);
    this.a.putShort(paramShort);
  }

  public final void a(boolean paramBoolean, int paramInt)
  {
    a((byte)paramBoolean, paramInt);
  }

  public final void a(byte[] paramArrayOfByte, int paramInt)
  {
    a(8 + paramArrayOfByte.length);
    b((byte)13, paramInt);
    b((byte)0, 0);
    a(paramArrayOfByte.length, 0);
    this.a.put(paramArrayOfByte);
  }

  public final byte[] b()
  {
    byte[] arrayOfByte = new byte[this.a.position()];
    System.arraycopy(this.a.array(), 0, arrayOfByte, 0, this.a.position());
    return arrayOfByte;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.j
 * JD-Core Version:    0.6.1
 */