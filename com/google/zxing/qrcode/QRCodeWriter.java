package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter
  implements Writer
{
  private static final int QUIET_ZONE_SIZE = 4;

  private static BitMatrix renderResult(QRCode paramQRCode, int paramInt1, int paramInt2, int paramInt3)
  {
    ByteMatrix localByteMatrix = paramQRCode.getMatrix();
    if (localByteMatrix == null)
      throw new IllegalStateException();
    int i = localByteMatrix.getWidth();
    int j = localByteMatrix.getHeight();
    int k = paramInt3 << 1;
    int m = i + k;
    int n = k + j;
    int i1 = Math.max(paramInt1, m);
    int i2 = Math.max(paramInt2, n);
    int i3 = Math.min(i1 / m, i2 / n);
    int i4 = (i1 - i * i3) / 2;
    int i5 = (i2 - j * i3) / 2;
    BitMatrix localBitMatrix = new BitMatrix(i1, i2);
    int i6 = 0;
    while (i6 < j)
    {
      int i7 = i4;
      int i8 = 0;
      while (i8 < i)
      {
        if (localByteMatrix.get(i8, i6) == 1)
          localBitMatrix.setRegion(i7, i5, i3, i3);
        i8++;
        i7 += i3;
      }
      i6++;
      i5 += i3;
    }
    return localBitMatrix;
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramString.length() == 0)
      throw new IllegalArgumentException("Found empty contents");
    if (paramBarcodeFormat != BarcodeFormat.QR_CODE)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Can only encode QR_CODE, but got ");
      localStringBuilder1.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder1.toString());
    }
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      Object localObject = ErrorCorrectionLevel.L;
      int i = 4;
      if (paramMap != null)
      {
        ErrorCorrectionLevel localErrorCorrectionLevel = (ErrorCorrectionLevel)paramMap.get(EncodeHintType.ERROR_CORRECTION);
        if (localErrorCorrectionLevel != null)
          localObject = localErrorCorrectionLevel;
        Integer localInteger = (Integer)paramMap.get(EncodeHintType.MARGIN);
        if (localInteger != null)
          i = localInteger.intValue();
      }
      return renderResult(Encoder.encode(paramString, (ErrorCorrectionLevel)localObject, paramMap), paramInt1, paramInt2, i);
    }
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Requested dimensions are too small: ");
    localStringBuilder2.append(paramInt1);
    localStringBuilder2.append('x');
    localStringBuilder2.append(paramInt2);
    throw new IllegalArgumentException(localStringBuilder2.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.QRCodeWriter
 * JD-Core Version:    0.6.1
 */