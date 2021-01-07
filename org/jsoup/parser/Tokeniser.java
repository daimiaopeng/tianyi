package org.jsoup.parser;

import org.jsoup.helper.Validate;

class Tokeniser
{
  static final char replacementChar = '�';
  private StringBuilder charBuffer = new StringBuilder();
  Token.Comment commentPending;
  StringBuilder dataBuffer;
  Token.Doctype doctypePending;
  private Token emitPending;
  private ParseErrorList errors;
  private boolean isEmitPending = false;
  private Token.StartTag lastStartTag;
  private CharacterReader reader;
  private boolean selfClosingFlagAcknowledged = true;
  private TokeniserState state = TokeniserState.Data;
  Token.Tag tagPending;

  Tokeniser(CharacterReader paramCharacterReader, ParseErrorList paramParseErrorList)
  {
    this.reader = paramCharacterReader;
    this.errors = paramParseErrorList;
  }

  private void characterReferenceError(String paramString)
  {
    if (this.errors.canAddError())
      this.errors.add(new ParseError(this.reader.pos(), "Invalid character reference: %s", new Object[] { paramString }));
  }

  private void error(String paramString)
  {
    if (this.errors.canAddError())
      this.errors.add(new ParseError(this.reader.pos(), paramString));
  }

  void acknowledgeSelfClosingFlag()
  {
    this.selfClosingFlagAcknowledged = true;
  }

  void advanceTransition(TokeniserState paramTokeniserState)
  {
    this.reader.advance();
    this.state = paramTokeniserState;
  }

  String appropriateEndTagName()
  {
    if (this.lastStartTag == null)
      return null;
    return this.lastStartTag.tagName;
  }

