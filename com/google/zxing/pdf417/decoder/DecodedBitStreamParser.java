package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import java.math.BigInteger;

final class DecodedBitStreamParser
{
  private static final int AL = 28;
  private static final int AS = 27;
  private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
  private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
  private static final int BYTE_COMPACTION_MODE_LATCH = 901;
  private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
  private static final BigInteger[] EXP900;
  private static final int LL = 27;
  private static final int MACRO_PDF417_TERMINATOR = 922;
  private static final int MAX_NUMERIC_CODEWORDS = 15;
  private static final char[] MIXED_CHARS;
  private static final int ML = 28;
  private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
  private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
  private static final int PAL = 29;
  private static final int PL = 25;
  private static final int PS = 29;
  private static final char[] PUNCT_CHARS = { 59, 60, 62, 64, 91, 92, 125, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39 };
  private static final int TEXT_COMPACTION_MODE_LATCH = 900;

  static
  {
    MIXED_CHARS = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94 };
    EXP900 = new BigInteger[16];
    EXP900[0] = BigInteger.ONE;
    BigInteger localBigInteger = BigInteger.valueOf(900L);
    EXP900[1] = localBigInteger;
    for (int i = 2; i < EXP900.length; i++)
      EXP900[i] = EXP900[(i - 1)].multiply(localBigInteger);
  }

  private static int byteCompaction(int paramInt1, int[] paramArrayOfInt, int paramInt2, StringBuilder paramStringBuilder)
  {
    int i = 922;
    int j = 923;
    long l1 = 900L;
    if (paramInt1 == 901)
    {
      char[] arrayOfChar2 = new char[6];
      int[] arrayOfInt = new int[6];
      int i5 = paramInt2 + 1;
      int i6 = paramArrayOfInt[paramInt2];
      k = i5;
      int i7 = 0;
      int i8 = 0;
      long l4 = 0L;
      while ((k < paramArrayOfInt[0]) && (i7 == 0))
      {
        int i11 = i8 + 1;
        arrayOfInt[i8] = i6;
        long l5 = l4 * l1 + i6;
        int i12 = k + 1;
        int i13 = paramArrayOfInt[k];
        if ((i13 != 900) && (i13 != 901) && (i13 != 902) && (i13 != 924) && (i13 != 928) && (i13 != j) && (i13 != i))
        {
          if ((i11 % 5 == 0) && (i11 > 0))
          {
            l4 = l5;
            for (int i15 = 0; i15 < 6; i15++)
            {
              arrayOfChar2[(5 - i15)] = (char)(int)(l4 % 256L);
              l4 >>= 8;
            }
            paramStringBuilder.append(arrayOfChar2);
            i = 922;
            j = 923;
            l1 = 900L;
            i8 = 0;
          }
          else
          {
            i8 = i11;
            l4 = l5;
            i = 922;
            j = 923;
            l1 = 900L;
          }
        }
        else
        {
          i12--;
          i8 = i11;
          l4 = l5;
          i = 922;
          j = 923;
          l1 = 900L;
          i7 = 1;
        }
        int i14 = i12;
        i6 = i13;
        k = i14;
      }
      int i9;
      if ((k == paramArrayOfInt[0]) && (i6 < 900))
      {
        i9 = i8 + 1;
        arrayOfInt[i8] = i6;
      }
      else
      {
        i9 = i8;
      }
      for (int i10 = 0; i10 < i9; i10++)
        paramStringBuilder.append((char)arrayOfInt[i10]);
    }
    if (paramInt1 == 924)
    {
      k = paramInt2;
      int m = 0;
      int n = 0;
      long l2 = 0L;
      while ((k < paramArrayOfInt[0]) && (n == 0))
      {
        int i1 = k + 1;
        int i2 = paramArrayOfInt[k];
        if (i2 < 900)
        {
          m++;
          long l3 = l2 * 900L + i2;
          k = i1;
          l2 = l3;
        }
        else if ((i2 != 900) && (i2 != 901) && (i2 != 902) && (i2 != 924) && (i2 != 928) && (i2 != 923) && (i2 != 922))
        {
          k = i1;
        }
        else
        {
          k = i1 - 1;
          n = 1;
        }
        if ((m % 5 == 0) && (m > 0))
        {
          int i3 = 6;
          char[] arrayOfChar1 = new char[i3];
          int i4 = 0;
          while (i4 < i3)
          {
            arrayOfChar1[(5 - i4)] = (char)(int)(l2 & 0xFF);
            l2 >>= 8;
            i4++;
            i3 = 6;
          }
          paramStringBuilder.append(arrayOfChar1);
          m = 0;
        }
      }
    }
    int k = paramInt2;
    return k;
  }

  static DecoderResult decode(int[] paramArrayOfInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    int i = paramArrayOfInt[1];
    int j = 2;
    while (j < paramArrayOfInt[0])
    {
      int k;
      if (i != 913)
      {
        if (i != 924)
          switch (i)
          {
          default:
            k = textCompaction(paramArrayOfInt, j - 1, localStringBuilder);
            break;
          case 902:
            k = numericCompaction(paramArrayOfInt, j, localStringBuilder);
            break;
          case 901:
            k = byteCompaction(i, paramArrayOfInt, j, localStringBuilder);
            break;
          case 900:
            k = textCompaction(paramArrayOfInt, j, localStringBuilder);
            break;
          }
        else
          k = byteCompaction(i, paramArrayOfInt, j, localStringBuilder);
      }
      else
        k = byteCompaction(i, paramArrayOfInt, j, localStringBuilder);
      if (k < paramArrayOfInt.length)
      {
        j = k + 1;
        i = paramArrayOfInt[k];
      }
      else
      {
        throw FormatException.getFormatInstance();
      }
    }
    if (localStringBuilder.length() == 0)
      throw FormatException.getFormatInstance();
    return new DecoderResult(null, localStringBuilder.toString(), null, null);
  }

  private static String decodeBase900toBase10(int[] paramArrayOfInt, int paramInt)
  {
    BigInteger localBigInteger = BigInteger.ZERO;
    for (int i = 0; i < paramInt; i++)
      localBigInteger = localBigInteger.add(EXP900[(paramInt - i - 1)].multiply(BigInteger.valueOf(paramArrayOfInt[i])));
    String str = localBigInteger.toString();
    if (str.charAt(0) != '1')
      throw FormatException.getFormatInstance();
    return str.substring(1);
  }

  private static void decodeTextCompaction(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, StringBuilder paramStringBuilder)
  {
    Mode localMode1 = Mode.ALPHA;
    Mode localMode2 = Mode.ALPHA;
    Object localObject1 = localMode1;
    Object localObject2 = localMode2;
    for (int i = 0; i < paramInt; i++)
    {
      int j = paramArrayOfInt1[i];
      Mode localMode3;
      switch (1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[localObject1.ordinal()])
      {
      default:
        break;
      case 6:
        if (j < 29)
          c = PUNCT_CHARS[j];
        else if (j == 29)
          localMode3 = Mode.ALPHA;
        else if (j == 913)
          paramStringBuilder.append((char)paramArrayOfInt2[i]);
        else if (j == 900)
          localMode3 = Mode.ALPHA;
        break;
      case 5:
        if (j < 26)
        {
          c = (char)(j + 65);
          localObject1 = localObject2;
          break label642;
        }
        if (j == 26)
          localObject1 = localObject2;
        else if (j == 900)
          localMode3 = Mode.ALPHA;
        else
          localObject1 = localObject2;
        break;
      case 4:
        if (j < 29)
        {
          c = PUNCT_CHARS[j];
          break label642;
        }
        if (j == 29)
        {
          localMode3 = Mode.ALPHA;
        }
        else
        {
          if (j == 913)
          {
            paramStringBuilder.append((char)paramArrayOfInt2[i]);
            break label639;
          }
          if (j != 900)
            break label639;
          localMode3 = Mode.ALPHA;
        }
        break;
      case 3:
        if (j < 25)
        {
          c = MIXED_CHARS[j];
          break label642;
        }
        if (j == 25)
          localMode3 = Mode.PUNCT;
        break;
      case 2:
      case 1:
      }
      while (true)
      {
        localObject1 = localMode3;
        break;
        if (j != 26)
        {
          if (j == 27)
          {
            localMode3 = Mode.LOWER;
          }
          else if (j == 28)
          {
            localMode3 = Mode.ALPHA;
          }
          else if (j == 29)
          {
            localMode3 = Mode.PUNCT_SHIFT;
          }
          else
          {
            if (j == 913)
            {
              paramStringBuilder.append((char)paramArrayOfInt2[i]);
              break;
            }
            if (j != 900)
              break;
            localMode3 = Mode.ALPHA;
            continue;
            if (j < 26)
            {
              c = (char)(j + 97);
              break label642;
            }
            if (j == 26)
              break label550;
            if (j != 27)
              break label458;
            localMode3 = Mode.ALPHA_SHIFT;
          }
        }
        else
        {
          while (true)
          {
            localObject2 = localObject1;
            break;
            label458: if (j == 28)
            {
              localMode3 = Mode.MIXED;
              break;
            }
            if (j == 29)
            {
              localMode3 = Mode.PUNCT_SHIFT;
            }
            else
            {
              if (j == 913)
              {
                paramStringBuilder.append((char)paramArrayOfInt2[i]);
                break label639;
              }
              if (j != 900)
                break label639;
              localMode3 = Mode.ALPHA;
              break;
              if (j < 26)
              {
                c = (char)(j + 65);
                break label642;
              }
              if (j == 26)
              {
                label550: c = ' ';
                break label642;
              }
              if (j == 27)
              {
                localMode3 = Mode.LOWER;
                break;
              }
              if (j == 28)
              {
                localMode3 = Mode.MIXED;
                break;
              }
              if (j != 29)
                break label602;
              localMode3 = Mode.PUNCT_SHIFT;
            }
          }
          label602: if (j == 913)
          {
            paramStringBuilder.append((char)paramArrayOfInt2[i]);
            break;
          }
          if (j != 900)
            break;
          localMode3 = Mode.ALPHA;
        }
      }
      label639: char c = '\000';
      label642: if (c != 0)
        paramStringBuilder.append(c);
    }
  }

  private static int numericCompaction(int[] paramArrayOfInt, int paramInt, StringBuilder paramStringBuilder)
  {
    int[] arrayOfInt = new int[15];
    int i = 0;
    int j = 0;
    while ((paramInt < paramArrayOfInt[0]) && (i == 0))
    {
      int k = paramInt + 1;
      int m = paramArrayOfInt[paramInt];
      if (k == paramArrayOfInt[0])
        i = 1;
      if (m < 900)
      {
        arrayOfInt[j] = m;
        j++;
      }
      else if ((m == 900) || (m == 901) || (m == 924) || (m == 928) || (m == 923) || (m == 922))
      {
        k--;
        i = 1;
      }
      if ((j % 15 == 0) || (m == 902) || (i != 0))
      {
        paramStringBuilder.append(decodeBase900toBase10(arrayOfInt, j));
        j = 0;
      }
      paramInt = k;
    }
    return paramInt;
  }

  private static int textCompaction(int[] paramArrayOfInt, int paramInt, StringBuilder paramStringBuilder)
  {
    int[] arrayOfInt1 = new int[paramArrayOfInt[0] << 1];
    int[] arrayOfInt2 = new int[paramArrayOfInt[0] << 1];
    int i = 0;
    int j = 0;
    while ((paramInt < paramArrayOfInt[0]) && (i == 0))
    {
      int k = paramInt + 1;
      int m = paramArrayOfInt[paramInt];
      if (m < 900)
      {
        arrayOfInt1[j] = (m / 30);
        arrayOfInt1[(j + 1)] = (m % 30);
        j += 2;
      }
      while (true)
      {
        label76: paramInt = k;
        break;
        if (m == 913)
          break label184;
        if (m == 924)
          break label175;
        switch (m)
        {
        default:
        case 902:
        case 901:
        case 900:
        }
      }
      int n = k - 1;
      label175: for (n = k - 1; ; n = k - 1)
      {
        paramInt = n;
        i = 1;
        break;
        int i1 = j + 1;
        arrayOfInt1[j] = 900;
        j = i1;
        break label76;
      }
      label184: arrayOfInt1[j] = 913;
      paramInt = k + 1;
      arrayOfInt2[j] = paramArrayOfInt[k];
      j++;
    }
    decodeTextCompaction(arrayOfInt1, arrayOfInt2, j, paramStringBuilder);
    return paramInt;
  }

  private static enum Mode
  {
    static
    {
      ALPHA_SHIFT = new Mode("ALPHA_SHIFT", 4);
      PUNCT_SHIFT = new Mode("PUNCT_SHIFT", 5);
      Mode[] arrayOfMode = new Mode[6];
      arrayOfMode[0] = ALPHA;
      arrayOfMode[1] = LOWER;
      arrayOfMode[2] = MIXED;
      arrayOfMode[3] = PUNCT;
      arrayOfMode[4] = ALPHA_SHIFT;
      arrayOfMode[5] = PUNCT_SHIFT;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.1
 */