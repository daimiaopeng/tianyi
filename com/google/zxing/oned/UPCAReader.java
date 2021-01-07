package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class UPCAReader extends UPCEANReader
{
  private final UPCEANReader ean13Reader = new EAN13Reader();

  private static Result maybeReturnResult(Result paramResult)
  {
    String str = paramResult.getText();
    if (str.charAt(0) == '0')
      return new Result(str.substring(1), null, paramResult.getResultPoints(), BarcodeFormat.UPC_A);
    throw FormatException.getFormatInstance();
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    return maybeReturnResult(this.ean13Reader.decode(paramBinaryBitmap));
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    return maybeReturnResult(this.ean13Reader.decode(paramBinaryBitmap, paramMap));
  }

  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
  {
    return this.ean13Reader.decodeMiddle(paramBitArray, paramArrayOfInt, paramStringBuilder);
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    return maybeReturnResult(this.ean13Reader.decodeRow(paramInt, paramBitArray, paramMap));
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt, Map<DecodeHintType, ?> paramMap)
  {
    return maybeReturnResult(this.ean13Reader.decodeRow(paramInt, paramBitArray, paramArrayOfInt, paramMap));
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.UPC_A;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCAReader
 * JD-Core Version:    0.6.1
 */