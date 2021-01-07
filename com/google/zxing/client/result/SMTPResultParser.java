package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMTPResultParser extends ResultParser
{
  public EmailAddressParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    boolean bool = str1.startsWith("smtp:");
    String str2 = null;
    if ((!bool) && (!str1.startsWith("SMTP:")))
      return null;
    String str3 = str1.substring(5);
    int i = str3.indexOf(':');
    String str4;
    if (i >= 0)
    {
      String str5 = str3.substring(i + 1);
      str3 = str3.substring(0, i);
      int j = str5.indexOf(':');
      if (j >= 0)
      {
        str2 = str5.substring(j + 1);
        str4 = str5.substring(0, j);
      }
      else
      {
        str4 = str5;
        str2 = null;
      }
    }
    else
    {
      str4 = null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mailto:");
    localStringBuilder.append(str3);
    return new EmailAddressParsedResult(str3, str4, str2, localStringBuilder.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMTPResultParser
 * JD-Core Version:    0.6.1
 */