  // ERROR //
  char[] consumeCharacterReference(Character paramCharacter, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   4: invokevirtual 106	org/jsoup/parser/CharacterReader:isEmpty	()Z
    //   7: ifeq +5 -> 12
    //   10: aconst_null
    //   11: areturn
    //   12: aload_1
    //   13: ifnull +19 -> 32
    //   16: aload_1
    //   17: invokevirtual 112	java/lang/Character:charValue	()C
    //   20: aload_0
    //   21: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   24: invokevirtual 115	org/jsoup/parser/CharacterReader:current	()C
    //   27: if_icmpne +5 -> 32
    //   30: aconst_null
    //   31: areturn
    //   32: aload_0
    //   33: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   36: bipush 7
    //   38: newarray char
    //   40: dup
    //   41: iconst_0
    //   42: ldc 116
    //   44: castore
    //   45: dup
    //   46: iconst_1
    //   47: ldc 117
    //   49: castore
    //   50: dup
    //   51: iconst_2
    //   52: ldc 118
    //   54: castore
    //   55: dup
    //   56: iconst_3
    //   57: ldc 119
    //   59: castore
    //   60: dup
    //   61: iconst_4
    //   62: ldc 120
    //   64: castore
    //   65: dup
    //   66: iconst_5
    //   67: ldc 121
    //   69: castore
    //   70: dup
    //   71: bipush 6
    //   73: ldc 122
    //   75: castore
    //   76: invokevirtual 126	org/jsoup/parser/CharacterReader:matchesAny	([C)Z
    //   79: ifeq +5 -> 84
    //   82: aconst_null
    //   83: areturn
    //   84: aload_0
    //   85: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   88: invokevirtual 129	org/jsoup/parser/CharacterReader:mark	()V
    //   91: aload_0
    //   92: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   95: ldc 131
    //   97: invokevirtual 135	org/jsoup/parser/CharacterReader:matchConsume	(Ljava/lang/String;)Z
    //   100: ifeq +166 -> 266
    //   103: aload_0
    //   104: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   107: ldc 137
    //   109: invokevirtual 140	org/jsoup/parser/CharacterReader:matchConsumeIgnoreCase	(Ljava/lang/String;)Z
    //   112: istore 7
    //   114: iload 7
    //   116: ifeq +15 -> 131
    //   119: aload_0
    //   120: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   123: invokevirtual 143	org/jsoup/parser/CharacterReader:consumeHexSequence	()Ljava/lang/String;
    //   126: astore 8
    //   128: goto +12 -> 140
    //   131: aload_0
    //   132: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   135: invokevirtual 146	org/jsoup/parser/CharacterReader:consumeDigitSequence	()Ljava/lang/String;
    //   138: astore 8
    //   140: aload 8
    //   142: invokevirtual 151	java/lang/String:length	()I
    //   145: ifne +18 -> 163
    //   148: aload_0
    //   149: ldc 153
    //   151: invokespecial 155	org/jsoup/parser/Tokeniser:characterReferenceError	(Ljava/lang/String;)V
    //   154: aload_0
    //   155: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   158: invokevirtual 158	org/jsoup/parser/CharacterReader:rewindToMark	()V
    //   161: aconst_null
    //   162: areturn
    //   163: aload_0
    //   164: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   167: ldc 160
    //   169: invokevirtual 135	org/jsoup/parser/CharacterReader:matchConsume	(Ljava/lang/String;)Z
    //   172: ifne +9 -> 181
    //   175: aload_0
    //   176: ldc 162
    //   178: invokespecial 155	org/jsoup/parser/Tokeniser:characterReferenceError	(Ljava/lang/String;)V
    //   181: iload 7
    //   183: ifeq +10 -> 193
    //   186: bipush 16
    //   188: istore 9
    //   190: goto +7 -> 197
    //   193: bipush 10
    //   195: istore 9
    //   197: aload 8
    //   199: iload 9
    //   201: invokestatic 168	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   204: invokevirtual 171	java/lang/Integer:intValue	()I
    //   207: istore 10
    //   209: goto +6 -> 215
    //   212: iconst_m1
    //   213: istore 10
    //   215: iload 10
    //   217: iconst_m1
    //   218: if_icmpeq +33 -> 251
    //   221: iload 10
    //   223: ldc 172
    //   225: if_icmplt +10 -> 235
    //   228: iload 10
    //   230: ldc 173
    //   232: if_icmple +19 -> 251
    //   235: iload 10
    //   237: ldc 174
    //   239: if_icmple +6 -> 245
    //   242: goto +9 -> 251
    //   245: iload 10
    //   247: invokestatic 178	java/lang/Character:toChars	(I)[C
    //   250: areturn
    //   251: aload_0
    //   252: ldc 180
    //   254: invokespecial 155	org/jsoup/parser/Tokeniser:characterReferenceError	(Ljava/lang/String;)V
    //   257: iconst_1
    //   258: newarray char
    //   260: dup
    //   261: iconst_0
    //   262: ldc 7
    //   264: castore
    //   265: areturn
    //   266: aload_0
    //   267: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   270: invokevirtual 183	org/jsoup/parser/CharacterReader:consumeLetterThenDigitSequence	()Ljava/lang/String;
    //   273: astore_3
    //   274: aload_0
    //   275: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   278: bipush 59
    //   280: invokevirtual 187	org/jsoup/parser/CharacterReader:matches	(C)Z
    //   283: istore 4
    //   285: aload_3
    //   286: invokestatic 192	org/jsoup/nodes/Entities:isBaseNamedEntity	(Ljava/lang/String;)Z
    //   289: ifne +24 -> 313
    //   292: aload_3
    //   293: invokestatic 195	org/jsoup/nodes/Entities:isNamedEntity	(Ljava/lang/String;)Z
    //   296: ifeq +11 -> 307
    //   299: iload 4
    //   301: ifeq +6 -> 307
    //   304: goto +9 -> 313
    //   307: iconst_0
    //   308: istore 5
    //   310: goto +6 -> 316
    //   313: iconst_1
    //   314: istore 5
    //   316: iload 5
    //   318: ifne +34 -> 352
    //   321: aload_0
    //   322: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   325: invokevirtual 158	org/jsoup/parser/CharacterReader:rewindToMark	()V
    //   328: iload 4
    //   330: ifeq +20 -> 350
    //   333: aload_0
    //   334: ldc 197
    //   336: iconst_1
    //   337: anewarray 4	java/lang/Object
    //   340: dup
    //   341: iconst_0
    //   342: aload_3
    //   343: aastore
    //   344: invokestatic 201	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   347: invokespecial 155	org/jsoup/parser/Tokeniser:characterReferenceError	(Ljava/lang/String;)V
    //   350: aconst_null
    //   351: areturn
    //   352: iload_2
    //   353: ifeq +60 -> 413
    //   356: aload_0
    //   357: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   360: invokevirtual 204	org/jsoup/parser/CharacterReader:matchesLetter	()Z
    //   363: ifne +41 -> 404
    //   366: aload_0
    //   367: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   370: invokevirtual 207	org/jsoup/parser/CharacterReader:matchesDigit	()Z
    //   373: ifne +31 -> 404
    //   376: aload_0
    //   377: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   380: iconst_3
    //   381: newarray char
    //   383: dup
    //   384: iconst_0
    //   385: ldc 208
    //   387: castore
    //   388: dup
    //   389: iconst_1
    //   390: ldc 209
    //   392: castore
    //   393: dup
    //   394: iconst_2
    //   395: ldc 210
    //   397: castore
    //   398: invokevirtual 126	org/jsoup/parser/CharacterReader:matchesAny	([C)Z
    //   401: ifeq +12 -> 413
    //   404: aload_0
    //   405: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   408: invokevirtual 158	org/jsoup/parser/CharacterReader:rewindToMark	()V
    //   411: aconst_null
    //   412: areturn
    //   413: aload_0
    //   414: getfield 52	org/jsoup/parser/Tokeniser:reader	Lorg/jsoup/parser/CharacterReader;
    //   417: ldc 160
    //   419: invokevirtual 135	org/jsoup/parser/CharacterReader:matchConsume	(Ljava/lang/String;)Z
    //   422: ifne +9 -> 431
    //   425: aload_0
    //   426: ldc 162
    //   428: invokespecial 155	org/jsoup/parser/Tokeniser:characterReferenceError	(Ljava/lang/String;)V
    //   431: iconst_1
    //   432: newarray char
    //   434: astore 6
    //   436: aload 6
    //   438: iconst_0
    //   439: aload_3
    //   440: invokestatic 214	org/jsoup/nodes/Entities:getCharacterByName	(Ljava/lang/String;)Ljava/lang/Character;
    //   443: invokevirtual 112	java/lang/Character:charValue	()C
    //   446: castore
    //   447: aload 6
    //   449: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   197	209	212	java/lang/NumberFormatException
  }

