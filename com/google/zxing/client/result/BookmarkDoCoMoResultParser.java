package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser
{
  public URIParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if (!str1.startsWith("MEBKM:"))
      return null;
    String str2 = matchSingleDoCoMoPrefixedField("TITLE:", str1, true);
    String[] arrayOfString = matchDoCoMoPrefixedField("URL:", str1, true);
    if (arrayOfString == null)
      return null;
    String str3 = arrayOfString[0];
    boolean bool = URIResultParser.isBasicallyValidURI(str3);
    URIParsedResult localURIParsedResult = null;
    if (bool)
      localURIParsedResult = new URIParsedResult(str3, str2);
    return localURIParsedResult;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.BookmarkDoCoMoResultParser
 * JD-Core Version:    0.6.1
 */