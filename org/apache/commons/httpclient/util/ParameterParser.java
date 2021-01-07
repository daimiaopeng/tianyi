package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;

public class ParameterParser
{
  private char[] chars = null;
  private int i1 = 0;
  private int i2 = 0;
  private int len = 0;
  private int pos = 0;

  private String getToken(boolean paramBoolean)
  {
    while ((this.i1 < this.i2) && (Character.isWhitespace(this.chars[this.i1])))
      this.i1 = (1 + this.i1);
    while ((this.i2 > this.i1) && (Character.isWhitespace(this.chars[(-1 + this.i2)])))
      this.i2 = (-1 + this.i2);
    if ((paramBoolean) && (this.i2 - this.i1 >= 2) && (this.chars[this.i1] == '"') && (this.chars[(-1 + this.i2)] == '"'))
    {
      this.i1 = (1 + this.i1);
      this.i2 = (-1 + this.i2);
    }
    int i = this.i2;
    int j = this.i1;
    String str = null;
    if (i >= j)
      str = new String(this.chars, this.i1, this.i2 - this.i1);
    return str;
  }

  private boolean hasChar()
  {
    boolean bool;
    if (this.pos < this.len)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean isOneOf(char paramChar, char[] paramArrayOfChar)
  {
    boolean bool;
    for (int i = 0; ; i++)
    {
      int j = paramArrayOfChar.length;
      bool = false;
      if (i >= j)
        break;
      if (paramChar == paramArrayOfChar[i])
      {
        bool = true;
        break;
      }
    }
    return bool;
  }

  private String parseQuotedToken(char[] paramArrayOfChar)
  {
    this.i1 = this.pos;
    this.i2 = this.pos;
    int i = 0;
    int j = 0;
    while (hasChar())
    {
      char c = this.chars[this.pos];
      if ((i == 0) && (isOneOf(c, paramArrayOfChar)))
        break;
      if ((j == 0) && (c == '"'))
        i ^= 1;
      if ((j == 0) && (c == '\\'))
        j = 1;
      else
        j = 0;
      this.i2 = (1 + this.i2);
      this.pos = (1 + this.pos);
    }
    return getToken(true);
  }

  private String parseToken(char[] paramArrayOfChar)
  {
    this.i1 = this.pos;
    this.i2 = this.pos;
    while ((hasChar()) && (!isOneOf(this.chars[this.pos], paramArrayOfChar)))
    {
      this.i2 = (1 + this.i2);
      this.pos = (1 + this.pos);
    }
    return getToken(false);
  }

  public List parse(String paramString, char paramChar)
  {
    if (paramString == null)
      return new ArrayList();
    return parse(paramString.toCharArray(), paramChar);
  }

  public List parse(char[] paramArrayOfChar, char paramChar)
  {
    if (paramArrayOfChar == null)
      return new ArrayList();
    return parse(paramArrayOfChar, 0, paramArrayOfChar.length, paramChar);
  }

  public List parse(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar)
  {
    if (paramArrayOfChar == null)
      return new ArrayList();
    ArrayList localArrayList = new ArrayList();
    this.chars = paramArrayOfChar;
    this.pos = paramInt1;
    this.len = paramInt2;
    while (hasChar())
    {
      String str1 = parseToken(new char[] { '=', paramChar });
      boolean bool = hasChar();
      String str2 = null;
      if (bool)
      {
        int i = paramArrayOfChar[this.pos];
        str2 = null;
        if (i == 61)
        {
          this.pos = (1 + this.pos);
          str2 = parseQuotedToken(new char[] { paramChar });
        }
      }
      if ((hasChar()) && (paramArrayOfChar[this.pos] == paramChar))
        this.pos = (1 + this.pos);
      if ((str1 != null) && ((!str1.equals("")) || (str2 != null)))
        localArrayList.add(new NameValuePair(str1, str2));
    }
    return localArrayList;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.ParameterParser
 * JD-Core Version:    0.6.1
 */