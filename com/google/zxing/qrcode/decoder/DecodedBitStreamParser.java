package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class DecodedBitStreamParser
{
  private static final char[] ALPHANUMERIC_CHARS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 32, 36, 37, 42, 43, 45, 46, 47, 58 };
  private static final int GB2312_SUBSET = 1;

  static DecoderResult decode(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel, Map<DecodeHintType, ?> paramMap)
  {
    BitSource localBitSource = new BitSource(paramArrayOfByte);
    StringBuilder localStringBuilder = new StringBuilder(50);
    ArrayList localArrayList = new ArrayList(1);
    CharacterSetECI localCharacterSetECI = null;
    boolean bool = false;
    while (true)
    {
      Mode localMode1;
      Mode localMode2;
      try
      {
        if (localBitSource.available() < 4)
        {
          localMode1 = Mode.TERMINATOR;
        }
        else
        {
          localMode1 = Mode.forBits(localBitSource.readBits(4));
          break label373;
          if (localMode2 != Mode.TERMINATOR)
          {
            if ((localMode2 == Mode.FNC1_FIRST_POSITION) || (localMode2 == Mode.FNC1_SECOND_POSITION))
              break label380;
            if (localMode2 == Mode.STRUCTURED_APPEND)
            {
              if (localBitSource.available() < 16)
                throw FormatException.getFormatInstance();
              localBitSource.readBits(16);
            }
            else if (localMode2 == Mode.ECI)
            {
              localCharacterSetECI = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(localBitSource));
              if (localCharacterSetECI == null)
                throw FormatException.getFormatInstance();
            }
            else if (localMode2 == Mode.HANZI)
            {
              int j = localBitSource.readBits(4);
              int k = localBitSource.readBits(localMode2.getCharacterCountBits(paramVersion));
              if (j == 1)
                decodeHanziSegment(localBitSource, localStringBuilder, k);
            }
            else
            {
              int i = localBitSource.readBits(localMode2.getCharacterCountBits(paramVersion));
              if (localMode2 == Mode.NUMERIC)
                decodeNumericSegment(localBitSource, localStringBuilder, i);
              else if (localMode2 == Mode.ALPHANUMERIC)
                decodeAlphanumericSegment(localBitSource, localStringBuilder, i, bool);
              else if (localMode2 == Mode.BYTE)
                decodeByteSegment(localBitSource, localStringBuilder, i, localCharacterSetECI, localArrayList, paramMap);
              else if (localMode2 == Mode.KANJI)
                decodeKanjiSegment(localBitSource, localStringBuilder, i);
              else
                throw FormatException.getFormatInstance();
            }
          }
          Mode localMode3 = Mode.TERMINATOR;
          if (localMode2 != localMode3)
            continue;
          String str1 = localStringBuilder.toString();
          if (localArrayList.isEmpty())
            localArrayList = null;
          String str2;
          if (paramErrorCorrectionLevel == null)
            str2 = null;
          else
            str2 = paramErrorCorrectionLevel.toString();
          return new DecoderResult(paramArrayOfByte, str1, localArrayList, str2);
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw FormatException.getFormatInstance();
        localMode2 = localMode1;
        tmpTernaryOp = localIllegalArgumentException;
      }
      label373: continue;
      label380: bool = true;
    }
  }

  private static void decodeAlphanumericSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt, boolean paramBoolean)
  {
    int i = paramStringBuilder.length();
    while (paramInt > 1)
    {
      if (paramBitSource.available() < 11)
        throw FormatException.getFormatInstance();
      int k = paramBitSource.readBits(11);
      paramStringBuilder.append(toAlphaNumericChar(k / 45));
      paramStringBuilder.append(toAlphaNumericChar(k % 45));
      paramInt -= 2;
    }
    if (paramInt == 1)
    {
      if (paramBitSource.available() < 6)
        throw FormatException.getFormatInstance();
      paramStringBuilder.append(toAlphaNumericChar(paramBitSource.readBits(6)));
    }
    if (paramBoolean)
      while (i < paramStringBuilder.length())
      {
        if (paramStringBuilder.charAt(i) == '%')
        {
          if (i < paramStringBuilder.length() - 1)
          {
            int j = i + 1;
            if (paramStringBuilder.charAt(j) == '%')
            {
              paramStringBuilder.deleteCharAt(j);
              break label166;
            }
          }
          paramStringBuilder.setCharAt(i, '\035');
        }
        label166: i++;
      }
  }

  // ERROR //
  private static void decodeByteSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt, CharacterSetECI paramCharacterSetECI, java.util.Collection<byte[]> paramCollection, Map<DecodeHintType, ?> paramMap)
  {
    // Byte code:
    //   0: iload_2
    //   1: iconst_3
    //   2: ishl
    //   3: aload_0
    //   4: invokevirtual 82	com/google/zxing/common/BitSource:available	()I
    //   7: if_icmple +7 -> 14
    //   10: invokestatic 111	com/google/zxing/FormatException:getFormatInstance	()Lcom/google/zxing/FormatException;
    //   13: athrow
    //   14: iload_2
    //   15: newarray byte
    //   17: astore 6
    //   19: iconst_0
    //   20: istore 7
    //   22: iload 7
    //   24: iload_2
    //   25: if_icmpge +21 -> 46
    //   28: aload 6
    //   30: iload 7
    //   32: aload_0
    //   33: bipush 8
    //   35: invokevirtual 92	com/google/zxing/common/BitSource:readBits	(I)I
    //   38: i2b
    //   39: bastore
    //   40: iinc 7 1
    //   43: goto -21 -> 22
    //   46: aload_3
    //   47: ifnonnull +15 -> 62
    //   50: aload 6
    //   52: aload 5
    //   54: invokestatic 209	com/google/zxing/common/StringUtils:guessEncoding	([BLjava/util/Map;)Ljava/lang/String;
    //   57: astore 8
    //   59: goto +9 -> 68
    //   62: aload_3
    //   63: invokevirtual 212	com/google/zxing/common/CharacterSetECI:name	()Ljava/lang/String;
    //   66: astore 8
    //   68: aload_1
    //   69: new 214	java/lang/String
    //   72: dup
    //   73: aload 6
    //   75: aload 8
    //   77: invokespecial 217	java/lang/String:<init>	([BLjava/lang/String;)V
    //   80: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload 4
    //   86: aload 6
    //   88: invokeinterface 226 2 0
    //   93: pop
    //   94: return
    //   95: invokestatic 111	com/google/zxing/FormatException:getFormatInstance	()Lcom/google/zxing/FormatException;
    //   98: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   68	84	95	java/io/UnsupportedEncodingException
  }

  // ERROR //
  private static void decodeHanziSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt)
  {
    // Byte code:
    //   0: iload_2
    //   1: bipush 13
    //   3: imul
    //   4: aload_0
    //   5: invokevirtual 82	com/google/zxing/common/BitSource:available	()I
    //   8: if_icmple +7 -> 15
    //   11: invokestatic 111	com/google/zxing/FormatException:getFormatInstance	()Lcom/google/zxing/FormatException;
    //   14: athrow
    //   15: iload_2
    //   16: iconst_2
    //   17: imul
    //   18: newarray byte
    //   20: astore_3
    //   21: iconst_0
    //   22: istore 4
    //   24: iload_2
    //   25: ifle +88 -> 113
    //   28: aload_0
    //   29: bipush 13
    //   31: invokevirtual 92	com/google/zxing/common/BitSource:readBits	(I)I
    //   34: istore 6
    //   36: iload 6
    //   38: bipush 96
    //   40: idiv
    //   41: bipush 8
    //   43: ishl
    //   44: iload 6
    //   46: bipush 96
    //   48: irem
    //   49: ior
    //   50: istore 7
    //   52: iload 7
    //   54: sipush 959
    //   57: if_icmpge +13 -> 70
    //   60: iload 7
    //   62: ldc 227
    //   64: iadd
    //   65: istore 8
    //   67: goto +10 -> 77
    //   70: iload 7
    //   72: ldc 228
    //   74: iadd
    //   75: istore 8
    //   77: aload_3
    //   78: iload 4
    //   80: sipush 255
    //   83: iload 8
    //   85: bipush 8
    //   87: ishr
    //   88: iand
    //   89: i2b
    //   90: bastore
    //   91: aload_3
    //   92: iload 4
    //   94: iconst_1
    //   95: iadd
    //   96: iload 8
    //   98: sipush 255
    //   101: iand
    //   102: i2b
    //   103: bastore
    //   104: iinc 4 2
    //   107: iinc 2 255
    //   110: goto -86 -> 24
    //   113: aload_1
    //   114: new 214	java/lang/String
    //   117: dup
    //   118: aload_3
    //   119: ldc 230
    //   121: invokespecial 217	java/lang/String:<init>	([BLjava/lang/String;)V
    //   124: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: return
    //   129: invokestatic 111	com/google/zxing/FormatException:getFormatInstance	()Lcom/google/zxing/FormatException;
    //   132: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   113	128	129	java/io/UnsupportedEncodingException
  }

  // ERROR //
  private static void decodeKanjiSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt)
  {
    // Byte code:
    //   0: iload_2
    //   1: bipush 13
    //   3: imul
    //   4: aload_0
    //   5: invokevirtual 82	com/google/zxing/common/BitSource:available	()I
    //   8: if_icmple +7 -> 15
    //   11: invokestatic 111	com/google/zxing/FormatException:getFormatInstance	()Lcom/google/zxing/FormatException;
    //   14: athrow
    //   15: iload_2
    //   16: iconst_2
    //   17: imul
    //   18: newarray byte
    //   20: astore_3
    //   21: iconst_0
    //   22: istore 4
    //   24: iload_2
    //   25: ifle +82 -> 107
    //   28: aload_0
    //   29: bipush 13
    //   31: invokevirtual 92	com/google/zxing/common/BitSource:readBits	(I)I
    //   34: istore 6
    //   36: iload 6
    //   38: sipush 192
    //   41: idiv
    //   42: bipush 8
    //   44: ishl
    //   45: iload 6
    //   47: sipush 192
    //   50: irem
    //   51: ior
    //   52: istore 7
    //   54: iload 7
    //   56: sipush 7936
    //   59: if_icmpge +13 -> 72
    //   62: iload 7
    //   64: ldc 231
    //   66: iadd
    //   67: istore 8
    //   69: goto +10 -> 79
    //   72: iload 7
    //   74: ldc 232
    //   76: iadd
    //   77: istore 8
    //   79: aload_3
    //   80: iload 4
    //   82: iload 8
    //   84: bipush 8
    //   86: ishr
    //   87: i2b
    //   88: bastore
    //   89: aload_3
    //   90: iload 4
    //   92: iconst_1
    //   93: iadd
    //   94: iload 8
    //   96: i2b
    //   97: bastore
    //   98: iinc 4 2
    //   101: iinc 2 255
    //   104: goto -80 -> 24
    //   107: aload_1
    //   108: new 214	java/lang/String
    //   111: dup
    //   112: aload_3
    //   113: ldc 234
    //   115: invokespecial 217	java/lang/String:<init>	([BLjava/lang/String;)V
    //   118: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: return
    //   123: invokestatic 111	com/google/zxing/FormatException:getFormatInstance	()Lcom/google/zxing/FormatException;
    //   126: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   107	122	123	java/io/UnsupportedEncodingException
  }

  private static void decodeNumericSegment(BitSource paramBitSource, StringBuilder paramStringBuilder, int paramInt)
  {
    while (paramInt >= 3)
    {
      if (paramBitSource.available() < 10)
        throw FormatException.getFormatInstance();
      int k = paramBitSource.readBits(10);
      if (k >= 1000)
        throw FormatException.getFormatInstance();
      paramStringBuilder.append(toAlphaNumericChar(k / 100));
      paramStringBuilder.append(toAlphaNumericChar(k / 10 % 10));
      paramStringBuilder.append(toAlphaNumericChar(k % 10));
      paramInt -= 3;
    }
    if (paramInt == 2)
    {
      if (paramBitSource.available() < 7)
        throw FormatException.getFormatInstance();
      int j = paramBitSource.readBits(7);
      if (j >= 100)
        throw FormatException.getFormatInstance();
      paramStringBuilder.append(toAlphaNumericChar(j / 10));
      paramStringBuilder.append(toAlphaNumericChar(j % 10));
    }
    else if (paramInt == 1)
    {
      if (paramBitSource.available() < 4)
        throw FormatException.getFormatInstance();
      int i = paramBitSource.readBits(4);
      if (i >= 10)
        throw FormatException.getFormatInstance();
      paramStringBuilder.append(toAlphaNumericChar(i));
    }
  }

  private static int parseECIValue(BitSource paramBitSource)
  {
    int i = paramBitSource.readBits(8);
    if ((i & 0x80) == 0)
      return i & 0x7F;
    if ((i & 0xC0) == 128)
      return paramBitSource.readBits(8) | (i & 0x3F) << 8;
    if ((i & 0xE0) == 192)
      return paramBitSource.readBits(16) | (i & 0x1F) << 16;
    throw FormatException.getFormatInstance();
  }

  private static char toAlphaNumericChar(int paramInt)
  {
    if (paramInt >= ALPHANUMERIC_CHARS.length)
      throw FormatException.getFormatInstance();
    return ALPHANUMERIC_CHARS[paramInt];
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.1
 */