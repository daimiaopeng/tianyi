package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.decoder.Version.ECBlocks;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class Encoder
{
  private static final int[] ALPHANUMERIC_TABLE = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1 };
  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

  static void append8BitBytes(String paramString1, BitArray paramBitArray, String paramString2)
  {
    try
    {
      byte[] arrayOfByte = paramString1.getBytes(paramString2);
      int i = arrayOfByte.length;
      for (int j = 0; j < i; j++)
        paramBitArray.appendBits(arrayOfByte[j], 8);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new WriterException(localUnsupportedEncodingException);
    }
  }

  static void appendAlphanumericBytes(CharSequence paramCharSequence, BitArray paramBitArray)
  {
    int i = paramCharSequence.length();
    int j = 0;
    while (j < i)
    {
      int k = getAlphanumericCode(paramCharSequence.charAt(j));
      if (k == -1)
        throw new WriterException();
      int m = j + 1;
      if (m < i)
      {
        int n = getAlphanumericCode(paramCharSequence.charAt(m));
        if (n == -1)
          throw new WriterException();
        paramBitArray.appendBits(n + k * 45, 11);
        j += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 6);
        j = m;
      }
    }
  }

  static void appendBytes(String paramString1, Mode paramMode, BitArray paramBitArray, String paramString2)
  {
    switch (1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[paramMode.ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid mode: ");
      localStringBuilder.append(paramMode);
      throw new WriterException(localStringBuilder.toString());
    case 4:
      appendKanjiBytes(paramString1, paramBitArray);
      break;
    case 3:
      append8BitBytes(paramString1, paramBitArray, paramString2);
      break;
    case 2:
      appendAlphanumericBytes(paramString1, paramBitArray);
      break;
    case 1:
      appendNumericBytes(paramString1, paramBitArray);
    }
  }

  private static void appendECI(CharacterSetECI paramCharacterSetECI, BitArray paramBitArray)
  {
    paramBitArray.appendBits(Mode.ECI.getBits(), 4);
    paramBitArray.appendBits(paramCharacterSetECI.getValue(), 8);
  }

  static void appendKanjiBytes(String paramString, BitArray paramBitArray)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("Shift_JIS");
      int i = arrayOfByte.length;
      for (int j = 0; j < i; j += 2)
      {
        int k = 0xFF & arrayOfByte[j];
        int m = 0xFF & arrayOfByte[(j + 1)] | k << 8;
        int n;
        if ((m >= 33088) && (m <= 40956))
          n = m - 33088;
        else if ((m >= 57408) && (m <= 60351))
          n = m - 49472;
        else
          n = -1;
        if (n == -1)
          throw new WriterException("Invalid byte sequence");
        paramBitArray.appendBits(192 * (n >> 8) + (n & 0xFF), 13);
      }
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new WriterException(localUnsupportedEncodingException);
    }
  }

  static void appendLengthInfo(int paramInt, Version paramVersion, Mode paramMode, BitArray paramBitArray)
  {
    int i = paramMode.getCharacterCountBits(paramVersion);
    int j = 1 << i;
    if (paramInt >= j)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" is bigger than ");
      localStringBuilder.append(j - 1);
      throw new WriterException(localStringBuilder.toString());
    }
    paramBitArray.appendBits(paramInt, i);
  }

  static void appendModeInfo(Mode paramMode, BitArray paramBitArray)
  {
    paramBitArray.appendBits(paramMode.getBits(), 4);
  }

  static void appendNumericBytes(CharSequence paramCharSequence, BitArray paramBitArray)
  {
    int i = paramCharSequence.length();
    int j = 0;
    while (j < i)
    {
      int k = '￐' + paramCharSequence.charAt(j);
      int m = j + 2;
      if (m < i)
      {
        int n = '￐' + paramCharSequence.charAt(j + 1);
        paramBitArray.appendBits('￐' + paramCharSequence.charAt(m) + (k * 100 + n * 10), 10);
        j += 3;
      }
      else
      {
        j++;
        if (j < i)
        {
          paramBitArray.appendBits('￐' + paramCharSequence.charAt(j) + k * 10, 7);
          j = m;
        }
        else
        {
          paramBitArray.appendBits(k, 4);
        }
      }
    }
  }

  private static int calculateMaskPenalty(ByteMatrix paramByteMatrix)
  {
    return MaskUtil.applyMaskPenaltyRule1(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule2(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule3(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule4(paramByteMatrix);
  }

  private static int chooseMaskPattern(BitArray paramBitArray, ErrorCorrectionLevel paramErrorCorrectionLevel, Version paramVersion, ByteMatrix paramByteMatrix)
  {
    int i = 2147483647;
    int j = -1;
    for (int k = 0; k < 8; k++)
    {
      MatrixUtil.buildMatrix(paramBitArray, paramErrorCorrectionLevel, paramVersion, k, paramByteMatrix);
      int m = calculateMaskPenalty(paramByteMatrix);
      if (m < i)
      {
        j = k;
        i = m;
      }
    }
    return j;
  }

  public static Mode chooseMode(String paramString)
  {
    return chooseMode(paramString, null);
  }

  private static Mode chooseMode(String paramString1, String paramString2)
  {
    if ("Shift_JIS".equals(paramString2))
    {
      Mode localMode;
      if (isOnlyDoubleByteKanji(paramString1))
        localMode = Mode.KANJI;
      else
        localMode = Mode.BYTE;
      return localMode;
    }
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < paramString1.length())
    {
      int m = paramString1.charAt(i);
      if ((m >= 48) && (m <= 57))
      {
        k = 1;
      }
      else
      {
        if (getAlphanumericCode(m) == -1)
          break label91;
        j = 1;
      }
      i++;
      continue;
      label91: return Mode.BYTE;
    }
    if (j != 0)
      return Mode.ALPHANUMERIC;
    if (k != 0)
      return Mode.NUMERIC;
    return Mode.BYTE;
  }

  private static Version chooseVersion(int paramInt, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    for (int i = 1; i <= 40; i++)
    {
      Version localVersion = Version.getVersionForNumber(i);
      if (localVersion.getTotalCodewords() - localVersion.getECBlocksForLevel(paramErrorCorrectionLevel).getTotalECCodewords() >= (paramInt + 7) / 8)
        return localVersion;
    }
    throw new WriterException("Data too big");
  }

  public static QRCode encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    return encode(paramString, paramErrorCorrectionLevel, null);
  }

  public static QRCode encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel, Map<EncodeHintType, ?> paramMap)
  {
    String str;
    if (paramMap == null)
      str = null;
    else
      str = (String)paramMap.get(EncodeHintType.CHARACTER_SET);
    if (str == null)
      str = "ISO-8859-1";
    Mode localMode = chooseMode(paramString, str);
    BitArray localBitArray1 = new BitArray();
    if ((localMode == Mode.BYTE) && (!"ISO-8859-1".equals(str)))
    {
      CharacterSetECI localCharacterSetECI = CharacterSetECI.getCharacterSetECIByName(str);
      if (localCharacterSetECI != null)
        appendECI(localCharacterSetECI, localBitArray1);
    }
    appendModeInfo(localMode, localBitArray1);
    BitArray localBitArray2 = new BitArray();
    appendBytes(paramString, localMode, localBitArray2, str);
    Version localVersion1 = chooseVersion(localBitArray1.getSize() + localMode.getCharacterCountBits(Version.getVersionForNumber(1)) + localBitArray2.getSize(), paramErrorCorrectionLevel);
    Version localVersion2 = chooseVersion(localBitArray1.getSize() + localMode.getCharacterCountBits(localVersion1) + localBitArray2.getSize(), paramErrorCorrectionLevel);
    BitArray localBitArray3 = new BitArray();
    localBitArray3.appendBitArray(localBitArray1);
    int i;
    if (localMode == Mode.BYTE)
      i = localBitArray2.getSizeInBytes();
    else
      i = paramString.length();
    appendLengthInfo(i, localVersion2, localMode, localBitArray3);
    localBitArray3.appendBitArray(localBitArray2);
    Version.ECBlocks localECBlocks = localVersion2.getECBlocksForLevel(paramErrorCorrectionLevel);
    int j = localVersion2.getTotalCodewords() - localECBlocks.getTotalECCodewords();
    terminateBits(j, localBitArray3);
    BitArray localBitArray4 = interleaveWithECBytes(localBitArray3, localVersion2.getTotalCodewords(), j, localECBlocks.getNumBlocks());
    QRCode localQRCode = new QRCode();
    localQRCode.setECLevel(paramErrorCorrectionLevel);
    localQRCode.setMode(localMode);
    localQRCode.setVersion(localVersion2);
    int k = localVersion2.getDimensionForVersion();
    ByteMatrix localByteMatrix = new ByteMatrix(k, k);
    int m = chooseMaskPattern(localBitArray4, paramErrorCorrectionLevel, localVersion2, localByteMatrix);
    localQRCode.setMaskPattern(m);
    MatrixUtil.buildMatrix(localBitArray4, paramErrorCorrectionLevel, localVersion2, m, localByteMatrix);
    localQRCode.setMatrix(localByteMatrix);
    return localQRCode;
  }

  static byte[] generateECBytes(byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramArrayOfByte.length;
    int[] arrayOfInt = new int[i + paramInt];
    int j = 0;
    for (int k = 0; k < i; k++)
      arrayOfInt[k] = (0xFF & paramArrayOfByte[k]);
    new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(arrayOfInt, paramInt);
    byte[] arrayOfByte = new byte[paramInt];
    while (j < paramInt)
    {
      arrayOfByte[j] = (byte)arrayOfInt[(i + j)];
      j++;
    }
    return arrayOfByte;
  }

  static int getAlphanumericCode(int paramInt)
  {
    if (paramInt < ALPHANUMERIC_TABLE.length)
      return ALPHANUMERIC_TABLE[paramInt];
    return -1;
  }

  static void getNumDataBytesAndNumECBytesForBlockID(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (paramInt4 >= paramInt3)
      throw new WriterException("Block ID too large");
    int i = paramInt1 % paramInt3;
    int j = paramInt3 - i;
    int k = paramInt1 / paramInt3;
    int m = k + 1;
    int n = paramInt2 / paramInt3;
    int i1 = n + 1;
    int i2 = k - n;
    int i3 = m - i1;
    if (i2 != i3)
      throw new WriterException("EC bytes mismatch");
    if (paramInt3 != j + i)
      throw new WriterException("RS blocks mismatch");
    if (paramInt1 != j * (n + i2) + i * (i1 + i3))
      throw new WriterException("Total bytes mismatch");
    if (paramInt4 < j)
    {
      paramArrayOfInt1[0] = n;
      paramArrayOfInt2[0] = i2;
    }
    else
    {
      paramArrayOfInt1[0] = i1;
      paramArrayOfInt2[0] = i3;
    }
  }

  static BitArray interleaveWithECBytes(BitArray paramBitArray, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBitArray.getSizeInBytes() != paramInt2)
      throw new WriterException("Number of bits and data bytes does not match");
    ArrayList localArrayList = new ArrayList(paramInt3);
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    while (i < paramInt3)
    {
      int[] arrayOfInt1 = new int[1];
      int[] arrayOfInt2 = new int[1];
      getNumDataBytesAndNumECBytesForBlockID(paramInt1, paramInt2, paramInt3, i, arrayOfInt1, arrayOfInt2);
      int i2 = arrayOfInt1[0];
      byte[] arrayOfByte3 = new byte[i2];
      paramBitArray.toBytes(j * 8, arrayOfByte3, 0, i2);
      byte[] arrayOfByte4 = generateECBytes(arrayOfByte3, arrayOfInt2[0]);
      localArrayList.add(new BlockPair(arrayOfByte3, arrayOfByte4));
      k = Math.max(k, i2);
      m = Math.max(m, arrayOfByte4.length);
      j += arrayOfInt1[0];
      i++;
    }
    if (paramInt2 != j)
      throw new WriterException("Data bytes does not match offset");
    BitArray localBitArray = new BitArray();
    int i1;
    for (int n = 0; ; n++)
    {
      i1 = 0;
      if (n >= k)
        break;
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
      {
        byte[] arrayOfByte2 = ((BlockPair)localIterator2.next()).getDataBytes();
        if (n < arrayOfByte2.length)
          localBitArray.appendBits(arrayOfByte2[n], 8);
      }
    }
    while (i1 < m)
    {
      Iterator localIterator1 = localArrayList.iterator();
      while (localIterator1.hasNext())
      {
        byte[] arrayOfByte1 = ((BlockPair)localIterator1.next()).getErrorCorrectionBytes();
        if (i1 < arrayOfByte1.length)
          localBitArray.appendBits(arrayOfByte1[i1], 8);
      }
      i1++;
    }
    if (paramInt1 != localBitArray.getSizeInBytes())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Interleaving error: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(" and ");
      localStringBuilder.append(localBitArray.getSizeInBytes());
      localStringBuilder.append(" differ.");
      throw new WriterException(localStringBuilder.toString());
    }
    return localBitArray;
  }

  // ERROR //
  private static boolean isOnlyDoubleByteKanji(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 113
    //   3: invokevirtual 27	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   6: astore_1
    //   7: aload_1
    //   8: arraylength
    //   9: istore_2
    //   10: iload_2
    //   11: iconst_2
    //   12: irem
    //   13: ifeq +5 -> 18
    //   16: iconst_0
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_3
    //   20: iload_3
    //   21: iload_2
    //   22: if_icmpge +55 -> 77
    //   25: sipush 255
    //   28: aload_1
    //   29: iload_3
    //   30: baload
    //   31: iand
    //   32: istore 4
    //   34: iload 4
    //   36: sipush 129
    //   39: if_icmplt +11 -> 50
    //   42: iload 4
    //   44: sipush 159
    //   47: if_icmple +22 -> 69
    //   50: iload 4
    //   52: sipush 224
    //   55: if_icmplt +20 -> 75
    //   58: iload 4
    //   60: sipush 235
    //   63: if_icmple +6 -> 69
    //   66: goto +9 -> 75
    //   69: iinc 3 2
    //   72: goto -52 -> 20
    //   75: iconst_0
    //   76: ireturn
    //   77: iconst_1
    //   78: ireturn
    //   79: iconst_0
    //   80: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	79	java/io/UnsupportedEncodingException
  }

  static void terminateBits(int paramInt, BitArray paramBitArray)
  {
    int i = paramInt << 3;
    if (paramBitArray.getSize() > i)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("data bits cannot fit in the QR Code");
      localStringBuilder.append(paramBitArray.getSize());
      localStringBuilder.append(" > ");
      localStringBuilder.append(i);
      throw new WriterException(localStringBuilder.toString());
    }
    int j = 0;
    for (int k = 0; (k < 4) && (paramBitArray.getSize() < i); k++)
      paramBitArray.appendBit(false);
    int m = 0x7 & paramBitArray.getSize();
    if (m > 0)
      while (m < 8)
      {
        paramBitArray.appendBit(false);
        m++;
      }
    int n = paramInt - paramBitArray.getSizeInBytes();
    while (j < n)
    {
      int i1;
      if ((j & 0x1) == 0)
        i1 = 236;
      else
        i1 = 17;
      paramBitArray.appendBits(i1, 8);
      j++;
    }
    if (paramBitArray.getSize() != i)
      throw new WriterException("Bits size does not equal capacity");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.Encoder
 * JD-Core Version:    0.6.1
 */