package com.google.zxing.datamatrix.encoder;

final class Base256Encoder
  implements Encoder
{
  private static char randomize255State(char paramChar, int paramInt)
  {
    int i = paramChar + (1 + paramInt * 149 % 255);
    if (i <= 255)
      return (char)i;
    return (char)(i - 256);
  }

  public void encode(EncoderContext paramEncoderContext)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i = 0;
    localStringBuilder1.append('\000');
    while (paramEncoderContext.hasMoreCharacters())
    {
      localStringBuilder1.append(paramEncoderContext.getCurrentChar());
      paramEncoderContext.pos = (1 + paramEncoderContext.pos);
      int i1 = HighLevelEncoder.lookAheadTest(paramEncoderContext.msg, paramEncoderContext.pos, getEncodingMode());
      if (i1 != getEncodingMode())
        paramEncoderContext.signalEncoderChange(i1);
    }
    int j = localStringBuilder1.length() - 1;
    int k = 1 + (j + paramEncoderContext.getCodewordCount());
    paramEncoderContext.updateSymbolInfo(k);
    int m;
    if (paramEncoderContext.symbolInfo.dataCapacity - k > 0)
      m = 1;
    else
      m = 0;
    if ((paramEncoderContext.hasMoreCharacters()) || (m != 0))
      if (j <= 249)
      {
        localStringBuilder1.setCharAt(0, (char)j);
      }
      else
      {
        if ((j <= 249) || (j > 1555))
          break label234;
        localStringBuilder1.setCharAt(0, (char)(249 + j / 250));
        localStringBuilder1.insert(1, (char)(j % 250));
      }
    int n = localStringBuilder1.length();
    while (i < n)
    {
      paramEncoderContext.writeCodeword(randomize255State(localStringBuilder1.charAt(i), 1 + paramEncoderContext.getCodewordCount()));
      i++;
    }
    return;
    label234: StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Message length not in valid ranges: ");
    localStringBuilder2.append(j);
    throw new IllegalStateException(localStringBuilder2.toString());
  }

  public int getEncodingMode()
  {
    return 5;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.Base256Encoder
 * JD-Core Version:    0.6.1
 */