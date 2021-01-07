package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension5Support
{
  private static final int[] CHECK_DIGIT_ENCODINGS = { 24, 20, 18, 17, 12, 6, 3, 10, 9, 5 };
  private final int[] decodeMiddleCounters = new int[4];
  private final StringBuilder decodeRowStringBuffer = new StringBuilder();

  private static int determineCheckDigit(int paramInt)
  {
    for (int i = 0; i < 10; i++)
      if (paramInt == CHECK_DIGIT_ENCODINGS[i])
        return i;
    throw NotFoundException.getNotFoundInstance();
  }

  private static int extensionChecksum(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    int j = i - 2;
    int k = 0;
    while (j >= 0)
    {
      k += '￐' + paramCharSequence.charAt(j);
      j -= 2;
    }
    int m = k * 3;
    for (int n = i - 1; n >= 0; n -= 2)
      m += '￐' + paramCharSequence.charAt(n);
    return m * 3 % 10;
  }

  private static String parseExtension5String(String paramString)
  {
    int i = paramString.charAt(0);
    String str1;
    if (i != 48)
    {
      if (i != 53)
      {
        if (i != 57)
        {
          str1 = "";
        }
        else
        {
          if ("90000".equals(paramString))
            return null;
          if ("99991".equals(paramString))
            return "0.00";
          if ("99990".equals(paramString))
            return "Used";
          str1 = "";
        }
      }
      else
        str1 = "$";
    }
    else
      str1 = "£";
    int j = Integer.parseInt(paramString.substring(1));
    String str2 = String.valueOf(j / 100);
    int k = j % 100;
    String str3;
    if (k < 10)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("0");
      localStringBuilder1.append(k);
      str3 = localStringBuilder1.toString();
    }
    else
    {
      str3 = String.valueOf(k);
    }
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(str1);
    localStringBuilder2.append(str2);
    localStringBuilder2.append('.');
    localStringBuilder2.append(str3);
    return localStringBuilder2.toString();
  }

  private static Map<ResultMetadataType, Object> parseExtensionString(String paramString)
  {
    if (paramString.length() != 5)
      return null;
    String str = parseExtension5String(paramString);
    if (str == null)
      return null;
    EnumMap localEnumMap = new EnumMap(ResultMetadataType.class);
    localEnumMap.put(ResultMetadataType.SUGGESTED_PRICE, str);
    return localEnumMap;
  }

  int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int i = paramBitArray.getSize();
    int j = paramArrayOfInt[1];
    int k = 0;
    int m = 0;
    while ((k < 5) && (j < i))
    {
      int i1 = UPCEANReader.decodeDigit(paramBitArray, arrayOfInt, j, UPCEANReader.L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(48 + i1 % 10));
      int i2 = arrayOfInt.length;
      int i3 = j;
      for (int i4 = 0; i4 < i2; i4++)
        i3 += arrayOfInt[i4];
      if (i1 >= 10)
        m |= 1 << 4 - k;
      if (k != 4)
        j = paramBitArray.getNextUnset(paramBitArray.getNextSet(i3));
      else
        j = i3;
      k++;
    }
    if (paramStringBuilder.length() != 5)
      throw NotFoundException.getNotFoundInstance();
    int n = determineCheckDigit(m);
    if (extensionChecksum(paramStringBuilder.toString()) != n)
      throw NotFoundException.getNotFoundInstance();
    return j;
  }

  Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt)
  {
    StringBuilder localStringBuilder = this.decodeRowStringBuffer;
    localStringBuilder.setLength(0);
    int i = decodeMiddle(paramBitArray, paramArrayOfInt, localStringBuilder);
    String str = localStringBuilder.toString();
    Map localMap = parseExtensionString(str);
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    float f1 = paramArrayOfInt[0] + paramArrayOfInt[1] / 2.0F;
    float f2 = paramInt;
    arrayOfResultPoint[0] = new ResultPoint(f1, f2);
    arrayOfResultPoint[1] = new ResultPoint(i, f2);
    Result localResult = new Result(str, null, arrayOfResultPoint, BarcodeFormat.UPC_EAN_EXTENSION);
    if (localMap != null)
      localResult.putAllMetadata(localMap);
    return localResult;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANExtension5Support
 * JD-Core Version:    0.6.1
 */