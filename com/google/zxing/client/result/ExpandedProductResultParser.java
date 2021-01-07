package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import java.util.Map;

public final class ExpandedProductResultParser extends ResultParser
{
  private static String findAIvalue(int paramInt, String paramString)
  {
    if (paramString.charAt(paramInt) != '(')
      return null;
    String str = paramString.substring(paramInt + 1);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < str.length())
    {
      char c = str.charAt(i);
      if (c == ')')
        return localStringBuilder.toString();
      if ((c >= '0') && (c <= '9'))
      {
        localStringBuilder.append(c);
        i++;
      }
      else
      {
        return null;
      }
    }
    return localStringBuilder.toString();
  }

  private static String findValue(int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = paramString.substring(paramInt);
    for (int i = 0; i < str.length(); i++)
    {
      char c = str.charAt(i);
      if (c == '(')
      {
        if (findAIvalue(i, str) != null)
          break;
        localStringBuilder.append('(');
      }
      else
      {
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }

  public ExpandedProductParsedResult parse(Result paramResult)
  {
    if (paramResult.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED)
      return null;
    String str1 = getMassagedText(paramResult);
    if (str1 == null)
      return null;
    HashMap localHashMap = new HashMap();
    Object localObject1 = null;
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject5 = null;
    Object localObject6 = null;
    Object localObject7 = null;
    Object localObject8 = null;
    Object localObject9 = null;
    Object localObject10 = null;
    Object localObject11 = null;
    Object localObject12 = null;
    Object localObject13 = null;
    int k;
    for (int i = 0; i < str1.length(); i = k)
    {
      String str2 = findAIvalue(i, str1);
      if (str2 == null)
        return null;
      int j = i + (2 + str2.length());
      String str3 = findValue(j, str1);
      k = j + str3.length();
      if ("00".equals(str2))
        localObject2 = str3;
      while (true)
      {
        break;
        if ("01".equals(str2))
        {
          localObject1 = str3;
        }
        else if ("10".equals(str2))
        {
          localObject3 = str3;
        }
        else if ("11".equals(str2))
        {
          localObject4 = str3;
        }
        else if ("13".equals(str2))
        {
          localObject5 = str3;
        }
        else if ("15".equals(str2))
        {
          localObject6 = str3;
        }
        else if ("17".equals(str2))
        {
          localObject7 = str3;
        }
        else
        {
          boolean bool = "3100".equals(str2);
          Object localObject14 = localObject11;
          String str4;
          String str5;
          if ((!bool) && (!"3101".equals(str2)) && (!"3102".equals(str2)) && (!"3103".equals(str2)) && (!"3104".equals(str2)) && (!"3105".equals(str2)) && (!"3106".equals(str2)) && (!"3107".equals(str2)) && (!"3108".equals(str2)) && (!"3109".equals(str2)))
          {
            if ((!"3200".equals(str2)) && (!"3201".equals(str2)) && (!"3202".equals(str2)) && (!"3203".equals(str2)) && (!"3204".equals(str2)) && (!"3205".equals(str2)) && (!"3206".equals(str2)) && (!"3207".equals(str2)) && (!"3208".equals(str2)) && (!"3209".equals(str2)))
            {
              if ((!"3920".equals(str2)) && (!"3921".equals(str2)) && (!"3922".equals(str2)) && (!"3923".equals(str2)))
              {
                if ((!"3930".equals(str2)) && (!"3931".equals(str2)) && (!"3932".equals(str2)) && (!"3933".equals(str2)))
                {
                  localHashMap.put(str2, str3);
                  localObject11 = localObject14;
                }
                else
                {
                  if (str3.length() < 4)
                    return null;
                  String str6 = str3.substring(3);
                  String str7 = str3.substring(0, 3);
                  String str8 = str2.substring(3);
                  localObject13 = str7;
                  localObject11 = str6;
                  localObject12 = str8;
                }
              }
              else
              {
                localObject12 = str2.substring(3);
                localObject11 = str3;
              }
            }
            else
            {
              str4 = "LB";
              str5 = str2.substring(3);
            }
          }
          else
          {
            str4 = "KG";
            str5 = str2.substring(3);
            localObject10 = str5;
            localObject9 = str4;
            localObject11 = localObject14;
            localObject8 = str3;
          }
        }
      }
    }
    ExpandedProductParsedResult localExpandedProductParsedResult = new ExpandedProductParsedResult(str1, localObject1, localObject2, localObject3, localObject4, localObject5, localObject6, localObject7, localObject8, localObject9, localObject10, localObject11, (String)localObject12, localObject13, localHashMap);
    return localExpandedProductParsedResult;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ExpandedProductResultParser
 * JD-Core Version:    0.6.1
 */