package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

public class QRCodeReader
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
      float f = moduleSize(arrayOfInt1, paramBitMatrix);
      int i = arrayOfInt1[1];
      int j = arrayOfInt2[1];
      int k = arrayOfInt1[0];
      int m = arrayOfInt2[0];
      int n = j - i;
      if (n != m - k)
        m = k + n;
      int i1 = Math.round(1 + (m - k) / f);
      int i2 = Math.round(n + 1 / f);
      if ((i1 > 0) && (i2 > 0))
      {
        if (i2 != i1)
          throw NotFoundException.getNotFoundInstance();
        int i3 = (int)(f / 2.0F);
        int i4 = i + i3;
        int i5 = k + i3;
        BitMatrix localBitMatrix = new BitMatrix(i1, i2);
        for (int i6 = 0; i6 < i2; i6++)
        {
          int i7 = i4 + (int)(f * i6);
          for (int i8 = 0; i8 < i1; i8++)
            if (paramBitMatrix.get(i5 + (int)(f * i8), i7))
              localBitMatrix.set(i8, i6);
        }
        return localBitMatrix;
      }
      throw NotFoundException.getNotFoundInstance();
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static float moduleSize(int[] paramArrayOfInt, BitMatrix paramBitMatrix)
  {
    int i = paramBitMatrix.getHeight();
    int j = paramBitMatrix.getWidth();
    int k = paramArrayOfInt[0];
    int m = 1;
    int n = paramArrayOfInt[m];
    int i1 = 0;
    while ((k < j) && (n < i))
    {
      if (m != paramBitMatrix.get(k, n))
      {
        i1++;
        if (i1 == 5)
          break;
        m ^= 1;
      }
      k++;
      n++;
    }
    if ((k != j) && (n != i))
      return k - paramArrayOfInt[0] / 7.0F;
    throw NotFoundException.getNotFoundInstance();
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    return decode(paramBinaryBitmap, null);
  }

  public final Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    Object localObject;
    ResultPoint[] arrayOfResultPoint;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      BitMatrix localBitMatrix = extractPureBits(paramBinaryBitmap.getBlackMatrix());
      localObject = this.decoder.decode(localBitMatrix, paramMap);
      arrayOfResultPoint = NO_POINTS;
    }
    else
    {
      DetectorResult localDetectorResult = new Detector(paramBinaryBitmap.getBlackMatrix()).detect(paramMap);
      DecoderResult localDecoderResult = this.decoder.decode(localDetectorResult.getBits(), paramMap);
      arrayOfResultPoint = localDetectorResult.getPoints();
      localObject = localDecoderResult;
    }
    Result localResult = new Result(((DecoderResult)localObject).getText(), ((DecoderResult)localObject).getRawBytes(), arrayOfResultPoint, BarcodeFormat.QR_CODE);
    List localList = ((DecoderResult)localObject).getByteSegments();
    if (localList != null)
      localResult.putMetadata(ResultMetadataType.BYTE_SEGMENTS, localList);
    String str = ((DecoderResult)localObject).getECLevel();
    if (str != null)
      localResult.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
    return localResult;
  }

  protected final Decoder getDecoder()
  {
    return this.decoder;
  }

  public void reset()
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.QRCodeReader
 * JD-Core Version:    0.6.1
 */