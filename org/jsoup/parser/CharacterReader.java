package org.jsoup.parser;

import java.util.Locale;
import org.jsoup.helper.Validate;

class CharacterReader
{
  static final char EOF = 'ð¿¿';
  private final char[] input;
  private final int length;
  private int mark = 0;
  private int pos = 0;

  CharacterReader(String paramString)
  {
    Validate.notNull(paramString);
    this.input = paramString.toCharArray();
    this.length = this.input.length;
  }

  void advance()
  {
    this.pos = (1 + this.pos);
  }

  char consume()
  {
    int i;
    if (this.pos >= this.length)
      i = 65535;
    else
      i = this.input[this.pos];
    this.pos = (1 + this.pos);
    return i;
  }

  String consumeAsString()
  {
    char[] arrayOfChar = this.input;
    int i = this.pos;
    this.pos = (i + 1);
    return new String(arrayOfChar, i, 1);
  }

  String consumeDigitSequence()
  {
    int i = this.pos;
    while (this.pos < this.length)
    {
      int j = this.input[this.pos];
      if ((j < 48) || (j > 57))
        break;
      this.pos = (1 + this.pos);
    }
    return new String(this.input, i, this.pos - i);
  }

  String consumeHexSequence()
  {
    int i = this.pos;
    while (this.pos < this.length)
    {
      int j = this.input[this.pos];
      if (((j < 48) || (j > 57)) && ((j < 65) || (j > 70)) && ((j < 97) || (j > 102)))
        break;
      this.pos = (1 + this.pos);
    }
    return new String(this.input, i, this.pos - i);
  }

  String consumeLetterSequence()
  {
    int i = this.pos;
    while (this.pos < this.length)
    {
      int j = this.input[this.pos];
      if (((j < 65) || (j > 90)) && ((j < 97) || (j > 122)))
        break;
      this.pos = (1 + this.pos);
    }
    return new String(this.input, i, this.pos - i);
  }

  String consumeLetterThenDigitSequence()
  {
    int i = this.pos;
    while (this.pos < this.length)
    {
      int k = this.input[this.pos];
      if (((k < 65) || (k > 90)) && ((k < 97) || (k > 122)))
        break;
      this.pos = (1 + this.pos);
    }
    while (!isEmpty())
    {
      int j = this.input[this.pos];
      if ((j < 48) || (j > 57))
        break;
      this.pos = (1 + this.pos);
    }
    return new String(this.input, i, this.pos - i);
  }

  String consumeTo(char paramChar)
  {
    int i = nextIndexOf(paramChar);
    if (i != -1)
    {
      String str = new String(this.input, this.pos, i);
      this.pos = (i + this.pos);
      return str;
    }
    return consumeToEnd();
  }

  String consumeTo(String paramString)
  {
    int i = nextIndexOf(paramString);
    if (i != -1)
    {
      String str = new String(this.input, this.pos, i);
      this.pos = (i + this.pos);
      return str;
    }
    return consumeToEnd();
  }

  String consumeToAny(char[] paramArrayOfChar)
  {
    int i = this.pos;
    while (this.pos < this.length)
    {
      for (int j = 0; j < paramArrayOfChar.length; j++)
        if (this.input[this.pos] == paramArrayOfChar[j])
          break label64;
      this.pos = (1 + this.pos);
    }
    label64: String str;
    if (this.pos > i)
      str = new String(this.input, i, this.pos - i);
    else
      str = "";
    return str;
  }

  String consumeToEnd()
  {
    String str = new String(this.input, this.pos, this.length - this.pos);
    this.pos = this.length;
    return str;
  }

  boolean containsIgnoreCase(String paramString)
  {
    String str1 = paramString.toLowerCase(Locale.ENGLISH);
    String str2 = paramString.toUpperCase(Locale.ENGLISH);
    boolean bool;
    if ((nextIndexOf(str1) <= -1) && (nextIndexOf(str2) <= -1))
      bool = false;
    else
      bool = true;
    return bool;
  }

  char current()
  {
    int i;
    if (this.pos >= this.length)
      i = 65535;
    else
      i = this.input[this.pos];
    return i;
  }

  boolean isEmpty()
  {
    boolean bool;
    if (this.pos >= this.length)
      bool = true;
    else
      bool = false;
    return bool;
  }

  void mark()
  {
    this.mark = this.pos;
  }

  boolean matchConsume(String paramString)
  {
    if (matches(paramString))
    {
      this.pos += paramString.length();
      return true;
    }
    return false;
  }

  boolean matchConsumeIgnoreCase(String paramString)
  {
    if (matchesIgnoreCase(paramString))
    {
      this.pos += paramString.length();
      return true;
    }
    return false;
  }

  boolean matches(char paramChar)
  {
    boolean bool;
    if ((!isEmpty()) && (this.input[this.pos] == paramChar))
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean matches(String paramString)
  {
    int i = paramString.length();
    if (i > this.length - this.pos)
      return false;
    for (int j = 0; j < i; j++)
      if (paramString.charAt(j) != this.input[(j + this.pos)])
        return false;
    return true;
  }

  boolean matchesAny(char[] paramArrayOfChar)
  {
    if (isEmpty())
      return false;
    int i = this.input[this.pos];
    int j = paramArrayOfChar.length;
    for (int k = 0; k < j; k++)
      if (paramArrayOfChar[k] == i)
        return true;
    return false;
  }

  boolean matchesDigit()
  {
    if (isEmpty())
      return false;
    int i = this.input[this.pos];
    boolean bool = false;
    if (i >= 48)
    {
      bool = false;
      if (i <= 57)
        bool = true;
    }
    return bool;
  }

  boolean matchesIgnoreCase(String paramString)
  {
    int i = paramString.length();
    if (i > this.length - this.pos)
      return false;
    for (int j = 0; j < i; j++)
      if (Character.toUpperCase(paramString.charAt(j)) != Character.toUpperCase(this.input[(j + this.pos)]))
        return false;
    return true;
  }

  boolean matchesLetter()
  {
    if (isEmpty())
      return false;
    int i = this.input[this.pos];
    boolean bool;
    if ((i < 65) || (i > 90))
    {
      bool = false;
      if (i >= 97)
      {
        bool = false;
        if (i > 122);
      }
    }
    else
    {
      bool = true;
    }
    return bool;
  }

  int nextIndexOf(char paramChar)
  {
    for (int i = this.pos; i < this.length; i++)
      if (paramChar == this.input[i])
        return i - this.pos;
    return -1;
  }

  int nextIndexOf(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.charAt(0);
    int k;
    for (int j = this.pos; j < this.length; j = k)
    {
      if (i != this.input[j])
        do
          j++;
        while ((j < this.length) && (i != this.input[j]));
      k = j + 1;
      int m = k + paramCharSequence.length() - 1;
      if ((j < this.length) && (m <= this.length))
      {
        int n = k;
        for (int i1 = 1; (n < m) && (paramCharSequence.charAt(i1) == this.input[n]); i1++)
          n++;
        if (n == m)
          return j - this.pos;
      }
    }
    return -1;
  }

  int pos()
  {
    return this.pos;
  }

  void rewindToMark()
  {
    this.pos = this.mark;
  }

  public String toString()
  {
    return new String(this.input, this.pos, this.length - this.pos);
  }

  void unconsume()
  {
    this.pos = (-1 + this.pos);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.CharacterReader
 * JD-Core Version:    0.6.1
 */