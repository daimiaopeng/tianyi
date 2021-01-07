package org.apache.commons.io;

import java.io.OutputStream;

public class HexDump
{
  public static final String EOL = System.getProperty("line.separator");
  private static final StringBuffer _cbuffer = new StringBuffer(2);
  private static final char[] _hexcodes = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static final StringBuffer _lbuffer = new StringBuffer(8);
  private static final int[] _shifts = { 28, 24, 20, 16, 12, 8, 4, 0 };

  private static StringBuffer dump(byte paramByte)
  {
    StringBuffer localStringBuffer = _cbuffer;
    int i = 0;
    localStringBuffer.setLength(0);
    while (i < 2)
    {
      _cbuffer.append(_hexcodes[(0xF & paramByte >> _shifts[(i + 6)])]);
      i++;
    }
    return _cbuffer;
  }

  private static StringBuffer dump(long paramLong)
  {
    StringBuffer localStringBuffer = _lbuffer;
    int i = 0;
    localStringBuffer.setLength(0);
    while (i < 8)
    {
      _lbuffer.append(_hexcodes[(0xF & (int)(paramLong >> _shifts[i]))]);
      i++;
    }
    return _lbuffer;
  }

  public static void dump(byte[] paramArrayOfByte, long paramLong, OutputStream paramOutputStream, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < paramArrayOfByte.length))
    {
      if (paramOutputStream == null)
        throw new IllegalArgumentException("cannot write to nullstream");
      long l1 = paramLong + paramInt;
      StringBuffer localStringBuffer2 = new StringBuffer(74);
      while (paramInt < paramArrayOfByte.length)
      {
        int i = paramArrayOfByte.length - paramInt;
        if (i > 16)
          i = 16;
        localStringBuffer2.append(dump(l1));
        localStringBuffer2.append(' ');
        for (int j = 0; j < 16; j++)
        {
          if (j < i)
            localStringBuffer2.append(dump(paramArrayOfByte[(j + paramInt)]));
          else
            localStringBuffer2.append("  ");
          localStringBuffer2.append(' ');
        }
        for (int k = 0; k < i; k++)
        {
          int m = k + paramInt;
          if ((paramArrayOfByte[m] >= 32) && (paramArrayOfByte[m] < 127))
            localStringBuffer2.append((char)paramArrayOfByte[m]);
          else
            localStringBuffer2.append('.');
        }
        localStringBuffer2.append(EOL);
        paramOutputStream.write(localStringBuffer2.toString().getBytes());
        paramOutputStream.flush();
        localStringBuffer2.setLength(0);
        long l2 = l1 + i;
        paramInt += 16;
        l1 = l2;
      }
      return;
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("illegal index: ");
    localStringBuffer1.append(paramInt);
    localStringBuffer1.append(" into array of length ");
    localStringBuffer1.append(paramArrayOfByte.length);
    throw new ArrayIndexOutOfBoundsException(localStringBuffer1.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.HexDump
 * JD-Core Version:    0.6.1
 */