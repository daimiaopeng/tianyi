package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class TelResultParser extends ResultParser
{
  public TelParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    if ((!str1.startsWith("tel:")) && (!str1.startsWith("TEL:")))
      return null;
    String str2;
    if (str1.startsWith("TEL:"))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("tel:");
      localStringBuilder.append(str1.substring(4));
      str2 = localStringBuilder.toString();
    }
    else
    {
      str2 = str1;
    }
    int i = str1.indexOf('?', 4);
    String str3;
    if (i < 0)
      str3 = str1.substring(4);
    else
      str3 = str1.substring(4, i);
    return new TelParsedResult(str3, str2, null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.TelResultParser
 * JD-Core Version:    0.6.1
 */