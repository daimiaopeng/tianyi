package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class SMSMMSResultParser extends ResultParser
{
  private static void addNumberVia(Collection<String> paramCollection1, Collection<String> paramCollection2, String paramString)
  {
    int i = paramString.indexOf(';');
    if (i < 0)
    {
      paramCollection1.add(paramString);
      paramCollection2.add(null);
    }
    else
    {
      paramCollection1.add(paramString.substring(0, i));
      String str1 = paramString.substring(i + 1);
      boolean bool = str1.startsWith("via=");
      String str2 = null;
      if (bool)
        str2 = str1.substring(4);
      paramCollection2.add(str2);
    }
  }

  public SMSParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    boolean bool = str1.startsWith("sms:");
    String str2 = null;
    if ((!bool) && (!str1.startsWith("SMS:")) && (!str1.startsWith("mms:")) && (!str1.startsWith("MMS:")))
      return null;
    Map localMap = parseNameValuePairs(str1);
    int i = 0;
    String str3;
    if ((localMap != null) && (!localMap.isEmpty()))
    {
      str2 = (String)localMap.get("subject");
      str3 = (String)localMap.get("body");
      i = 1;
    }
    else
    {
      str3 = null;
    }
    int j = str1.indexOf('?', 4);
    String str4;
    if ((j >= 0) && (i != 0))
      str4 = str1.substring(4, j);
    else
      str4 = str1.substring(4);
    int k = -1;
    ArrayList localArrayList1 = new ArrayList(1);
    ArrayList localArrayList2 = new ArrayList(1);
    int m;
    while (true)
    {
      m = k + 1;
      int n = str4.indexOf(',', m);
      if (n <= k)
        break;
      addNumberVia(localArrayList1, localArrayList2, str4.substring(m, n));
      k = n;
    }
    addNumberVia(localArrayList1, localArrayList2, str4.substring(m));
    return new SMSParsedResult((String[])localArrayList1.toArray(new String[localArrayList1.size()]), (String[])localArrayList2.toArray(new String[localArrayList2.size()]), str2, str3);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSMMSResultParser
 * JD-Core Version:    0.6.1
 */