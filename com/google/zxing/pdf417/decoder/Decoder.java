package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;

public final class Decoder
{
  private static final int MAX_EC_CODEWORDS = 512;
  private static final int MAX_ERRORS = 3;
  private final ErrorCorrection errorCorrection = new ErrorCorrection();

  private void correctErrors(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    if ((paramArrayOfInt2.length <= 3 + paramInt / 2) && (paramInt >= 0) && (paramInt <= 512))
    {
      this.errorCorrection.decode(paramArrayOfInt1, paramInt, paramArrayOfInt2);
      return;
    }
    throw ChecksumException.getChecksumInstance();
  }

  private static void verifyCodewordCount(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt.length < 4)
      throw FormatException.getFormatInstance();
    int i = paramArrayOfInt[0];
    if (i > paramArrayOfInt.length)
      throw FormatException.getFormatInstance();
    if (i == 0)
      if (paramInt < paramArrayOfInt.length)
        paramArrayOfInt[0] = (paramArrayOfInt.length - paramInt);
      else
        throw FormatException.getFormatInstance();
  }

  public DecoderResult decode(BitMatrix paramBitMatrix)
  {
    BitMatrixParser localBitMatrixParser = new BitMatrixParser(paramBitMatrix);
    int[] arrayOfInt = localBitMatrixParser.readCodewords();
    if (arrayOfInt.length == 0)
      throw FormatException.getFormatInstance();
    int i = 1 << 1 + localBitMatrixParser.getECLevel();
    correctErrors(arrayOfInt, localBitMatrixParser.getErasures(), i);
    verifyCodewordCount(arrayOfInt, i);
    return DecodedBitStreamParser.decode(arrayOfInt);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
  {
    int i = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(i);
    for (int j = 0; j < i; j++)
      for (int k = 0; k < i; k++)
        if (paramArrayOfBoolean[k][j] != 0)
          localBitMatrix.set(k, j);
    return decode(localBitMatrix);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.decoder.Decoder
 * JD-Core Version:    0.6.1
 */