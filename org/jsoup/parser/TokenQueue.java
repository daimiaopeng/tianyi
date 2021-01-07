package org.jsoup.parser;

import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;

public class TokenQueue
{
  private static final char ESC = '\\';
  private int pos = 0;
  private String queue;

  public TokenQueue(String paramString)
  {
    Validate.notNull(paramString);
    this.queue = paramString;
  }

  private int remainingLength()
  {
    return this.queue.length() - this.pos;
  }

  public static String unescape(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    char[] arrayOfChar = paramString.toCharArray();
    int i = arrayOfChar.length;
    int j = 0;
    char c;
    for (int k = 0; j < i; k = c)
    {
      c = arrayOfChar[j];
      if (c == '\\')
      {
        if ((k != 0) && (k == 92))
          localStringBuilder.append(c);
      }
      else
        localStringBuilder.append(c);
      j++;
    }
    return localStringBuilder.toString();
  }

  public void addFirst(Character paramCharacter)
  {
    addFirst(paramCharacter.toString());
  }

  public void addFirst(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(this.queue.substring(this.pos));
    this.queue = localStringBuilder.toString();
    this.pos = 0;
  }

  public void advance()
  {
    if (!isEmpty())
      this.pos = (1 + this.pos);
  }

  public String chompBalanced(char paramChar1, char paramChar2)
  {
    int i = 0;
    int j = 0;
    int k = -1;
    int m = -1;
    do
    {
      if (isEmpty())
        break;
      Character localCharacter = Character.valueOf(consume());
      if ((i == 0) || (i != 92))
        if (localCharacter.equals(Character.valueOf(paramChar1)))
        {
          j++;
          if (k == -1)
            k = this.pos;
        }
        else if (localCharacter.equals(Character.valueOf(paramChar2)))
        {
          j--;
        }
      if ((j > 0) && (i != 0))
        m = this.pos;
      i = localCharacter.charValue();
    }
    while (j > 0);
    String str;
    if (m >= 0)
      str = this.queue.substring(k, m);
    else
      str = "";
    return str;
  }

  public String chompTo(String paramString)
  {
    String str = consumeTo(paramString);
    matchChomp(paramString);
    return str;
  }

  public String chompToIgnoreCase(String paramString)
  {
    String str = consumeToIgnoreCase(paramString);
    matchChomp(paramString);
    return str;
  }

  public char consume()
  {
    String str = this.queue;
    int i = this.pos;
    this.pos = (i + 1);
    return str.charAt(i);
  }

  public void consume(String paramString)
  {
    if (!matches(paramString))
      throw new IllegalStateException("Queue did not match expected sequence");
    int i = paramString.length();
    if (i > remainingLength())
      throw new IllegalStateException("Queue not long enough to consume sequence");
    this.pos = (i + this.pos);
  }

  public String consumeAttributeKey()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord())
        if (!matchesAny(new char[] { 45, 95, 58 }))
          break;
      this.pos = (1 + this.pos);
    }
    return this.queue.substring(i, this.pos);
  }

  public String consumeCssIdentifier()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord())
        if (!matchesAny(new char[] { 45, 95 }))
          break;
      this.pos = (1 + this.pos);
    }
    return this.queue.substring(i, this.pos);
  }

  public String consumeElementSelector()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord())
        if (!matchesAny(new char[] { 124, 95, 45 }))
          break;
      this.pos = (1 + this.pos);
    }
    return this.queue.substring(i, this.pos);
  }

  public String consumeTagName()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord())
        if (!matchesAny(new char[] { 58, 95, 45 }))
          break;
      this.pos = (1 + this.pos);
    }
    return this.queue.substring(i, this.pos);
  }

  public String consumeTo(String paramString)
  {
    int i = this.queue.indexOf(paramString, this.pos);
    if (i != -1)
    {
      String str = this.queue.substring(this.pos, i);
      this.pos += str.length();
      return str;
    }
    return remainder();
  }

  public String consumeToAny(String[] paramArrayOfString)
  {
    int i = this.pos;
    while ((!isEmpty()) && (!matchesAny(paramArrayOfString)))
      this.pos = (1 + this.pos);
    return this.queue.substring(i, this.pos);
  }

  public String consumeToIgnoreCase(String paramString)
  {
    int i = this.pos;
    String str = paramString.substring(0, 1);
    boolean bool = str.toLowerCase().equals(str.toUpperCase());
    while ((!isEmpty()) && (!matches(paramString)))
      if (bool)
      {
        int j = this.queue.indexOf(str, this.pos) - this.pos;
        if (j == 0)
          this.pos = (1 + this.pos);
        else if (j < 0)
          this.pos = this.queue.length();
        else
          this.pos = (j + this.pos);
      }
      else
      {
        this.pos = (1 + this.pos);
      }
    return this.queue.substring(i, this.pos);
  }

  public boolean consumeWhitespace()
  {
    for (boolean bool = false; matchesWhitespace(); bool = true)
      this.pos = (1 + this.pos);
    return bool;
  }

  public String consumeWord()
  {
    int i = this.pos;
    while (matchesWord())
      this.pos = (1 + this.pos);
    return this.queue.substring(i, this.pos);
  }

  public boolean isEmpty()
  {
    boolean bool;
    if (remainingLength() == 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean matchChomp(String paramString)
  {
    if (matches(paramString))
    {
      this.pos += paramString.length();
      return true;
    }
    return false;
  }

  public boolean matches(String paramString)
  {
    return this.queue.regionMatches(true, this.pos, paramString, 0, paramString.length());
  }

  public boolean matchesAny(char[] paramArrayOfChar)
  {
    if (isEmpty())
      return false;
    int i = paramArrayOfChar.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfChar[j];
      if (this.queue.charAt(this.pos) == k)
        return true;
    }
    return false;
  }

  public boolean matchesAny(String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      if (matches(paramArrayOfString[j]))
        return true;
    return false;
  }

  public boolean matchesCS(String paramString)
  {
    return this.queue.startsWith(paramString, this.pos);
  }

  public boolean matchesStartTag()
  {
    int i = remainingLength();
    int j = 1;
    if ((i < 2) || (this.queue.charAt(this.pos) != '<') || (!Character.isLetter(this.queue.charAt(j + this.pos))))
      j = 0;
    return j;
  }

  public boolean matchesWhitespace()
  {
    boolean bool;
    if ((!isEmpty()) && (StringUtil.isWhitespace(this.queue.charAt(this.pos))))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean matchesWord()
  {
    boolean bool;
    if ((!isEmpty()) && (Character.isLetterOrDigit(this.queue.charAt(this.pos))))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public char peek()
  {
    char c;
    if (isEmpty())
      c = '\000';
    else
      c = this.queue.charAt(this.pos);
    return c;
  }

  public String remainder()
  {
    String str = this.queue.substring(this.pos, this.queue.length());
    this.pos = this.queue.length();
    return str;
  }

  public String toString()
  {
    return this.queue.substring(this.pos);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.TokenQueue
 * JD-Core Version:    0.6.1
 */