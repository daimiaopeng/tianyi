package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public final class ISBNResultParser extends ResultParser
{
  public ISBNParsedResult parse(Result paramResult)
  {
    if (paramResult.getBarcodeFormat() != BarcodeFormat.EAN_13)
      return null;
    String str = getMassagedText(paramResult);
    if (str.length() != 13)
      return null;
    if ((!str.startsWith("978")) && (!str.startsWith("979")))
      return null;
    return new ISBNParsedResult(str);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ISBNResultParser
 * JD-Core Version:    0.6.1
 */