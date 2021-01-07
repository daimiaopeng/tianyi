package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

abstract class Token
{
  TokenType type;

  Character asCharacter()
  {
    return (Character)this;
  }

  Comment asComment()
  {
    return (Comment)this;
  }

  Doctype asDoctype()
  {
    return (Doctype)this;
  }

  EndTag asEndTag()
  {
    return (EndTag)this;
  }

  StartTag asStartTag()
  {
    return (StartTag)this;
  }

  boolean isCharacter()
  {
    boolean bool;
    if (this.type == TokenType.Character)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isComment()
  {
    boolean bool;
    if (this.type == TokenType.Comment)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isDoctype()
  {
    boolean bool;
    if (this.type == TokenType.Doctype)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isEOF()
  {
    boolean bool;
    if (this.type == TokenType.EOF)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isEndTag()
  {
    boolean bool;
    if (this.type == TokenType.EndTag)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isStartTag()
  {
    boolean bool;
    if (this.type == TokenType.StartTag)
      bool = true;
    else
      bool = false;
    return bool;
  }

  String tokenType()
  {
    return getClass().getSimpleName();
  }

  static class Character extends Token
  {
    private final String data;

    Character(String paramString)
    {
      super();
      this.type = Token.TokenType.Character;
      this.data = paramString;
    }

    String getData()
    {
      return this.data;
    }

    public String toString()
    {
      return getData();
    }
  }

  static class Comment extends Token
  {
    boolean bogus = false;
    final StringBuilder data = new StringBuilder();

    Comment()
    {
      super();
      this.type = Token.TokenType.Comment;
    }

    String getData()
    {
      return this.data.toString();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("<!--");
      localStringBuilder.append(getData());
      localStringBuilder.append("-->");
      return localStringBuilder.toString();
    }
  }

  static class Doctype extends Token
  {
    boolean forceQuirks = false;
    final StringBuilder name = new StringBuilder();
    final StringBuilder publicIdentifier = new StringBuilder();
    final StringBuilder systemIdentifier = new StringBuilder();

    Doctype()
    {
      super();
      this.type = Token.TokenType.Doctype;
    }

    String getName()
    {
      return this.name.toString();
    }

    String getPublicIdentifier()
    {
      return this.publicIdentifier.toString();
    }

    public String getSystemIdentifier()
    {
      return this.systemIdentifier.toString();
    }

    public boolean isForceQuirks()
    {
      return this.forceQuirks;
    }
  }

  static class EOF extends Token
  {
    EOF()
    {
      super();
      this.type = Token.TokenType.EOF;
    }
  }

  static class EndTag extends Token.Tag
  {
    EndTag()
    {
      this.type = Token.TokenType.EndTag;
    }

    EndTag(String paramString)
    {
      this();
      this.tagName = paramString;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("</");
      localStringBuilder.append(name());
      localStringBuilder.append(">");
      return localStringBuilder.toString();
    }
  }

  static class StartTag extends Token.Tag
  {
    StartTag()
    {
      this.attributes = new Attributes();
      this.type = Token.TokenType.StartTag;
    }

    StartTag(String paramString)
    {
      this();
      this.tagName = paramString;
    }

    StartTag(String paramString, Attributes paramAttributes)
    {
      this();
      this.tagName = paramString;
      this.attributes = paramAttributes;
    }

    public String toString()
    {
      if ((this.attributes != null) && (this.attributes.size() > 0))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("<");
        localStringBuilder2.append(name());
        localStringBuilder2.append(" ");
        localStringBuilder2.append(this.attributes.toString());
        localStringBuilder2.append(">");
        return localStringBuilder2.toString();
      }
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("<");
      localStringBuilder1.append(name());
      localStringBuilder1.append(">");
      return localStringBuilder1.toString();
    }
  }

  static abstract class Tag extends Token
  {
    Attributes attributes;
    private String pendingAttributeName;
    private StringBuilder pendingAttributeValue;
    boolean selfClosing = false;
    protected String tagName;

    Tag()
    {
      super();
    }

    private final void ensureAttributeValue()
    {
      if (this.pendingAttributeValue == null)
        this.pendingAttributeValue = new StringBuilder();
    }

    void appendAttributeName(char paramChar)
    {
      appendAttributeName(String.valueOf(paramChar));
    }

    void appendAttributeName(String paramString)
    {
      if (this.pendingAttributeName != null)
        paramString = this.pendingAttributeName.concat(paramString);
      this.pendingAttributeName = paramString;
    }

    void appendAttributeValue(char paramChar)
    {
      ensureAttributeValue();
      this.pendingAttributeValue.append(paramChar);
    }

    void appendAttributeValue(String paramString)
    {
      ensureAttributeValue();
      this.pendingAttributeValue.append(paramString);
    }

    void appendAttributeValue(char[] paramArrayOfChar)
    {
      ensureAttributeValue();
      this.pendingAttributeValue.append(paramArrayOfChar);
    }

    void appendTagName(char paramChar)
    {
      appendTagName(String.valueOf(paramChar));
    }

    void appendTagName(String paramString)
    {
      if (this.tagName != null)
        paramString = this.tagName.concat(paramString);
      this.tagName = paramString;
    }

    void finaliseTag()
    {
      if (this.pendingAttributeName != null)
        newAttribute();
    }

    Attributes getAttributes()
    {
      return this.attributes;
    }

    boolean isSelfClosing()
    {
      return this.selfClosing;
    }

    String name()
    {
      boolean bool;
      if ((this.tagName != null) && (this.tagName.length() != 0))
        bool = false;
      else
        bool = true;
      Validate.isFalse(bool);
      return this.tagName;
    }

    Tag name(String paramString)
    {
      this.tagName = paramString;
      return this;
    }

    void newAttribute()
    {
      if (this.attributes == null)
        this.attributes = new Attributes();
      if (this.pendingAttributeName != null)
      {
        Attribute localAttribute;
        if (this.pendingAttributeValue == null)
          localAttribute = new Attribute(this.pendingAttributeName, "");
        else
          localAttribute = new Attribute(this.pendingAttributeName, this.pendingAttributeValue.toString());
        this.attributes.put(localAttribute);
      }
      this.pendingAttributeName = null;
      if (this.pendingAttributeValue != null)
        this.pendingAttributeValue.delete(0, this.pendingAttributeValue.length());
    }
  }

  static enum TokenType
  {
    static
    {
      EndTag = new TokenType("EndTag", 2);
      Comment = new TokenType("Comment", 3);
      Character = new TokenType("Character", 4);
      EOF = new TokenType("EOF", 5);
      TokenType[] arrayOfTokenType = new TokenType[6];
      arrayOfTokenType[0] = Doctype;
      arrayOfTokenType[1] = StartTag;
      arrayOfTokenType[2] = EndTag;
      arrayOfTokenType[3] = Comment;
      arrayOfTokenType[4] = Character;
      arrayOfTokenType[5] = EOF;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.Token
 * JD-Core Version:    0.6.1
 */