package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import java.util.Map;

public final class PDF417Reader
  implements Reader
{
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();

  private static BitMatrix extractPureBits(BitMatrix paramBitMatrix)
  {
    int[] arrayOfInt1 = paramBitMatrix.getTopLeftOnBit();
    int[] arrayOfInt2 = paramBitMatrix.getBottomRightOnBit();
    if ((arrayOfInt1 != null) && (arrayOfInt2 != null))
    {
      int i = moduleSize(arrayOfInt1, paramBitMatrix);
      int j = arrayOfInt1[1];
      int k = arrayOfInt2[1];
      int m = findPatternStart(arrayOfInt1[0], j, paramBitMatrix);
      int n = (1 + (findPatternEnd(arrayOfInt1[0], j, paramBitMatrix) - m)) / i;
      int i1 = (1 + (k - j)) / i;
      if ((n > 0) && (i1 > 0))
      {
        int i2 = i >> 1;
        int i3 = j + i2;
        int i4 = m + i2;
        BitMatrix localBitMatrix = new BitMatrix(n, i1);
        for (int i5 = 0; i5 < i1; i5++)
        {
          int i6 = i3 + i5 * i;
          for (int i7 = 0; i7 < n; i7++)
            if (paramBitMatrix.get(i4 + i7 * i, i6))
              localBitMatrix.set(i7, i5);
        }
        return localBitMatrix;
      }
      throw NotFoundException.getNotFoundInstance();
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int findPatternEnd(int paramInt1, int paramInt2, BitMatrix paramBitMatrix)
  {
    int i = paramBitMatrix.getWidth();
    int j = 1;
    for (int k = i - j; (k > paramInt1) && (!paramBitMatrix.get(k, paramInt2)); k--);
    int m = 0;
    while ((k > paramInt1) && (m < 9))
    {
      k--;
      int n = paramBitMatrix.get(k, paramInt2);
      if (j != n)
        m++;
      j = n;
    }
    if (k == paramInt1)
      throw NotFoundException.getNotFoundInstance();
    return k;
  }

  private static int findPatternStart(int paramInt1, int paramInt2, BitMatrix paramBitMatrix)
  {
    int i = paramBitMatrix.getWidth();
    int j = 0;
    int k;
    boolean bool2;
    for (boolean bool1 = true; ; bool1 = bool2)
    {
      k = i - 1;
      if ((paramInt1 >= k) || (j >= 8))
        break;
      paramInt1++;
      bool2 = paramBitMatrix.get(paramInt1, paramInt2);
      if (bool1 != bool2)
        j++;
    }
    if (paramInt1 == k)
      throw NotFoundException.getNotFoundInstance();
    return paramInt1;
  }

  private static int moduleSize(int[] paramArrayOfInt, BitMatrix paramBitMatrix)
  {
    int i = paramArrayOfInt[0];
    int j = paramArrayOfInt[1];
    int k = paramBitMatrix.getWidth();
    while ((i < k) && (paramBitMatrix.get(i, j)))
      i++;
    if (i == k)
      throw NotFoundException.getNotFoundInstance();
    int m = i - paramArrayOfInt[0] >>> 3;
    if (m == 0)
      throw NotFoundException.getNotFoundInstance();
    return m;
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    Object localObject;
    ResultPoint[] arrayOfResultPoint;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      BitMatrix localBitMatrix = extractPureBits(paramBinaryBitmap.getBlackMatrix());
      localObject = this.decoder.decode(localBitMatrix);
      arrayOfResultPoint = NO_POINTS;
    }
    else
    {
      DetectorResult localDetectorResult = new Detector(paramBinaryBitmap).detect();
      DecoderResult localDecoderResult = this.decoder.decode(localDetectorResult.getBits());
      arrayOfResultPoint = localDetectorResult.getPoints();
      localObject = localDecoderResult;
    }
    return new Result(((DecoderResult)localObject).getText(), ((DecoderResult)localObject).getRawBytes(), arrayOfResultPoint, BarcodeFormat.PDF_417);
  }

  public void reset()
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.PDF417Reader
 * JD-Core Version:    0.6.1
 */