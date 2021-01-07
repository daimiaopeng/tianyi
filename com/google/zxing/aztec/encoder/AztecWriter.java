package com.google.zxing.aztec.encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter
  implements Writer
{
  private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

  private static BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, Charset paramCharset, int paramInt)
  {
    if (paramBarcodeFormat != BarcodeFormat.AZTEC)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode AZTEC, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return Encoder.encode(paramString.getBytes(paramCharset), paramInt).getMatrix();
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    return encode(paramString, paramBarcodeFormat, DEFAULT_CHARSET, 33);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    String str = (String)paramMap.get(EncodeHintType.CHARACTER_SET);
    Number localNumber = (Number)paramMap.get(EncodeHintType.ERROR_CORRECTION);
    Charset localCharset;
    if (str == null)
      localCharset = DEFAULT_CHARSET;
    else
      localCharset = Charset.forName(str);
    int i;
    if (localNumber == null)
      i = 33;
    else
      i = localNumber.intValue();
    return encode(paramString, paramBarcodeFormat, localCharset, i);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.aztec.encoder.AztecWriter
 * JD-Core Version:    0.6.1
 */