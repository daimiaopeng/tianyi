package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;

public final class EmailAddressResultParser extends ResultParser
{
  public EmailAddressParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    boolean bool = str1.startsWith("mailto:");
    String str2 = null;
    if ((!bool) && (!str1.startsWith("MAILTO:")))
    {
      if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(str1))
        return null;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("mailto:");
      localStringBuilder.append(str1);
      return new EmailAddressParsedResult(str1, null, null, localStringBuilder.toString());
    }
    String str3 = str1.substring(7);
    int i = str3.indexOf('?');
    if (i >= 0)
      str3 = str3.substring(0, i);
    String str4 = urlDecode(str3);
    Map localMap = parseNameValuePairs(str1);
    String str5;
    if (localMap != null)
    {
      if (str4.length() == 0)
        str4 = (String)localMap.get("to");
      str2 = (String)localMap.get("subject");
      str5 = (String)localMap.get("body");
    }
    else
    {
      str5 = null;
    }
    return new EmailAddressParsedResult(str4, str2, str5, str1);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.EmailAddressResultParser
 * JD-Core Version:    0.6.1
 */