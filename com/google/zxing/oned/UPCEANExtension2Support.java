package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension2Support
{
  private final int[] decodeMiddleCounters = new int[4];
  private final StringBuilder decodeRowStringBuffer = new StringBuilder();

  private static Map<ResultMetadataType, Object> parseExtensionString(String paramString)
  {
    if (paramString.length() != 2)
      return null;
    EnumMap localEnumMap = new EnumMap(ResultMetadataType.class);
    localEnumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(paramString));
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
    while ((k < 2) && (j < i))
    {
      int n = UPCEANReader.decodeDigit(paramBitArray, arrayOfInt, j, UPCEANReader.L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(48 + n % 10));
      int i1 = arrayOfInt.length;
      int i2 = j;
      for (int i3 = 0; i3 < i1; i3++)
        i2 += arrayOfInt[i3];
      if (n >= 10)
        m |= 1 << 1 - k;
      if (k != 1)
        j = paramBitArray.getNextUnset(paramBitArray.getNextSet(i2));
      else
        j = i2;
      k++;
    }
    if (paramStringBuilder.length() != 2)
      throw NotFoundException.getNotFoundInstance();
    if (Integer.parseInt(paramStringBuilder.toString()) % 4 != m)
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
 * Qualified Name:     com.google.zxing.oned.UPCEANExtension2Support
 * JD-Core Version:    0.6.1
 */