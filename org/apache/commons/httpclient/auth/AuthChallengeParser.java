package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterParser;

public final class AuthChallengeParser
{
  public static Map extractParams(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Challenge may not be null");
    int i = paramString.indexOf(' ');
    if (i == -1)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid challenge: ");
      localStringBuffer.append(paramString);
      throw new MalformedChallengeException(localStringBuffer.toString());
    }
    HashMap localHashMap = new HashMap();
    List localList = new ParameterParser().parse(paramString.substring(i + 1, paramString.length()), ',');
    for (int j = 0; j < localList.size(); j++)
    {
      NameValuePair localNameValuePair = (NameValuePair)localList.get(j);
      localHashMap.put(localNameValuePair.getName().toLowerCase(), localNameValuePair.getValue());
    }
    return localHashMap;
  }

  public static String extractScheme(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Challenge may not be null");
    int i = paramString.indexOf(' ');
    String str;
    if (i == -1)
      str = paramString;
    else
      str = paramString.substring(0, i);
    if (str.equals(""))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid challenge: ");
      localStringBuffer.append(paramString);
      throw new MalformedChallengeException(localStringBuffer.toString());
    }
    return str.toLowerCase();
  }

  public static Map parseChallenges(Header[] paramArrayOfHeader)
  {
    if (paramArrayOfHeader == null)
      throw new IllegalArgumentException("Array of challenges may not be null");
    HashMap localHashMap = new HashMap(paramArrayOfHeader.length);
    for (int i = 0; i < paramArrayOfHeader.length; i++)
    {
      String str = paramArrayOfHeader[i].getValue();
      localHashMap.put(extractScheme(str), str);
    }
    return localHashMap;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthChallengeParser
 * JD-Core Version:    0.6.1
 */