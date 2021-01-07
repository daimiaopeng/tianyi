package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;

public final class l
{
  static
  {
    byte[] arrayOfByte1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    byte[] arrayOfByte2 = new byte[256];
    byte[] arrayOfByte3 = new byte[256];
    for (int i = 0; i < 256; i++)
    {
      arrayOfByte2[i] = arrayOfByte1[(i >>> 4)];
      arrayOfByte3[i] = arrayOfByte1[(i & 0xF)];
    }
  }

  public static boolean a(int paramInt1, int paramInt2)
  {
    return paramInt1 == paramInt2;
  }

  public static boolean a(long paramLong1, long paramLong2)
  {
    return paramLong1 == paramLong2;
  }

  public static boolean a(Object paramObject1, Object paramObject2)
  {
    return paramObject1.equals(paramObject2);
  }

  public static boolean a(boolean paramBoolean1, boolean paramBoolean2)
  {
    return paramBoolean1 == paramBoolean2;
  }

  public static byte[] a(ByteBuffer paramByteBuffer)
  {
    byte[] arrayOfByte = new byte[paramByteBuffer.position()];
    System.arraycopy(paramByteBuffer.array(), 0, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.l
 * JD-Core Version:    0.6.1
 */