package com.google.zxing.datamatrix.encoder;

final class EdifactEncoder
  implements Encoder
{
  private static void encodeChar(char paramChar, StringBuilder paramStringBuilder)
  {
    if ((paramChar >= ' ') && (paramChar <= '?'))
      paramStringBuilder.append(paramChar);
    else if ((paramChar >= '@') && (paramChar <= '^'))
      paramStringBuilder.append((char)(paramChar - '@'));
    else
      HighLevelEncoder.illegalCharacter(paramChar);
  }

  private static String encodeToCodewords(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length() - paramInt;
    if (i == 0)
      throw new IllegalStateException("StringBuilder must not be empty");
    int j = paramCharSequence.charAt(paramInt);
    int k;
    if (i >= 2)
      k = paramCharSequence.charAt(paramInt + 1);
    else
      k = 0;
    int m;
    if (i >= 3)
      m = paramCharSequence.charAt(paramInt + 2);
    else
      m = 0;
    int n = 0;
    if (i >= 4)
      n = paramCharSequence.charAt(paramInt + 3);
    int i1 = n + ((j << 18) + (k << 12) + (m << 6));
    char c1 = (char)(0xFF & i1 >> 16);
    char c2 = (char)(0xFF & i1 >> 8);
    char c3 = (char)(i1 & 0xFF);
    StringBuilder localStringBuilder = new StringBuilder(3);
    localStringBuilder.append(c1);
    if (i >= 2)
      localStringBuilder.append(c2);
    if (i >= 3)
      localStringBuilder.append(c3);
    return localStringBuilder.toString();
  }

  private static void handleEOD(EncoderContext paramEncoderContext, CharSequence paramCharSequence)
  {
    while (true)
    {
      try
      {
        int i = paramCharSequence.length();
        if (i == 0)
          return;
        j = 1;
        if (i == j)
        {
          paramEncoderContext.updateSymbolInfo();
          int m = paramEncoderContext.symbolInfo.dataCapacity - paramEncoderContext.getCodewordCount();
          int n = paramEncoderContext.getRemainingCharacters();
          if ((n == 0) && (m <= 2))
            return;
        }
        if (i > 4)
          throw new IllegalStateException("Count must not exceed 4");
        int k = i - j;
        String str = encodeToCodewords(paramCharSequence, 0);
        if (((j ^ paramEncoderContext.hasMoreCharacters()) != 0) && (k <= 2))
        {
          if (k <= 2)
          {
            paramEncoderContext.updateSymbolInfo(k + paramEncoderContext.getCodewordCount());
            if (paramEncoderContext.symbolInfo.dataCapacity - paramEncoderContext.getCodewordCount() >= 3)
            {
              paramEncoderContext.updateSymbolInfo(paramEncoderContext.getCodewordCount() + str.length());
              j = 0;
            }
          }
          if (j != 0)
          {
            paramEncoderContext.resetSymbolInfo();
            paramEncoderContext.pos -= k;
          }
          else
          {
            paramEncoderContext.writeCodewords(str);
          }
          return;
        }
      }
      finally
      {
        paramEncoderContext.signalEncoderChange(0);
      }
      int j = 0;
    }
  }

  public void encode(EncoderContext paramEncoderContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (paramEncoderContext.hasMoreCharacters())
    {
      encodeChar(paramEncoderContext.getCurrentChar(), localStringBuilder);
      paramEncoderContext.pos = (1 + paramEncoderContext.pos);
      if (localStringBuilder.length() >= 4)
      {
        paramEncoderContext.writeCodewords(encodeToCodewords(localStringBuilder, 0));
        localStringBuilder.delete(0, 4);
        if (HighLevelEncoder.lookAheadTest(paramEncoderContext.msg, paramEncoderContext.pos, getEncodingMode()) != getEncodingMode())
          paramEncoderContext.signalEncoderChange(0);
      }
    }
    localStringBuilder.append('\037');
    handleEOD(paramEncoderContext, localStringBuilder);
  }

  public int getEncodingMode()
  {
    return 4;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.EdifactEncoder
 * JD-Core Version:    0.6.1
 */