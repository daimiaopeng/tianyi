package org.apache.commons.httpclient.util;

import java.util.BitSet;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

public class URIUtil
{
  protected static final BitSet empty = new BitSet(1);

  public static String decode(String paramString)
  {
    try
    {
      String str = EncodingUtil.getString(URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(paramString)), URI.getDefaultProtocolCharset());
      return str;
    }
    catch (DecoderException localDecoderException)
    {
      throw new URIException(localDecoderException.getMessage());
    }
  }

  public static String decode(String paramString1, String paramString2)
  {
    return Coder.decode(paramString1.toCharArray(), paramString2);
  }

  public static String encode(String paramString, BitSet paramBitSet)
  {
    return encode(paramString, paramBitSet, URI.getDefaultProtocolCharset());
  }

  public static String encode(String paramString1, BitSet paramBitSet, String paramString2)
  {
    return EncodingUtil.getAsciiString(URLCodec.encodeUrl(paramBitSet, EncodingUtil.getBytes(paramString1, paramString2)));
  }

  public static String encodeAll(String paramString)
  {
    return encodeAll(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeAll(String paramString1, String paramString2)
  {
    return encode(paramString1, empty, paramString2);
  }

  public static String encodePath(String paramString)
  {
    return encodePath(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodePath(String paramString1, String paramString2)
  {
    return encode(paramString1, URI.allowed_abs_path, paramString2);
  }

  public static String encodePathQuery(String paramString)
  {
    return encodePathQuery(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodePathQuery(String paramString1, String paramString2)
  {
    int i = paramString1.indexOf('?');
    if (i < 0)
      return encode(paramString1, URI.allowed_abs_path, paramString2);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(encode(paramString1.substring(0, i), URI.allowed_abs_path, paramString2));
    localStringBuffer.append('?');
    localStringBuffer.append(encode(paramString1.substring(i + 1), URI.allowed_query, paramString2));
    return localStringBuffer.toString();
  }

  public static String encodeQuery(String paramString)
  {
    return encodeQuery(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeQuery(String paramString1, String paramString2)
  {
    return encode(paramString1, URI.allowed_query, paramString2);
  }

  public static String encodeWithinAuthority(String paramString)
  {
    return encodeWithinAuthority(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeWithinAuthority(String paramString1, String paramString2)
  {
    return encode(paramString1, URI.allowed_within_authority, paramString2);
  }

  public static String encodeWithinPath(String paramString)
  {
    return encodeWithinPath(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeWithinPath(String paramString1, String paramString2)
  {
    return encode(paramString1, URI.allowed_within_path, paramString2);
  }

  public static String encodeWithinQuery(String paramString)
  {
    return encodeWithinQuery(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeWithinQuery(String paramString1, String paramString2)
  {
    return encode(paramString1, URI.allowed_within_query, paramString2);
  }

  public static String getFromPath(String paramString)
  {
    if (paramString == null)
      return null;
    int i = paramString.indexOf("//");
    int j = 0;
    if (i >= 0)
      if (paramString.lastIndexOf("/", i - 1) >= 0)
        j = 0;
      else
        j = i + 2;
    int k = paramString.indexOf("/", j);
    if (k < 0)
    {
      if (i >= 0)
        paramString = "/";
    }
    else
      paramString = paramString.substring(k);
    return paramString;
  }

  public static String getName(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      String str = getPath(paramString);
      int i = str.lastIndexOf("/");
      int j = str.length();
      if (i >= 0)
        str = str.substring(i + 1, j);
      return str;
    }
    return paramString;
  }

  public static String getPath(String paramString)
  {
    if (paramString == null)
      return null;
    int i = paramString.indexOf("//");
    int j = 0;
    if (i >= 0)
      if (paramString.lastIndexOf("/", i - 1) >= 0)
        j = 0;
      else
        j = i + 2;
    int k = paramString.indexOf("/", j);
    int m = paramString.length();
    if (paramString.indexOf('?', k) != -1)
      m = paramString.indexOf('?', k);
    if ((paramString.lastIndexOf("#") > k) && (paramString.lastIndexOf("#") < m))
      m = paramString.lastIndexOf("#");
    if (k < 0)
    {
      if (i >= 0)
        paramString = "/";
    }
    else
      paramString = paramString.substring(k, m);
    return paramString;
  }

  public static String getPathQuery(String paramString)
  {
    if (paramString == null)
      return null;
    int i = paramString.indexOf("//");
    int j = 0;
    if (i >= 0)
      if (paramString.lastIndexOf("/", i - 1) >= 0)
        j = 0;
      else
        j = i + 2;
    int k = paramString.indexOf("/", j);
    int m = paramString.length();
    if (paramString.lastIndexOf("#") > k)
      m = paramString.lastIndexOf("#");
    if (k < 0)
    {
      if (i >= 0)
        paramString = "/";
    }
    else
      paramString = paramString.substring(k, m);
    return paramString;
  }

  public static String getQuery(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      int i = paramString.indexOf("//");
      int j = 0;
      if (i >= 0)
        if (paramString.lastIndexOf("/", i - 1) >= 0)
          j = 0;
        else
          j = i + 2;
      int k = paramString.indexOf("/", j);
      int m = paramString.length();
      int n = paramString.indexOf("?", k);
      if (n >= 0)
      {
        int i1 = n + 1;
        if (paramString.lastIndexOf("#") > i1)
          m = paramString.lastIndexOf("#");
        String str = null;
        if (i1 >= 0)
          if (i1 == m)
            str = null;
          else
            str = paramString.substring(i1, m);
        return str;
      }
      return null;
    }
    return null;
  }

  protected static class Coder extends URI
  {
    public static String decode(char[] paramArrayOfChar, String paramString)
    {
      return URI.decode(paramArrayOfChar, paramString);
    }

    public static char[] encode(String paramString1, BitSet paramBitSet, String paramString2)
    {
      return URI.encode(paramString1, paramBitSet, paramString2);
    }

    public static String replace(String paramString, char paramChar1, char paramChar2)
    {
      StringBuffer localStringBuffer = new StringBuffer(paramString.length());
      int j;
      for (int i = 0; ; i = j)
      {
        j = paramString.indexOf(paramChar1);
        if (j >= 0)
        {
          localStringBuffer.append(paramString.substring(0, j));
          localStringBuffer.append(paramChar2);
        }
        else
        {
          localStringBuffer.append(paramString.substring(i));
        }
        if (j < 0)
          return localStringBuffer.toString();
      }
    }

    public static String replace(String paramString, char[] paramArrayOfChar1, char[] paramArrayOfChar2)
    {
      for (int i = paramArrayOfChar1.length; i > 0; i--)
        paramString = replace(paramString, paramArrayOfChar1[i], paramArrayOfChar2[i]);
      return paramString;
    }

    public static boolean verifyEscaped(char[] paramArrayOfChar)
    {
      for (int i = 0; i < paramArrayOfChar.length; i++)
      {
        int j = paramArrayOfChar[i];
        if (j > 128)
          return false;
        if (j == 37)
        {
          int k = i + 1;
          if (Character.digit(paramArrayOfChar[k], 16) != -1)
          {
            i = k + 1;
            if (Character.digit(paramArrayOfChar[i], 16) != -1);
          }
          else
          {
            return false;
          }
        }
      }
      return true;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.URIUtil
 * JD-Core Version:    0.6.1
 */