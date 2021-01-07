package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader
{
  static final int[][] L_AND_G_PATTERNS;
  static final int[][] L_PATTERNS;
  private static final int MAX_AVG_VARIANCE = 122;
  private static final int MAX_INDIVIDUAL_VARIANCE = 179;
  static final int[] MIDDLE_PATTERN;
  static final int[] START_END_PATTERN = { 1, 1, 1 };
  private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
  private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
  private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

  static
  {
    MIDDLE_PATTERN = new int[] { 1, 1, 1, 1, 1 };
    int i = 10;
    int[][] arrayOfInt = new int[i][];
    arrayOfInt[0] = { 3, 2, 1, 1 };
    arrayOfInt[1] = { 2, 2, 2, 1 };
    arrayOfInt[2] = { 2, 1, 2, 2 };
    arrayOfInt[3] = { 1, 4, 1, 1 };
    arrayOfInt[4] = { 1, 1, 3, 2 };
    arrayOfInt[5] = { 1, 2, 3, 1 };
    arrayOfInt[6] = { 1, 1, 1, 4 };
    arrayOfInt[7] = { 1, 3, 1, 2 };
    arrayOfInt[8] = { 1, 2, 1, 3 };
    arrayOfInt[9] = { 3, 1, 1, 2 };
    L_PATTERNS = arrayOfInt;
    L_AND_G_PATTERNS = new int[20][];
    System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, i);
    while (i < 20)
    {
      int[] arrayOfInt1 = L_PATTERNS[(i - 10)];
      int[] arrayOfInt2 = new int[arrayOfInt1.length];
      for (int j = 0; j < arrayOfInt1.length; j++)
        arrayOfInt2[j] = arrayOfInt1[(arrayOfInt1.length - j - 1)];
      L_AND_G_PATTERNS[i] = arrayOfInt2;
      i++;
    }
  }

  static boolean checkStandardUPCEANChecksum(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    if (i == 0)
      return false;
    int j = i - 2;
    int k = 0;
    while (j >= 0)
    {
      int i3 = '￐' + paramCharSequence.charAt(j);
      if ((i3 >= 0) && (i3 <= 9))
      {
        k += i3;
        j -= 2;
      }
      else
      {
        throw FormatException.getFormatInstance();
      }
    }
    int m = k * 3;
    int n = i - 1;
    while (n >= 0)
    {
      int i2 = '￐' + paramCharSequence.charAt(n);
      if ((i2 >= 0) && (i2 <= 9))
      {
        m += i2;
        n -= 2;
      }
      else
      {
        throw FormatException.getFormatInstance();
      }
    }
    int i1 = m % 10;
    boolean bool = false;
    if (i1 == 0)
      bool = true;
    return bool;
  }

  static int decodeDigit(BitArray paramBitArray, int[] paramArrayOfInt, int paramInt, int[][] paramArrayOfInt1)
  {
    recordPattern(paramBitArray, paramInt, paramArrayOfInt);
    int i = paramArrayOfInt1.length;
    int j = 122;
    int k = -1;
    for (int m = 0; m < i; m++)
    {
      int n = patternMatchVariance(paramArrayOfInt, paramArrayOfInt1[m], 179);
      if (n < j)
      {
        k = m;
        j = n;
      }
    }
    if (k >= 0)
      return k;
    throw NotFoundException.getNotFoundInstance();
  }

  static int[] findGuardPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean, int[] paramArrayOfInt)
  {
    return findGuardPattern(paramBitArray, paramInt, paramBoolean, paramArrayOfInt, new int[paramArrayOfInt.length]);
  }

  private static int[] findGuardPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = paramArrayOfInt1.length;
    int j = paramBitArray.getSize();
    int k;
    if (paramBoolean)
      k = paramBitArray.getNextUnset(paramInt);
    else
      k = paramBitArray.getNextSet(paramInt);
    int m = k;
    int n = 0;
    while (k < j)
    {
      boolean bool1 = paramBoolean ^ paramBitArray.get(k);
      boolean bool2 = true;
      if (bool1)
      {
        paramArrayOfInt2[n] = (bool2 + paramArrayOfInt2[n]);
      }
      else
      {
        int i1 = i - 1;
        if (n == i1)
        {
          if (patternMatchVariance(paramArrayOfInt2, paramArrayOfInt1, 179) < 122)
          {
            int[] arrayOfInt = new int[2];
            arrayOfInt[0] = m;
            arrayOfInt[bool2] = k;
            return arrayOfInt;
          }
          m += paramArrayOfInt2[0] + paramArrayOfInt2[bool2];
          int i2 = i - 2;
          System.arraycopy(paramArrayOfInt2, 2, paramArrayOfInt2, 0, i2);
          paramArrayOfInt2[i2] = 0;
          paramArrayOfInt2[i1] = 0;
          n--;
        }
        else
        {
          n++;
        }
        paramArrayOfInt2[n] = bool2;
        if (paramBoolean)
          bool2 = false;
        paramBoolean = bool2;
      }
      k++;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  static int[] findStartGuardPattern(BitArray paramBitArray)
  {
    int[] arrayOfInt1 = new int[START_END_PATTERN.length];
    int[] arrayOfInt2 = null;
    boolean bool = false;
    int k;
    for (int i = 0; !bool; i = k)
    {
      Arrays.fill(arrayOfInt1, 0, START_END_PATTERN.length, 0);
      arrayOfInt2 = findGuardPattern(paramBitArray, i, false, START_END_PATTERN, arrayOfInt1);
      int j = arrayOfInt2[0];
      k = arrayOfInt2[1];
      int m = j - (k - j);
      if (m >= 0)
        bool = paramBitArray.isRange(m, j, false);
    }
    return arrayOfInt2;
  }

  boolean checkChecksum(String paramString)
  {
    return checkStandardUPCEANChecksum(paramString);
  }

  int[] decodeEnd(BitArray paramBitArray, int paramInt)
  {
    return findGuardPattern(paramBitArray, paramInt, false, START_END_PATTERN);
  }

  protected abstract int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder);

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    return decodeRow(paramInt, paramBitArray, findStartGuardPattern(paramBitArray), paramMap);
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt, Map<DecodeHintType, ?> paramMap)
  {
    ResultPointCallback localResultPointCallback;
    if (paramMap == null)
      localResultPointCallback = null;
    else
      localResultPointCallback = (ResultPointCallback)paramMap.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
    if (localResultPointCallback != null)
      localResultPointCallback.foundPossibleResultPoint(new ResultPoint(paramArrayOfInt[0] + paramArrayOfInt[1] / 2.0F, paramInt));
    StringBuilder localStringBuilder = this.decodeRowStringBuffer;
    localStringBuilder.setLength(0);
    int i = decodeMiddle(paramBitArray, paramArrayOfInt, localStringBuilder);
    if (localResultPointCallback != null)
      localResultPointCallback.foundPossibleResultPoint(new ResultPoint(i, paramInt));
    int[] arrayOfInt = decodeEnd(paramBitArray, i);
    if (localResultPointCallback != null)
      localResultPointCallback.foundPossibleResultPoint(new ResultPoint(arrayOfInt[0] + arrayOfInt[1] / 2.0F, paramInt));
    int j = arrayOfInt[1];
    int k = j + (j - arrayOfInt[0]);
    if ((k < paramBitArray.getSize()) && (paramBitArray.isRange(j, k, false)))
    {
      String str1 = localStringBuilder.toString();
      if (!checkChecksum(str1))
        throw ChecksumException.getChecksumInstance();
      float f1 = paramArrayOfInt[1] + paramArrayOfInt[0] / 2.0F;
      float f2 = arrayOfInt[1] + arrayOfInt[0] / 2.0F;
      BarcodeFormat localBarcodeFormat = getBarcodeFormat();
      ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
      float f3 = paramInt;
      arrayOfResultPoint[0] = new ResultPoint(f1, f3);
      arrayOfResultPoint[1] = new ResultPoint(f2, f3);
      Result localResult1 = new Result(str1, null, arrayOfResultPoint, localBarcodeFormat);
      try
      {
        Result localResult2 = this.extensionReader.decodeRow(paramInt, paramBitArray, arrayOfInt[1]);
        localResult1.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, localResult2.getText());
        localResult1.putAllMetadata(localResult2.getResultMetadata());
        localResult1.addResultPoints(localResult2.getResultPoints());
      }
      catch (ReaderException localReaderException)
      {
      }
      if ((localBarcodeFormat == BarcodeFormat.EAN_13) || (localBarcodeFormat == BarcodeFormat.UPC_A))
      {
        String str2 = this.eanManSupport.lookupCountryIdentifier(str1);
        if (str2 != null)
          localResult1.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, str2);
      }
      return localResult1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  abstract BarcodeFormat getBarcodeFormat();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANReader
 * JD-Core Version:    0.6.1
 */