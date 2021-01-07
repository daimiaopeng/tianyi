package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.DecoderResult;
import java.text.DecimalFormat;
import java.text.NumberFormat;

final class DecodedBitStreamParser
{
  private static final char ECI = '￺';
  private static final char FS = '\034';
  private static final char GS = '\035';
  private static final char LATCHA = '￷';
  private static final char LATCHB = '￸';
  private static final char LOCK = '￹';
  private static final NumberFormat NINE_DIGITS = new DecimalFormat("000000000");
  private static final char NS = '￻';
  private static final char PAD = '￼';
  private static final char RS = '\036';
  private static final String[] SETS = { "\nABCDEFGHIJKLMNOPQRSTUVWXYZ￺\034\035\036￻ ￼\"#$%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\034\035\036￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\034\035\036ÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\034\035\036￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "", "" };
  private static final char SHIFTA = '￰';
  private static final char SHIFTB = '￱';
  private static final char SHIFTC = '￲';
  private static final char SHIFTD = '￳';
  private static final char SHIFTE = '￴';
  private static final char THREESHIFTA = '￶';
  private static final NumberFormat THREE_DIGITS = new DecimalFormat("000");
  private static final char TWOSHIFTA = '￵';

  static DecoderResult decode(byte[] paramArrayOfByte, int paramInt)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(144);
    switch (paramInt)
    {
    default:
      break;
    case 5:
      localStringBuilder1.append(getMessage(paramArrayOfByte, 1, 77));
      break;
    case 4:
      localStringBuilder1.append(getMessage(paramArrayOfByte, 1, 93));
      break;
    case 2:
    case 3:
      String str1;
      if (paramInt == 2)
      {
        int i = getPostCode2(paramArrayOfByte);
        str1 = new DecimalFormat("0000000000".substring(0, getPostCode2Length(paramArrayOfByte))).format(i);
      }
      else
      {
        str1 = getPostCode3(paramArrayOfByte);
      }
      String str2 = THREE_DIGITS.format(getCountry(paramArrayOfByte));
      String str3 = THREE_DIGITS.format(getServiceClass(paramArrayOfByte));
      localStringBuilder1.append(getMessage(paramArrayOfByte, 10, 84));
      if (localStringBuilder1.toString().startsWith("[)>\03601\035"))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(str1);
        localStringBuilder2.append('\035');
        localStringBuilder2.append(str2);
        localStringBuilder2.append('\035');
        localStringBuilder2.append(str3);
        localStringBuilder2.append('\035');
        localStringBuilder1.insert(9, localStringBuilder2.toString());
      }
      else
      {
        StringBuilder localStringBuilder3 = new StringBuilder();
        localStringBuilder3.append(str1);
        localStringBuilder3.append('\035');
        localStringBuilder3.append(str2);
        localStringBuilder3.append('\035');
        localStringBuilder3.append(str3);
        localStringBuilder3.append('\035');
        localStringBuilder1.insert(0, localStringBuilder3.toString());
      }
      break;
    }
    return new DecoderResult(paramArrayOfByte, localStringBuilder1.toString(), null, String.valueOf(paramInt));
  }

  private static int getBit(int paramInt, byte[] paramArrayOfByte)
  {
    int i = paramInt - 1;
    int j = paramArrayOfByte[(i / 6)];
    int k = 5 - i % 6;
    int m = 1;
    if ((j & m << k) == 0)
      m = 0;
    return m;
  }

  private static int getCountry(byte[] paramArrayOfByte)
  {
    return getInt(paramArrayOfByte, new byte[] { 53, 54, 43, 44, 45, 46, 47, 48, 37, 38 });
  }

  private static int getInt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    int j = 0;
    while (i < paramArrayOfByte2.length)
    {
      j += (getBit(paramArrayOfByte2[i], paramArrayOfByte1) << -1 + (paramArrayOfByte2.length - i));
      i++;
    }
    return j;
  }

  private static String getMessage(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramInt1;
    int j = 0;
    int k = -1;
    int m = 0;
    while (i < paramInt1 + paramInt2)
    {
      int n = SETS[j].charAt(paramArrayOfByte[i]);
      switch (n)
      {
      case 65530:
      default:
        localStringBuilder.append(n);
        break;
      case 65531:
        int i3 = i + 1;
        int i4 = paramArrayOfByte[i3] << 24;
        int i5 = i3 + 1;
        int i6 = i4 + (paramArrayOfByte[i5] << 18);
        int i7 = i5 + 1;
        int i8 = i6 + (paramArrayOfByte[i7] << 12);
        int i9 = i7 + 1;
        int i10 = i8 + (paramArrayOfByte[i9] << 6);
        i = i9 + 1;
        int i11 = i10 + paramArrayOfByte[i];
        localStringBuilder.append(NINE_DIGITS.format(i11));
        break;
      case 65528:
        j = 1;
        break;
      case 65527:
        j = 0;
      case 65529:
        k = -1;
        break;
      case 65526:
        k = 3;
        break;
      case 65525:
        k = 2;
        m = j;
        j = 0;
        break;
      case 65520:
      case 65521:
      case 65522:
      case 65523:
      case 65524:
      }
      int i1 = n - 65520;
      m = j;
      j = i1;
      k = 1;
      int i2 = k - 1;
      if (k == 0)
        j = m;
      i++;
      k = i2;
    }
    while ((localStringBuilder.length() > 0) && (localStringBuilder.charAt(localStringBuilder.length() - 1) == 65532))
      localStringBuilder.setLength(localStringBuilder.length() - 1);
    return localStringBuilder.toString();
  }

  private static int getPostCode2(byte[] paramArrayOfByte)
  {
    return getInt(paramArrayOfByte, new byte[] { 33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2 });
  }

  private static int getPostCode2Length(byte[] paramArrayOfByte)
  {
    return getInt(paramArrayOfByte, new byte[] { 39, 40, 41, 42, 31, 32 });
  }

  private static String getPostCode3(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[6];
    arrayOfChar[0] = SETS[0].charAt(getInt(paramArrayOfByte, new byte[] { 39, 40, 41, 42, 31, 32 }));
    arrayOfChar[1] = SETS[0].charAt(getInt(paramArrayOfByte, new byte[] { 33, 34, 35, 36, 25, 26 }));
    arrayOfChar[2] = SETS[0].charAt(getInt(paramArrayOfByte, new byte[] { 27, 28, 29, 30, 19, 20 }));
    arrayOfChar[3] = SETS[0].charAt(getInt(paramArrayOfByte, new byte[] { 21, 22, 23, 24, 13, 14 }));
    arrayOfChar[4] = SETS[0].charAt(getInt(paramArrayOfByte, new byte[] { 15, 16, 17, 18, 7, 8 }));
    arrayOfChar[5] = SETS[0].charAt(getInt(paramArrayOfByte, new byte[] { 9, 10, 11, 12, 1, 2 }));
    return String.valueOf(arrayOfChar);
  }

  private static int getServiceClass(byte[] paramArrayOfByte)
  {
    return getInt(paramArrayOfByte, new byte[] { 55, 56, 57, 58, 59, 60, 49, 50, 51, 52 });
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.maxicode.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.1
 */