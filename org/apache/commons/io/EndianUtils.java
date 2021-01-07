package org.apache.commons.io;

import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;

public class EndianUtils
{
  private static int read(InputStream paramInputStream)
  {
    int i = paramInputStream.read();
    if (-1 == i)
      throw new EOFException("Unexpected EOF reached");
    return i;
  }

  public static double readSwappedDouble(InputStream paramInputStream)
  {
    return Double.longBitsToDouble(readSwappedLong(paramInputStream));
  }

  public static double readSwappedDouble(byte[] paramArrayOfByte, int paramInt)
  {
    return Double.longBitsToDouble(readSwappedLong(paramArrayOfByte, paramInt));
  }

  public static float readSwappedFloat(InputStream paramInputStream)
  {
    return Float.intBitsToFloat(readSwappedInteger(paramInputStream));
  }

  public static float readSwappedFloat(byte[] paramArrayOfByte, int paramInt)
  {
    return Float.intBitsToFloat(readSwappedInteger(paramArrayOfByte, paramInt));
  }

  public static int readSwappedInteger(InputStream paramInputStream)
  {
    int i = read(paramInputStream);
    int j = read(paramInputStream);
    int k = read(paramInputStream);
    int m = read(paramInputStream);
    return ((i & 0xFF) << 0) + ((j & 0xFF) << 8) + ((k & 0xFF) << 16) + ((m & 0xFF) << 24);
  }

  public static int readSwappedInteger(byte[] paramArrayOfByte, int paramInt)
  {
    return ((0xFF & paramArrayOfByte[(paramInt + 0)]) << 0) + ((0xFF & paramArrayOfByte[(paramInt + 1)]) << 8) + ((0xFF & paramArrayOfByte[(paramInt + 2)]) << 16) + ((0xFF & paramArrayOfByte[(paramInt + 3)]) << 24);
  }

  public static long readSwappedLong(InputStream paramInputStream)
  {
    byte[] arrayOfByte = new byte[8];
    for (int i = 0; i < 8; i++)
      arrayOfByte[i] = (byte)read(paramInputStream);
    return readSwappedLong(arrayOfByte, 0);
  }

  public static long readSwappedLong(byte[] paramArrayOfByte, int paramInt)
  {
    long l = ((0xFF & paramArrayOfByte[(paramInt + 0)]) << 0) + ((0xFF & paramArrayOfByte[(paramInt + 1)]) << 8) + ((0xFF & paramArrayOfByte[(paramInt + 2)]) << 16) + ((0xFF & paramArrayOfByte[(paramInt + 3)]) << 24);
    return (((0xFF & paramArrayOfByte[(paramInt + 4)]) << 0) + ((0xFF & paramArrayOfByte[(paramInt + 5)]) << 8) + ((0xFF & paramArrayOfByte[(paramInt + 6)]) << 16) + ((0xFF & paramArrayOfByte[(paramInt + 7)]) << 24) << 32) + (l & 0xFFFFFFFF);
  }

  public static short readSwappedShort(InputStream paramInputStream)
  {
    return (short)(((0xFF & read(paramInputStream)) << 0) + ((0xFF & read(paramInputStream)) << 8));
  }

  public static short readSwappedShort(byte[] paramArrayOfByte, int paramInt)
  {
    return (short)(((0xFF & paramArrayOfByte[(paramInt + 0)]) << 0) + ((0xFF & paramArrayOfByte[(paramInt + 1)]) << 8));
  }

  public static long readSwappedUnsignedInteger(InputStream paramInputStream)
  {
    int i = read(paramInputStream);
    int j = read(paramInputStream);
    int k = read(paramInputStream);
    int m = read(paramInputStream);
    long l = ((i & 0xFF) << 0) + ((j & 0xFF) << 8) + ((k & 0xFF) << 16);
    return (m & 0xFF << 24) + (l & 0xFFFFFFFF);
  }

  public static long readSwappedUnsignedInteger(byte[] paramArrayOfByte, int paramInt)
  {
    long l = ((0xFF & paramArrayOfByte[(paramInt + 0)]) << 0) + ((0xFF & paramArrayOfByte[(paramInt + 1)]) << 8) + ((0xFF & paramArrayOfByte[(paramInt + 2)]) << 16);
    return (0xFF & paramArrayOfByte[(paramInt + 3)] << 24) + (l & 0xFFFFFFFF);
  }

  public static int readSwappedUnsignedShort(InputStream paramInputStream)
  {
    int i = read(paramInputStream);
    int j = read(paramInputStream);
    return ((i & 0xFF) << 0) + ((j & 0xFF) << 8);
  }

  public static int readSwappedUnsignedShort(byte[] paramArrayOfByte, int paramInt)
  {
    return ((0xFF & paramArrayOfByte[(paramInt + 0)]) << 0) + ((0xFF & paramArrayOfByte[(paramInt + 1)]) << 8);
  }

