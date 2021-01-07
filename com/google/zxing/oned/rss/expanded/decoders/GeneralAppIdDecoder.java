package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class GeneralAppIdDecoder
{
  private final StringBuilder buffer = new StringBuilder();
  private final CurrentParsingState current = new CurrentParsingState();
  private final BitArray information;

  GeneralAppIdDecoder(BitArray paramBitArray)
  {
    this.information = paramBitArray;
  }

  private DecodedChar decodeAlphanumeric(int paramInt)
  {
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if (i == 15)
      return new DecodedChar(paramInt + 5, '$');
    if ((i >= 5) && (i < 15))
      return new DecodedChar(paramInt + 5, (char)(i + 48 - 5));
    int j = extractNumericValueFromBitArray(paramInt, 6);
    if ((j >= 32) && (j < 58))
      return new DecodedChar(paramInt + 6, (char)(j + 33));
    char c;
    switch (j)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Decoding invalid alphanumeric value: ");
      localStringBuilder.append(j);
      throw new IllegalStateException(localStringBuilder.toString());
    case 62:
      c = '/';
      break;
    case 61:
      c = '.';
      break;
    case 60:
      c = '-';
      break;
    case 59:
      c = ',';
      break;
    case 58:
      c = '*';
    }
    return new DecodedChar(paramInt + 6, c);
  }

  private DecodedChar decodeIsoIec646(int paramInt)
  {
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if (i == 15)
      return new DecodedChar(paramInt + 5, '$');
    if ((i >= 5) && (i < 15))
      return new DecodedChar(paramInt + 5, (char)(i + 48 - 5));
    int j = extractNumericValueFromBitArray(paramInt, 7);
    if ((j >= 64) && (j < 90))
      return new DecodedChar(paramInt + 7, (char)(j + 1));
    if ((j >= 90) && (j < 116))
      return new DecodedChar(paramInt + 7, (char)(j + 7));
    int k = extractNumericValueFromBitArray(paramInt, 8);
    char c;
    switch (k)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Decoding invalid ISO/IEC 646 value: ");
      localStringBuilder.append(k);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 252:
      c = ' ';
      break;
    case 251:
      c = '_';
      break;
    case 250:
      c = '?';
      break;
    case 249:
      c = '>';
      break;
    case 248:
      c = '=';
      break;
    case 247:
      c = '<';
      break;
    case 246:
      c = ';';
      break;
    case 245:
      c = ':';
      break;
    case 244:
      c = '/';
      break;
    case 243:
      c = '.';
      break;
    case 242:
      c = '-';
      break;
    case 241:
      c = ',';
      break;
    case 240:
      c = '+';
      break;
    case 239:
      c = '*';
      break;
    case 238:
      c = ')';
      break;
    case 237:
      c = '(';
      break;
    case 236:
      c = '\'';
      break;
    case 235:
      c = '&';
      break;
    case 234:
      c = '%';
      break;
    case 233:
      c = '"';
      break;
    case 232:
      c = '!';
    }
    return new DecodedChar(paramInt + 8, c);
  }

  private DecodedNumeric decodeNumeric(int paramInt)
  {
    int i = paramInt + 7;
    if (i > this.information.getSize())
    {
      int k = extractNumericValueFromBitArray(paramInt, 4);
      if (k == 0)
        return new DecodedNumeric(this.information.getSize(), 10, 10);
      return new DecodedNumeric(this.information.getSize(), k - 1, 10);
    }
    int j = -8 + extractNumericValueFromBitArray(paramInt, 7);
    return new DecodedNumeric(i, j / 11, j % 11);
  }

  static int extractNumericValueFromBitArray(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    if (paramInt2 > 32)
      throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
    int i = 0;
    int j = 0;
    while (i < paramInt2)
    {
      if (paramBitArray.get(paramInt1 + i))
        j |= 1 << paramInt2 - i - 1;
      i++;
    }
    return j;
  }

  private boolean isAlphaOr646ToNumericLatch(int paramInt)
  {
    int i = paramInt + 3;
    if (i > this.information.getSize())
      return false;
    while (paramInt < i)
    {
      if (this.information.get(paramInt))
        return false;
      paramInt++;
    }
    return true;
  }

  private boolean isAlphaTo646ToAlphaLatch(int paramInt)
  {
    if (paramInt + 1 > this.information.getSize())
      return false;
    for (int i = 0; i < 5; i++)
    {
      int j = i + paramInt;
      if (j >= this.information.getSize())
        break;
      if (i == 2)
      {
        if (!this.information.get(paramInt + 2))
          return false;
      }
      else if (this.information.get(j))
        return false;
    }
    return true;
  }

  private boolean isNumericToAlphaNumericLatch(int paramInt)
  {
    if (paramInt + 1 > this.information.getSize())
      return false;
    for (int i = 0; i < 4; i++)
    {
      int j = i + paramInt;
      if (j >= this.information.getSize())
        break;
      if (this.information.get(j))
        return false;
    }
    return true;
  }

  private boolean isStillAlpha(int paramInt)
  {
    if (paramInt + 5 > this.information.getSize())
      return false;
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if ((i >= 5) && (i < 16))
      return true;
    if (paramInt + 6 > this.information.getSize())
      return false;
    int j = extractNumericValueFromBitArray(paramInt, 6);
    boolean bool = false;
    if (j >= 16)
    {
      bool = false;
      if (j < 63)
        bool = true;
    }
    return bool;
  }

  private boolean isStillIsoIec646(int paramInt)
  {
    if (paramInt + 5 > this.information.getSize())
      return false;
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if ((i >= 5) && (i < 16))
      return true;
    if (paramInt + 7 > this.information.getSize())
      return false;
    int j = extractNumericValueFromBitArray(paramInt, 7);
    if ((j >= 64) && (j < 116))
      return true;
    if (paramInt + 8 > this.information.getSize())
      return false;
    int k = extractNumericValueFromBitArray(paramInt, 8);
    boolean bool = false;
    if (k >= 232)
    {
      bool = false;
      if (k < 253)
        bool = true;
    }
    return bool;
  }

  private boolean isStillNumeric(int paramInt)
  {
    int i = paramInt + 7;
    int j = this.information.getSize();
    boolean bool = true;
    if (i > j)
    {
      if (paramInt + 4 > this.information.getSize())
        bool = false;
      return bool;
    }
    int m;
    for (int k = paramInt; ; k++)
    {
      m = paramInt + 3;
      if (k >= m)
        break;
      if (this.information.get(k))
        return bool;
    }
    return this.information.get(m);
  }

  private BlockParsedResult parseAlphaBlock()
  {
    while (isStillAlpha(this.current.getPosition()))
    {
      DecodedChar localDecodedChar = decodeAlphanumeric(this.current.getPosition());
      this.current.setPosition(localDecodedChar.getNewPosition());
      if (localDecodedChar.isFNC1())
        return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
      this.buffer.append(localDecodedChar.getValue());
    }
    if (isAlphaOr646ToNumericLatch(this.current.getPosition()))
    {
      this.current.incrementPosition(3);
      this.current.setNumeric();
    }
    else if (isAlphaTo646ToAlphaLatch(this.current.getPosition()))
    {
      if (5 + this.current.getPosition() < this.information.getSize())
        this.current.incrementPosition(5);
      else
        this.current.setPosition(this.information.getSize());
      this.current.setIsoIec646();
    }
    return new BlockParsedResult(false);
  }

  private DecodedInformation parseBlocks()
  {
    BlockParsedResult localBlockParsedResult;
    boolean bool;
    int j;
    do
    {
      int i = this.current.getPosition();
      if (this.current.isAlpha())
      {
        localBlockParsedResult = parseAlphaBlock();
        bool = localBlockParsedResult.isFinished();
      }
      else if (this.current.isIsoIec646())
      {
        localBlockParsedResult = parseIsoIec646Block();
        bool = localBlockParsedResult.isFinished();
      }
      else
      {
        localBlockParsedResult = parseNumericBlock();
        bool = localBlockParsedResult.isFinished();
      }
      if (i != this.current.getPosition())
        j = 1;
      else
        j = 0;
    }
    while (((j != 0) || (bool)) && (!bool));
    return localBlockParsedResult.getDecodedInformation();
  }

  private BlockParsedResult parseIsoIec646Block()
  {
    while (isStillIsoIec646(this.current.getPosition()))
    {
      DecodedChar localDecodedChar = decodeIsoIec646(this.current.getPosition());
      this.current.setPosition(localDecodedChar.getNewPosition());
      if (localDecodedChar.isFNC1())
        return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
      this.buffer.append(localDecodedChar.getValue());
    }
    if (isAlphaOr646ToNumericLatch(this.current.getPosition()))
    {
      this.current.incrementPosition(3);
      this.current.setNumeric();
    }
    else if (isAlphaTo646ToAlphaLatch(this.current.getPosition()))
    {
      if (5 + this.current.getPosition() < this.information.getSize())
        this.current.incrementPosition(5);
      else
        this.current.setPosition(this.information.getSize());
      this.current.setAlpha();
    }
    return new BlockParsedResult(false);
  }

  private BlockParsedResult parseNumericBlock()
  {
    while (isStillNumeric(this.current.getPosition()))
    {
      DecodedNumeric localDecodedNumeric = decodeNumeric(this.current.getPosition());
      this.current.setPosition(localDecodedNumeric.getNewPosition());
      if (localDecodedNumeric.isFirstDigitFNC1())
      {
        DecodedInformation localDecodedInformation;
        if (localDecodedNumeric.isSecondDigitFNC1())
          localDecodedInformation = new DecodedInformation(this.current.getPosition(), this.buffer.toString());
        else
          localDecodedInformation = new DecodedInformation(this.current.getPosition(), this.buffer.toString(), localDecodedNumeric.getSecondDigit());
        return new BlockParsedResult(localDecodedInformation, true);
      }
      this.buffer.append(localDecodedNumeric.getFirstDigit());
      if (localDecodedNumeric.isSecondDigitFNC1())
        return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
      this.buffer.append(localDecodedNumeric.getSecondDigit());
    }
    if (isNumericToAlphaNumericLatch(this.current.getPosition()))
    {
      this.current.setAlpha();
      this.current.incrementPosition(4);
    }
    return new BlockParsedResult(false);
  }

  String decodeAllCodes(StringBuilder paramStringBuilder, int paramInt)
  {
    String str2;
    for (Object localObject = null; ; localObject = str2)
    {
      DecodedInformation localDecodedInformation = decodeGeneralPurposeField(paramInt, (String)localObject);
      String str1 = FieldParser.parseFieldsInGeneralPurpose(localDecodedInformation.getNewString());
      if (str1 != null)
        paramStringBuilder.append(str1);
      if (localDecodedInformation.isRemaining())
        str2 = String.valueOf(localDecodedInformation.getRemainingValue());
      else
        str2 = null;
      if (paramInt == localDecodedInformation.getNewPosition())
        return paramStringBuilder.toString();
      paramInt = localDecodedInformation.getNewPosition();
    }
  }

  DecodedInformation decodeGeneralPurposeField(int paramInt, String paramString)
  {
    this.buffer.setLength(0);
    if (paramString != null)
      this.buffer.append(paramString);
    this.current.setPosition(paramInt);
    DecodedInformation localDecodedInformation = parseBlocks();
    if ((localDecodedInformation != null) && (localDecodedInformation.isRemaining()))
      return new DecodedInformation(this.current.getPosition(), this.buffer.toString(), localDecodedInformation.getRemainingValue());
    return new DecodedInformation(this.current.getPosition(), this.buffer.toString());
  }

  int extractNumericValueFromBitArray(int paramInt1, int paramInt2)
  {
    return extractNumericValueFromBitArray(this.information, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder
 * JD-Core Version:    0.6.1
 */