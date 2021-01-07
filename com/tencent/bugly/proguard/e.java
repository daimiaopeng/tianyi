package com.tencent.bugly.proguard;

public final class e
{
  private static final char[] a = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };

  public static String a(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0))
    {
      char[] arrayOfChar = new char[2 * paramArrayOfByte.length];
      for (int i = 0; i < paramArrayOfByte.length; i++)
      {
        int j = paramArrayOfByte[i];
        int k = i * 2;
        arrayOfChar[(k + 1)] = a[(j & 0xF)];
        int m = (byte)(j >>> 4);
        arrayOfChar[k] = a[(m & 0xF)];
      }
      return new String(arrayOfChar);
    }
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.e
 * JD-Core Version:    0.6.1
 */