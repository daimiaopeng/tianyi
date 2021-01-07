package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser
{
  private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

  static boolean isBasicallyValidEmailAddress(String paramString)
  {
    boolean bool;
    if ((paramString != null) && (ATEXT_ALPHANUMERIC.matcher(paramString).matches()) && (paramString.indexOf('@') >= 0))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public EmailAddressParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    if (!str1.startsWith("MATMSG:"))
      return null;
    String[] arrayOfString = matchDoCoMoPrefixedField("TO:", str1, true);
    if (arrayOfString == null)
      return null;
    String str2 = arrayOfString[0];
    if (!isBasicallyValidEmailAddress(str2))
      return null;
    String str3 = matchSingleDoCoMoPrefixedField("SUB:", str1, false);
    String str4 = matchSingleDoCoMoPrefixedField("BODY:", str1, false);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mailto:");
    localStringBuilder.append(str2);
    return new EmailAddressParsedResult(str2, str3, str4, localStringBuilder.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.EmailDoCoMoResultParser
 * JD-Core Version:    0.6.1
 */