package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.nio.charset.Charset;
import java.util.Arrays;

public final class HighLevelEncoder
{
  static final int ASCII_ENCODATION = 0;
  static final int BASE256_ENCODATION = 5;
  static final int C40_ENCODATION = 1;
  static final char C40_UNLATCH = 'þ';
  static final int EDIFACT_ENCODATION = 4;
  static final char LATCH_TO_ANSIX12 = 'î';
  static final char LATCH_TO_BASE256 = 'ç';
  static final char LATCH_TO_C40 = 'æ';
  static final char LATCH_TO_EDIFACT = 'ð';
  static final char LATCH_TO_TEXT = 'ï';
  private static final char MACRO_05 = 'ì';
  private static final String MACRO_05_HEADER = "[)>\03605\035";
  private static final char MACRO_06 = 'í';
  private static final String MACRO_06_HEADER = "[)>\03606\035";
  private static final String MACRO_TRAILER = "\036\004";
  private static final char PAD = '';
  static final int TEXT_ENCODATION = 2;
  static final char UPPER_SHIFT = 'ë';
  static final int X12_ENCODATION = 3;
  static final char X12_UNLATCH = 'þ';

  public static int determineConsecutiveDigitCount(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    int j = 0;
    if (paramInt < i)
    {
      char c = paramCharSequence.charAt(paramInt);
      while ((isDigit(c)) && (paramInt < i))
      {
        j++;
        paramInt++;
        if (paramInt < i)
          c = paramCharSequence.charAt(paramInt);
      }
    }
    return j;
  }

  public static String encodeHighLevel(String paramString)
  {
    return encodeHighLevel(paramString, SymbolShapeHint.FORCE_NONE, null, null);
  }

  public static String encodeHighLevel(String paramString, SymbolShapeHint paramSymbolShapeHint, Dimension paramDimension1, Dimension paramDimension2)
  {
    Encoder[] arrayOfEncoder = new Encoder[6];
    arrayOfEncoder[0] = new ASCIIEncoder();
    arrayOfEncoder[1] = new C40Encoder();
    arrayOfEncoder[2] = new TextEncoder();
    arrayOfEncoder[3] = new X12Encoder();
    arrayOfEncoder[4] = new EdifactEncoder();
    arrayOfEncoder[5] = new Base256Encoder();
    EncoderContext localEncoderContext = new EncoderContext(paramString);
    localEncoderContext.setSymbolShape(paramSymbolShapeHint);
    localEncoderContext.setSizeConstraints(paramDimension1, paramDimension2);
    int i;
    if ((paramString.startsWith("[)>\03605\035")) && (paramString.endsWith("\036\004")))
    {
      localEncoderContext.writeCodeword('ì');
      localEncoderContext.setSkipAtEnd(2);
      localEncoderContext.pos += "[)>\03605\035".length();
      i = 0;
    }
    else
    {
      boolean bool1 = paramString.startsWith("[)>\03606\035");
      i = 0;
      if (bool1)
      {
        boolean bool2 = paramString.endsWith("\036\004");
        i = 0;
        if (bool2)
        {
          localEncoderContext.writeCodeword('í');
          localEncoderContext.setSkipAtEnd(2);
          localEncoderContext.pos += "[)>\03606\035".length();
        }
      }
    }
    while (localEncoderContext.hasMoreCharacters())
    {
      arrayOfEncoder[i].encode(localEncoderContext);
      if (localEncoderContext.newEncoding >= 0)
      {
        i = localEncoderContext.newEncoding;
        localEncoderContext.resetEncoderSignal();
      }
    }
    int j = localEncoderContext.codewords.length();
    localEncoderContext.updateSymbolInfo();
    int k = localEncoderContext.symbolInfo.dataCapacity;
    if ((j < k) && (i != 0) && (i != 5))
      localEncoderContext.writeCodeword('þ');
    StringBuilder localStringBuilder = localEncoderContext.codewords;
    if (localStringBuilder.length() < k)
      localStringBuilder.append('');
    while (localStringBuilder.length() < k)
      localStringBuilder.append(randomize253State('', 1 + localStringBuilder.length()));
    return localEncoderContext.codewords.toString();
  }