  public static double swapDouble(double paramDouble)
  {
    return Double.longBitsToDouble(swapLong(Double.doubleToLongBits(paramDouble)));
  }

  public static float swapFloat(float paramFloat)
  {
    return Float.intBitsToFloat(swapInteger(Float.floatToIntBits(paramFloat)));
  }

  public static int swapInteger(int paramInt)
  {
    return ((0xFF & paramInt >> 0) << 24) + ((0xFF & paramInt >> 8) << 16) + ((0xFF & paramInt >> 16) << 8) + ((0xFF & paramInt >> 24) << 0);
  }

  public static long swapLong(long paramLong)
  {
    return ((0xFF & paramLong >> 0) << 56) + ((0xFF & paramLong >> 8) << 48) + ((0xFF & paramLong >> 16) << 40) + ((0xFF & paramLong >> 24) << 32) + ((0xFF & paramLong >> 32) << 24) + ((0xFF & paramLong >> 40) << 16) + ((0xFF & paramLong >> 48) << 8) + ((0xFF & paramLong >> 56) << 0);
  }

  public static short swapShort(short paramShort)
  {
    return (short)(((0xFF & paramShort >> 0) << 8) + ((0xFF & paramShort >> 8) << 0));
  }

  public static void writeSwappedDouble(OutputStream paramOutputStream, double paramDouble)
  {
    writeSwappedLong(paramOutputStream, Double.doubleToLongBits(paramDouble));
  }

  public static void writeSwappedDouble(byte[] paramArrayOfByte, int paramInt, double paramDouble)
  {
    writeSwappedLong(paramArrayOfByte, paramInt, Double.doubleToLongBits(paramDouble));
  }

  public static void writeSwappedFloat(OutputStream paramOutputStream, float paramFloat)
  {
    writeSwappedInteger(paramOutputStream, Float.floatToIntBits(paramFloat));
  }

  public static void writeSwappedFloat(byte[] paramArrayOfByte, int paramInt, float paramFloat)
  {
    writeSwappedInteger(paramArrayOfByte, paramInt, Float.floatToIntBits(paramFloat));
  }

  public static void writeSwappedInteger(OutputStream paramOutputStream, int paramInt)
  {
    paramOutputStream.write((byte)(0xFF & paramInt >> 0));
    paramOutputStream.write((byte)(0xFF & paramInt >> 8));
    paramOutputStream.write((byte)(0xFF & paramInt >> 16));
    paramOutputStream.write((byte)(0xFF & paramInt >> 24));
  }

  public static void writeSwappedInteger(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[(paramInt1 + 0)] = (byte)(0xFF & paramInt2 >> 0);
    paramArrayOfByte[(paramInt1 + 1)] = (byte)(0xFF & paramInt2 >> 8);
    paramArrayOfByte[(paramInt1 + 2)] = (byte)(0xFF & paramInt2 >> 16);
    paramArrayOfByte[(paramInt1 + 3)] = (byte)(0xFF & paramInt2 >> 24);
  }

  public static void writeSwappedLong(OutputStream paramOutputStream, long paramLong)
  {
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 0));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 8));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 16));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 24));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 32));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 40));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 48));
    paramOutputStream.write((byte)(int)(0xFF & paramLong >> 56));
  }

  public static void writeSwappedLong(byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    paramArrayOfByte[(paramInt + 0)] = (byte)(int)(0xFF & paramLong >> 0);
    paramArrayOfByte[(paramInt + 1)] = (byte)(int)(0xFF & paramLong >> 8);
    paramArrayOfByte[(paramInt + 2)] = (byte)(int)(0xFF & paramLong >> 16);
    paramArrayOfByte[(paramInt + 3)] = (byte)(int)(0xFF & paramLong >> 24);
    paramArrayOfByte[(paramInt + 4)] = (byte)(int)(0xFF & paramLong >> 32);
    paramArrayOfByte[(paramInt + 5)] = (byte)(int)(0xFF & paramLong >> 40);
    paramArrayOfByte[(paramInt + 6)] = (byte)(int)(0xFF & paramLong >> 48);
    paramArrayOfByte[(paramInt + 7)] = (byte)(int)(0xFF & paramLong >> 56);
  }

  public static void writeSwappedShort(OutputStream paramOutputStream, short paramShort)
  {
    paramOutputStream.write((byte)(0xFF & paramShort >> 0));
    paramOutputStream.write((byte)(0xFF & paramShort >> 8));
  }

  public static void writeSwappedShort(byte[] paramArrayOfByte, int paramInt, short paramShort)
  {
    paramArrayOfByte[(paramInt + 0)] = (byte)(0xFF & paramShort >> 0);
    paramArrayOfByte[(paramInt + 1)] = (byte)(0xFF & paramShort >> 8);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.EndianUtils
 * JD-Core Version:    0.6.1
 */