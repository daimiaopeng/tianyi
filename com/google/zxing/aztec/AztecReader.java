package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.List;
import java.util.Map;

public final class AztecReader
  implements Reader
{
  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    AztecDetectorResult localAztecDetectorResult = new Detector(paramBinaryBitmap.getBlackMatrix()).detect();
    ResultPoint[] arrayOfResultPoint = localAztecDetectorResult.getPoints();
    if (paramMap != null)
    {
      ResultPointCallback localResultPointCallback = (ResultPointCallback)paramMap.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
      if (localResultPointCallback != null)
      {
        int i = arrayOfResultPoint.length;
        for (int j = 0; j < i; j++)
          localResultPointCallback.foundPossibleResultPoint(arrayOfResultPoint[j]);
      }
    }
    DecoderResult localDecoderResult = new Decoder().decode(localAztecDetectorResult);
    Result localResult = new Result(localDecoderResult.getText(), localDecoderResult.getRawBytes(), arrayOfResultPoint, BarcodeFormat.AZTEC);
    List localList = localDecoderResult.getByteSegments();
    if (localList != null)
      localResult.putMetadata(ResultMetadataType.BYTE_SEGMENTS, localList);
    String str = localDecoderResult.getECLevel();
    if (str != null)
      localResult.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
    return localResult;
  }

  public void reset()
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.aztec.AztecReader
 * JD-Core Version:    0.6.1
 */