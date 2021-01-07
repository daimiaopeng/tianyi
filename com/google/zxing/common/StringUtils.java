package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Map;

public final class StringUtils
{
  private static final boolean ASSUME_SHIFT_JIS = false;
  private static final String EUC_JP = "EUC_JP";
  public static final String GB2312 = "GB2312";
  private static final String ISO88591 = "ISO8859_1";
  private static final String PLATFORM_DEFAULT_ENCODING = System.getProperty("file.encoding");
  public static final String SHIFT_JIS = "SJIS";
  private static final String UTF8 = "UTF8";

  static
  {
    boolean bool;
    if ((!"SJIS".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING)) && (!"EUC_JP".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING)))
      bool = false;
    else
      bool = true;
  }

  public static String guessEncoding(byte[] paramArrayOfByte, Map<DecodeHintType, ?> paramMap)
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramMap != null)
    {
      String str2 = (String)paramMap.get(DecodeHintType.CHARACTER_SET);
      if (str2 != null)
        return str2;
    }
    int i = arrayOfByte.length;
    int j = arrayOfByte.length;
    int k = 0;
    int m;
    if ((j > 3) && (arrayOfByte[0] == -17) && (arrayOfByte[1] == -69) && (arrayOfByte[2] == -65))
      m = 1;
    else
      m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 1;
    int i3 = 1;
    int i4 = 1;
    int i5 = 0;
    int i6 = 0;
    int i7 = 0;
    int i8 = 0;
    int i9 = 0;
    int i10 = 0;
    int i11 = 0;
    int i12 = 0;
    int i13 = 0;
    while ((i1 < i) && ((i2 != 0) || (i3 != 0) || (i4 != 0)))
    {
      int i15 = 0xFF & arrayOfByte[i1];
      if (i4 != 0)
      {
        if (i5 > 0)
          if ((i15 & 0x80) != 0);
        do
        {
          do
          {
            i4 = 0;
            break;
            i5--;
            break;
            if ((i15 & 0x80) == 0)
              break;
          }
          while ((i15 & 0x40) == 0);
          i5++;
          if ((i15 & 0x20) == 0)
          {
            i7++;
            break;
          }
          i5++;
          if ((i15 & 0x10) == 0)
          {
            i8++;
            break;
          }
          i5++;
        }
        while ((i15 & 0x8) != 0);
        i9++;
      }
      if (i2 != 0)
        if ((i15 > 127) && (i15 < 160))
          i2 = 0;
        else if ((i15 > 159) && ((i15 < 192) || (i15 == 215) || (i15 == 247)))
          i11++;
      if (i3 != 0)
      {
        if (i6 > 0)
          if ((i15 >= 64) && (i15 != 127) && (i15 <= 252))
          {
            i6--;
            break label487;
          }
        while ((i15 == 128) || (i15 == 160) || (i15 > 239))
        {
          i3 = 0;
          break;
        }
        int i17;
        if ((i15 > 160) && (i15 < 224))
        {
          n++;
          i17 = i12 + 1;
          if (i17 > i10)
          {
            i10 = i17;
            i12 = i10;
          }
        }
        for (i12 = i17; ; i12 = 0)
        {
          i13 = 0;
          break;
          if (i15 > 127)
          {
            i6++;
            int i16 = i13 + 1;
            if (i16 > k)
            {
              k = i16;
              i13 = k;
            }
            else
            {
              i13 = i16;
            }
            i12 = 0;
            break;
          }
        }
      }
      label487: i1++;
      arrayOfByte = paramArrayOfByte;
    }
    if ((i4 != 0) && (i5 > 0))
      i4 = 0;
    int i14;
    if ((i3 != 0) && (i6 > 0))
      i14 = 0;
    else
      i14 = i3;
    if ((i4 != 0) && ((m != 0) || (i9 + (i7 + i8) > 0)))
      return "UTF8";
    if ((i14 != 0) && ((ASSUME_SHIFT_JIS) || (i10 >= 3) || (k >= 3)))
      return "SJIS";
    if ((i2 != 0) && (i14 != 0))
    {
      String str1;
      if (((i10 == 2) && (n == 2)) || (i11 * 10 >= i))
        str1 = "SJIS";
      else
        str1 = "ISO8859_1";
      return str1;
    }
    if (i2 != 0)
      return "ISO8859_1";
    if (i14 != 0)
      return "SJIS";
    if (i4 != 0)
      return "UTF8";
    return PLATFORM_DEFAULT_ENCODING;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.StringUtils
 * JD-Core Version:    0.6.1
 */