  private static int findMinimums(float[] paramArrayOfFloat, int[] paramArrayOfInt, int paramInt, byte[] paramArrayOfByte)
  {
    Arrays.fill(paramArrayOfByte, (byte)0);
    int i = paramInt;
    for (int j = 0; j < 6; j++)
    {
      paramArrayOfInt[j] = (int)Math.ceil(paramArrayOfFloat[j]);
      int k = paramArrayOfInt[j];
      if (i > k)
      {
        Arrays.fill(paramArrayOfByte, (byte)0);
        i = k;
      }
      if (i == k)
        paramArrayOfByte[j] = (byte)(1 + paramArrayOfByte[j]);
    }
    return i;
  }

  public static byte[] getBytesForMessage(String paramString)
  {
    return paramString.getBytes(Charset.forName("cp437"));
  }

  private static int getMinimumCount(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = 0;
    while (i < 6)
    {
      j += paramArrayOfByte[i];
      i++;
    }
    return j;
  }

  static void illegalCharacter(char paramChar)
  {
    String str1 = Integer.toHexString(paramChar);
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("0000".substring(0, 4 - str1.length()));
    localStringBuilder1.append(str1);
    String str2 = localStringBuilder1.toString();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Illegal character: ");
    localStringBuilder2.append(paramChar);
    localStringBuilder2.append(" (0x");
    localStringBuilder2.append(str2);
    localStringBuilder2.append(')');
    throw new IllegalArgumentException(localStringBuilder2.toString());
  }

  static boolean isDigit(char paramChar)
  {
    boolean bool;
    if ((paramChar >= '0') && (paramChar <= '9'))
      bool = true;
    else
      bool = false;
    return bool;
  }

