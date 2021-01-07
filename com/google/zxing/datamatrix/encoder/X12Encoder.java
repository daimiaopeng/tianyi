package com.google.zxing.datamatrix.encoder;

final class X12Encoder extends C40Encoder
{
  public void encode(EncoderContext paramEncoderContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (paramEncoderContext.hasMoreCharacters())
    {
      char c = paramEncoderContext.getCurrentChar();
      paramEncoderContext.pos = (1 + paramEncoderContext.pos);
      encodeChar(c, localStringBuilder);
      if (localStringBuilder.length() % 3 == 0)
      {
        writeNextTriplet(paramEncoderContext, localStringBuilder);
        int i = HighLevelEncoder.lookAheadTest(paramEncoderContext.msg, paramEncoderContext.pos, getEncodingMode());
        if (i != getEncodingMode())
          paramEncoderContext.signalEncoderChange(i);
      }
    }
    handleEOD(paramEncoderContext, localStringBuilder);
  }

  int encodeChar(char paramChar, StringBuilder paramStringBuilder)
  {
    if (paramChar == '\r')
      paramStringBuilder.append('\000');
    else if (paramChar == '*')
      paramStringBuilder.append('\001');
    else if (paramChar == '>')
      paramStringBuilder.append('\002');
    else if (paramChar == ' ')
      paramStringBuilder.append('\003');
    else if ((paramChar >= '0') && (paramChar <= '9'))
      paramStringBuilder.append((char)(4 + (paramChar - '0')));
    else if ((paramChar >= 'A') && (paramChar <= 'Z'))
      paramStringBuilder.append((char)(14 + (paramChar - 'A')));
    else
      HighLevelEncoder.illegalCharacter(paramChar);
    return 1;
  }

  public int getEncodingMode()
  {
    return 3;
  }

  void handleEOD(EncoderContext paramEncoderContext, StringBuilder paramStringBuilder)
  {
    paramEncoderContext.updateSymbolInfo();
    int i = paramEncoderContext.symbolInfo.dataCapacity - paramEncoderContext.getCodewordCount();
    int j = paramStringBuilder.length();
    if (j == 2)
    {
      paramEncoderContext.writeCodeword('þ');
      paramEncoderContext.pos -= 2;
      paramEncoderContext.signalEncoderChange(0);
    }
    else if (j == 1)
    {
      paramEncoderContext.pos -= 1;
      if (i > 1)
        paramEncoderContext.writeCodeword('þ');
      paramEncoderContext.signalEncoderChange(0);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.X12Encoder
 * JD-Core Version:    0.6.1
 */