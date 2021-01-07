package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class AddressBookAUResultParser extends ResultParser
{
  private static String[] matchMultipleValuePrefix(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    int i = 1;
    ArrayList localArrayList = null;
    while (i <= paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append(i);
      localStringBuilder.append(':');
      String str = matchSinglePrefixedField(localStringBuilder.toString(), paramString2, '\r', paramBoolean);
      if (str == null)
        break;
      if (localArrayList == null)
        localArrayList = new ArrayList(paramInt);
      localArrayList.add(str);
      i++;
    }
    if (localArrayList == null)
      return null;
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }

  public AddressBookParsedResult parse(Result paramResult)
  {
    String str1 = getMassagedText(paramResult);
    if ((str1.contains("MEMORY")) && (str1.contains("\r\n")))
    {
      String str2 = matchSinglePrefixedField("NAME1:", str1, '\r', true);
      String str3 = matchSinglePrefixedField("NAME2:", str1, '\r', true);
      String[] arrayOfString1 = matchMultipleValuePrefix("TEL", 3, str1, true);
      String[] arrayOfString2 = matchMultipleValuePrefix("MAIL", 3, str1, true);
      String str4 = matchSinglePrefixedField("MEMORY:", str1, '\r', false);
      String str5 = matchSinglePrefixedField("ADD:", str1, '\r', true);
      String[] arrayOfString3 = null;
      if (str5 == null);
      String[] arrayOfString4;
      while (true)
      {
        arrayOfString4 = arrayOfString3;
        break;
        arrayOfString3 = new String[] { str5 };
      }
      AddressBookParsedResult localAddressBookParsedResult = new AddressBookParsedResult(maybeWrap(str2), null, str3, arrayOfString1, null, arrayOfString2, null, null, str4, arrayOfString4, null, null, null, null, null, null);
      return localAddressBookParsedResult;
    }
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.AddressBookAUResultParser
 * JD-Core Version:    0.6.1
 */