  static boolean isExtendedASCII(char paramChar)
  {
    boolean bool;
    if ((paramChar >= '') && (paramChar <= 'ÿ'))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static boolean isNativeC40(char paramChar)
  {
    boolean bool;
    if ((paramChar != ' ') && ((paramChar < '0') || (paramChar > '9')) && ((paramChar < 'A') || (paramChar > 'Z')))
      bool = false;
    else
      bool = true;
    return bool;
  }

  private static boolean isNativeEDIFACT(char paramChar)
  {
    boolean bool;
    if ((paramChar >= ' ') && (paramChar <= '^'))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static boolean isNativeText(char paramChar)
  {
    boolean bool;
    if ((paramChar != ' ') && ((paramChar < '0') || (paramChar > '9')) && ((paramChar < 'a') || (paramChar > 'z')))
      bool = false;
    else
      bool = true;
    return bool;
  }

  private static boolean isNativeX12(char paramChar)
  {
    boolean bool;
    if ((!isX12TermSep(paramChar)) && (paramChar != ' ') && ((paramChar < '0') || (paramChar > '9')) && ((paramChar < 'A') || (paramChar > 'Z')))
      bool = false;
    else
      bool = true;
    return bool;
  }

  private static boolean isSpecialB256(char paramChar)
  {
    return false;
  }

  private static boolean isX12TermSep(char paramChar)
  {
    boolean bool;
    if ((paramChar != '\r') && (paramChar != '*') && (paramChar != '>'))
      bool = false;
    else
      bool = true;
    return bool;
  }

  static int lookAheadTest(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (paramInt1 >= paramCharSequence.length())
      return paramInt2;
    Object localObject;
    if (paramInt2 == 0)
    {
      localObject = new float[] { 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.25F };
    }
    else
    {
      float[] arrayOfFloat = { 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 2.25F };
      arrayOfFloat[paramInt2] = 0.0F;
      localObject = arrayOfFloat;
    }
    int i = 0;
    int[] arrayOfInt1;
    do
    {
      do
      {
        int k;
        do
        {
          int j = paramInt1 + i;
          if (j == paramCharSequence.length())
          {
            byte[] arrayOfByte2 = new byte[6];
            int[] arrayOfInt2 = new int[6];
            int i1 = findMinimums((float[])localObject, arrayOfInt2, 2147483647, arrayOfByte2);
            int i2 = getMinimumCount(arrayOfByte2);
            if (arrayOfInt2[0] == i1)
              return 0;
            if ((i2 == 1) && (arrayOfByte2[5] > 0))
              return 5;
            if ((i2 == 1) && (arrayOfByte2[4] > 0))
              return 4;
            if ((i2 == 1) && (arrayOfByte2[2] > 0))
              return 2;
            if ((i2 == 1) && (arrayOfByte2[3] > 0))
              return 3;
            return 1;
          }
          char c1 = paramCharSequence.charAt(j);
          i++;
          if (isDigit(c1))
          {
            localObject[0] = (float)(0.5D + localObject[0]);
          }
          else if (isExtendedASCII(c1))
          {
            localObject[0] = (int)Math.ceil(localObject[0]);
            localObject[0] = (2.0F + localObject[0]);
          }
          else
          {
            localObject[0] = (int)Math.ceil(localObject[0]);
            localObject[0] = (1.0F + localObject[0]);
          }
          if (isNativeC40(c1))
            localObject[1] = (0.6666667F + localObject[1]);
          else if (isExtendedASCII(c1))
            localObject[1] = (2.666667F + localObject[1]);
          else
            localObject[1] = (1.333333F + localObject[1]);
          if (isNativeText(c1))
            localObject[2] = (0.6666667F + localObject[2]);
          else if (isExtendedASCII(c1))
            localObject[2] = (2.666667F + localObject[2]);
          else
            localObject[2] = (1.333333F + localObject[2]);
          if (isNativeX12(c1))
            localObject[3] = (0.6666667F + localObject[3]);
          else if (isExtendedASCII(c1))
            localObject[3] = (4.333334F + localObject[3]);
          else
            localObject[3] = (3.333333F + localObject[3]);
          if (isNativeEDIFACT(c1))
            localObject[4] = (0.75F + localObject[4]);
          else if (isExtendedASCII(c1))
            localObject[4] = (4.25F + localObject[4]);
          else
            localObject[4] = (3.25F + localObject[4]);
          if (isSpecialB256(c1))
          {
            k = 5;
            localObject[k] = (4.0F + localObject[k]);
          }
          else
          {
            k = 5;
            localObject[k] = (1.0F + localObject[k]);
          }
        }
        while (i < 4);
        arrayOfInt1 = new int[6];
        byte[] arrayOfByte1 = new byte[6];
        findMinimums((float[])localObject, arrayOfInt1, 2147483647, arrayOfByte1);
        int m = getMinimumCount(arrayOfByte1);
        if ((arrayOfInt1[0] < arrayOfInt1[k]) && (arrayOfInt1[0] < arrayOfInt1[1]) && (arrayOfInt1[0] < arrayOfInt1[2]) && (arrayOfInt1[0] < arrayOfInt1[3]) && (arrayOfInt1[0] < arrayOfInt1[4]))
          return 0;
        if ((arrayOfInt1[5] < arrayOfInt1[0]) || (arrayOfByte1[1] + arrayOfByte1[2] + arrayOfByte1[3] + arrayOfByte1[4] == 0))
          break;
        if ((m == 1) && (arrayOfByte1[4] > 0))
          return 4;
        if ((m == 1) && (arrayOfByte1[2] > 0))
          return 2;
        if ((m == 1) && (arrayOfByte1[3] > 0))
          return 3;
      }
      while ((1 + arrayOfInt1[1] >= arrayOfInt1[0]) || (1 + arrayOfInt1[1] >= arrayOfInt1[5]) || (1 + arrayOfInt1[1] >= arrayOfInt1[4]) || (1 + arrayOfInt1[1] >= arrayOfInt1[2]));
      if (arrayOfInt1[1] < arrayOfInt1[3])
        return 1;
    }
    while (arrayOfInt1[1] != arrayOfInt1[3]);
    for (int n = 1 + (paramInt1 + i); n < paramCharSequence.length(); n++)
    {
      char c2 = paramCharSequence.charAt(n);
      if (isX12TermSep(c2))
        return 3;
      if (!isNativeX12(c2))
        break;
    }
    return 1;
    return 5;
  }

  private static char randomize253State(char paramChar, int paramInt)
  {
    int i = paramChar + (1 + paramInt * 149 % 253);
    if (i <= 254);
    char c;
    while (true)
    {
      c = (char)i;
      break;
      i -= 254;
    }
    return c;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.HighLevelEncoder
 * JD-Core Version:    0.6.1
 */