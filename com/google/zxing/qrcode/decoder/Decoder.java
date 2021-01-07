package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import java.util.Map;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

  // ERROR //
  private void correctErrors(byte[] paramArrayOfByte, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: arraylength
    //   2: istore_3
    //   3: iload_3
    //   4: newarray int
    //   6: astore 4
    //   8: iconst_0
    //   9: istore 5
    //   11: iconst_0
    //   12: istore 6
    //   14: iload 6
    //   16: iload_3
    //   17: if_icmpge +22 -> 39
    //   20: aload 4
    //   22: iload 6
    //   24: sipush 255
    //   27: aload_1
    //   28: iload 6
    //   30: baload
    //   31: iand
    //   32: iastore
    //   33: iinc 6 1
    //   36: goto -22 -> 14
    //   39: aload_1
    //   40: arraylength
    //   41: iload_2
    //   42: isub
    //   43: istore 7
    //   45: aload_0
    //   46: getfield 23	com/google/zxing/qrcode/decoder/Decoder:rsDecoder	Lcom/google/zxing/common/reedsolomon/ReedSolomonDecoder;
    //   49: aload 4
    //   51: iload 7
    //   53: invokevirtual 31	com/google/zxing/common/reedsolomon/ReedSolomonDecoder:decode	([II)V
    //   56: iload 5
    //   58: iload_2
    //   59: if_icmpge +19 -> 78
    //   62: aload_1
    //   63: iload 5
    //   65: aload 4
    //   67: iload 5
    //   69: iaload
    //   70: i2b
    //   71: bastore
    //   72: iinc 5 1
    //   75: goto -19 -> 56
    //   78: return
    //   79: invokestatic 37	com/google/zxing/ChecksumException:getChecksumInstance	()Lcom/google/zxing/ChecksumException;
    //   82: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   45	56	79	com/google/zxing/common/reedsolomon/ReedSolomonException
  }

  public DecoderResult decode(BitMatrix paramBitMatrix)
  {
    return decode(paramBitMatrix, null);
  }

  public DecoderResult decode(BitMatrix paramBitMatrix, Map<DecodeHintType, ?> paramMap)
  {
    BitMatrixParser localBitMatrixParser = new BitMatrixParser(paramBitMatrix);
    Version localVersion = localBitMatrixParser.readVersion();
    ErrorCorrectionLevel localErrorCorrectionLevel = localBitMatrixParser.readFormatInformation().getErrorCorrectionLevel();
    DataBlock[] arrayOfDataBlock = DataBlock.getDataBlocks(localBitMatrixParser.readCodewords(), localVersion, localErrorCorrectionLevel);
    int i = arrayOfDataBlock.length;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += arrayOfDataBlock[j].getNumDataCodewords();
      j++;
    }
    byte[] arrayOfByte1 = new byte[k];
    int m = arrayOfDataBlock.length;
    int n = 0;
    int i3;
    for (int i1 = 0; n < m; i1 = i3)
    {
      DataBlock localDataBlock = arrayOfDataBlock[n];
      byte[] arrayOfByte2 = localDataBlock.getCodewords();
      int i2 = localDataBlock.getNumDataCodewords();
      correctErrors(arrayOfByte2, i2);
      i3 = i1;
      int i4 = 0;
      while (i4 < i2)
      {
        int i5 = i3 + 1;
        arrayOfByte1[i3] = arrayOfByte2[i4];
        i4++;
        i3 = i5;
      }
      n++;
    }
    return DecodedBitStreamParser.decode(arrayOfByte1, localVersion, localErrorCorrectionLevel, paramMap);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
  {
    return decode(paramArrayOfBoolean, null);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean, Map<DecodeHintType, ?> paramMap)
  {
    int i = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(i);
    for (int j = 0; j < i; j++)
      for (int k = 0; k < i; k++)
        if (paramArrayOfBoolean[j][k] != 0)
          localBitMatrix.set(k, j);
    return decode(localBitMatrix, paramMap);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.Decoder
 * JD-Core Version:    0.6.1
 */