package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class URLTOResultParser extends ResultParser
{
  public URIParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    if ((!str1.startsWith("urlto:")) && (!str1.startsWith("URLTO:")))
      return null;
    int i = str1.indexOf(':', 6);
    if (i < 0)
      return null;
    String str2;
    if (i <= 6)
      str2 = null;
    else
      str2 = str1.substring(6, i);
    return new URIParsedResult(str1.substring(i + 1), str2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URLTOResultParser
 * JD-Core Version:    0.6.1
 */