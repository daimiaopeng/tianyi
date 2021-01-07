package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIResultParser extends ResultParser
{
  private static final String ALPHANUM_PART = "[a-zA-Z0-9\\-]";
  private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z0-9\\-]{2,}(:\\d{1,5})?(/|\\?|$)");
  private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z0-9]{2,}:");

  static boolean isBasicallyValidURI(String paramString)
  {
    if (paramString.contains(" "))
      return false;
    Matcher localMatcher1 = URL_WITH_PROTOCOL_PATTERN.matcher(paramString);
    if ((localMatcher1.find()) && (localMatcher1.start() == 0))
      return true;
    Matcher localMatcher2 = URL_WITHOUT_PROTOCOL_PATTERN.matcher(paramString);
    boolean bool1 = localMatcher2.find();
    boolean bool2 = false;
    if (bool1)
    {
      int i = localMatcher2.start();
      bool2 = false;
      if (i == 0)
        bool2 = true;
    }
    return bool2;
  }

  public URIParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    if ((!str1.startsWith("URL:")) && (!str1.startsWith("URI:")))
    {
      String str2 = str1.trim();
      URIParsedResult localURIParsedResult;
      if (isBasicallyValidURI(str2))
        localURIParsedResult = new URIParsedResult(str2, null);
      else
        localURIParsedResult = null;
      return localURIParsedResult;
    }
    return new URIParsedResult(str1.substring(4).trim(), null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URIResultParser
 * JD-Core Version:    0.6.1
 */