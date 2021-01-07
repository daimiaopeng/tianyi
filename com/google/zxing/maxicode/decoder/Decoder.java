package com.google.zxing.maxicode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import java.util.Map;

public final class Decoder
{
  private static final int ALL = 0;
  private static final int EVEN = 1;
  private static final int ODD = 2;
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);

  // ERROR //
  private void correctErrors(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    // Byte code:
    //   0: iload_3
    //   1: iload 4
    //   3: iadd
    //   4: istore 6
    //   6: iload 5
    //   8: ifne +9 -> 17
    //   11: iconst_1
    //   12: istore 7
    //   14: goto +6 -> 20
    //   17: iconst_2
    //   18: istore 7
    //   20: iload 6
    //   22: iload 7
    //   24: idiv
    //   25: newarray int
    //   27: astore 8
    //   29: iconst_0
    //   30: istore 9
    //   32: iconst_0
    //   33: istore 10
    //   35: iload 10
    //   37: iload 6
    //   39: if_icmpge +43 -> 82
    //   42: iload 5
    //   44: ifeq +14 -> 58
    //   47: iload 10
    //   49: iconst_2
    //   50: irem
    //   51: iload 5
    //   53: iconst_1
    //   54: isub
    //   55: if_icmpne +21 -> 76
    //   58: aload 8
    //   60: iload 10
    //   62: iload 7
    //   64: idiv
    //   65: sipush 255
    //   68: aload_1
    //   69: iload 10
    //   71: iload_2
    //   72: iadd
    //   73: baload
    //   74: iand
    //   75: iastore
    //   76: iinc 10 1
    //   79: goto -44 -> 35
    //   82: aload_0
    //   83: getfield 30	com/google/zxing/maxicode/decoder/Decoder:rsDecoder	Lcom/google/zxing/common/reedsolomon/ReedSolomonDecoder;
    //   86: aload 8
    //   88: iload 4
    //   90: iload 7
    //   92: idiv
    //   93: invokevirtual 38	com/google/zxing/common/reedsolomon/ReedSolomonDecoder:decode	([II)V
    //   96: iload 9
    //   98: iload_3
    //   99: if_icmpge +40 -> 139
    //   102: iload 5
    //   104: ifeq +14 -> 118
    //   107: iload 9
    //   109: iconst_2
    //   110: irem
    //   111: iload 5
    //   113: iconst_1
    //   114: isub
    //   115: if_icmpne +18 -> 133
    //   118: aload_1
    //   119: iload 9
    //   121: iload_2
    //   122: iadd
    //   123: aload 8
    //   125: iload 9
    //   127: iload 7
    //   129: idiv
    //   130: iaload
    //   131: i2b
    //   132: bastore
    //   133: iinc 9 1
    //   136: goto -40 -> 96
    //   139: return
    //   140: invokestatic 44	com/google/zxing/ChecksumException:getChecksumInstance	()Lcom/google/zxing/ChecksumException;
    //   143: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   82	96	140	com/google/zxing/common/reedsolomon/ReedSolomonException
  }

  public DecoderResult decode(BitMatrix paramBitMatrix)
  {
    return decode(paramBitMatrix, null);
  }

  public DecoderResult decode(BitMatrix paramBitMatrix, Map<DecodeHintType, ?> paramMap)
  {
    byte[] arrayOfByte1 = new BitMatrixParser(paramBitMatrix).readCodewords();
    correctErrors(arrayOfByte1, 0, 10, 10, 0);
    int i = 0xF & arrayOfByte1[0];
    byte[] arrayOfByte2;
    switch (i)
    {
    default:
      throw FormatException.getFormatInstance();
    case 5:
      correctErrors(arrayOfByte1, 20, 68, 56, 1);
      correctErrors(arrayOfByte1, 20, 68, 56, 2);
      arrayOfByte2 = new byte[78];
      break;
    case 2:
    case 3:
    case 4:
      correctErrors(arrayOfByte1, 20, 84, 40, 1);
      correctErrors(arrayOfByte1, 20, 84, 40, 2);
      arrayOfByte2 = new byte[94];
    }
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, 10);
    System.arraycopy(arrayOfByte1, 20, arrayOfByte2, 10, arrayOfByte2.length - 10);
    return DecodedBitStreamParser.decode(arrayOfByte2, i);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.maxicode.decoder.Decoder
 * JD-Core Version:    0.6.1
 */