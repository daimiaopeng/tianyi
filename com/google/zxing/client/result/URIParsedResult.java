package com.google.zxing.client.result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIParsedResult extends ParsedResult
{
  private static final Pattern USER_IN_HOST = Pattern.compile(":/*([^/@]+)@[^/]+");
  private final String title;
  private final String uri;

  public URIParsedResult(String paramString1, String paramString2)
  {
    super(ParsedResultType.URI);
    this.uri = massageURI(paramString1);
    this.title = paramString2;
  }

  private static boolean isColonFollowedByPortNumber(String paramString, int paramInt)
  {
    int i = paramInt + 1;
    int j = paramString.indexOf('/', i);
    if (j < 0)
      j = paramString.length();
    if (j <= i)
      return false;
    while (i < j)
      if ((paramString.charAt(i) >= '0') && (paramString.charAt(i) <= '9'))
        i++;
      else
        return false;
    return true;
  }

  private static String massageURI(String paramString)
  {
    String str = paramString.trim();
    int i = str.indexOf(':');
    if (i < 0)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("http://");
      localStringBuilder1.append(str);
      str = localStringBuilder1.toString();
    }
    else if (isColonFollowedByPortNumber(str, i))
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("http://");
      localStringBuilder2.append(str);
      str = localStringBuilder2.toString();
    }
    return str;
  }

  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(30);
    maybeAppend(this.title, localStringBuilder);
    maybeAppend(this.uri, localStringBuilder);
    return localStringBuilder.toString();
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getURI()
  {
    return this.uri;
  }

  public boolean isPossiblyMaliciousURI()
  {
    return USER_IN_HOST.matcher(this.uri).find();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URIParsedResult
 * JD-Core Version:    0.6.1
 */