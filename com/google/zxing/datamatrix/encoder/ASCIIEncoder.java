package com.google.zxing.datamatrix.encoder;

final class ASCIIEncoder
  implements Encoder
{
  private static char encodeASCIIDigits(char paramChar1, char paramChar2)
  {
    if ((HighLevelEncoder.isDigit(paramChar1)) && (HighLevelEncoder.isDigit(paramChar2)))
      return (char)(130 + (10 * (paramChar1 - '0') + (paramChar2 - '0')));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("not digits: ");
    localStringBuilder.append(paramChar1);
    localStringBuilder.append(paramChar2);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public void encode(EncoderContext paramEncoderContext)
  {
    if (HighLevelEncoder.determineConsecutiveDigitCount(paramEncoderContext.msg, paramEncoderContext.pos) >= 2)
    {
      paramEncoderContext.writeCodeword(encodeASCIIDigits(paramEncoderContext.msg.charAt(paramEncoderContext.pos), paramEncoderContext.msg.charAt(1 + paramEncoderContext.pos)));
      paramEncoderContext.pos = (2 + paramEncoderContext.pos);
    }
    else
    {
      char c = paramEncoderContext.getCurrentChar();
      int i = HighLevelEncoder.lookAheadTest(paramEncoderContext.msg, paramEncoderContext.pos, getEncodingMode());
      if (i != getEncodingMode())
      {
        switch (i)
        {
        default:
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Illegal mode: ");
          localStringBuilder.append(i);
          throw new IllegalStateException(localStringBuilder.toString());
        case 5:
          paramEncoderContext.writeCodeword('ç');
          paramEncoderContext.signalEncoderChange(5);
          return;
        case 4:
          paramEncoderContext.writeCodeword('ð');
          paramEncoderContext.signalEncoderChange(4);
          break;
        case 3:
          paramEncoderContext.writeCodeword('î');
          paramEncoderContext.signalEncoderChange(3);
          break;
        case 2:
          paramEncoderContext.writeCodeword('ï');
          paramEncoderContext.signalEncoderChange(2);
          break;
        case 1:
          paramEncoderContext.writeCodeword('æ');
          paramEncoderContext.signalEncoderChange(1);
          return;
        }
      }
      else if (HighLevelEncoder.isExtendedASCII(c))
      {
        paramEncoderContext.writeCodeword('ë');
        paramEncoderContext.writeCodeword((char)(1 + (c - '')));
        paramEncoderContext.pos = (1 + paramEncoderContext.pos);
      }
      else
      {
        paramEncoderContext.writeCodeword((char)(c + '\001'));
        paramEncoderContext.pos = (1 + paramEncoderContext.pos);
      }
    }
  }

  public int getEncodingMode()
  {
    return 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.ASCIIEncoder
 * JD-Core Version:    0.6.1
 */