  void createCommentPending()
  {
    this.commentPending = new Token.Comment();
  }

  void createDoctypePending()
  {
    this.doctypePending = new Token.Doctype();
  }

  Token.Tag createTagPending(boolean paramBoolean)
  {
    Object localObject;
    if (paramBoolean)
      localObject = new Token.StartTag();
    else
      localObject = new Token.EndTag();
    this.tagPending = ((Token.Tag)localObject);
    return this.tagPending;
  }

  void createTempBuffer()
  {
    this.dataBuffer = new StringBuilder();
  }

  boolean currentNodeInHtmlNS()
  {
    return true;
  }

  void emit(char paramChar)
  {
    this.charBuffer.append(paramChar);
  }

  void emit(String paramString)
  {
    this.charBuffer.append(paramString);
  }

  void emit(Token paramToken)
  {
    Validate.isFalse(this.isEmitPending, "There is an unread token pending!");
    this.emitPending = paramToken;
    this.isEmitPending = true;
    if (paramToken.type == Token.TokenType.StartTag)
    {
      Token.StartTag localStartTag = (Token.StartTag)paramToken;
      this.lastStartTag = localStartTag;
      if (localStartTag.selfClosing)
        this.selfClosingFlagAcknowledged = false;
    }
    else if ((paramToken.type == Token.TokenType.EndTag) && (((Token.EndTag)paramToken).attributes != null))
    {
      error("Attributes incorrectly present on end tag");
    }
  }

  void emit(char[] paramArrayOfChar)
  {
    this.charBuffer.append(paramArrayOfChar);
  }

  void emitCommentPending()
  {
    emit(this.commentPending);
  }

  void emitDoctypePending()
  {
    emit(this.doctypePending);
  }

  void emitTagPending()
  {
    this.tagPending.finaliseTag();
    emit(this.tagPending);
  }

  void eofError(TokeniserState paramTokeniserState)
  {
    if (this.errors.canAddError())
      this.errors.add(new ParseError(this.reader.pos(), "Unexpectedly reached end of file (EOF) in input state [%s]", new Object[] { paramTokeniserState }));
  }

  void error(TokeniserState paramTokeniserState)
  {
    if (this.errors.canAddError())
    {
      ParseErrorList localParseErrorList = this.errors;
      int i = this.reader.pos();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Character.valueOf(this.reader.current());
      arrayOfObject[1] = paramTokeniserState;
      localParseErrorList.add(new ParseError(i, "Unexpected character '%s' in input state [%s]", arrayOfObject));
    }
  }

  TokeniserState getState()
  {
    return this.state;
  }

  boolean isAppropriateEndTagToken()
  {
    if (this.lastStartTag == null)
      return false;
    return this.tagPending.tagName.equals(this.lastStartTag.tagName);
  }

  Token read()
  {
    if (!this.selfClosingFlagAcknowledged)
    {
      error("Self closing flag not acknowledged");
      this.selfClosingFlagAcknowledged = true;
    }
    while (!this.isEmitPending)
      this.state.read(this, this.reader);
    if (this.charBuffer.length() > 0)
    {
      String str = this.charBuffer.toString();
      this.charBuffer.delete(0, this.charBuffer.length());
      return new Token.Character(str);
    }
    this.isEmitPending = false;
    return this.emitPending;
  }

  void transition(TokeniserState paramTokeniserState)
  {
    this.state = paramTokeniserState;
  }

  String unescapeEntities(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (!this.reader.isEmpty())
    {
      localStringBuilder.append(this.reader.consumeTo('&'));
      if (this.reader.matches('&'))
      {
        this.reader.consume();
        char[] arrayOfChar = consumeCharacterReference(null, paramBoolean);
        if ((arrayOfChar != null) && (arrayOfChar.length != 0))
          localStringBuilder.append(arrayOfChar);
        else
          localStringBuilder.append('&');
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.Tokeniser
 * JD-Core Version:    0.6.1
 */